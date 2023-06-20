package PlaneFight;

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject {
    protected int YSpeed;//设置速度
    protected int XSpeed;
    private int index;//设置获取图片的顺序
    int f;
    Bullet(int x, int y, String direction,int f) {//设置方向变量
        super(8,14,x,y);//传参
        image = Images.bullets[index];//获取图片

        if(direction == "up"){//设置方向变量，当方向向上时，向上移动并调用向上的子弹的图片
            YSpeed = -3;
            index = 0;
        }else if(direction == "down"){//设置方向变量，当方向向下时，向下移动并调用向下的子弹的图片
            YSpeed = 3;
            index = 1;
        }
    }
    public int getYSpeed() {
        return YSpeed;
    }
    public void setYSpeed(int YSpeed) {
        this.YSpeed = YSpeed;
    }
    @Override
    public void step() {//子弹移动
        y=y+ YSpeed;


    }
    @Override
    /*返回子弹图片，Images.bullets[0]是向上的，Images.bullets[1]是向下的
     * 当状态为DEAD时,改变状态为REMOVE*/
    public BufferedImage getImage() {
        if(isLife()){
            return Images.bullets[index];
        }else if(isDead()){
            state = REMOVE;
            return null;
        }
        return null;
    }
    @Override
    public boolean out() {//判断是否出界
        return y<=0 || y>=Game.HEIGHT;
    }
}
class Bullet_2 extends Bullet {

    Bullet_2(int x, int y,int f) {
        super(x,y,"up",f);
        XSpeed=-4;
    }

    @Override
    public BufferedImage getImage() {
       if(isLife()){
           return Images.bullet_2;
       }else if(isDead()){
           state = REMOVE;
           return null;
       }
       return null;
    }

    @Override
    public void step() {

        y += YSpeed;
        x += XSpeed; //x加向左
        if (x <= 0|x >= Game.WIDTH ) { //x>=(屏幕宽-蜜蜂宽)时，x减(向左)x<=0时，x加(向右)
            XSpeed = -1 * XSpeed;
    }}

    @Override
    public boolean out() {
        return y<=0 || y>=Game.HEIGHT;
    }
}
class Bullet_3 extends Bullet {

    Bullet_3(int x, int y,int f) {
        super(x,y,"up",f);
        XSpeed=4;
    }

    @Override
    public BufferedImage getImage() {
        if(isLife()){
            return Images.bullet_3;
        }else if(isDead()){
            state = REMOVE;
            return null;
        }
        return null;
    }

    @Override
    public void step() {

        y += YSpeed;
        x += XSpeed; //x-向右)
        if (x >= Game.WIDTH | x <= 0) { //x>=(屏幕宽-蜜蜂宽)时，x减(向左)x<=0时，x加(向右)
            XSpeed = -1 * XSpeed;
        }
    }

    @Override
    public boolean out() {
        return y<=0 || y>=Game.HEIGHT;
    }
}
class Bullet_4 extends Bullet {

    Bullet_4(int x, int y,int f) {
        super(x,y,"up",f);
        this.f=f;
        XSpeed=-2;
    }

    @Override
    public BufferedImage getImage() {
        if(isLife()){
            return Images.bullet_4;
        }else if(isDead()){
            state = REMOVE;
            return null;
        }
        return null;
    }

    @Override
    public void step() {
        if (f==0){
        y=y+ YSpeed;
        }else if (f==1){
            y += YSpeed;
            x += XSpeed; //x加(向左或向右)
            if (x >= Game.WIDTH | x <= 0) { //x>=(屏幕宽-蜜蜂宽)时，x减(向左)x<=0时，x加(向右)
                XSpeed = -1 * XSpeed;
            }
    }else if (f==2){
            y += YSpeed;
            x -= XSpeed; //x加(向左或向右)
            if (x >= Game.WIDTH | x <= 0) { //x>=(屏幕宽-蜜蜂宽)时，x减(向左)x<=0时，x加(向右)
                XSpeed = -XSpeed;
            }
        }
    }

    @Override
    public boolean out() {
        return y<=0 || y>=Game.HEIGHT;
    }
}
