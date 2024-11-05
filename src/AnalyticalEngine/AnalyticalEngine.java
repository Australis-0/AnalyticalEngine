package AnalyticalEngine;
import AnalyticalEngine.Debugger.*;
import AnalyticalEngine.Framework.Javascript;

import javax.script.ScriptEngine;

public class AnalyticalEngine {
    public static ScriptEngine nashorn;

    public AnalyticalEngine initialise () {
        console.initialise();
        console.log("Current working directory: " + System.getProperty("user.dir"));

        //Initialise JS
        nashorn = Javascript.initialiseJavascript();

        return this;
    }
}
