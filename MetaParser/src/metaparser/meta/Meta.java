package metaparser.meta;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/29.
 */
public class Meta {

    private HashMap<String, Object> metaDatas = new HashMap<>();
    private String comments;

    public void addMetaData(String key, Object val) {
        metaDatas.put(key, val);
    }

    public Object getMetaData(String key) {
        return metaDatas.get(key);
    }

    public String getMetaDataAsString(String key)
    {
        return (String)metaDatas.get(key);
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean metaDataValueIsTrue(String key)
    {
        String data = (String) metaDatas.get(key);
        if (data == null) return false;
        return data.equalsIgnoreCase("true");
    }
}
