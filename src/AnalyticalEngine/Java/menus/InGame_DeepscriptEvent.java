package AnalyticalEngine.Java.menus;

import aoc.kingdoms.lukasz.events.EventsManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Missions.MissionTree;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamAchievementsManager;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitleIMG_DoubleText;
import aoc.kingdoms.lukasz.menu_element.Empty;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonGame_Value;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.*;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Desc;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.script.Invocable;
import javax.script.ScriptException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static AnalyticalEngine.AnalyticalEngine.nashorn;

public class InGame_DeepscriptEvent extends Menu {
    public static int animation_time = 60;
    public static String event_id = "default_event";
    public static String event_type = "civ_event";
    public int image_height = 1;
    public int image_width = 1;
    public static long time = 0L;

    public static String description = "";
    public static String name = "MISSING_LOC";

    boolean has_province_id = false;
    public static int mission_id = 0;
    public static int province_id = -1;

    List<MenuElement> menu_elements = new ArrayList();
    List<MenuElement> menu_options_elements = new ArrayList(); //Internal helper.
        public int button_x = 0;
        public int button_y = 0;
        public int menu_width = 0;
        public int menu_x = 0;
        public int menu_y = 0;
        public int padding_left = 0;
        public int title_height = 0;

    /**
     * InGame_DeepscriptEvent() - Constructor. Defines the initial event ID and type to display. Does not render the interface.
     * @param {String} arg0_event_id
     * @param {String} arg1_event_type - Either 'civ_event', 'mission_event', or 'province_event'.
     */
    public InGame_DeepscriptEvent (String arg0_event_id, String arg1_event_type) {
        //Convert from parameters
        String event_id = arg0_event_id;
        String event_type = arg1_event_type;

        //1. Set metadata
        //Set .event_id, .event_type
        this.event_id = event_id;
        this.event_type = event_type;

        //2. Set scope default
        //Set .province_id default
        this.province_id = Game.getCiv(Game.player.iCivID).eventProvinceID;
    }

    public void addOption (String arg0_option_id, String arg1_name, List<String> arg2_tooltip_localisation_strings) {
        //Convert from parameters
        String option_id = arg0_option_id;
        String option_name = arg1_name;
        List<String> tooltip_localisation_strings = arg2_tooltip_localisation_strings;

        //Invoke addOption()
        addOption(option_id, option_name, tooltip_localisation_strings, false);
    }

