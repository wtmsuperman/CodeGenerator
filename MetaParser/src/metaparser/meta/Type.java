package metaparser.meta;

import metaparser.meta.basic.*;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/29.
 */

public abstract class Type extends Meta {
    private static HashMap<String, Type> allTyps = new HashMap<>();

    //增加基础类型
    static {
        addType(new FloatType());
        addType(new BoolType());
        addType(new IntType());
        addType(new ListType());
        addType(new StringType());
    }

    public static void addType(Type type) {
        String typeName = type.getTypeName();
        if (typeName == null || typeName.isEmpty()) {
            throw new RuntimeException("typeName is empty!");
        }
        if (allTyps.put(typeName, type) != null) {
            throw new RuntimeException("duplicate type: " + typeName);
        }
    }

    public static Type getType(String typeName) {
        return allTyps.get(typeName);
    }

    public static Collection<Type> getAllType() {
        return allTyps.values();
    }

    public abstract String getTypeName();

    public boolean isClass() {
        return false;
    }

    public boolean isEnum() {
        return false;
    }

    private static HashMap<Type,TypeStrFmt> fmtors = new HashMap<>();

    public static void addTypeStrFormator(Type t,TypeStrFmt fmt)
    {
        fmtors.put(t,fmt);
    }

    public String getTypeStr()
    {
        if (!fmtors.containsKey(this))
        {
            return getTypeName();
        }

        return fmtors.get(this).fmt(this);
    }
}