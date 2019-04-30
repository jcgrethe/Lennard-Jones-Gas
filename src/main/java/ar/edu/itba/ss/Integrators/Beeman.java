package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.models.ForceFunction;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;
import ar.edu.itba.ss.models.Vector2D;

import java.util.List;

public class Beeman extends Integrator {
    public Beeman(Double dt, ForceFunction forceFunction) {
        super(dt, forceFunction);
    }

    @Override
    public void moveParticle(Particle particle, Double time, List<Particle> neighbors) {
        Vector2D force = forceFunction.getForce(new Vector2D(particle.getX(), particle.getY()), new Vector2D(particle.getvX(), particle.getvY()), neighbors);
        Double aX = force.getX()/particle.getMass(),
               aY = force.getY()/particle.getMass();
        Double x = particle.getX() + particle.getvX()*dt + 2.0/3.0*aX*dt*dt - 1.0/6.0*particle.getaX()*dt*dt;
        Double y = particle.getY() + particle.getvY()*dt + 2.0/3.0*aY*dt*dt-1.0/6.0*particle.getaY()*dt*dt;
//        if(x<0 || y < 0 || x>500 || y> 500)
//            System.out.println("error");

        Particle predictedParticle = new Particle(particle.getRadius(), particle.getMass(), x,y,particle.getvX(),particle.getvY());

        Vector2D predictedForce = forceFunction.getForce(new Vector2D(predictedParticle.getX(),predictedParticle.getY()), new Vector2D(predictedParticle.getvX(), predictedParticle.getvY()), neighbors);
        Double predictedAX = predictedForce.getX()/particle.getMass(),
                predictedAY = predictedForce.getY()/particle.getMass();

        Double vX = particle.getvX() + predictedAX*dt/3.0 + 5.0/6.0*aX-particle.getaX()*dt/6.0;
        Double vY = particle.getvY() + predictedAY*dt/3.0 + 5.0/6.0*aY-particle.getaY()*dt/6.0;


        particle.setFutureState(new State(
                x,y,vX,vY,predictedAX,predictedAY
        ));
    }
}
