package ar.edu.itba.ss.LennardJones;

import ar.edu.itba.ss.models.ForceFunction;
import ar.edu.itba.ss.models.Particle;
import ar.edu.itba.ss.models.Vector2D;

import java.util.List;

public class LennardJonesForce implements ForceFunction {
    private Double rm;
    private Double e;

    public LennardJonesForce(Double rm, Double e) {
        this.rm = rm;
        this.e = e;
    }

    @Override
    public Vector2D getForce(Vector2D position, Vector2D velocity, List<Particle> neighbors){
        Double xForce = 0.0, yForce = 0.0, xDistance, yDistance, distanceMod;
        Double x = position.getX();
        Double y = position.getY();

        for (Particle p : neighbors){
            if(p.getId() < 0)
                System.out.println("pared");
            xDistance = p.getX() - x;
            yDistance = p.getY() - y;
            distanceMod = Math.sqrt(Math.pow(xDistance,2.0) + Math.pow(yDistance,2.0));

            final Double forceMagnitude = calculateMagnitude(distanceMod);
            xForce += forceMagnitude* (xDistance/distanceMod);
            yForce += forceMagnitude* (yDistance/distanceMod);
//            if(Math.abs(xForce)>50 || Math.abs(yForce)>50 )
//                System.out.println("error");
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
