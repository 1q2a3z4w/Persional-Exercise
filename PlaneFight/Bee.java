package PlaneFight;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Bee extends FlyingObject implements Award {
    Random s = new Random();
    private int XSpeed = 2;
    private int YSpeed = s.nextInt(4) + 1;
    private int awardType;//奖励类型

    Bee() {
        super(60, 51, 1);
    }

    //返回奖励的类型
    @Override
    public int getType() {
        Random r = new Random();
        return r.nextInt(2);
    }

    /*
     * 获取奖励的图片，状态为LIFE时返回奖励机图片
     * 状态为DEAD时返回4张爆破图片，全部返回后将状态改为REMOVE，返回null
     */
    int index = 1;

    public BufferedImage getImage() {
        if (isLife()) {
            return Images.bees[0];
        } else if (isDead()) {
            if (index == 5) {
                state = REMOVE;
                return null;
            }
            return Images.bees[index++];
        }
        return null;
    }

    //当奖励机碰到边界时改变x方向的移动
    @Override
    public void step() {
        y += YSpeed;
        x += XSpeed; //x加(向左或向右)
        if (x >= Game.WIDTH - this.width || x <= 0) { //x>=(屏幕宽-蜜蜂宽)时，x减(向左)x<=0时，x加(向右)
            XSpeed = -1 * XSpeed;
        }
    }

    //检测碰撞，奖励机y坐标大于窗口的高返回true
    @Override
    public boolean out() {
        return this.y >= Game.HEIGHT;
    }
}

class Bee_2 extends Bee {
    Random s = new Random();
    private int XSpeed = 2;
    private int YSpeed = s.nextInt(4) + 1;
    private int awardType;//奖励类型

    @Override
    public int getType() {
        return 3;
    }

    int index = 1;

    public BufferedImage getImage() {
        if (isLife()) {
            return Images.bee_2s[0];
        } else if (isDead()) {
            if (index == 5) {
                state = REMOVE;
                return null;
            }
            return Images.bee_2s[index++];
        }
        return null;
    }

    @Override
    public void step() {
        y += YSpeed;
        x += XSpeed; //x加(向左或向右)
        if (x >= Game.WIDTH - this.width || x <= 0) { //x>=(屏幕宽-蜜蜂宽)时，x减(向左)x<=0时，x加(向右)
            XSpeed = -1 * XSpeed;
        }
    }

    @Override
    public boolean out() {
        return this.y >= Game.HEIGHT;
    }
}

class Bee_3 extends Bee {
    Bee_3(){
        life=3;
    }
    Random s = new Random();
    private int XSpeed = 2;
    private int YSpeed = s.nextInt(4) + 1;
    private int awardType;//奖励类型

    @Override
    public int getType() {
        return 4;
    }

    int index = 1;

    public BufferedImage getImage() {
        if (isLife()) {
            return Images.bee_3s[0];
        } else if (isDead()) {
            if (index == 5) {
                state = REMOVE;
                return null;
            }
            return Images.bee_3s[index++];
        }
        return null;
    }

    @Override
    public void step() {
        y += YSpeed;
        x += XSpeed; //x加(向左或向右)
        if (x >= Game.WIDTH - this.width || x <= 0) { //x>=(屏幕宽-蜜蜂宽)时，x减(向左)x<=0时，x加(向右)
            XSpeed = -1 * XSpeed;
        }
    }

    @Override
    public boolean out() {
        return this.y >= Game.HEIGHT;
    }
}
