/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

/**
 *
 * @author User
 */
public class Planeta extends CuerpoCeleste{
    double distance;
    double pressure;
    ImageView imagen;
    
    public Planeta(double masa, double diametro, double distancia, ImageView i){
        this.mass = masa; this.radius = (float) diametro/2;
        this.distance = distancia; this.imagen = i;
        Tooltip tooltip = new Tooltip("Masa: "+masa+"\nDiametro: "+diametro+"\nDistancia: "+distancia);
        tooltip.getStyleClass().add("tooltip");
        this.imagen.setPickOnBounds(true);
        Tooltip.install(this.imagen, tooltip);
    }
    
    public Planeta(String name, double mass, double radius, 
                    double angularSpeed, double mediumPower, double temperature, 
                    double distance, double pressure){
        
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.density = mass / (double)Math.pow(radius,3);
        this.angularSpeed = angularSpeed;
        this.mediumPower = mediumPower;
        this.temperature = temperature;
        this.distance = distance;
        this.pressure = pressure;
    }
    
    public Planeta(ImageView i){
        this.imagen = i;
    }
    
    public ImageView getImage(){
        return imagen;
    }
}
