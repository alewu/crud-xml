package com.koko.crud.util.freemarker;


import com.koko.crud.util.freemarker.bean.TableMetaData;
import com.koko.crud.util.freemarker.util.FreeMarkerTemplateUtils;
import com.koko.crud.util.freemarker.util.TableUtils;
import freemarker.template.Template;

import java.io.File;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerTest {
    private static final String AUTHOR = "alewu";
    private static final String CURRENT_DATE = "2017-11-16";
    private static final String PACKAGE_NAME = "com.koko.crud.util.freemarker.test";
    private static final String TEMPLATE_PATH = "F:\\java\\eclipseworkspace\\my-project\\crud\\src\\main\\java\\com\\koko\\crud\\util\\freemarker\\test\\";
    private static final String ENTITY_PATH = "entity\\";
    private static final String CONTROLLER_PATH = "controller\\";
    private static final String SERVICE_PATH = "service\\";
    private static final String DAO_PATH = "dao\\";
    private static final String MAPPER_PATH = "mapper\\";
    private static final String CONSTANTS_PATH = "constants\\";


    private static final String suffix = ".java";
    private static final String templateSuffix = ".ftl";
    private ResultSet resultSet = TableUtils.getResultSet();


    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis();

        FreeMarkerTest freeMarkerTest = new FreeMarkerTest();
        freeMarkerTest.generate();

        Long end = System.currentTimeMillis();
        System.out.println("finished!!!" + "\n\rtime >> " + (end - start) +" ms");
    }

    public void generate() throws Exception {
        List<TableMetaData> tableMetaDatas;
        try {
            tableMetaDatas = TableUtils.getTableMetaData();
            for (TableMetaData tableMetaData : tableMetaDatas) {
                // 生成Model文件
                generateEntityFile(tableMetaData);
                // 生成Mapper文件
                generateMapperFile(tableMetaData);
                // 生成服务层接口文件
                generateCommonServiceFile(tableMetaData);
                // 生成服务实现层文件
                generateCommonServiceImplFile(tableMetaData);
                // 生成Dao文件
                generateCommonDaoFile(tableMetaData);
                // 生成controller文件
                generateControllerFile(tableMetaData);
            }
            generateRestURIConstantsFile(tableMetaDatas);
            generateBaseServiceImplFile();
            generateBaseServiceFile();
            generateBaseDAOFile();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    private void generateEntityFile(TableMetaData tableMetaData) throws Exception {
        final String path = TEMPLATE_PATH + ENTITY_PATH + tableMetaData.getEntityName() + suffix;
        final String templateName = "Entity";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMetaData", tableMetaData);
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateControllerFile(TableMetaData tableMetaData) throws Exception {
        final String path = TEMPLATE_PATH + CONTROLLER_PATH + tableMetaData.getEntityName() + "Controller" + suffix;
        final String templateName = "Controller";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMetaData", tableMetaData);
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateCommonServiceImplFile(TableMetaData tableMetaData) throws Exception {
        final String path = TEMPLATE_PATH + SERVICE_PATH + "impl\\" + tableMetaData.getEntityName() + "ServiceImpl" + suffix;
        final String templateName = "CommonServiceImpl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("entityName", tableMetaData.getEntityName());
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateCommonDaoFile(TableMetaData tableMetaData) throws Exception {
        final String path = TEMPLATE_PATH + DAO_PATH + tableMetaData.getEntityName() + "DAO" + suffix;
        final String templateName = "CommonDAO";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("entityName", tableMetaData.getEntityName());
        generateFileByTemplate(templateName, mapperFile, dataMap);

    }

    private void generateCommonServiceFile(TableMetaData tableMetaData) throws Exception {
        final String path = TEMPLATE_PATH + SERVICE_PATH + tableMetaData.getEntityName() + "Service" + suffix;
        final String templateName = "CommonService";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("entityName", tableMetaData.getEntityName());
        dataMap.put("layer", "Service");
        generateFileByTemplate(templateName, mapperFile, dataMap);

    }

    private void generateMapperFile(TableMetaData tableMetaData) throws Exception {
        final String path = TEMPLATE_PATH + MAPPER_PATH + tableMetaData.getEntityName() + "Mapper.xml";
        final String templateName = "Mapper";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMetaData", tableMetaData);
        generateFileByTemplate(templateName, mapperFile, dataMap);

    }

    private void generateBaseDAOFile() throws Exception {
        final String path = TEMPLATE_PATH + DAO_PATH + "BaseDAO" + suffix;
        final String templateName = "BaseServiceDAO";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("layer", "DAO");
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateBaseServiceFile() throws Exception {
        final String path = TEMPLATE_PATH + SERVICE_PATH + "BaseService" + suffix;
        final String templateName = "BaseServiceDAO";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("layer", "Service");
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateBaseServiceImplFile() throws Exception {
        final String templateName = "BaseServiceImpl";
        final String path = TEMPLATE_PATH + SERVICE_PATH + "impl\\";
        final String file = templateName + suffix;
        File mapperFile = new File(path+file);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateRestURIConstantsFile(List<TableMetaData> tableMetaDatas) throws Exception {
        final String path = TEMPLATE_PATH + CONSTANTS_PATH + "RestURIConstants" + suffix;
        final String templateName = "RestURIConstants";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMetaDatas", tableMetaDatas);
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    private void generateFileByTemplate(final String templateName, File file, Map<String, Object> dataMap) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName + templateSuffix);
        dataMap.put("packageName", PACKAGE_NAME);
        dataMap.put("author", AUTHOR);
        dataMap.put("date", CURRENT_DATE);
        Writer out = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8);
        template.process(dataMap, out);


    }


}
