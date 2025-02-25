//Declare imports
package AnalyticalEngine;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadSavedGameManager;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveGameManager;
import aoc.kingdoms.lukasz.map.map.MapScenarios;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.*;
import aoc.kingdoms.lukasz.menu_element.button.AnalyticalEngine_Button_LoadGame_MainMenu;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Desc;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Static;
import aoc.kingdoms.lukasz.menus.Dialog;
import aoc.kingdoms.lukasz.menus.InitGame;
import aoc.kingdoms.lukasz.menus.MainMenu;
import aoc.kingdoms.lukasz.menus.NewGame.Scenarios.Scenarios;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

//Declare class
public class AnalyticalEngine_MainMenu extends Menu {
    //Declare local instance variables
    public static Color sparksColors = new Color(1.0F, 1.0F, 1.0F, 0.25F);
    private int iXPos = 0;
    private int iYPos = 0;
    private int iWidth = 480;
    private int iHeight = 480;
    public static boolean canContinue = false;
    public static Image flag = null;
    public static SaveGameManager.SaveDetails savedGame = null;
    public static String savedGameKey = null;
    public static float bgAlpha = 0.0F;
    public static long bgTIME;
    public static long bgTIME_CHANGE;

    //Declare AnalyticalEngine_MainMenu constructor
    public AnalyticalEngine_MainMenu () {
        List<MenuElement> menuElements = new ArrayList();
        int paddingTopBot = CFG.PADDING * 2 + CFG.PADDING / 2;
        int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2 + CFG.PADDING / 2;
        this.iXPos = (int)((float)CFG.GAME_WIDTH / (10.0F * CFG.GUI_SCALE));
        this.iWidth = (int)Math.max((float)CFG.LEFT_MENU_WIDTH, (float)Math.min(this.iWidth, CFG.GAME_WIDTH / 4) * CFG.GUI_SCALE);
        this.iHeight = paddingTopBot * 2 + paddingTopBot / 2 + (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) * 6;
        this.iYPos = (int)(0.5F * (float)(CFG.GAME_HEIGHT - this.iHeight - ImageManager.getImage(Images.mainTitle).getHeight()));
        if (this.iXPos + this.iWidth > CFG.GAME_WIDTH) {
            this.iXPos = CFG.PADDING * 2;
        }

        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), GameValues.text.VERSION);
        Game.versionWidth = (int) Renderer.glyphLayout.width;
        menuElements.add(new ButtonMainTitle( "", 0, -1, this.iXPos, this.iYPos, this.iWidth, true) {
            public void buildElementHover () {
                this.menuElementHover = getHoverAbout();
                /*if (CFG.isDesktop()) {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("CTRL + " + Game.lang.get("ChooseAProvince"), "", Images.council, CFG.FONT_BOLD_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                } else {
                    this.menuElementHover = null;
                }*/
            }
        });
        int buttonY = this.iYPos + ImageManager.getImage(Images.mainTitle).getHeight() + paddingTopBot;

        try {
            if (canContinue) {
                menuElements.add(new AnalyticalEngine_Button_LoadGame_MainMenu(Game.lang.get("Continue") + ": " + Game.getCiv(Game.player.iCivID).getCivName(), "" + Game_Calendar.currentDay + " " + Game_Calendar.getMonthName(Game_Calendar.currentMonth) + " " + Game.gameAges.getYear(Game_Calendar.currentYear), this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2));
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
            } else {
                FileHandle file;
                if (FileManager.IS_MAC) {
                    file = Gdx.files.external("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                } else if (CFG.readLocalFiles()) {
                    file = Gdx.files.local("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                } else {
                    file = Gdx.files.internal("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                }

                if (file.exists()) {
                    String[] tempTags = file.readString().split(";");
                    List<SaveGameManager.SaveDetails> tempSaveDetails = new ArrayList();
                    List<String> tempSaveKey = new ArrayList();

                    for(int i = 0; i < tempTags.length; ++i) {
                        SaveGameManager.SaveDetails readSD = LoadSavedGameManager.loadSave_Details(tempTags[i]);
                        if (readSD != null) {
                            tempSaveDetails.add(readSD);
                            tempSaveKey.add(tempTags[i]);
                        }
                    }

                    int bestID = 0;

                    for(int i = tempSaveDetails.size() - 1; i > 0; --i) {
                        if (((SaveGameManager.SaveDetails)tempSaveDetails.get(i)).time > ((SaveGameManager.SaveDetails)tempSaveDetails.get(bestID)).time) {
                            bestID = i;
                        }
                    }

                    savedGame = (SaveGameManager.SaveDetails)tempSaveDetails.get(bestID);
                    savedGameKey = (String)tempSaveKey.get(bestID);

                    loadFlag(savedGame.sCivTag);
                    menuElements.add(new AnalyticalEngine_Button_LoadGame_MainMenu(Game.lang.get("Continue") + ": " + Game.lang.getCiv(savedGame.sCivTag), "" + savedGame.iDay + " " + Game_Calendar.getMonthName(savedGame.iMonth) + " " + Game.gameAges.getYear(savedGame.iYear), this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2) {
                        public void actionElement () {
                            LoadSavedGameManager.key = savedGameKey;
                            Game.menuManager.setViewID(View.LOAD_SAVED_GAME);
                        }
                    });
                    buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
                } else {
                    menuElements.add(new ButtonGame2(Game.lang.get("Continue"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2, false));
                    buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }


        menuElements.add(new ButtonGame2(Game.lang.get("NewGame"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2, true) {
            public void actionElement () {
                Game.menuManager.setViewID(View.SCENARIOS);
                Game.menuManager.setOrderOfMenu_Scenarios();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        int statsW = CFG.BUTTON_WIDTH;
        if (FileManager.loadFile("game/Multiplayer.txt").exists()) {
            menuElements.add(new ButtonGame2(Game.lang.get("Campaign"), 1, -1, this.iXPos + paddingLeft, buttonY, (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
                public void actionElement () {
                    Game.menuManager.setViewID(View.SCENARIOS_CAMPAIGN);
                    Game.menuManager.setOrderOfMenu_Scenarios();
                }
            });
            menuElements.add(new ButtonGame2(Game.lang.get("Multiplayer"), 1, -1, this.iXPos + paddingLeft + CFG.PADDING + (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2, buttonY, (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
                public void actionElement () {
                    Game.menuManager.setViewID(View.MULTIPLAYER);
                }
            });
        } else {
            menuElements.add(new ButtonGame2(Game.lang.get("Campaign"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2, true) {
                public void actionElement () {
                    Game.menuManager.setViewID(View.SCENARIOS_CAMPAIGN);
                    Game.menuManager.setOrderOfMenu_Scenarios();
                }
            });
        }

        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        menuElements.add(new ButtonGame2(Game.lang.get("LoadGame"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2, true) {
            public void actionElement () {
                Game.menuManager.setViewID(View.LOAD_GAMES_LIST);
            }
        });

        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2 + paddingTopBot / 2;
        menuElements.add(new ButtonGame2(Game.lang.get("Editor"), 1, -1, this.iXPos + paddingLeft, buttonY, (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            public void actionElement () {
                Game.menuManager.setViewID(View.EDITOR);
            }
        });
        menuElements.add(new ButtonGame2(Game.lang.get("Settings"), 1, -1, this.iXPos + paddingLeft + (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2 + CFG.PADDING, buttonY, (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            public void actionElement () {
                Game.menuManager.setViewID(View.SETTINGS);
            }
        });

        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        menuElements.add(new ButtonGame2(Game.lang.get("ExitGame"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2 - CFG.PADDING - statsW, true) {
            public void actionElement () {
                Dialog.setDialogType(Dialog.DialogType.EXIT_GAME);
            }
        });
        menuElements.add(new ButtonGame_Image((String) null, 1, -1, this.iXPos + paddingLeft + this.iWidth - paddingLeft * 2 - statsW, buttonY, statsW, true, Images.development) {
            public void actionElement () {
                Game.menuManager.setViewID(View.MAINMENU_STATS);
            }
        });
        ((MenuElement) menuElements.get(menuElements.size() - 1)).setHeight(((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2);

        int var10000 = buttonY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Text_Static(GameValues.text.VERSION, CFG.FONT_REGULAR_SMALL, -1, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT3, 0, CFG.BUTTON_HEIGHT3, CFG.BUTTON_HEIGHT3) {
            public void actionElement () {
                MenuManager.sparksAnimation.updateAnimation();
                InitGame.loadBackground();
            }
        });
        int buttonsY = CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 4;
        menuElements.add(new Button_MainMenuIcon(Images.yt, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, buttonsY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT) {
            public void actionElement () {
                Dialog.GO_TO_LINK = "https://www.youtube.com/@Confoederatio";
                Dialog.setDialogType(Dialog.DialogType.GO_TO_LINK);
            }
        });
        buttonsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        menuElements.add(new Button_MainMenuIcon(Images.android, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, buttonsY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT));
        buttonsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        menuElements.add(new Button_MainMenuIcon(Images.app, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, buttonsY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT));
        buttonsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        menuElements.add(new Button_MainMenuIcon(Images.pc, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, buttonsY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT));
        var10000 = buttonsY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
        menuElements.add(new Text_Static("Lukasz Jakowski / Confoederatio - Aust Kätzchen, Vis Tacitus", CFG.PADDING * 3, CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 3, CFG.FONT_REGULAR_SMALL));
        this.initMenu((MenuTitle)null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
        bgTIME = System.currentTimeMillis();
        bgTIME_CHANGE = System.currentTimeMillis();
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        if (bgAlpha < 1.0F) {
            oSB.setColor(0.0F, 0.0F, 0.0F, 1.0F);
            Images.pix.draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
            bgAlpha = Math.min(1.0F, (float)(CFG.currentTimeMillis - bgTIME) / (float)GameValues.text.MAIN_MENU_BG_ANIMATION_TIME);
        }

        oSB.setColor(new Color(0.050980393F, 0.08627451F, 0.13333334F, 1.0F * bgAlpha));
        InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, bgAlpha));
        oSB.setShader(Renderer.shaderAlpha);
        InitGame.background.getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.gradientHorizontal2).draw(oSB, this.getPosX() + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, this.getPosY() + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        oSB.setColor(sparksColors);
        MenuManager.sparksAnimation.draw2(oSB, iTranslateX, CFG.GAME_HEIGHT - Images.sparkHeight + iTranslateY, CFG.GAME_WIDTH, Images.sparkHeight);
        oSB.setColor(Color.WHITE);
        Renderer.drawBoxCorner(oSB, iTranslateX + this.iXPos, iTranslateY + this.iYPos, this.iWidth, this.iHeight + ImageManager.getImage(Images.mainTitle).getHeight());
        Renderer.drawBox_EDGE_TOP_LR(oSB, Images.mainBox, this.iXPos + iTranslateX, this.iYPos + ImageManager.getImage(Images.mainTitle).getHeight() + iTranslateY, this.iWidth, this.iHeight, true);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.3F));
        Images.gradientXY.draw(oSB, this.iXPos + iTranslateX, this.iYPos + ImageManager.getImage(Images.mainTitle).getHeight() + iTranslateY, this.iWidth, this.iHeight, false, true);
        oSB.setColor(Color.WHITE);
        if ((CFG.isDesktop() && GameValues.text.MAIN_MENU_BG_ENABLE_AUTO_BG_CHANGE || !CFG.isDesktop() && GameValues.text.MAIN_MENU_BG_ENABLE_AUTO_BG_CHANGE_MOBILE) && CFG.currentTimeMillis > bgTIME_CHANGE + (long)GameValues.text.MAIN_MENU_BG_CHANGE_BG_EVERY_X_MS) {
            bgTIME_CHANGE = CFG.currentTimeMillis;
            Game.addSimpleTask(new Game.SimpleTask("loadBackground"));
        }

        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public static void loadFlag(String sCivTag) {
        disposeData();
        if (FileManager.loadFile("gfx/flagsXH/" + sCivTag + ".png").exists()) {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + sCivTag + ".png")), TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsXH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png").exists()) {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png")), TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsH/" + sCivTag + ".png").exists()) {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + sCivTag + ".png")), TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png").exists()) {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png")), TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flags/" + sCivTag + ".png").exists()) {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + sCivTag + ".png")), TextureFilter.Nearest);
        } else if (FileManager.loadFile("gfx/flags/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png").exists()) {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png")), TextureFilter.Nearest);
        } else {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/ran.png")), TextureFilter.Nearest);
        }

    }

    public void dispose() {
        disposeData();
    }

    public static void disposeData() {
        if (flag != null) {
            flag.getTexture().dispose();
            flag = null;
        }

    }

    public static MenuElement_Hover getHoverAbout_Short() {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center("Programmer and Designer", CFG.FONT_BOLD, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Lukasz Jakowski", "", Images.world, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("One Man Army!", "", Game_Calendar.IMG_MANPOWER, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }

    public static MenuElement_Hover getHoverAbout() {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center("Programmer and Designer", CFG.FONT_BOLD, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Lukasz Jakowski", "", Images.world, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("One Man Army!", "", Game_Calendar.IMG_MANPOWER, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        boolean lineAdded = false;
        if (GameValues.text.MAIN_MENU_LOGO_HOVER_TEXT != null) {
            for(int i = 0; i < GameValues.text.MAIN_MENU_LOGO_HOVER_TEXT.length; ++i) {
                if (GameValues.text.MAIN_MENU_LOGO_HOVER_TEXT[i] != null && !lineAdded) {
                    lineAdded = true;
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                nData.add(new MenuElement_HoverElement_Type_Text_Desc(GameValues.text.MAIN_MENU_LOGO_HOVER_TEXT[i], CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }

        return new MenuElement_Hover(nElements);
    }
}