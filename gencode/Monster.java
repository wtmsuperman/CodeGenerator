package gencode;

import java.util.List;
import java.util.Map;

public class Monster  {

    private float damage;
    private List<Item> itemDrops;
    private String planningDes;
    private int modelId;
    private String name;
    private List<Integer> attrs;
    private Map<String,String> desc;

    public float getDamage()
    {
        return damage;
    }

    public void setDamage(float damage)
    {
        this.damage = damage;
    }
    public List<Item> getItemDrops()
    {
        return itemDrops;
    }

    public void setItemDrops(List<Item> itemDrops)
    {
        this.itemDrops = itemDrops;
    }
    public String getPlanningDes()
    {
        return planningDes;
    }

    public void setPlanningDes(String planningDes)
    {
        this.planningDes = planningDes;
    }
    public int getModelId()
    {
        return modelId;
    }

    public void setModelId(int modelId)
    {
        this.modelId = modelId;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public List<Integer> getAttrs()
    {
        return attrs;
    }

    public void setAttrs(List<Integer> attrs)
    {
        this.attrs = attrs;
    }
    public Map<String,String> getDesc()
    {
        return desc;
    }

    public void setDesc(Map<String,String> desc)
    {
        this.desc = desc;
    }

}