/**
 * Created by Administrator on 2016/11/28.
 */
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public void Generate(String ftlPath,String outputPath,Map<String,Object> root)
    {
        Configuration cfg = new Configuration();
        try {
            Template t = cfg.getTemplate(ftlPath);
            t.process(root,new FileWriter(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
    }
}
