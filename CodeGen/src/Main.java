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
            System.out.println("parse faield");
            return;
        }

        GeneratorJava gen = new GeneratorJava("");
        gen.gen();
    }
}
