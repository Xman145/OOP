package io.github.xman145.task231;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeApp extends Application {

    static final int WIDTH = 800;
    static final int HEIGHT = WIDTH;
    static final int ROWS = 32;
    static final int COLUMNS = ROWS;
    static final int SQUARE_SIZE = WIDTH / ROWS;


    static int pointsize = 25;
    static List<Point> snake = new ArrayList<>();

    static int speed = 5;
    static int foodX = 8;
    static int foodY = 8;

    static  int goal = 10;

    static int foodcolor = 0;

    static Random rand = new Random();

    static boolean gameOver = false;

    static boolean gameWon = false;

    static Dir direction = Dir.left;

    public enum Dir {
        right,left,up,down
    }



    @Override

    public void start(Stage primaryStage) throws IOException {
        VBox root = new VBox();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    tick(gc);
                    return;
                }

                if (now - lastTick > 1000000000 / speed) {
                    lastTick = now;
                    tick(gc);
                }
            }

        }.start();
        Scene scene = new Scene(root,WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake");
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);



        //controls
        scene.addEventFilter(KeyEvent.KEY_PRESSED,key->{
            if (key.getCode() == KeyCode.W) {
                direction = Dir.up;
            }
            if (key.getCode() == KeyCode.A) {
                direction = Dir.left;
            }
            if (key.getCode() == KeyCode.S) {
                direction = Dir.down;
            }
            if (key.getCode() == KeyCode.D) {
                direction = Dir.right;
            }
        });
        // add start snake parts
        snake.add(new Point(16, 16));


        primaryStage.show();
    }
    // food
    public static void spawnFood() {
        start: while (true) {
            foodX = rand.nextInt(32);
            foodY = rand.nextInt(32);

            for (Point c : snake) {
                if (c.x == foodX && c.y == foodY) {
                    continue start;
                }
            }
            foodcolor = rand.nextInt(5);
            speed++;
            break;

        }
    }

    public static void tick(GraphicsContext gc){
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("",50));
            gc.fillText("GAME OVER",300,300);
            return;
        }
        if (gameWon) {
            gc.setFill(Color.GREEN);
            gc.setFont(new Font("",50));
            gc.fillText("YOU WON!",300,300);
            return;
        }

        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        switch (direction) {
            case up:
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    gameOver = true;
                }
                break;
            case down:
                snake.get(0).y++;
                if (snake.get(0).y > ROWS) {
                    gameOver = true;
                }
                break;
            case left:
                snake.get(0).x--;
                if (snake.get(0).x < 0) {
                    gameOver = true;
                }
                break;
            case right:
                snake.get(0).x++;
                if (snake.get(0).x > COLUMNS) {
                    gameOver = true;
                }
                break;
        }

        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Point(-1, -1));
            spawnFood();
        }

        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
            }
        }
        if ((speed-5) >= goal){
            gameWon = true;
        }
        // fill
        // background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        // score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (speed - 5) + " The Goal is "+ (goal), 10, 30);

        // random foodcolor
        Color cc = Color.WHITE;

        switch (foodcolor) {
            case 0:
                cc = Color.RED;
                break;
            case 1:
                cc = Color.LIGHTBLUE;
                break;
            case 2:
                cc = Color.YELLOW;
                break;
            case 3:
                cc = Color.VIOLET;
                break;
            case 4:
                cc = Color.ORANGE;
                break;
        }
        gc.setFill(cc);
        gc.fillOval(foodX * pointsize, foodY * pointsize, pointsize, pointsize);

        // snake
        for (Point c : snake) {
            gc.setFill(Color.TEAL);
            gc.fillRect(c.x * pointsize, c.y * pointsize, pointsize - 1, pointsize - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * pointsize, c.y * pointsize, pointsize - 2, pointsize - 2);

        }
    }

    public static void main(String[] args) {
        launch();

    }
}