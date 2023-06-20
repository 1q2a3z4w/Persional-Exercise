package PlaneFight;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public  class Images {
    public static BufferedImage background;
    public static BufferedImage play;
    public static BufferedImage play_2;
    public static BufferedImage[] airplanes;
    public static BufferedImage[] bees;
    public static BufferedImage[] bullets;
    public static BufferedImage[] heroes;
    public static BufferedImage[] BigAirPlanes;
    public static BufferedImage[] Bosses;
    public static BufferedImage[] bee_2s;
    public static BufferedImage[] bee_3s;
    public static BufferedImage blood;
    public static BufferedImage Skill;
    public static BufferedImage bullet_2;
    public static BufferedImage bullet_3;
    public static BufferedImage bullet_4;

    static {
        try {
            //背景图片的加载
            background = ImageIO.read(Game.class.getResource("Picture/background.png"));
            //开始按钮
            play = ImageIO.read(Game.class.getResource("Picture/play.png"));
            play_2 = ImageIO.read(Game.class.getResource("Picture/play_2.png"));
            //子弹图片的加载
            bullets = new BufferedImage[2];
            bullets[1] = ImageIO.read(Game.class.getResource("Picture/bullet1.png"));
            bullets[0] = ImageIO.read(Game.class.getResource("Picture/bullet0.png"));
            bullet_2 = ImageIO.read(Game.class.getResource("Picture/bullet_2.png"));
            bullet_3 = ImageIO.read(Game.class.getResource("Picture/bullet_3.png"));
            bullet_4 = ImageIO.read(Game.class.getResource("Picture/bullet_4.png"));
            //英雄图片的加载
            heroes = new BufferedImage[2];
            heroes[0] = ImageIO.read(Game.class.getResource("Picture/hero1.png"));
            heroes[1] =  ImageIO.read(Game.class.getResource("Picture/hero0.png"));
            //小敌机图片的加载
            airplanes = new BufferedImage[5];
            airplanes[0] = ImageIO.read(Game.class.getResource("Picture/airplane.png"));
            //大敌机图片的加载
            BigAirPlanes = new BufferedImage[5];
            BigAirPlanes[0] = ImageIO.read(Game.class.getResource("Picture/BigAirPlane.png"));
            //奖励机图片的加载
            bees = new BufferedImage[5];
            bees[0] = ImageIO.read(Game.class.getResource("Picture/bee.png"));
            //能量奖励机图片的加载
            bee_2s = new BufferedImage[5];
            bee_2s[0] = ImageIO.read(Game.class.getResource("Picture/bee2.png"));
            //武器奖励机的图片的加载
            bee_3s = new BufferedImage[5];
            bee_3s[0] = ImageIO.read(Game.class.getResource("Picture/bee3.png"));
            //boss图片的加载
            Bosses = new BufferedImage[11];
            Bosses[0] = ImageIO.read(Game.class.getResource("Picture/Boss/boss0.png"));
            Bosses[5] = ImageIO.read(Game.class.getResource("Picture/Boss/boss1.png"));
            Bosses[6] = ImageIO.read(Game.class.getResource("Picture/Boss/boss2.png"));
            Bosses[7] = ImageIO.read(Game.class.getResource("Picture/Boss/boss3.png"));
            Bosses[8] = ImageIO.read(Game.class.getResource("Picture/Boss/boss4.png"));
            Bosses[9] = ImageIO.read(Game.class.getResource("Picture/Boss/boss5.png"));
            Bosses[10] = ImageIO.read(Game.class.getResource("Picture/Boss/boss6.png"));
            //技能图片的加载
            Skill = ImageIO.read(Game.class.getResource("Picture/skill.png"));
            //血量的加载
            blood = ImageIO.read(Game.class.getResource("Picture/blood.png"));

            //爆破图片的加载
            for(int i=1;i<5;i++){
                bees[i] = ImageIO.read(Game.class.getResource("Picture/boom"+i+".png"));
                airplanes[i] = ImageIO.read(Game.class.getResource("Picture/boom"+i+".png"));
                BigAirPlanes[i] = ImageIO.read(Game.class.getResource("Picture/boom"+i+".png"));
                Bosses[i] = ImageIO.read(Game.class.getResource("Picture/boom"+i+".png"));
                bee_2s[i] = ImageIO.read(Game.class.getResource("Picture/boom"+i+".png"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
