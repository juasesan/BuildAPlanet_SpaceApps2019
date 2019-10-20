/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildaplanet;

import Elementos.CuerpoCeleste;
import Elementos.Planeta;
import Elementos.SistemaSolar;
import static buildaplanet.Util.dialogoMaterial;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.geometry.Point2D;
import javafx.scene.shape.Ellipse;
		import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Josue
 */
public class GamePane {
    
    private Stage primaryStage;
    private BorderPane root;
    private StackPane lastRoot;
    private StackPane gameDisplay;
    private VBox planetBox;
    private Image img;

    private ScrollPane scrollP;
    private Random rd;
    private Point2D origen;
    private ArrayList<PathTransition> paths = new ArrayList();
    public boolean pause = false;

    public GamePane() {
    }


    public GamePane(Stage primaryStage,ImageView star) {

        this.primaryStage = primaryStage;
        //this.root = root;
        this.root=new BorderPane();
        this.gameDisplay=new StackPane();
        this.lastRoot=(StackPane)primaryStage.getScene().getRoot();
        this.rd=new Random();
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        this.origen=new Point2D(screenSize.getWidth()/2f,screenSize.getHeight()/2f);
        this.scrollP=new ScrollPane();
        planetBox=setPlanetsBox();
        scrollP.setContent(planetBox);
        scrollP.getStyleClass().add("scroll-pane");
        root.setLeft(scrollP);
        root.setBottom(setToolBox());
        //gameDisplay.getChildren().add(getSky());
        root.setCenter(gameDisplay);
        star.setLayoutX(origen.getX());
        star.setLayoutY(origen.getY());
        System.out.println(origen.getX() +"--------"+origen.getY());
        gameDisplay.getChildren().add(star);
        generateNotification();
    }
    
    public void generateNotification(){
        InputStream rutaStream=getClass().getResourceAsStream("/recursos/PantallaPrincipal/qmark.png");
        Image img = new Image(rutaStream,55,55,true,true);
        ImageView ig=new ImageView(img);
        
        
        Notifications noti=Notifications.create()
                .title("Dato Curioso")
                .text("En Marte hay hasta un 62% menos\n de gravedad que en la Tierra,"
                        + "siendo\n el lugar ideal para aquellos que\n quieren bajar de peso en poco tiempo,\n"
                        + " pues una persona que en nuestro planeta\n pesa,por ejemplo, 100 kg,"
                        + " pesaría\n apenas unos 40 kg en el planeta rojo.")
                .graphic(ig).darkStyle()
                .hideAfter(Duration.seconds(6))
                .position(Pos.TOP_RIGHT);
        
        noti.show();
        
    }
    
    public ImageView getSky(){
        InputStream rutaStream=getClass().getResourceAsStream("/recursos/PantallaPrincipal/fondoJuego.jpg");
        this.img = new Image(rutaStream);
        ImageView img=new ImageView(this.img);
        
        return img;
    }
    
    public VBox setPlanetsBox(){
        
        VBox planetBox=new VBox();
        planetBox.getStyleClass().add("planetBox");
        Label titulo=new Label("Planetas");
        titulo.getStyleClass().add("labelPlanetas");
        planetBox.getChildren().add(titulo);
        planetBox.setAlignment(Pos.CENTER);
        planetBox.setSpacing(10);
        planetBox.setPadding(new Insets(5));
        return planetBox;
    }
    
