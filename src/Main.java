/**
 * Created by Administrator on 2016/11/28.
 */

import meta.Type;
import parser.xml.XMLParser;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        XMLParser xmlParser = new XMLParser();
        xmlParser.parse("config/skill.xml");
        Collection<Type> allTypes = Type.getAllType();
        for (Type t : allTypes) {
            System.out.println(t.getTypeName());
        }
    }
}
