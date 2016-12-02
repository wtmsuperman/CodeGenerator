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

    public int getMetaDataAsInt(String key)
    {
        return Integer.parseInt(getMetaDataAsString(key));
    }

    public float getMetaDataAsFloat(String key)
    {
        return Float.parseFloat(getMetaDataAsString(key));
    }

    public boolean getMetaDataAsBoolean(String key)
    {
        return Boolean.parseBoolean(getMetaDataAsString(key));
    }

    public boolean getMetaDataAsBoolean(String key,boolean defaultVal)
    {
        Object val = metaDatas.get(key);
        if (key == null) return defaultVal;
        return Boolean.parseBoolean((String)val);
    }

    public boolean hasMetaData(String key)
    {
        return metaDatas.containsKey(key);
    }
}
