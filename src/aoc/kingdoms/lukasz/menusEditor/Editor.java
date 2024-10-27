//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusEditor;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonMain;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Desc_Simple;
import aoc.kingdoms.lukasz.menus.Dialog;
import aoc.kingdoms.lukasz.menus.MainMenu;
import aoc.kingdoms.lukasz.menus.Dialog.DialogType;
import aoc.kingdoms.lukasz.menusMapEditor.EditorSelectProvinces;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Editor extends Menu {
    public Editor() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = CFG.PADDING * 2;
        int titleHeight = CFG.BUTTON_HEIGHT;
        int menuX = CFG.GAME_WIDTH / 10;
        int menuY = CFG.GAME_HEIGHT / 10;
        int buttonYPadding = CFG.PADDING * 2;
        int buttonY = buttonYPadding;
        int textPosX = CFG.PADDING * 4;
        menuElements.add(new ButtonMain((String)null, 1, -1, paddingLeft, CFG.PADDING, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Back"));
            }

            public void actionElement() {
                Renderer.drawArmyInProvince = true;
                Game.menuManager.setViewID(View.MAINMENU);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(Game.lang.get("GameCivilizations"), 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void actionElement() {
                MainMenu.canContinue = false;
                Game.reloadScenario = true;
                Game.menuManager.setViewID(View.EDITOR_GAMECIVS);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(Game.lang.get("CreateaCivilization"), 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void actionElement() {
                MainMenu.canContinue = false;
                Game.reloadScenario = true;
                CreateCiv.saveFlag = false;
                CreateCiv.goBackTo = View.EDITOR;
                Game.menuManager.setViewID(View.CREATE_CIV);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("CreateaScenario"));
            }

            public void actionElement() {
                MainMenu.canContinue = false;
                Game.reloadScenario = true;
                Game.menuManager.setViewID(View.EDITOR_SCENARIOS_LIST);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, CFG.isDesktop()) {
            public void updateLanguage() {
                this.setText(Game.lang.get("MapEditor"));
            }

            public void actionElement() {
                MainMenu.canContinue = false;
                Game.reloadScenario = true;
                CFG.selectMode = true;
                MenuManager.mapEditorDrawProvinces = true;
                Game.menuManager.setViewID(View.EDITOR_MAPS);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding * 3;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, CFG.isDesktop()) {
            public void updateLanguage() {
                this.setText(Game.lang.get("ManageMods"));
            }

            public void actionElement() {
                Game.menuManager.setViewID(View.MANAGE_MODS);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ManageMods"), "", Images.technology2, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, CFG.isDesktop()) {
            public void updateLanguage() {
                this.setText(Game.lang.get("SteamWorkshop"));
            }

            public void actionElement() {
                Game.menuManager.setViewID(View.WORKSHOP);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("SubmitYourModsToTheSteamWorkshop"), "", Images.technology2, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new Text_Desc_Simple("Recompilation mark (Vis Tacitus).\n\nYou hereby agree to sell your soul to Lukasz Jakowski.", paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2) {
            public void actionElement() {
                Game.menuManager.addToastGold("Terms of Use", Images.technology2);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, CFG.isDesktop()) {
            public void updateLanguage() {
                this.setText(Game.lang.get("SelectProvinces"));
            }

            public void actionElement() {
                CFG.selectMode = true;
                MenuManager.mapEditorDrawProvinces = true;
                EditorSelectProvinces.selectedProvinces.clear();
                Game.menuManager.setViewID(View.EDITOR_SELECT_PROVINCES);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText("www.LukaszJakowski.pl");
            }

            public void actionElement() {
                Dialog.GO_TO_LINK = "https://lukaszjakowski.pl/";
                Dialog.setDialogType(DialogType.GO_TO_LINK);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        buttonY += ((MenuElement)menuElements.get(0)).getHeight() + buttonYPadding * 2;
        this.initMenu(new MenuTitle("", 1.0F, titleHeight, true, true), menuX, titleHeight + menuY, CFG.LEFT_MENU_WIDTH, Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY - CFG.PADDING * 2), menuElements, true, false);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight() + CFG.PADDING, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("GameEditor"));
    }
}
