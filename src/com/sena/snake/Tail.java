package com.sena.snake;

import javax.swing.*;
import java.awt.*;

public class Tail extends JLabel {
    final int SIZE = SnakeScreen.TAIL_SIZE;
    final ImageIcon TILE_PNG = new ImageIcon(new ImageIcon("src/com/sena/resources/Tail.png")
            .getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH));

    Tail() {
        setIcon(TILE_PNG);
        setSize(SIZE, SIZE);
    }
}
