package gencode;

public enum MonsterUseType{

    GHOST(1,"GHOST")  /*抓鬼*/ ,
    DEMON(3,"DEMON")  /*封妖*/ ,
    CREEPS(0,"CREEPS") ,
    STORY(2,"STORY")  /*剧情*/ ,
    ;

    private int value;
    private String desc;

    MonsterUseType(int value)
    {
        this.value = value;
    }

    MonsterUseType(int value,String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)",desc,value);
    }
}
