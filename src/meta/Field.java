package meta;

/**
 * Created by Administrator on 2016/11/29.
 */

public class Field extends Meta {

    private Type filedType;
    private String filedName;

    public Type getFiledType() {
        return filedType;
    }

    public void setFiledType(Type filedType) {
        this.filedType = filedType;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

}