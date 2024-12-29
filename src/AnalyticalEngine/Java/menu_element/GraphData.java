//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.graph;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import java.util.List;
import space.earlygrey.shapedrawer.JoinType;

public class GraphData {
    public static final float ALPHA_CIV_LINE = 0.8F;
    public int iCivID;
    public List<Integer> lPointsY;
    public int iPointsSize = 0;
    public Array<Vector2> lVectorPoints;
    public int iBeginTurnID;
    public boolean drawData = true;
    public boolean visible = true;
    public boolean backAnimation = false;
    protected static final int ANIMATION_TIME = 450;
    public long lTime = 0L;

    public GraphData(int iCivID, List<Integer> nPointsY, int iBeginTurnID) {
        this.iCivID = iCivID;
        this.iPointsSize = nPointsY.size();
        this.lPointsY = new ArrayList();
        this.lVectorPoints = new Array();

        for(int i = 0; i < this.iPointsSize; ++i) {
            this.lPointsY.add((Integer)nPointsY.get(i));
        }

        this.iBeginTurnID = iBeginTurnID;
        this.drawData = true;
    }

    public final void draw(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Integer> nPointsPosX, int id, boolean active, int iFixPosY) {
        if (this.lTime + 450L >= CFG.currentTimeMillis) {
            this.drawAnimation(oSB, iPosX, iPosY, iWidth, iHeight, nPointsPosX, id, active, iFixPosY);
        } else {
            this.drawGraphData(oSB, iPosX, iPosY - iFixPosY, nPointsPosX, id, active);
        }

    }

    public final void drawAnimation(SpriteBatch oSB, int iPosX, int iPosY, int iWidth, int iHeight, List<Integer> nPointsPosX, int id, boolean active, int iFixPosY) {
        if (this.backAnimation) {
            Renderer.clipView_Start(oSB, iPosX, CFG.GAME_HEIGHT - iPosY, iWidth - (int)((float)iWidth * ((float)(CFG.currentTimeMillis - this.lTime) / 450.0F)), -iHeight);
        } else {
            Renderer.clipView_Start(oSB, iPosX, CFG.GAME_HEIGHT - iPosY, (int)((float)iWidth * ((float)(CFG.currentTimeMillis - this.lTime) / 450.0F)), -iHeight);
        }

        this.drawGraphData(oSB, iPosX, iPosY - iFixPosY, nPointsPosX, id, true);
        Renderer.clipView_End(oSB);
    }

    public final void drawGraphData(SpriteBatch oSB, int iPosX, int iPosY, List<Integer> nPointsPosX, int id, boolean active) {
        try {
            oSB.setColor(new Color(Game.getCiv(this.iCivID).getR(), Game.getCiv(this.iCivID).getG(), Game.getCiv(this.iCivID).getB(), active ? 1.0F : 0.8F));
        } catch (IndexOutOfBoundsException var10) {
            oSB.setColor(new Color(0.05882353F, 0.05882353F, 0.05882353F, active ? 1.0F : 0.8F));
        }

        if (this.lVectorPoints.size > 1) {
            Array<Vector2> nPath = new Array();
            nPath.add(new Vector2((float)iPosX + ((Vector2)this.lVectorPoints.get(0)).x, (float)(-iPosY) + -((Vector2)this.lVectorPoints.get(0)).y));
            int i = 1;

            for(int iSize = this.lVectorPoints.size; i < iSize; ++i) {
                if (((Vector2)this.lVectorPoints.get(i)).x != ((Vector2)this.lVectorPoints.get(i - 1)).x) {
                    nPath.add(new Vector2((float)iPosX + ((Vector2)this.lVectorPoints.get(i)).x, (float)(-iPosY) + -((Vector2)this.lVectorPoints.get(i)).y));
                }
            }

            Renderer.shapeDrawer.setColor(Graph.GRAPH_LINE_COLOR);
            Renderer.shapeDrawer.path(nPath, 1.0F, JoinType.SMOOTH, true);
            Renderer.oSBBorder.end();
            Renderer.oSBBorder.begin();
        }
    }

