package generator.parser.xml;

import generator.AbsGenerator;
import generator.EnumGenerator;

/**
 * Created by Administrator on 2016/11/29.
 */
public class EnumXmlParser extends AbstractXmlParser {

    public EnumXmlParser(String xmlPath) {
        super(xmlPath);
    }

    @Override
    public void parse(AbsGenerator gen) {
        EnumGenerator enumGen = (EnumGenerator) gen;
    }
}
