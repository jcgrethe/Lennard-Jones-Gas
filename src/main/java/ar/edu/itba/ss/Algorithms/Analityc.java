package ar.edu.itba.ss.Algorithms;

import ar.edu.itba.ss.models.Particle;

import java.util.List;

public class Analityc extends Algorithm{

    private final Integer A = 1;       // TODO: Put real value

    // Default Paramenters
    private final Double M = 70.0;     // In Kilogrames
    private final Integer K = 10000;   // In N/m
    private final Double y = 100.0;    // In kg/s
    private final Double Tf = 5.0;     // In s

    // Initial Conditions
    private final Double initialX = 1.0;          // In m
    private final Double initialV = -A*y/(2*M);   // In m/s
    
    
    @Override
    public void moveParticle(Particle particle, List<Particle> neighbors) {

    }

    @Override
    public Double nextPosition(Double t) {
        return A*Math.exp(-(y/(2*M))*t)*Math.cos(Math.sqrt((K/M)-((y*y)/(4*M*M)))*t);
    }
}
