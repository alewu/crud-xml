<#if tableMetaData??>
<#assign fields=tableMetaData.fields>
<#assign entityName=tableMetaData.entityName>
package ${packageName}.controller;

import ${packageName}.entity.${entityName};
import ${packageName}.common.bean.page.PageBean;
import ${packageName}.common.bean.page.PageParams;
import ${packageName}.common.bean.Response;
import ${packageName}.constants.RestURIConstants;
import ${packageName}.service.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author ${author}
 * @date ${date}
 * @description ${entityName} 控制器
 */
@RestController
@RequestMapping(RestURIConstants.APP_PREFIX)
public class ${entityName}Controller {
    @Autowired
    private ${entityName}Service ${entityName?uncap_first}Service;

<#list data as d >

    @PostMapping(RestURIConstants.${entityName?upper_case}S)
    public Response insertOne(${entityName} ${entityName?uncap_first}){
        ${entityName?uncap_first}Service.insertOne(${entityName?uncap_first});
        return Response.success();
    }
</#list>
    @DeleteMapping(RestURIConstants.${entityName?upper_case}_ID)
    public Response deleteOne(@PathVariable String ${entityName?uncap_first}Id){
        ${entityName?uncap_first}Service.deleteOne(${entityName?uncap_first}Id);
        return Response.success();
    }

    @PutMapping(RestURIConstants.${entityName?upper_case}_ID)
    public Response updateOne(@PathVariable String ${entityName?uncap_first}Id,${entityName} ${entityName?uncap_first}){
        int m = ${entityName?uncap_first}Service.updateOne(${entityName?uncap_first});
        return Response.success();
    }

    @GetMapping(RestURIConstants.${entityName?upper_case}_ID)
    public Response getOne(@PathVariable String ${entityName?uncap_first}Id){
        ${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Service.getOne(${entityName?uncap_first}Id);
        return Response.success().put("${entityName?uncap_first}", ${entityName?uncap_first});
    }

    @GetMapping(RestURIConstants.${entityName?upper_case}S)
    public Response list${entityName}(PageParams pageParams){
        PageBean<${entityName}> pageBean = ${entityName?uncap_first}Service.list${entityName}(pageParams);
        return Response.success().put("pageBean", pageBean);
    }

}


</#if>