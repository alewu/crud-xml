package com.koko.crud.util.freemarker.util;

import com.koko.crud.util.freemarker.bean.Field;
import com.koko.crud.util.freemarker.bean.TableMetaData;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TableUtils {
    public static final String TABLE = "table";
    public static final String FILED = "filed";


    private static final String REGEX = "_";

    private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String TABLE_NAME = "people";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet getResultSet() {
        try {
            Connection connection = TableUtils.getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            return databaseMetaData.getColumns(null, "%", "%", "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<TableMetaData> getTableMetaData() throws SQLException {
        TableMetaData tableMetaData = null;
        Field field = null;
        Connection connection = TableUtils.getConnection();
        DatabaseMetaData dbMetData = connection.getMetaData();
        ResultSet rs = dbMetData.getTables(null, "%", "%", new String[]{"TABLE"});
        List<TableMetaData> tableMetaDatas = new LinkedList<>();
        while (rs.next()) {
            tableMetaData = new TableMetaData();
            // 获取表名
            String tableName = rs.getString("TABLE_NAME");
            tableMetaData.setTableName(tableName);
            tableMetaData.setEntityName(TableUtils.processName(tableName,TableUtils.TABLE));
            // 根据表名提取表里面信息
            ResultSet colRS = dbMetData.getColumns(null, "%", tableName, "%");
            List<Field> fields = new LinkedList<>();
            while (colRS.next()) {
                field = new Field();
                String columnName = colRS.getString("COLUMN_NAME");
                String typeName = colRS.getString("TYPE_NAME");
                String remarks = colRS.getString("REMARKS");
                field.setColumnName(columnName);
                field.setMemberVariable(TableUtils.processName(columnName,FILED));
                field.setTypeName(typeName);
                field.setRemarks(remarks);
                fields.add(field);
            }
            tableMetaData.setFields(fields);
            tableMetaDatas.add(tableMetaData);
        }
        return tableMetaDatas;
    }

    public static String processName(String str, String target) {
        String[] words = str.split(REGEX);
        StringBuilder sb = new StringBuilder(words.length);
        String firstWord = StringUtils.uncapitalize(words[0].trim());
        sb.append(firstWord);
        for (int i = 1; i < words.length; i++) {
            String otherWord = StringUtils.capitalize(words[i].trim());
            sb.append(otherWord);
        }
        if (TABLE.equals(target)) {
            return sb.toString().replace(firstWord, "");
        }
        if (FILED.equals(target)) {
            return sb.toString();
        }
        return null;
    }
}
