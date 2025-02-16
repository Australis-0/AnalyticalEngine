//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map.plague;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

public class PlagueManager {
    public static List<Plague> activePlagues = new ArrayList();
    public static List<Data_Disease> lPlagues = new ArrayList();
    public static int iPlaguesSize = 0;
    public static List<Image> plagueImages = new ArrayList();
    public static List<Image> plagueImagesBig = new ArrayList();

    public PlagueManager() {
    }

    public static final void runPlagues() {
        try {
            for(int i = activePlagues.size() - 1; i >= 0; --i) {
                try {
                    ((Plague)activePlagues.get(i)).runDisease();
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }

            for(int i = activePlagues.size() - 1; i >= 0; --i) {
                try {
                    if (--((Plague)activePlagues.get(i)).iDurationTurnsLeft < 1 && ((Plague)activePlagues.get(i)).lProvinces_Active.size() == 0) {
                        for(int k = i + 1; k < activePlagues.size(); ++k) {
                            for(int o = 0; o < ((Plague)activePlagues.get(k)).lProvinces_Active.size(); ++o) {
                                if (Game.getProvince((Integer)((Plague)activePlagues.get(k)).lProvinces_Active.get(o)).provincePlague != null && Game.getProvince((Integer)((Plague)activePlagues.get(k)).lProvinces_Active.get(o)).provincePlague.id == ((Plague)activePlagues.get(k)).getPlagueID_InGame()) {
                                    --Game.getProvince((Integer)((Plague)activePlagues.get(k)).lProvinces_Active.get(o)).provincePlague.id;
                                }
                            }

                            ((Plague)activePlagues.get(k)).setPlagueID_InGame(((Plague)activePlagues.get(k)).getPlagueID_InGame() - 1);
                        }

                        activePlagues.remove(i);
                    }
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }

            for(int i = activePlagues.size() - 1; i >= 0; --i) {
                ((Plague)activePlagues.get(i)).spreadDisease();
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static final void startDisease() {
        int tRandScore = Game.oR.nextInt(GameValues.plagues.DISEASE_OUTBREAK_RANDOM);
        if ((float)tRandScore < (float)GameValues.plagues.DISEASE_OUTBREAK_RANDOM * Game.gameAges.getAge_DiseaseChance(Game_Calendar.CURRENT_AGEID)) {
            List<Integer> tempIDsToSpawn = new ArrayList();
            int tScoreTotal = 0;

            for(int i = 0; i < iPlaguesSize; ++i) {
                if (Game_Calendar.currentYear >= ((Data_Disease)lPlagues.get(i)).BeginningYear && Game_Calendar.currentYear <= ((Data_Disease)lPlagues.get(i)).EndYear) {
                    tempIDsToSpawn.add(i);
                    tScoreTotal = (int)((float)tScoreTotal + ((Data_Disease)lPlagues.get(i)).OUTBREAK_CHANCE * (float)GameValues.plagues.DISEASE_OUTBREAK_MODIFY);
                }
            }

            if (tempIDsToSpawn.size() > 0) {
                int spawnID = 0;
                if (tScoreTotal > 0) {
                    int i = tempIDsToSpawn.size() - 1;

                    for(int tCurrentScore = 0; i >= 0; --i) {
                        tCurrentScore += (int)(((Data_Disease)lPlagues.get((Integer)tempIDsToSpawn.get(i))).OUTBREAK_CHANCE * (float)GameValues.plagues.DISEASE_OUTBREAK_MODIFY);
                        tRandScore = Game.oR.nextInt(tScoreTotal);
                        if (tCurrentScore > tRandScore) {
                            spawnID = i;
                            break;
                        }
                    }
                } else {
                    spawnID = Game.oR.nextInt(tempIDsToSpawn.size());
                }

                startDisease((Integer)tempIDsToSpawn.get(spawnID));
            }
        }

    }

    //ANALYTICALENGINE START
    public static final void startDisease (int nID) {
        startDisease(nID, null);
    }

    public static final void startDisease (int arg0_disease_id, List<Integer> arg1_province_ids) {
        //Convert from parameters
        int nID = arg0_disease_id;
        List<Integer> province_ids = arg1_province_ids;

        try {
            int nOutbreakProvinces = ((Data_Disease)lPlagues.get(nID)).OUTBREAK_PROVINCES;
            if (((Data_Disease)lPlagues.get(nID)).OUTBREAK_PROVINCES_EXTRA > 0) {
                nOutbreakProvinces += Game.oR.nextInt(((Data_Disease)lPlagues.get(nID)).OUTBREAK_PROVINCES_EXTRA);
            }

            List<Integer> lPossibleProvinces = (province_ids != null) ?
                province_ids : new ArrayList();

            for(int i = 0; i < Game.getProvincesSize(); ++i) {
                if (Game.getProvince(i).getWasteland() < 0 && !Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCivID() > 0 && Game.getProvince(i).provincePlague == null && Game_Calendar.TURN_ID - Game.getProvinceData10(i).t > GameValues.plagues.PLAGUE_PAUSE_FOR_X_DAYS) {
                    lPossibleProvinces.add(i);
                }
            }

            if (lPossibleProvinces.size() > 0) {
                List<Integer> lSpreadPropositions = new ArrayList();
                int nToCheck = 8 + (int)(10.0F * Math.min(((Data_Disease)lPlagues.get(nID)).DEATH_RATE_MIN, 1.0F));

                while(lPossibleProvinces.size() > 0 && nToCheck-- > 0) {
                    int tRandID = Game.oR.nextInt(lPossibleProvinces.size());
                    lSpreadPropositions.add((Integer)lPossibleProvinces.get(tRandID));
                    lPossibleProvinces.remove(tRandID);
                }

                lPossibleProvinces.clear();
                if (lSpreadPropositions.size() > 0) {
                    List<Float> lSpreadPropositions_Score = new ArrayList();
                    int tMaxPopulation = 0;
                    float tMaxEconomy = 0.0F;
                    float tMaxInfrastructure = 0.0F;
                    float tMaxDevastation = 0.0F;

                    for(int i = lSpreadPropositions.size() - 1; i >= 0; --i) {
                        if (Game.getProvince((Integer)lSpreadPropositions.get(i)).getPopulationTotal() > tMaxPopulation) {
                            tMaxPopulation = Game.getProvince((Integer)lSpreadPropositions.get(i)).getPopulationTotal();
                        }

                        if (Game.getProvince((Integer)lSpreadPropositions.get(i)).getEconomyWithBonuses() > tMaxEconomy) {
                            tMaxEconomy = Game.getProvince((Integer)lSpreadPropositions.get(i)).getEconomyWithBonuses();
                        }

                        if ((float)Game.getProvince((Integer)lSpreadPropositions.get(i)).getInfrastructure() > tMaxInfrastructure) {
                            tMaxInfrastructure = (float)Game.getProvince((Integer)lSpreadPropositions.get(i)).getInfrastructure();
                        }

                        if (Game.getProvince((Integer)lSpreadPropositions.get(i)).getDevastation() > tMaxDevastation) {
                            tMaxDevastation = Game.getProvince((Integer)lSpreadPropositions.get(i)).getDevastation();
                        }
                    }

                    for(int i = lSpreadPropositions.size() - 1; i >= 0; --i) {
                        lSpreadPropositions_Score.add(((Data_Disease)lPlagues.get(nID)).OUTBREAK_SCORE_POPULATION * (float)Game.getProvince((Integer)lSpreadPropositions.get(i)).getPopulationTotal() / (float)tMaxPopulation + ((Data_Disease)lPlagues.get(nID)).OUTBREAK_SCORE_ECONOMY * Game.getProvince((Integer)lSpreadPropositions.get(i)).getEconomyWithBonuses() / tMaxEconomy + (((Data_Disease)lPlagues.get(nID)).OUTBREAK_SCORE_INFRASTRUCTURE - ((Data_Disease)lPlagues.get(nID)).OUTBREAK_SCORE_INFRASTRUCTURE * (float)Game.getProvince((Integer)lSpreadPropositions.get(i)).getInfrastructure() / tMaxInfrastructure) + ((Data_Disease)lPlagues.get(nID)).OUTBREAK_SCORE_DEVASTATION * Game.getProvince((Integer)lSpreadPropositions.get(i)).getDevastation() / tMaxDevastation);
                    }

                    int tBestID = 0;

                    for(int i = lSpreadPropositions_Score.size() - 1; i > 0; --i) {
                        if ((Float)lSpreadPropositions_Score.get(tBestID) < (Float)lSpreadPropositions_Score.get(i)) {
                            tBestID = i;
                        }
                    }

                    int nPlagueID_InGame = activePlagues.size();
                    activePlagues.add(new Plague((Integer)lSpreadPropositions.get(tBestID), ((Data_Disease)lPlagues.get(nID)).Name, ((Data_Disease)lPlagues.get(nID)).R / 255.0F, ((Data_Disease)lPlagues.get(nID)).G / 255.0F, ((Data_Disease)lPlagues.get(nID)).B / 255.0F, nPlagueID_InGame, ((Data_Disease)lPlagues.get(nID)).DEATH_RATE_MIN + (float)Game.oR.nextInt((int)(((Data_Disease)lPlagues.get(nID)).DEATH_RATE_EXTRA * 100000.0F + 1.0F)) / 100000.0F, ((Data_Disease)lPlagues.get(nID)).DURATION_TURNS_MIN + (((Data_Disease)lPlagues.get(nID)).DURATION_TURNS_EXTRA > 0 ? Game.oR.nextInt(((Data_Disease)lPlagues.get(nID)).DURATION_TURNS_EXTRA) : 0), ((Data_Disease)lPlagues.get(nID)).EXPANSION_MODIFIER + (float)Game.oR.nextInt((int)(((Data_Disease)lPlagues.get(nID)).EXPANSION_MODIFIER_EXTRA * 100000.0F + 1.0F)) / 100000.0F, ((Data_Disease)lPlagues.get(nID)).ImageID, ((Data_Disease)lPlagues.get(nID)).DEVASTATION));
                    lSpreadPropositions.clear();
                    lSpreadPropositions_Score.clear();
                    --nOutbreakProvinces;
                    if (nOutbreakProvinces > 0) {
                        ((Plague)activePlagues.get(nPlagueID_InGame)).spreadDisease(nOutbreakProvinces);
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
    }
    //ANALYTICALENGINE END

    public static final void loadDiseases() {
        try {
            FileHandle fileList = FileManager.loadFile("game/diseases/Diseases.json");
            String fileContent = fileList.readString();
            Json json = new Json();
            json.setElementType(ConfigDiseaseData.class, "Disease", Data_Disease.class);
            ConfigDiseaseData data = (ConfigDiseaseData)json.fromJson(ConfigDiseaseData.class, fileContent);

            for(Object e : data.Disease) {
                lPlagues.add((Data_Disease)e);
            }
        } catch (GdxRuntimeException ex) {
            CFG.exceptionStack(ex);
        }

        iPlaguesSize = lPlagues.size();
        loadPlagueImages();
    }

    public static final void loadPlagueImages() {
        FileHandle tempFileT = FileManager.loadFile("game/diseases/images/numOfImages.txt");
        int numOfImages = Integer.parseInt(tempFileT.readString());

        for(int i = 0; i < numOfImages; ++i) {
            if (FileManager.loadFile("game/diseases/images/" + CFG.getRescouresPath_Short() + i + ".png").exists()) {
                plagueImages.add(new Image(ImageManager.loadTexture("game/diseases/images/" + CFG.getRescouresPath_Short() + i + ".png"), TextureFilter.Linear, TextureWrap.ClampToEdge));
                plagueImagesBig.add(new Image(ImageManager.loadTexture("game/diseases/images/" + CFG.getRescouresPath_Short() + i + "b.png"), TextureFilter.Linear, TextureWrap.ClampToEdge));
            } else {
                plagueImages.add(new Image(ImageManager.loadTexture("game/diseases/images/" + CFG.getRescouresPath_Short_H() + i + ".png"), TextureFilter.Linear, TextureWrap.ClampToEdge));
                plagueImagesBig.add(new Image(ImageManager.loadTexture("game/diseases/images/" + CFG.getRescouresPath_Short_H() + i + "b.png"), TextureFilter.Linear, TextureWrap.ClampToEdge));
            }
        }

    }

    public static class ConfigDiseaseData {
        public String Age_of_History;
        public ArrayList Disease;

        public ConfigDiseaseData() {
        }
    }

    public static class Data_Disease {
        public String Name;
        public int BeginningYear;
        public int EndYear;
        public int ImageID;
        public float OUTBREAK_CHANCE;
        public int OUTBREAK_PROVINCES;
        public int OUTBREAK_PROVINCES_EXTRA;
        public int DURATION_TURNS_MIN;
        public int DURATION_TURNS_EXTRA;
        public float EXPANSION_MODIFIER;
        public float EXPANSION_MODIFIER_EXTRA;
        public float OUTBREAK_SCORE_POPULATION;
        public float OUTBREAK_SCORE_ECONOMY;
        public float OUTBREAK_SCORE_INFRASTRUCTURE;
        public float OUTBREAK_SCORE_DEVASTATION;
        public float DEATH_RATE_MIN;
        public float DEATH_RATE_EXTRA;
        public float DEVASTATION;
        public float R;
        public float G;
        public float B;

        public Data_Disease() {
        }
    }
}
