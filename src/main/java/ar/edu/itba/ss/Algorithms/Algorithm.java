package ar.edu.itba.ss.Algorithms;

import ar.edu.itba.ss.models.Particle;

import java.util.List;

public abstract class Algorithm {
    public abstract Particle moveParticle(Particle particle, List<Particle> neighbors);
    public abstract Double nextPosition(Double t);

}
