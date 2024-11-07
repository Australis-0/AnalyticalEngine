//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski;

import static AnalyticalEngine.AnalyticalEngine.AnalyticalEngine;
import aoc.kingdoms.lukasz.map.map.MapEdgeMove;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menus.Dialog;
import aoc.kingdoms.lukasz.menus.Settings.Settings_Menu;
import aoc.kingdoms.lukasz.menusEditor.GameCivsEdit;
import aoc.kingdoms.lukasz.menusInGame.InGame;
import aoc.kingdoms.lukasz.menusInGame.InGame_ConvertReligion;
import aoc.kingdoms.lukasz.menusInGame.InGame_MoveCapital_PopUp;
import aoc.kingdoms.lukasz.menusInGame.AllianceSpecial.InGame_AllianceSpecialReformHRE;
import aoc.kingdoms.lukasz.menusInGame.AtomicNukes.InGame_BuildAtomicBomb;
import aoc.kingdoms.lukasz.menusInGame.Buildings.InGame_Destroy;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions2;
import aoc.kingdoms.lukasz.menusInGame.Court.ChangeGovernmentReligion.InGame_ChangeIdeology2;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Alliance;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_CallAllies;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_DeclareWar;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_DefensivePact;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_DemandMilitaryAccess;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Guarantee;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Intervene;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_LiberateCivilization;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_NonAggression;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_OfferMilitaryAccess;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Rivals;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Rivals_End;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_SendGift;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_SendInsult;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_SendSpy;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_ShareTechnology;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageAlliance;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageDefensivePact;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageDemandsMilitaryAccess;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageGift;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageGuarantee;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageInsult;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageNonAggressionPact;
import aoc.kingdoms.lukasz.menusInGame.Laws.InGame_LawReform;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy_Invasion;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy_Regroup;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitArmy;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitArmy_NewArmy;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeCapital;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeMilitaryAcademy;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeMilitaryAcademyForGenerals;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeNuclearReactor;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeSupremeCourt;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapArmyPosition;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapLines;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapLinesWaves;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapPortPosition;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapProvinceConnections;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapProvinceNamePoints;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapSeaProvinces;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioAssign;

public class AA_KeyManager {
    public static boolean SHIFT_HOLD = false;
    public static boolean CTRL_HOLD = false;
    public static boolean ALT_HOLD = false;
    public static keyExtraAction keyExtraAction = new keyExtraAction() {
        public boolean extraAction(int keycode) {
            return false;
        }
    };

    public AA_KeyManager() {
    }

    public static boolean keyDown(int keycode) {
        Keyboard var10000 = Game.keyboard;
        if (Keyboard.keyboardMode) {
            return true;
        } else {
            if (CFG.isDesktop()) {
                if (keycode == 59 || keycode == 60) {
                    SHIFT_HOLD = true;
                }

                if (keycode == 129 || keycode == 130) {
                    CTRL_HOLD = true;
                }

                if (keycode == 57 || keycode == 58) {
                    ALT_HOLD = true;
                }
            }

            Game.soundsManager.playSound(SoundsManager.SOUND_CLICK_MAIN2);
            if (keyExtraAction.extraAction(keycode)) {
                return true;
            } else {
                if (!CTRL_HOLD && !AnalyticalEngine().keybind_freeze) {
                    MapEdgeMove var10001;
                    if (keycode == 21 || keycode == 29) {
                        Game.mapEdgeMove.MAP_MOVE_LEFT = true;
                        Game.mapEdgeMove.MAP_MOVE_RIGHT = false;
                        Game.mapEdgeMove.lScrollTime_MAP = CFG.currentTimeMillis;
                        var10001 = Game.mapEdgeMove;
                        Game.mapEdgeMove.iScroll_MAP = (float)MapEdgeMove.DEFAULT_SCROLL;
                        ++Game.mapEdgeMove.MAP_MOVE_KEYBOARD;
                    }

                    if (keycode == 22 || keycode == 32) {
                        Game.mapEdgeMove.MAP_MOVE_RIGHT = true;
                        Game.mapEdgeMove.MAP_MOVE_LEFT = false;
                        Game.mapEdgeMove.lScrollTime_MAP = CFG.currentTimeMillis;
                        var10001 = Game.mapEdgeMove;
                        Game.mapEdgeMove.iScroll_MAP = (float)MapEdgeMove.DEFAULT_SCROLL;
                        ++Game.mapEdgeMove.MAP_MOVE_KEYBOARD;
                    }

                    if (keycode == 19 || keycode == 51) {
                        Game.mapEdgeMove.MAP_MOVE_TOP = true;
                        Game.mapEdgeMove.MAP_MOVE_BOT = false;
                        Game.mapEdgeMove.lScrollTime_MAP = CFG.currentTimeMillis;
                        var10001 = Game.mapEdgeMove;
                        Game.mapEdgeMove.iScroll_MAP = (float)MapEdgeMove.DEFAULT_SCROLL;
                        ++Game.mapEdgeMove.MAP_MOVE_KEYBOARD;
                    }

                    if (keycode == 20 || keycode == 47) {
                        Game.mapEdgeMove.MAP_MOVE_BOT = true;
                        Game.mapEdgeMove.MAP_MOVE_TOP = false;
                        Game.mapEdgeMove.lScrollTime_MAP = CFG.currentTimeMillis;
                        var10001 = Game.mapEdgeMove;
                        Game.mapEdgeMove.iScroll_MAP = (float)MapEdgeMove.DEFAULT_SCROLL;
                        ++Game.mapEdgeMove.MAP_MOVE_KEYBOARD;
                    }
                }

                return false;
            }
        }
    }

