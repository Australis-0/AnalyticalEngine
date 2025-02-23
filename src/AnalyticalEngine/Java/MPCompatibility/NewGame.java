//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menus.NewGame;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.jakowski.AI.Advantages.AI_Advantages;
import aoc.kingdoms.lukasz.jakowski.AI.Colonization.AI_Colonization;
import aoc.kingdoms.lukasz.jakowski.GameThreads.GameThread_Events;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessage_ChooseRivals;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Civilization.M_AdvantageUnlock;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.M_Players;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.M_StartGame;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.SteamMultiManager;
import aoc.kingdoms.lukasz.map.LuckyCivsManager;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonPlay;
import aoc.kingdoms.lukasz.menu_element.button.ButtonTopDate;
import aoc.kingdoms.lukasz.menu_element.button.ButtonTopOutliner;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menus.LoadSave.Menu_LoadScenario;
import aoc.kingdoms.lukasz.menus.MainMenu;
import aoc.kingdoms.lukasz.menus.Multi.MultiLobbyCreate;
import aoc.kingdoms.lukasz.menusInGame.InGame;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Government;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class NewGame extends Menu {
    public static final int ANIMATION_TIME = 500;
    public static long lTime = 0L;
    public static boolean settingsMenu = false;

    public NewGame() {
        List<MenuElement> menuElements = new ArrayList();
        menuElements.add(new ButtonPlay(Game.lang.get("PLAY"), 1, -1, CFG.GAME_WIDTH - ImageManager.getImage(Images.buttonPlay).getWidth(), CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight(), false) {
            public void actionElement() {
                //ANALYTICALENGINE START
                //1. Create MP Game
                MainMenu.actionMultiplayer();

                //2. Quickly Create a New Lobby
                if (Game_Calendar.TURN_ID == 1 && !Game.reloadScenario && !MainMenu.canContinue) {
                    Game.menuManager.setViewIDWithoutAnimation(View.MULTIPLAYER_CREATE_LOBBY);
                } else {
                    Game.reloadScenario = false;
                    Menu_LoadScenario.editorMode = false;
                    Menu_LoadScenario.goToMenu = View.MULTIPLAYER_CREATE_LOBBY;
                    Game.menuManager.setViewIDWithoutAnimation(View.LOAD_SCENARIO);
                }

                //3. Launch Lobby
                SteamMultiManager.createLobby();
                Game.menuManager.setViewIDWithoutAnimation(View.MULTIPLAYER_LOBBY);

                //4. Make sure Lobby is private and can only have 1 player
                MultiLobbyCreate.players = 1;
                MultiLobbyCreate.visibilityType = false;

                //5. Launch Game
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                scheduler.schedule(() -> {
                    SteamMultiManager.updateIsHost();
                    M_StartGame.startGame(SteamMultiManager.lobbyData.lobbyID);
                }, 500, TimeUnit.MILLISECONDS);
                //ANALYTICALENGINE END

                //VANILLA - DEPRECATED CODE - START
                /*if (Game.menuManager.getColorPicker().getVisible()) {
                    Game.menuManager.getColorPicker().hideColorPicker();
                }

                NewGame.clearPlayerData();
                GameThread_Events.THREAD_TURN_ID = Game_Calendar.TURN_ID;
                if (Game.SPECTATOR_MODE) {
                    Game.player.iCivID = 0;
                }

                Game.player.playerStats.initStartingData();
                Game.stats.loadStats(Game.getCiv(Game.player.iCivID).getCivTag(), true);
                NewGame.initNewGame();
                Game.player.addMessage(new PMessage_ChooseRivals(Game.player.iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
                Game.menuManager.setViewID(View.IN_GAME);
                Game.menuManager.rebuildInGame_Messages();
                Game.menuManager.rebuildInGame_Wars();
                Game.menuManager.setOrderOfMenu_InGame();
                if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEFAULT) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                }

                ProvinceDraw.updateDrawExtraDetails();*/
                //VANILLA - DEPRECATED CODE - END
            }

            public int getSFX() {
                return SoundsManager.SOUND_PLAY_NEW_GAME;
            }
        });
        menuElements.add(new ButtonPlay(Game.lang.get("Back"), 1, -1, 0, CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight(), true) {
            public void actionElement() {
                if (Game.menuManager.getColorPicker().getVisible()) {
                    Game.menuManager.getColorPicker().hideColorPicker();
                }

                Game.setActiveProvinceID(-1);
                ProvinceBorderManager.clearProvinceBorder();
                Game.menuManager.setViewID(View.MAINMENU);
            }
        });
        menuElements.add(new ButtonTopOutliner(CFG.BUTTON_WIDTH + InGame.topRightPadding, 0, CFG.BUTTON_WIDTH, InGame.topStatsHeight, Images.encyclopedia) {
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }

            public void actionElement() {
                if (!NewGame.settingsMenu && Game.menuManager.getVisible_NewGame_Settings()) {
                    Game.menuManager.setVisible_NewGame_Settings(false);
                } else {
                    NewGame.settingsMenu = false;
                    Game.menuManager.rebuild_NewGame_Encyclopedia();
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Encyclopedia"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.encyclopedia, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }

            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                oSB.setColor(this.getColor(isActive));
                ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 + InGame.outlinerExtraClassic - ImageManager.getImage(this.imageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.imageID).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }
        });
        menuElements.add(new ButtonTopOutliner(0, 0, CFG.BUTTON_WIDTH, InGame.topStatsHeight, Images.settings) {
            public int getSFX() {
                return SoundsManager.SOUND_CLICK_TOP;
            }

            public void actionElement() {
                if (NewGame.settingsMenu && Game.menuManager.getVisible_NewGame_Settings()) {
                    Game.menuManager.setVisible_NewGame_Settings(false);
                } else {
                    NewGame.settingsMenu = true;
                    Game.menuManager.rebuild_NewGame_Settings();
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Settings"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.settings, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }

            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                oSB.setColor(this.getColor(isActive));
                ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + this.getWidth() / 2 + InGame.outlinerExtraClassic - ImageManager.getImage(this.imageID).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - ImageManager.getImage(this.imageID).getHeight() / 2 + iTranslateY);
                oSB.setColor(Color.WHITE);
            }
        });
        lTime = CFG.currentTimeMillis;
        this.initMenu((MenuTitle)null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.BUTTON_HEIGHT / 2);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.topStatsClassic).draw2(oSB, iTranslateX, iTranslateY, this.getMenuElement(this.getMenuElementsSize() - 2).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 2).getWidth() + InGame.topStatsPadding, ImageManager.getImage(Images.topStatsClassic).getHeight(), true, false);
        ImageManager.getImage(Images.topStatsClassic).draw2(oSB, iTranslateX, iTranslateY, this.getMenuElement(this.getMenuElementsSize() - 1).getPosX() + this.getMenuElement(this.getMenuElementsSize() - 1).getWidth() + InGame.topStatsPadding, ImageManager.getImage(Images.topStatsClassic).getHeight(), true, false);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public static final void setRandomCiv() {
        List<Integer> tCivs = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                tCivs.add(i);
            }
        }

        if (tCivs.size() > 0) {
            Game.player.iCivID = (Integer)tCivs.get(Game.oR.nextInt(tCivs.size()));

            try {
                if (Game.getCiv(Game.player.iCivID).getCapitalProvinceID() >= 0) {
                    Game.mapCoords.centerToProvinceID(Game.getCiv(Game.player.iCivID).getCapitalProvinceID());
                    ProvinceBorderManager.updateDrawProvinceBorder_SelectCiv_ByProvinceID(Game.getCiv(Game.player.iCivID).getCapitalProvinceID());
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

    }

    public static final void initNewGame() { //[WIP] - Use this as reference for switchTag()
        Game.setActiveProvinceID(-1);
        Game.gameThread.play = false;
        ProvinceBorderManager.clearProvinceBorder();
        Game.initDifficulty();
        Game.player.currSituation.buildPlayerLegaciesLVL();
        Game.player.currSituation.updateCurrentSituation();
        ProvinceDrawArmy.updateArmyImgID();
        Game.menuManager.rebuildInGame();
        hideMenus();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateArmyRegimentSize();
        }

        Game.player.loadFormableCivs();
        InGame_Court_Government.reloadFlags = true;
        if (Game.SPECTATOR_MODE) {
            Game.FOG_OF_WAR = false;
        }

        AI_Colonization.initData();
        Game.player.fog.buildFogOfWar(Game.player.iCivID);
        InGame_Court_Government.reloadFlags = true;
        Game.mapCoords.centerToProvinceID(Game.getCiv(Game.player.iCivID).getCapitalProvinceID());
        Game.soundsManager.updateSoundsRandomTime();
        if (SteamMultiManager.isHost()) {
            Game.addSimpleTask(new Game.SimpleTask("takeAdvantages_AI") {
                public void update() {
                    NewGame.takeAdvantages_AI();
                }
            });
        }

        if (GameValues.fog.HIDE_ARMIES) {
            updateArmiesText();
        }

    }

    public static final void updateArmiesText() {
        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            for(int j = 0; j < Game.getProvince(i).getArmySize(); ++j) {
                Game.getProvince(i).getArmy(j).updateArmyWidth_Just(false);
            }
        }

    }

    public static final void takeAdvantages_AI() {
        if (SteamMultiManager.isHost()) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (!M_Players.isPlayer(i)) {
                    AI_Advantages.takeAdvantages(i);
                }
            }

            M_AdvantageUnlock.serializeCommand_All();
        }

    }

    public static final void clearPlayerData() {
        Game.player.civilizationGeneralsPool.clearData();
        Game.player.civAdvisorsPool_Administration.clearData();
        Game.player.civAdvisorsPool_Economy.clearData();
        Game.player.civAdvisorsPool_Technology.clearData();
        Game.player.civAdvisorsPool_Military.clearData();
        Game.player.clearEvents();
        Game.player.playerData.invasion.clearInvasions();
        Game.player.clearMessages();
        Game.player.clearNotifications();
        Game.player.clearBattleReport();
        Game.player.playerData.espionage.clearEspionageMissions();
        Game.player.clearPinnedArmy();
        Game.player.clearFormableCivs();
        Game.player.playerData.techQueue.clearTechQueue();
        Game.player.allowAIMove = false;
    }

    public static final void hideMenus() {
        try {
            if (Game.menuManager.getVisibleInGame_Console()) {
                Game.menuManager.setVisibleInGame_Console(false);
            }

            if (Game.menuManager.getVisibleInGame_SaveGame()) {
                Game.menuManager.setVisibleInGame_SaveGame(false);
            }

            if (Game.menuManager.getVisibleInGame_Escape()) {
                Game.menuManager.setVisibleInGame_Escape(false);
            }

            if (Game.menuManager.getVisibleInGame_PopUp()) {
                Game.menuManager.setVisibleInGame_PopUp(false);
            }

            if (Game.menuManager.getVisibleInGame_Right()) {
                Game.menuManager.rebuildInGame_Right();
            }

            if (Game.menuManager.getVisibleInGame_Notifications()) {
                Game.menuManager.rebuildInGame_Notifications();
            }
        } catch (Exception var4) {
        }

        try {
            ButtonTopDate.updateMaxWidth();
        } catch (Exception var3) {
        }

        try {
            if (Game.menuManager.getVisibleInGame_Civ()) {
                Game.menuManager.setVisibleInGame_Civ(false);
            }
        } catch (Exception var2) {
        }

        try {
            if (Game.menuManager.getVisibleInGame_Court()) {
                Game.menuManager.setVisibleInGame_Court(false);
            }
        } catch (Exception var1) {
        }

    }

    public static void play() {
        Game.FOG_OF_WAR = SteamMultiManager.lobbyData.FOG_OF_WAR;
        Game.difficultyID = SteamMultiManager.lobbyData.difficulty;
        if (Game.menuManager.getColorPicker().getVisible()) {
            Game.menuManager.getColorPicker().hideColorPicker();
        }

        clearPlayerData();
        GameThread_Events.THREAD_TURN_ID = Game_Calendar.TURN_ID;
        if (Game.SPECTATOR_MODE) {
            Game.player.iCivID = 0;
        }

        Game.player.playerStats.initStartingData();
        Game.stats.loadStats(Game.getCiv(Game.player.iCivID).getCivTag(), true);
        initNewGame();
        Game.player.addMessage(new PMessage_ChooseRivals(Game.player.iCivID, Game_Calendar.TURN_ID + GameValues.diplomacy.DIPLOMACY_MESSAGE_DAYS));
        Game.menuManager.setViewID(View.IN_GAME);
        Game.menuManager.rebuildInGame_Messages();
        Game.menuManager.rebuildInGame_Wars();
        Game.menuManager.setOrderOfMenu_InGame();
        if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEFAULT) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }

        ProvinceDraw.updateDrawExtraDetails();
    }
}
