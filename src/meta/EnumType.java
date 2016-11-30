package meta;

import java.util.*;

/**
 * Created by Administrator on 2016/11/29.
 */
public class EnumType extends Type {
    private HashMap<String, EnumCase> cases;
    private Set<Integer> valueSet;
    private String enumName;

    public EnumType() {
        cases = new HashMap<>();
        valueSet = new HashSet<>();
    }

    public EnumType(String enumName) {
        cases = new HashMap<>();
        setEnumName(enumName);
    }

    public void newEnum() {
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

    public void addCase(EnumCase c) {
        if (cases.containsKey(c.getName())) {
            throw new RuntimeException("duplicate enum case:" + c.getName());
        }

        if (valueSet.contains(c.getValue())) {
            throw new RuntimeException("duplicate enum case value:" + c.getName());
        }

        cases.put(c.getName(), c);
        valueSet.add(c.getValue());
    }

    public void addCase(String name, int value) {
        addCase(new EnumCase(name, value));
    }

    public EnumCase getCase(String caseName) {
        return cases.get(caseName);
    }

    public boolean contains(String caseName) {
        return cases.containsKey(caseName);
    }

    public List<EnumCase> getCases() {
        List<EnumCase> all = new ArrayList<>(cases.size());
        cases.forEach((k, v) -> all.add(v));
        return all;
    }
}