package generator.parser.json;

import generator.AbsGenerator;
import generator.EnumGenerator;

/**
 * Created by Administrator on 2016/11/29.
 */
public class EnumJsonParser extends AbstractJsonParser {
    public EnumJsonParser(String jsonPath) {
        super(jsonPath);
    }

    @Override
    public void parse(AbsGenerator gen) {
        EnumGenerator enumGen = (EnumGenerator) gen;
    }
}
