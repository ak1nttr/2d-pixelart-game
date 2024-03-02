package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.*;


public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage bufferedImage;
    private BufferedImage[][] animations;
    private int aniTick , aniIndex , aniSpeed = 20;
    private int playerAction = ATTACK_JUMP_2;
    private int playerDirection = -1;
    private boolean moving = false;


    public GamePanel(){

            importImage();
            loadAnimations();
            mouseInputs = new MouseInputs(this);
            setPanelSize();
            addKeyListener(new KeyboardInputs(this));
            addMouseListener(mouseInputs);
            addMouseMotionListener(mouseInputs);
    }

    private void updatePosition(){
            if (moving){
                switch (playerDirection){
                    case LEFT -> {this.xDelta += -5;}
                    case RIGHT -> {this.xDelta += 5;}
                    case UP -> {this.yDelta += -5;}
                    case DOWN -> {this.yDelta += 5;}
                }
            }
    }
    private void setAnimation(){
        if (moving)
            playerAction = RUNNING;
        else playerAction = IDLE;
    }


    public void updateGame(){
        setAnimation();
        updatePosition();
        updateAnimationTick();
    }

    public void setDirection(int direction){
        this.playerDirection = direction;
        setMoving(true);
    }

    public void setMoving(boolean b) {
        this.moving = b;
    }

    private void updateAnimationTick(){
            aniTick++;
            if (aniTick >= aniSpeed){
                aniTick = 0;
                aniIndex++;
                    if (aniIndex >=getSpriteAmount(playerAction)){
                        aniIndex= 0;
                    }
            }
    }
    private void loadAnimations(){
        animations = new BufferedImage[9][6];
        for (int i = 0;i< animations.length;i++){
            for (int j = 0;j<animations[i].length;j++){
            animations[i][j] = bufferedImage.getSubimage(j*64,i*40,64,40);

            }
        }

    }
    private void importImage(){
        InputStream inputStream = getClass().getResourceAsStream("/player_sprites.png");
        try {
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.getMessage();
        }


    }
    private void setPanelSize(){
            Dimension size = new Dimension(1280,720);
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);


        g.drawImage(animations[playerAction][aniIndex],
                (int)xDelta,
                (int)yDelta,
                64*3,
                40*3,
                null);
    }






}
