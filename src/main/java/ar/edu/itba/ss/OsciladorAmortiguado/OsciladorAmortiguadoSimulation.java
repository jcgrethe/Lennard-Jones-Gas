package ar.edu.itba.ss.OsciladorAmortiguado;

import ar.edu.itba.ss.Integrators.*;
import ar.edu.itba.ss.io.Input;
import ar.edu.itba.ss.io.Output;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.State;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
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
        List<Double> diferentials = new LinkedList<>();
//        for (double i = 0.000001 ; i < 0.00001 ; i+=0.0000999){
            diferentials.add(0.0001);
//            diferentials.add(0.000001);
//            diferentials.add(0.0000001);


        diferentials.sort(Comparator.comparingDouble(Double::doubleValue));
//        diferentials.sort(Comparator.comparingDouble(Double::doubleValue).reversed());

        double[] beenmanError = new double[diferentials.size()];
        double[] gearPredictorError = new double[diferentials.size()];
        double[] verletError = new double[diferentials.size()];
        double[][] analitycPositions = new double[diferentials.size()][];
        double[][] beenmanPositions = new double[diferentials.size()][];
        double[][] gearPredictorPositions = new double[diferentials.size()][];
        double[][] verletPositions = new double[diferentials.size()][];

        OscillatorForce oscillatorForce = new OscillatorForce(input.getY(), input.getK());

        for (double diferential_t : diferentials){
            int index = diferentials.indexOf(diferential_t);

            analitycPositions[index] = oscillation(new Analityc(diferential_t, oscillatorForce, input.getA(), input.getK(), input.getY()), diferential_t, input.getEndTime(), particle, input);
            beenmanPositions[index] = oscillation(new Beeman(diferential_t, oscillatorForce), diferential_t, input.getEndTime(), particle, input);
            gearPredictorPositions[index] = oscillation(new GearPredictor(diferential_t, oscillatorForce), diferential_t, input.getEndTime(), particle, input);
            verletPositions[index] = oscillation(new VelocityVerlet(diferential_t, oscillatorForce), diferential_t, input.getEndTime(), particle, input);

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

    private static double[] oscillation(Integrator integrator, double dt, double endtime, Particle particle, Input input){
        particle = new Particle(0.0, input.getMass(), new State(
                0.0,input.getInitialX(),0.0,input.getInitialV(),0.0,input.getInitialA()
        ));
        int bins = (int) (endtime/dt);
        double[] positions = new double[bins];
        int t =0;
        for ( ; t < bins ; t++){
            positions[t] = integrator.unidimensionalNextPosition(particle, dt*(t+1));
        }

        return positions;
    }
}
