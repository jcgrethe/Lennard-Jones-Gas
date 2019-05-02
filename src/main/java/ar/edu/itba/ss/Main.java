package ar.edu.itba.ss;

import ar.edu.itba.ss.Integrators.Beeman;
import ar.edu.itba.ss.Integrators.GearPredictor;
import ar.edu.itba.ss.Integrators.Integrator;
import ar.edu.itba.ss.Integrators.VelocityVerlet;
import ar.edu.itba.ss.LennardJones.LennardJonesForce;
import ar.edu.itba.ss.LennardJones.LennardJonesSimulation;
import ar.edu.itba.ss.OsciladorAmortiguado.OsciladorAmortiguadoSimulation;
import ar.edu.itba.ss.io.Output;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Main {
    /**
     * Main manager of the two projects.
     *
     * @param args  The arguments to manage the program.
     */
    static final double DEFAULT_DT = 0.0001;

    public static void main(String[] args) throws IOException {
        CommandLine cmd = getOptions(args);
        LennardJonesForce l = new LennardJonesForce(1.0,2.0);
        Output.generateXYZFile();
        Output.generateEnergyStadistics();
        Output.generateVelocityStatistics();
        Output.generateParticleStadistics();
        Integrator i= new VelocityVerlet(DEFAULT_DT,l);
        LennardJonesSimulation simulation = new LennardJonesSimulation(DEFAULT_DT,i);
        simulation.simulate(DEFAULT_DT);
    }

    private static CommandLine getOptions(String[] args){


        Options options = new Options();

        Option beeman = new Option("b", "beeman", false, "beeman");
        beeman.setRequired(false);
        options.addOption(beeman);

        Option gear = new Option("g", "gear", false, "gear predictor");
        gear.setRequired(false);
        options.addOption(gear);

        Option verlet = new Option("v", "verlet", false, "verlet predictor");
        verlet.setRequired(false);
        options.addOption(verlet);

        Option quantity = new Option("N", "quantity", true, "quantity(N)");
        quantity.setRequired(false);
        options.addOption(quantity);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd=null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }
        return cmd;
    }
}
