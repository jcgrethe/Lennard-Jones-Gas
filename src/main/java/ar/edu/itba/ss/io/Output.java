package ar.edu.itba.ss.io;
import ar.edu.itba.ss.models.Particle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Output {
    private final static String FILENAME = "output.txt";
    private final static String SIMULATION_FILENAME = "positions.xyz";
    private final static String STATISTICS_FILENAME = "statistics_collisions_per_unit_of_time.csv";
    private final static String OSCILLATION_RESULTS_FILENAME = "oscillation_results.csv";

    private static BufferedWriter simulationBufferedWriter;

    public static void printOscillationsResults(double[][] analitycPositions,
                                                double[][] beemanPositions,
                                                double[][] GearPredictorPositions,
                                                double[][] verletPositions,
                                                double[] beenmanError,
                                                double[] gearPredictorError,
                                                double[] verletError,
                                                List<Double> dts
                                                ){
        System.out.println("Printing...");
        try{
            FileWriter ofw = new FileWriter(OSCILLATION_RESULTS_FILENAME);
            BufferedWriter obufferedw = new BufferedWriter(ofw);
            obufferedw.write(
                    "ECM\\Algoritmo,Beeman,GP,VelocityVerlet"
            );
            obufferedw.newLine();
            for (int i=0 ; i < dts.size() ; i++) {
                obufferedw.write(
                        dts.get(i) + "," +
                        beenmanError[i] + "," +
                        gearPredictorError[i] + "," +
                        verletError[i]
                        );
                obufferedw.newLine();
            }
            for (int i=0 ; i < dts.size() ; i++){
                try {
                    obufferedw.write(
                            dts.get(i).toString()
                    );
                    obufferedw.newLine();
                    obufferedw.write(
                            "Analytic,Beeman,Gear Predictor,VelocityVerlet"
                    );
                    obufferedw.newLine();
                    for (int p=0 ; p < analitycPositions[i].length ; p++){
//                        if (p % 100 == 0){
                            obufferedw.write(
                                    analitycPositions[i][p] + ","+
                                            beemanPositions[i][p] + "," +
                                            GearPredictorPositions[i][p] + ","+
                                            verletPositions[i][p]
                            );
                            obufferedw.newLine();
//                        }
                    }
                }catch (IOException e) {
                }
            }
            obufferedw.flush();
            obufferedw.close();
            ofw.close();

        }catch (IOException e) {
        }
    }


    public static void generateXYZFile(){
        try{
            FileWriter fileWriter = new FileWriter(SIMULATION_FILENAME);
            simulationBufferedWriter = new BufferedWriter(fileWriter);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static void printToFile(List<Particle> particles) throws IOException {
        simulationBufferedWriter.write(String.valueOf(particles.size()));
        simulationBufferedWriter.newLine();
        simulationBufferedWriter.newLine();
        particles.stream().forEach(particle -> {
            try{
                simulationBufferedWriter.write(
                        particle.getId()
                                + " " + particle.getX()
                                + " " + particle.getY()
                                + " " + particle.getvX()
                                + " " + particle.getvY()
                                + " " + particle.getRadius()
                                + " " + particle.getMass()
                                + " " + particle.getaX()
                                + " " + particle.getaY());
                simulationBufferedWriter.newLine();
            }catch (IOException e){
                System.out.println(e);
            }
        });
        simulationBufferedWriter.flush();
    }

    public static void printEnergy(Map<Particle,List<Particle>> particles, Input input){
        double kineticE = 0.0, potencialE = 0.0;
        for (Map.Entry<Particle,List<Particle>> entry : particles.entrySet()){
            kineticE+= kinetic(entry.getKey());
            potencialE+=potencial(entry.getKey(),entry.getValue(),input.getRm(),input.getEpsilon());
        }
        System.out.println("Potencial:"+ potencialE + ", Kinetic:" +kineticE + ", Total: " + (potencialE+kineticE));
    }

    public static double kinetic(Particle p){
        return 0.5 * p.getMass() * Math.pow(p.getCurrentState().getvModule(),2);
    }


    public static double potencial(Particle p, List<Particle> neighbors, double rm, double e){
        double acum= 0.0;
        for(Particle n: neighbors){
            double aux = e * ( Math.pow(rm/getDistances(p,n),12) - 2 * Math.pow(rm/getDistances(p,n),6) );
            acum+= (n.getId()<0)?aux*2:aux;
        }
        return acum;
    }

    private static double getDistances(Particle p1, Particle p2){
        double y = Math.abs(p2.getY() - p1.getY());
        double x = Math.abs(p2.getX() - p1.getX());
        return Math.hypot(y, x);
    }


}
