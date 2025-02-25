//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski.SaveLoad;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.AI.AI_CivDiplomacy;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Civ_CreateNewArmy;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Manager;
import aoc.kingdoms.lukasz.jakowski.AI.Army.AI_Merge;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerData;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerStats;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerStats2;
import aoc.kingdoms.lukasz.jakowski.Player.More.PlayerStats3;
import aoc.kingdoms.lukasz.map.Loan;
import aoc.kingdoms.lukasz.map.advisors.Advisor;
import aoc.kingdoms.lukasz.map.advisors.AdvisorSerializer;
import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import aoc.kingdoms.lukasz.map.army.ArmyGeneral;
import aoc.kingdoms.lukasz.map.army.ArmyRecruit;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonusesSerializer;
import aoc.kingdoms.lukasz.map.civilization.CivilizationEventsData_Variables;
import aoc.kingdoms.lukasz.map.civilization.CivilizationGoldenAge;
import aoc.kingdoms.lukasz.map.civilization.CivilizationLegacy;
import aoc.kingdoms.lukasz.map.civilization.save.CivData;
import aoc.kingdoms.lukasz.map.civilization.save.CivData2;
import aoc.kingdoms.lukasz.map.civilization.save.CivData3;
import aoc.kingdoms.lukasz.map.civilization.save.CivData3Serializer;
import aoc.kingdoms.lukasz.map.civilization.save.CivData4;
import aoc.kingdoms.lukasz.map.civilization.save.CivData4Serializer;
import aoc.kingdoms.lukasz.map.civilization.stats.CivilizationEventsData;
import aoc.kingdoms.lukasz.map.civilization.stats.CivilizationEventsData2;
import aoc.kingdoms.lukasz.map.civilization.stats.CivilizationEventsData2Serializer;
import aoc.kingdoms.lukasz.map.civilization.stats.CivilizationEventsData3;
import aoc.kingdoms.lukasz.map.civilization.stats.CivilizationEventsData3Serializer;
import aoc.kingdoms.lukasz.map.civilization.stats.CivilizationEventsDataSerializer;
import aoc.kingdoms.lukasz.map.diplomacy.Diplomacy;
import aoc.kingdoms.lukasz.map.diplomacy.Diplomacy_RelationsAction;
import aoc.kingdoms.lukasz.map.diplomacy.Vassal;
import aoc.kingdoms.lukasz.map.moveUnits.MoveUnits;
import aoc.kingdoms.lukasz.map.plague.PlagueManager;
import aoc.kingdoms.lukasz.map.plague.ProvincePlague;
import aoc.kingdoms.lukasz.map.province.ProvinceInvest;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData10;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData2;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData2Serializer;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData3;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData4;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData4Serializer;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData5;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData6;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData6Serializer;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData7;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData8;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData8Serializer;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData9;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData9Serializer;
import aoc.kingdoms.lukasz.map.province.data.ProvinceDataSerializer;
import aoc.kingdoms.lukasz.map.rebels.RevolutionManager;
import aoc.kingdoms.lukasz.map.technology.TechnologyResearch;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.map.war.War;
import aoc.kingdoms.lukasz.map.war.WarManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import javax.script.Invocable;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SaveGameManager {
    public static String saveKey = "SAVE_KEY";
    public static String deleteSavedGameKey = "";
    public static int AUTO_SAVE_LAST_TURN_ID = 0;
    public static final float RESEARCH_PROGRESS_MULTIPLIER = 1000.0F;

    public SaveGameManager() {
    }

    public static void autosave() {
    }

    public static final void Save_1() {
        SaveDetails saveDetails = new SaveDetails();
        saveDetails.sCivTag = Game.getCiv(Game.player.iCivID).getCivTag();
        saveDetails.iTurnID = Game_Calendar.TURN_ID;
        saveDetails.iDay = Game_Calendar.currentDay;
        saveDetails.iMonth = Game_Calendar.currentMonth;
        saveDetails.iYear = Game_Calendar.currentYear;
        saveDetails.time = System.currentTimeMillis();
        saveDetails.scenarioTAG = Game.mapScenarios.sActiveScenarioTag;
        saveDetails.DIFFICULTY = Game.difficultyID;
        saveDetails.FOG_OF_WAR = Game.FOG_OF_WAR;
        saveDetails.SPECTATOR_MODE = Game.SPECTATOR_MODE;
        saveDetails.SANDBOX = Game.SANDBOX;
        saveDetails.ENABLE_CALL_VASSALS = Game.ENABLE_CALL_VASSALS;
        saveDetails.SCENARIO_EVENTS = Game.SCENARIO_EVENTS;
        saveDetails.HOURS_PER_TURN = Game.HOURS_PER_TURN;
        saveDetails.AI_AGGRESSIVENESS = Game.aiAggressivnes;
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", SaveDetails.class);
        FileHandle file;
        if (FileManager.IS_MAC) {
            file = Gdx.files.external("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Details.json");
        } else {
            file = Gdx.files.local("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Details.json");
        }

        file.writeString(json.prettyPrint(saveDetails), false);
        SaveDetails var3 = null;
        updateSavesList();

        //ANALYTICALENGINE START
        Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
        try {
            invocable.invokeFunction("parseOnGameSave", saveKey);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        //ANALYTICALENGINE END
    }

    public static final void updateSavesList() {
        try {
            FileHandle file;
            if (FileManager.IS_MAC) {
                file = Gdx.files.external("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
            } else if (CFG.readLocalFiles()) {
                file = Gdx.files.local("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
            } else {
                file = Gdx.files.internal("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
            }

            String tempTags = file.readString();
            if (tempTags.indexOf(saveKey) < 0) {
                FileHandle fileSave;
                if (FileManager.IS_MAC) {
                    fileSave = Gdx.files.external("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                } else {
                    fileSave = Gdx.files.local("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                }

                fileSave.writeString(tempTags + saveKey + ";", false);
            }
        } catch (Exception var3) {
            FileHandle fileSave;
            if (FileManager.IS_MAC) {
                fileSave = Gdx.files.external("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
            } else {
                fileSave = Gdx.files.local("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
            }

            fileSave.writeString(saveKey + ";", false);
        }

    }

    public static final void Save_Provinces_Data() {
        Json json = SaveManager.getJson();
        json.setSerializer(ProvinceData.class, new ProvinceDataSerializer());
        json.setElementType(LoadManager.ConfigJson.class, "Data", ProvinceData.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Data.json");
        file.writeString(json.toJson(Game.lProvincesData), false);
    }

    public static final void Save_Provinces_Data2() {
        Json json = SaveManager.getJson();
        json.setSerializer(ProvinceData2.class, new ProvinceData2Serializer());
        json.setElementType(LoadManager.ConfigJson.class, "Data", ProvinceData2.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Data2.json");
        file.writeString(json.toJson(Game.lProvincesData2), false);
    }

    public static final void Save_Provinces_Data3() {
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", ProvinceData3.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Data3.json");
        file.writeString(json.toJson(Game.lProvincesData3), false);
    }

    public static final void Save_Provinces_Data4() {
        Json json = SaveManager.getJson();
        json.setSerializer(ProvinceData4.class, new ProvinceData4Serializer());
        json.setElementType(LoadManager.ConfigJson.class, "Data", ProvinceData4.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Data4.json");
        file.writeString(json.toJson(Game.lProvincesData4), false);
    }

    public static final void Save_Provinces_Data5() {
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", ProvinceData5.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Data5.json");
        file.writeString(json.toJson(Game.lProvincesData5), false);
    }

    public static final void Save_Provinces_Data6() {
        Json json = SaveManager.getJson();
        json.setSerializer(ProvinceData6.class, new ProvinceData6Serializer());
        json.setElementType(LoadManager.ConfigJson.class, "Data", ProvinceData6.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Data6.json");
        file.writeString(json.toJson(Game.lProvincesData6), false);
    }

    public static final void Save_Provinces_Data7() {
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", ProvinceData7.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Data7.json");
        file.writeString(json.toJson(Game.lProvincesData7), false);
    }

    public static final void Save_Provinces_Data8() {
        Json json = SaveManager.getJson();
        json.setSerializer(ProvinceData8.class, new ProvinceData8Serializer());
        json.setElementType(LoadManager.ConfigJson.class, "Data", ProvinceData8.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Data8.json");
        file.writeString(json.toJson(Game.lProvincesData8), false);
    }

    public static final void Save_Provinces_Data9() {
        Json json = SaveManager.getJson();
        json.setSerializer(ProvinceData9.class, new ProvinceData9Serializer());
        json.setElementType(LoadManager.ConfigJson.class, "Data", ProvinceData9.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Data9.json");
        file.writeString(json.toJson(Game.lProvincesData9), false);
    }

    public static final void Save_Provinces_Data10() {
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", ProvinceData10.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Data10.json");
        file.writeString(json.toJson(Game.lProvincesData10), false);
    }

    public static final void Save_Provinces_Data_Population() {
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", ProvinceData.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Population.json");
        file.writeString(json.toJson(Game.lProvincesPopulation), false);
    }

    public static final void Save_Provinces_Data_Vassals() {
        ArrayList<Vassal> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.iVassalsSize > 0) {
                for(int a = 0; a < Game.getCiv(i).diplomacy.lVassals.size(); ++a) {
                    tSaveDetails.add((Vassal)Game.getCiv(i).diplomacy.lVassals.get(a));
                }
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Vassals.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Vassal> var3 = null;
    }

    public static final void Save_Provinces_Data_Buildings(int id) {
        ArrayList<SaveManager.ScenarioData_Buildings> tSaveDetails = new ArrayList();
        int i = id * GameValues.value.SAVE_PROVINCES_BUILDINGS_PER_FILE;

        for(int iSize = Math.min(Game.getProvincesSize(), (id + 1) * GameValues.value.SAVE_PROVINCES_BUILDINGS_PER_FILE); i < iSize; ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).iBuildingsSize > 0) {
                try {
                    SaveManager.ScenarioData_Buildings tData = new SaveManager.ScenarioData_Buildings();
                    tData.pid = i;

                    for(int j = 0; j < Game.getProvince(i).iBuildingsSize; ++j) {
                        tData.b0.add(Game.getProvince(i).getBuildings(j).getBuilding());
                        tData.b1.add(Game.getProvince(i).getBuildings(j).getBuildingID());
                    }

                    if (tData.b0.size() > 0) {
                        tSaveDetails.add(tData);
                    }
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Buildings_" + id + ".json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<SaveManager.ScenarioData_Buildings> var7 = null;
    }

    public static final void saveRelations() {
        ArrayList<SaveManager.ScenarioData_Relations> tData = new ArrayList();
        int i = 1;

        for(int iSize = (int)Math.floor((double)((float)Game.getCivsSize() / 2.0F)); i < iSize; ++i) {
            if (Game.getCiv(i).diplomacy.relation.size() > 0) {
                SaveManager.ScenarioData_Relations nData = new SaveManager.ScenarioData_Relations();
                nData.c = i;

                for(Map.Entry<Integer, Float> entry : Game.getCiv(i).diplomacy.relation.entrySet()) {
                    nData.w.add((Integer)entry.getKey());
                    nData.r.add(((Float)entry.getValue()).intValue());
                }

                tData.add(nData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Relations.json");
        file.writeString(json.toJson(tData), false);
        tData.clear();
        ArrayList<SaveManager.ScenarioData_Relations> var6 = null;
    }

    public static final void saveRelations2() {
        ArrayList<SaveManager.ScenarioData_Relations> tData = new ArrayList();

        for(int i = (int)Math.max((double)1.0F, Math.floor((double)((float)Game.getCivsSize() / 2.0F))); i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.relation.size() > 0) {
                SaveManager.ScenarioData_Relations nData = new SaveManager.ScenarioData_Relations();
                nData.c = i;

                for(Map.Entry<Integer, Float> entry : Game.getCiv(i).diplomacy.relation.entrySet()) {
                    nData.w.add((Integer)entry.getKey());
                    nData.r.add(((Float)entry.getValue()).intValue());
                }

                tData.add(nData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Relations2.json");
        file.writeString(json.toJson(tData), false);
        tData.clear();
        ArrayList<SaveManager.ScenarioData_Relations> var5 = null;
    }

    public static final void saveAlliances() {
        ArrayList<SaveManager.ScenarioData_Diplomacy> tData = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.alliance.size() > 0) {
                SaveManager.ScenarioData_Diplomacy nData = new SaveManager.ScenarioData_Diplomacy();
                nData.pid = i;

                for(Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.alliance.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }

                tData.add(nData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Alliances.json");
        file.writeString(json.toJson(tData), false);
        tData.clear();
        ArrayList<SaveManager.ScenarioData_Diplomacy> var5 = null;
    }

    public static final void saveRivals() {
        ArrayList<SaveManager.ScenarioData_Diplomacy> tData = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.rivals.size() > 0) {
                SaveManager.ScenarioData_Diplomacy nData = new SaveManager.ScenarioData_Diplomacy();
                nData.pid = i;

                for(Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.rivals.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }

                tData.add(nData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Rivals.json");
        file.writeString(json.toJson(tData), false);
        tData.clear();
        ArrayList<SaveManager.ScenarioData_Diplomacy> var5 = null;
    }

    public static final void saveRelationsImprove() {
        ArrayList<SaveManager.ScenarioData_Diplomacy> tData = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.iImprovingRelationsSize > 0) {
                SaveManager.ScenarioData_Diplomacy nData = new SaveManager.ScenarioData_Diplomacy();
                nData.pid = i;

                for(int j = 0; j < Game.getCiv(i).diplomacy.iImprovingRelationsSize; ++j) {
                    nData.w0.add(((Diplomacy_RelationsAction)Game.getCiv(i).diplomacy.improvingRelations.get(j)).iCivID);
                    nData.t0.add(((Diplomacy_RelationsAction)Game.getCiv(i).diplomacy.improvingRelations.get(j)).iTurnID);
                }

                tData.add(nData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "RelationsImprove.json");
        file.writeString(json.toJson(tData), false);
        tData.clear();
        ArrayList<SaveManager.ScenarioData_Diplomacy> var4 = null;
    }

    public static final void saveRelationsDamage() {
        ArrayList<SaveManager.ScenarioData_Diplomacy> tData = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.iDamagingRelationsSize > 0) {
                SaveManager.ScenarioData_Diplomacy nData = new SaveManager.ScenarioData_Diplomacy();
                nData.pid = i;

                for(int j = 0; j < Game.getCiv(i).diplomacy.iDamagingRelationsSize; ++j) {
                    nData.w0.add(((Diplomacy_RelationsAction)Game.getCiv(i).diplomacy.damagingRelations.get(j)).iCivID);
                    nData.t0.add(((Diplomacy_RelationsAction)Game.getCiv(i).diplomacy.damagingRelations.get(j)).iTurnID);
                }

                tData.add(nData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "RelationsDamage.json");
        file.writeString(json.toJson(tData), false);
        tData.clear();
        ArrayList<SaveManager.ScenarioData_Diplomacy> var4 = null;
    }

    public static final void saveDefensive() {
        ArrayList<SaveManager.ScenarioData_Diplomacy> tData = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.defensivePact.size() > 0) {
                SaveManager.ScenarioData_Diplomacy nData = new SaveManager.ScenarioData_Diplomacy();
                nData.pid = i;

                for(Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.defensivePact.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }

                tData.add(nData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Defensive.json");
        file.writeString(json.toJson(tData), false);
        tData.clear();
        ArrayList<SaveManager.ScenarioData_Diplomacy> var5 = null;
    }

    public static final void saveTruces() {
        ArrayList<SaveManager.ScenarioData_Diplomacy> tData = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.truce.size() > 0) {
                SaveManager.ScenarioData_Diplomacy nData = new SaveManager.ScenarioData_Diplomacy();
                nData.pid = i;

                for(Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.truce.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }

                tData.add(nData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Trcues.json");
        file.writeString(json.toJson(tData), false);
        tData.clear();
        ArrayList<SaveManager.ScenarioData_Diplomacy> var5 = null;
    }

    public static final void saveNonAggression() {
        ArrayList<SaveManager.ScenarioData_Diplomacy> tData = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.nonAggressionPact.size() > 0) {
                SaveManager.ScenarioData_Diplomacy nData = new SaveManager.ScenarioData_Diplomacy();
                nData.pid = i;

                for(Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.nonAggressionPact.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }

                tData.add(nData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "NonAggression.json");
        file.writeString(json.toJson(tData), false);
        tData.clear();
        ArrayList<SaveManager.ScenarioData_Diplomacy> var5 = null;
    }

    public static final void saveMilitaryAccess() {
        ArrayList<SaveManager.ScenarioData_Diplomacy> tData = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.militaryAccess.size() > 0) {
                SaveManager.ScenarioData_Diplomacy nData = new SaveManager.ScenarioData_Diplomacy();
                nData.pid = i;

                for(Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.militaryAccess.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }

                tData.add(nData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "MilitaryAccess.json");
        file.writeString(json.toJson(tData), false);
        tData.clear();
        ArrayList<SaveManager.ScenarioData_Diplomacy> var5 = null;
    }

    public static final void saveGuarantee() {
        ArrayList<SaveManager.ScenarioData_Diplomacy> tData = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).diplomacy.guarantee.size() > 0) {
                SaveManager.ScenarioData_Diplomacy nData = new SaveManager.ScenarioData_Diplomacy();
                nData.pid = i;

                for(Diplomacy.DiplomacyData dData : Game.getCiv(i).diplomacy.guarantee.values()) {
                    nData.w0.add(dData.iCivID);
                    nData.t0.add(dData.iTurnID);
                }

                tData.add(nData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Guarantee.json");
        file.writeString(json.toJson(tData), false);
        tData.clear();
        ArrayList<SaveManager.ScenarioData_Diplomacy> var5 = null;
    }

    public static final void saveWars() {
        ArrayList<War> tSaveDetails = new ArrayList();

        for(War nWar : WarManager.lWars.values()) {
            tSaveDetails.add(nWar);
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Wars.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<War> var3 = null;
    }

    public static final void Save_Provinces_Data_ConstructionBuilding() {
        ArrayList<ScenarioData_ProvinceConstructionBuilding> tSaveDetails = new ArrayList();

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).iBuildingsConstructionSize > 0) {
                try {
                    ScenarioData_ProvinceConstructionBuilding tData = new ScenarioData_ProvinceConstructionBuilding();
                    tData.p = i;

                    for(int j = 0; j < Game.getProvince(i).iBuildingsConstructionSize; ++j) {
                        tData.b0.add(Game.getProvince(i).getBuildingsConstruction(j).getBuilding());
                        tData.b1.add(Game.getProvince(i).getBuildingsConstruction(j).getBuildingID());
                        tData.ct.add(Game.getProvince(i).getBuildingsConstruction(j).getConstructionTime());
                        tData.ctL.add(Game.getProvince(i).getBuildingsConstruction(j).getConstructionTimeLeft());
                    }

                    if (tData.b0.size() > 0) {
                        tSaveDetails.add(tData);
                    }
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_BuildingsConstruction.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<ScenarioData_ProvinceConstructionBuilding> var5 = null;
    }

    public static final void Save_Provinces_Data_InvestDaysLeft() {
        ArrayList<Save_ProvinceInvest> tSaveDetails = new ArrayList();

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).iProvinceInvestSize > 0) {
                Save_ProvinceInvest tData = new Save_ProvinceInvest();
                tData.p = i;

                for(int j = 0; j < Game.getProvince(i).iProvinceInvestSize; ++j) {
                    tData.d.add(((ProvinceInvest)Game.getProvince(i).provinceInvestDaysLeft.get(j)).daysLeft);
                    tData.n.add(((ProvinceInvest)Game.getProvince(i).provinceInvestDaysLeft.get(j)).investTime);
                }

                tSaveDetails.add(tData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_InvestEconomy.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_ProvinceInvest> var4 = null;
    }

    public static final void Save_Provinces_Data_InvestTax() {
        ArrayList<Save_ProvinceInvest> tSaveDetails = new ArrayList();

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).iProvinceIncreaseTaxEfficiencySize > 0) {
                Save_ProvinceInvest tData = new Save_ProvinceInvest();
                tData.p = i;

                for(int j = 0; j < Game.getProvince(i).iProvinceIncreaseTaxEfficiencySize; ++j) {
                    tData.d.add(((ProvinceInvest)Game.getProvince(i).provinceIncreasTaxEfficiencyDaysLeft.get(j)).daysLeft);
                    tData.n.add(((ProvinceInvest)Game.getProvince(i).provinceIncreasTaxEfficiencyDaysLeft.get(j)).investTime);
                }

                tSaveDetails.add(tData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_InvestTax.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_ProvinceInvest> var4 = null;
    }

    public static final void Save_Provinces_Data_InvestManpower() {
        ArrayList<Save_ProvinceInvest> tSaveDetails = new ArrayList();

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).iProvinceIncreaseManpowerSize > 0) {
                Save_ProvinceInvest tData = new Save_ProvinceInvest();
                tData.p = i;

                for(int j = 0; j < Game.getProvince(i).iProvinceIncreaseManpowerSize; ++j) {
                    tData.d.add(((ProvinceInvest)Game.getProvince(i).provinceIncreaseManpowerDaysLeft.get(j)).daysLeft);
                    tData.n.add(((ProvinceInvest)Game.getProvince(i).provinceIncreaseManpowerDaysLeft.get(j)).investTime);
                }

                tSaveDetails.add(tData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_InvestManpower.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_ProvinceInvest> var4 = null;
    }

    public static final void Save_Provinces_Data_InvestGrowthRate() {
        ArrayList<Save_ProvinceInvest> tSaveDetails = new ArrayList();

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).iProvinceIncreaseGrowthRateSize > 0) {
                Save_ProvinceInvest tData = new Save_ProvinceInvest();
                tData.p = i;

                for(int j = 0; j < Game.getProvince(i).iProvinceIncreaseGrowthRateSize; ++j) {
                    tData.d.add(((ProvinceInvest)Game.getProvince(i).provinceIncreaseGrowthRateDaysLeft.get(j)).daysLeft);
                    tData.n.add(((ProvinceInvest)Game.getProvince(i).provinceIncreaseGrowthRateDaysLeft.get(j)).investTime);
                }

                tSaveDetails.add(tData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_InvestGrowth.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_ProvinceInvest> var4 = null;
    }

    public static final void Save_Provinces_Data_Infrastructure() {
        ArrayList<Save_ProvinceInvest> tSaveDetails = new ArrayList();

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).iProvinceDevelopInfrastructureSize > 0) {
                Save_ProvinceInvest tData = new Save_ProvinceInvest();
                tData.p = i;

                for(int j = 0; j < Game.getProvince(i).iProvinceDevelopInfrastructureSize; ++j) {
                    tData.d.add(((ProvinceInvest)Game.getProvince(i).provinceDevelopInfrastructureDaysLeft.get(j)).daysLeft);
                    tData.n.add(((ProvinceInvest)Game.getProvince(i).provinceDevelopInfrastructureDaysLeft.get(j)).investTime);
                }

                tSaveDetails.add(tData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_InvestInfrastructure.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_ProvinceInvest> var4 = null;
    }

    public static final void Save_Provinces_Data_CoreConstruction() {
        ArrayList<Save_ProvinceInvest_Single> tSaveDetails = new ArrayList();

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).coreCreation != null) {
                Save_ProvinceInvest_Single tData = new Save_ProvinceInvest_Single();
                tData.p = i;
                tData.d = Game.getProvince(i).coreCreation.daysLeft;
                tData.n = Game.getProvince(i).coreCreation.investTime;
                tSaveDetails.add(tData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_CoreCreation.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_ProvinceInvest_Single> var3 = null;
    }

    public static final void Save_Provinces_Data_ReligionConversion() {
        ArrayList<Save_ProvinceInvest_Single> tSaveDetails = new ArrayList();

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).religionConversion != null) {
                Save_ProvinceInvest_Single tData = new Save_ProvinceInvest_Single();
                tData.p = i;
                tData.d = Game.getProvince(i).religionConversion.daysLeft;
                tData.n = Game.getProvince(i).religionConversion.investTime;
                tSaveDetails.add(tData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Conversion.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_ProvinceInvest_Single> var3 = null;
    }

    public static final void Save_Provinces_Data_WondersConstruction() {
        ArrayList<Save_ProvinceInvest_Single> tSaveDetails = new ArrayList();

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).wonderConstruction != null) {
                Save_ProvinceInvest_Single tData = new Save_ProvinceInvest_Single();
                tData.p = i;
                tData.d = Game.getProvince(i).wonderConstruction.daysLeft;
                tData.n = Game.getProvince(i).wonderConstruction.investTime;
                tSaveDetails.add(tData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_WonderConstruction.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_ProvinceInvest_Single> var3 = null;
    }

    public static final void Save_Provinces_Data_Armies_MoreFiles(int id) {
        ArrayList<Save_Provinces_ArmyDivision> tSaveDetails = new ArrayList();
        int i = id * GameValues.value.SAVE_PROVINCES_ARMIES_PER_FILE;

        for(int iSize = Math.min(Game.getProvincesSize(), (id + 1) * GameValues.value.SAVE_PROVINCES_ARMIES_PER_FILE); i < iSize; ++i) {
            if (Game.getProvince(i).getArmySize() > 0) {
                for(int j = 0; j < Game.getProvince(i).getArmySize(); ++j) {
                    Save_Provinces_ArmyDivision tData = new Save_Provinces_ArmyDivision();
                    tData.k = Game.getProvince(i).getArmy(j).key;

                    for(int k = 0; k < Game.getProvince(i).getArmy(j).iArmyRegimentSize; ++k) {
                        tData.r.add(new Save_Provinces_ArmyRegiment((ArmyRegiment)Game.getProvince(i).getArmy(j).lArmyRegiment.get(k)));
                    }

                    tData.c = Game.getProvince(i).getArmy(j).civID;
                    tData.p = Game.getProvince(i).getArmy(j).provinceID;
                    tData.g = Game.getProvince(i).getArmy(j).armyGeneral;
                    tData.t = Game.getProvince(i).getArmy(j).inRetreat;
                    tSaveDetails.add(tData);
                }
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Armies_" + id + ".json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Provinces_ArmyDivision> var7 = null;
    }

    public static final void Save_Civ_Data_NukeProduction() {
        ArrayList<Save_ProvinceInvest> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).iNukesSize > 0) {
                Save_ProvinceInvest tData = new Save_ProvinceInvest();
                tData.p = i;

                for(int j = 0; j < Game.getCiv(i).iNukesSize; ++j) {
                    tData.d.add(((ProvinceInvest)Game.getCiv(i).nukesDaysLeft.get(j)).daysLeft);
                    tData.n.add(((ProvinceInvest)Game.getCiv(i).nukesDaysLeft.get(j)).investTime);
                }

                tSaveDetails.add(tData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "NukesProduction.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_ProvinceInvest> var4 = null;
    }

    public static final void Save_Civ_Data_UnlockedTechnologies() {
        ArrayList<Save_Civ_UnlockedTechnologies> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Save_Civ_UnlockedTechnologies tData = new Save_Civ_UnlockedTechnologies();
            tData.a = TechnologyTree.iTechnologySize;

            for(int j = 0; j < TechnologyTree.iTechnologySize; ++j) {
                if (!Game.getCiv(i).getTechResearched(j)) {
                    tData.a = j;
                    break;
                }
            }

            for(int j = tData.a; j < TechnologyTree.iTechnologySize; ++j) {
                if (Game.getCiv(i).getTechResearched(j)) {
                    tData.u.add(j);
                }
            }

            tSaveDetails.add(tData);
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "UnlockedTechnologies.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_UnlockedTechnologies> var4 = null;
    }

    public static final void Save_Civ_Data_ResearchProgress() {
        ArrayList<Save_Civ_ResearchProgress> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Save_Civ_ResearchProgress tData = new Save_Civ_ResearchProgress();
            int j = 0;

            for(int jSize = Game.getCiv(i).lResearching.size(); j < jSize; ++j) {
                tData.t.add(((TechnologyResearch)Game.getCiv(i).lResearching.get(j)).iTechID);
                tData.p.add((int)(((TechnologyResearch)Game.getCiv(i).lResearching.get(j)).fProgress * 1000.0F));
            }

            tSaveDetails.add(tData);
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "ResearchProgress.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_ResearchProgress> var5 = null;
    }

    public static final void Save_Civ_Data_Laws() {
        ArrayList<Save_Civ_Laws> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Save_Civ_Laws tData = new Save_Civ_Laws();
            int j = 0;

            for(int jSize = Game.getCiv(i).laws.size(); j < jSize; ++j) {
                tData.l.add((Integer)Game.getCiv(i).laws.get(j));
            }

            tSaveDetails.add(tData);
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Laws.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_Laws> var5 = null;
    }

    public static final void Save_Civ_Data_RecruitingArmy() {
        ArrayList<Save_Civ_RecruitArmy> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).iArmyRecruitSize > 0) {
                Save_Civ_RecruitArmy tData = new Save_Civ_RecruitArmy();
                tData.c = i;

                for(int j = 0; j < Game.getCiv(i).iArmyRecruitSize; ++j) {
                    for(int k = 0; k < ((ArrayList)Game.getCiv(i).lArmyRecruit.get(j)).size(); ++k) {
                        tData.a.add((ArmyRecruit)((ArrayList)Game.getCiv(i).lArmyRecruit.get(j)).get(k));
                    }
                }

                tSaveDetails.add(tData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "RecruitArmy.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_RecruitArmy> var5 = null;
    }

    public static final void Save_Civ_Data_RecruitArmyCreate() {
        ArrayList<Save_Civ_RecruitArmyCreate> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).lCreateNewArmy.size() > 0) {
                Save_Civ_RecruitArmyCreate tData = new Save_Civ_RecruitArmyCreate();
                tData.c = i;

                for(Map.Entry<String, Integer> entry : Game.getCiv(i).lCreateNewArmy.entrySet()) {
                    tData.k.add((String)entry.getKey());
                    tData.p.add((Integer)entry.getValue());
                }

                tSaveDetails.add(tData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "RecruitArmyCreate.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_RecruitArmyCreate> var5 = null;
    }

    public static final void Save_Civ_Data_UnlockedAdvantages() {
        ArrayList<Save_Civ_UnlockedAdvantages> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Save_Civ_UnlockedAdvantages tData = new Save_Civ_UnlockedAdvantages();

            for(int j = 0; j < Game.getCiv(i).iAdvantagesSize; ++j) {
                tData.a.add(((CivilizationLegacy)Game.getCiv(i).lAdvantages.get(j)).id);
                tData.l.add(((CivilizationLegacy)Game.getCiv(i).lAdvantages.get(j)).lvl);
            }

            tSaveDetails.add(tData);
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Advantages.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_UnlockedAdvantages> var4 = null;
    }

    public static final void Save_Map_Data_Plagues() {
        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Plagues.json");
        file.writeString(json.toJson(PlagueManager.activePlagues), false);
    }

    public static final void Save_Provinces_Data_Plagues() {
        ArrayList<Save_Province_Plague> tSaveDetails = new ArrayList();

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).provincePlague != null) {
                Save_Province_Plague tData = new Save_Province_Plague();
                tData.p = i;
                tData.l = Game.getProvince(i).provincePlague;
                tSaveDetails.add(tData);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Provinces_Plagues.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Province_Plague> var3 = null;
    }

    public static final void Save_Civs_Data_Rulers() {
        Json json = SaveManager.getJson();
        ArrayList<Save_Civ_Ruler> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Save_Civ_Ruler tData = new Save_Civ_Ruler();
            if (Game.getCiv(i).ruler == null) {
                tData = null;
            } else {
                tData.n = Game.getCiv(i).ruler.Name;
                tData.a = Game.getCiv(i).ruler.ImageID;
                tData.k = Game.getCiv(i).ruler.kingImage;
                tData.d = Game.getCiv(i).ruler.BornDay;
                tData.m = Game.getCiv(i).ruler.BornMonth;
                tData.y = Game.getCiv(i).ruler.BornYear;
                tData.r = Game.getCiv(i).ruler.isRandom;
            }

            tSaveDetails.add(tData);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Rulers.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_Ruler> var4 = null;
    }

    public static final void Save_Civs_Data_RulersBonuses() {
        Json json = SaveManager.getJson();
        json.setSerializer(CivilizationBonuses.class, new CivilizationBonusesSerializer());
        ArrayList<CivilizationBonuses> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            new Save_Civ_Ruler();
            if (Game.getCiv(i).ruler == null) {
                tSaveDetails.add(null);
            } else {
                tSaveDetails.add(Game.getCiv(i).ruler.rulerBonuses);
            }
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "RulersBonuses.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<CivilizationBonuses> var4 = null;
    }

    public static final void Save_Civs_Data_AdvisorsAdm() {
        Json json = SaveManager.getJson();
        ArrayList<Save_Civ_Advisor> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Save_Civ_Advisor tData = new Save_Civ_Advisor();
            if (Game.getCiv(i).advisorAdministration.sName == null) {
                tData = null;
            } else {
                tData.n = Game.getCiv(i).advisorAdministration.sName;
                tData.y = Game.getCiv(i).advisorAdministration.iYearOfBirth;
                tData.m = Game.getCiv(i).advisorAdministration.iMonthOfBirth;
                tData.d = Game.getCiv(i).advisorAdministration.iDayOfBirth;
                tData.g = Game.getCiv(i).advisorAdministration.sIMG;
                tData.e = Game.getCiv(i).advisorAdministration.imageID;
                tData.l = Game.getCiv(i).advisorAdministration.iLevel;
            }

            tSaveDetails.add(tData);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AdvisorAdministration.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_Advisor> var4 = null;
    }

    public static final void Save_Civs_Data_AdvisorsAdmBonuses() {
        Json json = SaveManager.getJson();
        json.setSerializer(Advisor.class, new AdvisorSerializer());
        ArrayList<Advisor> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            new Save_Civ_Ruler();
            if (Game.getCiv(i).advisorAdministration.sName == null) {
                tSaveDetails.add(null);
            } else {
                tSaveDetails.add(Game.getCiv(i).advisorAdministration);
            }
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AdvisorAdministrationBonuses.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Advisor> var4 = null;
    }

    public static final void Save_Civs_Data_AdvisorsEconomy() {
        Json json = SaveManager.getJson();
        ArrayList<Save_Civ_Advisor> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Save_Civ_Advisor tData = new Save_Civ_Advisor();
            if (Game.getCiv(i).advisorEconomy.sName == null) {
                tData = null;
            } else {
                tData.n = Game.getCiv(i).advisorEconomy.sName;
                tData.y = Game.getCiv(i).advisorEconomy.iYearOfBirth;
                tData.m = Game.getCiv(i).advisorEconomy.iMonthOfBirth;
                tData.d = Game.getCiv(i).advisorEconomy.iDayOfBirth;
                tData.g = Game.getCiv(i).advisorEconomy.sIMG;
                tData.e = Game.getCiv(i).advisorEconomy.imageID;
                tData.l = Game.getCiv(i).advisorEconomy.iLevel;
            }

            tSaveDetails.add(tData);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AdvisorEconomy.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_Advisor> var4 = null;
    }

    public static final void Save_Civs_Data_AdvisorsEconomyBonuses() {
        Json json = SaveManager.getJson();
        json.setSerializer(Advisor.class, new AdvisorSerializer());
        ArrayList<Advisor> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            new Save_Civ_Ruler();
            if (Game.getCiv(i).advisorEconomy.sName == null) {
                tSaveDetails.add(null);
            } else {
                tSaveDetails.add(Game.getCiv(i).advisorEconomy);
            }
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AdvisorEconomyBonuses.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Advisor> var4 = null;
    }

    public static final void Save_Civs_Data_AdvisorsInnovation() {
        Json json = SaveManager.getJson();
        ArrayList<Save_Civ_Advisor> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Save_Civ_Advisor tData = new Save_Civ_Advisor();
            if (Game.getCiv(i).advisorTechnology.sName == null) {
                tData = null;
            } else {
                tData.n = Game.getCiv(i).advisorTechnology.sName;
                tData.y = Game.getCiv(i).advisorTechnology.iYearOfBirth;
                tData.m = Game.getCiv(i).advisorTechnology.iMonthOfBirth;
                tData.d = Game.getCiv(i).advisorTechnology.iDayOfBirth;
                tData.g = Game.getCiv(i).advisorTechnology.sIMG;
                tData.e = Game.getCiv(i).advisorTechnology.imageID;
                tData.l = Game.getCiv(i).advisorTechnology.iLevel;
            }

            tSaveDetails.add(tData);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AdvisorInnovation.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_Advisor> var4 = null;
    }

    public static final void Save_Civs_Data_AdvisorsInnovationBonuses() {
        Json json = SaveManager.getJson();
        json.setSerializer(Advisor.class, new AdvisorSerializer());
        ArrayList<Advisor> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            new Save_Civ_Ruler();
            if (Game.getCiv(i).advisorTechnology.sName == null) {
                tSaveDetails.add(null);
            } else {
                tSaveDetails.add(Game.getCiv(i).advisorTechnology);
            }
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AdvisorInnovationBonuses.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Advisor> var4 = null;
    }

    public static final void Save_Civs_Data_AdvisorsMilitary() {
        Json json = SaveManager.getJson();
        ArrayList<Save_Civ_Advisor> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Save_Civ_Advisor tData = new Save_Civ_Advisor();
            if (Game.getCiv(i).advisorMilitary.sName == null) {
                tData = null;
            } else {
                tData.n = Game.getCiv(i).advisorMilitary.sName;
                tData.y = Game.getCiv(i).advisorMilitary.iYearOfBirth;
                tData.m = Game.getCiv(i).advisorMilitary.iMonthOfBirth;
                tData.d = Game.getCiv(i).advisorMilitary.iDayOfBirth;
                tData.g = Game.getCiv(i).advisorMilitary.sIMG;
                tData.e = Game.getCiv(i).advisorMilitary.imageID;
                tData.l = Game.getCiv(i).advisorMilitary.iLevel;
            }

            tSaveDetails.add(tData);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AdvisorMilitary.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_Advisor> var4 = null;
    }

    public static final void Save_Civs_Data_AdvisorsMilitaryBonuses() {
        Json json = SaveManager.getJson();
        json.setSerializer(Advisor.class, new AdvisorSerializer());
        ArrayList<Advisor> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            new Save_Civ_Ruler();
            if (Game.getCiv(i).advisorMilitary.sName == null) {
                tSaveDetails.add(null);
            } else {
                tSaveDetails.add(Game.getCiv(i).advisorMilitary);
            }
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AdvisorMilitaryBonuses.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Advisor> var4 = null;
    }

    public static final void Save_Civs_Data_GeneralsNotAssigned() {
        ArrayList<ArmyGeneral> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).getGeneralsNotAssignedSize() > 0) {
                for(int j = 0; j < Game.getCiv(i).getGeneralsNotAssignedSize(); ++j) {
                    tSaveDetails.add(Game.getCiv(i).getGeneralNotAssigned(j));
                }
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "GeneralsNotAssigned.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<ArmyGeneral> var3 = null;
    }

    public static final void Save_Civs_Data_EventsData() {
        Json json = SaveManager.getJson();
        json.setSerializer(CivilizationEventsData.class, new CivilizationEventsDataSerializer());
        ArrayList<CivilizationEventsData> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            tSaveDetails.add(Game.getCiv(i).eventsData);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "EventsData.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<CivilizationEventsData> var3 = null;
    }

    public static final void Save_Civs_Data_EventsData2() {
        Json json = SaveManager.getJson();
        json.setSerializer(CivilizationEventsData2.class, new CivilizationEventsData2Serializer());
        ArrayList<CivilizationEventsData2> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            tSaveDetails.add(Game.getCiv(i).eventsData2);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "EventsData2.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<CivilizationEventsData2> var3 = null;
    }

    public static final void Save_Civs_Data_EventsData3() {
        Json json = SaveManager.getJson();
        json.setSerializer(CivilizationEventsData3.class, new CivilizationEventsData3Serializer());
        ArrayList<CivilizationEventsData3> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            tSaveDetails.add(Game.getCiv(i).eventsData3);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "EventsData3.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<CivilizationEventsData3> var3 = null;
    }

    public static final void Save_Civs_Data() {
        Json json = SaveManager.getJson();
        ArrayList<CivData> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            tSaveDetails.add(Game.getCiv(i).civData);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Civs.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<CivData> var3 = null;
    }

    public static final void Save_Civs_Data2() {
        Json json = SaveManager.getJson();
        ArrayList<CivData2> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            tSaveDetails.add(new CivData2(i));
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Civs2.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<CivData2> var3 = null;
    }

    public static final void Save_Civs_Data3() {
        Json json = SaveManager.getJson();
        json.setSerializer(CivData3.class, new CivData3Serializer());
        ArrayList<CivData3> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            tSaveDetails.add(Game.getCiv(i).civData3);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Civs3.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<CivData3> var3 = null;
    }

    public static final void Save_Civs_Data4() {
        Json json = SaveManager.getJson();
        json.setSerializer(CivData4.class, new CivData4Serializer());
        ArrayList<CivData4> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            tSaveDetails.add(Game.getCiv(i).civData4);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Civs4.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<CivData4> var3 = null;
    }

    public static final void Save_Civs_Data_TemporaryBonuses() {
        Json json = SaveManager.getJson();
        json.setSerializer(CivilizationBonuses.class, new CivilizationBonusesSerializer());
        ArrayList<Save_Civ_TemporaryBonuses> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Save_Civ_TemporaryBonuses tData = new Save_Civ_TemporaryBonuses();
            if (Game.getCiv(i).iCivBonusesTemporarySize > 0) {
                for(int j = 0; j < Game.getCiv(i).iCivBonusesTemporarySize; ++j) {
                    tData.b.add((CivilizationBonuses)Game.getCiv(i).civBonusesTemporary.get(j));
                }
            }

            tSaveDetails.add(tData);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "BonusesTemp.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_TemporaryBonuses> var5 = null;
    }

    public static final void Save_Civs_Data_EventsVariables() {
        Json json = SaveManager.getJson();
        ArrayList<CivilizationEventsData_Variables> tSaveDetails = new ArrayList();
        int i = 1;

        for(int iSize = (int)Math.floor((double)((float)Game.getCivsSize() / 2.0F)); i < iSize; ++i) {
            tSaveDetails.add(Game.getCiv(i).eventsDataVariables);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "EventsVariables.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<CivilizationEventsData_Variables> var4 = null;
    }

    public static final void Save_Civs_Data_EventsVariables2() {
        Json json = SaveManager.getJson();
        ArrayList<CivilizationEventsData_Variables> tSaveDetails = new ArrayList();

        for(int i = (int)Math.floor((double)((float)Game.getCivsSize() / 2.0F)); i < Game.getCivsSize(); ++i) {
            tSaveDetails.add(Game.getCiv(i).eventsDataVariables);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "EventsVariables2.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<CivilizationEventsData_Variables> var3 = null;
    }

    public static final void Save_Civs_Data_GoldenAges() {
        Json json = SaveManager.getJson();
        ArrayList<CivilizationGoldenAge> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            tSaveDetails.add(Game.getCiv(i).goldenAge);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "GoldenAge.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<CivilizationGoldenAge> var3 = null;
    }

    public static final void Save_Civs_Data_Loans() {
        Json json = SaveManager.getJson();
        ArrayList<Save_Civ_Loan> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).iLoansSize > 0) {
                for(int j = 0; j < Game.getCiv(i).iLoansSize; ++j) {
                    tSaveDetails.add(new Save_Civ_Loan(i, (Loan)Game.getCiv(i).loans.get(j)));
                }
            }
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Loans.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_Loan> var4 = null;
    }

    public static final void Save_Civs_Data_Legacies() {
        Json json = SaveManager.getJson();
        ArrayList<Save_Civ_Legacies> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Save_Civ_Legacies tData = new Save_Civ_Legacies();
            if (Game.getCiv(i).getLegaciesSize() > 0) {
                for(int j = 0; j < Game.getCiv(i).getLegaciesSize(); ++j) {
                    tData.b.add(Game.getCiv(i).getCivLegacy(j));
                }
            }

            tSaveDetails.add(tData);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Legacies.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_Legacies> var5 = null;
    }

    public static final void Save_Civs_Data_MoveUnits() {
        Json json = SaveManager.getJson();
        ArrayList<Save_Civ_MoveUnits> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getMoveUnitsSize() > 0) {
                for(int j = 0; j < Game.getCiv(i).getMoveUnitsSize(); ++j) {
                    Save_Civ_MoveUnits tData = new Save_Civ_MoveUnits();
                    tData.c = i;
                    tData.k = Game.getCiv(i).getMoveUnits(j).key;
                    tData.f = Game.getCiv(i).getMoveUnits(j).getFromProvinceID();
                    tData.t = Game.getCiv(i).getMoveUnits(j).getToProvinceLastID();
                    tData.m = Game.getCiv(i).getMoveUnits(j).currentMovementProgressWidth;
                    tSaveDetails.add(tData);
                }
            }
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "MoveUnits.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_MoveUnits> var5 = null;
    }

    public static final void Save_Rebels_Data_MoveUnits() {
        Json json = SaveManager.getJson();
        ArrayList<Save_Civ_MoveUnits> tSaveDetails = new ArrayList();

        for(int i = 0; i < Game.revolutionMoveUnits.iMoveUnitsSize; ++i) {
            Save_Civ_MoveUnits tData = new Save_Civ_MoveUnits();
            tData.c = -1;
            tData.k = ((MoveUnits)Game.revolutionMoveUnits.lMoveUnits.get(i)).key;
            tData.f = ((MoveUnits)Game.revolutionMoveUnits.lMoveUnits.get(i)).getFromProvinceID();
            tData.t = ((MoveUnits)Game.revolutionMoveUnits.lMoveUnits.get(i)).getToProvinceLastID();
            tData.m = ((MoveUnits)Game.revolutionMoveUnits.lMoveUnits.get(i)).currentMovementProgressWidth;
            tSaveDetails.add(tData);
        }

        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "RebelsMoveUnits.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_MoveUnits> var4 = null;
    }

    public static final void Save_Rebels_Data() {
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", RevolutionManager.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Rebels.json");
        file.writeString(json.toJson(Game.revolutionManager), false);
    }

    public static final void Save_Map_Data_Battles() {
        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "Battles.json");
        file.writeString(json.toJson(Game.battleManager.lBattle), false);
    }

    public static final void Save_Player_Data() {
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", PlayerData.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "PlayerData.json");
        file.writeString(json.toJson(Game.player.playerData), false);
    }

    public static final void Save_Player_Stats() {
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", PlayerStats.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "PlayerStats.json");
        file.writeString(json.toJson(Game.player.playerStats), false);
    }

    public static final void Save_Player_Stats2() {
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", PlayerStats2.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "PlayerStats2.json");
        file.writeString(json.toJson(Game.player.playerStats2), false);
    }

    public static final void Save_Player_Stats3() {
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", PlayerStats3.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "PlayerStats3.json");
        file.writeString(json.toJson(Game.player.playerStats3), false);
    }

    public static final void Save_Map_AlliancesSpecial() {
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", Alliance.class);
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AllianceSpecial.json");
        file.writeString(json.toJson(Game.alliancesSpecial), false);
    }

    public static final void Save_Civs_Data_AI_Merge() {
        ArrayList<Save_Civ_AI_Merge> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).aiMerge.aiMergeTasks.size() > 0) {
                Save_Civ_AI_Merge saveCivAiMerge = new Save_Civ_AI_Merge();
                saveCivAiMerge.c = i;
                saveCivAiMerge.a = Game.getCiv(i).aiMerge;
                tSaveDetails.add(saveCivAiMerge);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AI_Merge.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_AI_Merge> var3 = null;
    }

    public static final void Save_Civs_Data_AI_CreateNewArmy() {
        ArrayList<Save_Civ_AI_CreateNewArmy> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).aiCivCreateNewArmy.cNA.size() > 0) {
                Save_Civ_AI_CreateNewArmy saveAI = new Save_Civ_AI_CreateNewArmy();
                saveAI.c = i;
                saveAI.a = Game.getCiv(i).aiCivCreateNewArmy;
                tSaveDetails.add(saveAI);
            }
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AI_CreateNewArmy.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<Save_Civ_AI_CreateNewArmy> var3 = null;
    }

    public static final void Save_Civs_Data_AI_Diplomacy() {
        ArrayList<AI_CivDiplomacy> tSaveDetails = new ArrayList();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            tSaveDetails.add(Game.getCiv(i).aiCivDiplomacy);
        }

        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AI_Diplomacy.json");
        file.writeString(json.toJson(tSaveDetails), false);
        tSaveDetails.clear();
        ArrayList<AI_CivDiplomacy> var3 = null;
    }

    public static final void Save_Civs_Data_AI_Budget() {
        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + saveKey + "/" + "AI_Budget.json");
        file.writeString(json.toJson(AI_Manager.aiBudget), false);
    }

    public static final void deleteFile(String path) {
        try {
            if (Gdx.files.local(path).exists()) {
                Gdx.files.local(path).delete();
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        try {
            if (Gdx.files.internal(path).exists()) {
                Gdx.files.internal(path).delete();
            }
        } catch (Exception var3) {
        }

        if (FileManager.IS_MAC) {
            try {
                if (Gdx.files.external(path).exists()) {
                    Gdx.files.external(path).delete();
                }
            } catch (Exception var2) {
            }
        }

    }

    public static final void deleteSavedGame(String deleteKey, boolean updateList) {
        try {
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Details.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Data.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Data2.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Data3.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Data4.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Data5.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Data6.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Data7.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Data8.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Data9.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Data10.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Population.json");

            for(int i = 0; i < 1000; ++i) {
                if (FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Buildings_" + i + ".json").exists()) {
                    deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Buildings_" + i + ".json");
                }
            }

            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_BuildingsConstruction.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_InvestEconomy.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_InvestTax.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_InvestManpower.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_InvestGrowth.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_InvestInfrastructure.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_CoreCreation.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Conversion.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_WonderConstruction.json");

            for(int i = 0; i < 1000; ++i) {
                if (FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Armies_" + i + ".json").exists()) {
                    deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Armies_" + i + ".json");
                }
            }

            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Provinces_Plagues.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "PlayerData.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "PlayerStats.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "PlayerStats2.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "PlayerStats3.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "UnlockedTechnologies.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "ResearchProgress.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Advantages.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Laws.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "RecruitArmy.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "RecruitArmyCreate.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "NukesProduction.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Rulers.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "RulersBonuses.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AdvisorAdministration.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AdvisorAdministrationBonuses.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AdvisorEconomy.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AdvisorEconomyBonuses.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AdvisorInnovation.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AdvisorInnovationBonuses.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AdvisorMilitary.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AdvisorMilitaryBonuses.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "GeneralsNotAssigned.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "EventsVariables.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "EventsVariables2.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Civs.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Civs2.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Civs3.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Civs4.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "EventsData.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "EventsData2.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "EventsData3.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "BonusesTemp.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "GoldenAge.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Loans.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Legacies.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "MoveUnits.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Battles.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Plagues.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Relations.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Relations2.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Alliances.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Guarantee.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Defensive.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Trcues.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "NonAggression.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "MilitaryAccess.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AllianceSpecial.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Rivals.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "RelationsImprove.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "RelationsDamage.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Vassals.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Wars.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AI_Merge.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AI_CreateNewArmy.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AI_Diplomacy.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "AI_Budget.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "Rebels.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey + "/" + "RebelsMoveUnits.json");
            deleteFile("saves/" + Game.map.getFile_ActiveMap_Path() + deleteKey);
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        try {
            if (updateList) {
                FileHandle fileList;
                if (FileManager.IS_MAC) {
                    fileList = Gdx.files.external("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                } else if (CFG.readLocalFiles()) {
                    fileList = Gdx.files.local("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                } else {
                    fileList = Gdx.files.internal("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                }

                if (fileList.exists()) {
                    String[] tempTags = fileList.readString().split(";");
                    if (tempTags.length > 1) {
                        String nListKeys = "";

                        for(int i = 0; i < tempTags.length; ++i) {
                            if (!tempTags[i].equals(deleteKey)) {
                                nListKeys = nListKeys + tempTags[i] + ";";
                            }
                        }

                        FileHandle fileSave = FileManager.getSaveType("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                        fileSave.writeString(nListKeys, false);
                    } else if (Gdx.files.external("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt").exists()) {
                        Gdx.files.external("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt").delete();
                    } else if (Gdx.files.local("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt").exists()) {
                        Gdx.files.local("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt").delete();
                    } else if (Gdx.files.internal("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt").exists()) {
                        Gdx.files.internal("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt").delete();
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static class SaveDetails {
        public String sCivTag;
        public int iTurnID;
        public int iDay;
        public int iMonth;
        public int iYear;
        public String scenarioTAG;
        public boolean SCENARIO_EVENTS = true;
        public boolean AOC_MODE = false;
        public int HOURS_PER_TURN = 24;
        public int DIFFICULTY;
        public boolean FOG_OF_WAR;
        public boolean SPECTATOR_MODE;
        public boolean SANDBOX;
        public boolean ENABLE_CALL_VASSALS;
        public int AI_AGGRESSIVENESS;
        public long time;

        public SaveDetails() {
        }
    }

    public static class ScenarioData_ProvinceConstructionBuilding {
        public int p;
        public List<Integer> b0 = new ArrayList();
        public List<Integer> b1 = new ArrayList();
        public List<Integer> ct = new ArrayList();
        public List<Integer> ctL = new ArrayList();

        public ScenarioData_ProvinceConstructionBuilding() {
        }
    }

    public static class Save_ProvinceInvest {
        public int p;
        public List<Integer> d = new ArrayList();
        public List<Integer> n = new ArrayList();

        public Save_ProvinceInvest() {
        }
    }

    public static class Save_ProvinceInvest_Single {
        public int p;
        public int d;
        public int n;

        public Save_ProvinceInvest_Single() {
        }
    }

    public static class Save_Provinces_ArmyDivision {
        public String k;
        public List<Save_Provinces_ArmyRegiment> r = new ArrayList();
        public int c;
        public int p;
        public ArmyGeneral g;
        public boolean t;

        public Save_Provinces_ArmyDivision() {
        }
    }

    public static class Save_Provinces_ArmyRegiment {
        public String k;
        public int u;
        public int a;
        public int n;
        public float m;

        public Save_Provinces_ArmyRegiment() {
        }

        public Save_Provinces_ArmyRegiment(ArmyRegiment armyRegiment) {
            this.k = armyRegiment.key;
            this.u = armyRegiment.uID;
            this.a = armyRegiment.aID;
            this.n = armyRegiment.num;
            this.m = armyRegiment.mo;
        }
    }

    public static class Save_Civ_UnlockedTechnologies {
        public int a;
        public List<Integer> u = new ArrayList();

        public Save_Civ_UnlockedTechnologies() {
        }
    }

    public static class Save_Civ_ResearchProgress {
        public List<Integer> t = new ArrayList();
        public List<Integer> p = new ArrayList();

        public Save_Civ_ResearchProgress() {
        }
    }

    public static class Save_Civ_Laws {
        public List<Integer> l = new ArrayList();

        public Save_Civ_Laws() {
        }
    }

    public static class Save_Civ_RecruitArmy {
        public int c;
        public List<ArmyRecruit> a = new ArrayList();

        public Save_Civ_RecruitArmy() {
        }
    }

    public static class Save_Civ_RecruitArmyCreate {
        public int c;
        public List<String> k = new ArrayList();
        public List<Integer> p = new ArrayList();

        public Save_Civ_RecruitArmyCreate() {
        }
    }

    public static class Save_Civ_UnlockedAdvantages {
        public List<Integer> a = new ArrayList();
        public List<Integer> l = new ArrayList();

        public Save_Civ_UnlockedAdvantages() {
        }
    }

    public static class Save_Province_Plague {
        public int p;
        public ProvincePlague l;

        public Save_Province_Plague() {
        }
    }

    public static class Save_Civ_Ruler {
        public String n;
        public String a;
        public boolean k;
        public int d;
        public int m;
        public int y;
        public boolean r;

        public Save_Civ_Ruler() {
        }
    }

    public static class Save_Civ_Advisor {
        public String n;
        public int y;
        public int m;
        public int d;
        public String g;
        public int e;
        public int l;

        public Save_Civ_Advisor() {
        }
    }

    public static class Save_Civ_TemporaryBonuses {
        public List<CivilizationBonuses> b = new ArrayList();

        public Save_Civ_TemporaryBonuses() {
        }
    }

    public static class Save_Civ_Loan {
        public int c;
        public float l;
        public float n;
        public int e;

        public Save_Civ_Loan() {
        }

        public Save_Civ_Loan(int civID, Loan loan) {
            this.c = civID;
            this.l = loan.fLoanValue;
            this.n = loan.fInterestPerMonth;
            this.e = loan.iExpires_TurnID;
        }
    }

    public static class Save_Civ_Legacies {
        public List<CivilizationLegacy> b = new ArrayList();

        public Save_Civ_Legacies() {
        }
    }

    public static class Save_Civ_MoveUnits {
        public int c;
        public String k;
        public int f;
        public int t;
        public float m;

        public Save_Civ_MoveUnits() {
        }
    }

    public static class Save_Civ_AI_Merge {
        public int c;
        public AI_Merge a;

        public Save_Civ_AI_Merge() {
        }
    }

    public static class Save_Civ_AI_CreateNewArmy {
        public int c;
        public AI_Civ_CreateNewArmy a;

        public Save_Civ_AI_CreateNewArmy() {
        }
    }
}
