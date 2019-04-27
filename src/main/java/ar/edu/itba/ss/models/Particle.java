package ar.edu.itba.ss.models;

public class Particle {
    private static Long serial_id = Long.valueOf(0);

    private final Long id;
    private final double radius;
    private final double mass;

    /**
     * Position
     */
    private double x;
    private double y;

    /**
     * Velocity
     */
    private double vX;
    private double vY;
    private double vAngle;
    private double vModule;

    /**
     * Acceleration
     */
    private double aX;
    private double aY;
    private double aAngle;
    private double aModule;

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
        this.aX = 0;
        this.aY = 0;
        this.aModule = 0;
        this.aAngle = 0;
    }

    public Particle(double radius, double mass, double x, double y, double vX, double vY, double aX, double aY) {
        this.id = serial_id++;
        this.radius = radius;
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.vX = vX;
        this.vY = vY;
        this.aX = aX;
        this.aY = aY;
        this.vModule = Math.hypot(vX, vY);
        this.vAngle = Math.atan(vY/vX);
        this.aModule = Math.hypot(aX, aY);
        this.aAngle = Math.atan(aY/aX);
    }

    /**
     * Big particles without radio and with infinite mass for simulating walls.
     */
    public Particle(long id,double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.vX = 0;
        this.vY = 0;
        this.radius = 0;
        this.mass = Double.POSITIVE_INFINITY;
        this.vModule = Math.hypot(vX, vY);
        this.vAngle = Math.atan(vY/vX);
        this.aX = 0;
        this.aY = 0;
        this.aModule = 0;
        this.aAngle = 0;
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

    public double getaX() {
        return aX;
    }

    public double getaY() {
        return aY;
    }

    public double getaAngle() {
        return aAngle;
    }

    public double getaModule() {
        return aModule;
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

}
