package ar.edu.itba.ss.models;

public class Particle {
    private static Long serial_id = Long.valueOf(0);

    private final Long id;
    private final double radius;
    private final double mass;
    private State previousState;
    private State currentState;

    public Particle(double radius, double mass) {
        this.id = serial_id++;
        this.radius = radius;
        this.mass = mass;
        this.previousState = new State();
        this.currentState = new State();
    }

    public Particle(double radius, double mass, State previousState, State currentState) {
        this.id = serial_id++;
        this.radius = radius;
        this.mass = mass;
        this.previousState = previousState;
        this.currentState = currentState;
    }

    public Particle(double radius, double mass, State currentState) {
        this.id = serial_id++;
        this.radius = radius;
        this.mass = mass;
        this.previousState = null;
        this.currentState = currentState;
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
    /**
     * Big particles without radio and with infinite mass for simulating walls.
     */
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

    public void updateState(State newCurrentState){
        this.previousState = this.currentState;
        this.currentState = newCurrentState;
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
}
