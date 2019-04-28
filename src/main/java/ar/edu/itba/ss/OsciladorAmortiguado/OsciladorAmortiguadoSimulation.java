package ar.edu.itba.ss.OsciladorAmortiguado;

import ar.edu.itba.ss.Integrators.*;
import ar.edu.itba.ss.LennardJones.LennardJonesForce;
import ar.edu.itba.ss.io.Input;
import ar.edu.itba.ss.io.Output;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Hello world!
 *
 */
public class OsciladorAmortiguadoSimulation {

    private static Particle particle;

    public static void main(String[] args){
        simulate();
    }

    public static void simulate()
    {
        Input input = new Input();
        particle = new Particle(0.0, input.getM(), new State(
                0.0,input.getInitialX(),0.0,input.getInitialV(),0.0,0.0
        ));
        Double[] dts = {0.0001, 0.001, 0.01, 0.1};
        List<Double> diferentials = Arrays.asList(dts);
        diferentials.sort(Comparator.comparingDouble(Double::doubleValue));
//        diferentials.sort(Comparator.comparingDouble(Double::doubleValue).reversed());

        double[] beenmanError = new double[diferentials.size()];
        double[] gearPredictorError = new double[diferentials.size()];
        double[] verletError = new double[diferentials.size()];
        double[][] analitycPositions = new double[diferentials.size()][];
        double[][] beenmanPositions = new double[diferentials.size()][];
        double[][] gearPredictorPositions = new double[diferentials.size()][];
        double[][] verletPositions = new double[diferentials.size()][];

        LennardJonesForce lennardJonesForce = new LennardJonesForce(input.getRm(), input.getEpsilon());

        for (double diferential_t : diferentials){
            int index = diferentials.indexOf(diferential_t);

            analitycPositions[index] = oscillation(new Analityc(diferential_t, lennardJonesForce, input.getA(), input.getK(), input.getY()), diferential_t, input.getEndTime(), particle);
            beenmanPositions[index] = oscillation(new Beeman(diferential_t, lennardJonesForce), diferential_t, input.getEndTime(), particle);
            gearPredictorPositions[index] = oscillation(new GearPredictor(diferential_t, lennardJonesForce), diferential_t, input.getEndTime(), particle);
            verletPositions[index] = oscillation(new VelocityVerlet(diferential_t, lennardJonesForce), diferential_t, input.getEndTime(), particle);

            beenmanError[index] = meanSquaredError(analitycPositions[index], beenmanPositions[index]);
            gearPredictorError[index] = meanSquaredError(analitycPositions[index], gearPredictorPositions[index]);
            verletError[index] = meanSquaredError(analitycPositions[index], verletPositions[index]);
        }
        Output.printOscillationsResults(analitycPositions,beenmanPositions,gearPredictorPositions,verletPositions,beenmanError,gearPredictorError,verletError,diferentials);
    }

    public static double meanSquaredError(double[] one, double[] another) {
        double sum = 0;
        for(int i = 0; i < one.length; i++) {
            sum += Math.pow(one[i] - another[i], 2);
        }
        return sum/one.length;
    }

    private static double[] oscillation(Integrator integrator, double dt, double endtime, Particle particle){
        int bins = (int) (endtime/dt);
        double[] positions = new double[bins];

        for (int t=0 ; t < bins ; t++){
            positions[t] = integrator.unidimensionalNextPosition(particle, dt*t);
        }

        return positions;
    }
}
