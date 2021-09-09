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

public class TestClass extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameOfLife.generateRandomGrid();

        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(50);
        tilePane.setPrefRows(50);
        tilePane.setTileAlignment( Pos.CENTER );

        Scene scene = new Scene(tilePane);
        scene.setFill(Color.LIGHTGRAY);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.05), e -> {
                    GameOfLife.nextGeneration();
                    drawNewFrame(primaryStage, tilePane, scene);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void drawNewFrame(Stage primaryStage, TilePane tilePane, Scene scene) {
        tilePane.getChildren().clear();

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (GameOfLife.grid[i][j] == 1) {
                    tilePane.getChildren().add(new Rectangle( 20, 20, Color.GREEN ));
                } else
                    tilePane.getChildren().add(new Rectangle( 20, 20, Color.WHITE ));
            }
        }

        primaryStage.setTitle("Game of Life");
        primaryStage.setScene( scene );
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);}
}