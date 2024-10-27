package AnalyticalEngine.Debugger;

public class Game {
    public static void exit () { exit(null); }
    public static void exit (String arg0_exit_code) {
        //Convert from parameters
        int exit_code = (arg0_exit_code != null) ? Integer.parseInt(arg0_exit_code) : 0;

        //Exit program
        console.log("Process closed. Exit code: " + exit_code + ".");
        System.exit(exit_code);
        Runtime.getRuntime().halt(exit_code);
    }
}
