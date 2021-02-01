package ${pkgPath}.vo;
<#if baseVO??>
    import ${baseVO};
</#if>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
/**
* ${moduleDesc} VO对象
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "${moduleDesc}VO对象",description = "${moduleDesc}VO对象")
@EqualsAndHashCode(callSuper = true)
<#if baseVO??>
    public class ${className}VO extends ${baseVOName} implements Serializable{
<#else>
    public class ${className}VO implements Serializable
</#if>

<#list fields as elem>
    <#if elem.getCType()=="INT">
        @ApiModelProperty("${elem.getRemarks()}")
        private Integer ${elem.getCName()} ;
    <#elseif elem.getCType()=="BIT">
        private Boolean ${elem.getCName()} ;
    <#elseif elem.getCType()=="BIGINT">
        @ApiModelProperty("${elem.getRemarks()}")
        private Long ${elem.getCName()} ;
    <#elseif elem.getCType()=="DATETIME">
        @ApiModelProperty("${elem.getRemarks()}")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime ${elem.getCName()} ;
    <#elseif elem.getCType()=="TIMESTAMP">
        @ApiModelProperty("${elem.getRemarks()}")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime ${elem.getCName()} ;
    <#elseif elem.getCType()=="TINYINT UNSIGNED">
        @ApiModelProperty("${elem.getRemarks()}")
        private Boolean ${elem.getCName()} ;
    <#elseif elem.getCType()=="VARCHAR">
        @ApiModelProperty("${elem.getRemarks()}")
        private String ${elem.getCName()} ;
    <#elseif elem.getCType()=="DECIMAL">
        @ApiModelProperty("${elem.getRemarks()}")
        private BigDecimal ${elem.getCName()} ;
    <#elseif elem.getCType()=="TINYINT">
        @ApiModelProperty("${elem.getRemarks()}")
        private Boolean ${elem.getCName()}  ;
    <#elseif elem.getCType()=="FLOAT">
        @ApiModelProperty("${elem.getRemarks()}")
        private Float ${elem.getCName()}  ;
    <#else>
        @ApiModelProperty("${elem.getRemarks()}")
        private String ${elem.getCName()} ;
    </#if>

</#list>
}
