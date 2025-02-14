//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map.province;

import aoc.kingdoms.lukasz.events.EventsManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.FBO.FBOProvincesBG;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification.Notification_BG;
import aoc.kingdoms.lukasz.jakowski.Player.Notification.Notification.Notification_Type;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadManager;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.SteamMultiManager;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Army.M_Army_Add;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Army.M_Army_Merge;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Army.M_Army_Split;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Army.M_Army_Update;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Battle.M_BattleStart;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Occupied.M_Occupy;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Occupied.M_OccupyRetake;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Province.M_BuildBuilding;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Province.M_BuildBuildingCancel;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Province.M_BuildingDestroy;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Province.M_ConvertReligion;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Province.M_CreateCore;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Province.M_GrowthRate;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Province.M_IncreaseManpower;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Province.M_Infrastructure;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Province.M_Invest;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Province.M_ProvinceInvestData;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Province.M_TaxEfficiency;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.Province.M_Wonder;
import aoc.kingdoms.lukasz.map.BonusesManager;
import aoc.kingdoms.lukasz.map.BuildingsManager;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.map.SiegeManager;
import aoc.kingdoms.lukasz.map.WondersManager;
import aoc.kingdoms.lukasz.map.army.ArmyDivision;
import aoc.kingdoms.lukasz.map.army.ArmyRegiment;
import aoc.kingdoms.lukasz.map.battles.Battle;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRegionsManager;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.map.City;
import aoc.kingdoms.lukasz.map.map.Continents;
import aoc.kingdoms.lukasz.map.plague.ProvincePlague;
import aoc.kingdoms.lukasz.map.province.data.ProvinceData_Population;
import aoc.kingdoms.lukasz.map.terrain.Terrain;
import aoc.kingdoms.lukasz.map.war.War;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.menu_element.Toast;
import aoc.kingdoms.lukasz.menusInGame.InGame_Disband;
import aoc.kingdoms.lukasz.menusInGame.InGame_Wonder;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import space.earlygrey.shapedrawer.JoinType;

import javax.script.Invocable;
import javax.script.ScriptException;

public class Province {
    public int iProvinceID = 0;
    private Image provinceBG = null;
    public int provinceBGExtraY = 0;
    private CopyOnWriteArrayList<ArmyDivision> lArmies = new CopyOnWriteArrayList();
    private int iArmiesSize = 0;
    public List<ProvinceConstructedBuilding> buildings = new ArrayList();
    public int iBuildingsSize = 0;
    public int provincePopulationSize = 0;
    public int provincePopulationTotal = 0;
    public List<ProvinceConstructionBuilding> buildingsConstruction = new ArrayList();
    public int iBuildingsConstructionSize = 0;
    public int iBuildingsLimit;
    public List<ProvinceInvest> provinceInvestDaysLeft;
    public int iProvinceInvestSize;
    public List<ProvinceInvest> provinceIncreaseManpowerDaysLeft;
    public int iProvinceIncreaseManpowerSize;
    public List<ProvinceInvest> provinceIncreasTaxEfficiencyDaysLeft;
    public int iProvinceIncreaseTaxEfficiencySize;
    public List<ProvinceInvest> provinceIncreaseGrowthRateDaysLeft;
    public int iProvinceIncreaseGrowthRateSize;
    public List<ProvinceInvest> provinceDevelopInfrastructureDaysLeft;
    public int iProvinceDevelopInfrastructureSize;
    public ProvincePlague provincePlague;
    public int iInfrastructureMax;
    public boolean isCapital;
    public boolean fogDrawArmy;
    public Fog_DrawLandProvince fog_drawLandProvince;
    public int wonderID;
    public ProvinceInvest wonderConstruction;
    public ProvinceBonuses provBonuses;
    public ProvinceInvest religionConversion;
    public int iCoresSize;
    public boolean haveACore;
    public ProvinceInvest coreCreation;
    private String sProvinceName;
    private String sProvinceNameUpperCase;
    public int iProvinceNameLength_Minus1;
    private int iContinentID;
    private int iGeoRegionID;
    private int iTerrainTypeID;
    private int iResourceID;
    public float BaseDevelopment;
    public float fBaseGrowthRate;
    private int iLevelOfPort;
    private boolean seaProvince;
    private List<City> lCities;
    private boolean drawCities;
    private int iCitiesSize;
    private boolean haveCity;
    public float fProvinceIncome;
    public float fProvinceIncomeTaxation;
    public float fProvinceIncomeEconomy;
    public float fProvinceIncomeProduction;
    public float fProvinceMaintenance;
    public float fProvinceValue;
    public boolean peaceTreatyIsToTake;
    public boolean peaceTreatyIsTaken;
    public int aiPeaceCivID;
    public int aiPeaceScore;
    public float aiInvestScore;
    public float aiBuildScore;
    public float aiScore_CoresReligion;
    public float aiDistanceToCapital;
    public int aiRecruitArmyScore;
    public float aiArmyScore;
    public float aiMoveArmyAtWarScore;
    public float aiMoveArmyAtWarScore_DistanceFromArmy;
    public boolean aiRebelsIndependenceChecked;
    private List<Short> lPointsX;
    private List<Short> lPointsY;
    private int iPointsSize;
    private int iMinX;
    private int iMinY;
    private int iMaxX;
    private int iMaxY;
    private int iCenterX;
    private int iCenterY;
    private int iMinX_Real;
    private int iMinY_Real;
    private int iMaxX_Real;
    private int iMaxY_Real;
    private int iCenterX_Real;
    private int iCenterY_Real;
    public int iCenterShiftX;
    public int iCenterShiftY;
    private int iShiftX;
    private int iShiftY;
    private int iPortShiftX;
    private int iPortShiftY;
    private boolean belowZeroPosX;
    private int iTranslateProvincePosX;
    private boolean drawProvince;
    private int iCivRegionID;
    private int iMapModeRegion;
    public boolean was;
    public boolean wasCivRegion;
    public boolean wasCities;
    public boolean wasAI;
    public boolean wasPlayer;
    public boolean wasRetreat;
    public boolean wasBattleStart;
    public boolean wasBattleEnded;
    public int iBattlesInProvince;
    public boolean drawInMapMode;
    public float cityScale;
    public List<Integer> lNeighboringProvinces;
    public List<Integer> lNeighboringSeaProvinces;
    public int iNeighboringProvincesSize;
    public int iNeighboringSeaProvincesSize;
    public boolean accessToMainSea;
    private List<ProvinceBorder> lProvinceBordersLandByLand;
    private List<ProvinceBorder> lProvinceBordersLandBySea;
    private List<ProvinceBorder> lProvinceBordersSeaBySea;
    private int iProvinceBordersLandByLandSize;
    private int iProvinceBordersLandBySeaSize;
    private int iProvinceBordersSeaBySeaSize;
    public Color provinceColor;
    private ProvinceDrawArmy.ProvinceDrawArmyINT drawArmy;
    public ProvinceDrawArmy.ProvinceDrawDetailsINT drawDetails;
    private float lastUpdatedDevastation;

    public final void clearData() {
        this.lArmies.clear();
        this.iArmiesSize = 0;
        this.clearPopulationData();
        this.buildings.clear();
        this.iBuildingsSize = 0;
        this.buildingsConstruction.clear();
        this.iBuildingsConstructionSize = 0;
        this.iBuildingsLimit = GameValues.buildings.BUILDINGS_LIMIT_DEFAULT;
        this.provinceInvestDaysLeft.clear();
        this.iProvinceInvestSize = 0;
        this.provinceIncreaseManpowerDaysLeft.clear();
        this.iProvinceIncreaseManpowerSize = 0;
        this.provinceIncreasTaxEfficiencyDaysLeft.clear();
        this.iProvinceIncreaseTaxEfficiencySize = 0;
        this.provinceIncreaseGrowthRateDaysLeft.clear();
        this.iProvinceIncreaseGrowthRateSize = 0;
        this.provinceDevelopInfrastructureDaysLeft.clear();
        this.iProvinceDevelopInfrastructureSize = 0;
        this.provincePlague = null;
        this.iInfrastructureMax = GameValues.infrastructure.INFRASTRUCTURE_MAX_DEFAULT;
        this.isCapital = false;
        this.setWonderBuilt(false);
        this.wonderConstruction = null;
        this.provBonuses = new ProvinceBonuses();
        this.religionConversion = null;
        Game.getProvinceData5(this.getProvinceID()).co.clear();
        this.iCoresSize = 0;
        this.haveACore = false;
        this.coreCreation = null;
    }

    protected final void drawProvinceBorder(SpriteBatch oSB, float lineWidth, JoinType joinType) {
        for(int i = 0; i < this.iProvinceBordersLandBySeaSize; ++i) {
            ((ProvinceBorder)this.lProvinceBordersLandBySea.get(i)).drawProvinceBorder.draw(oSB, this.iTranslateProvincePosX, joinType, lineWidth);
        }

        for(int i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            ((ProvinceBorder)this.lProvinceBordersSeaBySea.get(i)).drawProvinceBorder.draw(oSB, this.iTranslateProvincePosX, joinType, lineWidth);
        }

        for(int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            ((ProvinceBorder)this.lProvinceBordersLandByLand.get(i)).drawProvinceBorder.draw(oSB, this.iTranslateProvincePosX, joinType, lineWidth);
        }

    }

    public final void addProvinceBorder(int nWithProvinceID, List<Integer> nPointsX, List<Integer> nPointsY) {
        for(int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (nWithProvinceID == ((ProvinceBorder)this.lProvinceBordersLandByLand.get(i)).getWithProvinceID()) {
                return;
            }
        }

        for(int i = 0; i < this.iProvinceBordersLandBySeaSize; ++i) {
            if (nWithProvinceID == ((ProvinceBorder)this.lProvinceBordersLandBySea.get(i)).getWithProvinceID()) {
                return;
            }
        }

        for(int i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            if (nWithProvinceID == ((ProvinceBorder)this.lProvinceBordersSeaBySea.get(i)).getWithProvinceID()) {
                return;
            }
        }

        if (this.getSeaProvince() && Game.getProvince(nWithProvinceID).getSeaProvince()) {
            this.lProvinceBordersSeaBySea.add(new ProvinceBorder(nWithProvinceID, nPointsX, nPointsY));
            this.iProvinceBordersSeaBySeaSize = this.lProvinceBordersSeaBySea.size();
            ((ProvinceBorder)this.lProvinceBordersSeaBySea.get(this.iProvinceBordersSeaBySeaSize - 1)).updateDrawProvinceBorder(this.iProvinceID);
        } else if (!this.getSeaProvince() && !Game.getProvince(nWithProvinceID).getSeaProvince()) {
            this.lProvinceBordersLandByLand.add(new ProvinceBorder(nWithProvinceID, nPointsX, nPointsY));
            this.iProvinceBordersLandByLandSize = this.lProvinceBordersLandByLand.size();
            ((ProvinceBorder)this.lProvinceBordersLandByLand.get(this.iProvinceBordersLandByLandSize - 1)).updateDrawProvinceBorder(this.iProvinceID);
        } else {
            this.lProvinceBordersLandBySea.add(new ProvinceBorder(nWithProvinceID, nPointsX, nPointsY));
            this.iProvinceBordersLandBySeaSize = this.lProvinceBordersLandBySea.size();
            ((ProvinceBorder)this.lProvinceBordersLandBySea.get(this.iProvinceBordersLandBySeaSize - 1)).updateDrawProvinceBorder(this.iProvinceID);
        }

    }

    public final void removeProvinceBorder(int withProvinceID) {
        for(int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (withProvinceID == ((ProvinceBorder)this.lProvinceBordersLandByLand.get(i)).getWithProvinceID()) {
                this.lProvinceBordersLandByLand.remove(i);
                this.iProvinceBordersLandByLandSize = this.lProvinceBordersLandByLand.size();
                return;
            }
        }

        for(int i = 0; i < this.iProvinceBordersLandBySeaSize; ++i) {
            if (withProvinceID == ((ProvinceBorder)this.lProvinceBordersLandBySea.get(i)).getWithProvinceID()) {
                this.lProvinceBordersLandBySea.remove(i);
                this.iProvinceBordersLandBySeaSize = this.lProvinceBordersLandBySea.size();
                return;
            }
        }

        for(int i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            if (withProvinceID == ((ProvinceBorder)this.lProvinceBordersSeaBySea.get(i)).getWithProvinceID()) {
                this.lProvinceBordersSeaBySea.remove(i);
                this.iProvinceBordersSeaBySeaSize = this.lProvinceBordersSeaBySea.size();
                return;
            }
        }

    }

    public Province(int nProvinceID, List<Short> nPointsX, List<Short> nPointsY) {
        this.iBuildingsLimit = GameValues.buildings.BUILDINGS_LIMIT_DEFAULT;
        this.provinceInvestDaysLeft = new ArrayList();
        this.iProvinceInvestSize = 0;
        this.provinceIncreaseManpowerDaysLeft = new ArrayList();
        this.iProvinceIncreaseManpowerSize = 0;
        this.provinceIncreasTaxEfficiencyDaysLeft = new ArrayList();
        this.iProvinceIncreaseTaxEfficiencySize = 0;
        this.provinceIncreaseGrowthRateDaysLeft = new ArrayList();
        this.iProvinceIncreaseGrowthRateSize = 0;
        this.provinceDevelopInfrastructureDaysLeft = new ArrayList();
        this.iProvinceDevelopInfrastructureSize = 0;
        this.provincePlague = null;
        this.iInfrastructureMax = GameValues.infrastructure.INFRASTRUCTURE_MAX_DEFAULT;
        this.isCapital = false;
        this.fogDrawArmy = true;
        this.fog_drawLandProvince = new Fog_DrawLandProvince() {
            public void drawLandProvinceFog(SpriteBatch oSB, float fProvinceAlpha) {
            }
        };
        this.wonderID = -1;
        this.wonderConstruction = null;
        this.provBonuses = new ProvinceBonuses();
        this.religionConversion = null;
        this.iCoresSize = 0;
        this.haveACore = false;
        this.coreCreation = null;
        this.sProvinceName = "";
        this.sProvinceNameUpperCase = "";
        this.iProvinceNameLength_Minus1 = 0;
        this.iContinentID = 1;
        this.iTerrainTypeID = 1;
        this.iResourceID = -1;
        this.BaseDevelopment = 1.0F;
        this.iLevelOfPort = 0;
        this.seaProvince = false;
        this.lCities = new ArrayList();
        this.drawCities = true;
        this.iCitiesSize = 0;
        this.haveCity = false;
        this.fProvinceIncome = 0.0F;
        this.fProvinceIncomeTaxation = 0.0F;
        this.fProvinceIncomeEconomy = 0.0F;
        this.fProvinceIncomeProduction = 0.0F;
        this.fProvinceMaintenance = 0.0F;
        this.fProvinceValue = 1.0F;
        this.peaceTreatyIsToTake = false;
        this.peaceTreatyIsTaken = false;
        this.aiPeaceCivID = 0;
        this.aiPeaceScore = 0;
        this.aiInvestScore = 0.0F;
        this.aiBuildScore = 0.0F;
        this.aiScore_CoresReligion = 0.0F;
        this.aiDistanceToCapital = 1.0F;
        this.aiRecruitArmyScore = 0;
        this.aiArmyScore = 0.0F;
        this.aiMoveArmyAtWarScore = 0.0F;
        this.aiMoveArmyAtWarScore_DistanceFromArmy = 0.0F;
        this.aiRebelsIndependenceChecked = false;
        this.belowZeroPosX = false;
        this.iTranslateProvincePosX = 0;
        this.drawProvince = false;
        this.iCivRegionID = -1;
        this.iMapModeRegion = -1;
        this.was = false;
        this.wasCivRegion = false;
        this.wasCities = false;
        this.wasAI = false;
        this.wasPlayer = false;
        this.wasRetreat = false;
        this.wasBattleStart = false;
        this.wasBattleEnded = false;
        this.iBattlesInProvince = 0;
        this.drawInMapMode = false;
        this.cityScale = 1.0F;
        this.lNeighboringProvinces = new ArrayList();
        this.lNeighboringSeaProvinces = new ArrayList();
        this.accessToMainSea = false;
        this.lProvinceBordersLandByLand = new ArrayList();
        this.lProvinceBordersLandBySea = new ArrayList();
        this.lProvinceBordersSeaBySea = new ArrayList();
        this.iProvinceBordersLandByLandSize = 0;
        this.iProvinceBordersLandBySeaSize = 0;
        this.iProvinceBordersSeaBySeaSize = 0;
        this.provinceColor = Color.WHITE;
        this.drawArmy = new ProvinceDrawArmy.ProvinceDrawArmyINT() {
            public void drawArmy(SpriteBatch oSB, int nProvinceID, int iArmyID) {
            }
        };
        this.drawDetails = new ProvinceDrawArmy.ProvinceDrawDetailsINT() {
            public void drawDetails(SpriteBatch oSB, int nProvinceID) {
            }

            public void drawDetailsSea(SpriteBatch oSB, int nProvinceID) {
            }
        };
        this.lastUpdatedDevastation = 0.0F;
        this.iProvinceID = nProvinceID;
        this.lPointsX = nPointsX;
        this.lPointsY = nPointsY;
        this.iPointsSize = this.lPointsX.size();
        this.iMinX_Real = this.iMaxX_Real = (Short)this.lPointsX.get(0);
        this.iMinY_Real = this.iMaxY_Real = (Short)this.lPointsY.get(0);

        for(int i = 0; i < this.iPointsSize; ++i) {
            if (this.iMinX_Real > (Short)this.lPointsX.get(i)) {
                this.iMinX_Real = (Short)this.lPointsX.get(i);
            }

            if (this.iMaxX_Real < (Short)this.lPointsX.get(i)) {
                this.iMaxX_Real = (Short)this.lPointsX.get(i);
            }

            if (this.iMinY_Real > (Short)this.lPointsY.get(i)) {
                this.iMinY_Real = (Short)this.lPointsY.get(i);
            }

            if (this.iMaxY_Real < (Short)this.lPointsY.get(i)) {
                this.iMaxY_Real = (Short)this.lPointsY.get(i);
            }
        }

        this.iCenterX_Real = (this.iMinX_Real + this.iMaxX_Real) / 2;
        this.iCenterY_Real = (this.iMinY_Real + this.iMaxY_Real) / 2;
        this.iCenterX = (this.iMinX_Real + this.iMaxX_Real) / 2 * Game.mapBG.iMapScale;
        this.iCenterY = (this.iMinY_Real + this.iMaxY_Real) / 2 * Game.mapBG.iMapScale;
        this.iMinX = this.iMinX_Real * Game.mapBG.iMapScale;
        this.iMaxX = this.iMaxX_Real * Game.mapBG.iMapScale;
        this.iMinY = this.iMinY_Real * Game.mapBG.iMapScale;
        this.iMaxY = this.iMaxY_Real * Game.mapBG.iMapScale;
        this.belowZeroPosX = this.iMinX < 0;
        if (Game.MAX_BELOW_ZERO_POINT_X > this.iMinX) {
            Game.MAX_BELOW_ZERO_POINT_X = this.iMinX;
        }

    }

    public final void drawArmy(SpriteBatch oSB, float fAlpha) {
        for(int iArmyID = 0; iArmyID < this.iArmiesSize; ++iArmyID) {
            this.drawArmy.drawArmy(oSB, this.iProvinceID, iArmyID);
        }

    }

    public final String addArmy(ArmyDivision nArmy) {
        if (nArmy != null && nArmy.iArmyRegimentSize > 0) {
            if (Game.FOG_OF_WAR && GameValues.fog.HIDE_ARMIES) {
                Renderer.addSimpleTask_ArmyWidth(new Renderer.SimpleTaskArmyText(nArmy.key, nArmy, false));
            }

            nArmy.provinceID = this.getProvinceID();
            this.lArmies.add(nArmy);
            this.iArmiesSize = this.lArmies.size();
            this.updateDrawArmy();
            this.updateArmyPosY();
            if (nArmy.civID < 0) {
                Game.revolutionManager.addArmyPosition(this.getProvinceID(), nArmy.key);
            } else {
                Game.getCiv(nArmy.civID).addArmyPosition(this.getProvinceID(), nArmy.key);
            }

            this.checkForBattle();
            return nArmy.key;
        } else {
            return null;
        }
    }

    public final String updateArmy_Multi(ArmyDivision nArmy) {
        if (nArmy == null) {
            return null;
        } else {
            if (nArmy.iArmyRegimentSize > 0) {
                nArmy.provinceID = this.getProvinceID();

                for(int i = 0; i < this.iArmiesSize; ++i) {
                    if (((ArmyDivision)this.lArmies.get(i)).key.equals(nArmy.key) && ((ArmyDivision)this.lArmies.get(i)).civID == nArmy.civID) {
                        nArmy.updateMoraleIMG();
                        nArmy.updateArmy(true);
                        this.lArmies.set(i, nArmy);
                        if (Game.FOG_OF_WAR && GameValues.fog.HIDE_ARMIES) {
                            Renderer.addSimpleTask_ArmyWidth(new Renderer.SimpleTaskArmyText(nArmy.key, nArmy, false));
                        }

                        this.updateDrawArmy();
                        this.updateArmyPosY();
                        if (nArmy.civID < 0) {
                            Game.revolutionManager.addArmyPosition(this.getProvinceID(), nArmy.key);
                        } else {
                            Game.getCiv(nArmy.civID).addArmyPosition(this.getProvinceID(), nArmy.key);
                        }

                        this.checkForBattle();
                        return nArmy.key;
                    }
                }
            } else {
                for(int i = 0; i < this.iArmiesSize; ++i) {
                    if (((ArmyDivision)this.lArmies.get(i)).key.equals(nArmy.key) && ((ArmyDivision)this.lArmies.get(i)).civID == nArmy.civID) {
                        this.removeArmy_UnassignGeneral(i);
                        this.removeArmy(i);
                        return null;
                    }
                }
            }

            return null;
        }
    }

    public final String addArmy_Load(ArmyDivision nArmy) {
        if (nArmy != null && nArmy.iArmyRegimentSize > 0) {
            nArmy.provinceID = this.getProvinceID();
            this.lArmies.add(nArmy);
            this.iArmiesSize = this.lArmies.size();
            this.updateDrawArmy();
            this.updateArmyPosY();
            if (nArmy.civID < 0) {
                Game.revolutionManager.addArmyPosition(this.getProvinceID(), nArmy.key);
            } else {
                Game.getCiv(nArmy.civID).addArmyPosition(this.getProvinceID(), nArmy.key);
            }

            return nArmy.key;
        } else {
            return null;
        }
    }

    public final void addArmy_MoveUnits(ArmyDivision nArmy) {
        if (nArmy.iArmyRegimentSize > 0) {
            if (Game.FOG_OF_WAR && GameValues.fog.HIDE_ARMIES) {
                Renderer.addSimpleTask_ArmyWidth(new Renderer.SimpleTaskArmyText(nArmy.key, nArmy, false));
            }

            nArmy.provinceID = this.getProvinceID();
            this.lArmies.add(nArmy);
            this.iArmiesSize = this.lArmies.size();
            this.updateDrawArmy();
            this.updateArmyPosY();
            this.checkForBattle();
        }

    }

