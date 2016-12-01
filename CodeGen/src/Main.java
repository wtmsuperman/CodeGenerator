/**
 * Created by Administrator on 2016/11/28.
 */

import codegen.java.Generator;
import metaparser.meta.ClassType;
import metaparser.meta.EnumType;
import metaparser.meta.Type;
import metaparser.parser.xml.XMLParser;

public class Main {

    public static void main(String[] args) {
        XMLParser xmlParser = new XMLParser();
        boolean ret = xmlParser.parse("config/skill.xml");
        if (!ret) {
            System.out.println("parse faield");
            return;
        }

        Generator gen = new Generator();
        gen.init();
        gen.genEnum((EnumType)Type.getType("MonsterUseType"), "config/enum_java.ftl", "gencode/", "java");
        gen.genClass((ClassType) Type.getType("Item"), "config/class_java.ftl", "gencode/", "java");
        gen.genClass((ClassType) Type.getType("TaskItem"), "config/class_java.ftl", "gencode/", "java");
        gen.genClass((ClassType) Type.getType("Monster"), "config/class_java.ftl", "gencode/", "java");
    }
}
