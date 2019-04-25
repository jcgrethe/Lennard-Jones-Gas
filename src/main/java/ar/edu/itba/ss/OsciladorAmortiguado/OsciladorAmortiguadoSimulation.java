package ar.edu.itba.ss.OsciladorAmortiguado;

import ar.edu.itba.ss.Algorithms.Algorithm;
import ar.edu.itba.ss.Algorithms.Beeman;
import ar.edu.itba.ss.Algorithms.GearPredictor;
import ar.edu.itba.ss.Algorithms.Verlet;
import ar.edu.itba.ss.models.Particle;

/**
 * Hello world!
 *
 */
public class OsciladorAmortiguadoSimulation {

    private Integer A = 1;       // TODO: Put real value

    // Default Paramenters
    private Double M = 70.0;     // In Kilogrames
    private Integer K = 10000;   // In N/m
    private Double y = 100.0;    // In kg/s
    private Double Tf = 5.0;     // In s

    // Initial Conditions
    private Double initialX = 1.0;          // In m
    private Double initialV = -A*y/(2*M);   // In m/s

    public static void main( String[] args )
    {
        Particle particle = new Particle(0,0,0,0,0,0);
        Algorithm verletAlgorithm = new Verlet();
        Algorithm BeemanAlgorithm = new Beeman();
        Algorithm GearPredictorAlgorithm = new GearPredictor();
        System.out.println( "Hello World!" );
    }
}
