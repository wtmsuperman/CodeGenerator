package metaparser.meta.basic;

import metaparser.meta.Type;

/**
 * Created by Administrator on 2016/11/29.
 */
public abstract class CollectionType extends Type {
    protected Type valueType;

    public Type getValueType() {
        return valueType;
    }

    public void setValueType(Type valueType) {
        this.valueType = valueType;
    }
}
