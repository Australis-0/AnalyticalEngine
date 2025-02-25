//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.events.EventsManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.CharactersManager;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Budget;
import aoc.kingdoms.lukasz.jakowski.AI.AI_Manager;
import aoc.kingdoms.lukasz.jakowski.Missions.MissionTree;
import aoc.kingdoms.lukasz.jakowski.Player.Player;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadManager;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveGameManager;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveManager;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Synchronize.M_GameState;
import aoc.kingdoms.lukasz.map.AdvantagesManager;
import aoc.kingdoms.lukasz.map.BonusesManager;
import aoc.kingdoms.lukasz.map.FormableCivManager;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.map.SiegeManager;
import aoc.kingdoms.lukasz.map.WondersManager;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.map.allianceHRE.Alliance;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.army.ArmyGeneral;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.civilization.CivilizationAdvisorsPool;
import aoc.kingdoms.lukasz.map.civilization.CivilizationGeneralsPool;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRanking;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRegionsManager;
import aoc.kingdoms.lukasz.map.diplomacy.Diplomacy;
import aoc.kingdoms.lukasz.map.province.ProvinceBorder;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.map.terrain.Terrain;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menus.MainMenu;
import aoc.kingdoms.lukasz.menus.LoadSave.Menu_LoadScenario;
import aoc.kingdoms.lukasz.menus.NewGame.NewGame;
import aoc.kingdoms.lukasz.menusInGame.InGame;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Government;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioSettings;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.codedisaster.steamworks.SteamUGC;
import java.util.ArrayList;
import java.util.List;

public class MapScenarios {
    public int SCENARIOS_SIZE;
    public List<String> lScenarios_TagsList = new ArrayList();
    public List<Image> lScenarios_Preview = new ArrayList();
    public String sActiveScenarioTag = "";
    public boolean scenarioEditor_isCampaign = false;
    public List<Integer> editorProvinceReligion = new ArrayList();
    public List<Details> details = new ArrayList();
    public static Details scenarioEditorDetails = new Details();
    public static int DEFAULT_VALUE = -2;

    public MapScenarios() {
    }

