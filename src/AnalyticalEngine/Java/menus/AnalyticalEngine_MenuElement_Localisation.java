package AnalyticalEngine.Java.menus;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalyticalEngine_MenuElement_Localisation {
    //Move the Java-side Localisation parser to this class based on MenuElement_HoverElement
    public boolean draw_element = true;
    public int max_height = 0;
    public List<MenuElement_HoverElement_Type> menu_elements = new ArrayList<>();
    public List<MenuElement_HoverElement> processed_menu_elements = new ArrayList<>();

    public AnalyticalEngine_MenuElement_Localisation () {}

    public AnalyticalEngine_MenuElement_Localisation (List<MenuElement_HoverElement_Type> arg0_menu_elements) {
        //Convert from parameters
        List<MenuElement_HoverElement_Type> local_menu_elements = arg0_menu_elements;

        init(local_menu_elements, true);
    }

    public AnalyticalEngine_MenuElement_Localisation (List<MenuElement_HoverElement_Type> arg0_menu_elements, boolean arg1_draw_element) {
        List<MenuElement_HoverElement_Type> local_menu_elements = arg0_menu_elements;
        boolean local_draw_element = arg1_draw_element;

        init(local_menu_elements, local_draw_element);
    }

    public void addLocalisation (List<String> arg0_localisation_array) {
        //Convert from parameters
        List<String> localisation_array = arg0_localisation_array;

        //Declare local instance variables
        List<MenuElement_HoverElement> n_elements = new ArrayList();

        //Parse localisation_array; iterate over all elements in localisation_array
        try {
            for (int i = 0; i < localisation_array.size(); i++) {
                String[] local_split_localisation = localisation_array.get(i)
                    .trim().split("`");
                List<String> local_localisation_elements = new ArrayList<>();
                local_localisation_elements.addAll(Arrays.asList(local_split_localisation));

                //Text formatting trackers
                Color local_colour = Color.WHITE;
                int local_font_weight = CFG.FONT_REGULAR_SMALL;

                for (int x = 0; x < local_localisation_elements.size(); x++) {
                    boolean is_reserved = false;
                    String local_value = local_localisation_elements.get(x);

                    if (local_value.length() > 0) {
                        //1. Colour Handling
                        //Check if local_tooltip aligns with a known colour
                        if (local_value.startsWith("COLOUR<") && local_value.endsWith(">")) {
                            String rgba_values = local_value.substring(local_value.indexOf("<") + 1, local_value.indexOf(">"));
                            String[] rgba_array = rgba_values.split(",\\s*");

                            //Parse the RGB(A) values as floats
                            float r = Float.parseFloat(rgba_array[0])/255.0F;
                            float g = Float.parseFloat(rgba_array[1])/255.0F;
                            float b = Float.parseFloat(rgba_array[2])/255.0F;
                            float a = (rgba_array.length >= 4) ? Float.parseFloat(rgba_array[3]) : 1.0F;

                            local_colour = new Color(r, g, b, a);
                            is_reserved = true;
                        } else {
                            try {
                                Field local_colour_field = Color.class.getField(local_value.toUpperCase());
                                local_colour = (Color) local_colour_field.get(null); //Static field; so pass null as object
                                is_reserved = true;
                            } catch (Exception ex) {}
                        }

                        //2. Font Weight Handling
                        if (local_value.equals("BOLD")) {
                            local_font_weight = CFG.FONT_BOLD;
                            is_reserved = true;
                        } else if (local_value.equals("NORMAL")) {
                            local_font_weight = CFG.FONT_REGULAR_SMALL;
                            is_reserved = true;
                        }

                        //3. Keyword handling
                        if (local_value.equals("RESET")) {
                            local_colour = Color.WHITE;
                            local_font_weight = CFG.FONT_REGULAR_SMALL;
                            is_reserved = true;
                        }

                        //4. Image handling
                        if (local_value.startsWith("IMAGE<") && local_value.endsWith(">")) {
                            String image_file_path = local_value.substring(local_value.indexOf("<") + 1, local_value.indexOf(">"));
                            this.menu_elements.add(new AnalyticalEngine_MenuElement_HoverElement_Type_Image(
                                image_file_path,
                                CFG.PADDING,
                                CFG.PADDING
                            ));
                            is_reserved = true;
                        }

                        //5. Display Text if not reserved
                        if (!is_reserved)
                            this.menu_elements.add(new MenuElement_HoverElement_Type_Text(
                                local_value,
                                local_font_weight,
                                local_colour
                            ));
                    }
                }

                //Push this.menu_elements to hover_element
                this.processed_menu_elements.add(new MenuElement_HoverElement(this.menu_elements));
                this.menu_elements.clear();
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }

    public final void draw (SpriteBatch arg0_oSB, int arg1_pos_x, int arg2_pos_y, float arg3_alpha, int arg4_max_width) {
        //Convert from parameters
        SpriteBatch oSB = arg0_oSB;
        int pos_x = arg1_pos_x;
        int pos_y = arg2_pos_y;
        float alpha = arg3_alpha;
        int max_width = arg4_max_width;

        //Declare local instance variables
        int i = 0;

        //Iterate over all this.menu_elements
        for (int tx = 0; i < this.menu_elements.size(); i++) {
            ((MenuElement_HoverElement_Type) this.menu_elements.get(i)).draw(oSB, pos_x + tx, pos_y, alpha, max_width);
            tx += ((MenuElement_HoverElement_Type) this.menu_elements.get(i)).getWidth();
        }
    }

    public int getHeight () {
        return this.max_height;
    }

    public final int getWidth () {
        //Declare local instance variables
        int out = 0;

        //Iterate over all this.menu_elements
        for (int i = 0; i < this.menu_elements.size(); i++)
            out += ((MenuElement_HoverElement_Type) this.menu_elements.get(i)).getWidth();

        //Return statement
        return out;
    }

    public void init (List<MenuElement_HoverElement_Type> arg0_menu_elements, boolean arg1_draw_element) {
        //Convert from parameters
        List<MenuElement_HoverElement_Type> local_menu_elements = arg0_menu_elements;
        this.draw_element = arg1_draw_element;

        //Declare local instance variables
        this.menu_elements = new ArrayList();

        //Iterate over all menu_elements
        for (int i = 0; i < local_menu_elements.size(); i++) {
            this.menu_elements.add((MenuElement_HoverElement_Type) local_menu_elements.get(i));
            this.max_height = Math.max(this.max_height, ((MenuElement_HoverElement_Type) local_menu_elements.get(i)).getHeight());
        }
    }
}
