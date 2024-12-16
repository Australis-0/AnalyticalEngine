//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusEditor;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Keyboard;
import aoc.kingdoms.lukasz.jakowski.Keyboard.KeyboardActionType;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.ColorPicker.PickerAction;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonMain;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import java.util.ArrayList;
import java.util.List;

public class GameCivsEdit extends Menu {
    public static Game.LoadCivilizationData nCiv = new Game.LoadCivilizationData();
    private final String sCivTAG = "Civilization TAG";
    private String sCivName = "";
    public static View goBackTo;

    public GameCivsEdit() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = CFG.PADDING * 2;
        int titleHeight = CFG.BUTTON_HEIGHT;
        int menuX = CFG.GAME_WIDTH / 10;
        int menuY = CFG.GAME_HEIGHT / 10;
        int buttonYPadding = CFG.PADDING * 2;
        int textPosX = CFG.PADDING * 4;
        this.sCivName = Game.lang.get("Name");
        if (nCiv.Name == null) {
            nCiv.Name = "";
        }

        menuElements.add(new ButtonMain(nCiv.Tag, 1, textPosX, paddingLeft, buttonYPadding, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public String getTextToDraw() {
                return "Civilization TAG: " + GameCivsEdit.nCiv.Tag + (Keyboard.keyboardActionType == KeyboardActionType.GAMECIVS_EDIT ? Keyboard.getKeyboardVerticalLine() : "");
            }

            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.GAMECIVS_EDIT, GameCivsEdit.nCiv.Tag);
            }

            public int getButtonBG() {
                Keyboard var10000 = Game.keyboard;
                return Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.GAMECIVS_EDIT ? super.getButtonBG_Active() : super.getButtonBG();
            }

