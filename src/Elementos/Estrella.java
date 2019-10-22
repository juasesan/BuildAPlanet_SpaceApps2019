/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

/**
 *
 * @author User
 */
public class Estrella extends CuerpoCeleste {
    
    public Estrella(String name, double mass, double radius, double angularSpeed, double mediumPower, double temperature){
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.density = mass / (double)Math.pow(radius,3);
        this.angularSpeed = angularSpeed;
        this.mediumPower = mediumPower;
        this.temperature = temperature;
        
    }
}