    public final void updateArmyPosY() {
        try {
            int i = 0;

            for(int j = 0; i < this.iArmiesSize; ++i) {
                if (!((ArmyDivision)this.lArmies.get(i)).inMovement) {
                    ((ArmyDivision)this.lArmies.get(i)).iShiftY = ProvinceDrawArmy.getArmyHeight() * j + j * 2 - ProvinceDrawArmy.getArmyHeight() / 2;
                    ++j;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void removeArmy_UnassignGeneral(int i) {
        try {
            if (((ArmyDivision)this.lArmies.get(i)).armyGeneral != null) {
                Game.getCiv(((ArmyDivision)this.lArmies.get(i)).civID).addGeneral(((ArmyDivision)this.lArmies.get(i)).armyGeneral);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void removeArmy(int i) {
        try {
            int tCivID = ((ArmyDivision)this.lArmies.get(i)).civID;
            if (tCivID < 0) {
                Game.revolutionManager.removeArmyPosition(this.getProvinceID(), ((ArmyDivision)this.lArmies.get(i)).key);
            } else {
                Game.getCiv(tCivID).removeArmyPosition(this.getProvinceID(), ((ArmyDivision)this.lArmies.get(i)).key);
            }

            this.lArmies.remove(i);
            this.iArmiesSize = this.lArmies.size();
            this.updateDrawArmy();
            this.updateArmyPosY();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void removeArmy_MoveUnits(int i) {
        try {
            int tCivID = ((ArmyDivision)this.lArmies.get(i)).civID;
            this.lArmies.remove(i);
            this.iArmiesSize = this.lArmies.size();
            this.updateDrawArmy();
            this.updateArmyPosY();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void removeArmyCivID(int civID) {
        try {
            for(int i = this.iArmiesSize - 1; i >= 0; --i) {
                if (((ArmyDivision)this.lArmies.get(i)).civID == civID) {
                    this.lArmies.remove(i);
                }
            }

            this.iArmiesSize = this.lArmies.size();
            this.updateDrawArmy();
            this.updateArmyPosY();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void removeArmy(String key) {
        try {
            for(int i = 0; i < this.iArmiesSize; ++i) {
                if (this.getArmy(i).key.equals(key)) {
                    this.removeArmy(i);
                    return;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void removeArmy(String key, int civID) {
        try {
            for(int i = 0; i < this.iArmiesSize; ++i) {
                if (this.getArmy(i).civID == civID && this.getArmy(i).key.equals(key)) {
                    this.removeArmy(i);
                    return;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void removeArmy_MoveUnits(String key) {
        try {
            for(int i = 0; i < this.iArmiesSize; ++i) {
                if (this.getArmy(i).key.equals(key)) {
                    this.removeArmy_MoveUnits(i);
                    return;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void clearArmiesCivID(int nRemoveAllArmiesCivID) {
        try {
            for(int i = this.iArmiesSize - 1; i >= 0; --i) {
                if (nRemoveAllArmiesCivID == ((ArmyDivision)this.lArmies.get(i)).civID) {
                    if (nRemoveAllArmiesCivID < 0) {
                        Game.revolutionManager.removeArmyPosition(this.getProvinceID(), ((ArmyDivision)this.lArmies.get(i)).key);
                    } else {
                        Game.getCiv(nRemoveAllArmiesCivID).removeArmyPosition(this.getProvinceID(), ((ArmyDivision)this.lArmies.get(i)).key);
                    }

                    this.lArmies.remove(i);
                }
            }

            this.iArmiesSize = this.lArmies.size();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void clearArmies() {
        try {
            for(int i = this.iArmiesSize - 1; i >= 0; --i) {
                if (((ArmyDivision)this.lArmies.get(i)).civID < 0) {
                    Game.revolutionManager.removeArmyPosition(this.getProvinceID(), ((ArmyDivision)this.lArmies.get(i)).key);
                } else {
                    Game.getCiv(((ArmyDivision)this.lArmies.get(i)).civID).removeArmyPosition(this.getProvinceID(), ((ArmyDivision)this.lArmies.get(i)).key);
                }

                this.lArmies.remove(i);
            }

            this.iArmiesSize = this.lArmies.size();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void clearArmies_ScenarioEditor() {
        this.lArmies.clear();
        this.iArmiesSize = 0;
    }

    public final ArmyDivision getArmy(int i) {
        try {
            return (ArmyDivision)this.lArmies.get(i);
        } catch (Exception var3) {
            return new ArmyDivision(0, 0, new ArrayList());
        }
    }

    public final ArmyDivision getArmy_MultiNull(int i) {
        try {
            return (ArmyDivision)this.lArmies.get(i);
        } catch (Exception var3) {
            return null;
        }
    }

    public final void setArmy(int i, ArmyDivision armyDivision) {
        try {
            this.lArmies.set(i, armyDivision);
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void updateArmy_AfterBattle(String key, String regimentKey, int numOfSoldiers, float morale) {
        try {
            for(int i = 0; i < this.iArmiesSize; ++i) {
                if (this.getArmy(i).key.equals(key)) {
                    for(int j = 0; j < ((ArmyDivision)this.lArmies.get(i)).iArmyRegimentSize; ++j) {
                        if (((ArmyRegiment)((ArmyDivision)this.lArmies.get(i)).lArmyRegiment.get(j)).key.equals(regimentKey)) {
                            ((ArmyDivision)this.lArmies.get(i)).provinceID = this.getProvinceID();
                            ((ArmyRegiment)((ArmyDivision)this.lArmies.get(i)).lArmyRegiment.get(j)).num = numOfSoldiers;
                            ((ArmyRegiment)((ArmyDivision)this.lArmies.get(i)).lArmyRegiment.get(j)).mo = morale;
                            return;
                        }
                    }

                    return;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final boolean haveArmy(int civID) {
        for(int i = 0; i < this.iArmiesSize; ++i) {
            if (((ArmyDivision)this.lArmies.get(i)).civID == civID) {
                return true;
            }
        }

        return false;
    }

    public final ArmyDivision getArmy(String key) {
        try {
            for(int i = 0; i < this.iArmiesSize; ++i) {
                if (this.getArmy(i).key.equals(key)) {
                    return (ArmyDivision)this.lArmies.get(i);
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return null;
    }

    public final ArmyDivision getArmy(String key, int civID) {
        try {
            for(int i = 0; i < this.iArmiesSize; ++i) {
                if (this.getArmy(i).civID == civID && this.getArmy(i).key.equals(key)) {
                    return (ArmyDivision)this.lArmies.get(i);
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return null;
    }

    public final boolean isEnemyArmyInProvince(int civID) {
        try {
            for(int i = 0; i < this.iArmiesSize; ++i) {
                if (DiplomacyManager.isAtWar(civID, ((ArmyDivision)this.lArmies.get(i)).civID)) {
                    return true;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return false;
    }

    public final boolean updateRegiment(int i, List<ArmyRegiment> nArmyRegiment) {
        return this.updateRegiment(i, nArmyRegiment, false);
    }

    public final boolean updateRegiment(int i, List<ArmyRegiment> nArmyRegiment, boolean fromMulti) {
        try {
            if (nArmyRegiment.size() == 0) {
                this.getArmy(i).provinceID = this.getProvinceID();
                this.getArmy(i).updateRegiment(nArmyRegiment);
                if (!fromMulti && SteamMultiManager.isHost()) {
                    M_Army_Update.serializeArmy(this.getArmy(i));
                }

                this.removeArmy(i);
                return false;
            }

            this.getArmy(i).provinceID = this.getProvinceID();
            this.getArmy(i).updateRegiment(nArmyRegiment);
            if (!fromMulti && SteamMultiManager.isHost()) {
                M_Army_Update.serializeArmy(this.getArmy(i));
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return true;
    }

    public boolean disbandRegiment(String key, List<ArmyRegiment> nArmyRegiment) {
        return this.disbandRegiment(key, nArmyRegiment, false);
    }

    public boolean disbandRegiment(String key, List<ArmyRegiment> nArmyRegiment, boolean fromMulti) {
        try {
            int armyID = this.getArmyKeyID(key);
            if (armyID >= 0) {
                int i = 0;

                //ANALYTICALENGINE START
                Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
                try {
                    invocable.invokeFunction("parseOnArmyDisband", this.getArmy(armyID));
                } catch (ScriptException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                //ANALYTICALENGINE END

                for(int iSize = nArmyRegiment.size(); i < iSize; ++i) {
                    this.getArmy(armyID).removeRegiment(((ArmyRegiment)nArmyRegiment.get(i)).key, true, this.getArmy(armyID).civID);
                }

                if (!fromMulti) {
                    M_Army_Update.serializeArmy(this.getArmy(armyID));
                }

                if (this.getArmy(armyID).iArmyRegimentSize == 0) {
                    this.removeArmy_UnassignGeneral(armyID);
                    this.removeArmy(armyID);
                } else {
                    this.updateDrawArmy();
                    this.updateArmyPosY();
                }

                Game.gameThread.addCivUpdateArmyMaintenance(this.getArmy(armyID).civID);

                return true;
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return false;
    }

    public final void splitInHalf(int i) {
        this.splitInHalf(i, false);
    }

    public final void splitInHalf(int i, boolean fromMulti) {
        try {
            if (((ArmyDivision)this.lArmies.get(i)).iArmyRegimentSize > 1) {
                if (!fromMulti && !SteamMultiManager.isHost()) {
                    M_Army_Split.requestCommand(this.getProvinceID(), ((ArmyDivision)this.lArmies.get(i)).civID, ((ArmyDivision)this.lArmies.get(i)).key);
                    return;
                }

                List<ArmyRegiment> nArmyRegiment = new ArrayList();
                List<ArmyRegiment> nArmyRegiment2 = new ArrayList();

                for(int a = 0; a < ((ArmyDivision)this.lArmies.get(i)).iArmyRegimentSize; ++a) {
                    if (a % 2 == 0) {
                        nArmyRegiment.add((ArmyRegiment)((ArmyDivision)this.lArmies.get(i)).lArmyRegiment.get(a));
                    } else {
                        nArmyRegiment2.add((ArmyRegiment)((ArmyDivision)this.lArmies.get(i)).lArmyRegiment.get(a));
                    }
                }

                if (this.updateRegiment(i, nArmyRegiment)) {
                    ArmyDivision armyDivision = new ArmyDivision(((ArmyDivision)this.lArmies.get(i)).civID, ((ArmyDivision)this.lArmies.get(i)).provinceID, nArmyRegiment2);
                    this.addArmy(armyDivision);
                    if (!fromMulti && SteamMultiManager.isHost()) {
                        M_Army_Add.serializeArmy(armyDivision);
                    }

                    this.updateDrawArmy();
                    this.updateArmyPosY();
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final String mergeUnits(List<String> list) {
        return this.mergeUnits(list, false);
    }

    public final String mergeUnits(List<String> list, boolean fromMulti) {
        try {
            int listSize = list.size();
            if (listSize > 1) {
                if (!fromMulti) {
                    if (!SteamMultiManager.isHost()) {
                        M_Army_Merge.requestCommand(this.getProvinceID(), list);
                        return null;
                    }

                    M_Army_Merge.armies.add(new M_Army_Merge.MergeUnitsMulti(this.getProvinceID(), list));
                }

                int armyWithGeneral = this.getArmyKeyID((String)list.get(0));
                int armyWithGeneralID = 0;

                for(int i = 0; i < listSize; ++i) {
                    int tID = this.getArmyKeyID((String)list.get(i));
                    if (tID >= 0) {
                        if (this.getArmy(tID).armyGeneral != null) {
                            armyWithGeneral = tID;
                            armyWithGeneralID = i;
                            break;
                        }

                        if (armyWithGeneral < 0) {
                            armyWithGeneral = tID;
                            armyWithGeneralID = i;
                        }
                    }
                }

                if (armyWithGeneral >= 0) {
                    List<ArmyRegiment> nArmyRegiment = new ArrayList();

                    for(int i = 0; i < listSize; ++i) {
                        int tID = this.getArmyKeyID((String)list.get(i));
                        if (tID >= 0) {
                            for(int j = 0; j < this.getArmy(tID).iArmyRegimentSize; ++j) {
                                nArmyRegiment.add((ArmyRegiment)this.getArmy(tID).lArmyRegiment.get(j));
                            }
                        }
                    }

                    String out = (String)list.get(armyWithGeneralID);
                    this.updateRegiment(armyWithGeneral, nArmyRegiment, true);
                    list.remove(armyWithGeneralID);
                    listSize = list.size();

                    for(int i = 0; i < listSize; ++i) {
                        int armyKeyID = this.getArmyKeyID((String)list.get(i));
                        if (armyKeyID >= 0 && armyKeyID < this.iArmiesSize) {
                            this.removeArmy_UnassignGeneral(armyKeyID);
                            this.removeArmy(armyKeyID);
                        }
                    }

                    return out;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return null;
    }

    public final int getArmyKeyID(String nKey) {
        try {
            for(int i = 0; i < this.iArmiesSize; ++i) {
                if (((ArmyDivision)this.lArmies.get(i)).key.equals(nKey)) {
                    return i;
                }
            }
        } catch (Exception var3) {
        }

        return -1;
    }

    public final int getArmyKeyID(String nKey, int iCivID) {
        try {
            for(int i = 0; i < this.iArmiesSize; ++i) {
                if (((ArmyDivision)this.lArmies.get(i)).civID == iCivID && ((ArmyDivision)this.lArmies.get(i)).key.equals(nKey)) {
                    return i;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return -1;
    }

    public final ArmyDivision getArmyKey(String nKey) {
        try {
            for(int i = 0; i < this.iArmiesSize; ++i) {
                if (((ArmyDivision)this.lArmies.get(i)).key.equals(nKey)) {
                    return (ArmyDivision)this.lArmies.get(i);
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return null;
    }

    public final int getArmySize() {
        return this.iArmiesSize;
    }

    public final int getArmyRegimentSize_InProvince() {
        int out = 0;

        for(int i = 0; i < this.iArmiesSize; ++i) {
            if (((ArmyDivision)this.lArmies.get(i)).civID == this.getCivID()) {
                out += ((ArmyDivision)this.lArmies.get(i)).iArmyRegimentSize;
            }
        }

        return out;
    }

    public final void updateArmy_BattleSummary(String key) {
        try {
            for(int i = this.iArmiesSize - 1; i >= 0; --i) {
                if (((ArmyDivision)this.lArmies.get(i)).key.equals(key)) {
                    ((ArmyDivision)this.lArmies.get(i)).inBattle = false;
                    ((ArmyDivision)this.lArmies.get(i)).updateMorale();
                    ((ArmyDivision)this.lArmies.get(i)).updateArmy(false);
                    if (((ArmyDivision)this.lArmies.get(i)).iArmy == 0) {
                        this.removeArmy(i);
                    } else if (((ArmyDivision)this.lArmies.get(i)).inMovement) {
                        if (((ArmyDivision)this.lArmies.get(i)).civID < 0) {
                            Game.revolutionMoveUnits.updateMoveInBattle(((ArmyDivision)this.lArmies.get(i)).key, ((ArmyDivision)this.lArmies.get(i)).inBattle);
                        } else {
                            Game.getCiv(((ArmyDivision)this.lArmies.get(i)).civID).updateMoveInBattle(((ArmyDivision)this.lArmies.get(i)).key, ((ArmyDivision)this.lArmies.get(i)).inBattle);
                        }
                    }

                    return;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void armyDestroyed(String key) {
        try {
            if (this.getArmy(key) == null) {
                return;
            }

            if (this.getArmy(key).civID == Game.player.iCivID) {
                Game.player.addNotification(new Notification(Notification_Type.ARMY_DESTROYED, this.getProvinceName() + ": " + Game.lang.get("ArmyDestroyed"), Images.battle, Game_Calendar.TURN_ID, Notification_BG.RED, this.getProvinceID()) {
                    public void onAction() {
                        Game.mapCoords.centerToProvinceID(this.id);
                    }
                });
            }

            this.removeArmy(key);
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final int armyRetreat(String key, int extraArmyY) {
        try {
            int tArmyID = this.getArmyKeyID(key);
            if (tArmyID >= 0) {
                int iCivID = this.getArmy(tArmyID).civID;
                if (iCivID < 0) {
                    Game.getProvince(this.iProvinceID).armyDestroyed(key);
                    return 0;
                }

                int retreatToProvinceID = WarManager.retreatToProvinceID(iCivID, this.iProvinceID);
                if (retreatToProvinceID < 0) {
                    List<Integer> tempRetreatTo = new ArrayList();
                    if (this.getNeighboringProvincesSize() > 0) {
                        for(int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
                            if (DiplomacyManager.isAtWar(iCivID, Game.getProvince(this.getNeighboringProvinces(i)).getCivID()) && Game.getProvince(this.getNeighboringProvinces(i)).isOccupied() && this.getNeighboringProvinces(i) != this.getProvinceID()) {
                                tempRetreatTo.add(this.getNeighboringProvinces(i));
                            }
                        }
                    }

                    if (tempRetreatTo.size() > 0) {
                        retreatToProvinceID = (Integer)tempRetreatTo.get(Game.oR.nextInt(tempRetreatTo.size()));
                    } else if (this.getNeighboringSeaProvincesSize() > 0) {
                        retreatToProvinceID = this.getNeighboringSeaProvinces(Game.oR.nextInt(this.getNeighboringSeaProvincesSize()));
                    }
                } else {
                    List<Integer> tempRetreatTo = new ArrayList();

                    for(int i = 0; i < Game.getProvince(retreatToProvinceID).getNeighboringProvincesSize(); ++i) {
                        if (iCivID == Game.getProvince(Game.getProvince(retreatToProvinceID).getNeighboringProvinces(i)).getCivID()) {
                            if (Game.getProvince(retreatToProvinceID).getNeighboringProvinces(i) != this.getProvinceID() && !Game.getProvince(Game.getProvince(retreatToProvinceID).getNeighboringProvinces(i)).isOccupied()) {
                                tempRetreatTo.add(Game.getProvince(retreatToProvinceID).getNeighboringProvinces(i));
                            }

                            for(int j = 0; j < Game.getProvince(Game.getProvince(retreatToProvinceID).getNeighboringProvinces(i)).getNeighboringProvincesSize(); ++j) {
                                if (iCivID == Game.getProvince(Game.getProvince(Game.getProvince(retreatToProvinceID).getNeighboringProvinces(i)).getNeighboringProvinces(j)).getCivID() && Game.getProvince(Game.getProvince(retreatToProvinceID).getNeighboringProvinces(i)).getNeighboringProvinces(j) != this.getProvinceID() && !Game.getProvince(Game.getProvince(Game.getProvince(retreatToProvinceID).getNeighboringProvinces(i)).getNeighboringProvinces(j)).isOccupied()) {
                                    tempRetreatTo.add(Game.getProvince(Game.getProvince(retreatToProvinceID).getNeighboringProvinces(i)).getNeighboringProvinces(j));
                                }
                            }
                        }
                    }

                    if (!tempRetreatTo.isEmpty()) {
                        retreatToProvinceID = (Integer)tempRetreatTo.get(Game.oR.nextInt(tempRetreatTo.size()));
                    }
                }

                if (iCivID == Game.player.iCivID) {
                    Game.player.playerData.invasion.removeInvasion(key);
                }

                if (retreatToProvinceID < 0) {
                    return -1;
                }

                if (DiplomacyManager.isAtWar(Game.getProvince(retreatToProvinceID).getCivID(), iCivID)) {
                    return -1;
                }

                if (SteamMultiManager.isHost()) {
                    Game.getCiv(iCivID).newMove(this.getProvinceID(), retreatToProvinceID, key, extraArmyY++, true);
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return extraArmyY;
    }

    public final void drawDetails(SpriteBatch oSB) {
        this.drawDetails.drawDetails(oSB, this.iProvinceID);
    }

    public final void drawDetailsSea(SpriteBatch oSB) {
        this.drawDetails.drawDetailsSea(oSB, this.iProvinceID);
    }

    public final void setProvinceColor(SpriteBatch oSB, float fAlpha) {
        this.setCivilizationProvinceColor(oSB, this.getCivID(), fAlpha);
    }

    public final void setProvinceColor_Fog(SpriteBatch oSB, float fAlpha) {
        this.setCivilizationProvinceColor_Fog(oSB, this.getCivID(), fAlpha);
    }

    public final void setCivilizationProvinceColor(SpriteBatch oSB, int nCivID, float nAlpha) {
        Game.getCiv(nCivID).civColorMap.a = nAlpha;
        oSB.setColor(Game.getCiv(nCivID).civColorMap);
    }

    public final void setCivilizationProvinceColor_Fog(SpriteBatch oSB, int nCivID, float nAlpha) {
        Game.getCiv(nCivID).civColorMap.a = nAlpha;
        Game.getCiv(nCivID).civColorFog.a = nAlpha;
        oSB.setColor(Game.getCiv(nCivID).civColorFog);
    }

    public final void drawProvince_ActiveProvince(SpriteBatch oSB) {
        if (!this.getSeaProvince()) {
            if (this.iLevelOfPort == -4) {
                this.provinceBG.draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, (float)Game.mapBG.iMapScale);
            } else {
                this.provinceBG.draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, Game.mapBG.iMapExtraScale);
            }

        }
    }

    public final void drawProvince_ActiveProvince(SpriteBatch oSB, Image imgProvince) {
        try {
            if (this.iLevelOfPort == -4) {
                imgProvince.draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, (float)Game.mapBG.iMapScale);
            } else {
                imgProvince.draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, Game.mapBG.iMapExtraScale);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void drawLandProvince(SpriteBatch oSB) {
        this.provinceBG.draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, Game.mapBG.iMapExtraScale);
    }

    public final void drawLandProvince(SpriteBatch oSB, float fProvinceAlpha) {
        this.setProvinceColor(oSB, fProvinceAlpha);
        this.provinceBG.draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, Game.mapBG.iMapExtraScale);
    }

    public final void drawLandProvince_Fog(SpriteBatch oSB, float fProvinceAlpha) {
        this.setProvinceColor_Fog(oSB, fProvinceAlpha);
        this.provinceBG.draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, Game.mapBG.iMapExtraScale);
    }

    public final void drawLandProvinceExtra(SpriteBatch oSB) {
        this.provinceBG.draw(oSB, Game.mapCoords.getPosX() + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, Game.mapBG.iMapExtraScale);
    }

    public final void drawLandProvinceExtra(SpriteBatch oSB, float fProvinceAlpha) {
        this.setProvinceColor(oSB, fProvinceAlpha);
        this.provinceBG.draw(oSB, Game.mapCoords.getPosX() + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, Game.mapBG.iMapExtraScale);
    }

    protected final void drawOccupiedProvince(SpriteBatch oSB, float fAlpha) {
        if (Game.getProvinceData(this.getProvinceID()).getOccupiedByCivID() < 0) {
            oSB.setColor(new Color(DiplomacyManager.COLOR_WAR.r, DiplomacyManager.COLOR_WAR.g, DiplomacyManager.COLOR_WAR.b, fAlpha));
        } else {
            oSB.setColor(Game.getCiv(Game.getProvinceData(this.getProvinceID()).getOccupiedByCivID()).getColor(fAlpha));
        }

        Renderer.shaderAlpha_Pattern.setUniformf("u_maskScale", (float)this.provinceBG.getWidth() / ((float)ImageManager.getImage(Images.pattOccupied).getWidth() / Game.DRAW_OCCUPIED_SCALE));
        Renderer.shaderAlpha_Pattern.setUniformf("u_maskScaleY", (float)this.provinceBG.getHeight() / ((float)ImageManager.getImage(Images.pattOccupied).getHeight() / Game.DRAW_OCCUPIED_SCALE));
        this.provinceBG.getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.pattOccupied).draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, (int)((float)this.provinceBG.getWidth() * Game.mapBG.iMapExtraScale), (int)((float)this.provinceBG.getHeight() * Game.mapBG.iMapExtraScale));
        oSB.flush();
    }

    public final void drawOccupiedProvince_Religion(SpriteBatch oSB) {
        oSB.setColor(new Color(Game.religionManager.getReligion(Game.getCiv(this.getCivID()).getReligionID()).Color[0], Game.religionManager.getReligion(Game.getCiv(this.getCivID()).getReligionID()).Color[1], Game.religionManager.getReligion(Game.getCiv(this.getCivID()).getReligionID()).Color[2], Game.settingsManager.OCCUPIED_PROVINCE_ALPHA));
        float fMapScale = (Game.mapScale.getCurrentScale() > 1.0F ? Game.mapScale.getCurrentScale() : 1.0F) * Game.DRAW_OCCUPIED_SCALE;
        Renderer.shaderAlpha_Pattern.setUniformf("u_maskScale", (float)this.provinceBG.getWidth() / ((float)ImageManager.getImage(Images.patt3).getWidth() / fMapScale));
        Renderer.shaderAlpha_Pattern.setUniformf("u_maskScaleY", (float)this.provinceBG.getHeight() / ((float)ImageManager.getImage(Images.patt3).getHeight() / fMapScale));
        this.provinceBG.getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        ImageManager.getImage(Images.patt3).draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, (int)((float)this.provinceBG.getWidth() * Game.mapBG.iMapExtraScale), (int)((float)this.provinceBG.getHeight() * Game.mapBG.iMapExtraScale));
        oSB.flush();
    }

    public final void draw(SpriteBatch oSB, int nPosX, int nPosY, float scale, int nAlpha) {
        try {
            this.setCivilizationProvinceColor(oSB, this.getCivID(), (float)nAlpha / 255.0F);
            this.provinceBG.draw(oSB, nPosX + (int)Math.floor((double)((float)this.iMinX * scale)), nPosY + (int)Math.floor((double)((float)this.iMinY * scale)), Game.mapBG.iMapExtraScale * scale);
        } catch (Exception var7) {
        }

    }

    public final void draw_FogOfWarDiscovery(SpriteBatch oSB, int nPosX, int nPosY, float scale, int nAlpha) {
        try {
            if (CFG.getMetProvince(this.getProvinceID())) {
                this.setCivilizationProvinceColor(oSB, this.getCivID(), (float)nAlpha / 255.0F);
                this.provinceBG.draw(oSB, nPosX + (int)Math.floor((double)((float)this.iMinX * scale)), nPosY + (int)Math.floor((double)((float)this.iMinY * scale)), Game.mapBG.iMapExtraScale * scale);
            }
        } catch (NullPointerException var7) {
            this.draw(oSB, nPosX, nPosY, scale, nAlpha);
        }

    }

    public final void drawWasteland(SpriteBatch oSB, int nPosX, int nPosY, float scale, int nAlpha) {
        oSB.setColor(ProvinceDraw.getWastelandColor(this.getWasteland(), (float)nAlpha / 255.0F));
        this.provinceBG.draw(oSB, nPosX + (int)Math.floor((double)((float)this.iMinX * scale)), nPosY + (int)Math.floor((double)((float)this.iMinY * scale)), Game.mapBG.iMapExtraScale * scale);
    }

    public final void loadProvinceBG() {
        try {
            if (this.getSeaProvince() && !GameValues.value.LOAD_SEA_PROVINCES) {
                return;
            }

            StringBuilder var10000 = (new StringBuilder()).append("map/").append(Game.map.getFile_ActiveMap_Path()).append("data/").append("scales/");
            Continents var10002 = Game.continents;
            if (FileManager.loadFile(var10000.append(this.iLevelOfPort == -4 ? 1 : (int)((float)Game.mapBG.iMapScale / Game.mapBG.iMapExtraScale)).append("/").append(this.iProvinceID).toString()).exists()) {
                var10000 = (new StringBuilder()).append("map/").append(Game.map.getFile_ActiveMap_Path()).append("data/").append("scales/");
                var10002 = Game.continents;
                Pixmap pixmap = PixmapIO.readCIM(FileManager.loadFile(var10000.append(this.iLevelOfPort == -4 ? 1 : (int)((float)Game.mapBG.iMapScale / Game.mapBG.iMapExtraScale)).append("/").append(this.iProvinceID).toString()));
                this.provinceBG = new Image(new Texture(pixmap), TextureFilter.Nearest, TextureWrap.ClampToEdge);
                pixmap.dispose();
                Object var3 = null;
            } else {
                this.buildProvinceBG(false);
                this.loadProvinceBG();
            }
        } catch (GdxRuntimeException ex) {
            CFG.exceptionStack(ex);
            CFG.LOG("Build province BG: " + this.iProvinceID);
            this.buildProvinceBG(false);
            this.loadProvinceBG();
        }

    }

    public final Image getProvinceBG() {
        return this.provinceBG;
    }

    public final void disposeProvinceBG() {
        this.provinceBG.getTexture().dispose();
    }

    public final void setBG(Pixmap pixmap) {
        this.provinceBG.getTexture().dispose();
        this.provinceBG = null;
        this.provinceBG = new Image(new Texture(pixmap), TextureFilter.Nearest, TextureWrap.ClampToEdge);
    }

    public final void buildProvinceBG(boolean overwriteExistingFiles) {
        int tempMapScaleBefore = Game.mapBG.iMapScale;
        FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "scales/" + "AvailableScales.txt");
        String tempT = tempFileT.readString();
        String[] tagsSPLITED = tempT.split(";");
        List<String> tempL = new ArrayList();

        for(int i = 0; i < tagsSPLITED.length; ++i) {
            tempL.add(tagsSPLITED[i]);
        }

        boolean addStandardScale = true;
        boolean addScale1 = this.iLevelOfPort == -4;

        for(int i = 0; i < tempL.size(); ++i) {
            if (Integer.parseInt((String)tempL.get(i)) == Game.map.getActiveMap_MapData().mapData.DefaultMapScale) {
                addStandardScale = false;
                break;
            }
        }

        for(int i = 0; i < tempL.size(); ++i) {
            if (Integer.parseInt((String)tempL.get(i)) == 1) {
                addScale1 = false;
                break;
            }
        }

        if (addStandardScale) {
            tempL.add("" + Game.map.getActiveMap_MapData().mapData.DefaultMapScale);
        }

        if (addScale1 && Game.map.getActiveMap_MapData().mapData.DefaultMapScale != 1) {
            tempL.add("1");
        }

        for(int i = 0; i < tempL.size(); ++i) {
            Game.mapBG.iMapScale = Integer.parseInt((String)tempL.get(i));
            if (overwriteExistingFiles || !FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "scales/" + Game.mapBG.iMapScale + "/" + this.iProvinceID).exists()) {
                Pixmap pixmap = new Pixmap(this.iMaxX_Real * Game.mapBG.iMapScale - this.iMinX_Real * Game.mapBG.iMapScale, this.iMaxY_Real * Game.mapBG.iMapScale - this.iMinY_Real * Game.mapBG.iMapScale, Format.Alpha);
                pixmap.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
                if (Game.cutProvinces == null) {
                    LoadManager.loadProvincePoints_Cut();
                }

                for(int y = 0; y < pixmap.getHeight(); ++y) {
                    for(int x = 0; x < pixmap.getWidth(); ++x) {
                        boolean addCrop = true;
                        if (!Game.getProvince(this.iProvinceID).getSeaProvince()) {
                            int z = 0;

                            for(int zSize = Game.cutProvinces.size(); z < zSize; ++z) {
                                if (Game.pathContains_Cut(z, this.iMinX_Real * Game.mapBG.iMapScale + x, this.iMinY_Real * Game.mapBG.iMapScale + y)) {
                                    addCrop = false;
                                }
                            }
                        }

                        if (addCrop) {
                            if (Game.pathContains(this.iProvinceID, this.iMinX_Real * Game.mapBG.iMapScale + x, this.iMinY_Real * Game.mapBG.iMapScale + y)) {
                                boolean add = true;

                                for(int a = 0; a < this.getNeighboringProvincesSize(); ++a) {
                                    if (this.iProvinceID > this.getNeighboringProvinces(a) && Game.pathContains(this.getNeighboringProvinces(a), this.iMinX_Real * Game.mapBG.iMapScale + x, this.iMinY_Real * Game.mapBG.iMapScale + y)) {
                                        add = false;
                                    }
                                }

                                if (add) {
                                    pixmap.drawPixel(x, y);
                                }
                            } else {
                                boolean add = false;
                                boolean check = false;
                                if (Game.pathContains(this.iProvinceID, this.iMinX_Real * Game.mapBG.iMapScale + x + 1, this.iMinY_Real * Game.mapBG.iMapScale + y)) {
                                    check = true;
                                }

                                if (Game.pathContains(this.iProvinceID, this.iMinX_Real * Game.mapBG.iMapScale + x, this.iMinY_Real * Game.mapBG.iMapScale + y + 1)) {
                                    check = true;
                                }

                                if (Game.pathContains(this.iProvinceID, this.iMinX_Real * Game.mapBG.iMapScale + x - 1, this.iMinY_Real * Game.mapBG.iMapScale + y)) {
                                    check = true;
                                }

                                if (Game.pathContains(this.iProvinceID, this.iMinX_Real * Game.mapBG.iMapScale + x, this.iMinY_Real * Game.mapBG.iMapScale + y - 1)) {
                                    check = true;
                                }

                                if (check) {
                                    boolean edn = false;

                                    for(int a = 0; a < this.getNeighboringProvincesSize(); ++a) {
                                        if (Game.pathContains(this.getNeighboringProvinces(a), this.iMinX_Real * Game.mapBG.iMapScale + x, this.iMinY_Real * Game.mapBG.iMapScale + y)) {
                                            edn = true;
                                            break;
                                        }
                                    }

                                    if (!edn) {
                                        for(int a = 0; a < this.getNeighboringProvincesSize(); ++a) {
                                            if (this.iProvinceID > this.getNeighboringProvinces(a)) {
                                                if (Game.pathContains(this.getNeighboringProvinces(a), this.iMinX_Real * Game.mapBG.iMapScale + x + 1, this.iMinY_Real * Game.mapBG.iMapScale + y)) {
                                                    add = true;
                                                    break;
                                                }

                                                if (Game.pathContains(this.getNeighboringProvinces(a), this.iMinX_Real * Game.mapBG.iMapScale + x, this.iMinY_Real * Game.mapBG.iMapScale + y + 1)) {
                                                    add = true;
                                                    break;
                                                }

                                                if (Game.pathContains(this.getNeighboringProvinces(a), this.iMinX_Real * Game.mapBG.iMapScale + x - 1, this.iMinY_Real * Game.mapBG.iMapScale + y)) {
                                                    add = true;
                                                    break;
                                                }

                                                if (Game.pathContains(this.getNeighboringProvinces(a), this.iMinX_Real * Game.mapBG.iMapScale + x, this.iMinY_Real * Game.mapBG.iMapScale + y - 1)) {
                                                    add = true;
                                                    break;
                                                }
                                            }
                                        }

                                        if (add) {
                                            pixmap.drawPixel(x, y);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                PixmapIO.writeCIM(FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "scales/" + Integer.parseInt((String)tempL.get(i)) + "/" + this.iProvinceID), pixmap);
                pixmap.dispose();
                Game.menuManager.addToast(new Toast("-- PROVINCE DATA GENERATED " + this.iProvinceID + " --"));
            }
        }

        Gdx.app.log("AoC", "PROVINCE: " + this.iProvinceID);
        Game.mapBG.iMapScale = tempMapScaleBefore;
    }

    public final void buildProvinceBG_Just(boolean overwriteExistingFiles) {
        int tempMapScaleBefore = Game.mapBG.iMapScale;
        FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "scales/" + "AvailableScales.txt");
        String tempT = tempFileT.readString();
        String[] tagsSPLITED = tempT.split(";");
        List<String> tempL = new ArrayList();

        for(int i = 0; i < tagsSPLITED.length; ++i) {
            tempL.add(tagsSPLITED[i]);
        }

        boolean addStandardScale = true;
        boolean addScale1 = this.iLevelOfPort == -4;

        for(int i = 0; i < tempL.size(); ++i) {
            if (Integer.parseInt((String)tempL.get(i)) == Game.map.getActiveMap_MapData().mapData.DefaultMapScale) {
                addStandardScale = false;
                break;
            }
        }

        for(int i = 0; i < tempL.size(); ++i) {
            if (Integer.parseInt((String)tempL.get(i)) == 1) {
                addScale1 = false;
                break;
            }
        }

        if (addStandardScale) {
            tempL.add("" + Game.map.getActiveMap_MapData().mapData.DefaultMapScale);
        }

        if (addScale1 && Game.map.getActiveMap_MapData().mapData.DefaultMapScale != 1) {
            tempL.add("1");
        }

        for(int i = 0; i < tempL.size(); ++i) {
            Game.mapBG.iMapScale = Integer.parseInt((String)tempL.get(i));
            if (overwriteExistingFiles || !FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "scales/" + Game.mapBG.iMapScale + "/" + this.iProvinceID).exists()) {
                Pixmap pixmap = new Pixmap(this.iMaxX_Real * Game.mapBG.iMapScale - this.iMinX_Real * Game.mapBG.iMapScale, this.iMaxY_Real * Game.mapBG.iMapScale - this.iMinY_Real * Game.mapBG.iMapScale, Format.Alpha);
                pixmap.setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
                if (Game.cutProvinces == null) {
                    LoadManager.loadProvincePoints_Cut();
                }

                for(int y = 0; y < pixmap.getHeight(); ++y) {
                    for(int x = 0; x < pixmap.getWidth(); ++x) {
                        boolean addCrop = true;
                        if (Game.pathContains(this.iProvinceID, this.iMinX_Real * Game.mapBG.iMapScale + x, this.iMinY_Real * Game.mapBG.iMapScale + y)) {
                            boolean add = true;
                            if (add) {
                                pixmap.drawPixel(x, y);
                            }
                        }
                    }
                }

                PixmapIO.writeCIM(FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "data/" + "scales/" + Integer.parseInt((String)tempL.get(i)) + "/" + this.iProvinceID), pixmap);
                pixmap.dispose();
                Game.menuManager.addToast(new Toast("-- PROVINCE DATA GENERATED " + this.iProvinceID + " --"));
            }
        }

        Gdx.app.log("AoC", "PROVINCE: " + this.iProvinceID);
        Game.mapBG.iMapScale = tempMapScaleBefore;
    }

    public final int getMinX() {
        return this.iMinX;
    }

    public final int getMaxX() {
        return this.iMaxX;
    }

    public final int getMinY() {
        return this.iMinY;
    }

    public final int getMaxY() {
        return this.iMaxY;
    }

    public final int getPointsX(int i) {
        return (Short)this.lPointsX.get(i) * Game.mapBG.iMapScale;
    }

    public final int getPointsY(int i) {
        return (Short)this.lPointsY.get(i) * Game.mapBG.iMapScale;
    }

    public final int getCenterX() {
        return this.iCenterX;
    }

    public final int getCenterY() {
        return this.iCenterY;
    }

    public final int getCenterX_Real() {
        return this.iCenterX_Real;
    }

    public final int getCenterY_Real() {
        return this.iCenterY_Real;
    }

    public final int getShiftX() {
        return this.iShiftX;
    }

    public final int getShiftY() {
        return this.iShiftY;
    }

    public final void setShiftX(int iShiftX) {
        this.iShiftX = iShiftX;
        this.iCenterShiftX = this.getCenterX() + this.getShiftX();
    }

    public final void setShiftY(int iShiftY) {
        this.iShiftY = iShiftY;
        this.iCenterShiftY = this.getCenterY() + this.getShiftY();
    }

    public final int getPortShiftX() {
        return this.iPortShiftX;
    }

    public final int getPortShiftY() {
        return this.iPortShiftY;
    }

    public final void setPortShiftX(int iPortShiftX) {
        this.iPortShiftX = iPortShiftX;
    }

    public final void setPortShiftY(int iPortShiftY) {
        this.iPortShiftY = iPortShiftY;
    }

    public final int getPointsSize() {
        return this.iPointsSize;
    }

    public final int getTranslateProvincePosX() {
        return this.iTranslateProvincePosX;
    }

    public final void setTranslateProvincePosX(int iTranslateProvincePosX) {
        this.iTranslateProvincePosX = iTranslateProvincePosX;
    }

    public final boolean getDrawProvince() {
        return this.drawProvince;
    }

    public final void setDrawProvince(boolean drawProvince) {
        this.drawProvince = drawProvince;
    }

    public final void buildDistanceToCapital() {
        if (this.getCivID() > 0 && Game.getCiv(this.getCivID()).getCapitalProvinceID() >= 0) {
            this.aiDistanceToCapital = Math.min(0.99F, Math.max(0.001F, Game.getDistanceFromProvinceToProvince(Game.getCiv(this.getCivID()).getCapitalProvinceID(), this.getProvinceID()) / Game.iMaxDistance));
        }

    }

    public final int getProvinceID() {
        return this.iProvinceID;
    }

    public final int getCivID() {
        return Game.getProvinceData(this.getProvinceID()).getCivID();
    }

    public final void setCivID_Just(int nCivID) {
        Game.getProvinceData(this.getProvinceID()).setOccupiedByCivID(this.getProvinceID(), 0);
        Game.getProvinceData(this.getProvinceID()).setCivID(nCivID);
        Game.mapCities.updateNameToNewTrueOwner(this.getProvinceID(), false);
    }

    public final void setCivID(int nCivID) {
        if (nCivID != Game.getProvinceData(this.getProvinceID()).getCivID()) {
            if (Game.getProvinceData(this.getProvinceID()).getCivID() > 0) {
                Game.getCiv(Game.getProvinceData(this.getProvinceID()).getCivID()).removeProvince(this.getProvinceID());
                if (this.wonderID >= 0 && this.getWonderBuilt()) {
                    WondersManager.updateCivBonuses(this.getCivID(), this.wonderID, -1);
                    WondersManager.updateProvinceBonuses(this.getProvinceID(), this.wonderID, -1);
                }
            }

            boolean updateBonuses = this.isCapital && Game.getCiv(Game.getProvinceData(this.getProvinceID()).getCivID()).getCapitalProvinceID() == this.getProvinceID();
            if (updateBonuses) {
                for(int i = 0; i < this.iBuildingsSize; ++i) {
                    BonusesManager.updateBuildingBonuses(this.getProvinceID(), ((ProvinceConstructedBuilding)this.buildings.get(i)).getBuilding(), ((ProvinceConstructedBuilding)this.buildings.get(i)).getBuildingID(), -1);
                }
            }

            Game.addSimpleTask(new Game.SimpleTask("buildCivilizationsRegion" + Game.getProvinceData(this.getProvinceID()).getCivID(), Game.getProvinceData(this.getProvinceID()).getCivID()) {
                public void update() {
                    CivilizationRegionsManager.buildCivilizationsRegion(this.id);
                    Game.updateProvincesInView = true;
                    CivilizationRegionsManager.updateRegionsInView = true;
                }
            });
            Game.addSimpleTask(new Game.SimpleTask("updateCivStability" + this.getCivID(), this.getCivID()) {
                public void update() {
                    Game.getCiv(this.id).updateCivStability();
                }
            });
            Game.addSimpleTask(new Game.SimpleTask("buildNeighbors" + this.getCivID(), this.getCivID()) {
                public void update() {
                    Game.getCiv(this.id).civNeighbors.buildNeighbors(this.id);
                }
            });
            Game.getProvinceData(this.getProvinceID()).setOccupiedByCivID(this.getProvinceID(), 0);
            Game.getProvinceData(this.getProvinceID()).setCivID(nCivID);
            Game.addSimpleTask(new Game.SimpleTask("updateCivStability" + this.getCivID(), this.getCivID()) {
                public void update() {
                    Game.getCiv(this.id).updateCivStability();
                }
            });
            Game.addSimpleTask(new Game.SimpleTask("buildNeighbors" + this.getCivID(), this.getCivID()) {
                public void update() {
                    Game.getCiv(this.id).civNeighbors.buildNeighbors(this.id);
                }
            });
            if (updateBonuses) {
                for(int i = 0; i < this.iBuildingsSize; ++i) {
                    BonusesManager.updateBuildingBonuses(this.getProvinceID(), ((ProvinceConstructedBuilding)this.buildings.get(i)).getBuilding(), ((ProvinceConstructedBuilding)this.buildings.get(i)).getBuildingID(), 1);
                }
            }

            if (Game.getProvinceData(this.getProvinceID()).getCivID() > 0) {
                Game.getCiv(Game.getProvinceData(this.getProvinceID()).getCivID()).addProvince(this.getProvinceID());
                if (this.wonderID >= 0 && this.getWonderBuilt()) {
                    WondersManager.updateCivBonuses(this.getCivID(), this.wonderID, 1);
                    WondersManager.updateProvinceBonuses(this.getProvinceID(), this.wonderID, 1);
                }
            }

            if (this.religionConversion != null) {
                Game.gameThreadTurns.removeProvinceConvertReligion(this.getProvinceID());
            }

            if (this.coreCreation != null) {
                Game.gameThreadTurns.removeProvinceCoreCreation(this.getProvinceID());
            }

            this.religionConversion = null;
            this.coreCreation = null;
            this.haveACore = false;

            for(int i = 0; i < this.iCoresSize; ++i) {
                if ((Integer)Game.getProvinceData5(this.getProvinceID()).co.get(i) == this.getCivID()) {
                    this.haveACore = true;
                    break;
                }
            }

            if (this.isCapital && Game.getCiv(this.getCivID()).getCapitalProvinceID() != this.getProvinceID()) {
                this.setIsCapital(false);
            }

            this.updateProvinceIncome();
            Game.mapCities.updateNameToNewTrueOwner(this.getProvinceID(), false);
            Game.updateProvinceBorder(this.getProvinceID());
            Game.addSimpleTask(new Game.SimpleTask("buildCivilizationsRegion" + nCivID, nCivID) {
                public void update() {
                    CivilizationRegionsManager.buildCivilizationsRegion(this.id);
                    Game.updateProvincesInView = true;
                    CivilizationRegionsManager.updateRegionsInView = true;
                }
            });
            if (this.drawProvince) {
                FBOProvincesBG.redrawnProvinces();
            }

            this.buildDistanceToCapital();
        }
    }

    public final void setCivID_RemoveOldAddNewToCiv(int nCivID) {
        if (nCivID != Game.getProvinceData(this.getProvinceID()).getCivID()) {
            if (Game.getProvinceData(this.getProvinceID()).getCivID() > 0) {
                Game.getCiv(Game.getProvinceData(this.getProvinceID()).getCivID()).removeProvince(this.getProvinceID());
                if (this.wonderID >= 0 && this.getWonderBuilt()) {
                    WondersManager.updateCivBonuses(this.getCivID(), this.wonderID, -1);
                    WondersManager.updateProvinceBonuses(this.getProvinceID(), this.wonderID, -1);
                }
            }

            Game.getProvinceData(this.getProvinceID()).setOccupiedByCivID(this.getProvinceID(), 0);
            Game.getProvinceData(this.getProvinceID()).setCivID(nCivID);
            this.clearCores();
            if (nCivID > 0) {
                this.addCore_Just(nCivID);
            }

            Game.mapCities.updateNameToNewTrueOwner(this.getProvinceID(), false);
            if (Game.getProvinceData(this.getProvinceID()).getCivID() > 0) {
                Game.getCiv(Game.getProvinceData(this.getProvinceID()).getCivID()).addProvince(this.getProvinceID());
                if (this.wonderID >= 0 && this.getWonderBuilt()) {
                    WondersManager.updateCivBonuses(this.getCivID(), this.wonderID, 1);
                    WondersManager.updateProvinceBonuses(this.getProvinceID(), this.wonderID, 1);
                }
            }

        }
    }

    public final void setCivID_LoadScenario(int nCivID) {
        Game.getProvinceData(this.getProvinceID()).setCivID(nCivID);
    }

    public final boolean isOccupied() {
        return Game.getProvinceData(this.getProvinceID()).getOccupiedByCivID() != 0;
    }

    public final void updateSeaProvince() {
        this.seaProvince = this.getLevelOfPort() < -1;
    }

    public final boolean getSeaProvince() {
        return this.seaProvince;
    }

    public final int getWasteland() {
        return Game.getProvinceData(this.getProvinceID()).getWastelandLevel();
    }

    public final void setWasteland(int wastelandLevel) {
        Game.getProvinceData(this.getProvinceID()).setWastelandLevel(wastelandLevel);

        for(int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
            if (this.getProvinceID() < this.getNeighboringProvinces(i)) {
                if (wastelandLevel >= 0) {
                    if (Game.getProvince(this.getNeighboringProvinces(i)).getWasteland() >= 0) {
                        this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder_Just(false, this.getNeighboringProvinces(i));
                        this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsWastelandBorder(false, this.getNeighboringProvinces(i));
                    } else {
                        this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder_Just(this.getCivID() != Game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getNeighboringProvinces(i));
                        this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsWastelandBorder(true, this.getNeighboringProvinces(i));
                    }
                } else if (Game.getProvince(this.getNeighboringProvinces(i)).getWasteland() >= 0) {
                    this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder_Just(this.getCivID() != Game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getNeighboringProvinces(i));
                    this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsWastelandBorder(true, this.getNeighboringProvinces(i));
                } else {
                    this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsCivilizationBorder_Just(this.getCivID() != Game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getNeighboringProvinces(i));
                    this.getProvinceBordersLandByLand(this.getNeighboringProvinces(i)).setIsWastelandBorder(false, this.getNeighboringProvinces(i));
                }
            } else if (wastelandLevel >= 0) {
                if (Game.getProvince(this.getNeighboringProvinces(i)).getWasteland() >= 0) {
                    Game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder_Just(false, this.getProvinceID());
                    Game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsWastelandBorder(false, this.getProvinceID());
                } else {
                    Game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder_Just(this.getCivID() != Game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getProvinceID());
                    Game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsWastelandBorder(true, this.getProvinceID());
                }
            } else if (Game.getProvince(this.getNeighboringProvinces(i)).getWasteland() >= 0) {
                Game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder_Just(this.getCivID() != Game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getProvinceID());
                Game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsWastelandBorder(true, this.getProvinceID());
            } else {
                Game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsCivilizationBorder_Just(this.getCivID() != Game.getProvince(this.getNeighboringProvinces(i)).getCivID(), this.getProvinceID());
                Game.getProvince(this.getNeighboringProvinces(i)).getProvinceBordersLandByLand(this.getProvinceID()).setIsWastelandBorder(false, this.getProvinceID());
            }
        }

        Game.setUpdateProvincesInView(true);
    }

    public final boolean getBelowZero() {
        return this.belowZeroPosX;
    }

    public final int getCivRegionID() {
        return this.iCivRegionID;
    }

    public final void setCivRegionID(int iCivRegionID) {
        this.iCivRegionID = iCivRegionID;
    }

    public final int getMapModeRegionID() {
        return this.iMapModeRegion;
    }

    public final void setMapModeRegionID(int iMapModeRegion) {
        this.iMapModeRegion = iMapModeRegion;
    }

    public final int getContinent() {
        return this.iContinentID;
    }

    public final void setContinent(int iContinentID) {
        this.iContinentID = iContinentID;
    }

    public final int getGeoRegion() {
        return this.iGeoRegionID;
    }

    public final void setGeoRegion(int iGeoRegionID) {
        this.iGeoRegionID = iGeoRegionID;
    }

    public final int getTerrainID() {
        return this.iTerrainTypeID;
    }

    public final void setTerrainID(int iTerrainTypeID) {
        this.iTerrainTypeID = iTerrainTypeID;
    }

    public final int getResourceID() {
        return this.iResourceID;
    }

    public final void setResourceID(int iResourceID) {
        this.iResourceID = iResourceID;
    }

    public final int getLevelOfPort() {
        return this.iLevelOfPort;
    }

    public final void setLevelOfPort(int iLevelOfPort) {
        this.iLevelOfPort = iLevelOfPort;
    }

    public final float getGrowthRate() {
        return this.fBaseGrowthRate;
    }

    public final float getGrowthRateWithBonuses() {
        return Math.max(1.0F, (this.fBaseGrowthRate + this.provBonuses.LocalGrowthRate + Game.getProvinceData7(this.getProvinceID()).getIncreasedGrowthRate() + (float)Game.getProvinceData9(this.getProvinceID()).getColonizationGrowthRateExtra() + (float)((Terrain)Game.terrainManager.terrains.get(this.getTerrainID())).PopulationGrowth) * (1.0F + (Game.getCiv(this.getCivID()).civBonuses.GrowthRate + (float)this.getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_GROWTH_RATE_PER_LVL) / 100.0F));
    }

    public final float getGrowthRateWithBonuses_WithoutColonizationBonus() {
        return Math.max(1.0F, this.fBaseGrowthRate + this.provBonuses.LocalGrowthRate + Game.getProvinceData7(this.getProvinceID()).getIncreasedGrowthRate() + (float)((Terrain)Game.terrainManager.terrains.get(this.getTerrainID())).PopulationGrowth) * (1.0F + (Game.getCiv(this.getCivID()).civBonuses.GrowthRate + (float)this.getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_GROWTH_RATE_PER_LVL) / 100.0F);
    }

    public final void setGrowthRate(float fGrowthRate) {
        this.fBaseGrowthRate = fGrowthRate;
    }

    public final int getProvinceBordersLandByLandSize() {
        return this.iProvinceBordersLandByLandSize;
    }

    public final int getProvinceBordersLandBySeaSize() {
        return this.iProvinceBordersLandBySeaSize;
    }

    public final int getProvinceBordersSeaBySeaSize() {
        return this.iProvinceBordersSeaBySeaSize;
    }

    public final List<ProvinceBorder> getProvinceBordersLandByLand() {
        return this.lProvinceBordersLandByLand;
    }

    public final List<ProvinceBorder> getProvinceBordersLandBySea() {
        return this.lProvinceBordersLandBySea;
    }

    public final List<ProvinceBorder> getProvinceBordersSeaBySea() {
        return this.lProvinceBordersSeaBySea;
    }

    public final ProvinceBorder getProvinceBordersAll(int withProvinceID) {
        for(int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (withProvinceID == ((ProvinceBorder)this.lProvinceBordersLandByLand.get(i)).getWithProvinceID()) {
                return (ProvinceBorder)this.lProvinceBordersLandByLand.get(i);
            }
        }

        for(int i = 0; i < this.iProvinceBordersLandBySeaSize; ++i) {
            if (withProvinceID == ((ProvinceBorder)this.lProvinceBordersLandBySea.get(i)).getWithProvinceID()) {
                return (ProvinceBorder)this.lProvinceBordersLandBySea.get(i);
            }
        }

        for(int i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            if (withProvinceID == ((ProvinceBorder)this.lProvinceBordersSeaBySea.get(i)).getWithProvinceID()) {
                return (ProvinceBorder)this.lProvinceBordersSeaBySea.get(i);
            }
        }

        return new ProvinceBorder(0, new ArrayList(), new ArrayList());
    }

    public final ProvinceBorder getProvinceBordersLandByLand(int withProvinceID) {
        for(int i = 0; i < this.iProvinceBordersLandByLandSize; ++i) {
            if (withProvinceID == ((ProvinceBorder)this.lProvinceBordersLandByLand.get(i)).getWithProvinceID()) {
                return (ProvinceBorder)this.lProvinceBordersLandByLand.get(i);
            }
        }

        return new ProvinceBorder(0, new ArrayList(), new ArrayList());
    }

    public final ProvinceBorder getProvinceBordersLandBySea(int withProvinceID) {
        for(int i = 0; i < this.iProvinceBordersLandBySeaSize; ++i) {
            if (withProvinceID == ((ProvinceBorder)this.lProvinceBordersLandBySea.get(i)).getWithProvinceID()) {
                return (ProvinceBorder)this.lProvinceBordersLandBySea.get(i);
            }
        }

        return new ProvinceBorder(0, new ArrayList(), new ArrayList());
    }

    public final ProvinceBorder getProvinceBordersSeaBySea(int withProvinceID) {
        for(int i = 0; i < this.iProvinceBordersSeaBySeaSize; ++i) {
            if (withProvinceID == ((ProvinceBorder)this.lProvinceBordersSeaBySea.get(i)).getWithProvinceID()) {
                return (ProvinceBorder)this.lProvinceBordersSeaBySea.get(i);
            }
        }

        return new ProvinceBorder(0, new ArrayList(), new ArrayList());
    }

    public final int getNeighboringProvinces(int i) {
        return (Integer)this.lNeighboringProvinces.get(i);
    }

    public final int getNeighboringSeaProvinces(int i) {
        return (Integer)this.lNeighboringSeaProvinces.get(i);
    }

    public final void addNeighboringProvince(int nProvinceID) {
        this.lNeighboringProvinces.add(nProvinceID);
        this.iNeighboringProvincesSize = this.lNeighboringProvinces.size();
    }

    public final void removeNeighboringProvince(int nProvinceID) {
        for(int i = 0; i < this.iNeighboringProvincesSize; ++i) {
            if (nProvinceID == this.getNeighboringProvinces(i)) {
                this.lNeighboringProvinces.remove(i);
                this.iNeighboringProvincesSize = this.lNeighboringProvinces.size();
                return;
            }
        }

    }

    public final void addNeighboringSeaProvince(int nProvinceID) {
        this.lNeighboringSeaProvinces.add(nProvinceID);
        this.iNeighboringSeaProvincesSize = this.lNeighboringSeaProvinces.size();
        this.updateAccessToMainSea();
    }

    public final void removeNeighboringSeaProvince(int nProvinceID) {
        for(int i = 0; i < this.iNeighboringSeaProvincesSize; ++i) {
            if (nProvinceID == this.getNeighboringSeaProvinces(i)) {
                this.lNeighboringSeaProvinces.remove(i);
                this.iNeighboringSeaProvincesSize = this.lNeighboringSeaProvinces.size();
                this.updateAccessToMainSea();
                return;
            }
        }

    }

    public void updateAccessToMainSea() {
        this.accessToMainSea = false;

        for(int i = 0; i < this.iNeighboringSeaProvincesSize; ++i) {
            if (Game.getProvince(this.getNeighboringSeaProvinces(i)).getLevelOfPort() == -2) {
                this.accessToMainSea = true;
                return;
            }
        }

    }

    public final int getNeighboringProvincesSize() {
        return this.iNeighboringProvincesSize;
    }

    public final int getNeighboringSeaProvincesSize() {
        return this.iNeighboringSeaProvincesSize;
    }

    public final String getProvinceName() {
        return this.sProvinceName;
    }

    public final String getProvinceNameUpperCase() {
        return this.sProvinceNameUpperCase;
    }

    public final void setProvinceName(String sProvinceName) {
        this.sProvinceName = sProvinceName;
        this.sProvinceNameUpperCase = sProvinceName.toUpperCase();
        this.iProvinceNameLength_Minus1 = this.sProvinceNameUpperCase.length() - 1;
    }

    public final void drawCities(SpriteBatch oSB, float nScale, float fAlpha, float fAlpha2, float fontScale) {
        if (this.haveCity) {
            ((City)this.lCities.get(0)).drawCity(oSB, this.iProvinceID, nScale, fAlpha, fAlpha2, fontScale);
        }

    }

    public final void drawCities_InGame(SpriteBatch oSB, float nScale, float fAlpha, float fAlpha2, float fontScale) {
        if (this.haveCity) {
            ((City)this.lCities.get(0)).drawCity_InGame(oSB, this.iProvinceID, nScale, fAlpha, fAlpha2, fontScale);
        }

    }

    public final void drawCities_InGame_NamesLow(SpriteBatch oSB, float nScale, float fAlpha, float fAlpha2, float fontScale) {
        if (this.haveCity) {
            ((City)this.lCities.get(0)).drawCity_InGame_NamesLow(oSB, this.iProvinceID, nScale, fAlpha, fAlpha2, fontScale);
        }

    }

    public final void drawCities_InGame_NamesLow_OnlyName(SpriteBatch oSB, float nScale, float fAlpha, float fAlpha2, float fontScale) {
        if (this.haveCity) {
            ((City)this.lCities.get(0)).drawCity_InGame_NamesLow_OnlyName(oSB, this.iProvinceID, nScale, fAlpha, fAlpha2, fontScale);
        }

    }

    public final void drawCities_InGame_CivFlagName(SpriteBatch oSB, float nScale, float fAlpha) {
        if (this.isCapital && this.haveCity) {
            ((City)this.lCities.get(0)).drawCity_CivFlagName(oSB, this.iProvinceID, nScale, fAlpha);
        }

    }

    public final void drawCities_InGame_CivFlag(SpriteBatch oSB, float nScale, float fAlpha) {
        if (this.haveCity) {
            ((City)this.lCities.get(0)).drawCity_CivFlag(oSB, this.iProvinceID, nScale, fAlpha);
        }

    }

    public final void drawCities_InGame_CivFlagWar(SpriteBatch oSB, float nScale, float fAlpha) {
        if (this.haveCity) {
            ((City)this.lCities.get(0)).drawCityName_Capital_CivFlag_War(oSB, this.iProvinceID, nScale, fAlpha);
        }

    }

    public final void drawCities_NamesNotCapital(SpriteBatch oSB, float nScale, float fAlpha, float fAlpha2, float fontScale) {
        if (this.haveCity) {
            ((City)this.lCities.get(0)).drawCity_NameNotCapital(oSB, this.iProvinceID, nScale, fAlpha, fontScale);
        }

    }

    public final void addCity(City oCity) {
        this.lCities.add(oCity);
        this.iCitiesSize = this.lCities.size();
        this.haveCity = this.iCitiesSize > 0;
    }

    public final City getCity(int i) {
        return (City)this.lCities.get(i);
    }

    public final int getCitiesSize() {
        return this.iCitiesSize;
    }

    public final void clearCities() {
        this.lCities = new ArrayList();
        this.iCitiesSize = 0;
        this.haveCity = false;
    }

    public final boolean getDrawCities() {
        return this.drawCities;
    }

    public final void setDrawCities(boolean drawCities) {
        this.drawCities = drawCities;
    }

    public int getPopulationTotal() {
        return this.provincePopulationTotal;
    }

    public final void updatePopulationOfProvince() {
        this.provincePopulationTotal = 0;

        for(int i = 0; i < this.provincePopulationSize; ++i) {
            this.provincePopulationTotal += Game.getProvincePopulation(this.getProvinceID()).getPopulation(i).getPopulation();
        }

    }

    public final int getPopulationID(int nID) {
        return Game.getProvincePopulation(this.getProvinceID()).getPopulation(nID).getPopulation();
    }

    public final int getPopulationCivID(int nID) {
        return Game.getProvincePopulation(this.getProvinceID()).getPopulation(nID).getCivID();
    }

    public final int getPopulationSize() {
        return this.provincePopulationSize;
    }

    public final int getPopulationOfCivID(int nCivID) {
        ProvinceData_Population population = Game.getProvincePopulation(this.getProvinceID());

        for(int i = 0; i < this.provincePopulationSize; ++i) {
            if (population.getPopulation(i).getCivID() == nCivID) {
                return population.getPopulation(i).getPopulation();
            }
        }

        return 0;
    }

    public final boolean setPopulationOfCivID(int nCivID, int nPopulation) {
        try {
            for(int i = 0; i < this.provincePopulationSize; ++i) {
                if (Game.getProvincePopulation(this.getProvinceID()).getPopulation(i).getCivID() == nCivID) {
                    if (nPopulation <= 0) {
                        if (this.provincePopulationSize > 1) {
                            this.provincePopulationTotal -= Game.getProvincePopulation(this.getProvinceID()).getPopulation(i).getPopulation();
                            Game.getProvincePopulation(this.getProvinceID()).getPopulation().remove(i);
                            this.provincePopulationSize = Game.getProvincePopulation(this.getProvinceID()).getPopulation().size();
                            return true;
                        }

                        Game.getProvincePopulation(this.getProvinceID()).getPopulation(i).setPopulation(GameValues.province.MIN_POPULATION);
                        this.provincePopulationTotal = GameValues.province.MIN_POPULATION;
                    } else {
                        this.provincePopulationTotal -= Game.getProvincePopulation(this.getProvinceID()).getPopulation(i).getPopulation();
                        this.provincePopulationTotal += nPopulation;
                        Game.getProvincePopulation(this.getProvinceID()).getPopulation(i).setPopulation(nPopulation);
                    }

                    return false;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        try {
            if (nPopulation > 0) {
                Game.getProvincePopulation(this.getProvinceID()).getPopulation().add(new ProvincePopulation(nCivID, nPopulation));
                this.provincePopulationTotal += nPopulation;
                this.provincePopulationSize = Game.getProvincePopulation(this.getProvinceID()).getPopulation().size();
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return false;
    }

    public final void updateIncreasePopulationOfCivID(int nCivID, int nPopulation) {
        for(int i = 0; i < this.provincePopulationSize; ++i) {
            ProvincePopulation population = Game.getProvincePopulation(this.getProvinceID()).getPopulation(i);
            if (population.getCivID() == nCivID) {
                population.setPopulation(population.getPopulation() + nPopulation);
                this.provincePopulationTotal += nPopulation;
                return;
            }
        }

        Game.getProvincePopulation(this.getProvinceID()).getPopulation().add(new ProvincePopulation(nCivID, nPopulation));
        this.provincePopulationTotal += nPopulation;
        this.provincePopulationSize = Game.getProvincePopulation(this.getProvinceID()).getPopulation().size();
    }

    public final void autoAssimilate() {
        try {
            if (this.haveACore(this.getCivID())) {
                for(int i = this.provincePopulationSize - 1; i >= 0; --i) {
                    ProvincePopulation population = Game.getProvincePopulation(this.getProvinceID()).getPopulation(i);
                    if (population.getCivID() != this.getCivID()) {
                        int tPop = Math.max(Math.min(GameValues.province.AUTO_ASSIMILATION_MIN, population.getPopulation()), (int)((float)population.getPopulation() * GameValues.province.AUTO_ASSIMILATION_PERC));
                        if (population.getPopulation() < GameValues.province.AUTO_ASSIMILATION_ALL_IF_BELOW) {
                            tPop = population.getPopulation();
                        }

                        this.setPopulationOfCivID(this.getCivID(), this.getPopulationOfCivID(this.getCivID()) + tPop);
                        this.setPopulationOfCivID(population.getCivID(), population.getPopulation() - tPop);
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void buildPopulation_LoadGame() {
        this.provincePopulationSize = Game.getProvincePopulation(this.getProvinceID()).getPopulation().size();
        int tPopTotal = 0;

        for(int i = 0; i < this.provincePopulationSize; ++i) {
            tPopTotal += Game.getProvincePopulation(this.getProvinceID()).getPopulation(i).getPopulation();
        }

        this.provincePopulationTotal = tPopTotal;
    }

    public final void clearPopulationData() {
        this.provincePopulationTotal = 0;
        Game.getProvincePopulation(this.getProvinceID()).getPopulation().clear();
        this.provincePopulationSize = 0;
    }

    public float getBaseEconomy() {
        return this.BaseDevelopment;
    }

    public float getEconomy() {
        return Game.getProvinceData6(this.getProvinceID()).getEconomy();
    }

    public float getEconomyWithBonuses() {
        return Game.getProvinceData6(this.getProvinceID()).getEconomy() + this.provBonuses.Economy;
    }

    public int getReligion() {
        return Game.getProvinceData7(this.getProvinceID()).getReligionID();
    }

    public void setReligion(int iReligionID) {
        if (iReligionID >= 0 && iReligionID < Game.religionManager.getReligionsSize()) {
            Game.getProvinceData7(this.getProvinceID()).setReligionID(iReligionID);
            if (this.getCivID() > 0) {
                Game.getCiv(this.getCivID()).convertReligion.checkProvince(this.getProvinceID(), this.getCivID());
            }
        }

    }

    public void setReligion_LoadScenario(int iReligionID) {
        Game.getProvinceData7(this.getProvinceID()).setReligionID(iReligionID);
        if (this.getCivID() > 0 && iReligionID != Game.getCiv(this.getCivID()).getReligionID()) {
            Game.getCiv(this.getCivID()).convertReligion.addProvince(this.getProvinceID());
        }

    }

    public float getTaxEfficiency() {
        return Game.getProvinceData3(this.getProvinceID()).getTaxEfficiency();
    }

    public float getTaxEfficiencyWithBonuses() {
        return (Game.getProvinceData3(this.getProvinceID()).getTaxEfficiency() + Game.getProvince(this.getProvinceID()).provBonuses.LocalTaxEfficiency) * (1.0F + (Game.getCiv(Game.getProvince(this.getProvinceID()).getCivID()).civBonuses.TaxEfficiency + (float)this.getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_TAX_EFFICIENCY_PER_LVL) / 100.0F);
    }

    public float getProvinceIncome() {
        return this.fProvinceIncome;
    }

    public void setEconomy(float fEconomy) {
        Game.getProvinceData6(this.getProvinceID()).setEconomy(Math.max(0.1F, fEconomy));
    }

    public void setTaxEfficiency(float fTaxEfficiency) {
        Game.getProvinceData3(this.getProvinceID()).setTaxEfficiency(fTaxEfficiency);
    }

    public float getManpower() {
        return Game.getProvinceData3(this.getProvinceID()).getManpower();
    }

    public void setManpower(float fManpower) {
        Game.getProvinceData3(this.getProvinceID()).setManpower(Math.max(0.0F, fManpower));
    }

    public void setWonderBuilt(boolean built) {
        Game.getProvinceData8(this.getProvinceID()).setWonderBuilt(built);
    }

    public boolean getWonderBuilt() {
        return Game.getProvinceData8(this.getProvinceID()).isWonderBuilt();
    }

    public final void addBuildingConstruction_Load(ProvinceConstructionBuilding nConstruction) {
        this.buildingsConstruction.add(nConstruction);
        this.iBuildingsConstructionSize = this.buildingsConstruction.size();
        Game.gameThreadTurns.addProvinceBuildingsUnderConstruction(this.getProvinceID());
    }

    public final boolean addBuildingConstruction(int building, int buildingID) {
        return this.addBuildingConstruction(building, buildingID, false);
    }

    public final boolean addBuildingConstruction(int building, int buildingID, boolean fromMulti) {
        if (!fromMulti) {
            if (this.isOccupied()) {
                return false;
            }

            if (this.getUsedBuildingsSlots() >= this.iBuildingsLimit) {
                return false;
            }

            if (((BuildingsManager.Buildings)BuildingsManager.buildings.get(building)).RequiredResource >= 0 && ((BuildingsManager.Buildings)BuildingsManager.buildings.get(building)).RequiredResource != this.getResourceID()) {
                return false;
            }

            if (((BuildingsManager.Buildings)BuildingsManager.buildings.get(building)).RequiredGovernmentID >= 0 && ((BuildingsManager.Buildings)BuildingsManager.buildings.get(building)).RequiredGovernmentID != Game.getCiv(this.getCivID()).getIdeologyID()) {
                return false;
            }

            if (((BuildingsManager.Buildings)BuildingsManager.buildings.get(building)).RequiredReligionID >= 0 && ((BuildingsManager.Buildings)BuildingsManager.buildings.get(building)).RequiredReligionID != Game.getCiv(this.getCivID()).getReligionID()) {
                return false;
            }

            for(int i = 0; i < this.iBuildingsConstructionSize; ++i) {
                if (((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuilding() == building && ((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuildingID() == buildingID) {
                    return false;
                }
            }

            if (Game.getCiv(this.getCivID()).fGold < (float)Game.getBuildingConstructionCost(this.getCivID(), this.getProvinceID(), building, buildingID)) {
                return false;
            }

            if (((BuildingsManager.Buildings)BuildingsManager.buildings.get(building)).SeaAccessRequired && this.getLevelOfPort() < 0) {
                return false;
            }

            if (this.buildingBuilt(building, buildingID)) {
                return false;
            }

            if (!SteamMultiManager.isHost()) {
                M_BuildBuilding.requestCommand(this.getProvinceID(), building, buildingID);
                return true;
            }

            M_BuildBuilding.provinceBuild.add(new M_BuildBuilding.BuildBuildingData(this.getProvinceID(), building, buildingID));
        }

        if (((BuildingsManager.Buildings)BuildingsManager.buildings.get(building)).SeaAccessRequired && this.getLevelOfPort() < 0) {
            return false;
        } else if (this.buildingBuilt(building, buildingID)) {
            return false;
        } else {
            ProvinceConstructionBuilding nConstruction = new ProvinceConstructionBuilding(building, buildingID, Game.getBuildingConstructionTime(this.getCivID(), this.getProvinceID(), building, buildingID), Game.getBuildingConstructionTime(this.getCivID(), this.getProvinceID(), building, buildingID));
            Civilization var10000 = Game.getCiv(this.getCivID());
            var10000.fGold -= (float)Game.getBuildingConstructionCost(this.getCivID(), this.getProvinceID(), building, buildingID);
            this.buildingsConstruction.add(nConstruction);
            this.iBuildingsConstructionSize = this.buildingsConstruction.size();
            Game.gameThreadTurns.addProvinceBuildingsUnderConstruction(this.getProvinceID());
            if (this.getCivID() == Game.player.iCivID) {
                Game.menuManager.addRebuildInGame_RightQueue();
            }

            //ANALYTICALENGINE START
            Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
            try {
                invocable.invokeFunction("parseOnBuildingConstruction", this, nConstruction);
            } catch (ScriptException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            //ANALYTICALENGINE END

            return true;
        }
    }

    public final boolean cancelBuildingConstruction(int nCivID, int building, int buildingID) {
        return this.cancelBuildingConstruction(nCivID, building, buildingID, false);
    }

    public final boolean cancelBuildingConstruction(int nCivID, int building, int buildingID, boolean fromMulti) {
        if (!fromMulti) {
            if (this.isOccupied()) {
                return false;
            }

            M_BuildBuildingCancel.serializeCommand(this.getProvinceID(), building, buildingID);
        }

        for(int i = 0; i < this.iBuildingsConstructionSize; ++i) {
            if (((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuilding() == building && ((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuildingID() == buildingID) {
                Civilization var10000 = Game.getCiv(this.getCivID());
                var10000.fGold += (float)Game.getBuildingConstructionCost(this.getCivID(), this.getProvinceID(), building, buildingID) * ((float)((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getConstructionTimeLeft() / (float)((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getConstructionTime());
                this.buildingsConstruction.remove(i);
                this.iBuildingsConstructionSize = this.buildingsConstruction.size();
                if (this.iBuildingsConstructionSize == 0) {
                    Game.gameThreadTurns.removeProvinceBuildingsUnderConstruction(this.getProvinceID());
                }

                if (this.getCivID() == Game.player.iCivID) {
                    Game.menuManager.addRebuildInGame_RightQueue();
                }

                return true;
            }
        }

        return false;
    }

    public final boolean isUnderConstruction(int building, int buildingID) {
        for(int i = 0; i < this.iBuildingsConstructionSize; ++i) {
            if (((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuilding() == building && ((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuildingID() == buildingID) {
                return true;
            }
        }

        return false;
    }

    public final float underConstruction(int building, int buildingID) {
        for(int i = 0; i < this.iBuildingsConstructionSize; ++i) {
            if (((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuilding() == building && ((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuildingID() == buildingID) {
                return (float)((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getConstructionTimeLeft() / (float)((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getConstructionTime();
            }
        }

        return -1.0F;
    }

    public final int getConstructionID(int building, int buildingID) {
        for(int i = 0; i < this.iBuildingsConstructionSize; ++i) {
            if (((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuilding() == building && ((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuildingID() == buildingID) {
                return i;
            }
        }

        return -1;
    }

    public boolean buildingBuilt(int building, int buildingID) {
        for(int i = 0; i < this.iBuildingsSize; ++i) {
            if (((ProvinceConstructedBuilding)this.buildings.get(i)).getBuilding() == building && ((ProvinceConstructedBuilding)this.buildings.get(i)).getBuildingID() == buildingID) {
                return true;
            }
        }

        return false;
    }

    public void updateBuildingsUnderConstrucion(int turns) {
        for(int j = this.iBuildingsConstructionSize - 1; j >= 0; --j) {
            ProvinceConstructionBuilding var10000 = (ProvinceConstructionBuilding)this.buildingsConstruction.get(j);
            var10000.iConstructionTimeLeft -= turns;
            if (((ProvinceConstructionBuilding)this.buildingsConstruction.get(j)).iConstructionTimeLeft <= 0) {
                try {
                    if (this.getCivID() == Game.player.iCivID) {
                        ++Game.stats.civStats.bc;
                    }

                    Game.getCiv(this.getCivID()).eventsData2.addBuildingsConstructed(1);
                    switch (((BuildingsManager.Buildings)BuildingsManager.buildings.get(((ProvinceConstructionBuilding)this.buildingsConstruction.get(j)).getBuilding())).GroupID) {
                        case 0:
                            Game.getCiv(this.getCivID()).eventsData2.addAdministrativeBuildingsConstructed(1);
                            break;
                        case 1:
                            Game.getCiv(this.getCivID()).eventsData2.addMilitaryBuildingsConstructed(1);
                            break;
                        case 2:
                            Game.getCiv(this.getCivID()).eventsData2.addEconomyBuildingsConstructed(1);
                            break;
                        case 3:
                            Game.getCiv(this.getCivID()).eventsData2.addCapitalBuildingsConstructed(1);
                    }
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }

                this.addNewBuilding(new ProvinceConstructedBuilding(((ProvinceConstructionBuilding)this.buildingsConstruction.get(j)).getBuilding(), ((ProvinceConstructionBuilding)this.buildingsConstruction.get(j)).getBuildingID()));
                BonusesManager.updateBuildingBonuses(this.getProvinceID(), ((ProvinceConstructionBuilding)this.buildingsConstruction.get(j)).getBuilding(), ((ProvinceConstructionBuilding)this.buildingsConstruction.get(j)).getBuildingID(), 1);
                if (((BuildingsManager.Buildings)BuildingsManager.buildings.get(((ProvinceConstructionBuilding)this.buildingsConstruction.get(j)).getBuilding())).UniqueCapitalBuilding && this.getCivID() == Game.player.iCivID && !this.isOccupied()) {
                    InGame_Info.iCivID = Game.player.iCivID;
                    InGame_Info.iCivID2 = 0;
                    Game.menuManager.rebuildInGame_Info(((BuildingsManager.Buildings)BuildingsManager.buildings.get(((ProvinceConstructionBuilding)this.buildingsConstruction.get(j)).getBuilding())).Name[((ProvinceConstructionBuilding)this.buildingsConstruction.get(j)).getBuildingID()], Game.lang.get("BuildingConstructed"));
                    InGame_Info.imgID = Images.infoCrown;
                }

                this.buildingsConstruction.remove(j);
                this.iBuildingsConstructionSize = this.buildingsConstruction.size();
                if (this.iBuildingsConstructionSize == 0) {
                    Game.gameThreadTurns.removeProvinceBuildingsUnderConstruction(this.getProvinceID());
                }

                this.updateProvinceIncome();
                this.updateBuildingLimit();
                if (this.getCivID() == Game.player.iCivID) {
                    Game.menuManager.addRebuildInGame_RightQueue();
                }
            }
        }

    }

    public final void addNewBuilding(ProvinceConstructedBuilding nBuilding) {
        for(int i = 0; i < this.iBuildingsSize; ++i) {
            if (((ProvinceConstructedBuilding)this.buildings.get(i)).getBuilding() == nBuilding.getBuilding() && ((ProvinceConstructedBuilding)this.buildings.get(i)).getBuildingID() == nBuilding.getBuildingID()) {
                return;
            }
        }

        this.buildings.add(nBuilding);
        this.iBuildingsSize = this.buildings.size();
    }

    public final void addNewBuilding_LoadScenario(ProvinceConstructedBuilding nBuilding) {
        for(int i = 0; i < this.iBuildingsSize; ++i) {
            if (((ProvinceConstructedBuilding)this.buildings.get(i)).getBuilding() == nBuilding.getBuilding() && ((ProvinceConstructedBuilding)this.buildings.get(i)).getBuildingID() == nBuilding.getBuildingID()) {
                return;
            }
        }

        this.buildings.add(nBuilding);
        this.iBuildingsSize = this.buildings.size();
        BonusesManager.updateBuildingBonuses(this.getProvinceID(), nBuilding.getBuilding(), nBuilding.getBuildingID(), 1);
    }

    public final void destroyBuilding(int building, int buildingID) {
        this.destroyBuilding(building, buildingID, false);
    }

    public final void destroyBuilding(int building, int buildingID, boolean fromMulti) {
        for(int i = 0; i < this.iBuildingsSize; ++i) {
            if (((ProvinceConstructedBuilding)this.buildings.get(i)).getBuilding() == building && ((ProvinceConstructedBuilding)this.buildings.get(i)).getBuildingID() == buildingID) {
                if (!fromMulti) {
                    if (!SteamMultiManager.isHost()) {
                        M_BuildingDestroy.requestCommand(this.getProvinceID(), building, buildingID);
                        return;
                    }

                    M_BuildingDestroy.serializeCommand(this.getProvinceID(), building, buildingID);
                }

                BonusesManager.updateBuildingBonuses(this.getProvinceID(), building, buildingID, -1);
                this.buildings.remove(i);
                this.iBuildingsSize = this.buildings.size();
                this.updateBuildingLimit();
                this.updateProvinceIncome();
                return;
            }
        }

    }

    public final void destroyBuilding_ScenarioEditor(int building, int buildingID) {
        for(int i = 0; i < this.iBuildingsSize; ++i) {
            if (((ProvinceConstructedBuilding)this.buildings.get(i)).getBuilding() == building && ((ProvinceConstructedBuilding)this.buildings.get(i)).getBuildingID() == buildingID) {
                this.buildings.remove(i);
                this.iBuildingsSize = this.buildings.size();
                return;
            }
        }

    }

    public ProvinceConstructedBuilding getBuildings(int i) {
        return (ProvinceConstructedBuilding)this.buildings.get(i);
    }

    public ProvinceConstructionBuilding getBuildingsConstruction(int i) {
        return (ProvinceConstructionBuilding)this.buildingsConstruction.get(i);
    }

    public float getBuildingsConstruction_MaintenanceCosts() {
        float out = 0.0F;

        for(int i = 0; i < this.iBuildingsConstructionSize; ++i) {
            out += ((BuildingsManager.Buildings)BuildingsManager.buildings.get(((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuilding())).MaintenanceCost[((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuildingID()] + (((BuildingsManager.Buildings)BuildingsManager.buildings.get(((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuilding())).ResearchPoints != null && ((BuildingsManager.Buildings)BuildingsManager.buildings.get(((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuilding())).ResearchPoints[((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuildingID()] > 0.0F ? ((BuildingsManager.Buildings)BuildingsManager.buildings.get(((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuilding())).ResearchPoints[((ProvinceConstructionBuilding)this.buildingsConstruction.get(i)).getBuildingID()] * (this.getGrowthRateWithBonuses() / 100.0F) * GameValues.research.RESEARCH_MAINTENANCE_COST : 0.0F);
        }

        return out;
    }

    public final void updateBuildingLimit() {
        this.iBuildingsLimit = GameValues.buildings.BUILDINGS_LIMIT_DEFAULT + (this.isCapital ? GameValues.buildings.BUILDINGS_LIMIT_CAPITAL : 0) + GameValues.infrastructure.INFRASTRUCTURE_BUILDINGS_SLOT_PER_LVL * this.getInfrastructure() + (int)Math.floor((double)(this.getEconomyWithBonuses() / GameValues.buildings.BUILDINGS_SLOT_PER_ECONOMY)) + this.provBonuses.BuildingSlots + Game.getCiv(this.getCivID()).civBonuses.BuildingSlot;
    }

    public int getBuildingsLimit_FreeSlots() {
        return Math.max(0, this.iBuildingsLimit + Game.getCiv(this.getCivID()).civBonuses.BuildingSlot - this.getUsedBuildingsSlots());
    }

    public int getUsedBuildingsSlots() {
        return this.iBuildingsSize + this.iBuildingsConstructionSize;
    }

    public boolean addInvestInProvince() {
        return this.addInvestInProvince(this.getCivID());
    }

    public boolean addInvestInProvince(int iCivID_Paying) {
        return this.addInvestInProvince(this.getCivID(), false);
    }

    public boolean addInvestInProvince(int iCivID_Paying, boolean fromMulti) {
        if (!fromMulti) {
            if (Game.getCiv(iCivID_Paying).fGold < Game.getInvestCost(this.getProvinceID())) {
                return false;
            }

            if (Game.getCiv(iCivID_Paying).fLegacy < Game.getInvestCost_Legacy(this.getProvinceID())) {
                return false;
            }

            if (Game.canInvestInEconomy(this.iProvinceID)) {
                return false;
            }

            if (!SteamMultiManager.isHost()) {
                M_Invest.requestCommand(this.getProvinceID(), iCivID_Paying);
                return true;
            }

            M_Invest.provinceInvests.add(new M_ProvinceInvestData(this.getProvinceID(), iCivID_Paying));
        }

        Civilization var10000 = Game.getCiv(iCivID_Paying);
        var10000.fGold -= Game.getInvestCost(this.getProvinceID());
        var10000 = Game.getCiv(iCivID_Paying);
        var10000.fLegacy -= Game.getInvestCost_Legacy(this.getProvinceID());
        int investTime = Game.getInvestTime(this.getCivID());
        this.provinceInvestDaysLeft.add(new ProvinceInvest(investTime, investTime));
        this.iProvinceInvestSize = this.provinceInvestDaysLeft.size();
        Game.gameThreadTurns.addProvinceInvest(this.getProvinceID());
        Game.getCiv(iCivID_Paying).eventsData.addInvestedInEconomy((int)(Game.getInvestEconomyGrowth(this.getProvinceID()) * 100.0F));
        return true;
    }

    public void addInvestInProvince_Load(int daysLeft, int investTime) {
        this.provinceInvestDaysLeft.add(new ProvinceInvest(daysLeft, investTime));
        this.iProvinceInvestSize = this.provinceInvestDaysLeft.size();
        Game.gameThreadTurns.addProvinceInvest(this.getProvinceID());
    }

    public void updateInvestEconomy() {
        if (this.iProvinceInvestSize > 0) {
            --((ProvinceInvest)this.provinceInvestDaysLeft.get(0)).daysLeft;
            this.setEconomy(this.getEconomy() + Game.getInvestEconomyGrowth(this.iProvinceID) / (float)((ProvinceInvest)this.provinceInvestDaysLeft.get(0)).investTime);
            if (((ProvinceInvest)this.provinceInvestDaysLeft.get(0)).daysLeft <= 0) {
                this.provinceInvestDaysLeft.remove(0);
                this.iProvinceInvestSize = this.provinceInvestDaysLeft.size();
                this.updateAfterEconomyChange();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
                if (this.iProvinceInvestSize == 0) {
                    Game.gameThreadTurns.removeProvinceInvest(this.getProvinceID());
                }
            }
        }

    }

    public final void updateAfterEconomyChange() {
        this.updateProvinceIncome();
        this.updateBuildingLimit();
        this.updateInfrastructureMax();
    }

    public boolean addIncreaseManpowerInProvince() {
        return this.addIncreaseManpowerInProvince(this.getCivID());
    }

    public boolean addIncreaseManpowerInProvince(int iCivID_Paying) {
        return this.addIncreaseManpowerInProvince(iCivID_Paying, false);
    }

    public boolean addIncreaseManpowerInProvince(int iCivID_Paying, boolean fromMulti) {
        if (!fromMulti) {
            if (Game.getCiv(iCivID_Paying).fGold < Game.getIncreaseManpowerCost(this.getProvinceID())) {
                return false;
            }

            if (Game.getCiv(iCivID_Paying).fLegacy < Game.getIncreaseManpowerCostLegacy(this.getProvinceID())) {
                return false;
            }

            if (!SteamMultiManager.isHost()) {
                M_IncreaseManpower.requestCommand(this.getProvinceID(), iCivID_Paying);
                return true;
            }

            M_IncreaseManpower.provinceInvests.add(new M_ProvinceInvestData(this.getProvinceID(), iCivID_Paying));
        }

        Civilization var10000 = Game.getCiv(iCivID_Paying);
        var10000.fGold -= Game.getIncreaseManpowerCost(this.getProvinceID());
        var10000 = Game.getCiv(iCivID_Paying);
        var10000.fLegacy -= Game.getIncreaseManpowerCostLegacy(this.getProvinceID());
        int investTime = Game.getIncreseManpowerTime(this.getCivID());
        this.provinceIncreaseManpowerDaysLeft.add(new ProvinceInvest(investTime, investTime));
        this.iProvinceIncreaseManpowerSize = this.provinceIncreaseManpowerDaysLeft.size();
        Game.gameThreadTurns.addProvinceIncreaseManpower(this.getProvinceID());
        Game.getCiv(iCivID_Paying).eventsData.addIncreasedManpower((int)(GameValues.manpower.INCREASE_MANPOWER_GROWTH * 100.0F));
        return true;
    }

    public void addIncreaseManpowerInProvince_Load(int daysLeft, int investTime) {
        this.provinceIncreaseManpowerDaysLeft.add(new ProvinceInvest(daysLeft, investTime));
        this.iProvinceIncreaseManpowerSize = this.provinceIncreaseManpowerDaysLeft.size();
        Game.gameThreadTurns.addProvinceIncreaseManpower(this.getProvinceID());
    }

    public void updateIncreaseManpower() {
        if (this.iProvinceIncreaseManpowerSize > 0) {
            --((ProvinceInvest)this.provinceIncreaseManpowerDaysLeft.get(0)).daysLeft;
            this.setManpower(this.getManpower() + GameValues.manpower.INCREASE_MANPOWER_GROWTH / (float)((ProvinceInvest)this.provinceIncreaseManpowerDaysLeft.get(0)).investTime);
            if (((ProvinceInvest)this.provinceIncreaseManpowerDaysLeft.get(0)).daysLeft <= 0) {
                this.provinceIncreaseManpowerDaysLeft.remove(0);
                this.iProvinceIncreaseManpowerSize = this.provinceIncreaseManpowerDaysLeft.size();
                Game.gameThreadTurns.addCivUpdateMaxManpower(this.getCivID());
                if (this.iProvinceIncreaseManpowerSize == 0) {
                    Game.gameThreadTurns.removeProvinceIncreaseManpower(this.getProvinceID());
                }
            }
        }

    }

    public boolean addIncreaseTaxEfficiencyInProvince() {
        return this.addIncreaseTaxEfficiencyInProvince(this.getCivID());
    }

    public boolean addIncreaseTaxEfficiencyInProvince(int iCivID_Paying) {
        return this.addIncreaseTaxEfficiencyInProvince(iCivID_Paying, false);
    }

    public boolean addIncreaseTaxEfficiencyInProvince(int iCivID_Paying, boolean fromMulti) {
        if (!fromMulti) {
            if (Game.getCiv(iCivID_Paying).fGold < Game.getIncreaseTaxEfficiencyCost(this.getProvinceID())) {
                return false;
            }

            if (Game.getCiv(iCivID_Paying).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(this.getProvinceID())) {
                return false;
            }

            if (!SteamMultiManager.isHost()) {
                M_TaxEfficiency.requestCommand(this.getProvinceID(), iCivID_Paying);
                return true;
            }

            M_TaxEfficiency.provinceInvests.add(new M_ProvinceInvestData(this.getProvinceID(), iCivID_Paying));
        }

        Civilization var10000 = Game.getCiv(iCivID_Paying);
        var10000.fGold -= Game.getIncreaseTaxEfficiencyCost(this.getProvinceID());
        var10000 = Game.getCiv(iCivID_Paying);
        var10000.fLegacy -= Game.getIncreaseTaxEfficiencyCostLegacy(this.getProvinceID());
        int investTime = Game.getIncreasTaxEfficiencyTime(this.getCivID());
        this.provinceIncreasTaxEfficiencyDaysLeft.add(new ProvinceInvest(investTime, investTime));
        this.iProvinceIncreaseTaxEfficiencySize = this.provinceIncreasTaxEfficiencyDaysLeft.size();
        Game.gameThreadTurns.addProvinceIncreaseTaxEfficiency(this.getProvinceID());
        Game.getCiv(iCivID_Paying).eventsData.addIncreasedTaxEfficiency((int)(GameValues.tax.INCREASE_TAX_EFFICIENCY_GROWTH * 100.0F));
        return true;
    }

    public void addIncreaseTaxEfficiencyInProvince_Load(int daysLeft, int investTime) {
        this.provinceIncreasTaxEfficiencyDaysLeft.add(new ProvinceInvest(daysLeft, investTime));
        this.iProvinceIncreaseTaxEfficiencySize = this.provinceIncreasTaxEfficiencyDaysLeft.size();
        Game.gameThreadTurns.addProvinceIncreaseTaxEfficiency(this.getProvinceID());
    }

    public void updateIncreaseTaxEfficiency() {
        if (this.iProvinceIncreaseTaxEfficiencySize > 0) {
            --((ProvinceInvest)this.provinceIncreasTaxEfficiencyDaysLeft.get(0)).daysLeft;
            this.setTaxEfficiency(this.getTaxEfficiency() + GameValues.tax.INCREASE_TAX_EFFICIENCY_GROWTH / (float)((ProvinceInvest)this.provinceIncreasTaxEfficiencyDaysLeft.get(0)).investTime);
            if (((ProvinceInvest)this.provinceIncreasTaxEfficiencyDaysLeft.get(0)).daysLeft <= 0) {
                this.provinceIncreasTaxEfficiencyDaysLeft.remove(0);
                this.iProvinceIncreaseTaxEfficiencySize = this.provinceIncreasTaxEfficiencyDaysLeft.size();
                this.updateProvinceIncome();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
                if (this.iProvinceIncreaseTaxEfficiencySize == 0) {
                    Game.gameThreadTurns.removeProvinceIncreaseTaxEfficiency(this.getProvinceID());
                }
            }
        }

    }

    public boolean addIncreaseGrowthRateInProvince() {
        return this.addIncreaseGrowthRateInProvince(this.getCivID());
    }

    public boolean addIncreaseGrowthRateInProvince(int iCivID_Paying) {
        return this.addIncreaseGrowthRateInProvince(iCivID_Paying, false);
    }

    public boolean addIncreaseGrowthRateInProvince(int iCivID_Paying, boolean fromMulti) {
        if (!fromMulti) {
            if (Game.getCiv(iCivID_Paying).fGold < Game.getIncreaseGrowthRateCost(this.getProvinceID())) {
                return false;
            }

            if (!SteamMultiManager.isHost()) {
                M_GrowthRate.requestCommand(this.getProvinceID(), iCivID_Paying);
                return true;
            }

            M_GrowthRate.provinceInvests.add(new M_ProvinceInvestData(this.getProvinceID(), iCivID_Paying));
        }

        Civilization var10000 = Game.getCiv(iCivID_Paying);
        var10000.fGold -= Game.getIncreaseGrowthRateCost(this.getProvinceID());
        int investTime = Game.getIncreaseGrowthRateTime(this.getCivID());
        this.provinceIncreaseGrowthRateDaysLeft.add(new ProvinceInvest(investTime, investTime));
        this.iProvinceIncreaseGrowthRateSize = this.provinceIncreaseGrowthRateDaysLeft.size();
        Game.gameThreadTurns.addProvinceIncreaseGrowthRate(this.getProvinceID());
        Game.getCiv(iCivID_Paying).eventsData.addIncreasedGrowthRate((int)(GameValues.growthRate.INCREASE_GROWTH_RATE_GROWTH * 100.0F));
        return true;
    }

    public void addIncreaseGrowthRateInProvince_Load(int daysLeft, int investTime) {
        this.provinceIncreaseGrowthRateDaysLeft.add(new ProvinceInvest(daysLeft, investTime));
        this.iProvinceIncreaseGrowthRateSize = this.provinceIncreaseGrowthRateDaysLeft.size();
        Game.gameThreadTurns.addProvinceIncreaseGrowthRate(this.getProvinceID());
    }

    public void updateIncreaseGrowthRate() {
        if (this.iProvinceIncreaseGrowthRateSize > 0) {
            --((ProvinceInvest)this.provinceIncreaseGrowthRateDaysLeft.get(0)).daysLeft;
            Game.getProvinceData7(this.getProvinceID()).setIncreasedGrowthRate(Game.getProvinceData7(this.getProvinceID()).getIncreasedGrowthRate() + GameValues.growthRate.INCREASE_GROWTH_RATE_GROWTH / (float)((ProvinceInvest)this.provinceIncreaseGrowthRateDaysLeft.get(0)).investTime);
            this.updateProvinceIncome();
            this.updateInfrastructureMax();
            if (((ProvinceInvest)this.provinceIncreaseGrowthRateDaysLeft.get(0)).daysLeft <= 0) {
                this.provinceIncreaseGrowthRateDaysLeft.remove(0);
                this.iProvinceIncreaseGrowthRateSize = this.provinceIncreaseGrowthRateDaysLeft.size();
                this.updateCityScale();
                if (this.iProvinceIncreaseGrowthRateSize == 0) {
                    Game.gameThreadTurns.removeProvinceIncreaseGrowthRate(this.getProvinceID());
                }

                if (this.haveResearchBuilding()) {
                    Game.getCiv(this.getCivID()).updateResearchPerMonth();
                    Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
                }
            }
        }

    }

    public final void updateCityScale() {
        this.cityScale = Game.mapCities.citiesSettings.citiesScale[0] * Math.min(1.0F, Math.max(0.4F, this.getGrowthRateWithBonuses() / 100.0F));
    }

    public boolean haveResearchBuilding() {
        for(int i = 0; i < this.iBuildingsSize; ++i) {
            if (((BuildingsManager.Buildings)BuildingsManager.buildings.get(((ProvinceConstructedBuilding)this.buildings.get(i)).getBuilding())).ResearchPoints != null && ((BuildingsManager.Buildings)BuildingsManager.buildings.get(((ProvinceConstructedBuilding)this.buildings.get(i)).getBuilding())).ResearchPoints[((ProvinceConstructedBuilding)this.buildings.get(i)).getBuildingID()] > 0.0F) {
                return true;
            }
        }

        return false;
    }

    public boolean buildWonder() {
        return this.buildWonder(false);
    }

    public boolean buildWonder(boolean fromMulti) {
        if (this.wonderID >= 0 && this.wonderConstruction == null && !this.getWonderBuilt()) {
            if (!fromMulti) {
                if (Game.getCiv(this.getCivID()).fGold < WondersManager.getWonderConstructionCost(this.getProvinceID(), this.wonderID)) {
                    return false;
                }

                if (!SteamMultiManager.isHost()) {
                    M_Wonder.requestCommand(this.getProvinceID());
                    return true;
                }

                M_Wonder.provincesIDs.add(this.getProvinceID());
            }

            Civilization var10000 = Game.getCiv(this.getCivID());
            var10000.fGold -= WondersManager.getWonderConstructionCost(this.getProvinceID(), this.wonderID);
            this.wonderConstruction = new ProvinceInvest(((WondersManager.Wonders)WondersManager.wonders.get(this.wonderID)).ConstructionTime, ((WondersManager.Wonders)WondersManager.wonders.get(this.wonderID)).ConstructionTime);
            Game.gameThreadTurns.addProvinceWonderConstruction(this.getProvinceID());
            return true;
        } else {
            return false;
        }
    }

    public final void addWonderConstruction_Load(int daysLeft, int investTime) {
        if (this.wonderID >= 0 && this.wonderConstruction == null && !this.getWonderBuilt()) {
            this.wonderConstruction = new ProvinceInvest(daysLeft, investTime);
            Game.gameThreadTurns.addProvinceWonderConstruction(this.getProvinceID());
        }

    }

    public void updateWonderConstruction() {
        if (this.wonderConstruction != null) {
            --this.wonderConstruction.daysLeft;
            if (this.wonderConstruction.daysLeft <= 0) {
                this.setWonderBuilt(true);
                this.wonderConstruction = null;
                Game.gameThreadTurns.removeProvinceWonderConstruction(this.getProvinceID());
                WondersManager.updateCivBonuses(this.getCivID(), this.wonderID, 1);
                WondersManager.updateProvinceBonuses(this.getProvinceID(), this.wonderID, 1);
                if (((WondersManager.Wonders)WondersManager.wonders.get(this.wonderID)).Legacy > 0.0F) {
                    Game.getCiv(this.getCivID()).addLegacy(((WondersManager.Wonders)WondersManager.wonders.get(this.wonderID)).Legacy);
                }

                if (this.getCivID() == Game.player.iCivID) {
                    InGame_Info.iCivID = Game.player.iCivID;
                    InGame_Info.iCivID2 = 0;
                    Game.menuManager.rebuildInGame_Info(((WondersManager.Wonders)WondersManager.wonders.get(this.wonderID)).Name, Game.lang.get("WonderConstructed"));
                    InGame_Info.imgID = Images.infoCrown;
                }

                if (Game.menuManager.getVisibleInGame_Wonder() && InGame_Wonder.iProvinceID == this.getProvinceID()) {
                    InGame_Wonder.rebuildMenu = true;
                }
            }
        }

    }

    public float getRevulutionaryRisk() {
        return Game.getProvinceData8(this.getProvinceID()).getRevolutionaryRisk();
    }

    public void setRevulutionaryRisk(float fRevolutionaryRisk) {
        Game.getProvinceData8(this.getProvinceID()).setRevolutionaryRisk(Math.max(0.0F, Math.min(GameValues.province.PROVINCE_MAX_UNREST, fRevolutionaryRisk)));
    }

    public final void updateProvinceIncome() {
        this.fProvinceIncome = 0.0F;
        this.updateIncomeTaxation();
        this.updateIncomeEconomy();
        this.updateIncomeProduction();
        this.fProvinceIncome += this.fProvinceIncomeTaxation + this.fProvinceIncomeEconomy + this.fProvinceIncomeProduction + this.provBonuses.MonthlyIncome;
        this.updateProvinceMaintenance();
    }

    public final void updateIncomeTaxation() {
        this.fProvinceIncomeTaxation = Game.getIncomePopulationTaxation(this.getProvinceID());
    }

    public final void updateIncomeEconomy() {
        this.fProvinceIncomeEconomy = Game.getIncomeFromEconomy(this.getProvinceID());
    }

    public final void updateIncomeProduction() {
        this.fProvinceIncomeProduction = ResourcesManager.getMonthlyIncome(this.getProvinceID(), this.getResourceID());
    }

    public final void updateProvinceMaintenance() {
        this.fProvinceMaintenance = this.getProvinceBuildingsMaintenance() + this.getProvinceMaintenance();
        if (this.fProvinceIncome - this.fProvinceMaintenance < 0.0F) {
            this.fProvinceMaintenance = this.fProvinceIncome;
        }

    }

    public final float getProvinceMaintenance() {
        return (Game.getProvinceMaintenanceEconomy(this.getCivID(), this.getProvinceID()) + Game.getProvinceMaintenanceTax(this.getCivID(), this.getProvinceID()) + Game.getProvinceMaintenanceManpower(this.getCivID(), this.getProvinceID())) * Math.max(0.0F, 1.0F + (this.provBonuses.ProvinceMaintenance + Game.getCiv(this.getCivID()).civBonuses.ProvinceMaintenance) / 100.0F + GameValues.civRank.CIV_RANK_PROVINCE_MAINTENANCE[Game.getCiv(this.getCivID()).iCivRankID] + (float)this.getInfrastructure() * GameValues.infrastructure.INFRASTRUCTURE_PROVINCE_MAINTENANCE_PER_LVL);
    }

    public final float getProvinceBuildingsMaintenance() {
        return this.provBonuses.MaintenanceCost * Math.max(0.0F, 1.0F + Game.getCiv(this.getCivID()).civBonuses.BuildingsMaintenanceCost);
    }

    public final boolean addReligionConversion() {
        return this.addReligionConversion(false);
    }

    public final boolean addReligionConversion(boolean fromMulti) {
        if (!fromMulti) {
            if (!SteamMultiManager.isHost()) {
                M_ConvertReligion.requestCommand(this.getProvinceID());
                return true;
            }

            M_ConvertReligion.provincesIDs.add(this.getProvinceID());
        }

        if (this.religionConversion != null) {
            return false;
        } else if (Game.getCiv(this.getCivID()).getReligionID() != this.getReligion() && Game.getCiv(this.getCivID()).fGold >= (float)Game.religionManager.getReligionConversionCost(this.getProvinceID())) {
            Civilization var10000 = Game.getCiv(this.getCivID());
            var10000.fGold -= (float)Game.religionManager.getReligionConversionCost(this.getProvinceID());
            this.religionConversion = new ProvinceInvest(Game.religionManager.getReligionConversionTime(this.getProvinceID()), Game.religionManager.getReligionConversionTime(this.getProvinceID()));
            Game.getCiv(this.getCivID()).convertReligion.removeProvince(this.getProvinceID());
            Game.gameThreadTurns.addProvinceConvertReligion(this.getProvinceID());
            return true;
        } else {
            return false;
        }
    }

    public final void addReligionConversion_Load(int daysLeft, int investTime) {
        if (Game.getCiv(this.getCivID()).getReligionID() != this.getReligion()) {
            this.religionConversion = new ProvinceInvest(daysLeft, investTime);
            Game.gameThreadTurns.addProvinceConvertReligion(this.getProvinceID());
        }

    }

    public final void updateReligionConversion(int turns) {
        if (this.religionConversion != null) {
            ProvinceInvest var10000 = this.religionConversion;
            var10000.daysLeft -= turns;
            if (this.religionConversion.daysLeft <= 0) {
                this.setReligion(Game.getCiv(this.getCivID()).getReligionID());
                this.religionConversion = null;
                Game.addSimpleTask(new Game.SimpleTask("updateCivStability" + this.getCivID(), this.getCivID()) {
                    public void update() {
                        Game.getCiv(this.id).updateCivStability();
                    }
                });
                Game.gameThreadTurns.removeProvinceConvertReligion(this.getProvinceID());
                this.updateAfterReligionConversion();
            }
        }

    }

    public final void updateAfterReligionConversion() {
        this.updateProvinceIncome();
        Game.gameThreadTurns.addCivUpdateMaxManpower(this.getCivID());
        Game.gameThread.addCivUpdateLegacyPerMonth(this.getCivID());
        Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
    }

    public void addCore_Just(int iCivID) {
        for(int i = 0; i < this.iCoresSize; ++i) {
            if ((Integer)Game.getProvinceData5(this.getProvinceID()).co.get(i) == iCivID) {
                if (iCivID == this.getCivID()) {
                    this.haveACore = true;
                }

                return;
            }
        }

        Game.getProvinceData5(this.getProvinceID()).co.add(iCivID);
        this.iCoresSize = Game.getProvinceData5(this.getProvinceID()).co.size();
        this.updateHaveACore();
    }

    public void removeCore_ScenarioEditor(int iCivID) {
        for(int i = 0; i < this.iCoresSize; ++i) {
            if ((Integer)Game.getProvinceData5(this.getProvinceID()).co.get(i) == iCivID) {
                Game.getProvinceData5(this.getProvinceID()).co.remove(i);
                this.iCoresSize = Game.getProvinceData5(this.getProvinceID()).co.size();
                this.updateHaveACore();
                return;
            }
        }

    }

    public void updateCores_AfterRemoveCiv(int nRemoveCivID) {
        for(int i = this.iCoresSize - 1; i >= 0; --i) {
            if ((Integer)Game.getProvinceData5(this.getProvinceID()).co.get(i) > nRemoveCivID) {
                Game.getProvinceData5(this.getProvinceID()).co.set(i, (Integer)Game.getProvinceData5(this.getProvinceID()).co.get(i) - 1);
            } else if ((Integer)Game.getProvinceData5(this.getProvinceID()).co.get(i) == nRemoveCivID) {
                Game.getProvinceData5(this.getProvinceID()).co.remove(i);
                this.iCoresSize = Game.getProvinceData5(this.getProvinceID()).co.size();
            }
        }

    }

    public void updateHaveACore() {
        this.haveACore = false;

        for(int i = 0; i < this.iCoresSize; ++i) {
            if ((Integer)Game.getProvinceData5(this.getProvinceID()).co.get(i) == this.getCivID()) {
                this.haveACore = true;
                return;
            }
        }

    }

    public void updateCoresSize() {
        this.iCoresSize = Game.getProvinceData5(this.getProvinceID()).co.size();
    }

    public void clearCores() {
        Game.getProvinceData5(this.getProvinceID()).co.clear();
        this.iCoresSize = 0;
        this.haveACore = false;
    }

    public void addCore(int iCivID) {
        for(int i = 0; i < this.iCoresSize; ++i) {
            if ((Integer)Game.getProvinceData5(this.getProvinceID()).co.get(i) == iCivID) {
                if (iCivID == this.getCivID()) {
                    this.haveACore = true;
                }

                return;
            }
        }

        Game.getProvinceData5(this.getProvinceID()).co.add(iCivID);
        this.iCoresSize = Game.getProvinceData5(this.getProvinceID()).co.size();
        this.haveACore = false;

        for(int i = 0; i < this.iCoresSize; ++i) {
            if ((Integer)Game.getProvinceData5(this.getProvinceID()).co.get(i) == this.getCivID()) {
                this.haveACore = true;
                this.updateProvinceIncome();
                Game.gameThreadTurns.addCivUpdateMaxManpower(this.getCivID());
                Game.gameThread.addCivUpdateLegacyPerMonth(this.getCivID());
                Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
                break;
            }
        }

        Game.getCiv(this.getCivID()).civilizationCores.checkProvince(this.getProvinceID(), this.getCivID());
    }

    public void removeCore(int iCivID) {
        for(int i = 0; i < this.iCoresSize; ++i) {
            if ((Integer)Game.getProvinceData5(this.getProvinceID()).co.get(i) == iCivID) {
                Game.getProvinceData5(this.getProvinceID()).co.remove(i);
                break;
            }
        }

        this.iCoresSize = Game.getProvinceData5(this.getProvinceID()).co.size();
        this.haveACore = false;

        for(int i = 0; i < this.iCoresSize; ++i) {
            if ((Integer)Game.getProvinceData5(this.getProvinceID()).co.get(i) == this.getCivID()) {
                this.haveACore = true;
                break;
            }
        }

        this.updateProvinceIncome();
        Game.gameThreadTurns.addCivUpdateMaxManpower(this.getCivID());
        Game.gameThread.addCivUpdateLegacyPerMonth(this.getCivID());
        Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        Game.getCiv(this.getCivID()).civilizationCores.checkProvince(this.getProvinceID(), this.getCivID());
    }

    public int getCore(int id) {
        try {
            return (Integer)Game.getProvinceData5(this.getProvinceID()).co.get(id);
        } catch (Exception var3) {
            return 0;
        }
    }

    public boolean haveACore(int iCivID) {
        for(int i = 0; i < this.iCoresSize; ++i) {
            if ((Integer)Game.getProvinceData5(this.getProvinceID()).co.get(i) == iCivID) {
                return true;
            }
        }

        return false;
    }

    public final boolean addCoreCreation() {
        return this.addCoreCreation(false);
    }

    public final boolean addCoreCreation(boolean fromMulti) {
        if (!fromMulti) {
            if (this.coreCreation != null) {
                return false;
            }

            if (this.isOccupied()) {
                return false;
            }

            if (!SteamMultiManager.isHost()) {
                M_CreateCore.requestCommand(this.getProvinceID());
                return true;
            }

            M_CreateCore.provincesIDs.add(this.getProvinceID());
        }

        if (!this.haveACore(this.getCivID()) && Game.getCiv(this.getCivID()).fGold >= Game.getCoreCreationCost(this.getProvinceID())) {
            Civilization var10000 = Game.getCiv(this.getCivID());
            var10000.fGold -= Game.getCoreCreationCost(this.getProvinceID());
            this.coreCreation = new ProvinceInvest(Game.getCoreCreationTime(this.getProvinceID()), Game.getCoreCreationTime(this.getProvinceID()));
            Game.gameThreadTurns.addProvinceCoreCreation(this.getProvinceID());
            Game.getCiv(this.getCivID()).civilizationCores.removeProvince(this.getProvinceID());
            return true;
        } else {
            return false;
        }
    }

    public final void addCoreCreation_Load(int daysLeft, int investTime) {
        if (!this.haveACore(this.getCivID())) {
            this.coreCreation = new ProvinceInvest(daysLeft, investTime);
            Game.gameThreadTurns.addProvinceCoreCreation(this.getProvinceID());
        }

    }

    public final void updateCoreCreation(int turns) {
        if (this.coreCreation != null) {
            ProvinceInvest var10000 = this.coreCreation;
            var10000.daysLeft -= turns;
            if (this.coreCreation.daysLeft <= 0) {
                this.addCore(this.getCivID());
                this.coreCreation = null;
                Game.addSimpleTask(new Game.SimpleTask("updateCivStability" + this.getCivID(), this.getCivID()) {
                    public void update() {
                        Game.getCiv(this.id).updateCivStability();
                    }
                });
                Game.gameThreadTurns.removeProvinceCoreCreation(this.getProvinceID());
            }
        }

    }

    public final void updatePopulationGrowth() {
        this.updateIncreasePopulationOfCivID(this.getCivID(), (int)Math.max(1.0F, (float)((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).POPULATION_GROWTH_PER_MONTH * (this.getGrowthRateWithBonuses() / 100.0F)));
    }

    public final int getInfrastructure() {
        return Game.getProvinceData6(this.getProvinceID()).getInfrastructure();
    }

    public final void setInfrastructure(int niInfrastructure) {
        Game.getProvinceData6(this.getProvinceID()).setInfrastructure(Math.max(0, niInfrastructure));
    }

    public final void updateInfrastructureMax() {
        this.iInfrastructureMax = Math.min(GameValues.infrastructure.INFRASTRUCTURE_MAX_DEFAULT + (this.isCapital ? GameValues.infrastructure.INFRASTRUCTURE_MAX_CAPITAL : 0) + (int)Math.floor((double)(this.getEconomyWithBonuses() / GameValues.infrastructure.INFRASTRUCTURE_MAX_PER_ECONOMY + this.getGrowthRateWithBonuses() / GameValues.infrastructure.INFRASTRUCTURE_MAX_PER_GROWTH_RATE)) + this.provBonuses.MaxInfrastructure + Game.getCiv(this.getCivID()).civBonuses.MaxInfrastructure, GameValues.infrastructure.INFRASTRUCTURE_MAX_LVL + Game.getCiv(this.getCivID()).civBonuses.MaxInfrastructure);
    }

    public boolean addDevelopInfrastructure() {
        return this.addDevelopInfrastructure(this.getCivID());
    }

    public boolean addDevelopInfrastructure(int iCivID_Paying) {
        return this.addDevelopInfrastructure(iCivID_Paying, false);
    }

    public boolean addDevelopInfrastructure(int iCivID_Paying, boolean fromMulti) {
        if (!fromMulti) {
            if (Game.getCiv(iCivID_Paying).fGold < Game.getDevelopInfrastructureCost(this.getProvinceID())) {
                return false;
            }

            if (Game.getCiv(iCivID_Paying).fLegacy < Game.getDevelopInfrastructureCostLegacy(this.getProvinceID())) {
                return false;
            }

            if (this.getInfrastructure() + this.iProvinceDevelopInfrastructureSize >= this.iInfrastructureMax) {
                return false;
            }

            if (!SteamMultiManager.isHost()) {
                M_Infrastructure.requestCommand(this.getProvinceID(), iCivID_Paying);
                return true;
            }

            M_Infrastructure.provinceInvests.add(new M_ProvinceInvestData(this.getProvinceID(), iCivID_Paying));
        }

        if (this.getInfrastructure() + this.iProvinceDevelopInfrastructureSize >= this.iInfrastructureMax) {
            this.updateInfrastructureMax();
            if (this.getInfrastructure() + this.iProvinceDevelopInfrastructureSize >= this.iInfrastructureMax) {
                return false;
            }
        }

        Civilization var10000 = Game.getCiv(iCivID_Paying);
        var10000.fGold -= Game.getDevelopInfrastructureCost(this.getProvinceID());
        var10000 = Game.getCiv(iCivID_Paying);
        var10000.fLegacy -= Game.getDevelopInfrastructureCostLegacy(this.getProvinceID());
        int investTime = Game.getDevelopInfrastructureTime(this.getCivID());
        this.provinceDevelopInfrastructureDaysLeft.add(new ProvinceInvest(investTime, investTime));
        this.iProvinceDevelopInfrastructureSize = this.provinceDevelopInfrastructureDaysLeft.size();
        Game.gameThreadTurns.addProvinceDevelopInfrastructure(this.getProvinceID());
        Game.getCiv(iCivID_Paying).eventsData.addDevelopedInfrastructure(1);
        return true;
    }

    public void addDevelopInfrastructure_Load(int daysLeft, int investTime) {
        this.provinceDevelopInfrastructureDaysLeft.add(new ProvinceInvest(daysLeft, investTime));
        this.iProvinceDevelopInfrastructureSize = this.provinceDevelopInfrastructureDaysLeft.size();
        Game.gameThreadTurns.addProvinceDevelopInfrastructure(this.getProvinceID());
    }

    public boolean addDevelopInfrastructure_Free() {
        if (this.getInfrastructure() + this.iProvinceDevelopInfrastructureSize >= this.iInfrastructureMax) {
            return false;
        } else {
            int investTime = Game.getDevelopInfrastructureTime(this.getCivID());
            this.provinceDevelopInfrastructureDaysLeft.add(new ProvinceInvest(investTime, investTime));
            this.iProvinceDevelopInfrastructureSize = this.provinceDevelopInfrastructureDaysLeft.size();
            Game.gameThreadTurns.addProvinceDevelopInfrastructure(this.getProvinceID());
            return true;
        }
    }

    public void updateDevelopInfrastructure() {
        if (this.iProvinceDevelopInfrastructureSize > 0) {
            --((ProvinceInvest)this.provinceDevelopInfrastructureDaysLeft.get(0)).daysLeft;
            if (((ProvinceInvest)this.provinceDevelopInfrastructureDaysLeft.get(0)).daysLeft <= 0) {
                this.provinceDevelopInfrastructureDaysLeft.remove(0);
                this.iProvinceDevelopInfrastructureSize = this.provinceDevelopInfrastructureDaysLeft.size();
                this.setInfrastructure(Math.min(this.iInfrastructureMax, this.getInfrastructure() + 1));
                if (this.iProvinceDevelopInfrastructureSize == 0) {
                    Game.gameThreadTurns.removeProvinceDevelopInfrastructure(this.getProvinceID());
                }

                this.updateProvinceIncome();
                this.updateBuildingLimit();
                this.updateInfrastructureMax();
                if (Game.getProvince(this.iProvinceID).haveResearchBuilding()) {
                    Game.getCiv(Game.getProvince(this.iProvinceID).getCivID()).updateResearchPerMonth();
                }

                Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
            }
        }

    }

    public final void setIsCapital(boolean nIsCapital) {
        this.setIsCapital(nIsCapital, false);
    }

    public final void setIsCapital(boolean nIsCapital, boolean init) {
        try {
            if (this.isCapital != nIsCapital || init) {
                if (nIsCapital) {
                    ProvinceBonuses var9 = this.provBonuses;
                    var9.LocalGrowthRate += GameValues.capital.CAPITAL_GROWTH_RATE;
                    var9 = this.provBonuses;
                    var9.MonthlyIncome += GameValues.capital.CAPITAL_MONTHLY_INCOME;
                    var9 = this.provBonuses;
                    var9.FortLevel += GameValues.capital.CAPITAL_FORT_LVL;
                } else {
                    ProvinceBonuses var10000 = this.provBonuses;
                    var10000.LocalGrowthRate -= GameValues.capital.CAPITAL_GROWTH_RATE;
                    var10000 = this.provBonuses;
                    var10000.MonthlyIncome -= GameValues.capital.CAPITAL_MONTHLY_INCOME;
                    var10000 = this.provBonuses;
                    var10000.FortLevel -= GameValues.capital.CAPITAL_FORT_LVL;

                    for(int i = this.iBuildingsSize - 1; i >= 0; --i) {
                        try {
                            if (((BuildingsManager.Buildings)BuildingsManager.buildings.get(((ProvinceConstructedBuilding)this.buildings.get(i)).getBuilding())).UniqueCapitalBuilding) {
                                BonusesManager.updateBuildingBonuses(this.getProvinceID(), ((ProvinceConstructedBuilding)this.buildings.get(i)).getBuilding(), ((ProvinceConstructedBuilding)this.buildings.get(i)).getBuildingID(), -1);
                                this.buildings.remove(i);
                            }
                        } catch (Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }

                    this.iBuildingsSize = this.buildings.size();
                }

                this.isCapital = nIsCapital;
                this.updateProvinceIncome();
                this.updateProvinceValue();
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void updateDevastation() {
        if (this.isOccupied()) {
            this.setDevastation(this.getDevastation() + GameValues.siege.DEVASTATION_PER_MONTH_OCCUPIED);
        } else {
            this.setDevastation(this.getDevastation() + GameValues.siege.DEVASTATION_PER_MONTH_DEFAULT);
        }

    }

    public float getDevastation() {
        return Game.getProvinceData2(this.getProvinceID()).getDevastation();
    }

    public void setDevastation(float fDevastation) {
        fDevastation = Math.max(0.0F, Math.min(fDevastation, GameValues.siege.DEVASTATION_MAX));
        if (Game.getProvinceData2(this.getProvinceID()).getDevastation() != fDevastation) {
            Game.getProvinceData2(this.getProvinceID()).setDevastation(fDevastation);
            if (Math.abs(this.lastUpdatedDevastation - fDevastation) > GameValues.gameUpdate.GAME_UPDATE_INCOME_PER_DEVASTATION_CHANGE) {
                this.lastUpdatedDevastation = fDevastation;
                this.updateProvinceIncome();
                Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
            }
        }

    }

    public final void updateRevolutionaryRisk() {
        Game.getProvinceData8(this.getProvinceID()).setRevolutionaryRisk(Math.max(0.0F, Math.min(GameValues.province.PROVINCE_MAX_UNREST, Game.getProvinceData8(this.getProvinceID()).getRevolutionaryRisk() + this.getRevolutionaryRisk_MonhtlyChange())));
        if (this.getCivID() == Game.player.iCivID && Game.getProvinceData8(this.getProvinceID()).getRevolutionaryRisk() > (float)GameValues.rebels.SEND_NOTIFICATION_IF_UNREST_OVER) {
            Game.player.addNotification_Unrest(new Notification(Notification_Type.HIGH_UNREST, Game.lang.get("Unrest") + ": " + (int)Game.getProvinceData8(this.getProvinceID()).getRevolutionaryRisk() + "% - " + this.getProvinceName(), Images.revolutionRisk, Game_Calendar.TURN_ID, Notification_BG.RED, this.getProvinceID()));
        }

    }

    public final float getRevolutionaryRisk_MonhtlyChange() {
        return (this.getRevolutionaryRisk_MonhtlyChange_CivStability() + this.getRevolutionaryRisk_MonhtlyChange_WarWeariness()) * (1.0F + Game.getCiv(this.getCivID()).civBonuses.RevolutionaryRisk / 100.0F) + this.getRevolutionaryRisk_MonhtlyChange_BudgetTaxation() * (1.0F + this.getTaxEfficiencyWithBonuses() / 100.0F);
    }

    public final float getRevolutionaryRisk_MonhtlyChange_CivStability() {
        return Game.getCiv(this.getCivID()).civStability_LostFrom100 > 0.0F ? Game.getCiv(this.getCivID()).civStability_LostFrom100 * (GameValues.civStability.CS_UNREST_PER_POINT + (this.getReligion() != Game.getCiv(this.getCivID()).getReligionID() ? GameValues.civStability.CS_UNREST_DIFFERENT_RELIGION_PER_POINT : 0.0F) + (this.haveACore ? 0.0F : GameValues.civStability.CS_UNREST_NON_CORE_PER_POINT) * (GameValues.civStability.CS_UNREST_PERC_MIN + GameValues.civStability.CS_UNREST_PERC_GROWTH_RATE * this.getGrowthRateWithBonuses())) : 0.0F;
    }

    public final float getRevolutionaryRisk_MonhtlyChange_WarWeariness() {
        return Game.getCiv(this.getCivID()).getWarWeariness() > 0.0F ? Game.getCiv(this.getCivID()).getWarWeariness() * (GameValues.warWeariness.WW_UNREST_PER_POINT + (this.getReligion() != Game.getCiv(this.getCivID()).getReligionID() ? GameValues.warWeariness.WW_UNREST_DIFFERENT_RELIGION_PER_POINT : 0.0F) + (this.haveACore ? 0.0F : GameValues.warWeariness.WW_UNREST_NON_CORE_PER_POINT)) * (GameValues.warWeariness.WW_UNREST_PERC_MIN + GameValues.warWeariness.WW_UNREST_PERC_GROWTH_RATE * this.getGrowthRateWithBonuses()) : 0.0F;
    }

    public final float getRevolutionaryRisk_MonhtlyChange_BudgetTaxation() {
        return GameValues.budget.TAXATION_LEVEL_UNREST_PER_MONTH[Game.getCiv(this.getCivID()).getTaxationLevel()];
    }

    public final int atomicBombDropped() {
        int nPopBefore = this.getPopulationTotal();

        try {
            float populationCasualtiesPerc = Game.getAtomicBombCasualties(this.getProvinceID());
            if (populationCasualtiesPerc > 0.0F) {
                for(int k = this.getPopulationSize() - 1; k >= 0; --k) {
                    this.setPopulationOfCivID(this.getPopulationCivID(k), (int)((double)this.getPopulationID(k) - Math.floor((double)(populationCasualtiesPerc * (float)this.getPopulationID(k)))));
                }
            }

            this.setEconomy(this.getEconomy() - this.getEconomy() * Math.max(0.01F, GameValues.atomic.ATOMIC_BOMB_ECONOMY));

            for(int i = this.iArmiesSize - 1; i >= 0; --i) {
                for(int j = this.getArmy(i).iArmyRegimentSize - 1; j >= 0; --j) {
                    ((ArmyRegiment)this.getArmy(i).lArmyRegiment.get(j)).num = Math.max(0, (int)((float)((ArmyRegiment)this.getArmy(i).lArmyRegiment.get(j)).num - (float)((ArmyRegiment)this.getArmy(i).lArmyRegiment.get(j)).num * GameValues.atomic.ATOMIC_BOMB_ARMY));
                }

                this.getArmy(i).updateArmy(true);
            }

            this.setDevastation(this.getDevastation() + GameValues.atomic.ATOMIC_BOMB_DEVASTATION);
            this.updateProvinceIncome();
            Game.gameThread.addCivUpdateTotalIncomePerMonth(this.getCivID());
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return nPopBefore - this.getPopulationTotal();
    }

    public int getFortLevel() {
        return this.provBonuses.FortLevel;
    }

    public int getFortDefense() {
        return (int)((GameValues.siege.SIEGE_FORT_DEFENSE_DEFAULT + GameValues.siege.SIEGE_FORT_DEFENSE_PER_FORT_LVL * (float)this.provBonuses.FortLevel + GameValues.siege.SIEGE_FORT_DEFENSE_PER_GROWTH_RATE_LVL * this.getGrowthRateWithBonuses() + (float)this.provBonuses.FortDefense + GameValues.siege.SIEGE_FORT_DEFENSE_PER_MANPOWER_LVL * Game.getProvinceData3(this.getProvinceID()).getManpower()) * (Game.getProvinceData(this.iProvinceID).getOccupiedByCivID() < 0 ? GameValues.rebels.REBELS_FORT_DEFENSE : 1.0F));
    }

    public int getFortDefense_Manpower() {
        return (int)(GameValues.siege.SIEGE_FORT_DEFENSE_PER_MANPOWER_LVL * Game.getProvinceData3(this.getProvinceID()).getManpower());
    }

    public float getSiegeProgress() {
        return Math.min(1.0F, Game.getProvinceData4(this.getProvinceID()).getSiegeProgress() / (float)this.getFortDefense());
    }

    public final void retakeOccupiedProvince_Multi(List<Integer> neighProvinces) {
        String warKey = WarManager.getWarKey(this.getCivID(), Game.getProvinceData(this.getProvinceID()).getOccupiedByCivID());
        Game.getCiv(this.getCivID()).updateTotalProvincesValue();
        boolean notifyPlayer = false;
        boolean notifyPlayerPositive = false;
        if (warKey != null) {
            try {
                float fWarScore = this.getWarScore();
                fWarScore = ((War)WarManager.lWars.get(warKey)).addWarScore_ValueToAdd_Province(fWarScore, Game.getProvinceData(this.getProvinceID()).getOccupiedByCivID(), this.getCivID(), this.getProvinceID());
                if (((War)WarManager.lWars.get(warKey)).isAggressor(Game.getProvinceData(this.getProvinceID()).getOccupiedByCivID())) {
                    ((War)WarManager.lWars.get(warKey)).addWarScore_Just(-fWarScore);
                    War var10000 = (War)WarManager.lWars.get(warKey);
                    var10000.warScoreFromOccupiedProvinces -= fWarScore;
                } else {
                    ((War)WarManager.lWars.get(warKey)).addWarScore_Just(fWarScore);
                    War var16 = (War)WarManager.lWars.get(warKey);
                    var16.warScoreFromOccupiedProvinces += fWarScore;
                }

                ((War)WarManager.lWars.get(warKey)).lastFight_TurnID = Game_Calendar.TURN_ID;
                if (((War)WarManager.lWars.get(warKey)).isAggressor(Game.player.iCivID) || ((War)WarManager.lWars.get(warKey)).isDefender(Game.player.iCivID)) {
                    notifyPlayer = true;
                    notifyPlayerPositive = this.getCivID() == Game.player.iCivID;
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

        if (Game.getProvinceData(this.getProvinceID()).getOccupiedByCivID() < 0) {
            Game.revolutionManager.removeOccupiedProvince(this.getProvinceID());

            for(int i = 0; i < Game.getProvince(this.getProvinceID()).getArmySize(); ++i) {
                if (Game.getProvince(this.getProvinceID()).getArmy(i).civID == Game.player.iCivID) {
                    notifyPlayer = true;
                    notifyPlayerPositive = true;
                    break;
                }
            }
        }

        this.resetSiegeData();
        Game.getProvinceData(this.getProvinceID()).setOccupiedByCivID(this.getProvinceID(), 0);

        for(int i = 0; i < neighProvinces.size(); ++i) {
            warKey = WarManager.getWarKey(this.getCivID(), Game.getProvinceData((Integer)neighProvinces.get(i)).getOccupiedByCivID());
            if (warKey != null) {
                try {
                    float fWarScore = this.getWarScore((Integer)neighProvinces.get(i));
                    fWarScore = ((War)WarManager.lWars.get(warKey)).addWarScore_ValueToAdd_Province(fWarScore, Game.getProvinceData((Integer)neighProvinces.get(i)).getOccupiedByCivID(), this.getCivID(), this.getProvinceID());
                    if (((War)WarManager.lWars.get(warKey)).isAggressor(Game.getProvinceData((Integer)neighProvinces.get(i)).getOccupiedByCivID())) {
                        ((War)WarManager.lWars.get(warKey)).addWarScore_Just(-fWarScore);
                        War var17 = (War)WarManager.lWars.get(warKey);
                        var17.warScoreFromOccupiedProvinces -= fWarScore;
                    } else {
                        ((War)WarManager.lWars.get(warKey)).addWarScore_Just(fWarScore);
                        War var18 = (War)WarManager.lWars.get(warKey);
                        var18.warScoreFromOccupiedProvinces += fWarScore;
                    }
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }

            Game.getProvinceData((Integer)neighProvinces.get(i)).setOccupiedByCivID(this.getProvinceID(), 0);
            if (Game.getProvinceData4((Integer)neighProvinces.get(i)).isUnderSiege()) {
                Game.getProvince((Integer)neighProvinces.get(i)).resetSiegeData();
                SiegeManager.removeProvinceSiege((Integer)neighProvinces.get(i));
            }

            Game.getProvince((Integer)neighProvinces.get(i)).invasionMoveArmies();
        }

        if (notifyPlayer) {
            this.invasionMoveArmies();
        }

    }

    public final void retakeOccupiedProvince() {
        String warKey = WarManager.getWarKey(this.getCivID(), Game.getProvinceData(this.getProvinceID()).getOccupiedByCivID());
        Game.getCiv(this.getCivID()).updateTotalProvincesValue();
        boolean notifyPlayer = false;
        boolean notifyPlayerPositive = false;
        List<Integer> neighProvinces = new ArrayList();
        if (warKey != null) {
            try {
                float fWarScore = this.getWarScore();
                fWarScore = ((War)WarManager.lWars.get(warKey)).addWarScore_ValueToAdd_Province(fWarScore, Game.getProvinceData(this.getProvinceID()).getOccupiedByCivID(), this.getCivID(), this.getProvinceID());
                if (((War)WarManager.lWars.get(warKey)).isAggressor(Game.getProvinceData(this.getProvinceID()).getOccupiedByCivID())) {
                    ((War)WarManager.lWars.get(warKey)).addWarScore_Just(-fWarScore);
                    War var10000 = (War)WarManager.lWars.get(warKey);
                    var10000.warScoreFromOccupiedProvinces -= fWarScore;
                } else {
                    ((War)WarManager.lWars.get(warKey)).addWarScore_Just(fWarScore);
                    War var15 = (War)WarManager.lWars.get(warKey);
                    var15.warScoreFromOccupiedProvinces += fWarScore;
                }

                ((War)WarManager.lWars.get(warKey)).lastFight_TurnID = Game_Calendar.TURN_ID;
                if (((War)WarManager.lWars.get(warKey)).isAggressor(Game.player.iCivID) || ((War)WarManager.lWars.get(warKey)).isDefender(Game.player.iCivID)) {
                    notifyPlayer = true;
                    notifyPlayerPositive = this.getCivID() == Game.player.iCivID;
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

        if (Game.getProvinceData(this.getProvinceID()).getOccupiedByCivID() < 0) {
            Game.revolutionManager.removeOccupiedProvince(this.getProvinceID());

            for(int i = 0; i < Game.getProvince(this.getProvinceID()).getArmySize(); ++i) {
                if (Game.getProvince(this.getProvinceID()).getArmy(i).civID == Game.player.iCivID) {
                    notifyPlayer = true;
                    notifyPlayerPositive = true;
                    break;
                }
            }
        }

        this.resetSiegeData();
        Game.getProvinceData(this.getProvinceID()).setOccupiedByCivID(this.getProvinceID(), 0);

        try {
            if (notifyPlayer) {
                Game.player.addNotification(new Notification(Notification_Type.SIEGE, (notifyPlayerPositive ? Game.lang.get("SiegeWon") : Game.lang.get("SiegeLost")) + ": " + this.getProvinceName(), Images.siege, Game_Calendar.TURN_ID, notifyPlayerPositive ? Notification_BG.GREEN : Notification_BG.RED, this.getProvinceID()) {
                    public void onAction() {
                        Game.mapCoords.centerToProvinceID(this.id);
                    }
                });
            }

            for(int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
                if (Game.getProvince(this.getNeighboringProvinces(i)).getFortLevel() == 0 && Game.getProvince(this.getNeighboringProvinces(i)).getCivID() == this.getCivID() && Game.getProvince(this.getNeighboringProvinces(i)).isOccupied()) {
                    warKey = WarManager.getWarKey(this.getCivID(), Game.getProvinceData(this.getNeighboringProvinces(i)).getOccupiedByCivID());
                    if (warKey != null) {
                        try {
                            float fWarScore = this.getWarScore(this.getNeighboringProvinces(i));
                            fWarScore = ((War)WarManager.lWars.get(warKey)).addWarScore_ValueToAdd_Province(fWarScore, Game.getProvinceData(this.getNeighboringProvinces(i)).getOccupiedByCivID(), this.getCivID(), this.getProvinceID());
                            if (((War)WarManager.lWars.get(warKey)).isAggressor(Game.getProvinceData(this.getNeighboringProvinces(i)).getOccupiedByCivID())) {
                                ((War)WarManager.lWars.get(warKey)).addWarScore_Just(-fWarScore);
                                War var16 = (War)WarManager.lWars.get(warKey);
                                var16.warScoreFromOccupiedProvinces -= fWarScore;
                            } else {
                                ((War)WarManager.lWars.get(warKey)).addWarScore_Just(fWarScore);
                                War var17 = (War)WarManager.lWars.get(warKey);
                                var17.warScoreFromOccupiedProvinces += fWarScore;
                            }
                        } catch (Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }

                    neighProvinces.add(this.getNeighboringProvinces(i));
                    Game.getProvinceData(this.getNeighboringProvinces(i)).setOccupiedByCivID(this.getProvinceID(), 0);
                    if (Game.getProvinceData4(this.getNeighboringProvinces(i)).isUnderSiege()) {
                        Game.getProvince(this.getNeighboringProvinces(i)).resetSiegeData();
                        SiegeManager.removeProvinceSiege(this.getNeighboringProvinces(i));
                    }

                    Game.getProvince(this.getNeighboringProvinces(i)).invasionMoveArmies();
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        M_OccupyRetake.serializeCommand(this.getProvinceID(), neighProvinces);
        if (notifyPlayer) {
            this.invasionMoveArmies();
        }

    }

    public final void retakeOccupiedProvince_Peace() {
        this.resetSiegeData();
        Game.getProvinceData(this.getProvinceID()).setOccupiedByCivID(this.getProvinceID(), 0);
    }

    public final void occupyProvince_Multi(int bestCivID, List<Integer> neighProvinces) {
        Game.getProvinceData4(this.getProvinceID()).setIsUnderSiege_Just(false);
        SiegeManager.removeProvinceSiege(this.getProvinceID());
        this.resetSiegeData();
        boolean notifyPlayer = false;
        boolean notifyPlayerPositive = false;
        if (this.getCivID() == Game.player.iCivID) {
            notifyPlayer = true;
            notifyPlayerPositive = false;
        }

        if (bestCivID != 0) {
            Game.getCiv(this.getCivID()).updateTotalProvincesValue();
            this.setOccupiedByCivID(bestCivID);
            if (bestCivID < 0) {
                Game.revolutionManager.addOccupiedProvince(this.getProvinceID());
            } else if (bestCivID > 0) {
                this.lootProvince(bestCivID);
                this.occupyProvince_DecreaseGrowthRate();
                EventsManager.runEvents_Siege(bestCivID, this.getProvinceID());
            }

            if (notifyPlayer) {
                Game.player.addNotification(new Notification(Notification_Type.SIEGE, (notifyPlayerPositive ? Game.lang.get("SiegeWon") : Game.lang.get("SiegeLost")) + ": " + this.getProvinceName(), Images.siege, Game_Calendar.TURN_ID, notifyPlayerPositive ? Notification_BG.GREEN : Notification_BG.RED, this.getProvinceID()) {
                    public void onAction() {
                        Game.mapCoords.centerToProvinceID(this.id);
                    }
                });
            }

            for(int i = 0; i < neighProvinces.size(); ++i) {
                Game.getProvince((Integer)neighProvinces.get(i)).setOccupiedByCivID(bestCivID);
                if (bestCivID < 0) {
                    Game.revolutionManager.addOccupiedProvince((Integer)neighProvinces.get(i));
                }

                if (Game.getProvinceData4((Integer)neighProvinces.get(i)).isUnderSiege()) {
                    SiegeManager.removeProvinceSiege((Integer)neighProvinces.get(i));
                    Game.getProvince((Integer)neighProvinces.get(i)).resetSiegeData();
                    Game.getProvince((Integer)neighProvinces.get(i)).lootProvince(bestCivID);
                    Game.getProvince((Integer)neighProvinces.get(i)).invasionMoveArmies();
                }
            }

            if (notifyPlayer) {
                this.invasionMoveArmies();
            }
        }

    }

    public final void occupyProvince() {
        this.resetSiegeData();
        int bestCivID = 0;
        int bestArmy = 0;
        boolean notifyPlayer = false;
        boolean notifyPlayerPositive = false;
        if (this.getCivID() == Game.player.iCivID) {
            notifyPlayer = true;
            notifyPlayerPositive = false;
        }

        for(int i = 0; i < this.getArmySize(); ++i) {
            if (DiplomacyManager.isAtWar(this.getCivID(), this.getArmy(i).civID)) {
                if (this.getArmy(i).civID == Game.player.iCivID) {
                    notifyPlayer = true;
                    notifyPlayerPositive = true;
                }

                if (this.getArmy(i).iArmy > bestArmy) {
                    bestCivID = this.getArmy(i).civID;
                    bestArmy = this.getArmy(i).iArmy;
                }
            }
        }

        if (bestCivID != 0) {
            List<Integer> neighProvinces = new ArrayList();
            Game.getCiv(this.getCivID()).updateTotalProvincesValue();
            this.setOccupiedByCivID(bestCivID);

            try {
                if (bestCivID < 0) {
                    Game.revolutionManager.addOccupiedProvince(this.getProvinceID());
                } else if (bestCivID > 0) {
                    this.lootProvince(bestCivID);
                    this.occupyProvince_DecreaseGrowthRate();
                    EventsManager.runEvents_Siege(bestCivID, this.getProvinceID());
                }

                if (notifyPlayer) {
                    Game.player.addNotification(new Notification(Notification_Type.SIEGE, (notifyPlayerPositive ? Game.lang.get("SiegeWon") : Game.lang.get("SiegeLost")) + ": " + this.getProvinceName(), Images.siege, Game_Calendar.TURN_ID, notifyPlayerPositive ? Notification_BG.GREEN : Notification_BG.RED, this.getProvinceID()) {
                        public void onAction() {
                            Game.mapCoords.centerToProvinceID(this.id);
                        }
                    });
                }

                if (bestCivID >= 0 || GameValues.rebels.REBELS_OCCUPY_NEIGHBORING_PROVINCES) {
                    for(int i = 0; i < this.getNeighboringProvincesSize(); ++i) {
                        if (Game.getProvince(this.getNeighboringProvinces(i)).getFortLevel() == 0 && Game.getProvince(this.getNeighboringProvinces(i)).getCivID() == this.getCivID()) {
                            if (Game.getProvinceData(this.getNeighboringProvinces(i)).getOccupiedByCivID() > 0) {
                                if (DiplomacyManager.isAtWar(bestCivID, Game.getProvinceData(this.getNeighboringProvinces(i)).getOccupiedByCivID())) {
                                    Game.getProvince(this.getNeighboringProvinces(i)).setOccupiedByCivID(bestCivID);
                                    neighProvinces.add(this.getNeighboringProvinces(i));
                                    if (bestCivID < 0) {
                                        Game.revolutionManager.addOccupiedProvince(this.getNeighboringProvinces(i));
                                    }

                                    if (Game.getProvinceData4(this.getNeighboringProvinces(i)).isUnderSiege()) {
                                        SiegeManager.removeProvinceSiege(this.getNeighboringProvinces(i));
                                        Game.getProvince(this.getNeighboringProvinces(i)).resetSiegeData();
                                        Game.getProvince(this.getNeighboringProvinces(i)).lootProvince(bestCivID);
                                        Game.getProvince(this.getNeighboringProvinces(i)).invasionMoveArmies();
                                    }
                                }
                            } else {
                                Game.getProvince(this.getNeighboringProvinces(i)).setOccupiedByCivID(bestCivID);
                                neighProvinces.add(this.getNeighboringProvinces(i));
                                if (bestCivID < 0) {
                                    Game.revolutionManager.addOccupiedProvince(this.getNeighboringProvinces(i));
                                }

                                if (Game.getProvinceData4(this.getNeighboringProvinces(i)).isUnderSiege()) {
                                    SiegeManager.removeProvinceSiege(this.getNeighboringProvinces(i));
                                    Game.getProvince(this.getNeighboringProvinces(i)).resetSiegeData();
                                    Game.getProvince(this.getNeighboringProvinces(i)).lootProvince(bestCivID);
                                    Game.getProvince(this.getNeighboringProvinces(i)).invasionMoveArmies();
                                }
                            }
                        }
                    }
                }

                if (notifyPlayer) {
                    this.invasionMoveArmies();
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }

            M_Occupy.serializeCommand(this.getProvinceID(), bestCivID, neighProvinces);
        }

    }

    public final void invasionMoveArmies() {
        for(int i = this.getArmySize() - 1; i >= 0; --i) {
            if (this.getArmy(i).civID == Game.player.iCivID && !this.getArmy(i).inMovement && !this.getArmy(i).inRetreat && !this.getArmy(i).inBattle) {
                Game.player.playerData.invasion.moveInvasion(this.getArmy(i).key);
            }
        }

    }

    public final float getWarScore() {
        return this.getWarScore(this.getProvinceID());
    }

    public final float getWarScore(int iProvinceID) {
        return Game.getProvince(iProvinceID).fProvinceValue / Game.getCiv(this.getCivID()).fTotalProvincesValue * 100.0F;
    }

    public final void setOccupiedByCivID(int nOccupiedByCivID) {
        Game.getProvinceData(this.getProvinceID()).setOccupiedByCivID(this.getProvinceID(), nOccupiedByCivID);
        String warKey = WarManager.getWarKey(this.getCivID(), nOccupiedByCivID);
        if (warKey != null) {
            try {
                float fWarScore = this.getWarScore();
                fWarScore = ((War)WarManager.lWars.get(warKey)).addWarScore_ValueToAdd_Province(fWarScore, nOccupiedByCivID, this.getCivID(), this.getProvinceID());
                if (((War)WarManager.lWars.get(warKey)).isAggressor(nOccupiedByCivID)) {
                    ((War)WarManager.lWars.get(warKey)).addWarScore_Just(fWarScore);
                    War var10000 = (War)WarManager.lWars.get(warKey);
                    var10000.warScoreFromOccupiedProvinces += fWarScore;
                } else {
                    ((War)WarManager.lWars.get(warKey)).addWarScore_Just(-fWarScore);
                    War var6 = (War)WarManager.lWars.get(warKey);
                    var6.warScoreFromOccupiedProvinces -= fWarScore;
                }

                ((War)WarManager.lWars.get(warKey)).lastFight_TurnID = Game_Calendar.TURN_ID;
                Game.getCiv(this.getCivID()).updateWarWeariness(fWarScore * GameValues.war.WAR_WAR_WEARINESS_OCCUPIED_PROVINCE);
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

    }

    public final void resetSiegeData() {
        Game.getProvinceData4(this.getProvinceID()).setSiegeProgress(0.0F);
        Game.getProvinceData4(this.getProvinceID()).setIsUnderSiege(this.getProvinceID(), false);
    }

    public final void updateIsUnderSiege() {
        if (Game.getProvinceData4(this.getProvinceID()).isUnderSiege()) {
            if (this.isOccupied()) {
                for(int i = 0; i < this.iArmiesSize; ++i) {
                    if (!this.getArmy(i).inMovement && !this.getArmy(i).inRetreat && DiplomacyManager.isAtWar(Game.getProvinceData(this.getProvinceID()).getOccupiedByCivID(), this.getArmy(i).civID)) {
                        return;
                    }
                }
            } else {
                for(int i = 0; i < this.iArmiesSize; ++i) {
                    if (!this.getArmy(i).inMovement && !this.getArmy(i).inRetreat && DiplomacyManager.isAtWar(this.getCivID(), this.getArmy(i).civID)) {
                        return;
                    }
                }
            }

            this.resetSiegeData();
            SiegeManager.removeProvinceSiege(this.getProvinceID());
        }

    }

    public final void occupyProvince_DecreaseGrowthRate() {
    }

    public final void lootProvince(int nCivID) {
        if (nCivID >= 0) {
            Civilization var10000 = Game.getCiv(nCivID);
            var10000.fGold += Game.getLootValue(this.getProvinceID());
            this.setLoot(0.0F);
            this.setDevastation(this.getDevastation() + Game.getDevastationOccupiedProvince(nCivID));
        }
    }

    public final void updateLoot() {
        if (!this.isOccupied()) {
            this.setLoot(this.getLoot() + GameValues.siege.LOOT_PROVINCE_RECOVERY_PER_MONTH);
        }

    }

    public float getLoot() {
        return Game.getProvinceData2(this.getProvinceID()).getLoot();
    }

    public void setLoot(float fLoot) {
        Game.getProvinceData2(this.getProvinceID()).setLoot(Math.max(0.0F, Math.min(fLoot, 1.0F)));
    }

    public final void checkForBattle() {
        this.checkForBattle(false);
    }

    public final void checkForBattle(boolean fromMulti) {
        try {
            if (this.getArmySize() > 1) {
                for(int i = 0; i < this.getArmySize() - 1; ++i) {
                    if (!this.getArmy(i).inRetreat && !this.getArmy(this.getArmySize() - 1).inRetreat && DiplomacyManager.isAtWar(this.getArmy(this.getArmySize() - 1).civID, this.getArmy(i).civID)) {
                        if (this.getArmy(i).inBattle && !this.getArmy(this.getArmySize() - 1).inBattle) {
                            Game.battleManager.joinBattle(this.getProvinceID(), this.getArmy(this.getArmySize() - 1), this.getArmy(i).civID);
                        } else if (!this.getArmy(i).inBattle && this.getArmy(this.getArmySize() - 1).inBattle) {
                            Game.battleManager.joinBattle(this.getProvinceID(), this.getArmy(i), this.getArmy(this.getArmySize() - 1).civID);
                        } else {
                            if (!SteamMultiManager.isHost()) {
                                return;
                            }

                            this.startBattle();
                        }
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void startBattle() {
        this.startBattle(false, (M_BattleStart.BattleStarted)null);
    }

    public final void startBattle(boolean fromMulti, M_BattleStart.BattleStarted battleStarted) {
        try {
            List<ArmyDivision> tAttackingDiv = new ArrayList();
            List<ArmyDivision> tDefendingDiv = new ArrayList();

            for(int i = 0; i < this.getArmySize(); ++i) {
                this.getArmy(i).addedToBattle = false;
            }

            int i = 0;

            boolean rebels;
            for(rebels = false; i < this.getArmySize() - 1; ++i) {
                for(int j = i + 1; j < this.getArmySize(); ++j) {
                    if (!this.getArmy(i).inRetreat && !this.getArmy(j).inRetreat && !this.getArmy(i).inBattle && !this.getArmy(j).inBattle && DiplomacyManager.isAtWar(this.getArmy(i).civID, this.getArmy(j).civID)) {
                        String tKey = Game.getCiv(this.getArmy(i).civID).diplomacy.getWarKey(this.getArmy(i).civID, this.getArmy(j).civID);
                        if (tKey == null) {
                            tKey = WarManager.getWarKey(this.getArmy(i).civID, this.getArmy(j).civID);
                        }

                        if (tKey != null && WarManager.lWars.containsKey(tKey)) {
                            if (((War)WarManager.lWars.get(tKey)).isAggressor(this.getArmy(i).civID)) {
                                tAttackingDiv.add(this.getArmy(i));
                                tDefendingDiv.add(this.getArmy(j));
                                this.getArmy(i).addedToBattle = true;
                                this.getArmy(j).addedToBattle = true;
                                break;
                            }

                            if (((War)WarManager.lWars.get(tKey)).isAggressor(this.getArmy(j).civID)) {
                                tAttackingDiv.add(this.getArmy(j));
                                tDefendingDiv.add(this.getArmy(i));
                                this.getArmy(i).addedToBattle = true;
                                this.getArmy(j).addedToBattle = true;
                                break;
                            }
                        } else if ((this.getArmy(i).civID < 0 || this.getArmy(j).civID < 0) && this.getArmy(i).civID != this.getArmy(j).civID) {
                            rebels = true;
                            if (this.getArmy(i).civID < 0) {
                                tAttackingDiv.add(this.getArmy(j));
                                tDefendingDiv.add(this.getArmy(i));
                                this.getArmy(i).addedToBattle = true;
                                this.getArmy(j).addedToBattle = true;
                            } else {
                                tAttackingDiv.add(this.getArmy(i));
                                tDefendingDiv.add(this.getArmy(j));
                                this.getArmy(i).addedToBattle = true;
                                this.getArmy(j).addedToBattle = true;
                            }
                            break;
                        }
                    }
                }

                if (tAttackingDiv.size() > 0) {
                    break;
                }
            }

            if (tAttackingDiv.size() > 0 && tDefendingDiv.size() > 0) {
                ++i;
                if (rebels) {
                    for(; i < this.getArmySize(); ++i) {
                        if (!this.getArmy(i).inRetreat && !this.getArmy(i).inBattle && !this.getArmy(i).addedToBattle) {
                            if (this.getArmy(i).civID < 0) {
                                tDefendingDiv.add(this.getArmy(i));
                            } else {
                                tAttackingDiv.add(this.getArmy(i));
                            }
                        }
                    }
                } else {
                    for(; i < this.getArmySize(); ++i) {
                        if (!this.getArmy(i).inRetreat && !this.getArmy(i).inBattle && !this.getArmy(i).addedToBattle) {
                            if (DiplomacyManager.isAtWar(this.getArmy(i).civID, ((ArmyDivision)tAttackingDiv.get(0)).civID)) {
                                tDefendingDiv.add(this.getArmy(i));
                            } else if (DiplomacyManager.isAtWar(this.getArmy(i).civID, ((ArmyDivision)tDefendingDiv.get(0)).civID)) {
                                tAttackingDiv.add(this.getArmy(i));
                            }
                        }
                    }
                }

                if (SteamMultiManager.isHost()) {
                    M_BattleStart.battleStartedList.add(new M_BattleStart.BattleStarted(this.getProvinceID(), tAttackingDiv, tDefendingDiv));
                } else if (battleStarted != null) {
                    int attackingSize = tAttackingDiv.size();
                    int defendingSize = tDefendingDiv.size();

                    for(int j = 0; j < battleStarted.armiesAttacking.size(); ++j) {
                        boolean found = false;

                        for(int k = 0; k < attackingSize; ++k) {
                            if (((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesAttacking.get(j)).civID == ((ArmyDivision)tAttackingDiv.get(k)).civID && ((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesAttacking.get(j)).key.equals(((ArmyDivision)tAttackingDiv.get(k)).key)) {
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            CFG.LOG("Province, startBattle: ARMY NOT FOUND ATT: " + (((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesAttacking.get(j)).civID > 0 ? Game.getCiv(((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesAttacking.get(j)).civID).getCivName() : "CivID: " + ((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesAttacking.get(j)).civID) + ", key: " + ((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesAttacking.get(j)).key);

                            for(int k = 0; k < defendingSize; ++k) {
                                if (((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesAttacking.get(j)).civID == ((ArmyDivision)tDefendingDiv.get(k)).civID && ((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesAttacking.get(j)).key.equals(((ArmyDivision)tDefendingDiv.get(k)).key)) {
                                    found = true;
                                    break;
                                }
                            }

                            if (found) {
                                CFG.LOG("Province, startBattle: ARMY ATT FOUND ON THE OPPOSITE SIDE: " + (((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesAttacking.get(j)).civID > 0 ? Game.getCiv(((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesAttacking.get(j)).civID).getCivName() : "CivID: " + ((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesAttacking.get(j)).civID) + ", key: " + ((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesAttacking.get(j)).key);
                            }
                        }
                    }

                    for(int j = 0; j < battleStarted.armiesDefending.size(); ++j) {
                        boolean found = false;

                        for(int k = 0; k < defendingSize; ++k) {
                            if (((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesDefending.get(j)).civID == ((ArmyDivision)tDefendingDiv.get(k)).civID && ((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesDefending.get(j)).key.equals(((ArmyDivision)tDefendingDiv.get(k)).key)) {
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            CFG.LOG("Province, startBattle: ARMY NOT FOUND DEF: " + (((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesDefending.get(j)).civID > 0 ? Game.getCiv(((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesDefending.get(j)).civID).getCivName() : "CivID: " + ((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesDefending.get(j)).civID) + ", key: " + ((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesDefending.get(j)).key);

                            for(int k = 0; k < attackingSize; ++k) {
                                if (((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesDefending.get(j)).civID == ((ArmyDivision)tAttackingDiv.get(k)).civID && ((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesDefending.get(j)).key.equals(((ArmyDivision)tAttackingDiv.get(k)).key)) {
                                    found = true;
                                    break;
                                }
                            }

                            if (found) {
                                CFG.LOG("Province, startBattle: ARMY DEF FOUND ON THE OPPOSITE SIDE: " + (((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesDefending.get(j)).civID > 0 ? Game.getCiv(((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesDefending.get(j)).civID).getCivName() : "CivID: " + ((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesDefending.get(j)).civID) + ", key: " + ((M_BattleStart.BattleStarted_ArmyData)battleStarted.armiesDefending.get(j)).key);
                            }
                        }
                    }
                }

                Game.battleManager.addBattle(new Battle(this.getProvinceID(), tAttackingDiv, tDefendingDiv));
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void resetColonizationData() {
        Game.getProvinceData9(this.getProvinceID()).setColonizationStartedTurnID(-1);
        Game.getProvinceData9(this.getProvinceID()).setColonizationGrowthRateExtra(0);
    }

    public void updateDrawArmy() {
        this.drawArmy = ProvinceDrawArmy.updateDrawArmy(this.iProvinceID);
    }

    public final void updateProvinceValue() {
        this.fProvinceValue = GameValues.province.PROVINCE_VALUE_BASE;
        this.fProvinceValue += this.getProvinceValue_Economy();
        this.fProvinceValue += this.getProvinceValue_GrowthRate();
        if (this.wonderID >= 0 && this.getWonderBuilt()) {
            this.fProvinceValue += GameValues.province.PROVINCE_VALUE_WONDER_BUILT;
        }

        if (this.isCapital) {
            this.fProvinceValue += GameValues.province.PROVINCE_VALUE_CAPITAL;
        }

    }

    public final float getProvinceValue_Economy() {
        return Math.min(GameValues.province.PROVINCE_VALUE_PER_ECONOMY_MAX, GameValues.province.PROVINCE_VALUE_PER_ECONOMY * this.getEconomyWithBonuses());
    }

    public final float getProvinceValue_GrowthRate() {
        return Math.min(GameValues.province.PROVINCE_VALUE_PER_GROWTH_RATE_MAX, GameValues.province.PROVINCE_VALUE_PER_GROWTH_RATE * this.getGrowthRateWithBonuses());
    }

    public boolean getFogDrawArmy() {
        return this.fogDrawArmy;
    }

    public void setFogDrawArmy(boolean nFogDrawArmy) {
        this.fogDrawArmy = nFogDrawArmy;
        if (this.fogDrawArmy) {
            this.fog_drawLandProvince = new Fog_DrawLandProvince() {
                public void drawLandProvinceFog(SpriteBatch oSB, float fProvinceAlpha) {
                    Province.this.drawLandProvince(oSB, fProvinceAlpha);
                }
            };
        } else {
            this.fog_drawLandProvince = new Fog_DrawLandProvince() {
                public void drawLandProvinceFog(SpriteBatch oSB, float fProvinceAlpha) {
                    Province.this.drawLandProvince_Fog(oSB, fProvinceAlpha);
                }
            };
        }

    }

    public interface Fog_DrawLandProvince {
        void drawLandProvinceFog(SpriteBatch var1, float var2);
    }
}
