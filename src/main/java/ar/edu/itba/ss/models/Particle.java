package ar.edu.itba.ss.models;

public class Particle {
    private static Long serial_id = Long.valueOf(0);

    private final Long id;
    private final double radius;
    private final double mass;
    State previousState;
    State currentState;


    public Particle(double x, double y, double vX, double vY, double radius, double mass) {
        this.id = serial_id++;
        previousState = new State(x, y, vX, vY, 0, 0);
        this.radius = radius;
        this.mass = mass;
        currentState=previousState;
    }

    public Particle(double radius, double mass, double x, double y, double vX, double vY, double aX, double aY) {
        this.id = serial_id++;
        this.radius = radius;
        this.mass = mass;
        previousState = new State(x, y, vX, vY, aX, aY);
        currentState=previousState;

    }

    /**
     * Big particles without radio and with infinite mass for simulating walls.
     */
    public Particle(long id,double x, double y) {
        this.id = id;
        previousState = new State(x, y, 0, 0, 0, 0);
        this.radius = 0;
        this.mass = Double.POSITIVE_INFINITY;
        currentState=previousState;
    }

    public Long getId() {
        return id;
    }

    public double getX() {
        return previousState.getX();
    }

    public double getY() {
        return previousState.getY();
    }

    public double getvX() {
        return previousState.getvX();
    }

    public double getvY() {
        return previousState.getvY();
    }

    public double getvAngle() {
        return previousState.getvAngle();
    }

    public double getvModule() {
        return previousState.getvModule();
    }

    public double getRadius() {
        return radius;
    }

    public double getMass() {
        return mass;
    }

    public double getaX() {
        return previousState.getaX();
    }

    public double getaY() {
        return previousState.getaY();
    }

    public double getaAngle() {
        return previousState.getaAngle();
    }

    public double getaModule() {
        return previousState.getaModule();
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
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

    class State{
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

        State(double x, double y, double vX, double vY, double aX, double aY) {
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

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getvX() {
            return vX;
        }

        public void setvX(double vX) {
            this.vX = vX;
        }

        public double getvY() {
            return vY;
        }

        public void setvY(double vY) {
            this.vY = vY;
        }

        public double getvAngle() {
            return vAngle;
        }

        public void setvAngle(double vAngle) {
            this.vAngle = vAngle;
        }

        public double getvModule() {
            return vModule;
        }

        public void setvModule(double vModule) {
            this.vModule = vModule;
        }

        public double getaX() {
            return aX;
        }

        public void setaX(double aX) {
            this.aX = aX;
        }

        public double getaY() {
            return aY;
        }

        public void setaY(double aY) {
            this.aY = aY;
        }

        public double getaAngle() {
            return aAngle;
        }

        public void setaAngle(double aAngle) {
            this.aAngle = aAngle;
        }

        public double getaModule() {
            return aModule;
        }

        public void setaModule(double aModule) {
            this.aModule = aModule;
        }
    }

}
