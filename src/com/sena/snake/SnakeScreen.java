package com.sena.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SnakeScreen extends JPanel implements ActionListener {
    static final int GAME_WIDTH = 580;
    static final int GAME_HEIGHT = 390;
    static final int TAIL_SIZE = 40;
    final int GAME_SPEED = 5;
    final int SNAKE_INITIAL_SIZE = 2;

    final ImageIcon HEAD_RIGHT_PNG = new ImageIcon("src/com/sena/resources/HeadRight.png");
    final ImageIcon HEAD_LEFT_PNG = new ImageIcon("src/com/sena/resources/HeadLeft.png");
    final ImageIcon HEAD_DOWN_PNG = new ImageIcon("src/com/sena/resources/HeadDown.png");
    final ImageIcon HEAD_UP_PNG = new ImageIcon("src/com/sena/resources/HeadUp.png");


    Apple apple;
    String direction = "RIGHT";
    int applesEaten = 0;

    Point savedLocation1 = new Point();
    Point savedLocation2 = new Point();

    public Timer gameClock = new Timer(1000 / GAME_SPEED, this);
    ArrayList<Tail> snake = new ArrayList<>();


    public SnakeScreen() {
        Action upAction = new UpAction();
        Action downAction = new DownAction();
        Action leftAction = new LeftAction();
        Action rightAction = new RightAction();
        Action pause = new PauseAction();

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "upAction");
        getActionMap().put("upAction", upAction);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        getActionMap().put("downAction", downAction);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        getActionMap().put("leftAction", leftAction);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        getActionMap().put("rightAction", rightAction);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "pause");
        getActionMap().put("pause", pause);

        setLayout(null);
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));


        setBackground(new Color(0x123456));

        addSnake();
        apple = new Apple();
        add(apple);

        gameClock.start();
    }

    void addSnake() {
        for (int i = 0; i < SNAKE_INITIAL_SIZE; i++) {
            snake.add(new Tail());
            add(snake.get(i));
        }
    }

    void moveHead() {
        savedLocation1 = snake.get(0).getLocation();
        snake.get(0).setIcon(HEAD_RIGHT_PNG);
        if (direction.equals("RIGHT")) {
            snake.get(0).setLocation(snake.get(0).getX() + TAIL_SIZE, snake.get(0).getY());
            snake.get(0).setIcon(HEAD_RIGHT_PNG);
        }
        if (direction.equals("LEFT")) {
            snake.get(0).setLocation(snake.get(0).getX() - TAIL_SIZE, snake.get(0).getY());
            snake.get(0).setIcon(HEAD_LEFT_PNG);
        }
        if (direction.equals("UP")) {
            snake.get(0).setLocation(snake.get(0).getX(), snake.get(0).getY() - TAIL_SIZE);
            snake.get(0).setIcon(HEAD_UP_PNG);
        }
        if (direction.equals("DOWN")) {
            snake.get(0).setLocation(snake.get(0).getX(), snake.get(0).getY() + TAIL_SIZE);
            snake.get(0).setIcon(HEAD_DOWN_PNG);
        }

    }

    void moveRest() {
        for (int i = 1; i < snake.size(); i++) {
            savedLocation2 = snake.get(i).getLocation();
            snake.get(i).setLocation(savedLocation1);
            savedLocation1 = savedLocation2;
        }
    }

    void newApple() {
        remove(apple);
        apple = new Apple();
        add(apple);
    }

    void checkAppleCollision() {
        if (snake.get(0).getLocation().equals(apple.getLocation())) {
            snake.add(new Tail());
            snake.get(snake.size() - 1).setLocation(snake.get(snake.size() - 2).getLocation());
            add(snake.get(snake.size() - 1));

            newApple();
            applesEaten++;
            SoundLoader.play("src/com/sena/resources/Bite.wav");

            for (int i = 1; i < snake.size(); i++) {
                if (snake.get(i).getLocation().equals(apple.getLocation())) {
                    newApple();
                }
            }
            String apple = Integer.toString(applesEaten);
            GameRunner.display.counter.setText("Score:" + apple);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }

    void checkFrameCollision() {
        if ((snake.get(0).getX() < 0) || (snake.get(0).getX() > GAME_WIDTH)
                || (snake.get(0).getY() < 0) || (snake.get(0).getY() > GAME_HEIGHT)) {
            gameClock.stop();
            SoundLoader.play("src/com/sena/resources/GameOver.wav");
            GameRunner.display.pause.setText("GAME OVER!");
        }
    }

    void checkSelfCollision() {
        for (int i = 1; i < snake.size(); i++) {
            if ((snake.size() > SNAKE_INITIAL_SIZE) && (snake.get(i).getLocation().equals(snake.get(0).getLocation()))) {
                gameClock.stop();
                SoundLoader.play("src/com/sena/resources/GameOver.wav");
                GameRunner.display.pause.setText("GAME OVER!");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveHead();
        moveRest();
        checkAppleCollision();
        checkFrameCollision();
        checkSelfCollision();
    }

    private class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!direction.equals("DOWN"))
                direction = "UP";
        }
    }

    private class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!direction.equals("UP"))
                direction = "DOWN";
        }
    }

    private class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!direction.equals("LEFT"))
                direction = "RIGHT";
        }
    }

    private class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!direction.equals("RIGHT"))
                direction = "LEFT";
        }
    }

    private class PauseAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameClock.isRunning()) {
                gameClock.stop();
                GameRunner.display.pause.setText("press space to START");
            } else {
                gameClock.start();
                GameRunner.display.pause.setText("press space to PAUSE");
            }
        }
    }


}
