//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusInGame;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.jakowski.Touch;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.SteamMultiManager;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Synchronize.M_GameState;
import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import aoc.kingdoms.lukasz.map.allianceHRE.HREManager;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRanking;
import aoc.kingdoms.lukasz.map.civilization.CivilizationsNeighbors;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.province.ProvinceInvest;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.menu.ColorPicker;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.HoverManager;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Minimap;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.Button32Padd;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_CurrentSituation;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Rank;
import aoc.kingdoms.lukasz.menu_element.button.ButtonTopDate;
import aoc.kingdoms.lukasz.menu_element.button.ButtonTopFlag;
import aoc.kingdoms.lukasz.menu_element.button.ButtonTopOutliner;
import aoc.kingdoms.lukasz.menu_element.button.ButtonTopSpeed;
import aoc.kingdoms.lukasz.menu_element.graph.Graph.GraphType;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus_Perc;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Graph;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Space;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextTop_Diplomacy;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextTop_Gold;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextTop_Legacy;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextTop_Manpower;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextTop_Nukes;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextTop_Technology;
import aoc.kingdoms.lukasz.menus.MainMenu;
import aoc.kingdoms.lukasz.menusInGame.Battle.InGame_OngoingBattles;
import aoc.kingdoms.lukasz.menusInGame.Budget.InGame_Budget;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_Compare;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_List;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions2;
import aoc.kingdoms.lukasz.menusInGame.DrawOver.InGameDrawOver;
import aoc.kingdoms.lukasz.menusInGame.DrawOver.InGameDrawOver_RecruitArmy;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitArmy;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_Right;
import aoc.kingdoms.lukasz.menusInGame.Technology.InGame_TechnologyChoose;
import aoc.kingdoms.lukasz.menusInGame.Technology.InGame_TechnologyTree;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;

