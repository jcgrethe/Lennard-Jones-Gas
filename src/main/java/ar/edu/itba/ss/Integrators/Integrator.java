package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.LennardJones.LennardJonesForce;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;

public abstract class Integrator {
    Double dt;
    LennardJonesForce lennardJonesForce;

    public Integrator(Double dt, LennardJonesForce lennardJonesForce) {
        this.dt = dt;
        this.lennardJonesForce = lennardJonesForce;
    }

    public abstract void moveParticle(Particle particle, Double time, List<Particle> neighbors);

    public Double unidimensionalNextPosition(Particle particle, Double time) {
        moveParticle(particle, time, Collections.emptyList());
        particle.updateState();
        return particle.getY();
    }
}
