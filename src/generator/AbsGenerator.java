package generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/29.
 */

public abstract class AbsGenerator {
    private String namespace;

    public void parse(Parser parser)
    {
        parser.parse(this);
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void process(String namespace,String templatePath,String outputPath) throws IOException, TemplateException
    {
        this.namespace = namespace;
        process(templatePath,outputPath);
    }

    //override if you wanner generate multi files
    public void process(String templatePath,String outputPath) throws IOException, TemplateException {
        if (namespace == null || namespace.isEmpty())
        {
            File tmp = new File(templatePath);
            namespace = tmp.getName().split(".")[0];
        }
        HashMap<String,Object> root = makeRoot();
        root.put("namespace",namespace);
        generate(makeTemplate(templatePath),outputPath,makeRoot());
    }

    protected Template makeTemplate(String templatePath) throws IOException {
        Configuration cfg = new Configuration();
        Template t = cfg.getTemplate(templatePath);
        return t;
    }

    protected void generate(Template t,String outputPath,HashMap<String,Object> root) throws IOException, TemplateException {
        t.process(root,new FileWriter(outputPath));
    }

    public HashMap<String,Object> makeRoot()
    {
        HashMap<String,Object> root = new HashMap<>();
        makeRoot(root);
        return root;
    }

    protected abstract void makeRoot(HashMap<String,Object> toFill);
}
