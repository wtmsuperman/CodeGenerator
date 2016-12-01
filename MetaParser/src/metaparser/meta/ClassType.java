package metaparser.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public class ClassType extends Type {

    private HashMap<String, Field> fields;
    private String className;
    private ClassType baseClass;

    public ClassType() {
        fields = new HashMap<>();
    }

    public ClassType(String className) {
        fields = new HashMap<>();
        setClassName(className);
    }

    public ClassType(String className, String baseClass) {
        fields = new HashMap<>();
        setClassName(className);
        setBaseClass(baseClass);
    }

    public void newClass() {
        addType(this);
    }

    public boolean hasField(String name, boolean recursion) {
        if (fields.containsKey(name))
            return fields.containsKey(name);

        if (!recursion) return false;

        if (baseClass != null) {
            return baseClass.hasField(name, recursion);
        }

        return false;
    }

    public void addField(Field field) {
        if (hasField(field.getFieldName(), true)) {
            throw new RuntimeException(getTypeName() + " already have this field " + field.getFieldName());
        }
        fields.put(field.getFieldName(), field);
    }

    public Field getFiled(String name, boolean recursion) {
        Field field = fields.get(name);
        if (field != null)
            return field;

        if (!recursion) return null;

        if (baseClass != null) {
            return baseClass.getFiled(name, recursion);
        }

        return null;
    }

    public ClassType getBaseClass() {
        return baseClass;
    }

    public void setBaseClass(String baseClass) {
        Type t = getType(baseClass);
        if (t != null) {
            if (t.isClass()) {
                setBaseClass((ClassType) t);
            } else {
                throw new RuntimeException(baseClass + " is not a class");
            }
        } else {
            throw new RuntimeException("no such type:" + baseClass);
        }
    }

    public void setBaseClass(ClassType baseClass) {
        this.baseClass = baseClass;
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

    public List<Field> getFields()
    {
        List<Field> list = new ArrayList<>();
        fields.forEach((k,v) -> list.add(v));
        return list;
    }
}