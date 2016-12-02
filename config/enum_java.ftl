<#if name_space??>
package ${name_space};
</#if>
public enum ${enum.typeStr}{

<#list enum.cases as case>
    ${case.name}(${case.value},"${case.name}"),      <#if case.comments??> /*${case.comments}*/ </#if>
</#list>
    ;

    private int value;
    private String desc;

    ${enum.typeStr}(int value)
    {
        this.value = value;
    }

    ${enum.typeStr}(int value,String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)",desc,value);
    }
}
