/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

/**
 *
 * @author mattr
 */
public class SistemaSolar {
    String name;
    public ArrayList<CuerpoCeleste> planets;
    private int planetsMax;
    private boolean stable = true;
    
    public void estabilizar(ArrayList<CuerpoCeleste> planets){
        // cambiar pm acorde las caracteristicas del sistema solar
        
    }
    
    public void crearOrbitas(ArrayList<CuerpoCeleste> planets){
        //creamos las orbitas de acuerdo las caracteristicas de los planetas y sistema solar
        
    }
    
    public void girarOrbita(ImageView i){
        PathTransition p = new PathTransition();
        p.setNode(i);
        p.setCycleCount(Timeline.INDEFINITE);
        p.setDuration(Duration.seconds(4));
        p.setAutoReverse(false);
        p.setPath(new Ellipse(250,150,120,80));
        p.play();
        
        
        
    }
}
