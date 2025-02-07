//Import classes - Static Imports
//General Classes
{
	this.AdvantagesManager = Java.type("aoc.kingdoms.lukasz.map.AdvantagesManager");
	this.Advisor = Java.type("aoc.kingdoms.lukasz.map.advisors.Advisor");
	this.AdvisorManager = Java.type("aoc.kingdoms.lukasz.map.advisors.AdvisorManager");
	this.ArrayList = Java.type("java.util.ArrayList");
	this.BufferedImage = Java.type("java.awt.image.BufferedImage");
	this.BufferedReader = Java.type("java.io.BufferedReader");
	this.BufferedWriter = Java.type("java.io.BufferedWriter");
	this.BuildingsManager = Java.type("aoc.kingdoms.lukasz.map.BuildingsManager");
	this.CFG = Java.type("aoc.kingdoms.lukasz.jakowski.CFG");
	this.Civilization = Java.type("aoc.kingdoms.lukasz.map.civilization.Civilization");
	this.CivilizationRegionsManager = Java.type("aoc.kingdoms.lukasz.map.civilization.CivilizationRegionsManager");
	this.classLoader = classLoader;
	this.Color = Java.type("com.badlogic.gdx.graphics.Color");
	this.DiplomacyManager = Java.type("aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager");
	this.File = Java.type("java.io.File");
	this.FileHandle = Java.type("com.badlogic.gdx.files.FileHandle");
	this.FileReader = Java.type("java.io.FileReader");
	this.Files = Java.type("java.nio.file.Files");
	this.FileWriter = Java.type("java.io.FileWriter");
	this.Float = Java.type("java.lang.Float");
	this.Game_Calendar = Java.type("aoc.kingdoms.lukasz.jakowski.Game_Calendar");
	this.GameCivs = Java.type("aoc.kingdoms.lukasz.menusEditor.GameCivs");
	this.GameCivsEdit = Java.type("aoc.kingdoms.lukasz.menusEditor.GameCivsEdit");
	this.Gdx = Java.type("com.badlogic.gdx.Gdx");
	this.Graphics2D = Java.type("java.awt.Graphics2D");
	this.IdeologiesManager = Java.type("aoc.kingdoms.lukasz.map.IdeologiesManager");
	this.ImageIO = Java.type("javax.imageio.ImageIO")
	this.InGame_DeepscriptEvent = Java.type("AnalyticalEngine.Java.menus.InGame_DeepscriptEvent");
	this.InputStreamReader = Java.type("java.io.InputStreamReader");
	this.Integer = Java.type("java.lang.Integer");
	this.Keyboard = Java.type("aoc.kingdoms.lukasz.jakowski.Keyboard");
	this.LegacyManager = Java.type("aoc.kingdoms.lukasz.map.LegacyManager");
	this.M_Players = Java.type("aoc.kingdoms.lukasz.jakowski.Steam.Multi.M_Players");
	this.MenuElement_Hover = Java.type("aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover");
	this.MenuElement_HoverElement_Type_Text = Java.type("aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text");
	this.MenuElement_HoverElement_Type_FlagCiv_Title = Java.type("aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title");
	this.Menu_LoadScenario = Java.type("aoc.kingdoms.lukasz.menus.LoadSave.Menu_LoadScenario");
	this.OnscreenKeyboardType = Java.type("com.badlogic.gdx.Input.OnscreenKeyboardType");
	this.Paths = Java.type("java.nio.file.Paths");
	this.Pixmap = Java.type("com.badlogic.gdx.graphics.Pixmap");
	this.PixmapIO = Java.type("com.badlogic.gdx.graphics.PixmapIO");
	this.PrintWriter = Java.type("java.io.PrintWriter");
	this.ProvinceConstructedBuilding = Java.type("aoc.kingdoms.lukasz.map.province.ProvinceConstructedBuilding");
	this.ProvinceDraw = Java.type("aoc.kingdoms.lukasz.map.province.ProvinceDraw");
	this.ProvinceDrawDetails = Java.type("aoc.kingdoms.lukasz.map.province.ProvinceDrawDetails");
	this.Random = Java.type("java.util.Random");
	this.ReligionManager = Java.type("aoc.kingdoms.lukasz.map.ReligionManager");
	this.ResourcesManager = Java.type("aoc.kingdoms.lukasz.map.ResourcesManager");
	this.Runnable = Java.type("java.lang.Runnable");
	this.SaveManager = Java.type("aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveManager");
	this.ScenarioWasteland = Java.type("aoc.kingdoms.lukasz.menusScenarioEditor.Wasteland.ScenarioWasteland");
	this.SteamManager = Java.type("aoc.kingdoms.lukasz.jakowski.Steam.SteamManager");
	this.StringBuilder = Java.type("java.lang.StringBuilder");
	this.System = Java.type("java.lang.System");
	this.TechnologyTree = Java.type("aoc.kingdoms.lukasz.map.technology.TechnologyTree");
	this.Terrain = Java.type("aoc.kingdoms.lukasz.map.terrain.Terrain");
	this.Texture = Java.type("com.badlogic.gdx.graphics.Texture");
	this.Thread = Java.type("java.lang.Thread");
	this.Touch = Java.type("aoc.kingdoms.lukasz.jakowski.Touch");
	this.View = Java.type("aoc.kingdoms.lukasz.menu.View");
	this.WarManager = Java.type("aoc.kingdoms.lukasz.map.war.WarManager");
}

