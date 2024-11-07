package AnalyticalEngine.Framework.Provinces;
import java.util.*;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.province.Province;

public class Provinces {
    public static HashMap<String, Province> getAllProvinces () {
        //Declare local instance variables
        int number_of_provinces = Game.getProvincesSize();
        HashMap<String, Province> all_provinces = new HashMap<String, Province>();

        //Iterate over all provinces
        for (int i = 0; i < number_of_provinces; i++) {
            Province local_province = Game.getProvince(i);

            if (local_province != null)
                all_provinces.put(Integer.toString(i), local_province);
        }

        //Return statement
        return all_provinces;
    }

    public static Province getProvince (int arg0_province_id) throws Exception {
        return getProvince(Integer.toString(arg0_province_id)); }
    public static Province getProvince (String arg0_province_name) throws Exception {
        //Convert from parameters
        String province_name = arg0_province_name;

        //Declare local instance variables
        int province_id;
        Province province_obj;

        //Try/catch to check for Province names as well
        try {
            //Integer handling
            province_id = Integer.parseInt(arg0_province_name);
            province_obj = Game.getProvince(province_id);

            //Return statement
            return province_obj;
        } catch (NumberFormatException e) {
            //String handling
            HashMap<String, Province> all_provinces = getAllProvinces();

            //Iterate over all_provinces to find a province name match
            for (Province local_province : all_provinces.values())
                if (local_province.getProvinceName().equalsIgnoreCase(province_name)) {
                    province_id = local_province.getProvinceID();
                    province_obj = local_province;

                    //Return statement
                    return province_obj;
                }
        }

        //Throw exception if Province could not be returned.
        throw new Exception("Invalid Province ID/Name: " + arg0_province_name);
    }

    public static float[] getProvinceCentre (String arg0_string) throws Exception {
        return getProvinceCentre(getProvince(arg0_string)); }
    public static float[] getProvinceCentre (Province arg0_province) throws Exception {
        //Convert from parameters
        Province province = arg0_province;

        //Return statement
        return new float[]{
            (float) province.getCenterX(),
            (float) province.getCenterY()
        };
    }

    public static List<String> getProvinceCores (String arg0_string) throws Exception {
        return getProvinceCores(getProvince(arg0_string)); }
    public static List<String> getProvinceCores (Province arg0_province) throws Exception {
        //Convert from parameters
        Province province = arg0_province;

        //Declare local instance variable
        List<Integer> core_ids = new ArrayList();
        List<String> core_tags = new ArrayList();

        //Iterate over all cores and push their Civilisation integer ids
        for (int i = 0; i < province.iCoresSize; i++)
            core_ids.add(province.getCore(i));
        //Iterate over all core_ids and push their Civilisation tags
        for (int i = 0; i < core_ids.size(); i++)
            core_tags.add(Game.getCiv(core_ids.get(i)).getCivTag());

        //Return statement
        return core_tags;
    }
}