    public final void drawCivButton(SpriteBatch oSB, int iPosX, int iPosY, boolean active) {
        oSB.setColor(new Color(Graph.GRAPH_BG_COLOR.r, Graph.GRAPH_BG_COLOR.g, Graph.GRAPH_BG_COLOR.b, active ? Graph.GRAPH_BG_COLOR.a * 2.0F : (this.drawData ? Graph.GRAPH_BG_COLOR.a : Graph.GRAPH_BG_COLOR.a / 4.0F)));
        Images.pix.draw(oSB, iPosX, iPosY, Graph.getGraphButtonWidth(), Graph.getGraphButtonHeight());
        oSB.setColor(new Color(Graph.GRAPH_BORDERS_COLOR.r, Graph.GRAPH_BORDERS_COLOR.g, Graph.GRAPH_BORDERS_COLOR.b, this.drawData ? Graph.GRAPH_BORDERS_COLOR.a : 0.25F));

        try {
            oSB.setColor(new Color(Game.getCiv(this.iCivID).getR(), Game.getCiv(this.iCivID).getG(), Game.getCiv(this.iCivID).getB(), this.drawData ? 0.8F : 0.4F));
        } catch (IndexOutOfBoundsException var7) {
            oSB.setColor(new Color(0.05882353F, 0.05882353F, 0.05882353F, this.drawData ? 0.8F : 0.4F));
        }

        Images.pix.draw(oSB, iPosX, iPosY, CFG.CIV_COLOR_WIDTH, Graph.getGraphButtonHeight());
        oSB.setColor(this.drawData ? Color.WHITE : new Color(1.0F, 1.0F, 1.0F, 0.25F));

        try {
            Game.getCiv(this.iCivID).getFlag().draw(oSB, iPosX + Graph.getGraphButtonWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2, iPosY + Graph.getGraphButtonHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        } catch (IndexOutOfBoundsException var6) {
            ImageManager.getImage(Images.randomCivilizationFlag).draw(oSB, iPosX + Graph.getGraphButtonWidth() / 2 - CFG.CIV_FLAG_WIDTH / 2, iPosY + Graph.getGraphButtonHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2 - ImageManager.getImage(Images.randomCivilizationFlag).getHeight(), CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
        }

        oSB.setColor(Color.WHITE);
    }

    public final void buildGraph(int iHeight, int nMinPoint, int nMaxPoint, List<Integer> nPointsPosX) {
        this.lVectorPoints.clear();

        for(int i = 0; i < this.iPointsSize; ++i) {
            this.lVectorPoints.add(new Vector2((float)(Integer)nPointsPosX.get(this.iBeginTurnID + i), (float)iHeight - (float)iHeight * 100.0F * (float)(Integer)this.lPointsY.get(i) / (float)(nMaxPoint - nMinPoint) / 100.0F));
        }

    }

    public final int getPointY(int i) {
        try {
            return (Integer)this.lPointsY.get(i);
        } catch (IndexOutOfBoundsException var3) {
            return 0;
        }
    }

    public final int getPointsSize() {
        return this.iPointsSize;
    }

    public final int getCivID() {
        return this.iCivID;
    }

    public final int getBeginTurnID() {
        return this.iBeginTurnID;
    }

    public final boolean getDrawData() {
        return this.drawData;
    }

    public final void setDrawData(boolean drawData) {
        if (drawData != this.drawData) {
            if (this.lTime <= CFG.currentTimeMillis - 450L || !this.drawData && !this.backAnimation) {
                this.lTime = CFG.currentTimeMillis;
            } else {
                this.lTime = CFG.currentTimeMillis - (450L - (CFG.currentTimeMillis - this.lTime));
            }

            this.backAnimation = !drawData;
        }

        this.drawData = drawData;
    }

    public final boolean getVisible() {
        return this.visible;
    }

    public final void setVisible(boolean visible) {
        this.visible = visible;
    }

    public final boolean getBackAnimation() {
        return this.backAnimation;
    }

    public final void setBackAnimation(boolean backAnimation) {
        this.backAnimation = backAnimation;
    }

    public final long getTime() {
        return this.lTime;
    }
}
