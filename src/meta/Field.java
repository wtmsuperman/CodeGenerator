package meta;

/**
 * Created by Administrator on 2016/11/29.
 */

public class Field extends Meta {

    private Type fieldType;
    private String fieldName;

    public Type getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        setFieldType(Type.getType(fieldType));
    }

    public void setFieldType(Type fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}