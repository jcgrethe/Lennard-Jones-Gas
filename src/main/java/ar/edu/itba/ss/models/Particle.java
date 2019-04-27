package ar.edu.itba.ss.models;

import java.util.LinkedList;
import java.util.List;

public class Particle {
    private static Long serial_id = Long.valueOf(0);

    private final Long id;
    private double x;
    private double y;
    private double vX;
    private double vY;
    private double vAngle;
    private double vModule;
    private final double radius;
    private final double mass;
    private List<State> states;

    public Particle(double x, double y, double vX, double vY, double radius, double mass) {
        this.id = serial_id++;
        this.x = x;
        this.y = y;
        this.vX = vX;
        this.vY = vY;
        this.radius = radius;
        this.mass = mass;
        this.vModule = Math.hypot(vX, vY);
        this.vAngle = Math.atan(vY/vX);
        states = new LinkedList<>();
    }

    public void updateMotion(Double vX, Double vY){
        this.vX = vX;
        this.vY = vY;
        this.vModule = Math.hypot(vX, vY);
        this.vAngle = Math.atan(vY/vX);
    }

    public void evolve(Double time){
        this.x += time * this.vX;
        this.y += time * this.vY;
    }

    public Long getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getvX() {
        return vX;
    }

    public double getvY() {
        return vY;
    }

    public double getvAngle() {
        return vAngle;
    }

    public double getvModule() {
        return vModule;
    }

    public double getRadius() {
        return radius;
    }

    public double getMass() {
        return mass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Particle particle = (Particle) o;

        return id.equals(particle.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public List<State> getStates() {
        return states;
    }

    public class State{
        private final Double x;
        private final Double y;

        private final double vx;
        private final double vy;
        private final Double speedModule;
        private Double speedAngle;


        public State(Double x, Double y, double speedModule, double speedAngle) {
            this.x = x;
            this.y = y;
            this.speedAngle = speedAngle;
            this.speedModule = speedModule;
            this.vx = Math.cos(speedAngle) * speedModule;
            this.vy = Math.sin(speedAngle) * speedModule;
        }

        public Double getX() {
            return x;
        }

        public Double getY() {
            return y;
        }

        public Double getSpeedModule() {
            return speedModule;
        }

        public Double getSpeedAngle() {
            return speedAngle;
        }

        public double getVx() {
            return vx;
        }

        public double getVy() {
            return vy;
        }
    }

}
