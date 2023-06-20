package PlaneFight;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Player extends KeyAdapter {
    Game game;
    public Player(Game game){
        this.game=game;
    }
    public  void keyPressed(KeyEvent e) {//按下空格为true
        int keyCode = e.getKeyCode();
        //空格的keyCode值为32
        if (keyCode==32){
            Hero.space=true;
        }
    }
    public void keyReleased(KeyEvent e) {//松开空格为false
        int keyCode = e.getKeyCode();
        if  (keyCode == 32){
            Hero.space=false;
        }
    }
}
