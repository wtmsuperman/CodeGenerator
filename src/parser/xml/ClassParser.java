package parser.xml;

import meta.ClassType;
import meta.Field;
import meta.Meta;

/**
 * Created by Administrator on 2016/11/30.
 */
public class ClassParser extends XMLMetaParser {
    public ClassParser(String nodeName) {
        super(nodeName);
    }

    @Override
    protected Meta create() {
        return new ClassType();
    }

    @Override
    protected boolean parseAttr(Meta meta, String name, String value) {
        ClassType clazz = (ClassType) meta;
        if (name.equalsIgnoreCase("name")) {
            clazz.setClassName(value);
            return true;
        } else if (name.equalsIgnoreCase("base")) {
            clazz.setBaseClass(value);
            return true;
        }
        return super.parseAttr(meta, name, value);
    }

    @Override
    protected boolean handleChild(Meta parent, Meta child) {
        ClassType clazz = (ClassType) parent;
        if (child instanceof Field) {
            clazz.addFiled((Field) child);
        }
        return true;
    }

    @Override
    public void parseDone(Meta meta) {
        ClassType clazz = (ClassType) meta;
        clazz.newClass();
    }
}

