/**
 * Created by Administrator on 2016/11/28.
 */

import codegen.java.ClassGenerator;
import codegen.java.EnumGenerator;
import metaparser.meta.ClassType;
import metaparser.meta.Type;
import metaparser.meta.TypeStrFmt;
import metaparser.meta.basic.ListType;
import metaparser.parser.xml.XMLParser;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        XMLParser xmlParser = new XMLParser();
        boolean ret = xmlParser.parse("config/skill.xml");
        if (!ret) {
            System.out.println("parse faield");
            return;
        }

        EnumGenerator gen = new EnumGenerator();
        gen.gen(Type.getType("MonsterUseType"), "config/enum_java.ftl", "", "java");

        Type.addTypeStrFormator(Type.String.getTypeName(), new TypeStrFmt() {
            @Override
            public String fmt(Type t) {
                return "String";
            }
        });

        Type.addTypeStrFormator(Type.List.getTypeName(), new TypeStrFmt() {
            @Override
            public String fmt(Type t) {
                ListType l = (ListType)t;
                String boxName = "";
                HashMap<Type,String> map = new HashMap<Type, String>();
                map.put(Type.Int,"Integer");
                map.put(Type.String,"String");
                map.put(Type.Float,"Float");
                if (map.containsKey(l.getValueType()))
                {
                    boxName = map.get(l.getValueType());
                }
                else
                {
                    boxName = l.getValueType().getTypeStr();
                }
                return "List<" + boxName + ">";
            }
        });

        ClassGenerator gen2 = new ClassGenerator();
        gen2.gen((ClassType) Type.getType("Monster"), "config/class_java.ftl", "", "java");
    }
}
