package ar.edu.itba.ss.OsciladorAmortiguado;

import ar.edu.itba.ss.models.ForceFunction;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.Vector2D;

import java.util.List;

public class OscillatorForce implements ForceFunction {
    private Double y;
    private Double k;

    public OscillatorForce(Double y, Double k) {
        this.k = k;
        this.y = y;
    }

    @Override
    public Vector2D getForce(Vector2D position, Vector2D velocity, List<Particle> neighbors) {
        return new Vector2D(position).multiply(-k).add(velocity.multiply(-y));
    }
}
