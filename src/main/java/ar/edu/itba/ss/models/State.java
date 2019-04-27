package ar.edu.itba.ss.models;

public class State {

    private Double x;
    private Double y;
    private Double vx;
    private Double vy;
    private Double ax;
    private Double ay;

    public State(Double x, Double y, Double vx, Double vy, Double ax, Double ay) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ax = ax;
        this.ay = ay;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getVx() {
        return vx;
    }

    public Double getVy() {
        return vy;
    }

    public Double getAx() {
        return ax;
    }

    public Double getAy() {
        return ay;
    }
}
