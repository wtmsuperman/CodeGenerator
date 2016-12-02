package cfg;

public enum MonsterAttackType{

    INATTACK(1,"INATTACK")  /*内功*/ ,
    AUXILIARYMAINMONSTER(7,"AUXILIARYMAINMONSTER")  /*辅助主怪*/ ,
    SEALMAINMONSTER(6,"SEALMAINMONSTER")  /*封印主怪*/ ,
    OUTATTACK(0,"OUTATTACK")  /*外功*/ ,
    SEAL(2,"SEAL")  /*封印*/ ,
    AUXILIARY(3,"AUXILIARY")  /*辅助*/ ,
    OUTATTACKMAINMONSTER(4,"OUTATTACKMAINMONSTER")  /*外功主怪*/ ,
    INATTACKMAINMONSTER(5,"INATTACKMAINMONSTER")  /*内功主怪*/ ,
    ;

    private int value;
    private String desc;

    MonsterAttackType(int value)
    {
        this.value = value;
    }

    MonsterAttackType(int value,String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)",desc,value);
    }
}
