//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusInGame.Province;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.BuildingsManager;
import aoc.kingdoms.lukasz.map.army.ArmyRecruit;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonBuild;
import aoc.kingdoms.lukasz.menu_element.button.ButtonBuildingProvince;
import aoc.kingdoms.lukasz.menu_element.button.ButtonRecruitingArmy;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextBGBot;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon_VerticalBuildings;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Static;
import aoc.kingdoms.lukasz.menusInGame.Buildings.InGame_BuildingsGroup;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.script.Invocable;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class InGame_ProvinceInfoBuildings extends Menu {
    public static int mPosX = 0;
    public static int mPosY = 0;
    public static int mWidth = 0;

    public InGame_ProvinceInfoBuildings() {
        List<MenuElement> menuElements = new ArrayList();
        int buttonX = CFG.PADDING;
        int buttonY = CFG.PADDING;
        if (InGame_ProvinceInfo.iProvinceID >= 0) {
            int tempX = CFG.PADDING;
            if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID() == Game.player.iCivID) {
                menuElements.add(new TextIcon_VerticalBuildings("" + Game.getProvince(InGame_ProvinceInfo.iProvinceID).getUsedBuildingsSlots() + " / " + Game.getProvince(InGame_ProvinceInfo.iProvinceID).iBuildingsLimit, Images.build, buttonX, buttonY, CFG.TEXT_HEIGHT_SMALL + CFG.PADDING * 2, ImageManager.getImage(Images.buildingsFrameSmall).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BuildingSlots") + ": ", "" + Game.getProvince(InGame_ProvinceInfo.iProvinceID).getUsedBuildingsSlots() + " / " + Game.getProvince(InGame_ProvinceInfo.iProvinceID).iBuildingsLimit, Images.build, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(" = " + GameValues.buildings.BUILDINGS_LIMIT_DEFAULT + " + " + Game.lang.get("Economy") + " / " + CFG.getPrecision2(GameValues.buildings.BUILDINGS_SLOT_PER_ECONOMY, 10) + " + " + Game.lang.get("Infrastructure") + (GameValues.infrastructure.INFRASTRUCTURE_BUILDINGS_SLOT_PER_LVL != 1 ? " * " + GameValues.infrastructure.INFRASTRUCTURE_BUILDINGS_SLOT_PER_LVL : ""), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Capital") + ": +" + GameValues.buildings.BUILDINGS_LIMIT_CAPITAL, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                tempX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonBuild(tempX, buttonY, true) {
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_Buildings() && Game.iActiveProvince == InGame_ProvinceInfo.iProvinceID) {
                            Game.menuManager.setVisibleInGame_Buildings(false, false);
                        } else if (Game.iActiveProvince >= 0) {
                            InGame_ProvinceInfo.iProvinceID = Game.iActiveProvince;
                            InGame_BuildingsGroup.iProvinceID = Game.iActiveProvince;
                            Game.menuManager.rebuildInGame_Buildings(true);
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ConstructNewBuilding") + ": ", CFG.FONT_BOLD_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getProvinceName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.build, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingSlots") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(InGame_ProvinceInfo.iProvinceID).getUsedBuildingsSlots() + " / " + Game.getProvince(InGame_ProvinceInfo.iProvinceID).iBuildingsLimit, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.build, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData, false));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(" = " + GameValues.buildings.BUILDINGS_LIMIT_DEFAULT + " + " + Game.lang.get("Economy") + " / " + CFG.getPrecision2(GameValues.buildings.BUILDINGS_SLOT_PER_ECONOMY, 10) + " + " + Game.lang.get("Infrastructure") + (GameValues.infrastructure.INFRASTRUCTURE_BUILDINGS_SLOT_PER_LVL != 1 ? " * " + GameValues.infrastructure.INFRASTRUCTURE_BUILDINGS_SLOT_PER_LVL : ""), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData, false));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Capital") + ": +" + GameValues.buildings.BUILDINGS_LIMIT_CAPITAL, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData, false));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                menuElements.add(new TextBGBot(Game.lang.get("Build"), -1, tempX, buttonY + ImageManager.getImage(Images.buildButton).getHeight(), ImageManager.getImage(Images.buildButton).getWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 2) {
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_Buildings() && Game.iActiveProvince == InGame_ProvinceInfo.iProvinceID) {
                            Game.menuManager.setVisibleInGame_Buildings(false, false);
                        } else if (Game.iActiveProvince >= 0) {
                            InGame_ProvinceInfo.iProvinceID = Game.iActiveProvince;
                            InGame_BuildingsGroup.iProvinceID = Game.iActiveProvince;
                            Game.menuManager.rebuildInGame_Buildings(true);
                        }

                        //ANALYTICALENGINE START
                        Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                        try {
                            invocable.invokeFunction("parseOnProvinceBuildClick", Game.iActiveProvince);
                        } catch (ScriptException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        //ANALYTICALENGINE END
                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ConstructNewBuilding") + ": ", CFG.FONT_BOLD_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getProvinceName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.build, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingSlots") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(InGame_ProvinceInfo.iProvinceID).getUsedBuildingsSlots() + " / " + Game.getProvince(InGame_ProvinceInfo.iProvinceID).iBuildingsLimit, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.build, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                tempX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            }

            try {
                int tRecruitArmyID = Game.getCiv(Game.player.iCivID).getRecruitArmyInProvinceID(InGame_ProvinceInfo.iProvinceID);
                if (tRecruitArmyID >= 0) {
                    menuElements.add(new ButtonRecruitingArmy(tempX, buttonY, ((ArmyRecruit)((ArrayList)Game.getCiv(Game.player.iCivID).lArmyRecruit.get(tRecruitArmyID)).get(0)).unitID, ((ArmyRecruit)((ArrayList)Game.getCiv(Game.player.iCivID).lArmyRecruit.get(tRecruitArmyID)).get(0)).armyID, Game.player.iCivID));
                    menuElements.add(new TextBGBot(Game.lang.get("Army"), -1, tempX, buttonY + ImageManager.getImage(Images.buildingsFrameSmall).getHeight(), ImageManager.getImage(Images.buildingsFrameSmall).getWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 2));
                    tempX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }

            if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).iBuildingsSize <= 0 && Game.getProvince(InGame_ProvinceInfo.iProvinceID).iBuildingsConstructionSize <= 0) {
                if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).getUsedBuildingsSlots() == 0) {
                    menuElements.add(new Text_Static(Game.lang.get("NoBuildingsConstructed") + ".", CFG.FONT_REGULAR_SMALL, -1, tempX, buttonY, mWidth - tempX - CFG.PADDING, ImageManager.getImage(Images.buildingsFrameSmall).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2) {
                        protected Color getColor(boolean isActive) {
                            if (isActive) {
                                return Colors.BUTTON_TEXT_ACTIVE;
                            } else if (this.getIsHovered()) {
                                return Colors.BUTTON_TEXT_HOVERED;
                            } else {
                                return this.getClickable() ? Colors.BUTTON_TEXT : Colors.BUTTON_TEXT_DISABLED;
                            }
                        }
                    });
                }
            } else {
                for(int i = 0; i < Game.getProvince(InGame_ProvinceInfo.iProvinceID).iBuildingsConstructionSize; ++i) {
                    menuElements.add(new ButtonBuildingProvince(tempX, buttonY, Game.getProvince(InGame_ProvinceInfo.iProvinceID).getBuildingsConstruction(i).getBuilding(), Game.getProvince(InGame_ProvinceInfo.iProvinceID).getBuildingsConstruction(i).getBuildingID(), true));
                    menuElements.add(new TextBGBot(((BuildingsManager.Buildings)BuildingsManager.buildings.get(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getBuildingsConstruction(i).getBuilding())).Name[Game.getProvince(InGame_ProvinceInfo.iProvinceID).getBuildingsConstruction(i).getBuildingID()], -1, tempX, buttonY + ImageManager.getImage(Images.buildingsFrameSmall).getHeight(), ImageManager.getImage(Images.buildingsFrameSmall).getWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 2));
                    tempX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                }

                for(int i = 0; i < Game.getProvince(InGame_ProvinceInfo.iProvinceID).iBuildingsSize; ++i) {
                    //ANALYTICALENGINE START
                    int final_i = i;
                    //ANALYTICALENGINE END

                    menuElements.add(new ButtonBuildingProvince(tempX, buttonY, Game.getProvince(InGame_ProvinceInfo.iProvinceID).getBuildings(i).getBuilding(), Game.getProvince(InGame_ProvinceInfo.iProvinceID).getBuildings(i).getBuildingID(), false) {
                        public void buildElementHover() {
                            this.menuElementHover = ButtonBuildingProvince.getHoverBuilding(this.building, this.buildingID, this.underConstruction, true);
                        }

                        public void actionElement() {
                            if (Game.menuManager.getVisibleInGame_Buildings() && Game.iActiveProvince == InGame_ProvinceInfo.iProvinceID) {
                                Game.menuManager.setVisibleInGame_Buildings(false, false);
                            } else if (Game.iActiveProvince >= 0) {
                                InGame_ProvinceInfo.iProvinceID = Game.iActiveProvince;
                                InGame_BuildingsGroup.iProvinceID = Game.iActiveProvince;
                                Game.menuManager.rebuildInGame_Buildings(true);
                            }

                            //ANALYTICALENGINE START
                            Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                            try {
                                invocable.invokeFunction("parseOnProvinceBuildingClick",
                                    Game.iActiveProvince, Game.getProvince(InGame_ProvinceInfo.iProvinceID).getBuildings(final_i).getBuilding(),
                                    Game.getProvince(InGame_ProvinceInfo.iProvinceID).getBuildings(final_i).getBuildingID());
                            } catch (ScriptException | NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                            //ANALYTICALENGINE END
                        }
                    });
                    menuElements.add(new TextBGBot(((BuildingsManager.Buildings)BuildingsManager.buildings.get(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getBuildings(i).getBuilding())).Name[Game.getProvince(InGame_ProvinceInfo.iProvinceID).getBuildings(i).getBuildingID()], -1, tempX, buttonY + ImageManager.getImage(Images.buildingsFrameSmall).getHeight(), ImageManager.getImage(Images.buildingsFrameSmall).getWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 2));
                    tempX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                }
            }
        }

        this.initMenu((MenuTitle)null, mPosX, mPosY, mWidth, ImageManager.getImage(Images.buildingsFrameSmall).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 4, menuElements, false);
        this.drawScrollPositionAlways = false;
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5F));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0F);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameProvinceInfo();
    }

    public int getMenuPosY() {
        return super.getMenuPosY() + InGame_ProvinceInfo.nTranslateY;
    }

    public int getPosY() {
        return super.getPosY() + InGame_ProvinceInfo.nTranslateY;
    }

    public int getPosX() {
        return super.getPosX() + InGame_ProvinceInfo.nTranslateX;
    }

    public int getMenuPosX() {
        return super.getMenuPosX() + InGame_ProvinceInfo.nTranslateX;
    }

    public boolean getVisible() {
        return super.getVisible() && Game.mapBG.getHideMenuZoomOut();
    }
}
