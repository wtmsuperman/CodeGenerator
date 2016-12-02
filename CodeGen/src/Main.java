/**
 * Created by Administrator on 2016/11/28.
 */

import codegen.GeneratorJava;
import metaparser.parser.xml.XMLParser;

public class Main {

    public static void main(String[] args) {
        XMLParser xmlParser = new XMLParser();
        boolean ret = xmlParser.parse("config/skill.xml");
        if (!ret) {
            System.out.println("parse failed");
            return;
        }

        GeneratorJava gen = new GeneratorJava();
        gen.setOutputPath("CodeGen/src/");
        gen.setTemplatePath("config/");
        gen.addTemplate("class","class_java.ftl");
        gen.addTemplate("enum","enum_java.ftl");
        gen.setTypeTemplate("msg.Message","msg_java_base.ftl");
        gen.gen();
    }
}
