package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.LennardJones.LennardJonesForce;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;

import java.awt.geom.Point2D;
import java.util.List;

public abstract class Integrator {
    Double dt;
    LennardJonesForce lennardJonesForce;

    public Integrator(Double dt, LennardJonesForce lennardJonesForce) {
        this.dt = dt;
        this.lennardJonesForce = lennardJonesForce;
    }

    public abstract Particle moveParticle(Particle particle, Double time, List<Particle> neighbors);
    public abstract Double nextPosition(Double t);


}
