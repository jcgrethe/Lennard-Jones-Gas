package ar.edu.itba.ss.LennardJones;

import ar.edu.itba.ss.Algorithms.Algorithm;
import ar.edu.itba.ss.Algorithms.Beeman;
import ar.edu.itba.ss.Algorithms.GearPredictor;
import ar.edu.itba.ss.Algorithms.Verlet;
import ar.edu.itba.ss.models.Particle;

/**
 * Hello world!
 *
 */
public class LennardJonesSimulation
{

    // Default Parameters
    private Double Rm = 1.0;
    private Double epsilon = 2.0;
    private Double m = 0.1;
    private Double initialVelocity = 10.0;
    private Double r = 5.0;
    private Integer boxWidth = 400;
    private Integer boxHeight = 200;
    private Integer middleBoxWidth = boxWidth/2;
    private Integer orificeLength = 10;





    public static void main( String[] args )
    {
        Particle particle = new Particle(0,0,0,0,0,0);
        Algorithm verletAlgorithm = new Verlet();
        Algorithm BeemanAlgorithm = new Beeman();
        Algorithm GearPredictorAlgorithm = new GearPredictor();
    }
}
