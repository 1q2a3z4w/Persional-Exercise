package PlaneFight;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {
    //定义布尔类型的值，判断是否按下空格键
    public static boolean space;
    private int doubleFire;
    private int weapon;
    Hero() {
        super(100, 106, 140, 300);
        life = 20000;
        doubleFire = 1;
        weapon= 1;
    }

    //随着鼠标的x，y移动
    public void moveTo(int x, int y) {
        this.x = x - this.width / 2;
        this.y = y - this.height / 2;
    }

    //发射子弹
    public Bullet[] shoot() {
        Bullet[] bullets = null;
        if (weapon==1){
        if (doubleFire == 1) {//发射单发
            bullets = new Bullet[1];
            bullets[0] = new Bullet(x + width / 2, y - 15, "up",0);//x坐标少减子弹一半宽度
        } else if (doubleFire == 2) {//双发
            bullets = new Bullet[2];
            bullets[0] = new Bullet(x + width / 4, y - 15, "up",0);//x坐标少减子弹一半宽度
            bullets[1] = new Bullet(x + width * 3 / 4, y - 15, "up",0);//x坐标少减子弹一半宽度
        } else if (doubleFire == 3) {
            bullets = new Bullet[3];
            bullets[0] = new Bullet(x + width / 5, y, "up",0);//x坐标少减子弹一半宽度
            bullets[1] = new Bullet(x + width / 2, y - 15, "up",0);//x坐标少减子弹一半宽度
            bullets[2] = new Bullet(x + width * 4 / 5, y, "up",0);
        } else if (doubleFire == 4) {
            bullets = new Bullet[4];
            bullets[0] = new Bullet(x, y, "up",0);//x坐标少减子弹一半宽度
            bullets[1] = new Bullet(x + width / 3, y - 15, "up",0);//x坐标少减子弹一半宽度
            bullets[2] = new Bullet(x + width * 2 / 3, y - 15, "up",0);
            bullets[3] = new Bullet(x + width, y, "up",0);
        } else if (doubleFire == 5) {
            bullets = new Bullet[5];
            bullets[0] = new Bullet(x, y, "up",0);//x坐标少减子弹一半宽度
            bullets[1] = new Bullet(x + width / 4, y - 15, "up",0);//x坐标少减子弹一半宽度
            bullets[2] = new Bullet(x + width / 2, y - 30, "up",0);
            bullets[3] = new Bullet(x + width * 3 / 4, y - 15, "up",0);
            bullets[4] = new Bullet(x + width, y, "up",0);
        }} else if (weapon ==2) {
            if (doubleFire == 1) {//发射单发
                bullets = new Bullet_2[1];
                bullets[0] = new Bullet_2(x + width / 2, y - 15,0);//x坐标少减子弹一半宽度
            } else if (doubleFire == 2) {//双发
                bullets = new Bullet_2[2];
                bullets[0] = new Bullet_2(x + width / 4, y - 15,0);//x坐标少减子弹一半宽度
                bullets[1] = new Bullet_2(x + width * 3 / 4, y - 15,0);//x坐标少减子弹一半宽度
            } else if (doubleFire == 3) {
                bullets = new Bullet_2[3];
                bullets[0] = new Bullet_2(x + width / 5, y,0);//x坐标少减子弹一半宽度
                bullets[1] = new Bullet_2(x + width / 2, y - 15,0);//x坐标少减子弹一半宽度
                bullets[2] = new Bullet_2(x + width * 4 / 5, y,0);
            } else if (doubleFire == 4) {
                bullets = new Bullet_2[4];
                bullets[0] = new Bullet_2(x, y,0);//x坐标少减子弹一半宽度
                bullets[1] = new Bullet_2(x + width / 3, y - 15,0);//x坐标少减子弹一半宽度
                bullets[2] = new Bullet_2(x + width * 2 / 3, y - 15,0);
                bullets[3] = new Bullet_2(x + width, y,0);
            } else  {
                bullets = new Bullet_2[5];
                bullets[0] = new Bullet_2(x, y,0);//x坐标少减子弹一半宽度
                bullets[1] = new Bullet_2(x + width / 4, y - 15,0);//x坐标少减子弹一半宽度
                bullets[2] = new Bullet_2(x + width / 2, y - 30,0);
                bullets[3] = new Bullet_2(x + width * 3 / 4, y - 15,0);
                bullets[4] = new Bullet_2(x + width, y,0);
            }
        }else if (weapon==3) {
            if (doubleFire == 1) {//发射单发
                bullets = new Bullet_3[1];
                bullets[0] = new Bullet_3(x + width / 2, y - 15,0);//x坐标少减子弹一半宽度
            } else if (doubleFire == 2) {//双发
                bullets = new Bullet_3[2];
                bullets[0] = new Bullet_3(x + width / 4, y - 15,0);//x坐标少减子弹一半宽度
                bullets[1] = new Bullet_3(x + width * 3 / 4, y - 15,0);//x坐标少减子弹一半宽度
            } else if (doubleFire == 3) {
                bullets = new Bullet_3[3];
                bullets[0] = new Bullet_3(x + width / 5, y,0);//x坐标少减子弹一半宽度
                bullets[1] = new Bullet_3(x + width / 2, y - 15,0);//x坐标少减子弹一半宽度
                bullets[2] = new Bullet_3(x + width * 4 / 5, y,0);
            } else if (doubleFire == 4) {
                bullets = new Bullet_3[4];
                bullets[0] = new Bullet_3(x, y,0);//x坐标少减子弹一半宽度
                bullets[1] = new Bullet_3(x + width / 3, y - 15,0);//x坐标少减子弹一半宽度
                bullets[2] = new Bullet_3(x + width * 2 / 3, y - 15,0);
                bullets[3] = new Bullet_3(x + width, y,0);
            } else {
                bullets = new Bullet_3[5];
                bullets[0] = new Bullet_3(x, y,0);//x坐标少减子弹一半宽度
                bullets[1] = new Bullet_3(x + width / 4, y - 15,0);//x坐标少减子弹一半宽度
                bullets[2] = new Bullet_3(x + width / 2, y - 30,0);
                bullets[3] = new Bullet_3(x + width * 3 / 4, y - 15,0);
                bullets[4] = new Bullet_3(x + width, y,0);
            }
        }else if (weapon==4) {
            if (doubleFire == 1) {//发射单发
                bullets = new Bullet_4[1];
                bullets[0] = new Bullet_4(x + width / 2, y - 15,0);//x坐标少减子弹一半宽度
            } else if (doubleFire == 2) {//双发
                bullets = new Bullet_4[2];
                bullets[0] = new Bullet_4(x + width / 4, y - 15,1);//x坐标少减子弹一半宽度
                bullets[1] = new Bullet_4(x + width * 3 / 4, y - 15,2);//x坐标少减子弹一半宽度
            } else if (doubleFire == 3) {
                bullets = new Bullet_4[3];
                bullets[0] = new Bullet_4(x + width / 5, y,1);//x坐标少减子弹一半宽度
                bullets[1] = new Bullet_4(x + width / 2, y - 15,0);//x坐标少减子弹一半宽度
                bullets[2] = new Bullet_4(x + width * 4 / 5, y,2);
            } else if (doubleFire == 4) {
                bullets = new Bullet_4[4];
                bullets[0] = new Bullet_4(x, y,1);//x坐标少减子弹一半宽度
                bullets[1] = new Bullet_4(x + width / 3, y - 15,1);//x坐标少减子弹一半宽度
                bullets[2] = new Bullet_4(x + width * 2 / 3, y - 15,2);
                bullets[3] = new Bullet_4(x + width, y,2);
            } else {
                bullets = new Bullet_4[5];
                bullets[0] = new Bullet_4(x, y,1);//x坐标少减子弹一半宽度
                bullets[1] = new Bullet_4(x + width / 4, y - 15,1);//x坐标少减子弹一半宽度
                bullets[2] = new Bullet_4(x + width / 2, y - 30,0);
                bullets[3] = new Bullet_4(x + width * 3 / 4, y - 15,2);
                bullets[4] = new Bullet_4(x + width, y,2);
            }
        }
        return bullets;
    }

    @Override
    public boolean isLife() {
        return life > 0;
    }


    @Override
    public void step() {
        if(Game.state==Game.WIN){
            y-=3;
        }
    }

    //英雄机活着时每次返回不同的图片实现英雄机的火焰
    private int index = 0;

    public BufferedImage getImage() {
        if (isLife()) {
            return Images.heroes[index++ % 2];
        }
        return null;
    }

    @Override
    //英雄机不存在越界
    public boolean out() {
        return false;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getDoubleFire() {
        return doubleFire;
    }

    public void setDoubleFire(int doubleFire) {
        this.doubleFire = doubleFire;
    }

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }
}
