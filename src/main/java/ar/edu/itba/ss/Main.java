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
        LennardJonesForce l = new LennardJonesForce(1.0,2.0);
        CommandLine opt = getOptions(args);
        double dt = DEFAULT_DT;
        if (!opt.getOptionValue('t').isEmpty())
            dt= Double.valueOf(opt.getOptionValue('t'));
        Output.generateXYZFile();
        Output.generateEnergyStadistics();
        Output.generateVelocityStatistics();
        Output.generateParticleStadistics();
        Integrator i= new VelocityVerlet(dt,l);
        LennardJonesSimulation simulation = new LennardJonesSimulation(dt,i);
        simulation.simulate(dt);
    }

    private static CommandLine getOptions(String[] args){


        Options options = new Options();

        Option time = new Option("t", "dt", true, "dt");
        time.setRequired(false);
        options.addOption(time);

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
