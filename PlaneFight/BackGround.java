package PlaneFight;

import java.awt.image.BufferedImage;

public class BackGround extends FlyingObject{
    private int speed;
    private int y2;
    BackGround(){
        super(Game.WIDTH,Game.HEIGHT,0,0);
        speed = 1;
        y2 = -height;
    }
    @Override
    public BufferedImage getImage() {
        return Images.background;
    }

    public void step() {
        y += speed;
        y2 += speed;
        if(y>=Game.HEIGHT){
            y = -this.height;
        }
        if(y2>=Game.HEIGHT){
            y2 = -this.height;
        }
    }

    @Override
    public boolean out() {
        return false;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
