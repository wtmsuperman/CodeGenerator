/**
 * Created by Administrator on 2016/11/28.
 */

import codegen.java.ClassGenerator;
import codegen.java.EnumGenerator;
import metaparser.meta.ClassType;
import metaparser.meta.Type;
import metaparser.meta.TypeStrFmt;
import metaparser.parser.xml.XMLParser;

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

        Type.addTypeStrFormator(Type.getType("string"), new TypeStrFmt() {
            @Override
            public String fmt(Type t) {
                return "String";
            }
        });
        ClassGenerator gen2 = new ClassGenerator();
        gen2.gen((ClassType) Type.getType("Item"), "config/class_java.ftl", "", "java");
    }
}
