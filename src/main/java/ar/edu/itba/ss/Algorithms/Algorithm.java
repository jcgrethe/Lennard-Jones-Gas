package ar.edu.itba.ss.Algorithms;

import ar.edu.itba.ss.models.Particle;

import java.util.List;

public abstract class Algorithm {
    public abstract void moveParticle(Particle particle, List<Particle> neighbors);

}
