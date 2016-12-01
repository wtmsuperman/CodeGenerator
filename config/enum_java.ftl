
public enum ${enum.typeName}{

<#list enum.cases as case>
    ${case.name}(${case.value},"${case.name}") /*${case.comment}*/,
</#list>
    ;

    private int value;
    private String desc;

    ${enum.typeName}(int value)
    {
        this.value = value;
    }

    ${enum.typeName}(int value,String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)",desc,value);
    }
}
