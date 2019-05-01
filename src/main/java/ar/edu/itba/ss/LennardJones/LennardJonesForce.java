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
            Point2D nPoint = neighbour.point2D();
            Point2D distance = pPoint.subtract(nPoint);
            double forceMagnitude = calculateMagnitude(distance.magnitude());
            xForce += forceMagnitude * (distance.getX()) / distance.magnitude();
            yForce += forceMagnitude * (distance.getY()) / distance.magnitude();
        }
        return new Vector2D(xForce, yForce);
    }

    private double calculateMagnitude(final double r) {
        return ( (12 * e) / rm) * (Math.pow(rm / r, 13) - Math.pow(rm / r, 7)) ;
    }
}
