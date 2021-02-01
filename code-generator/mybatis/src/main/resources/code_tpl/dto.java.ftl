package ${package.Entity?replace(".entity",".dto")};
<#if swagger2>
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
    import lombok.Data;
    import lombok.experimental.Accessors;
    import lombok.EqualsAndHashCode;
</#if>

/**
* <p>
    * ${cfg.classDesc!}对象
    * </p>
*
* @author ${author}
*/
<#if entityLombokModel>
    @Data
    @Accessors(chain = true)
</#if>
<#assign className = "${entity ?replace('Entity', 'DTO')}">
@ApiModel(value="${cfg.classDesc!}${className}对象", description="${cfg.classDesc!}${className}对象")
@EqualsAndHashCode(callSuper = true)
public class ${className} extends AbstractDTO {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.comment!?length gt 0>
        /**
        * ${field.comment}
        */
        <#if swagger2>
            @ApiModelProperty(value = "${field.comment}")
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
}
