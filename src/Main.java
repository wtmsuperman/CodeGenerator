/**
 * Created by Administrator on 2016/11/28.
 */
import generator.EnumGenerator;
import generator.parser.json.EnumJsonParser;

public class Main {

    public static void main(String[] args)
    {
        EnumGenerator e = new EnumGenerator();
        e.parse(new EnumJsonParser("config/enum.json"));
    }
}
