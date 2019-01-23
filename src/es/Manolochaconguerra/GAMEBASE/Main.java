/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.Manolochaconguerra.GAMEBASE;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Manuel Jose chacon Guerra
 */
public class Main extends Application {
    Pane root = new Pane();
    int navegarX = 325;
    int navegarY = 510;
    int lanchaCurrentSpeed = 0;
    int TamañoTexto = 200;
    char estadoLobo = 'I';
    char estadoBarco = 'I';
    char estadoObeja = 'I';
    char estadoCol = 'I';
    String perder = "has perdido";
    String ganar  = "Has ganado";
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
        //TEXTOS
        //Has perdido
        Text derrota = new Text ("Has Perdido");
        derrota.setFont(Font.font(TamañoTexto));
        derrota.setFill(Color.GREEN);
        derrota.setX(100);
        derrota.setY(200);
        //Has ganado
        Text victoria = new Text ("Has Ganado");
        victoria.setFont(Font.font(TamañoTexto));
        victoria.setFill(Color.GREEN);
        victoria.setX(100);
        victoria.setY(200);
        //insertar imagen 
        Image fondo = new Image(getClass().getResourceAsStream("Imagenes/fondo.jpg"));
        Image Sheep = new Image(getClass().getResourceAsStream("Imagenes/PixelSheep.png"));
        Image howl = new Image(getClass().getResourceAsStream("Imagenes/howl.png"));
        Image col = new Image(getClass().getResourceAsStream("Imagenes/col.png"));
        Image barquero = new Image(getClass().getResourceAsStream("Imagenes/barquero.png"));
        //Insertar magen
        ImageView imageViewfondo = new ImageView(fondo);
        ImageView imageViewSheep = new ImageView(Sheep);
        ImageView imageViewHowl = new ImageView(howl);
        ImageView imageViewCol = new ImageView(col);
        ImageView imageViewbarquero = new ImageView(barquero);
        //Posicion de la imagen
        //Fondo
        imageViewfondo.setX(00);
        imageViewfondo.setY(00);
        //Obeja
        imageViewSheep.setX(150);
        imageViewSheep.setY(460);
        //Lobo
        imageViewHowl.setX(50);
        imageViewHowl.setY(455);
        //Col
        imageViewCol.setX(210);
        imageViewCol.setY(480);
        imageViewCol.setFitHeight(35);
        imageViewCol.setFitWidth(35);
        //barquero
        imageViewbarquero.setFitHeight(100);
        imageViewbarquero.setFitWidth(100);
        imageViewbarquero.setX(140);
        imageViewbarquero.setY(-90);
        // Agrupar todos los elementos
        root.getChildren().add(imageViewfondo);
        root.getChildren().add(imageViewSheep);
        root.getChildren().add(imageViewHowl);
        root.getChildren().add(imageViewCol);
        Group lancha = new Group();
        lancha.getChildren().addAll(barco, proa, popa, mastil,vela,imageViewbarquero);
        
