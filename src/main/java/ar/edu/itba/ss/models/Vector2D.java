package ar.edu.itba.ss.models;

public class Vector2D {
    private Double x;
    private Double y;

    public Vector2D(){
        this.x = 0.0;
        this.y = 0.0;
    }

    public Vector2D(Double x, Double y) {
        this.x = x.doubleValue();
        this.y = y.doubleValue();
    }

    public Vector2D(Vector2D base){
        this.x = base.getX().doubleValue();
        this.y = base.getY().doubleValue();
    }

    public Vector2D add(Double magnitude){
        this.x += magnitude.doubleValue();
        this.y += magnitude.doubleValue();
        return this;
    }

    public Vector2D multiply(Double magnitude){
        this.x = this.x * magnitude.doubleValue();
        this.y = this.y * magnitude.doubleValue();
        return this;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Vector2D add(Vector2D vector2D){
        this.x += vector2D.getX().doubleValue();
        this.y += vector2D.getY().doubleValue();
        return this;
    }
}
