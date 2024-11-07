package AnalyticalEngine;
import AnalyticalEngine.Debugger.*;
import AnalyticalEngine.Framework.ABRS.EditorSaveLoad;
import AnalyticalEngine.Framework.Javascript;
import AnalyticalEngine.Framework.Datatypes.Map;

import javax.script.ScriptEngine;
import java.util.ArrayList;
import java.util.HashMap;

public class AnalyticalEngine {
    private static AnalyticalEngine AnalyticalEngine;
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
        console.initialise();
        console.log("Current working directory: " + System.getProperty("user.dir"));

        //Initialise JS
        nashorn = Javascript.initialiseJavascript();
        initialiseGlobal();

        return this;
    }

    public void initialiseGlobal () {
        //Begin populating main
        main.put("map_cities", new ArrayList<Map.City>());
    }
}
