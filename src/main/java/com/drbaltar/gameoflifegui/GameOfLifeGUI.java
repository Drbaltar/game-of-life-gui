package com.drbaltar.gameoflifegui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameOfLifeGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game of Life");
        TilePane tilePane = getTilePane(50, 50);
        setScene(primaryStage, tilePane);
        playAnimation(primaryStage, tilePane);
    }

    private TilePane getTilePane(int columns, int rows) {
        TilePane tilePane = new TilePane();

        tilePane.setPrefColumns(columns);
        tilePane.setPrefRows(rows);
        tilePane.setTileAlignment(Pos.CENTER);

        return tilePane;
    }

    private void setScene(Stage primaryStage, TilePane tilePane) {
        Scene scene = new Scene(tilePane);

        scene.setFill(Color.LIGHTGRAY);
        primaryStage.setScene(scene);
    }

    private void playAnimation(Stage primaryStage, TilePane tilePane) {
        GameOfLife.generateRandomGrid();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.05), e -> showNextGridPane(primaryStage, tilePane))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void showNextGridPane(Stage primaryStage, TilePane tilePane) {
        GameOfLife.nextGeneration();
        drawNewPane(tilePane);
        primaryStage.show();
    }

    private void drawNewPane(TilePane tilePane) {
        tilePane.getChildren().clear();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (GameOfLife.grid[i][j] == 1) {
                    tilePane.getChildren().add(new Rectangle(20, 20, Color.GREEN));
                } else
                    tilePane.getChildren().add(new Rectangle(20, 20, Color.WHITE));
            }
        }
    }
}