package AnalyticalEngine;
import AnalyticalEngine.Debugger.*;
import AnalyticalEngine.Framework.Javascript;
import aoc.kingdoms.lukasz.jakowski.AA_Game;

import javax.script.ScriptEngine;
import java.util.ArrayList;
import java.util.HashMap;

public class AnalyticalEngine {
    private static AnalyticalEngine AnalyticalEngine;
    public static AA_Game application;
    public static ScriptEngine nashorn;
    public static HashMap<String, Object> main = new HashMap<>();

    //AnalyticalEngine Settings
    public static boolean keybind_freeze = false;

    public static synchronized AnalyticalEngine AnalyticalEngine () {
        if (AnalyticalEngine == null) AnalyticalEngine = new AnalyticalEngine();

        //Return statement
        return AnalyticalEngine;
    }

    public AnalyticalEngine initialise () {
        console.log("Current working directory: " + System.getProperty("user.dir"));

        //Initialise JS
        nashorn = Javascript.initialiseJavascript();
        initialiseGlobal();

        return this;
    }

    public void initialiseGlobal () {
        //Begin populating main
    }

    public void setApplicationInstance (AA_Game arg0_application) {
        //Convert from parameters
        AA_Game application = arg0_application;

        this.application = application;
    }
}
