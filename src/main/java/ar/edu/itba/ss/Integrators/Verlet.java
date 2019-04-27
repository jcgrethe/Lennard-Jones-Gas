package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.models.Particle;

import java.awt.geom.Point2D;
import java.util.List;

public class Verlet extends Integrator {
    public Verlet(Double dt) {
        super(dt);
    }

    @Override
    public Particle moveParticle(Particle particle, Double time, Point2D force) {
        return null;
    }

    @Override
    public Double nextPosition(Double t) {
        return null;
    }
}
