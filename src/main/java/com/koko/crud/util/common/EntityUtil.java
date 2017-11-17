package com.koko.crud.util.common;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * 自动生成MyBatis的实体类、实体映射XML文件、Mapper
 *
 * @author chenkai
 * @version v1.0
 * @date 2017-08-14
 */
public class EntityUtil {

    /**
     * ********************************* 使用前必读*******************
     * *
     * * 使用前请将entityName更改为自己模块的名称即可（一般情况下与数据库名一致），其他无须改动。
     * *
     * **********************************************************
     */

    private final String type_char = "char";

    private final String type_datetime = "datetime";

    private final String type_timestamp = "timestamp";

    private final String type_int = "int";

    private final String type_bigint = "bigint";

    private final String type_text = "text";

    private final String type_bit = "bit";

    private final String type_decimal = "decimal";

    private final String type_double = "double";

    private final String type_blob = "blob";

    private String date = null;

    private final String author = "alewu";

    private final String entityName = "rent"; // 对应模块名称（根据自己模块做相应调整!!!务必修改^_^）

    private final String bean_path = "C:/Users/hs/Desktop/rent/src/main/java/com/sinsinet/entity";

    private final String mapper_path = "C:/Users/hs/Desktop/rent/src/main/java/com/sinsinet/dao";

    private final String service_path = "C:/Users/hs/Desktop/rent/src/main/java/com/sinsinet/service";

    private final String serviceImp_path = "C:/Users/hs/Desktop/rent/src/main/java/com/sinsinet/service/impl";

    private final String xml_path = "C:/Users/hs/Desktop/rent/src/main/resources/mapper";

    private final String bean_package = "com.sinsinet.entity";

    private final String mapper_package = "com.sinsinet.dao";

    private final String service_package = "com.sinsinet.service";

    private final String service_imp_package = "com.sinsinet.service.impl";

    private final String driverName = "com.mysql.jdbc.Driver";

    private final String user = "root";

    private final String password = "root";

    private final String url = "jdbc:mysql://test.sinsinet.com:3313/rent?serverTimezone=UTC&characterEncoding=utf-8";

    private String tableName = null;

    private String beanName = null;

    private String mapperName = null;

    private String serviceName = null;

    private String serviceImpName = null;

    private Connection conn = null;

    private void init() throws ClassNotFoundException, SQLException {
        Date nowDate = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sd.format(nowDate);
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("数据库连接成功");
    }

