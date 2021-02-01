package ${pkgPath}.entity;

<#if baseEntity??>
    import ${baseEntity};
</#if>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import javax.persistence.*;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
/**
* ${moduleDesc} 实体类
*/
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "${tablename}")
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Accessors(chain = true)
<#if baseEntity??>
    public class ${className}Entity extends ${baseEntityName} implements Serializable{
<#else >
    public class ${className}Entity implements Serializable
</#if>

<#list fields as elem>
    <#if elem.getCType()=="INT">
        @Column(name = "${elem.getTName()}")
        private Integer ${elem.getCName()} ;
    <#elseif elem.getCType()=="BIT">
        @Column(name = "${elem.getTName()}")
        private Boolean ${elem.getCName()} ;
    <#elseif elem.getCType()=="BIGINT">
        @Column(name = "${elem.getTName()}")
        private Long ${elem.getCName()} ;
    <#elseif elem.getCType()=="DATETIME">
        @Column(name = "${elem.getTName()}")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime ${elem.getCName()} ;
    <#elseif elem.getCType()=="TIMESTAMP">
        @Column(name = "${elem.getTName()}")
        private LocalDateTime ${elem.getCName()} ;
    <#elseif elem.getCType()=="TINYINT UNSIGNED">
        @Column(name = "${elem.getTName()}")
        private Boolean ${elem.getCName()} ;
    <#elseif elem.getCType()=="VARCHAR">
        @Column(name = "${elem.getTName()}")
        private String ${elem.getCName()} ;
    <#elseif elem.getCType()=="DECIMAL">
        @Column(name = "${elem.getTName()}")
        private BigDecimal ${elem.getCName()} ;
    <#elseif elem.getCType()=="TINYINT">
        @Column(name = "${elem.getTName()}")
        private Boolean ${elem.getCName()}  ;
    <#elseif elem.getCType()=="FLOAT">
        @Column(name = "${elem.getTName()}")
        private Float ${elem.getCName()}  ;
    <#else>
        @Column(name = "${elem.getTName()}")
        private String ${elem.getCName()} ;
    </#if>

</#list>
}
