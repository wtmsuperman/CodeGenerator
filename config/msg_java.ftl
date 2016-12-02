<#if name_space??>
package ${name_space};
</#if>

//这是一个消息

<#list usings as u>
import ${u};
</#list>

public class ${class.typeStr} <#if class.baseClass??>extends ${class.baseClass.typeStr}</#if> {

<#list class.fields as field>
    private ${field.fieldType.typeStr} ${field.fieldName};      <#if field.comments??>//${field.comments}</#if>
</#list>

<#list class.fields as field>
    public ${field.fieldType.typeStr} get${field.fieldName?cap_first}()
    {
        return ${field.fieldName};
    }

    public void set${field.fieldName?cap_first}(${field.fieldType.typeStr} ${field.fieldName})
    {
        this.${field.fieldName} = ${field.fieldName};
    }
</#list>

}