    /**
     * 获取所有的表
     *
     * @return
     * @throws SQLException
     */
    private List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<String>();
        PreparedStatement pstate = conn.prepareStatement("show tables");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString(1);
            // if ( tableName.toLowerCase().startsWith("yy_") ) {
            tables.add(tableName);
            // }
        }
        return tables;
    }

    private void processTable(String table) {
        StringBuffer sb = new StringBuffer(table.length());
        String tableNew = table.toLowerCase();
        System.out.println(tableNew);
        String[] tables = tableNew.replace("tb_", "").split("_");
        String temp = null;
        for (int i = 0; i < tables.length; i++) {
            temp = tables[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(
                    temp.substring(1));
        }
        beanName = sb.toString();

        System.out.println("给类命名：" + beanName);
        mapperName = beanName + "Dao";
        serviceName = beanName + "Service";
        serviceImpName = beanName + "ServiceImp";
    }

    private String processType(String type) {
        if (type.indexOf(type_char) > -1) {
            return "String";
        } else if (type.indexOf(type_bigint) > -1) {
            return "Long";
        } else if (type.indexOf(type_int) > -1) {
            return "Integer";
        } else if (type.indexOf(type_datetime) > -1) {
            return "java.util.Date";
        } else if (type.indexOf(type_text) > -1) {
            return "String";
        } else if (type.indexOf(type_timestamp) > -1) {
            return "java.util.Date";
        } else if (type.indexOf(type_bit) > -1) {
            return "Boolean";
        } else if (type.indexOf(type_decimal) > -1) {
            return "java.math.BigDecimal";
        } else if (type.indexOf(type_double) > -1) {
            return "Double";
        } else if (type.indexOf(type_blob) > -1) {
            return "byte[]";
        }
        return null;
    }

    private String processField(String field) {
        StringBuffer sb = new StringBuffer(field.length());
        String[] fields = field.split("_");
        String temp = null;
        sb.append(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            temp = fields[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(
                    temp.substring(1));
        }
        return sb.toString();
    }

    /**
     * 将实体类名首字母改为小写
     *
     * @param beanName
     * @return
     */
    @SuppressWarnings("unused")
    private String processResultMapId(String beanName) {
        return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
    }

    /**
     * 构建类上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws IOException
     */
    private BufferedWriter buildClassComment(BufferedWriter bw, String text)
            throws IOException {
        bw.newLine();
        bw.write("/**");
        bw.newLine();
        bw.write(" * ");
        bw.newLine();
        bw.write(" * @author: " + author);
        bw.newLine();
        bw.write(" * @date " + date);
        bw.newLine();
        bw.write(" * @description " + text);
        bw.newLine();
        bw.write(" **/");
        return bw;
    }

    /**
     * 构建方法上面的注释：set/get方法注释
     *
     * @param bw
     * @param tempType
     * @return
     * @throws IOException
     */
    private BufferedWriter buildMethodComment(BufferedWriter bw,
                                              String tempType, String Description) throws IOException {
        bw.write("\t/**");
        bw.newLine();
        bw.write("\t * @author: " + author);
        bw.newLine();
        bw.write("\t * @return: " + tempType);
        bw.newLine();
        bw.write("\t * @Description: " + Description);
        bw.newLine();
        bw.write("\t * @date:" + date);
        bw.newLine();
        bw.write("\t **/");
        return bw;
    }

    /**
     * 构建方法上面的注释：一个参数一个返回类型一个描述
     *
     * @param bw
     * @param result
     * @return
     * @throws IOException
     */
    private BufferedWriter buildMethodComment(BufferedWriter bw, String param,
                                              String result, String Description) throws IOException {
        bw.write("\t/**");
        bw.newLine();
        bw.write("\t * @author: " + author);
        bw.newLine();
        bw.write("\t * @param: " + param);
        bw.newLine();
        bw.write("\t * @return: " + result);
        bw.newLine();
        bw.write("\t * @description: " + Description);
        bw.newLine();
        bw.write("\t * @date:" + date);
        bw.newLine();
        bw.write("\t **/");
        return bw;
    }

    /**
     * 构建方法上面的注释：三个个参数一个返回类型一个描述
     *
     * @param bw
     * @param result
     * @return
     * @throws IOException
     */
    private BufferedWriter buildMethodComment(BufferedWriter bw, String param1,
                                              String param2, String param3, String result, String Description)
            throws IOException {
        bw.write("\t/**");
        bw.newLine();
        bw.write("\t * @author: " + author);
        bw.newLine();
        bw.write("\t * @param: " + param1);
        bw.newLine();
        bw.write("\t * @param: " + param2);
        bw.newLine();
        bw.write("\t * @param: " + param3);
        bw.newLine();
        bw.write("\t * @return: " + result);
        bw.newLine();
        bw.write("\t * @description: " + Description);
        bw.newLine();
        bw.write("\t * @date:" + date);
        bw.newLine();
        bw.write("\t **/");
        return bw;
    }

    /**
     * 生成实体类
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    @SuppressWarnings("resource")
    private void buildEntityBean(List<String> columns, List<String> types,
                                 List<String> comments, String tableComment) throws IOException {
        File folder = new File(bean_path);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File beanFile = new File(bean_path, beanName + ".java");

        if (beanName == null || "".equals(beanName)) {
            return;

        }
        if (beanFile.exists()) {
            return;
        }
        System.out.println(beanFile.getAbsolutePath());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(beanFile)));
        bw.write("package " + bean_package + ";");
        bw.newLine();
        bw.write("import java.io.Serializable;");
        bw.newLine();
        bw = buildClassComment(bw, beanName + "  数据库表对应的实体类");
        bw.newLine();
        bw.write("@SuppressWarnings(\"serial\")");
        bw.newLine();
        bw.write("public class " + beanName + " implements Serializable {");
        bw.newLine();
        bw.newLine();
        int size = columns.size();
        for (int i = 0; i < size; i++) {
            bw.write("\t/**" + comments.get(i) + "**/");
            bw.newLine();
            bw.write("\tprivate " + processType(types.get(i)) + " "
                    + processField(columns.get(i)) + ";");
            bw.newLine();
            bw.newLine();
        }
        bw.newLine();
        // 生成get 和 set方法
        String tempField = null;
        String _tempField = null;
        String tempType = null;
        for (int i = 0; i < size; i++) {
            tempType = processType(types.get(i));
            _tempField = processField(columns.get(i));
            tempField = _tempField.substring(0, 1).toUpperCase()
                    + _tempField.substring(1);
            bw.newLine();
            bw = buildMethodComment(bw, "void " + _tempField,
                    "设置" + comments.get(i));
            bw.newLine();
            bw.write("\tpublic void set" + tempField + "(" + tempType + " "
                    + _tempField + "){");
            bw.newLine();
            bw.write("\t\tthis." + _tempField + " = " + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();
            bw = buildMethodComment(bw, "return " + tempType,
                    "获取" + comments.get(i));
            bw.newLine();
            bw.write("\tpublic " + tempType + " get" + tempField + "(){");
            bw.newLine();
            bw.write("\t\treturn this." + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
        }
        bw.newLine();
        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }

    /**
     * 构建Dao文件
     *
     * @throws IOException
     */
    private void buildMapper() throws IOException {
        File folder = new File(mapper_path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(mapper_path, mapperName + ".java");
        if (mapperFile.exists()) {
            return;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + mapper_package + ";");
        bw.newLine();
        bw.newLine();
        bw.write("import " + bean_package + "." + beanName + ";");
        bw = buildClassComment(bw, mapperName + "持久层映射接口");
        bw.newLine();
        bw.write("public interface " + mapperName + " extends BaseDao<"
                + beanName + "> {");
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();
    }

    /**
     * 构建Service文件
     *
     * @throws IOException
     */
    private void buildService() throws IOException {
        File folder = new File(service_path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(service_path, serviceName + ".java");
        if (mapperFile.exists()) {
            return;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + service_package + ";");
        bw.newLine();
        bw.newLine();
        bw.write("import " + bean_package + "." + beanName + ";");
        bw = buildClassComment(bw, serviceName + "服务层接口");
        bw.newLine();
        bw.write("public interface " + serviceName + " extends BaseService<"
                + beanName + "> {");
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();

    }

    /**
     * 构建ServiceImp文件
     *
     * @throws IOException
     */
    private void buildImpService() throws IOException {
        String daoName = mapperName.substring(0, 1).toLowerCase()
                + mapperName.substring(1, mapperName.length());
        File folder = new File(serviceImp_path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(serviceImp_path, serviceImpName + ".java");
        if (mapperFile.exists()) {
            return;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + service_imp_package + ";");
        bw.newLine();
        bw.newLine();
        bw.write("import com.github.pagehelper.PageHelper;");
        bw.newLine();
        bw.write("import com.github.pagehelper.PageInfo;");
        bw.newLine();
        bw.write("import " + mapper_package + "." + mapperName + ";");
        bw.newLine();
        bw.write("import " + bean_package + "." + beanName + ";");
        bw.newLine();
        bw.write("import " + service_package + "." + serviceName + ";");
        bw.newLine();
        bw.write("import com.sinsinet.util.PageUtils;");
        bw.newLine();
        bw.newLine();
        bw.write("import java.util.ArrayList;");
        bw.newLine();
        bw.write("import java.util.Date;");
        bw.newLine();
        bw.write("import java.util.List;");
        bw.newLine();
        bw.write("import java.util.Map;");
        bw.newLine();
        bw.write("import org.apache.commons.lang3.StringUtils;");
        bw.newLine();
        bw.write("import org.springframework.beans.factory.annotation.Autowired;");
        bw.newLine();
        bw.write("import org.springframework.stereotype.Service;");
        bw.newLine();
        bw = buildClassComment(bw, serviceImpName + "服务层接口业务逻辑实现类");
        bw.newLine();
        bw.write("@Service(\"" + serviceName.substring(0, 1).toLowerCase()
                + serviceName.substring(1, serviceName.length()) + "\")");
        bw.newLine();
        bw.write("public class " + serviceImpName + " implements "
                + serviceName + "{");
        bw.newLine();
        bw.write("\t@Autowired");
        bw.newLine();
        bw.write("\tprivate " + mapperName + " " + daoName + ";");
        bw.newLine();
        bw.newLine();
        // ----------定义Service中的方法Begin----------
        bw = buildMethodComment(bw, "主键ID", beanName, "根据主键ID查询一个对象");
        bw.newLine();
        bw.write("\t@Override");
        bw.newLine();
        bw.write("\tpublic " + beanName + " get"+ beanName +"(Long id){");
        bw.newLine();
        bw.write("\t\treturn " + daoName + ".get"+ beanName +"(id);");
        bw.newLine();
        bw.write("\t}");
        bw.newLine();
        bw = buildMethodComment(bw, "pageNum", "pageSize", "map", "PageUtils",
                "分页/检索/全部等查询");
        bw.newLine();
        bw.write("\t@Override");
        bw.newLine();
        bw.write("\tpublic PageUtils findByLike(String pageNum, String pageSize, Map<String, Object> map){");
        bw.newLine();
        bw.write("\t\tPageUtils page = new PageUtils();");
        bw.newLine();
        bw.write("\t\tList<" + beanName + "> list = new ArrayList<" + beanName
                + ">();");
        bw.newLine();
        bw.write("\t\tif(StringUtils.isBlank(pageNum) || StringUtils.isBlank(pageSize)){");
        bw.newLine();
        bw.write("\t\t\tlist = " + daoName + ".findByLike(map);");
        bw.newLine();
        bw.write("\t\t\tpage.setTotalCount(list.size());");
        bw.newLine();
        bw.write("\t\t} else {");
        bw.newLine();
        bw.write("\t\t\tPageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));");
        bw.newLine();
        bw.write("\t\t\tlist = " + daoName + ".findByLike(map);");
        bw.newLine();
        bw.write("\t\t\tPageInfo<" + beanName + "> pageInfo = new PageInfo<"
                + beanName + ">(list);");
        bw.newLine();
        bw.write("\t\t\tlong total = pageInfo.getTotal();");
        bw.newLine();
        bw.write("\t\t\tpage.setTotalCount(total);");
        bw.newLine();
        bw.write("\t\t}");
        bw.newLine();
        bw.write("\t\tpage.setList(list);");
        bw.newLine();
        bw.write("\t\treturn page;");
        bw.newLine();
        bw.write("\t}");
        bw.newLine();
        bw = buildMethodComment(bw, "id", "int", "根据ID删除一个对象");
        bw.newLine();
        bw.write("\t@Override");
        bw.newLine();
        bw.write("\tpublic " + "int removeById(Long id){");
        bw.newLine();
        bw.write("\t\treturn " + daoName + ".removeById(id);");
        bw.newLine();
        bw.write("\t}");
        bw.newLine();
        bw = buildMethodComment(bw, beanName.substring(0, 1).toLowerCase()
                + beanName.substring(1, beanName.length()), "int", "添加一个对象");
        bw.newLine();
        bw.write("\t@Override");
        bw.newLine();
        bw.write("\tpublic int addOne(" + beanName + " "
                + beanName.substring(0, 1).toLowerCase()
                + beanName.substring(1, beanName.length()) + "){");
        bw.newLine();
        bw.write("\t\t" + beanName.substring(0, 1).toLowerCase()
                + beanName.substring(1, beanName.length())
                + ".setCreateTime(new Date());");
        bw.newLine();
        bw.write("\t\treturn " + daoName + ".addOne("
                + beanName.substring(0, 1).toLowerCase()
                + beanName.substring(1, beanName.length()) + ");");
        bw.newLine();
        bw.write("\t}");
        bw.newLine();
        bw = buildMethodComment(bw, beanName.substring(0, 1).toLowerCase()
                + beanName.substring(1, beanName.length()), "int", "修改一个对象");
        bw.newLine();
        bw.write("\t@Override");
        bw.newLine();
        bw.write("\tpublic int editOne(" + beanName + " "
                + beanName.substring(0, 1).toLowerCase()
                + beanName.substring(1, beanName.length()) + "){");
        bw.newLine();
        bw.write("\t\t" + beanName.substring(0, 1).toLowerCase()
                + beanName.substring(1, beanName.length())
                + ".setUpdateTime(new Date());");
        bw.newLine();
        bw.write("\t\treturn " + daoName + ".editOne("
                + beanName.substring(0, 1).toLowerCase()
                + beanName.substring(1, beanName.length()) + ");");
        bw.newLine();
        bw.write("\t}");
        bw.newLine();

        // ----------定义Mapper中的方法End----------
        bw.newLine();
        bw.write("}");
        bw.flush();
        bw.close();
    }

    /**
     * 构建实体类映射XML文件
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    private void buildMapperXml(List<String> columns, List<String> types,
                                List<String> comments) throws IOException {
        File folder = new File(xml_path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperXmlFile = new File(xml_path, beanName + "Mapper" + ".xml");
        if (mapperXmlFile.exists()) {
            return;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(mapperXmlFile)));
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        bw.newLine();
        bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
        bw.newLine();
        bw.write("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        bw.newLine();
        bw.write("<mapper namespace=\"" + mapper_package + "." + mapperName
                + "\">");
        bw.newLine();
        bw.newLine();

        // 下面开始写SqlMapper中的方法
        buildSQL(bw, columns, types);

        bw.write("</mapper>");
        bw.flush();
        bw.close();
    }

    private void buildSQL(BufferedWriter bw, List<String> columns,
                          List<String> types) throws IOException {
        int size = columns.size();
        // 通用结果列
        bw.write("\t<!-- 通用查询结果列-->");
        bw.newLine();
        bw.write("\t<sql id=\"Base_Column_List\">");
        bw.newLine();

        for (int i = 0; i < size; i++) {
            bw.write("    " + columns.get(i));
            if (i != size - 1) {
                bw.write(",");
            }
        }

        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
        bw.newLine();

        // 可根据自己的需求，是否要使用
        bw.write("\t<!-- BaseResultMap：实体类与数据库字段一一对应-->");
        bw.newLine();
        bw.write("\t<resultMap type=\"com.sinsinet.entity." + beanName
                + "\" id=\"BaseResultMap\">");
        bw.newLine();

        bw.write("\t\t<id property=\"" + processField(columns.get(0))
                + "\" column=\"" + columns.get(0) + "\" />");
        bw.newLine();
        for (int i = 1; i < size; i++) {
            bw.write("\t\t<result property=\"" + processField(columns.get(i))
                    + "\" column=\"" + columns.get(i) + "\" />");
            bw.newLine();
        }

        bw.write("\t</resultMap>");
        bw.newLine();
        bw.newLine();

        String className = "com.sinsinet.entity."
                + beanName.substring(0, 1).toUpperCase()
                + beanName.substring(1);

        // --------------- addOne方法（匹配有值的字段）
        bw.write("\t<!-- 增：匹配有值的字段 -->");
        bw.newLine();
        bw.write("\t<insert id=\"addOne\" parameterType=\"" + className + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + tableName);
        bw.newLine();
        bw.write("\t\t <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
        bw.newLine();

        String tempField = null;
        for (int i = 0; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.newLine();
            bw.write("\t\t\t\t " + columns.get(i) + ",");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }

        bw.write("\t\t </trim>");
        bw.newLine();

        bw.write("\t\t <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");
        bw.newLine();

        tempField = null;
        for (int i = 0; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + "!=null\">");
            bw.newLine();
            bw.write("\t\t\t\t #{" + tempField + "},");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }

        bw.write("\t\t </trim>");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        // --------------- 完毕

        // 删除（根据主键ID删除）
        bw.write("\t<!-- 删：根据primaryKeyID删除对象 -->");
        bw.newLine();
        bw.write("\t<delete id=\"removeById\" parameterType=\"java.lang."
                + processType(types.get(0)) + "\">");
        bw.newLine();
        bw.write("\t\t DELETE FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{"
                + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</delete>");
        bw.newLine();
        bw.newLine();
        // 删除完

        // 修改update方法
        bw.write("\t<!-- 改：更新新model中不为NULL的参数-->");
        bw.newLine();
        bw.write("\t<update id=\"editOne\" parameterType=\"" + className
                + "\">");
        bw.newLine();
        bw.write("\t\t UPDATE " + tableName);
        bw.newLine();
        bw.write(" \t\t <set> ");
        bw.newLine();

        tempField = null;
        for (int i = 1; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.newLine();
            bw.write("\t\t\t\t " + columns.get(i) + " = #{" + tempField + "},");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }

        bw.write(" \t\t </set>");
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{"
                + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
        bw.newLine();
        // update方法完毕

        // 查询（根据主键ID查询）
        bw.write("\t<!-- 查：根据主键ID查询对象 -->");
        bw.newLine();

        bw.write("\t<select id=\"getOne\" resultMap=\"BaseResultMap\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t <include refid=\"Base_Column_List\" />");
        bw.newLine();
        bw.write("\t\t FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{"
                + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();
        // 查询完

        // 模糊查询
        bw.write("\t<!-- 查：模糊查询 -->");
        bw.newLine();
        bw.write("\t<select id=\"findByLike\" resultMap=\"BaseResultMap\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t <include refid=\"Base_Column_List\" />");
        bw.newLine();
        bw.write("\t\t FROM " + tableName);
        bw.newLine();
        bw.write("\t\t <where>");
        bw.newLine();
        bw.write("\t\t\t <if test=\"keyword!= null and keyword != ''\">");
        bw.newLine();
        bw.write("\t\t\t\t and keyword LIKE CONCAT(CONCAT('%', #{keyword}),'%')");
        bw.newLine();
        bw.write("\t\t\t </if>");
        bw.newLine();
        bw.write("\t\t </where>");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();
        // 查询完
    }

    /**
     * 获取所有的数据库表注释
     *
     * @return
     * @throws SQLException
     */
    private Map<String, String> getTableComment() throws SQLException {
        Map<String, String> maps = new HashMap<String, String>(16);
        PreparedStatement pstate = conn.prepareStatement("show table status");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString("NAME");
            String comment = results.getString("COMMENT");
            maps.put(tableName, comment);
        }
        return maps;
    }

    public void generate() throws ClassNotFoundException, SQLException,
            IOException {
        init();
        String prefix = "show full fields from ";
        List<String> columns = null;
        List<String> types = null;
        List<String> comments = null;
        PreparedStatement pstate = null;
        List<String> tables = getTables();
        Map<String, String> tableComments = getTableComment();
        for (String table : tables) {
            columns = new ArrayList<String>();
            types = new ArrayList<String>();
            comments = new ArrayList<String>();
            pstate = conn.prepareStatement(prefix + entityName + "." + table);
            ResultSet results = pstate.executeQuery();
            while (results.next()) {
                columns.add(results.getString("FIELD"));
                types.add(results.getString("TYPE"));
                comments.add(results.getString("COMMENT"));
            }
            tableName = table;
            processTable(table);
            String tableComment = tableComments.get(tableName);
            buildEntityBean(columns, types, comments, tableComment);
            buildMapper();
            buildMapperXml(columns, types, comments);
            buildService();
            buildImpService();
        }
        conn.close();
    }

    public static void main(String[] args) {
        try {
            System.out.println("开始操作数据库");

            new EntityUtil().generate();
            System.out.println("操作数据库结束");
            // 自动打开生成文件的目录
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}