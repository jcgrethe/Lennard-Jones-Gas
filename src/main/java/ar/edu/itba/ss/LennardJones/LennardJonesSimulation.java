package ar.edu.itba.ss.LennardJones;

import ar.edu.itba.ss.Algorithms.*;
import ar.edu.itba.ss.io.Input;
import ar.edu.itba.ss.models.Grid;
import ar.edu.itba.ss.models.Particle;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class LennardJonesSimulation
{
    public LennardJonesSimulation()
    {
        Particle particle = new Particle(0,0,0,0,0,0);
        Algorithm verletAlgorithm = new Verlet();
        Algorithm BeemanAlgorithm = new Beeman();
        Algorithm GearPredictorAlgorithm = new GearPredictor();
        Input input = new Input(Long.valueOf(100),0.1);
        this.simulate(input,0.1);
    }

    public void simulate(Input input, double dt){

        double time = 0;
        int iteration = 0;
        List<Particle> particles = input.getParticles();

        while (true) {
            Grid grid = new Grid(input.getCellSideQuantity(), input.getSystemSideLength());
            grid.setParticles(particles);
            Map<Particle, List<Particle>> neighbours = NeighborDetection.getNeighbors(grid,grid.getUsedCells(),input.getInteractionRadio(),false);

            for(Map.Entry<Particle,List<Particle>> particle: neighbours.entrySet()){
                move(particle.getKey(),particle.getValue());
            }

            //particles = nextParticles(neighbours);
            //TODO: generate output
            time += dt;
            iteration++;
        }

    }

    public void move(Particle p, List<Particle> neighbors){

    }




}
