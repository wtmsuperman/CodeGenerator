package meta;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/29.
 */

public class ClassType extends Type{

    private HashMap<String, Field> fields;
    private String className;
    private ClassType baseClass;

    public ClassType()
    {
        fields = new HashMap<>();
    }

    public void newClass()
    {
        Type.addType(this);
    }

    public void addFiled(Field field)
    {
        fields.put(field.getFiledName(), field);
    }

    public Field getFiled(String name)
    {
        return fields.get(name);
    }

    public ClassType getBaseClass() {
        return baseClass;
    }

    public void setBaseClass(ClassType baseClass) {
        this.baseClass = baseClass;
    }

    @Override
    public String getTypeName() {
        return className;
    }
}