    public final void loadScenarios(boolean updateDefaultScenario) {
        if (!updateDefaultScenario) {
            this.clearScenarios();
        }

        List<String> tempScenarios_TagsList = new ArrayList();
        List<Details> tempDetails = new ArrayList();
        if (CFG.isDesktop()) {
            try {
                if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "Scenarios.txt").exists()) {
                    FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "Scenarios.txt");
                    String tempT = tempFileT.readString();
                    String[] tagsSPLITED = tempT.split(";");
                    int i = 0;

                    for(int iSize = tagsSPLITED.length; i < iSize; ++i) {
                        Json json = new Json();
                        FileHandle file = null;

                        for(int a = 0; a < SteamManager.modsFoldersSize; ++a) {
                            if (FileManager.IS_MAC) {
                                if (Gdx.files.external((String)SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json").exists()) {
                                    file = Gdx.files.external((String)SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json");
                                    break;
                                }
                            } else if (Gdx.files.internal((String)SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json").exists()) {
                                file = Gdx.files.internal((String)SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json");
                                break;
                            }
                        }

                        if (file == null) {
                            for(int a = 0; a < SteamManager.itemsInstalledSize; ++a) {
                                if (Gdx.files.absolute(((SteamUGC.ItemInstallInfo)SteamManager.itemsInstalled.get(a)).getFolder() + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json").exists()) {
                                    file = Gdx.files.absolute(((SteamUGC.ItemInstallInfo)SteamManager.itemsInstalled.get(a)).getFolder() + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json");
                                    break;
                                }
                            }
                        }

                        try {
                            if (file == null) {
                                file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json");
                            }
                        } catch (Exception ex) {
                            CFG.exceptionStack(ex);
                        }

                        if (file != null && file.exists()) {
                            tempScenarios_TagsList.add(tagsSPLITED[i]);
                            tempDetails.add((Details)json.fromJson(Details.class, file));
                        }
                    }
                }

                int listBegin = tempScenarios_TagsList.size();
                FileHandle[] files;
                if (FileManager.IS_MAC) {
                    files = Gdx.files.external("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/").list();
                } else {
                    files = Gdx.files.internal("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/").list();
                }

                for(FileHandle file : files) {
                    if (!tempScenarios_TagsList.contains(file.name())) {
                        tempScenarios_TagsList.add(file.name());
                    }
                }

                for(int i = 0; i < SteamManager.modsFoldersSize; ++i) {
                    if (FileManager.IS_MAC) {
                        files = Gdx.files.external((String)SteamManager.modsFolders.get(i) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/").list();
                    } else {
                        files = Gdx.files.internal((String)SteamManager.modsFolders.get(i) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/").list();
                    }

                    for(FileHandle file : files) {
                        if (!tempScenarios_TagsList.contains(file.name())) {
                            tempScenarios_TagsList.add(file.name());
                        }
                    }
                }

                for(int i = 0; i < SteamManager.itemsInstalledSize; ++i) {
                    files = Gdx.files.absolute(((SteamUGC.ItemInstallInfo)SteamManager.itemsInstalled.get(i)).getFolder() + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/").list();

                    for(FileHandle file : files) {
                        if (!tempScenarios_TagsList.contains(file.name())) {
                            tempScenarios_TagsList.add(file.name());
                        }
                    }
                }

                int i = listBegin;

                for(int iSize = tempScenarios_TagsList.size(); i < iSize; ++i) {
                    try {
                        Json json = new Json();
                        FileHandle file2 = null;

                        for(int a = 0; a < SteamManager.modsFoldersSize; ++a) {
                            if (FileManager.IS_MAC) {
                                if (Gdx.files.external((String)SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)tempScenarios_TagsList.get(i) + "/" + "Details.json").exists()) {
                                    file2 = Gdx.files.external((String)SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)tempScenarios_TagsList.get(i) + "/" + "Details.json");
                                    break;
                                }
                            } else if (Gdx.files.internal((String)SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)tempScenarios_TagsList.get(i) + "/" + "Details.json").exists()) {
                                CFG.LOG("" + (String)SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)tempScenarios_TagsList.get(i) + "/" + "Details.json");
                                file2 = Gdx.files.internal((String)SteamManager.modsFolders.get(a) + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)tempScenarios_TagsList.get(i) + "/" + "Details.json");
                                break;
                            }
                        }

                        if (file2 == null) {
                            for(int a = 0; a < SteamManager.itemsInstalledSize; ++a) {
                                if (Gdx.files.absolute(((SteamUGC.ItemInstallInfo)SteamManager.itemsInstalled.get(a)).getFolder() + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)tempScenarios_TagsList.get(i) + "/" + "Details.json").exists()) {
                                    file2 = Gdx.files.absolute(((SteamUGC.ItemInstallInfo)SteamManager.itemsInstalled.get(a)).getFolder() + "/" + "map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)tempScenarios_TagsList.get(i) + "/" + "Details.json");
                                    break;
                                }
                            }
                        }

                        try {
                            if (file2 == null) {
                                file2 = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)tempScenarios_TagsList.get(i) + "/" + "Details.json");
                            }
                        } catch (Exception ex) {
                            CFG.exceptionStack(ex);
                        }

                        if (file2 != null && file2.exists()) {
                            tempDetails.add((Details)json.fromJson(Details.class, file2));
                        }
                    } catch (Exception ex) {
                        tempScenarios_TagsList.remove(i--);
                        iSize = tempScenarios_TagsList.size();
                        CFG.exceptionStack(ex);
                    }
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        } else {
            FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "Scenarios.txt");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            int i = 0;

            for(int iSize = tagsSPLITED.length; i < iSize; ++i) {
                try {
                    Json json = new Json();
                    FileHandle file = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + tagsSPLITED[i] + "/" + "Details.json");
                    tempScenarios_TagsList.add(tagsSPLITED[i]);
                    tempDetails.add((Details)json.fromJson(Details.class, file));
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        }

        while(tempScenarios_TagsList.size() > 0) {
            int nAdd = 0;

            for(int i = 1; i < tempScenarios_TagsList.size(); ++i) {
                if (((Details)tempDetails.get(nAdd)).Year < ((Details)tempDetails.get(i)).Year) {
                    nAdd = i;
                }
            }

            this.lScenarios_TagsList.add((String)tempScenarios_TagsList.get(nAdd));
            tempScenarios_TagsList.remove(nAdd);
            this.details.add((Details)tempDetails.get(nAdd));
            tempDetails.remove(nAdd);
        }

        this.SCENARIOS_SIZE = this.lScenarios_TagsList.size();
        if (updateDefaultScenario) {
            Game.updateDaultScenarioID();
        }

        this.loadPreviews();
        tempScenarios_TagsList.clear();
        tempDetails.clear();
    }

    public final void clearScenarios() {
        this.lScenarios_TagsList.clear();
        this.details.clear();
        this.SCENARIOS_SIZE = 0;
    }

    public final void loadPreviews() {
        if (this.lScenarios_Preview.size() > 0) {
            for(int i = 0; i < this.lScenarios_Preview.size(); ++i) {
                ((Image)this.lScenarios_Preview.get(i)).dispose();
                this.lScenarios_Preview.set(i, null);
            }

            this.lScenarios_Preview.clear();
        }

        for(int i = 0; i < this.lScenarios_TagsList.size(); ++i) {
            try {
                if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(i) + "/" + "previewSpecial.png").exists()) {
                    this.lScenarios_Preview.add(new Image(ImageManager.loadTexture_RGB888("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(i) + "/" + "previewSpecial.png"), TextureFilter.Linear, TextureWrap.ClampToEdge));
                } else {
                    this.lScenarios_Preview.add(new Image(ImageManager.loadTexture_RGB888("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(i) + "/" + "preview.png"), TextureFilter.Linear, TextureWrap.ClampToEdge));
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

    }

    public final void saveScenario_1() {
        Details nDetails = new Details();
        nDetails.Name = ScenarioSettings.sName;
        nDetails.Author = ScenarioSettings.sAuthor;
        nDetails.Day = Game_Calendar.currentDay;
        nDetails.Month = Game_Calendar.currentMonth;
        nDetails.Year = Game_Calendar.currentYear;
        nDetails.Civs = Game.getCivsSize() - 1;
        nDetails.Age = Game.gameAges.getAgeOfYear(Game_Calendar.currentYear);
        nDetails.Campaign = this.scenarioEditor_isCampaign;
        nDetails.CivDefault_Gold = scenarioEditorDetails.CivDefault_Gold;
        nDetails.CivDefault_GoldRandom = scenarioEditorDetails.CivDefault_GoldRandom;
        nDetails.CivDefault_Legacy = scenarioEditorDetails.CivDefault_Legacy;
        nDetails.CivDefault_LegacyRandom = scenarioEditorDetails.CivDefault_LegacyRandom;
        nDetails.CivDefault_ManpowerPercentage = scenarioEditorDetails.CivDefault_ManpowerPercentage;
        nDetails.CivDefault_Technology = scenarioEditorDetails.CivDefault_Technology;
        nDetails.HoursPerTurn = scenarioEditorDetails.HoursPerTurn;
        nDetails.ProvinceDefault_Population = scenarioEditorDetails.ProvinceDefault_Population;
        nDetails.ProvinceDefault_Economy = scenarioEditorDetails.ProvinceDefault_Economy;
        nDetails.ProvinceDefault_TaxEfficiency = scenarioEditorDetails.ProvinceDefault_TaxEfficiency;
        nDetails.ProvinceDefault_Manpower = scenarioEditorDetails.ProvinceDefault_Manpower;
        Json json = SaveManager.getJson();
        FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Details.json");
        file.writeString(json.prettyPrint(nDetails), false);
    }

    public final void saveScenario_2() {
    }

    public final void saveScenario_3() {
        SaveManager.saveScenarioDetails();
    }

    public final void saveScenario_4() {
    }

    public final void saveScenario_5() {
        SaveManager.saveScenarioDetailsProvinces();
    }

    public final void saveScenario_6() {
        this.saveWasteland();
    }

    public final void saveScenario_7() {
    }

    public final void saveScenario_8() {
        SaveManager.saveScenarioArmies();
    }

    public final void saveScenario_9() {
    }

    public final void saveScenario_10() {
        SaveManager.saveScenarioBuildings();
    }

    public final void saveScenario_11() {
        SaveManager.saveScenariosList();
    }

    public final void saveScenario_12() {
        SaveManager.saveScenarioAlliances();
    }

    public final void saveScenario_13() {
        SaveManager.saveScenarioRelations();
    }

    public final void saveScenario_14() {
    }

    public final void saveScenario_15() {
        SaveManager.saveScenarioGuarantee();
    }

    public final void saveScenario_16() {
        SaveManager.saveScenarioDefensive();
    }

    public final void saveScenario_17() {
    }

    public final void saveScenario_18() {
        SaveManager.saveScenarioMilitaryAccess();
    }

    public final void saveScenario_19() {
        SaveManager.saveScenarioNonAggression();
    }

    public final void saveScenario_20() {
        SaveManager.saveScenarioTruces();
    }

    public final void saveScenario_21() {
    }

    public final void saveScenario_22() {
        SaveManager.saveScenarioAlliancesSpecial();
    }

    public final void saveScenario_23() {
        SaveManager.saveScenarioCores();
    }

    public final void saveScenario_24() {
    }

    public final void saveScenario_25() {
        SaveManager.saveScenarioReligion();
        Game.mapScenarios.editorProvinceReligion.clear();
    }

    public final void saveWasteland() {
        FileHandle fileWrite = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + ScenarioSettings.sTag + "/" + "Wasteland.txt");
        fileWrite.writeString("", false);

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getWasteland() >= 0) {
                fileWrite.writeString(i + ";", true);
            }
        }

    }

    public final void loadWasteland() {
        try {
            if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Wasteland.txt").exists()) {
                FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Wasteland.txt");
                String tempT = tempFileT.readString();
                if (tempT.length() > 0) {
                    String[] tagsSPLITED = tempT.split(";");
                    int i = 0;

                    for(int iSize = tagsSPLITED.length; i < iSize; ++i) {
                        Game.getProvince(Integer.parseInt(tagsSPLITED[i])).setWasteland(0);
                    }

                    Object var7 = null;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final List<Civilization> loadCivilizations(boolean nEditor) {
        List<Civilization> lCivs = new ArrayList();
        lCivs.add(Game.getNeutralCivilization());
        ((Civilization)lCivs.get(0)).iCivID = 0;

        try {
            FileHandle fileList;
            if (Game.settingsManager.LANGUAGE_TAG != null && Game.settingsManager.LANGUAGE_TAG.length() != 0 && FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Data_" + Game.settingsManager.LANGUAGE_TAG + ".json").exists()) {
                fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Data_" + Game.settingsManager.LANGUAGE_TAG + ".json");
            } else {
                fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Data.json");
            }

            Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList)json.fromJson(ArrayList.class, fileList);
            int tCivID = 1;

            for(JsonValue jValue : tempArrayData) {
                ScenarioCivData tempData = (ScenarioCivData)json.readValue(ScenarioCivData.class, jValue);
                Game.LoadCivilizationData civData = Game.loadCivilization(tempData.CivTAG);
                lCivs.add(new Civilization(tCivID, tempData.CivTAG, tempData.PCID, civData.iR, civData.iG, civData.iB, tempData.CPID, civData.ReligionID, civData.GroupID, true));
                ((Civilization)lCivs.get(tCivID)).scenarioEditorData = tempData;
                ++tCivID;
                tempData = null;
            }

            tempArrayData.clear();
            Object var13 = null;
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        try {
            this.sActiveScenarioTag = (String)this.lScenarios_TagsList.get(Game.scenarioID);
        } catch (Exception var11) {
            this.sActiveScenarioTag = "";
        }

        return lCivs;
    }

    public final void loadCivilizationsProvinces() {
        try {
            FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "DataProvinces.json");
            Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList)json.fromJson(ArrayList.class, fileList);
            int tCivID = 1;

            for(JsonValue jValue : tempArrayData) {
                ScenarioCivData_Provinces tempData = (ScenarioCivData_Provinces)json.readValue(ScenarioCivData_Provinces.class, jValue);
                int i = 0;

                for(int iSize = tempData.Provinces.length; i < iSize; ++i) {
                    Game.getProvince(tempData.Provinces[i]).setCivID_LoadScenario(tCivID);
                }

                ++tCivID;
            }

            tempArrayData.clear();
            Object var11 = null;
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void loadCores() {
        try {
            if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Cores.json").exists()) {
                FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Cores.json");
                Json json = new Json();
                ArrayList<JsonValue> tempArrayData = (ArrayList)json.fromJson(ArrayList.class, fileList);

                for(JsonValue jValue : tempArrayData) {
                    ScenarioCoresData tempData = (ScenarioCoresData)json.readValue(ScenarioCoresData.class, jValue);
                    if (tempData.id >= 0 && tempData.id < Game.getProvincesSize()) {
                        Game.getProvince(tempData.id).clearCores();

                        for(int i = 0; i < tempData.Cores.size(); ++i) {
                            if ((Integer)tempData.Cores.get(i) > 0 && (Integer)tempData.Cores.get(i) < Game.getCivsSize()) {
                                Game.getProvince(tempData.id).addCore_Just((Integer)tempData.Cores.get(i));
                            }
                        }
                    }
                }

                tempArrayData.clear();
                Object var9 = null;
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void loadReligions_JustBuild(boolean editorMode) {
        if (editorMode) {
            this.editorProvinceReligion.clear();

            for(int i = 0; i < Game.getProvincesSize(); ++i) {
                this.editorProvinceReligion.add(-1);
            }
        }

    }

    public final void loadReligions(boolean editorMode) {
        try {
            this.loadReligions_JustBuild(editorMode);
            FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Religions.json");
            Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList)json.fromJson(ArrayList.class, fileList);

            for(JsonValue jValue : tempArrayData) {
                ScenarioReligionData tempData = (ScenarioReligionData)json.readValue(ScenarioReligionData.class, jValue);
                if (tempData.id >= 0 && tempData.id < Game.getProvincesSize()) {
                    Game.getProvince(tempData.id).setReligion(tempData.rel);
                    if (!editorMode && Game.getProvince(tempData.id).isCapital && Game.getProvince(tempData.id).getCivID() > 0 && Game.getCiv(Game.getProvince(tempData.id).getCivID()).getCapitalProvinceID() == tempData.id) {
                        Game.getCiv(Game.getProvince(tempData.id).getCivID()).setReligionID_UpdateBonuses(tempData.rel);
                    }

                    if (editorMode) {
                        this.editorProvinceReligion.set(tempData.id, tempData.rel);
                    }
                }
            }

            tempArrayData.clear();
            Object var9 = null;
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void loadScenarioCharacters(boolean nEditor) {
        if (!nEditor) {
            try {
                if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Characters.json").exists()) {
                    FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Characters.json");
                    Json json = new Json();
                    ArrayList<JsonValue> tempArrayData = (ArrayList)json.fromJson(ArrayList.class, fileList);

                    for(JsonValue jValue : tempArrayData) {
                        try {
                            CharactersManager.ScenarioCharacters tData = (CharactersManager.ScenarioCharacters)json.readValue(CharactersManager.ScenarioCharacters.class, jValue);
                            int civID = Game.getCivID(tData.CivTAG);
                            if (civID > 0) {
                                if (tData.Administrative != null && tData.Administrative.length() > 0) {
                                    CharactersManager.loadAdvisor(civID, tData.Administrative, 0);
                                }

                                if (tData.Economic != null && tData.Economic.length() > 0) {
                                    CharactersManager.loadAdvisor(civID, tData.Economic, 1);
                                }

                                if (tData.Innovation != null && tData.Innovation.length() > 0) {
                                    CharactersManager.loadAdvisor(civID, tData.Innovation, 2);
                                }

                                if (tData.Military != null && tData.Military.length() > 0) {
                                    CharactersManager.loadAdvisor(civID, tData.Military, 3);
                                }

                                if (tData.Generals != null && tData.Generals.length > 0) {
                                    for(int i = tData.Generals.length - 1; i >= 0; --i) {
                                        if (tData.Generals[i].length() > 0) {
                                            CharactersManager.loadGeneral(civID, tData.Generals[i], -99, -99);
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }

                    tempArrayData.clear();
                    Object var12 = null;
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

    }

    public final void createScenario() {
        Game.disposeCivilizations();
        Game.clearAllianceSpecial();
        scenarioEditorDetails = new Details();
        Game_Calendar.TURN_ID = 1;
        Game_Calendar.TURN_ID_HOST = 1;
        SaveGameManager.AUTO_SAVE_LAST_TURN_ID = Game_Calendar.TURN_ID;
        Game_Calendar.HOUR = 0;

        try {
            Game_Calendar.currentDay = ((Details)this.details.get(Game.scenarioID)).Day;
            Game_Calendar.currentMonth = ((Details)this.details.get(Game.scenarioID)).Month;
            Game_Calendar.currentYear = ((Details)this.details.get(Game.scenarioID)).Year;
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        Game_Calendar.CURRENT_AGEID = Game.gameAges.getAgeOfYear(Game_Calendar.currentYear);
        Game_Calendar.updateManpowerImg();
        scenarioEditorDetails = new Details();
        ScenarioSettings.sAuthor = "";
        ScenarioSettings.sTag = "";
        ScenarioSettings.sName = "";

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).clearData();
        }

        WondersManager.initProvinceWonders();
        Game.lCivs.add(Game.getNeutralCivilization());
        ((Civilization)Game.lCivs.get(0)).iCivID = 0;
        Game.iCivsSize = Game.lCivs.size();

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            for(int j = 0; j < Game.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
                ((ProvinceBorder)Game.getProvince(i).getProvinceBordersLandByLand().get(j)).setIsCivilizationBorder(Game.getProvince(i).getCivID() != Game.getProvince(((ProvinceBorder)Game.getProvince(i).getProvinceBordersLandByLand().get(j)).getWithProvinceID()).getCivID(), i);
            }

            if (Game.getProvince(i).getCivID() > 0) {
                Game.getCiv(Game.getProvince(i).getCivID()).addProvince_Just(i);
            }
        }

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.updateProvinceBorder(i);
        }

        Game.player = new Player();
        M_GameState.initChannelsTurns();
    }

    public final void clearData() {
        SaveGameManager.AUTO_SAVE_LAST_TURN_ID = 0;
        this.loadScenario_1_ClearData();
        ResourcesManager.resetPriceChangePerc();
        Game.player = new Player();
        Game.player.fog.initFogOfWar();
        Game.battleManager.clearData();
        SiegeManager.clearData();
        Game.revolutionManager.clearData();
        Game.gameThread.clearData();
        Game.gameThreadTurns.clearData();
        Game.gameThreadTurns.iLastUpdateTurnID = 0;
        Game.gameThreadTurns.THREAD_TURN_ID = 0;
        Game.gameThreadUpdate.clearData();
        WarManager.clearData();
        AI_Manager.aiBudget = new AI_Budget();
        Game.aiAggressivnes = 0;
    }

    public final void loadScenario_1_ClearData() {
        Game.disposeCivilizations();
        Game.clearAllianceSpecial();
        Game_Calendar.TURN_ID = 1;
        Game_Calendar.TURN_ID_HOST = 1;
        Game_Calendar.HOUR = 0;
        Game_Calendar.currentDay = ((Details)this.details.get(Game.scenarioID)).Day;
        Game_Calendar.currentMonth = ((Details)this.details.get(Game.scenarioID)).Month;
        Game_Calendar.currentYear = ((Details)this.details.get(Game.scenarioID)).Year;
        Game_Calendar.CURRENT_AGEID = Game.gameAges.getAgeOfYear(Game_Calendar.currentYear);
        M_GameState.initChannelsTurns();
    }

    public final void loadScenario_1() {
        MainMenu.canContinue = false;
        this.loadScenario_1_ClearData();
        Game_Calendar.updateManpowerImg();
        Game_Calendar.updateAge(false);
        this.scenarioEditor_isCampaign = ((Details)this.details.get(Game.scenarioID)).Campaign;
        scenarioEditorDetails = new Details();
        scenarioEditorDetails.CivDefault_Gold = ((Details)this.details.get(Game.scenarioID)).CivDefault_Gold;
        scenarioEditorDetails.CivDefault_GoldRandom = ((Details)this.details.get(Game.scenarioID)).CivDefault_GoldRandom;
        scenarioEditorDetails.CivDefault_Legacy = ((Details)this.details.get(Game.scenarioID)).CivDefault_Legacy;
        scenarioEditorDetails.CivDefault_LegacyRandom = ((Details)this.details.get(Game.scenarioID)).CivDefault_LegacyRandom;
        scenarioEditorDetails.CivDefault_ManpowerPercentage = ((Details)this.details.get(Game.scenarioID)).CivDefault_ManpowerPercentage;
        scenarioEditorDetails.CivDefault_Technology = ((Details)this.details.get(Game.scenarioID)).CivDefault_Technology;
        scenarioEditorDetails.HoursPerTurn = ((Details)this.details.get(Game.scenarioID)).HoursPerTurn;
        scenarioEditorDetails.ProvinceDefault_Population = ((Details)this.details.get(Game.scenarioID)).ProvinceDefault_Population;
        scenarioEditorDetails.ProvinceDefault_Economy = ((Details)this.details.get(Game.scenarioID)).ProvinceDefault_Economy;
        scenarioEditorDetails.ProvinceDefault_TaxEfficiency = ((Details)this.details.get(Game.scenarioID)).ProvinceDefault_TaxEfficiency;
        scenarioEditorDetails.ProvinceDefault_Manpower = ((Details)this.details.get(Game.scenarioID)).ProvinceDefault_Manpower;
        Game.SCENARIO_EVENTS = true;
        Game.HOURS_PER_TURN = ((Details) this.details.get(Game.scenarioID)).HoursPerTurn;
    }

    public final void loadScenario_2() {
        this.loadWasteland();
    }

    public final void loadScenario_3(boolean nEditor) {
        Game.lCivs = this.loadCivilizations(nEditor);
        Game.iCivsSize = Game.lCivs.size();
        if (nEditor) {
            for(int i = 0; i < Game.getProvincesSize(); ++i) {
                Game.getProvince(i).fogDrawArmy = true;
            }
        }

    }

    public final void loadScenario_3_A() {
        for(int i = 1; i < Game.iCivsSize; ++i) {
            Game.getCiv(i).loadScenario_A();
        }

    }

    public final void loadScenario_3_B() {
        for(int i = 1; i < Game.iCivsSize; ++i) {
            Game.getCiv(i).loadScenario_B();
        }

    }

    public final void loadScenario_3_C() {
        this.loadCivilizationsProvinces();
    }

    public final void loadScenario_4() {
        for(int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).buildTechTree();
        }

    }

    public final void loadScenario_5() {
        Game.buildProvinceIsCapital();
    }

    public final void loadScenario_6() {
        Game.initProvinceData();
    }

    public final void loadScenario_7() {
        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            for(int j = 0; j < Game.getProvince(i).getProvinceBordersLandByLandSize(); ++j) {
                ((ProvinceBorder)Game.getProvince(i).getProvinceBordersLandByLand().get(j)).setIsCivilizationBorder(Game.getProvince(i).getCivID() != Game.getProvince(((ProvinceBorder)Game.getProvince(i).getProvinceBordersLandByLand().get(j)).getWithProvinceID()).getCivID(), i);
            }

            if (Game.getProvince(i).getCivID() > 0) {
                Game.getCiv(Game.getProvince(i).getCivID()).addProvince_LoadScenario(i);
            }
        }

        for(int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateNumOfProvinces();
        }

    }

    public final void loadScenario_8() {
        for(int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).diplomacy = new Diplomacy();
            Game.getCiv(i).fDiplomacy = GameValues.diplomacy.STARTING_DIPLOMACY_POINTS;
        }

    }

    public final void loadScenario_9() {
        Game.buildWastelandLevels();
    }

    public final void loadScenario_10() {
        RulersManager.loadRulers(1, (int)Math.floor((double)((float)Game.getCivsSize() / 2.0F)));
    }

    public final void loadScenario_11() {
        RulersManager.loadRulers((int)Math.floor((double)((float)Game.getCivsSize() / 2.0F)), Game.getCivsSize());
    }

    public final void loadScenario_12() {
        Game.addSimpleTask(new Game.SimpleTask("buildCivilizationsRegions") {
            public void update() {
                CivilizationRegionsManager.buildCivilizationsRegions();
            }
        });
        AdvantagesManager.initRandomAdvantages();
        AdvantagesManager.initAdvantagePoints();
    }

    public final void loadScenario_13() {
        this.buildProvincesReligion();
    }

    public final void loadScenario_14() {
        this.buildProvincesCores();
    }

    public final void loadScenario_15() {
        LoadManager.loadScenarioArmies();
    }

    public final void loadScenario_16() {
        this.buildProvincesEconomy();
    }

    public final void loadScenario_17() {
        this.buildProvincesTaxEfficiency();
    }

    public final void loadScenario_18() {
        this.buildProvincesManpower();
    }

    public final void loadScenario_19() {
        this.buildCivsGovernmentBuildings();
    }

    public final void loadScenario_20() {
        LoadManager.loadScenarioBuildings();
    }

    public final void loadScenario_21() {
        buildProvinceData();
    }

    public final void loadScenario_22() {
        buildCivData();
    }

    public final void loadScenario_23() {
        this.loadCores();
    }

    public final void loadScenario_24() {
        this.buildProvincesPopulation();
    }

    public final void loadScenario_25(boolean nEditor) {
        this.loadReligions(nEditor);
    }

    public final void loadScenario_26() {
        this.buildCivsGold();
    }

    public final void loadScenario_27() {
        this.buildCivsManpower();
    }

    public final void loadScenario_28() {
        ResourcesManager.updateWorldResourcesProduced(true);
    }

    public final void loadScenario_29() {
        ResourcesManager.initUniqueCivsGoods();
        Game.buildDistanceToCapital();
    }

    public final void loadScenario_30() {
        LoadManager.loadScenarioRelations();
    }

    public final void loadScenario_31() {
        LoadManager.loadScenarioMilitaryAccess();
    }

    public final void loadScenario_32() {
        LoadManager.loadScenarioAlliances();
    }

    public final void loadScenario_33() {
        LoadManager.loadScenarioDefensive();
    }

    public final void loadScenario_34() {
        LoadManager.loadScenarioTruces();
    }

    public final void loadScenario_35() {
        LoadManager.loadScenarioNonAggression();
    }

    public final void loadScenario_36(boolean nEditor) {
        if (!nEditor) {
            buildStartingRelationsRandom();
        }

    }

    public final void loadScenario_37() {
        LoadManager.loadScenarioGuarantee();
    }

    public final void loadScenario_38(boolean editorMode) {
        this.loadScenarioCharacters(editorMode);
    }

    public final void loadScenario_39(boolean editorMode) {
    }

    public final void loadScenario_40(boolean editorMode) {
        if (!editorMode) {
            this.buildStartingAdvisors(1, (int)Math.floor((double)((float)Game.getCivsSize() / 2.0F)));
        }

    }

    public final void loadScenario_41(boolean editorMode) {
        if (!editorMode) {
            this.buildStartingAdvisors((int)Math.floor((double)((float)Game.getCivsSize() / 2.0F)), Game.getCivsSize());
        }

    }

    public final void loadScenario_42() {
        LoadManager.loadScenarioAlliancesSpecial();
        Game.loadAlliancesSpecial_Images();
    }

    public final void loadScenario_43() {
        buildAlliancesSpecial();
    }

    public final void loadScenario_44() {
        buildManpower();
    }

    public final void loadScenario_45() {
        CivilizationRanking.buildCivilizationRanking();
    }

    public final void loadScenario_46() {
    }

    public final void loadScenario_47() {
        Game.mapCities.updateCities();
    }

    public final void loadScenario_48() {
        Game.addSimpleTask(new Game.SimpleTask("loadScenario_48") {
            public void update() {
                for(int i = 1; i < Game.getCivsSize(); ++i) {
                    Game.mapCities.updateNameToNewTrueOwner_Civ(i, false);
                }

            }
        });
    }

    public final void loadScenario_49(boolean editorMode) {
        if (!editorMode) {
            this.buildStartingGenerals(1, (int)Math.floor((double)((float)Game.getCivsSize() / 2.0F)));
        }

    }

    public final void loadScenario_50(boolean editorMode) {
        if (!editorMode) {
            this.buildStartingGenerals((int)Math.floor((double)((float)Game.getCivsSize() / 2.0F)), Game.getCivsSize());
        }

    }

    public final void loadScenario_51(boolean editorMode) {
        if (!editorMode) {
            this.buildStartingArmy();
        }

    }

    public final void loadScenario_52() {
        buildArmyPosition();
    }

    public final void loadScenario_53() {
        buildCivsStability();
    }

    public final void loadScenario_54() {
        this.buildCivsLegacy_Nukes_Aggressiveness();
    }

    public final void loadScenario_55() {
        buildIncome();
    }

    public final void loadScenario_56() {
        this.initCivilizations_ConvertReligion();
        this.initCivilizations_NonCore();
    }

    public final void loadScenario_57() {
        this.initCivilizations_ArmiesWithoutGenerals();
    }

    public final void loadScenario_58() {
        updateUQ_UI();
        buildCivsColors();
    }

    public final void loadScenario_59() {
        EventsManager.clearEventsScenario();
        EventsManager.loadEvents_Scenario();
    }

    public final void loadScenario_60() {
        this.buildCivsNeighbors();
    }

    public final void loadScenario_61() {
    }

    public final void loadScenario_62(boolean nEditor) {
        if (!nEditor) {
            FormableCivManager.buildFormableCivilizations();
            Game.player.loadFormableCivs();
            InGame_Court_Government.reloadFlags = true;
        }

    }

    public final void loadScenario_63() {
    }

    public final void loadScenario_64() {
        MissionTree.loadMissions_Civs();
    }

    public static final void buildCivsColors() {
        for(int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateColorMap();
        }

    }

    public static final void updateUQ_UI() {
        if (((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).UQ_UI) {
            Images.flagBG = Images.flagBGUQ;
            Images.topStats = Images.topStatsUQ;
            Images.topStats2 = Images.topStats2UQ;
            Images.leftSideBar = Images.leftSideBarUQ;
            InGame.outlinerExtraX = InGame.outlinerExtraUQ;
        } else {
            Images.flagBG = Images.flagBGClassic;
            Images.topStats = Images.topStatsClassic;
            Images.topStats2 = Images.topStats2Classic;
            Images.leftSideBar = Images.leftSideBarClassic;
            InGame.outlinerExtraX = InGame.outlinerExtraClassic;
        }

    }

    public final void buildCivsNeighbors() {
        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).civNeighbors.buildNeighbors(i);
        }

    }

    public final void initCivilizations_ConvertReligion() {
        for(int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).convertReligion.buildProvincesConvertReligion(i);
        }

    }

    public final void initCivilizations_NonCore() {
        for(int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).civilizationCores.buildProvincesNonCore(i);
        }

    }

    public final void initCivilizations_ArmiesWithoutGenerals() {
        for(int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).armiesWithoutGenerals.buildArmiesWithoutGenerals(i);
        }

    }

    public final void buildProvincesCores() {
        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCivID() > 0) {
                Game.getProvince(i).addCore(Game.getProvince(i).getCivID());
            }
        }

    }

    public final void buildProvincesReligion() {
        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCivID() > 0) {
                Game.getProvince(i).setReligion_LoadScenario(Game.getCiv(Game.getProvince(i).getCivID()).getReligionID());
            }
        }

    }

    public final void buildProvincesPopulation() {
        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).scenarioEditorData.Population != 0) {
                Game.getCiv(i).fDiplomacyMax = 0.0F;

                for(int j = 0; j < Game.getCiv(i).getNumOfProvinces(); ++j) {
                    Civilization var10000 = Game.getCiv(i);
                    var10000.fDiplomacyMax += Math.max(1.0F, Game.getProvince(Game.getCiv(i).getProvinceID(j)).getGrowthRateWithBonuses() + (Game.getProvince(Game.getCiv(i).getProvinceID(j)).isCapital ? -GameValues.capital.CAPITAL_GROWTH_RATE : 0.0F) + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(Game.getCiv(i).getProvinceID(j)).getTerrainID())).BasePopulation);
                }
            }
        }

