/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.Manolochaconguerra.GAMEBASE;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Manuel Jose chacon Guerra
 */

public class Main extends Application {
    Pane root = new Pane();
    int navegarX = 320;
    int navegarY = 510;
    int lanchaCurrentSpeed = 0;
    int TamañoTexto = 150;
    int puntuacion = 0;
    char estadoLobo = 'I';
    char estadoBarco = 'I';
    char estadoOveja = 'I';
    char estadoCol = 'I';
    long segundos = 0;
    boolean gameover = false;
    boolean colisionIzda = false;
    boolean colisionDecha = true;
    LocalDateTime inicial = LocalDateTime.now();
    AnimationTimer animationlancha;
    Text textPerder = new Text("Has Perdido");
    Text textGanar = new Text("Has Ganado");
    Text textReinicio = new Text("(Pulsa enter para reiniciar)");
    
    public void mostrarTexto (Text text ,int x, int y, int a){
        root.getChildren().add(text);
        text.setLayoutX (x);
        text.setLayoutY (y);
        text.setFont(Font.font(a));
        text.setFill(Color.GREEN);
           
    }
         
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 1320, 700);
        
        // Elementos del personaje
        Rectangle mastil = new Rectangle(150, -100, 10, 100);
        Rectangle Orillaizd = new Rectangle(200,100, 321, 170);
        Rectangle Orilladecha = new Rectangle(200,100, 325, 170);
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
  
    
        //Contenedores 
        HBox paneContenedor = new HBox();
        paneContenedor.setLayoutY(20);
        paneContenedor.setMinWidth (1320);
        paneContenedor.setAlignment(Pos.CENTER);
        paneContenedor.setSpacing(100);
        //intentos 
        HBox paneIntentos = new HBox();
        paneIntentos.setSpacing(10);
        paneContenedor.getChildren().add(paneIntentos);
        //Temporizador
        HBox paneTemporizador = new HBox();
        paneTemporizador.setSpacing(10);
        paneContenedor.getChildren().add(paneTemporizador);
        //Escore
        Text textScore = new Text ("INTENTOS:");
        textScore.setFont(Font.font(50));
        textScore.setFill(Color.GREEN);
        //Texto para la puntuacion
        Text score = new Text("0");
        score.setFont(Font.font(50));
        score.setFill(Color.GREEN);
        
        //Texto temporizador
        Text texTemp = new Text ("TIEMPO:");
        texTemp.setFont(Font.font(50));
        texTemp.setFill(Color.GREEN);
        //Puntuacion temporizador
        Text temp = new Text ("0");
        temp.setFont(Font.font(50));
        temp.setFill(Color.GREEN);
        //añadir los textos al layout
        paneIntentos.getChildren().add(textScore);
        paneIntentos.getChildren().add(score);
        paneTemporizador.getChildren().add(texTemp);
        paneTemporizador.getChildren().add(temp);
        //insertar imagen 
        Image fondo = new Image(getClass().getResourceAsStream("Imagenes/fondo.jpg"));
        Image Sheep = new Image(getClass().getResourceAsStream("Imagenes/PixelSheep.png"));
        Image howl = new Image(getClass().getResourceAsStream("Imagenes/howl.png"));
        Image col = new Image(getClass().getResourceAsStream("Imagenes/col.png"));
        Image barquero = new Image(getClass().getResourceAsStream("Imagenes/barquero.png"));
        //Insertar imagen
        ImageView imageViewfondo = new ImageView(fondo);
        ImageView imageViewSheep = new ImageView(Sheep);
        ImageView imageViewHowl = new ImageView(howl);
        ImageView imageViewCol = new ImageView(col);
        ImageView imageViewbarquero = new ImageView(barquero);
        //Posicion de la imagen
        // Variable Posicion Col
        final int POSIZDACOLX = 210;
        final int POSCOLY = 480;
        final int POSDECHACOLX = 1210;
        final int TAMAÑOCOL = 35;
        //Variable Posicion oveja
        final int POSIZDAOVEJAX = 150;
        final int POSOVEJAY = 460;
        final int POSDECHAOVEJAX = 1120;
        //Variable Posicion Lobo
        final int POSIZDALOBOX = 50;
        final int POSLOBOY = 455;
        final int POSDECHALOBOX = 1020;
        //Fondo
        imageViewfondo.setX(00);
        imageViewfondo.setY(00);
        //orillaizda
        Orillaizd.setX(0);
        Orillaizd.setY(510);
        //orilladecha
        Orilladecha.setX(972);
        Orilladecha.setY(510);
        //ocultar orillas
        Orillaizd.setVisible(false);
        Orilladecha.setVisible(false);
        //Oveja
        imageViewSheep.setX(POSIZDAOVEJAX);
        imageViewSheep.setY(POSOVEJAY);
        //Lobo
        imageViewHowl.setX(POSIZDALOBOX);
        imageViewHowl.setY(POSLOBOY);
        //Col
        imageViewCol.setX(POSIZDACOLX);
        imageViewCol.setY(POSCOLY);
        imageViewCol.setFitHeight(TAMAÑOCOL);
        imageViewCol.setFitWidth(TAMAÑOCOL);
        //barquero
        imageViewbarquero.setFitHeight(100);
        imageViewbarquero.setFitWidth(100);
        imageViewbarquero.setX(140);
        imageViewbarquero.setY(-90);
        // Agrupar todos los elementos
        root.getChildren().add(imageViewfondo);
        //orillas
        root.getChildren().add(Orillaizd);
        root.getChildren().add(Orilladecha);
        root.getChildren().add(paneContenedor);
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
                //Moviento
        animationlancha = new AnimationTimer() {
            @Override
            public void handle (long now){
                //Temporizador
                LocalDateTime actual;
                actual = LocalDateTime.now();
                navegarX += lanchaCurrentSpeed;
                lancha.setLayoutX(navegarX);
                Shape shapecolision1 = Shape.intersect (proa ,Orillaizd);
                colisionIzda = shapecolision1.getBoundsInLocal().isEmpty();
                Shape shapecolision2 = Shape.intersect (popa ,Orilladecha);
                colisionDecha = shapecolision2.getBoundsInLocal().isEmpty();
                if (colisionDecha == false){
                    lanchaCurrentSpeed = 0;
                    estadoBarco = 'D';
                }
                if (colisionIzda == false){
                    lanchaCurrentSpeed = 0;
                    estadoBarco = 'I' ;
                }
                //col derecha
                if (estadoCol == 'C' && estadoBarco == 'D'){
                    root.getChildren().remove(imageViewCol);
                    lancha.getChildren().remove(imageViewCol);
                    root.getChildren().add(imageViewCol);
                    imageViewCol.setX(POSDECHACOLX);
                    imageViewCol.setY(POSCOLY);
                    estadoCol = 'D';
                }
                //col izquierda
                if (estadoCol == 'C' && estadoBarco == 'I'){
                    root.getChildren().remove(imageViewCol);
                    lancha.getChildren().remove(imageViewCol);
                    root.getChildren().add(imageViewCol);
                    imageViewCol.setX(POSIZDACOLX);
                    imageViewCol.setY(POSCOLY);
                    estadoCol = 'I'; 
                }
                //Oveja derecha
                if (estadoOveja == 'C' && estadoBarco == 'D'){
                    root.getChildren().remove(imageViewSheep);
                    lancha.getChildren().remove(imageViewSheep);
                    root.getChildren().add(imageViewSheep);
                    imageViewSheep.setX(POSDECHAOVEJAX);
                    imageViewSheep.setY(POSOVEJAY);
                    estadoOveja = 'D';
                }
                //Oveja izquierda
                if (estadoOveja == 'C' && estadoBarco == 'I'){
                    root.getChildren().remove(imageViewSheep);
                    lancha.getChildren().remove(imageViewSheep);
                    root.getChildren().add(imageViewSheep);
                    imageViewSheep.setX(POSIZDAOVEJAX);
                    imageViewSheep.setY(POSOVEJAY);
                    estadoOveja = 'I'; 
                }
                //Lobo derecha
                if (estadoLobo == 'C' && estadoBarco == 'D'){
                    root.getChildren().remove(imageViewHowl);
                    lancha.getChildren().remove(imageViewHowl);
                    root.getChildren().add(imageViewHowl);
                    imageViewHowl.setX(POSDECHALOBOX);
                    imageViewHowl.setY(POSLOBOY);
                    estadoLobo = 'D';
                }
                //Lobo izquierda
                if (estadoLobo == 'C' && estadoBarco == 'I'){
                    root.getChildren().remove(imageViewHowl);
                    lancha.getChildren().remove(imageViewHowl);
                    root.getChildren().add(imageViewHowl);
                    imageViewHowl.setX(POSIZDALOBOX);
                    imageViewHowl.setY(POSLOBOY);
                    estadoLobo = 'I'; 
                }
                //Restricciones y Mensaje 
                if (estadoLobo == 'I'&& estadoOveja == 'I' && estadoBarco == 'D'){
                    mostrarTexto(textPerder, 425, 180, 100);
                    mostrarTexto(textReinicio, 325, 270, 60);
                    gameover = true;
                    segundos = ChronoUnit.SECONDS.between(inicial ,actual);
                    temp.setText(String.valueOf(segundos));
                    this.stop();
                }
                
                if (estadoLobo == 'D'&& estadoOveja == 'D' && estadoBarco == 'I'){
                    mostrarTexto(textPerder, 425, 180, 100);
                    mostrarTexto(textReinicio, 325, 270, 60);
                    gameover = true;
                    segundos = ChronoUnit.SECONDS.between(inicial ,actual);
                    temp.setText(String.valueOf(segundos));
                    this.stop();
                }
                if (estadoCol == 'I'&& estadoOveja == 'I' && estadoBarco == 'D'){
                    mostrarTexto(textPerder, 425, 180, 100);
                    mostrarTexto(textReinicio, 325, 270, 60);
                    gameover = true;
                    segundos = ChronoUnit.SECONDS.between(inicial ,actual);
                    temp.setText(String.valueOf(segundos));
                    this.stop();
                    
                }
                if (estadoCol == 'D'&& estadoOveja == 'D' && estadoBarco == 'I'){
                    mostrarTexto(textPerder, 425, 180, 100);
                    mostrarTexto(textReinicio, 325, 270, 60);
                    gameover = true;
                    segundos = ChronoUnit.SECONDS.between(inicial ,actual);
                    temp.setText(String.valueOf(segundos));
                    this.stop();
                }
                if (estadoLobo == 'D' && estadoCol == 'D'&& estadoOveja == 'D' && estadoBarco == 'D'){
                    mostrarTexto(textGanar, 425, 180, 100);
                    mostrarTexto(textReinicio, 325, 270, 60);
                    gameover = false;
                    segundos = ChronoUnit.SECONDS.between(inicial ,actual);
                    temp.setText(String.valueOf(segundos));
                    puntuacion = -1;
                    this.stop();
                }
            };
        };
        animationlancha.start();
        //Accion Izquierda
        //col
        imageViewCol.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle (MouseEvent mouseEvent) {
                if (estadoCol == 'I' && estadoBarco == 'I' && gameover == false ){
                    //Insertar aquí el código a ejecutar cuando se haga clic en el ratón
                    lancha.getChildren().add(imageViewCol);
                    imageViewCol.setX(20);
                    imageViewCol.setY(-30);
                    if (colisionIzda == true && colisionDecha == false ){
                        colisionDecha = false;
                        lanchaCurrentSpeed = -2;
                    }
                    if (colisionIzda == false && colisionDecha == true){
                        colisionIzda = true;
                        lanchaCurrentSpeed = 2;
                    }
                    estadoCol = 'C';
                }
                if (estadoCol == 'D' && estadoBarco == 'D' && gameover == false){
                    //Insertar aquí el código a ejecutar cuando se haga clic en el ratón
                    lancha.getChildren().add(imageViewCol);
                    imageViewCol.setX(20);
                    imageViewCol.setY(-30);
                    if (colisionIzda == true && colisionDecha == false ){
                        colisionDecha = false;
                        lanchaCurrentSpeed = -2;
                    }
                    if (colisionIzda == false && colisionDecha == true){
                        colisionIzda = true;
                        lanchaCurrentSpeed = 2;
                    }
                    estadoCol = 'C';
                }
                estadoBarco = 'C';
                System.out.println(estadoCol);
            }
        });
        //oveja
        imageViewSheep.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle (MouseEvent mouseEvent) {
                if (estadoOveja == 'I' && estadoBarco == 'I' && gameover == false){
                    //Insertar aquí el código a ejecutar cuando se haga clic en el ratónç
                    lancha.getChildren().add(imageViewSheep);
                    imageViewSheep.setX(30);
                    imageViewSheep.setY(-50);
                    if (colisionIzda == true && colisionDecha == false ){
                        colisionDecha = false;
                        lanchaCurrentSpeed = -2;
                    }
                    if (colisionIzda == false && colisionDecha == true){
                        colisionIzda = true;
                        lanchaCurrentSpeed = 2;
                    }
                    estadoOveja = 'C';
                }
                if (estadoOveja == 'D' && estadoBarco == 'D' && gameover == false){
                    //Insertar aquí el código a ejecutar cuando se haga clic en el ratónç
                    lancha.getChildren().add(imageViewSheep);
                    imageViewSheep.setX(30);
                    imageViewSheep.setY(-50);
                    if (colisionIzda == true && colisionDecha == false ){
                        colisionDecha = false;
                        lanchaCurrentSpeed = -2;
                    }
                    if (colisionIzda == false && colisionDecha == true){
                        colisionIzda = true;
                        lanchaCurrentSpeed = 2;
                    }
                    estadoOveja = 'C';
                }
                estadoBarco = 'C';
                System.out.println(estadoOveja);
            }
        });
        //lobo
        imageViewHowl.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle (MouseEvent mouseEvent) {
                if (estadoLobo == 'I'&& estadoBarco == 'I' && gameover == false){
                    //Insertar aquí el código a ejecutar cuando se haga clic en el ratón
                    lancha.getChildren().add(imageViewHowl);
                    imageViewHowl.setX(30);
                    imageViewHowl.setY(-60);
                    if (colisionIzda == true && colisionDecha == false ){
                        colisionDecha = false;
                        lanchaCurrentSpeed = -2;
                    }
                    if (colisionIzda == false && colisionDecha == true){
                        colisionIzda = true;
                        lanchaCurrentSpeed = 2;
                    }
                    estadoLobo = 'C';
                }
                if (estadoLobo == 'D'&& estadoBarco == 'D' && gameover == false){
                    //Insertar aquí el código a ejecutar cuando se haga clic en el ratón
                    lancha.getChildren().add(imageViewHowl);
                    imageViewHowl.setX(30);
                    imageViewHowl.setY(-60);
                    if (colisionIzda == true && colisionDecha == false ){
                        colisionDecha = false;
                        lanchaCurrentSpeed = -2;
                    }
                    if (colisionIzda == false && colisionDecha == true){
                        colisionIzda = true;
                        lanchaCurrentSpeed = 2;
                    }
                    estadoLobo = 'C';
                }
                estadoBarco = 'C';
                 System.out.println(estadoLobo);
            }
        });
        //baquero
        imageViewbarquero.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle (MouseEvent mouseEvent) {
                //Insertar aquí el código a ejecutar cuando se haga clic en el ratón
                if (colisionIzda == true && colisionDecha == false ){
                    colisionDecha = false;
                    lanchaCurrentSpeed = -2;
                    estadoBarco = 'D';
                }
                if (colisionIzda == false && colisionDecha == true){
                    colisionIzda = true;
                    lanchaCurrentSpeed = 2;
                    estadoBarco = 'I';
                }
                estadoBarco = 'C';
                System.out.println(estadoCol);
                System.out.println(estadoLobo);
                System.out.println(estadoOveja);
                System.out.println(estadoBarco);
            }
        });
        scene.setOnKeyPressed((KeyEvent reinicio) -> {
            switch(reinicio.getCode()) {
                case ENTER :
                    animationlancha.start();
                    gameover = false;
                    if (estadoBarco != 'C'){
                    //Grupo
                    navegarX = 320;
                    navegarY = 510;
                    lancha.setLayoutX(navegarX);
                    lancha.setLayoutY(navegarY);
                    //Oveja
                    imageViewSheep.setX(POSIZDAOVEJAX);
                    imageViewSheep.setY(POSOVEJAY);
                    //Lobo
                    imageViewHowl.setX(POSIZDALOBOX);
                    imageViewHowl.setY(POSLOBOY);
                    //Col
                    imageViewCol.setX(POSIZDACOLX);
                    imageViewCol.setY(POSCOLY);
                    imageViewCol.setFitHeight(TAMAÑOCOL);
                    imageViewCol.setFitWidth(TAMAÑOCOL);
                    root.getChildren().remove(textGanar);
                    root.getChildren().remove(textPerder);
                    root.getChildren().remove(textReinicio);
                    estadoLobo = 'I';
                    estadoBarco = 'I';
                    estadoOveja = 'I';
                    estadoCol = 'I';
                    puntuacion ++;
                    segundos = 0;
                    colisionDecha = false;
                    colisionIzda = false;
                    score.setText(String.valueOf(puntuacion));
                    temp.setText(String.valueOf(segundos));
                    }
                    break;
            }
        
    });

        
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
