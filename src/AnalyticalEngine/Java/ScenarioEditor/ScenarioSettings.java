//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusScenarioEditor;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Keyboard;
import aoc.kingdoms.lukasz.jakowski.Keyboard.KeyboardActionType;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.map.MapScenarios;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawDetails;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonGame;
import aoc.kingdoms.lukasz.menu_element.button.ButtonMain;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsBudget;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoc.kingdoms.lukasz.menus.LoadSave.Menu_LoadSaveScenario;
import aoc.kingdoms.lukasz.menusMapEditor.EditorSelectProvinces;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioAlliancesList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioDefensiveList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioDiplomacy;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioGuaranteeList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioMilitaryAccessList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioNonAggressionList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioRelationsList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioTrucesList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioVassalsList;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioEconomy_List.ListMode;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class ScenarioSettings extends Menu {
    public static String sName = "";
    public static String sAuthor = "";
    public static String sTag = "";
    public static String sWiki = "";
    public static String sGold = "";
    public static String sGoldRandom = "";
    public static String sLegacy = "";
    public static String sLegacyRandom = "";
    public static String sManpower = "";
    public static String sProvinceDefault_Population = "";
    public static String sProvinceDefault_Economy = "";
    public static String sProvinceDefault_TaxEfficiency = "";
    public static String sProvinceDefault_Manpower = "";

    public ScenarioSettings() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = CFG.PADDING * 2;
        int titleHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2;
        int menuX = CFG.BUTTON_WIDTH*3 + CFG.PADDING*4;
        int menuY = CFG.GAME_HEIGHT / 10;
        int buttonYPadding = CFG.PADDING * 2;
        int textPosX = CFG.PADDING * 4;
        sGold = "" + MapScenarios.scenarioEditorDetails.CivDefault_Gold;
        sGoldRandom = "" + MapScenarios.scenarioEditorDetails.CivDefault_GoldRandom;
        sLegacy = "" + MapScenarios.scenarioEditorDetails.CivDefault_Legacy;
        sLegacyRandom = "" + MapScenarios.scenarioEditorDetails.CivDefault_LegacyRandom;
        sManpower = "" + MapScenarios.scenarioEditorDetails.CivDefault_ManpowerPercentage;
        sProvinceDefault_Population = "" + MapScenarios.scenarioEditorDetails.ProvinceDefault_Population;
        sProvinceDefault_Economy = "" + MapScenarios.scenarioEditorDetails.ProvinceDefault_Economy;
        sProvinceDefault_TaxEfficiency = "" + MapScenarios.scenarioEditorDetails.ProvinceDefault_TaxEfficiency;
        sProvinceDefault_Manpower = "" + MapScenarios.scenarioEditorDetails.ProvinceDefault_Manpower;
        menuElements.add(new ButtonGame((String)null, 1, -1, paddingLeft, buttonYPadding, (CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Back"));
            }

            public void actionElement() {
                Game.menuManager.setViewID(View.SCENARIO_ASSIGN);
            }
        });
        menuElements.add(new ButtonGame((String)null, 1, -1, paddingLeft + CFG.PADDING + (CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2 - CFG.PADDING) / 2, buttonYPadding, (CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Save"));
            }

            public void actionElement() {
                if (ScenarioSettings.sTag.length() == 0) {
                    Game.menuManager.addToast_Error(Game.lang.get("Error") + " -> " + Game.lang.get("Tag") + " is null");
                } else {
                    Menu_LoadSaveScenario.goToMenu = View.SCENARIO_PREVIEW;
                    Game.menuManager.setViewID(View.LOAD_SAVE_SCENARIO);
                }

            }
        });
        int buttonY = buttonYPadding + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("ScenarioName") + ": ");
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + ScenarioSettings.sName + (Keyboard.keyboardActionType == KeyboardActionType.SCENARIO_NAME ? Keyboard.getKeyboardVerticalLine() : "");
            }

            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.SCENARIO_NAME, ScenarioSettings.sName);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Date") + ": ");
            }

            public void actionElement() {
                if (Game.menuManager.getVisibleScenario_Calendar()) {
                    Game.menuManager.setVisibleScenario_Calendar(false);
                } else {
                    Game.menuManager.rebuildScenario_Calendar();
                }

            }

            public String getTextToDraw() {
                return super.getTextToDraw() + Game_Calendar.getCurrentDate();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Author") + ": ");
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + ScenarioSettings.sAuthor + (Keyboard.keyboardActionType == KeyboardActionType.SCENARIO_AUTHOR ? Keyboard.getKeyboardVerticalLine() : "");
            }

            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.SCENARIO_AUTHOR, ScenarioSettings.sAuthor);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Scenario") + ": " + Game.lang.get("Tag") + ": ");
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + ScenarioSettings.sTag + (Keyboard.keyboardActionType == KeyboardActionType.SCENARIO_TAG ? Keyboard.getKeyboardVerticalLine() : "");
            }

            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.SCENARIO_TAG, ScenarioSettings.sTag);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("<<", 1, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true) {
            public void actionElement() {
                MapScenarios var10000 = Game.mapScenarios;
                MapScenarios var10003 = Game.mapScenarios;
                MapScenarios.scenarioEditorDetails.HoursPerTurn = Math.max(1, Math.min(24, MapScenarios.scenarioEditorDetails.HoursPerTurn - 1));
            }
        });
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft + CFG.BUTTON_WIDTH, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2 - CFG.BUTTON_WIDTH * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("HoursPerTurn") + ": ");
            }

            public String getTextToDraw() {
                StringBuilder var10000 = (new StringBuilder()).append(super.getTextToDraw());
                MapScenarios var10001 = Game.mapScenarios;
                return var10000.append(MapScenarios.scenarioEditorDetails.HoursPerTurn).toString();
            }
        });
        menuElements.add(new ButtonMain(">>", 1, -1, CFG.LEFT_MENU_WIDTH2 - paddingLeft - CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true) {
            public void actionElement() {
                MapScenarios var10000 = Game.mapScenarios;
                MapScenarios var10003 = Game.mapScenarios;
                MapScenarios.scenarioEditorDetails.HoursPerTurn = Math.max(1, Math.min(24, MapScenarios.scenarioEditorDetails.HoursPerTurn + 1));
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Campaign") + ": ");
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + Game.mapScenarios.scenarioEditor_isCampaign;
            }

            public void actionElement() {
                Game.mapScenarios.scenarioEditor_isCampaign = !Game.mapScenarios.scenarioEditor_isCampaign;
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("UnlockedTechnologies"), Images.technology, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                for(int i = 0; i < Game.getCivsSize(); ++i) {
                    Game.getCiv(i).buildTechTree();
                }

                Game.menuManager.setViewID(View.SCENARIO_TECHNOLOGIES);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("UnlockedTechnologies"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.technology, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Provinces") + ": " + Game.lang.get("Population") + ": ", Images.population, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.SCENARIO_PROVINCE_POPULATION, ScenarioSettings.sProvinceDefault_Population);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG("Province default starting Population", Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.population, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("Value * the province's Growth Rate"));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + ScenarioSettings.sProvinceDefault_Population + (Keyboard.keyboardActionType == KeyboardActionType.SCENARIO_PROVINCE_POPULATION ? Keyboard.getKeyboardVerticalLine() : "");
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Civilizations") + ": " + Game.lang.get("Population"), Images.population, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                Game.menuManager.setViewID(View.SCENARIO_POPULATION);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Population"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.population, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Armies"), Images.manpower, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                for(int i = 0; i < Game.getCivsSize(); ++i) {
                    Game.getCiv(i).buildTechTree();
                }

                Renderer.drawArmyInProvince = true;
                ScenarioDiplomacy.goBackTo = View.SCENARIO_SETTINGS;
                Game.menuManager.setViewID(View.SCENARIO_ARMIES);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Armies"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.manpower, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Gold") + ": " + Game.lang.get("Default") + ": ", Images.gold, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.SCENARIO_GOLD, ScenarioSettings.sGold);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG("Starting Gold", Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + ScenarioSettings.sGold + (Keyboard.keyboardActionType == KeyboardActionType.SCENARIO_GOLD ? Keyboard.getKeyboardVerticalLine() : "");
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Gold") + ": " + Game.lang.get("Random") + ": ", Images.gold, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.SCENARIO_GOLD_RANDOM, ScenarioSettings.sGoldRandom);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG("Starting Gold - Extra Random", Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + ScenarioSettings.sGoldRandom + (Keyboard.keyboardActionType == KeyboardActionType.SCENARIO_GOLD_RANDOM ? Keyboard.getKeyboardVerticalLine() : "");
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Legacy") + ": " + Game.lang.get("Default") + ": ", Images.legacy, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.SCENARIO_LEGACY, ScenarioSettings.sLegacy);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG("Starting Legacy", Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.legacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + ScenarioSettings.sLegacy + (Keyboard.keyboardActionType == KeyboardActionType.SCENARIO_LEGACY ? Keyboard.getKeyboardVerticalLine() : "");
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Legacy") + ": " + Game.lang.get("Random") + ": ", Images.legacy, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.SCENARIO_LEGACY_RANDOM, ScenarioSettings.sLegacyRandom);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG("Starting Legacy - Extra Random", Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.legacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + ScenarioSettings.sLegacyRandom + (Keyboard.keyboardActionType == KeyboardActionType.SCENARIO_LEGACY_RANDOM ? Keyboard.getKeyboardVerticalLine() : "");
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Civilizations") + ": " + Game.lang.get("Manpower") + ": ", Images.manpower, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.SCENARIO_MANPOWER_PERC, ScenarioSettings.sLegacyRandom);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG("Starting Manpower Percentage of 100%", Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.manpower, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + ScenarioSettings.sManpower + "%" + (Keyboard.keyboardActionType == KeyboardActionType.SCENARIO_MANPOWER_PERC ? Keyboard.getKeyboardVerticalLine() : "");
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding * 2;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Provinces") + ": " + Game.lang.get("Economy") + ": ", Game_Calendar.IMG_ECONOMY, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.SCENARIO_PROVINCE_ECONOMY, ScenarioSettings.sProvinceDefault_Economy);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG("Province Starting Economy", Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("Value * the province Base Development"));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + ScenarioSettings.sProvinceDefault_Economy + (Keyboard.keyboardActionType == KeyboardActionType.SCENARIO_PROVINCE_ECONOMY ? Keyboard.getKeyboardVerticalLine() : "");
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Provinces") + ": " + Game.lang.get("TaxEfficiency") + ": ", Images.tax, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.SCENARIO_PROVINCE_TAX_EFFICIENCY, ScenarioSettings.sProvinceDefault_TaxEfficiency);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG("Province Starting Tax Efficiency", Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.tax, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("Value * the province Base Development"));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + ScenarioSettings.sProvinceDefault_TaxEfficiency + (Keyboard.keyboardActionType == KeyboardActionType.SCENARIO_PROVINCE_TAX_EFFICIENCY ? Keyboard.getKeyboardVerticalLine() : "");
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Provinces") + ": " + Game.lang.get("Manpower") + ": ", Images.manpower, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.SCENARIO_PROVINCE_MANPOWER, ScenarioSettings.sProvinceDefault_Manpower);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG("Province Starting Manpower", Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.manpower, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("Value * the province Base Development"));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + ScenarioSettings.sProvinceDefault_Manpower + (Keyboard.keyboardActionType == KeyboardActionType.SCENARIO_PROVINCE_MANPOWER ? Keyboard.getKeyboardVerticalLine() : "");
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding * 2;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Civilizations") + ": " + Game.lang.get("Economy"), Game_Calendar.IMG_ECONOMY, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioEconomy_List.listMode = ListMode.ECONOMY;
                Game.menuManager.setViewID(View.SCENARIO_ECONOMY);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Economy"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Civilizations") + ": " + Game.lang.get("TaxEfficiency"), Images.tax, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioEconomy_List.listMode = ListMode.TAX_EFFICIENCY;
                Game.menuManager.setViewID(View.SCENARIO_ECONOMY);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("TaxEfficiency"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.tax, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Civilizations") + ": " + Game.lang.get("Manpower"), Images.manpower, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioEconomy_List.listMode = ListMode.MANPOWER;
                Game.menuManager.setViewID(View.SCENARIO_ECONOMY);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Manpower"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.manpower, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Civilizations") + ": " + Game.lang.get("Gold"), Images.gold, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioEconomy_List.listMode = ListMode.GOLD;
                Game.menuManager.setViewID(View.SCENARIO_ECONOMY);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Gold"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Civilizations") + ": " + Game.lang.get("Legacy"), Game_Calendar.IMG_ECONOMY, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioEconomy_List.listMode = ListMode.LEGACY;
                Game.menuManager.setViewID(View.SCENARIO_ECONOMY);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Legacy"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.legacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Civilizations") + ": " + Game.lang.get("AIAggressiveness"), Images.ai, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioEconomy_List.listMode = ListMode.AI_AGGRESSIVENESS;
                Game.menuManager.setViewID(View.SCENARIO_ECONOMY);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("AIAggressiveness"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.ai, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Default") + ": ", "" + Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_CHANCE + " + " + Game.lang.get("Extra") + " / " + Game.aiValuesDiplomacy.AI_PREPARE_FOR_WAR_RANDOM_MAX, Images.ai, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Civilizations") + ": " + Game.lang.get("AtomicBombs"), Images.nuke, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioEconomy_List.listMode = ListMode.NUKES;
                Game.menuManager.setViewID(View.SCENARIO_ECONOMY);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("AtomicBombs"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.nuke, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Civilizations") + ": " + Game.lang.get("Government"), Images.government, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                Game.menuManager.setViewID(View.SCENARIO_GOVERNMENT);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Government"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.government, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Provinces") + ": " + Game.lang.get("Buildings"), Images.buildings, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                CFG.brushTool = false;
                CFG.selectMode = true;
                EditorSelectProvinces.selectedProvinces.clear();
                ScenarioDiplomacy.goBackTo = View.SCENARIO_SETTINGS;
                Game.menuManager.setViewID(View.SCENARIO_BUILDINGS);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Buildings"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.buildings, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Provinces") + ": " + Game.lang.get("Cores"), Images.core, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                CFG.brushTool = false;
                CFG.selectMode = true;
                EditorSelectProvinces.selectedProvinces.clear();
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_ScenarioCores();
                Game.menuManager.setViewID(View.SCENARIO_CORES);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("AddCore"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Provinces") + ": " + Game.lang.get("Religion"), Images.religion, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                CFG.brushTool = false;
                CFG.selectMode = true;
                EditorSelectProvinces.selectedProvinces.clear();
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_ScenarioReligion();
                ScenarioDiplomacy.goBackTo = View.SCENARIO_SETTINGS;
                Game.menuManager.setViewID(View.SCENARIO_RELIGION);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Religion"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.religion, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc("Change the religion in the capital province to change the Civilization's religion.", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Diplomacy") + ": " + Game.lang.get("CreateAlliance"), Images.hre, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                Game.menuManager.setViewID(View.SCENARIO_CREATE_ALLIANCE);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CreateAlliance"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.hre, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("HolyRomanEmpire"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Diplomacy") + ": " + Game.lang.get("Relations"), Images.relations, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioRelationsList.activeCivID = 0;
                ScenarioDiplomacy.goBackTo = View.SCENARIO_SETTINGS;
                Game.menuManager.setViewID(View.SCENARIO_RELATIONS);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Relations"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.relations, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Diplomacy") + ": " + Game.lang.get("Alliances"), Images.alliance, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioAlliancesList.activeCivID = 0;
                ScenarioAlliancesList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.SCENARIO_SETTINGS;
                Game.menuManager.setViewID(View.SCENARIO_ALLIANCES);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Alliances"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.alliance, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Diplomacy") + ": " + Game.lang.get("DefensivePacts"), Images.defensivePact, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioDefensiveList.activeCivID = 0;
                ScenarioDefensiveList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.SCENARIO_SETTINGS;
                Game.menuManager.setViewID(View.SCENARIO_DEFENSIVE);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DefensivePacts"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.defensivePact, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Diplomacy") + ": " + Game.lang.get("NonAggressionPacts"), Images.nonAggression, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioNonAggressionList.activeCivID = 0;
                ScenarioNonAggressionList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.SCENARIO_SETTINGS;
                Game.menuManager.setViewID(View.SCENARIO_NON_AGGRESSION);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("NonAggressionPacts"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.nonAggression, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Diplomacy") + ": " + Game.lang.get("GuaranteeIndependence"), Images.guaranteeIndependence, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioGuaranteeList.activeCivID = 0;
                ScenarioGuaranteeList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.SCENARIO_SETTINGS;
                Game.menuManager.setViewID(View.SCENARIO_GUARANTEE);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("GuaranteeIndependence"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.guaranteeIndependence, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Diplomacy") + ": " + Game.lang.get("MilitaryAccess"), Images.militaryAccess, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioMilitaryAccessList.activeCivID = 0;
                ScenarioMilitaryAccessList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.SCENARIO_SETTINGS;
                Game.menuManager.setViewID(View.SCENARIO_MILITARY_ACCESS);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MilitaryAccess"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.militaryAccess, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Diplomacy") + ": " + Game.lang.get("Truces"), Images.truce, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioTrucesList.activeCivID = 0;
                ScenarioTrucesList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.SCENARIO_SETTINGS;
                Game.menuManager.setViewID(View.SCENARIO_TRUCES);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Truces"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.truce, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonStatsBudget(Game.lang.get("Diplomacy") + ": " + Game.lang.get(Game_Ages.getVassals()), Images.vassal, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
            public void actionElement() {
                ScenarioVassalsList.activeCivID = 0;
                ScenarioVassalsList.activeCivID2 = 0;
                ScenarioDiplomacy.goBackTo = View.SCENARIO_SETTINGS;
                Game.menuManager.setViewID(View.SCENARIO_VASSALS);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game_Ages.getVassals()), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        this.initMenu(new MenuTitle("", 1.0F, titleHeight, true, true), menuX, titleHeight + menuY, CFG.LEFT_MENU_WIDTH2, Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY * 2), menuElements, true, false);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight() + CFG.PADDING, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("ScenarioSettings"));
    }
}
