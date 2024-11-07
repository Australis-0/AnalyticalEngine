package AnalyticalEngine.Debugger;

import AnalyticalEngine.Framework.Datatypes.Map;
import aoc.kingdoms.lukasz.jakowski.Game;

import java.util.ArrayList;
import java.util.HashMap;

import static AnalyticalEngine.AnalyticalEngine.AnalyticalEngine;

public class Main {
    public static void printMain () {
        //Declare local instance variables
        HashMap<String, Object> main = AnalyticalEngine().main;

        //Print Main
        console.log("Main: " + main);
        console.log("Main size: " + main.size());
    }

    public static void printMainCities () {
        //Declare local instance variables
        ArrayList<Map.City> main_map_cities = (ArrayList<Map.City>) AnalyticalEngine().main.get("map_cities");

        //Print Main
        console.log("Cities: " + main_map_cities.size() + "/" + Game.getProvincesSize() + " Provinces");
    }
}
