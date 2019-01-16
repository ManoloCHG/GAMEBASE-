/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.Manolochaconguerra.GAMEBASE;

import java.awt.event.MouseEvent;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    Pane root = new Pane();
    int navegarX = 325;
    int navegarY = 510;
    int lanchacurrentspeedX = 0;
    Color colorFondo = Color.rgb(158, 251, 252);       
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 1320, 700);
        
        // Elementos del personaje
        Rectangle mastil = new Rectangle(150, -100, 10, 100);
        Rectangle barco = new Rectangle(50, 0, 150, 50);
        Polygon proa = new Polygon();
        proa.getPoints().addAll(new Double[]{
            0.0, 0.0,
            50.0, 0.0,
            50.0, 50.0 });
        Polygon popa = new Polygon();
        popa.getPoints().addAll(new Double[]{
            250.0, 0.0,
            200.0, 0.0,
            200.0, 50.0 });
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
        
        //insertar imagen 
        Image fondo = new Image(getClass().getResourceAsStream("Imagenes/fondo.jpg"));
        Image Sheep = new Image(getClass().getResourceAsStream("Imagenes/PixelSheep.png"));
        Image howl = new Image(getClass().getResourceAsStream("Imagenes/howl.png"));
        Image col = new Image(getClass().getResourceAsStream("Imagenes/col.png"));
        Image barquero = new Image(getClass().getResourceAsStream("Imagenes/barquero.png"));
        //Insertar magen
        ImageView imageViewfondo = new ImageView(fondo);
        ImageView imageViewSheep = new ImageView(Sheep);
        ImageView imageViewhowl = new ImageView(howl);
        ImageView imageViewcol = new ImageView(col);
        ImageView imageViewbarquero = new ImageView(barquero);
        //Posicion de la imagen
        //Fondo
        imageViewfondo.setX(00);
        imageViewfondo.setY(00);
        //Obeja
        imageViewSheep.setX(150);
        imageViewSheep.setY(460);
        //Lobo
        imageViewhowl.setX(50);
        imageViewhowl.setY(455);
        //Col
        imageViewcol.setX(210);
        imageViewcol.setY(480);
        imageViewcol.setFitHeight(35);
        imageViewcol.setFitWidth(35);
        //barquero
        imageViewbarquero.setFitHeight(100);
        imageViewbarquero.setFitWidth(100);
        imageViewbarquero.setX(140);
        imageViewbarquero.setY(-90);
        // Agrupar todos los elementos
        root.getChildren().add(imageViewfondo);
        root.getChildren().add(imageViewSheep);
        root.getChildren().add(imageViewhowl);
        root.getChildren().add(imageViewcol);
        Group lancha = new Group();
        lancha.getChildren().addAll(barco, proa, popa, mastil,vela,imageViewbarquero);
        
        // Posicionar el grupo en la pantalla
        lancha.setLayoutX(325);
        lancha.setLayoutY(510);
        //Añadir el grupo al contenedor principal
        root.getChildren().add(lancha);
        //Accion
        //scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            //@Override
            //public void handle(MouseEvent mouseEvent) {
                // Insertar aquí el código a ejecutar cuando se haga clic en el ratón
                //System.out.println("Mouse clicked X : Y - " + 
                        //mouseEvent.getX() + " : " + mouseEvent.getY());
                //};
        //};
        //Moviento
       AnimationTimer navegando = new AnimationTimer() {
            @Override
            public void handle(long now) {
                navegarX += lanchacurrentspeedX ;
                if (navegarX >= 750){
                    lanchacurrentspeedX = -1;
                }
                if (navegarX >= 325){
                    lanchacurrentspeedX = 1;
                }
                System.out.println(navegarX);
                lancha.setLayoutX(navegarX);
                lancha.setLayoutY(navegarY);
            };
       };
       navegando.start();
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
