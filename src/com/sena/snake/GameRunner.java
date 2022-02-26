package com.sena.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameRunner extends JFrame implements ActionListener {
    public static Display display = new Display();
    private SnakeScreen snakeScreen;
    private final JButton restartButton = new JButton("restart");

    public static void main(String[] args) {
        new GameRunner();
    }

    GameRunner() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon("src/com/sena/resources/SnakeIcon.png").getImage());
        setTitle("Snake");

        restartButton.setBounds(Display.dy * 12, 0, Display.dy * 3, Display.dy);
        restartButton.setBackground(new Color(0x3d668a));
        restartButton.setFont(new Font("Digital-7", Font.ITALIC, 20));
        restartButton.setFocusable(false);
        restartButton.setBorder(BorderFactory.createEmptyBorder());
        restartButton.addActionListener(this);
        display.add(restartButton);

        add(snakeScreen = new SnakeScreen(), BorderLayout.CENTER);
        add(display, BorderLayout.NORTH);
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            remove(snakeScreen);
            snakeScreen = new SnakeScreen();
            add(snakeScreen, BorderLayout.CENTER);
            display.pause.setText("press space to PAUSE");
            SwingUtilities.updateComponentTreeUI(this);
        }
    }

}
