<#if tableMetaData ?? >
<#assign fields=tableMetaData.fields>
<#assign entityName=tableMetaData.entityName>
package ${packageName}.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * @author ${author}
 * @date ${date}
 * @@description ${entityName} 数据库表对应的实体类
*/
@Data
@SuppressWarnings("serial")
public class ${entityName} extends BaseEntity implements Serializable {
    <#list fields as field>
    <#if !(field.memberVariable ? starts_with("gmt"))>
    /** ${field.remarks!} **/
    <#if field.typeName = 'BIT' >
    private Boolean ${field.memberVariable};
    </#if>
    <#if field.typeName = 'INT' || field.typeName = 'TINYINT' ||  field.typeName = 'SMALLINT'>
    private Integer ${field.memberVariable};
    </#if>
    <#if field.typeName = 'BIGINT' >
    private Long ${field.memberVariable};
    </#if>
    <#if field.typeName = 'DOUBLE' >
    private Double ${field.memberVariable};
    </#if>
    <#if field.typeName = 'DECIMAL' >
    private Decimal ${field.memberVariable};
    </#if>
    <#if field.typeName = 'CHAR' || field.typeName = 'VARCHAR' || field.typeName = 'TEXT' >
    private String ${field.memberVariable};
    </#if>
    <#if field.typeName = 'BLOB' >
    private byte[] ${field.memberVariable};
    </#if>
    <#if field.typeName = 'DATETIME' || field.typeName = 'TIMESTAMP' || field.typeName = 'DATE'>
    private Date ${field.memberVariable};
    </#if>
    </#if>
    </#list>


}
</#if>
