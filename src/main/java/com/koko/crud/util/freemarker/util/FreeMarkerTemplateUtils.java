package com.koko.crud.util.freemarker.util;

import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
/**
 * @author alewu
 * @date 2017/11/4 8:23
 * @description FreeMarker模板工具类：主要用于配置模板，获取模板
 */
public class FreeMarkerTemplateUtils {

    /** 通过Freemarker的Configuration读取相应的ftl **/
    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_26);
    public static final String TEMPLATE_PATH  = "F:\\java\\eclipseworkspace\\crud\\src\\main\\java\\com\\koko\\crud\\util\\freemarker\\template";

    static{
        // 指定加载模板所在的路径
        try {
            CONFIGURATION.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO
        //CONFIGURATION.setClassForTemplateLoading(FreeMarkerTemplateUtils.class,"/util/freemarker/template");
        //ClassTemplateLoader ctl = new ClassTemplateLoader(FreeMarkerTemplateUtils.class, "/freemarker");
        //CONFIGURATION.setTemplateLoader(ctl);
        CONFIGURATION.setDefaultEncoding("UTF-8");
        CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
    }


    public static Template getTemplate(String templateName) {
        try {
            return CONFIGURATION.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void clearCache() {
        CONFIGURATION.clearTemplateCache();
    }
}
