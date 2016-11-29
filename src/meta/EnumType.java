package meta;

import java.util.ArrayList;
import java.util.HashMap;
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

    private HashMap<String,EnumCase> cases;
    private String enumName;

    public EnumType()
    {
        cases = new HashMap<>();
    }

    public EnumType(String enumName)
    {
        cases = new HashMap<>();
        setEnumName(enumName);
    }

    public void newEnum()
    {
        Type.addType(this);
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    @Override
    public String getTypeName() {
        return enumName;
    }

    @Override
    public boolean isEnum() {
        return true;
    }

    public void addCase(EnumCase c)
    {
        if (cases.put(c.getName(),c) != null)
        {
            throw new RuntimeException("duplicate enum case:" + c.getName());
        }
    }

    public EnumCase getCase(String caseName)
    {
        return cases.get(caseName);
    }

    public boolean contains(String caseName)
    {
        return cases.containsKey(caseName);
    }

    public List<EnumCase> getCases() {
        List<EnumCase> all = new ArrayList<>(cases.size());
        cases.forEach((k,v) -> all.add(v));
        return all;
    }
}