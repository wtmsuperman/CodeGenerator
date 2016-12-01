package codegen.java;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import metaparser.meta.EnumType;
import metaparser.meta.Type;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/1.
 */
public class EnumGenerator {
    public void gen(Type enumType, String templatePath, String outputPath, String typeName)
    {
        HashMap<String,Object> root = new HashMap<>();

        root.put("enum",enumType);

        Configuration config = new Configuration();
        try {
            Template template = config.getTemplate(templatePath);
            String path = outputPath + enumType.getTypeName() + "." + typeName;
            template.process(root,new FileWriter(path));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
