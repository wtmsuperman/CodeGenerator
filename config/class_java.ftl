


public class ${class.typeStr} <#if class.baseClass??>extends ${class.baseClass.typeStr}</#if> {

<#list class.fields as field>
    private ${field.fieldType.typeStr} ${field.fieldName};
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