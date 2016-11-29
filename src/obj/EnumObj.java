package obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class EnumObj{

    public class EnumItem
    {
        private String name;
        private int value;

        public EnumItem(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private List<EnumItem> allItmes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public EnumObj()
    {
        allItmes = new ArrayList<>();
    }

    public void addItem(EnumItem item)
    {
        allItmes.add(item);
    }

    public List<EnumItem> getAllItmes() {
        return allItmes;
    }

    public void setAllItmes(List<EnumItem> allItmes) {
        this.allItmes = allItmes;
    }
}
