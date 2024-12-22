//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusScenarioEditor;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
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
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
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

public class ScenarioCivilizations extends Menu {
    public static boolean listOfAllCivs = true;

    public ScenarioCivilizations() {
        List<MenuElement> menuElements = new ArrayList();
        menuElements.add(new Minimap(0, 0) {
            public int getPosX() {
                return CFG.GAME_WIDTH - this.getWidth();
            }

            public int getPosY() {
                return CFG.GAME_HEIGHT - this.getHeight();
            }
        });
        menuElements.add(new Text_Static(Game.lang.get("ManageCivilizations"), CFG.PADDING, CFG.BUTTON_HEIGHT + CFG.PADDING * 3) {
            public int getPosX() {
                return CFG.GAME_WIDTH - CFG.PADDING * 4 - this.getTextWidth();
            }

            public int getPosY() {
                return ScenarioCivilizations.this.getMenuElement(0).getPosY() - CFG.PADDING - this.getHeight();
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
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ManageCivilizations"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ClickAprovinceOnTheMapToAddOrRemoveCivilization"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonMain((String)null, 1, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Remove"));
            }

            public void actionElement() {
                CFG.iCreateScenario_ActiveProvinceID = Game.iActiveProvince;
                Dialog.setDialogType(DialogType.CREATE_SCENARIO_REMOVE_CIVILIZATION);
            }

            public boolean getClickable() {
                return Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0;
            }

            public void buildElementHover() {
                try {
                    if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iActiveProvince).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("RemoveCivilization") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    } else {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SelectProvince")));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                } catch (IndexOutOfBoundsException var3) {
                    this.menuElementHover = null;
                }

            }

            public int getTextWidth() {
                return super.getTextWidth() + CFG.PADDING + CFG.CIV_FLAG_WIDTH;
            }

            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (!this.getClickable()) {
                    oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.7F));
                }

                Game.getCiv(Game.iActiveProvince >= 0 ? Game.getProvince(Game.iActiveProvince).getCivID() : 0).getFlag().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
                super.drawText(oSB, CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX, iTranslateY, isActive);
            }
        });
        menuElements.add(new ButtonMain((String)null, 1, -1, CFG.PADDING * 2 + CFG.BUTTON_WIDTH * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("SetCapital"));
            }

            public void actionElement() {
                if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                    if (Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCapitalProvinceID()).getCivID() == Game.getProvince(Game.iActiveProvince).getCivID()) {
                        Game.getProvince(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCapitalProvinceID()).setIsCapital(false);
                    }

                    Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).setCapitalProvinceID(Game.iActiveProvince);
                    Game.getProvince(Game.iActiveProvince).setIsCapital(true);
                    Game.getProvince(Game.iActiveProvince).setDrawCities(true);
                }

            }

            public boolean getClickable() {
                return Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0;
            }

            public void buildElementHover() {
                try {
                    if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.getProvince(Game.iActiveProvince).getCivID()));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SetCapital") + ": "));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getProvince(Game.iActiveProvince).getProvinceName(), Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    } else {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SelectProvince")));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                } catch (IndexOutOfBoundsException var3) {
                    this.menuElementHover = null;
                }

            }

            public int getTextWidth() {
                return super.getTextWidth() + CFG.PADDING + CFG.CIV_FLAG_WIDTH;
            }

            public void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                if (!this.getClickable()) {
                    oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.7F));
                }

                Game.getCiv(Game.iActiveProvince >= 0 ? Game.getProvince(Game.iActiveProvince).getCivID() : 0).getFlag().draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                ImageManager.getImage(Images.flag_rect).draw(oSB, this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
                super.drawText(oSB, CFG.CIV_FLAG_WIDTH + CFG.PADDING + iTranslateX, iTranslateY, isActive);
            }
        });
        this.initMenu((MenuTitle)null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        ImageManager.getImage(Images.boxBIG).draw2(oSB, iTranslateX, CFG.GAME_HEIGHT - (CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH) + iTranslateY, CFG.BUTTON_WIDTH * 4 + CFG.PADDING * 3 + Images.boxTitleBORDERWIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH, true, false);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public static void actionUpdateData() {
        if (listOfAllCivs) {
            if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0 && Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCapitalProvinceID() == Game.iActiveProvince) {
                Game.keyboard.hideKeyboard();
                Game.hoverManager.resetHoverActive_Menu();
                Game.menuManager.rebuildScenarioCivilizationsList();
            } else if (Game.iActiveProvince >= 0) {
                Game.menuManager.rebuildScenarioCivilizationsList();
            }
        } else {
            Game.hoverManager.resetHoverActive_Menu();
            Game.menuManager.rebuildScenarioCivilizationsList();
        }

    }
}
