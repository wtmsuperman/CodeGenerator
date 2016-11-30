/**
 * Created by Administrator on 2016/11/28.
 */

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
        Collection<Type> allTypes = Type.getAllType();
        for (Type t : allTypes) {
            System.out.println(t.getTypeName());
        }
    }
}
