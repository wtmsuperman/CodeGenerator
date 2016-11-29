package meta;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/29.
 */

public abstract class Type extends Meta{
    private static HashMap<String,Type> allTyps = new HashMap<>();

    public abstract String getTypeName();

    public static void addType(Type type)
    {
        String typeName = type.getTypeName();
        if (typeName == null || typeName.isEmpty())
        {
            throw new RuntimeException("typeName is empty!");
        }
        if (allTyps.put(typeName,type) != null)
        {
            throw new RuntimeException("duplicate type: " + typeName);
        }
    }

    public static Type getType(String typeName)
    {
        return allTyps.get(typeName);
    }

    public boolean isClass()
    {
        return false;
    }

    public boolean isEnum()
    {
        return false;
    }
}