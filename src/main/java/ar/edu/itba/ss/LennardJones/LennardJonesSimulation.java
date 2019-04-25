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
    public static void main( String[] args )
    {
        Particle particle = new Particle(0,0,0,0,0,0);
        Algorithm verletAlgorithm = new Verlet();
        Algorithm BeemanAlgorithm = new Beeman();
        Algorithm GearPredictorAlgorithm = new GearPredictor();
    }
}
