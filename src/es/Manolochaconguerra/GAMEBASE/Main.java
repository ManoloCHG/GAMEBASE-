/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.Manolochaconguerra.GAMEBASE;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Manuel Jose chacon Guerra
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("PongFx");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Circle cabeza = new Circle(300, 200,50, Color.RED);
        Circle ojoi = new Circle (275, 175, 10, Color.BLUE);
        Circle ojod = new Circle (325, 175, 10, Color.BLUE);
        Rectangle r = new Rectangle(50,50,50,50);
        root.getChildren().add(cabeza);
        root.getChildren().add(ojoi);
        root.getChildren().add(ojod);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