//Menu Classes
{
	this.AA_Game = Java.type("aoc.kingdoms.lukasz.jakowski.AA_Game");
	this.ArrayList = Java.type("java.util.ArrayList");
	this.ButtonFlag2_CivName = Java.type("aoc.kingdoms.lukasz.menu_element.button.ButtonFlag2_CivName");
	this.ButtonMain = Java.type("aoc.kingdoms.lukasz.menu_element.button.ButtonMain");
	this.ButtonStatsRect_Active = Java.type("aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRect_Active");
	this.CFG = Java.type("aoc.kingdoms.lukasz.jakowski.CFG");
	this.DesktopLauncher = Java.type("aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher");
	this.Float = Java.type("java.lang.Float");
	this.Graph = Java.type("aoc.kingdoms.lukasz.menu_element.graph.Graph");
	this.GraphData = Java.type("aoc.kingdoms.lukasz.menu_element.graph.GraphData");
	this.Graph_Vertical = Java.type("aoc.kingdoms.lukasz.menu_element.graph.Graph_Vertical");
	this.Graph_Vertical_Data_Type = Java.type("aoc.kingdoms.lukasz.menu_element.graph.Graph_Vertical_Data_Type");
	this.Integer = Java.type("java.lang.Integer");
	//this.Menu = Java.type("aoc.kingdoms.lukasz.menu.Menu");
	//this.MenuTitle = Java.type("aoc.kingdoms.lukasz.menu.menuTitle");
	this.Method = Java.type("java.lang.reflect.Method");
	this.Minimap = Java.type("aoc.kingdoms.lukasz.menu_element.Minimap");
	this.PieChart = Java.type("aoc.kingdoms.lukasz.menu_element.pieChart.PieChart");
	this.PieChart_Data = Java.type("aoc.kingdoms.lukasz.menu_element.pieChart.PieChart_Data");
	this.PieChart_Value = Java.type("aoc.kingdoms.lukasz.menu_element.pieChart.PieChart_Value");
	this.PieChart_WithStats = Java.type("aoc.kingdoms.lukasz.menu_element.pieChart.PieChart_WithStats");
	this.Slider = Java.type("aoc.kingdoms.lukasz.menu_element.Slider");

	this.boolean_class = Java.type("java.lang.Boolean").TYPE;
	this.integer_class = Java.type("java.lang.Integer").TYPE;
	this.list_class = Java.type("java.util.List");
	this.Renderer = Java.type("aoc.kingdoms.lukasz.jakowski.Renderer.Renderer");
	this.Text_Scrollable = Java.type("aoc.kingdoms.lukasz.menu_element.textStatic.Text_Scrollable");
	this.TextBG = Java.type("aoc.kingdoms.lukasz.menu_element.textStatic.TextBG");
	this.Text_Static = Java.type("aoc.kingdoms.lukasz.menu_element.textStatic.Text_Static");
	this.Text_StaticBG_ID_FlagCiv = Java.type("aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_ID_FlagCiv");
	this.Touch = Java.type("aoc.kingdoms.lukasz.jakowski.Touch");
}

//Import classes - Dynamic Imports
{
	function importDynamicClasses () {
		Object.defineProperty(this, "Game", {
			get: function () {
				return Java.type('aoc.kingdoms.lukasz.jakowski.Game');
			}
		});
		Object.defineProperty(this, "Menu", {
			get: function () {
				return Java.type('aoc.kingdoms.lukasz.menu.Menu');
			}
		});
		Object.defineProperty(this, "MenuTitle", {
			get: function () {
				return Java.type('aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle');
			}
		});
	}
}