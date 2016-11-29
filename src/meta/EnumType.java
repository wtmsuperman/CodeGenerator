package meta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class EnumType extends Type {

    public class EnumCase
    {
        private String name;
        private int value;

        public EnumCase() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private List<EnumCase> cases;
    private String enumName;

    public EnumType()
    {
        cases = new ArrayList<>();
    }

    public void newEnum()
    {
        Type.addType(this);
    }

    @Override
    public String getTypeName() {
        return enumName;
    }

    public void addCase(EnumCase c)
    {
        cases.add(c);
    }

    public List<EnumCase> getCases() {
        return cases;
    }
}