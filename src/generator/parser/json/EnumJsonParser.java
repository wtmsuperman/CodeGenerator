package generator.parser.json;

import generator.AbsGenerator;
import generator.EnumGenerator;
import generator.EnumObj;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class EnumJsonParser extends AbstractJsonParser {
    public EnumJsonParser(String jsonPath) {
        super(jsonPath);
    }

    @Override
    public void parse(AbsGenerator toFill) {
        List<EnumObj> all = parseArray(EnumObj.class);
        EnumGenerator enumGen = (EnumGenerator) toFill;
        all.forEach(enumObj -> enumGen.addEnum(enumObj));
    }
}
