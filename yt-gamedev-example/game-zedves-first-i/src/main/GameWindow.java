package main;

import javax.swing.*;

public class GameWindow {
    private JFrame jFrame;
    public GameWindow(GamePanel gamePanel){
        this.jFrame = new JFrame();



        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame.EXIT_ON_CLOSE == 3
        jFrame.add(gamePanel);

        jFrame.pack();
        jFrame.setResizable(false);
        jFrame.setVisible(true);

        jFrame.setLocationRelativeTo(null);
    }
}
