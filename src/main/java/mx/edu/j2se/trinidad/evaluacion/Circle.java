package mx.edu.j2se.trinidad.evaluacion;

import static java.lang.Math.PI;

public class Circle {
    private int radius;

    public Circle(){
        this.radius = 1;
    }

    public Circle(int radius){
        if(radius>0) {
            this.radius = radius;
        }else{
            throw new IllegalArgumentException("invalid radius: " + radius +". Radius must be greater than 0");
        }
    }

    public void setRadius(int radius){
        if(radius>0) {
            this.radius = radius;
        }else{
            throw new IllegalArgumentException("invalid radius " + this.radius);
        }
    }

    public int getRadius(){
        return this.radius;
    }
    public double getArea(){
        return PI*this.radius*this.radius;
    }
}
