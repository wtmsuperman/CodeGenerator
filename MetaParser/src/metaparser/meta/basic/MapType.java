package metaparser.meta.basic;

import metaparser.meta.Type;

/**
 * Created by Administrator on 2016/11/30.
 */
public class MapType extends CollectionType {
    protected Type keyType;

    public Type getKeyType() {
        return keyType;
    }

    public void setKeyType(Type keyType) {
        this.keyType = keyType;
    }

    @Override
    public String getTypeName() {
        return "map";
    }
}
