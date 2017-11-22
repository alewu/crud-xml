package com.ale.crud.util.common;

import com.ale.crud.util.freemarker.bean.TableMetaData;
import com.ale.crud.util.freemarker.util.TableUtils;
import org.junit.Test;



import java.sql.SQLException;
import java.util.List;

public class JDBCTest {
    @Test
    public void test01() throws SQLException {
        List<TableMetaData> tableMetaDatas = TableUtils.getTableMetaData();
        for (TableMetaData tableMetaData : tableMetaDatas) {
            System.out.println(tableMetaData);
        }

    }
}
