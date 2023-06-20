package PlaneFight;

import java.awt.image.BufferedImage;

public class BigAirPlane extends FlyingObject implements Enemy {
    private int speed;
    BigAirPlane(){
        super(100,61,5);
        speed=2;
    }
    @Override
    public int getScore() {
        return 8;
    }

    int index = 1;
    public BufferedImage getImage() {
        if(isLife()){
            return Images.BigAirPlanes[0];
        }else if(isDead()){
            if(index==5){
                state = REMOVE;
                return null;
            }
            return Images.BigAirPlanes[index++];
        }
        return null;
    }
    public Bullet[] shoot(){
        Bullet[] bullets = new Bullet[1];
        bullets[0] = new Bullet(x+this.width/2,y+this.height+10,"down",0);
        return bullets;
    }
    @Override
    public void step() {
        y += speed;
    }

    @Override
    //检测出界 大敌机的y坐标大于窗口的高返回true
    public boolean out() {
        return y>=Game.HEIGHT;
    }

    @Override
    public void setLife(int life) {
        super.setLife(life);
    }

    @Override
    public int getLife() {
        return super.getLife();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
