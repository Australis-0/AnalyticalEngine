//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu;

import AnalyticalEngine.AnalyticalEngine_MainMenu;
import aoc.kingdoms.lukasz.events.Event;
import aoc.kingdoms.lukasz.jakowski.AA_KeyManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Keyboard;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.jakowski.Touch;
import aoc.kingdoms.lukasz.jakowski.Keyboard.KeyboardActionType;
import aoc.kingdoms.lukasz.jakowski.Player.MessageTypes.PMessage;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.Renderer.RendererGame;
import aoc.kingdoms.lukasz.jakowski.Renderer.SparksAnimation;
import aoc.kingdoms.lukasz.jakowski.zOther.Point_XY;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRegionsManager;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.map.province.ProvinceHover;
import aoc.kingdoms.lukasz.map.province.ProvinceTouchExtraAction;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.Toast;
import aoc.kingdoms.lukasz.menu_element.button.Button;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menus.CloudsMenu;
import aoc.kingdoms.lukasz.menus.Dialog;
import aoc.kingdoms.lukasz.menus.EmptyMenu;
import aoc.kingdoms.lukasz.menus.InitGame;
import aoc.kingdoms.lukasz.menus.Init_SelectLanguage;
import aoc.kingdoms.lukasz.menus.Init_SelectMap;
import aoc.kingdoms.lukasz.menus.Init_SelectMap2;
import aoc.kingdoms.lukasz.menus.MainMenu;
import aoc.kingdoms.lukasz.menus.MainMenu_Stats;
import aoc.kingdoms.lukasz.menus.MainMenu_StatsEmpty;
import aoc.kingdoms.lukasz.menus.Menu_LoadGames_List;
import aoc.kingdoms.lukasz.menus.WorkshopMenu;
import aoc.kingdoms.lukasz.menus.LoadSave.Menu_LoadSaveScenario;
import aoc.kingdoms.lukasz.menus.LoadSave.Menu_LoadSavedGame;
import aoc.kingdoms.lukasz.menus.LoadSave.Menu_LoadSavingGame;
import aoc.kingdoms.lukasz.menus.LoadSave.Menu_LoadScenario;
import aoc.kingdoms.lukasz.menus.LoadSave.Menu_Load_Workshop;
import aoc.kingdoms.lukasz.menus.Multi.MultiGames;
import aoc.kingdoms.lukasz.menus.Multi.MultiLobby;
import aoc.kingdoms.lukasz.menus.Multi.MultiLobbyCreate;
import aoc.kingdoms.lukasz.menus.Multi.MultiPassword;
import aoc.kingdoms.lukasz.menus.NewGame.NewGame;
import aoc.kingdoms.lukasz.menus.NewGame.NewGameCiv;
import aoc.kingdoms.lukasz.menus.NewGame.NewGameFlags;
import aoc.kingdoms.lukasz.menus.NewGame.NewGame_Settings;
import aoc.kingdoms.lukasz.menus.NewGame.Scenarios.Scenarios;
import aoc.kingdoms.lukasz.menus.NewGame.Scenarios.ScenariosList_NewGame;
import aoc.kingdoms.lukasz.menus.NewGame.Scenarios.ScenariosList_NewGame_Buttons;
import aoc.kingdoms.lukasz.menus.NewGame.Scenarios.Scenarios_Campaign_Buttons;
import aoc.kingdoms.lukasz.menus.NewGame.Scenarios.Scenarios_Campaign_Vertical;
import aoc.kingdoms.lukasz.menus.NewGame.Scenarios.Scenarios_Campaign_VerticalScenarios;
import aoc.kingdoms.lukasz.menus.Settings.Settings_Menu;
import aoc.kingdoms.lukasz.menus.Settings.Settings_Resolution;
import aoc.kingdoms.lukasz.menus.Settings.Settings_UIScale;
import aoc.kingdoms.lukasz.menusEditor.CreateCiv;
import aoc.kingdoms.lukasz.menusEditor.CreateCivGroup;
import aoc.kingdoms.lukasz.menusEditor.CreateCivReligion;
import aoc.kingdoms.lukasz.menusEditor.CreateCiv_Flag;
import aoc.kingdoms.lukasz.menusEditor.Editor;
import aoc.kingdoms.lukasz.menusEditor.GameCivs;
import aoc.kingdoms.lukasz.menusEditor.GameCivsAlphabet;
import aoc.kingdoms.lukasz.menusEditor.GameCivsEdit;
import aoc.kingdoms.lukasz.menusEditor.GameCivsEditGroup;
import aoc.kingdoms.lukasz.menusEditor.GameCivsEditReligion;
import aoc.kingdoms.lukasz.menusEditor.ManageMods;
import aoc.kingdoms.lukasz.menusInGame.InGame;
import aoc.kingdoms.lukasz.menusInGame.InGame_AdvisorRecruit;
import aoc.kingdoms.lukasz.menusInGame.InGame_Armies;
import aoc.kingdoms.lukasz.menusInGame.InGame_ArmyCustomize;
import aoc.kingdoms.lukasz.menusInGame.InGame_Audio;
import aoc.kingdoms.lukasz.menusInGame.InGame_BattleTactics;
import aoc.kingdoms.lukasz.menusInGame.InGame_CivBonuses;
import aoc.kingdoms.lukasz.menusInGame.InGame_CivilizationAdvantages;
import aoc.kingdoms.lukasz.menusInGame.InGame_CivilizationAdvantages2;
import aoc.kingdoms.lukasz.menusInGame.InGame_ConvertReligion;
import aoc.kingdoms.lukasz.menusInGame.InGame_CurrentSituation;
import aoc.kingdoms.lukasz.menusInGame.InGame_Disband;
import aoc.kingdoms.lukasz.menusInGame.InGame_Encyclopedia;
import aoc.kingdoms.lukasz.menusInGame.InGame_Escape;
import aoc.kingdoms.lukasz.menusInGame.InGame_EscapeEmpty;
import aoc.kingdoms.lukasz.menusInGame.InGame_Event;
import aoc.kingdoms.lukasz.menusInGame.InGame_GameLost;
import aoc.kingdoms.lukasz.menusInGame.InGame_GeneralRecruit;
import aoc.kingdoms.lukasz.menusInGame.InGame_Generals;
import aoc.kingdoms.lukasz.menusInGame.InGame_HideUI;
import aoc.kingdoms.lukasz.menusInGame.InGame_Legacies;
import aoc.kingdoms.lukasz.menusInGame.InGame_LegaciesEmpty;
import aoc.kingdoms.lukasz.menusInGame.InGame_ListOfBuildings;
import aoc.kingdoms.lukasz.menusInGame.InGame_ListOfUnits;
import aoc.kingdoms.lukasz.menusInGame.InGame_Loan;
import aoc.kingdoms.lukasz.menusInGame.InGame_LoanRepay;
import aoc.kingdoms.lukasz.menusInGame.InGame_MapModes;
import aoc.kingdoms.lukasz.menusInGame.InGame_Messages;
import aoc.kingdoms.lukasz.menusInGame.InGame_MessagesWars;
import aoc.kingdoms.lukasz.menusInGame.InGame_MissionTree;
import aoc.kingdoms.lukasz.menusInGame.InGame_Missions;
import aoc.kingdoms.lukasz.menusInGame.InGame_MoveCapital_PopUp;
import aoc.kingdoms.lukasz.menusInGame.InGame_Notifications;
import aoc.kingdoms.lukasz.menusInGame.InGame_Ranking;
import aoc.kingdoms.lukasz.menusInGame.InGame_ReleaseAVassal;
import aoc.kingdoms.lukasz.menusInGame.InGame_ReorganizeUnits;
import aoc.kingdoms.lukasz.menusInGame.InGame_Revolutions;
import aoc.kingdoms.lukasz.menusInGame.InGame_SaveGame;
import aoc.kingdoms.lukasz.menusInGame.InGame_War;
import aoc.kingdoms.lukasz.menusInGame.InGame_Wonder;
import aoc.kingdoms.lukasz.menusInGame.InGame_Wonders;
import aoc.kingdoms.lukasz.menusInGame.AllianceSpecial.InGame_AllianceSpecial;
import aoc.kingdoms.lukasz.menusInGame.AllianceSpecial.InGame_AllianceSpecialReformHRE;
import aoc.kingdoms.lukasz.menusInGame.AtomicNukes.InGame_BuildAtomicBomb;
import aoc.kingdoms.lukasz.menusInGame.AtomicNukes.InGame_Nukes;
import aoc.kingdoms.lukasz.menusInGame.Battle.InGame_Battle;
import aoc.kingdoms.lukasz.menusInGame.Battle.InGame_BattleArmy;
import aoc.kingdoms.lukasz.menusInGame.Battle.InGame_BattleArmyDefenders;
import aoc.kingdoms.lukasz.menusInGame.Battle.InGame_BattleReport;
import aoc.kingdoms.lukasz.menusInGame.Battle.InGame_Battle_Full;
import aoc.kingdoms.lukasz.menusInGame.Battle.InGame_Battle_FullEmpty;
import aoc.kingdoms.lukasz.menusInGame.Battle.InGame_Battlefield;
import aoc.kingdoms.lukasz.menusInGame.Budget.InGame_Budget;
import aoc.kingdoms.lukasz.menusInGame.Budget.InGame_BudgetBalanceProvinces;
import aoc.kingdoms.lukasz.menusInGame.Budget.InGame_BudgetExpensesBuildingsMaintenance;
import aoc.kingdoms.lukasz.menusInGame.Budget.InGame_BudgetExpensesMaintenance;
import aoc.kingdoms.lukasz.menusInGame.Budget.InGame_BudgetIncomeBuildings;
import aoc.kingdoms.lukasz.menusInGame.Budget.InGame_BudgetIncomeEconomy;
import aoc.kingdoms.lukasz.menusInGame.Budget.InGame_BudgetIncomeProduction;
import aoc.kingdoms.lukasz.menusInGame.Budget.InGame_BudgetIncomeProvinces;
import aoc.kingdoms.lukasz.menusInGame.Budget.InGame_BudgetIncomeTaxation;
import aoc.kingdoms.lukasz.menusInGame.Buildings.InGame_BuildingsGroup;
import aoc.kingdoms.lukasz.menusInGame.Buildings.InGame_BuildingsGroupID;
import aoc.kingdoms.lukasz.menusInGame.Buildings.InGame_Destroy;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_CivRank;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_AggressiveExpansion;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_ArmiesRegiments;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_CapitalCity;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_Compare;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_Economy;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_Government;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_List;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_MilitaryAcademy;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_Population;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_Provinces;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_Religion;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ_UnlockedTechnologies;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions2;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Build;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Buildings2;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Core;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_DevelopInfrastructure;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Espionage;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_ExploitEconomy;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_GoldenAges;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Government;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_IncreaseGrowthRate;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_IncreaseManpower;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_IncreaseTaxEfficiency;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_InvestInEconomy;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Law;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Missions;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Multi;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Provinces;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Religion;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Stats;
import aoc.kingdoms.lukasz.menusInGame.Court.ChangeGovernmentReligion.InGame_ChangeIdeology;
import aoc.kingdoms.lukasz.menusInGame.Court.ChangeGovernmentReligion.InGame_ChangeIdeology2;
import aoc.kingdoms.lukasz.menusInGame.Court.ChangeGovernmentReligion.InGame_ChangeReligion;
import aoc.kingdoms.lukasz.menusInGame.Court.ChangeGovernmentReligion.InGame_ChangeReligion2;
import aoc.kingdoms.lukasz.menusInGame.Court.World.InGame_Court_WorldAlliances;
import aoc.kingdoms.lukasz.menusInGame.Court.World.InGame_Court_WorldCivs;
import aoc.kingdoms.lukasz.menusInGame.Court.World.InGame_Court_WorldDefensive;
import aoc.kingdoms.lukasz.menusInGame.Court.World.InGame_Court_WorldNonAggression;
import aoc.kingdoms.lukasz.menusInGame.Court.World.InGame_Court_WorldSearch;
import aoc.kingdoms.lukasz.menusInGame.Court.World.InGame_Court_WorldTruces;
import aoc.kingdoms.lukasz.menusInGame.Court.World.InGame_Court_WorldVassals;
import aoc.kingdoms.lukasz.menusInGame.Court.World.InGame_Court_WorldWars;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Alliance;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_AllianceList;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_CallAllies;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_DeclareWar;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_DefensivePact;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_DemandMilitaryAccess;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_FormCiv;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Guarantee;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Intervene;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_LiberateCivilization;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_NonAggression;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_OfferMilitaryAccess;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Rivals;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_RivalsList;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Rivals_End;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_SendGift;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_SendInsult;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_SendSpy;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_ShareTechnology;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageAlliance;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageCallToWar;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageDefensivePact;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageDemandsMilitaryAccess;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageGift;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageGuarantee;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageInsult;
import aoc.kingdoms.lukasz.menusInGame.DiplomacyMessage.InGame_MessageNonAggressionPact;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_Goods;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_GoodsEmpty;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_GoodsMarket;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_GoodsMarket_Resource;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_Goods_LargestProducers;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_Goods_Provinces;
import aoc.kingdoms.lukasz.menusInGame.Graph.InGame_GraphPopulation;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info_2;
import aoc.kingdoms.lukasz.menusInGame.Laws.InGame_LawReform;
import aoc.kingdoms.lukasz.menusInGame.Laws.InGame_Laws;
import aoc.kingdoms.lukasz.menusInGame.Peace.InGame_Peace;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmyTopBar;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmyUnits;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy_Invasion;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy_Move;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy_Regroup;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceBonuses;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceInfo;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceInfoBuildings;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceInfo_Army;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceSiege;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitArmy;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitArmy_NewArmy;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitArmy_NewArmy_Battlefield;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitMercenaries;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitSameType;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_Right;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightEconomy;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightGoods;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightGoodsProvinces;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightGovernment;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightInfrastructure;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightPopulation;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightProvinceIncome;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightQueue;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightReligion;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightTaxEfficiency;
import aoc.kingdoms.lukasz.menusInGame.Right.InGame_RightWonders;
import aoc.kingdoms.lukasz.menusInGame.Technology.InGame_TechnologyChoose;
import aoc.kingdoms.lukasz.menusInGame.Technology.InGame_TechnologyTree;
import aoc.kingdoms.lukasz.menusInGame.Technology.InGame_TechnologyTreeEmpty;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeCapital;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeMilitaryAcademy;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeMilitaryAcademyForGenerals;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeNuclearReactor;
import aoc.kingdoms.lukasz.menusInGame.Upgrade.InGame_UpgradeSupremeCourt;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapArmyPosition;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapContinents;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapContinentsList;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapEconomy;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapEconomy_Random;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapEdit;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapGeoRegions;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapGeoRegionsList;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapGrowthRate;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapGrowthRate_Random;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapLines;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapLinesWaves;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapOptimizationRegions;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapPortPosition;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapProvinceConnections;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapProvinceConnectionsList;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapProvinceNamePoints;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapResources;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapResourcesList;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapSeaProvinces;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapTerrainType;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapTerrainTypeList;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMap_CivSelect;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMap_FormableCiv;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMap_FormableCivClaimants;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMap_FormableCivFormable;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMap_FormableCivs;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMap_GenerateSuggestedCivilizations;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMap_PrintMap;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMaps;
import aoc.kingdoms.lukasz.menusMapEditor.EditorSelectProvinces;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioArmies;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioArmies_List;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioAssign;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioAssignCivs;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioAssignList;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioAssign_InGame;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioAssign_InGameCivs;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioAssign_InGameList;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioBuildings;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioBuildings_List;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioCivilizations;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioCivilizationsList;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioCores;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioCores_InProvinces;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioCores_List;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioCreateAlliance;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioCreateAllianceList;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioCreateAlliance_Alliances;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioCreateAlliance_AlliancesList;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioEconomy;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioEconomy_List;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioGeneratePreview;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioGovernment;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioGovernment_List;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioPopulation;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioPopulation_List;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioReligion;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioReligion_List;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioSettings;
import aoc.kingdoms.lukasz.menusScenarioEditor.Scenario_Calendar;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenariosList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioAlliances;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioAlliancesList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioDeclareWar;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioDeclareWarList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioDefensive;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioDefensiveList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioGuarantee;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioGuaranteeList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioMilitaryAccess;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioMilitaryAccessList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioNonAggression;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioNonAggressionList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioRelations;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioRelationsList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioTruces;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioTrucesList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioVassals;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioVassalsList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Technology.ScenarioTechnologyCivs;
import aoc.kingdoms.lukasz.menusScenarioEditor.Technology.ScenarioTechnologyCivs_List;
import aoc.kingdoms.lukasz.menusScenarioEditor.Technology.ScenarioTechnology_Default;
import aoc.kingdoms.lukasz.menusScenarioEditor.Technology.ScenarioTechnology_Default_List;
import aoc.kingdoms.lukasz.menusScenarioEditor.Wasteland.ScenarioWasteland;
import aoc.kingdoms.lukasz.menusScenarioEditor.Wasteland.ScenarioWastelandContinents;
import aoc.kingdoms.lukasz.menusScenarioEditor.Wasteland.ScenarioWastelandContinentsList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Wasteland.ScenarioWastelandOptions;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.utilities.FPS;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    public List<List<Menu>> menus = new ArrayList();
    public List<List<Integer>> orderOfMenu = new ArrayList();
    public Menu dialogMenu = new Dialog();
    public List<Toast> lToast = new ArrayList();
    public int iToastSize = 0;
    public int viewID = 0;
    public ColorPicker colorPicker;
    public static SparksAnimation sparksAnimation = new SparksAnimation();
    public static SparksAnimation sparksAnimationHover = new SparksAnimation();
    public static SparksAnimation sparksAnimationSidebar = new SparksAnimation();
    public static SparksAnimation sparksAnimationSidebarActive = new SparksAnimation();
    public int INIT_GAME_MENU = -1;
    public int INIT_GAME_MENU_SELECT_MAP = -1;
    public int INIT_GAME_MENU_LANGUAGE = -1;
    public int MAINMENU = -1;
    public int MAINMENU_STATS = -1;
    public int LOAD_GAMES_LIST = -1;
    public int CLOUDS_MENU = -1;
    public int IN_GAME = -1;
    public int IN_GAME_MAPMODES = -1;
    public int IN_GAME_PROVINCE_ARMY = -1;
    public int IN_GAME_PROVINCE_ARMY_UNITS = -1;
    public int IN_GAME_PROVINCE_ARMY_TOP_BAR = -1;
    public int IN_GAME_PROVINCE_ARMY_MOVE = -1;
    public int IN_GAME_ESCAPE = -1;
    public int IN_GAME_ESCAPE_EMPTY = -1;
    public int IN_GAME_RECRUIT_ARMY = -1;
    public int IN_GAME_RECRUIT_ARMY_BATTLEFIELD = -1;
    public int IN_GAME_GENERALS = -1;
    public int IN_GAME_GENERAL_RECRUIT = -1;
    public int IN_GAME_BUILDINGS = -1;
    public int IN_GAME_BUILDINGS_GROUP_0 = -1;
    public int IN_GAME_BUILDINGS_GROUP_1 = -1;
    public int IN_GAME_BUILDINGS_GROUP_2 = -1;
    public int IN_GAME_BUILDINGS_GROUP_3 = -1;
    public int IN_GAME_ARMIES = -1;
    public int IN_GAME_CIV = -1;
    public int IN_GAME_COURT = -1;
    public int IN_GAME_COURT_OPTIONS = -1;
    public int IN_GAME_COURT_OPTIONS2 = -1;
    public int IN_GAME_RIGHT = -1;
    public int IN_GAME_RIGHT_QUEUE = -1;
    public int IN_GAME_NOTIFICATION = -1;
    public int IN_GAME_ADVISOR_RECRUIT = -1;
    public int IN_GAME_CHOOSE_TECHNOLOGY = -1;
    public int IN_GAME_NUKES = -1;
    public int IN_GAME_TECHNOLOGY_TREE = -1;
    public int IN_GAME_TECHNOLOGY_TREE_EMPTY = -1;
    public int IN_GAME_BATTLE_FULL = -1;
    public int IN_GAME_BATTLE_FULL_EMPTY = -1;
    public int IN_GAME_BUDGET = -1;
    public int IN_GAME_TAKE_LOAN = -1;
    public int IN_GAME_TAKE_LOAN_REPAY = -1;
    public int IN_GAME_EVENT = -1;
    public int IN_GAME_CURRENT_SITUATION = -1;
    public int IN_GAME_MESSAGES = -1;
    public int IN_GAME_WARS = -1;
    public int IN_GAME_CONSOLE = -1;
    public int IN_GAME_SAVE_GAME = -1;
    public int IN_GAME_POP_UP = -1;
    public int IN_GAME_CIV_BONUSES = -1;
    public int IN_GAME_PROVINCE_BONUSES = -1;
    public int IN_GAME_GOODS_CIV = -1;
    public int IN_GAME_GOODS = -1;
    public int IN_GAME_GOODS_EMPTY = -1;
    public int IN_GAME_WONDER = -1;
    public int IN_GAME_LEGACIES = -1;
    public int IN_GAME_LEGACIES_EMPTY = -1;
    public int IN_GAME_LEGACIES_INFO = -1;
    public int IN_GAME_HIDE_UI = -1;
    public int IN_GAME_INFO = -1;
    public int IN_GAME_PROVINCE_INFO = -1;
    public int IN_GAME_PROVINCE_INFO_BUILDINGS = -1;
    public int IN_GAME_PROVINCE_INFO_ARMY = -1;
    public int IN_GAME_REORGANIZE_UNITS = -1;
    public int IN_GAME_DISBAND_UNITS = -1;
    public int IN_GAME_BATTLE = -1;
    public int IN_GAME_BATTLE_ARMY = -1;
    public int IN_GAME_BATTLE_ARMY_DEFENDERS = -1;
    public int IN_GAME_SIEGE = -1;
    public int IN_GAME_WAR = -1;
    public int IN_GAME_PEACE = -1;
    public int NEW_GAME = -1;
    public int NEW_GAME_CIV = -1;
    public int NEW_GAME_SETTINGS = -1;
    public int NEW_GAME_FLAGS = -1;
    public int SCENARIOS = -1;
    public int SCENARIOS_CAMPAIGN = -1;
    public int MULTIPLAYER = -1;
    public int MULTIPLAYER_LOBBY = -1;
    public int MULTIPLAYER_LOBBY_CIV = -1;
    public int MULTIPLAYER_CREATE_LOBBY = -1;
    public int MULTIPLAYER_PASSWORD = -1;
    public int GAME_LOST = -1;
    public int EDITOR = -1;
    public int EDITOR_GAMECIVS = -1;
    public int EDITOR_GAMECIVS_EDIT = -1;
    public int EDITOR_GAMECIVS_EDIT_RELIGION = -1;
    public int EDITOR_GAMECIVS_EDIT_GROUP = -1;
    public int CREATE_CIV = -1;
    public int CREATE_CIV_RELIGION = -1;
    public int CREATE_CIV_GROUP = -1;
    public int CREATE_CIV_FLAG = -1;
    public int EDITOR_MAPS = -1;
    public int EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS = -1;
    public int EDITOR_MAPS_EDIT = -1;
    public int EDITOR_MAPS_EDIT_SEA_PROVINCES = -1;
    public int EDITOR_MAPS_EDIT_ARMY_POSITION = -1;
    public int EDITOR_MAPS_EDIT_PORT_POSITION = -1;
    public int EDITOR_MAPS_EDIT_TERRAIN = -1;
    public int EDITOR_MAPS_EDIT_RESOURCE = -1;
    public int EDITOR_SELECT_PROVINCES = -1;
    public int EDITOR_MAPS_EDIT_CONTINENTS = -1;
    public int EDITOR_MAPS_EDIT_GEO_REGION = -1;
    public int EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS = -1;
    public int EDITOR_MAPS_EDIT_GROWTH_RATE = -1;
    public int EDITOR_MAPS_EDIT_FORMABLE_CIVS = -1;
    public int EDITOR_MAPS_EDIT_FORMABLE_CIV = -1;
    public int EDITOR_MAPS_EDIT_CIV_SELECT = -1;
    public int EDITOR_MAPS_EDIT_ECONOMY = -1;
    public int EDITOR_MAPS_EDIT_PROVINCE_NAMES = -1;
    public int EDITOR_MAPS_EDIT_LINES = -1;
    public int EDITOR_MAPS_EDIT_WAVES = -1;
    public int EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS = -1;
    public int PRINT_A_MAP = -1;
    public int SETTINGS = -1;
    public int SETTINGS_RESOLUTION = -1;
    public int SETTINGS_UI = -1;
    public int EDITOR_SCENARIOS_LIST = -1;
    public int SCENARIO_WASTELAND_CONTINENTS = -1;
    public int SCENARIO_WASTELAND = -1;
    public int SCENARIO_CIVILIZATIONS = -1;
    public int SCENARIO_CIVILIZATIONS_LIST = -1;
    public int SCENARIO_ASSIGN = -1;
    public int SCENARIO_ASSIGN_CIVS = -1;
    public int SCENARIO_TECHNOLOGIES = -1;
    public int SCENARIO_TECHNOLOGIES_CIVS = -1;
    public int SCENARIO_SETTINGS = -1;
    public int SCENARIO_CALENDAR = -1;
    public int SCENARIO_ARMIES = -1;
    public int SCENARIO_POPULATION = -1;
    public int SCENARIO_ECONOMY = -1;
    public int SCENARIO_GOVERNMENT = -1;
    public int SCENARIO_BUILDINGS = -1;
    public int SCENARIO_CORES = -1;
    public int SCENARIO_CORES_LIST = -1;
    public int SCENARIO_CORES_LIST_IN_PROVINCE = -1;
    public int SCENARIO_RELIGION = -1;
    public int SCENARIO_RELIGION_LIST = -1;
    public int SCENARIO_ASSIGN_IN_GAME = -1;
    public int SCENARIO_ASSIGN_IN_GAME_CIVS = -1;
    public int SCENARIO_PREVIEW = -1;
    public int SCENARIO_RELATIONS = -1;
    public int SCENARIO_VASSALS = -1;
    public int SCENARIO_TRUCES = -1;
    public int SCENARIO_DECLARE_WAR = -1;
    public int SCENARIO_ALLIANCES = -1;
    public int SCENARIO_NON_AGGRESSION = -1;
    public int SCENARIO_GUARANTEE = -1;
    public int SCENARIO_DEFENSIVE = -1;
    public int SCENARIO_MILITARY_ACCESS = -1;
    public int SCENARIO_CREATE_ALLIANCE = -1;
    public int SCENARIO_CREATE_ALLIANCE_EDIT = -1;
    public int WORKSHOP = -1;
    public int MANAGE_MODS = -1;
    public int LOAD_STUFF = -1;
    private int activeSliderMenuID = -1;
    private int activeMenuElementID = -1;
    private boolean menu_MoveInnerElements = false;
    private int iSliderMenuStartPosY = 0;
    private int iSliderMenuActionDownPosY = 0;
    private boolean updateSliderMenuPosY = false;
    private int iSliderMenuStartPosX = 0;
    private int iSliderMenuActionDownPosX = 0;
    private boolean updateSliderMenuPosX = false;
    private boolean menu_MoveByTitleMode = false;
    private boolean menuResizeMode = false;
    private boolean menuResizeLEFT = false;
    private boolean closeMenuMode = false;
    private boolean colorPickerMode = false;
    private boolean pieChartMode = false;
    private boolean textScrollableMode = false;
    private boolean sliderMode = false;
    public boolean graphVertical_ScrollMode_Y = false;
    public boolean graphVertical_ScrollMode_X = false;
    private int fromViewID = -1;
    private int toViewID = -1;
    private boolean backAnimation = false;
    private float animationChangeViewPosX = 0.0F;
    private int animationStepID = 0;
    public static boolean orderOfMenuInGame = false;
    public int ANIMATION_TIME = 1000;
    public long TOAST_TIME = 0L;
    public int WAR_ANIMATION_TIME = 3000;
    public long WAR_TIME = 0L;
    public static List<ClickAnimation> clickAnimations = new ArrayList();
    public static int clickAnimationsSize = 0;
    public static boolean mapEditorDrawProvinces = true;
    public boolean inCreateNewArmy = false;
    public static boolean currentSituationMode = false;
    public static int IN_GAME_POP_UP_MENU_ID = 0;
    public static int IN_GAME_POP_UP_MENU_FORM_CIV = 39;
    public static int IN_GAME_POP_UP_MENU_ID_DECLARE_WAR = 12;

    //ANALYTICALENGINE START
    public final int getNumberOfMenus () {
        //Declare local instance variables
        List<Integer> current_order = (List<Integer>) this.orderOfMenu.get(this.viewID);

        //Return statement
        return (current_order != null) ?
                current_order.size() : 0;
    }

    public final void setOrderOfMenu (int arg0_menu_id, int arg1_z_index) {
        //Convert from parameters
        int menu_id = arg0_menu_id;
        int z_index = arg1_z_index;

        //Declare local instance variables
        List<Integer> current_order = (List<Integer>) this.orderOfMenu.get(this.viewID);
        int inverted_z_index = current_order.size() - z_index;
        boolean menu_exists = current_order.contains(menu_id);

        orderOfMenuInGame = false;

        //Try/catch for error wrapping
        try {
            //If menu_exists, remove the menu_id from its current position and place it at the specified z_index
            if (menu_exists)
                current_order.remove((Integer) menu_id);

            //Clamp inverted_z_index to valid bounds
            if (inverted_z_index < 0) inverted_z_index = 0; //Place at start
            if (inverted_z_index >= current_order.size()) inverted_z_index = current_order.size() - 1; //Place at end
            current_order.add(inverted_z_index, menu_id);

            //Update .orderOfMenu for the current view
            this.orderOfMenu.set(this.viewID, current_order);
        } catch (Exception e) {
            CFG.exceptionStack(e);
        }
    }

    //ANALYTICALENGINE END

    public final int getViewID(View eView) {
        try {
            switch (eView) {
                case INIT_GAME_MENU:
                    if (this.INIT_GAME_MENU == -1) {
                        this.INIT_GAME_MENU = this.addMenu(new InitGame());
                    }

                    return this.INIT_GAME_MENU;
                case INIT_GAME_MENU_SELECT_MAP:
                    if (this.INIT_GAME_MENU_SELECT_MAP == -1) {
                        if (CFG.isDesktop()) {
                            this.INIT_GAME_MENU_SELECT_MAP = this.addMenu(new Init_SelectMap());
                        } else {
                            this.INIT_GAME_MENU_SELECT_MAP = this.addMenu(new Init_SelectMap2());
                        }
                    }

                    return this.INIT_GAME_MENU_SELECT_MAP;
                case INIT_GAME_MENU_LANGUAGE:
                    if (this.INIT_GAME_MENU_LANGUAGE == -1) {
                        this.INIT_GAME_MENU_LANGUAGE = this.addMenu(new Init_SelectLanguage());
                    }

                    return this.INIT_GAME_MENU_LANGUAGE;
                case LOAD_SCENARIO:
                    if (this.LOAD_STUFF == -1) {
                        this.LOAD_STUFF = this.addMenu(new Menu_LoadScenario());
                    } else {
                        ((List)this.menus.get(this.LOAD_STUFF)).set(0, new Menu_LoadScenario());
                    }

                    return this.LOAD_STUFF;
                case LOAD_SAVE_SCENARIO:
                    if (this.LOAD_STUFF == -1) {
                        this.LOAD_STUFF = this.addMenu(new Menu_LoadSaveScenario());
                    } else {
                        ((List)this.menus.get(this.LOAD_STUFF)).set(0, new Menu_LoadSaveScenario());
                    }

                    return this.LOAD_STUFF;
                case LOAD_SAVE_GAME:
                    if (this.LOAD_STUFF == -1) {
                        this.LOAD_STUFF = this.addMenu(new Menu_LoadSavingGame());
                    } else {
                        ((List)this.menus.get(this.LOAD_STUFF)).set(0, new Menu_LoadSavingGame());
                    }

                    return this.LOAD_STUFF;
                case LOAD_SAVED_GAME:
                    if (this.LOAD_STUFF == -1) {
                        this.LOAD_STUFF = this.addMenu(new Menu_LoadSavedGame());
                    } else {
                        ((List)this.menus.get(this.LOAD_STUFF)).set(0, new Menu_LoadSavedGame());
                    }

                    return this.LOAD_STUFF;
                case LOAD_WORKSHOP_PUBLISH:
                    if (this.LOAD_STUFF == -1) {
                        this.LOAD_STUFF = this.addMenu(new Menu_Load_Workshop());
                    } else {
                        ((List)this.menus.get(this.LOAD_STUFF)).set(0, new Menu_Load_Workshop());
                    }

                    return this.LOAD_STUFF;
                case MAINMENU:
                    if (this.MAINMENU == -1) {
                        this.MAINMENU = this.addMenu(new AnalyticalEngine_MainMenu());
                    } else {
                        ((Menu)((List)this.menus.get(this.MAINMENU)).get(0)).setVisible(false);
                        ((List)this.menus.get(this.MAINMENU)).set(0, new AnalyticalEngine_MainMenu());
                    }

                    return this.MAINMENU;
                case MAINMENU_STATS:
                    if (this.MAINMENU_STATS == -1) {
                        this.MAINMENU_STATS = this.addMenu(new MainMenu_Stats());
                        this.addNextMenuToView(this.MAINMENU_STATS, new MainMenu_StatsEmpty());
                    } else {
                        ((Menu)((List)this.menus.get(this.MAINMENU_STATS)).get(0)).setVisible(false);
                        ((List)this.menus.get(this.MAINMENU_STATS)).set(0, new MainMenu_Stats());
                    }

                    return this.MAINMENU_STATS;
                case LOAD_GAMES_LIST:
                    if (this.LOAD_GAMES_LIST == -1) {
                        this.LOAD_GAMES_LIST = this.addMenu(new Menu_LoadGames_List());
                    } else {
                        ((Menu)((List)this.menus.get(this.LOAD_GAMES_LIST)).get(0)).setVisible(false);
                        ((List)this.menus.get(this.LOAD_GAMES_LIST)).set(0, new Menu_LoadGames_List());
                    }

                    return this.LOAD_GAMES_LIST;
                case MULTIPLAYER:
                    if (this.MULTIPLAYER == -1) {
                        this.MULTIPLAYER = this.addMenu(new MultiGames());
                    } else {
                        ((List)this.menus.get(this.MULTIPLAYER)).set(0, new MultiGames());
                    }

                    return this.MULTIPLAYER;
                case MULTIPLAYER_CREATE_LOBBY:
                    if (this.MULTIPLAYER_CREATE_LOBBY == -1) {
                        this.MULTIPLAYER_CREATE_LOBBY = this.addMenu(new MultiLobbyCreate(true));
                    } else {
                        ((List)this.menus.get(this.MULTIPLAYER_CREATE_LOBBY)).set(0, new MultiLobbyCreate(true));
                    }

                    return this.MULTIPLAYER_CREATE_LOBBY;
                case MULTIPLAYER_LOBBY:
                    if (this.MULTIPLAYER_LOBBY == -1) {
                        this.MULTIPLAYER_LOBBY = this.addMenu(new MultiLobby());
                        this.MULTIPLAYER_LOBBY_CIV = this.addNextMenuToView(this.MULTIPLAYER_LOBBY, new NewGameCiv());
                    } else {
                        ((List)this.menus.get(this.MULTIPLAYER_LOBBY)).set(0, new MultiLobby());
                        ((List)this.menus.get(this.MULTIPLAYER_LOBBY)).set(this.MULTIPLAYER_LOBBY_CIV, new NewGameCiv());
                    }

                    return this.MULTIPLAYER_LOBBY;
                case MULTIPLAYER_PASSWORD:
                    if (this.MULTIPLAYER_PASSWORD == -1) {
                        this.MULTIPLAYER_PASSWORD = this.addMenu(new MultiPassword());
                    } else {
                        ((List)this.menus.get(this.MULTIPLAYER_PASSWORD)).set(0, new MultiPassword());
                    }

                    return this.MULTIPLAYER_PASSWORD;
                case NEW_GAME:
                    if (this.NEW_GAME == -1) {
                        this.NEW_GAME = this.addMenu(new NewGame());
                        this.NEW_GAME_CIV = this.addNextMenuToView(this.NEW_GAME, new NewGameCiv());
                        this.NEW_GAME_SETTINGS = this.addNextMenuToView(this.NEW_GAME, new NewGame_Settings());
                        this.NEW_GAME_FLAGS = this.addNextMenuToView(this.NEW_GAME, new NewGameFlags());
                    } else {
                        NewGame.lTime = CFG.currentTimeMillis;
                        this.rebuildNewGameCiv();
                        ((List)this.menus.get(this.NEW_GAME)).set(this.NEW_GAME_FLAGS, new NewGameFlags());
                    }

                    return this.NEW_GAME;
                case GAME_LOST:
                    if (this.GAME_LOST == -1) {
                        this.GAME_LOST = this.addMenu(new InGame_GameLost());
                    } else {
                        ((List)this.menus.get(this.GAME_LOST)).set(0, new InGame_GameLost());
                    }

                    return this.GAME_LOST;
                case CLOUDS_MENU:
                    if (this.CLOUDS_MENU == -1) {
                        this.CLOUDS_MENU = this.addMenu(new CloudsMenu());
                    } else {
                        ((List)this.menus.get(this.CLOUDS_MENU)).set(0, new CloudsMenu());
                    }

                    return this.CLOUDS_MENU;
                case WORKSHOP:
                    if (this.WORKSHOP == -1) {
                        this.WORKSHOP = this.addMenu(new WorkshopMenu());
                    } else {
                        ((List)this.menus.get(this.WORKSHOP)).set(0, new WorkshopMenu());
                    }

                    return this.WORKSHOP;
                case MANAGE_MODS:
                    if (this.MANAGE_MODS == -1) {
                        this.MANAGE_MODS = this.addMenu(new ManageMods());
                    } else {
                        ((List)this.menus.get(this.MANAGE_MODS)).set(0, new ManageMods());
                    }

                    return this.MANAGE_MODS;
                case SCENARIOS:
                    if (this.SCENARIOS == -1) {
                        this.SCENARIOS = this.addMenu(new Scenarios());
                        if (Scenarios.SCENARIOS_DEFAULT_MODE) {
                            this.addNextMenuToView(this.SCENARIOS, new ScenariosList_NewGame());
                        } else {
                            this.addNextMenuToView(this.SCENARIOS, new ScenariosList_NewGame_Buttons());
                        }
                    } else {
                        ((List)this.menus.get(this.SCENARIOS)).set(0, new Scenarios());
                        if (Scenarios.SCENARIOS_DEFAULT_MODE) {
                            ((List)this.menus.get(this.SCENARIOS)).set(1, new ScenariosList_NewGame());
                        } else {
                            ((List)this.menus.get(this.SCENARIOS)).set(1, new ScenariosList_NewGame_Buttons());
                        }
                    }

                    return this.SCENARIOS;
                case SCENARIOS_CAMPAIGN:
                    if (this.SCENARIOS_CAMPAIGN == -1) {
                        this.SCENARIOS_CAMPAIGN = this.addMenu(new Scenarios_Campaign_Vertical());
                        if (Scenarios.SCENARIOS_DEFAULT_MODE) {
                            this.addNextMenuToView(this.SCENARIOS_CAMPAIGN, new Scenarios_Campaign_VerticalScenarios());
                        } else {
                            this.addNextMenuToView(this.SCENARIOS_CAMPAIGN, new Scenarios_Campaign_Buttons());
                        }
                    } else {
                        ((List)this.menus.get(this.SCENARIOS_CAMPAIGN)).set(0, new Scenarios_Campaign_Vertical());
                        if (Scenarios.SCENARIOS_DEFAULT_MODE) {
                            ((List)this.menus.get(this.SCENARIOS_CAMPAIGN)).set(1, new Scenarios_Campaign_VerticalScenarios());
                        } else {
                            ((List)this.menus.get(this.SCENARIOS_CAMPAIGN)).set(1, new Scenarios_Campaign_Buttons());
                        }
                    }

                    return this.SCENARIOS_CAMPAIGN;
                case SETTINGS:
                    if (this.SETTINGS == -1) {
                        this.SETTINGS = this.addMenu(new Settings_Menu());
                        this.addNextMenuToView(this.SETTINGS, new EmptyMenu());
                    } else {
                        ((List)this.menus.get(this.SETTINGS)).set(0, new Settings_Menu());
                        ((List)this.menus.get(this.SETTINGS)).set(1, new EmptyMenu());
                    }

                    return this.SETTINGS;
                case SETTINGS_RESOLUTION:
                    if (this.SETTINGS_RESOLUTION == -1) {
                        this.SETTINGS_RESOLUTION = this.addMenu(new Settings_Resolution());
                        this.addNextMenuToView(this.SETTINGS_RESOLUTION, new EmptyMenu());
                    } else {
                        ((List)this.menus.get(this.SETTINGS_RESOLUTION)).set(0, new Settings_Resolution());
                        ((List)this.menus.get(this.SETTINGS_RESOLUTION)).set(1, new EmptyMenu());
                    }

                    return this.SETTINGS_RESOLUTION;
                case SETTINGS_UI:
                    if (this.SETTINGS_UI == -1) {
                        this.SETTINGS_UI = this.addMenu(new Settings_UIScale());
                        this.addNextMenuToView(this.SETTINGS_UI, new EmptyMenu());
                    } else {
                        ((List)this.menus.get(this.SETTINGS_UI)).set(0, new Settings_UIScale());
                        ((List)this.menus.get(this.SETTINGS_UI)).set(1, new EmptyMenu());
                    }

                    return this.SETTINGS_UI;
                case IN_GAME_LEGACIES:
                    if (this.IN_GAME_LEGACIES == -1) {
                        this.IN_GAME_LEGACIES = this.addMenu(new InGame_Legacies());
                        this.IN_GAME_LEGACIES_EMPTY = this.addNextMenuToView(this.IN_GAME_LEGACIES, new InGame_LegaciesEmpty());
                        this.IN_GAME_LEGACIES_INFO = this.addNextMenuToView(this.IN_GAME_LEGACIES, new EmptyMenu());
                    } else {
                        this.rebuildInGame_Legacies();
                    }

                    return this.IN_GAME_LEGACIES;
                case IN_GAME_HIDE_UI:
                    if (this.IN_GAME_HIDE_UI == -1) {
                        this.IN_GAME_HIDE_UI = this.addMenu(new InGame_HideUI());
                    } else {
                        ((List)this.menus.get(this.IN_GAME_HIDE_UI)).set(0, new InGame_HideUI());
                    }

                    return this.IN_GAME_HIDE_UI;
                case IN_GAME:
                    if (this.IN_GAME == -1) {
                        this.IN_GAME = this.addMenu(new InGame());
                        this.IN_GAME_MAPMODES = this.addNextMenuToView(this.IN_GAME, new InGame_MapModes());
                        this.IN_GAME_CIV_BONUSES = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_ARMY = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_ARMY_UNITS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_ARMY_TOP_BAR = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_ARMY_MOVE = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_ESCAPE = this.addNextMenuToView(this.IN_GAME, new InGame_Escape());
                        this.IN_GAME_ESCAPE_EMPTY = this.addNextMenuToView(this.IN_GAME, new InGame_EscapeEmpty());
                        this.IN_GAME_RECRUIT_ARMY = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BUILDINGS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BUILDINGS_GROUP_0 = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BUILDINGS_GROUP_1 = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BUILDINGS_GROUP_2 = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BUILDINGS_GROUP_3 = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_GOODS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_GOODS_EMPTY = this.addNextMenuToView(this.IN_GAME, new InGame_GoodsEmpty());
                        this.IN_GAME_GOODS_CIV = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_WONDER = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_INFO = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_INFO = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_INFO_BUILDINGS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PROVINCE_INFO_ARMY = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_REORGANIZE_UNITS = this.addNextMenuToView(this.IN_GAME, new InGame_ReorganizeUnits());
                        this.IN_GAME_DISBAND_UNITS = this.addNextMenuToView(this.IN_GAME, new InGame_Disband());
                        this.IN_GAME_BATTLE = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BATTLE_ARMY = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BATTLE_ARMY_DEFENDERS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_SIEGE = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_WAR = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_PEACE = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_GENERALS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_GENERAL_RECRUIT = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_ARMIES = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_RIGHT = this.addNextMenuToView(this.IN_GAME, new InGame_Right());
                        this.IN_GAME_RIGHT_QUEUE = this.addNextMenuToView(this.IN_GAME, new InGame_RightQueue());
                        this.IN_GAME_NOTIFICATION = this.addNextMenuToView(this.IN_GAME, new InGame_Notifications());
                        this.IN_GAME_PROVINCE_BONUSES = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_CIV = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_COURT = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_COURT_OPTIONS = this.addNextMenuToView(this.IN_GAME, new InGame_CourtOptions());
                        this.IN_GAME_COURT_OPTIONS2 = this.addNextMenuToView(this.IN_GAME, new InGame_CourtOptions2());
                        this.IN_GAME_ADVISOR_RECRUIT = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_CHOOSE_TECHNOLOGY = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_TECHNOLOGY_TREE = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_TECHNOLOGY_TREE_EMPTY = this.addNextMenuToView(this.IN_GAME, new InGame_TechnologyTreeEmpty());
                        this.IN_GAME_BATTLE_FULL = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BATTLE_FULL_EMPTY = this.addNextMenuToView(this.IN_GAME, new InGame_Battle_FullEmpty());
                        this.IN_GAME_NUKES = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_TAKE_LOAN = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_TAKE_LOAN_REPAY = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_CONSOLE = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_SAVE_GAME = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_POP_UP = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_BUDGET = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_EVENT = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_CURRENT_SITUATION = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_MESSAGES = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                        this.IN_GAME_WARS = this.addNextMenuToView(this.IN_GAME, new EmptyMenu());
                    } else {
                        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT_OPTIONS2, new InGame_CourtOptions2());
                    }

                    InGame.updateDrawOver();
                    return this.IN_GAME;
                case EDITOR:
                    if (this.EDITOR == -1) {
                        this.EDITOR = this.addMenu(new Editor());
                    }

                    return this.EDITOR;
                case EDITOR_GAMECIVS:
                    if (this.EDITOR_GAMECIVS == -1) {
                        this.EDITOR_GAMECIVS = this.addMenu(new GameCivs());
                        this.addNextMenuToView(this.EDITOR_GAMECIVS, new GameCivsAlphabet());
                    } else {
                        ((Menu)((List)this.menus.get(this.EDITOR_GAMECIVS)).get(0)).setVisible(false);
                        ((List)this.menus.get(this.EDITOR_GAMECIVS)).set(0, new GameCivs());
                        ((List)this.menus.get(this.EDITOR_GAMECIVS)).set(1, new GameCivsAlphabet());
                    }

                    return this.EDITOR_GAMECIVS;
                case CREATE_CIV:
                    if (this.CREATE_CIV == -1) {
                        this.CREATE_CIV = this.addMenu(new CreateCiv());
                        this.CREATE_CIV_RELIGION = this.addNextMenuToView(this.CREATE_CIV, new CreateCivReligion());
                        this.CREATE_CIV_GROUP = this.addNextMenuToView(this.CREATE_CIV, new CreateCivGroup());
                        this.CREATE_CIV_FLAG = this.addNextMenuToView(this.CREATE_CIV, new CreateCiv_Flag());
                    } else {
                        ((List)this.menus.get(this.CREATE_CIV)).set(0, new CreateCiv());
                        ((List)this.menus.get(this.CREATE_CIV)).set(this.CREATE_CIV_RELIGION, new CreateCivReligion());
                        ((List)this.menus.get(this.CREATE_CIV)).set(this.CREATE_CIV_GROUP, new CreateCivGroup());
                        ((List)this.menus.get(this.CREATE_CIV)).set(this.CREATE_CIV_FLAG, new CreateCiv_Flag());
                    }

                    return this.CREATE_CIV;
                case EDITOR_GAMECIVS_EDIT:
                    if (this.EDITOR_GAMECIVS_EDIT == -1) {
                        this.EDITOR_GAMECIVS_EDIT = this.addMenu(new GameCivsEdit());
                        this.EDITOR_GAMECIVS_EDIT_RELIGION = this.addNextMenuToView(this.EDITOR_GAMECIVS_EDIT, new GameCivsEditReligion());
                        this.EDITOR_GAMECIVS_EDIT_GROUP = this.addNextMenuToView(this.EDITOR_GAMECIVS_EDIT, new GameCivsEditGroup());
                    } else {
                        ((List)this.menus.get(this.EDITOR_GAMECIVS_EDIT)).set(0, new GameCivsEdit());
                    }

                    return this.EDITOR_GAMECIVS_EDIT;
                case EDITOR_MAPS:
                    if (this.EDITOR_MAPS == -1) {
                        this.EDITOR_MAPS = this.addMenu(new EditorMaps());
                    }

                    return this.EDITOR_MAPS;
                case EDITOR_MAPS_EDIT:
                    if (this.EDITOR_MAPS_EDIT == -1) {
                        this.EDITOR_MAPS_EDIT = this.addMenu(new EditorMapEdit());
                    }

                    return this.EDITOR_MAPS_EDIT;
                case EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS:
                    if (this.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS == -1) {
                        this.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS = this.addMenu(new EditorMapProvinceConnections());
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS, new EditorMapProvinceConnectionsList());
                    }

                    return this.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS;
                case EDITOR_MAPS_EDIT_GROWTH_RATE:
                    if (this.EDITOR_MAPS_EDIT_GROWTH_RATE == -1) {
                        this.EDITOR_MAPS_EDIT_GROWTH_RATE = this.addMenu(new EditorMapGrowthRate());
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_GROWTH_RATE, new EditorMapGrowthRate_Random());
                    }

                    return this.EDITOR_MAPS_EDIT_GROWTH_RATE;
                case EDITOR_MAPS_EDIT_FORMABLE_CIVS:
                    if (this.EDITOR_MAPS_EDIT_FORMABLE_CIVS == -1) {
                        this.EDITOR_MAPS_EDIT_FORMABLE_CIVS = this.addMenu(new EditorMap_FormableCivs());
                    } else {
                        ((List)this.menus.get(this.EDITOR_MAPS_EDIT_FORMABLE_CIVS)).set(0, new EditorMap_FormableCivs());
                    }

                    return this.EDITOR_MAPS_EDIT_FORMABLE_CIVS;
                case EDITOR_MAPS_EDIT_FORMABLE_CIV:
                    if (this.EDITOR_MAPS_EDIT_FORMABLE_CIV == -1) {
                        this.EDITOR_MAPS_EDIT_FORMABLE_CIV = this.addMenu(new EditorMap_FormableCiv());
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_FORMABLE_CIV, new EditorMap_FormableCivClaimants());
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_FORMABLE_CIV, new EditorMap_FormableCivFormable());
                    } else {
                        ((List)this.menus.get(this.EDITOR_MAPS_EDIT_FORMABLE_CIV)).set(0, new EditorMap_FormableCiv());
                        ((List)this.menus.get(this.EDITOR_MAPS_EDIT_FORMABLE_CIV)).set(1, new EditorMap_FormableCivClaimants());
                        ((List)this.menus.get(this.EDITOR_MAPS_EDIT_FORMABLE_CIV)).set(2, new EditorMap_FormableCivFormable());
                    }

                    return this.EDITOR_MAPS_EDIT_FORMABLE_CIV;
                case EDITOR_MAPS_EDIT_CIV_SELECT:
                    if (this.EDITOR_MAPS_EDIT_CIV_SELECT == -1) {
                        this.EDITOR_MAPS_EDIT_CIV_SELECT = this.addMenu(new EditorMap_CivSelect());
                    } else {
                        ((List)this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT)).set(0, new EditorMap_CivSelect());
                    }

                    return this.EDITOR_MAPS_EDIT_CIV_SELECT;
                case EDITOR_MAPS_EDIT_ECONOMY:
                    if (this.EDITOR_MAPS_EDIT_ECONOMY == -1) {
                        this.EDITOR_MAPS_EDIT_ECONOMY = this.addMenu(new EditorMapEconomy());
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_ECONOMY, new EditorMapEconomy_Random());
                    }

                    return this.EDITOR_MAPS_EDIT_ECONOMY;
                case EDITOR_MAPS_EDIT_SEA_PROVINCES:
                    if (this.EDITOR_MAPS_EDIT_SEA_PROVINCES == -1) {
                        this.EDITOR_MAPS_EDIT_SEA_PROVINCES = this.addMenu(new EditorMapSeaProvinces());
                    }

                    return this.EDITOR_MAPS_EDIT_SEA_PROVINCES;
                case EDITOR_MAPS_EDIT_ARMY_POSITION:
                    if (this.EDITOR_MAPS_EDIT_ARMY_POSITION == -1) {
                        this.EDITOR_MAPS_EDIT_ARMY_POSITION = this.addMenu(new EditorMapArmyPosition());
                    }

                    return this.EDITOR_MAPS_EDIT_ARMY_POSITION;
                case EDITOR_MAPS_EDIT_PROVINCE_NAMES:
                    if (this.EDITOR_MAPS_EDIT_PROVINCE_NAMES == -1) {
                        this.EDITOR_MAPS_EDIT_PROVINCE_NAMES = this.addMenu(new EditorMapProvinceNamePoints());
                    }

                    return this.EDITOR_MAPS_EDIT_PROVINCE_NAMES;
                case EDITOR_MAPS_EDIT_LINES:
                    if (this.EDITOR_MAPS_EDIT_LINES == -1) {
                        this.EDITOR_MAPS_EDIT_LINES = this.addMenu(new EditorMapLines());
                    }

                    return this.EDITOR_MAPS_EDIT_LINES;
                case EDITOR_MAPS_EDIT_WAVES:
                    if (this.EDITOR_MAPS_EDIT_WAVES == -1) {
                        this.EDITOR_MAPS_EDIT_WAVES = this.addMenu(new EditorMapLinesWaves());
                    }

                    return this.EDITOR_MAPS_EDIT_WAVES;
                case EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS:
                    if (this.EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS == -1) {
                        this.EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS = this.addMenu(new EditorMap_GenerateSuggestedCivilizations());
                    } else {
                        ((List)this.menus.get(this.EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS)).set(0, new EditorMap_GenerateSuggestedCivilizations());
                    }

                    return this.EDITOR_MAPS_EDIT_SUGGESTED_CIVILIZATIONS;
                case PRINT_A_MAP:
                    if (this.PRINT_A_MAP == -1) {
                        this.PRINT_A_MAP = this.addMenu(new EditorMap_PrintMap());
                    } else {
                        ((List)this.menus.get(this.PRINT_A_MAP)).set(0, new EditorMap_PrintMap());
                    }

                    return this.PRINT_A_MAP;
                case EDITOR_MAPS_EDIT_PORT_POSITION:
                    if (this.EDITOR_MAPS_EDIT_PORT_POSITION == -1) {
                        this.EDITOR_MAPS_EDIT_PORT_POSITION = this.addMenu(new EditorMapPortPosition());
                    }

                    return this.EDITOR_MAPS_EDIT_PORT_POSITION;
                case EDITOR_MAPS_EDIT_TERRAIN:
                    if (this.EDITOR_MAPS_EDIT_TERRAIN == -1) {
                        this.EDITOR_MAPS_EDIT_TERRAIN = this.addMenu(new EditorMapTerrainType());
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_TERRAIN, new EditorMapTerrainTypeList());
                    }

                    return this.EDITOR_MAPS_EDIT_TERRAIN;
                case EDITOR_MAPS_EDIT_RESOURCE:
                    if (this.EDITOR_MAPS_EDIT_RESOURCE == -1) {
                        this.EDITOR_MAPS_EDIT_RESOURCE = this.addMenu(new EditorMapResources());
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_RESOURCE, new EditorMapResourcesList());
                    }

                    return this.EDITOR_MAPS_EDIT_RESOURCE;
                case EDITOR_SELECT_PROVINCES:
                    if (this.EDITOR_SELECT_PROVINCES == -1) {
                        this.EDITOR_SELECT_PROVINCES = this.addMenu(new EditorSelectProvinces());
                    }

                    return this.EDITOR_SELECT_PROVINCES;
                case EDITOR_MAPS_EDIT_CONTINENTS:
                    if (this.EDITOR_MAPS_EDIT_CONTINENTS == -1) {
                        this.EDITOR_MAPS_EDIT_CONTINENTS = this.addMenu(new EditorMapContinents());
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_CONTINENTS, new EditorMapContinentsList());
                    }

                    return this.EDITOR_MAPS_EDIT_CONTINENTS;
                case EDITOR_MAPS_EDIT_GEO_REGION:
                    if (this.EDITOR_MAPS_EDIT_GEO_REGION == -1) {
                        this.EDITOR_MAPS_EDIT_GEO_REGION = this.addMenu(new EditorMapGeoRegions());
                        this.addNextMenuToView(this.EDITOR_MAPS_EDIT_GEO_REGION, new EditorMapGeoRegionsList());
                    }

                    return this.EDITOR_MAPS_EDIT_GEO_REGION;
                case EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS:
                    if (this.EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS == -1) {
                        this.EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS = this.addMenu(new EditorMapOptimizationRegions());
                    }

                    return this.EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS;
                case EDITOR_SCENARIOS_LIST:
                    if (this.EDITOR_SCENARIOS_LIST == -1) {
                        this.EDITOR_SCENARIOS_LIST = this.addMenu(new ScenariosList());
                    } else {
                        ((List)this.menus.get(this.EDITOR_SCENARIOS_LIST)).set(0, new ScenariosList());
                    }

                    ScenarioWasteland.lUndo.clear();
                    return this.EDITOR_SCENARIOS_LIST;
                case SCENARIO_WASTELAND_CONTINENTS:
                    if (this.SCENARIO_WASTELAND_CONTINENTS == -1) {
                        this.SCENARIO_WASTELAND_CONTINENTS = this.addMenu(new ScenarioWastelandContinents());
                        this.addNextMenuToView(this.SCENARIO_WASTELAND_CONTINENTS, new ScenarioWastelandContinentsList());
                    }

                    return this.SCENARIO_WASTELAND_CONTINENTS;
                case SCENARIO_WASTELAND:
                    if (this.SCENARIO_WASTELAND == -1) {
                        this.SCENARIO_WASTELAND = this.addMenu(new ScenarioWasteland());
                        this.addNextMenuToView(this.SCENARIO_WASTELAND, new ScenarioWastelandOptions());
                    }

                    return this.SCENARIO_WASTELAND;
                case SCENARIO_CIVILIZATIONS:
                    if (this.SCENARIO_CIVILIZATIONS == -1) {
                        this.SCENARIO_CIVILIZATIONS = this.addMenu(new ScenarioCivilizations());
                        this.SCENARIO_CIVILIZATIONS_LIST = this.addNextMenuToView(this.SCENARIO_CIVILIZATIONS, new ScenarioCivilizationsList());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_CIVILIZATIONS)).set(this.SCENARIO_CIVILIZATIONS_LIST, new ScenarioCivilizationsList());
                    }

                    try {
                        ProvinceBorderManager.clearProvinceBorder();
                    } catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }

                    return this.SCENARIO_CIVILIZATIONS;
                case SCENARIO_ASSIGN:
                    if (this.SCENARIO_ASSIGN == -1) {
                        this.SCENARIO_ASSIGN = this.addMenu(new ScenarioAssign());
                        this.addNextMenuToView(this.SCENARIO_ASSIGN, new ScenarioAssignList());
                        this.SCENARIO_ASSIGN_CIVS = this.addNextMenuToView(this.SCENARIO_ASSIGN, new ScenarioAssignCivs());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_ASSIGN)).set(this.SCENARIO_ASSIGN_CIVS, new ScenarioAssignCivs());
                    }

                    return this.SCENARIO_ASSIGN;
                case SCENARIO_ASSIGN_IN_GAME:
                    if (this.SCENARIO_ASSIGN_IN_GAME == -1) {
                        this.SCENARIO_ASSIGN_IN_GAME = this.addMenu(new ScenarioAssign_InGame());
                        this.addNextMenuToView(this.SCENARIO_ASSIGN_IN_GAME, new ScenarioAssign_InGameList());
                        this.SCENARIO_ASSIGN_IN_GAME_CIVS = this.addNextMenuToView(this.SCENARIO_ASSIGN_IN_GAME, new ScenarioAssign_InGameCivs());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_ASSIGN_IN_GAME)).set(this.SCENARIO_ASSIGN_IN_GAME_CIVS, new ScenarioAssign_InGameCivs());
                    }

                    return this.SCENARIO_ASSIGN_IN_GAME;
                case SCENARIO_TECHNOLOGIES:
                    if (this.SCENARIO_TECHNOLOGIES == -1) {
                        this.SCENARIO_TECHNOLOGIES = this.addMenu(new ScenarioTechnology_Default());
                        this.addNextMenuToView(this.SCENARIO_TECHNOLOGIES, new ScenarioTechnology_Default_List());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_TECHNOLOGIES)).set(0, new ScenarioTechnology_Default());
                    }

                    return this.SCENARIO_TECHNOLOGIES;
                case SCENARIO_TECHNOLOGIES_CIVS:
                    if (this.SCENARIO_TECHNOLOGIES_CIVS == -1) {
                        this.SCENARIO_TECHNOLOGIES_CIVS = this.addMenu(new ScenarioTechnologyCivs());
                        this.addNextMenuToView(this.SCENARIO_TECHNOLOGIES_CIVS, new ScenarioTechnologyCivs_List());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_TECHNOLOGIES_CIVS)).set(0, new ScenarioTechnologyCivs());
                        ((List)this.menus.get(this.SCENARIO_TECHNOLOGIES_CIVS)).set(1, new ScenarioTechnologyCivs_List());
                    }

                    return this.SCENARIO_TECHNOLOGIES_CIVS;
                case SCENARIO_ARMIES:
                    if (this.SCENARIO_ARMIES == -1) {
                        this.SCENARIO_ARMIES = this.addMenu(new ScenarioArmies());
                        this.addNextMenuToView(this.SCENARIO_ARMIES, new ScenarioArmies_List());
                    }

                    return this.SCENARIO_ARMIES;
                case SCENARIO_POPULATION:
                    if (this.SCENARIO_POPULATION == -1) {
                        this.SCENARIO_POPULATION = this.addMenu(new ScenarioPopulation());
                        this.addNextMenuToView(this.SCENARIO_POPULATION, new ScenarioPopulation_List());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_POPULATION)).set(1, new ScenarioPopulation_List());
                    }

                    return this.SCENARIO_POPULATION;
                case SCENARIO_ECONOMY:
                    if (this.SCENARIO_ECONOMY == -1) {
                        this.SCENARIO_ECONOMY = this.addMenu(new ScenarioEconomy());
                        this.addNextMenuToView(this.SCENARIO_ECONOMY, new ScenarioEconomy_List());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_ECONOMY)).set(0, new ScenarioEconomy());
                        ((List)this.menus.get(this.SCENARIO_ECONOMY)).set(1, new ScenarioEconomy_List());
                    }

                    return this.SCENARIO_ECONOMY;
                case SCENARIO_GOVERNMENT:
                    if (this.SCENARIO_GOVERNMENT == -1) {
                        this.SCENARIO_GOVERNMENT = this.addMenu(new ScenarioGovernment());
                        this.addNextMenuToView(this.SCENARIO_GOVERNMENT, new ScenarioGovernment_List());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_GOVERNMENT)).set(0, new ScenarioGovernment());
                        ((List)this.menus.get(this.SCENARIO_GOVERNMENT)).set(1, new ScenarioGovernment_List());
                    }

                    return this.SCENARIO_GOVERNMENT;
                case SCENARIO_BUILDINGS:
                    if (this.SCENARIO_BUILDINGS == -1) {
                        this.SCENARIO_BUILDINGS = this.addMenu(new ScenarioBuildings());
                        this.addNextMenuToView(this.SCENARIO_BUILDINGS, new ScenarioBuildings_List());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_BUILDINGS)).set(1, new ScenarioBuildings_List());
                    }

                    return this.SCENARIO_BUILDINGS;
                case SCENARIO_CORES:
                    if (this.SCENARIO_CORES == -1) {
                        this.SCENARIO_CORES = this.addMenu(new ScenarioCores());
                        this.SCENARIO_CORES_LIST = this.addNextMenuToView(this.SCENARIO_CORES, new ScenarioCores_List());
                        this.SCENARIO_CORES_LIST_IN_PROVINCE = this.addNextMenuToView(this.SCENARIO_CORES, new ScenarioCores_InProvinces());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_CORES)).set(this.SCENARIO_CORES_LIST, new ScenarioCores_List());
                        ((List)this.menus.get(this.SCENARIO_CORES)).set(this.SCENARIO_CORES_LIST_IN_PROVINCE, new ScenarioCores_InProvinces());
                    }

                    return this.SCENARIO_CORES;
                case SCENARIO_RELIGION:
                    if (this.SCENARIO_RELIGION == -1) {
                        this.SCENARIO_RELIGION = this.addMenu(new ScenarioReligion());
                        this.SCENARIO_RELIGION_LIST = this.addNextMenuToView(this.SCENARIO_RELIGION, new ScenarioReligion_List());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_RELIGION)).set(this.SCENARIO_RELIGION_LIST, new ScenarioReligion_List());
                    }

                    return this.SCENARIO_RELIGION;
                case SCENARIO_RELATIONS:
                    if (this.SCENARIO_RELATIONS == -1) {
                        this.SCENARIO_RELATIONS = this.addMenu(new ScenarioRelations());
                        this.addNextMenuToView(this.SCENARIO_RELATIONS, new ScenarioRelationsList());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_RELATIONS)).set(1, new ScenarioRelationsList());
                    }

                    return this.SCENARIO_RELATIONS;
                case SCENARIO_ALLIANCES:
                    if (this.SCENARIO_ALLIANCES == -1) {
                        this.SCENARIO_ALLIANCES = this.addMenu(new ScenarioAlliances());
                        this.addNextMenuToView(this.SCENARIO_ALLIANCES, new ScenarioAlliancesList());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_ALLIANCES)).set(1, new ScenarioAlliancesList());
                    }

                    return this.SCENARIO_ALLIANCES;
                case SCENARIO_VASSALS:
                    if (this.SCENARIO_VASSALS == -1) {
                        this.SCENARIO_VASSALS = this.addMenu(new ScenarioVassals());
                        this.addNextMenuToView(this.SCENARIO_VASSALS, new ScenarioVassalsList());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_VASSALS)).set(1, new ScenarioVassalsList());
                    }

                    return this.SCENARIO_VASSALS;
                case SCENARIO_TRUCES:
                    if (this.SCENARIO_TRUCES == -1) {
                        this.SCENARIO_TRUCES = this.addMenu(new ScenarioTruces());
                        this.addNextMenuToView(this.SCENARIO_TRUCES, new ScenarioTrucesList());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_TRUCES)).set(1, new ScenarioTrucesList());
                    }

                    return this.SCENARIO_TRUCES;
                case SCENARIO_DECLARE_WAR:
                    if (this.SCENARIO_DECLARE_WAR == -1) {
                        this.SCENARIO_DECLARE_WAR = this.addMenu(new ScenarioDeclareWar());
                        this.addNextMenuToView(this.SCENARIO_DECLARE_WAR, new ScenarioDeclareWarList());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_DECLARE_WAR)).set(1, new ScenarioDeclareWarList());
                    }

                    return this.SCENARIO_DECLARE_WAR;
                case SCENARIO_NON_AGGRESSION:
                    if (this.SCENARIO_NON_AGGRESSION == -1) {
                        this.SCENARIO_NON_AGGRESSION = this.addMenu(new ScenarioNonAggression());
                        this.addNextMenuToView(this.SCENARIO_NON_AGGRESSION, new ScenarioNonAggressionList());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_NON_AGGRESSION)).set(1, new ScenarioNonAggressionList());
                    }

                    return this.SCENARIO_NON_AGGRESSION;
                case SCENARIO_MILITARY_ACCESS:
                    if (this.SCENARIO_MILITARY_ACCESS == -1) {
                        this.SCENARIO_MILITARY_ACCESS = this.addMenu(new ScenarioMilitaryAccess());
                        this.addNextMenuToView(this.SCENARIO_MILITARY_ACCESS, new ScenarioMilitaryAccessList());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_MILITARY_ACCESS)).set(1, new ScenarioMilitaryAccessList());
                    }

                    return this.SCENARIO_MILITARY_ACCESS;
                case SCENARIO_CREATE_ALLIANCE:
                    if (this.SCENARIO_CREATE_ALLIANCE == -1) {
                        this.SCENARIO_CREATE_ALLIANCE = this.addMenu(new ScenarioCreateAlliance_Alliances());
                        this.addNextMenuToView(this.SCENARIO_CREATE_ALLIANCE, new ScenarioCreateAlliance_AlliancesList());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_CREATE_ALLIANCE)).set(1, new ScenarioCreateAlliance_AlliancesList());
                    }

                    return this.SCENARIO_CREATE_ALLIANCE;
                case SCENARIO_CREATE_ALLIANCE_EDIT:
                    if (this.SCENARIO_CREATE_ALLIANCE_EDIT == -1) {
                        this.SCENARIO_CREATE_ALLIANCE_EDIT = this.addMenu(new ScenarioCreateAlliance());
                        this.addNextMenuToView(this.SCENARIO_CREATE_ALLIANCE_EDIT, new ScenarioCreateAllianceList());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT)).set(1, new ScenarioCreateAllianceList());
                    }

                    return this.SCENARIO_CREATE_ALLIANCE_EDIT;
                case SCENARIO_DEFENSIVE:
                    if (this.SCENARIO_DEFENSIVE == -1) {
                        this.SCENARIO_DEFENSIVE = this.addMenu(new ScenarioDefensive());
                        this.addNextMenuToView(this.SCENARIO_DEFENSIVE, new ScenarioDefensiveList());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_DEFENSIVE)).set(1, new ScenarioDefensiveList());
                    }

                    return this.SCENARIO_DEFENSIVE;
                case SCENARIO_GUARANTEE:
                    if (this.SCENARIO_GUARANTEE == -1) {
                        this.SCENARIO_GUARANTEE = this.addMenu(new ScenarioGuarantee());
                        this.addNextMenuToView(this.SCENARIO_GUARANTEE, new ScenarioGuaranteeList());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_GUARANTEE)).set(1, new ScenarioGuaranteeList());
                    }

                    return this.SCENARIO_GUARANTEE;
                case SCENARIO_PREVIEW:
                    if (this.SCENARIO_PREVIEW == -1) {
                        this.SCENARIO_PREVIEW = this.addMenu(new ScenarioGeneratePreview());
                    } else {
                        ((List)this.menus.get(this.SCENARIO_PREVIEW)).set(0, new ScenarioGeneratePreview());
                    }

                    return this.SCENARIO_PREVIEW;
                case SCENARIO_SETTINGS:
                    if (this.SCENARIO_SETTINGS == -1) {
                        this.SCENARIO_SETTINGS = this.addMenu(new ScenarioSettings());
                        this.SCENARIO_CALENDAR = this.addNextMenuToView(this.SCENARIO_SETTINGS, new Scenario_Calendar(false));
                    } else {
                        ((List)this.menus.get(this.SCENARIO_SETTINGS)).set(0, new ScenarioSettings());
                    }

                    return this.SCENARIO_SETTINGS;
                default:
                    return this.MAINMENU;
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
            return this.MAINMENU;
        }
    }

    public final int getViewID() {
        return this.viewID;
    }

    public final void setViewID(View eView) {
        Game.hoverManager.resetHoverActive_Menu();
        this.fromViewID = this.viewID;
        this.viewID = this.getViewID(eView);
        this.toViewID = this.viewID;
        this.updateDrawProvinces();
        ProvinceTouchExtraAction.updateExtraAction();
        AA_KeyManager.updateKeyExtraAction();
        ProvinceBorderManager.updateAction();
        ProvinceDraw.updateDrawMoveUnits();
        Game.mapBG.updateWorldMap();
    }

    public final void setViewIDWithoutAnimation(View eView) {
        Game.hoverManager.resetHoverActive_Menu();
        this.viewID = this.getViewID(eView);
        this.updateDrawProvinces();
        ProvinceTouchExtraAction.updateExtraAction();
        AA_KeyManager.updateKeyExtraAction();
        ProvinceBorderManager.updateAction();
        ProvinceDraw.updateDrawMoveUnits();
        Game.mapBG.updateWorldMap();
    }

    protected final void updateLanguage() {
        for(int i = 0; i < this.menus.size(); ++i) {
            for(int j = 0; j < ((List)this.menus.get(i)).size(); ++j) {
                ((Menu)((List)this.menus.get(i)).get(j)).updateLanguage();
            }
        }

        this.dialogMenu.updateLanguage();
    }

    private final void updateDrawProvinces() {
        if (this.viewID > 0) {
            RendererGame.updateRenderer();
            ProvinceDraw.updateDrawProvinces();
            CivilizationRegionsManager.updateRenderer_CivNames();

            try {
                ProvinceHover.updateProvinceHoverBuild();
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

        Game.mapCoords.updateMinMaxPosY();
    }

    private final void resetChangeViewMode() {
        this.fromViewID = this.toViewID = -1;
        this.animationChangeViewPosX = 0.0F;
        this.backAnimation = false;
        this.animationStepID = 0;
    }

    public MenuManager() {
    }

    public final void initColorPicker() {
        this.colorPicker = new ColorPicker();
        this.getColorPicker().buildColors();
        this.getColorPicker().setHueWidth((int)((float)CFG.BUTTON_WIDTH * 0.35F * CFG.GUI_SCALE));
        this.getColorPicker().setSVHeight((int)((float)(ImageManager.getImage(Images.pickerSV).getHeight() * 2) * CFG.GUI_SCALE));
        this.getColorPicker().setResizeHeight((int)(30.0F * CFG.GUI_SCALE));
    }

    private final int addMenu(Menu menu) {
        List<Menu> nMenus = new ArrayList();
        nMenus.add(menu);
        this.menus.add(nMenus);
        List<Integer> order = new ArrayList();
        order.add(((List)this.menus.get(this.menus.size() - 1)).size() - 1);
        this.orderOfMenu.add(order);
        return this.menus.size() - 1;
    }

    public final int addNextMenuToView(int toView, Menu menu) {
        ((List)this.menus.get(toView)).add(menu);
        this.addMenuToOrderAtTheTop(toView);
        return ((List)this.menus.get(toView)).size() - 1;
    }

    public final void addMenuToOrderAtTheTop(int toView) {
        try {
            ((List)this.orderOfMenu.get(toView)).add(((List)this.menus.get(toView)).size() - 1);
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void setOrderOfMenu(int menuID) {
        try {
            orderOfMenuInGame = false;
            boolean found = false;

            for(int i = 0; i < ((List)this.orderOfMenu.get(this.viewID)).size(); ++i) {
                if ((Integer)((List)this.orderOfMenu.get(this.viewID)).get(i) == menuID) {
                    menuID = (Integer)((List)this.orderOfMenu.get(this.viewID)).get(i);
                    if (i == 0) {
                        return;
                    }

                    found = true;
                    break;
                }
            }

            if (found) {
                List<Integer> lOrder = new ArrayList();
                lOrder.add(menuID);

                for(int i = 0; i < ((List)this.orderOfMenu.get(this.viewID)).size(); ++i) {
                    if ((Integer)((List)this.orderOfMenu.get(this.viewID)).get(i) != menuID) {
                        lOrder.add((Integer)((List)this.orderOfMenu.get(this.viewID)).get(i));
                    }
                }

                this.orderOfMenu.set(this.viewID, lOrder);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void addToast(String sText) {
        this.addToast(new Toast(sText, 0));
    }

    public final void addToast(String sText, int iFontID) {
        this.addToast(new Toast(sText, iFontID));
    }

    public final void addToast_Error(String sText) {
        this.addToast(new Toast(sText, 0, 6000, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
    }

    public final void addToast_Error(String sText, int imageID) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Text(sText, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(imageID, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 6000));
    }

    public final void addToastGold(String s1, int img) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Text(s1, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 6000));
    }

    public final void addToastBonus(String s1, String s2, int img, int civID) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Flag(civID, 0, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(s1, CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
        nData.add(new MenuElement_HoverElement_Type_Text(s2, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 6000));
    }

    public final void addToastGold(String s1, int img, int time) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Text(s1, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, time));
    }

    public final void addToastGold_Short(String s1, int img) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Text(s1, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 1750));
    }

    public final void addToastInsufficient(String s1, String s2, int img) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Text(s1));
        nData.add(new MenuElement_HoverElement_Type_Text(s2, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 6000));
    }

    public final void addToastPositive(String s1, String s2, int img) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Text(s1));
        nData.add(new MenuElement_HoverElement_Type_Text(s2, Colors.HOVER_POSITIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 2500));
    }

    public final void addToastNegative(String s1, String s2, int img) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Text(s1));
        nData.add(new MenuElement_HoverElement_Type_Text(s2, Colors.HOVER_NEGATIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(img, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, 2500));
    }

    public final void addToast_Error(String sText, int imageID, int time) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Text(sText, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
        nData.add(new MenuElement_HoverElement_Type_Image(imageID, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        this.addToast(new Toast(nElements, 0, time));
    }

    public final void addToast(String sText, int iFontID, int nTimeInView) {
        this.addToast(new Toast(sText, iFontID, nTimeInView));
    }

    public final void addToast(Toast nToast) {
        this.lToast.add(nToast);
        if (this.lToast.size() > GameValues.notifications.TOAST_MAX_SIZE) {
            for(int i = 0; i < this.lToast.size() - GameValues.notifications.TOAST_MAX_SIZE; ++i) {
                this.lToast.remove(i);
            }
        }

        this.iToastSize = this.lToast.size();
        this.TOAST_TIME = CFG.currentTimeMillis;
    }

    private final void clearToast() {
        this.lToast.clear();
        this.iToastSize = 0;
    }

    public final void update() {
        try {
            if (this.fromViewID >= 0) {
                label44: {
                    this.animationChangeViewPosX = CFG.changeAnimationPos(this.animationStepID++, this.animationChangeViewPosX, this.backAnimation, CFG.GAME_WIDTH);
                    if (!(Math.abs(this.animationChangeViewPosX) >= (float)CFG.GAME_WIDTH)) {
                        FPS var10001 = Renderer.uFPS;
                        if (Renderer.uFPS.iNumOfFPS >= 22) {
                            break label44;
                        }
                    }

                    this.resetChangeViewMode();
                }
            }

            Game.hoverManager.udpateMobile();

            for(int i = 0; i < this.getActiveMenu().size(); ++i) {
                if (((Menu)this.getActiveMenu().get(i)).getScrollableY() || ((Menu)this.getActiveMenu().get(i)).getScrollableX()) {
                    ((Menu)this.getActiveMenu().get(i)).update();
                }
            }

            Game.hoverManager.updateHoveredElement();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static final void addClickAnimation(ClickAnimation nClick) {
        try {
            if (GameValues.clickAnim.ENABLE_ACTION_DOTS_ANIMATION) {
                clickAnimations.add(nClick);
                clickAnimationsSize = clickAnimations.size();
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void draw(SpriteBatch oSB) {
        try {
            if (this.getInGame() && InGame.ONLY_MAP_MODE) {
                return;
            }

            if (this.TOAST_TIME + (long)this.ANIMATION_TIME >= CFG.currentTimeMillis) {
                float fAlpha = 0.8F - 0.8F * ((float)(CFG.currentTimeMillis - this.TOAST_TIME) / (float)this.ANIMATION_TIME);
                oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, fAlpha));
                Images.gradientXY.draw(oSB, CFG.GAME_WIDTH / 4, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * 2, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT * 2);
                oSB.setColor(Color.WHITE);
            }

            if (this.WAR_TIME + (long)this.WAR_ANIMATION_TIME >= CFG.currentTimeMillis) {
                float fAlpha = 0.4F - 0.4F * ((float)(CFG.currentTimeMillis - this.WAR_TIME) / (float)this.WAR_ANIMATION_TIME);
                oSB.setColor(new Color(DiplomacyManager.COLOR_WAR.r, DiplomacyManager.COLOR_WAR.g, DiplomacyManager.COLOR_WAR.b, fAlpha));
                Images.gradientXY.draw(oSB, 0, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT, CFG.GAME_WIDTH, CFG.BUTTON_HEIGHT);
                Images.gradientXY.draw(oSB, CFG.GAME_WIDTH / 4, CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT / 2, CFG.GAME_WIDTH / 2, CFG.BUTTON_HEIGHT / 2);
                oSB.setColor(Color.WHITE);
            }

            if (this.fromViewID < 0) {
                this.draw(oSB, this.viewID, 0, 0);
            } else {
                this.draw(oSB, this.fromViewID, (int)this.animationChangeViewPosX, 0);
                this.draw(oSB, this.toViewID, (int)this.animationChangeViewPosX + CFG.GAME_WIDTH * (this.backAnimation ? -1 : 1), 0);
            }

            if (clickAnimationsSize > 0) {
                try {
                    oSB.setColor(Color.WHITE);
                    Renderer.oSBBorder.end();
                    Renderer.oSBBorder.begin();
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }

                try {
                    try {
                        for(int j = clickAnimationsSize - 1; j >= 0; --j) {
                            try {
                                if (((ClickAnimation)clickAnimations.get(j)).draw(oSB)) {
                                    clickAnimations.remove(j);
                                }
                            } catch (Exception ex) {
                                CFG.exceptionStack(ex);
                            }
                        }

                        clickAnimationsSize = clickAnimations.size();
                    } catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }

                try {
                    Renderer.oSBBorder.end();
                    Renderer.oSBBorder.begin();
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }

            this.drawToast(oSB);
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void drawToast(SpriteBatch oSB) {
        for(int i = this.iToastSize - 1; i >= 0; --i) {
            if (!((Toast)this.lToast.get(i)).inView) {
                this.lToast.remove(i);
                this.iToastSize = this.lToast.size();
            }
        }

        int i = this.iToastSize - 1;

        for(int tY = 0; i >= 0; --i) {
            ((Toast)this.lToast.get(i)).draw(oSB, 0, tY);
            if (((Toast)this.lToast.get(i)).iPosX == -1) {
                tY += ((Toast)this.lToast.get(i)).iHeight + CFG.PADDING;
            }
        }

    }

    public final Status getTitleStatus(int menuID, int i) {
        try {
            if (this.fromViewID < 0) {
                if (this.menu_MoveByTitleMode) {
                    if (this.activeSliderMenuID == (Integer)((List)this.orderOfMenu.get(menuID)).get(i)) {
                        return Status.ACTIVE;
                    }
                } else if (Game.hoverManager.hoverActiveMenuTitleID == (Integer)((List)this.orderOfMenu.get(menuID)).get(i)) {
                    if (Game.hoverManager.hoverActiveMenuTitleCloseHovered) {
                        return Status.CLOSE_HOVERED;
                    }

                    return Status.HOVERED;
                }
            }
        } catch (Exception var4) {
        }

        return Status.DEFAULT;
    }

    public final void draw(SpriteBatch oSB, int menuID, int iTranslateX, int iTranslateY) {
        try {
            for(int i = ((List)this.menus.get(menuID)).size() - 1; i >= 0; --i) {
                try {
                    if (((Menu)((List)this.menus.get(menuID)).get((Integer)((List)this.orderOfMenu.get(menuID)).get(i))).getVisible()) {
                        ((Menu)((List)this.menus.get(menuID)).get((Integer)((List)this.orderOfMenu.get(menuID)).get(i))).draw(oSB, iTranslateX, iTranslateY, this.dialogMenu.getVisible() ? false : (Integer)((List)this.orderOfMenu.get(menuID)).get(i) == this.activeSliderMenuID, this.getTitleStatus(menuID, i));
                        Renderer.clearUnclearedScissors(oSB);
                    }
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        try {
            if (Game.hoverManager.hoverActiveSliderMenuID >= 0 && Game.hoverManager.hoverActiveMenuElementID >= 0) {
                Game.hoverManager.updateElementHover_Animation();
                ((Menu)this.getActiveMenu().get(Game.hoverManager.hoverActiveSliderMenuID)).drawHover(oSB, iTranslateX, 0, Game.hoverManager.hoverActiveMenuElementID);
            } else if (Game.provinceHover_Informations != null) {
                Game.hoverManager.updateElementHover_Animation();
                Game.provinceHover_Informations.drawProvinceInfo(oSB, Touch.getMousePosX() + Renderer.getHover_ExtraPosX(), Touch.getMousePosY() + Renderer.getHover_ExtraPosY());
            }

            if (this.colorPicker.getVisible()) {
                this.colorPicker.draw(oSB, iTranslateX);
            }

            if (this.dialogMenu.getVisible()) {
                this.dialogMenu.draw(oSB, iTranslateX, iTranslateY, true, Status.DEFAULT);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final List<Menu> getActiveMenu() {
        try {
            return (List)this.menus.get(this.viewID);
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
            return (List)this.menus.get(0);
        }
    }

    public final MenuElement getActiveMenuElement() {
        try {
            try {
                return ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID);
            } catch (IndexOutOfBoundsException ex) {
                CFG.exceptionStack(ex);
                return ((Menu)((List)this.menus.get(0)).get(0)).getMenuElement(0);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
            return new Button();
        }
    }

    public final MenuElement getMenuElement(int nSliderMenuID, int nMenuElementID) {
        return ((Menu)this.getActiveMenu().get(nSliderMenuID)).getMenuElement(nMenuElementID);
    }

    public final int getActiveOrder(int i) {
        try {
            return (Integer)((List)this.orderOfMenu.get(this.viewID)).get(i);
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
            return 0;
        }
    }

    protected final void actionElement(int sliderMenuID, int nMenuElementID, boolean PPM) {
        Game.keyboard.hideKeyboard();
        if (PPM) {
            ((Menu)this.getActiveMenu().get(sliderMenuID)).actionElementPPM(nMenuElementID);
        } else {
            ((Menu)this.getActiveMenu().get(sliderMenuID)).actionElement(nMenuElementID);
        }

    }

    protected final void onBackPressed() {
        if (this.dialogMenu.getVisible()) {
            this.dialogMenu.onBackPressed();
        }

        try {
            ((Menu)this.getActiveMenu().get(0)).onBackPressed();
        } catch (IndexOutOfBoundsException var2) {
        }

        Touch.resetAllModes();
    }

    public final boolean actionDown(int nPosX, int nPosY) {
        if (this.getInGame() && InGame.ONLY_MAP_MODE) {
            return false;
        } else {
            try {
                if (this.dialogMenu.getVisible()) {
                    for(int i = 0; i < this.dialogMenu.getMenuElementsSize(); ++i) {
                        if (nPosX >= this.dialogMenu.getPosX() + this.dialogMenu.getMenuElement(i).getPosX() && nPosX <= this.dialogMenu.getPosX() + this.dialogMenu.getMenuElement(i).getPosX() + this.dialogMenu.getMenuElement(i).getWidth() && nPosY >= this.dialogMenu.getPosY() + this.dialogMenu.getMenuElement(i).getPosY() && nPosY <= this.dialogMenu.getPosY() + this.dialogMenu.getMenuElement(i).getPosY() + this.dialogMenu.getMenuElement(i).getHeight()) {
                            this.activeMenuElementID = i;
                            return true;
                        }
                    }

                    return true;
                }

                if (this.colorPickerMode) {
                    this.colorPicker.touch(nPosX, nPosY);
                    return true;
                }

                if (this.colorPicker.getVisible() && nPosX >= this.colorPicker.getPosX() && nPosX <= this.colorPicker.getPosX() + this.colorPicker.getWidth() && nPosY >= this.colorPicker.getPosY() && nPosY <= this.colorPicker.getPosY() + this.colorPicker.getHeight()) {
                    this.colorPickerMode = true;
                    this.colorPicker.touch(nPosX, nPosY);
                    return true;
                }

                if (this.actionDown_CloseMenu(nPosX, nPosY)) {
                    return true;
                }

                if (this.actionDown_ResizeMenu(nPosX, nPosY)) {
                    return true;
                }

                if (this.actionDown_MenuTitle(nPosX, nPosY)) {
                    try {
                        ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getTitle().onHovered();
                    } catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }

                    return true;
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }

            try {
                for(int i = 0; i < this.getActiveMenu().size(); ++i) {
                    if (((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getVisible() && nPosX >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosX() && nPosX <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosX() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getWidth() && nPosY >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosY() && nPosY <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosY() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getHeight()) {
                        this.activeSliderMenuID = this.getActiveOrder(i);
                        ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).stopScrolling();
                        if (((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getScrollableY() || ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getScrollableX()) {
                            this.menu_MoveInnerElements = true;
                            this.iSliderMenuStartPosY = ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuPosY() - nPosY;
                            this.iSliderMenuActionDownPosY = nPosY;
                            this.iSliderMenuStartPosX = ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuPosX() - nPosX;
                            this.iSliderMenuActionDownPosX = nPosX;
                        }

                        for(int j = 0; j < ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuElementsSize(); ++j) {
                            if (((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuElement(j).getVisible() && nPosX >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuElement(j).getPosX() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuPosX() && nPosX <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuElement(j).getPosX() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuPosX() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuElement(j).getWidth() && nPosY >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuElement(j).getPosY() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuPosY() && nPosY <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuElement(j).getPosY() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuElement(j).getHeight() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getMenuPosY()) {
                                this.activeMenuElementID = j;
                                if (CFG.isAndroid) {
                                    if (Game.hoverManager.hoverActiveSliderMenuID == this.activeSliderMenuID && Game.hoverManager.hoverActiveMenuElementID == j) {
                                        HoverManager var9 = Game.hoverManager;
                                        HoverManager.hoverMobileTime = CFG.currentTimeMillis;
                                    } else {
                                        if (Game.hoverManager.hoverActiveMenuElementID >= 0 && Game.hoverManager.hoverActiveSliderMenuID >= 0) {
                                            Game.hoverManager.resetHoverActive_Menu();
                                        }

                                        Game.hoverManager.hoverActiveSliderMenuID = this.activeSliderMenuID;
                                        Game.hoverManager.hoverActiveMenuElementID = this.activeMenuElementID;
                                        HoverManager.hoverTime = CFG.currentTimeMillis;
                                        ((Menu)this.getActiveMenu().get(Game.hoverManager.hoverActiveSliderMenuID)).getMenuElement(Game.hoverManager.hoverActiveMenuElementID).setIsHovered(true);
                                        ((Menu)this.getActiveMenu().get(Game.hoverManager.hoverActiveSliderMenuID)).getMenuElement(Game.hoverManager.hoverActiveMenuElementID).buildElementHover();
                                        Game.hoverManager.updateHoveredFlag();
                                        HoverManager var10000 = Game.hoverManager;
                                        HoverManager.hoverMobileTime = CFG.currentTimeMillis;
                                    }
                                }

                                this.setOrderOfMenu(this.getActiveOrder(i));
                                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).onHovered();
                                switch (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getTypeOfElement()) {
                                    case SLIDER:
                                        if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getClickable()) {
                                            this.sliderMode = true;
                                            ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).updateSlider(nPosX - ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX());
                                        }
                                        break;
                                    case PIECHART_WITH_STATS:
                                        this.pieChartMode = true;
                                        if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getScrollable()) {
                                            this.menu_MoveInnerElements = false;
                                            this.iSliderMenuStartPosY = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getScrollPosY() - nPosY;
                                            this.iSliderMenuActionDownPosX = nPosX;
                                            this.iSliderMenuActionDownPosY = nPosY;
                                        }
                                        break;
                                    case TEXT_SCROLLABLE:
                                        if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getScrollable()) {
                                            this.textScrollableMode = true;
                                            this.menu_MoveInnerElements = false;
                                            this.iSliderMenuStartPosX = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getScrollPosX() - nPosX;
                                            this.iSliderMenuActionDownPosX = nPosX;
                                            this.iSliderMenuActionDownPosY = nPosY;
                                        }
                                        break;
                                    case GRAPH_VERTICAL:
                                        if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getMoveable()) {
                                            if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getInStatisticsMode()) {
                                                this.iSliderMenuStartPosY = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getCurrent() - nPosY;
                                                this.graphVertical_ScrollMode_Y = true;
                                            } else {
                                                this.iSliderMenuStartPosX = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getCurrent() - nPosX;
                                                this.graphVertical_ScrollMode_X = true;
                                            }

                                            ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).stopScrolling();
                                        } else {
                                            this.graphVertical_ScrollMode_X = true;
                                        }

                                        this.iSliderMenuActionDownPosY = nPosY;
                                        this.iSliderMenuActionDownPosX = nPosX;
                                        this.iSliderMenuStartPosX = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getCurrent() - nPosX;
                                        this.iSliderMenuActionDownPosX = nPosX;
                                        this.menu_MoveInnerElements = false;
                                }

                                return true;
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }

            return false;
        }
    }

    public final boolean actionMove(int nPosX, int nPosY) {
        try {
            if (this.dialogMenu.getVisible()) {
                return true;
            }

            if (this.colorPickerMode) {
                this.colorPicker.touch(nPosX, nPosY);
                return true;
            }

            if (this.menuResizeMode) {
                this.actionMove_ResizeMenu(nPosX, nPosY);
                return true;
            }

            if (this.menu_MoveByTitleMode) {
                this.actionMove_MenuTitle(nPosX, nPosY);
                return true;
            }

            if (this.menu_MoveInnerElements) {
                this.actionMove_MenuInnerElements(nPosX, nPosY);
                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setScrollPosY(nPosY);
                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setScrollPosX(nPosX);
                return true;
            }

            if (this.pieChartMode) {
                this.actionMove_ScrollPosY(nPosX, nPosY);
                return true;
            }

            if (this.textScrollableMode) {
                this.actionMove_ScrollPosX(nPosX, nPosY);
                return true;
            }

            if (this.sliderMode) {
                this.actionMoveSlider(nPosX, nPosY);
                return true;
            }

            if (this.graphVertical_ScrollMode_Y) {
                if (this.activeSliderMenuID >= 0 && this.activeMenuElementID >= 0) {
                    if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getMoveable()) {
                        this.actionMove_MenuInnerElements(nPosX, nPosY);
                        ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).setScrollPosY(nPosY);
                        if (this.updateSliderMenuPosY) {
                            this.iSliderMenuStartPosY = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getCurrent() - nPosY;
                            this.updateSliderMenuPosY = false;
                        } else {
                            ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).setCurrent(this.iSliderMenuStartPosY + nPosY);
                        }
                    }

                    return true;
                }
            } else if (this.graphVertical_ScrollMode_X) {
                if (this.activeSliderMenuID >= 0 && this.activeMenuElementID >= 0) {
                    if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getMoveable()) {
                        this.actionMove_MenuInnerElements(nPosX, nPosY);
                        ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).setScrollPosY(nPosX);
                        if (this.updateSliderMenuPosX) {
                            this.iSliderMenuStartPosX = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getCurrent() - nPosX;
                            this.updateSliderMenuPosX = false;
                        } else {
                            ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).setCurrent(this.iSliderMenuStartPosX + nPosX);
                        }
                    }

                    return true;
                }
            } else {
                if (this.activeMenuElementID >= 0) {
                    return true;
                }

                if (this.closeMenuMode) {
                    return true;
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        } catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
        }

        return false;
    }

    public final boolean actionUp(int nPosX, int nPosY, int nPointer, int button) {
        try {
            if (this.dialogMenu.getVisible()) {
                if (this.activeMenuElementID >= 0) {
                    if (this.dialogMenu.getMenuElement(this.activeMenuElementID).getClickable() && nPosX >= this.dialogMenu.getPosX() + this.dialogMenu.getMenuElement(this.activeMenuElementID).getPosX() && nPosX <= this.dialogMenu.getPosX() + this.dialogMenu.getMenuElement(this.activeMenuElementID).getPosX() + this.dialogMenu.getMenuElement(this.activeMenuElementID).getWidth() && nPosY >= this.dialogMenu.getPosY() + this.dialogMenu.getMenuElement(this.activeMenuElementID).getPosY() && nPosY <= this.dialogMenu.getPosY() + this.dialogMenu.getMenuElement(this.activeMenuElementID).getPosY() + this.dialogMenu.getMenuElement(this.activeMenuElementID).getHeight()) {
                        Game.soundsManager.playSound(SoundsManager.SOUND_CLICK2);
                        this.dialogMenu.actionElement(this.activeMenuElementID);
                        return true;
                    }
                } else {
                    this.dialogMenu.disableButtons();
                    Dialog.dialogFalse();
                    this.dialogMenu.closeMenu();
                }
            }

            if (this.colorPickerMode) {
                this.colorPicker.touch(nPosX, nPosY);
                this.colorPicker.touchUp();
                Game.soundsManager.playSound(Game.soundsManager.getClickMain());
                return true;
            }

            if (this.menuResizeMode) {
                return true;
            }

            if (this.menu_MoveByTitleMode) {
                if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getTitle() != null && nPosX >= ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX() && nPosX <= ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX() + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getWidth() && nPosY >= ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosY() - ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getTitle().getHeight() && nPosY <= ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosY()) {
                    ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getTitle().action();
                }

                return true;
            }

            if (this.sliderMode) {
                this.sliderMode = false;
                if (this.getActiveMenuElement().getClickable()) {
                    this.actionElement(this.activeSliderMenuID, this.activeMenuElementID, button == 1);
                }

                return true;
            }

            if (this.closeMenuMode) {
                this.actionUp_CloseMenu(nPosX, nPosY);
                return true;
            }

            if (this.activeMenuElementID >= 0 && this.activeSliderMenuID >= 0) {
                if (this.pieChartMode) {
                    if (this.iSliderMenuActionDownPosY >= nPosY - CFG.PADDING && this.iSliderMenuActionDownPosY <= nPosY + CFG.PADDING && this.iSliderMenuActionDownPosX >= nPosX - CFG.PADDING && this.iSliderMenuActionDownPosX <= nPosX + CFG.PADDING) {
                        ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).setDescription(!((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getDescription());
                        Game.soundsManager.playSound(((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getSFX());
                    }

                    if (this.getActiveMenuElement().getClickable()) {
                        this.actionElement(this.activeSliderMenuID, this.activeMenuElementID, button == 1);
                    }

                    return true;
                }

                if (this.graphVertical_ScrollMode_X || this.graphVertical_ScrollMode_Y) {
                    if ((float)this.iSliderMenuActionDownPosY >= (float)nPosY - (float)CFG.PADDING * CFG.GUI_SCALE && (float)this.iSliderMenuActionDownPosY <= (float)nPosY + (float)CFG.PADDING * CFG.GUI_SCALE && (float)this.iSliderMenuActionDownPosX >= (float)nPosX - (float)CFG.PADDING * CFG.GUI_SCALE && (float)this.iSliderMenuActionDownPosX <= (float)nPosX + (float)CFG.PADDING * CFG.GUI_SCALE) {
                        ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).setInStatisticsMode(!((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getInStatisticsMode());
                    } else if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getMoveable()) {
                        ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).scrollTheMenu();
                    }

                    this.actionElement(this.activeSliderMenuID, this.activeMenuElementID, button == 1);
                    return true;
                }

                if ((!this.menu_MoveInnerElements || this.iSliderMenuActionDownPosY >= nPosY - CFG.PADDING && this.iSliderMenuActionDownPosY <= nPosY + CFG.PADDING && this.iSliderMenuActionDownPosX >= nPosX - CFG.PADDING && this.iSliderMenuActionDownPosX <= nPosX + CFG.PADDING) && ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getClickable() && nPosX >= ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getPosX() + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuPosX() && nPosX <= ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getPosX() + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuPosX() + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getWidth() && nPosY >= ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getPosY() + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuPosY() && nPosY <= ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getPosY() + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getHeight() + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuPosY()) {
                    try {
                        Game.soundsManager.playSound(((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getSFX());
                    } catch (IndexOutOfBoundsException var6) {
                        Game.soundsManager.playSound(Game.soundsManager.getClickMain());
                    }

                    this.actionElement(this.activeSliderMenuID, this.activeMenuElementID, button == 1);
                    return true;
                }
            }

            if (this.menu_MoveInnerElements) {
                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).scrollTheMenu();
                return true;
            }
        } catch (IndexOutOfBoundsException var7) {
        } catch (Exception var8) {
        }

        return false;
    }

    private final void actionMove_MenuInnerElements(int nPosX, int nPosY) {
        try {
            if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getScrollableY()) {
                if (this.updateSliderMenuPosY) {
                    this.iSliderMenuStartPosY = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuPosY() - nPosY;
                    this.updateSliderMenuPosY = false;
                } else {
                    ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setMenuPosY(this.iSliderMenuStartPosY + nPosY);
                }
            }

            if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getScrollableX()) {
                if (this.updateSliderMenuPosX) {
                    this.iSliderMenuStartPosX = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuPosX() - nPosX;
                    this.updateSliderMenuPosX = false;
                } else {
                    ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setMenuPosX(this.iSliderMenuStartPosX + nPosX);
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        } catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
        }

    }

    private final boolean actionDown_ResizeMenu(int nPosX, int nPosY) {
        try {
            for(int i = 0; i < this.getActiveMenu().size(); ++i) {
                if (((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getVisible() && ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getResizable()) {
                    if (nPosX >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosX() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getWidth() - CFG.PADDING * 6 && nPosX <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosX() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getWidth() && nPosY >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosY() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getHeight() - CFG.PADDING * 6 && nPosY <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosY() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getHeight()) {
                        this.startMenuResizeMode(this.getActiveOrder(i), nPosX, nPosY, false);
                        this.setOrderOfMenu(this.getActiveOrder(i));
                        return true;
                    }

                    if (nPosX >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosX() && nPosX <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosX() + CFG.PADDING * 6 && nPosY >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosY() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getHeight() - CFG.PADDING * 6 && nPosY <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosY() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getHeight()) {
                        this.startMenuResizeMode(this.getActiveOrder(i), nPosX, nPosY, true);
                        this.iSliderMenuActionDownPosX = nPosX;
                        this.setOrderOfMenu(this.getActiveOrder(i));
                        return true;
                    }
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        } catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
        }

        return false;
    }

    protected final void startMenuResizeMode(int i, int nPosX, int nPosY, boolean sliderMenuResizeLEFT) {
        this.menuResizeMode = true;
        this.activeSliderMenuID = i;
        this.iSliderMenuStartPosX = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getWidth() - nPosX;
        this.iSliderMenuStartPosY = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getHeight() - nPosY;
        this.menuResizeLEFT = sliderMenuResizeLEFT;
    }

    private final void actionMove_ResizeMenu(int nPosX, int nPosY) {
        ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setHeight(nPosY + this.iSliderMenuStartPosY);
        ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setMenuPosY(((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuPosY());
        ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).updateScrollable();
        ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).updateMenuElements_IsInView();
        if (this.menuResizeLEFT) {
            if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setWidth_Resize(((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getWidth() - (nPosX - this.iSliderMenuActionDownPosX))) {
                this.iSliderMenuActionDownPosX = nPosX;
            } else {
                this.iSliderMenuActionDownPosX = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX();
            }

            if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX() < 0) {
                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setWidth(((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getWidth() + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX());
                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setPosX(0);
            }
        } else {
            ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setWidth(nPosX + this.iSliderMenuStartPosX);
            if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX() + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getWidth() > CFG.GAME_WIDTH) {
                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setWidth(CFG.GAME_WIDTH - ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX());
            }
        }

    }

    private final boolean actionDown_MenuTitle(int nPosX, int nPosY) {
        try {
            for(int i = 0; i < this.getActiveMenu().size(); ++i) {
                if (((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getVisible() && ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getTitle() != null && nPosX >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosX() && nPosX <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosX() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getWidth() && nPosY >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosY() - ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getTitle().getHeight() && nPosY <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getPosY()) {
                    this.menu_MoveByTitleMode = true;
                    this.activeSliderMenuID = this.getActiveOrder(i);
                    this.iSliderMenuStartPosX = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX() - nPosX;
                    this.iSliderMenuStartPosY = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosY() - nPosY;
                    this.iSliderMenuActionDownPosX = nPosX;
                    this.iSliderMenuActionDownPosY = nPosY;
                    this.setOrderOfMenu(this.getActiveOrder(i));
                    ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).stopScrolling();
                    return true;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return false;
    }

    private final void actionMove_MenuTitle(int nPosX, int nPosY) {
        if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMoveable()) {
            if (nPosX + this.iSliderMenuStartPosX + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getWidth() - ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMinWidth() > 0 && nPosX + this.iSliderMenuStartPosX < CFG.GAME_WIDTH - ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMinWidth()) {
                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setPosX(nPosX + this.iSliderMenuStartPosX);
                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).updateMenuPosX(((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuPosX() + (nPosX - this.iSliderMenuActionDownPosX));
                this.iSliderMenuActionDownPosX = nPosX;
            }

            if (nPosY + this.iSliderMenuStartPosY > ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMinHeight() && nPosY + this.iSliderMenuStartPosY < CFG.GAME_HEIGHT - ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMinHeight()) {
                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).setPosY(nPosY + this.iSliderMenuStartPosY);
                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).updateMenuPosY(((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuPosY() + (nPosY - this.iSliderMenuActionDownPosY));
                this.iSliderMenuActionDownPosY = nPosY;
            }
        }

    }

    private final void actionMove_ScrollPosX(int nPosX, int nPosY) {
        if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getScrollable()) {
            if (this.updateSliderMenuPosX) {
                this.iSliderMenuStartPosX = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getScrollPosX() - nPosX;
                this.updateSliderMenuPosX = false;
            } else {
                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).setScrollPosX(this.iSliderMenuStartPosX + nPosX);
            }
        }

    }

    private final void actionMove_ScrollPosY(int nPosX, int nPosY) {
        if (((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getScrollable()) {
            if (this.updateSliderMenuPosY) {
                this.iSliderMenuStartPosY = ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).getScrollPosY() - nPosY;
                this.updateSliderMenuPosY = false;
            } else {
                ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getMenuElement(this.activeMenuElementID).setScrollPosY(this.iSliderMenuStartPosY + nPosY);
            }
        }

    }

    private final boolean actionDown_CloseMenu(int nPosX, int nPosY) {
        try {
            for(int i = 0; i < this.getActiveMenu().size(); ++i) {
                if (((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getVisible() && ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).getCloseable() && nPosX >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).menuClose.getCloseMenu_PosX() && nPosX <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).menuClose.getCloseMenu_PosX() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).menuClose.getCloseMenu_Width() && nPosY >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).menuClose.getCloseMenu_PosY() && nPosY <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).menuClose.getCloseMenu_PosY() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(i))).menuClose.getCloseMenu_Height()) {
                    this.activeSliderMenuID = i;
                    this.closeMenuMode = true;
                    return true;
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            CFG.exceptionStack(ex);
        } catch (NullPointerException ex) {
            CFG.exceptionStack(ex);
        }

        return false;
    }

    private final void actionUp_CloseMenu(int nPosX, int nPosY) {
        if (((Menu)this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID))).getVisible() && ((Menu)this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID))).getCloseable() && nPosX >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID))).menuClose.getCloseMenu_PosX() && nPosX <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID))).menuClose.getCloseMenu_PosX() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID))).menuClose.getCloseMenu_Width() && nPosY >= ((Menu)this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID))).menuClose.getCloseMenu_PosY() && nPosY <= ((Menu)this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID))).menuClose.getCloseMenu_PosY() + ((Menu)this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID))).menuClose.getCloseMenu_Height()) {
            ((Menu)this.getActiveMenu().get(this.getActiveOrder(this.activeSliderMenuID))).actionCloseMenu();
        }

    }

    private final void actionMoveSlider(int nPosX, int nPosY) {
        if (this.getActiveMenuElement().getClickable()) {
            if (nPosX >= this.getActiveMenuElement().getPosX() + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX() && nPosX <= this.getActiveMenuElement().getPosX() + this.getActiveMenuElement().getWidth() + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX()) {
                this.getActiveMenuElement().updateSlider(nPosX - ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX());
            } else if (nPosX < this.getActiveMenuElement().getPosX() + ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).getPosX()) {
                this.getActiveMenuElement().updateSlider(this.getActiveMenuElement().getPosX());
            } else {
                this.getActiveMenuElement().updateSlider(this.getActiveMenuElement().getPosX() + this.getActiveMenuElement().getWidth());
            }

            ((Menu)this.getActiveMenu().get(this.activeSliderMenuID)).actionElement(this.activeMenuElementID);
        }

    }

    public final void setOrderOfMenu_InGame() {
        this.setOrderOfMenu(0);
        this.setOrderOfMenu(this.IN_GAME_MAPMODES);
        this.setOrderOfMenu(this.IN_GAME_COURT_OPTIONS2);
        orderOfMenuInGame = true;
    }

    public final void setOrderOfMenu_InGameBattle() {
        this.setOrderOfMenu(this.IN_GAME_BATTLE);
        this.setOrderOfMenu(this.IN_GAME_BATTLE_ARMY);
        this.setOrderOfMenu(this.IN_GAME_BATTLE_ARMY_DEFENDERS);
    }

    public final void setOrderOfMenu_Scenarios() {
        this.setOrderOfMenu(0);
        this.setOrderOfMenu(1);
    }

    public final void setOrderOfMenu_InGameProvinceInfo() {
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_INFO);
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_INFO_BUILDINGS);
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_INFO_ARMY);
    }

    public final void setOrderOfMenu_InGameCourt() {
        this.setOrderOfMenu(this.IN_GAME_COURT);
        this.setOrderOfMenu(this.IN_GAME_COURT_OPTIONS);
        this.setOrderOfMenu(this.IN_GAME_COURT_OPTIONS2);
    }

    public final void setOrderOfMenu_InGameProvinceArmy() {
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY);
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY_UNITS);
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY_TOP_BAR);
        this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY_MOVE);
    }

    public final void setOrderOfMenu_InGameBuildings() {
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_0);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_1);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_2);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_3);
    }

    public final void setOrderOfMenu_InGameLegacies() {
        this.setOrderOfMenu(this.IN_GAME_LEGACIES_EMPTY);
        this.setOrderOfMenu(0);
        this.setOrderOfMenu(this.IN_GAME_LEGACIES_INFO);
    }

    public final void setOrderOfMenu_MainMenu_Stats() {
        this.setOrderOfMenu(1);
        this.setOrderOfMenu(0);
    }

    public final void setOrderOfMenu_InGameEscape() {
        this.setOrderOfMenu(this.IN_GAME_ESCAPE_EMPTY);
        this.setOrderOfMenu(this.IN_GAME_ESCAPE);
    }

    public final void setOrderOfMenu_InGameGoods() {
        this.setOrderOfMenu(this.IN_GAME_GOODS_EMPTY);
        this.setOrderOfMenu(this.IN_GAME_GOODS);
    }

    public final int getActiveMenuElementID() {
        return this.activeMenuElementID;
    }

    public final int getActiveSliderMenuID() {
        return this.activeSliderMenuID;
    }

    public final void setActiveSliderMenuID(int activeSliderMenuID) {
        this.activeSliderMenuID = activeSliderMenuID;
    }

    public final void setActiveMenuElementID(int activeMenuElementID) {
        this.activeMenuElementID = activeMenuElementID;
    }

    public final void setUpdateSliderMenuPosY(boolean updateSliderMenuPosY) {
        this.updateSliderMenuPosY = updateSliderMenuPosY;
    }

    public final void setUpdateSliderMenuPosX(boolean updateSliderMenuPosX) {
        this.updateSliderMenuPosX = updateSliderMenuPosX;
    }

    public final void setPieChartMode(boolean pieChartMode) {
        this.pieChartMode = pieChartMode;
    }

    public final boolean getTextScrollableMode() {
        return this.textScrollableMode;
    }

    public final void setTextScrollableMode(boolean textScrollableMode) {
        this.textScrollableMode = textScrollableMode;
    }

    public final boolean getMenu_MoveInnerElements() {
        return this.menu_MoveInnerElements;
    }

    public final void setMenu_MoveInnerElements(boolean menu_MoveInnerElements) {
        this.menu_MoveInnerElements = menu_MoveInnerElements;
    }

    public final void setBackAnimation(boolean backAnimation) {
        this.backAnimation = backAnimation;
    }

    public final boolean getMoveMenu_ByTitleMode() {
        return this.menu_MoveByTitleMode;
    }

    public final void setMenu_MoveByTitleMode(boolean menu_MoveByTitleMode) {
        this.menu_MoveByTitleMode = menu_MoveByTitleMode;
    }

    public final boolean getMenuResizeLEFT() {
        return this.menuResizeLEFT;
    }

    public final boolean getCloseMenuMode() {
        return this.closeMenuMode;
    }

    public final void setCloseMenuMode(boolean closeMenuMode) {
        this.closeMenuMode = closeMenuMode;
    }

    public final int getFromViewID() {
        return this.fromViewID;
    }

    public final ColorPicker getColorPicker() {
        return this.colorPicker;
    }

    public final boolean getMenuResizeMode() {
        return this.menuResizeMode;
    }

    public final void setMenu_ResizeMode(boolean menuResizeMode) {
        this.menuResizeMode = menuResizeMode;
    }

    public final boolean getColorPickerMode() {
        return this.colorPickerMode;
    }

    public final void setColorPickerMode(boolean colorPickerMode) {
        this.colorPickerMode = colorPickerMode;
    }

    public final boolean getInGame() {
        return this.viewID == this.IN_GAME || this.getInGameHideUI();
    }

    public final boolean getInMultiLobby() {
        return this.viewID == this.MULTIPLAYER_LOBBY;
    }

    public final boolean getInMultiGames() {
        return this.viewID == this.MULTIPLAYER;
    }

    public final boolean getInGameLegacies() {
        return this.viewID == this.IN_GAME_LEGACIES;
    }

    public final boolean getInGameHideUI() {
        return this.viewID == this.IN_GAME_HIDE_UI;
    }

    public final boolean getInInitGameMenu() {
        return this.viewID == this.INIT_GAME_MENU;
    }

    public final boolean getInInitGame_Menus() {
        return this.viewID == this.INIT_GAME_MENU_SELECT_MAP || this.viewID == this.INIT_GAME_MENU_LANGUAGE;
    }

    public final boolean getInLoadScenario() {
        return this.viewID == this.LOAD_STUFF;
    }

    public final boolean getInMainMenu() {
        return this.viewID == this.MAINMENU || this.viewID == this.MAINMENU_STATS;
    }

    public final boolean getInLoadGamesList() {
        return this.viewID == this.LOAD_GAMES_LIST;
    }

    public final boolean getInSettingsMenu() {
        return this.viewID == this.SETTINGS;
    }

    public final boolean getInScenarios_NewGame() {
        return this.viewID == this.SCENARIOS;
    }

    public final boolean getInEditorGameCivsEdit() {
        return this.viewID == this.EDITOR_GAMECIVS_EDIT;
    }

    public final boolean getInEditorCreateCiv() {
        return this.viewID == this.CREATE_CIV;
    }

    public final boolean getInMapEditorGrowthRate() {
        return this.viewID == this.EDITOR_MAPS_EDIT_GROWTH_RATE;
    }

    public final boolean getInNewGame_Scenarios() {
        return this.viewID == this.SCENARIOS;
    }

    public final boolean getInNewGame_ScenariosCampaign() {
        return this.viewID == this.SCENARIOS_CAMPAIGN;
    }

    public final boolean getInMapEditorEconomy() {
        return this.viewID == this.EDITOR_MAPS_EDIT_ECONOMY;
    }

    public final boolean getInMapEditorLines() {
        return this.viewID == this.EDITOR_MAPS_EDIT_LINES;
    }

    public final boolean getInMapEditorWaves() {
        return this.viewID == this.EDITOR_MAPS_EDIT_WAVES;
    }

    public final boolean getInMapEditorSeaProvinces() {
        return this.viewID == this.EDITOR_MAPS_EDIT_SEA_PROVINCES;
    }

    public final boolean getInMapEditorArmyPosition() {
        return this.viewID == this.EDITOR_MAPS_EDIT_ARMY_POSITION;
    }

    public final boolean getInMapEditorProvinceNamePoints() {
        return this.viewID == this.EDITOR_MAPS_EDIT_PROVINCE_NAMES;
    }

    public final boolean getInScenarioEditor_CreateAllianceEdit() {
        return this.viewID == this.SCENARIO_CREATE_ALLIANCE_EDIT;
    }

    public final boolean getInMapEditorTerrain() {
        return this.viewID == this.EDITOR_MAPS_EDIT_TERRAIN;
    }

    public final boolean getInScenarioEditorTechnologiesCivs() {
        return this.viewID == this.SCENARIO_TECHNOLOGIES_CIVS;
    }

    public final boolean getInScenarioEditorArmies() {
        return this.viewID == this.SCENARIO_ARMIES;
    }

    public final boolean getInScenarioEditorGovernment() {
        return this.viewID == this.SCENARIO_GOVERNMENT;
    }

    public final boolean getInScenarioEditorBuildings() {
        return this.viewID == this.SCENARIO_BUILDINGS;
    }

    public final boolean getInScenarioEditorRelations() {
        return this.viewID == this.SCENARIO_RELATIONS;
    }

    public final boolean getInScenarioEditorAlliances() {
        return this.viewID == this.SCENARIO_ALLIANCES;
    }

    public final boolean getInScenarioEditorVassals() {
        return this.viewID == this.SCENARIO_VASSALS;
    }

    public final boolean getInScenarioEditorTruces() {
        return this.viewID == this.SCENARIO_TRUCES;
    }

    public final boolean getInScenarioEditorDeclareWar() {
        return this.viewID == this.SCENARIO_DECLARE_WAR;
    }

    public final boolean getInScenarioEditorNonAggression() {
        return this.viewID == this.SCENARIO_NON_AGGRESSION;
    }

    public final boolean getInScenarioEditorMilitaryAccess() {
        return this.viewID == this.SCENARIO_MILITARY_ACCESS;
    }

    public final boolean getInScenarioEditorDefensive() {
        return this.viewID == this.SCENARIO_DEFENSIVE;
    }

    public final boolean getInScenarioEditorGuarantee() {
        return this.viewID == this.SCENARIO_GUARANTEE;
    }

    public final boolean getInMapEditorResource() {
        return this.viewID == this.EDITOR_MAPS_EDIT_RESOURCE;
    }

    public final boolean getInEditorSelectProvinces() {
        return this.viewID == this.EDITOR_SELECT_PROVINCES;
    }

    public final boolean getInMapEditorContinents() {
        return this.viewID == this.EDITOR_MAPS_EDIT_CONTINENTS;
    }

    public final boolean getInMapEditorOptimizationRegions() {
        return this.viewID == this.EDITOR_MAPS_EDIT_OPTIMIZATION_REGIONS;
    }

    public final boolean getInMapEditorGeoRegions() {
        return this.viewID == this.EDITOR_MAPS_EDIT_GEO_REGION;
    }

    public final boolean getInMapEditorPortPosition() {
        return this.viewID == this.EDITOR_MAPS_EDIT_PORT_POSITION;
    }

    public final boolean getInMapEditorProvinceConnections() {
        return this.viewID == this.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS;
    }

    public final boolean getInNewGame() {
        return this.viewID == this.NEW_GAME;
    }

    public final boolean getInGameLost() {
        return this.viewID == this.GAME_LOST;
    }

    public final boolean getInCloudsMenu() {
        return this.viewID == this.CLOUDS_MENU;
    }

    public final boolean getInScenariosList() {
        return this.viewID == this.EDITOR_SCENARIOS_LIST;
    }

    public final boolean getInScenarioWasteland() {
        return this.viewID == this.SCENARIO_WASTELAND;
    }

    public final boolean getInScenarioWastelandContinents() {
        return this.viewID == this.SCENARIO_WASTELAND_CONTINENTS;
    }

    public final boolean getInPrintMap() {
        return this.viewID == this.PRINT_A_MAP;
    }

    public final boolean getInScenarioCivilizations() {
        return this.viewID == this.SCENARIO_CIVILIZATIONS;
    }

    public final boolean getInScenarioAssign() {
        return this.viewID == this.SCENARIO_ASSIGN;
    }

    public final boolean getInScenarioAssignInGame() {
        return this.viewID == this.SCENARIO_ASSIGN_IN_GAME;
    }

    public final boolean getInScenarioCores() {
        return this.viewID == this.SCENARIO_CORES;
    }

    public final boolean getInScenarioReligion() {
        return this.viewID == this.SCENARIO_RELIGION;
    }

    public final boolean getInScenarioSettings() {
        return this.viewID == this.SCENARIO_SETTINGS;
    }

    public final boolean getInEditorFormableCiv() {
        return this.viewID == this.EDITOR_MAPS_EDIT_FORMABLE_CIV;
    }

    public final void rebuildScenarioReligionList() {
        int nX = ((Menu)((List)this.menus.get(this.SCENARIO_RELIGION)).get(this.SCENARIO_RELIGION_LIST)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.SCENARIO_RELIGION)).get(this.SCENARIO_RELIGION_LIST)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.SCENARIO_RELIGION)).get(this.SCENARIO_RELIGION_LIST)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.SCENARIO_RELIGION)).get(this.SCENARIO_RELIGION_LIST)).getMenuPosY();
        ((Menu)((List)this.menus.get(this.SCENARIO_RELIGION)).get(this.SCENARIO_RELIGION_LIST)).setVisible(false);
        ((List)this.menus.get(this.SCENARIO_RELIGION)).set(this.SCENARIO_RELIGION_LIST, new ScenarioReligion_List());
        ((Menu)((List)this.menus.get(this.SCENARIO_RELIGION)).get(this.SCENARIO_RELIGION_LIST)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.SCENARIO_RELIGION)).get(this.SCENARIO_RELIGION_LIST)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.SCENARIO_RELIGION)).get(this.SCENARIO_RELIGION_LIST)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.SCENARIO_RELIGION)).get(this.SCENARIO_RELIGION_LIST)).setMenuPosY(mY);
    }

    public final void rebuildScenarioCoresList() {
        int nX = ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST)).getMenuPosY();
        ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST)).setVisible(false);
        ((List)this.menus.get(this.SCENARIO_CORES)).set(this.SCENARIO_CORES_LIST, new ScenarioCores_List());
        ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST)).setMenuPosY(mY);
    }

    public final void rebuildScenarioCoresList_InProvince() {
        int nX = ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST_IN_PROVINCE)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST_IN_PROVINCE)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST_IN_PROVINCE)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST_IN_PROVINCE)).getMenuPosY();
        ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST_IN_PROVINCE)).setVisible(false);
        ((List)this.menus.get(this.SCENARIO_CORES)).set(this.SCENARIO_CORES_LIST_IN_PROVINCE, new ScenarioCores_InProvinces());
        ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST_IN_PROVINCE)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST_IN_PROVINCE)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST_IN_PROVINCE)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.SCENARIO_CORES)).get(this.SCENARIO_CORES_LIST_IN_PROVINCE)).setMenuPosY(mY);
    }

    public final void rebuildScenarioCivilizationsList() {
        int nX = ((Menu)((List)this.menus.get(this.SCENARIO_CIVILIZATIONS)).get(this.SCENARIO_CIVILIZATIONS_LIST)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.SCENARIO_CIVILIZATIONS)).get(this.SCENARIO_CIVILIZATIONS_LIST)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.SCENARIO_CIVILIZATIONS)).get(this.SCENARIO_CIVILIZATIONS_LIST)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.SCENARIO_CIVILIZATIONS)).get(this.SCENARIO_CIVILIZATIONS_LIST)).getMenuPosY();
        ((Menu)((List)this.menus.get(this.SCENARIO_CIVILIZATIONS)).get(this.SCENARIO_CIVILIZATIONS_LIST)).setVisible(false);
        ((List)this.menus.get(this.SCENARIO_CIVILIZATIONS)).set(this.SCENARIO_CIVILIZATIONS_LIST, new ScenarioCivilizationsList());
        ((Menu)((List)this.menus.get(this.SCENARIO_CIVILIZATIONS)).get(this.SCENARIO_CIVILIZATIONS_LIST)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.SCENARIO_CIVILIZATIONS)).get(this.SCENARIO_CIVILIZATIONS_LIST)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.SCENARIO_CIVILIZATIONS)).get(this.SCENARIO_CIVILIZATIONS_LIST)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.SCENARIO_CIVILIZATIONS)).get(this.SCENARIO_CIVILIZATIONS_LIST)).setMenuPosY(mY);
    }

    public final void rebuildEditorCivSelect() {
        int nX = ((Menu)((List)this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT)).get(0)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT)).get(0)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT)).get(0)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT)).get(0)).getMenuPosY();
        ((Menu)((List)this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT)).get(0)).setVisible(false);
        ((List)this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT)).set(0, new EditorMap_CivSelect());
        ((Menu)((List)this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT)).get(0)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT)).get(0)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT)).get(0)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.EDITOR_MAPS_EDIT_CIV_SELECT)).get(0)).setMenuPosY(mY);
    }

    public final Menu rebuildEditorMapProvinceConnectionsList() {
        return (Menu)((List)this.menus.get(this.EDITOR_MAPS_EDIT_PROVINCE_CONNECTIONS)).set(1, new EditorMapProvinceConnectionsList());
    }

    public final void rebuildSettingsAudio() {
        ((List)this.menus.get(this.SETTINGS)).set(1, new InGame_Audio());
        ((Menu)((List)this.menus.get(this.SETTINGS)).get(1)).setVisible(true);
        this.setOrderOfMenu(1);
    }

    public final boolean getVisibleSettingsAudio() {
        return ((Menu)((List)this.menus.get(this.SETTINGS)).get(1)).getVisible();
    }

    public final void setVisibleSettingsAudio(boolean visible) {
        ((Menu)((List)this.menus.get(this.SETTINGS)).get(1)).setVisible(visible);
    }

    public final int getInGame_MapModesPosY() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_MAPMODES)).getPosY();
    }

    public final boolean getVisibleInGame_Escape() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ESCAPE)).getVisible();
    }

    public final void setVisibleInGame_Escape(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ESCAPE_EMPTY)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ESCAPE)).setVisible(visible);
        if (visible) {
            if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEFAULT) {
                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
            }

            this.setOrderOfMenu_InGameEscape();
        }

        Game.mapBG.updateActiveMapBGShader();
    }

    public final void setVisibleInGame_ProvinceArmy(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_UNITS)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_MOVE)).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY);
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY_UNITS);
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY_TOP_BAR);
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_ARMY_MOVE);
        }

    }

    public final void rebuildInGame_ProvinceArmy_HideMenus() {
        if (this.getVisibleInGame_ProvinceInfo()) {
            this.setVisibleInGame_ProvinceInfo(false);
        }

        if (this.getVisibleInGame_RecruitArmy()) {
            this.setVisibleInGame_RecruitArmy(false);
        }

        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }

        if (this.getVisibleInGame_Buildings()) {
            this.setVisibleInGame_Buildings(false, false);
        }

        if (this.getVisibleInGame_Battle()) {
            this.setVisibleInGame_Battle(false);
        }

        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }

        if (this.getVisibleInGame_War()) {
            this.setVisibleInGame_War(false);
        }

        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }

        if (this.getVisibleInGame_DisbandArmy()) {
            this.setVisibleInGame_DisbandUnits(false);
        }

        if (this.getVisibleInGame_ReorganizeUnits()) {
            this.setVisibleInGame_ReorganizeUnits(false);
        }

        this.setOrderOfMenu_InGameProvinceArmy();
        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }

        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }

        if (this.getVisibleInGame_TechnologyTree()) {
            this.setVisibleInGame_TechnologyTree(false);
        }

        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }

    }

    public final void rebuildInGame_ProvinceArmy() {
        this.rebuildInGame_ProvinceArmy(false, true);
    }

    public final void rebuildInGame_ProvinceArmy_RegroupArmy() {
        if (Game.regroupArmyMode) {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PROVINCE_ARMY_UNITS, new InGame_ProvinceArmy_Regroup());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_UNITS)).setVisible(true);
        } else {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PROVINCE_ARMY_UNITS, new InGame_ProvinceArmyUnits());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_UNITS)).setVisible(true);
        }

    }

    public final void rebuildInGame_ProvinceArmy_InvasionArmy() {
        if (Game.invasionArmyMode) {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PROVINCE_ARMY_UNITS, new InGame_ProvinceArmy_Invasion());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_UNITS)).setVisible(true);
        } else {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PROVINCE_ARMY_UNITS, new InGame_ProvinceArmyUnits());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_UNITS)).setVisible(true);
        }

    }

    public final void rebuildInGame_ProvinceArmy(boolean keepMenuPos, boolean updateOrder) {
        if (Game.activeArmySize > 0) {
            boolean odlVisible = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY)).getVisible();
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY)).dispose();
            int nX = 0;
            int nY = 0;
            int mX = 0;
            int mY = 0;
            int nX2 = 0;
            int nY2 = 0;
            int mX2 = 0;
            int mY2 = 0;
            int nX3 = 0;
            int nY3 = 0;
            int mX3 = 0;
            int mY3 = 0;
            if (keepMenuPos && odlVisible) {
                nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY)).getPosX();
                mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY)).getMenuPosX();
                mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY)).getMenuPosY();
                nX2 = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_UNITS)).getPosX();
                mX2 = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_UNITS)).getMenuPosX();
                mY2 = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_UNITS)).getMenuPosY();
                nX3 = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR)).getPosX();
                mX3 = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR)).getMenuPosX();
                mY3 = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR)).getMenuPosY();
            }

            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PROVINCE_ARMY, new InGame_ProvinceArmy());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY)).setVisible(true);
            if (Game.invasionArmyMode) {
                this.rebuildInGame_ProvinceArmy_InvasionArmy();
            } else {
                this.rebuildInGame_ProvinceArmy_RegroupArmy();
            }

            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PROVINCE_ARMY_TOP_BAR, new InGame_ProvinceArmyTopBar());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR)).setVisible(true);
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PROVINCE_ARMY_MOVE, new InGame_ProvinceArmy_Move());
            Game.hoverManager.rebuildHoverAfterRebuildMenu();
            if (keepMenuPos && odlVisible) {
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY)).setPosX(nX);
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY)).setMenuPosX(mX);
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY)).setMenuPosY(mY);
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_UNITS)).setPosX(nX2);
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_UNITS)).setMenuPosX(mX2);
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_UNITS)).setMenuPosY(mY2);
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR)).setPosX(nX3);
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR)).setMenuPosX(mX3);
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR)).setMenuPosY(mY3);
            }

            if (odlVisible) {
                InGame_ProvinceArmy.lTime = 0L;
            }

            if (updateOrder) {
                this.hideCourtCiv();
                this.setOrderOfMenu_InGameProvinceArmy();
                if (this.getVisibleInGame_GoodsMarket()) {
                    this.setVisibleInGame_GoodsMarket(false);
                }
            }
        } else {
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY)).setVisible(false);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_UNITS)).setVisible(false);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_TOP_BAR)).setVisible(false);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY_MOVE)).setVisible(false);
        }

    }

    public final void rebuildInGame() {
        if (this.IN_GAME >= 0) {
            ((List)this.menus.get(this.IN_GAME)).set(0, new InGame());
        }

    }

    public final boolean getVisibleInGame_Battle() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE)).getVisible();
    }

    public final boolean getVisibleInGame_Siege() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_SIEGE)).getVisible();
    }

    public final boolean getVisibleInGame_War() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_WAR)).getVisible();
    }

    public final boolean getVisibleInGame_Peace() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PEACE)).getVisible();
    }

    public final void hideCourtCiv() {
        this.setVisibleInGame_Court(false);
        this.setVisibleInGame_Civ(false);
        this.setVisibleInGame_Budget(false);
    }

    public final void setVisibleInGame_Battle(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_ARMY)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_ARMY_DEFENDERS)).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setOrderOfMenu(this.IN_GAME_BATTLE);
            this.setOrderOfMenu(this.IN_GAME_BATTLE_ARMY);
            this.setOrderOfMenu(this.IN_GAME_BATTLE_ARMY_DEFENDERS);
        }

    }

    public final void setVisibleInGame_Siege(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_SIEGE)).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setOrderOfMenu(this.IN_GAME_SIEGE);
        }

    }

    public final void setVisibleInGame_War(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_WAR)).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setOrderOfMenu(this.IN_GAME_WAR);
        }

    }

    public final void setVisibleInGame_Peace(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PEACE)).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setOrderOfMenu(this.IN_GAME_PEACE);
        }

    }

    public final void showInGame_Battle_HideMenus() {
        if (this.getVisibleInGame_ProvinceInfo()) {
            this.setVisibleInGame_ProvinceInfo(false);
        }

        if (this.getVisibleInGame_RecruitArmy()) {
            this.setVisibleInGame_RecruitArmy(false);
        }

        if (this.getVisibleInGame_Buildings()) {
            this.setVisibleInGame_Buildings(false, false);
        }

        if (this.getVisibleInGame_ProvinceArmy()) {
            this.setVisibleInGame_ProvinceArmy(false);
        }

        if (this.getVisibleInGame_DisbandArmy()) {
            this.setVisibleInGame_DisbandUnits(false);
        }

        if (this.getVisibleInGame_ReorganizeUnits()) {
            this.setVisibleInGame_ReorganizeUnits(false);
        }

        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }

        if (this.getVisibleInGame_TechnologyTree()) {
            this.setVisibleInGame_TechnologyTree(false);
        }

        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }

        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }

        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }

    }

    public final void rebuildInGame_Siege() {
        boolean odlVisible = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_SIEGE)).getVisible();
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_SIEGE)).dispose();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_SIEGE, new InGame_ProvinceSiege());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_SIEGE)).setVisible(true);
        Game.hoverManager.rebuildHoverAfterRebuildMenu();
        this.setOrderOfMenu(this.IN_GAME_SIEGE);
        if (odlVisible) {
            InGame_ProvinceSiege.lTime = 0L;
        }

        this.hideCourtCiv();
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }

    }

    public final void rebuildInGame_War() {
        boolean odlVisible = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_WAR)).getVisible();
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_WAR)).dispose();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_WAR, new InGame_War());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_WAR)).setVisible(true);
        Game.hoverManager.rebuildHoverAfterRebuildMenu();
        this.setOrderOfMenu(this.IN_GAME_WAR);
        if (odlVisible) {
            InGame_War.lTime = 0L;
        }

        this.hideCourtCiv();
        this.showInGame_Battle_HideMenus();
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }

        if (this.getVisibleInGame_Battle()) {
            this.setVisibleInGame_Battle(false);
        }

        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }

        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }

    }

    public final void rebuildInGame_Peace() {
        boolean odlVisible = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PEACE)).getVisible();
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PEACE)).dispose();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PEACE, new InGame_Peace());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PEACE)).setVisible(true);
        Game.hoverManager.rebuildHoverAfterRebuildMenu();
        this.setOrderOfMenu(this.IN_GAME_PEACE);
        if (odlVisible) {
            InGame_War.lTime = 0L;
        }

        this.hideCourtCiv();
        this.showInGame_Battle_HideMenus();
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }

        if (this.getVisibleInGame_Battle()) {
            this.setVisibleInGame_Battle(false);
        }

        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }

    }

    public final void rebuildInGame_Battle() {
        boolean odlVisible = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE)).getVisible();
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE)).dispose();

        try {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BATTLE, new InGame_Battle());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE)).setVisible(InGame_Battle.battleID >= 0);
        } catch (Exception var4) {
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE)).setVisible(false);
        }

        if (InGame_Battle.battleID >= 0) {
            try {
                ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BATTLE_ARMY, new InGame_BattleArmy());
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_ARMY)).setVisible(((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE)).getVisible());
                ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BATTLE_ARMY_DEFENDERS, new InGame_BattleArmyDefenders());
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_ARMY_DEFENDERS)).setVisible(((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE)).getVisible());
            } catch (Exception var3) {
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_ARMY)).setVisible(false);
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_ARMY_DEFENDERS)).setVisible(false);
            }
        } else {
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_ARMY)).setVisible(false);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_ARMY_DEFENDERS)).setVisible(false);
        }

        Game.hoverManager.rebuildHoverAfterRebuildMenu();
        this.setOrderOfMenu(this.IN_GAME_BATTLE);
        this.setOrderOfMenu(this.IN_GAME_BATTLE_ARMY);
        this.setOrderOfMenu(this.IN_GAME_BATTLE_ARMY_DEFENDERS);
        if (odlVisible) {
            InGame_Battle.lTime = 0L;
        }

        this.hideCourtCiv();
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }

    }

    public final void setVisibleInGame_ProvinceInfo(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_INFO)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_INFO_BUILDINGS)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_INFO_ARMY)).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_INFO);
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_INFO_BUILDINGS);
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_INFO_ARMY);
        } else {
            Keyboard var10000 = Game.keyboard;
            if (Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.INGAME_RENAME_PROVINCE) {
                Game.keyboard.hideKeyboard();
            }

            this.setVisibleInGame_Buildings(false, false);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_BONUSES)).setVisible(false);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_INFO_ARMY)).setVisible(false);
        }

    }

    public final void rebuildInGame_ProvinceInfo(boolean updateOrder) {
        boolean odlVisible = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_INFO)).getVisible();
        Keyboard var10000 = Game.keyboard;
        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.INGAME_RENAME_PROVINCE) {
            Game.keyboard.hideKeyboard();
        }

        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PROVINCE_INFO, new InGame_ProvinceInfo());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_INFO)).setVisible(true);
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PROVINCE_INFO_BUILDINGS, new InGame_ProvinceInfoBuildings());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_INFO_BUILDINGS)).setVisible(Game.getProvince(InGame_ProvinceInfo.iProvinceID).getCivID() > 0);
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PROVINCE_INFO_ARMY, new InGame_ProvinceInfo_Army());
        Game.hoverManager.rebuildHoverAfterRebuildMenu();
        if (updateOrder) {
            this.setOrderOfMenu_InGameProvinceInfo();
        }

        if (odlVisible) {
            InGame_ProvinceInfo.lTime = 0L;
        }

    }

    public final void rebuildInGame_ScenarioEditorArmies() {
        ((List)this.menus.get(this.SCENARIO_ARMIES)).set(1, new ScenarioArmies_List());
    }

    public final void rebuildInGame_ScenarioEditorGovernment() {
        ((List)this.menus.get(this.SCENARIO_GOVERNMENT)).set(1, new ScenarioGovernment_List());
    }

    public final void rebuildInGame_ScenarioEditorBuildings() {
        ((List)this.menus.get(this.SCENARIO_BUILDINGS)).set(1, new ScenarioBuildings_List());
    }

    public final void rebuildInGame_ScenarioEditorRelations() {
        ((List)this.menus.get(this.SCENARIO_RELATIONS)).set(1, new ScenarioRelationsList());
    }

    public final void rebuildInGame_ScenarioEditorAlliances() {
        ((List)this.menus.get(this.SCENARIO_ALLIANCES)).set(1, new ScenarioAlliancesList());
    }

    public final void rebuildInGame_ScenarioEditorCreateAlliance_Edit() {
        ((List)this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT)).set(1, new ScenarioCreateAllianceList());
    }

    public final void rebuildInGame_ScenarioEditorCreateAlliance_Edit_SavePos() {
        int nX = ((Menu)((List)this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT)).get(1)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT)).get(1)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT)).get(1)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT)).get(1)).getMenuPosY();
        ((List)this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT)).set(1, new ScenarioCreateAllianceList());
        ((Menu)((List)this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT)).get(1)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT)).get(1)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT)).get(1)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.SCENARIO_CREATE_ALLIANCE_EDIT)).get(1)).setMenuPosY(mY);
    }

    public final void rebuildInGame_ScenarioEditorVassals() {
        ((List)this.menus.get(this.SCENARIO_VASSALS)).set(1, new ScenarioVassalsList());
    }

    public final void rebuildInGame_ScenarioEditorTruces() {
        ((List)this.menus.get(this.SCENARIO_TRUCES)).set(1, new ScenarioTrucesList());
    }

    public final void rebuildInGame_ScenarioEditorDeclareWar() {
        ((List)this.menus.get(this.SCENARIO_DECLARE_WAR)).set(1, new ScenarioDeclareWarList());
    }

    public final void rebuildInGame_ScenarioEditorNonAggression() {
        ((List)this.menus.get(this.SCENARIO_NON_AGGRESSION)).set(1, new ScenarioNonAggressionList());
    }

    public final void rebuildInGame_ScenarioEditorMilitaryAccess() {
        ((List)this.menus.get(this.SCENARIO_MILITARY_ACCESS)).set(1, new ScenarioMilitaryAccessList());
    }

    public final void rebuildInGame_ScenarioEditorDefensive() {
        ((List)this.menus.get(this.SCENARIO_DEFENSIVE)).set(1, new ScenarioDefensiveList());
    }

    public final void rebuildInGame_ScenarioEditorGuarantee() {
        ((List)this.menus.get(this.SCENARIO_GUARANTEE)).set(1, new ScenarioGuaranteeList());
    }

    public final void rebuildInGame_ProvinceInfo_Army() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PROVINCE_INFO_ARMY, new InGame_ProvinceInfo_Army());
        Game.hoverManager.rebuildHoverAfterRebuildMenu();
    }

    public final void setVisibleInGame_ReorganizeUnits(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_REORGANIZE_UNITS)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_REORGANIZE_UNITS);
        }

    }

    public final void rebuildInGame_ReorganizeUnits() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_REORGANIZE_UNITS)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_REORGANIZE_UNITS)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_REORGANIZE_UNITS)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_REORGANIZE_UNITS)).getMenuPosY();
        InGame_ReorganizeUnits.restartAnimation = !((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_REORGANIZE_UNITS)).getVisible();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_REORGANIZE_UNITS, new InGame_ReorganizeUnits());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_REORGANIZE_UNITS)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_REORGANIZE_UNITS)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_REORGANIZE_UNITS)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_REORGANIZE_UNITS)).setMenuPosY(mY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_REORGANIZE_UNITS)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_REORGANIZE_UNITS);
    }

    public final void setVisibleInGame_DisbandUnits(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_DISBAND_UNITS)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_DISBAND_UNITS);
        }

    }

    public final void rebuildInGame_DisbandUnits() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_DISBAND_UNITS)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_DISBAND_UNITS)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_DISBAND_UNITS)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_DISBAND_UNITS)).getMenuPosY();
        InGame_ReorganizeUnits.restartAnimation = !((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_DISBAND_UNITS)).getVisible();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_DISBAND_UNITS, new InGame_Disband());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_DISBAND_UNITS)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_DISBAND_UNITS)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_DISBAND_UNITS)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_DISBAND_UNITS)).setMenuPosY(mY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_DISBAND_UNITS)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_DISBAND_UNITS);
    }

    public final void rebuildNewGameCiv() {
        ((List)this.menus.get(this.NEW_GAME)).set(this.NEW_GAME_CIV, new NewGameCiv());
    }

    public final void rebuildMultiGames() {
        ((List)this.menus.get(this.MULTIPLAYER)).set(0, new MultiGames());
    }

    public final void rebuildMultiCreateLobby() {
        ((List)this.menus.get(this.MULTIPLAYER_CREATE_LOBBY)).set(0, new MultiLobbyCreate(false));
    }

    public final void rebuildMultiLobby() {
        ((List)this.menus.get(this.MULTIPLAYER_LOBBY)).set(0, new MultiLobby());
    }

    public final void rebuildNewGameCiv_MultiLobby() {
        ((List)this.menus.get(this.MULTIPLAYER_LOBBY)).set(this.MULTIPLAYER_LOBBY_CIV, new NewGameCiv());
    }

    public final void rebuildNewGame_Settings() {
        ((List)this.menus.get(this.NEW_GAME)).set(this.NEW_GAME_SETTINGS, new NewGame_Settings());
        this.setVisible_NewGame_Settings(true);
    }

    public final boolean getVisible_NewGame_Settings() {
        return ((Menu)((List)this.menus.get(this.NEW_GAME)).get(this.NEW_GAME_SETTINGS)).getVisible();
    }

    public final void setVisible_NewGame_Settings(boolean visible) {
        ((Menu)((List)this.menus.get(this.NEW_GAME)).get(this.NEW_GAME_SETTINGS)).setVisible(visible);
    }

    public final void rebuild_NewGame_Settings() {
        ((List)this.menus.get(this.NEW_GAME)).set(this.NEW_GAME_SETTINGS, new NewGame_Settings());
        ((Menu)((List)this.menus.get(this.NEW_GAME)).get(this.NEW_GAME_SETTINGS)).setVisible(true);
    }

    public final void rebuild_NewGame_Encyclopedia() {
        ((List)this.menus.get(this.NEW_GAME)).set(this.NEW_GAME_SETTINGS, new InGame_Encyclopedia());
        ((Menu)((List)this.menus.get(this.NEW_GAME)).get(this.NEW_GAME_SETTINGS)).setVisible(true);
    }

    public final void rebuildInGame_RecruitSameType() {
        this.inCreateNewArmy = false;
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RECRUIT_ARMY, new InGame_RecruitSameType());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RECRUIT_ARMY)).setVisible(true);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD)).setVisible(false);
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY);
    }

    public final void getInGame_RecruitSameType_UpdateLanguage() {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RECRUIT_ARMY)).updateLanguage();
    }

    public final void rebuildInGame_RecruitArmy() {
        this.inCreateNewArmy = false;
        InGame_RecruitArmy.key = null;
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RECRUIT_ARMY, new InGame_RecruitArmy());
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD)).setVisible(false);
    }

    public final void rebuildInGame_RecruitArmy_NewArmy() {
        this.inCreateNewArmy = true;
        InGame_RecruitArmy.key = null;
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RECRUIT_ARMY, new InGame_RecruitArmy_NewArmy());
        if (InGame_RecruitArmy_NewArmy_Battlefield.autoVisible) {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD, new InGame_RecruitArmy_NewArmy_Battlefield());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD)).setVisible(true);
        } else {
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD)).setVisible(false);
        }

        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD);
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY);
    }

    public final void rebuildInGame_RecruitArmy_NewArmy_Battlefield() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD, new InGame_RecruitArmy_NewArmy_Battlefield());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD);
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY);
    }

    public final void rebuildInGame_Generals() {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GENERALS)).dispose();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_GENERALS, new InGame_Generals());
        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }

    }

    public final void rebuildInGame_Notifications() {
        try {
            if (this.IN_GAME >= 0 && this.IN_GAME_NOTIFICATION >= 0) {
                ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_NOTIFICATION, new InGame_Notifications());
                ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_NOTIFICATION)).setVisible(true);
                this.setOrderOfMenu(this.IN_GAME_NOTIFICATION);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void rebuildInGame_Right() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RIGHT, new InGame_Right());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).setVisible(true);
        this.rebuildInGame_RightQueue();
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
        this.setOrderOfMenu(this.IN_GAME_RIGHT_QUEUE);
    }

    public final void addRebuildInGame_RightQueue() {
        Game.addSimpleTask(new Game.SimpleTask("rebuildInGame_RightQueue") {
            public void update() {
                Game.menuManager.rebuildInGame_RightQueue();
            }
        });
    }

    public final void rebuildInGame_RightQueue() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RIGHT_QUEUE, new InGame_RightQueue());
        this.setOrderOfMenu(this.IN_GAME_RIGHT_QUEUE);
    }

    public final void rebuildInGame_RightPopulation() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RIGHT, new InGame_RightPopulation());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }

    public final void rebuildInGame_RightGovernment() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RIGHT, new InGame_RightGovernment());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }

    public final void rebuildInGame_RightReligion() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RIGHT, new InGame_RightReligion());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }

    public final void rebuildInGame_RightWonders() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RIGHT, new InGame_RightWonders());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }

    public final void rebuildInGame_RightEconomy() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RIGHT, new InGame_RightEconomy());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }

    public final void rebuildInGame_RightInfrastructure() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RIGHT, new InGame_RightInfrastructure());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }

    public final void rebuildInGame_RightGoods() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RIGHT, new InGame_RightGoods());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }

    public final void rebuildInGame_RightGoodsProvinces() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RIGHT, new InGame_RightGoodsProvinces());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }

    public final void rebuildInGame_RightProvinceIncome() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RIGHT, new InGame_RightProvinceIncome());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }

    public final void rebuildInGame_RightTaxEfficiency() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_RIGHT, new InGame_RightTaxEfficiency());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_RIGHT);
    }

    public final void rebuildInGame_Armies(boolean noGeneralsSort, boolean savePos) {
        if (savePos) {
            int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ARMIES)).getPosX();
            int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ARMIES)).getPosY();
            int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ARMIES)).getMenuPosX();
            int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ARMIES)).getMenuPosY();
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_ARMIES, new InGame_Armies(noGeneralsSort));
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ARMIES)).setPosX(nX);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ARMIES)).setPosY(nY);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ARMIES)).setMenuPosX(mX);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ARMIES)).setMenuPosY(mY);
            InGame_Armies.lTime = 0L;
        } else {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_ARMIES, new InGame_Armies(noGeneralsSort));
        }

    }

    public final void rebuildInGame_Mercenaries() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_ARMIES, new InGame_RecruitMercenaries());
    }

    public final boolean getVisibleInGame_Right() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).getVisible();
    }

    public final boolean getVisibleInGame_Notifications() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_NOTIFICATION)).getVisible();
    }

    public final void rebuildInGame_CivBonuses() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV_BONUSES, new InGame_CivBonuses());
    }

    public final void rebuildInGame_Court() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court());
    }

    public final void rebuildInGame_CourtSavePos() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosY(mY);
        this.setVisibleInGame_Court(true);
        InGame_Court.lTime = 0L;
    }

    public final void rebuildInGame_CourtOptions() {
        if (this.IN_GAME >= 0 && this.IN_GAME_COURT_OPTIONS >= 0) {
            boolean vis1 = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT_OPTIONS)).getVisible();
            boolean vis2 = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT_OPTIONS2)).getVisible();
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT_OPTIONS, new InGame_CourtOptions());
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT_OPTIONS2, new InGame_CourtOptions2());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT_OPTIONS)).setVisible(vis1);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT_OPTIONS2)).setVisible(vis2);
        }

    }

    public final void rebuildInGame_ExploitEconomy() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_ExploitEconomy());
    }

    public final void rebuildInGame_ExploitEconomy_SavePos() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_ExploitEconomy());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosY(mY);
        this.setVisibleInGame_Court(true);
        InGame_Court.lTime = 0L;
    }

    public final void rebuildInGame_InvestInEconomy() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_InvestInEconomy());
    }

    public final void rebuildInGame_IncreaseTaxEfficiency() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_IncreaseTaxEfficiency());
    }

    public final void rebuildInGame_Government() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Government());
    }

    public final void rebuildInGame_Government_SavePos() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Government());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosY(mY);
        this.setVisibleInGame_Court(true);
        InGame_Court.lTime = 0L;
    }

    public final void rebuildInGame_LawsCourt() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Law());
    }

    public final void rebuildInGame_Court_Multi() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Multi());
    }

    public final void rebuildInGame_CourtProvinces() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Provinces());
    }

    public final void rebuildInGame_CourtWorld_Wars() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_WorldWars());
    }

    public final void rebuildInGame_CourtStatistics() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Stats());
    }

    public final void rebuildInGame_CourtSearch() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_WorldSearch());
    }

    public final void rebuildInGame_CourtSearchCivs() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_WorldCivs());
    }

    public final void rebuildInGame_CourtMissions() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Missions());
    }

    public final void rebuildInGame_CourtGoldenAges() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_GoldenAges());
    }

    public final void rebuildInGame_CourtWorld_Alliances() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_WorldAlliances());
    }

    public final void rebuildInGame_CourtWorld_Truces() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_WorldTruces());
    }

    public final void rebuildInGame_CourtWorld_Defensive() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_WorldDefensive());
    }

    public final void rebuildInGame_CourtWorld_NonAggression() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_WorldNonAggression());
    }

    public final void rebuildInGame_CourtWorld_Vassals() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_WorldVassals());
    }

    public final void rebuildInGame_EspionageReportCourt(int iCivID, int iReportEndTurnID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Espionage(iCivID, iReportEndTurnID));
    }

    public final void rebuildInGame_IncreaseManpower() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_IncreaseManpower());
    }

    public final void rebuildInGame_IncreaseGrowthRate() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_IncreaseGrowthRate());
    }

    public final void rebuildInGame_DevelopInfrastructure() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_DevelopInfrastructure());
    }

    public final void rebuildInGame_Core() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Core());
    }

    public final void rebuildInGame_CoreSavePos() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Core());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosY(mY);
    }

    public final void rebuildInGame_Religion() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Religion());
    }

    public final void rebuildInGame_ReligionSavePos() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Religion());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosY(mY);
    }

    public final void rebuildInGame_Buildings2() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Buildings2());
    }

    public final void rebuildInGame_Buildings2SavePos() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Buildings2());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosY(mY);
    }

    public final void savePosInGame_Buildings2() {
        InGame_Court_Buildings2.savePos_X = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosX();
        InGame_Court_Buildings2.savePos_Y = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosY();
        InGame_Court_Buildings2.saveMenuPos_X = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosX();
        InGame_Court_Buildings2.saveMenuPos_Y = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosY();
    }

    public final void rebuildInGame_Buildings2_Back() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Buildings2());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosX(InGame_Court_Buildings2.savePos_X);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosY(InGame_Court_Buildings2.savePos_Y);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosX(InGame_Court_Buildings2.saveMenuPos_X);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosY(InGame_Court_Buildings2.saveMenuPos_Y);
    }

    public final void rebuildInGame_Build() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Build());
    }

    public final void rebuildInGame_BuildSavePos() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_COURT, new InGame_Court_Build());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setMenuPosY(mY);
    }

    public final void rebuildInGame_Budget() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUDGET, new InGame_Budget());
    }

    public final void rebuildInGame_Event(Event event, int eventType, int eventID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_EVENT, new InGame_Event(event, eventType, eventID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_EVENT)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_EVENT);
    }

    public final boolean getVisibleInGame_CurrentSituation_Ranking() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).getVisible() && !currentSituationMode;
    }

    public final void rebuildInGame_CurrentSituation_Ranking() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CURRENT_SITUATION, new InGame_Ranking());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
        currentSituationMode = false;
        this.hideCourtCiv();
        this.setVisibleInGame_Buildings(false, false);
        this.setVisibleInGame_Wonder(false);
        this.setVisibleInGame_Generals(false);
        this.setVisibleInGame_ReorganizeUnits(false);
        this.setVisibleInGame_DisbandUnits(false);
        this.setVisibleInGame_RecruitArmy(false);
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }

        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }

        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }

        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }

        if (this.getVisibleInGame_War()) {
            this.setVisibleInGame_War(false);
        }

        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }

    }

    public final void rebuildInGame_CurrentSituation_Missions() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CURRENT_SITUATION, new InGame_Missions());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
        currentSituationMode = true;
        this.hideCourtCiv();
        this.setVisibleInGame_Buildings(false, false);
        this.setVisibleInGame_Wonder(false);
        this.setVisibleInGame_Generals(false);
        this.setVisibleInGame_ReorganizeUnits(false);
        this.setVisibleInGame_DisbandUnits(false);
        this.setVisibleInGame_RecruitArmy(false);
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }

        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }

        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }

        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }

    }

    public final void rebuildInGame_ReleaseAVassal() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CURRENT_SITUATION, new InGame_ReleaseAVassal());
        this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
        currentSituationMode = false;
        this.rebuildInGame_CurrentSituation_HideMenus();
    }

    public final void rebuildInGame_ReleaseAVassal_SavePos() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CURRENT_SITUATION, new InGame_ReleaseAVassal());
        this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
        currentSituationMode = false;
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).setMenuPosY(mY);
    }

    public final void rebuildInGame_CurrentSituation() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CURRENT_SITUATION, new InGame_CurrentSituation());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
        currentSituationMode = true;
        this.rebuildInGame_CurrentSituation_HideMenus();
    }

    public final void rebuildInGame_CurrentSituation_HideMenus() {
        this.hideCourtCiv();
        this.setVisibleInGame_Buildings(false, false);
        this.setVisibleInGame_Wonder(false);
        this.setVisibleInGame_Generals(false);
        this.setVisibleInGame_ReorganizeUnits(false);
        this.setVisibleInGame_DisbandUnits(false);
        this.setVisibleInGame_RecruitArmy(false);
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }

        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }

        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }

        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }

    }

    public final void rebuildInGame_CurrentSituation_Wonders() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CURRENT_SITUATION, new InGame_Wonders());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
    }

    public final void rebuildInGame_BudgetIncomeTaxation() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUDGET, new InGame_BudgetIncomeTaxation());
        this.setVisibleInGame_Budget(true);
    }

    public final void rebuildInGame_BudgetIncomeProvinces() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUDGET, new InGame_BudgetIncomeProvinces());
        this.setVisibleInGame_Budget(true);
    }

    public final void rebuildInGame_BudgetBalanceProvinces() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUDGET, new InGame_BudgetBalanceProvinces());
        this.setVisibleInGame_Budget(true);
    }

    public final void rebuildInGame_BudgetIncomeEconomy() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUDGET, new InGame_BudgetIncomeEconomy());
        this.setVisibleInGame_Budget(true);
    }

    public final void rebuildInGame_BudgetExpensesMaintenance() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUDGET, new InGame_BudgetExpensesMaintenance());
        this.setVisibleInGame_Budget(true);
    }

    public final void rebuildInGame_BudgetExpensesBuildingsMaintenance() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUDGET, new InGame_BudgetExpensesBuildingsMaintenance());
        this.setVisibleInGame_Budget(true);
    }

    public final void rebuildInGame_BudgetIncomeProduction() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUDGET, new InGame_BudgetIncomeProduction());
        this.setVisibleInGame_Budget(true);
    }

    public final void rebuildInGame_BudgetIncomeBuildings() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUDGET, new InGame_BudgetIncomeBuildings());
        this.setVisibleInGame_Budget(true);
    }

    public final void rebuildInGame_Civ_SavePos() {
        Keyboard var10000 = Game.keyboard;
        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.INGAME_RENAME_CIV) {
            Game.keyboard.hideKeyboard();
        }

        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).setVisible(true);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).setMenuPosY(mY);
        InGame_Civ.enabledByScaleOut = false;
    }

    public final void rebuildInGame_Civ() {
        this.rebuildInGame_Civ(false);
    }

    public final void rebuildInGame_Civ(boolean rebuildMode) {
        Keyboard var10000 = Game.keyboard;
        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.INGAME_RENAME_CIV) {
            Game.keyboard.hideKeyboard();
        }

        if (!rebuildMode && Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
        }

        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ());
        if (rebuildMode) {
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).setVisible(true);
        } else {
            this.setVisibleInGame_Civ(true);
            InGame_Civ.actionOnOpen();
        }

        InGame_Civ.enabledByScaleOut = false;
    }

    public final void rebuildInGame_Civ_Government() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ_Government());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_Civ_Religion() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ_Religion());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_Civ_MilitaryAcademy() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ_MilitaryAcademy());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_Civ_CapitalCity() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ_CapitalCity());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_Civ_Regiments() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ_ArmiesRegiments());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_Civ_AggressiveExpansion() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ_AggressiveExpansion());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildMainMenu_Stats() {
        ((List)this.menus.get(this.MAINMENU_STATS)).set(0, new MainMenu_Stats());
    }

    public final void rebuildInGame_Civ_Population() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ_Population());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_Civ_List() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ_List());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_Civ_Provinces() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ_Provinces());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_CivRank() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_CivRank());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_Civ_UnlockedTechnologies() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ_UnlockedTechnologies());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_Civ_UnlockedAdvantages_2() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_CivilizationAdvantages2());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_Civ_UnlockedAdvantages() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_CivilizationAdvantages(InGame_Civ.iActiveCivID));
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_Civ_Economy() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ_Economy());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_Civ_Compare() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CIV, new InGame_Civ_Compare());
        this.setVisibleInGame_Civ(true);
        InGame_Civ.enabledByScaleOut = false;
        if (this.getVisibleInGame_CivBonuses()) {
            this.setVisibleInGame_CivBonuses(false);
        }

    }

    public final void rebuildInGame_ProvinceBonuses() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_PROVINCE_BONUSES, new InGame_ProvinceBonuses());
    }

    public final void rebuildInGame_GeneralRecruit() {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GENERAL_RECRUIT)).dispose();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_GENERAL_RECRUIT, new InGame_GeneralRecruit());
        this.setVisibleInGame_GeneralRecruit(true);
    }

    public final void rebuildInGame_TakeLoan() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_TAKE_LOAN, new InGame_Loan());
        this.setVisibleInGame_TakeLoan(true);
    }

    public final void rebuildEditor() {
        ((List)this.menus.get(this.EDITOR)).set(0, new Editor());
    }

    public final void rebuildInGame_SaveGame() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_SAVE_GAME, new InGame_SaveGame(true));
        this.setOrderOfMenu(this.IN_GAME_SAVE_GAME);
    }

    public final void rebuildScenario_Calendar() {
        ((List)this.menus.get(this.SCENARIO_SETTINGS)).set(this.SCENARIO_CALENDAR, new Scenario_Calendar(true));
        this.setOrderOfMenu(this.SCENARIO_CALENDAR);
    }

    public final void rebuildInGame_TakeLoanRepay() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_TAKE_LOAN_REPAY, new InGame_LoanRepay());
        this.setVisibleInGame_TakeLoanRepay(true);
    }

    public final void rebuildInGame_Battlefield() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_Battlefield());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 51;
    }

    public final void rebuildInGame_ChangeReligion2() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_ChangeReligion2());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 50;
    }

    public final void rebuildInGame_ChangeReligion() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_ChangeReligion());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 49;
    }

    public final void rebuildInGame_Intervene(int iCivID, int iCivID2) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_Intervene(iCivID, iCivID2));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 48;
    }

    public final void rebuildInGame_AllianceList() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_AllianceList());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 47;
    }

    public final void rebuildInGame_AllianceList_SavePos() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_AllianceList());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 47;
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setMenuPosY(mY);
    }

    public final void rebuildInGame_MessageCallToWar(PMessage key) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_MessageCallToWar(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 46;
    }

    public final void rebuildInGame_Revolutions() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_Revolutions());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 45;
    }

    public final void rebuildInGame_Revolutions_SavePos() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_Revolutions());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setMenuPosY(mY);
        InGame_Revolutions.lTime = 0L;
    }

    public final void rebuildInGame_ShareTechnology(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_ShareTechnology(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 44;
    }

    public final void rebuildInGame_LiberateCivilization(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_LiberateCivilization(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 43;
    }

    public final void rebuildInGame_SellProvince(int sellToCivID) {
    }

    public final boolean getVisibleSellProvince() {
        return this.getVisibleInGame_PopUp() && IN_GAME_POP_UP_MENU_ID == 41;
    }

    public final void rebuildInGame_Rivals_End() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_Rivals_End());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 40;
    }

    public final boolean getVisibleFormCiv() {
        return this.getVisibleInGame_PopUp() && IN_GAME_POP_UP_MENU_ID == IN_GAME_POP_UP_MENU_FORM_CIV;
    }

    public final void rebuildInGame_FormCiv(int playerCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_FormCiv(playerCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = IN_GAME_POP_UP_MENU_FORM_CIV;
    }

    public final void rebuildInGame_RivalsList() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_RivalsList());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 38;
    }

    public final void rebuildInGame_RivalsList_SavePos() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_RivalsList());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 38;
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setMenuPosY(mY);
    }

    public final void rebuildInGame_CallAllies(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_CallAllies(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 37;
    }

    public final void rebuildInGame_AllianceSpecialReform(int allianceID, int reformID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_AllianceSpecialReformHRE(allianceID, reformID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 36;
    }

    public final void rebuildInGame_AllianceSpecial_SavePos(int allianceID) {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_AllianceSpecial(allianceID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setMenuPosY(mY);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 35;
    }

    public final boolean getVisible_SpecialAlliance() {
        return this.getVisibleInGame_PopUp() && (IN_GAME_POP_UP_MENU_ID == 35 || IN_GAME_POP_UP_MENU_ID == 36);
    }

    public final void rebuildInGame_AllianceSpecial(int nAllianceID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_AllianceSpecial(nAllianceID));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 35;
        if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_SPECIAL_ALLIANCE_VIEW) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_SPECIAL_ALLIANCE_VIEW);
        }

    }

    public final void rebuildInGame_MessageInsult(String key) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_MessageInsult(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 34;
    }

    public final void rebuildInGame_MessageGuarantee(String key) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_MessageGuarantee(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 33;
    }

    public final void rebuildInGame_MessageDemandsMilitaryAccess(String key) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_MessageDemandsMilitaryAccess(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 32;
    }

    public final void rebuildInGame_MessageNonAggressionPact(String key) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_MessageNonAggressionPact(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 31;
    }

    public final void rebuildInGame_MessageDefensivePact(String key) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_MessageDefensivePact(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 30;
    }

    public final void rebuildInGame_MessageAlliance(String key) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_MessageAlliance(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 29;
    }

    public final void rebuildInGame_MessageGift(String key) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_MessageGift(key));
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 28;
    }

    public final void rebuildInGame_SendSpy(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_SendSpy(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 27;
    }

    public final void rebuildInGame_BattleTactics() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_BattleTactics());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 26;
    }

    public final void rebuildInGame_Rivals() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_Rivals());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 25;
    }

    public final void rebuildInGame_LawReform(int lawID, int lawID2) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_LawReform(lawID, lawID2));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 24;
    }

    public final void rebuildInGame_Laws(int lawID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_Laws(lawID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 23;
    }

    public final void rebuildInGame_SendGift(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_SendGift(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 22;
    }

    public final void rebuildInGame_Guarantee(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_Guarantee(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 21;
    }

    public final void rebuildInGame_OfferMilitaryAccess(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_OfferMilitaryAccess(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 20;
    }

    public final void rebuildInGame_DemandMilitaryAccess(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_DemandMilitaryAccess(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 19;
    }

    public final void rebuildInGame_Alliance(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_Alliance(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 18;
    }

    public final void rebuildInGame_NonAggression(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_NonAggression(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 17;
    }

    public final void rebuildInGame_DefensivePact(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_DefensivePact(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 16;
    }

    public final void rebuildInGame_Encyclopedia() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_Encyclopedia());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 15;
    }

    public final void rebuildInGame_BattleReport(String key) {
        int reportID = Game.player.getBattleReportID(key);
        if (reportID >= 0) {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_BattleReport(reportID));
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
            this.setOrderOfMenu(this.IN_GAME_POP_UP);
            IN_GAME_POP_UP_MENU_ID = 14;
        }

    }

    public final void rebuildInGame_SendInsult(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_SendInsult(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 13;
    }

    public final boolean getVisibleRevolutions() {
        return this.getVisibleInGame_PopUp() && IN_GAME_POP_UP_MENU_ID == 45;
    }

    public final boolean getVisibleDeclareWar() {
        return this.getVisibleInGame_PopUp() && IN_GAME_POP_UP_MENU_ID == IN_GAME_POP_UP_MENU_ID_DECLARE_WAR;
    }

    public final void rebuildInGame_DeclareWar(int iCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_DeclareWar(iCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = IN_GAME_POP_UP_MENU_ID_DECLARE_WAR;
    }

    public final void rebuildInGame_ChangeIdeology2() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_ChangeIdeology2());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 11;
    }

    public final void rebuildInGame_ChangeIdeology() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_ChangeIdeology());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 10;
    }

    public final void rebuildInGame_Audio() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_Audio());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 9;
    }

    public final void rebuildInGame_UpgradeCapital() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_UpgradeCapital());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 8;
    }

    public final void rebuildInGame_MoveCapital_PopUp() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_MoveCapital_PopUp());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 7;
    }

    public final void rebuildInGame_BuildAtomicBomb() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_BuildAtomicBomb());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 6;
    }

    public final void rebuildInGame_UpgradeNuclearReactor() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_UpgradeNuclearReactor());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 5;
    }

    public final void rebuildInGame_UpgradeSupremeCourt() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_UpgradeSupremeCourt());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 4;
    }

    public final void rebuildInGame_UpgradeMilitaryAcademyForGenerals() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_UpgradeMilitaryAcademyForGenerals());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 3;
    }

    public final void rebuildInGame_UpgradeMilitaryAcademy() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_UpgradeMilitaryAcademy());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 2;
    }

    public final void rebuildInGame_Destroy() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_Destroy());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 1;
    }

    public final void rebuildInGame_ConvertReligion() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_POP_UP, new InGame_ConvertReligion());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_POP_UP);
        IN_GAME_POP_UP_MENU_ID = 0;
    }

    public final void rebuildInGame_CustomizeArmy() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_ADVISOR_RECRUIT, new InGame_ArmyCustomize());
        this.setVisibleInGame_AdvisorRecruit(true);
    }

    public final void rebuildInGame_AdvisorRecruit() {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ADVISOR_RECRUIT)).dispose();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_ADVISOR_RECRUIT, new InGame_AdvisorRecruit());
        this.setVisibleInGame_AdvisorRecruit(true);
    }

    public final void setOrderOfMenu_TechnologyTree() {
        this.setOrderOfMenu(this.IN_GAME_TECHNOLOGY_TREE_EMPTY);
        this.setOrderOfMenu(this.IN_GAME_TECHNOLOGY_TREE);
    }

    public final void rebuildInGame_TechnologyTree(boolean savePos, boolean updateOrder) {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_TECHNOLOGY_TREE, new InGame_TechnologyTree());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).setVisible(true);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE_EMPTY)).setVisible(true);
        if (savePos) {
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).setPosX(nX);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).setPosY(nY);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).setMenuPosX(mX);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).setMenuPosY(mY);
        }

        if (updateOrder) {
            this.setOrderOfMenu_TechnologyTree();
        }

    }

    public final void rebuildInGame_MissionTree(boolean savePos, boolean updateOrder) {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_TECHNOLOGY_TREE, new InGame_MissionTree());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).setVisible(true);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE_EMPTY)).setVisible(true);
        if (savePos) {
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).setPosX(nX);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).setPosY(nY);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).setMenuPosX(mX);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).setMenuPosY(mY);
        }

        if (updateOrder) {
            this.setOrderOfMenu_TechnologyTree();
        }

    }

    public final void setOrderOfMenu_BattleFull() {
        this.setOrderOfMenu(this.IN_GAME_BATTLE_FULL_EMPTY);
        this.setOrderOfMenu(this.IN_GAME_BATTLE_FULL);
    }

    public final void rebuildInGame_BattleFull() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BATTLE_FULL, new InGame_Battle_Full());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_FULL)).setVisible(true);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_FULL_EMPTY)).setVisible(true);
        this.setOrderOfMenu_BattleFull();
    }

    public final void rebuildInGame_TechnologyChoose(boolean savePos, boolean updateOrder) {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CHOOSE_TECHNOLOGY, new InGame_TechnologyChoose());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setVisible(true);
        if (updateOrder) {
            this.rebuildInGame_TechnologyChoose_HideMenus();
        }

        if (savePos) {
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setPosX(nX);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setPosY(nY);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setMenuPosX(mX);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setMenuPosY(mY);
        }

    }

    public final void rebuildInGame_TechnologyChoose_HideMenus() {
        this.hideCourtCiv();
        this.setVisibleInGame_Buildings(false, false);
        this.setVisibleInGame_Wonder(false);
        this.setVisibleInGame_Generals(false);
        this.setVisibleInGame_GeneralRecruit(false);
        if (this.getVisibleInGame_ProvinceArmy()) {
            this.setVisibleInGame_ProvinceArmy(false);
        }

        if (this.getVisibleInGame_Battle()) {
            this.setVisibleInGame_Battle(false);
        }

        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }

        if (this.getVisibleInGame_War()) {
            this.setVisibleInGame_War(false);
        }

        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }

        this.setVisibleInGame_ReorganizeUnits(false);
        this.setVisibleInGame_DisbandUnits(false);
        this.setVisibleInGame_RecruitArmy(false);
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }

        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }

        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }

        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }

        this.setVisibleInGame_ProvinceInfo(false);
        Game.gameActiveProvince.resetLastActiveProvince();
        Game.setActiveProvinceID(-1);
        Game.clearActiveArmy();
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }

    public final void rebuildInGame_ListOfBuildings() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CHOOSE_TECHNOLOGY, new InGame_ListOfBuildings());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }

    public final void rebuildInGame_ListOfUnits() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CHOOSE_TECHNOLOGY, new InGame_ListOfUnits());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }

    public final void rebuildInGame_CivilizationAdvantages_2() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CHOOSE_TECHNOLOGY, new InGame_CivilizationAdvantages2());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }

    public final void rebuildInGame_CivilizationAdvantages(int nActiveCivID) {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CHOOSE_TECHNOLOGY, new InGame_CivilizationAdvantages(nActiveCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }

    public final void rebuildInGame_CivilizationAdvantages_SavePos_2() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CHOOSE_TECHNOLOGY, new InGame_CivilizationAdvantages2());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setVisible(true);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setMenuPosY(mY);
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }

    public final void rebuildInGame_CivilizationAdvantages_SavePos(int nActiveCivID) {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_CHOOSE_TECHNOLOGY, new InGame_CivilizationAdvantages(nActiveCivID));
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setVisible(true);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setMenuPosY(mY);
        this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
    }

    public final void rebuildInGame_Nukes() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_NUKES, new InGame_Nukes());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_NUKES)).setVisible(true);
        this.hideCourtCiv();
        this.setVisibleInGame_Buildings(false, false);
        this.setVisibleInGame_Wonder(false);
        this.setVisibleInGame_Generals(false);
        this.setVisibleInGame_GeneralRecruit(false);
        this.setVisibleInGame_ProvinceArmy(false);
        this.setVisibleInGame_Battle(false);
        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }

        if (this.getVisibleInGame_War()) {
            this.setVisibleInGame_War(false);
        }

        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }

        this.setVisibleInGame_ReorganizeUnits(false);
        this.setVisibleInGame_DisbandUnits(false);
        this.setVisibleInGame_RecruitArmy(false);
        this.setVisibleInGame_TechnologyChoose(false);
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }

        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }

        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }

        this.setVisibleInGame_ProvinceInfo(false);
        Game.gameActiveProvince.resetLastActiveProvince();
        Game.setActiveProvinceID(-1);
        Game.clearActiveArmy();
        this.setOrderOfMenu(this.IN_GAME_NUKES);
    }

    public final void rebuildInGame_Legacies() {
        ((List)this.menus.get(this.IN_GAME_LEGACIES)).set(0, new InGame_Legacies());
    }

    public final void rebuildInGame_Goods_LargestProducers() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_GOODS, new InGame_Goods_LargestProducers());
        this.setVisibleInGame_Goods(true);
    }

    public final void rebuildInGame_GraphPopulation() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_GOODS, new InGame_GraphPopulation());
        this.setVisibleInGame_Goods(true);
    }

    public final void rebuildInGame_Goods_Provinces() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_GOODS, new InGame_Goods_Provinces());
        this.setVisibleInGame_Goods(true);
    }

    public final void rebuildInGame_GoodsMarket() {
        this.setVisibleInGame_CivBonuses(false);
        this.setVisibleInGame_Court(false);
        this.setVisibleInGame_Budget(false);
        this.hideCivCourt_Menus();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_GOODS_CIV, new InGame_GoodsMarket());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS_CIV)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_GOODS_CIV);
    }

    public final void rebuildInGame_GoodsMarketResource() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_GOODS_CIV, new InGame_GoodsMarket_Resource());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS_CIV)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_GOODS_CIV);
    }

    public final void rebuildInGame_Goods() {
        this.rebuildInGame_Goods(false);
    }

    public final void rebuildInGame_Goods(boolean saveMenuPos) {
        if (saveMenuPos) {
            int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS)).getPosX();
            int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS)).getPosY();
            int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS)).getMenuPosX();
            int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS)).getMenuPosY();
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_GOODS, new InGame_Goods());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS)).setPosX(nX);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS)).setPosY(nY);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS)).setMenuPosX(mX);
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS)).setMenuPosY(mY);
        } else {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_GOODS, new InGame_Goods());
        }

    }

    public final void rebuildInGame_MessagesSavePos() {
        int nX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_MESSAGES)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_MESSAGES)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_MESSAGES)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_MESSAGES)).getMenuPosY();
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_MESSAGES, new InGame_Messages());
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_MESSAGES)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_MESSAGES)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_MESSAGES)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_MESSAGES)).setMenuPosY(mY);
    }

    public final void rebuildInGame_Messages() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_MESSAGES, new InGame_Messages());
    }

    public final void rebuildInGame_Wars() {
        try {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_WARS, new InGame_MessagesWars());
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void rebuildInGame_Wonder() {
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_WONDER, new InGame_Wonder(true));
        this.setVisibleInGame_Generals(false);
        this.setVisibleInGame_Buildings(false, false);
        this.setVisibleInGame_RecruitArmy(false);
        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }

    }

    public final void rebuildInGame_Info(String sText, String sText2) {
        if (((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).OVER_IMAGE_ID > 2) {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_INFO, new InGame_Info_2(sText, sText2));
        } else {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_INFO, new InGame_Info(sText, sText2));
        }

        this.setVisibleInGame_Info(true);
        this.setOrderOfMenu(this.IN_GAME_INFO);
    }

    public final void rebuildInGameLegacies_Info(String sText, String sText2) {
        if (((Game_Ages.Data_Ages)Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID)).OVER_IMAGE_ID > 2) {
            ((List)this.menus.get(this.IN_GAME_LEGACIES)).set(this.IN_GAME_LEGACIES_INFO, new InGame_Info_2(sText, sText2));
        } else {
            ((List)this.menus.get(this.IN_GAME_LEGACIES)).set(this.IN_GAME_LEGACIES_INFO, new InGame_Info(sText, sText2));
        }

        ((Menu)((List)this.menus.get(this.IN_GAME_LEGACIES)).get(this.IN_GAME_LEGACIES_INFO)).setVisible(true);
        this.setOrderOfMenu(this.IN_GAME_LEGACIES_INFO);
    }

    public final void rebuildInGame_Buildings(boolean updateOrder) {
        boolean odlVisible = ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS)).getVisible();
        List<Point_XY> lBuildings = new ArrayList();
        List<Point_XY> lBuildingsMenu = new ArrayList();
        if (odlVisible) {
            lBuildings.add(new Point_XY(((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_0)).getPosX(), ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_0)).getPosY()));
            lBuildingsMenu.add(new Point_XY(((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_0)).getMenuPosX(), ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_0)).getMenuPosY()));
            lBuildings.add(new Point_XY(((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_1)).getPosX(), ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_1)).getPosY()));
            lBuildingsMenu.add(new Point_XY(((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_1)).getMenuPosX(), ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_1)).getMenuPosY()));
            lBuildings.add(new Point_XY(((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_2)).getPosX(), ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_2)).getPosY()));
            lBuildingsMenu.add(new Point_XY(((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_2)).getMenuPosX(), ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_2)).getMenuPosY()));
        }

        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUILDINGS, new InGame_BuildingsGroup());
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUILDINGS_GROUP_0, new InGame_BuildingsGroupID(0));
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUILDINGS_GROUP_1, new InGame_BuildingsGroupID(1));
        ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUILDINGS_GROUP_2, new InGame_BuildingsGroupID(2));
        if (InGame_BuildingsGroup.isCapital) {
            ((List)this.menus.get(this.IN_GAME)).set(this.IN_GAME_BUILDINGS_GROUP_3, new InGame_BuildingsGroupID(3));
        }

        this.setVisibleInGame_Buildings(true, updateOrder);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_3)).setVisible(InGame_BuildingsGroup.isCapital);
        this.setVisibleInGame_RecruitArmy(false);
        this.setVisibleInGame_Wonder(false);
        this.setVisibleInGame_Generals(false);
        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }

        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }

        if (odlVisible) {
            InGame_BuildingsGroup.lTime = 0L;
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_0)).setPosX(((Point_XY)lBuildings.get(0)).getPosX());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_0)).setPosY(((Point_XY)lBuildings.get(0)).getPosY());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_0)).setMenuPosX(((Point_XY)lBuildingsMenu.get(0)).getPosX());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_0)).setMenuPosY(((Point_XY)lBuildingsMenu.get(0)).getPosY());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_1)).setPosX(((Point_XY)lBuildings.get(1)).getPosX());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_1)).setPosY(((Point_XY)lBuildings.get(1)).getPosY());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_1)).setMenuPosX(((Point_XY)lBuildingsMenu.get(1)).getPosX());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_1)).setMenuPosY(((Point_XY)lBuildingsMenu.get(1)).getPosY());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_2)).setPosX(((Point_XY)lBuildings.get(2)).getPosX());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_2)).setPosY(((Point_XY)lBuildings.get(2)).getPosY());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_2)).setMenuPosX(((Point_XY)lBuildingsMenu.get(2)).getPosX());
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_2)).setMenuPosY(((Point_XY)lBuildingsMenu.get(2)).getPosY());
            lBuildings.clear();
            lBuildingsMenu.clear();
        }

    }

    public final void rebuildEditorGameCivs() {
        ((Menu)((List)this.menus.get(this.EDITOR_GAMECIVS)).get(0)).setVisible(false);
        ((List)this.menus.get(this.EDITOR_GAMECIVS)).set(0, new GameCivs());
        ((List)this.menus.get(this.EDITOR_GAMECIVS)).set(1, new GameCivsAlphabet());
    }

    public final boolean getVisibleScenario_Calendar() {
        return ((Menu)((List)this.menus.get(this.SCENARIO_SETTINGS)).get(this.SCENARIO_CALENDAR)).getVisible();
    }

    public final void setVisibleScenario_Calendar(boolean visible) {
        ((Menu)((List)this.menus.get(this.SCENARIO_SETTINGS)).get(this.SCENARIO_CALENDAR)).setVisible(visible);
    }

    public final boolean getVisibleInGame_ProvinceArmy() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_ARMY)).getVisible();
    }

    public final boolean getVisibleInGame_ProvinceInfo() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_INFO)).getVisible();
    }

    public final boolean getVisibleInGame_RecruitArmy() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RECRUIT_ARMY)).getVisible();
    }

    public final boolean getVisibleInGame_CurrentSituation() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).getVisible();
    }

    public final boolean getVisibleInGame_Generals() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GENERALS)).getVisible();
    }

    public final boolean getVisibleInGame_Armies() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ARMIES)).getVisible();
    }

    public final boolean getVisibleInGame_CivBonuses() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV_BONUSES)).getVisible();
    }

    public final boolean getVisibleInGame_Court() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).getVisible();
    }

    public final boolean getVisibleInGame_Budget() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUDGET)).getVisible();
    }

    public final boolean getVisibleInGame_Civ() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).getVisible();
    }

    public final void getInGame_Civ_updateLanguage() {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).updateLanguage();
    }

    public final void getInGame_ProvinceInfo_updateLanguage() {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_INFO)).updateLanguage();
    }

    public final boolean getVisibleInGame_ProvinceBonuses() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_BONUSES)).getVisible();
    }

    public final boolean getVisibleInGame_GeneralRecruit() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GENERAL_RECRUIT)).getVisible();
    }

    public final boolean getVisibleInGame_TakeLoan() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TAKE_LOAN)).getVisible();
    }

    public final boolean getVisibleInGame_TakeLoanRepay() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TAKE_LOAN_REPAY)).getVisible();
    }

    public final boolean getVisibleInGame_Console() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CONSOLE)).getVisible();
    }

    public final boolean getVisibleInGame_SaveGame() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_SAVE_GAME)).getVisible();
    }

    public final boolean getVisibleInGame_PopUp() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).getVisible();
    }

    public final boolean getVisibleInGame_AdvisorRecruit() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ADVISOR_RECRUIT)).getVisible();
    }

    public final boolean getVisibleInGame_TechnologyTree() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).getVisible();
    }

    public final boolean getVisibleInGame_BattleFull() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_FULL)).getVisible();
    }

    public final boolean getVisibleInGame_TechnologyChoose() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).getVisible();
    }

    public final boolean getVisibleInGame_Nukes() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_NUKES)).getVisible();
    }

    public final boolean getVisibleInGame_Goods() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS)).getVisible();
    }

    public final boolean getVisibleInGame_GoodsMarket() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS_CIV)).getVisible();
    }

    public final boolean getVisibleInGame_Wonder() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_WONDER)).getVisible();
    }

    public final boolean getVisibleInGame_Info() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_INFO)).getVisible();
    }

    public final boolean getVisibleInGame_Buildings() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS)).getVisible();
    }

    public final boolean getVisibleInGame_DisbandArmy() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_DISBAND_UNITS)).getVisible();
    }

    public final boolean getVisibleInGame_ReorganizeUnits() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_REORGANIZE_UNITS)).getVisible();
    }

    public final void setVisibleInGame_RecruitArmy(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RECRUIT_ARMY)).setVisible(visible);
        if (visible) {
            this.hideMenus_RecruitArmy(visible);
        } else {
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD)).setVisible(false);
        }

    }

    public final void setVisibleInGame_RecruitArmy_Battlefield(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD)).setVisible(visible);
    }

    public final void hideMenus_RecruitArmy(boolean visible) {
        this.hideCourtCiv();
        this.setVisibleInGame_Buildings(false, false);
        if (this.getVisibleInGame_Wonder()) {
            this.setVisibleInGame_Wonder(false);
        }

        if (this.getVisibleInGame_Generals()) {
            this.setVisibleInGame_Generals(false);
        }

        if (this.getVisibleInGame_GeneralRecruit()) {
            this.setVisibleInGame_GeneralRecruit(false);
        }

        if (this.getVisibleInGame_ProvinceArmy()) {
            this.setVisibleInGame_ProvinceArmy(false);
        }

        if (this.getVisibleInGame_Battle()) {
            this.setVisibleInGame_Battle(false);
        }

        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }

        if (this.getVisibleInGame_War()) {
            this.setVisibleInGame_War(false);
        }

        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }

        if (this.getVisibleInGame_ReorganizeUnits()) {
            this.setVisibleInGame_ReorganizeUnits(false);
        }

        if (this.getVisibleInGame_DisbandArmy()) {
            this.setVisibleInGame_DisbandUnits(false);
        }

        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }

        if (this.getVisibleInGame_TechnologyTree()) {
            this.setVisibleInGame_TechnologyTree(false);
        }

        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }

        if (this.getVisibleInGame_GoodsMarket()) {
            this.setVisibleInGame_GoodsMarket(false);
        }

        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }

        this.setVisibleInGame_ProvinceInfo(false);
        if (visible) {
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
        }

        Game.clearActiveArmy();
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY_BATTLEFIELD);
        this.setOrderOfMenu(this.IN_GAME_RECRUIT_ARMY);
    }

    public final void setVisibleInGame_Generals(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GENERALS)).setVisible(visible);
        if (visible) {
            this.hideCourtCiv();
            this.setVisibleInGame_TechnologyChoose(false);
            this.setVisibleInGame_TechnologyTree(false);
            this.setVisibleInGame_Buildings(false, false);
            this.setVisibleInGame_Wonder(false);
            this.setVisibleInGame_RecruitArmy(false);
            if (this.getVisibleInGame_GoodsMarket()) {
                this.setVisibleInGame_GoodsMarket(false);
            }

            this.setOrderOfMenu(this.IN_GAME_GENERALS);
        }

    }

    public final void setOrderInGame_Generals() {
        this.setOrderOfMenu(this.IN_GAME_GENERALS);
    }

    public final void setVisibleInGame_Armies(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ARMIES)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_ARMIES);
        }

    }

    public final void setVisibleInGame_Right(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_RIGHT)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_RIGHT);
        }

    }

    public final void setVisibleInGame_Notifications(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_NOTIFICATION)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_NOTIFICATION);
        }

    }

    public final void setVisibleInGame_CivBonuses(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV_BONUSES)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_CIV_BONUSES);
        }

    }

    public final void setVisibleInGame_Court(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT_OPTIONS)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT_OPTIONS2)).setVisible(Game.settingsManager.enableHideSideMenu ? visible : true);
        if (visible) {
            this.setVisibleInGame_Civ(false);
            this.setVisibleInGame_Budget(false);
            this.hideCivCourt_Menus();
            if (this.getVisibleInGame_GoodsMarket()) {
                this.setVisibleInGame_GoodsMarket(false);
            }

            this.setOrderOfMenu_InGameCourt();
        } else {
            InGame_CourtOptions.iActiveID = -1;
            this.setVisibleInGame_AdvisorRecruit(false);
            InGame_CourtOptions.disableAllViews();
        }

    }

    public final void setInGame_CivOptions_Title(String sText) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_COURT_OPTIONS)).getTitle().setText(sText);
    }

    public final void setVisibleInGame_Budget(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUDGET)).setVisible(visible);
        if (visible) {
            this.setVisibleInGame_Civ(false);
            this.setVisibleInGame_Court(false);
            this.hideCivCourt_Menus();
            if (this.getVisibleInGame_GoodsMarket()) {
                this.setVisibleInGame_GoodsMarket(false);
            }

            this.setOrderOfMenu(this.IN_GAME_BUDGET);
        } else {
            this.setVisibleInGame_AdvisorRecruit(false);
        }

    }

    private final void hideCivCourt_Menus() {
        if (this.getVisibleInGame_ProvinceInfo()) {
            this.setVisibleInGame_ProvinceInfo(false);
            Game.gameActiveProvince.resetLastActiveProvince();
            Game.setActiveProvinceID(-1);
        }

        if (this.getVisibleInGame_ProvinceArmy()) {
            Game.clearActiveArmy();
            this.setVisibleInGame_ProvinceArmy(false);
        }

        if (this.getVisibleInGame_Battle()) {
            this.setVisibleInGame_Battle(false);
        }

        if (this.getVisibleInGame_Siege()) {
            this.setVisibleInGame_Siege(false);
        }

        if (this.getVisibleInGame_War()) {
            this.setVisibleInGame_War(false);
        }

        if (this.getVisibleInGame_Peace()) {
            this.setVisibleInGame_Peace(false);
        }

        if (this.getVisibleInGame_RecruitArmy()) {
            this.setVisibleInGame_RecruitArmy(false);
        }

        if (this.getVisibleInGame_Armies()) {
            this.setVisibleInGame_Armies(false);
        }

        if (this.getVisibleInGame_DisbandArmy()) {
            this.setVisibleInGame_DisbandUnits(false);
        }

        if (this.getVisibleInGame_ReorganizeUnits()) {
            this.setVisibleInGame_ReorganizeUnits(false);
        }

        if (this.getVisibleInGame_TechnologyChoose()) {
            this.setVisibleInGame_TechnologyChoose(false);
        }

        if (this.getVisibleDeclareWar()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisible_SpecialAlliance()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
            this.setVisibleInGame_PopUp(false);
        }

        if (this.getVisibleInGame_Nukes()) {
            this.setVisibleInGame_Nukes(false);
        }

        if (this.getVisibleInGame_CurrentSituation()) {
            this.setVisibleInGame_CurrentSituation(false);
        }

        if (this.getVisibleInGame_TechnologyTree()) {
            this.setVisibleInGame_TechnologyTree(false);
        }

        if (this.getVisibleInGame_Wonder()) {
            this.setVisibleInGame_Wonder(false);
        }

        if (this.getVisibleInGame_Generals()) {
            this.setVisibleInGame_Generals(false);
        }

        if (this.getVisibleInGame_GeneralRecruit()) {
            this.setVisibleInGame_GeneralRecruit(false);
        }

    }

    public final void setVisibleInGame_Civ(boolean visible) {
        if (!visible && ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).getVisible()) {
            InGame_Civ.actionOnClose();
        }

        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CIV)).setVisible(visible);
        if (!visible) {
            this.setVisibleInGame_CivBonuses(false);
        }

        if (visible) {
            if (this.getVisibleInGame_Court()) {
                this.setVisibleInGame_Court(false);
            }

            if (this.getVisibleInGame_Budget()) {
                this.setVisibleInGame_Budget(false);
            }

            this.hideCivCourt_Menus();
            if (this.getVisibleInGame_GoodsMarket()) {
                this.setVisibleInGame_GoodsMarket(false);
            }

            this.setOrderOfMenu(this.IN_GAME_CIV);
        }

    }

    public final void setVisibleInGame_ProvinceBonuses(boolean visible, boolean updateOrder) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_PROVINCE_BONUSES)).setVisible(visible);
        if (visible && updateOrder) {
            this.setOrderOfMenu(this.IN_GAME_PROVINCE_BONUSES);
        }

    }

    public final void setVisibleInGame_GeneralRecruit(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GENERAL_RECRUIT)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_GENERAL_RECRUIT);
        }

    }

    public final void setVisibleInGame_TakeLoan(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TAKE_LOAN)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_TAKE_LOAN);
        }

    }

    public final void setVisibleInGame_TakeLoanRepay(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TAKE_LOAN_REPAY)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_TAKE_LOAN_REPAY);
        }

    }

    public final void setVisibleInGame_Console(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CONSOLE)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_CONSOLE);
        }

    }

    public final boolean getVisibleInGame_Event() {
        return ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_EVENT)).getVisible();
    }

    public final void setVisibleInGame_Event(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_EVENT)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_EVENT);
        }

    }

    public final void setVisibleInGame_CurrentSituation(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CURRENT_SITUATION)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_CURRENT_SITUATION);
        }

    }

    public final void setVisibleInGame_SaveGame(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_SAVE_GAME)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_SAVE_GAME);
        }

    }

    public final void setVisibleInGame_PopUp(boolean visible) {
        if (!visible) {
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_POP_UP)).setVisible(visible);
        }

    }

    public final void setVisibleInGame_AdvisorRecruit(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_ADVISOR_RECRUIT)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_ADVISOR_RECRUIT);
        }

    }

    public final void setVisibleInGame_TechnologyTree(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE_EMPTY)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu_TechnologyTree();
        }

    }

    public final void setVisibleInGame_MissionTree(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_TECHNOLOGY_TREE_EMPTY)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu_TechnologyTree();
        }

    }

    public final void setVisibleInGame_BattleFull(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_FULL)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BATTLE_FULL_EMPTY)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu_BattleFull();
        }

    }

    public final void setVisibleInGame_TechnologyChoose(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_CHOOSE_TECHNOLOGY)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_CHOOSE_TECHNOLOGY);
        }

    }

    public final void setVisibleInGame_Nukes(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_NUKES)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu(this.IN_GAME_NUKES);
        }

    }

    public final void setVisibleInGame_Goods(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS_EMPTY)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS)).setVisible(visible);
        if (visible) {
            this.setOrderOfMenu_InGameGoods();
        }

    }

    public final void setVisibleInGame_GoodsMarket(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_GOODS_CIV)).setVisible(visible);
        this.setOrderOfMenu(this.IN_GAME_GOODS_CIV);
    }

    public final void setVisibleInGame_Wonder(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_WONDER)).setVisible(visible);
    }

    public final void setVisibleInGame_Info(boolean visible) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_INFO)).setVisible(visible);
    }

    public final void setVisibleInGame_Buildings(boolean visible, boolean updateOrder) {
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_0)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_1)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_2)).setVisible(visible);
        ((Menu)((List)this.menus.get(this.IN_GAME)).get(this.IN_GAME_BUILDINGS_GROUP_3)).setVisible(visible);
        if (visible && updateOrder) {
            this.setVisibleInGame_RecruitArmy(false);
            this.setVisibleInGame_Generals(false);
            if (this.getVisibleInGame_Armies()) {
                this.setVisibleInGame_Armies(false);
            }

            this.setVisibleInGame_TechnologyChoose(false);
            this.setVisibleInGame_TechnologyTree(false);
            if (this.getVisibleDeclareWar()) {
                this.setVisibleInGame_PopUp(false);
            }

            if (this.getVisible_SpecialAlliance()) {
                this.setVisibleInGame_PopUp(false);
            }

            if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
                this.setVisibleInGame_PopUp(false);
            }

            if (this.getVisibleInGame_Nukes()) {
                this.setVisibleInGame_Nukes(false);
            }

            if (this.getVisibleInGame_CurrentSituation()) {
                this.setVisibleInGame_CurrentSituation(false);
            }

            this.setOrderInGame_Buildings();
        }

    }

    public final void setOrderInGame_Buildings() {
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_0);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_1);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_2);
        this.setOrderOfMenu(this.IN_GAME_BUILDINGS_GROUP_3);
    }

    public final void updateInGameFlag() {
        try {
            ((Menu)((List)this.menus.get(this.IN_GAME)).get(0)).getMenuElement(0).updateLanguage();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final Menu editorGameCivsEditReligion() {
        return (Menu)((List)this.menus.get(this.EDITOR_GAMECIVS_EDIT)).get(this.EDITOR_GAMECIVS_EDIT_RELIGION);
    }

    public final Menu editorGameCivsEditGroup() {
        return (Menu)((List)this.menus.get(this.EDITOR_GAMECIVS_EDIT)).get(this.EDITOR_GAMECIVS_EDIT_GROUP);
    }

    public final Menu createCivReligion() {
        return (Menu)((List)this.menus.get(this.CREATE_CIV)).get(this.CREATE_CIV_RELIGION);
    }

    public final Menu createCivGroup() {
        return (Menu)((List)this.menus.get(this.CREATE_CIV)).get(this.CREATE_CIV_GROUP);
    }

    public final Menu createCivFlag() {
        return (Menu)((List)this.menus.get(this.CREATE_CIV)).get(this.CREATE_CIV_FLAG);
    }

    public final void rebuildCreateCivFlag() {
        int nX = ((Menu)((List)this.menus.get(this.CREATE_CIV)).get(this.CREATE_CIV_FLAG)).getPosX();
        int nY = ((Menu)((List)this.menus.get(this.CREATE_CIV)).get(this.CREATE_CIV_FLAG)).getPosY();
        int mX = ((Menu)((List)this.menus.get(this.CREATE_CIV)).get(this.CREATE_CIV_FLAG)).getMenuPosX();
        int mY = ((Menu)((List)this.menus.get(this.CREATE_CIV)).get(this.CREATE_CIV_FLAG)).getMenuPosY();
        ((List)this.menus.get(this.CREATE_CIV)).set(this.CREATE_CIV_FLAG, new CreateCiv_Flag());
        ((Menu)((List)this.menus.get(this.CREATE_CIV)).get(this.CREATE_CIV_FLAG)).setVisible(true);
        ((Menu)((List)this.menus.get(this.CREATE_CIV)).get(this.CREATE_CIV_FLAG)).setPosX(nX);
        ((Menu)((List)this.menus.get(this.CREATE_CIV)).get(this.CREATE_CIV_FLAG)).setPosY(nY);
        ((Menu)((List)this.menus.get(this.CREATE_CIV)).get(this.CREATE_CIV_FLAG)).setMenuPosX(mX);
        ((Menu)((List)this.menus.get(this.CREATE_CIV)).get(this.CREATE_CIV_FLAG)).setMenuPosY(mY);
    }

    public final void hideInGameMenus() {
        try {
            if (this.getVisibleInGame_Buildings()) {
                this.setVisibleInGame_Buildings(false, false);
            }

            if (this.getVisibleInGame_Court()) {
                this.setVisibleInGame_Court(false);
            }

            if (this.getVisibleInGame_Civ()) {
                this.setVisibleInGame_Civ(false);
            }

            if (this.getVisibleInGame_Budget()) {
                this.setVisibleInGame_Budget(false);
            }

            if (this.getVisibleInGame_CivBonuses()) {
                this.setVisibleInGame_CivBonuses(false);
            }

            if (this.getVisibleInGame_Wonder()) {
                this.setVisibleInGame_Wonder(false);
            }

            if (this.getVisibleInGame_GeneralRecruit()) {
                this.setVisibleInGame_GeneralRecruit(false);
            }

            if (this.getVisibleInGame_AdvisorRecruit()) {
                this.setVisibleInGame_AdvisorRecruit(false);
            }

            if (this.getVisibleInGame_RecruitArmy()) {
                this.setVisibleInGame_RecruitArmy(false);
            }

            if (this.getVisibleInGame_ProvinceBonuses()) {
                this.setVisibleInGame_ProvinceBonuses(false, false);
            }

            if (this.getVisibleInGame_Generals()) {
                this.setVisibleInGame_Generals(false);
            }

            if (this.getVisibleInGame_GoodsMarket()) {
                this.setVisibleInGame_GoodsMarket(false);
            }

            if (this.getVisibleInGame_Armies()) {
                this.setVisibleInGame_Armies(false);
            }

            if (this.getVisibleInGame_DisbandArmy()) {
                this.setVisibleInGame_DisbandUnits(false);
            }

            if (this.getVisibleInGame_ReorganizeUnits()) {
                this.setVisibleInGame_ReorganizeUnits(false);
            }

            if (this.getVisibleInGame_Battle()) {
                this.setVisibleInGame_Battle(false);
            }

            if (this.getVisibleInGame_Siege()) {
                this.setVisibleInGame_Siege(false);
            }

            if (this.getVisibleInGame_War()) {
                this.setVisibleInGame_War(false);
            }

            if (this.getVisibleInGame_Peace()) {
                this.setVisibleInGame_Peace(false);
            }

            if (this.getVisibleInGame_TechnologyChoose()) {
                this.setVisibleInGame_TechnologyChoose(false);
            }

            if (this.getVisibleInGame_TechnologyTree()) {
                this.setVisibleInGame_TechnologyTree(false);
            }

            if (this.getVisibleDeclareWar()) {
                this.setVisibleInGame_PopUp(false);
            }

            if (this.getVisible_SpecialAlliance()) {
                this.setVisibleInGame_PopUp(false);
            }

            if (this.getVisibleFormCiv() || this.getVisibleSellProvince() || this.getVisibleRevolutions()) {
                this.setVisibleInGame_PopUp(false);
            }

            if (this.getVisibleInGame_Nukes()) {
                this.setVisibleInGame_Nukes(false);
            }

            if (this.getVisibleInGame_CurrentSituation()) {
                this.setVisibleInGame_CurrentSituation(false);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }
}
