package ar.edu.itba.ss;

import ar.edu.itba.ss.Integrators.Beeman;
import ar.edu.itba.ss.Integrators.GearPredictor;
import ar.edu.itba.ss.Integrators.Integrator;
import ar.edu.itba.ss.LennardJones.LennardJonesForce;
import ar.edu.itba.ss.LennardJones.LennardJonesSimulation;
import ar.edu.itba.ss.OsciladorAmortiguado.OsciladorAmortiguadoSimulation;
import org.apache.commons.cli.*;

public class Main {
    /**
     * Main manager of the two projects.
     *
     * @param args  The arguments to manage the program.
     */
    static final double DEFAULT_DT = 0.001;

    public static void main(String[] args){
        CommandLine cmd = getOptions(args);
        LennardJonesForce l = new LennardJonesForce(1.0,2.0);
        Integrator i=null;
        if(cmd.getOptionValue('b')!= null)
            i = new Beeman(DEFAULT_DT,l);
        if(cmd.getOptionValue('g')!= null){
            if (i==null)
                i = new GearPredictor(0.001,l);
            else
                throw new IllegalArgumentException();
        }
        if(cmd.getOptionValue('v')!= null){
            if (i==null)
                i = new GearPredictor(DEFAULT_DT,l);
            else
                throw new IllegalArgumentException();
        }
        if (i==null)
            i = new Beeman(DEFAULT_DT, l);
        LennardJonesSimulation simulation = new LennardJonesSimulation(DEFAULT_DT,i);
        simulation.simulate(DEFAULT_DT);
    }

    private static CommandLine getOptions(String[] args){


        Options options = new Options();

        Option staticInput = new Option("b", "beeman", true, "beeman");
        staticInput.setRequired(false);
        options.addOption(staticInput);

        Option dinamicInput = new Option("g", "gear", true, "gear predictor");
        dinamicInput.setRequired(false);
        options.addOption(dinamicInput);

        Option noise = new Option("v", "verlet", true, "verlet predictor");
        noise.setRequired(true);
        options.addOption(noise);

        Option quantity = new Option("N", "quantity", true, "quantity(N)");
        quantity.setRequired(true);
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
