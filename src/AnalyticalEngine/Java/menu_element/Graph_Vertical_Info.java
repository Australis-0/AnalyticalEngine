//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.graph;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Graph_Vertical_Info {
    private List<String> lTexts = null;
    private int iTextsSize = 0;
    private List<Integer> lTextWidths = null;
    private List<Color> lColors = null;
    private boolean isMoveable = false;
    private boolean moveRight = false;
    private int iTextWidth = 0;
    private int iTextPosX = 0;
    private long lTime = 0L;
    private List<Integer> lSortedIDs = null;

    protected Graph_Vertical_Info(List<String> nTexts, List<Color> nColors, int iWidth, boolean nSortText) {
        this.iTextsSize = nTexts.size();
        this.lTexts = new ArrayList();
        this.lColors = new ArrayList();
        this.lSortedIDs = new ArrayList();
        List<Boolean> tempAdded = new ArrayList();

        for(int i = 0; i < this.iTextsSize; ++i) {
            this.lSortedIDs.add(i);
            tempAdded.add(false);
        }

        if (!nSortText) {
            this.lTexts = nTexts;
            this.lColors = nColors;
        } else {
            while(nTexts.size() != this.lTexts.size()) {
                int nMinID = 0;

                for(int i = 0; i < this.iTextsSize; ++i) {
                    if (!(Boolean)tempAdded.get(i)) {
                        nMinID = i;
                        break;
                    }
                }

                for(int i = nMinID + 1; i < this.iTextsSize; ++i) {
                    if (!(Boolean)tempAdded.get(i) && CFG.compareAlphabetic_TwoString((String)nTexts.get(nMinID), (String)nTexts.get(i))) {
                        nMinID = i;
                    }
                }

                this.lTexts.add((String)nTexts.get(nMinID));
                this.lColors.add((Color)nColors.get(nMinID));
                tempAdded.set(nMinID, true);
                this.lSortedIDs.set(nMinID, this.lTexts.size() - 1);
            }
        }

        this.lTextWidths = new ArrayList();
        ((BitmapFont)Renderer.fontMain.get(0)).getData().setScale(0.7F);

        for(int i = 0; i < this.iTextsSize; ++i) {
            Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(0), (CharSequence)this.lTexts.get(i));
            this.iTextWidth += (int)Renderer.glyphLayout.width;
            this.lTextWidths.add((int)Renderer.glyphLayout.width);
        }

        ((BitmapFont)Renderer.fontMain.get(0)).getData().setScale(1.0F);
        this.iTextWidth += CFG.PADDING * this.iTextsSize + CFG.PADDING * (this.iTextsSize - 1) + (int)((float)CFG.TEXT_HEIGHT * 0.7F * (float)this.iTextsSize);
        this.updateMoveable(iWidth);
    }

    protected final void updateMoveable(int iWidth) {
        if (this.iTextWidth > iWidth) {
            this.isMoveable = true;
            this.resetisMoveable();
        } else {
            this.resetisMoveable();
            this.isMoveable = false;
            this.iTextPosX = iWidth / 2 - this.iTextWidth / 2;
        }

    }

    protected final void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth) {
        if (this.isMoveable) {
            Renderer.clipView_Start(oSB, nPosX, CFG.GAME_HEIGHT - nPosY, nWidth, -((int)((float)CFG.TEXT_HEIGHT * 0.7F)) - CFG.PADDING);
            if (this.lTime < CFG.currentTimeMillis - 45L) {
                this.lTime = CFG.currentTimeMillis;
                if (this.moveRight) {
                    --this.iTextPosX;
                    if (-this.iTextPosX + nWidth >= this.iTextWidth + CFG.PADDING) {
                        this.moveRight = !this.moveRight;
                    }
                } else {
                    ++this.iTextPosX;
                    if (this.iTextPosX >= 0) {
                        this.moveRight = !this.moveRight;
                    }
                }
            }
        }

        int i = 0;

        for(int tempOffsetX = 0; i < this.iTextsSize; ++i) {
            oSB.setColor((Color)this.lColors.get(i));
            Images.pix.draw(oSB, nPosX + tempOffsetX + this.iTextPosX, nPosY, (int)((float)CFG.TEXT_HEIGHT * 0.7F), (int)((float)CFG.TEXT_HEIGHT * 0.7F));
            oSB.setColor(new Color(((Color)this.lColors.get(i)).r, ((Color)this.lColors.get(i)).g, ((Color)this.lColors.get(i)).b, 0.7F));
            ImageManager.getImage(Images.gradientVertical).draw(oSB, nPosX + tempOffsetX + this.iTextPosX, nPosY, (int)((float)CFG.TEXT_HEIGHT * 0.7F), (int)((float)CFG.TEXT_HEIGHT * 0.7F));
            tempOffsetX += (int)((float)CFG.TEXT_HEIGHT * 0.7F) + CFG.PADDING;
            Renderer.drawTextWithShadow(oSB, (String)this.lTexts.get(i), nPosX + tempOffsetX + this.iTextPosX, nPosY, new Color(((Color)this.lColors.get(i)).r, ((Color)this.lColors.get(i)).g, ((Color)this.lColors.get(i)).b, 0.7F));
            tempOffsetX += (Integer)this.lTextWidths.get(i) + CFG.PADDING;
        }

        if (this.isMoveable) {
            Renderer.clipView_End(oSB);
        }

    }

    protected final void resetisMoveable() {
        this.iTextPosX = 0;
        this.moveRight = true;
    }

    protected final int getTextSize() {
        return this.iTextsSize;
    }

    protected final String getText(int i) {
        return (String)this.lTexts.get(i);
    }

    protected final List<Integer> getSorted() {
        return this.lSortedIDs;
    }

    protected final int getSortedID(int i) {
        if (i < 0 || i >= this.lSortedIDs.size()) return (Integer) this.lSortedIDs.get(0); //Guard clause for out-of-bounds

        //Return statement
        return (Integer) this.lSortedIDs.get(i);
    }

    protected final List<Color> getColors() {
        return this.lColors;
    }
}
