package AnalyticalEngine.Framework.Civilisations;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.civilization.Civilization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Civilisations {
    public static HashMap<String, Civilization> getAllCivilisations () {
        //Declare local instance variables
        HashMap<String, Civilization> all_civilisations = new HashMap<>();

        //Iterate over all civs defined and get them by tag
        for (int i = 1; i < Game.getCivsSize(); i++) {
            Civilization local_civilisation = Game.getCiv(i);

            all_civilisations.put(local_civilisation.getCivTag(), local_civilisation);
        }

        //Return statement
        return all_civilisations;
    }

    public static Civilization getCivilisation (String arg0_civilisation_name) throws Exception {
        //Convert from parameters
        String civilisation_name = arg0_civilisation_name;

        //Declare local instance variables
        HashMap<String, Civilization> all_civilisations = getAllCivilisations();
        Civilization current_civilisation = all_civilisations.get(civilisation_name);

        if (current_civilisation == null)
            //Iterate over all_civilisations if tag was not specified
            for (Civilization local_civilisation : all_civilisations.values())
                if (local_civilisation.getCivName().equalsIgnoreCase(civilisation_name))
                    current_civilisation = local_civilisation;

        //Return statement
        return current_civilisation;
    }

    public static String getCivilisationsAsString (List<String> arg0_civilisation_tags) throws Exception {
        //Convert from parameters
        List<String> civilisation_tags = new ArrayList();

        //Declare local instance variables
        String civilisation_string = "";

        for (int i = 0; i < civilisation_tags.size(); i++)
            civilisation_string += getCivilisation(civilisation_tags.get(i)).getCivName()
                + ((i < civilisation_tags.size() - 1) ? ", " : "");

        //Return statement
        return civilisation_string;
    }
}
