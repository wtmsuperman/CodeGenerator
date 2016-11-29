package generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/29.
 */

public abstract class AbsGenerator {
    private String nameSpace;

    public void parse(Parser parser)
    {
        parser.parse(this);
    }

    //override if you wanner generate multi files
    public void process(String templatePath,String outputPath)
    {
        generate(makeTemplate(templatePath),outputPath,makeRoot());
    }

    protected Template makeTemplate(String templatePath)
    {
        Configuration cfg = new Configuration();
        try {
            Template t = cfg.getTemplate(templatePath);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void generate(Template t,String outputPath,HashMap<String,Object> root)
    {
        try {
            t.process(root,new FileWriter(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String,Object> makeRoot()
    {
        HashMap<String,Object> root = new HashMap<>();
        makeRoot(root);
        return root;
    }

    protected abstract void makeRoot(HashMap<String,Object> toFill);
}
