package com.sena.snake;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    JLabel counter = new JLabel();
    JLabel apple = new JLabel();
    JLabel pause = new JLabel();

    int dx = SnakeScreen.GAME_WIDTH;
    public static int dy = SnakeScreen.GAME_HEIGHT / 10;
    final ImageIcon APPLE_PNG = new ImageIcon(new ImageIcon("src/com/sena/resources/Apple.png")
            .getImage().getScaledInstance(dy, dy, Image.SCALE_SMOOTH));

    public Display() {
        setLayout(null);
        setPreferredSize(new Dimension(dx, dy));

        setBackground(Color.lightGray);

        pause.setFont(new Font("Digital-7", Font.ITALIC, 20));
        pause.setBackground(new Color(0x4c92ac));
        pause.setOpaque(true);
        pause.setBounds(dy * 3, 0, dy * 9, dy);
        pause.setHorizontalAlignment(SwingUtilities.CENTER);
        pause.setText("press space to PAUSE");
        add(pause);

        apple.setBounds(dy * 2, 0, dy, dy);
        apple.setOpaque(true);

        apple.setBackground(new Color(0Xffdf33));
        apple.setIcon(APPLE_PNG);
        add(apple);

        counter.setBounds(0, 0, dy * 2, dy);
        counter.setOpaque(true);
        counter.setBackground(new Color(0Xffdf33));
        counter.setFont(new Font("Digital-7", Font.ITALIC, 19));
        counter.setForeground(new Color(0xF44336));
        counter.setHorizontalAlignment(SwingUtilities.CENTER);
        counter.setText("Score: 0");
        add(counter);
    }
}