    public static final void actionPinnedArmy(int id) {
        try {
            if (Game.player.playerData.pinnedArmies.size() > id) {
                Game.ArmyPos nArmyPos = Game.findArmy_FullCheck(Game.player.iCivID, (String)Game.player.playerData.pinnedArmies.get(id));
                if (nArmyPos != null) {
                    Game.setActiveArmy(nArmyPos.iProvinceID, (String)Game.player.playerData.pinnedArmies.get(id));
                    InGame_ProvinceArmy.iActiveID = 0;
                    InGame_ProvinceArmy.sActiveKEY = (String)Game.player.playerData.pinnedArmies.get(id);
                    Game.menuManager.rebuildInGame_ProvinceArmy();
                    Game.menuManager.rebuildInGame_ProvinceArmy_HideMenus();
                    Game.gameActiveProvince.resetLastActiveProvince();
                    Game.setActiveProvinceID(-1);
                    Game.animationHover.resetAnimationData();
                }
            }
        } catch (Exception var2) {
            Exception ex = var2;
            CFG.exceptionStack(ex);
        }

    }

    public static boolean keyUp(int keycode) {
        Keyboard var10000 = Game.keyboard;
        if (Keyboard.keyboardMode) {
            if (keycode == 66 || keycode == 160) {
                var10000 = Game.keyboard;
                Keyboard.keyboardAction.save();
                return true;
            }

            if (keycode == 111) {
                Game.keyboard.hideKeyboard();
                return true;
            }
        }

        if (CFG.isDesktop()) {
            if (keycode == 59 || keycode == 60) {
                SHIFT_HOLD = false;
            }

            if (keycode == 129 || keycode == 130) {
                CTRL_HOLD = false;
            }

            if (keycode == 57 || keycode == 58) {
                ALT_HOLD = false;
            }
        }

        if (!Game.menuManager.getInMapEditorArmyPosition()) {
            if (keycode == 21 || keycode == 29) {
                Game.mapEdgeMove.MAP_MOVE_LEFT = false;
            }

            if (keycode == 22 || keycode == 32) {
                Game.mapEdgeMove.MAP_MOVE_RIGHT = false;
            }

            if (keycode == 19 || keycode == 51) {
                Game.mapEdgeMove.MAP_MOVE_TOP = false;
            }

            if (keycode == 20 || keycode == 47) {
                Game.mapEdgeMove.MAP_MOVE_BOT = false;
            }
        }

        --Game.mapEdgeMove.MAP_MOVE_KEYBOARD;
        Game.mapEdgeMove.MAP_MOVE_KEYBOARD = Math.max(0, Game.mapEdgeMove.MAP_MOVE_KEYBOARD);
        if (CFG.isDesktop()) {
            if (Game.menuManager.dialogMenu.getVisible()) {
                if (keycode != 66 && keycode != 160 && keycode != 62) {
                    if (keycode == 111 || keycode == 67) {
                        Game.menuManager.dialogMenu.disableButtons();
                        Dialog.dialogFalse();
                        Game.menuManager.dialogMenu.closeMenu();
                    }
                } else {
                    Game.menuManager.dialogMenu.disableButtons();
                    Dialog.dialogTrue();
                    Game.menuManager.dialogMenu.closeMenu();
                }

                return true;
            }

            if (keycode == 141) {
                Game.soundsManager.loadNextMusic();
                return true;
            }

            if (!Keyboard.keyboardMode) {
                if (keycode == 44) {
                    Game.mapScale.scrollScale(-1);
                } else if (keycode == 43) {
                    Game.mapScale.scrollScale(1);
                }
            }

            if (Game.menuManager.getInGameHideUI() && keycode == 111) {
                Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
                return true;
            }

            if (Game.menuManager.getInGame()) {
                if (InGame.ONLY_MAP_MODE) {
                    InGame.ONLY_MAP_MODE = false;
                    return true;
                }

                if (Keyboard.keyboardMode) {
                    return true;
                }

                if (!Game.menuManager.getVisibleInGame_Escape()) {
                    if (keycode == 131) {
                        InGame.action1();
                    } else if (keycode == 132) {
                        InGame.action2();
                    } else if (keycode == 133) {
                        InGame.action3();
                    } else if (keycode == 134) {
                        InGame.action4();
                    } else if (keycode == 135) {
                        InGame.action5();
                    } else if (keycode == 136) {
                        InGame.action6();
                    }
                }

                if (CTRL_HOLD && keycode == 29) {
                    Game.clearActiveArmy();

                    for(int i = 0; i < Game.getCiv(Game.player.iCivID).iArmyPositionSize; ++i) {
                        int tID = Game.getProvince(Game.getCiv(Game.player.iCivID).getArmyPosition(i)).getArmyKeyID(Game.getCiv(Game.player.iCivID).getArmyPositionKey(i));
                        if (tID >= 0) {
                            Game.HoveredArmy nHA = new Game.HoveredArmy();
                            nHA.iCivID = Game.player.iCivID;
                            nHA.iProvinceID = Game.getCiv(Game.player.iCivID).getArmyPosition(i);
                            nHA.key = Game.getCiv(Game.player.iCivID).getArmyPositionKey(i);
                            nHA.iArmyID = tID;
                            Game.addActiveArmy(nHA);
                        }
                    }

                    if (Game.activeArmySize > 0) {
                        Game.setActiveProvinceID(-1);
                        Game.menuManager.rebuildInGame_ProvinceArmy();
                    } else {
                        Game.menuManager.setVisibleInGame_ProvinceArmy(false);
                    }

                    return true;
                }

                if (keycode != 145 && keycode != 8) {
                    if (keycode != 146 && keycode != 9) {
                        if (keycode != 147 && keycode != 10) {
                            if (keycode != 148 && keycode != 11) {
                                if (keycode != 149 && keycode != 12) {
                                    if (keycode == 34) {
                                        if (Game.menuManager.getVisibleInGame_Court() && InGame_Court.inSearchProvinces) {
                                            Game.menuManager.setVisibleInGame_Court(false);
                                        } else {
                                            InGame_CourtOptions2.actionCourt(InGame_CourtOptions2.idCourt);
                                            InGame_Court.actionSearchProvinces();
                                        }
                                    } else if (keycode == 50) {
                                        if (Game.menuManager.getVisibleInGame_RecruitArmy() && Game.menuManager.inCreateNewArmy) {
                                            Game.menuManager.setVisibleInGame_RecruitArmy(false);
                                        } else {
                                            Game.menuManager.hideMenus_RecruitArmy(false);
                                            if (Game.menuManager.getVisibleInGame_Armies()) {
                                                Game.menuManager.setVisibleInGame_Armies(false);
                                            }

                                            InGame_RecruitArmy.actionCreateNewArmy();
                                            if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                                InGame_RecruitArmy_NewArmy.iProvinceID = Game.iActiveProvince;
                                            }
                                        }
                                    } else if (keycode == 30) {
                                        if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.buildID != InGame_CourtOptions.iActiveID) {
                                            InGame_CourtOptions2.actionBuildings(InGame_CourtOptions.buildID);
                                        } else if (!Game.menuManager.getVisibleInGame_Court()) {
                                            InGame.action1();
                                            InGame_CourtOptions2.actionBuildings(InGame_CourtOptions.buildID);
                                        } else {
                                            Game.menuManager.setVisibleInGame_Court(false);
                                        }
                                    } else if (keycode == 40) {
                                        if (Game.menuManager.getVisibleInGame_Court() && InGame_CourtOptions.iLawID != InGame_CourtOptions.iActiveID) {
                                            InGame_CourtOptions2.actionLaws(InGame_CourtOptions.iLawID);
                                        } else if (!Game.menuManager.getVisibleInGame_Court()) {
                                            InGame.action1();
                                            InGame_CourtOptions2.actionLaws(InGame_CourtOptions.iLawID);
                                        } else {
                                            Game.menuManager.setVisibleInGame_Court(false);
                                        }
                                    } else if (keycode == 35) {
                                        if (!Game.menuManager.getVisibleInGame_Generals()) {
                                            InGame_RecruitArmy.actionGenerals();
                                        } else {
                                            Game.menuManager.setVisibleInGame_Generals(false);
                                        }
                                    } else if (keycode == 41) {
                                        if (!Game.menuManager.getVisibleInGame_Armies()) {
                                            Game.menuManager.hideMenus_RecruitArmy(false);
                                        }

                                        InGame_RecruitArmy.actionArmies();
                                    } else if (keycode == 31) {
                                        if (!Game.menuManager.getVisibleInGame_Armies()) {
                                            Game.menuManager.hideMenus_RecruitArmy(false);
                                        }

                                        InGame_RecruitArmy.actionMercenaries();
                                    } else if (keycode == 46) {
                                        InGame.actionRanking();
                                    } else if (keycode == 48) {
                                        InGame.actionCurrent();
                                    } else if (keycode != 66 && keycode != 160) {
                                        if (keycode == 61) {
                                            if (Game.menuManager.getVisibleInGame_Civ()) {
                                                InGame_Civ.diplomacyMode = !InGame_Civ.diplomacyMode;
                                                InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                                                Game.menuManager.rebuildInGame_Civ(true);
                                                InGame_Civ.lTime = 0L;
                                            }
                                        } else if (keycode == 137) {
                                            InGame.ONLY_MAP_MODE = !InGame.ONLY_MAP_MODE;
                                        } else if (keycode == 142) {
                                            if (Game.menuManager.getVisibleInGame_Console()) {
                                                Game.menuManager.setVisibleInGame_Console(false);
                                            } else {
                                                Game.menuManager.setVisibleInGame_Console(false);
                                            }
                                        } else if (keycode == 3) {
                                            if (Game.getCiv(Game.player.iCivID).getCapitalProvinceID() >= 0) {
                                                Game.mapCoords.centerToProvinceID(Game.getCiv(Game.player.iCivID).getCapitalProvinceID());
                                            }
                                        } else {
                                            if (keycode == 111) {
                                                if (Game.regroupArmyMode) {
                                                    Game.setRegroupArmyMode(false);
                                                } else if (Game.invasionArmyMode) {
                                                    Game.setInvasionArmyMode(false);
                                                } else if (Game.menuManager.getVisibleInGame_SaveGame()) {
                                                    Game.menuManager.setVisibleInGame_SaveGame(false);
                                                } else if (Game.menuManager.getVisibleInGame_Escape()) {
                                                    Game.menuManager.setVisibleInGame_Escape(!Game.menuManager.getVisibleInGame_Escape());
                                                } else if (Game.menuManager.getVisibleInGame_Console()) {
                                                    Game.menuManager.setVisibleInGame_Console(false);
                                                } else if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                                                    Game.menuManager.setVisibleInGame_TechnologyTree(false);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RECRUIT_ARMY) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NEW_ARMY_CHOOSE_PROVINCE) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NUKE_CHOOSE_PROVINCE) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_WARS) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_BUILDING) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CONVERT_RELIGION) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CORE) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.menuManager.getVisibleInGame_PopUp()) {
                                                    Game.menuManager.setVisibleInGame_PopUp(false);
                                                } else if (Game.menuManager.getVisibleInGame_TakeLoanRepay()) {
                                                    Game.menuManager.setVisibleInGame_TakeLoanRepay(false);
                                                } else if (Game.menuManager.getVisibleInGame_TakeLoan()) {
                                                    Game.menuManager.setVisibleInGame_TakeLoan(false);
                                                } else if (Game.menuManager.getVisibleInGame_Wonder()) {
                                                    Game.menuManager.setVisibleInGame_Wonder(false);
                                                    if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_WONDERS) {
                                                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                    }
                                                } else if (Game.menuManager.getVisibleInGame_Nukes()) {
                                                    Game.menuManager.setVisibleInGame_Nukes(false);
                                                } else if (Game.menuManager.getVisibleInGame_GoodsMarket()) {
                                                    Game.menuManager.setVisibleInGame_GoodsMarket(false);
                                                } else if (Game.menuManager.getVisibleInGame_Goods()) {
                                                    Game.menuManager.setVisibleInGame_Goods(false);
                                                } else if (Game.menuManager.getVisibleInGame_DisbandArmy()) {
                                                    Game.menuManager.setVisibleInGame_DisbandUnits(false);
                                                } else if (Game.menuManager.getVisibleInGame_ReorganizeUnits()) {
                                                    Game.menuManager.setVisibleInGame_ReorganizeUnits(false);
                                                } else if (Game.menuManager.getVisibleInGame_GeneralRecruit()) {
                                                    Game.menuManager.setVisibleInGame_GeneralRecruit(false);
                                                } else if (Game.menuManager.getVisibleInGame_RecruitArmy()) {
                                                    Game.menuManager.setVisibleInGame_RecruitArmy(false);
                                                } else if (Game.menuManager.getVisibleInGame_TechnologyChoose()) {
                                                    Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                                                } else if (Game.menuManager.getVisibleInGame_Budget()) {
                                                    Game.menuManager.setVisibleInGame_Budget(false);
                                                } else if (Game.menuManager.getVisibleInGame_Civ()) {
                                                    Game.menuManager.setVisibleInGame_Civ(false);
                                                } else if (Game.menuManager.getVisibleInGame_Court()) {
                                                    Game.menuManager.setVisibleInGame_Court(false);
                                                } else if (Game.menuManager.getVisibleInGame_ProvinceBonuses()) {
                                                    Game.menuManager.setVisibleInGame_ProvinceBonuses(false, false);
                                                } else if (Game.menuManager.getVisibleInGame_CivBonuses()) {
                                                    Game.menuManager.setVisibleInGame_CivBonuses(false);
                                                } else if (Game.menuManager.getVisibleInGame_Armies()) {
                                                    Game.menuManager.setVisibleInGame_Armies(false);
                                                } else if (Game.menuManager.getVisibleInGame_Generals()) {
                                                    Game.menuManager.setVisibleInGame_Generals(false);
                                                } else if (Game.menuManager.getVisibleInGame_Buildings()) {
                                                    Game.menuManager.setVisibleInGame_Buildings(false, false);
                                                } else if (Game.menuManager.getVisibleInGame_CurrentSituation()) {
                                                    Game.menuManager.setVisibleInGame_CurrentSituation(false);
                                                } else if (Game.menuManager.getVisibleInGame_Battle()) {
                                                    Game.menuManager.setVisibleInGame_Battle(false);
                                                } else if (Game.menuManager.getVisibleInGame_Siege()) {
                                                    Game.menuManager.setVisibleInGame_Siege(false);
                                                } else if (Game.menuManager.getVisibleInGame_War()) {
                                                    Game.menuManager.setVisibleInGame_War(false);
                                                } else if (Game.menuManager.getVisibleInGame_Peace()) {
                                                    Game.menuManager.setVisibleInGame_Peace(false);
                                                } else if (Game.menuManager.getVisibleInGame_ProvinceArmy()) {
                                                    Game.menuManager.setVisibleInGame_ProvinceArmy(false);
                                                    Game.clearActiveArmy();
                                                } else if (Game.menuManager.getVisibleInGame_ProvinceInfo()) {
                                                    Game.menuManager.setVisibleInGame_ProvinceInfo(false);
                                                    Game.gameActiveProvince.resetLastActiveProvince();
                                                    Game.setActiveProvinceID(-1);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INVEST_IN_ECONOMY) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_MANPOWER) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_MOVE_CAPITAL) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_GROWTH_RATE) {
                                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                                } else {
                                                    Game.menuManager.setVisibleInGame_Escape(!Game.menuManager.getVisibleInGame_Escape());
                                                }

                                                return true;
                                            }

                                            if (keycode == 62) {
                                                Game.gameThread.play = !Game.gameThread.play;
                                            } else if (keycode != 69 && keycode != 156) {
                                                if (keycode == 81 || keycode == 157) {
                                                    Game.gameThread.updateSpeedPlus();
                                                    Game.menuManager.TOAST_TIME = 0L;
                                                }
                                            } else {
                                                Game.gameThread.updateSpeedMinus();
                                                Game.menuManager.TOAST_TIME = 0L;
                                            }
                                        }
                                    } else if (Game.menuManager.getVisibleInGame_RecruitArmy() && Game.menuManager.inCreateNewArmy) {
                                        InGame_RecruitArmy_NewArmy.actionCreateNewArmy();
                                    } else if (Game.regroupArmyMode) {
                                        if (Game.iRegroupArmyProvincesSize > 0) {
                                            InGame_ProvinceArmy_Regroup.confirm();
                                        }
                                    } else if (Game.invasionArmyMode) {
                                        if (Game.invasionArmyProvincesSize > 0) {
                                            InGame_ProvinceArmy_Invasion.confirm();
                                        }
                                    } else if (Game.menuManager.getVisibleInGame_PopUp()) {
                                        MenuManager var4 = Game.menuManager;
                                        if (MenuManager.IN_GAME_POP_UP_MENU_ID == 0) {
                                            InGame_ConvertReligion.confirm();
                                        } else {
                                            var4 = Game.menuManager;
                                            if (MenuManager.IN_GAME_POP_UP_MENU_ID == 1) {
                                                InGame_Destroy.confirm();
                                            } else {
                                                var4 = Game.menuManager;
                                                if (MenuManager.IN_GAME_POP_UP_MENU_ID == 2) {
                                                    InGame_UpgradeMilitaryAcademy.confirm();
                                                } else {
                                                    var4 = Game.menuManager;
                                                    if (MenuManager.IN_GAME_POP_UP_MENU_ID == 3) {
                                                        InGame_UpgradeMilitaryAcademyForGenerals.confirm();
                                                    } else {
                                                        var4 = Game.menuManager;
                                                        if (MenuManager.IN_GAME_POP_UP_MENU_ID == 4) {
                                                            InGame_UpgradeSupremeCourt.confirm();
                                                        } else {
                                                            var4 = Game.menuManager;
                                                            if (MenuManager.IN_GAME_POP_UP_MENU_ID == 5) {
                                                                InGame_UpgradeNuclearReactor.confirm();
                                                            } else {
                                                                var4 = Game.menuManager;
                                                                if (MenuManager.IN_GAME_POP_UP_MENU_ID == 6) {
                                                                    InGame_BuildAtomicBomb.confirm();
                                                                } else {
                                                                    var4 = Game.menuManager;
                                                                    if (MenuManager.IN_GAME_POP_UP_MENU_ID == 7) {
                                                                        InGame_MoveCapital_PopUp.confirm();
                                                                    } else {
                                                                        var4 = Game.menuManager;
                                                                        if (MenuManager.IN_GAME_POP_UP_MENU_ID == 8) {
                                                                            InGame_UpgradeCapital.confirm();
                                                                        } else {
                                                                            var4 = Game.menuManager;
                                                                            if (MenuManager.IN_GAME_POP_UP_MENU_ID == 11) {
                                                                                InGame_ChangeIdeology2.confirm();
                                                                            } else {
                                                                                var4 = Game.menuManager;
                                                                                if (MenuManager.IN_GAME_POP_UP_MENU_ID == 12) {
                                                                                    InGame_DeclareWar.confirm();
                                                                                } else {
                                                                                    var4 = Game.menuManager;
                                                                                    if (MenuManager.IN_GAME_POP_UP_MENU_ID == 13) {
                                                                                        InGame_SendInsult.confirm();
                                                                                    } else {
                                                                                        var4 = Game.menuManager;
                                                                                        if (MenuManager.IN_GAME_POP_UP_MENU_ID == 16) {
                                                                                            InGame_DefensivePact.confirm();
                                                                                        } else {
                                                                                            var4 = Game.menuManager;
                                                                                            if (MenuManager.IN_GAME_POP_UP_MENU_ID == 17) {
                                                                                                InGame_NonAggression.confirm();
                                                                                            } else {
                                                                                                var4 = Game.menuManager;
                                                                                                if (MenuManager.IN_GAME_POP_UP_MENU_ID == 18) {
                                                                                                    InGame_Alliance.confirm();
                                                                                                } else {
                                                                                                    var4 = Game.menuManager;
                                                                                                    if (MenuManager.IN_GAME_POP_UP_MENU_ID == 19) {
                                                                                                        InGame_DemandMilitaryAccess.confirm();
                                                                                                    } else {
                                                                                                        var4 = Game.menuManager;
                                                                                                        if (MenuManager.IN_GAME_POP_UP_MENU_ID == 20) {
                                                                                                            InGame_OfferMilitaryAccess.confirm();
                                                                                                        } else {
                                                                                                            var4 = Game.menuManager;
                                                                                                            if (MenuManager.IN_GAME_POP_UP_MENU_ID == 21) {
                                                                                                                InGame_Guarantee.confirm();
                                                                                                            } else {
                                                                                                                var4 = Game.menuManager;
                                                                                                                if (MenuManager.IN_GAME_POP_UP_MENU_ID == 22) {
                                                                                                                    InGame_SendGift.confirm();
                                                                                                                } else {
                                                                                                                    var4 = Game.menuManager;
                                                                                                                    if (MenuManager.IN_GAME_POP_UP_MENU_ID == 24) {
                                                                                                                        InGame_LawReform.confirm();
                                                                                                                    } else {
                                                                                                                        var4 = Game.menuManager;
                                                                                                                        if (MenuManager.IN_GAME_POP_UP_MENU_ID == 25) {
                                                                                                                            InGame_Rivals.confirm();
                                                                                                                        } else {
                                                                                                                            var4 = Game.menuManager;
                                                                                                                            if (MenuManager.IN_GAME_POP_UP_MENU_ID == 27) {
                                                                                                                                InGame_SendSpy.confirm();
                                                                                                                            } else {
                                                                                                                                var4 = Game.menuManager;
                                                                                                                                if (MenuManager.IN_GAME_POP_UP_MENU_ID == 28) {
                                                                                                                                    InGame_MessageGift.confirm();
                                                                                                                                } else {
                                                                                                                                    var4 = Game.menuManager;
                                                                                                                                    if (MenuManager.IN_GAME_POP_UP_MENU_ID == 29) {
                                                                                                                                        InGame_MessageAlliance.confirm();
                                                                                                                                    } else {
                                                                                                                                        var4 = Game.menuManager;
                                                                                                                                        if (MenuManager.IN_GAME_POP_UP_MENU_ID == 30) {
                                                                                                                                            InGame_MessageDefensivePact.confirm();
                                                                                                                                        } else {
                                                                                                                                            var4 = Game.menuManager;
                                                                                                                                            if (MenuManager.IN_GAME_POP_UP_MENU_ID == 31) {
                                                                                                                                                InGame_MessageNonAggressionPact.confirm();
                                                                                                                                            } else {
                                                                                                                                                var4 = Game.menuManager;
                                                                                                                                                if (MenuManager.IN_GAME_POP_UP_MENU_ID == 32) {
                                                                                                                                                    InGame_MessageDemandsMilitaryAccess.confirm();
                                                                                                                                                } else {
                                                                                                                                                    var4 = Game.menuManager;
                                                                                                                                                    if (MenuManager.IN_GAME_POP_UP_MENU_ID == 33) {
                                                                                                                                                        InGame_MessageGuarantee.confirm();
                                                                                                                                                    } else {
                                                                                                                                                        var4 = Game.menuManager;
                                                                                                                                                        if (MenuManager.IN_GAME_POP_UP_MENU_ID == 34) {
                                                                                                                                                            InGame_MessageInsult.confirm();
                                                                                                                                                        } else {
                                                                                                                                                            var4 = Game.menuManager;
                                                                                                                                                            if (MenuManager.IN_GAME_POP_UP_MENU_ID == 36) {
                                                                                                                                                                InGame_AllianceSpecialReformHRE.confirm();
                                                                                                                                                            } else {
                                                                                                                                                                var4 = Game.menuManager;
                                                                                                                                                                if (MenuManager.IN_GAME_POP_UP_MENU_ID == 37) {
                                                                                                                                                                    InGame_CallAllies.confirm();
                                                                                                                                                                } else {
                                                                                                                                                                    var4 = Game.menuManager;
                                                                                                                                                                    if (MenuManager.IN_GAME_POP_UP_MENU_ID == 40) {
                                                                                                                                                                        InGame_Rivals_End.confirm();
                                                                                                                                                                    } else {
                                                                                                                                                                        var4 = Game.menuManager;
                                                                                                                                                                        if (MenuManager.IN_GAME_POP_UP_MENU_ID == 43) {
                                                                                                                                                                            InGame_LiberateCivilization.confirm();
                                                                                                                                                                        } else {
                                                                                                                                                                            var4 = Game.menuManager;
                                                                                                                                                                            if (MenuManager.IN_GAME_POP_UP_MENU_ID == 44) {
                                                                                                                                                                                InGame_ShareTechnology.confirm();
                                                                                                                                                                            } else {
                                                                                                                                                                                var4 = Game.menuManager;
                                                                                                                                                                                if (MenuManager.IN_GAME_POP_UP_MENU_ID == 48) {
                                                                                                                                                                                    InGame_Intervene.confirm();
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    Game.gameThread.updateSpeed(5);
                                    Game.menuManager.TOAST_TIME = 0L;
                                }
                            } else {
                                Game.gameThread.updateSpeed(4);
                                Game.menuManager.TOAST_TIME = 0L;
                            }
                        } else {
                            Game.gameThread.updateSpeed(3);
                            Game.menuManager.TOAST_TIME = 0L;
                        }
                    } else {
                        Game.gameThread.updateSpeed(2);
                        Game.menuManager.TOAST_TIME = 0L;
                    }
                } else {
                    Game.gameThread.updateSpeed(1);
                    Game.menuManager.TOAST_TIME = 0L;
                }
            } else if (Game.menuManager.getInGameLegacies()) {
                if (keycode == 111 || keycode == 131 || keycode == 132 || keycode == 133 || keycode == 134 || keycode == 135 || keycode == 136) {
                    Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
                }
            } else if (Game.menuManager.getInSettingsMenu()) {
                if (keycode == 111) {
                    Game.menuManager.setViewID(Settings_Menu.goBackToMenu);
                }
            } else if (Game.menuManager.getInScenarioAssign()) {
                if (keycode != 59 && keycode != 60) {
                    if (keycode == 67) {
                        ScenarioAssign.popUndo();
                    } else if (keycode == 45) {
                        CFG.iCreateScenario_AssignProvinces_Civ = 0;
                    }
                } else {
                    CFG.brushTool = !CFG.brushTool;
                }
            } else if (Game.menuManager.getInScenarioAssignInGame()) {
                if (keycode != 59 && keycode != 60) {
                    if (keycode == 45) {
                        CFG.iCreateScenario_AssignProvinces_Civ = 0;
                    }
                } else {
                    CFG.brushTool = !CFG.brushTool;
                }
            } else if (Game.menuManager.getInScenarioCores()) {
                if (keycode == 59 || keycode == 60) {
                    CFG.brushTool = !CFG.brushTool;
                }
            } else if (!Game.menuManager.getInScenarioReligion() && !Game.menuManager.getInScenarioEditorBuildings()) {
                if (Game.menuManager.getInNewGame()) {
                    if (keycode == 33) {
                        GameCivsEdit.nCiv = Game.loadCivilization(Game.ideologiesManager.getRealTag(Game.getCiv(Game.player.iCivID).getCivTag()));
                        GameCivsEdit.goBackTo = View.NEW_GAME;
                        Game.menuManager.setViewID(View.EDITOR_GAMECIVS_EDIT);
                    } else if (keycode == 46) {
                        GameCivsEdit.nCiv = Game.loadCivilization(Game.getCiv(Game.player.iCivID).getCivTag());
                        GameCivsEdit.goBackTo = View.NEW_GAME;
                        Game.menuManager.setViewID(View.EDITOR_GAMECIVS_EDIT);
                    }
                }
            } else if (keycode == 59 || keycode == 60) {
                CFG.brushTool = !CFG.brushTool;
            }
        }

        return false;
    }

    public static final void updateKeyExtraAction() {
        if (Game.menuManager.getInMapEditorProvinceConnections()) {
            keyExtraAction = new keyExtraAction() {
                public boolean extraAction(int keycode) {
                    return EditorMapProvinceConnections.keyUp(keycode);
                }
            };
        } else if (Game.menuManager.getInMapEditorLines()) {
            keyExtraAction = new keyExtraAction() {
                public boolean extraAction(int keycode) {
                    return EditorMapLines.keyUp(keycode);
                }
            };
        } else if (Game.menuManager.getInMapEditorWaves()) {
            keyExtraAction = new keyExtraAction() {
                public boolean extraAction(int keycode) {
                    return EditorMapLinesWaves.keyUp(keycode);
                }
            };
        } else if (Game.menuManager.getInMapEditorSeaProvinces()) {
            keyExtraAction = new keyExtraAction() {
                public boolean extraAction(int keycode) {
                    return EditorMapSeaProvinces.keyUp(keycode);
                }
            };
        } else if (Game.menuManager.getInMapEditorArmyPosition()) {
            keyExtraAction = new keyExtraAction() {
                public boolean extraAction(int keycode) {
                    return EditorMapArmyPosition.keyUp(keycode);
                }
            };
        } else if (Game.menuManager.getInMapEditorProvinceNamePoints()) {
            keyExtraAction = new keyExtraAction() {
                public boolean extraAction(int keycode) {
                    return EditorMapProvinceNamePoints.keyUp(keycode);
                }
            };
        } else if (Game.menuManager.getInMapEditorPortPosition()) {
            keyExtraAction = new keyExtraAction() {
                public boolean extraAction(int keycode) {
                    return EditorMapPortPosition.keyUp(keycode);
                }
            };
        } else {
            keyExtraAction = new keyExtraAction() {
                public boolean extraAction(int keycode) {
                    return false;
                }
            };
        }

    }

    public interface keyExtraAction {
        boolean extraAction(int var1);
    }
}
