package com.sena.snake;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Apple extends JLabel {
    Random random = new Random();
    final int APPLE_SIZE = SnakeScreen.TAIL_SIZE;
    final ImageIcon APPLE_PNG = new ImageIcon(new ImageIcon("src/com/sena/resources/Apple.png")
            .getImage().getScaledInstance(APPLE_SIZE, APPLE_SIZE, Image.SCALE_SMOOTH));

    int appleX = random.nextInt(SnakeScreen.GAME_WIDTH / APPLE_SIZE) * APPLE_SIZE;
    int appleY = random.nextInt(SnakeScreen.GAME_HEIGHT / APPLE_SIZE) * APPLE_SIZE;

    Apple() {
        setBounds(appleX, appleY, APPLE_SIZE, APPLE_SIZE);
        setIcon(APPLE_PNG);
    }
}

