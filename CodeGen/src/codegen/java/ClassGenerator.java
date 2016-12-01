package codegen.java;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import metaparser.meta.ClassType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/1.
 */
public class ClassGenerator {
    public void gen(ClassType classType, String templatePath, String outputPath, String typeName) {
        HashMap<String, Object> root = new HashMap<>();

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
