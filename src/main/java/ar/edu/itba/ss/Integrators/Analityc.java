package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.LennardJones.LennardJonesForce;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;

public class Analityc extends Integrator {

    private Double A;
    private Double K;
    private Double y;

    public Analityc(Double dt, LennardJonesForce lennardJonesForce, Double A, Double K, Double y) {
        super(dt, lennardJonesForce);
        this.A = A;
        this.K = K;
        this.y = y;
    }

    @Override
    public void moveParticle(Particle particle, Double time, List<Particle> neighbors) {
        Double M = particle.getMass(), t  = time;
        State newState = new State(
                time, A*Math.exp(-(y/(2*M))*t)*Math.cos(Math.sqrt((K/M)-((y*y)/(4*M*M)))*t),
                particle.getvX(),
                particle.getvY()
        );
        particle.updateState(newState);
    }

    @Override
    public Double unidimensionalNextPosition(Particle particle, Double time) {
        moveParticle(particle, time, Collections.emptyList());
        return particle.getY();
    }

}
