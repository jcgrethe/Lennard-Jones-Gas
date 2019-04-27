package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.LennardJones.LennardJonesForce;
import ar.edu.itba.ss.LennardJones.LennardJonesSimulation;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;

import java.awt.geom.Point2D;
import java.util.List;

public class Beeman extends Integrator {
    public Beeman(Double dt, LennardJonesForce lennardJonesForce) {
        super(dt, lennardJonesForce);
    }

    @Override
    public Particle moveParticle(Particle particle, Double time, List<Particle> neighbors) {
        Point2D force = lennardJonesForce.getForce(particle, neighbors);
        Double aX = force.getX()/particle.getMass(),
               aY = force.getY()/particle.getMass();
        Double x = particle.getX() + particle.getvX()*dt + 2.0/3.0*aX*dt*dt-1.0/6.0*particle.getaX()*dt*dt;
        Double y = particle.getY() + particle.getvY()*dt + 2.0/3.0*aY*dt*dt-1.0/6.0*particle.getaY()*dt*dt;
        Particle predictedParticle = new Particle(particle.getRadius(), particle.getMass(), x,y,particle.getvX(),particle.getvY());

        Point2D predictedForce = lennardJonesForce.getForce(predictedParticle, neighbors);
        Double predictedAX = predictedForce.getX()/particle.getMass(),
                predictedAY = predictedForce.getY()/particle.getMass();

        Double vX = particle.getvX() + predictedAX*dt/3.0 + 5.0/6.0*aX-particle.getaX()*dt/6.0;
        Double vY = particle.getvY() + predictedAY*dt/3.0 + 5.0/6.0*aY-particle.getaY()*dt/6.0;

        return new Particle(particle.getRadius(),particle.getMass(),x,y,vX,vY,aX,aY);
    }

    @Override
    public Double nextPosition(Double t) {
        return null;
    }
}
