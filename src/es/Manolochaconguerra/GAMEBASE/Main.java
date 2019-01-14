/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.Manolochaconguerra.GAMEBASE;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Manuel Jose chacon Guerra
 */
public class Main extends Application {
    
    int navegarX = 0;
    int navegarY = 0;       
            
    @Override
    public void start(Stage primaryStage) {
        
        // Elementos del personaje
        Rectangle mastil = new Rectangle(150, -100, 10, 100);
        Rectangle barco = new Rectangle(50, 0, 200, 50);
        Polygon proa = new Polygon();
        proa.getPoints().addAll(new Double[]{
            0.0, 0.0,
            50.0, 0.0,
            50.0, 50.0 });
        Polygon popa = new Polygon();
        popa.getPoints().addAll(new Double[]{
            310.0, 0.0,
            250.0, 0.0,
            250.0, 50.0 });
        Polygon vela = new Polygon();
        vela.getPoints().addAll(new Double[]{
            150.0, -100.0,
            150.0, -25.0,
            100.0, -25.0 });
        // Colorear cada elemento
        barco.setFill(Color.BROWN);
        proa.setFill(Color.BROWN);
        popa.setFill(Color.BROWN);
        mastil.setFill(Color.BROWN);
        vela.setFill(Color.BLUE);
        // Agrupar todos los elementos
        Group lancha = new Group();
        lancha.getChildren().addAll(barco, proa, popa, mastil,vela);
        
        // Posicionar el grupo en la pantalla
        lancha.setLayoutX(200);
        lancha.setLayoutY(300);
        //Añadir el grupo al contenedor principal
        Pane root = new Pane();
        root.getChildren().add(lancha);
        
        //Moviento
       AnimationTimer navegando = new AnimationTimer() {
            @Override
            public void handle(long now) {
                navegarX++;
                lancha.setLayoutX(navegarX);
                //lancha.setLayoutY(navegarY);
            };
       };
       navegando.start();
       
       Scene scene = new Scene(root, 1200, 700);
       primaryStage.setTitle("GAMEBASE");
       primaryStage.setScene(scene);
       primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
