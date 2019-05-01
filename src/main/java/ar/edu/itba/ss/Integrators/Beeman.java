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
            Vector2D previousAcceleration;
            if (time == 0){
                previousAcceleration = new Vector2D(
                    force.getX()/particle.getMass(),
                    force.getY()/particle.getMass()
                );
            }else{
                previousAcceleration = new Vector2D(
                    particle.getPreviousAcceleration().getX(),
                    particle.getPreviousAcceleration().getX()
                );
            }
            Vector2D position = particle.getPosition()
                    .add(particle.getVelocity().multiply(dt))
                    .add(particle.getAcceleration().multiply(2d*dt*dt/3d))
                    .add(previousAcceleration.multiply(-dt*dt/6d));
            Vector2D predictedAcceleration = forceFunction.getForce(
                    new Vector2D(position.getX(), position.getY()), particle.getVelocity(), neighbors)
                    .multiply(1d/particle.getMass());
            Vector2D velocity = particle.getVelocity()
                    .add(predictedAcceleration.multiply(dt/3d))
                    .add(particle.getAcceleration().multiply(5d*dt/6d))
                    .add(previousAcceleration.multiply(-dt/6d));

            particle.setFutureState(new State(
                    position.getX(),position.getY(),
                    velocity.getX(), velocity.getY(),
                    predictedAcceleration.getX(), predictedAcceleration.getY()
            ));
        }
    }
}
