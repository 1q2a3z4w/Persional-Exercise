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
    public static URL BGM;//�����������ֵĵ�ַ
    public static URL Bang;//�����������ֵĵ�ַ
    public static URL Defeat;
    public static URL BossBGM;
    public static URL Victory;
    public static URL biu;
    public static URL da;
    public static URL piu;
    public static URL xiu;
    public static AudioClip BGM_play;//����������
    public static AudioClip Bang_play;//����������
    public static AudioClip Defeat_play;
    public static AudioClip BossBGM_play;
    public static AudioClip Victory_play;
    public static AudioClip biu_play;//����������
    public static AudioClip da_play;//����������
    public static AudioClip piu_play;//����������
    public static AudioClip xiu_play;//����������
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
            BGM_play = Applet.newAudioClip(BGM);//����һ������������
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
    //������Ϸ״̬
    public static final int START = 0;//��Ϸ��ʼ��״̬
    public static final int RUNNING = 1;//��Ϸ���е�״̬
    public static final int PAUSE = 2;//��Ϸ��ͣ��״̬
    public static final int GAME_OVER = 3;//��Ϸ�����ķ���
    public static final int WIN = 4;
    //��ǰ״̬
    public static int state = 0;
    //������������
    BackGround backGround = new BackGround();
    //����Ӣ�ۻ�����
    Hero hero = new Hero();
    //�������
    public static int score = 0;
    //��������
    public static int energy = 0;

    //����һ��װӢ���ӵ��Ĳֿ�
    private Bullet[] heroBullets = {};
    //����һ��������Ĳֿ�
    private FlyingObject[] flyingObjects = {};
    //����һ��װ�л��ӵ��Ĳֿ�
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

    //������
    public void paintBackGround(Graphics g) {
        g.drawImage(backGround.getImage(), backGround.getX(), backGround.getY(), null);
        g.drawImage(backGround.getImage(), backGround.getX(), backGround.getY2(), null);
    }

    //��״̬ͼ
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
                flyingObjects = new FlyingObject[0];//�½�����������
                heroBullets = new Bullet[0];//�½��ӵ�����
                enemyBullets = new Bullet[0];//�½��л��ӵ�����
                //Ӣ���ƶ�
                hero.step();

                if (hero.getY()+hero.getHeight()<=0){
                    g.drawImage(win,0,0,null);
                    g.drawImage(Images.play_2,0,0,null);
                    Font f = new Font(Font.SANS_SERIF, Font.BOLD, 34);
                    g.setFont(f);
                    g.setColor(new Color(0x51748567));
                    g.drawString("�÷֣�"+score,110,350);

                }

        }
    }

    //��Ӣ�ۻ�
    public void paintHero(Graphics g) {
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
    }//���ӵ�

    public void paintBullet(Graphics g) {
        for (Bullet b : heroBullets) {
            g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2, b.getY(), null);
        }
    }

    //��������
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


    //���л��ӵ�
    public void paintEnemyBullet(Graphics g) {
        for (Bullet b : enemyBullets) {
            g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2 - 50, b.getY(), null);
        }
    }

    //������
    public void paintScore(Graphics g) {
        int x = 11;
        int y = 24;
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        g.setFont(f);
        g.setColor(new Color(0x4C7335));
        g.drawString("�÷�" + score, x, y);
        y += 20;
        //����
        for (int i = 0; i < hero.getLife(); i++) {
            g.drawImage(Images.blood, x + i * 34, y, 20, 20, null);
        }
        g.drawString(" " + energy, x, y + 20);
    }

    //�ܻ滭����
    public void paint(Graphics g) {

        paintBackGround(g);//������
        paintHero(g);//��Ӣ�ۻ�
        paintState(g);//��״̬ͼ
        paintBullet(g);//���ҷ��ӵ�
        paintFlyingObject(g);//��������
        paintEnemyBullet(g);//���л��ӵ�
        if (state==RUNNING)
        paintScore(g);//������

    }

    //���,Ȼ����ӵ�װ���ֿ���
    int ShootIndex = 0;//����Ĵ���

    public void shootAction() {
        ShootIndex++;
        if (ShootIndex % 20== 0) {
            Bullet[] bs = hero.shoot();
            //                     ��˭����    ���󵽶���
            heroBullets = Arrays.copyOf(heroBullets, heroBullets.length + bs.length);//����
            //          //����Դ   ���±�Ϊ����ʱ  �ŵ���ȥ                    �ĸ�λ��    ȡ����
            System.arraycopy(bs, 0, heroBullets, heroBullets.length - bs.length, bs.length);//�������
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
    //���ɷ�����װ�ֿ�
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

    //�л������ӵ�
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
        //�����ƶ�
        backGround.step();
        //�ӵ��ƶ�
        for (Bullet heroBullet : heroBullets) {
            heroBullet.step();
        }

        //�������ƶ�
        for (FlyingObject flyingObject : flyingObjects) {
            flyingObject.step();
        }
        //�л��ӵ��ƶ�
        for (Bullet enemyBullet : enemyBullets) {
            enemyBullet.step();
        }
    }

    public void action() {
        //����������
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
                    score = 0; //����
                    energy = 0;
                    hero = new Hero();//�½�Ӣ�۶���
                    flyingObjects = new FlyingObject[0];//�½�����������
                    heroBullets = new Bullet[0];//�½��ӵ�����
                    enemyBullets = new Bullet[0];//�½��л��ӵ�����
                    boss = 1;
                    state = START; //��ǰ״̬��Ϊ����״̬
                    Defeat_play.stop();
                } else if (state == WIN) {
                    energy = 0;
                    score = 0; //����
                    hero = new Hero();//�½�Ӣ�۶���
                    flyingObjects = new FlyingObject[0];//�½�����������
                    heroBullets = new Bullet[0];//�½��ӵ�����
                    enemyBullets = new Bullet[0];//�½��л��ӵ�����
                    boss = 1;
                    state = START; //��ǰ״̬��Ϊ����״̬

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

        //��Ӽ�����
        this.addMouseMotionListener(ma);
        this.addMouseListener(ma);

        //������ʱ������
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state == RUNNING) {
                    shootAction();//�����������
                    enterAction();//��ӵл�����
                    step();//�˶�����
                    enemiesShoot();//�л��������
                    outAction();//���紦����
                    hitAction();//Ӣ�ۻ���ײ��Ĵ�����
                    bangAction();//�����ײ����
                    isGameOverAction();//�����Ϸ����
                    Skill();//�ż���

                }
                repaint();//�ػ�
            }
        }, 10, 10);//��һ����ʱ10����,ÿ��10��Ҫ����
    }

    //�жϷ�����֮�����ײ
    public void bangAction() {
        //��������������
        for (FlyingObject f : flyingObjects) {
            //����Ӣ�ۻ��ӵ�����
            for (Bullet b : heroBullets) {
                if (b.isLife() && f.isLife() && b.shootBy(f)) {//���ӵ��ͷ����ﶼ����ʱ���ж���ײ�Ƿ���
                    if (f.life > 1) {
                        f.decreaseLife();//���������Ѫ������1ʱ����Ѫ
                        b.goDie();//�ӵ���״̬��Ϊ����
                    } else {
                        f.goDie();//���������Ѫ������0ʱ��״̬��Ϊ����
                        b.goDie();//�ӵ���״̬��Ϊ����
                        //��ⱻ���еķ������ǵл����ǽ���
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
                        if (f instanceof Enemy) {//����ǵл��ӷ�
                            Enemy e = (Enemy) f;
                            score += e.getScore();
                        }
                        if (f instanceof Award) {//����ǽ�������뽱��
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

    public void hitAction() {//Ӣ�ۻ������еĴ���
        //�����л�����
        for (FlyingObject f : flyingObjects) {
            if (f.isLife() && hero.isLife() && hero.shootBy(f)) {//����л���Ӣ�ۻ�����Ӣ�ۻ�������
                f.goDie();//�л�����
                if (hero.getDoubleFire() > 1) {
                    hero.setDoubleFire(hero.getDoubleFire() - 1);//Ӣ�ۻ��������
                }
                hero.decreaseLife();//Ӣ�ۻ���Ѫ
            }
        }

        //�����л��ӵ�����
        for (Bullet b : enemyBullets) {
            if (b.shootBy(hero) && b.isLife()) {//����л��ӵ���Ӣ�ۻ�����Ӣ�ۻ�������
                b.goDie();//�ӵ�����
                hero.decreaseLife();//Ӣ�ۻ���Ѫ
                if (hero.getDoubleFire() > 1) {
                    hero.setDoubleFire(hero.getDoubleFire() - 1);//Ӣ�ۻ�����-1
                }
            }
        }
    }

    public void outAction() {//�жϳ����Ĵ���
        int index = 0;//��������
        FlyingObject[] fs = new FlyingObject[flyingObjects.length];//�½�һ������������
        for (FlyingObject f : flyingObjects) {//�����ɵ�����
            if (!f.isRemove() && !f.out()) {//���״̬����Ҫ���Ƴ����߳��磬
                fs[index++] = f;//�±����¸�ֵ
            }
        }
        flyingObjects = Arrays.copyOf(fs, index);//������������

        index = 0;//��������
        Bullet[] bs = new Bullet[heroBullets.length];//�½�һ���ӵ�����
        for (Bullet b : heroBullets) {//�����ɵ�����
            if (!b.isRemove() && !b.out()) {//���״̬����Ҫ���Ƴ����߳��磬
                bs[index++] = b;//�±����¸�ֵ
            }
        }
        heroBullets = Arrays.copyOf(bs, index);//������������

        index = 0;//��������
        Bullet[] enemyBullets = new Bullet[this.enemyBullets.length];//�½�һ���л��ӵ�����
        for (Bullet b : this.enemyBullets) {//�����ɵ�����
            if (!b.isRemove() && !b.out()) {//���״̬����Ҫ���Ƴ����߳��磬
                enemyBullets[index++] = b;////�±����¸�ֵ
            }
        }
        this.enemyBullets = Arrays.copyOf(enemyBullets, index);//������������
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
        JFrame game = new JFrame("�ɻ���ս");//�½����ڶ�������
        Game panel = new Game();//�½�������
        Player player = new Player(panel);//������Ҷ���
        game.addKeyListener(player);//��Ӽ��̼�����

        game.setSize(WIDTH, HEIGHT);//ȷ�����ڴ�С
        game.setAlwaysOnTop(true);//���ô�����ǰ

        game.add(panel);//�����嵽����
        game.setLocationRelativeTo(null);//���ô��ھ���
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùرշ���
        game.setVisible(true);//���ô���ɼ�
        Image img = Toolkit.getDefaultToolkit().getImage(planeIcon);
        game.setIconImage(img);//����ͼ��
        panel.action();

    }
}
