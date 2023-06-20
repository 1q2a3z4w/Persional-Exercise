package PlaneFight;

import java.awt.image.BufferedImage;

public class AirPlane extends FlyingObject implements Enemy {
    private int speed;
    AirPlane() {
        super(48,50,1);
        speed=2;
    }

    @Override
    public int getScore() {
        return 3;
    }
    /*
     * 获取小敌机的图片，状态为LIFE时返回小敌机图片
     * 状态为DEAD时返回4张爆破图片，全部返回后将状态改为REMOVE，返回空
     */
    int index = 1;
    @Override
    public BufferedImage getImage() {
        if(isLife()){
            return Images.airplanes[0];
        }else if(isDead()){
            if(index == 5){
                state = REMOVE;
                return null;
            }
            return Images.airplanes[index++];
        }
        return null;
    }
    //移动方式
    @Override
    public void step() {
            y += speed;
    }
    //判断出界
    @Override
    public boolean out() {
        return y>=Game.HEIGHT;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    @Override
    public void setLife(int life) {
        super.setLife(life);
    }

    @Override
    public int getLife() {
        return super.getLife();
    }
}
