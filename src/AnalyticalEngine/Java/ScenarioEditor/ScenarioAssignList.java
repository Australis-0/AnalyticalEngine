//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusScenarioEditor;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonMain;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menusScenarioEditor.Wasteland.ScenarioWasteland;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class ScenarioAssignList extends Menu {
    public ScenarioAssignList() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = CFG.PADDING * 2;
        int titleHeight = CFG.BUTTON_HEIGHT;
        int menuWidth = CFG.LEFT_MENU_WIDTH * 3 / 5;
        int menuX = CFG.GAME_WIDTH - menuWidth - CFG.PADDING*4;
        int menuY = CFG.BUTTON_HEIGHT + CFG.PADDING*8;
        int buttonYPadding = CFG.PADDING * 2;
        menuElements.add(new ButtonMain("", 1, -1, paddingLeft, buttonYPadding, menuWidth - paddingLeft * 2, true, true) {
            public void buildElementHover() {
                if (CFG.isDesktop()) {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("SHIFT", "", Images.council, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                } else {
                    this.menuElementHover = null;
                }

            }

            public void updateLanguage() {
                this.setText(Game.lang.get("Brush"));
            }

            public void actionElement() {
                CFG.brushTool = !CFG.brushTool;
            }

            public boolean getCheckboxState() {
                return CFG.brushTool;
            }
        });
        int buttonY = buttonYPadding + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
            public void buildElementHover() {
                if (CFG.isDesktop()) {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("BACKSPACE", "", Images.council, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                } else {
                    this.menuElementHover = null;
                }

            }

            public void updateLanguage() {
                this.setText(Game.lang.get("Undo"));
            }

            public boolean getClickable() {
                return ScenarioWasteland.lUndo.size() > 0;
            }

            public void actionElement() {
                ScenarioAssign.popUndo();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
            public void buildElementHover() {
                if (CFG.isDesktop()) {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Q", "", Images.council, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                } else {
                    this.menuElementHover = null;
                }

            }

            public void updateLanguage() {
                this.setText(Game.getCiv(0).getCivName());
            }

            public void actionElement() {
                CFG.iCreateScenario_AssignProvinces_Civ = 0;
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        this.initMenu(new MenuTitle(Game.lang.get("Editor"), 1.0F, titleHeight, true, true), menuX, titleHeight + menuY, menuWidth, Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY * 2), menuElements, true, false);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight() + CFG.PADDING, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("Editor"));
    }
}
