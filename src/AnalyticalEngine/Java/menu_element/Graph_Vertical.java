//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.graph;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRanking;
import aoc.kingdoms.lukasz.map.map.Continents;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.MenuElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_Government;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_Religion;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_Goods_LargestProducers;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightGovernment;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightReligion;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Graph_Vertical extends MenuElement {
    public static List<Integer> graphCivs = new ArrayList();
    public List<Graph_Vertical_Data> lValues = new ArrayList();
    public int iValuesSize = 0;
    public int iValuesTotal = 0;
    public int iDataWidth = 0;
    public Graph_Vertical_Info verticalInfo;
    public Graph_Vertical_Data_Type GRAPH_DATA_TYPE;
    public boolean splitBy100 = false;
    public boolean drawShort = false;
    public boolean statisticsMode = false;
    public String sTextX;
    public String sTextY;
    public int iWidthTextY;
    public int iMinPoint;
    public int iMaxPoint;
    public float fAvaragePoint;
    public int iAvaragePosY;
    public byte bDecimal = 0;
    public boolean lessThanTen = false;
    public String sTotal = "";
    public boolean moveable = false;
    public int iButtonsPosX;
    public int iButtonsPosY;
    public int iHoveredID = -1;
    public boolean scrollModeY = false;
    public int iScrollPosX = -1;
    public int iScrollPosX2 = -1;
    public float fScrollNewMenuPosY = 0.0F;
    public DrawStatisticsData drawStatisticsData;
    public boolean allowStatisticsMode = true;

    public List<String> graph_labels = new ArrayList();

    public Graph_Vertical(Graph_Vertical_Data_Type nType, String sTextX, String sTextY, int iPosX, int iPosY, int iWidth, int iHeight, boolean visible) {
        this.GRAPH_DATA_TYPE = nType;
        this.sTotal = Game.lang.get("Total");
        if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue() + " [" + CFG.getPercentage(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.iValuesTotal, 4) + "%]", Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_LIST_PROVINCES) {
            for(int i = graphCivs.size() - 1; i >= 0; --i) {
                if ((Integer)graphCivs.get(i) > 0 && (Integer)graphCivs.get(i) < Game.getCivsSize() && Game.getCiv((Integer)graphCivs.get(i)).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data((Integer)graphCivs.get(i)));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j)), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue()) + " [" + CFG.getPercentage(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_LIST_POPULATION) {
            for(int i = graphCivs.size() - 1; i >= 0; --i) {
                if ((Integer)graphCivs.get(i) > 0 && (Integer)graphCivs.get(i) < Game.getCivsSize() && Game.getCiv((Integer)graphCivs.get(i)).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data((Integer)graphCivs.get(i)));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j)), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue()) + " [" + CFG.getPercentage(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_POPULATION) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j)), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue()) + " [" + CFG.getPercentage(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.GOVERNMENTS_CIVS) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).getIdeologyID() == InGame_Civ_Government.iGovID) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j)), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue()) + " [" + CFG.getPercentage(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.GOVERNMENTS_CIVS_RIGHT) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0 && Game.getCiv(i).getIdeologyID() == InGame_RightGovernment.iGovID) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j)), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue()) + " [" + CFG.getPercentage(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RELIGION_CIVS) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                for(int j = 0; j < Game.getCiv(i).getNumOfProvinces(); ++j) {
                    if (Game.getProvince(Game.getCiv(i).getProvinceID(j)).getReligion() == InGame_Civ_Religion.iReligionID) {
                        this.lValues.add(new Graph_Vertical_Data(i));
                        break;
                    }
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j)), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue()) + " [" + CFG.getPercentage(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RELIGION_CIVS_RIGHT) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                for(int j = 0; j < Game.getCiv(i).getNumOfProvinces(); ++j) {
                    if (Game.getProvince(Game.getCiv(i).getProvinceID(j)).getReligion() == InGame_RightReligion.iReligionID) {
                        this.lValues.add(new Graph_Vertical_Data(i));
                        break;
                    }
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getNumberWithSpaces("" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j)), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + CFG.getShortNumber(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue()) + " [" + CFG.getPercentage(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + CFG.getShortNumber(Math.max(1, Graph_Vertical.this.iValuesTotal)) + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_INFRASTRUCTURE) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue() + " [" + CFG.getPercentage(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_CONSTRUCTED_BUILDINGS) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue() + " [" + CFG.getPercentage(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_ECONOMY) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue() + " [" + CFG.getPercentage(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.iValuesTotal, 3) + "%]", Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_UNLOCKED_TECHS) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_PRESTIGE) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RESOURCE_PRODUCTION) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_REGIMENTS_LIMIT) {
            for(int i = 1; i < Game.getCivsSize(); ++i) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    this.lValues.add(new Graph_Vertical_Data(i));
                }
            }

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for(int j = 0; j < ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValuesSize(); ++j) {
                        Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(j), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + Graph_Vertical.this.getStatisticsWidth() * 2 + Graph_Vertical.this.getStatisticsWidth() * Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(j)) + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                    }

                    Graph_Vertical.this.drawStatisticsValue(oSB, "" + ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getValue(), Graph_Vertical.this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, Graph_Vertical.this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY);
                }

                public String getTotal() {
                    return Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]";
                }

                public String getStatsLP(int i) {
                    return Game.getCiv(((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID()).getCivName();
                }

                public int getStatsLPCivFlagID(int i) {
                    return ((Graph_Vertical_Data)Graph_Vertical.this.lValues.get(i)).getCivID();
                }
            };
        } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CUSTOM) {
            for (int i = 0; i < this.lValues.size(); i++)
                this.lValues.add(new Graph_Vertical_Data(0));

            this.iValuesSize = this.lValues.size();
            this.drawShort = true;
            this.drawStatisticsData = new DrawStatisticsData() {
                public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                    for (int x = 0; x < ((Graph_Vertical_Data) Graph_Vertical.this.lValues.get(i)).getValuesSize(); x++)
                        Graph_Vertical.this.drawStatisticsValue(
                            oSB,
                            "" + ((Graph_Vertical_Data) Graph_Vertical.this.lValues.get(x)).getValue(x),
                            Graph_Vertical.this.getPosX() + CFG.PADDING*2 + Graph_Vertical.this.getStatisticsWidth()*2 + Graph_Vertical.this.getStatisticsWidth()*Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data) Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(x) + iTranslateX),
                            Graph_Vertical.this.getPosY() + ((int) ((float) CFG.TEXT_HEIGHT*0.7F) + CFG.PADDING*2)*(i + 1) + iTranslateY
                        );

                    Graph_Vertical.this.drawStatisticsValue(
                        oSB,
                        "" + ((Graph_Vertical_Data) Graph_Vertical.this.lValues.get(i)).getValue(),
                        Graph_Vertical.this.getPosX() + CFG.PADDING*2 + tempOffsetX + iTranslateX,
                        Graph_Vertical.this.getPosY() + ((int) ((float) CFG.TEXT_HEIGHT*0.7F) + CFG.PADDING*2)*(i + 1) + iTranslateY
                    );
                }

                public String getTotal() {
                    return (Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]");
                }

                public String getStatsLP (int i) {
                    return "Custom";
                }

                public int getStatsLPCivFlagID(int i) {
                    return 0;
                }
            };
        }

        this.iDataWidth = CFG.CIV_FLAG_WIDTH;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setVisible(visible);
        this.sTextX = sTextX;
        this.sTextY = sTextY;
        ((BitmapFont)Renderer.fontMain.get(0)).getData().setScale(0.7F);
        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(0), sTextY);
        this.iWidthTextY = (int)Renderer.glyphLayout.width;
        ((BitmapFont)Renderer.fontMain.get(0)).getData().setScale(1.0F);
        this.buildData();
        this.buildValuesHeights();
        this.typeOfElement = MenuElement_Type.GRAPH_VERTICAL;
    }

    public final void buildData() {
        if (this.lValues.size() != 0) {
            List<String> nTexts = new ArrayList();
            List<Color> nColors = new ArrayList();
            if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData();
                }

                for(int i = 1; i < Game.continents.iContinentsSize; ++i) {
                    nTexts.add(((Continents.Continent)Game.continents.lContinents.get(i)).sName);
                    nColors.add(new Color((float)((Continents.Continent)Game.continents.lContinents.get(i)).iR / 255.0F, (float)((Continents.Continent)Game.continents.lContinents.get(i)).iG / 255.0F, (float)((Continents.Continent)Game.continents.lContinents.get(i)).iB / 255.0F, 1.0F));
                }
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_POPULATION) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_Population();
                }

                for(int i = 1; i < Game.continents.iContinentsSize; ++i) {
                    nTexts.add(((Continents.Continent)Game.continents.lContinents.get(i)).sName);
                    nColors.add(new Color((float)((Continents.Continent)Game.continents.lContinents.get(i)).iR / 255.0F, (float)((Continents.Continent)Game.continents.lContinents.get(i)).iG / 255.0F, (float)((Continents.Continent)Game.continents.lContinents.get(i)).iB / 255.0F, 1.0F));
                }
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_LIST_PROVINCES) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_CivsProvinces();
                }

                nTexts.add(Game.lang.get("Provinces"));
                nColors.add(Colors.HOVER_GOLD);
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_LIST_POPULATION) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_CivsPopulation();
                }

                nTexts.add(Game.lang.get("Population"));
                nColors.add(Colors.COLOR_POPULATION);
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.GOVERNMENTS_CIVS) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_GovernmentCivs();
                }

                nTexts.add(Game.lang.get("Population"));
                nColors.add(new Color(Game.ideologiesManager.getIdeology(InGame_Civ_Government.iGovID).Color[0], Game.ideologiesManager.getIdeology(InGame_Civ_Government.iGovID).Color[1], Game.ideologiesManager.getIdeology(InGame_Civ_Government.iGovID).Color[2], 1.0F));
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.GOVERNMENTS_CIVS_RIGHT) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_GovernmentCivs();
                }

                nTexts.add(Game.lang.get("Population"));
                nColors.add(new Color(Game.ideologiesManager.getIdeology(InGame_RightGovernment.iGovID).Color[0], Game.ideologiesManager.getIdeology(InGame_RightGovernment.iGovID).Color[1], Game.ideologiesManager.getIdeology(InGame_RightGovernment.iGovID).Color[2], 1.0F));
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RELIGION_CIVS) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_ReligionCivs();
                }

                nTexts.add(Game.lang.get("Population"));
                nColors.add(new Color(Game.religionManager.getReligion(InGame_Civ_Religion.iReligionID).Color[0], Game.religionManager.getReligion(InGame_Civ_Religion.iReligionID).Color[1], Game.religionManager.getReligion(InGame_Civ_Religion.iReligionID).Color[2], 1.0F));
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RELIGION_CIVS_RIGHT) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_ReligionCivs_Right();
                }

                nTexts.add(Game.lang.get("Population"));
                nColors.add(new Color(Game.religionManager.getReligion(InGame_RightReligion.iReligionID).Color[0], Game.religionManager.getReligion(InGame_RightReligion.iReligionID).Color[1], Game.religionManager.getReligion(InGame_RightReligion.iReligionID).Color[2], 1.0F));
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_CONSTRUCTED_BUILDINGS) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_ConstructedBuildings();
                }

                for(int i = 1; i < Game.continents.iContinentsSize; ++i) {
                    nTexts.add(((Continents.Continent)Game.continents.lContinents.get(i)).sName);
                    nColors.add(new Color((float)((Continents.Continent)Game.continents.lContinents.get(i)).iR / 255.0F, (float)((Continents.Continent)Game.continents.lContinents.get(i)).iG / 255.0F, (float)((Continents.Continent)Game.continents.lContinents.get(i)).iB / 255.0F, 1.0F));
                }
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_INFRASTRUCTURE) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_Infrastructure();
                }

                for(int i = 1; i < Game.continents.iContinentsSize; ++i) {
                    nTexts.add(((Continents.Continent)Game.continents.lContinents.get(i)).sName);
                    nColors.add(new Color((float)((Continents.Continent)Game.continents.lContinents.get(i)).iR / 255.0F, (float)((Continents.Continent)Game.continents.lContinents.get(i)).iG / 255.0F, (float)((Continents.Continent)Game.continents.lContinents.get(i)).iB / 255.0F, 1.0F));
                }
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_ECONOMY) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_Economy();
                }

                for(int i = 1; i < Game.continents.iContinentsSize; ++i) {
                    nTexts.add(((Continents.Continent)Game.continents.lContinents.get(i)).sName);
                    nColors.add(new Color((float)((Continents.Continent)Game.continents.lContinents.get(i)).iR / 255.0F, (float)((Continents.Continent)Game.continents.lContinents.get(i)).iG / 255.0F, (float)((Continents.Continent)Game.continents.lContinents.get(i)).iB / 255.0F, 1.0F));
                }
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_UNLOCKED_TECHS) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_UnlockedTechnologies();
                }

                nTexts.add(Game.lang.get("UnlockedTechnologies"));
                nColors.add(Colors.HOVER_GOLD);
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_PRESTIGE) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_Prestige();
                }

                nTexts.add(Game.lang.get("Prestige"));
                nColors.add(Colors.TECH_BLUE2);
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RESOURCE_PRODUCTION) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_ResourceProduction();
                }

                nTexts.add(Game.lang.get("Production"));
                nColors.add(Colors.TECH_BLUE2);
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_REGIMENTS_LIMIT) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).buildContinentData_RegimentsLimit();
                }

                nTexts.add(Game.lang.get("RegimentsLimit"));
                nColors.add(Colors.TECH_BLUE);
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CUSTOM) { //FIXED - This line is correct.
                //There has
                //[WIP] - Finish function body - we need to call .buildCustomData() somehow
                nTexts.add(Game.lang.get(this.sTextX));
                nColors.add(Colors.COLOR_TEXT_GOLD);
            }

            this.verticalInfo = new Graph_Vertical_Info(nTexts, nColors, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), true);
            List<Graph_Vertical_Data> tempData = new ArrayList();

            for(int i = 0; i < this.iValuesSize; ++i) {
                tempData.add((Graph_Vertical_Data)this.lValues.get(i));
            }

            this.lValues.clear();

            while(tempData.size() > 0) {
                int tempMaxID = 0;

                for(int i = 1; i < tempData.size(); ++i) {
                    if (((Graph_Vertical_Data)tempData.get(i)).getValue() > ((Graph_Vertical_Data)tempData.get(tempMaxID)).getValue()) {
                        tempMaxID = i;
                    }
                }

                this.lValues.add((Graph_Vertical_Data)tempData.get(tempMaxID));
                tempData.remove(tempMaxID);
            }

            try {
                this.iMinPoint = this.iMaxPoint = ((Graph_Vertical_Data)this.lValues.get(0)).getValue();
            } catch (IndexOutOfBoundsException var8) {
                this.iMinPoint = 0;
            }

            this.fAvaragePoint = 0.0F;
            long tempAvarage = 0L;
            int tempAvarageSize = 0;

            for(int i = 0; i < this.iValuesSize; ++i) {
                if (this.iMaxPoint < ((Graph_Vertical_Data)this.lValues.get(i)).getValue()) {
                    this.iMaxPoint = ((Graph_Vertical_Data)this.lValues.get(i)).getValue();
                }

                if (this.iMinPoint > ((Graph_Vertical_Data)this.lValues.get(i)).getValue()) {
                    this.iMinPoint = ((Graph_Vertical_Data)this.lValues.get(i)).getValue();
                }

                if (((Graph_Vertical_Data)this.lValues.get(i)).getValue() > 0) {
                    ++tempAvarageSize;
                    tempAvarage += (long)((Graph_Vertical_Data)this.lValues.get(i)).getValue();
                }
            }

            this.fAvaragePoint = (float)tempAvarage / (float)tempAvarageSize;
            this.iAvaragePosY = (int)((float)(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) - (float)(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) * 100.0F * this.fAvaragePoint / (float)(this.iMaxPoint - this.iMinPoint) / 100.0F);
            this.roundAverage();
            this.updateMoveable();
            this.updateInView();
            this.countValuesTotal();
        }
    }

    //ANALYTICALENGINE START
    public final void setData (List<Integer> arg0_data_values) { //[WIP] - Formalise function later after testing
        //Convert from parameters
        List<Integer> data_values = arg0_data_values;

        // Clear existing lValues
        this.lValues.clear();

        // Populate lValues with Graph_Vertical_Data instances
        for (int i = 0; i < data_values.size(); i++) {
            System.out.println("Called setData() with value " + data_values.get(i));
            // Create a new Graph_Vertical_Data instance for each value
            Graph_Vertical_Data graphData = new Graph_Vertical_Data(0); //[WIP] - This can be specified as civilisation_id. 0 corresponds to 'neu'.

            // Populate its internal lValues with a single Graph_Vertical_Data_Value
            graphData.lValues.add(new Graph_Vertical_Data_Value(data_values.get(i), 0)); //This is a visual bug

            // Add the Graph_Vertical_Data instance to this.lValues
            this.lValues.add(graphData);
        }

        this.iValuesSize = this.lValues.size();
        this.drawShort = true;
        //Make sure to actually build heights
        this.buildData(); //Build data to avoid divide by 0
        this.buildValuesHeights();

        this.drawStatisticsData = new DrawStatisticsData() {
            public void draw(SpriteBatch oSB, int i, int tempOffsetX, int iTranslateX, int iTranslateY) {
                try {
                    for (int x = 0; x < ((Graph_Vertical_Data) Graph_Vertical.this.lValues.get(i)).getValuesSize(); x++)
                        Graph_Vertical.this.drawStatisticsValue(
                                oSB,
                                "" + ((Graph_Vertical_Data) Graph_Vertical.this.lValues.get(x)).getValue(x),
                                Graph_Vertical.this.getPosX() + CFG.PADDING*2 + Graph_Vertical.this.getStatisticsWidth()*2 + Graph_Vertical.this.getStatisticsWidth()*Graph_Vertical.this.verticalInfo.getSortedID(((Graph_Vertical_Data) Graph_Vertical.this.lValues.get(i)).getValueDataTypeID(x) + iTranslateX),
                                Graph_Vertical.this.getPosY() + ((int) ((float) CFG.TEXT_HEIGHT*0.7F) + CFG.PADDING*2)*(i + 1) + iTranslateY
                        );

                    Graph_Vertical.this.drawStatisticsValue(
                            oSB,
                            "" + ((Graph_Vertical_Data) Graph_Vertical.this.lValues.get(i)).getValue(),
                            Graph_Vertical.this.getPosX() + CFG.PADDING*2 + tempOffsetX + iTranslateX,
                            Graph_Vertical.this.getPosY() + ((int) ((float) CFG.TEXT_HEIGHT*0.7F) + CFG.PADDING*2)*(i + 1) + iTranslateY
                    );
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            public String getTotal() {
                return (Graph_Vertical.this.sTotal + " [" + Graph_Vertical.this.iValuesTotal + "]");
            }

            public String getStatsLP (int i) {
                try {
                    return Graph_Vertical.this.graph_labels.get(i);
                } catch (Exception ex) {
                    return "Custom " + i;
                }
            }

            public int getStatsLPCivFlagID(int i) {
                return 0;
            }
        };
    }

    public final void setDataLabels (List<String> arg0_data_labels) {
        //Convert from parameters
        List<String> data_labels = arg0_data_labels;

        //Set this.graph_labels
        this.graph_labels = data_labels;
    }

    //ANALYTICALENGINE END

    public final void countValuesTotal() {
        this.iValuesTotal = 0;

        for(int i = 0; i < this.iValuesSize; ++i) {
            this.iValuesTotal += ((Graph_Vertical_Data)this.lValues.get(i)).getValue();
        }

    }

    public final void buildValuesHeights() {
        for(int i = 0; i < this.iValuesSize; ++i) {
            ((Graph_Vertical_Data)this.lValues.get(i)).buildHeights(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 - (CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2), this.iMaxPoint);
        }

    }

    public void updateHover(int nPosX, int nPosY, int menuPosX, int menuPosY) {
        if (!this.statisticsMode) {
            if (nPosX >= menuPosX + this.getPosX() && nPosX <= menuPosX + this.getPosX() + this.getWidth() && nPosY >= menuPosY + this.getPosY() && nPosY <= menuPosY + this.getPosY() + this.getHeight()) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    if (nPosX >= menuPosX + this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX && nPosX <= menuPosX + this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + this.iDataWidth) {
                        this.setHoveredID(i);
                        return;
                    }
                }
            }

            this.setHoveredID(-1);
        } else {
            if (nPosX >= menuPosX + this.getPosX() && nPosX <= menuPosX + this.getPosX() + this.getWidth() && nPosY >= menuPosY + this.getPosY() && nPosY <= menuPosY + this.getPosY() + this.getHeight()) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    if (nPosY >= menuPosY + this.getPosY() + this.iButtonsPosY + (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)(CFG.PADDING * 2)) * i && nPosY <= menuPosY + this.getPosY() + this.iButtonsPosY + (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)(CFG.PADDING * 2)) * i + (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)(CFG.PADDING * 2))) {
                        this.setHoveredID(i);
                        return;
                    }
                }
            }

            this.setHoveredID(-1);
        }

    }

    public final void setHoveredID(int nHoveredID) {
        if (this.iHoveredID != nHoveredID) {
            this.iHoveredID = nHoveredID;
            this.buildElementHover();
        }

    }

    public void buildElementHover() {
        if (this.iHoveredID >= 0 && !this.statisticsMode) {
            if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.NUM_OF_PROVINCES_BY_CONTINENT) {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getNumOfProvinces()), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_POPULATION) {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_LIST_PROVINCES) {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getNumOfProvinces()), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_LIST_POPULATION) {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            } else if (this.GRAPH_DATA_TYPE != Graph_Vertical_Data_Type.GOVERNMENTS_CIVS && this.GRAPH_DATA_TYPE != Graph_Vertical_Data_Type.GOVERNMENTS_CIVS_RIGHT) {
                if (this.GRAPH_DATA_TYPE != Graph_Vertical_Data_Type.RELIGION_CIVS && this.GRAPH_DATA_TYPE != Graph_Vertical_Data_Type.RELIGION_CIVS_RIGHT) {
                    if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_CONSTRUCTED_BUILDINGS) {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructedBuildings") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getConstructedBuildings()), Images.buildings, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_INFRASTRUCTURE) {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Infrastructure") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getInfrastructure()), Images.infrastructure, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_UNLOCKED_TECHS) {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnlockedTechnologies") + ": ", "" + Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getResearchedTechnologies(), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_PRESTIGE) {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Prestige") + ": ", "" + CFG.getNumberWithSpaces("" + (int)Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).iCivRankScore), CivilizationRanking.getCivilizationRanking_IMG_STAR_CIVID(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()), CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.RESOURCE_PRODUCTION) {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Production") + ": ", "" + CFG.getNumberWithSpaces("" + (int)ResourcesManager.getProducedGoods_ResourceCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), InGame_Goods_LargestProducers.RESOURCE_ID)), Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_REGIMENTS_LIMIT) {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RegimentsLimit") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).iRegimentsLimit), Images.regimentsLimit, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    } else if (this.GRAPH_DATA_TYPE == Graph_Vertical_Data_Type.CIVS_ECONOMY) {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", "" + CFG.getNumberWithSpaces("" + (int)Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getEconomyTotal()), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    } else {
                        this.menuElementHover = null;
                    }
                } else {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            } else {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getCivName()));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID(), CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", "" + CFG.getNumberWithSpaces("" + Game.getCiv(((Graph_Vertical_Data)this.lValues.get(this.iHoveredID)).getCivID()).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        } else {
            this.menuElementHover = null;
        }

    }

    public void setCheckboxState(boolean checkboxState) {
        this.buildValuesHeights();
        this.updateInView();
        this.verticalInfo.updateMoveable(this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2));
        this.updateMoveable();
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (this.scrollModeY) {
            if (this.statisticsMode) {
                if (Math.abs(this.fScrollNewMenuPosY) > 1.0F) {
                    this.setCurrent(this.iButtonsPosY + (int)this.fScrollNewMenuPosY);
                    this.fScrollNewMenuPosY *= 0.97F;
                } else {
                    this.scrollModeY = false;
                }
            } else if (Math.abs(this.fScrollNewMenuPosY) > 1.0F) {
                this.setCurrent(this.iButtonsPosX + (int)this.fScrollNewMenuPosY);
                this.fScrollNewMenuPosY *= 0.97F;
            } else {
                this.scrollModeY = false;
            }
        }

        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.9F));
        ImageManager.getImage(Images.graphBG).draw2(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.3F));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, false, true);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.3F));
        Images.gradientXY.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, false, true);
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.065F));
        ImageManager.getImage(Images.noise).draw2(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
        ((BitmapFont)Renderer.fontMain.get(0)).getData().setScale(0.7F);
        Renderer.drawTextRotated(oSB, this.sTextY, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 + this.iWidthTextY / 2 + iTranslateY, Graph.TEXT_COLOR, 90.0F);
        this.verticalInfo.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING - (int)((float)CFG.TEXT_HEIGHT * 0.7F) + iTranslateY, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2));
        ((BitmapFont)Renderer.fontMain.get(0)).getData().setScale(1.0F);
        if (this.statisticsMode) {
            this.drawStatisticsBegan(oSB, 1 + iTranslateX, iTranslateY, isActive, scrollableY);
        } else {
            Images.pix.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX, this.getPosY() + (this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - CFG.CIV_FLAG_HEIGHT - CFG.PADDING * 2) / 2 + CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2 + 1 + iTranslateY, CFG.PADDING - 1);
            oSB.setColor(Graph.GRAPH_LINES_DESC);
            ImageManager.getImage(Images.line_33).draw2(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2 + 1 + iTranslateY, this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
            ImageManager.getImage(Images.line_33).draw2(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.iAvaragePosY + iTranslateY, this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, 1, 0, -this.iButtonsPosX);
            Renderer.clipView_Start(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - 1, -this.getHeight());
            if (this.getIsHovered() && this.iHoveredID >= 0) {
                oSB.setColor(Graph.GRAPH_LINES_DESC);
                ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + this.getWidth() - 1 - CFG.PADDING * (this.iHoveredID + 1) - CFG.PADDING * this.iHoveredID - this.iDataWidth * (this.iHoveredID + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth + 2, this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false, true);
                oSB.setColor(new Color(Graph.GRAPH_LINES_DESC.r, Graph.GRAPH_LINES_DESC.g, Graph.GRAPH_LINES_DESC.b, 0.025F));
                ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getWidth() - 1 - CFG.PADDING * (this.iHoveredID + 1) - CFG.PADDING * this.iHoveredID - this.iDataWidth * (this.iHoveredID + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, (this.iDataWidth + 2) / 4, this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), false, false);
                ImageManager.getImage(Images.gradientHorizontal).draw(oSB, this.getPosX() + this.getWidth() + this.iDataWidth + 2 - (this.iDataWidth + 2) / 4 - 1 - CFG.PADDING * (this.iHoveredID + 1) - CFG.PADDING * this.iHoveredID - this.iDataWidth * (this.iHoveredID + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, (this.iDataWidth + 2) / 4, this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), true, false);
                oSB.setColor(Color.WHITE);
            }

            if (this.drawShort) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    if (((Graph_Vertical_Data)this.lValues.get(i)).getInView()) {
                        ((Graph_Vertical_Data)this.lValues.get(i)).drawData(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth, this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), this.verticalInfo.getColors(), this.verticalInfo.getSorted());
                        ((Graph_Vertical_Data)this.lValues.get(i)).drawDataTextValue_Short(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth, this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2));
                    }
                }
            } else if (this.splitBy100) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    if (((Graph_Vertical_Data)this.lValues.get(i)).getInView()) {
                        ((Graph_Vertical_Data)this.lValues.get(i)).drawData(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth, this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), this.verticalInfo.getColors(), this.verticalInfo.getSorted());
                        ((Graph_Vertical_Data)this.lValues.get(i)).drawDataTextValue_Splitted(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth, this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2));
                    }
                }
            } else {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    if (((Graph_Vertical_Data)this.lValues.get(i)).getInView()) {
                        ((Graph_Vertical_Data)this.lValues.get(i)).drawData(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth, this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2), this.verticalInfo.getColors(), this.verticalInfo.getSorted());
                        ((Graph_Vertical_Data)this.lValues.get(i)).drawDataTextValue(oSB, this.getPosX() + this.getWidth() - CFG.PADDING * (i + 1) - CFG.PADDING * i - this.iDataWidth * (i + 1) + this.iButtonsPosX + iTranslateX, this.getPosY() + iTranslateY, this.iDataWidth, this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2));
                    }
                }
            }

            Renderer.clipView_End(oSB);
            ((BitmapFont)Renderer.fontMain.get(0)).getData().setScale(Graph.POINTS_TEXT_SCALE);
            Renderer.drawText(oSB, "" + CFG.getNumberWithSpaces("" + this.iMaxPoint), this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateX, this.getPosY() + CFG.CIV_FLAG_HEIGHT + CFG.PADDING * 2 + 1 + iTranslateY - (int)(2.0F * CFG.GUI_SCALE + Graph.POINTS_TEXT_SCALE * (float)CFG.TEXT_HEIGHT), Graph.DATA_COLOR);
            ((BitmapFont)Renderer.fontMain.get(0)).getData().setScale(1.0F);
        }

        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.6F));
        Images.pix.draw(oSB, this.getPosX() - 1 + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + CFG.PADDING);
        Images.pix.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + iTranslateY, this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + CFG.PADDING, 1);
        oSB.setColor(Graph.GRAPH_BORDERS_COLOR);
        Images.pix.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + CFG.PADDING);
        Images.pix.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 - CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + iTranslateY, this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + CFG.PADDING, 1);
        Images.pix.draw(oSB, this.getPosX() + this.getWidth() - CFG.PADDING + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING);
        Images.pix.draw(oSB, this.getPosX() - 1 + this.getWidth() + iTranslateX, this.getPosY() + iTranslateY, 1, CFG.PADDING - 1);
        Images.pix.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING - 1);
        Images.pix.draw(oSB, this.getPosX() + this.getWidth() - 1 + iTranslateX, this.getPosY() + this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - CFG.PADDING + iTranslateY, 1, CFG.PADDING - 1);
        oSB.setColor(Color.WHITE);
    }

    public final void drawStatisticsBegan(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        ((BitmapFont)Renderer.fontMain.get(0)).getData().setScale(0.7F);
        int tempOffsetX = 0;
        this.drawStatisticsBoxTitle(oSB, this.sTextX, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getStatisticsWidth() * 2);
        tempOffsetX += this.getStatisticsWidth() * 2;

        for(int i = 0; i < this.verticalInfo.getTextSize(); ++i) {
            this.drawStatisticsBoxTitle(oSB, this.verticalInfo.getText(i), this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, this.getPosY() + iTranslateY, this.getStatisticsWidth());
            tempOffsetX += this.getStatisticsWidth();
        }

        this.drawStatisticsBoxTitle(oSB, this.drawStatisticsData.getTotal(), this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + tempOffsetX + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() - tempOffsetX - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2));
        Renderer.clipView_Start(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - 2 - iTranslateY, this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - 1, -this.getHeight() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2 + 1);
        this.drawStatisticsEnd(oSB, iTranslateX, this.iButtonsPosY + iTranslateY, isActive, scrollableY, tempOffsetX);
        Renderer.clipView_End(oSB);
        oSB.setColor(Graph.GRAPH_LINES_COLOR);

        for(int i = -1; i < this.verticalInfo.getTextSize() - 1; ++i) {
            ImageManager.getImage(Images.line_32_vertical).draw2(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + this.getStatisticsWidth() * 2 + this.getStatisticsWidth() * (i + 1) - 1 + iTranslateX, this.getPosY() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateY, 1, this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2, false, true);
        }

        Images.pix.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + this.getStatisticsWidth() * 2 + this.getStatisticsWidth() * (this.verticalInfo.getTextSize() - 1 + 1) - 1 + iTranslateX, this.getPosY() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateY, 1, this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2, false, true);
        oSB.setColor(Color.WHITE);
    }

    public final void drawStatisticsEnd(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY, int tempOffsetX) {
        float tempFlagScale = (float)CFG.TEXT_HEIGHT * 0.7F / (float)CFG.CIV_FLAG_HEIGHT;

        for(int i = 0; i < this.iValuesSize; ++i) {
            if (((Graph_Vertical_Data)this.lValues.get(i)).getInView()) {
                if (i % 2 == 0) {
                    this.drawStatisticsRowBG(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY, this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
                }

                if (this.getIsHovered() && i == this.iHoveredID - 1) {
                    this.drawStatisticsRowHoverBG(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY, this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
                }

                this.drawStatisticsRowLine(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY, this.getWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
                oSB.setColor(Color.WHITE);
                Game.getCiv(this.drawStatisticsData.getStatsLPCivFlagID(i)).getFlag().draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)CFG.CIV_COLOR_WIDTH) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + CFG.PADDING + iTranslateY, (int)Math.ceil((double)((float)CFG.CIV_FLAG_WIDTH * tempFlagScale)), (int)Math.ceil((double)((float)CFG.CIV_FLAG_HEIGHT * tempFlagScale)));
                this.drawStatisticsValue2(oSB, this.drawStatisticsData.getStatsLP(i), this.getPosX() + (int)((float)CFG.CIV_FLAG_WIDTH * tempFlagScale + (float)CFG.CIV_COLOR_WIDTH) + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * (i + 1) + iTranslateY, this.getStatisticsWidth() * 2 - (int)((float)CFG.CIV_FLAG_WIDTH * tempFlagScale + (float)CFG.CIV_COLOR_WIDTH));
                this.drawStatisticsData.draw(oSB, i, tempOffsetX, iTranslateX, iTranslateY);
            }
        }

        ((BitmapFont)Renderer.fontMain.get(0)).getData().setScale(1.0F);
    }

    public final void drawStatisticsRowLine(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
        oSB.setColor(Graph.GRAPH_LINES_COLOR);
        Images.line_32_off1.draw(oSB, nPosX, nPosY + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2, nWidth, 1);
    }

    public final void drawStatisticsRowBG(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
        oSB.setColor(new Color(0.05F, 0.05F, 0.05F, 0.7F));
        Images.pix.draw(oSB, nPosX, nPosY, nWidth - 1, (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2);
    }

    public final void drawStatisticsRowHoverBG(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
        oSB.setColor(new Color(Graph.GRAPH_LINES_DESC.r, Graph.GRAPH_LINES_DESC.g, Graph.GRAPH_LINES_DESC.b, 0.1F));
        ImageManager.getImage(Images.gradientHorizontal).draw(oSB, nPosX, nPosY, nWidth - 1, (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2);
    }

    public final void drawStatisticsBoxTitle(SpriteBatch oSB, String nText, int nPosX, int nPosY, int nWidth) {
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG.r, Colors.COLOR_GRADIENT_BG.g, Colors.COLOR_GRADIENT_BG.b, 0.7F));
        Images.pix.draw(oSB, nPosX, nPosY, nWidth, (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.6F));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX, nPosY + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) / 2, nWidth, ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) / 2, false, true);
        oSB.setColor(Graph.GRAPH_BORDERS_COLOR);
        Images.pix.draw(oSB, nPosX, nPosY + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2, nWidth, 1);
        oSB.setColor(Graph.GRAPH_LINES_COLOR);
        ImageManager.getImage(Images.line_32_vertical).draw2(oSB, nPosX - 1 + nWidth, nPosY, 1, (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2);
        Renderer.clipView_Start(oSB, nPosX, CFG.GAME_HEIGHT - nPosY, nWidth - CFG.PADDING, -((int)((float)CFG.TEXT_HEIGHT * 0.7F)) - CFG.PADDING * 2);
        Renderer.drawText(oSB, nText, nPosX + CFG.PADDING, nPosY + CFG.PADDING, Graph.TEXT_COLOR);
        Renderer.clipView_End(oSB);
    }

    public final void drawStatisticsValue(SpriteBatch oSB, String nText, int nPosX, int nPosY) {
        Renderer.drawText(oSB, nText, nPosX + CFG.PADDING, nPosY + CFG.PADDING, new Color(1.0F, 1.0F, 1.0F, 0.45F));
    }

    public final void drawStatisticsValueWithFlag(SpriteBatch oSB, String nText, int nCivID, int nPosX, int nPosY) {
        float tempFlagScale = (float)CFG.TEXT_HEIGHT * 0.7F / (float)CFG.CIV_FLAG_HEIGHT;
        oSB.setColor(Color.WHITE);
        Game.getCiv(nCivID).getFlag().draw(oSB, nPosX + CFG.PADDING, nPosY + CFG.PADDING, (int)Math.ceil((double)((float)CFG.CIV_FLAG_WIDTH * tempFlagScale)), (int)Math.ceil((double)((float)CFG.CIV_FLAG_HEIGHT * tempFlagScale)));
        Renderer.drawText(oSB, nText, nPosX + CFG.PADDING * 2 + (int)((float)CFG.CIV_FLAG_WIDTH * tempFlagScale), nPosY + CFG.PADDING, new Color(1.0F, 1.0F, 1.0F, 0.45F));
    }

    public final void drawStatisticsValue2(SpriteBatch oSB, String nText, int nPosX, int nPosY, int nWidth) {
        Renderer.clipView_Start(oSB, nPosX, CFG.GAME_HEIGHT - nPosY, nWidth - CFG.PADDING, -this.getHeight());
        Renderer.drawText(oSB, nText, nPosX + CFG.CIV_COLOR_WIDTH, nPosY + CFG.PADDING, Graph.DATA_COLOR);
        Renderer.clipView_End(oSB);
    }

    public final int getStatisticsWidth() {
        return (this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)) / (this.verticalInfo.getTextSize() + 3);
    }

    public final void updateInView() {
        if (this.statisticsMode) {
            for(int i = 0; i < this.iValuesSize; ++i) {
                if (this.getButtonsPosY(i) + this.iButtonsPosY >= (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 && this.getButtonsPosY(i) + this.iButtonsPosY <= this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).setInView(true);
                } else if (this.getButtonsPosY(i) + this.iButtonsPosY + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 >= 0 && this.getButtonsPosY(i) + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + this.iButtonsPosY <= this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).setInView(true);
                } else {
                    ((Graph_Vertical_Data)this.lValues.get(i)).setInView(false);
                }
            }
        } else {
            for(int i = 0; i < this.iValuesSize; ++i) {
                if (this.getButtonsPosX(i) + this.iButtonsPosX >= (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 && this.getButtonsPosX(i) + this.iButtonsPosX <= this.getWidth()) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).setInView(true);
                } else if (this.getButtonsPosX(i) - this.iDataWidth + this.iButtonsPosX >= (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 && this.getButtonsPosX(i) - this.iDataWidth + this.iButtonsPosX <= this.getWidth()) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).setInView(true);
                } else {
                    ((Graph_Vertical_Data)this.lValues.get(i)).setInView(false);
                }
            }
        }

    }

    public final int getButtonsPosX(int i) {
        return this.getWidth() - this.iDataWidth * i - CFG.PADDING - CFG.PADDING * 2 * i;
    }

    public final int getButtonsPosY(int i) {
        return (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)(CFG.PADDING * 2)) * i;
    }

    public final void updateMoveable() {
        if (this.statisticsMode) {
            if (this.getButtonsPosY(this.iValuesSize) > this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F + (float)(CFG.PADDING * 2)) * 2) {
                this.moveable = true;
            } else {
                this.moveable = false;
                this.iButtonsPosY = 0;
            }
        } else if (this.getButtonsWidth() > this.getWidth() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2)) {
            this.moveable = true;
        } else {
            this.moveable = false;
            this.iButtonsPosX = 0;
        }

    }

    public boolean getScrollable() {
        return this.moveable;
    }

    public void scrollByWheel(int nScoll) {
        this.scrollModeY = false;
        this.setCurrent(this.getCurrent() + nScoll);
    }

    public final int getButtonsWidth() {
        return this.iDataWidth * this.iValuesSize + CFG.PADDING * 2 * (this.iValuesSize - 1);
    }

    public final void roundAverage() {
        if (this.fAvaragePoint - (float)((int)this.fAvaragePoint) != 0.0F) {
            this.bDecimal = (byte)Math.round((this.fAvaragePoint - (float)((int)this.fAvaragePoint)) * 100.0F);
            this.fAvaragePoint -= this.fAvaragePoint - (float)((int)this.fAvaragePoint);
            this.lessThanTen = false;
            if (this.bDecimal % 10 == 0) {
                this.bDecimal = (byte)(this.bDecimal / 10);
            } else if (this.bDecimal < 10) {
                this.lessThanTen = true;
            }
        } else {
            this.bDecimal = 0;
        }

    }

    public boolean getMoveable() {
        return this.moveable;
    }

    public int getCurrent() {
        return this.statisticsMode ? this.iButtonsPosY : this.iButtonsPosX;
    }

    public void setCurrent(int nButtonsPosX) {
        if (this.statisticsMode) {
            if (nButtonsPosX > 0) {
                nButtonsPosX = 0;
                Game.menuManager.setUpdateSliderMenuPosY(true);
                this.scrollModeY = false;
            } else if (nButtonsPosX < -((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * this.iValuesSize + (this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2)) {
                nButtonsPosX = -((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * this.iValuesSize + (this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) * 2);
                Game.menuManager.setUpdateSliderMenuPosY(true);
                this.scrollModeY = false;
            }

            if (this.iButtonsPosY != nButtonsPosX) {
                this.iButtonsPosY = nButtonsPosX;
                this.updateInView();
            }
        } else {
            if (nButtonsPosX < 0) {
                nButtonsPosX = 0;
                Game.menuManager.setUpdateSliderMenuPosX(true);
                this.scrollModeY = false;
            } else if (nButtonsPosX > this.getButtonsWidth() - this.getWidth() + this.iDataWidth + CFG.PADDING - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2)) {
                nButtonsPosX = this.getButtonsWidth() - this.getWidth() + this.iDataWidth + CFG.PADDING - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
                Game.menuManager.setUpdateSliderMenuPosX(true);
                this.scrollModeY = false;
            }

            if (this.iButtonsPosX != nButtonsPosX) {
                this.iButtonsPosX = nButtonsPosX;
                this.updateInView();
            }
        }

    }

    public final void scrollTheMenu() {
        if (this.moveable && this.iScrollPosX > 0 && this.iScrollPosX2 > 0 && (float)Math.abs(this.iScrollPosX - this.iScrollPosX2) > 3.0F * CFG.DENSITY) {
            this.fScrollNewMenuPosY = (float)(this.iScrollPosX - this.iScrollPosX2) * 1.25F;
            this.scrollModeY = true;
        }

    }

    public final void setScrollPosY(int iScrollPosX) {
        this.iScrollPosX2 = this.iScrollPosX;
        this.iScrollPosX = iScrollPosX;
    }

    public void stopScrolling() {
        this.iScrollPosX = this.iScrollPosX2 = -1;
        this.scrollModeY = false;
    }

    public boolean getInStatisticsMode() {
        return this.statisticsMode;
    }

    public void setInStatisticsMode(boolean inStatisticsMode) {
        if (this.allowStatisticsMode) {
            this.statisticsMode = inStatisticsMode;
            this.scrollModeY = false;
            this.iButtonsPosX = this.iButtonsPosY = 0;
            if (!this.statisticsMode) {
                for(int i = 0; i < this.iValuesSize; ++i) {
                    ((Graph_Vertical_Data)this.lValues.get(i)).resetAnimation();
                }
            }

            this.updateMoveable();
            this.updateInView();
            this.setHoveredID(-1);
        }

    }

    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible);
        this.setHoveredID(-1);
    }

    interface DrawStatisticsData {
        void draw(SpriteBatch var1, int var2, int var3, int var4, int var5);

        String getTotal();

        String getStatsLP(int var1);

        int getStatsLPCivFlagID(int var1);
    }
}
