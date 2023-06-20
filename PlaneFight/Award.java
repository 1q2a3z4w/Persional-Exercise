package PlaneFight;

public interface Award {//奖励接口
    int DOUBLE_FIRE=0;//加火力
    int LIFE=1;//加命
    int ENERGY=3;//能量
    int WEAPON=4;
    int getType();
}
