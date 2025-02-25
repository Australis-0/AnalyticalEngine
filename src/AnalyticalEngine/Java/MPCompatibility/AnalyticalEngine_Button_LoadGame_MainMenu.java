//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.button;

import AnalyticalEngine.AnalyticalEngine_MainMenu;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class AnalyticalEngine_Button_LoadGame_MainMenu extends Button {
    public String sText2;
    public int iTextWidth2;
    public int iTextHeight2;
    protected static long lTimeAnimation = 0L;
    protected static int animationState = 0;
    protected static final int ANIMATION_T = 1000;
    public static final int EXTRA_Y = 3;
    private static final Color colorLine = new Color(0.5176471F, 0.43529412F, 0.25882354F, 0.55F);

    public AnalyticalEngine_Button_LoadGame_MainMenu(String sText, String sText2, int iPosX, int iPosY, int nWidth) {
        this.init(sText, CFG.FONT_REGULAR, 0, iPosX, iPosY, nWidth, getButtonHeight(), true, true, false, false);
        this.sText2 = sText2;
        GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        glyphLayout.setText((BitmapFont)Renderer.fontMain.get(this.fontID), sText2);
        this.iTextWidth2 = (int)glyphLayout.width;
        this.iTextHeight2 = (int)glyphLayout.height;
    }

    protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if ((isActive || this.getIsHovered()) && this.getClickable()) {
            Renderer.drawBox(oSB, this.getButtonBG_Active(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0F);
            oSB.setColor(ButtonGame2.colorGradientHover);
        } else {
            Renderer.drawBox(oSB, this.getButtonBG(), this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0F);
            oSB.setColor(ButtonGame2.colorGradient);
        }

        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + 2 + iTranslateY, this.getWidth(), this.getHeight() - 4);
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.35F));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 3 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        if (!this.getIsHovered() && !isActive) {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.4F));
        } else {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BG_RED.r, Colors.COLOR_GRADIENT_OVER_BG_RED.g, Colors.COLOR_GRADIENT_OVER_BG_RED.b, 0.4F));
        }

        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + this.getInnerPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 3 + CFG.PADDING + iTranslateY, this.getInnerWidth() - CFG.PADDING * 3, (getButtonHeight() - CFG.PADDING * 3) / 2, 1.0F);
        oSB.setColor(Color.WHITE);
        if (this.getClickable() && this.getIsHovered() && animationState >= 0) {
            if (animationState == 0) {
                float drawPerc = Math.min(1.0F * (float)(CFG.currentTimeMillis - lTimeAnimation) / 1000.0F, 1.0F);
                oSB.setColor(getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + 1 + iTranslateY, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++animationState;
                    lTimeAnimation = CFG.currentTimeMillis;
                }
            } else {
                float drawPerc = Math.min(1.0F * (float)(CFG.currentTimeMillis - lTimeAnimation) / 1000.0F, 1.0F);
                oSB.setColor(getColorLine());
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + 1 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, this.getPosX() + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc) + iTranslateX, this.getPosY() + this.getHeight() - 2 + iTranslateY, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    animationState = 0;
                    lTimeAnimation = CFG.currentTimeMillis;
                }
            }

            oSB.setColor(Color.WHITE);
        }

        this.drawImage(oSB, iTranslateX, iTranslateY, isActive);
    }

    public int getButtonBG() {
        return Images.buttonGame;
    }

    public int getButtonBG_Active() {
        return Images.buttonGameH;
    }

    protected static final Color getColorLine() {
        return colorLine;
    }

    protected void drawImage(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (AnalyticalEngine_MainMenu.flag != null) {
            oSB.setShader(Renderer.shaderAlpha);

            try {
                AnalyticalEngine_MainMenu.flag.getTexture().bind(1);
                Gdx.gl.glActiveTexture(33984);
                ImageManager.getImage(Images.flagMaskDefault).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX + (ImageManager.getImage(Images.flagOverDefault).getWidth() - ImageManager.getImage(Images.flagMaskDefault).getWidth()) / 2, this.getPosY() + getPaddingIMG() + iTranslateY + (ImageManager.getImage(Images.flagOverDefault).getHeight() - ImageManager.getImage(Images.flagMaskDefault).getHeight()) / 2, ImageManager.getImage(Images.flagMaskDefault).getWidth(), ImageManager.getImage(Images.flagMaskDefault).getHeight());
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }

            oSB.flush();
            oSB.setShader(Renderer.shaderDefault);
            ImageManager.getImage(Images.flagOverDefault).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY);
        }

    }

    protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        Renderer.drawText(oSB, this.fontID, this.sText, this.getPosX() + this.getInnerPosX() + CFG.PADDING * 3 + iTranslateX, this.getPosY() + 3 + getButtonHeight() / 4 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        Renderer.drawText(oSB, this.fontID, this.sText2, this.getPosX() + this.getInnerPosX() + CFG.PADDING * 3 + iTranslateX, this.getPosY() + getButtonHeight() / 2 + getButtonHeight() / 4 - this.iTextHeight2 / 2 - CFG.PADDING / 2 + iTranslateY, this.getColor(isActive));
    }

    public static int getButtonHeight() {
        return ImageManager.getImage(Images.flagOverDefault).getHeight() + getPaddingIMG() * 2;
    }

    public static int getPaddingIMG() {
        return CFG.PADDING * 2;
    }

    public int getInnerPosX() {
        return getPaddingIMG() * 2 + ImageManager.getImage(Images.flagOverDefault).getWidth();
    }

    public int getInnerWidth() {
        return this.getWidth() - this.getInnerPosX();
    }

    protected Color getColor(boolean isActive) {
        return Colors.getColorButtonHover2(isActive, this.getIsHovered());
    }

    public void setIsHovered(boolean isHovered) {
        super.setIsHovered(isHovered);
        lTimeAnimation = CFG.currentTimeMillis;
        animationState = 0;
    }

    public void buildElementHover() {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LoadGame"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.save, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(this.sText, "", Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(this.sText2, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
}
