package ${packageName}.service;

import ${packageName}.entity.${entityName};
import ${packageName}.common.bean.page.PageBean;
import ${packageName}.common.bean.page.PageParam;

/**
 * @author ${author}
 * @date ${date}
 * @description ${entityName}服务层接口
 */
public interface ${entityName}Service extends BaseService<${entityName}> {

    PageBean<${entityName}> list${entityName}(PageParam PageParam);

}