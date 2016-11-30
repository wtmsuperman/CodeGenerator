package parser.xml;

import meta.Field;
import meta.Meta;
import meta.Type;
import meta.basic.ListType;
import meta.basic.MapType;

/**
 * Created by Administrator on 2016/11/30.
 */
public class FieldParser extends XMLMetaParser {
    public FieldParser(String nodeName) {
        super(nodeName);
    }

    @Override
    protected boolean parseAttr(Meta meta, String name, String value) {
        Field field = (Field) meta;
        if (name.equalsIgnoreCase("name")) {
            field.setFieldName(value);
            return true;
        } else if (name.equalsIgnoreCase("type")) {
            //eg:
            //int
            //string
            //repeated@int
            //map@int,bean

            String[] sub = split(value, "@");

            if (sub.length <= 1) {
                field.setFieldType(value);
                return true;
            }

            String pre = sub[0];
            if (pre.equalsIgnoreCase("repeated")) {
                ListType type = new ListType();
                String valueType = sub[1];
                type.setValueType(Type.getType(valueType));
                field.setFieldType(type);
                return true;
            } else if (pre.equalsIgnoreCase("map")) {
                String[] kv = split(sub[1], ",");
                if (kv.length < 2) {
                    throw new RuntimeException("map need key and value " + value);
                }
                Type keyType = Type.getType(kv[0]);
                Type valueType = Type.getType(kv[1]);
                MapType map = new MapType();
                map.setKeyType(keyType);
                map.setValueType(valueType);
            }

            throw new RuntimeException("unknown type :" + value);
        }
        return super.parseAttr(meta, name, value);
    }

    protected String[] split(String str, String p) {
        String[] sub = str.split(p);
        for (int i = 0; i < sub.length; i++) {
            sub[i] = sub[i].trim();
        }
        return sub;
    }

    @Override
    protected Meta create() {
        return new Field();
    }
}

