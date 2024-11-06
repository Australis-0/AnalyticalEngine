//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map.map;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveManager;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawArmy;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapCities {
    public static Color COLOR_CITY_NAME = new Color(0.9137255F, 0.9137255F, 0.9137255F, 0.65F);
    public static Color COLOR_CITY_CAPITAL_NAME = new Color(0.92156863F, 0.92156863F, 0.92156863F, 0.85F);
    public static final int FONT_ID = 4;
    public List<Image> imageCity = new ArrayList();
    public List<Image> imageCityFort = new ArrayList();
    public CitiesSettings citiesSettings = new CitiesSettings();
    public static float citiesScaleGrowthRate = 1.0F;
    public static final int ACTIVE_CITIES_ANIMATION_TIME = 550;
    public static long lTIME_ACTIVE_CITIES = 0L;
    public static final int HOVERED_CITIES_ANIMATION_TIME = 2000;
    public static long lTIME_HOVERED_CITIES = 0L;
    public CitiesInGame citiesInGame = new CitiesInGame() {
        public void draw(SpriteBatch oSB, float nScale) {
        }
    };
    public CapitalCityName capitalCityName = new CapitalCityName() {
        public void draw(SpriteBatch oSB, int nProvinceID, float fAlpha, int nPosX, int nPosY) {
        }
    };

    public MapCities() {
        this.readSettings();
    }

    public final void updateCitiesScale_CurrentScale() {
        for(int i = this.citiesSettings.citiesScale_CurrentScale.length - 1; i >= 0; --i) {
            this.citiesSettings.citiesScale_CurrentScale[i] = Game.mapCities.citiesSettings.citiesScale[i] * Game.mapScale.getCurrentScale();
        }

    }

    public final void drawCities(SpriteBatch oSB, float nScale) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CITIES_MIN_SCALE) {
            this.drawCities_Just(oSB, nScale, ProvinceDrawArmy.DRAW_CITIES_ALPHA);
        } else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
            this.drawCities_Just(oSB, nScale, ProvinceDrawArmy.DRAW_CITIES_ALPHA);
        }

        if (Game.settingsManager.SETTINGS_PROVINCE_FLAGS > 0) {
            if (Game.settingsManager.SETTINGS_PROVINCE_FLAGS == 1) {
                if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
                    this.drawCities_Just_InGame_CivFlag(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
                } else if (ProvinceDrawArmy.drawProvinceNamesHideAnimation) {
                    this.drawCities_Just_InGame_CivFlag(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
                }
            } else if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
                this.drawCities_Just_InGame_CivFlagName(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
            } else if (ProvinceDrawArmy.drawProvinceNamesHideAnimation) {
                this.drawCities_Just_InGame_CivFlagName(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
            }
        }

    }

    public final void drawCities_HighAllTheTime(SpriteBatch oSB, float nScale) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CITIES_MIN_SCALE) {
            this.drawCities_Just(oSB, nScale, ProvinceDrawArmy.DRAW_CITIES_ALPHA);
        } else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
            this.drawCities_Just(oSB, nScale, ProvinceDrawArmy.DRAW_CITIES_ALPHA);
        }

        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
            this.drawCities_Just_InGame_CivFlagName(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        } else if (ProvinceDrawArmy.drawProvinceNamesHideAnimation) {
            this.drawCities_Just_InGame_CivFlagName(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        }

    }

    public final void updateCitiesInGame() {
        if (Game.settingsManager.SETTINGS_CITIES) {
            if (Game.settingsManager.SETTINGS_PROVINCE_NAMES == 1) {
                this.citiesInGame = new CitiesInGame() {
                    public void draw(SpriteBatch oSB, float nScale) {
                        MapCities.this.drawCities_InGame_NamesLow(oSB, nScale);
                    }
                };
            } else {
                this.citiesInGame = new CitiesInGame() {
                    public void draw(SpriteBatch oSB, float nScale) {
                        MapCities.this.drawCities_InGame(oSB, nScale);
                    }
                };
            }
        } else {
            this.citiesInGame = new CitiesInGame() {
                public void draw(SpriteBatch oSB, float nScale) {
                }
            };
        }

    }

    public final void updateCapitalCityName() {
        if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS) {
            this.capitalCityName = new CapitalCityName() {
                public void draw(SpriteBatch oSB, int nProvinceID, float fAlpha, int nPosX, int nPosY) {
                    Renderer.drawText(oSB, 4, "" + Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getCivName(), nPosX + ImageManager.getImage(Images.capitalLeft).getWidth(), nPosY + (ImageManager.getImage(Images.capitalLeft).getHeight() - Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCapitalNameHeight) / 2, new Color(MapCities.COLOR_CITY_CAPITAL_NAME.r, MapCities.COLOR_CITY_CAPITAL_NAME.g, MapCities.COLOR_CITY_CAPITAL_NAME.b, MapCities.COLOR_CITY_CAPITAL_NAME.a * fAlpha));
                }
            };
        } else {
            this.capitalCityName = new CapitalCityName() {
                public void draw(SpriteBatch oSB, int nProvinceID, float fAlpha, int nPosX, int nPosY) {
                    Renderer.drawText(oSB, 4, "" + Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getCivName(), nPosX + ImageManager.getImage(Images.capitalLeft).getWidth(), nPosY + (ImageManager.getImage(Images.capitalLeft).getHeight() - Game.getCiv(Game.getProvince(nProvinceID).getCivID()).iCapitalNameHeight) / 2, Game.getCiv(Game.player.iCivID).diplomacy.isImprovingRelations(Game.getProvince(nProvinceID).getCivID()) ? new Color(Colors.HOVER_POSITIVE.r, Colors.HOVER_POSITIVE.g, Colors.HOVER_POSITIVE.b, MapCities.COLOR_CITY_CAPITAL_NAME.a * fAlpha) : (Game.getCiv(Game.player.iCivID).diplomacy.isDamagingRelations(Game.getProvince(nProvinceID).getCivID()) ? new Color(Colors.HOVER_NEGATIVE.r, Colors.HOVER_NEGATIVE.g, Colors.HOVER_NEGATIVE.b, Colors.HOVER_NEGATIVE.a * fAlpha) : new Color(MapCities.COLOR_CITY_CAPITAL_NAME.r, MapCities.COLOR_CITY_CAPITAL_NAME.g, MapCities.COLOR_CITY_CAPITAL_NAME.b, MapCities.COLOR_CITY_CAPITAL_NAME.a * fAlpha)));
                }
            };
        }

    }

    public final void drawCities_InGame(SpriteBatch oSB, float nScale) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CITIES_MIN_SCALE) {
            this.drawCities_Just_InGame(oSB, nScale, ProvinceDrawArmy.DRAW_CITIES_ALPHA);
        } else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
            this.drawCities_Just_InGame(oSB, nScale, ProvinceDrawArmy.DRAW_CITIES_ALPHA);
        }

        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
            this.drawCities_Just_InGame_CivFlag(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        } else if (ProvinceDrawArmy.drawProvinceNamesHideAnimation) {
            this.drawCities_Just_InGame_CivFlag(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        }

    }

    public final void drawCities_InGame_NamesLow(SpriteBatch oSB, float nScale) {
        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
            this.drawCities_Just_InGame_NamesLow(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        } else if (ProvinceDrawArmy.drawCitiesHideAnimation) {
            this.drawCities_Just_InGame_NamesLow(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        }

        if (Game.mapScale.getCurrentScale() >= Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE) {
            this.drawCities_Just_InGame_CivFlag(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        } else if (ProvinceDrawArmy.drawProvinceNamesHideAnimation) {
            this.drawCities_Just_InGame_CivFlag(oSB, nScale, ProvinceDrawArmy.DRAW_PROVINCE_NAMES_ALPHA);
        }

    }

    private final void drawCities_Just(SpriteBatch oSB, float nScale, float fAlpha) {
        float fAlpha2 = fAlpha * Game.mapScale.getCurrentScale() / 3.0F;
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, fAlpha));
        float fontScale = Math.min(1.0F, Game.settingsManager.CITIES_FONT_SCALE * Game.mapScale.getCurrentScale());
        this.updateCitiesScale_CurrentScale();

        int i;
        for(i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getProvinceInViewID(i)).drawCities(oSB, nScale, fAlpha, fAlpha2, fontScale);
            }
        }

        for(i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawCities(oSB, nScale, fAlpha, fAlpha2, fontScale);
            }
        }

        long tempTime;
        if (Game.iActiveProvince >= 0) {
            tempTime = CFG.currentTimeMillis;
            if (lTIME_ACTIVE_CITIES > tempTime - 550L) {
                if (Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                    Game.getProvince(Game.iActiveProvince).drawCities(oSB, nScale, fAlpha * ((float)(tempTime - lTIME_ACTIVE_CITIES) / 550.0F), fAlpha * ((float)(tempTime - lTIME_ACTIVE_CITIES) / 550.0F), fontScale);
                }
            } else if (Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                Game.getProvince(Game.iActiveProvince).drawCities(oSB, nScale, fAlpha, fAlpha, fontScale);
            }
        }

        if (Game.iHoveredProvinceID >= 0 && Game.iHoveredProvinceID != Game.iActiveProvince) {
            tempTime = CFG.currentTimeMillis;
            if (lTIME_HOVERED_CITIES > tempTime - 2000L) {
                if (Game.getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                    Game.getProvince(Game.iHoveredProvinceID).drawCities(oSB, nScale, fAlpha * ((float)(tempTime - lTIME_HOVERED_CITIES) / 2000.0F), fAlpha * ((float)(tempTime - lTIME_HOVERED_CITIES) / 2000.0F), fontScale);
                }
            } else if (Game.getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                Game.getProvince(Game.iHoveredProvinceID).drawCities(oSB, nScale, fAlpha, fAlpha, fontScale);
            }
        }

        oSB.setColor(Color.WHITE);
        ((BitmapFont)Renderer.fontMain.get(4)).getData().setScale(1.0F);
    }

    private final void drawCities_Just_InGame(SpriteBatch oSB, float nScale, float fAlpha) {
        float fAlpha2 = fAlpha * Game.mapScale.getCurrentScale() / 3.0F;
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, fAlpha));
        float fontScale = Math.min(1.0F, Game.settingsManager.CITIES_FONT_SCALE * Game.mapScale.getCurrentScale());
        this.updateCitiesScale_CurrentScale();

        int i;
        for(i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getProvinceInViewID(i)).drawCities_InGame(oSB, nScale, fAlpha, fAlpha2, fontScale);
            }
        }

        for(i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawCities_InGame(oSB, nScale, fAlpha, fAlpha2, fontScale);
            }
        }

        long tempTime;
        if (Game.iActiveProvince >= 0) {
            tempTime = CFG.currentTimeMillis;
            if (lTIME_ACTIVE_CITIES > tempTime - 550L) {
                if (Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                    Game.getProvince(Game.iActiveProvince).drawCities_InGame(oSB, nScale, fAlpha * ((float)(tempTime - lTIME_ACTIVE_CITIES) / 550.0F), fAlpha * ((float)(tempTime - lTIME_ACTIVE_CITIES) / 550.0F), fontScale);
                }
            } else if (Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                Game.getProvince(Game.iActiveProvince).drawCities_InGame(oSB, nScale, fAlpha, fAlpha, fontScale);
            }
        }

        if (Game.iHoveredProvinceID >= 0 && Game.iHoveredProvinceID != Game.iActiveProvince) {
            tempTime = CFG.currentTimeMillis;
            if (lTIME_HOVERED_CITIES > tempTime - 2000L) {
                if (Game.getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                    Game.getProvince(Game.iHoveredProvinceID).drawCities_InGame(oSB, nScale, fAlpha * ((float)(tempTime - lTIME_HOVERED_CITIES) / 2000.0F), fAlpha * ((float)(tempTime - lTIME_HOVERED_CITIES) / 2000.0F), fontScale);
                }
            } else if (Game.getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                Game.getProvince(Game.iHoveredProvinceID).drawCities_InGame(oSB, nScale, fAlpha, fAlpha, fontScale);
            }
        }

        oSB.setColor(Color.WHITE);
        ((BitmapFont)Renderer.fontMain.get(4)).getData().setScale(1.0F);
    }

    public final void drawCities_Just_InGame_CivFlagName(SpriteBatch oSB, float nScale, float fAlpha) {
        int i;
        for(i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getProvinceInViewID(i)).drawCities_InGame_CivFlagName(oSB, nScale, fAlpha);
            }
        }

        for(i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawCities_InGame_CivFlagName(oSB, nScale, fAlpha);
            }
        }

        oSB.setColor(Color.WHITE);
    }

    public final void drawCities_Just_InGame_CivFlag(SpriteBatch oSB, float nScale, float fAlpha) {
        if (Game.settingsManager.SETTINGS_PROVINCE_FLAGS > 0) {
            int i;
            for(i = 0; i < Game.getCiv(Game.player.iCivID).diplomacy.iAtWarSize; ++i) {
                int provinceID = Game.getCiv((Integer)Game.getCiv(Game.player.iCivID).diplomacy.atWar.get(i)).getCapitalProvinceID();
                if (provinceID >= 0 && Game.getProvince(provinceID).getDrawProvince()) {
                    Game.getProvince(provinceID).drawCities_InGame_CivFlagWar(oSB, nScale, fAlpha);
                }
            }

            for(i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getProvinceInViewID(i)).isCapital) {
                    Game.getProvince(Game.getProvinceInViewID(i)).drawCities_InGame_CivFlag(oSB, nScale, fAlpha);
                }
            }

            for(i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
                if (Game.getProvince(Game.getExtraProvinceInViewID(i)).isCapital) {
                    Game.getProvince(Game.getExtraProvinceInViewID(i)).drawCities_InGame_CivFlag(oSB, nScale, fAlpha);
                }
            }

            oSB.setColor(Color.WHITE);
        }

    }

    private final void drawCities_Just_InGame_NamesLow(SpriteBatch oSB, float nScale, float fAlpha) {
        float fAlpha2 = fAlpha * Game.mapScale.getCurrentScale() / 2.0F;
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, fAlpha));
        float fontScale = Math.min(1.0F, Game.settingsManager.CITIES_FONT_SCALE * Game.mapScale.getCurrentScale());
        this.updateCitiesScale_CurrentScale();

        int i;
        for(i = 0; i < Game.NUM_OF_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getProvinceInViewID(i)).drawCities_InGame_NamesLow_OnlyName(oSB, nScale, fAlpha, fAlpha2, fontScale);
            }
        }

        for(i = 0; i < Game.NUM_OF_EXTRA_PROVINCES_IN_VIEW; ++i) {
            if (Game.getProvince(Game.getExtraProvinceInViewID(i)).getDrawCities()) {
                Game.getProvince(Game.getExtraProvinceInViewID(i)).drawCities_InGame_NamesLow_OnlyName(oSB, nScale, fAlpha, fAlpha2, fontScale);
            }
        }

        long tempTime;
        if (Game.iActiveProvince >= 0) {
            tempTime = CFG.currentTimeMillis;
            if (lTIME_ACTIVE_CITIES > tempTime - 550L) {
                if (Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                    Game.getProvince(Game.iActiveProvince).drawCities_InGame_NamesLow_OnlyName(oSB, nScale, fAlpha * ((float)(tempTime - lTIME_ACTIVE_CITIES) / 550.0F), fAlpha * ((float)(tempTime - lTIME_ACTIVE_CITIES) / 550.0F), fontScale);
                }
            } else if (Game.getProvince(Game.iActiveProvince).getDrawProvince()) {
                Game.getProvince(Game.iActiveProvince).drawCities_InGame_NamesLow_OnlyName(oSB, nScale, fAlpha, fAlpha, fontScale);
            }
        }

        if (Game.iHoveredProvinceID >= 0 && Game.iHoveredProvinceID != Game.iActiveProvince) {
            tempTime = CFG.currentTimeMillis;
            if (lTIME_HOVERED_CITIES > tempTime - 2000L) {
                if (Game.getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                    Game.getProvince(Game.iHoveredProvinceID).drawCities_InGame_NamesLow_OnlyName(oSB, nScale, fAlpha * ((float)(tempTime - lTIME_HOVERED_CITIES) / 2000.0F), fAlpha * ((float)(tempTime - lTIME_HOVERED_CITIES) / 2000.0F), fontScale);
                }
            } else if (Game.getProvince(Game.iHoveredProvinceID).getDrawProvince()) {
                Game.getProvince(Game.iHoveredProvinceID).drawCities_InGame_NamesLow_OnlyName(oSB, nScale, fAlpha, fAlpha, fontScale);
            }
        }

        oSB.setColor(Color.WHITE);
        ((BitmapFont)Renderer.fontMain.get(4)).getData().setScale(1.0F);
    }

    public final void loadCitiesImages() {
        if (!CFG.isDesktop() && GameValues.value.MOBILE_LOAD_CITIES_2) {
            this.imageCity.add(ImageManager.loadImage("gfx/cities/0.png"));
            this.imageCityFort.add(ImageManager.loadImage("gfx/cities/fort/0.png"));
        } else {
            int i;
            for(i = 0; i < this.citiesSettings.numOfCitiesImages; ++i) {
                this.imageCity.add(ImageManager.loadImage("gfx/cities/" + i + ".png"));
            }

            for(i = 0; i < this.citiesSettings.numOfCitiesImages; ++i) {
                this.imageCityFort.add(ImageManager.loadImage("gfx/cities/fort/" + i + ".png"));
            }
        }

    }

    public final void readSettings() {
        Json json = new Json();
        FileHandle file = FileManager.loadFile("gfx/cities/" + (Game.highTextureSettings ? "Config.json" : "ConfigLow.json"));
        this.citiesSettings = (CitiesSettings)json.fromJson(CitiesSettings.class, file);
        this.citiesSettings.citiesScale_CurrentScale = new float[this.citiesSettings.citiesScale.length];
    }

    public final void buildCities() {
        try {
            List<GameCity> saveGameCities = new ArrayList();

            try {
                Config citiesData = this.readCities("cities.json");
                Iterator var9 = citiesData.cities.iterator();

                while(var9.hasNext()) {
                    Object e = var9.next();
                    saveGameCities.add((GameCity)e);
                }

                citiesData = null;
            } catch (GdxRuntimeException var6) {
                GdxRuntimeException ex = var6;
                CFG.exceptionStack(ex);
            }

            int i = 0;

            for(int iSize = saveGameCities.size(); i < iSize; ++i) {
                for(int j = 0; j < Game.getProvincesSize(); ++j) {
                    if (Game.getProvince(j).getMinX() <= ((GameCity)saveGameCities.get(i)).x * Game.mapBG.iMapScale && Game.getProvince(j).getMaxX() >= ((GameCity)saveGameCities.get(i)).x * Game.mapBG.iMapScale && Game.getProvince(j).getMinY() <= ((GameCity)saveGameCities.get(i)).y * Game.mapBG.iMapScale && Game.getProvince(j).getMaxY() >= ((GameCity)saveGameCities.get(i)).y * Game.mapBG.iMapScale && Game.pathContains(j, ((GameCity)saveGameCities.get(i)).x * Game.mapBG.iMapScale, ((GameCity)saveGameCities.get(i)).y * Game.mapBG.iMapScale)) {
                        ((GameCity)saveGameCities.get(i)).p = j;
                        break;
                    }
                }
            }

            Json json = SaveManager.getJson();
            json.setElementType(Config.class, "cities", GameCity.class);
            FileHandle file = FileManager.getSaveType("map/" + Game.map.getFile_ActiveMap_Path() + "cities/" + "cities.json");
            file.writeString(json.prettyPrint(saveGameCities), false);
            Game.menuManager.addToastGold("Cities Generated!", Images.technology2);
        } catch (Exception var7) {
            Exception ex = var7;
            CFG.exceptionStack(ex);
            Game.menuManager.addToastGold("Cities Generation Error!", Images.technology2);
        }
    }

    public final void loadCities() {
        boolean generateCities = false;

        Exception ex;
        Json json;
        FileHandle tempFileT;
        try {
            tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "cities/" + "cities.json");
            json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList)json.fromJson(ArrayList.class, tempFileT);
            Iterator var5 = tempArrayData.iterator();

            while(var5.hasNext()) {
                JsonValue jValue = (JsonValue)var5.next();
                GameCity oCityData = (GameCity)json.readValue(GameCity.class, jValue);
                if (oCityData.p >= 0 && oCityData.p < Game.getProvincesSize()) {
                    Game.getProvince(oCityData.p).addCity(new City(oCityData.Name, oCityData.x * Game.mapBG.iMapScale, oCityData.y * Game.mapBG.iMapScale, 0));
                }
            }

            tempArrayData.clear();
            tempArrayData = null;
        } catch (Exception var10) {
            ex = var10;
            CFG.exceptionStack(ex);
            generateCities = true;
        }

        try {
            tempFileT = FileManager.loadFile("game/random/RandomProvinceNames.txt");
            String[] tempSplit = tempFileT.readString().split("\n");

            for(int i = 0; i < Game.getProvincesSize(); ++i) {
                if (Game.getProvince(i).getCitiesSize() == 0) {
                    Game.getProvince(i).setProvinceName(tempSplit[i % tempSplit.length]);
                }
            }

            json = null;
        } catch (GdxRuntimeException var9) {
            CFG.exceptionStack(var9);
        }

        try {
            if (generateCities) {
                this.buildCities();
            } else {
                tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "cities/" + "build_cities.txt");
                boolean generate = Boolean.parseBoolean(tempFileT.readString());
                if (generate) {
                    this.buildCities();
                }
            }
        } catch (Exception var8) {
            ex = var8;
            CFG.exceptionStack(ex);
        }

    }

    private final Config readCities(String nFileName) {
        Json json = new Json();
        json.setElementType(Config.class, "cities", GameCity.class);
        return (Config)json.fromJson(Config.class, FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "cities/" + nFileName).reader("UTF8"));
    }

    public final void buildProvinceNames() {
        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (Game.getProvince(i).getCitiesSize() > 0) {
                Game.getProvince(i).setProvinceName(Game.getProvince(i).getCity(0).sCityName);
            }
        }

    }

    public final void updateNameToNewTrueOwner(int iProvinceID, boolean updateNameNow) {
        try {
            if (Game.getProvince(iProvinceID).getCivID() == 0 || Game.getProvince(iProvinceID).getCitiesSize() == 0) {
                return;
            }

            String sCities = "";
            FileHandle fileList;
            if (FileManager.loadFile("game/cities/" + (String)RulersManager.groups.get(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).iGroupID) + ".txt").exists()) {
                fileList = FileManager.loadFile("game/cities/" + (String)RulersManager.groups.get(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).iGroupID) + ".txt");
                sCities = fileList.readString();
            } else if (FileManager.loadFile("game/cities/" + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).realTag + ".txt").exists()) {
                fileList = FileManager.loadFile("game/cities/" + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).realTag + ".txt");
                sCities = fileList.readString();
            } else if (FileManager.loadFile("game/rulersRandom/link/" + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).realTag + ".txt").exists()) {
                fileList = FileManager.loadFile("game/rulersRandom/link/" + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).realTag + ".txt");
                String nFile = fileList.readString();
                if (FileManager.loadFile("game/cities/" + nFile + ".txt").exists()) {
                    FileHandle fileList2 = FileManager.loadFile("game/cities/" + nFile + ".txt");
                    sCities = fileList2.readString();
                }
            }

            if (sCities.length() > 0 && sCities.indexOf(Game.getProvince(iProvinceID).getCity(0).sCityName + "=") >= 0) {
                sCities = sCities.substring(sCities.indexOf(Game.getProvince(iProvinceID).getCity(0).sCityName));
                int tIndex = sCities.indexOf(10, sCities.indexOf("=") + 1);
                sCities = sCities.substring(sCities.indexOf("=") + 1, tIndex < 0 ? sCities.length() : tIndex);
                if (sCities.length() > 1 && sCities.indexOf("=") < 0) {
                    Game.addSimpleTask(new Game.SimpleTask(sCities, iProvinceID) {
                        public void update() {
                            Game.getProvince(this.id).getCity(0).setCityName(this.id, this.taskKey);
                        }
                    });
                    return;
                }
            }

            if (updateNameNow) {
                try {
                    Game.getProvince(iProvinceID).getCity(0).setCityNameOriginal(iProvinceID);
                } catch (Exception var7) {
                    Exception ex = var7;
                    CFG.exceptionStack(ex);
                }
            } else {
                Game.addSimpleTask(new Game.SimpleTask("updateCityName" + iProvinceID, iProvinceID) {
                    public void update() {
                        try {
                            Game.getProvince(this.id).getCity(0).setCityNameOriginal(this.id);
                        } catch (Exception var2) {
                            Exception ex = var2;
                            CFG.exceptionStack(ex);
                        }

                    }
                });
            }
        } catch (Exception var8) {
            Exception ex = var8;
            CFG.exceptionStack(ex);
        }

    }

    public final void updateNameToNewTrueOwner_Civ(int iCivID, boolean updateNameNow) {
        try {
            if (iCivID == 0 || Game.getCiv(iCivID).getNumOfProvinces() == 0) {
                return;
            }

            String sCities = "";
            FileHandle fileList;
            String tempCities;
            if (FileManager.loadFile("game/cities/" + (String)RulersManager.groups.get(Game.getCiv(iCivID).iGroupID) + ".txt").exists()) {
                fileList = FileManager.loadFile("game/cities/" + (String)RulersManager.groups.get(Game.getCiv(iCivID).iGroupID) + ".txt");
                sCities = fileList.readString();
            } else if (FileManager.loadFile("game/cities/" + Game.getCiv(iCivID).realTag + ".txt").exists()) {
                fileList = FileManager.loadFile("game/cities/" + Game.getCiv(iCivID).realTag + ".txt");
                sCities = fileList.readString();
            } else if (FileManager.loadFile("game/rulersRandom/link/" + Game.getCiv(iCivID).realTag + ".txt").exists()) {
                fileList = FileManager.loadFile("game/rulersRandom/link/" + Game.getCiv(iCivID).realTag + ".txt");
                tempCities = fileList.readString();
                if (FileManager.loadFile("game/cities/" + tempCities + ".txt").exists()) {
                    FileHandle fileList2 = FileManager.loadFile("game/cities/" + tempCities + ".txt");
                    sCities = fileList2.readString();
                }
            }

            if (sCities.length() > 0) {
                for(int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getCitiesSize() > 0) {
                        if (sCities.indexOf(Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getCity(0).sCityName + "=") >= 0) {
                            tempCities = sCities.substring(sCities.indexOf(Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getCity(0).sCityName));
                            int tIndex = tempCities.indexOf(10, tempCities.indexOf("=") + 1);
                            tempCities = tempCities.substring(tempCities.indexOf("=") + 1, tIndex < 0 ? tempCities.length() : tIndex);
                            if (tempCities.length() > 1 && tempCities.indexOf("=") < 0) {
                                Game.gameThreadUpdate.addSimpleTask_First(new Game.SimpleTask(tempCities, Game.getCiv(iCivID).getProvinceID(i)) {
                                    public void update() {
                                        Game.getProvince(this.id).getCity(0).setCityName(this.id, this.taskKey);
                                    }
                                });
                                continue;
                            }
                        }

                        if (updateNameNow) {
                            try {
                                Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getCity(0).setCityNameOriginal(Game.getCiv(iCivID).getProvinceID(i));
                            } catch (Exception var7) {
                                CFG.exceptionStack(var7);
                            }
                        } else {
                            Game.gameThreadUpdate.addSimpleTask_First(new Game.SimpleTask("updateCityName" + Game.getCiv(iCivID).getProvinceID(i), Game.getCiv(iCivID).getProvinceID(i)) {
                                public void update() {
                                    try {
                                        Game.getProvince(this.id).getCity(0).setCityNameOriginal(this.id);
                                    } catch (Exception var2) {
                                        Exception ex = var2;
                                        CFG.exceptionStack(ex);
                                    }

                                }
                            });
                        }
                    }
                }
            }
        } catch (Exception var8) {
            Exception ex = var8;
            CFG.exceptionStack(ex);
        }

    }

    public final String getProvinceCityTitle(int iProvinceID) {
        if (Game.getProvince(iProvinceID).getCivID() == 0) {
            return "";
        } else if (Game.getProvince(iProvinceID).isCapital) {
            return Game.lang.get("CapitalCity");
        } else if (Game.getProvince(iProvinceID).getGrowthRateWithBonuses() >= (float)GameValues.growthRate.GROWTH_RATE_MAJOR_CITY) {
            return Game.lang.get("MajorCity");
        } else if (Game.getProvince(iProvinceID).getGrowthRateWithBonuses() >= (float)GameValues.growthRate.GROWTH_RATE_CITY) {
            return Game.lang.get("City");
        } else if (Game.getProvince(iProvinceID).getGrowthRateWithBonuses() >= (float)GameValues.growthRate.GROWTH_RATE_TOWN) {
            return Game.lang.get("Town");
        } else {
            return Game.getProvince(iProvinceID).getGrowthRateWithBonuses() >= (float)GameValues.growthRate.GROWTH_RATE_VILLAGE ? Game.lang.get("Village") : Game.lang.get("SmallVillage");
        }
    }

    public final void updateCities() {
        int i;
        for(i = 0; i < Game.getProvincesSize(); ++i) {
            Game.getProvince(i).setDrawCities(false);
        }

        for(i = 1; i < Game.getCivsSize(); ++i) {
            this.updateCities(i);
        }

    }

    public final void updateCities(int nCivID) {
        try {
            int tempNumOfCities = (int)Math.ceil((double)((float)Game.getCiv(nCivID).getNumOfProvinces() * Game.settingsManager.PERCENTAGE_OF_CITIES_ON_MAP));
            int tMaxPopulation = 1;
            List<Integer> tempProvinces = new ArrayList();

            int i;
            for(i = 0; i < Game.getCiv(nCivID).getNumOfProvinces(); ++i) {
                tempProvinces.add(Game.getCiv(nCivID).getProvinceID(i));
                Game.getProvince(Game.getCiv(nCivID).getProvinceID(i)).setDrawCities(false);
                if (!Game.getProvince(Game.getCiv(nCivID).getProvinceID(i)).isOccupied() && tMaxPopulation < Game.getProvince(Game.getCiv(nCivID).getProvinceID(i)).getPopulationTotal()) {
                    tMaxPopulation = Game.getProvince(Game.getCiv(nCivID).getProvinceID(i)).getPopulationTotal();
                }
            }

            for(i = 0; i < tempNumOfCities && tempProvinces.size() > 0; ++i) {
                int largestProvinceID = 0;
                int largestPopulation = Game.getProvince((Integer)tempProvinces.get(largestProvinceID)).getPopulationTotal();
                i = 1;

                for(int iSize = tempProvinces.size(); i < iSize; ++i) {
                    if (largestPopulation < Game.getProvince((Integer)tempProvinces.get(i)).getPopulationTotal()) {
                        largestProvinceID = i;
                        largestPopulation = Game.getProvince((Integer)tempProvinces.get(largestProvinceID)).getPopulationTotal();
                    }
                }

                Game.getProvince((Integer)tempProvinces.get(largestProvinceID)).setDrawCities(true);
                tempProvinces.remove(largestProvinceID);
            }

            if (Game.getCiv(nCivID).getCapitalProvinceID() >= 0) {
                Game.getProvince(Game.getCiv(nCivID).getCapitalProvinceID()).setDrawCities(true);
            }

            tempProvinces.clear();
        } catch (Exception var10) {
            Exception ex = var10;
            CFG.exceptionStack(ex);
        }

    }

    public static class CitiesSettings {
        public int numOfCitiesImages;
        public float[] citiesScale;
        public float[] citiesScale_CurrentScale;

        public CitiesSettings() {
        }
    }

    public interface CitiesInGame {
        void draw(SpriteBatch var1, float var2);
    }

    public interface CapitalCityName {
        void draw(SpriteBatch var1, int var2, float var3, int var4, int var5);
    }

    protected static class Config {
        public List<GameCity> cities;
        public String name;

        protected Config() {
        }
    }

    public static class GameCity {
        public String Name;
        public int x;
        public int y;
        public int p;

        public GameCity() {
        }
    }
}
