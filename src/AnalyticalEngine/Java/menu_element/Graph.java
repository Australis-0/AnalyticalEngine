//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.graph;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.MenuElement_Type;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Graph extends MenuElement {
    public static final Color GRAPH_BG_COLOR = new Color(0.17254902F, 0.14901961F, 0.13333334F, 1.0F);
    public static final Color GRAPH_BORDERS_COLOR = new Color(0.078431375F, 0.11764706F, 0.17254902F, 1.0F);
    public static final Color GRAPH_LINES_COLOR = new Color(0.9F, 0.9F, 0.9F, 0.1F);
    public static final Color GRAPH_LINES_DESC = new Color(0.9F, 0.9F, 0.9F, 0.15F);
    public static final Color GRAPH_LINE_COLOR = new Color(0.8235294F, 0.8235294F, 0.8235294F, 1.0F);
    public static final Color TEXT_COLOR = new Color(0.9F, 0.9F, 0.9F, 1.0F);
    public static final Color DATA_COLOR = new Color(0.6862745F, 0.6862745F, 0.6862745F, 1.0F);
    public static float POINTS_TEXT_SCALE = 0.8F;
    public List<GraphData> lData;
    public int iDataSize;
    public List<Integer> lSortedData;
    public List<Integer> lPointsPosX;
    public int iPointsPosXSize;
    public int iMaxSize = 0;
    public int iFixPosY;
    public int iHoveredID = -1;
    public static final int FONT_ID = 1;
    public int iZeroPosY;
    public int iMinPoint;
    public int iMinTextWidth;
    public int iWorstCivID;
    public int iMaxPoint;
    public int iMaxPoint_Text;
    public int iMaxTextWidth;
    public int iBestCivID;
    public float fAvaragePoint;
    public int iAvaragePosY;
    public byte bDecimal = 0;
    public boolean lessThanTen = false;
    public boolean split100 = false;
    public int iDescOfTurnID = 0;
    public int iWorstDescDataID;
    public int iWorstDescDataTextWidth;
    public int iBestDescDataID;
    public int iBestDescDataTextWidth;
    public String sTextX;
    public String sTextX2;
    public String sTextY;
    public int iWidthTextX;
    public int iWidthTextX2;
    public int iWidthTextY;
    public static final int ANIMATION_TIME = 250;
    public long lTime = 0L;
    public static final int AUTO_MOVE_TURN_TIME = 1450;
    public long lAuto_Move_Turn_Time = 0L;
    public boolean moveable = false;
    public int iButtonsPosY = 0;
    public int iActiveButtonID = -1;
    public GraphType graphType;

    protected static final int getGraphButtonWidth() {
        return 0;
    }

    protected static final int getGraphButtonHeight() {
        return CFG.BUTTON_HEIGHT / 2;
    }

    public Graph(String sTextX, String sTextY, int iPosX, int iPosY, int iWidth, int iHeight, boolean visible, int nLoadSize, GraphType graphType, boolean split100) {
        this.sTextX = sTextX;
        this.sTextY = sTextY;
        this.graphType = graphType;
        this.split100 = split100;
        List<Integer> nCivs = new ArrayList();
        if (graphType == Graph.GraphType.PLAYER_INCOME) {
            nCivs.add(Game.player.iCivID);
        } else {
            nCivs.add(Game.player.iCivID);
        }

        ((BitmapFont)Renderer.fontMain.get(1)).getData().setScale(0.7F);
        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(1), sTextX);
        this.iWidthTextX = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(1), sTextY);
        this.iWidthTextY = (int)Renderer.glyphLayout.width;
        ((BitmapFont)Renderer.fontMain.get(1)).getData().setScale(1.0F);
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.setVisible(visible);
        this.lData = new ArrayList();
        this.lSortedData = new ArrayList();
        this.lPointsPosX = new ArrayList();
        this.iFixPosY = 0;
        this.typeOfElement = MenuElement_Type.GRAPH;

        for(int i = 0; i < nCivs.size(); ++i) {
            this.addData(new GraphData((Integer)nCivs.get(i), new ArrayList(), 0));
        }

        for(int i = 0; i < nLoadSize && i < this.lData.size(); ++i) {
            this.loadData(i);
        }

        this.iDataSize = this.lData.size();
    }

    public void loadData(int i) {
        int nStartTurnID = 0;
        List<Integer> tempPoints = new ArrayList();
        if (this.graphType == Graph.GraphType.PLAYER_INCOME) {
            for(int j = 0; j < Game.player.playerStats2.income.size(); ++j) {
                tempPoints.add((Integer)Game.player.playerStats2.income.get(j));
            }
        } else if (this.graphType == Graph.GraphType.PLAYER_BALANCE) {
            for(int j = 0; j < Game.player.playerStats2.balance.size(); ++j) {
                tempPoints.add((Integer)Game.player.playerStats2.balance.get(j));
            }
        } else if (this.graphType == Graph.GraphType.PLAYER_PRESTIGE) {
            for(int j = 0; j < Game.player.playerStats3.prestige.size(); ++j) {
                tempPoints.add((Integer)Game.player.playerStats3.prestige.get(j));
            }
        } else if (this.graphType == Graph.GraphType.PLAYER_POPULATION) {
            for(int j = 0; j < Game.player.playerStats3.population.size(); ++j) {
                tempPoints.add((Integer)Game.player.playerStats3.population.get(j));
            }
        } else {
            for(int a = 0; a < 5; ++a) {
                tempPoints.add(100 + Game.oR.nextInt(1 + Game.oR.nextInt(1 + Game.oR.nextInt(100))));
            }
        }

        if (tempPoints.size() > 0) {
            this.lData.set(i, new GraphData(((GraphData)this.lData.get(i)).getCivID(), tempPoints, nStartTurnID));
            ((GraphData)this.lData.get(i)).setDrawData(true);
            this.updateMoveable();
            this.buildGraph();
        }

    }

    public void updateHover(int nPosX, int nPosY, int menuPosX, int menuPosY) {
        for(int i = 0; i < this.iDataSize; ++i) {
            if (this.getPosX() + this.getWidth() - getGraphButtonWidth() + menuPosX <= nPosX && this.getPosX() + this.getWidth() + menuPosX >= nPosX && this.getButtonsPosY(i) + this.iButtonsPosY + menuPosY <= nPosY && this.getButtonsPosY(i) + getGraphButtonHeight() + this.iButtonsPosY + menuPosY >= nPosY) {
                this.setHoveredID((Integer)this.lSortedData.get(i));
                return;
            }
        }

        this.setHoveredID(-1);
    }

    public final void setHoveredID(int nHoveredID) {
        if (this.iHoveredID != nHoveredID) {
            this.iHoveredID = nHoveredID;
            this.buildElementHover();
        }

    }

    public void buildElementHover() {
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if (this.lAuto_Move_Turn_Time + 1450L < CFG.currentTimeMillis) {
            this.incrementTurnDescInfo();
        }
        //System.out.println("DRAW CALLED");

        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.06666667F, 0.07450981F, 0.09019608F, 1.0F));
        Renderer.drawBox2(oSB, -2 + this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, -2 + this.getPosY() + iTranslateY, 4 + this.getGraphWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, 4 + this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, 1.0F);
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.75F));
        ImageManager.getImage(Images.graphBG).draw2(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getGraphWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.8F));
        ImageManager.getImage(Images.gradientVertical).draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getGraphWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getGraphWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
        ((BitmapFont)Renderer.fontMain.get(1)).getData().setScale(0.8F);
        Renderer.drawTextWithShadowRotated(oSB, 1, this.sTextY, this.getPosX() - 2 + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() / 2 + this.iWidthTextY / 2 + iTranslateY, Colors.HOVER_GOLD, 90.0F);
        Renderer.drawTextWithShadow(oSB, 1, this.sTextX, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.8F) + CFG.PADDING * 2 + CFG.PADDING * 2 + iTranslateX, this.getPosY() + 2 + this.getHeight() - CFG.PADDING - (int)((float)CFG.TEXT_HEIGHT * 0.8F) + iTranslateY, DATA_COLOR);
        ((BitmapFont)Renderer.fontMain.get(1)).getData().setScale(1.0F);
        oSB.setColor(GRAPH_LINES_DESC);
        ImageManager.getImage(Images.line_33).draw2(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() - this.iFixPosY + this.iAvaragePosY + iTranslateY, this.getGraphWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
        if (this.getMinPoint() < 0 && this.iMaxPoint > 0) {
            oSB.setColor(GRAPH_LINES_COLOR);
            Images.pix.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX, this.getPosY() - this.iFixPosY + this.iZeroPosY + iTranslateY, this.getGraphWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 - 1);
            oSB.setColor(GRAPH_BORDERS_COLOR);
            Images.pix.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + iTranslateX, this.getPosY() - 1 - this.iFixPosY + this.iZeroPosY + iTranslateY, CFG.PADDING - 1);
            ((BitmapFont)Renderer.fontMain.get(1)).getData().setScale(POINTS_TEXT_SCALE);
            Renderer.drawTextWithShadow(oSB, 1, "0", this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateX, this.getPosY() - (int)(2.0F * CFG.GUI_SCALE) - (int)((float)CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) - this.iFixPosY + this.iZeroPosY - 1 + iTranslateY, DATA_COLOR);
            ((BitmapFont)Renderer.fontMain.get(1)).getData().setScale(1.0F);
        }

        if (this.lTime + 250L > CFG.currentTimeMillis) {
            Renderer.clipView_Start(oSB, this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, (int)((float)this.getGraphWidth() * ((float)(CFG.currentTimeMillis - this.lTime) / 250.0F)), -this.getHeight());
            this.drawGraphData(oSB, iTranslateX, iTranslateY);
            Renderer.clipView_End(oSB);
        } else {
            this.drawGraphData(oSB, iTranslateX, iTranslateY);
        }

        oSB.setColor(GRAPH_BORDERS_COLOR);
        Images.pix.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING - 1);
        Images.pix.draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() - this.iFixPosY + this.iAvaragePosY + iTranslateY, CFG.PADDING - 1);
        Images.pix.draw(oSB, this.getPosX() + this.getGraphWidth() - 1 + iTranslateX, this.getPosY() + this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - CFG.PADDING + 1 + iTranslateY, 1, CFG.PADDING - 1);
        ((BitmapFont)Renderer.fontMain.get(1)).getData().setScale(POINTS_TEXT_SCALE);
        if (this.split100) {
            Renderer.drawTextWithShadow(oSB, 1, "" + CFG.getPrecision2((float)this.getMinPoint() / 100.0F, 10), this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateX, this.getPosY() + this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - (int)(2.0F * CFG.GUI_SCALE) - (int)((float)CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) + iTranslateY, DATA_COLOR);
            Renderer.drawTextWithShadow(oSB, 1, "" + CFG.getPrecision2((float)this.iMaxPoint_Text / 100.0F, 10), this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateX, this.getPosY() + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateY, DATA_COLOR);
        } else {
            Renderer.drawTextWithShadow(oSB, 1, "" + this.getMinPoint(), this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateX, this.getPosY() + this.getHeight() - ((int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2) - (int)(2.0F * CFG.GUI_SCALE) - (int)((float)CFG.TEXT_HEIGHT * POINTS_TEXT_SCALE) + iTranslateY, DATA_COLOR);
            Renderer.drawTextWithShadow(oSB, 1, "" + this.iMaxPoint_Text, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateX, this.getPosY() + 1 + (int)(2.0F * CFG.GUI_SCALE) + iTranslateY, DATA_COLOR);
        }

        oSB.setColor(Color.WHITE);
        ((BitmapFont)Renderer.fontMain.get(1)).getData().setScale(1.0F);
        oSB.setColor(Color.WHITE);
        oSB.setColor(GRAPH_BORDERS_COLOR);
        Images.pix.draw(oSB, this.getPosX() - 1 + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
        Images.pix.draw(oSB, this.getPosX() - 1 + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + iTranslateY, this.getGraphWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + 1, 1);
        Images.pix.draw(oSB, this.getPosX() + this.getGraphWidth() - CFG.PADDING + iTranslateX, this.getPosY() + iTranslateY, CFG.PADDING);
        Images.pix.draw(oSB, this.getPosX() - 1 + this.getGraphWidth() + iTranslateX, this.getPosY() + iTranslateY, 1, CFG.PADDING - 1);
        Renderer.clipView_Start(oSB, this.getPosX() + iTranslateX, CFG.GAME_HEIGHT - this.getPosY() - iTranslateY, this.getWidth(), -(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2 + 2));
        Renderer.clipView_End(oSB);
        oSB.setColor(Color.WHITE);
    }

    public final void drawGraphData(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
        int i = 0;
        //System.out.println("DRAWGRAPHDATA 1 CALLED");

        for(int tempFixPosY = this.getMinPoint() > 0 ? this.iFixPosY : this.iFixPosY; i < this.iDataSize; ++i) {
            //System.out.println("GraphData[" + i + "].getDrawData(): " + ((GraphData)this.lData.get(i)).getDrawData());

            if (((GraphData)this.lData.get(i)).getDrawData()) {
                ((GraphData)this.lData.get(i)).draw(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getGraphWidth(), this.getHeight(), this.lPointsPosX, i, this.iActiveButtonID >= 0 ? (Integer)this.lSortedData.get(this.iActiveButtonID) == i : (this.iHoveredID >= 0 ? (Integer)this.lSortedData.get(this.iHoveredID) == i : false), tempFixPosY);
            } else if (((GraphData)this.lData.get(i)).getBackAnimation()) {
                if (((GraphData)this.lData.get(i)).getTime() + 450L <= CFG.currentTimeMillis) {
                    ((GraphData)this.lData.get(i)).setBackAnimation(false);
                } else {
                    ((GraphData)this.lData.get(i)).drawAnimation(oSB, this.getPosX() + (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING * 2 + iTranslateX, this.getPosY() + iTranslateY, this.getGraphWidth(), this.getHeight(), this.lPointsPosX, i, this.iActiveButtonID == (Integer)this.lSortedData.get(i) || this.iHoveredID == (Integer)this.lSortedData.get(i), tempFixPosY);
                }
            }
        }

    }

    public final void setData(List<GraphData> nData) {
        this.lData.clear();

        for(int i = 0; i < nData.size(); ++i) {
            this.lData.add((GraphData)nData.get(i));
        }

        this.iDataSize = this.lData.size();
        this.buildGraph();
    }

    public final void addData(GraphData nData) {
        for(int i = 0; i < this.iDataSize; ++i) {
            if (((GraphData)this.lData.get(i)).getCivID() == nData.getCivID()) {
                return;
            }
        }

        this.lData.add(nData);
        this.iDataSize = this.lData.size();
        this.updateMoveable();
        this.buildGraph();
        this.sortCivsByLastPoint();
    }

    public final void removeData(int iCivID) {
        if (this.iDataSize > 1) {
            for(int i = 0; i < this.iDataSize; ++i) {
                if (((GraphData)this.lData.get(i)).getCivID() == iCivID) {
                    this.lData.remove(i);
                    this.iDataSize = this.lData.size();
                    this.updateMoveable();
                    this.buildGraph();
                    this.updateButtonsInView();
                    return;
                }
            }
        }

        this.sortCivsByLastPoint();
    }

    public void setMin(int nCivID) {
        for(int i = 0; i < this.lData.size(); ++i) {
            if (((GraphData)this.lData.get(i)).getCivID() == nCivID) {
                ((GraphData)this.lData.get(i)).setDrawData(!((GraphData)this.lData.get(i)).getDrawData());
                if (((GraphData)this.lData.get(i)).getDrawData()) {
                    this.loadData(i);
                }
                break;
            }
        }

    }

    public final void sortCivsByLastPoint() {
        this.lSortedData.clear();

        for(int i = 0; i < this.iDataSize; ++i) {
            this.lSortedData.add(i);
        }

    }

    public final int getDataLastPoint(int id) {
        try {
            return ((GraphData)this.lData.get(id)).getPointY(this.iPointsPosXSize - 1 - ((GraphData)this.lData.get(id)).getBeginTurnID());
        } catch (IndexOutOfBoundsException var3) {
            return 0;
        }
    }

    public void updateSlider(int nPosX) {
        this.updateMoveTurnTime();
    }

    public final void updateDescInfo() {
        int tempBestResult = this.getMinPoint();
        int tempWorstResult = this.iMaxPoint;

        for(int i = 0; i < this.iDataSize; ++i) {
            if (((GraphData)this.lData.get(i)).getDrawData() && this.iDescOfTurnID >= ((GraphData)this.lData.get(i)).getBeginTurnID() && this.iDescOfTurnID < ((GraphData)this.lData.get(i)).getBeginTurnID() + ((GraphData)this.lData.get(i)).getPointsSize()) {
                if (((GraphData)this.lData.get(i)).getPointY(this.iDescOfTurnID - ((GraphData)this.lData.get(i)).getBeginTurnID()) > tempBestResult) {
                    tempBestResult = ((GraphData)this.lData.get(i)).getPointY(this.iDescOfTurnID - ((GraphData)this.lData.get(i)).getBeginTurnID());
                    this.iBestDescDataID = i;
                }

                if (((GraphData)this.lData.get(i)).getPointY(this.iDescOfTurnID - ((GraphData)this.lData.get(i)).getBeginTurnID()) <= tempWorstResult) {
                    tempWorstResult = ((GraphData)this.lData.get(i)).getPointY(this.iDescOfTurnID - ((GraphData)this.lData.get(i)).getBeginTurnID());
                    this.iWorstDescDataID = i;
                }
            }
        }

        ((BitmapFont)Renderer.fontMain.get(1)).getData().setScale(POINTS_TEXT_SCALE);
        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(1), "" + ((GraphData)this.lData.get(this.iWorstDescDataID)).getPointY(this.iDescOfTurnID - ((GraphData)this.lData.get(this.iWorstDescDataID)).getBeginTurnID()));
        this.iWorstDescDataTextWidth = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(1), "" + ((GraphData)this.lData.get(this.iBestDescDataID)).getPointY(this.iDescOfTurnID - ((GraphData)this.lData.get(this.iBestDescDataID)).getBeginTurnID()));
        this.iBestDescDataTextWidth = (int)Renderer.glyphLayout.width;
        ((BitmapFont)Renderer.fontMain.get(1)).getData().setScale(1.0F);
        int tempRealTurnID = 1;
        if (this.iPointsPosXSize < Game_Calendar.TURN_ID) {
            tempRealTurnID = Game_Calendar.TURN_ID - this.iPointsPosXSize - 1 + this.iDescOfTurnID + 1;
        } else {
            tempRealTurnID = this.iDescOfTurnID + 1;
        }

        this.sTextX = Game_Calendar.getDate_ByTurnID(0);
        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(1), this.sTextX);
        this.iWidthTextX = (int)Renderer.glyphLayout.width;
        this.updateMoveTurnTime();
    }

    public final void buildGraph() {
        this.iMinPoint = this.iMaxPoint = ((GraphData)this.lData.get(0)).getPointY(0);
        this.fAvaragePoint = 0.0F;
        this.iBestCivID = this.iWorstCivID = ((GraphData)this.lData.get(0)).getCivID();
        int tempAvarageSize = 0;
        this.iMaxSize = 0;

        for(int i = 0; i < this.iDataSize; ++i) {
            if (((GraphData)this.lData.get(i)).getDrawData()) {
                float tempAverage = 0.0F;

                for(int j = 0; j < ((GraphData)this.lData.get(i)).getPointsSize(); ++j) {
                    if (((GraphData)this.lData.get(i)).getPointY(j) > this.iMaxPoint) {
                        this.iMaxPoint = ((GraphData)this.lData.get(i)).getPointY(j);
                        this.iBestCivID = ((GraphData)this.lData.get(i)).getCivID();
                    }

                    if (((GraphData)this.lData.get(i)).getPointY(j) <= this.iMinPoint) {
                        this.iMinPoint = ((GraphData)this.lData.get(i)).getPointY(j);
                        this.iWorstCivID = ((GraphData)this.lData.get(i)).getCivID();
                    }

                    tempAverage += (float)((GraphData)this.lData.get(i)).getPointY(j);
                }

                this.fAvaragePoint += tempAverage / (float)((GraphData)this.lData.get(i)).getPointsSize();
                ++tempAvarageSize;
                if (this.iMaxSize < ((GraphData)this.lData.get(i)).getPointsSize() + ((GraphData)this.lData.get(i)).getBeginTurnID()) {
                    this.iMaxSize = ((GraphData)this.lData.get(i)).getPointsSize() + ((GraphData)this.lData.get(i)).getBeginTurnID();
                }
            } else {
                for(int j = 0; j < ((GraphData)this.lData.get(i)).getPointsSize(); ++j) {
                    if (((GraphData)this.lData.get(i)).getPointY(j) > this.iMaxPoint) {
                        this.iMaxPoint = ((GraphData)this.lData.get(i)).getPointY(j);
                        this.iBestCivID = ((GraphData)this.lData.get(i)).getCivID();
                    }

                    if (((GraphData)this.lData.get(i)).getPointY(j) <= this.iMinPoint) {
                        this.iMinPoint = ((GraphData)this.lData.get(i)).getPointY(j);
                        this.iWorstCivID = ((GraphData)this.lData.get(i)).getCivID();
                    }
                }

                if (this.iMaxSize < ((GraphData)this.lData.get(i)).getPointsSize() + ((GraphData)this.lData.get(i)).getBeginTurnID()) {
                    this.iMaxSize = ((GraphData)this.lData.get(i)).getPointsSize() + ((GraphData)this.lData.get(i)).getBeginTurnID();
                }
            }
        }

        this.iMaxPoint_Text = this.iMaxPoint;
        this.iMaxPoint = (int)((float)this.iMaxPoint + (float)this.iMaxPoint * 0.05F);
        this.fAvaragePoint /= (float)tempAvarageSize;

        try {
            if (this.iMinPoint < 0) {
                this.iFixPosY = -((int)((float)(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) * 100.0F * (float)this.getMinPoint() / (float)(this.iMaxPoint - this.getMinPoint()) / 100.0F));
                this.iZeroPosY = (int)((float)(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) - (float)(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) * 0.0F / (float)(this.iMaxPoint - this.getMinPoint()) / 100.0F);
            } else if (this.iMinPoint > 0) {
                this.iFixPosY = (int)((float)(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) - (float)(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) * 100.0F * (float)this.getMinPoint() / (float)(this.iMaxPoint - this.getMinPoint()) / 100.0F - (float)(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2));
            } else {
                this.iFixPosY = 0;
            }
        } catch (ArithmeticException var5) {
            this.iFixPosY = 0;
        }

        this.iAvaragePosY = (int)((float)(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) - (float)(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) * 100.0F * this.fAvaragePoint / (float)(this.iMaxPoint - this.getMinPoint()) / 100.0F);
        this.roundAverage();
        this.lPointsPosX.clear();
        this.lPointsPosX.add(0);

        for(int i = 1; i < this.iMaxSize - 1; ++i) {
            this.lPointsPosX.add((int)((float)(this.getGraphWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2) * 100.0F * (float)i / (float)(this.iMaxSize - 1) / 100.0F));
        }

        this.lPointsPosX.add(this.getGraphWidth() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2);
        this.iPointsPosXSize = this.lPointsPosX.size();

        for(int i = 0; i < this.iDataSize; ++i) {
            ((GraphData)this.lData.get(i)).buildGraph(this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2, this.getMinPoint(), this.iMaxPoint, this.lPointsPosX);
        }

        ((BitmapFont)Renderer.fontMain.get(1)).getData().setScale(POINTS_TEXT_SCALE);
        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(1), "" + this.iMinPoint);
        this.iMinTextWidth = (int)Renderer.glyphLayout.width;
        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(1), "" + this.iMaxPoint_Text);
        this.iMaxTextWidth = (int)Renderer.glyphLayout.width;
        ((BitmapFont)Renderer.fontMain.get(1)).getData().setScale(1.0F);
        this.updateDescInfo();
    }

    public final void updateButtonsInView() {
        for(int i = 0; i < this.iDataSize; ++i) {
            if (this.getButtonsPosY(i) + this.iButtonsPosY >= 0 && this.getButtonsPosY(i) + this.iButtonsPosY <= this.getHeight()) {
                ((GraphData)this.lData.get((Integer)this.lSortedData.get(i))).setVisible(true);
            } else if (this.getButtonsPosY(i) + getGraphButtonHeight() + this.iButtonsPosY >= 0 && this.getButtonsPosY(i) + getGraphButtonHeight() + this.iButtonsPosY <= this.getHeight()) {
                ((GraphData)this.lData.get((Integer)this.lSortedData.get(i))).setVisible(true);
            } else {
                ((GraphData)this.lData.get((Integer)this.lSortedData.get(i))).setVisible(false);
            }
        }

    }

    public final void updateMoveable() {
        if (this.getButtonsHeight() > this.getHeight()) {
            this.moveable = true;
        } else {
            this.moveable = false;
            this.iButtonsPosY = 0;
        }

    }

    public final void setScrollPosY(int nPosY) {
        nPosY -= this.getPosY();

        for(int i = 0; i < this.iDataSize; ++i) {
            if (this.getButtonsPosY(i) + this.iButtonsPosY <= nPosY && this.getButtonsPosY(i) + getGraphButtonHeight() + this.iButtonsPosY >= nPosY) {
                this.iActiveButtonID = i;
                Gdx.app.log("AoC", "" + this.iActiveButtonID);
                break;
            }
        }

    }

    public final void actionUp(int nPosY) {
        nPosY -= this.getPosY();
        if (this.iActiveButtonID >= 0 && this.getButtonsPosY(this.iActiveButtonID) + this.iButtonsPosY <= nPosY && this.getButtonsPosY(this.iActiveButtonID) + getGraphButtonHeight() + this.iButtonsPosY >= nPosY) {
            if (!((GraphData)this.lData.get((Integer)this.lSortedData.get(this.iActiveButtonID))).getDrawData()) {
                ((GraphData)this.lData.get((Integer)this.lSortedData.get(this.iActiveButtonID))).setDrawData(!((GraphData)this.lData.get((Integer)this.lSortedData.get(this.iActiveButtonID))).getDrawData());
                if (((GraphData)this.lData.get((Integer)this.lSortedData.get(this.iActiveButtonID))).getDrawData()) {
                    this.loadData((Integer)this.lSortedData.get(this.iActiveButtonID));
                }

                this.buildGraph();
            } else {
                int numOfActiveDatas = 0;

                for(int j = 0; j < this.iDataSize; ++j) {
                    if (((GraphData)this.lData.get(j)).getDrawData()) {
                        ++numOfActiveDatas;
                    }
                }

                if (numOfActiveDatas > 1) {
                    ((GraphData)this.lData.get((Integer)this.lSortedData.get(this.iActiveButtonID))).setDrawData(!((GraphData)this.lData.get((Integer)this.lSortedData.get(this.iActiveButtonID))).getDrawData());
                    if (((GraphData)this.lData.get((Integer)this.lSortedData.get(this.iActiveButtonID))).getDrawData()) {
                        this.loadData((Integer)this.lSortedData.get(this.iActiveButtonID));
                    }

                    this.buildGraph();
                }
            }
        }

        this.iActiveButtonID = -1;
    }

    public int getCurrent() {
        return this.iButtonsPosY;
    }

    public void setCurrent(int nButtonsPosY) {
        if (nButtonsPosY >= 0) {
            nButtonsPosY = 0;
        } else if (nButtonsPosY <= -(this.getButtonsHeight() - (this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2))) {
            nButtonsPosY = -(this.getButtonsHeight() - (this.getHeight() - (int)((float)CFG.TEXT_HEIGHT * 0.7F) - CFG.PADDING * 2));
        }

        if (this.iButtonsPosY != nButtonsPosY) {
            this.iButtonsPosY = nButtonsPosY;
            this.updateButtonsInView();
        }

    }

    public boolean getMoveable() {
        return this.moveable;
    }

    public final int getButtonsPosY(int i) {
        return getGraphButtonHeight() * i + CFG.PADDING * i;
    }

    public final int getButtonsHeight() {
        return getGraphButtonHeight() * this.iDataSize + CFG.PADDING * (this.iDataSize - 1);
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

    public void setVisible(boolean isVisible) {
        if (isVisible) {
            if (this.iDescOfTurnID != 0) {
                this.updateSlider(0);
            }

            this.lTime = CFG.currentTimeMillis - 1L;
            this.updateMoveTurnTime();
        } else {
            this.lTime = 0L;
            this.iButtonsPosY = 0;
        }

        super.setVisible(isVisible);
        this.setHoveredID(-1);
    }

    public final int getGraphWidth() {
        return this.getWidth() - getGraphButtonWidth() - CFG.PADDING;
    }

    public final int getMinPoint() {
        return this.iMinPoint > 0 ? 0 : this.iMinPoint;
    }

    public final void updateMoveTurnTime() {
        this.lAuto_Move_Turn_Time = CFG.currentTimeMillis;
    }

    public final void incrementTurnDescInfo() {
        ++this.iDescOfTurnID;
        if (this.iDescOfTurnID >= this.iMaxSize) {
            this.iDescOfTurnID = 0;
        }

        this.updateDescInfo();
    }

    public void setCheckboxState(boolean checkboxState) {
        this.buildGraph();
        this.updateMoveable();
        this.updateButtonsInView();
    }

    public static enum GraphType {
        PLAYER_INCOME,
        PLAYER_BALANCE,
        PLAYER_POPULATION,
        PLAYER_PRESTIGE;

        private GraphType() {
        }
    }
}
