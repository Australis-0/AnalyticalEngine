//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.graph;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_Religion;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_Goods_LargestProducers;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightReligion;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Graph_Vertical_Data {
    public int iCivID;
    public List<Graph_Vertical_Data_Value> lValues = new ArrayList();
    public boolean inView = true;
    public long lTime = 0L;
    public static final int ANIMATION_TIME = 300;

    public Graph_Vertical_Data(int iCivID) {
        this.iCivID = iCivID;
    }

    public final void buildContinentData() {
        this.lValues.clear();
        List<Integer> numOfProvincesByContinents = new ArrayList();

        for(int i = 1; i < Game.continents.iContinentsSize; ++i) {
            numOfProvincesByContinents.add(0);
        }

        try {
            for(int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                int tID = Math.max(0, Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getContinent() - 1);
                numOfProvincesByContinents.set(tID, (Integer)numOfProvincesByContinents.get(tID) + 1);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if ((Integer)numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)numOfProvincesByContinents.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_Population() {
        this.lValues.clear();
        List<Integer> numOfProvincesByContinents = new ArrayList();

        for(int i = 1; i < Game.continents.iContinentsSize; ++i) {
            numOfProvincesByContinents.add(0);
        }

        try {
            for(int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                int tID = Math.max(0, Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getContinent() - 1);
                numOfProvincesByContinents.set(tID, (Integer)numOfProvincesByContinents.get(tID) + Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getPopulationTotal());
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if ((Integer)numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)numOfProvincesByContinents.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_GovernmentCivs() {
        this.lValues.clear();
        List<Integer> populationData = new ArrayList();
        populationData.add((int)Game.getCiv(this.iCivID).getPopulationTotal());
        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < populationData.size(); ++i) {
                if ((Integer)populationData.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)populationData.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_ReligionCivs() {
        this.lValues.clear();
        List<Integer> populationReligion = new ArrayList();
        populationReligion.add(0);

        try {
            for(int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                if (Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getReligion() == InGame_Civ_Religion.iReligionID) {
                    populationReligion.set(0, (Integer)populationReligion.get(0) + Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getPopulationTotal());
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < populationReligion.size(); ++i) {
                if ((Integer)populationReligion.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)populationReligion.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_ReligionCivs_Right() {
        this.lValues.clear();
        List<Integer> populationReligion = new ArrayList();
        populationReligion.add(0);

        try {
            for(int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                if (Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getReligion() == InGame_RightReligion.iReligionID) {
                    populationReligion.set(0, (Integer)populationReligion.get(0) + Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getPopulationTotal());
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < populationReligion.size(); ++i) {
                if ((Integer)populationReligion.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)populationReligion.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_ConstructedBuildings() {
        this.lValues.clear();
        List<Integer> numOfProvincesByContinents = new ArrayList();

        for(int i = 1; i < Game.continents.iContinentsSize; ++i) {
            numOfProvincesByContinents.add(0);
        }

        try {
            for(int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                int tID = Math.max(0, Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getContinent() - 1);
                numOfProvincesByContinents.set(tID, (Integer)numOfProvincesByContinents.get(tID) + Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).iBuildingsSize);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if ((Integer)numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)numOfProvincesByContinents.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_Infrastructure() {
        this.lValues.clear();
        List<Integer> numOfProvincesByContinents = new ArrayList();

        for(int i = 1; i < Game.continents.iContinentsSize; ++i) {
            numOfProvincesByContinents.add(0);
        }

        try {
            for(int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                int tID = Math.max(0, Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getContinent() - 1);
                numOfProvincesByContinents.set(tID, (Integer)numOfProvincesByContinents.get(tID) + Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getInfrastructure());
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if ((Integer)numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)numOfProvincesByContinents.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_UnlockedTechnologies() {
        this.lValues.clear();
        List<Integer> numOfProvincesByContinents = new ArrayList();
        numOfProvincesByContinents.add(Game.getCiv(this.iCivID).getResearchedTechnologies());
        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if ((Integer)numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)numOfProvincesByContinents.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_Prestige() {
        this.lValues.clear();
        List<Integer> numOfProvincesByContinents = new ArrayList();
        numOfProvincesByContinents.add((int)Game.getCiv(this.iCivID).iCivRankScore);
        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if ((Integer)numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)numOfProvincesByContinents.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_CivsProvinces() {
        this.lValues.clear();
        List<Integer> numOfProvincesByContinents = new ArrayList();
        numOfProvincesByContinents.add(Game.getCiv(this.iCivID).getNumOfProvinces());
        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if ((Integer)numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)numOfProvincesByContinents.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_CivsPopulation() {
        this.lValues.clear();
        List<Integer> numOfProvincesByContinents = new ArrayList();
        numOfProvincesByContinents.add((int)Game.getCiv(this.iCivID).getPopulationTotal());
        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if ((Integer)numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)numOfProvincesByContinents.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_ResourceProduction() {
        this.lValues.clear();
        List<Integer> numOfProvincesByContinents = new ArrayList();
        numOfProvincesByContinents.add((int)ResourcesManager.getProducedGoods_ResourceCiv(this.iCivID, InGame_Goods_LargestProducers.RESOURCE_ID));
        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if ((Integer)numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)numOfProvincesByContinents.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_RegimentsLimit() {
        this.lValues.clear();
        List<Integer> numOfProvincesByContinents = new ArrayList();
        numOfProvincesByContinents.add(Game.getCiv(this.iCivID).iRegimentsLimit);
        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if ((Integer)numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer)numOfProvincesByContinents.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void buildContinentData_Economy() {
        this.lValues.clear();
        List<Integer> numOfProvincesByContinents = new ArrayList();
        List<Float> FloatNumOfProvincesByContinents = new ArrayList();

        for(int i = 1; i < Game.continents.iContinentsSize; ++i) {
            FloatNumOfProvincesByContinents.add(0.0F);
        }

        try {
            for(int i = 0; i < Game.getCiv(this.iCivID).getNumOfProvinces(); ++i) {
                int tID = Math.max(0, Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getContinent() - 1);
                FloatNumOfProvincesByContinents.set(tID, (Float)FloatNumOfProvincesByContinents.get(tID) + Game.getProvince(Game.getCiv(this.iCivID).getProvinceID(i)).getEconomyWithBonuses());
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        for(int i = 1; i < Game.continents.iContinentsSize; ++i) {
            numOfProvincesByContinents.add((int) FloatNumOfProvincesByContinents.get(i - 1).floatValue());
        }

        List<Graph_Vertical_Data_Value> tempValues = new ArrayList();

        try {
            for(int i = 0; i < numOfProvincesByContinents.size(); ++i) {
                if ((Integer)numOfProvincesByContinents.get(i) > 0) {
                    tempValues.add(new Graph_Vertical_Data_Value((Integer) numOfProvincesByContinents.get(i), i));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        while(tempValues.size() > 0) {
            int tempMaxID = 0;

            for(int i = 1; i < tempValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)tempValues.get(tempMaxID)).getValue() < ((Graph_Vertical_Data_Value)tempValues.get(i)).getValue()) {
                    tempMaxID = i;
                }
            }

            this.lValues.add((Graph_Vertical_Data_Value)tempValues.get(tempMaxID));
            tempValues.remove(tempMaxID);
        }

    }

    public final void drawData(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Color> nColors, List<Integer> tSorted) {
        if (this.lTime == 0L) {
            this.lTime = CFG.currentTimeMillis;
        }

        int tempValuesHeight = 0;
        if (this.lTime + 300L > CFG.currentTimeMillis) {
            int tempHeight = 0;

            for(int i = 0; i < this.lValues.size(); ++i) {
                tempHeight += ((Graph_Vertical_Data_Value)this.lValues.get(i)).getHeight();
            }

            tempHeight = (int)((float)tempHeight * ((float)(CFG.currentTimeMillis - this.lTime) / 300.0F));
            tempValuesHeight = tempHeight;
            int i = 0;

            for(int tempAnimationHeight = 0; i < this.lValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)this.lValues.get(i)).getValue() > 0) {
                    try {
                        ((Graph_Vertical_Data_Value)this.lValues.get(i)).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempAnimationHeight, tempHeight >= ((Graph_Vertical_Data_Value)this.lValues.get(i)).getHeight() ? ((Graph_Vertical_Data_Value)this.lValues.get(i)).getHeight() : tempHeight, (Color)nColors.get((Integer)tSorted.get(((Graph_Vertical_Data_Value)this.lValues.get(i)).getDataTypeID())));
                    } catch (Exception var15) {
                        ((Graph_Vertical_Data_Value)this.lValues.get(i)).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempAnimationHeight, tempHeight >= ((Graph_Vertical_Data_Value)this.lValues.get(i)).getHeight() ? ((Graph_Vertical_Data_Value)this.lValues.get(i)).getHeight() : tempHeight, Color.WHITE);
                    }

                    tempAnimationHeight += ((Graph_Vertical_Data_Value)this.lValues.get(i)).getHeight();
                    tempHeight -= ((Graph_Vertical_Data_Value)this.lValues.get(i)).getHeight();
                    if (tempHeight <= 0) {
                        break;
                    }
                }
            }
        } else {
            for(int i = 0; i < this.lValues.size(); ++i) {
                if (((Graph_Vertical_Data_Value)this.lValues.get(i)).getValue() > 0) {
                    try {
                        ((Graph_Vertical_Data_Value)this.lValues.get(i)).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, (Color)nColors.get((Integer)tSorted.get(((Graph_Vertical_Data_Value)this.lValues.get(i)).getDataTypeID())));
                    } catch (Exception var14) {
                        ((Graph_Vertical_Data_Value)this.lValues.get(i)).draw(oSB, iPosX, iPosY + iHeight, iWidth, tempValuesHeight, Color.WHITE);
                    }

                    tempValuesHeight += ((Graph_Vertical_Data_Value)this.lValues.get(i)).getHeight();
                }
            }
        }

        oSB.setColor(Color.WHITE);

        try {
            Game.getCiv(this.iCivID).getFlag().draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        } catch (Exception var13) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }

        ImageManager.getImage(Images.flag_rect).draw(oSB, iPosX, iPosY + iHeight - tempValuesHeight - CFG.PADDING - CFG.CIV_FLAG_HEIGHT, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
    }

    public final void drawDataTextValue(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight) {
        Renderer.drawTextRotated(oSB, "" + this.getValue(), iPosX + iWidth / 2 - CFG.TEXT_HEIGHT / 2, iPosY + iHeight - CFG.PADDING, new Color(1.0F, 1.0F, 1.0F, 0.85F), 90.0F);
    }

    public final void drawDataTextValue_Splitted(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight) {
        Renderer.drawTextRotated(oSB, "" + (float)this.getValue() / 100.0F, iPosX + iWidth / 2 - CFG.TEXT_HEIGHT / 2, iPosY + iHeight - CFG.PADDING, new Color(1.0F, 1.0F, 1.0F, 0.45F), 90.0F);
    }

    public final void drawDataTextValue_Short(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight) {
        try {
            Renderer.drawTextRotated(oSB, "" + CFG.getShortNumber(this.getValue()), iPosX + iWidth / 2 - CFG.TEXT_HEIGHT / 2, iPosY + iHeight - CFG.PADDING, new Color(1.0F, 1.0F, 1.0F, 0.45F), 90.0F);
        } catch (Exception var7) {
        }

    }

    public final void buildHeights(int nGraphHeight, int nMaxValue) {
        for (int i = 0; i < this.lValues.size(); ++i)
            ((Graph_Vertical_Data_Value) this.lValues.get(i)).setHeight((int)((float)((Graph_Vertical_Data_Value)this.lValues.get(i)).getValue() / (float)nMaxValue * (float)nGraphHeight));
    }

    public final int getCivID() {
        return this.iCivID;
    }

    public final int getValue() {
        int tOut = 0;

        for(int i = 0; i < this.lValues.size(); ++i) {
            tOut += ((Graph_Vertical_Data_Value)this.lValues.get(i)).getValue();
        }

        return tOut;
    }

    public final boolean getInView() {
        return this.inView;
    }

    public final void setInView(boolean inView) {
        this.inView = inView;
    }

    public final void resetAnimation() {
        this.lTime = 0L;
    }

    public final int getValuesSize() {
        return this.lValues.size();
    }

    public final int getValue(int i) {
        return ((Graph_Vertical_Data_Value)this.lValues.get(i)).getValue();
    }

    public final int getValueDataTypeID(int i) {
        return ((Graph_Vertical_Data_Value)this.lValues.get(i)).getDataTypeID();
    }
}
