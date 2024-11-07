//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusMapEditor;

import static AnalyticalEngine.AnalyticalEngine.AnalyticalEngine;
import AnalyticalEngine.Debugger.console;
import AnalyticalEngine.Framework.ABRS.EditorSaveLoad;
import AnalyticalEngine.Framework.Datatypes.Map;
import AnalyticalEngine.Framework.KeyboardHandler;
import AnalyticalEngine.Framework.Provinces.Provinces;
import aoc.kingdoms.lukasz.jakowski.AA_KeyManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.FBO.FBOProvinceNames;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveManager;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.map.province.ProvinceNameData;
import aoc.kingdoms.lukasz.map.province.ProvinceNamesManager;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonMain;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class EditorMapProvinceNamePoints extends Menu {
    //Declare global instance variables
    public static boolean centerPoint = false;
    public static boolean editing_province_names = false;
    public static boolean firstPoint = true;

    public static boolean started_editor = true;

    //Event Handlers
    //Keyboard Events
    static KeyboardHandler province_name_input = new KeyboardHandler();

    //Framework
    public static void editorSetProvinceName (String arg0_province_id, String arg1_province_name) {
        //Convert from parameters
        String province_id = arg0_province_id;
        String province_name = arg1_province_name;

        try {
            //Declare local instance variables
            ArrayList<Map.City> main_map_cities = (ArrayList<Map.City>) AnalyticalEngine().main.get("map_cities");
            int province_city_index = Map.cityExistsIndexOf(province_id);
            Province province_obj = Provinces.getProvince(province_id);

            float[] province_centre = Provinces.getProvinceCentre(province_obj);

            //Set province name; add city if it doesn't exist
            province_obj.setProvinceName(province_name);
            Map.City local_city = new Map.City(province_centre[0], province_centre[1], province_name, province_id);

            console.log("City exists in index " + province_city_index);

            if (province_city_index == -1) {
                main_map_cities.add(local_city);
            } else {
                main_map_cities.set(province_city_index, local_city);
            }

            //Update main
            AnalyticalEngine().main.put("map_cities", main_map_cities);
            //console.log("Cities: " + main_map_cities.size() + "/" + Game.getProvincesSize());
        } catch (Exception e) {
            console.log(e);
        }
    }

    //UI - Editor Menu
    public EditorMapProvinceNamePoints() {
        List<MenuElement> menuElements = new ArrayList();
        menuElements.add(new ButtonMain((String)null, 1, -1, CFG.PADDING, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Save"));
            }

            public void actionElement() {
                started_editor = false;

                EditorSaveLoad.saveCities();
                SaveManager.saveProvinceNamesPoints();
                Game.menuManager.setViewIDWithoutAnimation(View.EDITOR_MAPS_EDIT);
                Game.menuManager.addToast(Game.lang.get("Saved"));

                AnalyticalEngine().keybind_freeze = false;
                Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE = 0.6F;
            }
        });
        menuElements.add(new ButtonMain((String)null, 1, -1, CFG.GAME_WIDTH - CFG.PADDING - CFG.BUTTON_WIDTH * 2, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH * 2, true) {
            public void updateLanguage() {
                this.setText("Change point");
            }

            public void actionElement() {
                EditorMapProvinceNamePoints.firstPoint = !EditorMapProvinceNamePoints.firstPoint;
            }
        });

        menuElements.add(new ButtonMain((String) null, 1, -1, CFG.GAME_WIDTH - CFG.PADDING - CFG.BUTTON_WIDTH*7, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING, CFG.BUTTON_WIDTH*4, true) {
            public void updateLanguage () {
                this.setText((!editing_province_names) ? "Edit province names" : "Edit province name positions");
            }

            public void actionElement() {
                editing_province_names = (!editing_province_names);
                AnalyticalEngine().keybind_freeze = editing_province_names;
                this.updateLanguage();
            }
        });

        this.initMenu((MenuTitle)null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);

        //Load constants
        EditorSaveLoad.loadCities();
    }
    //UI - Editor Menu/Map
    public void draw (SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        //Draws boxes for buttons
        ImageManager.getImage(Images.boxBIG).draw2(oSB, iTranslateX, iTranslateY + CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - Images.boxTitleBORDERWIDTH, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH, true, false);
        ImageManager.getImage(Images.boxBIG).draw2(oSB, CFG.GAME_WIDTH - CFG.BUTTON_WIDTH * 2 - CFG.PADDING * 2 - Images.boxTitleBORDERWIDTH + iTranslateX, iTranslateY + CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT - CFG.PADDING * 2 - Images.boxTitleBORDERWIDTH, CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH, CFG.BUTTON_HEIGHT + CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH, false, false);

        //Draws text
        this.drawEditorText(oSB, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
        FBOProvinceNames.fboNumToGenerate_Names = 0;
        FBOProvinceNames.disposeProvinceNamesTexture();
        FBOProvinceNames.disposeProvinceNamesFBO();

        //Update constants
        AnalyticalEngine().keybind_freeze = true;
        Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE = 0F;
    }
    //UI - Editor Menu
    public final void drawEditorText (SpriteBatch oSB, int iTranslateX, int iTranslateY) {
        String sText;

        if (Game.iActiveProvince >= 0) {
            Province selected_province = Game.getProvince(Game.iActiveProvince);
            String selected_province_id = Integer.toString(Game.iActiveProvince);
            String selected_province_name = selected_province.getProvinceName();

            sText = "PROVINCE NAME: " + province_name_input.getDisplayString();

            if (!province_name_input.id.equals(selected_province_id)) {
                province_name_input.id = selected_province_id;
                province_name_input.loadString(selected_province_name);
            }
        } else {
            sText = "SELECT PROVINCE";
        }

        sText = sText + "\nEditing Province Names: " + editing_province_names;
        sText = sText + "\n W A S D x3";
        sText = sText + "\n CTRL + W A S D x15";
        sText = sText + "\n ARROWS x1";
        sText = sText + "\n SPACE -> Change Point MODE";
        sText = sText + "\n C -> Center Point ON/OFF";
        sText = sText + "\n P -> RESET";
        if (centerPoint) {
            sText = sText + "\n MODE -> CENTER POINT";
        } else if (firstPoint) {
            sText = sText + "\n MODE -> LEFT POINT";
        } else {
            sText = sText + "\n MODE -> RIGHT POINT";
        }

        //Draw initial black background
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.6F));
        Renderer.drawBoxCorner(oSB, iTranslateX + CFG.PADDING * 3, iTranslateY + CFG.PADDING * 3, (int)Renderer.glyphLayout.width + CFG.PADDING * 2, (int)Renderer.glyphLayout.height + CFG.PADDING * 2);

        //Draw menu text
        oSB.setColor(Color.WHITE);
        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(0), sText);
        Renderer.drawText(oSB, sText, iTranslateX + CFG.PADDING * 4, iTranslateY + CFG.PADDING * 4, Colors.COLOR_TEXT_TITLE);
    }

    //Events handler - Keyboard
    public static boolean keyUp (int keycode) {
        if (Game.iActiveProvince >= 0) {
            Province selected_province = Game.getProvince(Game.iActiveProvince);

            if (editing_province_names) {
                //Province Name Editor
                String current_province_name = selected_province.getProvinceName();
                    province_name_input.pressKey(keycode);
                editorSetProvinceName(Integer.toString(Game.iActiveProvince), province_name_input.getString());
            } else {
                //Positions Editor
                //Positions Editor - Change Mode
                if (keycode == 62) {
                    firstPoint = (!firstPoint);
                    centerPoint = false;
                } else if (keycode == 31) {
                    centerPoint = (!centerPoint);
                }

                //Positions Editor - Keybind Controls
                if (ProvinceNamesManager.provinceNames.get(Game.iActiveProvince) != null) {
                    if (keycode == 46) {
                        if (Game.settingsManager.SETTINGS_PROVINCE_NAMES == 3) {
                            Game.settingsManager.SETTINGS_PROVINCE_NAMES = 2;
                        } else {
                            Game.settingsManager.SETTINGS_PROVINCE_NAMES = 3;
                        }

                        Game.addSimpleTask(new Game.SimpleTask("updateDrawProvinceNames") {
                            public void update() {
                                ProvinceNamesManager.updateDrawProvinceNames();
                                Game.mapCities.updateCitiesInGame();
                            }
                        });
                    }

                    ProvinceNameData var10000;
                    if (centerPoint) {
                        if (keycode == 21) {
                            var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                            var10000.fCenterX += -1.0F;
                        }

                        if (keycode == 22) {
                            ++((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fCenterX;
                        }

                        if (keycode == 19) {
                            var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                            var10000.fCenterY += -1.0F;
                        }

                        if (keycode == 20) {
                            ++((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fCenterY;
                        }

                        if (AA_KeyManager.CTRL_HOLD) {
                            if (keycode == 29) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fCenterX += (float)(-15 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 32) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fCenterX += (float)(15 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 51) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fCenterY += (float)(-15 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 47) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fCenterY += (float)(15 * Game.mapBG.iMapScale);
                            }
                        } else {
                            if (keycode == 29) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fCenterX += (float)(-3 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 32) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fCenterX += (float)(3 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 51) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fCenterY += (float)(-3 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 47) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fCenterY += (float)(3 * Game.mapBG.iMapScale);
                            }
                        }
                    } else if (firstPoint) {
                        if (keycode == 21) {
                            var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                            var10000.fX += -1.0F;
                        }

                        if (keycode == 22) {
                            ++((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fX;
                        }

                        if (keycode == 19) {
                            var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                            var10000.fY += -1.0F;
                        }

                        if (keycode == 20) {
                            ++((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fY;
                        }

                        if (AA_KeyManager.CTRL_HOLD) {
                            if (keycode == 29) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fX += (float)(-15 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 32) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fX += (float)(15 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 51) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fY += (float)(-15 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 47) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fY += (float)(15 * Game.mapBG.iMapScale);
                            }
                        } else {
                            if (keycode == 29) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fX += (float)(-3 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 32) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fX += (float)(3 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 51) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fY += (float)(-3 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 47) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fY += (float)(3 * Game.mapBG.iMapScale);
                            }
                        }
                    } else {
                        if (keycode == 21) {
                            var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                            var10000.fX2 += -1.0F;
                        }

                        if (keycode == 22) {
                            ++((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fX2;
                        }

                        if (keycode == 19) {
                            var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                            var10000.fY2 += -1.0F;
                        }

                        if (keycode == 20) {
                            ++((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fY2;
                        }

                        if (AA_KeyManager.CTRL_HOLD) {
                            if (keycode == 29) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fX2 += (float)(-15 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 32) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fX2 += (float)(15 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 51) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fY2 += (float)(-15 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 47) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fY2 += (float)(15 * Game.mapBG.iMapScale);
                            }
                        } else {
                            if (keycode == 29) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fX2 += (float)(-3 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 32) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fX2 += (float)(3 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 51) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fY2 += (float)(-3 * Game.mapBG.iMapScale);
                            }

                            if (keycode == 47) {
                                var10000 = (ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince);
                                var10000.fY2 += (float)(3 * Game.mapBG.iMapScale);
                            }
                        }
                    }

                    if (keycode == 44) {
                        if (ProvinceNamesManager.provinceNames.get(Game.iActiveProvince) == null)
                            return true;

                        ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fX = (float)Game.getProvince(Game.iActiveProvince).getCenterX();
                        ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fX2 = (float)Game.getProvince(Game.iActiveProvince).getCenterX();
                        ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fY = (float)Game.getProvince(Game.iActiveProvince).getCenterY();
                        ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fY2 = (float)Game.getProvince(Game.iActiveProvince).getCenterY();
                        ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fCenterX = (float)Game.getProvince(Game.iActiveProvince).getCenterX();
                        ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fCenterY = (float)Game.getProvince(Game.iActiveProvince).getCenterY();
                    }

                    if (((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fX2 < ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fX) {
                        float tSw = ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fX;
                        ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fX = ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fX2;
                        ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fX2 = tSw;
                        tSw = ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fY;
                        ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fY = ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fY2;
                        ((ProvinceNameData)ProvinceNamesManager.provinceNames.get(Game.iActiveProvince)).fY2 = tSw;
                        firstPoint = !firstPoint;
                    }

                    ProvinceNamesManager.clearProvNameData(Game.iActiveProvince);
                    ProvinceNamesManager.buildProvNameData(Game.iActiveProvince, false);

                    return true;
                }

                ProvinceNameData newProvinceName = new ProvinceNameData();
                newProvinceName.fCenterX = (float)Game.getProvince(Game.iActiveProvince).getCenterX();
                newProvinceName.fCenterY = (float)Game.getProvince(Game.iActiveProvince).getCenterY();
                newProvinceName.fX = (float)Game.getProvince(Game.iActiveProvince).getMinX();
                newProvinceName.fY = (float)Game.getProvince(Game.iActiveProvince).getCenterY();
                newProvinceName.fX2 = (float)Game.getProvince(Game.iActiveProvince).getMaxX();
                newProvinceName.fY2 = (float)Game.getProvince(Game.iActiveProvince).getCenterY();
                ProvinceNamesManager.provinceNames.set(Game.iActiveProvince, newProvinceName);
                ProvinceNamesManager.clearProvNameData(Game.iActiveProvince);
                ProvinceNamesManager.buildProvNameData(Game.iActiveProvince, false);
            }
        }

        //Return statement
        return false;
    }
}