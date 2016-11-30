package generator.parser.json;

import com.alibaba.fastjson.JSON;
import generator.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public abstract class AbstractJsonParser implements Parser {

    private byte[] bytes;

    public AbstractJsonParser(String jsonPath) {
        try {
            File f = new File(jsonPath);
            bytes = new byte[(int) f.length()];
            FileInputStream fs = new FileInputStream(f);
            fs.read(bytes);
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T parseObject(Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }

    public <T> List<T> parseArray(Class<T> clazz) {
        return JSON.parseArray(new String(bytes), clazz);
    }
}