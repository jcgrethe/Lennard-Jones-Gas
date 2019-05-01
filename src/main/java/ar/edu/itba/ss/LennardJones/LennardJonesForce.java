package ar.edu.itba.ss.LennardJones;

import ar.edu.itba.ss.models.ForceFunction;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.Vector2D;
import javafx.geometry.Point2D;

import java.util.List;

public class LennardJonesForce implements ForceFunction {
    private Double rm;
    private Double e;

    public LennardJonesForce(Double rm, Double e) {
        this.rm = rm;
        this.e = e;
    }

    @Override
    public Vector2D getForce(Vector2D position, Vector2D velocity, List<Particle> neighbours){
        Double xForce = 0.0, yForce = 0.0;
        Point2D pPoint = new Point2D(position.getX(),position.getY());
        for (Particle neighbour : neighbours) {
            Point2D nPoint = new Point2D(neighbour.getX(),neighbour.getY());
            Point2D distance = pPoint.subtract(nPoint);
            double forceMagnitude = calculateMagnitude(distance.magnitude());
            xForce += forceMagnitude * (distance.getX()) / distance.magnitude();
            yForce += forceMagnitude * (distance.getY()) / distance.magnitude();
        }
        return new Vector2D(xForce, yForce);
    }


    public Vector2D getForce(Particle particle, List<Particle> neighbors){
        return getForce(new Vector2D(particle.getX(), particle.getY()), new Vector2D(), neighbors);
    }

    public Vector2D getForce(Vector2D position, List<Particle> neighbors){
        Vector2D force = getForce(position, new Vector2D(), neighbors);
        return new Vector2D(force.getX(), force.getY());
    }


    private double calculateMagnitude(final double r) {
        return 12 * e * (Math.pow(rm / r, 13) - Math.pow(rm / r, 7)) / rm;
    }
}
