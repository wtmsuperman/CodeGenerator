package cfg.Skill;

import cfg.TaskItem;

public class Test  {

    private String planningDes;      //策划描述
    private String name;      //怪物名称
    private TaskItem TaskItem;      //测试

    public String getPlanningDes()
    {
        return planningDes;
    }

    public void setPlanningDes(String planningDes)
    {
        this.planningDes = planningDes;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public TaskItem getTaskItem()
    {
        return TaskItem;
    }

    public void setTaskItem(TaskItem TaskItem)
    {
        this.TaskItem = TaskItem;
    }

}