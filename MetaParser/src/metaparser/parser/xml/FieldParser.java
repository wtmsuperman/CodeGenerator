package metaparser.parser.xml;

import metaparser.meta.Field;
import metaparser.meta.Meta;
import metaparser.meta.Type;
import metaparser.meta.basic.ListType;
import metaparser.meta.basic.MapType;

/**
 * Created by Administrator on 2016/11/30.
 */
public class FieldParser extends XMLMetaParser {
    public FieldParser(String nodeName) {
        super(nodeName);
    }

    public static Type parseType(String str)
    {
        //eg:
        //int
        //string
        //list@int
        //map@int,bean
        //list@list@int

        Type tryGet = Type.getType(str);
        if (tryGet != null)
        {
            return tryGet;
        }

        String[] sub = split(str, "@");

        String pre = sub[0];
        if (pre.equalsIgnoreCase("list")) {
            ListType type = new ListType();
            Type valueType = parseType(sub[1]);
            type.setValueType(valueType);
            return type;
        } else if (pre.equalsIgnoreCase("map")) {
            String[] kv = split(sub[1], ",");
            if (kv.length < 2) {
                throw new RuntimeException("map need key and value " + str);
            }
            Type keyType = parseType(kv[0]);
            Type valueType = parseType(kv[1]);
            MapType map = new MapType();
            map.setKeyType(keyType);
            map.setValueType(valueType);
            return map;
        }
        throw new RuntimeException("parse type failed " + str);
    }

    @Override
    protected boolean parseAttr(Meta meta, String name, String value) {
        Field field = (Field) meta;
        if (name.equalsIgnoreCase("name")) {
            field.setFieldName(value);
            return true;
        } else if (name.equalsIgnoreCase("type")) {
            field.setFieldType(parseType(value));
            return true;
        }
        return super.parseAttr(meta, name, value);
    }

    protected static String[] split(String str, String p) {
        String[] sub = new String[2];
        int index = str.indexOf(p);
        sub[0] = str.substring(0,index);
        sub[1] = str.substring(index+1,str.length());
        return sub;
    }

    @Override
    protected Meta create() {
        return new Field();
    }
}