    /**
     * addOption() - Adds an option to the event prior to rendering. Does not render the interface. Fires setGlobalVariable() for global.event_click_id: (String) and global.event_click_option: (String)
     * @param {String} arg0_option_id
     * @param {String} arg1_name
     * @param {List<String>} arg2_tooltip_localisation_strings
     */
    public void addOption (String arg0_option_id, String arg1_name, List<String> arg2_tooltip_localisation_strings, boolean arg3_disable_option) {
        //Convert from parameters
        String option_id = arg0_option_id;
        String option_name = arg1_name;
        List<String> tooltip_localisation_strings = arg2_tooltip_localisation_strings;
        boolean disable_option = arg3_disable_option;

        //Declare local instance variables
        Invocable invocable = (Invocable) nashorn;

        try {
            this.menu_options_elements.add(new ButtonGame_Value(Game.lang.get(option_name), CFG.FONT_REGULAR, -1, this.padding_left, this.button_y, this.menu_width - this.padding_left*2, (!disable_option), this.menu_options_elements.size()) {
                public void actionElement() {
                    //Send message to Nashorn that this option has been successfully clicked
                    try {
                        invocable.invokeFunction("eventOptionHandler", event_id, option_id);
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                    if (!disable_option) {
                        if (event_type == "mission_event") {
                            MissionTree.takeMissionDecision(Game.player.iCivID, mission_id, this.getCurrent());
                        } else {
                            Game.player.currSituation.updateCurrentSituation();
                        }

                        Game.menuManager.rebuildInGame_Right();
                        Game.menuManager.setVisibleInGame_Event(false);
                        SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.EVENT_RES);
                    }
                }

                public void buildElementHover () {
                    AnalyticalEngine_MenuElement_Localisation localisation_element = new AnalyticalEngine_MenuElement_Localisation();

                    localisation_element.addLocalisation(tooltip_localisation_strings);
                    this.menuElementHover = new MenuElement_Hover(localisation_element.processed_menu_elements, (localisation_element.processed_menu_elements.size() == 1));
                }
            });
            this.button_y += ((MenuElement) menu_options_elements.get(menu_options_elements.size() - 1)).getHeight() + CFG.PADDING;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * draw() - Internal helper function. Parameters are provided via instantiation by default.
     * @param {SpriteBatch} arg0_oSB
     * @param {int} arg1_translate_x
     * @param {int} arg2_translate_y
     * @param {boolean} arg3_menu_is_active
     * @param {Status} arg4_title_status
     */
    public void draw (SpriteBatch arg0_oSB, int arg1_translate_x, int arg2_translate_y, boolean arg3_menu_is_active, Status arg4_title_status) {
        //Convert from parameters
        SpriteBatch oSB = arg0_oSB;
        int translate_x = arg1_translate_x;
        int translate_y = arg2_translate_y;
        boolean menu_is_active = arg3_menu_is_active;
        Status title_status = arg4_title_status;

        //1. Draw Event
        if (this.time + 60L >= CFG.currentTimeMillis)
            translate_y = translate_y - CFG.BUTTON_HEIGHT + (int) ((float) CFG.BUTTON_HEIGHT*((float) (CFG.currentTimeMillis - this.time)/60.0F));
        Renderer.drawBoxCorner(oSB, this.getPosX() + translate_x, this.getPosY() - this.getTitle().getHeight() + translate_y, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + translate_x, this.getPosY() + translate_y, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        oSB.setColor(Color.WHITE);

        //2. Set eventIMG
        try {
            EventsManager.eventIMG.draw(oSB, this.getPosX() + Images.boxTitleBORDERWIDTH + translate_x, this.getPosY() + translate_y, this.image_width, this.image_height);
            Renderer.drawBox(oSB, Images.eventCorner, this.getPosX() + Images.boxTitleBORDERWIDTH + translate_x, this.getPosY() + translate_y, this.image_width, this.image_height, 1.0F);
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + Images.boxTitleBORDERWIDTH + translate_x, this.getPosY() + this.image_height + translate_y, this.image_width, CFG.PADDING*2, false, true);
            oSB.setColor(Color.WHITE);
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        //3. Call super.draw()
        super.draw(oSB, translate_x, translate_y, menu_is_active, title_status);
    }

    /**
     * loadDefaultEventImage() - Internal helper function. Loads a default event image.
     */
    public void loadDefaultEventImage () {
        //Load default event image
        EventsManager.eventIMG = new Image(
            ImageManager.loadTexture_RGB888("game/events/images/" + CFG.getRescouresPath_Short() + "default.png"),
            Texture.TextureFilter.Linear,
            Texture.TextureWrap.ClampToEdge
        );
    }

    /**
     * loadEventImage() - Loads an event image into the present cache based upon its file path.
     * @param {String} arg0_file_path
     */
    public void loadEventImage (String arg0_file_path) {
        //Convert from parameters
        String file_path = arg0_file_path;

        //Load file_path as an event image into path.
        if (!file_path.equals(EventsManager.loadedEventIMG)) {
            //1. Make sure EventsManager.eventIMG is currently disposed of
            if (EventsManager.eventIMG != null) {
                EventsManager.eventIMG.dispose();
                EventsManager.eventIMG = null;
            }

            //2. Attempt to load the specified image with a default fallback
            try {
                if (FileManager.loadFile(file_path).exists()) {
                    EventsManager.eventIMG = new Image(
                        ImageManager.loadTexture_RGB888(file_path),
                        Texture.TextureFilter.Linear,
                        Texture.TextureWrap.ClampToEdge
                    );
                } else {
                    loadDefaultEventImage();
                }
            } catch (Exception ex) {
                loadDefaultEventImage();
                CFG.exceptionStack(ex);
            }
        }
    }

    public void setDescription (String arg0_string) {
        //Convert from parameters
        String description_string = arg0_string;

        //Set .description
        this.description = description_string;
    }

    public void setMissionID (int arg0_mission_id) {
        //Convert from parameters
        int mission_id = arg0_mission_id;

        //Set .mission_id
        this.mission_id = mission_id;
    }

    public void setName (String arg0_string) {
        //Convert from parameters
        String name_string = arg0_string;

        //Set .name
        this.name = name_string;
    }

    public void setProvinceID (int arg0_province_id) {
        //Convert from parameters
        int province_id = arg0_province_id;

        //Set .has_province_id, .province_id
        this.has_province_id = true;
        this.province_id = province_id;
    }

    public void render () {
        //Declare local instance variables
        this.button_x = Images.boxTitleBORDERWIDTH;
        this.button_y = CFG.PADDING*2;
        this.menu_width = ImageManager.getImage(Images.title600).getWidth();
        this.menu_x = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        this.menu_y = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING*2 + ImageManager.getImage(Images.title600).getHeight();
        this.padding_left = CFG.PADDING*2 + Images.boxTitleBORDERWIDTH;
        this.title_height = ImageManager.getImage(Images.title600).getHeight();

        //Set .image_height, .image_width
        try {
            float f_scale = (float) (menu_width - Images.boxTitleBORDERWIDTH*2)/(float) EventsManager.eventIMG.getWidth();
            this.image_height = (int) ((float) EventsManager.eventIMG.getHeight()*f_scale);
            this.image_width = menu_width - Images.boxTitleBORDERWIDTH*2;
            this.button_y += this.image_height;
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        //Add .description
        this.menu_elements.add(new Text_Desc(
            Game.lang.get(this.description),
            padding_left,
            button_y,
            menu_width - padding_left*2
        ));
        button_y += ((MenuElement) menu_elements.get(menu_elements.size() - 1)).getHeight();

        //Iterate over menu_options_elements and add them to the present this.menu_elements.
        for (int i = 0; i < this.menu_options_elements.size(); i++) {
            this.menu_options_elements.get(i).setPosY(button_y);
            this.menu_options_elements.get(i).setWidth(menu_width);
            this.menu_elements.add(this.menu_options_elements.get(i));

            button_y += ((MenuElement) this.menu_elements.get(menu_elements.size() - 1)).getHeight() + CFG.PADDING;
        }

        //Reset button_y; i; iterate over menu_elements and adjust button_y; i
        button_y = 0;
        int i = 0;

        for (int i_size = menu_elements.size(); i < i_size; i++)
            if (button_y < ((MenuElement) menu_elements.get(i)).getPosY() + ((MenuElement) menu_elements.get(i)).getHeight() + CFG.PADDING*2)
                button_y = ((MenuElement) menu_elements.get(i)).getPosY() + ((MenuElement) menu_elements.get(i)).getHeight() + CFG.PADDING*2;
        i = Math.min(button_y, CFG.GAME_HEIGHT - menu_y - CFG.PADDING*2);

        //Add Empty padding
        this.menu_elements.add(new Empty(0, 0, menu_width, Math.max(button_y, i)));

        if (!this.has_province_id) {
            if (province_id < 0 && Game.getCiv(Game.player.iCivID).getCapitalProvinceID() >= 0) {
                province_id = Game.getCiv(Game.player.iCivID).getCapitalProvinceID();
            } else if (Game.getCiv(Game.player.iCivID).getNumOfProvinces() > 0) {
                province_id = Game.getCiv(Game.player.iCivID).getProvinceID(Game.oR.nextInt(Game.getCiv(Game.player.iCivID).getNumOfProvinces()));
            }
            province_id = Math.max(0, province_id);
        }

        //Init menu
        this.initMenu(new MenuTitleIMG_DoubleText(Game.lang.get(this.name), Game.lang.get("EventInX", Game.getProvince(province_id).getProvinceName()), true, false, Images.title600) {
            public long getTime () {
                return InGame_DeepscriptEvent.time;
            }
        }, CFG.GAME_WIDTH/2 - menu_width/2, CFG.GAME_HEIGHT/5, menu_width, i, menu_elements, false, true);
        this.drawScrollPositionAlways = false;
    }

    public void setVisible (boolean arg0_visible) {
        //Convert from parameters
        boolean visible = arg0_visible;

        //Toggle event visibility
        super.setVisible(visible);
        this.time = CFG.currentTimeMillis;
    }
}
