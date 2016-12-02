package metaparser.meta;

import metaparser.meta.basic.*;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/29.
 */

public abstract class Type extends Meta {
    private static HashMap<String, Type> allTypes = new HashMap<>();
    private static HashMap<String, TypeStrFmt> fmtters = new HashMap<>();
    private static TypeStrFmt classFmtter;
    private static TypeStrFmt enumFmtter;

    public static void addType(Type type) {
        String typeName = type.getTypeName();
        if (typeName == null || typeName.isEmpty()) {
            throw new RuntimeException("typeName is empty!");
        }
        if (allTypes.put(typeName, type) != null) {
            throw new RuntimeException("duplicate type: " + typeName);
        }
    }

    public static Type getType(String typeName) {
        return allTypes.get(typeName);
    }

    public static Collection<Type> getAllType() {
        return allTypes.values();
    }

    public static void addTypeStdFormatter(Type t, TypeStrFmt fmt) {
        fmtters.put(t.getTypeName(), fmt);
    }

    public abstract String getTypeName();

    public boolean isClass() {
        return false;
    }

    public boolean isEnum() {
        return false;
    }

    public String getTypeStr() {
        java.lang.String typeName = this.getTypeName();
        if (!fmtters.containsKey(typeName)) {
            if (isClass())
            {
                return classFmtter.fmt(this);
            }
            else if(isEnum())
            {
                return enumFmtter.fmt(this);
            }
            return getTypeName();
        }

        return fmtters.get(typeName).fmt(this);
    }

    public boolean isType(Type type)
    {
        return this.getTypeName().equals(type.getTypeName());
    }

    public static void setClassFmtter(TypeStrFmt classFmtter) {
        Type.classFmtter = classFmtter;
    }

    public static void setEnumFmtter(TypeStrFmt enumFmtter) {
        Type.enumFmtter = enumFmtter;
    }

    public static FloatType Float = new FloatType();
    public static BoolType  Bool = new BoolType();
    public static IntType Int = new IntType();
    public static ListType List = new ListType();
    public static StringType String = new StringType();
    public static MapType Map = new MapType();

    //增加基础类型
    static {
        addType(Float);
        addType(Bool);
        addType(Int);
        addType(List);
        addType(String);
        addType(Map);
    }
}