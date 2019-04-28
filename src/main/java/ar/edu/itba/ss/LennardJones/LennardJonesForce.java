package ar.edu.itba.ss.LennardJones;

import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.Vector2D;

import java.awt.geom.Point2D;
import java.util.List;

public class LennardJonesForce {
    private Double rm;
    private Double e;

    public LennardJonesForce(Double rm, Double e) {
        this.rm = rm;
        this.e = e;
    }

    public Point2D getForce(Double x, Double y, List<Particle> neighbors){
        Double xForce = 0.0, yForce = 0.0, xDistance, yDistance, distanceMod;

        for (Particle p : neighbors){
            if(p.getId() < 0)
                System.out.println("pared");
            xDistance = Math.abs(x - p.getX());
            yDistance = Math.abs(y - p.getY());
            distanceMod = Math.sqrt(Math.pow(xDistance,2.0) + Math.pow(yDistance,2.0));

            final Double forceMagnitude = calculateMagnitude(distanceMod);
            xForce += forceMagnitude*xDistance/distanceMod;
            yForce += forceMagnitude*yDistance/distanceMod;
        }

        return new Point2D.Double(xForce, yForce);
    }


    public Point2D getForce(Particle particle, List<Particle> neighbors){
        return getForce(particle.getX(), particle.getY(), neighbors);
    }

    public Vector2D getForce(Vector2D position, List<Particle> neighbors){
        Point2D force = getForce(position.getX(), position.getY(), neighbors);
        return new Vector2D(force.getX(), force.getY());
    }


    private Double calculateMagnitude(Double r){
        return 12.0*e/rm*( Math.pow(rm/r ,13.0) - Math.pow(rm/r, 7));
    }
}