    public HBox setToolBox(){
        
        HBox toolBox=new HBox();
        toolBox.getStyleClass().add("optionsBox");
        toolBox.setAlignment(Pos.CENTER);
        toolBox.setSpacing(10);
        toolBox.setPadding(new Insets(5));
        
        JFXButton volver=new JFXButton("Menú principal");
        
        volver.getStyleClass().add("jfx-buttonGamePane");
        volver.setOnAction((y)->{this.primaryStage.getScene().setRoot(lastRoot);});
        JFXButton addPlanetHandle=new JFXButton("Añadir planeta");
        addPlanetHandle.setOnAction((t)->{
            //logica que anade el planeta al sistema
            Planeta planeta = new Planeta(getEntity());
            this.planetBox.getChildren().add(planeta.getImage());
            SistemaSolar.planets.add(planeta);
        });
        
        JFXButton addPlanet=new JFXButton("Añadir planeta");
        
        addPlanet.getStyleClass().add("jfx-buttonGamePane");
        addPlanet.setOnAction((t)->{
            dialogoMaterial(gameDisplay,"Configuración del planeta",(Node)getPlanetForm(),addPlanetHandle);
        //Pane panel,String titulo, Node cuerpo, JFXButton boton
            
        });
        JFXButton resumeSim=new JFXButton("|>");

        resumeSim.setOnAction(e -> {
            if(pause == false) putPlanets();
            else{
                for(PathTransition p: paths){
                    p.play();
                }
                pause = false;
            }});
        JFXButton pauseSim=new JFXButton("||");
        pauseSim.setOnAction(e -> {
            for(PathTransition p: paths){
                p.pause();
                
            }
            pause = true;
        });

        resumeSim.getStyleClass().add("jfx-buttonGamePane");
        
        pauseSim.getStyleClass().add("jfx-buttonGamePane");

        toolBox.getChildren().addAll(volver,addPlanet,new Separator(Orientation.VERTICAL),resumeSim,pauseSim);
        return toolBox;
    }
    
    public VBox getPlanetForm(){
       
        VBox systemPlanet=new VBox();
        systemPlanet.setAlignment(Pos.CENTER);
        systemPlanet.setSpacing(20);
        systemPlanet.setPadding(new Insets(10));
        
        VBox subForm=new VBox();
        subForm.setAlignment(Pos.CENTER);
        subForm.setSpacing(20);
        
        Label starLabel=new Label("Datos del planeta");
        
        JFXTextField dato1=new JFXTextField();
        dato1.setPromptText("Masa [Kg]*10^6");
        JFXTextField dato2=new JFXTextField();
        dato2.setPromptText("Diametro [Km]*10^6");
        JFXTextField dato3=new JFXTextField();
        dato3.setPromptText("Distancia a la estrella[Km]*10^6");
        subForm.getChildren().addAll(dato1,dato2,dato3);
        
        systemPlanet.getChildren().addAll(starLabel,subForm);
        
        return systemPlanet;
    }
    

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
    
    public ImageView getEntity(){
        
        File directorio=new File("src/sprites");
        //directorio.mkdirs();
        StringBuilder sb=new StringBuilder();
        LinkedList<String> rutasEncontradas=new LinkedList<>();
        for (File f:directorio.listFiles()){
            //src\sprites\1.png
            rutasEncontradas.add(f.getPath().replace("\\", "/").replace("src", ""));
        }
        
        String rutaSelected=rutasEncontradas.get(rd.nextInt(rutasEncontradas.size()));
        
        InputStream rutaStream=getClass().getResourceAsStream(rutaSelected);
        Image im=new Image(rutaStream,25,25,true,true);
        ImageView img=new ImageView(im);
        
        return img;
    }
    
    public ImageView getEntity(String name){
        InputStream rutaStream=getClass().getResourceAsStream("/sprites/"+name+".jpg");
        Image im=new Image(rutaStream,25,25,true,true);
        ImageView img=new ImageView(im);
        
        return img;
    }
    
    public void putPlanets(){
        double posx = this.origen.getX();
        double posy = this.origen.getY();
        int ref = 50;
        int refy = 25;
        int duration = 2;
        
        for(Planeta i : SistemaSolar.planets){
            PathTransition p = new PathTransition();
            p.setPath(new Ellipse(0,0, 75+ref, 0+refy));
            p.setNode(i.getImage());
            p.setCycleCount(Timeline.INDEFINITE);
            p.setDuration(Duration.seconds(duration));
            p.setAutoReverse(false);
            p.play();
            gameDisplay.getChildren().add(i.getImage());
            ref += 85;
            refy += 60;
            duration += 3;
            paths.add(p);
        }
        
        planetBox.getChildren().clear();
        planetBox.setVisible(false);
    }
    
}
