package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.LennardJones.LennardJonesForce;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;
import ar.edu.itba.ss.models.Vector2D;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;

public class VelocityVerlet extends Integrator {

    public VelocityVerlet(Double dt, ForceFunction forceFunction) {
        super(dt, forceFunction);
    }

    @Override
    public void moveParticle(Particle particle, Double time, List<Particle> neighbors) {
        Vector2D force = forceFunction.getForce(new Vector2D(particle.getX(),particle.getY()), new Vector2D(particle.getvX(), particle.getvY()),neighbors);
        Double x = particle.getX() + dt*particle.getvX() + dt*dt/particle.getMass()*force.getX();
        Double y = particle.getY() + dt*particle.getvY() + dt*dt/particle.getMass()*force.getY();

        Particle predictedParticle = new Particle(particle.getRadius(), particle.getMass(), x,y, particle.getvX(),particle.getvY());
        Vector2D predictedForce = forceFunction.getForce(new Vector2D(predictedParticle.getX(), predictedParticle.getY()),
                new Vector2D(predictedParticle.getvX(), predictedParticle.getvY()),neighbors);

        Double vX = particle.getvX() + dt*(force.getX() + predictedForce.getX())/(2*particle.getMass());
        Double vY = particle.getvY() + dt*(force.getY() + predictedForce.getY())/(2*particle.getMass());
        // TODO CHECK WHAT IS LAST ELEMENT IN ECUATION "+ O(dt^3)" and "+ O(dt^2)"
        particle.setFutureState(new State(
                x,y,vX,vY
        ));
    }
}
