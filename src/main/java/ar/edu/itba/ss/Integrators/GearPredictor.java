package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.LennardJones.LennardJonesForce;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;

import java.awt.geom.Point2D;
import java.util.List;

public class GearPredictor extends Integrator {
    public GearPredictor(Double dt, LennardJonesForce lennardJonesForce) {
        super(dt, lennardJonesForce);
    }

    @Override
    public void moveParticle(Particle particle, Double time, List<Particle> neighbors) {
    }

    @Override
    public Double nextPosition(Double t) {
        return null;
    }
}
