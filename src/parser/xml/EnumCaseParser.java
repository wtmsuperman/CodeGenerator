package parser.xml;

import meta.EnumCase;
import meta.Meta;

/**
 * Created by Administrator on 2016/11/30.
 */

public class EnumCaseParser extends XMLMetaParser {
    public EnumCaseParser(String nodeName) {
        super(nodeName);
    }

    @Override
    protected boolean parseAttr(Meta meta, String name, String value) {
        EnumCase caseType = (EnumCase) meta;
        if (name.equalsIgnoreCase("name")) {
            caseType.setName(value);
            return true;
        } else if (name.equalsIgnoreCase("value")) {
            caseType.setValue(Integer.parseInt(value));
            return true;
        }
        return super.parseAttr(meta, name, value);
    }

    @Override
    protected Meta create() {
        return new EnumCase();
    }
}
