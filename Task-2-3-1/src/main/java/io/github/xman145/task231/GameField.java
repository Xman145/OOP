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

import static io.github.xman145.task231.SnakeApp.HEIGHT;
import static io.github.xman145.task231.SnakeApp.COLUMNS;
import static io.github.xman145.task231.SnakeApp.ROWS;
import static io.github.xman145.task231.SnakeApp.SQUARE_SIZE;
import static io.github.xman145.task231.SnakeApp.WIDTH;
import static io.github.xman145.task231.SnakeApp.pointsize;
import static io.github.xman145.task231.SnakeApp.goal;
import static io.github.xman145.task231.SnakeApp.speed;

public class GameField {
    public Scene scene;
    private Stage stage;
    private GraphicsContext gcBackground;
    private GraphicsContext gcObjects;
    private SnakeApp gameProcess;
    public GameField(Stage stage, SnakeApp gameProcess) {

        this.stage = stage;
        this.gameProcess = gameProcess;
        VBox root = new VBox();
        Canvas canvasObjects = new Canvas(WIDTH, HEIGHT);
        Canvas canvasBackground = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvasObjects);
        root.getChildren().add(canvasBackground);
        this.scene = new Scene(root,WIDTH,HEIGHT);
        this.gcObjects = canvasObjects.getGraphicsContext2D();
        this.gcBackground = canvasBackground.getGraphicsContext2D();

    }

    void showGameField() {
        this.stage.setScene(this.scene);
    }

    public GraphicsContext getGraphicsContext() {
        return this.gcObjects;
    }

    public void drawBackground() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gcBackground.setFill(Color.BLUE);
                } else {
                    this.gcBackground.setFill(Color.BLACK);
                }
                this.gcBackground.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE,
                        SQUARE_SIZE);
            }
        }
    }

    public void drawFood(Color foodcolor,int foodX,int foodY){
        gcObjects.setFill(foodcolor);
        gcObjects.fillOval(foodX * pointsize, foodY * pointsize, pointsize, pointsize);

    }

    public void drawScore(){
        gcObjects.setFill(Color.WHITE);
        gcObjects.setFont(new Font("", 30));
        gcObjects.fillText("Score: " + (speed - 5) + " The Goal is "+ (goal), 10, 30);
    }

    public void drawSnake(List<Point> snake){
        gcObjects.setFill(Color.GREEN);


    }


}
