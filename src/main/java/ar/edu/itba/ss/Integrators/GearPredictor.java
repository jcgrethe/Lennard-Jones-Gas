package ar.edu.itba.ss.Integrators;

import ar.edu.itba.ss.LennardJones.LennardJonesForce;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;
import ar.edu.itba.ss.models.Vector2D;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Vector;

public class GearPredictor extends Integrator {
    private final Integer[] periodicNumbers = {1, 1, 2, 6, 24, 120};

    private Double correctFactor0;
    private Double correctFactor1;
    private Double correctFactor2;
    private Double correctFactor3;
    private Double correctFactor4;
    private Double correctFactor5;

    public GearPredictor(Double dt, LennardJonesForce lennardJonesForce) {
        super(dt, lennardJonesForce);
        this.correctFactor0 = 3d / 16d;
        this.correctFactor1 = (251d / 360d) / dt;
        this.correctFactor2 = 1d * (2d / Math.pow(dt,2));
        this.correctFactor3 = (11d / 18d) * (6d / Math.pow(dt,3));
        this.correctFactor4 = (1d / 6d) * (24d / Math.pow(dt,4));
        this.correctFactor5 = (1d / 60d) * (120d / Math.pow(dt,5));
    }

    @Override
    public void moveParticle(Particle particle, Double time, List<Particle> neighbors) {
        if (particle.getGPState().isPresent()){
            GPState gpState = particle.getGPState().get();

            //Predict
            GPState predictedGPState = new GPState(
                    getR(gpState),
                    getR1(gpState),
                    getR2(gpState),
                    getR3(gpState),
                    getR4(gpState),
                    getR5(gpState)
            );

            //Evaluate
            Vector2D force = lennardJonesForce.getForce(predictedGPState.getR(), neighbors);
            Vector2D acceleration = force.multiply(1.0/particle.getMass());
            Vector2D deltaAcceleration = gpState.getR2().add(predictedGPState.getR2());
            Vector2D deltaR2 = deltaAcceleration.multiply(dt*dt/periodicNumbers[2]);

            //Correct
            GPState correctedGPState = new GPState(
                    predictedGPState.getR().add(deltaR2.multiply(correctFactor0)),
                    predictedGPState.getR1().add(deltaR2.multiply(correctFactor1)),
                    predictedGPState.getR2().add(deltaR2.multiply(correctFactor2)),
                    predictedGPState.getR3().add(deltaR2.multiply(correctFactor3)),
                    predictedGPState.getR4().add(deltaR2.multiply(correctFactor4)),
                    predictedGPState.getR5().add(deltaR2.multiply(correctFactor5))
            );
            State newParticleState = new State(
              correctedGPState.getR().getX(),
              correctedGPState.getR().getY(),
              correctedGPState.getR1().getX(),
              correctedGPState.getR1().getY(),
              correctedGPState.getR2().getX(),
              correctedGPState.getR2().getY()
            );

            // Finally, update the new state
            particle.updateState(newParticleState);
        }else {
            throw new IllegalStateException("No GPState founded, could not predict new position.");
        }
    }

    private Vector2D getR(GPState gpState){
        return new Vector2D(gpState.getR())
                .add(gpState.getR1().multiply(Math.pow(dt,1)/periodicNumbers[1]))
                .add(gpState.getR2().multiply(Math.pow(dt,2)/periodicNumbers[2]))
                .add(gpState.getR3().multiply(Math.pow(dt,3)/periodicNumbers[3]))
                .add(gpState.getR4().multiply(Math.pow(dt,4)/periodicNumbers[4]))
                .add(gpState.getR5().multiply(Math.pow(dt,5)/periodicNumbers[5]));
    }

    private Vector2D getR1(GPState gpState){
        return new Vector2D(gpState.getR1())
                .add(gpState.getR2().multiply(Math.pow(dt,1)/periodicNumbers[1]))
                .add(gpState.getR3().multiply(Math.pow(dt,2)/periodicNumbers[2]))
                .add(gpState.getR4().multiply(Math.pow(dt,3)/periodicNumbers[3]))
                .add(gpState.getR5().multiply(Math.pow(dt,4)/periodicNumbers[4]));
    }

    private Vector2D getR2(GPState gpState){
        return new Vector2D(gpState.getR2())
                .add(gpState.getR3().multiply(Math.pow(dt,1)/periodicNumbers[1]))
                .add(gpState.getR4().multiply(Math.pow(dt,2)/periodicNumbers[2]))
                .add(gpState.getR5().multiply(Math.pow(dt,3)/periodicNumbers[3]));
    }

    private Vector2D getR3(GPState gpState){
        return new Vector2D(gpState.getR3())
                .add(gpState.getR4().multiply(Math.pow(dt,1)/periodicNumbers[1]))
                .add(gpState.getR5().multiply(Math.pow(dt,2)/periodicNumbers[2]));
    }

    private Vector2D getR4(GPState gpState){
        return new Vector2D(gpState.getR4())
                .add(gpState.getR5().multiply(Math.pow(dt,1)/periodicNumbers[1]));
    }

    private Vector2D getR5(GPState gpState){
        return new Vector2D(gpState.getR5());
    }

    public class GPState{
        private Vector2D r;
        private Vector2D r1;
        private Vector2D r2;
        private Vector2D r3;
        private Vector2D r4;
        private Vector2D r5;

        public GPState(Vector2D r, Vector2D r1, Vector2D r2, Vector2D r3, Vector2D r4, Vector2D r5) {
            this.r = r;
            this.r1 = r1;
            this.r2 = r2;
            this.r3 = r3;
            this.r4 = r4;
            this.r5 = r5;
        }

        public Vector2D getR() {
            return r;
        }

        public Vector2D getR1() {
            return r1;
        }

        public Vector2D getR2() {
            return r2;
        }

        public Vector2D getR3() {
            return r3;
        }

        public Vector2D getR4() {
            return r4;
        }

        public Vector2D getR5() {
            return r5;
        }
    }
}
