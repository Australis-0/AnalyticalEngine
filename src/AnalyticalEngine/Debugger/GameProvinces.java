package AnalyticalEngine.Debugger;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.zOther.ReligionRegionsManager;
import aoc.kingdoms.lukasz.map.ReligionManager;
import aoc.kingdoms.lukasz.map.ReligionManager.Religion;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.province.Province;

import AnalyticalEngine.Logic.Scopes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameProvinces {
    public static void printProvince (String arg0_province_id) throws Exception {
        printProvince(arg0_province_id, "true");
    }
    public static void printProvince (String arg0_province_id, String arg1_display_methods) throws Exception {
        //Convert from parameters
        String province_id = arg0_province_id;
        String display_methods = arg1_display_methods;

        //Declare local instance variables
        ArrayList<Object[]> province_string = new ArrayList<Object[]>();
            //[0] - Key string, [1] - Method string, [2] - Value string

        HashMap<String, Object> display_province = new HashMap<String, Object>();
        Province province = Provinces.getProvince(province_id);
        Civilization province_owner = Game.getCiv(province.getCivID());
            String display_province_owner = province_owner.getCivName();
            String display_province_owner_tag = province_owner.getCivTag();
            String display_resource = ResourcesManager.getResourceName(province.getResourceID());
            String display_religion = Game.religionManager.getReligion(province_owner.getReligionID()).Name;

        //Format HashMap
        province_string.add(new Object[]{"Province ID", ".iProvinceID", province.iProvinceID});
        province_string.add(new Object[]{"Province Name", ".getProvinceName()", province.getProvinceName()});
        province_string.add(new Object[]{"Owner", ".getCivID()", display_province_owner + " - (Tag: " + display_province_owner_tag + ", ID: " + province.getCivID() + ")"});
        province_string.add(new Object[]{"- Good", ".getResourceID()", display_resource + " (ID: " + province.getResourceID() + ")"});
        province_string.add(new Object[]{"- Religion", ".getReligionID()", display_religion + " (ID: " + province.getReligion() + ")"});
        province_string.add(new Object[]{"- Population", ".getPopulationTotal()", province.getPopulationTotal()});

        //Print Province information
        console.log("Flags - (arg1) Display methods: " + display_methods);
        for (int i = 0; i < province_string.size(); i++) {
            Object[] local_element = province_string.get(i);
            String local_method_string = (display_methods.equals("true")) ?
                "(" + (String) local_element[1] + "): " : "";

            console.log((String) local_element[0] + ": " + local_method_string + local_element[2]);
        }
    }
}