            public int getButtonBG_Active() {
                Keyboard var10000 = Game.keyboard;
                return Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.GAMECIVS_EDIT ? super.getButtonBG() : super.getButtonBG_Active();
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("CIV_TAG: ", GameCivsEdit.nCiv.Tag, Images.council, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("How to add flag:", "", Images.council, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("Go to:", CFG.FONT_REGULAR, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("mods/GameCivs/gfx/flagsXH/", CFG.FONT_REGULAR, Colors.COLOR_BOX_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("Add new Flag with created TAG of Civilization", CFG.FONT_REGULAR, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("CIV_TAG.png", CFG.FONT_REGULAR, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("Example: ", CFG.FONT_REGULAR, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(GameCivsEdit.nCiv.Tag + ".png", CFG.FONT_REGULAR, Colors.COLOR_BOX_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text("Flag image dimensions: 154 px width x 100 px height", CFG.FONT_REGULAR, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        int buttonY = buttonYPadding + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(nCiv.Name, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public String getTextToDraw() {
                return GameCivsEdit.this.sCivName + ": " + GameCivsEdit.nCiv.Name + (Keyboard.keyboardActionType == KeyboardActionType.GAMECIVS_EDIT_NAME ? Keyboard.getKeyboardVerticalLine() : "");
            }

            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.GAMECIVS_EDIT_NAME, GameCivsEdit.nCiv.Name);
            }

            public int getButtonBG() {
                Keyboard var10000 = Game.keyboard;
                return Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.GAMECIVS_EDIT_NAME ? super.getButtonBG_Active() : super.getButtonBG();
            }

            public int getButtonBG_Active() {
                Keyboard var10000 = Game.keyboard;
                return Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.GAMECIVS_EDIT_NAME ? super.getButtonBG() : super.getButtonBG_Active();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("CivilizationColor"));
            }

            public void actionElement() {
                if (Game.menuManager.getColorPicker().getVisible()) {
                    Game.menuManager.getColorPicker().hideColorPicker();
                } else {
                    Game.menuManager.getColorPicker().setActiveRGBColor((float)GameCivsEdit.nCiv.iR / 255.0F, (float)GameCivsEdit.nCiv.iG / 255.0F, (float)GameCivsEdit.nCiv.iB / 255.0F);
                    Game.menuManager.getColorPicker().setVisible(true, PickerAction.GAMECIVS_EDIT);
                    Game.menuManager.getColorPicker().setPosX(GameCivsEdit.this.getPosX() + GameCivsEdit.this.getWidth() + CFG.PADDING * 4);
                    Game.menuManager.getColorPicker().setPosY(GameCivsEdit.this.getPosY());
                }

            }

            protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                oSB.setColor(new Color((float)GameCivsEdit.nCiv.iR / 255.0F, (float)GameCivsEdit.nCiv.iG / 255.0F, (float)GameCivsEdit.nCiv.iB / 255.0F, 1.0F));
                Images.pix.draw(oSB, iTranslateX + this.getPosX() + this.getWidth() - CFG.CIV_FLAG_WIDTH - CFG.PADDING * 2, iTranslateY + this.getPosY() + this.getHeight() / 2 - CFG.CIV_FLAG_HEIGHT / 2, CFG.CIV_FLAG_WIDTH, CFG.CIV_FLAG_HEIGHT);
                oSB.setColor(Color.WHITE);
                super.drawText(oSB, iTranslateX, iTranslateY, isActive);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Religion") + ": ");
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + Game.religionManager.getReligion(GameCivsEdit.nCiv.ReligionID).Name;
            }

            public void actionElement() {
                if (Game.menuManager.editorGameCivsEditReligion().getVisible()) {
                    Game.menuManager.editorGameCivsEditReligion().setVisible(false);
                } else {
                    Game.menuManager.editorGameCivsEditGroup().setVisible(false);
                    Game.menuManager.editorGameCivsEditReligion().setVisible(!Game.menuManager.editorGameCivsEditReligion().getVisible());
                    Game.menuManager.editorGameCivsEditReligion().setPosX(GameCivsEdit.this.getPosX() + GameCivsEdit.this.getWidth() + CFG.PADDING * 4);
                }

            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Group") + ": ");
            }

            public String getTextToDraw() {
                return super.getTextToDraw() + (String)RulersManager.groups.get(GameCivsEdit.nCiv.GroupID);
            }

            public void actionElement() {
                if (Game.menuManager.editorGameCivsEditGroup().getVisible()) {
                    Game.menuManager.editorGameCivsEditGroup().setVisible(false);
                } else {
                    Game.menuManager.editorGameCivsEditReligion().setVisible(false);
                    Game.menuManager.editorGameCivsEditGroup().setVisible(!Game.menuManager.editorGameCivsEditGroup().getVisible());
                    Game.menuManager.editorGameCivsEditGroup().setPosX(GameCivsEdit.this.getPosX() + GameCivsEdit.this.getWidth() + CFG.PADDING * 4);
                }

            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain(nCiv.Wiki, 1, textPosX, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public String getTextToDraw() {
                return "Wiki: " + GameCivsEdit.nCiv.Wiki + (Keyboard.keyboardActionType == KeyboardActionType.GAMECIVS_EDIT_WIKI ? Keyboard.getKeyboardVerticalLine() : "");
            }

            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.GAMECIVS_EDIT_WIKI, GameCivsEdit.nCiv.Wiki);
            }

            public int getButtonBG() {
                Keyboard var10000 = Game.keyboard;
                return Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.GAMECIVS_EDIT_WIKI ? super.getButtonBG_Active() : super.getButtonBG();
            }

            public int getButtonBG_Active() {
                Keyboard var10000 = Game.keyboard;
                return Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.GAMECIVS_EDIT_WIKI ? super.getButtonBG() : super.getButtonBG_Active();
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Optional"), "", Images.wiki, CFG.FONT_REGULAR, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding + CFG.BUTTON_HEIGHT;
        menuElements.add(new ButtonMain((String)null, 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Save"));
            }

            public void actionElement() {
                GameCivsEdit.this.save();
                Game.menuManager.setViewID(GameCivsEdit.goBackTo);
            }

            public boolean getClickable() {
                return GameCivsEdit.nCiv.Tag.length() > 0 && super.getClickable();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        menuElements.add(new ButtonMain((String)null, 1, -1, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH - paddingLeft * 2, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Back"));
            }

            public void actionElement() {
                Game.menuManager.setViewID(GameCivsEdit.goBackTo);
            }
        });
        int var10000 = buttonY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding;
        this.initMenu(new MenuTitle("", 1.0F, titleHeight, true, true), menuX, titleHeight + menuY, CFG.LEFT_MENU_WIDTH, CFG.GAME_HEIGHT - titleHeight - menuY - CFG.PADDING * 2, menuElements, true);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight() + CFG.PADDING, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void updateLanguage() {
        super.updateLanguage();
        if (nCiv.Tag.length() > 0) {
            this.getTitle().setText(Game.lang.get("Edit") + ": " + Game.lang.getCiv(nCiv.Tag));
        } else {
            this.getTitle().setText(Game.lang.get("AddCivilization"));
        }

    }

    public void actionElement(int nMenuElementID) {
        if (Game.menuManager.getColorPicker().getVisible() && nMenuElementID != 1) {
            Game.menuManager.getColorPicker().hideColorPicker();
        }

        if (Game.menuManager.editorGameCivsEditReligion().getVisible() && nMenuElementID != 2) {
            Game.menuManager.editorGameCivsEditReligion().setVisible(false);
        }

        if (Game.menuManager.editorGameCivsEditGroup().getVisible() && nMenuElementID != 3) {
            Game.menuManager.editorGameCivsEditGroup().setVisible(false);
        }

        super.actionElement(nMenuElementID);
    }

    public final void save() {
        Json json = new Json();
        json.setTypeName((String)null);
        json.setUsePrototypes(false);
        json.setIgnoreUnknownFields(true);
        json.setOutputType(OutputType.json);
        if (CFG.isDesktop()) {
            FileHandle file = FileManager.getSaveType("mods/GameCivs/game/civilizations/" + nCiv.Tag + ".json");
            file.writeString(json.prettyPrint(nCiv), false);
        } else {
            FileHandle file = Gdx.files.local("game/civilizations/" + nCiv.Tag + ".json");
            file.writeString(json.prettyPrint(nCiv), false);
        }

        this.saveCivsList();
    }

    private final void saveCivsList() {
        try {
            FileHandle file;
            if (FileManager.IS_MAC && Gdx.files.external("game/Civilizations.txt").exists()) {
                file = Gdx.files.external("game/Civilizations.txt");
            } else {
                file = Gdx.files.internal("game/Civilizations.txt");
            }

            String tempTags = file.readString();
            if (tempTags.indexOf(nCiv.Tag) < 0) {
                FileHandle fileSave = FileManager.getSaveType("game/Civilizations.txt");
                fileSave.writeString(tempTags + nCiv.Tag + ";", false);
            } else {
                String[] tempTagsSplited = tempTags.split(";");
                boolean tAdd = true;
                int i = 0;

                for(int iSize = tempTagsSplited.length; i < iSize; ++i) {
                    if (tempTagsSplited[i].equals(nCiv.Tag)) {
                        tAdd = false;
                        break;
                    }
                }

                if (!tAdd) {
                    this.onBackPressed();
                    return;
                }

                FileHandle fileSave = FileManager.getSaveType("game/Civilizations.txt");
                fileSave.writeString(tempTags + nCiv.Tag + ";", false);
            }
        } catch (GdxRuntimeException var7) {
            FileHandle fileSave = FileManager.getSaveType("game/Civilizations.txt");
            fileSave.writeString(nCiv.Tag + ";", false);
        }

    }
}
