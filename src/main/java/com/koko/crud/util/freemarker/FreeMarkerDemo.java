package com.koko.crud.util.freemarker;


import com.koko.crud.util.freemarker.bean.TableMetaData;
import com.koko.crud.util.freemarker.util.FreeMarkerTemplateUtils;
import com.koko.crud.util.freemarker.util.TableUtils;
import freemarker.template.Template;

import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.koko.crud.util.freemarker.bean.SuffixConstants.*;

public class FreeMarkerDemo {
    private static final String AUTHOR = "alewu";
    private static final String CURRENT_DATE = "2017-11-21";
    private static final String PACKAGE_NAME = "com.koko.crud.util.freemarker.test";
    private static final String TEMPLATE_PATH = "F:\\java\\eclipseworkspace\\my-project\\crud\\src\\main\\java\\com\\koko\\crud\\util\\freemarker\\test\\";

    private static final String SERVICE = "service";
    private static final String IMPL = "impl";
    private static final String DAO = "dao";
    private static final String MAPPER = "mapper";
    private static final String CONSTANTS = "constants";

    // 模板名称
    private static final String ENTITY_TEMPLATE = "Entity";
    private static final String CONTROLLER_TEMPLATE = "Controller";
    private static final String SERVICE_TEMPLATE = "Service";
    private static final String SERVICE_IMPL_TEMPLATE = "ServiceImpl";
    private static final String DAO_TEMPLATE = "DAO";
    private static final String MAPPER_TEMPLATE = "Mapper";
    // 文件后缀
    private static final String JAVA_SUFFIX = JAVA;
    private static final String TEMPLATE_SUFFIX = FREEMARKER;
    private static final String MAPPER_SUFFIX = XML;
    // Controller层类级别匹配
    public static final String APP_PREFIX = "/web";

    private ResultSet resultSet = TableUtils.getResultSet();


    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis();
        String path = FreeMarkerDemo.class.getResource("").getPath().replaceFirst("/", "").replace("target/classes", "src/main/java").replace("/util/freemarker", "");

        System.out.println(path);


        FreeMarkerDemo freeMarkerDemo = new FreeMarkerDemo();
        freeMarkerDemo.generate();

        Long end = System.currentTimeMillis();
        System.out.println("finished!!!" + "\n\rtime >> " + (end - start) + " ms");
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
                generateServiceFile(tableMetaData);
                // 生成服务实现层文件
                generateServiceImplFile(tableMetaData);
                // 生成Dao文件
                generateDaoFile(tableMetaData);
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
        Path path = Paths.get(TEMPLATE_PATH, ENTITY_TEMPLATE.toLowerCase(),
                tableMetaData.getEntityName() + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMetaData", tableMetaData);
        generateFileByTemplate(ENTITY_TEMPLATE, path, dataMap);
    }

    private void generateControllerFile(TableMetaData tableMetaData) throws Exception {
        Path path = Paths.get(TEMPLATE_PATH, CONTROLLER_TEMPLATE.toLowerCase(),
                tableMetaData.getEntityName() + CONTROLLER_TEMPLATE + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMetaData", tableMetaData);
        generateFileByTemplate(CONTROLLER_TEMPLATE, path, dataMap);
    }

    private void generateServiceImplFile(TableMetaData tableMetaData) throws Exception {
        Path path = Paths.get(TEMPLATE_PATH, SERVICE, IMPL,
                tableMetaData.getEntityName() + SERVICE_IMPL_TEMPLATE + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("entityName", tableMetaData.getEntityName());
        generateFileByTemplate(SERVICE_IMPL_TEMPLATE, path, dataMap);
    }

    private void generateDaoFile(TableMetaData tableMetaData) throws Exception {
        Path path = Paths.get(TEMPLATE_PATH, DAO_TEMPLATE.toLowerCase(),
                tableMetaData.getEntityName() + DAO_TEMPLATE + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("entityName", tableMetaData.getEntityName());
        generateFileByTemplate(DAO_TEMPLATE, path, dataMap);

    }

    private void generateServiceFile(TableMetaData tableMetaData) throws Exception {
        Path path = Paths.get(TEMPLATE_PATH, SERVICE_TEMPLATE.toLowerCase(),
                tableMetaData.getEntityName() + SERVICE_TEMPLATE + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("entityName", tableMetaData.getEntityName());
        generateFileByTemplate(SERVICE_TEMPLATE, path, dataMap);

    }

    private void generateMapperFile(TableMetaData tableMetaData) throws Exception {
        Path path = Paths.get(TEMPLATE_PATH, MAPPER,
                tableMetaData.getEntityName() + MAPPER_TEMPLATE + MAPPER_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMetaData", tableMetaData);
        generateFileByTemplate(MAPPER_TEMPLATE, path, dataMap);

    }


    private void generateBaseEntityFile() throws Exception {
        //todo
        final String templateName = "BaseEntity";
        Path path = Paths.get(TEMPLATE_PATH, ENTITY_TEMPLATE, templateName + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, path, dataMap);
    }

    private void generateBaseServiceFile() throws Exception {
        final String templateName = "BaseService";
        Path path = Paths.get(TEMPLATE_PATH, SERVICE, templateName + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, path, dataMap);
    }

    private void generateBaseServiceImplFile() throws Exception {
        final String templateName = "BaseServiceImpl";
        Path path = Paths.get(TEMPLATE_PATH, SERVICE, IMPL, templateName + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, path, dataMap);
    }

    private void generateBaseDAOFile() throws Exception {
        final String templateName = "BaseDAO";
        Path path = Paths.get(TEMPLATE_PATH, DAO, templateName + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, path, dataMap);
    }

    private void generateRestURIConstantsFile(List<TableMetaData> tableMetaDatas) throws Exception {
        final String templateName = "RestURIConstants";
        Path path = Paths.get(TEMPLATE_PATH, CONSTANTS, templateName + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("appPrefix", APP_PREFIX);
        dataMap.put("tableMetaDatas", tableMetaDatas);
        generateFileByTemplate(templateName, path, dataMap);
    }


    private void generateFileByTemplate(final String templateName, Path path, Map<String, Object> dataMap) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName + TEMPLATE_SUFFIX);
        dataMap.put("packageName", PACKAGE_NAME);
        dataMap.put("author", AUTHOR);
        dataMap.put("date", CURRENT_DATE);
        Writer out = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        template.process(dataMap, out);

    }


}
