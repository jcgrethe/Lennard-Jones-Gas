package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;

import java.awt.geom.Point2D;

public abstract class Integrator {
    Double dt;

    public Integrator(Double dt) {
        this.dt = dt;
    }

    public abstract Particle moveParticle(Particle particle, Double time, Point2D force);
    public abstract Double nextPosition(Double t);


}
