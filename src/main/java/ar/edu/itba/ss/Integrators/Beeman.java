package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.OsciladorAmortiguado.OscillatorForce;
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
        if (forceFunction instanceof OscillatorForce){
            Vector2D previousPosition = new Vector2D(
                particle.getPosition().add(particle.getVelocity().multiply(-dt))
            );
            Vector2D previousAcceleration = new Vector2D(
                forceFunction.getForce(previousPosition, particle.getVelocity(), neighbors).multiply(1.0/particle.getMass())
            );
            Vector2D position = particle.getPosition()
                    .add(particle.getVelocity().multiply(dt))
                    .add(particle.getAcceleration().multiply(2.0/3.0*dt*dt))
                    .add(previousAcceleration.multiply(-1.0/6.0*dt*dt));
            Vector2D predictedVelocity = particle.getVelocity()
                    .add(particle.getAcceleration().multiply(3.0/2.0*dt))
                    .add(previousAcceleration.multiply(-1.0/2.0*dt));
            Vector2D acceleration = forceFunction.getForce(position, predictedVelocity, neighbors).multiply(1.0/particle.getMass());
            Vector2D velocityFinal = particle.getVelocity()
                    .add(acceleration.multiply(1.0/3.0*dt))
                    .add(particle.getAcceleration().multiply(5.0/6.0*dt))
                    .add(previousAcceleration.multiply(-1.0/6.0*dt));

            particle.setFutureState(new State(
                    position.getX(), position.getY(),
                    velocityFinal.getX(), velocityFinal.getY(),
                    acceleration.getX(), acceleration.getY()
            ));
        }else {
            Vector2D force = forceFunction.getForce(particle.getPosition(), particle.getVelocity(), neighbors);
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
}
