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
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class ScenarioAssignCivs extends Menu {
    public ScenarioAssignCivs() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = CFG.PADDING * 2;
        int titleHeight = CFG.BUTTON_HEIGHT;
        int buttonYPadding = CFG.PADDING * 2;
        int buttonY = buttonYPadding;
        int menuWidth = CFG.LEFT_MENU_WIDTH * 3 / 5;
        int textPosX = CFG.PADDING * 4;

        int menuX = CFG.GAME_WIDTH - CFG.PADDING*4*2 - menuWidth*2;
        int menuY = CFG.PADDING * 4 + Images.boxTitleBORDERWIDTH;
        List<Integer> tCivs = new ArrayList();

        for(int i = 0; i < Game.getCivsSize(); ++i) {
            tCivs.add(i);
        }

        while(tCivs.size() > 0) {
            int toAddID = 0;
            int i = 1;

            for(int iSize = tCivs.size(); i < iSize; ++i) {
                for(int j = 0; j < Math.min(Game.getCiv((Integer)tCivs.get(toAddID)).getCivName().length(), Game.getCiv((Integer)tCivs.get(i)).getCivName().length()); ++j) {
                    if (Game.getCiv((Integer)tCivs.get(toAddID)).getCivName().charAt(j) > Game.getCiv((Integer)tCivs.get(i)).getCivName().charAt(j)) {
                        toAddID = i;
                        break;
                    }

                    if (Game.getCiv((Integer)tCivs.get(toAddID)).getCivName().charAt(j) < Game.getCiv((Integer)tCivs.get(i)).getCivName().charAt(j)) {
                        break;
                    }
                }
            }

            menuElements.add(new ButtonMain(Game.getCiv((Integer)tCivs.get(toAddID)).getCivName(), 1, CFG.PADDING * 2 + CFG.CIV_FLAG_WIDTH, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true) {
                public void buildElementHover() {
                    if (CFG.isDesktop()) {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("CTRL + " + Game.lang.get("ChooseAProvince"), "", Images.council, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    } else {
                        this.menuElementHover = null;
                    }

                }

                public void actionElement() {
                    CFG.iCreateScenario_AssignProvinces_Civ = this.getCurrent();
                }

                protected Color getColor(boolean isActive) {
                    return this.getCurrent() == CFG.iCreateScenario_AssignProvinces_Civ ? Colors.HOVER_GOLD : super.getColor(isActive);
                }
            });
            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent((Integer)tCivs.get(toAddID));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
            tCivs.remove(toAddID);
        }

        this.initMenu(new MenuTitle("", 1.0F, titleHeight, true, true), menuX, titleHeight + menuY, menuWidth, Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY * 2), menuElements, true);
    }

    public final void drawEditorMenuBG(SpriteBatch oSB, int iX, int iY, int iWidth, int iHeight, int iTranslateX, int iTranslateY) {
        Renderer.drawBoxCorner(oSB, iX + iTranslateX, iY - this.getTitle().getHeight() + iTranslateY, iWidth, iHeight + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawBox_EDGE_TOP_LR(oSB, Images.mainBox, iX + iTranslateX, iY + iTranslateY, iWidth, iHeight + CFG.PADDING, true);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        this.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), iTranslateX, iTranslateY);
        super.beginClip(oSB, iTranslateX, iTranslateY, menuIsActive);
        super.drawMenu(oSB, iTranslateX, iTranslateY, menuIsActive);

        try {
            for(int i = 0; i < this.getMenuElementsSize(); ++i) {
                if (this.getMenuElement(i).getIsInView()) {
                    Game.getCiv(this.getMenuElement(i).getCurrent()).getFlag().draw(oSB, this.getPosX() + this.getMenuElement(i).getPosX() + CFG.PADDING + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                    ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getMenuElement(i).getPosX() + this.getMenuElement(i).getTextPos() / 2 - CFG.CIV_FLAG_WIDTH / 2 + iTranslateX, this.getMenuElement(i).getPosY() + this.getMenuPosY() + this.getMenuElement(i).getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                }
            }
        } catch (IndexOutOfBoundsException var7) {
        } catch (NullPointerException var8) {
        }

        super.endClip(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("Civilisations"));
    }
}