        // Posicionar el grupo en la pantalla
        lancha.setLayoutX(navegarX);
        lancha.setLayoutY(navegarY);
        //Añadir el grupo al contenedor principal
        root.getChildren().add(lancha);
        //Accion Izquierda
        //col
        imageViewCol.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle (MouseEvent mouseEvent) {
                if (estadoCol == 'I' && estadoBarco == 'I'){
                //Insertar aquí el código a ejecutar cuando se haga clic en el ratón
                lancha.getChildren().add(imageViewCol);
                imageViewCol.setX(20);
                imageViewCol.setY(-30);
                if (navegarX >= 720){
                    lanchaCurrentSpeed = -1;
                }
                if (navegarX <= 325){
                    lanchaCurrentSpeed = 1;
                }
                estadoCol = 'C';
                }
                if (estadoCol == 'D' && estadoBarco == 'D'){
                //Insertar aquí el código a ejecutar cuando se haga clic en el ratón
                lancha.getChildren().add(imageViewCol);
                imageViewCol.setX(20);
                imageViewCol.setY(-30);
                if (navegarX >= 720){
                    lanchaCurrentSpeed = -1;
                }
                if (navegarX <= 325){
                    lanchaCurrentSpeed = 1;
                }
                estadoCol = 'C';
                }
                estadoBarco = 'C';
            }
        });
        //obeja
        imageViewSheep.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle (MouseEvent mouseEvent) {
                if (estadoObeja == 'I' && estadoBarco == 'I'){
                //Insertar aquí el código a ejecutar cuando se haga clic en el ratónç
                lancha.getChildren().add(imageViewSheep);
                imageViewSheep.setX(30);
                imageViewSheep.setY(-50);
                if (navegarX >= 720){
                    lanchaCurrentSpeed = -1;
                }
                if (navegarX <= 325){
                    lanchaCurrentSpeed = 1;
                }
                estadoObeja = 'C';
                }
                if (estadoObeja == 'D' && estadoBarco == 'D'){
                //Insertar aquí el código a ejecutar cuando se haga clic en el ratónç
                lancha.getChildren().add(imageViewSheep);
                imageViewSheep.setX(30);
                imageViewSheep.setY(-50);
                if (navegarX >= 720){
                    lanchaCurrentSpeed = -1;
                }
                if (navegarX <= 325){
                    lanchaCurrentSpeed = 1;
                }
                estadoObeja = 'C';
                }
                estadoBarco = 'C';
            }
        });
        //lobo
        imageViewHowl.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle (MouseEvent mouseEvent) {
                if (estadoLobo == 'I'&& estadoBarco == 'I'){
                //Insertar aquí el código a ejecutar cuando se haga clic en el ratón
                lancha.getChildren().add(imageViewHowl);
                imageViewHowl.setX(30);
                imageViewHowl.setY(-60);
                if (navegarX >= 720){
                    lanchaCurrentSpeed = -1;
                }
                if (navegarX <= 325){
                    lanchaCurrentSpeed = 1;
                }
                estadoLobo = 'C';
                }
                if (estadoLobo == 'D'&& estadoBarco == 'D'){
                //Insertar aquí el código a ejecutar cuando se haga clic en el ratón
                lancha.getChildren().add(imageViewHowl);
                imageViewHowl.setX(30);
                imageViewHowl.setY(-60);
                if (navegarX >= 720){
                    lanchaCurrentSpeed = -1;
                }
                if (navegarX <= 325){
                    lanchaCurrentSpeed = 1;
                }
                estadoLobo = 'C';
                }
                estadoBarco = 'C';
            }
        });
        //baquero
        imageViewbarquero.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle (MouseEvent mouseEvent) {
                //Insertar aquí el código a ejecutar cuando se haga clic en el ratón
                if (navegarX >= 720){
                    lanchaCurrentSpeed = -1;
                }
                if (navegarX <= 325){
                    lanchaCurrentSpeed = 1;
                }
                estadoBarco = 'C';
                System.out.println(estadoCol);
                System.out.println(estadoLobo);
                System.out.println(estadoObeja);
                System.out.println(estadoBarco);
            }
        });
        scene.setOnKeyPressed((KeyEvent reinicio) -> {
            switch(reinicio.getCode()) {
                case ENTER :
                    break;
            }
    });
        //Moviento
       AnimationTimer animationlancha = new AnimationTimer() {
            @Override
            public void handle (long now){
                navegarX += lanchaCurrentSpeed;
                lancha.setLayoutX(navegarX);
                if (navegarX == 720){
                    lanchaCurrentSpeed = 0;
                    estadoBarco = 'D';
                }
                if (navegarX == 325){
                    lanchaCurrentSpeed = 0;
                    estadoBarco = 'I' ;
                }
                //col derecha
                if (estadoCol == 'C' && estadoBarco == 'D'){
                    root.getChildren().remove(imageViewCol);
                    lancha.getChildren().remove(imageViewCol);
                    root.getChildren().add(imageViewCol);
                    imageViewCol.setX(1210);
                    imageViewCol.setY(480);
                    estadoCol = 'D';
                }
                //col izquierda
                if (estadoCol == 'C' && estadoBarco == 'I'){
                    root.getChildren().remove(imageViewCol);
                    lancha.getChildren().remove(imageViewCol);
                    root.getChildren().add(imageViewCol);
                    imageViewCol.setX(210);
                    imageViewCol.setY(480);
                    estadoCol = 'I'; 
                }
                //Obeja derecha
                if (estadoObeja == 'C' && estadoBarco == 'D'){
                    root.getChildren().remove(imageViewSheep);
                    lancha.getChildren().remove(imageViewSheep);
                    root.getChildren().add(imageViewSheep);
                    imageViewSheep.setX(1120);
                    imageViewSheep.setY(460);
                    estadoObeja = 'D';
                }
                //Obeja izquierda
                if (estadoObeja == 'C' && estadoBarco == 'I'){
                    root.getChildren().remove(imageViewSheep);
                    lancha.getChildren().remove(imageViewSheep);
                    root.getChildren().add(imageViewSheep);
                    imageViewSheep.setX(150);
                    imageViewSheep.setY(460);
                    estadoObeja = 'I'; 
                }
                //Lobo derecha
                if (estadoLobo == 'C' && estadoBarco == 'D'){
                    root.getChildren().remove(imageViewHowl);
                    lancha.getChildren().remove(imageViewHowl);
                    root.getChildren().add(imageViewHowl);
                    imageViewHowl.setX(1020);
                    imageViewHowl.setY(455);
                    estadoLobo = 'D';
                }
                //Lobo izquierda
                if (estadoLobo == 'C' && estadoBarco == 'I'){
                    root.getChildren().remove(imageViewHowl);
                    lancha.getChildren().remove(imageViewHowl);
                    root.getChildren().add(imageViewHowl);
                    imageViewHowl.setX(50);
                    imageViewHowl.setY(455);
                    estadoLobo = 'I'; 
                }
                //Restricciones y Mensaje 
                if (estadoLobo == 'I'&& estadoObeja == 'I' && estadoBarco == 'D'){
                    System.out.println(perder);
                    root.getChildren().add(derrota);
                    this.stop();
                }
                
                if (estadoLobo == 'D'&& estadoObeja == 'D' && estadoBarco == 'I'){
                    System.out.println(perder);
                    root.getChildren().add(derrota);
                    this.stop();
                }
                if (estadoCol == 'I'&& estadoObeja == 'I' && estadoBarco == 'D'){
                    System.out.println(perder);
                    root.getChildren().add(derrota);
                    this.stop();
                }
                if (estadoCol == 'I'&& estadoObeja == 'I' && estadoBarco == 'D'){
                    System.out.println(perder);
                    root.getChildren().add(derrota);
                    this.stop();
                    //this.
                }
                if (estadoCol == 'D'&& estadoObeja == 'D' && estadoBarco == 'I'){
                    System.out.println(perder);
                    root.getChildren().add(derrota);
                    this.stop();
                }
                if ( estadoLobo == 'D' && estadoCol == 'D'&& estadoObeja == 'D' && estadoBarco == 'D'){
                    System.out.println(ganar);
                    root.getChildren().add(victoria);
                    this.stop();
                }
            };
        };
        animationlancha.start();
        
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