import javax.script.Invocable;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class InGame extends Menu {
    public static InGameDrawOver drawOver = new InGameDrawOver();
    public static boolean ONLY_MAP_MODE = false;
    public static int rankPosXW = 0;
    public static int topStatsPadding = 0;
    public static int topRightPadding = 0;
    public static int topStatsHeight = 0;
    public static int topBar2PosY = 0;
    public static int leftSideBarPadding = 0;
    public static int leftSideBarInnerWidth = 0;
    public static int outlinerExtraX = 0;
    public static int outlinerExtraClassic = 0;
    public static int outlinerExtraUQ = 0;
    public static int iMinimapPosY = 0;
    public static boolean inAnimation = false;
    public static boolean hideAnimation = false;
    public long minimapTime = 0L;
    public int minimapAnimationTime = 275;
    public int lastStatElementID = 0;
    public int lastStatElementID2 = 0;
    public int dateElementID = 0;
    public int plusElementID = 0;
    public int minusElementID = 0;
    public int outlinerElementID = 0;
    public int minimapElementID = 0;

    //ANALYTICALENGINE START
    public static boolean mapmode_list_displayed = false;
    //ANALYTICALENGINE END

    public static final void updateDrawOver() {
        if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_RECRUIT_ARMY && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_NEW_ARMY_CHOOSE_PROVINCE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_NUKE_CHOOSE_PROVINCE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_SELL_PROVINCES && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_MERCENARIES_CHOOSE_PROVINCE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INVEST_IN_ECONOMY && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_MANPOWER && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_MOVE_CAPITAL && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_GROWTH_RATE && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_BUILDING && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_CONVERT_RELIGION && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_CORE) {
            drawOver = new InGameDrawOver();
        } else {
            drawOver = new InGameDrawOver_RecruitArmy();
        }

    }

    public static void initInGame() {
        try {
            FileHandle tempFileT = FileManager.loadFile("ui/" + CFG.getRescouresPath() + "top/" + "topStatsAndRightPadding.txt");
            String[] tempSplit = tempFileT.readString().split(";");
            topStatsPadding = Integer.parseInt(tempSplit[0]);
            topRightPadding = Integer.parseInt(tempSplit[1]);
            topStatsHeight = Integer.parseInt(tempSplit[2]);
            topBar2PosY = ImageManager.getImage(Images.topStats).getHeight() - Integer.parseInt(tempSplit[2]);
            leftSideBarPadding = Integer.parseInt(tempSplit[4]);
            leftSideBarInnerWidth = Integer.parseInt(tempSplit[5]);
            outlinerExtraClassic = Integer.parseInt(tempSplit[6]);
            outlinerExtraUQ = Integer.parseInt(tempSplit[7]);
        } catch (GdxRuntimeException ex) {
            CFG.exceptionStack(ex);
        }

    }

    public InGame() {
        List<MenuElement> menuElements = new ArrayList();
        int initMinimapPosY = iMinimapPosY;
        iMinimapPosY = 0;
        menuElements.add(new ButtonTopFlag(0, 0, true) {
            public int getSFX() {
                return SoundsManager.FLAG_CLICK;
            }

            public void actionElement() {
                InGame.action1();
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, CivilizationRanking.getCivilizationRank_Name(Game.getCiv(Game.player.iCivID).iCivRankID)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(GameValues.court.COUNCIL_NAME), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(Game.player.iCivID).ruler != null) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.ideologiesManager.getIdeology(Game.getCiv(Game.player.iCivID).getIdeologyID()).RulerTitle + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(Game.player.iCivID).ruler.Name));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Space());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("F1", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        int elemPosX = ImageManager.getImage(Images.flagBG).getWidth() + CFG.PADDING + CFG.PADDING / 2;
        menuElements.add(new TextTop_Gold(Images.gold, "", "", elemPosX, CFG.PADDING) {
            public int getSFX() {
                return this.getIsActiveButton() ? SoundsManager.SOUND_CLICK_TOP : SoundsManager.BUDGET_CLICK;
            }

            public void updateHovered() {
                super.updateHovered();
                if (CFG.isDesktop() && GameValues.mapModes.TOP_HOVER_BUDGET && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_INCOME_HOVER);
                }

            }

            public void setIsHovered(boolean isHovered) {
                super.setIsHovered(isHovered);
                if (CFG.isDesktop() && GameValues.mapModes.TOP_HOVER_BUDGET && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_INCOME_HOVER) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Budget"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fVal = Game.getCiv(Game.player.iCivID).fGold;
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus_Perc(Game.lang.get("Treasury") + ": ", "" + CFG.getPrecision2(fVal, 100), Images.gold, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, fVal == 0.0F ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : (fVal > 0.0F ? Colors.HOVER_GOLD : Colors.COLOR_TEXT_MODIFIER_NEGATIVE), fVal / (float)Game.getMaxAmountOfGold(Game.player.iCivID)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumAmountOfGold") + ": ", "" + CFG.getNumberWithSpaces("" + CFG.getPrecision2((float)Game.getMaxAmountOfGold(Game.player.iCivID), 1)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Game.getCiv(Game.player.iCivID).fGold >= (float)Game.getMaxAmountOfGold(Game.player.iCivID) ? Colors.HOVER_NEGATIVE : Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                fVal = Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(Game.player.iCivID).civBonuses.MonthlyIncome;
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TotalIncome") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text((fVal > 0.0F ? "+" : "") + CFG.getPrecision2(fVal, 100), CFG.FONT_BOLD_SMALL, fVal == 0.0F ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : (fVal > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                fVal = Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth;
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TotalExpenses") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text((fVal > 0.0F ? "-" : "+") + CFG.getPrecision2(fVal, 100), CFG.FONT_BOLD_SMALL, fVal == 0.0F ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : (fVal > 0.0F ? Colors.COLOR_TEXT_MODIFIER_NEGATIVE : Colors.COLOR_TEXT_MODIFIER_POSITIVE)));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.goldNegative, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                fVal = Game.getCiv(Game.player.iCivID).fTotalIncomePerMonth + Game.getCiv(Game.player.iCivID).civBonuses.MonthlyIncome - Game.getCiv(Game.player.iCivID).fTotalExpensesPerMonth;
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Balance") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text((fVal > 0.0F ? "+" : "") + CFG.getPrecision2(fVal, 100), CFG.FONT_BOLD_SMALL, fVal == 0.0F ? Colors.COLOR_TEXT_MODIFIER_NEUTRAL : (fVal > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();

                try {
                    nData.add(new MenuElement_HoverElement_Type_Empty());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Graph(Game.lang.get("Income"), GraphType.PLAYER_INCOME, true));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Empty());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }

                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Space());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("F2", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public void actionElement() {
                InGame.action2();
            }

            public void actionElementPPM() {
                InGame_Budget.actionTakeLoan();
            }
        });
        menuElements.add(new TextTop_Manpower(Game_Calendar.IMG_MANPOWER, "", "", elemPosX, CFG.PADDING) {
            public int getSFX() {
                return this.getIsActiveButton() ? SoundsManager.SOUND_CLICK_TOP : SoundsManager.ARMY_CLICK;
            }

            public int getImageID() {
                return Game_Calendar.IMG_MANPOWER;
            }

            public int getPosX() {
                return InGame.this.getMenuElement(1).getPosX() + InGame.this.getMenuElement(1).getWidth() + InGame.this.getStatsPadding();
            }

            public void actionElement() {
                InGame.action3();
            }

            public void actionElementPPM() {
                if (Game.menuManager.inCreateNewArmy && Game.menuManager.getVisibleInGame_RecruitArmy()) {
                    Game.menuManager.setVisibleInGame_RecruitArmy(false);
                } else {
                    InGame_RecruitArmy.actionCreateNewArmy();
                    Game.menuManager.hideMenus_RecruitArmy(true);
                }

            }

            public void updateHovered() {
                super.updateHovered();
                if (CFG.isDesktop() && GameValues.mapModes.TOP_HOVER_ARMY && HoverManager.hoverTime + GameValues.mapModes.HOVER_TIME <= CFG.currentTimeMillis && this.getIsHovered() && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEFAULT) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_PROVINCE_MANPOWER_HOVER_PLAYER);
                }

            }

            public void setIsHovered(boolean isHovered) {
                super.setIsHovered(isHovered);
                if (CFG.isDesktop() && GameValues.mapModes.TOP_HOVER_ARMY && !isHovered && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PROVINCE_MANPOWER_HOVER_PLAYER) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                Civilization playerCiv = Game.getCiv(Game.player.iCivID);
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("RecruitRegiments"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float manpowerPerc = (float)Math.min((double)1.0F, playerCiv.fManpower / playerCiv.fManpowerMax);
                int manpowerDays = (int)((1.0F - manpowerPerc) * GameValues.manpower.MANPOWER_FULL_RECOVERY_MONTHS * 30.0F);
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus_Perc(Game.lang.get("Manpower") + ": ", "" + CFG.getNumberWithSpaces("" + (int)Math.floor(playerCiv.fManpower)) + " [" + (int)(manpowerPerc * 100.0F) + "%]", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD, manpowerPerc));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "" + CFG.getNumberWithSpaces("" + (int)Math.floor(playerCiv.fManpowerMax)), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (manpowerPerc < 0.98F && Game_Calendar.getNumOfDays_ByTurnsPlayed_WithoutDays(manpowerDays).length() > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus((int)(manpowerPerc * 100.0F) + "% -> 100%: ", Game_Calendar.getNumOfDays_ByTurnsPlayed_WithoutDays(manpowerDays), Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("0% -> 100%: ", "" + Game.lang.get("MonthsX", (int)GameValues.manpower.MANPOWER_FULL_RECOVERY_MONTHS), Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                int armiesManpower = 0;
                int armiesManpowerFull = 0;

                try {
                    for(int i = 0; i < playerCiv.iArmyPositionSize; ++i) {
                        ArmyDivision armyDivision = Game.getProvince(playerCiv.getArmyPosition(i)).getArmy(playerCiv.getArmyPositionKey(i));
                        if (armyDivision != null) {
                            for(int j = 0; j < armyDivision.iArmyRegimentSize; ++j) {
                                armiesManpower += ((ArmyRegiment)armyDivision.lArmyRegiment.get(j)).num;
                                ++armiesManpowerFull;
                            }
                        }
                    }

                    armiesManpowerFull *= ((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).REGIMENT_SIZE;
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Armies") + ", " + Game.lang.get("Strength") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)armiesManpower / (float)armiesManpowerFull * 100.0F, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Armies") + ", " + Game.lang.get("Manpower") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + armiesManpower) + " / " + CFG.getNumberWithSpaces("" + armiesManpowerFull), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                } catch (Exception var11) {
                }

                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MonthlyChange") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getNumberWithSpaces("" + CFG.getPrecision2(playerCiv.fManpowerPerMonth, 1)), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BaseValue") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + GameValues.manpower.MANPOWER_MAX_BASE), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Provinces") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + (int)Math.floor((double)Math.max(0.0F, playerCiv.getManpowerMax_Provinces_INFO()))), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                if (GameValues.civRank.CIV_RANK_MANPOWER_MAX[playerCiv.iCivRankID] != 0) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationRank") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.getPrecision2((float)GameValues.civRank.CIV_RANK_MANPOWER_MAX[playerCiv.iCivRankID], 1)), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationBonuses") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.getPrecision2(playerCiv.civBonuses.MaxManpower, 1)), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                float tNum = playerCiv.getManpowerMax_Vassals_INFO();
                if (tNum > 0.0F) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(Game_Ages.getVassals()) + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + (int)Math.floor((double)tNum)), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                if (playerCiv.fManpowerMax_ToLord != (double)0.0F) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(Game_Ages.getLord()) + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(playerCiv.fManpowerMax_ToLord * (double)-1.0F, 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_NEGATIVE));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                for(int i = 0; i < playerCiv.inAllianceSize; ++i) {
                    if (((Alliance)Game.alliancesSpecial.get((Integer)playerCiv.inAlliance.get(i))).iLeaderCivID == Game.player.iCivID && ((Alliance)Game.alliancesSpecial.get((Integer)playerCiv.inAlliance.get(i))).typeOfAlliance == 0) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(((Alliance)Game.alliancesSpecial.get((Integer)playerCiv.inAlliance.get(i))).Name_Alliance) + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getNumberWithSpaces("" + CFG.getPrecision2((float)HREManager.getManpower_Emperor((Integer)playerCiv.inAlliance.get(i)), 1)), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                        nElements.add(new MenuElement_HoverElement(nData, false));
                        nData.clear();
                    }
                }

                if (playerCiv.civBonuses.MaxManpower_Percentage != 0.0F) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumManpower") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text((playerCiv.civBonuses.MaxManpower_Percentage > 0.0F ? "+" : "") + CFG.getPrecision2(playerCiv.civBonuses.MaxManpower_Percentage * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Space());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("F3", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new TextTop_Technology(Game_Calendar.IMG_TECHNOLOGY, "", "", elemPosX, CFG.PADDING) {
            public int getSFX() {
                return this.getIsActiveButton() ? SoundsManager.SOUND_CLICK_TOP : SoundsManager.TECHNOLOGY_CLICK;
            }

            public int getPosX() {
                return InGame.this.getMenuElement(2).getPosX() + InGame.this.getMenuElement(2).getWidth() + InGame.this.getStatsPadding();
            }

            public void actionElement() {
                InGame.action4();
            }

            public void actionElementPPM() {
                if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                    Game.menuManager.setVisibleInGame_TechnologyTree(false);
                } else {
                    Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                    InGame_TechnologyTree.centerToTechID = -1;
                    Game.menuManager.rebuildInGame_TechnologyTree(false, true);
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                if (Game.getCiv(Game.player.iCivID).getActiveTechResearch() >= 0) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Technology") + ": ", CFG.FONT_BOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(((TechnologyTree.Technology)TechnologyTree.lTechnology.get(Game.getCiv(Game.player.iCivID).getActiveTechResearch())).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus_Perc(Game.lang.get("ResearchProgress") + ": ", CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getResearchProgress(Game.getCiv(Game.player.iCivID).getActiveTechResearch()) * 100.0F, 100) + "%", Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD, Game.getCiv(Game.player.iCivID).getResearchProgress(Game.getCiv(Game.player.iCivID).getActiveTechResearch())));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                } else {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ChooseResearch"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus_Perc(Game.lang.get("MonthlyChange") + ": ", "+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fResearchPerMonth, 100) + " / " + CFG.getPrecision2(TechnologyTree.getMaxResearch(Game.player.iCivID), 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD, Game.getCiv(Game.player.iCivID).fResearchPerMonth / TechnologyTree.getMaxResearch(Game.player.iCivID)));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumResearch") + ": ", CFG.getPrecision2(TechnologyTree.getMaxResearch(Game.player.iCivID), 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("MaximumResearch") + " = " + CFG.getPrecision2(GameValues.research.MAX_RESEARCH_BASE, 100) + " + " + Game.lang.get("Capital") + ", " + Game.lang.get("GrowthRate") + " * " + CFG.getPrecision2(GameValues.research.MAX_RESEARCH_PER_GROWTH_RATE_IN_CAPITAL, 100) + " + " + Game.lang.get("CapitalCity") + ", " + Game.lang.get("Level") + " * " + CFG.getPrecision2(GameValues.capital.CAPITAL_MAX_RESEARCH_PER_LVL, 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT3));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BaseValue") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.research.BASE_RESEARCH, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Buildings") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getResearchFromBuildings(), 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                if (Game.getCiv(Game.player.iCivID).advisorTechnology.Research != 0.0F) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(GameValues.court.ADVISOR_NAME_INNOVATION) + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).advisorTechnology.Research, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                if (GameValues.civRank.CIV_RANK_MONTHLY_RESEARCH[Game.getCiv(Game.player.iCivID).iCivRankID] != 0.0F) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationRank") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_MONTHLY_RESEARCH[Game.getCiv(Game.player.iCivID).iCivRankID], 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                if (Game.getCiv(Game.player.iCivID).civBonuses.ResearchPoints != 0.0F) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("CivilizationBonuses") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).civBonuses.ResearchPoints, 100), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                if (Game.getCiv(Game.player.iCivID).civBonuses.Research != 0.0F) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Research") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).civBonuses.Research, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Space());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("F4", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new TextTop_Legacy(Images.legacy, "", "", elemPosX, CFG.PADDING) {
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }

            public int getPosX() {
                return InGame.this.getMenuElement(3).getPosX() + InGame.this.getMenuElement(3).getWidth() + InGame.this.getStatsPadding();
            }

            public void actionElement() {
                InGame.action5();
            }

            public void actionElementPPM() {
                if (Game.menuManager.getVisibleInGame_TechnologyChoose()) {
                    Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                } else {
                    Game.menuManager.rebuildInGame_CivilizationAdvantages(Game.player.iCivID);
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CivilizationLegacy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.legacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus_Perc(Game.lang.get("LegacyPoints") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fLegacy, 100), Images.legacy, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD, Game.getCiv(Game.player.iCivID).fLegacy / GameValues.legacy.MAX_LEGACY_POINTS));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyChange") + ": ", "" + (Game.getCiv(Game.player.iCivID).getLegacyPerMonth() > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fLegacyPerMonth, 100), Images.legacy, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Legacy0"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Max") + ": ", "" + CFG.getPrecision2(GameValues.legacy.MAX_LEGACY_POINTS, 100), Images.legacy, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Space());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("F5", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new TextTop_Diplomacy(Images.diplomacy, "", "", elemPosX, CFG.PADDING) {
            public int getSFX() {
                return this.getIsActiveButton() ? SoundsManager.SOUND_CLICK_TOP : SoundsManager.DIPLOMACY1;
            }

            public int getPosX() {
                return InGame.this.getMenuElement(4).getPosX() + InGame.this.getMenuElement(4).getWidth() + InGame.this.getStatsPadding();
            }

            public void actionElement() {
                InGame.action6();
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Diplomacy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus_Perc(Game.lang.get("DiplomacyPoints") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fDiplomacy, 100), Images.diplomacy, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD, Game.getCiv(Game.player.iCivID).fDiplomacy / Game.getCiv(Game.player.iCivID).fDiplomacyMax));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumDiplomacyPoints") + ": ", "" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fDiplomacyMax, 100), Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyChange") + ": ", (Game.getCiv(Game.player.iCivID).getDiplomacyPerMonth() > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getDiplomacyPerMonth(), 100), Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BaseValue") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)GameValues.diplomacy.DIPLOMACY_POINTS_MAX, 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Provinces") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_POINTS_MAX_PER_PROVINCE * (float)Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100), CFG.FONT_BOLD_SMALL, Colors.HOVER_POSITIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.diplomacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData, false));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaxNumOfAlliances") + ": ", "" + DiplomacyManager.getMaxNumberOfAlliances(Game.player.iCivID), Images.alliance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Space());
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text("F6", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData, false));
                    nData.clear();
                }

                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public boolean getIsActiveButton() {
                return Game.menuManager.getVisibleInGame_Civ();
            }
        });
        menuElements.add(new TextTop_Nukes(Images.nuke, "", "", elemPosX, CFG.PADDING) {
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }

            public int getImageID() {
                return Images.nuke;
            }

            public int getPosX() {
                return this.getVisible() ? InGame.this.getMenuElement(5).getPosX() + InGame.this.getMenuElement(5).getWidth() + InGame.this.getStatsPadding() : InGame.this.getMenuElement(4).getPosX() + InGame.this.getMenuElement(4).getWidth() + InGame.this.getStatsPadding();
            }

            public boolean getVisible() {
                return Game.getCiv(Game.player.iCivID).canBuildNuke;
            }

            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_Nukes()) {
                    Game.menuManager.setVisibleInGame_Nukes(false);
                } else {
                    Game.menuManager.rebuildInGame_Nukes();
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("NuclearWeapons"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.nuke, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AtomicBombs") + ": ", "" + Game.getCiv(Game.player.iCivID).getNukes(), Images.nuke, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(Game.player.iCivID).iNukesSize > 0) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AtomicBombUnderConstruction") + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DaysX", ((ProvinceInvest)Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0)).daysLeft), CFG.FONT_REGULAR_SMALL, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_HoverElement_Type_Text("[" + CFG.getPrecision2((1.0F - (float)((ProvinceInvest)Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0)).daysLeft / (float)((ProvinceInvest)Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(0)).investTime) * 100.0F, 10) + "%]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (Game.getCiv(Game.player.iCivID).iNukesSize > 1) {
                        int tDays = 0;

                        for(int i = 0; i < Game.getCiv(Game.player.iCivID).iNukesSize; ++i) {
                            tDays += ((ProvinceInvest)Game.getCiv(Game.player.iCivID).nukesDaysLeft.get(i)).daysLeft;
                        }

                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("InQueue") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text("" + (Game.getCiv(Game.player.iCivID).iNukesSize - 1), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Text(" [" + Game.lang.get("DaysX", tDays) + "]", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }
                }

                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        this.lastStatElementID = menuElements.size() - 1;
        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), "999");
        rankPosXW = (int)Renderer.glyphLayout.width + ImageManager.getImage(Images.victoryPoints).getWidth() + CFG.PADDING * 4;
        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), "99");
        int rankPosXW2 = (int)Renderer.glyphLayout.width + ImageManager.getImage(Images.victoryPoints).getWidth() + CFG.PADDING * 4;
        int rankHeight = ImageManager.getImage(Images.topStats).getHeight() * 3 / 5;
        menuElements.add(new ButtonStatsRectIMG_Rank("" + Game.getCiv(Game.player.iCivID).iCivRankPosition, CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(Game.player.iCivID), elemPosX, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING / 2, rankPosXW, rankHeight, ImageManager.getImage(Images.rankGold).getWidth()) {
            public String getTextToDraw() {
                if (this.iCurrent != Game.getCiv(Game.player.iCivID).iCivRankPosition) {
                    this.iCurrent = Game.getCiv(Game.player.iCivID).iCivRankPosition;
                    this.setText("" + (Game.getCiv(Game.player.iCivID).iCivRankPosition < 1 ? "---" : Game.getCiv(Game.player.iCivID).iCivRankPosition));
                    if (Game.getCiv(Game.player.iCivID).iCivRankPosition < 1) {
                        this.imageID = Images.rankBlack;
                    } else {
                        this.imageID = CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(Game.player.iCivID);
                    }
                }

                return super.getTextToDraw();
            }

            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }

            public void actionElement() {
                InGame.actionRanking();
            }

            public void actionElementPPM() {
                if (Game.player.iCivID > 0 && Game.getCiv(Game.player.iCivID).getNumOfProvinces() > 0) {
                    if (Game.menuManager.getVisibleInGame_Civ()) {
                        Game.menuManager.setVisibleInGame_Civ(false);
                    } else {
                        InGame_Civ_List.civsList.clear();
                        InGame_Civ_List.civsListTitle = Game.lang.get("NeighbouringCivilizations");
                        InGame_Civ_List.civsListTitle2 = Game.getCiv(Game.player.iCivID).getCivName();
                        InGame_Civ_List.civsList.add(Game.player.iCivID);

                        for(int a = 0; a < Game.getCiv(Game.player.iCivID).civNeighbors.civsSize; ++a) {
                            if (((CivilizationsNeighbors.CivNeighbor)Game.getCiv(Game.player.iCivID).civNeighbors.civs.get(a)).byLand) {
                                InGame_Civ_List.civsList.add(((CivilizationsNeighbors.CivNeighbor)Game.getCiv(Game.player.iCivID).civNeighbors.civs.get(a)).civID);
                            }
                        }

                        Game.menuManager.rebuildInGame_Civ_List();
                    }
                }

            }

            public void buildElementHover() {
                this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(Game.player.iCivID, true, true);
            }

            protected Color getColor(boolean isActive) {
                return Colors.getColorTopStats(isActive, this.getIsHovered());
            }
        });
        menuElements.add(new ButtonStatsRectIMG_CurrentSituation("0", 0, elemPosX + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING, ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING / 2, rankPosXW2, rankHeight, ImageManager.getImage(Images.rankGold).getWidth()) {
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }

            public void actionElement() {
                InGame.actionCurrent();
            }

            protected Color getColor(boolean isActive) {
                return Colors.getColorTopStats(isActive, this.getIsHovered());
            }
        });
        this.lastStatElementID2 = menuElements.size() - 1;
        rankPosXW = ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosX() + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + topStatsPadding;
        menuElements.add(new ButtonTopSpeed(Images.plus, 0, topStatsHeight) {
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }

            public void actionElement() {
                Game.gameThread.updateSpeedPlus();
                Game.menuManager.TOAST_TIME = 0L;
            }

            public int getPosX() {
                return InGame.this.getMenuElement(InGame.this.dateElementID).getPosX() - this.getWidth();
            }
        });
        this.plusElementID = menuElements.size() - 1;
        menuElements.add(new ButtonTopDate(Game_Calendar.getCurrentDate(), 0, topStatsHeight) {
            public int getSFX() {
                return Game.gameThread.play ? SoundsManager.SOUND_CLICK_TOP : SoundsManager.PLAY;
            }

            public void actionElement() {
                if (SteamMultiManager.isHost()) {
                    Game.gameThread.play = !Game.gameThread.play;
                    M_GameState.updateGameState();
                } else {
                    M_GameState.nonHostPlayPauseAction();
                }

            }

            public int getPosX() {
                return CFG.GAME_WIDTH - InGame.getDatePadding() - this.getWidth();
            }
        });
        this.dateElementID = menuElements.size() - 1;
        menuElements.add(new ButtonTopSpeed(Images.minus, 0, topStatsHeight) {
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }

            public void actionElement() {
                Game.gameThread.updateSpeedMinus();
                Game.menuManager.TOAST_TIME = 0L;
            }

            public int getPosX() {
                return InGame.this.getMenuElement(InGame.this.plusElementID).getPosX() - this.getWidth();
            }
        });
        this.minusElementID = menuElements.size() - 1;
        menuElements.add(new ButtonTopOutliner(0, topStatsHeight) {
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }

            public void actionElement() {
                if (!CFG.isDesktop()) {
                    Touch.selectArmiesMode = !Touch.selectArmiesMode;
                } else {
                    InGame_Right.outlinerInView = !InGame_Right.outlinerInView;
                    Game.menuManager.rebuildInGame_Right();
                }

            }

            public int getPosX() {
                return InGame.this.getMenuElement(InGame.this.minusElementID).getPosX() - InGame.topRightPadding - this.getWidth();
            }
        });
        this.outlinerElementID = menuElements.size() - 1;

        //ANALYTICALENGINE START
        menuElements.add(new Minimap(0, 0) {
            public int getPosX() {
                return CFG.GAME_WIDTH - this.getWidth();
            }

            public int getPosY() {
                return CFG.GAME_HEIGHT - this.getHeight() + InGame.iMinimapPosY;
            }

            public boolean getVisible() {
                return super.getVisible() && Game.mapBG.getHideMenuZoomOut();
            }
        });
        this.minimapElementID = menuElements.size() - 1;
        menuElements.add(new Button32Padd(Images.arrowUpDown) {
            public void actionElement() {
                //Declare local instance variables
                Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;

                InGame.mapmode_list_displayed = (!InGame.mapmode_list_displayed);
                try {
                    invocable.invokeFunction("parseOnMapmodeListClick", InGame.mapmode_list_displayed);
                } catch (ScriptException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            /**
             * getFlipY() - Flips the main chevron GFX icon from side to side.
             *
             * @return boolean
             */
            public boolean getFlipY () {
                //Return statement
                return (!InGame.mapmode_list_displayed);
            }

            public int getPosX() {
                return CFG.GAME_WIDTH - Game.mapBG.iMinimapWidth /*- this.getWidth()*/ + CFG.PADDING;
            }

            public int getPosY() {
                return InGame.this.getMenuElement(InGame.this.minimapElementID).getPosY() - this.getHeight() - CFG.PADDING;
            }

            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(InGame.mapmode_list_displayed ? "HideMapmodes" : "ShowMapmodes"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }

            public boolean getVisible() {
                return super.getVisible() && Game.mapBG.getHideMenuZoomOut();
            }

            protected Color getColor(boolean isActive) {
                if (isActive) {
                    return Colors.COLOR_TOP_STATS_ACTIVE;
                } else {
                    return this.getIsHovered() ? Colors.COLOR_TOP_STATS_HOVER : buttonColor;
                }
            }

            public int getWidth() {
                return ImageManager.getImage(this.iconImageID).getWidth() + CFG.PADDING * 2;
            }
        });
        //ANALYTICALENGINE END

        this.initMenu((MenuTitle)null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
        iMinimapPosY = initMinimapPosY;
        if (!CFG.isDesktop() && GameValues.value.MOBILE_HIDE_MINIMAP) {
            iMinimapPosY = this.getMenuElement(this.minimapElementID).getHeight();
            inAnimation = false;
            hideAnimation = true;
        }
    }

    public final int getRightButtonsPadding() {
        return 0;
    }

    public final int getStatsPadding() {
        return CFG.PADDING * 2;
    }

    public static final int getDatePadding() {
        return CFG.PADDING;
    }

    public final void minimapAnimation() {
        if (inAnimation) {
            if (hideAnimation) {
                if (iMinimapPosY < this.getMenuElement(this.minimapElementID).getHeight()) {
                    iMinimapPosY = (int)((float)this.getMenuElement(this.minimapElementID).getHeight() * ((float)(CFG.currentTimeMillis - this.minimapTime) / (float)this.minimapAnimationTime));
                    if (iMinimapPosY > this.getMenuElement(this.minimapElementID).getHeight()) {
                        iMinimapPosY = this.getMenuElement(this.minimapElementID).getHeight();
                        inAnimation = false;
                    }
                } else {
                    iMinimapPosY = this.getMenuElement(this.minimapElementID).getHeight();
                    inAnimation = false;
                }
            } else if (iMinimapPosY > 0) {
                iMinimapPosY = (int)((float)this.getMenuElement(this.minimapElementID).getHeight() - (float)this.getMenuElement(this.minimapElementID).getHeight() * ((float)(CFG.currentTimeMillis - this.minimapTime) / (float)this.minimapAnimationTime));
                if (iMinimapPosY < 0) {
                    iMinimapPosY = 0;
                    inAnimation = false;
                }
            } else {
                iMinimapPosY = 0;
                inAnimation = false;
            }
        }

    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        this.minimapAnimation();
        Image topStats = ImageManager.getImage(Images.topStats);
        oSB.setColor(Color.WHITE);
        if (!Game.settingsManager.enableHideSideMenu || Game.menuManager.getVisibleInGame_Court()) {
            ImageManager.getImage(Images.leftSideBar).draw2(oSB, iTranslateX + Game.settingsManager.IN_GAME_LEFT_PADDING_EXTRA, iTranslateY, ImageManager.getImage(Images.leftSideBar).getWidth(), InGame_CourtOptions2.HEIGHT, false, true);
        }

        topStats.draw2(oSB, ImageManager.getImage(Images.flagBG).getWidth() / 2 + iTranslateX, topBar2PosY + this.getMenuElement(this.lastStatElementID2).getHeight() + CFG.PADDING + iTranslateY, this.getMenuElement(this.lastStatElementID2).getPosX() + this.getMenuElement(this.lastStatElementID2).getWidth() + topStatsPadding - ImageManager.getImage(Images.flagBG).getWidth() / 2, topStats.getHeight(), true, false);
        topStats.draw2(oSB, ImageManager.getImage(Images.flagBG).getWidth() / 2 + iTranslateX, iTranslateY, (this.getMenuElement(this.lastStatElementID).getVisible() ? this.getMenuElement(this.lastStatElementID).getPosX() + this.getMenuElement(this.lastStatElementID).getWidth() : this.getMenuElement(this.lastStatElementID - 1).getPosX() + this.getMenuElement(this.lastStatElementID - 1).getWidth()) + topStatsPadding - ImageManager.getImage(Images.flagBG).getWidth() / 2, topStats.getHeight(), true, false);
        topStats.draw2(oSB, this.getMenuElement(this.outlinerElementID).getPosX() - topRightPadding + iTranslateX, iTranslateY, CFG.GAME_WIDTH - (this.getMenuElement(this.outlinerElementID).getPosX() - topRightPadding), topStats.getHeight());
        int rightBG_X = this.getMenuElement(this.minusElementID).getPosX() + CFG.PADDING - getDatePadding() - topRightPadding;
        ImageManager.getImage(Images.topStats2).draw2(oSB, rightBG_X + iTranslateX, iTranslateY, CFG.GAME_WIDTH - rightBG_X, topStats.getHeight());
        if (Game.gameThread.play) {
            oSB.setColor(new Color(Colors.COLOR_TEXT_MODIFIER_NEGATIVE.r, Colors.COLOR_TEXT_MODIFIER_NEGATIVE.g, Colors.COLOR_TEXT_MODIFIER_NEGATIVE.b, 0.175F));
        } else {
            oSB.setColor(new Color(Colors.COLOR_TEXT_MODIFIER_NEGATIVE.r, Colors.COLOR_TEXT_MODIFIER_NEGATIVE.g, Colors.COLOR_TEXT_MODIFIER_NEGATIVE.b, 0.05F));
        }

        ImageManager.getImage(Images.gradientXYVertical).draw(oSB, rightBG_X + iTranslateX, iTranslateY, CFG.GAME_WIDTH - rightBG_X, topStats.getHeight(), true, false);
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, rightBG_X + iTranslateX, iTranslateY, CFG.GAME_WIDTH - rightBG_X, topStats.getHeight(), true, false);
        oSB.setColor(Color.WHITE);
        if (this.getMenuElement(this.minimapElementID).getVisible()) {
            MenuElement minimap = this.getMenuElement(this.minimapElementID);
            Renderer.drawBoxCorner(oSB, minimap.getPosX() - Images.boxTitleBORDERWIDTH + iTranslateX, Game.menuManager.getInGame_MapModesPosY() + iTranslateY, CFG.GAME_WIDTH - minimap.getPosX() + Images.boxTitleBORDERWIDTH, CFG.GAME_HEIGHT - Game.menuManager.getInGame_MapModesPosY() + Images.boxTitleBORDERWIDTH);
            ImageManager.getImage(Images.boxBIG_Red).draw2(oSB, minimap.getPosX() - Images.boxTitleBORDERWIDTH + iTranslateX, Game.menuManager.getInGame_MapModesPosY() + iTranslateY, minimap.getWidth() + Images.boxTitleBORDERWIDTH, CFG.GAME_HEIGHT - Game.menuManager.getInGame_MapModesPosY() + Images.boxTitleBORDERWIDTH);
            oSB.setColor(new Color(Colors.COLOR_TITLE.r, Colors.COLOR_TITLE.g, Colors.COLOR_TITLE.b, 0.15F));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, minimap.getPosX() + iTranslateX, minimap.getPosY() - CFG.PADDING * 2 + iTranslateY, minimap.getWidth(), CFG.PADDING * 2, false, true);
            Images.gradientXY.draw(oSB, minimap.getPosX() + iTranslateX, minimap.getPosY() - CFG.PADDING * 4 + iTranslateY, minimap.getWidth(), CFG.PADDING * 4);
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, minimap.getPosX() + iTranslateX, minimap.getPosY() - CFG.PADDING + iTranslateY, minimap.getWidth(), CFG.PADDING, false, true);
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
            Images.gradientFull.draw(oSB, minimap.getPosX() + iTranslateX, minimap.getPosY() - 2 + iTranslateY, minimap.getWidth(), 1);
            oSB.setColor(Color.WHITE);
            ImageManager.getImage(Images.boxBIG).draw2(oSB, minimap.getPosX() - Images.boxTitleBORDERWIDTH + iTranslateX, minimap.getPosY() + iTranslateY, minimap.getWidth() + Images.boxTitleBORDERWIDTH, CFG.GAME_HEIGHT - minimap.getPosY(), 0, Images.boxTitleBORDERWIDTH * 2);
        }

        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
        InGame_OngoingBattles.draw(oSB, iTranslateX, iTranslateY);
        drawOver.draw(oSB, iTranslateX, iTranslateY);
        Game.soundsManager.playRandomSounds();
    }

    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGame();
    }

    public static void action1() {
        CFG.brushTool = false;
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().setVisible(false, (ColorPicker.PickerAction)null);
        }

        if (Game.menuManager.getVisibleInGame_Court()) {
            Game.menuManager.setVisibleInGame_Court(false);
        } else {
            InGame_CourtOptions.iActiveID = InGame_CourtOptions2.idCourt;
            InGame_Court.modeID = 0;
            InGame_Court.iActiveCivID = Game.player.iCivID;
            Game.menuManager.rebuildInGame_Court();
            Game.menuManager.setVisibleInGame_Court(true);
            Game.setRegroupArmyMode(false);
            if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 35) {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }
        }

    }

    public static void action2() {
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().setVisible(false, (ColorPicker.PickerAction)null);
        }

        if (Game.menuManager.getVisibleInGame_Budget()) {
            Game.menuManager.setVisibleInGame_Budget(false);
        } else {
            Game.menuManager.rebuildInGame_Budget();
            Game.menuManager.setVisibleInGame_Budget(true);
        }

    }

    public static void action3() {
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().setVisible(false, (ColorPicker.PickerAction)null);
        }

        if (Game.menuManager.getVisibleInGame_RecruitArmy()) {
            Game.menuManager.setVisibleInGame_RecruitArmy(false);
        } else {
            if (Game.menuManager.getVisibleInGame_Armies()) {
                Game.menuManager.setVisibleInGame_Armies(false);
            }

            Game.menuManager.rebuildInGame_RecruitArmy();
            Game.menuManager.setVisibleInGame_RecruitArmy(true);
        }

    }

    public static void action4() {
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().setVisible(false, (ColorPicker.PickerAction)null);
        }

        if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
            Game.menuManager.setVisibleInGame_TechnologyTree(false);
        } else if (Game.menuManager.getVisibleInGame_TechnologyChoose() && InGame_TechnologyChoose.IN_TECHNOLOGY_CHOOSE) {
            Game.menuManager.setVisibleInGame_TechnologyChoose(false);
        } else {
            Game.menuManager.rebuildInGame_TechnologyChoose(false, true);
        }

    }

    public static void action5() {
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().setVisible(false, (ColorPicker.PickerAction)null);
        }

        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RECRUIT_ARMY) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }

        InGame_Legacies.iActiveCivID = Game.player.iCivID;
        MainMenu.bgTIME = System.currentTimeMillis();
        MainMenu.bgTIME_CHANGE = System.currentTimeMillis();
        MainMenu.bgAlpha = 0.0F;
        Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME_LEGACIES);
        Game.menuManager.setOrderOfMenu_InGameLegacies();
    }

    public static void action6() {
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().setVisible(false, (ColorPicker.PickerAction)null);
        }

        if (Game.menuManager.getVisibleInGame_Civ()) {
            Game.menuManager.setVisibleInGame_Civ(false);
        } else {
            Game.menuManager.rebuildInGame_Civ();
        }

    }

    public static void actionRanking() {
        if (Game.menuManager.getVisibleInGame_CurrentSituation() && !MenuManager.currentSituationMode) {
            Game.menuManager.setVisibleInGame_CurrentSituation(false);
        } else {
            InGame_Civ_Compare.civLeft_Rank = -1;
            InGame_Civ_Compare.civRight_Rank = -1;
            InGame_Ranking.sSearch = "";
            Game.menuManager.rebuildInGame_CurrentSituation_Ranking();
        }

    }

    public static void actionCurrent() {
        if ((!Game.menuManager.getVisibleInGame_CurrentSituation() || !MenuManager.currentSituationMode) && Game.player.currSituation.currentSituationNum != 0) {
            Game.menuManager.rebuildInGame_CurrentSituation();
        } else {
            Game.menuManager.setVisibleInGame_CurrentSituation(false);
        }

    }
}
