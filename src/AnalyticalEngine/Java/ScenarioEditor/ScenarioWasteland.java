//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusScenarioEditor.Wasteland;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRegionsManager;
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
import aoc.kingdoms.lukasz.menusEditor.GameCivs;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class ScenarioWasteland extends Menu {
    public static boolean bSetWasteland_AvailableProvinces = true;
    public static List<Undo> lUndo = new ArrayList();

    public ScenarioWasteland() {
        List<MenuElement> menuElements = new ArrayList();
        menuElements.add(new Minimap(0, 0) {
            public int getPosX() {
                return CFG.GAME_WIDTH - this.getWidth();
            }

            public int getPosY() {
                return CFG.GAME_HEIGHT - this.getHeight();
            }
        });
        menuElements.add(new Text_Static(Game.lang.get("CustomizeWasteland"), CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3) {
            public int getPosX() {
                return CFG.GAME_WIDTH - CFG.PADDING * 4 - this.getTextWidth();
            }

            public int getPosY() {
                return ScenarioWasteland.this.getMenuElement(0).getPosY() - CFG.PADDING - this.getHeight();
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
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CustomizeWasteland"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("SetWhichProvincesOfTheWorldAreWasteland"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
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

    public static void actionUpdateData(boolean addUndo) {
        if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
            if (Game.getProvince(Game.iActiveProvince).isCapital && bSetWasteland_AvailableProvinces) {
                return;
            }

            if (addUndo) {
                addUndo(Game.iActiveProvince);
            }

            if (bSetWasteland_AvailableProvinces && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                int tCivID = Game.getProvince(Game.iActiveProvince).getCivID();
                Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).removeProvince(Game.iActiveProvince);
                Game.getProvince(Game.iActiveProvince).setCivID_Just(0);
                CivilizationRegionsManager.buildCivilizationsRegion(tCivID);
            }

            Game.getProvince(Game.iActiveProvince).setWasteland(bSetWasteland_AvailableProvinces ? 1 : -1);
            Game.updateProvincesInView = true;
            CivilizationRegionsManager.updateRegionsInView = true;
        }

    }

    private static final void addUndo(int nProvinceID) {
        if (nProvinceID >= 0) {
            if (Game.getProvince(nProvinceID).getWasteland() >= 0 != bSetWasteland_AvailableProvinces) {
                if (lUndo.size() > 50) {
                    lUndo.remove(0);
                }

                lUndo.add(new Undo(nProvinceID, Game.getProvince(nProvinceID).getWasteland()));
            }

        }
    }

    protected static void popUndo() {
        if (lUndo.size() > 0) {
            Game.iActiveProvince = ((Undo)lUndo.get(lUndo.size() - 1)).iProvinceID;
            bSetWasteland_AvailableProvinces = ((Undo)lUndo.get(lUndo.size() - 1)).iState >= 0;
            actionUpdateData(false);
            if (!Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                Game.mapCoords.centerToProvinceID(Game.iActiveProvince);
            }

            lUndo.remove(lUndo.size() - 1);
        }

    }

    public static class Undo {
        public int iProvinceID;
        public int iState;

        public Undo(int iProvinceID, int iState) {
            this.iProvinceID = iProvinceID;
            this.iState = iState;
        }
    }
}
