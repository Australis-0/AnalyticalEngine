//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map.plague;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification.Notification_BG;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification.Notification_Type;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData10;
import aoc.kingdoms.lukasz.textures.Images;
import java.util.ArrayList;
import java.util.List;

public class Plague {
    public String sName;
    public int iPlagueID_InGame = 0;
    public List<Integer> lProvinces = new ArrayList();
    public int iProvincesSize = 0;
    public List<Integer> lProvinces_Active = new ArrayList();
    public float fDeathRate = 0.0F;
    public int iDurationTurnsLeft = 0;
    public float fDevastation = 0.0F;
    public int iDeaths = 0;
    public int iImageID = 0;
    public int iDurationTurnsLeft_BEGINNING = 0;
    public float fR;
    public float fG;
    public float fB;
    public float EXPANSION_MODIFIER;
    public float EXPANSION_SCORE;
    public boolean nS = false;

    public Plague() {
    }

    public Plague(int outbreakProvince, String sName, float fR, float fG, float fB, int nPlagueID_InGame, float fDeathRate, int iDurationTurnsLeft, float EXPANSION_MODIFIER, int iImageID, float fDevastation) {
        this.sName = Game.lang.get(sName);
        this.iPlagueID_InGame = nPlagueID_InGame;
        this.fR = fR;
        this.fG = fG;
        this.fB = fB;
        this.iImageID = iImageID;
        this.fDevastation = fDevastation;
        this.fDeathRate = fDeathRate;
        this.iDurationTurnsLeft = iDurationTurnsLeft;
        this.iDurationTurnsLeft_BEGINNING = iDurationTurnsLeft;
        this.EXPANSION_MODIFIER = EXPANSION_MODIFIER;
        this.addProvince(outbreakProvince);
    }

    public final void runDisease() {
        for(int i = this.lProvinces_Active.size() - 1; i >= 0; --i) {
            if (Game.getProvince((Integer)this.lProvinces_Active.get(i)).provincePlague != null && Game.getProvince((Integer)this.lProvinces_Active.get(i)).provincePlague.id == this.getPlagueID_InGame()) {
                int nPopBefore = Game.getProvince((Integer)this.lProvinces_Active.get(i)).getPopulationTotal();
                int nDeaths = (int)Math.ceil((double)((float)nPopBefore * this.fDeathRate * (0.225F + 0.325F * this.getDurationPercLEFT() + 0.55F * (float)Game.oR.nextInt(100) / 100.0F) * (1.0F + Math.max(GameValues.plagues.DISEASE_MAX_DEATH_RATE_REDUCTION, Game.getProvince((Integer)this.lProvinces_Active.get(i)).provBonuses.DiseaseDeathRate + Game.getCiv(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getCivID()).civBonuses.DiseaseDeathRate + (float)Game.getProvince((Integer)this.lProvinces_Active.get(i)).getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_DISEASE_DEATH_RATE_PER_LVL))));
                if (nDeaths > 0) {
                    for(int k = Game.getProvince((Integer)this.lProvinces_Active.get(i)).getPopulationSize() - 1; k >= 0; --k) {
                        Game.getProvince((Integer)this.lProvinces_Active.get(i)).setPopulationOfCivID(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getPopulationCivID(k), (int)((double)Game.getProvince((Integer)this.lProvinces_Active.get(i)).getPopulationID(k) - Math.floor((double)((float)nDeaths * ((float)Game.getProvince((Integer)this.lProvinces_Active.get(i)).getPopulationID(k) / (float)nPopBefore)))));
                    }

                    nPopBefore -= Game.getProvince((Integer)this.lProvinces_Active.get(i)).getPopulationTotal();
                    ProvincePlague var10000 = Game.getProvince((Integer)this.lProvinces_Active.get(i)).provincePlague;
                    var10000.deaths += nPopBefore;
                    ProvinceData10 var6 = Game.getProvinceData10((Integer)this.lProvinces_Active.get(i));
                    var6.d += nPopBefore;
                    this.iDeaths += nPopBefore;
                    Game.getProvince((Integer)this.lProvinces_Active.get(i)).setDevastation(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getDevastation() + this.fDevastation * (0.75F + (float)Game.oR.nextInt(75) / 100.0F));
                }

                ProvincePlague var7 = Game.getProvince((Integer)this.lProvinces_Active.get(i)).provincePlague;
                var7.turnsLeft -= 0.875F - 0.065F * (Game.getProvince((Integer)this.lProvinces_Active.get(i)).getGrowthRateWithBonuses() / 100.0F) + (float)Game.oR.nextInt(GameValues.plagues.DISEASE_DAYS_LEFT_RANDOM) / 1000.0F;
                if (Game.getProvince((Integer)this.lProvinces_Active.get(i)).provincePlague.turnsLeft <= 0.0F) {
                    Game.getProvinceData10((Integer)this.lProvinces_Active.get(i)).t = Game_Calendar.TURN_ID;
                    Game.getProvince((Integer)this.lProvinces_Active.get(i)).provincePlague = null;
                    this.lProvinces_Active.remove(i);
                }
            }
        }

        this.fDeathRate *= GameValues.plagues.DISEASE_DEATH_RATE_CHANGE_PER_DAY - (float)Game.oR.nextInt(GameValues.plagues.DISEASE_DEATH_RATE_CHANGE_PER_DAY_RANDOM) / 10000.0F;
    }

