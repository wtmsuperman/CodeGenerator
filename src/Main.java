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
    public void Gen()
    {
        Configuration cfg = new Configuration();
        Template t = null;
        try {
            t = cfg.getTemplate("");
            Map<String,Object> root = new HashMap<String,Object>();
            t.process(root,new FileWriter("mycode.java"));
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
