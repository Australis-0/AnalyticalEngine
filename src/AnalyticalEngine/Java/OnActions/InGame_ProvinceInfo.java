//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusInGame.Province;

import aoc.kingdoms.lukasz.jakowski.AA_KeyManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Keyboard;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.jakowski.Keyboard.KeyboardActionType;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.BuildingsManager;
import aoc.kingdoms.lukasz.map.ColonizationManager;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.map.WondersManager;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.map.province.ProvinceInvest;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.map.terrain.Terrain;
import aoc.kingdoms.lukasz.menu.ClickAnimation;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.HoverManager;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitleIMG_Flag_TextRight;
import aoc.kingdoms.lukasz.menu_element.Empty;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonGame;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Bonuses2_Value;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Economy;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Loot;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Manpower;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_ProvinceIncome;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Tax;
import aoc.kingdoms.lukasz.menu_element.button.ButtonTerrain;
import aoc.kingdoms.lukasz.menu_element.button.ButtonWonderProvince;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusReligion;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusResource;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Resource;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoc.kingdoms.lukasz.menu_element.pieChart.PieChart;
import aoc.kingdoms.lukasz.menu_element.pieChart.PieChart_Data;
import aoc.kingdoms.lukasz.menu_element.pieChart.PieChart_Value;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextBGBot;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextCores;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_Devastation;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_Economy;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_GrowthRate;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_Infrastructure;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_Manpower;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_Nuke;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_Population;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_ProvinceIncome;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_Religion;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_RevolutionaryRisk;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_Tax;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon_Resource;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon_VerticalProductionIncome;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Desc3;
import aoc.kingdoms.lukasz.menusInGame.InGame_ConvertReligion;
import aoc.kingdoms.lukasz.menusInGame.InGame_MapModes;
import aoc.kingdoms.lukasz.menusInGame.InGame_Wonder;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_Population;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_Goods;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_GoodsMarket;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_Goods_LargestProducers;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.script.Invocable;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class InGame_ProvinceInfo extends Menu {
    public static int nTranslateX;
    public static int nTranslateY;
    public static final int ANIMATION_TIME = 60;
    public static long lTime = 0L;
    public static long lTime2 = 0L;
    public static int iProvinceID = 0;
    public int statsElementsID = 0;
    public int pieElementID = 0;
    public int imageOverID = 0;
    public static int menuPosY = 0;
    public static int menuW = 0;
    public static int HOVER_POSX = 0;
    public static int HOVER_POSY = 0;
    public static boolean atomicOwnOnceTimeInfo = true;
    public static Image provinceIMG = null;
    public static int provinceIMG_ID = -1;
    public static int provinceIMG_ID_Loaded = -1;
    public static String renameProvince = "";
    public boolean confirmEditProvinceName = false;

    public static void loadProvinceIMG() {
        try {
            if (provinceIMG_ID >= 0 && (provinceIMG == null || provinceIMG_ID != provinceIMG_ID_Loaded)) {
                if (provinceIMG != null) {
                    provinceIMG.dispose();
                    provinceIMG = null;
                }

                provinceIMG = loadProvinceImage();
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static Image loadProvinceImage() {
        try {
            if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "provinces/" + provinceIMG_ID + ".png").exists()) {
                provinceIMG_ID_Loaded = provinceIMG_ID;
                return new Image(ImageManager.loadTexture_RGB888("map/" + Game.map.getFile_ActiveMap_Path() + "provinces/" + provinceIMG_ID + ".png"), TextureFilter.Linear, TextureWrap.ClampToEdge);
            }
        } catch (Exception ex) {
            provinceIMG_ID_Loaded = -1;
            CFG.exceptionStack(ex);
        }

        try {
            if (provinceIMG != null) {
                provinceIMG.dispose();
            }
        } catch (Exception exr) {
            CFG.exceptionStack(exr);
        }

        return null;
    }

    public InGame_ProvinceInfo() {
        List<MenuElement> menuElements = new ArrayList();
        if (Game.iActiveProvince >= 0) {
            if (iProvinceID != Game.iActiveProvince) {
                lTime2 = CFG.currentTimeMillis;
            }

            iProvinceID = Game.iActiveProvince;
            if (Game.getProvince(iProvinceID).getCivID() == 0) {
                this.imageOverID = Images.provinceInfoNeutralOver;
            } else if (Game.getProvince(iProvinceID).isCapital) {
                this.imageOverID = Images.provinceInfoOverCapital;
            } else {
                switch (iProvinceID % 3) {
                    case 1:
                        this.imageOverID = Images.provinceInfoOver1;
                        break;
                    case 2:
                        this.imageOverID = Images.provinceInfoOver2;
                        break;
                    default:
                        this.imageOverID = Images.provinceInfoOver;
                }
            }
        } else {
            iProvinceID = 0;
            this.imageOverID = Images.provinceInfoOver1;
        }

        Game.addSimpleTask(new Game.SimpleTask("loadProvinceIMG", iProvinceID) {
            public void update() {
                InGame_ProvinceInfo.provinceIMG_ID = this.id;
                InGame_ProvinceInfo.loadProvinceIMG();
            }
        });
        int paddingLeft = CFG.PADDING * 2;
        int titleHeight = ImageManager.getImage(Images.title580).getHeight();
        int menuWidth = ImageManager.getImage(Images.title580).getWidth();
        int menuHeight = 240;
        int menuX = 0;
        int var10000 = CFG.GAME_HEIGHT - menuHeight;
        int buttonYPadding = CFG.PADDING * 2;
        int textPosX = CFG.PADDING * 4;
        int statsRightX = ImageManager.getImage(Images.terrainFrame).getWidth() + CFG.PADDING;
        int statsRightH = ImageManager.getImage(Images.terrainFrame).getHeight();
        int statsRightW = (menuWidth - Images.boxTitleBORDERWIDTH - ImageManager.getImage(Images.terrainFrame).getWidth() - CFG.PADDING * 5) / 4;
        menuElements.add(new ButtonTerrain(iProvinceID, 0, 0) {
            public void actionElement() {
                Game.mapCoords.centerToProvinceID(this.iProvinceID);

                //ANALYTICALENGINE START
                Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                try {
                    invocable.invokeFunction("parseOnProvinceTerrainPictureClick", Game.iActiveProvince);
                } catch (ScriptException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                //ANALYTICALENGINE END
            }

            public void actionElementPPM() {
                Game.mapModes.setActiveViewID(Game.mapModes.MODE_TERRAIN);
            }
        });
        List<Integer> tempCores = new ArrayList();

        for(int i = 0; i < Game.getProvince(iProvinceID).iCoresSize; ++i) {
            tempCores.add(Game.getProvince(iProvinceID).getCore(i));
        }

        int buttonY = CFG.PADDING;
        int tempX = paddingLeft;
        if (Game.getProvince(iProvinceID).getCivID() > 0) {
            menuElements.add(new TextCores(Game.lang.get("Cores").substring(0, Math.min(8, Game.lang.get("Cores").length())) + ":", tempCores, statsRightX, buttonY, menuWidth - Images.boxTitleBORDERWIDTH - ImageManager.getImage(Images.terrainFrame).getWidth() - CFG.PADDING * 3 - statsRightW, CFG.TEXT_HEIGHT + CFG.PADDING * 2, iProvinceID, !Game.getProvince(iProvinceID).haveACore) {
                public int getSFX() {
                    return SoundsManager.SOUND_CORES;
                }

                public void actionElement() {
                    if (Game.getProvince(this.iProvinceID).getCivID() == Game.player.iCivID && Game.getProvince(this.iProvinceID).addCoreCreation()) {
                        MenuManager var10000 = Game.menuManager;
                        MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_ProvinceInfo.this.getPosX(), this.getPosY() + this.getHeight() / 2 + InGame_ProvinceInfo.this.getPosY(), this.getWidth(), this.getHeight()) {
                            public Color getColor() {
                                return Colors.HOVER_POSITIVE;
                            }
                        });
                    }

                    //ANALYTICALENGINE START
                    Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                    try {
                        invocable.invokeFunction("parseOnProvinceCoresClick", Game.iActiveProvince);
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    //ANALYTICALENGINE END
                }
            });
            menuElements.add(new ButtonStatsRectIMG("" + Game.getProvince(iProvinceID).getFortLevel(), Images.fort, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosX() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING, buttonY, statsRightW, CFG.TEXT_HEIGHT + CFG.PADDING * 2, ImageManager.getImage(Images.fort).getWidth()) {
                public void actionElement() {
                    //ANALYTICALENGINE START
                    Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                    try {
                        invocable.invokeFunction("parseOnProvinceDefenceLevelClick", Game.iActiveProvince);
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    //ANALYTICALENGINE END
                }

                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverFort(InGame_ProvinceInfo.iProvinceID, true);
                }

                public void actionElementPPM() {
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_FORTS) {
                        this.menuElementHover = null;
                    }

                }

                public void updateHovered() {
                    super.updateHovered();
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_FORTS && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_FORTS_HOVER);
                    }

                }

                public void setIsHovered(boolean isHovered) {
                    super.setIsHovered(isHovered);
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_FORTS && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_FORTS_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    }

                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            if (Game.getProvince(iProvinceID).getCivID() != Game.player.iCivID && !GameValues.province.PROVINCE_VIEW_USE_SAME_BUTTONS) {
                statsRightH = (statsRightH - ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() - CFG.PADDING * 3) / 2;
                int statsRightW2 = (menuWidth - Images.boxTitleBORDERWIDTH - ImageManager.getImage(Images.terrainFrame).getWidth() - CFG.PADDING * 3) / 2;
                menuElements.add(new ButtonStatsRectIMG_ProvinceIncome(iProvinceID, "" + Game.getProvince(iProvinceID).getFortLevel(), Images.gold, statsRightX, buttonY, statsRightW2, statsRightH, ImageManager.getImage(Images.gold).getWidth()) {
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverProvinceIncome(this.iProvinceID, true);
                    }

                    public void actionElement() {
                        //ANALYTICALENGINE START
                        Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                        try {
                            invocable.invokeFunction("parseOnProvinceMonthlyIncomeClick", Game.iActiveProvince);
                        } catch (ScriptException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        //ANALYTICALENGINE END

                        if (Game.menuManager.getVisibleInGame_ProvinceBonuses()) {
                            Game.menuManager.setVisibleInGame_ProvinceBonuses(false, false);
                        } else {
                            InGame_ProvinceBonuses.iProvinceID = this.iProvinceID;
                            Game.menuManager.rebuildInGame_ProvinceBonuses();
                            Game.menuManager.setVisibleInGame_ProvinceBonuses(true, true);
                        }
                    }

                    public void actionElementPPM() {
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_PROVINCE_INCOME) {
                            this.menuElementHover = null;
                        }

                    }

                    public void updateHovered() {
                        super.updateHovered();
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_PROVINCE_INCOME && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_PROVINCE_INCOME_HOVER);
                        }

                    }

                    public void setIsHovered(boolean isHovered) {
                        super.setIsHovered(isHovered);
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_PROVINCE_INCOME && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_PROVINCE_INCOME_HOVER) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                    }
                });
                menuElements.add(new ButtonStatsRectIMG_Tax(iProvinceID, "1", Images.tax, statsRightX, buttonY + statsRightH + CFG.PADDING, statsRightW2, statsRightH, ImageManager.getImage(Images.gold).getWidth()) {
                    public int getSFX() {
                        return Game.soundsManager.getCoin();
                    }

                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverTaxEfficiency(this.iProvinceID, true);
                    }

                    public void actionElement() {
                        if (InGame_ProvinceInfo.actionTax(this.iProvinceID)) {
                            MenuManager var10000 = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_ProvinceInfo.this.getPosX(), this.getPosY() + this.getHeight() / 2 + InGame_ProvinceInfo.this.getPosY(), this.getWidth(), this.getHeight()) {
                                public Color getColor() {
                                    return Colors.HOVER_YELLOW;
                                }
                            });
                        }

                    }

                    public void actionElementPPM() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_TAX);
                    }

                    public void updateHovered() {
                        super.updateHovered();
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_TAX_EFFICIENCY && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_TAX_EFFICIENCY_HOVER);
                        }

                    }

                    public void setIsHovered(boolean isHovered) {
                        super.setIsHovered(isHovered);
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_TAX_EFFICIENCY && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_TAX_EFFICIENCY_HOVER) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                    }
                });
                statsRightX += statsRightW2 + CFG.PADDING;
                menuElements.add(new ButtonStatsRectIMG_Economy(iProvinceID, "1", Game_Calendar.IMG_ECONOMY, statsRightX, buttonY, statsRightW2, statsRightH, ImageManager.getImage(Images.gold).getWidth()) {
                    public int getSFX() {
                        return Game.soundsManager.getEconomy();
                    }

                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverEconomy(this.iProvinceID, true);
                    }

                    public void actionElement() {
                        if (InGame_ProvinceInfo.actionEconomy(this.iProvinceID)) {
                            MenuManager var10000 = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_ProvinceInfo.this.getPosX(), this.getPosY() + this.getHeight() / 2 + InGame_ProvinceInfo.this.getPosY(), this.getWidth(), this.getHeight()) {
                                public Color getColor() {
                                    return Colors.COLOR_TEXT_ECONOMY;
                                }
                            });
                        }
                    }

                    public void actionElementPPM() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_ECONOMY);
                    }

                    public void updateHovered() {
                        super.updateHovered();
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_ECONOMY && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_ECONOMY_HOVER);
                        }

                    }

                    public void setIsHovered(boolean isHovered) {
                        super.setIsHovered(isHovered);
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_ECONOMY && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_ECONOMY_HOVER) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                    }
                });
                menuElements.add(new ButtonStatsRectIMG_Manpower(iProvinceID, "1", Game_Calendar.IMG_MANPOWER, statsRightX, buttonY + statsRightH + CFG.PADDING, statsRightW2, statsRightH, ImageManager.getImage(Images.gold).getWidth()) {
                    public int getSFX() {
                        return Game.soundsManager.getClickIncreaseManpower();
                    }

                    public void actionElement() {
                        //ANALYTICALENGINE START
                        Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                        try {
                            invocable.invokeFunction("parseOnProvinceManpowerClick", Game.iActiveProvince);
                        } catch (ScriptException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        //ANALYTICALENGINE END

                        if (InGame_ProvinceInfo.actionManpower(this.iProvinceID)) {
                            MenuManager var10000 = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_ProvinceInfo.this.getPosX(), this.getPosY() + this.getHeight() / 2 + InGame_ProvinceInfo.this.getPosY(), this.getWidth(), this.getHeight()));
                        }
                    }

                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverManpower(this.iProvinceID, true, true);
                    }

                    public void actionElementPPM() {
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_MANPOWER) {
                            this.menuElementHover = null;
                        }

                    }

                    public void updateHovered() {
                        super.updateHovered();
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_MANPOWER && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_MANPOWER_HOVER);
                        }

                    }

                    public void setIsHovered(boolean isHovered) {
                        super.setIsHovered(isHovered);
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_MANPOWER && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_MANPOWER_HOVER) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                    }
                });
            } else {
                statsRightH = statsRightH - ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() - CFG.PADDING * 2;
                menuElements.add(new TextIcon2_ProvinceIncome(iProvinceID, "" + CFG.getPrecision2(Game.getProvince(iProvinceID).getProvinceIncome() - Game.getProvince(iProvinceID).fProvinceMaintenance, 100), Images.gold, statsRightX, buttonY, statsRightW, statsRightH) {
                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverProvinceIncome(this.iProvinceID, true);
                    }

                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_ProvinceBonuses()) {
                            Game.menuManager.setVisibleInGame_ProvinceBonuses(false, false);
                        } else {
                            InGame_ProvinceBonuses.iProvinceID = this.iProvinceID;
                            Game.menuManager.rebuildInGame_ProvinceBonuses();
                            Game.menuManager.setVisibleInGame_ProvinceBonuses(true, true);
                        }

                        //ANALYTICALENGINE START
                        Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                        try {
                            invocable.invokeFunction("parseOnProvinceBonusModifiersClick", Game.iActiveProvince);
                        } catch (ScriptException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        //ANALYTICALENGINE END
                    }

                    public void actionElementPPM() {
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_PROVINCE_INCOME) {
                            this.menuElementHover = null;
                        }

                    }

                    public void updateHovered() {
                        super.updateHovered();
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_PROVINCE_INCOME && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_PROVINCE_INCOME_HOVER);
                        }

                    }

                    public void setIsHovered(boolean isHovered) {
                        super.setIsHovered(isHovered);
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_PROVINCE_INCOME && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_PROVINCE_INCOME_HOVER) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                    }
                });
                statsRightX += statsRightW + CFG.PADDING;
                menuElements.add(new TextIcon2_Tax(iProvinceID, "" + CFG.getPrecision2(Game.getProvince(iProvinceID).getTaxEfficiencyWithBonuses(), 10) + "%", Images.tax, statsRightX, buttonY, statsRightW, statsRightH) {
                    public int getSFX() {
                        return Game.soundsManager.getCoin();
                    }

                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverTaxEfficiency(this.iProvinceID, true);
                    }

                    public void actionElement() {
                        if (InGame_ProvinceInfo.actionTax(this.iProvinceID)) {
                            MenuManager var10000 = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_ProvinceInfo.this.getPosX(), this.getPosY() + this.getHeight() / 2 + InGame_ProvinceInfo.this.getPosY(), this.getWidth(), this.getHeight()) {
                                public Color getColor() {
                                    return Colors.HOVER_YELLOW;
                                }
                            });
                        }

                    }

                    public void actionElementPPM() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_TAX);
                    }

                    public void updateHovered() {
                        super.updateHovered();
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_TAX_EFFICIENCY && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_TAX_EFFICIENCY_HOVER);
                        }

                    }

                    public void setIsHovered(boolean isHovered) {
                        super.setIsHovered(isHovered);
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_TAX_EFFICIENCY && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_TAX_EFFICIENCY_HOVER) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                    }
                });
                statsRightX += statsRightW + CFG.PADDING;
                menuElements.add(new TextIcon2_Economy(iProvinceID, "" + CFG.getPrecision2(Game.getProvince(iProvinceID).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, statsRightX, buttonY, statsRightW, statsRightH) {
                    public int getSFX() {
                        return Game.soundsManager.getEconomy();
                    }

                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverEconomy(this.iProvinceID, true);
                    }

                    public void actionElement() {
                        //ANALYTICALENGINE START
                        Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                        try {
                            invocable.invokeFunction("parseOnProvinceEconomyClick", Game.iActiveProvince);
                        } catch (ScriptException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        //ANALYTICALENGINE END

                        if (InGame_ProvinceInfo.actionEconomy(this.iProvinceID)) {
                            MenuManager var10000 = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_ProvinceInfo.this.getPosX(), this.getPosY() + this.getHeight() / 2 + InGame_ProvinceInfo.this.getPosY(), this.getWidth(), this.getHeight()) {
                                public Color getColor() {
                                    return Colors.COLOR_TEXT_ECONOMY;
                                }
                            });
                        }

                    }

                    public void actionElementPPM() {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_ECONOMY);
                    }

                    public void updateHovered() {
                        super.updateHovered();
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_ECONOMY && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_ECONOMY_HOVER);
                        }

                    }

                    public void setIsHovered(boolean isHovered) {
                        super.setIsHovered(isHovered);
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_ECONOMY && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_ECONOMY_HOVER) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                    }
                });
                statsRightX += statsRightW + CFG.PADDING;
                menuElements.add(new TextIcon2_Manpower(iProvinceID, "1", Game_Calendar.IMG_MANPOWER, statsRightX, buttonY, statsRightW, statsRightH) {
                    public int getSFX() {
                        return Game.soundsManager.getClickIncreaseManpower();
                    }

                    public void actionElement() {
                        //ANALYTICALENGINE START
                        Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                        try {
                            invocable.invokeFunction("parseOnProvinceManpowerClick", Game.iActiveProvince);
                        } catch (ScriptException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        //ANALYTICALENGINE END

                        if (InGame_ProvinceInfo.actionManpower(this.iProvinceID)) {
                            MenuManager var10000 = Game.menuManager;
                            MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_ProvinceInfo.this.getPosX(), this.getPosY() + this.getHeight() / 2 + InGame_ProvinceInfo.this.getPosY(), this.getWidth(), this.getHeight()));
                        }

                    }

                    public int getImageID() {
                        return Game_Calendar.IMG_MANPOWER;
                    }

                    public void buildElementHover() {
                        this.menuElementHover = InGame_ProvinceInfo.getHoverManpower(this.iProvinceID, true, true);
                    }

                    public void actionElementPPM() {
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_MANPOWER) {
                            this.menuElementHover = null;
                        }

                    }

                    public void updateHovered() {
                        super.updateHovered();
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_MANPOWER && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_MANPOWER_HOVER);
                        }

                    }

                    public void setIsHovered(boolean isHovered) {
                        super.setIsHovered(isHovered);
                        if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_MANPOWER && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_MANPOWER_HOVER) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                    }
                });
                var10000 = statsRightX + statsRightW + CFG.PADDING;
            }

            this.statsElementsID = menuElements.size() - 1;
            buttonY = ((MenuElement)menuElements.get(0)).getHeight() + CFG.PADDING;
            PieChart_Data nPieChartData = new PieChart_Data();

            for(int i = 0; i < Game.getProvince(iProvinceID).getPopulationSize(); ++i) {
                nPieChartData.addPieChartValues(new PieChart_Value(Game.getProvince(iProvinceID).getPopulationCivID(i), (float)Game.getProvince(iProvinceID).getPopulationID(i)));
            }

            int pieDim = getSecondLineH();
            menuElements.add(new TextIcon2_Population(iProvinceID, "", Images.population, paddingLeft, buttonY, statsRightW + statsRightW / 4, pieDim) {
                protected Color getColor(boolean isActive) {
                    if (isActive) {
                        return Colors.COLOR_POPULATION_ACTIVE;
                    } else if (this.getIsHovered()) {
                        return Colors.COLOR_POPULATION_HOVER;
                    } else {
                        return this.getClickable() ? Colors.COLOR_POPULATION : Colors.BUTTON_TEXT_DISABLED;
                    }
                }

                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverPopulation(this.iProvinceID, true);
                }

                public void actionElement() {
                    Game.menuManager.setVisibleInGame_ProvinceInfo(false);
                    InGame_Civ_Population.goBackToRank = false;
                    Game.menuManager.rebuildInGame_Civ_Population();
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                    }

                }

                public void actionElementPPM() {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_POPULATION);
                }

                public void updateHovered() {
                    super.updateHovered();
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_POPULATION && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_POPULATION_HOVER);
                    }

                }

                public void setIsHovered(boolean isHovered) {
                    super.setIsHovered(isHovered);
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_POPULATION && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_POPULATION_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    }

                }
            });
            int buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING * 2;
            menuElements.add(new PieChart(buttonX, buttonY + CFG.PADDING, pieDim - CFG.PADDING * 2, pieDim - CFG.PADDING * 2, nPieChartData, (MenuElement_Hover)null) {
                protected void drawPieChart(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY, int nPosX, int nPosY, int nWidth, int nHeight, int nWidth_LEFT) {
                    oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5F));
                    Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.PADDING * 2, this.getHeight() + CFG.PADDING * 2, 1.0F);
                    oSB.setColor(Color.WHITE);
                    super.drawPieChart(oSB, iTranslateX, iTranslateY, isActive, scrollableY, nPosX, nPosY, nWidth, nHeight, nWidth_LEFT);
                }

                //ANALYTICALENGINE START
                public void actionElement () {
                    Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                    try {
                        invocable.invokeFunction("parseOnProvincePopulationChartClick", Game.iActiveProvince);
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                //ANALYTICALENGINE END

                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverPopulation(InGame_ProvinceInfo.iProvinceID, true);
                }

                protected float animationPerc() {
                    return Math.min(1.0F, (float)(CFG.currentTimeMillis - InGame_ProvinceInfo.lTime) / 150.0F);
                }
            });
            this.pieElementID = menuElements.size() - 1;
            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING * 2;
            int tWidthIcons = (ImageManager.getImage(Images.terrainFrame).getWidth() - buttonX - CFG.PADDING) / 2;
            menuElements.add(new TextIcon2_GrowthRate(iProvinceID, "" + CFG.getPrecision2(Game.getProvince(iProvinceID).getGrowthRateWithBonuses(), 10) + "%", Images.populationGrowth, buttonX, buttonY, tWidthIcons, pieDim) {
                public int getSFX() {
                    SoundsManager var10000 = Game.soundsManager;
                    return SoundsManager.getGrowthRate();
                }

                public void actionElement() {
                    if (InGame_ProvinceInfo.actionGrowthRate(this.iProvinceID)) {
                        MenuManager var10000 = Game.menuManager;
                        MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_ProvinceInfo.this.getPosX(), this.getPosY() + this.getHeight() / 2 + InGame_ProvinceInfo.this.getPosY(), this.getWidth(), this.getHeight()) {
                            public Color getColor() {
                                return Colors.COLOR_POPULATION;
                            }
                        });
                    }

                }

                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverPopulationGrowth(this.iProvinceID, true, true);
                }

                public void actionElementPPM() {
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_GROWTH_RATE) {
                        this.menuElementHover = null;
                    }

                }

                public void updateHovered() {
                    super.updateHovered();
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_GROWTH_RATE && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_GROWTH_RATE_HOVER);
                    }

                }

                public void setIsHovered(boolean isHovered) {
                    super.setIsHovered(isHovered);
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_GROWTH_RATE && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_GROWTH_RATE_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    }

                }
            });
            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2_Religion(Game.religionManager.getReligion(Game.getProvince(iProvinceID).getReligion()).Name, Game.getProvince(iProvinceID).getReligion(), buttonX, buttonY, tWidthIcons, pieDim, iProvinceID) {
                public void actionElement() {
                    if (Game.getProvince(this.iProvinceID).getCivID() == Game.player.iCivID && Game.getProvince(this.iProvinceID).getReligion() != Game.getCiv(Game.player.iCivID).getReligionID() && Game.getProvince(this.iProvinceID).religionConversion == null) {
                        InGame_ConvertReligion.iProvinceID = InGame_ProvinceInfo.iProvinceID;
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 0 && InGame_ConvertReligion.iProvinceID == this.iProvinceID) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        } else {
                            Game.menuManager.rebuildInGame_ConvertReligion();
                        }
                    }

                    //ANALYTICALENGINE START
                    Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                    try {
                        invocable.invokeFunction("parseOnProvinceReligionClick", Game.iActiveProvince);
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    //ANALYTICALENGINE END
                }

                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverReligion(this.iProvinceID, true, true);
                }

                public void actionElementPPM() {
                    InGame_MapModes.actionReligion();
                }

                public void updateHovered() {
                    super.updateHovered();
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_RELIGION && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_RELIGION_HOVER);
                    }

                }

                public void setIsHovered(boolean isHovered) {
                    super.setIsHovered(isHovered);
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_RELIGION && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_RELIGION_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    }

                }
            });
            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2_RevolutionaryRisk(iProvinceID, "", Images.revolutionRisk, buttonX, buttonY, statsRightW, pieDim) {
                public void updateHovered() {
                    super.updateHovered();
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_UNREST && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_UNREST_HOVER);
                    }

                }

                public void setIsHovered(boolean isHovered) {
                    super.setIsHovered(isHovered);
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_UNREST && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_UNREST_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    }

                }

                public void actionElement() {
                    InGame_ProvinceInfo.actionUnrest(this.iProvinceID);

                    //ANALYTICALENGINE START
                    Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                    try {
                        invocable.invokeFunction("parseOnProvinceUnrestClick", Game.iActiveProvince);
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    //ANALYTICALENGINE END
                }

                public void actionElementPPM() {
                    InGame_MapModes.actionUnrest();
                }

                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverUnrest(this.iProvinceID, true, true);
                }
            });
            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2_Infrastructure(iProvinceID, "", Images.infrastructure, buttonX, buttonY, statsRightW, pieDim) {
                public int getSFX() {
                    return Game.soundsManager.getInfrastructure();
                }

                public void actionElement() {
                    if (InGame_ProvinceInfo.actionInfrastructure(this.iProvinceID)) {
                        MenuManager var10000 = Game.menuManager;
                        MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_ProvinceInfo.this.getPosX(), this.getPosY() + this.getHeight() / 2 + InGame_ProvinceInfo.this.getPosY(), this.getWidth(), this.getHeight()) {
                            public Color getColor() {
                                return Colors.HOVER_LEFT;
                            }
                        });
                    }

                    //ANALYTICALENGINE START
                    Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                    try {
                        invocable.invokeFunction("parseOnProvinceInfrastructureClick", Game.iActiveProvince);
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    //ANALYTICALENGINE END
                }

                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverInfrastructure(this.iProvinceID, true);
                }

                public void actionElementPPM() {
                    InGame_MapModes.actionInfrastructure();
                }

                public void updateHovered() {
                    super.updateHovered();
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_INFRASTRUCTURE && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_INFRASTRUCTURE_HOVER);
                    }

                }

                public void setIsHovered(boolean isHovered) {
                    super.setIsHovered(isHovered);
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_INFRASTRUCTURE && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_INFRASTRUCTURE_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    }

                }
            });
            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            boolean showPriceIncome = Game.getProvince(iProvinceID).getResourceID() >= 0 && Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getTechResearched(((ResourcesManager.Resources)ResourcesManager.lResources.get(Game.getProvince(iProvinceID).getResourceID())).RequiredTechID);
            menuElements.add(new TextIcon_Resource(ResourcesManager.getResourceName(Game.getProvince(iProvinceID).getResourceID()), Game.getProvince(iProvinceID).getResourceID(), buttonX, buttonY, statsRightW * 2 + CFG.PADDING - (showPriceIncome ? CFG.TEXT_HEIGHT_SMALL + CFG.PADDING * 3 : 0), pieDim) {
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_GoodsMarket()) {
                        Game.menuManager.setVisibleInGame_GoodsMarket(false);
                    } else {
                        InGame_GoodsMarket.iActiveCivID = Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID() > 0 ? Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID() : Game.player.iCivID;
                        Game.menuManager.rebuildInGame_GoodsMarket();
                    }

                }

                public void actionElementPPM() {
                    InGame_MapModes.actionGoods();
                }

                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverResource(InGame_ProvinceInfo.iProvinceID, this.resourceID, true);
                }
            });
            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            if (showPriceIncome) {
                float tIncome = ResourcesManager.getMonthlyIncome(iProvinceID, Game.getProvince(iProvinceID).getResourceID());
                menuElements.add(new TextIcon_VerticalProductionIncome("" + CFG.getPrecision2(tIncome, tIncome >= 100.0F ? 10 : 100), Images.gold, iProvinceID, buttonX, buttonY, CFG.TEXT_HEIGHT_SMALL + CFG.PADDING * 2, pieDim) {
                    public void actionElement() {
                        if (Game.getProvince(this.iProvinceID).getResourceID() >= 0) {
                            InGame_Goods_LargestProducers.RESOURCE_ID = Game.getProvince(this.iProvinceID).getResourceID();
                            InGame_Goods_LargestProducers.lTime = 0L;
                            InGame_ProvinceInfo.lTime = 0L;
                            Game.menuManager.rebuildInGame_Goods_LargestProducers();
                        }

                    }

                    public void actionElementPPM() {
                        InGame_MapModes.actionGoods();
                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        int resourceID = Game.getProvince(this.iProvinceID).getResourceID();
                        if (resourceID >= 0) {
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(Game.lang.get("Resource") + ": ", ((ResourcesManager.Resources)ResourcesManager.lResources.get(resourceID)).Name, resourceID, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        } else {
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Resource") + ": ", Game.lang.get("None"), Images.resourceNone, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }

                        if (resourceID >= 0) {
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", "+" + CFG.getPrecision2(ResourcesManager.getMonthlyIncome(this.iProvinceID, resourceID), 100), Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(" = " + Game.lang.get("Price") + " * " + Game.lang.get("ProductionEfficiency") + " %", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData, false));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProductionEfficiency") + ": ", CFG.getPrecision2(ResourcesManager.getProductionEfficiency(this.iProvinceID) * 100.0F, 10) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(" = (" + CFG.getPrecision2(GameValues.production.BASE_PRODUCTION_EFFICIENCY * 100.0F, 100) + " + " + Game.lang.get("Economy") + " * " + CFG.getPrecision2(GameValues.production.PRODUCTION_EFFICIENCY_PER_ECONOMY * 100.0F, 100) + ") * " + Game.lang.get("Bonuses"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                            nElements.add(new MenuElement_HoverElement(nData, false));
                            nData.clear();
                            if (Game.getCiv(Game.getProvince(this.iProvinceID).getCivID()).getCorruption() > 0.005F) {
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Corruption") + ": ", "-" + CFG.getPrecision2(Game.getCiv(Game.getProvince(this.iProvinceID).getCivID()).getCorruption() * 100.0F, 10) + "%", Images.corruption, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_NEGATIVE));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }

                            if (!Game.getProvince(this.iProvinceID).haveACore(Game.getProvince(this.iProvinceID).getCivID())) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NonCoreProvince") + ":", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                                nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(this.iProvinceID).getCivID(), CFG.PADDING, CFG.PADDING));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.core.BASE_INCOME_NON_CORE * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }

                            if (Game.getProvince(this.iProvinceID).getReligion() != Game.getCiv(Game.getProvince(this.iProvinceID).getCivID()).getReligionID()) {
                                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DifferentReligion") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.religion.BASE_INCOME_DIFFERENT_RELIGION * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            }
                        }

                        this.menuElementHover = new MenuElement_Hover(nElements) {
                            public int getMinPosX() {
                                return InGame_ProvinceInfo.HOVER_POSX;
                            }

                            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                                super.draw(oSB, nPosX, nPosY);
                            }
                        };
                    }
                });
            }

            buttonY = 0;
            int i = 0;

            for(int iSize = menuElements.size(); i < iSize; ++i) {
                if (buttonY < ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING) {
                    buttonY = ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING;
                }
            }

            if (Game.getCiv(Game.player.iCivID).getNukes() > 0) {
                menuElements.add(new TextIcon2_Nuke(Game.lang.get("Nuke"), Images.nuke, paddingLeft, buttonY, statsRightW, ImageManager.getImage(Images.buildingsFrameSmall).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 4) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DropAtomicBomb"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.nuke, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("AtomicBombs") + ": ", CFG.FONT_REGULAR));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).getNukes(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.nuke, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Population") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("-" + CFG.getPrecision2(Game.getAtomicBombCasualties(InGame_ProvinceInfo.iProvinceID) * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Economy") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("-" + CFG.getPrecision2(GameValues.atomic.ATOMIC_BOMB_ECONOMY * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Devastation") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.atomic.ATOMIC_BOMB_DEVASTATION * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.devastation, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Army") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("-" + CFG.getPrecision2(GameValues.atomic.ATOMIC_BOMB_ARMY * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).provBonuses.CasualtiesNuclearAttacks != 0.0F) {
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CasualtiesFromNuclearAttacks") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getProvince(InGame_ProvinceInfo.iProvinceID).provBonuses.CasualtiesNuclearAttacks * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.nuke, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }

                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }

                    public void actionElement() {
                        if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID() == Game.player.iCivID) {
                            if (InGame_ProvinceInfo.atomicOwnOnceTimeInfo) {
                                Game.menuManager.addToastInsufficient(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getProvinceName() + ": ", Game.getCiv(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID()).getCivName(), Images.nuke);
                                InGame_ProvinceInfo.atomicOwnOnceTimeInfo = false;
                            }
                        } else if (!DiplomacyManager.isAtWar(Game.player.iCivID, Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID())) {
                            Game.menuManager.addToast_Error(Game.lang.get("AtPeace"), Images.peace);
                        } else if (Game.getCiv(Game.player.iCivID).getNukes() > 0) {
                            Game.dropAtomicBomb(Game.player.iCivID, InGame_ProvinceInfo.iProvinceID);
                            this.menuElementHover = null;
                        } else {
                            Game.menuManager.addToastInsufficient(Game.lang.get("AtomicBombs") + ": ", "" + Game.getCiv(Game.player.iCivID).getNukes(), Images.nuke);
                        }

                    }
                });
                tempX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            }

            menuElements.add(new ButtonStatsRectIMG_Loot("", Images.loot, tempX, buttonY + ImageManager.getImage(Images.buildingsFrameSmall).getHeight(), statsRightW * 2 + CFG.PADDING, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ImageManager.getImage(Images.loot).getWidth(), iProvinceID) {
                public void actionElement() {
                    if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_LOOT) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    } else {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_LOOT);
                    }

                    //ANALYTICALENGINE START
                    Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                    try {
                        invocable.invokeFunction("parseOnProvinceLootClick", Game.iActiveProvince);
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    //ANALYTICALENGINE END
                }

                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverLoot(InGame_ProvinceInfo.iProvinceID, true);
                }

                public void actionElementPPM() {
                    InGame_MapModes.actionLoot();
                }

                public void updateHovered() {
                    super.updateHovered();
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_LOOT && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_LOOT_HOVER);
                    }

                }

                public void setIsHovered(boolean isHovered) {
                    super.setIsHovered(isHovered);
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_LOOT && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_LOOT_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    }

                }
            });
            menuElements.add(new TextIcon2("" + CFG.getPrecision2(Game.getProvince(iProvinceID).fProvinceValue, 10), Images.victoryPoints, tempX, buttonY, statsRightW, ImageManager.getImage(Images.buildingsFrameSmall).getHeight() - CFG.PADDING) {
                public void actionElement () {
                    //ANALYTICALENGINE START
                    Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                    try {
                        invocable.invokeFunction("parseOnProvinceValueClick", Game.iActiveProvince);
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    //ANALYTICALENGINE END
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceValue") + ": ", CFG.getPrecision2(Game.getProvince(InGame_ProvinceInfo.iProvinceID).fProvinceValue, 10), Images.victoryPoints, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BaseProvinceValue") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.province.PROVINCE_VALUE_BASE, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_POSITIVE));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).isCapital) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Capital") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.province.PROVINCE_VALUE_CAPITAL, 10), CFG.FONT_BOLD_SMALL, Colors.HOVER_POSITIVE));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }

                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Economy") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getProvinceValue_Economy(), 10), CFG.FONT_BOLD_SMALL, Colors.HOVER_POSITIVE));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(" = min(" + Game.lang.get("Economy") + " * " + CFG.getPrecision2(GameValues.province.PROVINCE_VALUE_PER_ECONOMY, 1000) + ", " + CFG.getPrecision2(GameValues.province.PROVINCE_VALUE_PER_ECONOMY_MAX, 1000) + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GrowthRate") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getProvinceValue_GrowthRate(), 10), CFG.FONT_BOLD_SMALL, Colors.HOVER_POSITIVE));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(" = min(" + Game.lang.get("GrowthRate") + " * " + CFG.getPrecision2(GameValues.province.PROVINCE_VALUE_PER_GROWTH_RATE, 1000) + ", " + CFG.getPrecision2(GameValues.province.PROVINCE_VALUE_PER_GROWTH_RATE_MAX, 1000) + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).wonderID >= 0) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Wonder") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getWonderBuilt() ? "+" + CFG.getPrecision2(GameValues.province.PROVINCE_VALUE_WONDER_BUILT, 10) : "0", CFG.FONT_BOLD_SMALL, Colors.HOVER_POSITIVE));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }

                    this.menuElementHover = new MenuElement_Hover(nElements) {
                        public int getMinPosX() {
                            return InGame_ProvinceInfo.HOVER_POSX;
                        }

                        public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                            nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                            super.draw(oSB, nPosX, nPosY);
                        }
                    };
                }

                public void actionElementPPM() {
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_PROVINCE_VALUE) {
                        this.menuElementHover = null;
                    }

                }

                public void updateHovered() {
                    super.updateHovered();
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_PROVINCE_VALUE && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_PROVINCE_VALUE_HOVER);
                    }

                }

                public void setIsHovered(boolean isHovered) {
                    super.setIsHovered(isHovered);
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_PROVINCE_VALUE && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_PROVINCE_VALUE_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    }

                }
            });
            tempX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2_Devastation("" + CFG.getPrecision2(Game.getProvince(iProvinceID).getDevastation() * 100.0F, 100) + "%", Images.devastation, tempX, buttonY, statsRightW, ImageManager.getImage(Images.buildingsFrameSmall).getHeight() - CFG.PADDING, iProvinceID) {
                public void actionElement() {
                    //ANALYTICALENGINE START
                    Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                    try {
                        invocable.invokeFunction("parseOnProvinceDevastationClick", Game.iActiveProvince);
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    //ANALYTICALENGINE END
                }

                public void actionElementPPM() {
                    InGame_MapModes.actionDevastation();
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Devastation") + ": ", CFG.getPrecision2(Game.getProvince(this.iProvinceID).getDevastation() * 100.0F, 100) + "%", Images.devastation, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (Game.getProvince(this.iProvinceID).isOccupied()) {
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MonthlyChange") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(GameValues.siege.DEVASTATION_PER_MONTH_OCCUPIED * 100.0F, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.devastation, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    } else if (Game.getProvince(this.iProvinceID).getDevastation() > 5.0E-5F) {
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MonthlyChange") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(GameValues.siege.DEVASTATION_PER_MONTH_DEFAULT * 100.0F, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.devastation, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }

                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Max") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.siege.DEVASTATION_MAX * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.devastation, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Devastation"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements) {
                        public int getMinPosX() {
                            return InGame_ProvinceInfo.HOVER_POSX;
                        }

                        public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                            nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                            super.draw(oSB, nPosX, nPosY);
                        }
                    };
                }

                public void updateHovered() {
                    super.updateHovered();
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_DEVASTATION && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_DEVASTATION_HOVER);
                    }

                }

                public void setIsHovered(boolean isHovered) {
                    super.setIsHovered(isHovered);
                    if (CFG.isDesktop() && GameValues.mapModes.PROVINCE_INFO_HOVER_DEVASTATION && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_DEVASTATION_HOVER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                    }

                }
            });
            tempX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            if (Game.getProvince(iProvinceID).wonderID >= 0) {
                tempX += CFG.PADDING;
                menuElements.add(new TextBGBot(((WondersManager.Wonders)WondersManager.wonders.get(Game.getProvince(iProvinceID).wonderID)).Name, -1, tempX, buttonY + CFG.PADDING + ImageManager.getImage(Images.wonderFrame).getHeight(), ImageManager.getImage(Images.wonderFrame).getWidth(), CFG.TEXT_HEIGHT + CFG.PADDING * 2) {
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_Wonder()) {
                            Game.menuManager.setVisibleInGame_Wonder(false);
                        } else {
                            InGame_Wonder.iProvinceID = InGame_ProvinceInfo.iProvinceID;
                            Game.menuManager.rebuildInGame_Wonder();
                        }

                    }

                    public void buildElementHover() {
                        if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).wonderID >= 0) {
                            this.menuElementHover = ButtonWonderProvince.getHoverWonder(InGame_ProvinceInfo.iProvinceID, Game.getProvince(InGame_ProvinceInfo.iProvinceID).wonderID, true);
                        } else {
                            this.menuElementHover = null;
                        }

                    }

                    protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                        try {
                            if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).getWonderBuilt()) {
                                oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.5F));
                                Renderer.drawBoxBOT(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0F);
                                oSB.setColor(new Color(Colors.COLOR_TEXT_MODIFIER_POSITIVE.r, Colors.COLOR_TEXT_MODIFIER_POSITIVE.g, Colors.COLOR_TEXT_MODIFIER_POSITIVE.b, 0.45F));
                                Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
                                oSB.setColor(Color.WHITE);
                            } else {
                                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                            }
                        } catch (Exception ex) {
                            CFG.exceptionStack(ex);
                        }

                    }
                });
                menuElements.add(new ButtonWonderProvince(tempX, buttonY + CFG.PADDING, Game.getProvince(iProvinceID).wonderID, iProvinceID) {
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_Wonder()) {
                            if (InGame_Wonder.iProvinceID != this.iProvinceID) {
                                InGame_Wonder.iProvinceID = this.iProvinceID;
                                Game.menuManager.setVisibleInGame_Wonder(false);
                                Game.menuManager.rebuildInGame_Wonder();
                            } else {
                                Game.menuManager.setVisibleInGame_Wonder(false);
                            }
                        } else {
                            InGame_Wonder.iProvinceID = this.iProvinceID;
                            Game.menuManager.rebuildInGame_Wonder();
                        }

                    }

                    public void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5F));
                        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() - CFG.PADDING + iTranslateX, this.getPosY() - CFG.PADDING + iTranslateY, this.getWidth() + CFG.PADDING * 2, this.getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 4, 1.0F);
                        oSB.setColor(Color.WHITE);
                        super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                    }
                });
                tempX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING * 2;
            }

            InGame_ProvinceInfoBuildings.mPosX = tempX;
            InGame_ProvinceInfoBuildings.mPosY = buttonY;
            InGame_ProvinceInfoBuildings.mWidth = menuWidth - tempX - Images.boxTitleBORDERWIDTH - CFG.PADDING;
            if (Game.getProvince(iProvinceID).isOccupied() && Game.getProvinceData(iProvinceID).getOccupiedByCivID() < 0) {
                buttonY += ImageManager.getImage(Images.buildingsFrameSmall).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 4 + CFG.PADDING;
                i = Game.revolutionManager.declareIndependence_TurnsLeft(iProvinceID);
                menuElements.add(new ButtonStatsRectIMG_Bonuses2_Value(Game.lang.get("DaysX", i) + ": ", Game.lang.get("Liberation"), Images.revolutionRisk, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - Images.boxTitleBORDERWIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ImageManager.getImage(Images.revolutionRisk).getWidth()) {
                    int TurnID;

                    {
                        this.TurnID = Game_Calendar.TURN_ID;
                    }

                    public String getTextToDraw() {
                        if (this.TurnID != Game_Calendar.TURN_ID) {
                            if (Game.getProvince(InGame_ProvinceInfo.iProvinceID).isOccupied() && Game.getProvinceData(InGame_ProvinceInfo.iProvinceID).getOccupiedByCivID() < 0) {
                                int turnsLeft = Game.revolutionManager.declareIndependence_TurnsLeft(InGame_ProvinceInfo.iProvinceID);
                                this.setText(Game.lang.get("DaysX", turnsLeft) + ": ");
                                this.TurnID = Game_Calendar.TURN_ID;
                            } else {
                                this.setText("---");
                                this.sText2 = "";
                                this.TurnID = Game_Calendar.TURN_ID;
                            }
                        }

                        return super.getTextToDraw();
                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Revolt"), CFG.FONT_BOLD, Colors.HOVER_LEFT));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.rebelsFlag, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements) {
                            public int getMinPosX() {
                                return InGame_ProvinceInfo.HOVER_POSX;
                            }

                            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                                super.draw(oSB, nPosX, nPosY);
                            }
                        };
                    }
                });
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }

            if (Game.getProvinceData9(iProvinceID).getColonizationStartedTurnID() >= 0) {
                buttonY += ImageManager.getImage(Images.buildingsFrameSmall).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 4 + CFG.PADDING;
                menuElements.add(new ButtonStatsRectIMG_Bonuses2_Value(Game.lang.get("SettlementProgress") + ": ", CFG.getPrecision2(ColonizationManager.getSettlementEstablishmentProgress(iProvinceID) * 100.0F, 10) + "%", Images.population, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - Images.boxTitleBORDERWIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ImageManager.getImage(Images.population).getWidth()) {
                    public String getTextToDraw() {
                        if (this.iValue != (int)(ColonizationManager.getSettlementEstablishmentProgress(InGame_ProvinceInfo.iProvinceID) * 10000.0F)) {
                            float tProgress = ColonizationManager.getSettlementEstablishmentProgress(InGame_ProvinceInfo.iProvinceID) * 100.0F;
                            this.sText2 = CFG.getPrecision2(tProgress, 10) + "%";
                            Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(CFG.FONT_BOLD_SMALL), this.sText2);
                            this.iTextBonusW = (int)Renderer.glyphLayout.width;
                            this.iValue = (int)(tProgress * 10000.0F);
                        }

                        return super.getTextToDraw();
                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("SettlementProgress") + ": ", CFG.FONT_BOLD, Colors.HOVER_LEFT));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(ColonizationManager.getSettlementEstablishmentProgress(InGame_ProvinceInfo.iProvinceID) * 100.0F, 10) + "%", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.population, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game_Calendar.getDate_ByTurnID(Math.max(Game.getProvinceData9(InGame_ProvinceInfo.iProvinceID).getColonizationStartedTurnID() + GameValues.colonization.COLONIZATION_TIME, Game_Calendar.TURN_ID + 1)), "", Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GrowthRate") + ": ", "+" + Game.getProvinceData9(InGame_ProvinceInfo.iProvinceID).getColonizationGrowthRateExtra() + "%", Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements) {
                            public int getMinPosX() {
                                return InGame_ProvinceInfo.HOVER_POSX;
                            }

                            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                                super.draw(oSB, nPosX, nPosY);
                            }
                        };
                    }
                });
                var10000 = buttonY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }
        } else {
            int tWidthIcons = (menuWidth - ImageManager.getImage(Images.terrainFrame).getWidth() - Images.boxTitleBORDERWIDTH - CFG.PADDING * 2 - CFG.PADDING * 4) / 4;
            int tHeightIcons = ImageManager.getImage(Images.terrainFrame).getHeight() - CFG.PADDING;
            int buttonX = ImageManager.getImage(Images.terrainFrame).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2_ProvinceIncome(iProvinceID, "" + CFG.getPrecision2(Game.getProvince(iProvinceID).getProvinceIncome() - Game.getProvince(iProvinceID).fProvinceMaintenance, 100), Images.gold, buttonX, buttonY, tWidthIcons, tHeightIcons) {
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverProvinceIncome(this.iProvinceID, true);
                }

                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_ProvinceBonuses()) {
                        Game.menuManager.setVisibleInGame_ProvinceBonuses(false, false);
                    } else {
                        InGame_ProvinceBonuses.iProvinceID = this.iProvinceID;
                        Game.menuManager.rebuildInGame_ProvinceBonuses();
                        Game.menuManager.setVisibleInGame_ProvinceBonuses(true, true);
                    }

                }
            });
            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2_GrowthRate(iProvinceID, "" + CFG.getPrecision2(Game.getProvince(iProvinceID).getGrowthRateWithBonuses(), 10) + "%", Images.populationGrowth, buttonX, CFG.PADDING, tWidthIcons, tHeightIcons) {
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverPopulationGrowth(this.iProvinceID, true, true);
                }
            });
            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon2_Economy(iProvinceID, "" + CFG.getPrecision2(Game.getProvince(iProvinceID).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, buttonX, buttonY, tWidthIcons, tHeightIcons) {
                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverEconomy(this.iProvinceID, true);
                }
            });
            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            menuElements.add(new TextIcon_Resource(ResourcesManager.getResourceName(Game.getProvince(iProvinceID).getResourceID()), Game.getProvince(iProvinceID).getResourceID(), buttonX, buttonY, tWidthIcons, tHeightIcons) {
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_GoodsMarket()) {
                        Game.menuManager.setVisibleInGame_GoodsMarket(false);
                    } else {
                        InGame_GoodsMarket.iActiveCivID = Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID() > 0 ? Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID() : Game.player.iCivID;
                        Game.menuManager.rebuildInGame_GoodsMarket();
                    }

                }

                public void buildElementHover() {
                    this.menuElementHover = InGame_ProvinceInfo.getHoverResource(InGame_ProvinceInfo.iProvinceID, this.resourceID, true);
                }
            });
            var10000 = buttonX + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
            buttonY = ((MenuElement)menuElements.get(0)).getPosY() + ((MenuElement)menuElements.get(0)).getHeight() + CFG.PADDING;
            menuElements.add(new Text_Desc3(Game.lang.get("ColonizationTutorialText") + " " + Game.lang.get("ThePopulationOfTheProvinceWillBeEqualToTheNumberOfSettlers"), paddingLeft, buttonY, menuWidth - paddingLeft * 2 - Images.boxTitleBORDERWIDTH, getSecondLineH() + CFG.PADDING + ImageManager.getImage(Images.buildingsFrameSmall).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 4) {
                public void actionElement() {
                    if (ColonizationManager.establishSettlement(Game.player.iCivID, InGame_ProvinceInfo.iProvinceID, 0)) {
                        InGame_Info.iCivID = Game.player.iCivID;
                        InGame_Info.iCivID2 = 0;
                        Game.menuManager.rebuildInGame_Info(Game.lang.get("SettlementEstablished") + ": " + Game.getProvince(InGame_ProvinceInfo.iProvinceID).getProvinceName(), Game.lang.get("Population") + ": " + CFG.getNumberWithSpaces("" + Game.getProvince(InGame_ProvinceInfo.iProvinceID).getPopulationTotal()));
                        InGame_Info.imgID = Images.infoCrown;
                        Game.menuManager.rebuildInGame_ProvinceInfo(true);
                    }

                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    int numOfSettlers = ColonizationManager.getNumberOfSettlers(Game.player.iCivID, InGame_ProvinceInfo.iProvinceID);
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("EstablishSettlement"), CFG.FONT_BOLD, numOfSettlers > 0 ? Colors.HOVER_GOLD : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.populationGrowth, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Settlers") + ": ", CFG.getNumberWithSpaces("" + numOfSettlers), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ThePopulationOfTheProvinceWillBeEqualToTheNumberOfSettlers"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumNumberOfSettlers") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + ((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).REGIMENT_SIZE * GameValues.colonization.COLONIZATION_MAX_SETTLERS), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements) {
                        public int getMinPosX() {
                            return InGame_ProvinceInfo.HOVER_POSX;
                        }

                        public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                            nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                            super.draw(oSB, nPosX, nPosY);
                        }
                    };
                }
            });
            if (GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD || GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_PLAYER_TRIBAL && Game.ideologiesManager.getIdeology(Game.getCiv(Game.player.iCivID).getIdeologyID()).TRIBAL) {
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                int c0W = (menuWidth - paddingLeft * 2 - Images.boxTitleBORDERWIDTH - CFG.PADDING) / 2;
                int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
                menuElements.add(new ButtonStatsRectIMG_Bonuses2_Value(Game.lang.get("Cost") + ": ", "" + CFG.getPrecision2(GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST, 100), Images.gold, paddingLeft, buttonY, c0W, buttonH, ImageManager.getImage(Images.population).getWidth()) {
                });
                menuElements.add(new ButtonGame(Game.lang.get("EstablishSettlement"), CFG.FONT_REGULAR, -1, paddingLeft + c0W + CFG.PADDING, buttonY, c0W, buttonH, true) {
                    public void actionElement() {
                        if (Game.getCiv(Game.player.iCivID).fGold < GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST, 100), Images.gold);
                        } else if (ColonizationManager.establishSettlement_Gold(Game.player.iCivID, InGame_ProvinceInfo.iProvinceID)) {
                            Game.menuManager.rebuildInGame_ProvinceInfo(true);
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("EstablishSettlement"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.populationGrowth, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Settlers") + ": ", CFG.getNumberWithSpaces("" + GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_SETTLERS), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Cost") + ": ", CFG.getPrecision2(GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements) {
                            public int getMinPosX() {
                                return InGame_ProvinceInfo.HOVER_POSX;
                            }

                            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                                super.draw(oSB, nPosX, nPosY);
                            }
                        };
                    }
                });
                var10000 = buttonY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }
        }

        buttonY = 0;
        int i = 0;

        for(int iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING) {
                buttonY = ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING;
            }
        }

        menuHeight = Math.max(menuHeight, buttonY);
        int menuY = CFG.GAME_HEIGHT - menuHeight;
        InGame_ProvinceInfoBuildings.mPosY += menuY;
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        menuPosY = menuY;
        menuW = menuWidth;
        HOVER_POSX = menuX + menuWidth + Images.boxTitleBORDERWIDTH + CFG.PADDING;
        HOVER_POSY = menuY;
        this.initMenu(new MenuTitleIMG_Flag_TextRight(Game.getProvince(iProvinceID).getProvinceName(), Game.getProvince(iProvinceID).getWasteland() >= 0 ? Game.lang.get("Wasteland") : (Game.getProvince(iProvinceID).getCivID() == 0 ? Game.lang.get("UncolonizedLand") : Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCivName() + (Game.getProvince(iProvinceID).isOccupied() ? " - " + Game.lang.get("OccupiedByX", Game.getProvinceData(iProvinceID).getOccupiedByCivID() < 0 ? Game.lang.get("Rebels") : Game.getCiv(Game.getProvinceData(iProvinceID).getOccupiedByCivID()).getCivName()) : "")), false, false, Images.title580, Game.mapCities.getProvinceCityTitle(iProvinceID)) {
            public int getFlagCivID() {
                return Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID();
            }

            public long getTime() {
                return InGame_ProvinceInfo.lTime2;
            }

            public void onHovered() {
                Game.menuManager.setOrderOfMenu_InGameProvinceInfo();
            }

            public String getText2() {
                return Keyboard.keyboardActionType == KeyboardActionType.INGAME_RENAME_PROVINCE ? InGame_ProvinceInfo.renameProvince + Keyboard.getKeyboardVerticalLine() : Game.getProvince(InGame_ProvinceInfo.iProvinceID).getProvinceName();
            }

            public void action() {
                if (Keyboard.keyboardActionType != KeyboardActionType.INGAME_RENAME_PROVINCE) {
                    if (!CFG.isDesktop() && !InGame_ProvinceInfo.this.confirmEditProvinceName) {
                        InGame_ProvinceInfo.this.confirmEditProvinceName = true;
                        Game.menuManager.addToastGold(Game.lang.get("Edit") + ": " + Game.lang.get("ProvinceName"), Images.provinces);
                    } else {
                        InGame_ProvinceInfo.renameProvince = Game.getProvince(InGame_ProvinceInfo.iProvinceID).getProvinceName();
                        Game.keyboard.showKeyboard(KeyboardActionType.INGAME_RENAME_PROVINCE, Game.getProvince(InGame_ProvinceInfo.iProvinceID).getProvinceName());
                        Game.menuManager.addToastGold(Game.lang.get("ProvinceName") + ": " + Game.getProvince(InGame_ProvinceInfo.iProvinceID).getProvinceName(), Images.provinces);
                    }
                } else {
                    Game.keyboard.hideKeyboard();
                }

            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        if (Game.iActiveProvince < 0) {
            Game.menuManager.setVisibleInGame_ProvinceInfo(false);
        } else {
            if (lTime + 60L >= CFG.currentTimeMillis) {
                iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)((float)CFG.BUTTON_WIDTH * ((float)(CFG.currentTimeMillis - lTime) / 60.0F));
                iTranslateY += (int)((float)CFG.BUTTON_HEIGHT / 2.0F - (float)(CFG.BUTTON_HEIGHT / 2) * ((float)(CFG.currentTimeMillis - lTime) / 60.0F));
            }

            nTranslateX = iTranslateX;
            nTranslateY = iTranslateY;
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
            Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop580, Images.insideBot580);
            ImageManager.getImage(this.imageOverID).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(this.imageOverID).getHeight()));
            oSB.setColor(Color.WHITE);
            super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
        }
    }

    public static int getSecondLineH() {
        return ImageManager.getImage(Images.terrainFrame).getHeight() - CFG.TEXT_HEIGHT - CFG.PADDING * 3;
    }

    public void updateLanguage() {
        super.updateLanguage();
    }

    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.gameActiveProvince.resetLastActiveProvince();
        Game.setActiveProvinceID(-1);
        Game.menuManager.setVisibleInGame_ProvinceInfo(false);
        Keyboard var10000 = Game.keyboard;
        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.INGAME_RENAME_PROVINCE) {
            Game.keyboard.hideKeyboard();
        }

    }

    public void setVisible(boolean visible) {
        if (!this.getVisible()) {
            lTime = CFG.currentTimeMillis;
            lTime2 = lTime;
        }

        Keyboard var10000 = Game.keyboard;
        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.INGAME_RENAME_PROVINCE) {
            Game.keyboard.hideKeyboard();
        }

        super.setVisible(visible);
    }

    public boolean getVisible() {
        return super.getVisible() && Game.mapBG.getHideMenuZoomOut();
    }

    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameProvinceInfo();
    }

    public static MenuElement_Hover getHoverEconomy(int iProvinceID, boolean inProvinceInfo) {
        return getHoverEconomy(iProvinceID, inProvinceInfo, true);
    }

    public static MenuElement_Hover getHoverEconomy(int iProvinceID, boolean inProvinceInfo, boolean showInvest) {
        try {
            List<MenuElement_HoverElement> nElements = new ArrayList();
            List<MenuElement_HoverElement_Type> nData = new ArrayList();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getProvince(iProvinceID).getEconomyWithBonuses(), 100), Game_Calendar.IMG_ECONOMY, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MonthlyIncome") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getIncomeFromEconomy(iProvinceID), 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(" = " + Game.lang.get("Economy") + " * " + CFG.getPrecision2(GameValues.economy.INCOME_ECONOMY_PER_ECONOMY, 1000), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
            if (Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCorruption() > 0.005F) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Corruption") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nData.add(new MenuElement_HoverElement_Type_Text("-" + CFG.getPrecision2(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCorruption() * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.corruption, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
                nData.clear();
            }

            if (!Game.getProvince(iProvinceID).haveACore(Game.getProvince(iProvinceID).getCivID())) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NonCoreProvince") + ":", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getCivID(), CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.core.BASE_INCOME_NON_CORE * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
                nData.clear();
            }

            if (Game.getProvince(iProvinceID).getReligion() != Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getReligionID()) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DifferentReligion") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.religion.BASE_INCOME_DIFFERENT_RELIGION * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
                nData.clear();
            }

            if (Game.getProvince(iProvinceID).getDevastation() > 5.0E-5F) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Devastation") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(iProvinceID).getDevastation() * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.devastation, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceIncome") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nData.add(new MenuElement_HoverElement_Type_Text("-" + CFG.getPrecision2(Game.getProvince(iProvinceID).getDevastation() * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
                nData.clear();
            }

            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProductionEfficiency") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(ResourcesManager.getProductionEfficiency_FromEconomy(iProvinceID) * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(" = " + Game.lang.get("Economy") + " * " + CFG.getPrecision2(GameValues.production.PRODUCTION_EFFICIENCY_PER_ECONOMY * 100.0F, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumEconomy") + ": ", CFG.FONT_REGULAR, !Game.canInvestInEconomy(iProvinceID) ? Colors.HOVER_LEFT : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getMaxEconomy(iProvinceID), 100), CFG.FONT_BOLD, !Game.canInvestInEconomy(iProvinceID) ? Colors.HOVER_GOLD : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_HoverElement_Type_Text("[" + CFG.getPrecision2(Game.getProvince(iProvinceID).getEconomyWithBonuses() / Game.getMaxEconomy(iProvinceID) * 100.0F, 10) + "%]", CFG.FONT_REGULAR, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(" = " + Game.lang.get("GrowthRate") + " * " + CFG.getPrecision2(GameValues.economy.MAX_ECONOMY_GROWTH_RATE, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, Game.canInvestInEconomy(iProvinceID)));
            nData.clear();
            if ((Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getPuppetOfCivID() == Game.player.iCivID) && showInvest) {
                if ((Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getPuppetOfCivID() == Game.player.iCivID) && Game.getProvince(iProvinceID).iProvinceInvestSize > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InvestProgress") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", ((ProvinceInvest)Game.getProvince(iProvinceID).provinceInvestDaysLeft.get(0)).daysLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_HoverElement_Type_Text("[" + CFG.getPrecision2((1.0F - (float)((ProvinceInvest)Game.getProvince(iProvinceID).provinceInvestDaysLeft.get(0)).daysLeft / (float)((ProvinceInvest)Game.getProvince(iProvinceID).provinceInvestDaysLeft.get(0)).investTime) * 100.0F, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (Game.getProvince(iProvinceID).iProvinceInvestSize > 1) {
                        int tDays = 0;

                        for(int i = 0; i < Game.getProvince(iProvinceID).iProvinceInvestSize; ++i) {
                            tDays += ((ProvinceInvest)Game.getProvince(iProvinceID).provinceInvestDaysLeft.get(i)).daysLeft;
                        }

                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InQueue") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getProvince(iProvinceID).iProvinceInvestSize - 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text(" [" + Game.lang.get("DaysX", tDays) + "]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }

                if (!Game.canInvestInEconomy(iProvinceID)) {
                    nData.add(new MenuElement_HoverElement_Type_Empty());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToInvestInEconomy"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY_UP, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (Game.getProvince(iProvinceID).getCivID() != Game.player.iCivID) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCivName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getCivID(), CFG.PADDING, 0));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.vassal, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }

                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getInvestCost(iProvinceID), 10), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getInvestCost_Legacy(iProvinceID), 10), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2(GameValues.economy.INVEST_COST, 10) + " + " + Game.lang.get("Economy") + " * " + CFG.getPrecision2(GameValues.economy.INVEST_COST_PER_ECONOMY, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AfterXDays", Game.getInvestTime(Game.player.iCivID)) + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                    nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Economy") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getInvestEconomyGrowth(iProvinceID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
                    nData.clear();
                    float tIncomeProduction = (ResourcesManager.getMonthlyIncome(iProvinceID, Game.getProvince(iProvinceID).getResourceID(), ResourcesManager.getProductionEfficiency(iProvinceID) + ResourcesManager.getProductionEfficiency_FromEconomy(Game.getInvestEconomyGrowth(iProvinceID))) - ResourcesManager.getMonthlyIncome(iProvinceID, Game.getProvince(iProvinceID).getResourceID(), ResourcesManager.getProductionEfficiency(iProvinceID))) / 100.0F;
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MonthlyIncome") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getIncomeFromEconomy_Invest(iProvinceID) + tIncomeProduction, 1000), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProductionEfficiency") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(ResourcesManager.getProductionEfficiency_FromEconomy(Game.getInvestEconomyGrowth(iProvinceID)), 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InvestmentPayoff") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + Game_Calendar.getNumOfDates_ByTurnID(Game_Calendar.TURN_ID + (int)(Game.getInvestCost(iProvinceID) / (Game.getIncomeFromEconomy_Invest(iProvinceID) + tIncomeProduction) * 30.0F)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (CFG.isDesktop()) {
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData, false));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ShiftClickToInvestXTimes", 5), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                        nElements.add(new MenuElement_HoverElement(nData, false));
                        nData.clear();
                    }
                }
            }

            return inProvinceInfo ? new MenuElement_Hover(nElements) {
                public int getMinPosX() {
                    return InGame_ProvinceInfo.HOVER_POSX;
                }

                public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                    nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                    super.draw(oSB, nPosX, nPosY);
                }
            } : new MenuElement_Hover(nElements);
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
            return null;
        }
    }

    public static MenuElement_Hover getHoverResource(int iProvinceID, int resourceID, boolean inProvinceInfo) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        if (resourceID >= 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonusResource(Game.lang.get("Resource") + ": ", ((ResourcesManager.Resources)ResourcesManager.lResources.get(resourceID)).Name, resourceID, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        } else {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Resource") + ": ", Game.lang.get("None"), Images.resourceNone, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (resourceID >= 0) {
            if (!Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getTechResearched(((ResourcesManager.Resources)ResourcesManager.lResources.get(resourceID)).RequiredTechID)) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RequiredTechnology") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + ((TechnologyTree.Technology)TechnologyTree.lTechnology.get(((ResourcesManager.Resources)ResourcesManager.lResources.get(resourceID)).RequiredTechID)).Name, CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            } else {
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Price") + ": ", "" + CFG.getPrecision2(ResourcesManager.getPrice(resourceID), 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MonthlyIncome") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(ResourcesManager.getMonthlyIncome(iProvinceID, resourceID), 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.tax, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(" = " + Game.lang.get("Price") + " * " + Game.lang.get("ProductionEfficiency") + " %", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                if (Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCorruption() > 0.005F) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Corruption") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                    nData.add(new MenuElement_HoverElement_Type_Text("-" + CFG.getPrecision2(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCorruption() * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.corruption, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
                    nData.clear();
                }

                if (!Game.getProvince(iProvinceID).haveACore(Game.getProvince(iProvinceID).getCivID())) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NonCoreProvince") + ":", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                    nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getCivID(), CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.core.BASE_INCOME_NON_CORE * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
                    nData.clear();
                }

                if (Game.getProvince(iProvinceID).getReligion() != Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getReligionID()) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DifferentReligion") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.religion.BASE_INCOME_DIFFERENT_RELIGION * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
                    nData.clear();
                }

                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Production") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(ResourcesManager.getProducedGoods(iProvinceID), 10), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2(GameValues.production.BASE_PRODUCTION, 100) + " + " + Game.lang.get("Economy") + " * " + CFG.getPrecision2(GameValues.production.GOODS_PRODUCED_PER_ECONOMY, 100) + " * " + Game.lang.get("ProductionEfficiency") + " %", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProductionEfficiency") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(ResourcesManager.getProductionEfficiency(iProvinceID) * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(" = (" + CFG.getPrecision2(GameValues.production.BASE_PRODUCTION_EFFICIENCY * 100.0F, 100) + " + " + Game.lang.get("Economy") + " * " + CFG.getPrecision2(GameValues.production.PRODUCTION_EFFICIENCY_PER_ECONOMY * 100.0F, 100) + ") * " + Game.lang.get("Bonuses"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                if ((Integer)ResourcesManager.worldResourcesProduced.get(resourceID) > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LargestProducer") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv((Integer)ResourcesManager.worldResources_LargestProducer.get(resourceID)).getCivName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Flag((Integer)ResourcesManager.worldResources_LargestProducer.get(resourceID), CFG.PADDING, CFG.PADDING));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    InGame_Goods.BonusText bonusText = InGame_Goods.getBonusText(resourceID);
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Bonus") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                    nData.add(new MenuElement_HoverElement_Type_Text(bonusText.bonusText, CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                    nData.add(new MenuElement_HoverElement_Type_Text(bonusText.bonusText2, CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(bonusText.bonusIMGID, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                if (((ResourcesManager.Resources)ResourcesManager.lResources.get(resourceID)).uniqueBuildingID >= 0) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UniqueBuilding") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + ((BuildingsManager.Buildings)BuildingsManager.buildings.get(((ResourcesManager.Resources)ResourcesManager.lResources.get(resourceID)).uniqueBuildingID)).Name[0], CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nData.add(new MenuElement_HoverElement_Type_Resource(resourceID, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                nData.add(new MenuElement_HoverElement_Type_Text(ResourcesManager.getResourceGroupName(((ResourcesManager.Resources)ResourcesManager.lResources.get(resourceID)).GroupID), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }

        if (inProvinceInfo) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ProducedGoods"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            return new MenuElement_Hover(nElements, true) {
                public int getMinPosX() {
                    return InGame_ProvinceInfo.HOVER_POSX;
                }

                public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                    nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                    super.draw(oSB, nPosX, nPosY);
                }
            };
        } else {
            return new MenuElement_Hover(nElements);
        }
    }

    public static MenuElement_Hover getHoverTaxEfficiency(int iProvinceID, boolean inProvinceInfo) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TaxEfficiency") + ": ", CFG.getPrecision2(Game.getProvinceTaxEfficiency(iProvinceID), 100) + "%", Images.tax, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getProvince(iProvinceID).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MonthlyIncomeTaxation") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getIncomePopulationTaxation(iProvinceID), 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(" = " + GameValues.tax.BASE_INCOME_POPULATION_INCOME + " + (min(" + Game.lang.get("Population") + ", " + GameValues.tax.TAX_EFFICIENCY_MAX_POPULATION + ") / " + CFG.getPrecision2(GameValues.tax.TAX_EFFICIENCY_POPULATION_DIVIDE, 10) + ") * " + Game.lang.get("TaxEfficiency") + " %", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        if (Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCorruption() > 0.005F) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Corruption") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("-" + CFG.getPrecision2(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCorruption() * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.corruption, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
        }

        if (!Game.getProvince(iProvinceID).haveACore(Game.getProvince(iProvinceID).getCivID())) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NonCoreProvince") + ":", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getCivID(), CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.core.BASE_INCOME_NON_CORE * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
        }

        if (Game.getProvince(iProvinceID).getReligion() != Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getReligionID()) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DifferentReligion") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.religion.BASE_INCOME_DIFFERENT_RELIGION * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
        }

        if (Game.getProvince(iProvinceID).getDevastation() > 1.0E-4F) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Devastation") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(iProvinceID).getDevastation() * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.devastation, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceIncome") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("-" + CFG.getPrecision2(Game.getProvince(iProvinceID).getDevastation() * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
        }

        if (Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getPuppetOfCivID() == Game.player.iCivID) {
            if ((Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getPuppetOfCivID() == Game.player.iCivID) && Game.getProvince(iProvinceID).iProvinceIncreaseTaxEfficiencySize > 0) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Progress") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", ((ProvinceInvest)Game.getProvince(iProvinceID).provinceIncreasTaxEfficiencyDaysLeft.get(0)).daysLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_HoverElement_Type_Text("[" + CFG.getPrecision2((1.0F - (float)((ProvinceInvest)Game.getProvince(iProvinceID).provinceIncreasTaxEfficiencyDaysLeft.get(0)).daysLeft / (float)((ProvinceInvest)Game.getProvince(iProvinceID).provinceIncreasTaxEfficiencyDaysLeft.get(0)).investTime) * 100.0F, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getProvince(iProvinceID).iProvinceIncreaseTaxEfficiencySize > 1) {
                    int tDays = 0;

                    for(int i = 0; i < Game.getProvince(iProvinceID).iProvinceIncreaseTaxEfficiencySize; ++i) {
                        tDays += ((ProvinceInvest)Game.getProvince(iProvinceID).provinceIncreasTaxEfficiencyDaysLeft.get(i)).daysLeft;
                    }

                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InQueue") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getProvince(iProvinceID).iProvinceIncreaseTaxEfficiencySize - 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Text(" [" + Game.lang.get("DaysX", tDays) + "]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
            }

            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToIncreaseLocalTaxEfficiency"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.taxUp, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (Game.getProvince(iProvinceID).getCivID() != Game.player.iCivID) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCivName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getCivID(), CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.vassal, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(iProvinceID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2((float)GameValues.tax.INCREASE_TAX_EFFICIENCY_COST, 10) + " + " + Game.lang.get("TaxEfficiency") + " * " + CFG.getPrecision2(GameValues.tax.INCREASE_TAX_EFFICIENCY_COST_PER_TAX_EFFICIENCY, 10), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(iProvinceID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2(GameValues.tax.INCREASE_TAX_EFFICIENCY_COST_LEGACY, 10) + " + " + Game.lang.get("TaxEfficiency") + " * " + CFG.getPrecision2(GameValues.tax.INCREASE_TAX_EFFICIENCY_COST_LEGACY_PER_TAX, 10), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AfterXDays", Game.getIncreasTaxEfficiencyTime(Game.player.iCivID)) + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TaxEfficiency") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.tax.INCREASE_TAX_EFFICIENCY_GROWTH, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.tax, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MonthlyIncomeTaxation") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getIncomePopulationTaxation_Invest(iProvinceID), 1000), CFG.FONT_REGULAR_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InvestmentPayoff") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text("" + Game_Calendar.getNumOfDates_ByTurnID(Game_Calendar.TURN_ID + (int)(Game.getIncreaseTaxEfficiencyCost(iProvinceID) / Game.getIncomePopulationTaxation_Invest(iProvinceID) * 30.0F)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (CFG.isDesktop()) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ShiftClickToIncreaseTaxationEfficiencyXTimes", 5), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
            }
        }

        return inProvinceInfo ? new MenuElement_Hover(nElements) {
            public int getMinPosX() {
                return InGame_ProvinceInfo.HOVER_POSX;
            }

            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        } : new MenuElement_Hover(nElements);
    }

    public static MenuElement_Hover getHoverPopulation(int iProvinceID, boolean inProvinceInfo) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getProvince(iProvinceID).getPopulationTotal()), Images.population, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();

        for(int i = 0; i < Game.getProvince(iProvinceID).getPopulationSize(); ++i) {
            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getPopulationCivID(i), 0, CFG.PADDING));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.getProvince(iProvinceID).getPopulationID(i)), CFG.FONT_BOLD_SMALL, Colors.COLOR_POPULATION));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_HoverElement_Type_Text("[" + CFG.getPrecision2((float)Game.getProvince(iProvinceID).getPopulationID(i) / (float)Game.getProvince(iProvinceID).getPopulationTotal() * 100.0F, 100) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text(" " + Game.getCiv(Game.getProvince(iProvinceID).getPopulationCivID(i)).getCivName(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PopulationGrowth") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2((float)((int)Math.max(1.0F, (float)((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).POPULATION_GROWTH_PER_MONTH * (Game.getProvince(iProvinceID).getGrowthRateWithBonuses() / 100.0F))), 1), CFG.FONT_BOLD_SMALL, Colors.COLOR_POPULATION));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", GameValues.gameUpdate.GAME_UPDATE_POPULATION_STEPS * ((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).GAME_DAYS_PER_TURN), CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2((float)((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).POPULATION_GROWTH_PER_MONTH, 10) + " * " + Game.lang.get("GrowthRate") + " %", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("GrowthRate") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(iProvinceID).getGrowthRateWithBonuses(), 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ThePopulationWillSlowlyAssimilateIfTheCivilizationHasACore"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return inProvinceInfo ? new MenuElement_Hover(nElements) {
            public int getMinPosX() {
                return InGame_ProvinceInfo.HOVER_POSX;
            }

            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        } : new MenuElement_Hover(nElements);
    }

    public static MenuElement_Hover getHoverManpower(int iProvinceID, boolean inProvinceInfo, boolean showInvest) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LocalManpowerLevel") + ": ", "" + CFG.getPrecision2(Game.getProvince(iProvinceID).getManpower(), 100), Game_Calendar.IMG_MANPOWER, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumManpower") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2((float)Game.getManpowerMaxFromProvinceManpowerLvl(iProvinceID), 1), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(" = " + Game.lang.get("LocalManpowerLevel") + " * " + CFG.getPrecision2((float)GameValues.manpower.MANPOWER_MAX_PER_PROVINCE_MANPOWER_LVL, 100) + " * min(" + Game.lang.get("GrowthRate") + ", " + GameValues.manpower.MANPOWER_MAX_PER_PROVINCE_MAX_GROWTH_RATE + ") %", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ManpowerPerMonth") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getManpowerFromProvincePerMonth_Info(iProvinceID), 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(" = " + Game.lang.get("MaximumManpower") + " / " + Game.lang.get("MonthsX", (int)GameValues.manpower.MANPOWER_FULL_RECOVERY_MONTHS), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceDefense") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2((float)Game.getProvince(iProvinceID).getFortDefense_Manpower(), 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_FORT_DEFENSE, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(" = " + Game.lang.get("LocalManpowerLevel") + " * " + CFG.getPrecision2(GameValues.siege.SIEGE_FORT_DEFENSE_PER_MANPOWER_LVL, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LocalGrowthRate") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(iProvinceID).getGrowthRateWithBonuses(), 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (!Game.getProvince(iProvinceID).haveACore(Game.getProvince(iProvinceID).getCivID())) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NonCoreProvince") + ":", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getCivID(), CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.manpower.MANPOWER_MAX_NON_CORE * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
        }

        if (Game.getProvince(iProvinceID).getReligion() != Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getReligionID()) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DifferentReligion") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.manpower.MANPOWER_MAX_DIFFERENT_RELIGION * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
        }

        if (showInvest && (Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getPuppetOfCivID() == Game.player.iCivID)) {
            if ((Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getPuppetOfCivID() == Game.player.iCivID) && Game.getProvince(iProvinceID).iProvinceIncreaseManpowerSize > 0) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Progress") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", ((ProvinceInvest)Game.getProvince(iProvinceID).provinceIncreaseManpowerDaysLeft.get(0)).daysLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_HoverElement_Type_Text("[" + CFG.getPrecision2((1.0F - (float)((ProvinceInvest)Game.getProvince(iProvinceID).provinceIncreaseManpowerDaysLeft.get(0)).daysLeft / (float)((ProvinceInvest)Game.getProvince(iProvinceID).provinceIncreaseManpowerDaysLeft.get(0)).investTime) * 100.0F, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getProvince(iProvinceID).iProvinceIncreaseManpowerSize > 1) {
                    int tDays = 0;

                    for(int i = 0; i < Game.getProvince(iProvinceID).iProvinceIncreaseManpowerSize; ++i) {
                        tDays += ((ProvinceInvest)Game.getProvince(iProvinceID).provinceIncreaseManpowerDaysLeft.get(i)).daysLeft;
                    }

                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InQueue") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getProvince(iProvinceID).iProvinceIncreaseManpowerSize - 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Text(" [" + Game.lang.get("DaysX", tDays) + "]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
            }

            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToIncreaseManpower"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (Game.getProvince(iProvinceID).getCivID() != Game.player.iCivID) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCivName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getCivID(), CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.vassal, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getIncreaseManpowerCost(iProvinceID), 10), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2(GameValues.manpower.INCREASE_MANPOWER_COST, 10) + " + " + Game.lang.get("Manpower") + " * " + CFG.getPrecision2(GameValues.manpower.INCREASE_MANPOWER_COST_PER_LEVEL, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getIncreaseManpowerCostLegacy(iProvinceID), 10), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2(GameValues.manpower.INCREASE_MANPOWER_COST_LEGACY, 100) + " + " + Game.lang.get("Manpower") + " * " + CFG.getPrecision2(GameValues.manpower.INCREASE_MANPOWER_COST_LEGACY_PER_LEVEL, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AfterXDays", Game.getIncreseManpowerTime(Game.player.iCivID)) + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LocalManpowerLevel") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.manpower.INCREASE_MANPOWER_GROWTH, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumManpower") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2((float)Game.getIncreseManpower_MaximumManpowerInfo(iProvinceID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ManpowerPerMonth") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getIncreseManpower_ManpowerPerMonthInfo(iProvinceID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceDefense") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.siege.SIEGE_FORT_DEFENSE_PER_MANPOWER_LVL, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_FORT_DEFENSE, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("TipForBestResultsIncreaseManpowerInProvincesWithHighestGrowthRateOfPopulation"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nElements.add(new MenuElement_HoverElement(nData, !inProvinceInfo));
            nData.clear();
            if (CFG.isDesktop()) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ShiftClickToIncreaseManpowerXTimes", 5), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
            }
        }

        return inProvinceInfo ? new MenuElement_Hover(nElements) {
            public int getMinPosX() {
                return InGame_ProvinceInfo.HOVER_POSX;
            }

            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        } : new MenuElement_Hover(nElements);
    }

    public static MenuElement_Hover getHoverReligion(int iProvinceID, boolean inProvinceInfo, boolean showInvest) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusReligion(Game.lang.get("Religion") + ": ", Game.religionManager.getReligion(Game.getProvince(iProvinceID).getReligion()).Name, Game.getProvince(iProvinceID).getReligion(), CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();

        try {
            if (Game.getProvince(iProvinceID).religionConversion != null) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ReligionConversionProgress") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((1.0F - (float)Game.getProvince(iProvinceID).religionConversion.daysLeft / (float)Game.getProvince(iProvinceID).religionConversion.investTime) * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            } else if (showInvest && Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID && Game.getProvince(iProvinceID).getReligion() != Game.getCiv(Game.player.iCivID).getReligionID()) {
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ConvertReligion") + ": ", CFG.FONT_BOLD_SMALL));
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.religionManager.getReligion(Game.getCiv(Game.player.iCivID).getReligionID()).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ReligionConversionCost") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.religionManager.getReligionConversionCost(iProvinceID), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.religion, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2(GameValues.religion.DEFAULT_CONVERSION_COST, 100) + " + " + Game.lang.get("GrowthRate") + " * " + CFG.getPrecision2(GameValues.religion.CONVERSION_COST_PER_GROWTH_RATE, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ReligionConversionTime") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.religionManager.getReligionConversionTime(iProvinceID), CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(" = " + GameValues.religion.DEFAULT_CONVERSION_TIME + " + min(" + CFG.getPrecision2(GameValues.religion.DEFAULT_CONVERSION_TIME_POPULATION_MIN, 10) + ", " + Game.lang.get("Population") + " / " + CFG.getPrecision2(GameValues.religion.DEFAULT_CONVERSION_TIME_POPULATION, 10) + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        if (Game.getProvince(iProvinceID).getReligion() != Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getReligionID()) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DifferentReligion"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.religion, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceMonthlyIncome") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.religion.BASE_INCOME_DIFFERENT_RELIGION * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        return inProvinceInfo ? new MenuElement_Hover(nElements) {
            public int getMinPosX() {
                return InGame_ProvinceInfo.HOVER_POSX;
            }

            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        } : new MenuElement_Hover(nElements);
    }

    public static MenuElement_Hover getHoverCores(int iProvinceID, boolean inProvinceInfo) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Cores") + (Game.getProvince(iProvinceID).iCoresSize == 0 ? ": " : ""), Game.getProvince(iProvinceID).iCoresSize == 0 ? Game.lang.get("None") : "", Images.core, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getProvince(iProvinceID).iCoresSize != 0) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();

            for(int i = 0; i < Game.getProvince(iProvinceID).iCoresSize; ++i) {
                nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getCore(i), 0, CFG.PADDING));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.getProvince(iProvinceID).getCore(i)).getCivName(), CFG.FONT_REGULAR_SMALL));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }

        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("CoreIsALegitimatePartOfCivilization"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID && !Game.getProvince(iProvinceID).haveACore(Game.player.iCivID) && Game.getProvince(iProvinceID).coreCreation == null) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("AddCore"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.core, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getCoreCreationCost(iProvinceID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2(GameValues.core.CORE_CREATION_COST_DEFAULT, 100) + " + " + Game.lang.get("Economy") + " * " + CFG.getPrecision2(GameValues.core.CORE_CREATION_COST_PER_ECONOMY, 100) + " + " + Game.lang.get("Infrastructure") + " * " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_CORE_COST_PER_LVL, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionTime") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + Game.lang.get("DaysX", Game.getCoreCreationTime(iProvinceID)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2(GameValues.core.CORE_CREATION_TIME_DEFAULT, 100) + " + " + Game.lang.get("TaxEfficiency") + " * " + CFG.getPrecision2(GameValues.core.CORE_CREATION_TIME_PER_TAX_EFFICIENCY, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
        } else if (Game.getProvince(iProvinceID).coreCreation != null) {
            try {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CoreConstruction") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((1.0F - (float)Game.getProvince(iProvinceID).coreCreation.daysLeft / (float)Game.getProvince(iProvinceID).coreCreation.investTime) * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.core, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

        if (!Game.getProvince(iProvinceID).haveACore(Game.getProvince(iProvinceID).getCivID())) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NonCoreProvince") + ":", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getCivID(), CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceMonthlyIncome") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.core.BASE_INCOME_NON_CORE * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        return inProvinceInfo ? new MenuElement_Hover(nElements) {
            public int getMinPosX() {
                return InGame_ProvinceInfo.HOVER_POSX;
            }

            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        } : new MenuElement_Hover(nElements);
    }

    public static final MenuElement_Hover getHoverPopulationGrowth(int iProvinceID, boolean inProvinceInfo, boolean showInvest) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GrowthRate") + ": ", CFG.getPrecision2(Game.getProvince(iProvinceID).getGrowthRateWithBonuses(), 10) + "%", Images.populationGrowth, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BaseValue") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(iProvinceID).getGrowthRate(), 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationBonuses") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).civBonuses.GrowthRate, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceBonuses") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(iProvinceID).provBonuses.LocalGrowthRate + Game.getProvinceData7(iProvinceID).getIncreasedGrowthRate(), 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Infrastructure") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)Game.getProvince(iProvinceID).getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_GROWTH_RATE_PER_LVL, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getProvinceData9(iProvinceID).getColonizationGrowthRateExtra() > 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Colonization") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)Game.getProvinceData9(iProvinceID).getColonizationGrowthRateExtra(), 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        nData.add(new MenuElement_HoverElement_Type_Text(((Terrain)Game.terrainManager.terrains.get(Game.getProvince(iProvinceID).getTerrainID())).Name + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(iProvinceID).getTerrainID())).PopulationGrowth, 100) + "%", CFG.FONT_BOLD_SMALL, ((Terrain)Game.terrainManager.terrains.get(Game.getProvince(iProvinceID).getTerrainID())).PopulationGrowth < 0 ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getProvince(iProvinceID).isCapital) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Capital") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.capital.CAPITAL_GROWTH_RATE, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PopulationGrowth") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2((float)((int)Math.max(1.0F, (float)((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).POPULATION_GROWTH_PER_MONTH * (Game.getProvince(iProvinceID).getGrowthRateWithBonuses() / 100.0F))), 1), CFG.FONT_BOLD_SMALL, Colors.COLOR_POPULATION));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", GameValues.gameUpdate.GAME_UPDATE_POPULATION_STEPS * ((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).GAME_DAYS_PER_TURN), CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2((float)((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).POPULATION_GROWTH_PER_MONTH, 10) + " * " + Game.lang.get("GrowthRate") + " %", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        if (showInvest && (Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getPuppetOfCivID() == Game.player.iCivID)) {
            if ((Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getPuppetOfCivID() == Game.player.iCivID) && Game.getProvince(iProvinceID).iProvinceIncreaseGrowthRateSize > 0) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Progress") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", ((ProvinceInvest)Game.getProvince(iProvinceID).provinceIncreaseGrowthRateDaysLeft.get(0)).daysLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                nData.add(new MenuElement_HoverElement_Type_Text("[" + CFG.getPrecision2((1.0F - (float)((ProvinceInvest)Game.getProvince(iProvinceID).provinceIncreaseGrowthRateDaysLeft.get(0)).daysLeft / (float)((ProvinceInvest)Game.getProvince(iProvinceID).provinceIncreaseGrowthRateDaysLeft.get(0)).investTime) * 100.0F, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getProvince(iProvinceID).iProvinceIncreaseGrowthRateSize > 1) {
                    int tDays = 0;

                    for(int i = 0; i < Game.getProvince(iProvinceID).iProvinceIncreaseGrowthRateSize; ++i) {
                        tDays += ((ProvinceInvest)Game.getProvince(iProvinceID).provinceIncreaseGrowthRateDaysLeft.get(i)).daysLeft;
                    }

                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InQueue") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getProvince(iProvinceID).iProvinceIncreaseGrowthRateSize - 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Text(" [" + Game.lang.get("DaysX", tDays) + "]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }
            }

            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToIncreasePopulationGrowthRate"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.populationGrowth, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (Game.getProvince(iProvinceID).getCivID() != Game.player.iCivID) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCivName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getCivID(), CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.vassal, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getIncreaseGrowthRateCost(iProvinceID), 10), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2(GameValues.growthRate.INCREASE_GROWTH_RATE_COST, 10) + " + " + Game.lang.get("GrowthRate") + " * " + CFG.getPrecision2(GameValues.growthRate.INCREASE_GROWTH_RATE_COST_PER_GROWTH_RATE, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AfterXDays", Game.getIncreaseGrowthRateTime(Game.player.iCivID)) + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LocalGrowthRate") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.growthRate.INCREASE_GROWTH_RATE_GROWTH, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.COLOR_POPULATION));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (CFG.isDesktop()) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ShiftClickToIncreaseGrowthRateXTimes", 5), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
            }
        }

        return inProvinceInfo ? new MenuElement_Hover(nElements) {
            public int getMinPosX() {
                return InGame_ProvinceInfo.HOVER_POSX;
            }

            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        } : new MenuElement_Hover(nElements);
    }

    public static MenuElement_Hover getHoverInfrastructure(int iProvinceID, boolean inProvinceInfo) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Infrastructure") + ": ", "" + Game.getProvince(iProvinceID).getInfrastructure() + " / " + Game.getProvince(iProvinceID).iInfrastructureMax, Images.infrastructure, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TaxEfficiency") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text((Game.getProvince(iProvinceID).getInfrastructure() > 0 ? "+" : "") + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_TAX_EFFICIENCY_PER_LVL * (float)Game.getProvince(iProvinceID).getInfrastructure(), 100) + "%", CFG.FONT_BOLD_SMALL, Game.getProvince(iProvinceID).getInfrastructure() > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.tax, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PerLevel") + ": " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_TAX_EFFICIENCY_PER_LVL, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionCost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_CONSTRUCTION_COST_PER_LVL * 100.0F * (float)Game.getProvince(iProvinceID).getInfrastructure(), 100) + "%", CFG.FONT_BOLD_SMALL, Game.getProvince(iProvinceID).getInfrastructure() > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.build, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PerLevel") + ": " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_CONSTRUCTION_COST_PER_LVL * 100.0F, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionTime") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_CONSTRUCTION_TIME_PER_LVL * 100.0F * (float)Game.getProvince(iProvinceID).getInfrastructure(), 100) + "%", CFG.FONT_BOLD_SMALL, Game.getProvince(iProvinceID).getInfrastructure() > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.buildTime, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PerLevel") + ": " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_CONSTRUCTION_TIME_PER_LVL * 100.0F, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingSlots") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + GameValues.infrastructure.INFRASTRUCTURE_BUILDINGS_SLOT_PER_LVL * Game.getProvince(iProvinceID).getInfrastructure(), CFG.FONT_BOLD_SMALL, Game.getProvince(iProvinceID).getInfrastructure() > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.build, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PerLevel") + ": " + GameValues.infrastructure.INFRASTRUCTURE_BUILDINGS_SLOT_PER_LVL, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InvestInEconomyCost") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_INVEST_COST_PER_LVL * 100.0F * (float)Game.getProvince(iProvinceID).getInfrastructure(), 100) + "%", CFG.FONT_BOLD_SMALL, Game.getProvince(iProvinceID).getInfrastructure() > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PerLevel") + ": " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_INVEST_COST_PER_LVL * 100.0F, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProductionEfficiency") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text((Game.getProvince(iProvinceID).getInfrastructure() > 0 ? "+" : "") + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_PRODUCTION_EFFICIENCY_PER_LVL * 100.0F * (float)Game.getProvince(iProvinceID).getInfrastructure(), 100) + "%", CFG.FONT_BOLD_SMALL, Game.getProvince(iProvinceID).getInfrastructure() > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PerLevel") + ": " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_PRODUCTION_EFFICIENCY_PER_LVL * 100.0F, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RecruitmentTime") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_RECRUITMENT_TIME_PER_LVL * 100.0F * (float)Game.getProvince(iProvinceID).getInfrastructure(), 100) + "%", CFG.FONT_BOLD_SMALL, Game.getProvince(iProvinceID).getInfrastructure() > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER_TIME, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PerLevel") + ": " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_RECRUITMENT_TIME_PER_LVL * 100.0F, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LocalGrowthRate") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text((Game.getProvince(iProvinceID).getInfrastructure() > 0 ? "+" : "") + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_GROWTH_RATE_PER_LVL * (float)Game.getProvince(iProvinceID).getInfrastructure(), 100) + "%", CFG.FONT_BOLD_SMALL, Game.getProvince(iProvinceID).getInfrastructure() > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PerLevel") + ": " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_GROWTH_RATE_PER_LVL, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ArmyMovementSpeed") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text((Game.getProvince(iProvinceID).getInfrastructure() > 0 ? "+" : "") + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_ARMY_MOVEMENT_PER_LVL * 100.0F * (float)Game.getProvince(iProvinceID).getInfrastructure(), 100) + "%", CFG.FONT_BOLD_SMALL, Game.getProvince(iProvinceID).getInfrastructure() > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.movementSpeed, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PerLevel") + ": " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_ARMY_MOVEMENT_PER_LVL * 100.0F, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DiseasesDeathRate") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_DISEASE_DEATH_RATE_PER_LVL * 100.0F * (float)Game.getProvince(iProvinceID).getInfrastructure(), 100) + "%", CFG.FONT_BOLD_SMALL, Game.getProvince(iProvinceID).getInfrastructure() > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.disease, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PerLevel") + ": " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_DISEASE_DEATH_RATE_PER_LVL * 100.0F, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceMaintenance") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text((Game.getProvince(iProvinceID).getInfrastructure() > 0 ? "+" : "") + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_PROVINCE_MAINTENANCE_PER_LVL * 100.0F * (float)Game.getProvince(iProvinceID).getInfrastructure(), 100) + "%", CFG.FONT_BOLD_SMALL, Game.getProvince(iProvinceID).getInfrastructure() > 0 ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PerLevel") + ": " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_PROVINCE_MAINTENANCE_PER_LVL * 100.0F, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getProvince(iProvinceID).wonderID >= 0) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Wonder") + ", " + Game.lang.get("ConstructionCost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_WONDER_CONSTRUCTION_COST_PER_LVL * 100.0F * (float)Game.getProvince(iProvinceID).getInfrastructure(), 100) + "%", CFG.FONT_BOLD_SMALL, Game.getProvince(iProvinceID).getInfrastructure() > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEUTRAL));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.mapModesWonders, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PerLevel") + ": " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_WONDER_CONSTRUCTION_COST_PER_LVL * 100.0F, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumInfrastructureLevel") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(iProvinceID).iInfrastructureMax + " / " + GameValues.infrastructure.INFRASTRUCTURE_MAX_LVL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.infrastructure, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(" = min(" + GameValues.infrastructure.INFRASTRUCTURE_MAX_DEFAULT + (Game.getProvince(iProvinceID).isCapital ? " + " + Game.lang.get("Capital") + ": + " + GameValues.infrastructure.INFRASTRUCTURE_MAX_CAPITAL : "") + " + " + Game.lang.get("Economy") + " / " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_MAX_PER_ECONOMY, 10) + " + " + Game.lang.get("GrowthRate") + " / " + CFG.getPrecision2(GameValues.infrastructure.INFRASTRUCTURE_MAX_PER_GROWTH_RATE, 10) + ", " + GameValues.infrastructure.INFRASTRUCTURE_MAX_LVL + ")", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        if (Game.getProvince(iProvinceID).iProvinceDevelopInfrastructureSize > 1) {
            int tDays = 0;

            for(int i = 0; i < Game.getProvince(iProvinceID).iProvinceDevelopInfrastructureSize; ++i) {
                tDays += ((ProvinceInvest)Game.getProvince(iProvinceID).provinceDevelopInfrastructureDaysLeft.get(i)).daysLeft;
            }

            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InQueue") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getProvince(iProvinceID).iProvinceDevelopInfrastructureSize - 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Text(" [" + Game.lang.get("DaysX", tDays) + "]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if ((Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getPuppetOfCivID() == Game.player.iCivID) && Game.getProvince(iProvinceID).getInfrastructure() < Game.getProvince(iProvinceID).iInfrastructureMax) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToDevelopInfrastructure"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.infrastructureUp, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (Game.getProvince(iProvinceID).getCivID() != Game.player.iCivID) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCivName(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getCivID(), CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.vassal, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getDevelopInfrastructureCost(iProvinceID), 10), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2((float)GameValues.infrastructure.DEVELOP_INFRASTRUCTURE_COST_GOLD, 10) + " + " + Game.lang.get("Infrastructure") + " * " + CFG.getPrecision2((float)GameValues.infrastructure.DEVELOP_INFRASTRUCTURE_COST_GOLD_PER_INFRASTRUCTURE, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getDevelopInfrastructureCostLegacy(iProvinceID), 10), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(" = " + CFG.getPrecision2((float)GameValues.infrastructure.DEVELOP_INFRASTRUCTURE_COST_LEGACY, 10) + " + " + Game.lang.get("Infrastructure") + " * " + CFG.getPrecision2((float)GameValues.infrastructure.DEVELOP_INFRASTRUCTURE_COST_LEGACY_PER_INFRASTRUCTURE, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nElements.add(new MenuElement_HoverElement(nData, false));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ConstructionTime") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", Game.getDevelopInfrastructureTime(iProvinceID)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        return inProvinceInfo ? new MenuElement_Hover(nElements) {
            public int getMinPosX() {
                return InGame_ProvinceInfo.HOVER_POSX;
            }

            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        } : new MenuElement_Hover(nElements);
    }

    public static final boolean addDevelopInfrastructureCost(int iProvinceID) {
        if (Game.getDevelopInfrastructureCost(iProvinceID) > Game.getCiv(Game.player.iCivID).fGold) {
            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getDevelopInfrastructureCost(iProvinceID), 100), Images.gold);
            return false;
        } else if (Game.getDevelopInfrastructureCostLegacy(iProvinceID) > Game.getCiv(Game.player.iCivID).fLegacy) {
            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getDevelopInfrastructureCostLegacy(iProvinceID), 100), Images.legacy);
            return false;
        } else if (Game.getProvince(iProvinceID).getInfrastructure() + Game.getProvince(iProvinceID).iProvinceDevelopInfrastructureSize >= Game.getProvince(iProvinceID).iInfrastructureMax) {
            Game.menuManager.addToastInsufficient(Game.lang.get("MaximumInfrastructureLevel") + ": ", Game.getProvince(iProvinceID).getInfrastructure() + Game.getProvince(iProvinceID).iProvinceDevelopInfrastructureSize + " / " + Game.getProvince(iProvinceID).iInfrastructureMax, Images.infrastructure);
            return false;
        } else {
            return Game.getProvince(iProvinceID).addDevelopInfrastructure(Game.player.iCivID);
        }
    }

    public static MenuElement_Hover getHoverProvince(int iProvinceID) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.getProvince(iProvinceID).getCivID(), Game.getProvince(iProvinceID).getProvinceName()));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getProvince(iProvinceID).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getProvince(iProvinceID).getEconomyWithBonuses(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Income") + ": ", CFG.getPrecision2(Game.getProvince(iProvinceID).getProvinceIncome() - Game.getProvince(iProvinceID).fProvinceMaintenance, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }

    public static MenuElement_Hover getHoverProvinceIncome(int iProvinceID, boolean inProvinceInfo) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMonthlyIncome") + ": ", CFG.getPrecision2(Game.getProvince(iProvinceID).getProvinceIncome() - Game.getProvince(iProvinceID).fProvinceMaintenance, 100), Images.gold, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        float fTotal = 0.0F;
        float fGold = Game.getProvince(iProvinceID).fProvinceIncomeTaxation;
        fTotal += fGold;
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncomeTaxation") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(fGold, 100), CFG.FONT_BOLD_SMALL, fGold > 0.0F ? Colors.HOVER_POSITIVE : Colors.HOVER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.tax, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        fGold = Game.getProvince(iProvinceID).fProvinceIncomeEconomy;
        fTotal += fGold;
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Economy") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(fGold, 100), CFG.FONT_BOLD_SMALL, fGold > 0.0F ? Colors.HOVER_POSITIVE : Colors.HOVER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        fGold = Game.getProvince(iProvinceID).fProvinceIncomeProduction;
        fTotal += fGold;
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Production") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(fGold, 100), CFG.FONT_BOLD_SMALL, fGold > 0.0F ? Colors.HOVER_POSITIVE : Colors.HOVER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        fGold = Game.getProvince(iProvinceID).provBonuses.MonthlyIncome;
        fTotal += fGold;
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Buildings") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(fGold, 100), CFG.FONT_BOLD_SMALL, fGold > 0.0F ? Colors.HOVER_POSITIVE : Colors.HOVER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getProvince(iProvinceID).isCapital) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Capital") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.capital.CAPITAL_MONTHLY_INCOME, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TotalIncome") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(fTotal, 100), CFG.FONT_BOLD_SMALL, fTotal > 0.0F ? Colors.HOVER_POSITIVE : Colors.HOVER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (!Game.getProvince(iProvinceID).haveACore(Game.getProvince(iProvinceID).getCivID()) || Game.getProvince(iProvinceID).getReligion() != Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getReligionID() || Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCorruption() > 0.005F) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCorruption() > 0.005F) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Corruption") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("-" + CFG.getPrecision2(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getCorruption() * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.corruption, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (!Game.getProvince(iProvinceID).haveACore(Game.getProvince(iProvinceID).getCivID())) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NonCoreProvince") + ":", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Flag(Game.getProvince(iProvinceID).getCivID(), CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.core.BASE_INCOME_NON_CORE * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getProvince(iProvinceID).getReligion() != Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getReligionID()) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DifferentReligion") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.religion.BASE_INCOME_DIFFERENT_RELIGION * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getProvince(iProvinceID).getDevastation() > 5.0E-5F) {
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Devastation") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getProvince(iProvinceID).getDevastation() * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.devastation, CFG.PADDING, CFG.PADDING));
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceIncome") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
            nData.add(new MenuElement_HoverElement_Type_Text("-" + CFG.getPrecision2(Game.getProvince(iProvinceID).getDevastation() * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        fTotal = 0.0F;
        fGold = Game.getProvince(iProvinceID).fProvinceMaintenance - Game.getProvince(iProvinceID).getProvinceBuildingsMaintenance();
        fTotal += fGold;
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvinceMaintenance") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text((fGold > 0.0F ? "-" : "") + CFG.getPrecision2(fGold, 100), CFG.FONT_BOLD_SMALL, fGold > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(" = " + Game.lang.get("Economy") + " * " + CFG.getPrecision2(GameValues.province.PROVINCE_MAINTENANCE_PER_ECONOMY, 1000) + " + " + Game.lang.get("TaxEfficiency") + " * " + CFG.getPrecision2(GameValues.province.PROVINCE_MAINTENANCE_PER_TAX, 1000) + " + " + Game.lang.get("Manpower") + " * " + CFG.getPrecision2(GameValues.province.PROVINCE_MAINTENANCE_PER_MANPOWER, 1000), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        fGold = Game.getProvince(iProvinceID).getProvinceBuildingsMaintenance();
        fTotal += fGold;
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingsMaintenance") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text((fGold > 0.0F ? "-" : "") + CFG.getPrecision2(fGold, 100), CFG.FONT_BOLD_SMALL, fGold > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TotalExpenses") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text((fTotal > 0.0F ? "-" : "") + CFG.getPrecision2(fTotal, 100), CFG.FONT_BOLD_SMALL, fTotal > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_NEUTRAL));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return inProvinceInfo ? new MenuElement_Hover(nElements) {
            public int getMinPosX() {
                return InGame_ProvinceInfo.HOVER_POSX;
            }

            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        } : new MenuElement_Hover(nElements);
    }

    public static MenuElement_Hover getHoverUnrest(int iProvinceID, boolean inProvinceInfo, boolean addReduce) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Unrest") + ": ", CFG.getPrecision2(Game.getProvince(iProvinceID).getRevulutionaryRisk(), 100) + "%", Images.revolutionRisk, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        float value = Game.getProvince(iProvinceID).getRevolutionaryRisk_MonhtlyChange();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MonthlyChange") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text((value > 0.0F ? "+" : "") + CFG.getPrecision2(value, 1000), CFG.FONT_BOLD_SMALL, value == 0.0F ? Colors.HOVER_NEUTRAL : (value < 0.0F ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getProvince(iProvinceID).getRevolutionaryRisk_MonhtlyChange_BudgetTaxation();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TaxationLevel") + ": ", CFG.getPrecision2(value, 1000), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, value == 0.0F ? Colors.HOVER_NEUTRAL : (value < 0.0F ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getProvince(iProvinceID).getRevolutionaryRisk_MonhtlyChange_WarWeariness();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("WarWeariness") + ": ", CFG.getPrecision2(value, 10000), Images.weariness, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, value == 0.0F ? Colors.HOVER_NEUTRAL : (value < 0.0F ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        value = Game.getProvince(iProvinceID).getRevolutionaryRisk_MonhtlyChange_CivStability();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CivilizationStability") + ": ", CFG.getPrecision2(value, 10000), Images.civStability, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, value == 0.0F ? Colors.HOVER_NEUTRAL : (value < 0.0F ? Colors.HOVER_POSITIVE : Colors.HOVER_NEGATIVE)));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.getProvince(iProvinceID).getRevulutionaryRisk() > 0.0F && addReduce) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ReduceRevolutionaryRisk"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.revolutionRisk, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.revolutionManager.getDecreaseRevolutionaryRisk_CostGold(iProvinceID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.revolutionManager.getDecreaseRevolutionaryRisk_CostLegacy(iProvinceID), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        return inProvinceInfo ? new MenuElement_Hover(nElements) {
            public int getMinPosX() {
                return InGame_ProvinceInfo.HOVER_POSX;
            }

            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        } : new MenuElement_Hover(nElements);
    }

    public static MenuElement_Hover getHoverLoot(int iProvinceID, boolean inProvinceInfo) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Loot") + ": ", "" + CFG.getPrecision2(Game.getProvince(iProvinceID).getLoot() * 100.0F, 1) + "%", Images.loot, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Gold") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(Game.getLootValue(iProvinceID), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(" = " + Game.lang.get("ProvinceIncome") + " * " + CFG.getPrecision2(GameValues.siege.LOOT_PROVINCE_PER_PROVINCE_INCOME, 100) + " * " + Game.lang.get("Loot"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        if (Game.getProvince(iProvinceID).getLoot() < 0.99F && !Game.getProvince(iProvinceID).isOccupied()) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("MonthlyChange") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_TextTitle("+" + CFG.getPrecision2(GameValues.siege.LOOT_PROVINCE_RECOVERY_PER_MONTH * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.loot, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (GameValues.siege.OCCUPY_PROVINCE_DECREASE_GROWTH_RATE != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Occupation") + ", " + Game.lang.get("GrowthRate") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.siege.OCCUPY_PROVINCE_DECREASE_GROWTH_RATE, 100), CFG.FONT_BOLD_SMALL, GameValues.siege.OCCUPY_PROVINCE_DECREASE_GROWTH_RATE < 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.populationGrowth, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        return inProvinceInfo ? new MenuElement_Hover(nElements) {
            public int getMinPosX() {
                return InGame_ProvinceInfo.HOVER_POSX;
            }

            public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                super.draw(oSB, nPosX, nPosY);
            }
        } : new MenuElement_Hover(nElements);
    }

    public static MenuElement_Hover getHoverFort(int iProvinceID, boolean inProvinceInfo) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DefenseLevel") + ": ", "" + Game.getProvince(iProvinceID).getFortLevel(), Images.fort, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceDefense") + ": ", "" + Game.getProvince(iProvinceID).getFortDefense(), Game_Calendar.IMG_FORT_DEFENSE, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(" = " + CFG.getPrecision2(GameValues.siege.SIEGE_FORT_DEFENSE_DEFAULT, 100) + " + " + Game.lang.get("DefenseLevel") + " * " + CFG.getPrecision2(GameValues.siege.SIEGE_FORT_DEFENSE_PER_FORT_LVL, 100) + " + " + Game.lang.get("LocalManpowerLevel") + " * " + CFG.getPrecision2(GameValues.siege.SIEGE_FORT_DEFENSE_PER_MANPOWER_LVL, 100) + " + " + Game.lang.get("GrowthRate") + "*" + CFG.getPrecision2(GameValues.siege.SIEGE_FORT_DEFENSE_PER_GROWTH_RATE_LVL, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
        nElements.add(new MenuElement_HoverElement(nData, false));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("AfterASuccessfulSiegeAllNeighboringProvincesWithoutDefensiveBuildingsWillBeOccupied"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("MinimumArmyRequiredToSiegeProvinceX", CFG.getPrecision2((float)(GameValues.siege.SIEGE_REGIMENTS_MIN * ((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).REGIMENT_SIZE), 1)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        int tNeighboring = 0;

        for(int i = 0; i < Game.getProvince(iProvinceID).getNeighboringProvincesSize(); ++i) {
            if (Game.getProvince(Game.getProvince(iProvinceID).getNeighboringProvinces(i)).getCivID() == Game.getProvince(iProvinceID).getCivID() && Game.getProvince(Game.getProvince(iProvinceID).getNeighboringProvinces(i)).getFortLevel() == 0) {
                ++tNeighboring;
            }
        }

        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NeighboringProvinces") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getProvince(iProvinceID).getNeighboringProvincesSize(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ProvincesWithoutForts") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text("" + tNeighboring, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.provinces, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (inProvinceInfo) {
            return new MenuElement_Hover(nElements) {
                public int getMinPosX() {
                    return InGame_ProvinceInfo.HOVER_POSX;
                }

                public void draw(SpriteBatch oSB, int nPosX, int nPosY) {
                    nPosY = InGame_ProvinceInfo.HOVER_POSY + (CFG.GAME_HEIGHT - InGame_ProvinceInfo.HOVER_POSY) / 2 - this.iHeight / 2;
                    super.draw(oSB, nPosX, nPosY);
                }
            };
        } else {
            return new MenuElement_Hover(nElements);
        }
    }

    public static boolean actionTax(int iProvinceID) {
        boolean out = false;
        if (Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID) {
            if (!AA_KeyManager.SHIFT_HOLD && !AA_KeyManager.CTRL_HOLD) {
                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(iProvinceID)) {
                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(iProvinceID), 100), Images.legacy);
                } else if (!Game.getProvince(iProvinceID).addIncreaseTaxEfficiencyInProvince(Game.player.iCivID)) {
                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(iProvinceID), 100), Images.gold);
                } else {
                    ProvinceDraw.addProvinceDot_TaxEfficiency(iProvinceID);
                    out = true;
                }
            } else {
                for(int i = 0; i < 5; ++i) {
                    if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(iProvinceID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(iProvinceID), 100), Images.legacy);
                    } else {
                        if (!Game.getProvince(iProvinceID).addIncreaseTaxEfficiencyInProvince(Game.player.iCivID)) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(iProvinceID), 100), Images.gold);
                            break;
                        }

                        ProvinceDraw.addProvinceDot_TaxEfficiency(iProvinceID);
                        out = true;
                    }
                }
            }
        }

        return out;
    }

    public static boolean actionEconomy(int iProvinceID) {
        boolean out = false;
        if (Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID) {
            if (!AA_KeyManager.SHIFT_HOLD && !AA_KeyManager.CTRL_HOLD) {
                if (Game.canInvestInEconomy(iProvinceID)) {
                    Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                    Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(iProvinceID).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(iProvinceID), 10), Game_Calendar.IMG_ECONOMY);
                } else if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(iProvinceID)) {
                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(iProvinceID), 100), Images.legacy);
                } else if (!Game.getProvince(iProvinceID).addInvestInProvince(Game.player.iCivID)) {
                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(iProvinceID), 100), Images.gold);
                } else {
                    out = true;
                    ProvinceDraw.addProvinceDot_Economy(iProvinceID);
                }
            } else {
                for(int i = 0; i < 5; ++i) {
                    if (Game.canInvestInEconomy(iProvinceID)) {
                        Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                        Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(iProvinceID).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(iProvinceID), 10), Game_Calendar.IMG_ECONOMY);
                        break;
                    }

                    if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(iProvinceID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(iProvinceID), 100), Images.legacy);
                    } else {
                        if (!Game.getProvince(iProvinceID).addInvestInProvince(Game.player.iCivID)) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(iProvinceID), 100), Images.gold);
                            break;
                        }

                        out = true;
                        ProvinceDraw.addProvinceDot_Economy(iProvinceID);
                    }
                }
            }
        }

        return out;
    }

    public static boolean actionManpower(int iProvinceID) {
        boolean out = false;
        if (Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID) {
            if (!AA_KeyManager.SHIFT_HOLD && !AA_KeyManager.CTRL_HOLD) {
                if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseManpowerCostLegacy(iProvinceID)) {
                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseManpowerCostLegacy(iProvinceID), 100), Images.legacy);
                } else if (!Game.getProvince(iProvinceID).addIncreaseManpowerInProvince(Game.player.iCivID)) {
                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseManpowerCost(iProvinceID), 100), Images.gold);
                } else {
                    ProvinceDraw.addProvinceDot_Manpower(iProvinceID);
                    out = true;
                }
            } else {
                for(int i = 0; i < 5; ++i) {
                    if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseManpowerCostLegacy(iProvinceID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseManpowerCostLegacy(iProvinceID), 100), Images.legacy);
                        break;
                    }

                    if (!Game.getProvince(iProvinceID).addIncreaseManpowerInProvince(Game.player.iCivID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseManpowerCost(iProvinceID), 100), Images.gold);
                    } else {
                        ProvinceDraw.addProvinceDot_Manpower(iProvinceID);
                        out = true;
                    }
                }
            }
        }

        return out;
    }

    public static boolean actionGrowthRate(int iProvinceID) {
        boolean out = false;
        if (Game.getProvince(iProvinceID).getCivID() == Game.player.iCivID) {
            if (!AA_KeyManager.SHIFT_HOLD && !AA_KeyManager.CTRL_HOLD) {
                if (!Game.getProvince(iProvinceID).addIncreaseGrowthRateInProvince(Game.player.iCivID)) {
                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseGrowthRateCost(iProvinceID), 100), Images.gold);
                } else {
                    ProvinceDraw.addProvinceDot_GrowthRate(iProvinceID);
                    out = true;
                }
            } else {
                for(int i = 0; i < 5; ++i) {
                    if (!Game.getProvince(iProvinceID).addIncreaseGrowthRateInProvince(Game.player.iCivID)) {
                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseGrowthRateCost(iProvinceID), 100), Images.gold);
                        break;
                    }

                    ProvinceDraw.addProvinceDot_GrowthRate(iProvinceID);
                    out = true;
                }
            }
        }

        return out;
    }

    public static boolean actionInfrastructure(int provinceID) {
        if (Game.getProvince(provinceID).getCivID() == Game.player.iCivID && addDevelopInfrastructureCost(provinceID)) {
            ProvinceDraw.addProvinceDot_Infrastructure(provinceID);
            return true;
        } else {
            return false;
        }
    }

    public static boolean actionUnrest(int provinceID) {
        if (Game.getProvince(provinceID).getRevulutionaryRisk() > 0.0F) {
            if (Game.getProvince(provinceID).getRevulutionaryRisk() <= 0.0F) {
                return false;
            }

            if (Game.getProvince(provinceID).getCivID() != Game.player.iCivID) {
                return false;
            }

            if (Game.getCiv(Game.player.iCivID).fGold < GameValues.province.UNREST_DECREASE_COST_GOLD) {
                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(GameValues.province.UNREST_DECREASE_COST_GOLD, 100), Images.gold);
                return false;
            }

            if (Game.getCiv(Game.player.iCivID).fLegacy < GameValues.province.UNREST_DECREASE_COST_LEGACY) {
                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(GameValues.province.UNREST_DECREASE_COST_LEGACY, 100), Images.legacy);
                return false;
            }

            if (Game.revolutionManager.decreaseRevolutionaryRisk(Game.player.iCivID, provinceID)) {
                InGame_Info.iCivID = Game.player.iCivID;
                InGame_Info.iCivID2 = Game.player.iCivID;
                Game.menuManager.rebuildInGame_Info(Game.lang.get("RevolutionaryRiskReduced"), Game.getProvince(provinceID).getProvinceName());
                InGame_Info.imgID = Images.infoUnrest;
                return true;
            }
        }

        return false;
    }
}
