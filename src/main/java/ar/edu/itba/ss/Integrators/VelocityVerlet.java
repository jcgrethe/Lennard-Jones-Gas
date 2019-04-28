package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.LennardJones.LennardJonesForce;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;

public class VelocityVerlet extends Integrator {

    public VelocityVerlet(Double dt, LennardJonesForce lennardJonesForce) {
        super(dt, lennardJonesForce);
    }

    @Override
    public void moveParticle(Particle particle, Double time, List<Particle> neighbors) {
        Point2D force = lennardJonesForce.getForce(particle,neighbors);
        Double x = particle.getX() + dt*particle.getvX() + dt*dt/particle.getMass()*force.getX();
        Double y = particle.getY() + dt*particle.getvY() + dt*dt/particle.getMass()*force.getY();

        Particle predictedParticle = new Particle(particle.getRadius(), particle.getMass(), x,y, particle.getvX(),particle.getvY());
        Point2D predictedForce = lennardJonesForce.getForce(predictedParticle,neighbors);

        Double vX = particle.getvX() + dt*(force.getX() + predictedForce.getX())/(2*particle.getMass());
        Double vY = particle.getvY() + dt*(force.getY() + predictedForce.getY())/(2*particle.getMass());
        // TODO CHECK WHAT IS LAST ELEMENT IN ECUATION "+ O(dt^3)" and "+ O(dt^2)"
        particle.updateState(new State(
                x,y,vX,vY
        ));
    }
}
