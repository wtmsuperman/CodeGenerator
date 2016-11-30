package parser.xml;

import meta.EnumCase;
import meta.EnumType;
import meta.Meta;

/**
 * Created by Administrator on 2016/11/30.
 */
public class EnumParser extends XMLMetaParser {
    public EnumParser(String nodeName) {
        super(nodeName);
    }

    @Override
    protected Meta create() {
        return new EnumType();
    }

    @Override
    protected boolean parseAttr(Meta meta, String name, String value) {
        EnumType enumType = (EnumType) meta;
        if (name.equalsIgnoreCase("name")) {
            enumType.setEnumName(value);
            return true;
        }
        return super.parseAttr(meta, name, value);
    }

    @Override
    public void parseDone(Meta meta) {
        EnumType enumType = (EnumType) meta;
        enumType.newEnum();
    }

    @Override
    protected boolean handleChild(Meta parent, Meta child) {
        EnumType enumType = (EnumType) parent;
        if (child instanceof EnumCase) {
            enumType.addCase((EnumCase) child);
            return true;
        }
        return super.handleChild(parent, child);
    }
}

