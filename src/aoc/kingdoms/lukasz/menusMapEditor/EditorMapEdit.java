//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusMapEditor;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.map.Map_Data;
import aoc.kingdoms.lukasz.map.province.ProvinceDrawDetails;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonMain;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class EditorMapEdit extends Menu {
    public EditorMapEdit() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = CFG.PADDING * 2;
        int titleHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2;
        int menuX = CFG.GAME_WIDTH / 10;
        int menuY = CFG.GAME_HEIGHT / 10;
        int buttonYPadding = CFG.PADDING * 2;
        int buttonY = buttonYPadding;
        int textPosX = CFG.PADDING * 4;
        menuElements.add(new ButtonMain((String)null, 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Back"));
            }

            public void actionElement() {
                CFG.brushTool = false;
                Game.menuManager.setViewID(View.EDITOR_MAPS);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.Name + " | " + ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.NumOfProvinces + " - " + Game.lang.get("Scale") + ": " + ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.DefaultMapScale);
            }

            public void actionElement() {
            }

            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawText(oSB, iTranslateX + ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).Icon.getWidth(), iTranslateY, isActive);
                ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).Icon.draw(oSB, iTranslateX + this.getPosX() + CFG.PADDING * 2, iTranslateY + this.getPosY() + this.getHeight() / 2 - ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).Icon.getHeight() / 2);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("FormableCivilizations"));
            }

            public void actionElement() {
                Renderer.drawArmyInProvince = false;
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_FORMABLE_CIVS);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("EditConnectionsAndProvinces"));
            }

            public void actionElement() {
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_ProvinceID();
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("EditSeaProvinces"));
            }

            public void actionElement() {
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_SeaProvinces();
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_SEA_PROVINCES);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("EditTerrainTypes"));
            }

            public void actionElement() {
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_TERRAIN);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Resources"));
            }

            public void actionElement() {
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_Resource();
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_RESOURCE);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("GrowthRateEditor"));
            }

            public void actionElement() {
                CFG.brushTool = false;
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_GrowthRate();
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_GROWTH_RATE);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("BaseDevelopmentLevel"));
            }

            public void actionElement() {
                CFG.brushTool = false;
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_Economy();
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_ECONOMY);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("ContinentsEditor"));
            }

            public void actionElement() {
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_Continent();
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_CONTINENTS);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("RegionsEditor"));
            }

            public void actionElement() {
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_GeoRegion();
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_GEO_REGION);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("ArmyPositionEditor"));
            }

            public void actionElement() {
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_ProvinceID();
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_ARMY_POSITION);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("EditProvinceNames"));
            }

            public void actionElement() {
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_PROVINCE_NAMES);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("OptimizationRegionsEditor"));
            }

            public void actionElement() {
                Renderer.drawArmyInProvince = false;
                ProvinceDrawDetails.updateDrawProvinceDetails_OptimizationRegions();
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS);
                EditorMapOptimizationRegions.lColors.clear();

                for(int i = 0; i < Game.regions.iRegionsSize; ++i) {
                    EditorMapOptimizationRegions.lColors.add(CFG.getRandomColor());
                }

            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("GenerateSuggestedCivilizations"));
            }

            public void actionElement() {
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, false, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("GenerateSeaRoutes"));
            }

            public void actionElement() {
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Lines"));
            }

            public void actionElement() {
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_LINES);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain("", 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, false) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Waves"));
            }

            public void actionElement() {
                Game.menuManager.setViewID(View.EDITOR_MAPS_EDIT_WAVES);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, CFG.isDesktop()) {
            public void updateLanguage() {
                this.setText("Print Map - Scale 0.1");
            }

            public void actionElement() {
                EditorMap_PrintMap.goBack = View.EDITOR_MAPS_EDIT;
                CFG.brushTool = false;
                EditorMap_PrintMap.scale = 12.5F;
                Game.menuManager.setViewIDWithoutAnimation(View.PRINT_A_MAP);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, CFG.isDesktop()) {
            public void updateLanguage() {
                this.setText("Print Map - Scale 0.25");
            }

            public void actionElement() {
                EditorMap_PrintMap.goBack = View.EDITOR_MAPS_EDIT;
                CFG.brushTool = false;
                EditorMap_PrintMap.scale = 4.166667F;
                Game.menuManager.setViewIDWithoutAnimation(View.PRINT_A_MAP);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, CFG.isDesktop()) {
            public void updateLanguage() {
                this.setText("Print Map - Scale 0.5");
            }

            public void actionElement() {
                EditorMap_PrintMap.goBack = View.EDITOR_MAPS_EDIT;
                CFG.brushTool = false;
                EditorMap_PrintMap.scale = 2.272727F;
                Game.menuManager.setViewIDWithoutAnimation(View.PRINT_A_MAP);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, CFG.isDesktop()) {
            public void updateLanguage() {
                this.setText("Print Map - Scale 1");
            }

            public void actionElement() {
                EditorMap_PrintMap.goBack = View.EDITOR_MAPS_EDIT;
                CFG.brushTool = false;
                EditorMap_PrintMap.scale = 1.0F;
                Game.menuManager.setViewIDWithoutAnimation(View.PRINT_A_MAP);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        this.initMenu(new MenuTitle("", 1.0F, titleHeight, true, true), menuX, titleHeight + menuY, CFG.LEFT_MENU_WIDTH, Math.min(buttonY, CFG.GAME_HEIGHT - titleHeight - menuY - CFG.PADDING * 2), menuElements, true);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight() + CFG.PADDING, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void updateLanguage() {
        super.updateLanguage();
        this.getTitle().setText(Game.lang.get("EditMap") + ": " + ((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.Name);
    }
}