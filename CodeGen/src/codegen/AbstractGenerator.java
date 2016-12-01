package codegen;

import metaparser.meta.ClassType;
import metaparser.meta.EnumType;

/**
 * Created by Administrator on 2016/12/1.
 */

public abstract class AbstractGenerator {

    public AbstractGenerator()
    {
        init();
    }

    public abstract void init();
    public abstract void genClass(ClassType classType, String templatePath, String outputPath, String typeName);
    public abstract void genEnum(EnumType enumType, String templatePath, String outputPath, String typeName);

}
