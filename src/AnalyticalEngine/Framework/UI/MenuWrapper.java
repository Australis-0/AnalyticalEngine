package AnalyticalEngine.Framework.UI;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu_element.MenuElement;

import java.util.ArrayList;
import java.util.List;

public class MenuWrapper extends Menu {
    public static int OVERLAY_MENU = -1;

    public MenuWrapper () {
        List<MenuElement> menu_elements = new ArrayList();

        /*
        if (this.SCENARIO_CIVILIZATIONS == -1) {
            this.SCENARIO_CIVILIZATIONS = this.addMenu(new ScenarioCivilizations());
            this.SCENARIO_CIVILIZATIONS_LIST = this.addNextMenuToView(this.SCENARIO_CIVILIZATIONS, new ScenarioCivilizationsList());
        } else {
            ((List)this.menus.get(this.SCENARIO_CIVILIZATIONS)).set(this.SCENARIO_CIVILIZATIONS_LIST, new ScenarioCivilizationsList());
        }
         */

        //int current_view_id = Game.menuManager.getViewID();

    }

    public static void addMenu () {

    }
}
