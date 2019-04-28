package ar.edu.itba.ss.models;

public class Vector2D {
    private Double x;
    private Double y;

    public Vector2D(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D base){
        this.x = base.getX();
        this.y = base.getY();
    }

    public Vector2D add(Double magnitude){
        this.x += magnitude;
        this.y += magnitude;
        return this;
    }

    public Vector2D multiply(Double magnitude){
        this.x *= magnitude;
        this.y *= magnitude;
        return this;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Vector2D add(Vector2D vector2D){
        this.x += vector2D.getX();
        this.y += vector2D.getY();
        return this;
    }
}