        try {
            for(int i = 0; i < Game.getProvincesSize(); ++i) {
                if (!Game.getProvince(i).getSeaProvince()) {
                    if (Game.getProvince(i).getCivID() == 0) {
                        Game.getProvince(i).setPopulationOfCivID(Game.getProvince(i).getCivID(), (int)(((float)((Details)this.details.get(Game.scenarioID)).ProvinceDefault_Population + (float)Game.oR.nextInt(((Details)this.details.get(Game.scenarioID)).ProvinceDefault_Population) / 100.0F) * (Math.max(1.0F, Game.getProvince(i).getGrowthRateWithBonuses() + (Game.getProvince(i).isCapital ? -GameValues.capital.CAPITAL_GROWTH_RATE : 0.0F) + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID())).BasePopulation) / 10000.0F)));
                    } else {
                        int population;
                        if (Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.Population == 0) {
                            population = (int)(((float)((Details)this.details.get(Game.scenarioID)).ProvinceDefault_Population + (float)Game.oR.nextInt(((Details)this.details.get(Game.scenarioID)).ProvinceDefault_Population) / 100.0F) * (Math.max(1.0F, Game.getProvince(i).getGrowthRateWithBonuses() + (Game.getProvince(i).isCapital ? -GameValues.capital.CAPITAL_GROWTH_RATE : 0.0F) + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID())).BasePopulation) / 100.0F));
                        } else {
                            population = (int)Math.max(1.0F, (float)Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.Population * (Math.max(1.0F, Game.getProvince(i).getGrowthRateWithBonuses() + (Game.getProvince(i).isCapital ? -GameValues.capital.CAPITAL_GROWTH_RATE : 0.0F) + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID())).BasePopulation) / Game.getCiv(Game.getProvince(i).getCivID()).fDiplomacyMax));
                        }

                        if (Game.getProvince(i).iCoresSize == 0) {
                            Game.getProvince(i).setPopulationOfCivID(Game.getProvince(i).getCivID(), population);
                        } else if (Game.getProvince(i).iCoresSize == 1) {
                            Game.getProvince(i).setPopulationOfCivID(Game.getProvince(i).getCore(0), population);
                        } else {
                            List<Integer> tPop = new ArrayList();

                            for(int a = 0; a < Game.getProvince(i).iCoresSize; ++a) {
                                tPop.add(GameValues.province.CORES_STARTING_POPULATION_MIN + Game.oR.nextInt(Math.max(1, GameValues.province.CORES_STARTING_POPULATION_RANDOM)));
                            }

                            int sum = 0;

                            for(int a = 0; a < Game.getProvince(i).iCoresSize; ++a) {
                                sum += (Integer)tPop.get(a);
                            }

                            for(int a = 0; a < Game.getProvince(i).iCoresSize; ++a) {
                                Game.getProvince(i).setPopulationOfCivID(Game.getProvince(i).getCore(a), (int)((float)population * ((float)(Integer)tPop.get(a) / (float)sum)));
                            }

                            tPop.clear();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateDiplomacyPerMonth();
        }

    }

    public final void buildProvincesEconomy() {
        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince()) {
                if (Game.getProvince(i).getCivID() == 0) {
                    Game.getProvince(i).setEconomy((float)((Details)this.details.get(Game.scenarioID)).ProvinceDefault_Economy * GameValues.economy.BASE_ECONOMY_NEUTRAL * (Math.max(0.0F, Game.getProvince(i).BaseDevelopment + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID())).BaseEconomy) / 100.0F));
                } else if (Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.Economy == 0) {
                    Game.getProvince(i).setEconomy((float)((Details)this.details.get(Game.scenarioID)).ProvinceDefault_Economy * (Math.max(0.0F, Game.getProvince(i).BaseDevelopment + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID())).BaseEconomy) / 100.0F));
                } else {
                    Game.getProvince(i).setEconomy((float)Math.max(1, Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.Economy) * (Math.max(0.0F, Game.getProvince(i).BaseDevelopment + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID())).BaseEconomy) / 100.0F));
                }
            }
        }

    }

    public final void buildProvincesTaxEfficiency() {
        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince()) {
                if (Game.getProvince(i).getCivID() == 0) {
                    Game.getProvince(i).setTaxEfficiency((float)((Details)this.details.get(Game.scenarioID)).ProvinceDefault_TaxEfficiency * GameValues.tax.BASE_TAX_EFFICIENCY_NEUTRAL * (Math.max(0.0F, Game.getProvince(i).BaseDevelopment + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID())).BaseEconomy) / 100.0F));
                } else if (Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.TaxEff == 0) {
                    Game.getProvince(i).setTaxEfficiency((float)((Details)this.details.get(Game.scenarioID)).ProvinceDefault_TaxEfficiency * (Math.max(0.0F, Game.getProvince(i).BaseDevelopment + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID())).BaseEconomy) / 100.0F));
                } else {
                    Game.getProvince(i).setTaxEfficiency((float)Math.max(1, Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.TaxEff) * (Math.max(0.0F, Game.getProvince(i).BaseDevelopment + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID())).BaseEconomy) / 100.0F));
                }
            }
        }

    }

    public final void buildProvincesManpower() {
        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince()) {
                if (Game.getProvince(i).getCivID() == 0) {
                    Game.getProvince(i).setManpower((float)((Details)this.details.get(Game.scenarioID)).ProvinceDefault_Manpower * GameValues.manpower.BASE_MANPOWER_NEUTRAL * (Math.max(0.0F, Game.getProvince(i).BaseDevelopment + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID())).BaseEconomy) / 100.0F));
                } else if (Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.Manpower == 0) {
                    Game.getProvince(i).setManpower((float)((Details)this.details.get(Game.scenarioID)).ProvinceDefault_Manpower * (Math.max(0.0F, Game.getProvince(i).BaseDevelopment + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID())).BaseEconomy) / 100.0F));
                } else {
                    Game.getProvince(i).setManpower((float)Math.max(1, Game.getCiv(Game.getProvince(i).getCivID()).scenarioEditorData.Manpower) * (Math.max(0.0F, Game.getProvince(i).BaseDevelopment + (float)((Terrain)Game.terrainManager.terrains.get(Game.getProvince(i).getTerrainID())).BaseEconomy) / 100.0F));
                }
            }
        }

    }

    public final void buildCivsGold() {
        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).scenarioEditorData.Gold != DEFAULT_VALUE) {
                Game.getCiv(i).fGold = (float)Game.getCiv(i).scenarioEditorData.Gold;
            } else {
                Game.getCiv(i).fGold = (float)(((Details)this.details.get(Game.scenarioID)).CivDefault_Gold + Game.oR.nextInt(Math.max(1, ((Details)this.details.get(Game.scenarioID)).CivDefault_GoldRandom)));
            }
        }

    }

    public final void buildCivsLegacy_Nukes_Aggressiveness() {
        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).scenarioEditorData.Legacy != DEFAULT_VALUE) {
                Game.getCiv(i).fLegacy = (float)Game.getCiv(i).scenarioEditorData.Legacy;
            } else {
                Game.getCiv(i).fLegacy = (float)(((Details)this.details.get(Game.scenarioID)).CivDefault_Legacy + Game.oR.nextInt(Math.max(1, ((Details)this.details.get(Game.scenarioID)).CivDefault_LegacyRandom)));
            }

            if (Game.getCiv(i).scenarioEditorData.Nukes > 0) {
                Game.getCiv(i).setNukes(Game.getCiv(i).scenarioEditorData.Nukes);
            }

            if (Game.getCiv(i).scenarioEditorData.v != 0) {
                Game.getCiv(i).setExtraAggressiveness(Game.getCiv(i).scenarioEditorData.v);
            }
        }

    }

    public final void buildCivsManpower() {
        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).fManpower = Math.max((double)0.0F, Game.getCiv(i).fManpowerMax * (double)((float)((Details)this.details.get(Game.scenarioID)).CivDefault_ManpowerPercentage / 100.0F));
        }

    }

    public final void buildCivsGovernmentBuildings() {
        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).setCapitalLevel(Math.max(0, Game.getCiv(i).scenarioEditorData.CCL));
            Game.getCiv(i).setMilitaryAcademyLevel(Math.max(0, Game.getCiv(i).scenarioEditorData.MAL));
            Game.getCiv(i).setMilitaryAcademyForGeneralsLevel(Math.max(0, Game.getCiv(i).scenarioEditorData.MAGL));
            Game.getCiv(i).setSupremeCourtLevel(Math.max(0, Game.getCiv(i).scenarioEditorData.SCL));
            Game.getCiv(i).setNuclearReactorLevel(Math.max(0, Game.getCiv(i).scenarioEditorData.NRL));
            Game.getCiv(i).buildCapitalCity_Bonuses();
            Game.getCiv(i).buildMilitaryAcademy_Bonuses();
            Game.getCiv(i).buildMilitaryAcademyForGenerals_Bonuses();
            Game.getCiv(i).buildSupremeCourt_Bonuses();
            Game.getCiv(i).buildNuclearReactor_Bonuses();
        }

    }

    public final void buildCivsGovernmentBuildings_LoadSavedGame() {
        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).buildCapitalCity_Bonuses();
            Game.getCiv(i).buildMilitaryAcademy_Bonuses();
            Game.getCiv(i).buildMilitaryAcademyForGenerals_Bonuses();
            Game.getCiv(i).buildNuclearReactor_Bonuses();
        }

    }

    public static final void buildArmyPosition() {
        for(int i = 0; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).clearArmyPosition();
        }

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            for(int j = 0; j < Game.getProvince(i).getArmySize(); ++j) {
                Game.getCiv(Game.getProvince(i).getArmy(j).civID).addArmyPosition(i, Game.getProvince(i).getArmy(j).key);
            }
        }

    }

    public static void buildProvinceData() {
        BonusesManager.initAndBuildProvinceBonuses();
        Game.initProvinceData();
    }

    public static void buildCivData_Load() {
        for(int i = 1; i < Game.getCivsSize(); ++i) {
            try {
                Game.ideologiesManager.updateCivBonuses(i, Game.getCiv(i).getIdeologyID(), 1, true);
                Game.religionManager.updateCivBonuses(i, Game.getCiv(i).getReligionID(), 1, true);
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

    }

    public static void buildCivData_Load2() {
        for(int i = 1; i < Game.getCivsSize(); ++i) {
            try {
                Game.getCiv(i).updateRegimentsLimit();
                Game.getCiv(i).updateResearchPerMonth();
                Game.getCiv(i).updateLegacyPerMonth();
                Game.getCiv(i).updateManpowerPerMonth();
                Game.getCiv(i).updateDiplomacyPerMonth();
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

    }

    public static void buildCivData() {
        for(int i = 1; i < Game.getCivsSize(); ++i) {
            try {
                Game.ideologiesManager.updateCivBonuses(i, Game.getCiv(i).getIdeologyID(), 1, true);
                Game.religionManager.updateCivBonuses(i, Game.getCiv(i).getReligionID(), 1, true);
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

        buildVassals();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            try {
                Game.getCiv(i).setTaxationLevel(1);
                Game.getCiv(i).setResearchLevel(1);
                Game.getCiv(i).setCorruption(GameValues.supremeCourt.CORRUPTION_BASE_VALUE);
                Game.getCiv(i).updateRegimentsLimit();
                Game.getCiv(i).updateResearchPerMonth();
                Game.getCiv(i).updateLegacyPerMonth();
                Game.getCiv(i).updateManpowerPerMonth();
                Game.getCiv(i).updateDiplomacyPerMonth();
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

    }

    public static final void buildManpower() {
        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateManpowerPerMonth();
        }

    }

    public static final void buildIncome() {
        Game.buildProsperity_AverageEconomy();

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateRegimentsLimit();
            Game.getCiv(i).updateArmyMaintenance();
        }

        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).updateProvinceIncome();
        }

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getPuppetOfCivID() != i) {
                Game.getCiv(i).updateIncome();
            }
        }

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getPuppetOfCivID() == i) {
                Game.getCiv(i).updateIncome();
            }
        }

        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateTotalIncomePerMonth();
        }

    }

    public static final void buildVassals() {
        try {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getPuppetOfCivID() != Game.getCiv(i).getCivID()) {
                    Game.getCiv(Game.getCiv(i).getPuppetOfCivID()).addVassal(Game.getCiv(i).getCivID());
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static final void buildAlliancesSpecial() {
        for(int i = 0; i < Game.alliancesSpecialSize; ++i) {
            try {
                Game.getCiv(((Alliance)Game.alliancesSpecial.get(i)).iLeaderCivID).addInAllianceSpecial(i);

                for(int j = ((Alliance)Game.alliancesSpecial.get(i)).firstTier.size() - 1; j >= 0; --j) {
                    Game.getCiv((Integer)((Alliance)Game.alliancesSpecial.get(i)).firstTier.get(j)).addInAllianceSpecial(i);
                }

                for(int j = ((Alliance)Game.alliancesSpecial.get(i)).secondTier.size() - 1; j >= 0; --j) {
                    Game.getCiv((Integer)((Alliance)Game.alliancesSpecial.get(i)).secondTier.get(j)).addInAllianceSpecial(i);
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

    }

    public static final void buildCivsStability() {
        for(int i = 1; i < Game.getCivsSize(); ++i) {
            Game.getCiv(i).updateCivStability();
        }

    }

    public static final void buildStartingRelationsRandom() {
        if (GameValues.diplomacy.STARTING_RANDOM_RELATIONS_CHANCE > 0) {
            List<Integer> lCivs = new ArrayList();

            for(int civID = 1; civID < Game.getCivsSize(); ++civID) {
                if (Game.getCiv(civID).getNumOfProvinces() > 0) {
                    for(int i = civID + 1; i < Game.getCivsSize(); ++i) {
                        if (Game.getCiv(civID).diplomacy.getRelation(i) == 0.0F && buildStartingRelationsRandom_IsInDistance(civID, i) && GameValues.diplomacy.STARTING_RANDOM_RELATIONS_CHANCE > Game.oR.nextInt(100)) {
                            lCivs.add(i);
                        }
                    }

                    for(int j = lCivs.size() - 1; j >= 0; --j) {
                        float relation = (float)Game.oR.nextInt(GameValues.diplomacy.STARTING_RANDOM_RELATIONS) - (float)GameValues.diplomacy.STARTING_RANDOM_RELATIONS / 2.0F;
                        relation += (float)(relation > 0.0F ? GameValues.diplomacy.STARTING_RANDOM_RELATIONS_MIN : -GameValues.diplomacy.STARTING_RANDOM_RELATIONS_MIN);
                        Game.getCiv(civID).diplomacy.setRelation(civID, (Integer)lCivs.get(j), relation);
                        Game.getCiv((Integer)lCivs.get(j)).diplomacy.setRelation((Integer)lCivs.get(j), civID, relation);
                    }

                    lCivs.clear();
                }
            }
        }

    }

    public static boolean buildStartingRelationsRandom_IsInDistance(int civID, int rivalID) {
        if (Game.getCiv(rivalID).getNumOfProvinces() <= 0) {
            return false;
        } else {
            return !(Game.getDistance_PercOfMax(Game.getCiv(civID).getCapitalProvinceID(), Game.getCiv(rivalID).getCapitalProvinceID()) > GameValues.diplomacy.RANDOM_RELATIONS_DISTANCE);
        }
    }

    public final void buildStartingAdvisors(int startID, int endID) {
        for(int i = startID; i < endID; ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                if (Game.getCiv(i).advisorAdministration.sName == null && Game.oR.nextInt(100) < GameValues.civRank.CIV_RANK_STARTING_ADVISOR_ADMINISTRATIVE_CHANCE[Game.getCiv(i).iCivRankID]) {
                    Game.getCiv(i).advisorAdministration = CivilizationAdvisorsPool.generateAdvisor_Random(i, 0);
                    AdvisorManager var10000 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(i).advisorAdministration, i, 1);
                }

                if (Game.getCiv(i).advisorEconomy.sName == null && Game.oR.nextInt(100) < GameValues.civRank.CIV_RANK_STARTING_ADVISOR_ECONOMY_CHANCE[Game.getCiv(i).iCivRankID]) {
                    Game.getCiv(i).advisorEconomy = CivilizationAdvisorsPool.generateAdvisor_Random(i, 1);
                    AdvisorManager var4 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(i).advisorEconomy, i, 1);
                }

                if (Game.getCiv(i).advisorTechnology.sName == null && Game.oR.nextInt(100) < GameValues.civRank.CIV_RANK_STARTING_ADVISOR_INNOVATION_CHANCE[Game.getCiv(i).iCivRankID]) {
                    Game.getCiv(i).advisorTechnology = CivilizationAdvisorsPool.generateAdvisor_Random(i, 2);
                    AdvisorManager var5 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(i).advisorTechnology, i, 1);
                }

                if (Game.getCiv(i).advisorMilitary.sName == null && Game.oR.nextInt(100) < GameValues.civRank.CIV_RANK_STARTING_ADVISOR_MILITARY_CHANCE[Game.getCiv(i).iCivRankID]) {
                    Game.getCiv(i).advisorMilitary = CivilizationAdvisorsPool.generateAdvisor_Random(i, 3);
                    AdvisorManager var6 = Game.advisorManager;
                    AdvisorManager.updateCivBonuses(Game.getCiv(i).advisorMilitary, i, 1);
                }
            }
        }

    }

    public final void buildStartingGenerals(int startID, int endID) {
        for(int i = startID; i < endID; ++i) {
            if (Game.getCiv(i).getNumOfProvinces() > 0) {
                for(int j = 0; j < GameValues.civRank.CIV_RANK_NUM_OF_STARTING_GENERALS[Game.getCiv(i).iCivRankID]; ++j) {
                    if (Game.oR.nextInt(100) < GameValues.civRank.CIV_RANK_STARTING_GENERAL_CHANCE[Game.getCiv(i).iCivRankID]) {
                        Game.getCiv(i).addGeneral(CivilizationGeneralsPool.getGeneral_Random(i));
                    }
                }
            }
        }

    }

    public final void buildStartingArmy() {
        try {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                this.buildStartingArmy(i);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void buildStartingArmy(int i) {
        if (Game.getCiv(i).getNumOfProvinces() > 0) {
            try {
                int units = (int)((float)(GameValues.civRank.CIV_RANK_STARTING_ARMY[Game.getCiv(i).iCivRankID] + (GameValues.civRank.CIV_RANK_STARTING_ARMY_RANDOM[Game.getCiv(i).iCivRankID] > 0 ? Game.oR.nextInt(GameValues.civRank.CIV_RANK_STARTING_ARMY_RANDOM[Game.getCiv(i).iCivRankID] + 1) : 0)) * Game.ideologiesManager.getIdeology(Game.getCiv(i).getIdeologyID()).STARTING_ARMY);
                if (Game.getCiv(i).getPuppetOfCivID() != i) {
                    units = (int)((float)units * GameValues.civRank.CIV_RANK_STARTING_ARMY_VASSAL);
                }

                if (units > 0) {
                    List<ArmyRegiment> nArmyRegiment = new ArrayList();
                    int firstLine = (int)Math.ceil((double)((float)units / 2.0F));
                    int secondLine = (int)Math.floor((double)((float)units / 2.0F));
                    if (Game.getCiv(i).unitsBest_SupportSize == 0) {
                        firstLine = units;
                        secondLine = 0;
                    }

                    if (Game.getCiv(i).unitsBest_FirstLineSize == 0 && Game.getCiv(i).unitsBest_FlankSize == 0) {
                        firstLine = 0;
                        secondLine = units;
                    }

                    int flankUnits = 0;
                    if (Game.getCiv(i).unitsBest_FlankSize > 0) {
                        if (Game.getCiv(i).unitsBest_FirstLineSize == 0) {
                            flankUnits = firstLine;
                            firstLine = 0;
                        } else {
                            flankUnits = (int)Math.floor((double)((float)firstLine / 2.0F));
                            firstLine = (int)Math.ceil((double)((float)firstLine / 2.0F));
                        }
                    }

                    if (Game.getCiv(i).unitsBest_FirstLineSize > 0) {
                        for(int a = 0; a < firstLine; ++a) {
                            int rand = Game.oR.nextInt(Game.getCiv(i).unitsBest_FirstLineSize);
                            nArmyRegiment.add(new ArmyRegiment(((TechnologyTree.Unit)Game.getCiv(i).unitsBest_FirstLine.get(rand)).unitID, ((TechnologyTree.Unit)Game.getCiv(i).unitsBest_FirstLine.get(rand)).armyID));
                        }
                    }

                    if (Game.getCiv(i).unitsBest_FlankSize > 0) {
                        for(int a = 0; a < flankUnits; ++a) {
                            int rand = Game.oR.nextInt(Game.getCiv(i).unitsBest_FlankSize);
                            nArmyRegiment.add(new ArmyRegiment(((TechnologyTree.Unit)Game.getCiv(i).unitsBest_Flank.get(rand)).unitID, ((TechnologyTree.Unit)Game.getCiv(i).unitsBest_Flank.get(rand)).armyID));
                        }
                    }

                    int siegeUnits = 0;
                    if (Game.getCiv(i).unitsBest_SiegeSize > 0) {
                        siegeUnits = (int)((float)secondLine * GameValues.civRank.STARTING_ARMY_SIEGE_UNITS_PERC[Game.getCiv(i).iCivRankID]);
                        secondLine -= siegeUnits;
                    }

                    if (Game.getCiv(i).unitsBest_SupportSize > 0) {
                        for(int a = 0; a < secondLine; ++a) {
                            int rand = Game.oR.nextInt(Game.getCiv(i).unitsBest_SupportSize);
                            nArmyRegiment.add(new ArmyRegiment(((TechnologyTree.Unit)Game.getCiv(i).unitsBest_Support.get(rand)).unitID, ((TechnologyTree.Unit)Game.getCiv(i).unitsBest_Support.get(rand)).armyID));
                        }
                    }

                    if (Game.getCiv(i).unitsBest_SiegeSize > 0) {
                        for(int a = 0; a < siegeUnits; ++a) {
                            int rand = Game.oR.nextInt(Game.getCiv(i).unitsBest_SiegeSize);
                            nArmyRegiment.add(new ArmyRegiment(((TechnologyTree.Unit)Game.getCiv(i).unitsBest_Siege.get(rand)).unitID, ((TechnologyTree.Unit)Game.getCiv(i).unitsBest_Siege.get(rand)).armyID));
                        }
                    }

                    int inProvinceID = Game.getCiv(i).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(i).getCapitalProvinceID()).getCivID() == i ? Game.getCiv(i).getCapitalProvinceID() : Game.getCiv(i).getProvinceID(Game.oR.nextInt(Game.getCiv(i).getNumOfProvinces()));
                    ArmyGeneral armyGeneral = CivilizationGeneralsPool.getGeneral_Random(i);
                    if (nArmyRegiment.size() > 0) {
                        Game.getProvince(inProvinceID).addArmy(new ArmyDivision(i, inProvinceID, nArmyRegiment, armyGeneral));
                    }
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

    }

    public static void loadRandomScenario() {
        int scenarioID = Game.oR.nextInt(Game.mapScenarios.SCENARIOS_SIZE);
        if (Game.scenarioID == scenarioID && Game_Calendar.TURN_ID == 1 && !Game.reloadScenario && !MainMenu.canContinue) {
            Game.mapScale.definedScale = MapScale.defScales.definedScale_Default;
            Game.mapScale.setCurrentScale(1.0F);
            NewGame.setRandomCiv();
            Game.menuManager.setViewIDWithoutAnimation(View.CLOUDS_MENU);
        } else {
            Game.scenarioID = scenarioID;
            Game.reloadScenario = false;
            Menu_LoadScenario.editorMode = false;
            Menu_LoadScenario.goToMenu = View.CLOUDS_MENU;
            Game.menuManager.setViewIDWithoutAnimation(View.LOAD_SCENARIO);
        }

        Game.menuManager.addToastGold(Game.lang.get(((Details)Game.mapScenarios.details.get(scenarioID)).Name), Images.dice);
    }

    public static class Details {
        public String Name;
        public String Author;
        public int Civs;
        public int Age;
        public int Day;
        public int Month;
        public int Year;
        public boolean Campaign = false;
        public int CivDefault_Gold = 100;
        public int CivDefault_GoldRandom = 50;
        public int CivDefault_Legacy = 100;
        public int CivDefault_LegacyRandom = 50;
        public int CivDefault_ManpowerPercentage = 60;
        public int CivDefault_Technology = -1;
        public int ProvinceDefault_Population = 200000;
        public int ProvinceDefault_Economy = 10;
        public int ProvinceDefault_TaxEfficiency = 10;
        public int ProvinceDefault_Manpower = 3;
        public int HoursPerTurn = 24;

        public Details() {
        }
    }

    public static class ScenarioCivData {
        public String CivTAG;
        public int CivID;
        public int PCID;
        public int CPID;
        public int Gold;
        public int Legacy;
        public int TechnologyID;
        public int Population;
        public int Economy;
        public int TaxEff;
        public int Manpower;
        public int CCL;
        public int MAL;
        public int MAGL;
        public int SCL;
        public int NRL;
        public int Nukes;
        public int v;

        public ScenarioCivData() {
            this.Gold = MapScenarios.DEFAULT_VALUE;
            this.Legacy = MapScenarios.DEFAULT_VALUE;
            this.TechnologyID = MapScenarios.DEFAULT_VALUE;
            this.Population = 0;
            this.Economy = 0;
            this.TaxEff = 0;
            this.Manpower = 0;
            this.CCL = 0;
            this.MAL = 0;
            this.MAGL = 0;
            this.SCL = 0;
            this.NRL = 0;
            this.Nukes = 0;
            this.v = 0;
        }
    }

    public static class ScenarioCivDataSerializer implements Json.Serializer<ScenarioCivData> {
        public ScenarioCivDataSerializer() {
        }

        public void write(Json json, ScenarioCivData data, Class knownType) {
            json.writeObjectStart();
            json.writeValue("CivTAG", data.CivTAG);
            json.writeValue("CivID", data.CivID);
            json.writeValue("PCID", data.PCID);
            json.writeValue("CPID", data.CPID);
            if (data.Gold != MapScenarios.DEFAULT_VALUE) {
                json.writeValue("Gold", data.Gold);
            }

            if (data.Legacy != MapScenarios.DEFAULT_VALUE) {
                json.writeValue("Legacy", data.Legacy);
            }

            if (data.TechnologyID != MapScenarios.DEFAULT_VALUE) {
                json.writeValue("TechnologyID", data.TechnologyID);
            }

            if (data.Population != 0) {
                json.writeValue("Population", data.Population);
            }

            if (data.Economy != 0) {
                json.writeValue("Economy", data.Economy);
            }

            if (data.TaxEff != 0) {
                json.writeValue("TaxEff", data.TaxEff);
            }

            if (data.Manpower != 0) {
                json.writeValue("Manpower", data.Manpower);
            }

            if (data.CCL != 0) {
                json.writeValue("CCL", data.CCL);
            }

            if (data.MAL != 0) {
                json.writeValue("MAL", data.MAL);
            }

            if (data.MAGL != 0) {
                json.writeValue("MAGL", data.MAGL);
            }

            if (data.SCL != 0) {
                json.writeValue("SCL", data.SCL);
            }

            if (data.NRL != 0) {
                json.writeValue("NRL", data.NRL);
            }

            if (data.Nukes != 0) {
                json.writeValue("Nukes", data.Nukes);
            }

            if (data.v != 0) {
                json.writeValue("v", data.v);
            }

            json.writeObjectEnd();
        }

        public ScenarioCivData read(Json json, JsonValue jsonData, Class type) {
            ScenarioCivData out = new ScenarioCivData();
            return out;
        }
    }

    public static class ScenarioCivData_Provinces {
        public int[] Provinces;

        public ScenarioCivData_Provinces() {
        }
    }

    public static class ScenarioCoresData {
        public int id;
        public List<Integer> Cores;

        public ScenarioCoresData() {
        }
    }

    public static class ScenarioReligionData {
        public int id;
        public int rel;

        public ScenarioReligionData() {
        }
    }
}
