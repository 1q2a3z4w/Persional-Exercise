package PlaneFight;

import java.awt.image.BufferedImage;

public class Boss extends FlyingObject implements Enemy{
    int XSpeed;
    int YSpeed;
    Boss() {
        super(150, 116, 100);
        XSpeed=2;
        YSpeed=2;
    }



    public int getScore() {
        return 100;
    }

    int index = 1;
    public BufferedImage getImage(Bullet bullet) {
            int i;
            if (isLife()){
            for ( i = 6; i <10 ; i++) {
                if (shootBy(bullet)){
                return Images.Bosses[i+1];
            }
        }
            }
        if(isDead()){
            if(index==5){
                state = REMOVE;
                return null;
            }
            return Images.Bosses[index++];
        }  return null;
    }
    @Override
    public BufferedImage getImage() {
        if(isLife()){
            return Images.Bosses[0];
        }else  if(isDead()){
            if(index==5){
                state = REMOVE;
                return null;
            }
            return Images.Bosses[index++];
        }
        return null;
    }

    @Override
    public Bullet[] shoot() {
        Bullet[] bullets = new Bullet[2];
        bullets[0] = new Bullet(x+this.width/3,y+this.height+10,"down",0);
        bullets[1] = new Bullet(x+2*this.width/3,y+this.height+10,"down",0);
        return bullets;
    }




    @Override
    public void step() {
        if (y<=30){
            y+=YSpeed;
        }
        x+=XSpeed;
        if (x<=0||x>=Game.WIDTH-width){
            XSpeed=-XSpeed;
        }

    }

    @Override
    public boolean out() {
        return y>=Game.HEIGHT;
    }

    public int getXSpeed() {
        return XSpeed;
    }

    public void setXSpeed(int XSpeed) {
        this.XSpeed = XSpeed;
    }

    public int getYSpeed() {
        return YSpeed;
    }

    public void setYSpeed(int YSpeed) {
        this.YSpeed = YSpeed;
    }
    public boolean isDead(){
        return life<=0;
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
