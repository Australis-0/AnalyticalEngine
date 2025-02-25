//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_SiegeStart;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification.Notification_BG;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification.Notification_Type;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.SteamMultiManager;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.script.Invocable;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SiegeManager {
    public static List<Integer> lProvinces = Collections.synchronizedList(new ArrayList());
    public static int iProvincesSize = 0;
    public static Color progressBarBG = new Color(0.23529412F, 0.15686275F, 0.15686275F, 1.0F);
    public static Color progressBar = new Color(0.15686275F, 0.5882353F, 0.84313726F, 1.0F);

    public SiegeManager() {
    }

    public static void buildProvinceUnderSiege_Load() {
        lProvinces.clear();
        iProvincesSize = 0;

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvinceData4(i).isUnderSiege()) {
                lProvinces.add(i);
            }
        }

        iProvincesSize = lProvinces.size();
    }

    public static final void addProvinceSiege(int nProvinceID) {
        for(int i = 0; i < iProvincesSize; ++i) {
            if ((Integer)lProvinces.get(i) == nProvinceID) {
                return;
            }
        }

        lProvinces.add(nProvinceID);
        iProvincesSize = lProvinces.size();
    }

    public static final void removeProvinceSiege(int nProvinceID) {
        for(int i = 0; i < iProvincesSize; ++i) {
            if ((Integer)lProvinces.get(i) == nProvinceID) {
                lProvinces.remove(i);
                iProvincesSize = lProvinces.size();
                Game.getProvinceData4(nProvinceID).setIsUnderSiege_Just(false);

                //ANALYTICALENGINE START
                Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                try {
                    invocable.invokeFunction("parseOnSiege", nProvinceID);
                } catch (ScriptException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                //ANALYTICALENGINE END

                return;
            }
        }

    }

    public static final boolean isProvinceUnderSiege(int nProvinceID) {
        for(int i = 0; i < iProvincesSize; ++i) {
            if ((Integer)lProvinces.get(i) == nProvinceID) {
                return true;
            }
        }

        return false;
    }

    public static final void checkForSiege(int nProvinceID) {
        try {
            if (Game.getProvince(nProvinceID).getSeaProvince()) {
                return;
            }

            int numOfRegiments = 0;
            if (Game.getProvince(nProvinceID).isOccupied()) {
                if (!Game.getProvinceData4(nProvinceID).isUnderSiege()) {
                    for(int j = 0; j < Game.getProvince(nProvinceID).getArmySize(); ++j) {
                        if (!Game.getProvince(nProvinceID).getArmy(j).inBattle && !Game.getProvince(nProvinceID).getArmy(j).inRetreat && !Game.getProvince(nProvinceID).getArmy(j).inMovement && Game.getProvince(nProvinceID).getArmy(j).fMorale >= GameValues.siege.SIEGE_MIN_MORALE) {
                            if (DiplomacyManager.isAtWar(Game.getProvinceData(nProvinceID).getOccupiedByCivID(), Game.getProvince(nProvinceID).getArmy(j).civID)) {
                                numOfRegiments += Game.getProvince(nProvinceID).getArmy(j).iArmyRegimentSize;
                                if (numOfRegiments >= GameValues.siege.SIEGE_REGIMENTS_MIN) {
                                    Game.getProvinceData4(nProvinceID).setIsUnderSiege(nProvinceID, true);
                                    Game.getProvinceData4(nProvinceID).setSiegeProgress(0.0F);
                                    addProvinceSiege(nProvinceID);
                                    AI_SiegeStart.siegeStarted_Attacker(nProvinceID);
                                    AI_SiegeStart.siegeStarted_Defend(nProvinceID);
                                    return;
                                }
                            } else if (Game.getProvince(nProvinceID).getCivID() == Game.getProvince(nProvinceID).getArmy(j).civID) {
                                Game.getProvince(nProvinceID).retakeOccupiedProvince_Peace();
                            }
                        }
                    }
                } else {
                    addProvinceSiege(nProvinceID);
                }
            } else if (!Game.getProvinceData4(nProvinceID).isUnderSiege()) {
                for(int j = 0; j < Game.getProvince(nProvinceID).getArmySize(); ++j) {
                    if (!Game.getProvince(nProvinceID).getArmy(j).inBattle && !Game.getProvince(nProvinceID).getArmy(j).inMovement && !Game.getProvince(nProvinceID).getArmy(j).inRetreat && Game.getProvince(nProvinceID).getArmy(j).fMorale >= GameValues.siege.SIEGE_MIN_MORALE && DiplomacyManager.isAtWar(Game.getProvince(nProvinceID).getCivID(), Game.getProvince(nProvinceID).getArmy(j).civID)) {
                        numOfRegiments += Game.getProvince(nProvinceID).getArmy(j).iArmyRegimentSize;
                        if (numOfRegiments >= GameValues.siege.SIEGE_REGIMENTS_MIN) {
                            Game.getProvinceData4(nProvinceID).setIsUnderSiege(nProvinceID, true);
                            Game.getProvinceData4(nProvinceID).setSiegeProgress(0.0F);
                            addProvinceSiege(nProvinceID);
                            AI_SiegeStart.siegeStarted_Attacker(nProvinceID);
                            AI_SiegeStart.siegeStarted_Defend(nProvinceID);
                            if (Game.getProvince(nProvinceID).getCivID() == Game.player.iCivID) {
                                Game.player.addNotification(new Notification(Notification_Type.SIEGE, Game.getProvince(nProvinceID).getProvinceName() + ": " + Game.lang.get("UnderSiege"), Images.siege, Game_Calendar.TURN_ID, Notification_BG.NEUTRAL_BG, nProvinceID) {
                                    public void onAction() {
                                        Game.mapCoords.centerToProvinceID(this.id);
                                    }
                                });
                            }

                            return;
                        }
                    }
                }
            } else {
                addProvinceSiege(nProvinceID);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static int getSiegeDaysLeft(int iProvinceID) {
        return (int)(Math.ceil((double)((float)Game.getProvince(iProvinceID).getFortDefense() - Game.getProvinceData4(iProvinceID).getSiegeProgress())) / (double)getDailySiegeProgress(iProvinceID));
    }

    public static final float getDailySiegeProgress(int iProvinceID) {
        float fSiegeProgress = 0.0F;
        if (Game.getProvince(iProvinceID).isOccupied()) {
            for(int j = 0; j < Game.getProvince(iProvinceID).getArmySize(); ++j) {
                if (!Game.getProvince(iProvinceID).getArmy(j).inBattle && !Game.getProvince(iProvinceID).getArmy(j).inMovement && DiplomacyManager.isAtWar(Game.getProvinceData(iProvinceID).getOccupiedByCivID(), Game.getProvince(iProvinceID).getArmy(j).civID)) {
                    fSiegeProgress += Game.getProvince(iProvinceID).getArmy(j).getSiegeProgressPerDay() * Math.min(Game.getProvince(iProvinceID).getArmy(j).fMorale, 1.0F);
                }
            }
        } else {
            for(int j = 0; j < Game.getProvince(iProvinceID).getArmySize(); ++j) {
                if (!Game.getProvince(iProvinceID).getArmy(j).inBattle && !Game.getProvince(iProvinceID).getArmy(j).inMovement && DiplomacyManager.isAtWar(Game.getProvince(iProvinceID).getCivID(), Game.getProvince(iProvinceID).getArmy(j).civID)) {
                    fSiegeProgress += Game.getProvince(iProvinceID).getArmy(j).getSiegeProgressPerDay() * Math.min(Game.getProvince(iProvinceID).getArmy(j).fMorale, 1.0F);
                }
            }
        }

        return Math.min(GameValues.siege.SIEGE_MAX_PROGRESS, fSiegeProgress);
    }

    public static boolean isStillUnderSiege(int nProvinceID) {
        try {
            if (Game.getProvince(nProvinceID).isOccupied()) {
                for(int j = 0; j < Game.getProvince(nProvinceID).getArmySize(); ++j) {
                    if (DiplomacyManager.isAtWar(Game.getProvinceData(nProvinceID).getOccupiedByCivID(), Game.getProvince(nProvinceID).getArmy(j).civID)) {
                        return true;
                    }
                }
            } else {
                for(int j = 0; j < Game.getProvince(nProvinceID).getArmySize(); ++j) {
                    if (DiplomacyManager.isAtWar(Game.getProvince(nProvinceID).getCivID(), Game.getProvince(nProvinceID).getArmy(j).civID)) {
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return false;
    }

    public static final void updateSieges() {
        try {
            for(int i = 0; i < iProvincesSize; ++i) {
                int provinceID = (Integer)lProvinces.get(i);
                if (Game.getProvinceData4(provinceID).isUnderSiege() && isStillUnderSiege(provinceID)) {
                    Game.getProvinceData4(provinceID).setSiegeProgress(Math.min(Game.getProvinceData4(provinceID).getSiegeProgress() + getDailySiegeProgress(provinceID), (float)Game.getProvince(provinceID).getFortDefense()));
                    if (Game.getProvinceData4(provinceID).getSiegeProgress() >= (float)Game.getProvince(provinceID).getFortDefense()) {
                        updateSiege_End(provinceID);
                    }
                } else {
                    lProvinces.remove(i);
                    iProvincesSize = lProvinces.size();
                    Game.getProvinceData4(provinceID).setIsUnderSiege_Just(false);
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static void updateSiege_End(int provinceID) {
        if (SteamMultiManager.isHost()) {
            if (Game.getProvince(provinceID).isOccupied()) {
                Game.getProvince(provinceID).retakeOccupiedProvince();
            } else {
                Game.getProvince(provinceID).occupyProvince();
            }

            Game.getProvinceData4(provinceID).setIsUnderSiege_Just(false);
            removeProvinceSiege(provinceID);
        }

    }

    public static void draw(SpriteBatch oSB) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_ARMY_MIN_SCALE) {
            drawSieges(oSB, ProvinceDrawArmy.DRAW_ARMY_ALPHA);
        } else if (Game.mapTouchManager.selectMode) {
            drawSieges(oSB, 1.0F);
        } else if (ProvinceDrawArmy.drawHideAnimation && Game.mapScale.getCurrentScale() >= ProvinceDrawArmy.DRAW_ARMY_MIN_SCALE_ANIMATION) {
            drawSieges(oSB, ProvinceDrawArmy.DRAW_ARMY_ALPHA);
        }

    }

    private static final void drawSieges(SpriteBatch oSB, float fAlpha) {
        try {
            if (Game.FOG_OF_WAR) {
                for(int i = iProvincesSize - 1; i >= 0; --i) {
                    if (Game.getProvince((Integer)lProvinces.get(i)).getDrawProvince() && Game.getProvince((Integer)lProvinces.get(i)).getFogDrawArmy()) {
                        drawSiege(oSB, fAlpha, (Integer)lProvinces.get(i));
                    }
                }
            } else {
                for(int i = iProvincesSize - 1; i >= 0; --i) {
                    if (Game.getProvince((Integer)lProvinces.get(i)).getDrawProvince()) {
                        drawSiege(oSB, fAlpha, (Integer)lProvinces.get(i));
                    }
                }
            }
        } catch (Exception var3) {
        }

    }

    private static final void drawSiege(SpriteBatch oSB, float fAlpha, int iProvinceID) {
        try {
            int iPosX = getArmyPosX(iProvinceID);
            int iPosY = getArmyPosY(iProvinceID);
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, fAlpha));
            int tCenterX = (ImageManager.getImage(Images.progressBarFrame).getWidth() - ImageManager.getImage(Images.progressBarFrameMask).getWidth()) / 2;
            int tCenterY = (ImageManager.getImage(Images.progressBarFrame).getHeight() - ImageManager.getImage(Images.progressBarFrameMask).getHeight()) / 2;
            ImageManager.getImage(Game_Calendar.IMG_FORT_DEFENSE).draw(oSB, iPosX - ImageManager.getImage(Game_Calendar.IMG_FORT_DEFENSE).getWidth() / 2, iPosY - ImageManager.getImage(Game_Calendar.IMG_FORT_DEFENSE).getHeight() - ImageManager.getImage(Images.progressBarFrame).getHeight() / 2);
            iPosX -= ImageManager.getImage(Images.progressBarFrame).getWidth() / 2;
            iPosY -= ImageManager.getImage(Images.progressBarFrame).getHeight();
            oSB.setColor(new Color(progressBarBG.r, progressBarBG.g, progressBarBG.b, fAlpha));
            ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, iPosX + tCenterX, iPosY + tCenterY);
            if (Game.hoveredSiegeID == iProvinceID) {
                oSB.setColor(new Color(Colors.HOVER_GOLD.r, Colors.HOVER_GOLD.g, Colors.HOVER_GOLD.b, fAlpha));
            } else {
                oSB.setColor(new Color(progressBar.r, progressBar.g, progressBar.b, fAlpha));
            }

            ImageManager.getImage(Images.progressBarFrameMask).draw(oSB, iPosX + tCenterX, iPosY + tCenterY, (int)((float)ImageManager.getImage(Images.progressBarFrameMask).getWidth() * Game.getProvince(iProvinceID).getSiegeProgress()), ImageManager.getImage(Images.progressBarFrameMask).getHeight());
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, fAlpha));
            ImageManager.getImage(Images.progressBarFrame).draw(oSB, iPosX, iPosY);
            oSB.setColor(Color.WHITE);
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    private static final void drawProvinceArmyFlag(SpriteBatch oSB, int nPosX, int nPosY, int nCivID, int ImageID, boolean flipX) {
        ImageManager.getImage(ImageID).draw2(oSB, nPosX, nPosY, ImageManager.getImage(Images.armyFlag).getWidth() + 3 + 2, ImageManager.getImage(ImageID).getHeight(), flipX, false);
        Game.getCiv(nCivID).getFlag().draw(oSB, nPosX + 3, nPosY + 2, ImageManager.getImage(Images.armyFlag).getWidth(), ImageManager.getImage(Images.armyFlag).getHeight());
        ImageManager.getImage(Images.armyFlag).draw(oSB, nPosX + 3, nPosY + 2);
    }

    public static final int getArmyPosX(int nProvinceID) {
        return (int)((float)(Game.getProvince(nProvinceID).iCenterShiftX + Game.getProvince(nProvinceID).getTranslateProvincePosX()) * Game.mapScale.getCurrentScale());
    }

    public static final int getArmyPosY(int nProvinceID) {
        return (int)((float)(Game.getProvince(nProvinceID).iCenterShiftY + Game.mapCoords.getPosY()) * Game.mapScale.getCurrentScale() - 4.0F - (float)ImageManager.getImage(Images.progressBarFrame).getHeight());
    }

    public static final int getWidth() {
        return ImageManager.getImage(Images.progressBarFrameMask).getWidth();
    }

    public static final int getHeight() {
        return ImageManager.getImage(Game_Calendar.IMG_FORT_DEFENSE).getHeight() + ImageManager.getImage(Images.progressBarFrame).getHeight() / 2;
    }

    public static void clearData() {
        try {
            for(int i = lProvinces.size() - 1; i >= 0; --i) {
                Game.getProvinceData4((Integer)lProvinces.get(i)).setIsUnderSiege_Just(false);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        lProvinces.clear();
        iProvincesSize = 0;
    }
}
