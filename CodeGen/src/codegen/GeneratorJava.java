package codegen;

import metaparser.meta.*;
import metaparser.meta.basic.ListType;
import metaparser.meta.basic.MapType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
public class GeneratorJava  extends  AbstractGenerator {

    @Override
    protected void init() {
        Type.addTypeStdFormatter(Type.String, new TypeStrFmt() {
            @Override
            public String fmt(Type t) {
                return "String";
            }
        });

        Type.addTypeStdFormatter(Type.List, new TypeStrFmt() {
            @Override
            public String fmt(Type t) {
                ListType l = (ListType) t;
                String boxName = getBoxName(l.getValueType());
                return "List<" + boxName + ">";
            }
        });

        Type.addTypeStdFormatter(Type.Map, new TypeStrFmt() {
            @Override
            public String fmt(Type t) {
                MapType m = (MapType) t;
                return "Map<" + getBoxName(m.getKeyType()) + "," + getBoxName(m.getValueType()) + ">";
            }
        });

        Type.setClassFmtter(new TypeStrFmt() {
            @Override
            public String fmt(Type t) {
                return calcName(t.getTypeName());
            }
        });

        Type.setEnumFmtter(new TypeStrFmt() {
            @Override
            public String fmt(Type t) {
                return calcName(t.getTypeName());
            }
        });
    }

    public String getBoxName(Type t) {
        String boxName = "";
        HashMap<Type, String> map = new HashMap<Type, String>();
        map.put(Type.Int, "Integer");
        map.put(Type.String, "String");
        map.put(Type.Float, "Float");
        map.put(Type.Bool,"Boolean");
        if (map.containsKey(t)) {
            boxName = map.get(t);
        } else {
            boxName = t.getTypeStr();
        }
        return boxName;
    }

    @Override
    protected void genClass(ClassType classType) {
        HashMap<String, Object> root = new HashMap<>();

        List<Field> fields = classType.getFields();
        HashSet<String> usings = new HashSet<>();
        String ns = calcNameSpace(classType.getTypeName());

        for (Field field : fields) {
            Type valType = field.getFieldType();
            if (valType.isType(Type.List)) {
                usings.add("java.util.List");
            } else if (valType.isType(Type.Map)) {
                usings.add("java.util.Map");
            } else if (valType.isClass() || valType.isEnum()) {
                String fns = calcNameSpace(valType.getTypeName());
                if (!fns.equals(ns)) //命名空间不同，加入到引用中
                {
                    usings.add(valType.getTypeName());
                }
            }
        }

        List<String> usingsList = new ArrayList<>();
        usings.forEach(s -> usingsList.add(s));

        root.put("name_space", ns);
        root.put("usings", usingsList);
        root.put("class", classType);

        genCode(getTemplate(classType,"class"),getOutputPath(classType.getTypeName(),"java"),root);
    }

    @Override
    protected void genEnum(EnumType enumType) {
        HashMap<String, Object> root = new HashMap<>();

        String ns = calcNameSpace(enumType.getTypeName());
        root.put("name_space", ns);
        root.put("enum", enumType);

        genCode(getTemplate(enumType,"enum"),getOutputPath(enumType.getTypeName(),"java"),root);
    }
}
