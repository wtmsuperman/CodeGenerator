package generator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class EnumObj{

    public class EnumItem
    {
        private String name;
        private int value;

        public EnumItem() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private List<EnumItem> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public EnumObj()
    {
        items = new ArrayList<>();
    }

    public void addItem(EnumItem item)
    {
        items.add(item);
    }

    public List<EnumItem> getItems() {
        return items;
    }

    public void setItems(List<EnumItem> items) {
        this.items = items;
    }
}
