package AnalyticalEngine.Framework;

import AnalyticalEngine.Debugger.console;
import com.badlogic.gdx.utils.Json;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Javascript {
    public static ScriptEngine initialiseJavascript () {
        //Declare local instance variables
        List<String> load_directories = new ArrayList<>();
        List<String> load_files = new ArrayList<>();
        ScriptEngineManager nashorn_manager = new ScriptEngineManager();
        ScriptEngine nashorn = nashorn_manager.getEngineByName("nashorn");

        //Load files as stated in js_file_structure.json
        String file_path = "/AnalyticalEngine/js_file_structure.json";
        Json file_structure_json = new Json();
        HashMap<String, Object> file_structure_obj = null;

        //Use class loader to load file from resources
        try (InputStream input_stream = Javascript.class.getResourceAsStream(file_path)) {
            if (input_stream == null)
                System.err.println("JSON File for loading JS not found: " + file_path);

            //Wrap input_stream with InputStreamReader
            Reader input_reader = new InputStreamReader(input_stream);
            file_structure_obj = file_structure_json.fromJson(HashMap.class, input_reader);
            input_reader.close();

            console.log("File Structure Object Directories:" + file_structure_obj.get("directories"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Return statement
        return nashorn;
    }
}