    public final void spreadDisease() {
        if (this.iDurationTurnsLeft > 0 && this.lProvinces_Active.size() > 0) {
            if ((float)this.lProvinces.size() / (float)Game.getProvincesSize() > 0.35F) {
                return;
            }

            this.EXPANSION_SCORE += (float)this.lProvinces_Active.size() * 0.425F * this.EXPANSION_MODIFIER * (0.1F + 0.9F * this.getDurationPercLEFT());
            this.EXPANSION_MODIFIER *= 0.925F - (float)Game.oR.nextInt(17850) / 100000.0F;
            if (this.EXPANSION_SCORE >= 1.0F) {
                int nRand = Game.oR.nextInt((int)this.EXPANSION_SCORE);
                if (nRand > 0) {
                    this.EXPANSION_SCORE -= (float)nRand;
                    this.spreadDisease(nRand);
                }
            }
        }

    }

    public final void spreadDisease(int nNumOfProvinces) {
        try {
            nNumOfProvinces = (int)Math.min((float)nNumOfProvinces, Math.max((float)Game.getProvincesSize() * 0.01425F, 16.0F));
            List<Integer> tPossibleSpreadProvinces = new ArrayList();
            List<Integer> tPossibleSpreadProvinces_Scores = new ArrayList();

            for(int i = 0; i < this.lProvinces_Active.size(); ++i) {
                if (Game.getProvince((Integer)this.lProvinces_Active.get(i)).getSeaProvince()) {
                    for(int k = 0; k < Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringProvincesSize(); ++k) {
                        if (Game.getProvince(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).provincePlague == null && Game_Calendar.TURN_ID - Game.getProvinceData10(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).t > GameValues.plagues.PLAGUE_PAUSE_FOR_X_DAYS) {
                            tPossibleSpreadProvinces.add(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringProvinces(k));
                        }
                    }
                } else {
                    for(int k = 0; k < Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringProvincesSize(); ++k) {
                        if (Game.getProvince(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).getWasteland() < 0 && Game.getProvince(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).provincePlague == null && Game_Calendar.TURN_ID - Game.getProvinceData10(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringProvinces(k)).t > GameValues.plagues.PLAGUE_PAUSE_FOR_X_DAYS) {
                            tPossibleSpreadProvinces.add(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringProvinces(k));
                        }
                    }

                    if (Game.getProvince((Integer)this.lProvinces_Active.get(i)).getLevelOfPort() > 0 || Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringProvincesSize() < 2) {
                        for(int k = 0; k < Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringSeaProvincesSize(); ++k) {
                            if (Game.getProvince(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k)).getWasteland() < 0 && Game.getProvince(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k)).provincePlague == null && Game_Calendar.TURN_ID - Game.getProvinceData10(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k)).t > GameValues.plagues.PLAGUE_PAUSE_FOR_X_DAYS) {
                                tPossibleSpreadProvinces.add(Game.getProvince((Integer)this.lProvinces_Active.get(i)).getNeighboringSeaProvinces(k));
                            }
                        }
                    }
                }
            }

            if (tPossibleSpreadProvinces.size() > 0) {
                int tTotalScore = 0;

                for(int i = tPossibleSpreadProvinces.size() - 1; i >= 0; --i) {
                    int tempScore = this.getSpreadScore((Integer)tPossibleSpreadProvinces.get(i)) * 3 + 1;
                    tPossibleSpreadProvinces_Scores.add(tempScore);
                    tTotalScore += tempScore;
                }

                if (tTotalScore > 0) {
                    while(tPossibleSpreadProvinces_Scores.size() > 0 && nNumOfProvinces > 0) {
                        int tRandScore = Game.oR.nextInt(tTotalScore);
                        int i = 0;

                        for(int tCurrentScore = 0; i < tPossibleSpreadProvinces_Scores.size(); ++i) {
                            tCurrentScore += (Integer)tPossibleSpreadProvinces_Scores.get(i);
                            if (tCurrentScore > tRandScore) {
                                this.addProvince((Integer)tPossibleSpreadProvinces.get(i));
                                tTotalScore -= (Integer)tPossibleSpreadProvinces_Scores.get(i);
                                tPossibleSpreadProvinces_Scores.remove(i);
                                tPossibleSpreadProvinces.remove(i);
                                --nNumOfProvinces;
                                break;
                            }
                        }
                    }

                    if (nNumOfProvinces > 0) {
                        this.spreadDisease(nNumOfProvinces);
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final int getSpreadScore(int nProvinceID) {
        int tempScore = 0;

        for(int k = 0; k < Game.getProvince(nProvinceID).getNeighboringProvincesSize(); ++k) {
            if (Game.getProvince(Game.getProvince(nProvinceID).getNeighboringProvinces(k)).provincePlague == null) {
                tempScore += Game.getProvince(Game.getProvince(nProvinceID).getNeighboringProvinces(k)).getSeaProvince() ? 1 : 2;
            }
        }

        for(int k = 0; k < Game.getProvince(nProvinceID).getNeighboringSeaProvincesSize(); ++k) {
            if (Game.getProvince(Game.getProvince(nProvinceID).getNeighboringSeaProvinces(k)).provincePlague == null) {
                tempScore += Game.getProvince(Game.getProvince(nProvinceID).getNeighboringSeaProvinces(k)).getSeaProvince() ? 1 : 2;
            }
        }

        return tempScore;
    }

    public final void addProvince(int nProvinceID) {
        for(int i = 0; i < this.iProvincesSize; ++i) {
            if ((Integer)this.lProvinces.get(i) == nProvinceID) {
                return;
            }
        }

        Game.getProvinceData10(nProvinceID).t = Game_Calendar.TURN_ID;
        if (Game.getProvince(nProvinceID).provincePlague == null) {
            Game.getProvince(nProvinceID).provincePlague = new ProvincePlague(this.iPlagueID_InGame, Game_Calendar.TURN_ID, (float)this.iDurationTurnsLeft * (0.625F + (float)Game.oR.nextInt(6000) / 10000.0F), 0);
            if (!this.nS && Game.getProvince(nProvinceID).getCivID() == Game.player.iCivID && Game.player.iCivID > 0) {
                this.nS = true;
                if (Game.oR.nextInt(100) < GameValues.plagues.SEND_NOTIFICATION_CHANCE) {
                    Game.player.addNotification(new Notification(Notification_Type.DISEASE, this.sName + ": " + Game.lang.get("Disease"), Images.disease, Game_Calendar.TURN_ID, Notification_BG.RED, this.sName, nProvinceID));
                }
            }

            ++Game.getProvinceData10(nProvinceID).n;
            this.lProvinces.add(nProvinceID);
            this.lProvinces_Active.add(nProvinceID);
            this.iProvincesSize = this.lProvinces.size();
        }
    }

    public final String getPlagueName() {
        try {
            return this.sName;
        } catch (Exception var2) {
            return Game.lang.get("Plague");
        }
    }

    public final void setPlagueID_InGame(int iPlagueID_InGame) {
        this.iPlagueID_InGame = iPlagueID_InGame;
    }

    public final int getPlagueID_InGame() {
        return this.iPlagueID_InGame;
    }

    public final float getDurationPercLEFT() {
        return (float)this.iDurationTurnsLeft / (float)this.iDurationTurnsLeft_BEGINNING;
    }

    public final float getDurationPercLEFT(int nNumOfTurns) {
        return (float)nNumOfTurns / (float)this.iDurationTurnsLeft_BEGINNING;
    }

    public final int getOutbreakProvinceID() {
        try {
            return (Integer)this.lProvinces.get(0);
        } catch (IndexOutOfBoundsException var2) {
            return -1;
        }
    }

    public final int getDeaths() {
        return this.iDeaths;
    }

    public final int getNumOfProvinces_Total() {
        return this.lProvinces.size();
    }

    public final int getNumOfProvinces_Active() {
        return this.lProvinces_Active.size();
    }
}
