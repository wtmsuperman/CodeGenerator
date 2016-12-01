/**
 * Created by Administrator on 2016/11/28.
 */

import codegen.java.EnumGenerator;
import metaparser.meta.EnumType;
import metaparser.parser.xml.XMLParser;
import metaparser.meta.Type;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        XMLParser xmlParser = new XMLParser();
        boolean ret = xmlParser.parse("config/skill.xml");
        if (!ret)
        {
            System.out.println("parse faield");
            return;
        }

        EnumGenerator gen = new EnumGenerator();
        gen.gen(Type.getType("MonsterUseType"),"config/enum_java.ftl","","java");
    }
}
