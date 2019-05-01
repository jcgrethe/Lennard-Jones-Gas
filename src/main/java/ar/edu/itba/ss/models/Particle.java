package ar.edu.itba.ss.models;

import ar.edu.itba.ss.Integrators.GearPredictor;

import java.util.Optional;

public class Particle {
    private static Long serial_id = Long.valueOf(0);

    private final Long id;
    private final double radius;
    private final double mass;
    private State previousState;
    private State currentState;
    private State futureState;

    public Particle(double radius, double mass) {
        this.id = serial_id++;
        this.radius = radius;
        this.mass = mass;
        this.previousState = new State();
        this.currentState = new State();
        this.futureState = null;
    }

    public Particle(double radius, double mass, State previousState, State currentState) {
        this.id = serial_id++;
        this.radius = radius;
        this.mass = mass;
        this.previousState = previousState;
        this.currentState = currentState;
        this.futureState = null;
    }

    public Particle(double radius, double mass, State currentState) {
        this.id = serial_id++;
        this.radius = radius;
        this.mass = mass;
        this.previousState = null;
        this.currentState = currentState;
        this.futureState = null;
    }

    /**
     * Big particles without radio and with infinite mass for simulating walls.
     */
    public Particle(long id,double x, double y) {
        this.id = id;
        previousState = new State(x, y, 0, 0, 0, 0);
        currentState=previousState;
        this.radius = 0;
        this.mass = Double.POSITIVE_INFINITY;
    }

    public Particle(double radius, double mass, double x, double y, double vx, double vy) {
        this.id = serial_id++;
        this.radius = radius;
        this.mass = mass;
        previousState = new State(x, y, vx, vy, 0, 0);
        currentState=previousState;
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

    public Long getId() {
        return id;
    }

    public double getRadius() {
        return radius;
    }

    public double getMass() {
        return mass;
    }

    public State getPreviousState() {
        return previousState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setFutureState(State futureState){
        this.futureState = futureState;
    }

    public void updateState(){
        this.previousState = this.currentState;
        this.currentState = this.futureState;
        this.futureState = null;
    }

    public Double getX(){
        return currentState.getX();
    }

    public Double getY(){
        return currentState.getY();
    }

    public Double getvX(){
        return currentState.getvX();
    }

    public Double getvY(){
        return currentState.getvY();
    }

    public Double getaX(){
        return currentState.getaX();
    }

    public Double getaY(){
        return currentState.getaY();
    }

    public Optional<GPState> getGPState(){
        return currentState.getGPState();
    }

    public void setX(double x ){
        this.currentState.setX(x);
    }

    public void setY(double y){
        this.currentState.setY(y);
    }


    public void initializeGPState(Double x, Double y, Double vx, Double vy){
        currentState.setNewGPState(x,y, vx, vy);
    }

}
