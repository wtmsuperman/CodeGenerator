package generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class EnumGenerator extends AbsGenerator {

    private List<EnumObj> objs;

    public EnumGenerator() {
        objs = new ArrayList<>();
    }

    public void addEnum(EnumObj obj) {
        objs.add(obj);
    }

    @Override
    protected void makeRoot(HashMap<String, Object> toFill) {
        toFill.put("enums", this);
    }
}
