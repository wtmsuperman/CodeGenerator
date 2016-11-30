package meta;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/29.
 */
public class Meta {

    private HashMap<String, Object> metaDatas = new HashMap<>();

    public void addMetaData(String key, Object val) {
        metaDatas.put(key, val);
    }

    public Object getMetaData(String key) {
        return metaDatas.get(key);
    }
}
