package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;

import java.awt.geom.Point2D;

public class Beeman extends Integrator {
    public Beeman(Double dt) {
        super(dt);
    }

    @Override
    public Particle moveParticle(Particle particle, Double time, Point2D force) {
        Double aX = force.getX()/particle.getMass(),
               aY = force.getY()/particle.getMass();

        Double x = particle.getX() + particle.getvX()*dt + 2.0/3.0*aX*dt*dt-1.0/6.0*particle.getaX()*dt*dt;
        Double y = particle.getY() + particle.getvY()*dt + 2.0/3.0*aY*dt*dt-1.0/6.0*particle.getaY()*dt*dt;

        Double vX = null;   //TODO
        Double vY = null;   //TODO

        return new Particle(particle.getRadius(),particle.getMass(),x,y,vX,vY,aX,aY);
    }

    @Override
    public Double nextPosition(Double t) {
        return null;
    }
}
