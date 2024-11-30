//Import classes
{
	//this.Game = "aoc.kingdoms.lukasz.jakowski.Game"; - Dynamically loaded
	this.ProvinceDraw = Java.type("aoc.kingdoms.lukasz.map.province.ProvinceDraw");
}

//Initialise functions
{
	function clearMap () {
		switchMapmode("MODE_DEFAULT_TERRAIN");
		ProvinceDraw.updateDrawProvinces_Standard();
		Game.mapModes.updateViews();

		delete main.map.custom_mapmode;
	}

	function getAllMapmodes () {
		//Return statement
		return ["MODE_DEFAULT", "MODE_DEFAULT_TERRAIN", "MODE_POPULATION", "MODE_ECONOMY", "MODE_PROVINCE_INCOM", "MODE_PROVINCE_INCOME_HOVER", "MODE_PROVINCE_EXPENSES_HOVER", "MODE_CIV_POPULATION_HOVER", "MODE_CIV_ECONOMY_HOVER", "MODE_PROVINCE_MANPOWER_HOVER_PLAYER", "MODE_PROVINCE_GROWTH_RATE_HOVER", "MODE_PROVINCE_MANPOWER_HOVER", "MODE_PROVINCE_ECONOMY_HOVER", "MODE_PROVINCE_TAX_EFFICIENCY_HOVER", "MODE_PROVINCE_POPULATION_HOVER", "MODE_PROVINCE_RELIGION_HOVER", "MODE_PROVINCE_FORTS_HOVER", "MODE_PROVINCE_INCOME_HOVER", "MODE_PROVINCE_INFRASTRUCTURE_HOVER", "MODE_PROVINCE_LOOT_HOVER", "MODE_PROVINCE_PROVINCE_VALUE_HOVER", "MODE_PROVINCE_DEVASTATION_HOVER", "MODE_PROVINCE_UNREST_HOVER", "MODE_PROVINCE_TAX", "MODE_TERRAIN", "MODE_GOODS", "MODE_INFRASTRUCTURE", "MODE_WONDERS", "MODE_RELIGION", "MODE_GOVERNMENT", "MODE_DEVASTATION", "MODE_LOOT", "MODE_DEFENSE_LEVEL", "MODE_UNREST", "MODE_DISEASES", "MODE_WARS", "MODE_ALLIANCES", "MODE_DEFENSIVE_PACTS", "MODE_NON_AGGRESSION_PACTS", "MODE_RECRUIT_ARMY", "MODE_NEW_ARMY_CHOOSE_PROVINCE", "MODE_NUKE_CHOOSE_PROVINCE", "MODE_MERCENARIES_CHOOSE_PROVINCE", "MODE_INVEST_IN_ECONOMY", "MODE_INCREASE_TAX_EFFICIENCY", "MODE_INCREASE_MANPOWER", "MODE_MOVE_CAPITAL", "MODE_COLONIZE_CHOOSE_PROVINCE", "MODE_DEVELOP_INFRASTRUCTURE", "MODE_INCREASE_GROWTH_RATE", "MODE_CIV_STABILITY_HOVER", "MODE_BUILDING", "MODE_CONVERT_RELIGION", "MODE_CORE", "MODE_DIPLOMACY", "MODE_DIPLOMACY_IMPROVE_RELATIONS", "MODE_DIPLOMACY_DAMAGE_RELATIONS", "MODE_DECLARE_WAR", "MODE_FORM_CIV", "MODE_SPECIAL_ALLIANCE_VIEW", "MODE_WAR_VIEW", "MODE_PEACE_VIEW", "MODE_RELEASE_VASSAL"];
	}

	function getCurrentMapmode () {
		//Return statement
		if (main.map.custom_mapmode)
			return main.map.custom_mapmode;

		//Iterate over main.map.mapmodes if custom mapmode is not active
		for (var i = 0; i < main.map.mapmodes.length; i++)
			if (Game.mapModes[main.map.mapmodes[i]])
				if (Game.mapModes.iActiveMapModeID == Game.mapModes[main.map.mapmodes[i]])
					//Return statement
					return main.map.mapmodes[i];

		//Return statement
		return "MODE_DEFAULT_TERRAIN";
	}

	function getMapmodeID (arg0_mapmode) {
		//Convert from parameters
		var mapmode = arg0_mapmode;

		//Return statement
		return Game.mapModes[mapmode];
	}

	function switchMapmode (arg0_mapmode) {
		//Convert from parameters
		var mapmode = arg0_mapmode;

		//Declare local instance variables
		var mapmode_id = getMapmodeID(mapmode);

		//Custom mapmode handler
		if (mapmode_id) {
			delete main.map.custom_mapmode;
			Game.mapModes.setActiveViewID(mapmode_id);
		} else {
			clearMap();
			main.map.custom_mapmode = mapmode;
		}
	}
}