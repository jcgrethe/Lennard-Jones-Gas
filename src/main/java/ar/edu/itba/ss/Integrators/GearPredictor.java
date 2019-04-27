package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;

import java.awt.geom.Point2D;

public class GearPredictor extends Integrator {

    public GearPredictor(Double dt) {
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
