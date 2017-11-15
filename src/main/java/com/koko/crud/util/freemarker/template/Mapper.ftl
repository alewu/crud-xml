<#if tableMetaData??>
<#assign fields=tableMetaData.fields>
<#assign entityName=tableMetaData.entityName>
<#assign tableName=tableMetaData.tableName>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.dao.${entityName}DAO">

    <#list fields>
    <!-- 通用查询结果列：数据库表字段 -->
    <sql id="Base_Column_List">
    <#items as field >
        ${field.columnName}<#sep>,
    </#items>

    </sql>
    </#list>
    <#list fields>
    <!-- 通用resultMap：实体类成员变量一一对应数据库表字段 -->
    <resultMap type="${packageName}.entity.${entityName}" id="BaseResultMap">
    <#items as field>
    <#if field ? index == 0>
        <id property="${field.memberVariable}" column="${field.columnName}" />
    <#else>
        <result property="${field.memberVariable}" column="${field.columnName}" />
    </#if>
    </#items>
    </resultMap>
    </#list>

    <#list fields>
    <insert id="insertOne" parameterType="${packageName}.entity.${entityName}">
        INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#items as field>
        <#if field.typeName ='VARCHAR' >
            <if test="${field.memberVariable} != null and ${field.memberVariable} !=''">
                ${field.columnName},
            </if>
        <#else>
            <if test="${field.memberVariable} != null">
                ${field.columnName},
            </if>
        </#if>
        </#items>
        </trim>
    </#list>
    <#list fields>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#items as field>
        <#if (field.typeName ='VARCHAR') >
            <if test="${field.memberVariable} != null and ${field.memberVariable} !=''">
                ${r'#{'}${field.memberVariable}${r'}'},
            </if>
        <#else>
            <if test="${field.memberVariable} != null">
                ${r'#{'}${field.memberVariable}${r'}'},
            </if>
        </#if>
        </#items>
        </trim>
    </#list>
    </insert>

    <delete id="deleteOne" parameterType="java.lang.Integer">
        DELETE FROM ${tableName}
        WHERE ${fields[0].columnName} = ${r'#{'}${fields[0].memberVariable}${r'}'}
    </delete>

<#if (fields[3].columnName != "gmt_create") >
<#list fields>
    <update id="updateOne" parameterType="${packageName}.entity.${entityName}">
        UPDATE ${tableName}
        <set>
    <#items as field>
        <#if field ? index != 0>
        <#if (field.typeName ='VARCHAR') >
            <if test="${field.memberVariable} != null and ${field.memberVariable} !=''">
                ${field.columnName} = ${r'#{'}${field.memberVariable}${r'}'},
            </if>
        <#else>
            <if test="${field.memberVariable} != null">
                ${field.columnName} = ${r'#{'}${field.memberVariable}${r'}'},
            </if>
        </#if>
        </#if>
    </#items>
        </set>
        WHERE ${fields[0].columnName} = ${r'#{'}${fields[0].memberVariable}${r'}'}
    </update>
</#list>

    <select id="getOne" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List"/>
        FROM ${tableName}
        WHERE ${fields[0].columnName} = ${r'#{'}${fields[0].memberVariable}${r'}'}
    </select>
</#if>


</mapper>


</#if>