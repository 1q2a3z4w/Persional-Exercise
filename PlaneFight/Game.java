package PlaneFight;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JPanel {
    public static URL BGM;//声明播放音乐的地址
    public static URL Bang;//声明播放音乐的地址
    public static URL Defeat;
    public static URL BossBGM;
    public static URL Victory;
    public static URL biu;
    public static URL da;
    public static URL piu;
    public static URL xiu;
    public static AudioClip BGM_play;//声明播放器
    public static AudioClip Bang_play;//声明播放器
    public static AudioClip Defeat_play;
    public static AudioClip BossBGM_play;
    public static AudioClip Victory_play;
    public static AudioClip biu_play;//声明播放器
    public static AudioClip da_play;//声明播放器
    public static AudioClip piu_play;//声明播放器
    public static AudioClip xiu_play;//声明播放器
    public static BufferedImage gameOver;
    public static BufferedImage pause;
    public static BufferedImage start;
    public static BufferedImage win;
    public static URL planeIcon;

    static {
        try {
            BossBGM = Game.class.getResource("Sound/BossBGM.wav");
            Bang = Game.class.getResource("Sound/Bang.wav");
            BGM = Game.class.getResource("Sound/BGM.wav");
            Defeat = Game.class.getResource("Sound/Defeat.wav");
            Victory = Game.class.getResource("Sound/victory.wav");
            biu = Game.class.getResource("Sound/biu.wav");
            da = Game.class.getResource("Sound/da.wav");
            xiu = Game.class.getResource("Sound/xiu.wav");
            piu = Game.class.getResource("Sound/piu.wav");
            BGM_play = Applet.newAudioClip(BGM);//创建一个播放器对象
            Bang_play = Applet.newAudioClip(Bang);
            Defeat_play = Applet.newAudioClip(Defeat);
            BossBGM_play = Applet.newAudioClip(BossBGM);
            Victory_play = Applet.newAudioClip(Victory);
            biu_play = Applet.newAudioClip(biu);
            da_play = Applet.newAudioClip(da);
            xiu_play = Applet.newAudioClip(xiu);
            piu_play = Applet.newAudioClip(piu);
            win = ImageIO.read(Game.class.getResource("Picture/victory.png"));
            gameOver = ImageIO.read(Game.class.getResource("Picture/gameOver.png"));
            pause = ImageIO.read(Game.class.getResource("Picture/pause.png"));
            start = ImageIO.read(Game.class.getResource("Picture/start.png"));
            planeIcon = Game.class.getResource("Picture/planeIcon.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final int WIDTH = 400;
    public static final int HEIGHT = 654;
    //定义游戏状态
    public static final int START = 0;//游戏开始的状态
    public static final int RUNNING = 1;//游戏运行的状态
    public static final int PAUSE = 2;//游戏暂停的状态
    public static final int GAME_OVER = 3;//游戏结束的方法
    public static final int WIN = 4;
    //当前状态
    public static int state = 0;
    //创建背景对象
    BackGround backGround = new BackGround();
    //创建英雄机对象
    Hero hero = new Hero();
    //定义分数
    public static int score = 0;
    //定义能量
    public static int energy = 0;

    //创建一个装英雄子弹的仓库
    private Bullet[] heroBullets = {};
    //创建一个飞行物的仓库
    private FlyingObject[] flyingObjects = {};
    //创建一个装敌机子弹的仓库
    private Bullet[] enemyBullets = {};

    //
    public FlyingObject nextOne() {
        Random r = new Random();
        int n = r.nextInt(100);
        if (n > 80) {
            return new Bee();
        } else if (n > 70) {
            return new Bee_2();
        } else if (n > 60) {
            return new Bee_3();
        } else if (n > 50) {
            return new BigAirPlane();
        } else {
            return new AirPlane();
        }
    }

    //画背景
    public void paintBackGround(Graphics g) {
        g.drawImage(backGround.getImage(), backGround.getX(), backGround.getY(), null);
        g.drawImage(backGround.getImage(), backGround.getX(), backGround.getY2(), null);
    }

    //化状态图
    public void paintState(Graphics g) {
        switch (state) {
            case START:
                g.drawImage(start, 0, 0, null);
                g.drawImage(Images.play,0,0,null);
                break;
            case PAUSE:
                g.drawImage(pause, 0, 0, null);
                break;
            case GAME_OVER:
                g.drawImage(gameOver, 0, 0, null);
                g.drawImage(Images.play_2,0,0,null);
                break;
            case WIN:
                flyingObjects = new FlyingObject[0];//新建飞行物数组
                heroBullets = new Bullet[0];//新建子弹数组
                enemyBullets = new Bullet[0];//新建敌机子弹数组
                //英雄移动
                hero.step();

                if (hero.getY()+hero.getHeight()<=0){
                    g.drawImage(win,0,0,null);
                    g.drawImage(Images.play_2,0,0,null);
                    Font f = new Font(Font.SANS_SERIF, Font.BOLD, 34);
                    g.setFont(f);
                    g.setColor(new Color(0x51748567));
                    g.drawString("得分："+score,110,350);

                }

        }
    }

    //画英雄机
    public void paintHero(Graphics g) {
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
    }//画子弹

    public void paintBullet(Graphics g) {
        for (Bullet b : heroBullets) {
            g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2, b.getY(), null);
        }
    }

    //画飞行物
    public void paintFlyingObject(Graphics g) {
        for (FlyingObject f : flyingObjects) {
                 g.drawImage(f.getImage(), f.getX() - f.width / 2, f.getY(), null);
            for (Bullet heroBullet : heroBullets) {
                if(f instanceof Boss){
                    g.setColor(Color.red);
                    g.fillRect(f.getX()-50,getY()+f.getHeight()+8,f.getLife(),5);
                    if (f.shootBy(heroBullet)){
                    g.drawImage(((Boss) f).getImage(heroBullet),f.getX() - f.width / 2, f.getY(), null);
                }}
            }
            }
        }


    //画敌机子弹
    public void paintEnemyBullet(Graphics g) {
        for (Bullet b : enemyBullets) {
            g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2 - 50, b.getY(), null);
        }
    }

    //画分数
    public void paintScore(Graphics g) {
        int x = 11;
        int y = 24;
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        g.setFont(f);
        g.setColor(new Color(0x4C7335));
        g.drawString("得分" + score, x, y);
        y += 20;
        //画命
        for (int i = 0; i < hero.getLife(); i++) {
            g.drawImage(Images.blood, x + i * 34, y, 20, 20, null);
        }
        g.drawString(" " + energy, x, y + 20);
    }

    //总绘画方法
    public void paint(Graphics g) {

        paintBackGround(g);//画背景
        paintHero(g);//画英雄机
        paintState(g);//画状态图
        paintBullet(g);//画我方子弹
        paintFlyingObject(g);//画飞行物
        paintEnemyBullet(g);//画敌机子弹
        if (state==RUNNING)
        paintScore(g);//画分数

    }

    //射击,然后把子弹装到仓库中
    int ShootIndex = 0;//射击的次数

    public void shootAction() {
        ShootIndex++;
        if (ShootIndex % 20== 0) {
            Bullet[] bs = hero.shoot();
            //                     对谁扩容    扩大到多少
            heroBullets = Arrays.copyOf(heroBullets, heroBullets.length + bs.length);//扩容
            //          //数据源   从下标为多少时  放到哪去                    哪个位置    取几个
            System.arraycopy(bs, 0, heroBullets, heroBullets.length - bs.length, bs.length);//填充数据
            if (hero.getWeapon() == 1) {
                biu_play.loop();
            } else if (hero.getWeapon() == 2) {
                biu_play.stop();
                da_play.loop();
            } else if (hero.getWeapon() == 3) {
                da_play.stop();
                piu_play.loop();
            } else {
                piu_play.stop();
                xiu_play.loop();
            }
        }

    }

    int enterIndex = 0;
    //生成飞行物装仓库
    int boss = 1;

    public void enterAction() {
        enterIndex++;
        if (enterIndex % 40== 0) {
            FlyingObject Obj = nextOne();
            flyingObjects = Arrays.copyOf(flyingObjects, flyingObjects.length + 1);
            flyingObjects[flyingObjects.length - 1] = Obj;
        }
        if (enterIndex % 40 == 0 && score >= 100 && boss == 1) {
            boss--;
            flyingObjects = Arrays.copyOf(flyingObjects, flyingObjects.length + 1);
            flyingObjects[flyingObjects.length - 1] = new Boss();
            BGM_play.stop();
            BossBGM_play.loop();
        }

    }

    //敌机发射子弹
    private int enemyShootIndex = 0;

    public void enemiesShoot() {
        if (enemyShootIndex++ % 60 == 0) {
            for (FlyingObject f : flyingObjects) {
                if (f.isLife() && !(f instanceof Bee)) {
                    Bullet[] b = f.shoot();
                    enemyBullets = Arrays.copyOf(enemyBullets, enemyBullets.length + b.length);
                    System.arraycopy(b, 0, enemyBullets, enemyBullets.length - b.length, b.length);
                }
            }
        }
    }

    public void step() {
        //背景移动
        backGround.step();
        //子弹移动
        for (Bullet heroBullet : heroBullets) {
            heroBullet.step();
        }

        //飞行物移动
        for (FlyingObject flyingObject : flyingObjects) {
            flyingObject.step();
        }
        //敌机子弹移动
        for (Bullet enemyBullet : enemyBullets) {
            enemyBullet.step();
        }
    }

    public void action() {
        //创建监听器
        MouseAdapter ma = new MouseAdapter() {

            public void mouseMoved(MouseEvent a) {
                if (state == RUNNING) {
                    int x = a.getX();
                    int y = a.getY();
                    hero.moveTo(x, y);

                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (state == START) {
                    state = RUNNING;
                    BGM_play.loop();
                } else if (state == GAME_OVER) {
                    score = 0; //重启
                    energy = 0;
                    hero = new Hero();//新建英雄对象
                    flyingObjects = new FlyingObject[0];//新建飞行物数组
                    heroBullets = new Bullet[0];//新建子弹数组
                    enemyBullets = new Bullet[0];//新建敌机子弹数组
                    boss = 1;
                    state = START; //当前状态变为启动状态
                    Defeat_play.stop();
                } else if (state == WIN) {
                    energy = 0;
                    score = 0; //重启
                    hero = new Hero();//新建英雄对象
                    flyingObjects = new FlyingObject[0];//新建飞行物数组
                    heroBullets = new Bullet[0];//新建子弹数组
                    enemyBullets = new Bullet[0];//新建敌机子弹数组
                    boss = 1;
                    state = START; //当前状态变为启动状态

                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (state == RUNNING) {
                    state = PAUSE;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (state == PAUSE) {
                    state = RUNNING;

                }
            }
        };

        //添加监听器
        this.addMouseMotionListener(ma);
        this.addMouseListener(ma);

        //创建计时器对象
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state == RUNNING) {
                    shootAction();//调用射击方法
                    enterAction();//添加敌机方法
                    step();//运动方法
                    enemiesShoot();//敌机射击方法
                    outAction();//出界处理方法
                    hitAction();//英雄机被撞后的处理方法
                    bangAction();//射击碰撞方法
                    isGameOverAction();//检测游戏结束
                    Skill();//放技能

                }
                repaint();//重绘
            }
        }, 10, 10);//第一次延时10毫秒,每隔10毫要运行
    }

    //判断飞行物之间的碰撞
    public void bangAction() {
        //遍历飞行物数组
        for (FlyingObject f : flyingObjects) {
            //遍历英雄机子弹数组
            for (Bullet b : heroBullets) {
                if (b.isLife() && f.isLife() && b.shootBy(f)) {//当子弹和飞行物都活着时，判断碰撞是否发生
                    if (f.life > 1) {
                        f.decreaseLife();//当飞行物的血量大于1时，减血
                        b.goDie();//子弹的状态变为死亡
                    } else {
                        f.goDie();//当飞行物的血量等于0时，状态变为死亡
                        b.goDie();//子弹的状态变为死亡
                        //检测被击中的飞行物是敌机还是奖励
                        if (f instanceof Boss) {
                            BossBGM_play.stop();
                            BGM_play.stop();
                            biu_play.stop();
                            da_play.stop();
                            piu_play.stop();
                            xiu_play.stop();
                            Victory_play.play();
                            state = WIN;
                        }
                        if (f instanceof Enemy) {//如果是敌机加分
                            Enemy e = (Enemy) f;
                            score += e.getScore();
                        }
                        if (f instanceof Award) {//如果是奖励物，给与奖励
                            Award a = (Award) f;
                            int type = a.getType();
                            switch (type) {
                                case Award.DOUBLE_FIRE:
                                    if (hero.getDoubleFire() < 5)
                                        hero.setDoubleFire(hero.getDoubleFire() + 1);
                                    break;
                                case Award.LIFE:
                                    hero.setLife(hero.getLife() + 1);
                                    break;
                                case Award.ENERGY:
                                    energy++;
                                    break;
                                case Award.WEAPON:
                                    if (hero.getWeapon() <= 3) {
                                        hero.setWeapon(hero.getWeapon() + 1);
                                    }
                            }
                        }
                    }
                    Bang_play.play();
                }
            }
        }
    }

    public void hitAction() {//英雄机被击中的处理
        //遍历敌机数组
        for (FlyingObject f : flyingObjects) {
            if (f.isLife() && hero.isLife() && hero.shootBy(f)) {//如果敌机存活，英雄机存活，且英雄机被击中
                f.goDie();//敌机死亡
                if (hero.getDoubleFire() > 1) {
                    hero.setDoubleFire(hero.getDoubleFire() - 1);//英雄机火力清除
                }
                hero.decreaseLife();//英雄机减血
            }
        }

        //遍历敌机子弹数组
        for (Bullet b : enemyBullets) {
            if (b.shootBy(hero) && b.isLife()) {//如果敌机子弹存活，英雄机存活，且英雄机被击中
                b.goDie();//子弹死亡
                hero.decreaseLife();//英雄机减血
                if (hero.getDoubleFire() > 1) {
                    hero.setDoubleFire(hero.getDoubleFire() - 1);//英雄机火力-1
                }
            }
        }
    }

    public void outAction() {//判断出界后的处理
        int index = 0;//定义索引
        FlyingObject[] fs = new FlyingObject[flyingObjects.length];//新建一个飞行物数组
        for (FlyingObject f : flyingObjects) {//遍历旧的数组
            if (!f.isRemove() && !f.out()) {//如果状态不是要被移除或者出界，
                fs[index++] = f;//下标重新赋值
            }
        }
        flyingObjects = Arrays.copyOf(fs, index);//重新整理数组

        index = 0;//索引归零
        Bullet[] bs = new Bullet[heroBullets.length];//新建一个子弹数组
        for (Bullet b : heroBullets) {//遍历旧的数组
            if (!b.isRemove() && !b.out()) {//如果状态不是要被移除或者出界，
                bs[index++] = b;//下标重新赋值
            }
        }
        heroBullets = Arrays.copyOf(bs, index);//重新整理数组

        index = 0;//索引归零
        Bullet[] enemyBullets = new Bullet[this.enemyBullets.length];//新建一个敌机子弹数组
        for (Bullet b : this.enemyBullets) {//遍历旧的数组
            if (!b.isRemove() && !b.out()) {//如果状态不是要被移除或者出界，
                enemyBullets[index++] = b;////下标重新赋值
            }
        }
        this.enemyBullets = Arrays.copyOf(enemyBullets, index);//重新整理数组
    }

    public void Skill() {
        if (Hero.space && energy >= 10) {
            for (FlyingObject flyingObject : flyingObjects) {
                flyingObject.decreaseLife();
                flyingObject.decreaseLife();
                flyingObject.decreaseLife();
                flyingObject.decreaseLife();
                flyingObject.decreaseLife();
            }
            energy -= 10;
            }
        }




    public void isGameOverAction() {

        if (hero.getLife() <= 0) {
            state = GAME_OVER;
            BossBGM_play.stop();
            BGM_play.stop();
            Defeat_play.play();
            biu_play.stop();
            da_play.stop();
            piu_play.stop();
            xiu_play.stop();
        }
    }


    public static void main(String[] args) {
        JFrame game = new JFrame("飞机大战");//新建窗口对象，命名
        Game panel = new Game();//新建面板对象
        Player player = new Player(panel);//创建玩家对象
        game.addKeyListener(player);//添加键盘监听器

        game.setSize(WIDTH, HEIGHT);//确定窗口大小
        game.setAlwaysOnTop(true);//设置窗口在前

        game.add(panel);//添加面板到窗口
        game.setLocationRelativeTo(null);//设置窗口居中
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭服务
        game.setVisible(true);//设置窗体可见
        Image img = Toolkit.getDefaultToolkit().getImage(planeIcon);
        game.setIconImage(img);//设置图标
        panel.action();

    }
}
