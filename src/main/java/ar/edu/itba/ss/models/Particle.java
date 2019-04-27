package ar.edu.itba.ss.models;

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
    }

    //wall
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

}
