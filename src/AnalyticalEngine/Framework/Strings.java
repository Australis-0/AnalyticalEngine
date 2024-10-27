package AnalyticalEngine.Framework;

import java.util.ArrayList;
import java.util.List;

public class Strings {
    /**
     * formatList() - Formats a given List<String> into a human-readable String.
     * @param arg0_strings (List<String>) - The list of strings to join into a single String.
     *
     * @return String
     */
    public static String formatList (List<String> arg0_strings) {
        return formatList(arg0_strings, ", ", true); }
    public static String formatList (List<String> arg0_strings, String arg1_delimiter) {
        return formatList(arg0_strings, arg1_delimiter, true ); }
    public static String formatList (List<String> arg0_strings, String arg1_delimiter, boolean arg2_display_and) {
        //Convert from parameters
        List<String> strings = arg0_strings;
        String delimiter = arg1_delimiter;
        boolean display_and = arg2_display_and;

        //Declare local instance variables
        String and_string = (strings.size() == 2) ? " and " : "and ";
        String formatted_list = "";

        for (int i = 0; i < strings.size(); i++)
            formatted_list += strings.get(i)
                + ((i < strings.size() - 1 && strings.size() != 2) ? delimiter : "")
                + ((i == strings.size() - 2 && display_and) ? and_string : "");

        //Return statement
        return formatted_list;
    }
}
