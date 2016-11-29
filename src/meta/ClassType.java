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

    public ClassType(String className)
    {
        fields = new HashMap<>();
        setClassName(className);
    }

    public ClassType(String className,String baseClass)
    {
        fields = new HashMap<>();
        setClassName(className);
        setBaseClass(baseClass);
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

    public void setBaseClass(String baseClass)
    {
        Type t = Type.getType(baseClass);
        if (t != null)
        {
            if (t.isClass())
            {
                setBaseClass((ClassType)t);
            }
            else
            {
                throw new RuntimeException(baseClass + " is not a class");
            }
        }
        else
        {
            throw new RuntimeException("no such type:"+baseClass);
        }
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String getTypeName() {
        return className;
    }

    @Override
    public boolean isClass() {
        return true;
    }
}