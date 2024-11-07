package AnalyticalEngine.Framework.ABRS;

import AnalyticalEngine.Debugger.console;
import AnalyticalEngine.Framework.Datatypes.Map;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.map.MapCities.GameCity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import static AnalyticalEngine.AnalyticalEngine.AnalyticalEngine;

import java.util.ArrayList;

public class EditorSaveLoad {
    public static boolean debug_mode = false; //Used for outputting to test output files before exporting to actual, real files. Used for debugging. False by default.

    //Initialise Json settings
    public static final Json getJson () {
        //Declare local instance variables
        Json json = new Json();

        json.setTypeName((String) null);
        json.setUsePrototypes(false);
        json.setIgnoreUnknownFields(true);
        json.setOutputType(JsonWriter.OutputType.javascript);

        //Return statement
        return json;
    }

    //Load Managers
    public static void loadCities () {
        //Declare local instance variables
        FileHandle cities_file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "cities/cities.json");
        ArrayList<Map.City> main_map_cities = (ArrayList<Map.City>) AnalyticalEngine().main.get("map_cities");
        Json json = new Json();
        ArrayList<JsonValue> raw_array_data = (ArrayList) json.fromJson(ArrayList.class, cities_file);

        for (JsonValue local_value : raw_array_data) {
            GameCity local_city = (GameCity) json.readValue(GameCity.class, local_value);

            if (local_city.p >= 0 && local_city.p < Game.getProvincesSize())
                main_map_cities.add(new Map.City(local_city.x*Game.mapBG.iMapScale, local_city.y*Game.mapBG.iMapScale, local_city.Name, Integer.toString(local_city.p)));
        }

        //Save loaded cities
        AnalyticalEngine().main.put("map_cities", main_map_cities);
    }

    //Save Managers
    public static void saveCities () {
        //Declare local instance variables
        String main_cities_file_path = "map/" + Game.map.getFile_ActiveMap_Path() + "cities/cities.json";
        ArrayList<Map.City> main_map_cities = (ArrayList<Map.City>) AnalyticalEngine().main.get("map_cities");
        Json json = getJson();

        String json_string = json.toJson(main_map_cities);
        FileHandle save_file = (!debug_mode) ?
            FileManager.getSaveType(main_cities_file_path) :
            FileManager.getSaveType("cities.json");

        //Save file
        save_file.writeString(json_string, false);

        console.log((!debug_mode) ?
            "[Editor] EditorSaveLoad.saveCities(): Saved to " + main_cities_file_path :
            "[DEBUGMODE] EditorSaveLoad.saveCities(): Saved to cities.json.");
    }
}
