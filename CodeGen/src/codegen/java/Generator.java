package codegen.java;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import metaparser.meta.*;
import metaparser.meta.basic.ListType;
import metaparser.meta.basic.MapType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
public class Generator {

    public void init()
    {
        Type.addTypeStrFormator(Type.String, new TypeStrFmt() {
            @Override
            public String fmt(Type t) {
                return "String";
            }
        });

        Type.addTypeStrFormator(Type.List, new TypeStrFmt() {
            @Override
            public String fmt(Type t) {
                ListType l = (ListType)t;
                String boxName = getBoxName(l.getValueType());
                return "List<" + boxName + ">";
            }
        });

        Type.addTypeStrFormator(Type.Map, new TypeStrFmt() {
            @Override
            public String fmt(Type t) {
                MapType m = (MapType)t;
                return "Map<" + getBoxName(m.getKeyType()) + "," + getBoxName(m.getValueType()) + ">";
            }
        });
    }

    public String getBoxName(Type t)
    {
        String boxName = "";
        HashMap<Type,String> map = new HashMap<Type, String>();
        map.put(Type.Int,"Integer");
        map.put(Type.String,"String");
        map.put(Type.Float,"Float");
        if (map.containsKey(t))
        {
            boxName = map.get(t);
        }
        else
        {
            boxName = t.getTypeStr();
        }
        return boxName;
    }

    public void genClass(ClassType classType, String templatePath, String outputPath, String typeName) {
        HashMap<String, Object> root = new HashMap<>();

        List<Field> fields = classType.getFields();
        HashSet<String> usings = new HashSet<>();
        for (Field field : fields) {
            if (field.getFieldType().isType(Type.List))
            {
                usings.add("java.util.List");
            }
            else if(field.getFieldType().isType(Type.Map))
            {
                usings.add("java.util.Map");
            }
        }

        List<String> usingsList = new ArrayList<>();
        usings.forEach(s -> usingsList.add(s));
        String ns = getNameSpace(outputPath);
        root.put("name_space",ns);
        root.put("usings",usingsList);
        root.put("class", classType);

        Configuration config = new Configuration();
        try {
            Template template = config.getTemplate(templatePath);
            String path = outputPath + classType.getTypeName() + "." + typeName;
            template.process(root, new FileWriter(path));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public void genEnum(EnumType enumType, String templatePath, String outputPath, String typeName) {
        HashMap<String, Object> root = new HashMap<>();

        String ns = getNameSpace(outputPath);
        root.put("name_space",ns);
        root.put("enum", enumType);

        Configuration config = new Configuration();
        try {
            Template template = config.getTemplate(templatePath);
            String path = outputPath + enumType.getTypeName() + "." + typeName;
            template.process(root, new FileWriter(path));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public String getNameSpace(String outputPath)
    {
        String ns = outputPath.replace("/",".");
        ns = ns.substring(0,ns.lastIndexOf('.'));
        return ns;
    }
}
