package PlaneFight;

import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class FlyingObject {

    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected int life;
    protected BufferedImage image;

    //定义状态常量
    public static final int LIFE = 0;
    public static final int DEAD = 1;
    public static final int REMOVE = 2;
    //设置当前状态
    protected int state = LIFE;
    //提供英雄、子弹的构造方法
    FlyingObject(int width,int height,int x,int y){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
    //提供敌人，奖励物的构造方法，
    FlyingObject(int width,int height,int life){
        this.width = width;
        this.height = height;
        Random r = new Random();
        this.x = r.nextInt(Game.WIDTH-width);
        this.y = -height;
        this.life = life;
    }
    //获取图片的抽象方法,
    public abstract BufferedImage getImage() ;
    //获取移动的抽象方法
    public abstract void step();
    //判断是否出界的抽象方法
    public abstract boolean out();
    //判断碰撞
    public boolean shootBy(FlyingObject object){
        return x>=object.x - this.width && x<=object.x + object.width && y>=object.y - this.height && y<=object.y + object.height;
    }
    public Bullet[] shoot(){//提供射子弹的方法
        return new Bullet[0];
    }
    //减血
    public void decreaseLife(){
        life--;
    }
    //判断是否活着
    public boolean isLife(){
        return state==LIFE;
    }

    //判断是否死了
    public boolean isDead(){
        return state==DEAD;
    }

    //判断状态是否要移除
    public boolean isRemove(){
        return state==REMOVE;
    }

    //将状态改为死了
    public void goDie(){
        state = DEAD;
    }
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
