//Initialise functions
{
	function onArmyDisband (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_army_disband[generateRandomID(main.scopes.on_army_disband)] = local_function;
	}

	function onArmyRecruitment (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_army_recruitment[generateRandomID(main.scopes.on_army_recruitment)] = local_function;
	}

	function onBuildingConstruction (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_building_construction[generateRandomID(main.scopes.on_building_construction)] = local_function;
	}

	function onRival (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_rival[generateRandomID(main.scopes.on_rival)] = local_function;
	}

	function onSiegeEnd (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_siege_end[generateRandomID(main.scopes.on_siege_end)] = local_function;
	}

	function onWar (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_war[generateRandomID(main.scopes.on_war)] = local_function;
	}

	function onWarEnd (arg0_function) {
		//Convert from parameters
		var local_function = arg0_function;

		//Declare local instance variables
		main.scopes.on_war_end[generateRandomID(main.scopes.on_war_end)] = local_function;
	}
}

//Initialise internal helper functions
{
	function parseOnArmyDisband (arg0_army_obj) {
		//Convert from parameters
		var army_obj = arg0_army_obj;

		//Declare local instance variables
		var all_on_army_disband_keys = Object.keys(main.scopes.on_army_disband);
		var civ_obj = getCivilisation(army_obj.civID);
		var civ_tag = getCurrentTag(civ_obj);

		//Iterate over all_on_army_disband_keys
		for (var i = 0; i < all_on_army_disband_keys.length; i++) {
			var local_function = main.scopes.on_army_disband[all_on_army_disband_keys[i]];

			local_function({
				army_obj: army_obj,
				civ_obj: civ_obj,
				civ_tag: civ_tag
			});
		}
	}

	function parseOnArmyRecruitment (arg0_army_obj) {
		//Convert from parameters
		var army_obj = arg0_army_obj;

		//Declare local instance variables
		var target_province_id = Game.getCiv(Game.getProvince(army_obj.provinceID).getCivID()).lCreateNewArmy.get(army_obj.toArmyKey);

		var all_on_army_recruitment_keys = Object.keys(main.scopes.on_army_recruitment);
		var civ_obj = getCivilisation(getProvinceOwner(target_province_id, { return_object: true }));
		var civ_tag = getCurrentTag(civ_obj);
		var province_obj = getProvince(target_province_id);

		//Iterate over all_on_army_recruitment_keys
		for (var i = 0; i < all_on_army_recruitment_keys.length; i++) {
			var local_function = main.scopes.on_army_recruitment[all_on_army_recruitment_keys[i]];

			local_function({
				army_obj: army_obj,
				civ_obj: civ_obj,
				civ_tag: civ_tag,
				province_id: target_province_id,
				province_obj: province_obj
			});
		}
	}

	function parseOnBuildingConstruction (arg0_province_obj, arg1_province_construction_building) {
		//Convert from parameters
		var province_obj = arg0_province_obj;
		var province_construction_building = arg1_province_construction_building;

		//Declare local instance variables
		var province_id = province_obj.getProvinceID();

		var civ_obj = getProvinceOwner(province_obj, { return_object: true });
		var civ_tag = getCurrentTag(civ_obj);
		var province_building_category_index = province_construction_building.getBuilding();
		var province_building_index = province_construction_building.getBuildingID();

		try {
			var all_on_building_construction_keys = Object.keys(main.scopes.on_building_construction);
			var building_key = getBuilding(province_building_category_index).Name[province_building_index];

			var building_obj = getBuilding(building_key);

			//Iterate over all_on_building_construction_keys
			for (var i = 0; i < all_on_building_construction_keys.length; i++) {
				var local_function = main.scopes.on_building_construction[all_on_building_construction_keys[i]];

				local_function({
					civ_obj: civ_obj,
					civ_tag: civ_tag,
					province_id: province_id,
					province_obj: province_obj,

					building_key: building_key,
					building_obj: building_obj
				});
			}
		} catch (e) {
			console.log(e.stack);
		}
	}

	function parseOnRival (arg0_civ_obj, arg1_civ_obj) {
		//Convert from parameters
		var civ_obj = arg0_civ_obj;
		var ot_civ_obj = arg1_civ_obj;

		//Declare local instance variables
		var civ_tag = getCurrentTag(civ_obj);
		var ot_civ_tag = getCurrentTag(ot_civ_obj);

		var all_on_rival_keys = Object.keys(main.scopes.on_rival);

		//Iterate over all_on_rival_keys
		for (var i = 0; i < all_on_rival_keys.length; i++) {
			var local_function = main.scopes.on_rival[all_on_rival_keys[i]];

			local_function({
				civ_obj: civ_obj,
				civ_tag: civ_tag,
				ot_civ_obj: ot_civ_obj,
				ot_civ_tag: ot_civ_tag
			});
		}
	}

	function parseOnSiege (arg0_province_id) {
		//Convert from parameters
		var province_id = arg0_province_id;

		//Delay the initial function to ensure occupation is correctly updated
		setTimeout(function(){
			//Declare local instance variables
			var province_obj = getProvince(province_id);

			var civ_obj = getProvinceOwner(province_obj, { return_object: true });
			var civ_tag = getCurrentTag(civ_obj);
			var ot_civ_obj = getProvinceController(province_obj, { return_object: true });
			var ot_civ_tag = getCurrentTag(ot_civ_obj);

			var all_on_siege_end_keys = Object.keys(main.scopes.on_siege_end);

			//Iterate over all_on_siege_keys
			for (var i = 0; i < all_on_siege_end_keys.length; i++) {
				var local_function = main.scopes.on_siege_end[all_on_siege_end_keys[i]];

				local_function({
					civ_obj: civ_obj,
					civ_tag: civ_tag,
					ot_civ_obj: ot_civ_obj,
					ot_civ_tag: ot_civ_tag,
					province_id: province_id,
					province_obj: province_obj
				});
			}
		}, 0);
	}

	function parseOnWar (arg0_war_obj) {
		//Convert from parameters
		var war_obj = arg0_war_obj;

		//Declare local instance variables
		var all_on_war_keys = Object.keys(main.scopes.on_war);
		var attackers = [];
		var defenders = [];

		//Iterate over lAggressors
		for (var i = 0; i < war_obj.lAggressors.size(); i++)
			attackers.push(getCurrentTag(war_obj.lAggressors.get(i).iCivID));

		//Iterate over lDefenders
		for (var i = 0; i < war_obj.lDefenders.size(); i++)
			defenders.push(getCurrentTag(war_obj.lDefenders.get(i).iCivID));

		//Iterate over all_on_war_keys
		for (var i = 0; i < all_on_war_keys.length; i++) {
			var local_function = main.scopes.on_war[all_on_war_keys[i]];

			local_function({
				attackers: attackers,
				defenders: defenders,
				war_obj: war_obj
			});
		}
	}

	function parseOnWarEnd (arg0_war_obj) {
		//Convert from parameters
		var war_obj = arg0_war_obj;

		//Declare local instance variables
		var all_on_war_end_keys = Object.keys(main.scopes.on_war_end);
		var attackers = [];
		var defenders = [];

		//Iterate over lAggressors
		for (var i = 0; i < war_obj.lAggressors.size(); i++)
			attackers.push(getCurrentTag(war_obj.lAggressors.get(i).iCivID));

		//Iterate over lDefenders
		for (var i = 0; i < war_obj.lDefenders.size(); i++)
			defenders.push(getCurrentTag(war_obj.lDefenders.get(i).iCivID));

		//Iterate over all_on_war_end_keys
		for (var i = 0; i < all_on_war_end_keys.length; i++) {
			var local_function = main.scopes.on_war_end[all_on_war_end_keys[i]];

			local_function({
				attackers: attackers,
				defenders: defenders,
				war_obj: war_obj
			});
		}
	}
}