//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map.province;

import AnalyticalEngine.Debugger.console;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.FBO.FBOProvinceNames;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveManager;
import aoc.kingdoms.lukasz.jakowski.zOther.Point_XY;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRegion;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapProvinceNamePoints;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class ProvinceNamesManager {
    public static final int FONT_ID = 0;
    public static int NULL_INDICATOR = 666;
    public static List<ProvinceNameData> provinceNames = new ArrayList();
    public static DrawProvinceNames drawProvinceNames = new DrawProvinceNames() {
        public void drawProvNames(SpriteBatch oSB) {
        }
    };

    public ProvinceNamesManager() {
    }

    public static final void buildProvNamePoints(int i) {
        ProvinceNameData nameData = new ProvinceNameData();
        float maxWidth = 0.0F;
        List<Boolean> was = new ArrayList();

        int checkedWidthNum;
        for(checkedWidthNum = 0; checkedWidthNum < Game.getProvince(i).getPointsSize(); ++checkedWidthNum) {
            was.add(false);
        }

        checkedWidthNum = 0;

        while(checkedWidthNum < Game.getProvince(i).getPointsSize() - 2 && checkedWidthNum < 299) {
            int iID = 0;
            int jID = 1;
            checkedWidthNum += 2;
            maxWidth = 0.0F;

            float tWidth;
            for(int j = 0; j < Game.getProvince(i).getPointsSize() - 1; ++j) {
                if (!(Boolean)was.get(j)) {
                    for(int k = j + 1; k < Game.getProvince(i).getPointsSize(); ++k) {
                        if (!(Boolean)was.get(k)) {
                            tWidth = (float)Math.ceil(Math.sqrt((double)((Game.getProvince(i).getPointsX(k) - Game.getProvince(i).getPointsX(j)) * (Game.getProvince(i).getPointsX(k) - Game.getProvince(i).getPointsX(j)) + (Game.getProvince(i).getPointsY(j) - Game.getProvince(i).getPointsY(k)) * (Game.getProvince(i).getPointsY(j) - Game.getProvince(i).getPointsY(k)))));
                            if (tWidth > maxWidth) {
                                maxWidth = tWidth;
                                nameData.fX = (float)Game.getProvince(i).getPointsX(j);
                                nameData.fX2 = (float)Game.getProvince(i).getPointsX(k);
                                nameData.fY = (float)Game.getProvince(i).getPointsY(j);
                                nameData.fY2 = (float)Game.getProvince(i).getPointsY(k);
                                nameData.fCenterX = (float)Game.getProvince(i).getCenterX();
                                nameData.fCenterY = (float)Game.getProvince(i).getCenterY();
                                iID = j;
                                jID = k;
                            }
                        }
                    }
                }
            }

            was.set(iID, true);
            was.set(jID, true);
            float tfX;
            if (nameData.fX2 < nameData.fX) {
                tfX = nameData.fX;
                nameData.fX = nameData.fX2;
                nameData.fX2 = tfX;
                tfX = nameData.fY;
                nameData.fY = nameData.fY2;
                nameData.fY2 = tfX;
            }

            tfX = nameData.fX + ((float)Game.getProvince(i).getCenterX() - nameData.fX) * 0.4F;
            float tfY = nameData.fY + ((float)Game.getProvince(i).getCenterY() - nameData.fY) * 0.4F;
            tWidth = nameData.fX2 + ((float)Game.getProvince(i).getCenterX() - nameData.fX2) * 0.4F;
            float tfY2 = nameData.fY2 + ((float)Game.getProvince(i).getCenterY() - nameData.fY2) * 0.4F;
            int iPrecision = Game.getProvince(i).getProvinceName().length() * 4;
            Vector2[] vPoints = new Vector2[iPrecision];
            Vector2[] dataSet = new Vector2[]{new Vector2(tfX, tfY), new Vector2(tfX, tfY), new Vector2((float)Game.getProvince(i).getCenterX(), (float)Game.getProvince(i).getCenterY()), new Vector2(tWidth, tfY2), new Vector2(tWidth, tfY2)};
            boolean isInProvince = true;
            CatmullRomSpline<Vector2> oCatmull = new CatmullRomSpline(dataSet, false);

            int j;
            for(j = 0; j < iPrecision; ++j) {
                vPoints[j] = new Vector2();
                oCatmull.valueAt(vPoints[j], (float)j / ((float)iPrecision - 1.0F));
            }

            for(j = vPoints.length - 1; j >= 0; --j) {
                if (Game.setProvinceID_Point((int)vPoints[j].x, (int)vPoints[j].y) != i || Game.setProvinceID_Point((int)vPoints[j].x + CFG.PADDING, (int)vPoints[j].y) != i || Game.setProvinceID_Point((int)vPoints[j].x - CFG.PADDING, (int)vPoints[j].y) != i || Game.setProvinceID_Point((int)vPoints[j].x, (int)vPoints[j].y + CFG.PADDING) != i || Game.setProvinceID_Point((int)vPoints[j].x, (int)vPoints[j].y - CFG.PADDING) != i) {
                    isInProvince = false;
                    break;
                }
            }

            if (isInProvince) {
                checkedWidthNum = -1;
                break;
            }
        }

        if (checkedWidthNum > 0) {
            provinceNames.add(null);
        } else {
            provinceNames.add(nameData);
        }

    }

    public static final void buildProvNameData() {
        boolean saveData = false;
        int i = 0;

        for(int iPNamesSize = provinceNames.size(); i < Game.getProvincesSize(); ++i) {
            if (iPNamesSize <= i) {
                buildProvNamePoints(i);
                iPNamesSize = provinceNames.size();
                saveData = true;
            }

            buildProvNameData(i, false);
        }

        if (saveData) {
            SaveManager.saveProvinceNamesPoints();
        }

    }

    public static void clearProvNameData(int i) {
        if (provinceNames.get(i) != null) {
            ((ProvinceNameData)provinceNames.get(i)).drawPoints.clear();
            ((ProvinceNameData)provinceNames.get(i)).drawMatrix4.clear();
            ((ProvinceNameData)provinceNames.get(i)).fontScale = 1.0F;
            ((ProvinceNameData)provinceNames.get(i)).drawAngleLow = 0.0F;
        }

    }

    public static final void buildProvNameData(int i, boolean rebuild) {
        if (provinceNames.get(i) != null) {
            try {
                if (rebuild) {
                    ((ProvinceNameData)provinceNames.get(i)).drawPoints.clear();
                    ((ProvinceNameData)provinceNames.get(i)).drawMatrix4.clear();
                    ((ProvinceNameData)provinceNames.get(i)).fontScale = 1.0F;
                    ((ProvinceNameData)provinceNames.get(0)).drawAngleLow = 0.0F;
                }

                float maxWidth = CivilizationRegion.getLineWidth3(((ProvinceNameData)provinceNames.get(i)).fX, ((ProvinceNameData)provinceNames.get(i)).fY, ((ProvinceNameData)provinceNames.get(i)).fX2, ((ProvinceNameData)provinceNames.get(i)).fY2);
                float tfX = ((ProvinceNameData)provinceNames.get(i)).fX + (((ProvinceNameData)provinceNames.get(i)).fCenterX - ((ProvinceNameData)provinceNames.get(i)).fX) * 0.4F;
                float tfY = ((ProvinceNameData)provinceNames.get(i)).fY + (((ProvinceNameData)provinceNames.get(i)).fCenterY - ((ProvinceNameData)provinceNames.get(i)).fY) * 0.4F;
                float tfX2 = ((ProvinceNameData)provinceNames.get(i)).fX2 + (((ProvinceNameData)provinceNames.get(i)).fCenterX - ((ProvinceNameData)provinceNames.get(i)).fX2) * 0.4F;
                float tfY2 = ((ProvinceNameData)provinceNames.get(i)).fY2 + (((ProvinceNameData)provinceNames.get(i)).fCenterY - ((ProvinceNameData)provinceNames.get(i)).fY2) * 0.4F;
                int iPrecision = Game.getProvince(i).getProvinceName().length() * 8;
                Vector2[] vPoints = new Vector2[iPrecision];
                Vector2[] dataSet = new Vector2[]{new Vector2(tfX, tfY), new Vector2(tfX, tfY), new Vector2(((ProvinceNameData)provinceNames.get(i)).fCenterX, ((ProvinceNameData)provinceNames.get(i)).fCenterY), new Vector2(tfX2, tfY2), new Vector2(tfX2, tfY2)};
                CatmullRomSpline<Vector2> oCatmull = new CatmullRomSpline(dataSet, false);

                for(int j = 0; j < iPrecision; ++j) {
                    vPoints[j] = new Vector2();
                    oCatmull.valueAt(vPoints[j], (float)j / ((float)iPrecision - 1.0F));
                }

                float tempPrecisionWidth = 0.0F;

                for(int j = 0; j < iPrecision - 1; ++j) {
                    tempPrecisionWidth += CivilizationRegion.getLineWidth3(vPoints[j].x, vPoints[j].y, vPoints[j + 1].x, vPoints[j + 1].y);
                }

                float acceptableWidth = 0.0F;

                try {
                    acceptableWidth = tempPrecisionWidth / (float)(Game.getProvince(i).getProvinceName().length() - 1);
                } catch (ArithmeticException var27) {
                    ArithmeticException ex = var27;
                    CFG.exceptionStack(ex);
                }

                List<Vector2> tempPoints = new ArrayList();
                tempPoints.add(new Vector2(vPoints[0].x, vPoints[0].y));
                float currentPointsWidth = 0.0F;
                int j = 1;

                for(int startPrecision = 0; j < Game.getProvince(i).getProvinceName().length(); ++j) {
                    while(startPrecision < iPrecision - 1) {
                        float tempPrecisionWidth2 = CivilizationRegion.getLineWidth3(vPoints[startPrecision].x, vPoints[startPrecision].y, vPoints[startPrecision + 1].x, vPoints[startPrecision + 1].y);
                        if (currentPointsWidth + tempPrecisionWidth2 >= acceptableWidth) {
                            tempPoints.add(new Vector2(vPoints[startPrecision].x, vPoints[startPrecision].y));
                            currentPointsWidth = acceptableWidth - (currentPointsWidth + tempPrecisionWidth2);
                            break;
                        }

                        currentPointsWidth += tempPrecisionWidth2;
                        ++startPrecision;
                    }
                }

                tempPoints.add(new Vector2(vPoints[vPoints.length - 1].x, vPoints[vPoints.length - 1].y));
                List<Float> lPointsAngle = new ArrayList();
                float fAngle = (float)(Math.atan2((double)(((Vector2)tempPoints.get(0)).y - ((Vector2)tempPoints.get(1)).y), (double)(-((Vector2)tempPoints.get(0)).x + ((Vector2)tempPoints.get(1)).y)) * 180.0 / Math.PI);
                j = 0;

                for(int jSize = Math.min(tempPoints.size(), Game.getProvince(i).getProvinceName().length()); j < jSize; ++j) {
                    try {
                        float tempPointsAngle;
                        if (j < Game.getProvince(i).getProvinceName().length() - 1) {
                            tempPointsAngle = CivilizationRegion.getLinesAngle2(((Vector2)tempPoints.get(j)).x, ((Vector2)tempPoints.get(j)).y, ((Vector2)tempPoints.get(j + 1)).x, ((Vector2)tempPoints.get(j + 1)).y);
                        } else {
                            tempPointsAngle = CivilizationRegion.getLinesAngle2(((Vector2)tempPoints.get(j - 1)).x, ((Vector2)tempPoints.get(j - 1)).y, ((Vector2)tempPoints.get(j)).x, ((Vector2)tempPoints.get(j)).y);
                        }

                        lPointsAngle.add(tempPointsAngle);
                    } catch (Exception var30) {
                        if (j == 0) {
                            try {
                                lPointsAngle.add(CivilizationRegion.getLinesAngle2(((Vector2)tempPoints.get(j)).x, ((Vector2)tempPoints.get(j)).y, ((Vector2)tempPoints.get(j + 1)).x, ((Vector2)tempPoints.get(j + 1)).y));
                            } catch (IndexOutOfBoundsException var26) {
                                lPointsAngle.add(fAngle);
                            }
                        } else {
                            try {
                                lPointsAngle.add(CivilizationRegion.getLinesAngle2(((Vector2)tempPoints.get(j - 1)).x, ((Vector2)tempPoints.get(j - 1)).y, ((Vector2)tempPoints.get(j)).x, ((Vector2)tempPoints.get(j)).y));
                            } catch (IndexOutOfBoundsException var25) {
                                lPointsAngle.add(fAngle);
                            }
                        }
                    }
                }

                float iDistance = maxWidth * 0.8F;
                GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
                Class var38 = ProvinceNamesManager.class;
                int tempNumOfIterations;
                synchronized(ProvinceNamesManager.class) {
                    ((BitmapFont)Renderer.fontBorder.get(0)).getData().setScale(1.0F);
                    glyphLayout.setText((BitmapFont)Renderer.fontBorder.get(0), Game.getProvince(i).getProvinceNameUpperCase());
                    tempNumOfIterations = 0;
                    float tempScale = Math.max(0.1F, iDistance / glyphLayout.width);
                    if (glyphLayout.width > 0.1F) {
                        ((BitmapFont)Renderer.fontBorder.get(0)).getData().setScale(tempScale);
                    }

                    try {
                        if (iDistance > 0.0F) {
                            while(true) {
                                if (iDistance > glyphLayout.width) {
                                    tempScale += 0.025F;
                                    ((BitmapFont)Renderer.fontBorder.get(0)).getData().setScale(Math.max(0.001F, tempScale));
                                    glyphLayout.setText((BitmapFont)Renderer.fontBorder.get(0), Game.getProvince(i).getProvinceNameUpperCase());
                                    if (iDistance < glyphLayout.width) {
                                        ((ProvinceNameData)provinceNames.get(i)).fontScale = Math.max(0.085F, tempScale - 0.0125F);
                                        break;
                                    }
                                } else {
                                    tempScale -= 0.025F;
                                    ((BitmapFont)Renderer.fontBorder.get(0)).getData().setScale(Math.max(0.001F, tempScale));
                                    glyphLayout.setText((BitmapFont)Renderer.fontBorder.get(0), Game.getProvince(i).getProvinceNameUpperCase());
                                    if (iDistance > glyphLayout.width) {
                                        ((ProvinceNameData)provinceNames.get(i)).fontScale = Math.max(0.085F, tempScale + 0.0125F);
                                        break;
                                    }
                                }

                                if (tempNumOfIterations++ > 999) {
                                    CFG.LOG("ProvinceNamesManager: tempNumOfIterations: " + tempNumOfIterations);
                                    ((ProvinceNameData)provinceNames.get(i)).fontScale = 0.085F;
                                    break;
                                }
                            }
                        }
                    } catch (Exception var28) {
                        ((ProvinceNameData)provinceNames.get(i)).fontScale = 1.0E-4F;
                    }
                }

                int a = 0;

                for(tempNumOfIterations = tempPoints.size(); a < tempNumOfIterations; ++a) {
                    ((ProvinceNameData)provinceNames.get(i)).drawPoints.add(new Point_XY((int)((Vector2)tempPoints.get(a)).x, (int)((Vector2)tempPoints.get(a)).y));
                }

                a = 0;

                for(tempNumOfIterations = lPointsAngle.size(); a < tempNumOfIterations; ++a) {
                    ((ProvinceNameData)provinceNames.get(i)).drawMatrix4.add((new Matrix4()).rotate(Renderer.textRotatedVector3, (Float)lPointsAngle.get(a)));
                }

                ((ProvinceNameData)provinceNames.get(i)).drawAngleLow = CivilizationRegion.getLinesAngle2(((Vector2)tempPoints.get(0)).x, ((Vector2)tempPoints.get(0)).y, ((Vector2)tempPoints.get(tempPoints.size() - 1)).x, ((Vector2)tempPoints.get(tempPoints.size() - 1)).y);

                for(a = ((ProvinceNameData)provinceNames.get(i)).drawMatrix4.size(); a < Game.getProvince(i).getProvinceNameUpperCase().length(); ++a) {
                    ((ProvinceNameData)provinceNames.get(i)).drawMatrix4.add((new Matrix4()).rotate(Renderer.textRotatedVector3, 0.0F));
                }

                ((ProvinceNameData)provinceNames.get(i)).drawMatrix4.add((new Matrix4()).rotate(Renderer.textRotatedVector3, ((ProvinceNameData)provinceNames.get(i)).drawAngleLow));
            } catch (Exception e) {
                e.printStackTrace();
                console.log("[ERROR] Province ID " + i + " has a null province name.");
            }
        }
    }

    public static final void updateDrawProvinceNames() {
        if (Game.settingsManager.SETTINGS_PROVINCE_NAMES > 1) {
            if (Game.settingsManager.SETTINGS_PROVINCE_NAMES == 2) {
                drawProvinceNames = new DrawProvinceNames() {
                    public void drawProvNames(SpriteBatch oSB) {
                        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
                            if (Game.settingsManager.FBO_PROVINCE_NAMES) {
                                ProvinceNamesManager.drawProvNames_Just_Medium(oSB);
                            } else {
                                ProvinceNamesManager.drawProvNames_Just_Medium_Default(oSB);
                            }
                        } else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
                            ProvinceNamesManager.drawProvNames_Just_Medium_Default(oSB);
                        }

                    }
                };
            } else {
                drawProvinceNames = new DrawProvinceNames() {
                    public void drawProvNames(SpriteBatch oSB) {
                        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
                            if (Game.settingsManager.FBO_PROVINCE_NAMES) {
                                ProvinceNamesManager.drawProvNames_Just(oSB);
                            } else {
                                ProvinceNamesManager.drawProvNames_Just_Default(oSB);
                            }
                        } else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
                            ProvinceNamesManager.drawProvNames_Just_Default(oSB);
                        }

                    }
                };
            }
        } else {
            drawProvinceNames = new DrawProvinceNames() {
                public void drawProvNames(SpriteBatch oSB) {
                }
            };
        }

    }

    public static final void drawProvNames(SpriteBatch oSB) {
        try {
            drawProvinceNames.drawProvNames(oSB);
        } catch (Exception var2) {
            Exception ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    public static final synchronized void drawProvNames_Just_Default(SpriteBatch oSB) {
        Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
        ((BitmapFont)Renderer.fontBorder.get(0)).setColor(new Color(1.0F, 1.0F, 1.0F, Game.settingsManager.PROVINCE_NAMES_ALPHA * ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA));
        drawProvNames_Just_Default_Inner(oSB);
        oSB.setTransformMatrix(oldTransformMatrix);
    }

    public static final void drawProvNames_Just_Default_Inner(SpriteBatch oSB) {
        try {
            int i;
            for(i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                drawProvName(oSB, Game.getProvinceInViewID(i), 0);
            }

            for(i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                drawProvName(oSB, Game.getExtraProvinceInViewID(i), Game.mapBG.getWidth());
            }
        } catch (Exception var2) {
            Exception ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    public static final synchronized void drawProvNames_Just(SpriteBatch oSB) {
        try {
            if (FBOProvinceNames.textureProvince_Names != null) {
                if (FBOProvinceNames.lastPosX == Game.mapCoords.getPosX() && FBOProvinceNames.lastPosY == Game.mapCoords.getPosY()) {
                    FBOProvinceNames.drawProvinceNames(oSB);
                } else {
                    FBOProvinceNames.disposeProvinceNamesTexture();
                    FBOProvinceNames.disposeProvinceNamesFBO();
                    drawProvNames_Just_Default(oSB);
                }
            } else {
                FBOProvinceNames.updateFBO();
                if (FBOProvinceNames.fboNumToGenerate_Names >= GameValues.value.FBO_NUM_TO_GENERATE_NAMES) {
                    FBOProvinceNames.fboNumToGenerate_Names = 0;
                    oSB.end();
                    FBOProvinceNames.disposeProvinceNamesFBO();
                    FBOProvinceNames.disposeProvinceNamesTexture();
                    FBOProvinceNames.fboProvince_Names = new FrameBuffer(Format.RGBA8888, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, false);
                    FBOProvinceNames.fboProvince_Names.begin();
                    oSB.begin();
                    Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
                    ((BitmapFont)Renderer.fontBorder.get(0)).setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
                    drawProvNames_Just_Default_Inner(oSB);
                    oSB.setTransformMatrix(oldTransformMatrix);
                    oSB.end();
                    FBOProvinceNames.fboProvince_Names.end();
                    FBOProvinceNames.textureProvince_Names = (Texture)FBOProvinceNames.fboProvince_Names.getColorBufferTexture();
                    FBOProvinceNames.fboPosX = Game.mapCoords.getPosX();
                    FBOProvinceNames.fboPosY = Game.mapCoords.getPosY();
                    oSB.begin();
                    if (FBOProvinceNames.textureProvince_Names != null) {
                        FBOProvinceNames.drawProvinceNames(oSB);
                    }
                } else {
                    drawProvNames_Just_Default(oSB);
                }
            }
        } catch (Exception var2) {
            Exception ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    public static final synchronized void drawProvNames_Just_Medium_Default(SpriteBatch oSB) {
        Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
        ((BitmapFont)Renderer.fontBorder.get(0)).setColor(new Color(1.0F, 1.0F, 1.0F, Game.settingsManager.PROVINCE_NAMES_ALPHA * ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA));
        drawProvNames_Just_Medium_Default_Inner(oSB);
        oSB.setTransformMatrix(oldTransformMatrix);
    }

    public static final void drawProvNames_Just_Medium_Default_Inner(SpriteBatch oSB) {
        try {
            int i;
            for(i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                drawProvName_Medium(oSB, Game.getProvinceInViewID(i), 0);
            }

            for(i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                drawProvName_Medium(oSB, Game.getExtraProvinceInViewID(i), Game.mapBG.getWidth());
            }
        } catch (Exception var2) {
            Exception ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    public static final synchronized void drawProvNames_Just_Medium(SpriteBatch oSB) {
        try {
            if (FBOProvinceNames.textureProvince_Names != null) {
                if (FBOProvinceNames.lastPosX == Game.mapCoords.getPosX() && FBOProvinceNames.lastPosY == Game.mapCoords.getPosY()) {
                    FBOProvinceNames.drawProvinceNames(oSB);
                } else {
                    FBOProvinceNames.disposeProvinceNamesTexture();
                    FBOProvinceNames.disposeProvinceNamesFBO();
                    drawProvNames_Just_Medium_Default(oSB);
                }
            } else {
                FBOProvinceNames.updateFBO();
                if (FBOProvinceNames.fboNumToGenerate_Names >= GameValues.value.FBO_NUM_TO_GENERATE_NAMES) {
                    FBOProvinceNames.fboNumToGenerate_Names = 0;
                    oSB.end();
                    FBOProvinceNames.disposeProvinceNamesFBO();
                    FBOProvinceNames.disposeProvinceNamesTexture();
                    FBOProvinceNames.fboProvince_Names = new FrameBuffer(Format.RGBA8888, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, false);
                    FBOProvinceNames.fboProvince_Names.begin();
                    oSB.begin();
                    Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
                    ((BitmapFont)Renderer.fontBorder.get(0)).setColor(new Color(1.0F, 1.0F, 1.0F, 1.0F));
                    drawProvNames_Just_Medium_Default_Inner(oSB);
                    oSB.setTransformMatrix(oldTransformMatrix);
                    oSB.end();
                    FBOProvinceNames.fboProvince_Names.end();
                    FBOProvinceNames.textureProvince_Names = (Texture)FBOProvinceNames.fboProvince_Names.getColorBufferTexture();
                    FBOProvinceNames.fboPosX = Game.mapCoords.getPosX();
                    FBOProvinceNames.fboPosY = Game.mapCoords.getPosY();
                    oSB.begin();
                    if (FBOProvinceNames.textureProvince_Names != null) {
                        FBOProvinceNames.drawProvinceNames(oSB);
                    }
                } else {
                    drawProvNames_Just_Medium_Default(oSB);
                }
            }
        } catch (Exception var2) {
            Exception ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    public static final synchronized void drawProvName_Old(SpriteBatch oSB, int i, int extraX) {
        try {
            if (!Game.getProvince(i).getSeaProvince() && provinceNames.get(i) != null) {
                ((BitmapFont)Renderer.fontBorder.get(0)).getData().setScale(((ProvinceNameData)provinceNames.get(i)).fontScale * Game.mapScale.getCurrentScale());
                extraX += Game.getProvince(i).getTranslateProvincePosX();

                for(int j = Game.getProvince(i).iProvinceNameLength_Minus1; j >= 0; --j) {
                    try {
                        Renderer.drawTextRotatedBorder(oSB, 0, "" + Game.getProvince(i).getProvinceNameUpperCase().charAt(j), (int)((float)(extraX + ((Point_XY)((ProvinceNameData)provinceNames.get(i)).drawPoints.get(j)).getPosX()) * Game.mapScale.getCurrentScale()), (int)((float)(Game.mapCoords.getPosY() + ((Point_XY)((ProvinceNameData)provinceNames.get(i)).drawPoints.get(j)).getPosY()) * Game.mapScale.getCurrentScale()), (Matrix4)((ProvinceNameData)provinceNames.get(i)).drawMatrix4.get(j));
                    } catch (Exception var5) {
                    }
                }
            }
        } catch (Exception var6) {
        }

    }

    public static final synchronized void drawProvName(SpriteBatch oSB, int i, int extraX) {
        if (provinceNames.get(i) != null) {
            try {
                ProvinceNameData provinceName = (ProvinceNameData)provinceNames.get(i);
                float fontScale = provinceName.fontScale * Game.mapScale.getCurrentScale();
                if (fontScale > Game.settingsManager.PROVINCE_NAMES_SCALE) {
                    ((BitmapFont)Renderer.fontBorder.get(0)).getData().setScale(fontScale);
                    extraX += Game.getProvince(i).getTranslateProvincePosX();

                    for(int j = Game.getProvince(i).iProvinceNameLength_Minus1; j >= 0; --j) {
                        Renderer.drawTextRotatedBorder(oSB, 0, String.valueOf(Game.getProvince(i).getProvinceNameUpperCase().charAt(j)), (int)((float)(extraX + ((Point_XY)provinceName.drawPoints.get(j)).getPosX()) * Game.mapScale.getCurrentScale()), (int)((float)(Game.mapCoords.getPosY() + ((Point_XY)provinceName.drawPoints.get(j)).getPosY()) * Game.mapScale.getCurrentScale()), (Matrix4)provinceName.drawMatrix4.get(j));
                    }
                }
            } catch (Exception var6) {
            }

        }
    }

    public static final synchronized void drawProvName_Medium(SpriteBatch oSB, int i, int extraX) {
        if (provinceNames.get(i) != null) {
            float fontScale = ((ProvinceNameData)provinceNames.get(i)).fontScale * Game.mapScale.getCurrentScale();
            if (fontScale > Game.settingsManager.PROVINCE_NAMES_SCALE) {
                ((BitmapFont)Renderer.fontBorder.get(0)).getData().setScale(fontScale);
                Renderer.drawTextRotatedBorder_2(oSB, 0, "" + Game.getProvince(i).getProvinceNameUpperCase(), (int)((float)(Game.getProvince(i).getTranslateProvincePosX() + extraX + ((Point_XY)((ProvinceNameData)provinceNames.get(i)).drawPoints.get(0)).getPosX()) * Game.mapScale.getCurrentScale()), (int)((float)(Game.mapCoords.getPosY() + ((Point_XY)((ProvinceNameData)provinceNames.get(i)).drawPoints.get(0)).getPosY()) * Game.mapScale.getCurrentScale()), ((ProvinceNameData)provinceNames.get(i)).drawAngleLow);
            }

        }
    }

    public static final void drawProvNamePoints(SpriteBatch oSB, int i) {
        if (provinceNames.get(i) != null) {
            if (EditorMapProvinceNamePoints.firstPoint && !EditorMapProvinceNamePoints.centerPoint) {
                oSB.setColor(Color.RED);
            }

            Images.pix.draw(oSB, (int)((float)Game.mapCoords.getPosX() + ((ProvinceNameData)provinceNames.get(i)).fX) - 1, (int)((float)Game.mapCoords.getPosY() + ((ProvinceNameData)provinceNames.get(i)).fY) - 1, 3, 3);
            oSB.setColor(Color.WHITE);
            if (EditorMapProvinceNamePoints.centerPoint) {
                oSB.setColor(Color.RED);
            }

            Images.pix.draw(oSB, (int)((float)Game.mapCoords.getPosX() + ((ProvinceNameData)provinceNames.get(i)).fCenterX) - 1, (int)((float)Game.mapCoords.getPosY() + ((ProvinceNameData)provinceNames.get(i)).fCenterY) - 1, 3, 3);
            oSB.setColor(Color.WHITE);
            if (!EditorMapProvinceNamePoints.firstPoint && !EditorMapProvinceNamePoints.centerPoint) {
                oSB.setColor(Color.RED);
            }

            Images.pix.draw(oSB, (int)((float)Game.mapCoords.getPosX() + ((ProvinceNameData)provinceNames.get(i)).fX2) - 1, (int)((float)Game.mapCoords.getPosY() + ((ProvinceNameData)provinceNames.get(i)).fY2) - 1, 3, 3);
            oSB.setColor(Color.WHITE);
        }

    }

    public interface DrawProvinceNames {
        void drawProvNames(SpriteBatch var1);
    }
}
