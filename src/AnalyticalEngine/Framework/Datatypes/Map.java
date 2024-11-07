package AnalyticalEngine.Framework.Datatypes;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.province.Province;

import java.util.ArrayList;

import static AnalyticalEngine.AnalyticalEngine.AnalyticalEngine;

public class Map {
    public static class City {
        public String Name; //Name of city
        public float x; //X coordinate
        public float y; //Y coordinate
        public int p; //Province ID

        public City (float arg0_x, float arg1_y, String arg2_city_name, String arg3_province_id) {
            //Convert from parameters
            float x = arg0_x;
            float y = arg1_y;
            String city_name = arg2_city_name;
            int province_id = Integer.parseInt(arg3_province_id);

            //Populate local class
            this.Name = city_name;
            this.x = x/Game.mapBG.iMapScale;
            this.y = y/Game.mapBG.iMapScale;
            this.p = province_id;
        }
    }

    //Editorstate checks
    public static boolean cityExists (Province arg0_province) {
        return cityExists(Integer.toString(arg0_province.getProvinceID())); }
    public static boolean cityExists (String arg0_province_id) {
        //Convert from parameters
        int province_id = Integer.parseInt(arg0_province_id);

        //Declare local instance variables
        ArrayList<Map.City> main_cities = (ArrayList<Map.City>) AnalyticalEngine().main.get("map_cities");

        //Iterate over all cities
        for (int i = 0; i < main_cities.size(); i++) {
            Map.City local_city = main_cities.get(i);

            //Return statement
            if (local_city.p == province_id) return true;
        }

        //Return statement
        return false;
    }
    public static int cityExistsIndexOf (Province arg0_province) {
        return cityExistsIndexOf(Integer.toString(arg0_province.getProvinceID())); }
    public static int cityExistsIndexOf (String arg0_province_id) {
        //Convert from parameters
        int province_id = Integer.parseInt(arg0_province_id);

        //Declare local instance variables
        int index = -1;
        ArrayList<Map.City> main_cities = (ArrayList<Map.City>) AnalyticalEngine().main.get("map_cities");

        //Iterate over all cities
        for (int i = 0; i < main_cities.size(); i++) {
            Map.City local_city = main_cities.get(i);
            if (local_city.p == province_id) index = i;
        }

        //Return statement
        return index;
    }
}
