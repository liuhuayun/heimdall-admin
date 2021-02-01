package ${pkgPath}.dto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;
<#if baseDTO??>
    import ${baseDTO};
</#if>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.io.Serializable;
/**
* ${moduleDesc} DTO对象
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "${moduleDesc}DTO对象",description = "${moduleDesc}DTO")
@EqualsAndHashCode(callSuper = true)
<#if baseDTO??>
    public class ${className}DTO extends ${baseDTOName} implements Serializable{
<#else>
    public class ${className}DTO implements Serializable
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
