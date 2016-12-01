package codegen.java;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import metaparser.meta.ClassType;
import metaparser.meta.Field;
import metaparser.meta.Type;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
public class ClassGenerator {
    public void gen(ClassType classType, String templatePath, String outputPath, String typeName) {
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
}
