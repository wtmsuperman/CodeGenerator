package codegen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import metaparser.meta.ClassType;
import metaparser.meta.EnumType;
import metaparser.meta.Type;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Administrator on 2016/12/1.
 */

public abstract class AbstractGenerator {

    Configuration configuration;

    protected String outputPath;
    protected String templatePath;
    protected String classSharedTemplate;
    protected String enumSharedTemplate;
    private HashMap<String,String> templateMaps;
    private HashMap<String,String> typeTemplateMaps; //特定类型的特殊模板

    public AbstractGenerator()
    {
        outputPath = "";
        templatePath = "";
        configuration = new Configuration();
        templateMaps = new HashMap<>();
        typeTemplateMaps = new HashMap<>();
        init();
    }

    protected abstract void init();
    protected abstract void genClass(ClassType classType);
    protected abstract void genEnum(EnumType enumType);


    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }


    //命名空间
    public String calcNameSpace(String name) {
        int index = name.lastIndexOf(".");
        if (index <= 0) {
            return null;
        }

        return name.substring(0, index);
    }

    //不包含命名空间的名字
    public String calcName(String name)
    {
        int index = name.lastIndexOf(".");
        if (index <= 0) {
            return name;
        }

        return name.substring(index+1, name.length());
    }

    protected void mkDirIfNeeded(String name)
    {
        int index = -1;
        while (true)
        {
            index = name.indexOf('.',index+1);
            if (index <= 0)
                break;
            String folder = name.substring(0,index);
            folder = folder.replace('.','/');
            File f = new File(outputPath + folder);
            if (!f.exists())
            {
                f.mkdir();
            }
        }
    }

    protected String getOutputPath(String name,String fileType)
    {
        String tmp = name.replace('.','/');
        return outputPath + tmp + "." + fileType;
    }

    public void gen() {
        Collection<Type> allTypes = Type.getAllType();
        HashSet<String> allName = new HashSet<>();
        allTypes.forEach((t)->allName.add(t.getTypeName()));
        allName.forEach(n -> mkDirIfNeeded(n));
        for (Type t: allTypes) {
            if (t.getMetaDataAsBoolean("notgen",false) == true)
            {
                continue;
            }

            if (t.isClass())
            {
                genClass((ClassType)t);
            }
            else if(t.isEnum())
            {
                genEnum((EnumType) t);
            }
        }
    }

    protected void genCode(String templatePath, String outputPath, HashMap<String,Object> root)
    {
        try {
            Template template = configuration.getTemplate(templatePath);
            template.process(root, new FileWriter(outputPath));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public void addTemplate(String tag,String name)
    {
        templateMaps.put(tag,name);
    }

    public String getTemplate(Type t,String tag)
    {
        //优先使用自身的设置
        Object data = t.getMetaData(tag + "_ftl");
        if (data != null)
        {
            return getTemplatePath() + (String)data;
        }

        if (t.isClass())
        {
            //向上查找，使用父类型的模板
            ClassType clazz = (ClassType)t;
            while (clazz != null)
            {
                if (typeTemplateMaps.containsKey(clazz.getTypeName()))
                {
                    return getTemplatePath() + typeTemplateMaps.get(clazz.getTypeName());
                }
                clazz = clazz.getBaseClass();
            }
        }

        return getTemplatePath() + templateMaps.get(tag);
    }

    public void setTypeTemplate(String typeName,String template)
    {
        typeTemplateMaps.put(typeName,template);
    }
}
