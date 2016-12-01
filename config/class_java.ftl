


public class ${class.typeName} <#if class.baseType??>extends ${class.baseType}</#if> {

<#list class.fields as field>
    private ${field.fieldType.typeName} ${field.fieldName};
</#list>

<#list class.fields as field>
    public ${field.fieldType.typeName} get${field.fieldName?cap_first}()
    {
        return ${field.fieldName};
    }

    public void set${field.fieldName?cap_first}(${field.fieldType.typeName} ${field.fieldName})
    {
        this.${field.fieldName} = ${field.fieldName};
    }
</#list>

}