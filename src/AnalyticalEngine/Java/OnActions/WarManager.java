//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map.war;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.AI.AI_PeaceTreaty;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.moveUnits.MoveUnits;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.menusInGame.InGame_War;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.textures.Images;

import javax.script.Invocable;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class WarManager {
    public static ConcurrentHashMap<String, War> lWars = new ConcurrentHashMap();
    public static int iWarsSize = 0;
    public static int UPDATE_WARS_CHECK_PROVINCES = 0;

    public WarManager() {
    }

    public static final String addWar(int nAggressor, int nDefender, boolean conquerVassal, boolean isCoalition, String warKey) {
        if (nAggressor != 0 && nDefender != 0) {
            if (Game.getCiv(nAggressor).getNumOfProvinces() != 0 && Game.getCiv(nDefender).getNumOfProvinces() != 0) {
                try {
                    for(War nWar : lWars.values()) {
                        if (nWar.areInThisWar(nAggressor, nDefender)) {
                            return null;
                        }
                    }
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }

                String tKey = warKey == null ? CFG.extraRandomTag() : warKey;
                lWars.put(tKey, new War(nAggressor, nDefender, tKey, conquerVassal, isCoalition));
                iWarsSize = lWars.size();
                Game.getCiv(nAggressor).diplomacy.addWar(tKey, nAggressor);
                Game.getCiv(nDefender).diplomacy.addWar(tKey, nDefender);

                //ANALYTICALENGINE START
                Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                try {
                    invocable.invokeFunction("parseOnWar", lWars.get(tKey));
                } catch (ScriptException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                //ANALYTICALENGINE END

                return tKey;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getWarKey(int iCivA, int iCivB) {
        try {
            for(War nWar : lWars.values()) {
                if (nWar.areInThisWar(iCivA, iCivB)) {
                    return nWar.key;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return null;
    }

    public static final void updateWars_Peace() {
        try {
            for(War nWar : lWars.values()) {
                if (Math.abs(nWar.warScore) >= GameValues.peace.WAR_MAKE_DEMANDS_MIN_WAR_SCORE) {
                    int tCivID = ((WarCivilization)nWar.lAggressors.get(0)).iCivID;
                    int tCivID2 = ((WarCivilization)nWar.lDefenders.get(0)).iCivID;
                    if (nWar.warScore > 0.0F) {
                        AI_PeaceTreaty.peaceTreaty(nWar.key, tCivID, tCivID2);
                    } else {
                        AI_PeaceTreaty.peaceTreaty(nWar.key, tCivID2, tCivID);
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static final void updateWars_WhitePeace() {
        try {
            List<String> toRemove = new ArrayList();

            for(War nWar : lWars.values()) {
                try {
                    if (nWar.lastFight_TurnID + GameValues.war.WAR_AUTO_WHITE_PEACE_IF_NOTHING_HAPPENS_IN_WAR_DAYS < Game_Calendar.TURN_ID && Math.abs(nWar.warScore) < GameValues.war.WAR_AUTO_WHITE_PEACE_IF_WARSCORE_BELOW || nWar.iWarTurnID + GameValues.war.WAR_AUTO_WHITE_PEACE_AFTER_X_DAYS_OF_WAR < Game_Calendar.TURN_ID && Math.abs(nWar.warScore) < GameValues.war.WAR_AUTO_WHITE_PEACE_AFTER_X_DAYS_OF_WAR_IF_WARSCORE_BELOW || Game.getCiv(((WarCivilization)nWar.lAggressors.get(0)).iCivID).getNumOfProvinces() <= 0 || Game.getCiv(((WarCivilization)nWar.lDefenders.get(0)).iCivID).getNumOfProvinces() <= 0) {
                        int tCivID = ((WarCivilization)nWar.lAggressors.get(0)).iCivID;
                        int tCivID2 = ((WarCivilization)nWar.lDefenders.get(0)).iCivID;
                        if (DiplomacyManager.whitePeace(nWar.key) && (tCivID == Game.player.iCivID || tCivID2 == Game.player.iCivID)) {
                            InGame_Info.iCivID = tCivID;
                            InGame_Info.iCivID2 = tCivID2;
                            Game.menuManager.rebuildInGame_Info(Game.lang.get("WhitePeace"), Game_Calendar.getCurrentDate());
                            InGame_Info.imgID = Images.infoDiplomacy;
                            Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_Wars") {
                                public void update() {
                                    Game.menuManager.rebuildInGame_Wars();
                                }
                            });
                            if (Game.menuManager.getVisibleInGame_War() && InGame_War.key.equals(nWar.key)) {
                                Game.menuManager.setVisibleInGame_War(false);
                            }
                        }

                        toRemove.add(nWar.key);
                    }
                } catch (Exception var5) {
                }
            }

            for(int i = toRemove.size() - 1; i >= 0; --i) {
                lWars.remove(toRemove.get(i));
            }

            toRemove.clear();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static final void updateWars_TickingWarScore() {
        try {
            if (UPDATE_WARS_CHECK_PROVINCES++ > GameValues.gameUpdate.GAME_UPDATE_WAR_ALL_PROVINCES_OCCUPIED_MONTHS) {
                UPDATE_WARS_CHECK_PROVINCES = 0;

                for(War nWar : lWars.values()) {
                    nWar.updateWars_AllProvincesOccupied();
                    nWar.updateWars_TickingWarScore();
                }
            } else {
                for(War nWar : lWars.values()) {
                    nWar.updateWars_TickingWarScore();
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static final int retreatToProvinceID(int nCivID, int fromProvinceID) {
        if (fromProvinceID < 0) {
            return -1;
        } else {
            List<Integer> was = new ArrayList();
            was.add(fromProvinceID);

            for(int i = 0; i < Game.getProvincesSize(); ++i) {
                Game.getProvince(i).wasRetreat = false;
            }

            Game.getProvince(fromProvinceID).wasRetreat = true;
            List<Integer> in = new ArrayList();
            List<List<Integer>> inPath = new ArrayList();

            for(int i = 0; i < Game.getProvince(fromProvinceID).getNeighboringProvincesSize(); ++i) {
                if (MoveUnits.canBeUsedInPath(nCivID, Game.getProvince(fromProvinceID).getNeighboringProvinces(i), true, fromProvinceID)) {
                    in.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
                    List<Integer> tP = new ArrayList();
                    tP.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
                    inPath.add(tP);
                    was.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID());
                    Game.getProvince(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringProvinces(i)).getProvinceID()).wasRetreat = true;
                }
            }

            if (!Game.getProvince(fromProvinceID).getSeaProvince()) {
                for(int i = 0; i < Game.getProvince(fromProvinceID).getNeighboringSeaProvincesSize(); ++i) {
                    in.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(i)).getProvinceID());
                    List<Integer> tP = new ArrayList();
                    tP.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(i)).getProvinceID());
                    inPath.add(tP);
                    was.add(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(i)).getProvinceID());
                    Game.getProvince(Game.getProvince(Game.getProvince(fromProvinceID).getNeighboringSeaProvinces(i)).getProvinceID()).wasRetreat = true;
                }
            }

            return buildPath(nCivID, was, in, inPath, fromProvinceID, nCivID);
        }
    }

    protected static int buildPath(int nCivID, List<Integer> was, List<Integer> in, List<List<Integer>> inPath, int from, int lookingForCivID) {
        List<Integer> nIN = new ArrayList();
        List<List<Integer>> nINPath = new ArrayList();

        for(int i = 0; i < in.size(); ++i) {
            Province province = Game.getProvince((Integer)in.get(i));
            if ((province.getCivID() == lookingForCivID || Game.getCiv(province.getCivID()).getPuppetOfCivID() == lookingForCivID || province.getCivID() == Game.getCiv(lookingForCivID).getPuppetOfCivID()) && !province.isOccupied()) {
                clearWas(was);
                return (Integer)in.get(i);
            }
        }

        for(int i = 0; i < in.size(); ++i) {
            Province province = Game.getProvince((Integer)in.get(i));

            for(int j = 0; j < province.getNeighboringProvincesSize(); ++j) {
                if (!Game.getProvince(Game.getProvince(province.getNeighboringProvinces(j)).getProvinceID()).wasRetreat && MoveUnits.canBeUsedInPath(nCivID, Game.getProvince(province.getNeighboringProvinces(j)).getProvinceID(), false, from)) {
                    if ((Game.getProvince(province.getNeighboringProvinces(j)).getCivID() == lookingForCivID || Game.getCiv(Game.getProvince(province.getNeighboringProvinces(j)).getCivID()).getPuppetOfCivID() == lookingForCivID || Game.getProvince(province.getNeighboringProvinces(j)).getCivID() == Game.getCiv(lookingForCivID).getPuppetOfCivID()) && !Game.getProvince(province.getNeighboringProvinces(j)).isOccupied()) {
                        clearWas(was);
                        return province.getNeighboringProvinces(j);
                    }

                    nIN.add(Game.getProvince(province.getNeighboringProvinces(j)).getProvinceID());
                    List<Integer> tPL = new ArrayList();

                    for(int u = 0; u < ((List)inPath.get(i)).size(); ++u) {
                        tPL.add((Integer)((List)inPath.get(i)).get(u));
                    }

                    tPL.add(Game.getProvince(province.getNeighboringProvinces(j)).getProvinceID());
                    nINPath.add(tPL);
                    Game.getProvince(Game.getProvince(province.getNeighboringProvinces(j)).getProvinceID()).wasRetreat = true;
                    was.add(Game.getProvince(province.getNeighboringProvinces(j)).getProvinceID());
                }
            }

            if (!province.getSeaProvince()) {
                for(int j = 0; j < province.getNeighboringSeaProvincesSize(); ++j) {
                    if (!Game.getProvince(Game.getProvince(province.getNeighboringSeaProvinces(j)).getProvinceID()).wasRetreat) {
                        if (Game.getProvince(province.getNeighboringSeaProvinces(j)).getCivID() == lookingForCivID && !Game.getProvince(province.getNeighboringSeaProvinces(j)).isOccupied()) {
                            return province.getNeighboringSeaProvinces(j);
                        }

                        nIN.add(Game.getProvince(province.getNeighboringSeaProvinces(j)).getProvinceID());
                        List<Integer> tPL = new ArrayList();

                        for(int u = 0; u < ((List)inPath.get(i)).size(); ++u) {
                            tPL.add((Integer)((List)inPath.get(i)).get(u));
                        }

                        tPL.add(Game.getProvince(province.getNeighboringSeaProvinces(j)).getProvinceID());
                        nINPath.add(tPL);
                        Game.getProvince(Game.getProvince(province.getNeighboringSeaProvinces(j)).getProvinceID()).wasRetreat = true;
                        was.add(Game.getProvince(province.getNeighboringSeaProvinces(j)).getProvinceID());
                    }
                }
            }
        }

        try {
            return buildPath(nCivID, was, nIN, nINPath, from, lookingForCivID);
        } catch (StackOverflowError var13) {
            clearWas(was);
            return -1;
        }
    }

    protected static final void clearWas(List<Integer> was) {
        for(int i = was.size() - 1; i >= 0; --i) {
            Game.getProvince((Integer)was.get(i)).wasRetreat = false;
        }

    }

    public static final void buildWars_Load() {
        try {
            for(War nWar : lWars.values()) {
                for(int i = 0; i < nWar.lAggressors.size(); ++i) {
                    Game.getCiv(((WarCivilization)nWar.lAggressors.get(i)).iCivID).diplomacy.addWar(nWar.key, ((WarCivilization)nWar.lAggressors.get(i)).iCivID);
                }

                for(int i = 0; i < nWar.lDefenders.size(); ++i) {
                    Game.getCiv(((WarCivilization)nWar.lDefenders.get(i)).iCivID).diplomacy.addWar(nWar.key, ((WarCivilization)nWar.lDefenders.get(i)).iCivID);
                }

                for(int i = 0; i < nWar.lAggressors.size(); ++i) {
                    for(int j = 0; j < nWar.lDefenders.size(); ++j) {
                        Game.getCiv(((WarCivilization)nWar.lAggressors.get(i)).iCivID).diplomacy.addAtWar(((WarCivilization)nWar.lDefenders.get(j)).iCivID);
                        Game.getCiv(((WarCivilization)nWar.lDefenders.get(j)).iCivID).diplomacy.addAtWar(((WarCivilization)nWar.lAggressors.get(i)).iCivID);
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static String getWarName(String key) {
        try {
            //ANALYTICALENGINE START
            War war_obj = lWars.get(key);

            if (war_obj.name != null) {
                return Game.lang.get(war_obj.name);
            } else {
                int war_name_id = key.charAt(0) % GameValues.war.WAR_NAMES.length;
                return Game.lang.get(GameValues.war.WAR_NAMES[war_name_id]);
            }
            //ANALYTICALENGINE END
        } catch (Exception var2) {
            return Game.lang.get("WarOverview");
        }
    }

    public static final void clearData() {
        lWars.clear();
        iWarsSize = 0;
    }
}
