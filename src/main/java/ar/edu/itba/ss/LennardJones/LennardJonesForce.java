package ar.edu.itba.ss.LennardJones;

import ar.edu.itba.ss.models.Particle;

import java.awt.geom.Point2D;
import java.util.List;

public class LennardJonesForce {
    private Double rm;
    private Double e;

    public LennardJonesForce(Double rm, Double e) {
        this.rm = rm;
        this.e = e;
    }

    public Point2D getForce(Particle particle, List<Particle> neighbors){
        Double xForce = 0.0, yForce = 0.0, xDistance, yDistance, distanceMod;

        for (Particle p : neighbors){
            xDistance = Math.abs(particle.getX() - p.getX());
            yDistance = Math.abs(particle.getY() - p.getY());
            distanceMod = Math.sqrt(Math.pow(xDistance,2.0) + Math.pow(yDistance,2.0));

            final Double forceMagnitude = calculateMagnitude(distanceMod);
            xForce += forceMagnitude*xDistance/distanceMod;
            yForce += forceMagnitude*yDistance/distanceMod;
        }

        return new Point2D.Double(xForce, yForce);
    }

    private Double calculateMagnitude(Double r){
        return 12.0*e/rm*( Math.pow(rm/r ,13.0) - Math.pow(rm/r, 7));
    }
}
