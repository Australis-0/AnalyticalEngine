//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusScenarioEditor.Wasteland;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.map.MapScale;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Minimap;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonMain;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Static;
import aoc.kingdoms.lukasz.menus.Dialog;
import aoc.kingdoms.lukasz.menus.Dialog.DialogType;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class ScenarioWastelandContinents extends Menu {
    public ScenarioWastelandContinents() {
        List<MenuElement> menuElements = new ArrayList();
        menuElements.add(new Minimap(0, 0) {
            public int getPosX() {
                return CFG.GAME_WIDTH - this.getWidth();
            }

            public int getPosY() {
                return CFG.GAME_HEIGHT - this.getHeight();
            }
        });
        menuElements.add(new Text_Static(Game.lang.get("SelectRegions"), CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3) {
            public int getPosX() {
                return CFG.GAME_WIDTH - CFG.PADDING * 4 - this.getTextWidth();
            }

            public int getPosY() {
                return ScenarioWastelandContinents.this.getMenuElement(0).getPosY() - CFG.PADDING - this.getHeight();
            }

            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                Images.statsBG.draw2(oSB, this.getPosX() - CFG.PADDING * 3 + iTranslateX, this.getPosY() - CFG.PADDING * 2 + iTranslateY, Images.statsBG.getWidth(), this.getHeight() + CFG.PADDING * 4);
                Images.statsBG2.draw2(oSB, this.getPosX() - CFG.PADDING * 3 + Images.statsBG.getWidth() + iTranslateX, this.getPosY() - CFG.PADDING * 2 + iTranslateY, this.getWidth() + CFG.PADDING * 6 - Images.statsBG.getWidth(), this.getHeight() + CFG.PADDING * 4, true, false);
                super.draw(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Help")));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SelectRegions"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SetWhichRegionsOfTheWorldAreWasteland"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        this.initMenu((MenuTitle)null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
}
