package parser.xml;

import meta.Field;
import meta.Meta;
import meta.Type;
import meta.basic.ListType;
import org.apache.poi.ss.formula.eval.NotImplementedException;

/**
 * Created by Administrator on 2016/11/30.
 */
public class FieldParser extends XMLMetaParser
{
    public FieldParser(String nodeName) {
        super(nodeName);
    }

    @Override
    protected boolean parseAttr(Meta meta, String name, String value) {
        Field field = (Field)meta;
        if (name == "name")
        {
            field.setFieldName(value);
            return true;
        }
        else if (name == "type")
        {
            //eg:
            //int
            //repeated int

            String[] sub = value.split(" ");
            if (sub.length <= 1)
            {
                field.setFieldType(value);
                return true;
            }

            String pre = sub[0];
            if (pre == "repeated")
            {
                ListType type = new ListType();
                type.setValueType(Type.getType(sub[1]));
                field.setFieldType(type);
                return true;
            }
            else if (pre == "map")
            {
                throw new NotImplementedException("map not support yet");
            }

            throw new RuntimeException("unkown type :"+value);
        }
        return super.parseAttr(meta,name,value);
    }

    @Override
    protected Meta create() {
        return new Field();
    }
}

