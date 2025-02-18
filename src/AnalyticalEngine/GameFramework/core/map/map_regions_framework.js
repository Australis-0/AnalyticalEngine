//Initialise functions
{
	function createRegion (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		if (!options.name) options.name = "New Region";
		if (!options.colour) options.colour = generateRandomColour();

		//Declare local instance variables
		var new_region_obj = {
			sName: options.name,
			iR: options.colour[0],
			iG: options.colour[1],
			iB: options.colour[2]
		};

		(!options.index) ?
			main.map.regions.Data.push(new_region_obj) :
			main.map.regions.Data.splice(options.index + 1, 0, new_region_obj);

		try {
			var all_provinces = getAllProvinces();

			// **Step 1: Track original GeoRegion assignments**
			var province_assignments = {};
			for (var x = 0; x < all_provinces.length; x++) {
				var region_id = all_provinces[x].getGeoRegion();
				if (!province_assignments[region_id]) {
					province_assignments[region_id] = [];
				}
				province_assignments[region_id].push(all_provinces[x]);
			}

			// **Step 2: Shift affected GeoRegion() values UP**
			for (var i = main.map.regions.Data.length - 1; i > options.index; i--) {
				if (province_assignments[i - 1]) { // Only shift valid regions
					for (var j = 0; j < province_assignments[i - 1].length; j++) {
						province_assignments[i - 1][j].setGeoRegion(i);
					}
				}
			}

		} catch (e) {
			console.error("Error updating province regions:", e.stack);
		}

		//Reload in-game menus
		reloadRegions();
	}

	function deleteRegion (arg0_region_name) {
		//Convert from parameters
		var region_name = arg0_region_name;

		//Declare local instance variables
		var current_page = getCurrentPage();
		var region_index = getRegion(region_name, { return_index: true });

		//Delete region
		if (region_index) try {
			main.map.regions.Data.splice(region_index, 1);

			if (EditorMapGeoRegions.currentGeoRegionID >= region_index)
				EditorMapGeoRegions.currentGeoRegionID--;
			if (EditorMapGeoRegions.currentGeoRegionID < 0)
				EditorMapGeoRegions.currentGeoRegionID = 0;

			//Set all provinces previously a part of this region to have a region ID of 0
			var all_provinces = getAllProvinces();

			for (var i = 0; i < all_provinces.length; i++)
				if (all_provinces[i].getGeoRegion() == region_index)
					all_provinces[i].setGeoRegion(0);

			//Reload in-game menus
			reloadRegions();
		} catch (e) {
			console.error(e.stack);
		}
	}

	function getAllRegions () {
		//Return statement
		return main.map.regions.Data;
	}

	/**
	 * getRegion() - Fetches the given region currently in main.map.regions.Data.
	 * @param {String} arg0_region_name
	 * @param {Object} [arg1_options]
	 *  @param {boolean} [arg1_options.return_index=false] - Whether to return the index instead of region object.
	 *
	 * @returns {Object}
	 */
	function getRegion (arg0_region_name, arg1_options) {
		//Convert from parameters
		var region_name = arg0_region_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var main_regions = getAllRegions();
		var region_exists = [false, ""]; //[region_exists, region_obj];
		var region_index = 0;

		//Guard clauses if name matches ID; or is object
		if (typeof region_name == "object") return region_name;
		if (!isNaN(region_name)) return (!options.return_index) ? main_regions[region_name] : region_name;

		//.sName search - soft search 1st, hard search 2nd
		var search_name = region_name.trim().toLowerCase();

		for (var i = 0; i < main_regions.length; i++) {
			var local_region = main_regions[i];

			if (local_region.sName.trim().toLowerCase().indexOf(search_name) != -1) {
				region_index = i;
				region_exists = [true, local_region];
			}
		}
		for (var i = 0; i < main_regions.length; i++) {
			var local_region = main_regions[i];

			if (local_region.sName.trim().toLowerCase() == search_name) {
				region_index = i;
				region_exists = [true, local_region];
			}
		}

		//Return statement
		if (options.return_index)
			return (region_exists[0]) ? region_index : undefined;
		return (region_exists[0]) ? region_exists[1] : undefined;
	}

	/**
	 * modifyRegion() - Creates/modifies an extant region to change the region for.
	 * @param {String} arg0_region_name
	 * @param {Object} [arg1_options]
	 *  @param {String} [arg1_options.name="New Region"]
	 *
	 *  @param {Array<number, number, number>} [arg1_options.colour=generateRandomColour()]
	 */
	function modifyRegion (arg0_region_name, arg1_options) {
		//Convert from parameters
		var region_name = arg0_region_name;
		var options = (arg1_options) ? arg1_options : {};

		//Declare local instance variables
		var region_index = getRegion(region_name, { return_index: true });

		//If region_index exists, modify that. Otherwise, create a new region
		if (region_index != undefined) {
			var region_obj = getAllRegions()[region_index];

			if (options.name) region_obj.sName = options.name;
			if (options.colour) {
				region_obj.iR = options.colour[0];
				region_obj.iG = options.colour[1];
				region_obj.iB = options.colour[2];
			}

			if (options.index) {
				var old_index = region_index;
				var new_index = options.index;

				main.map.regions.Data = moveElement(main.map.regions.Data, region_index, options.index);

				try {
					var all_provinces = getAllProvinces();

					// **Step 1: Track province assignments BEFORE shifting**
					var province_assignments = {};
					for (var x = 0; x < all_provinces.length; x++) {
						var region_id = all_provinces[x].getGeoRegion();
						if (!province_assignments[region_id]) {
							province_assignments[region_id] = [];
						}
						province_assignments[region_id].push(all_provinces[x]);
					}

					// **Step 2: Shift affected regions' `GeoRegion()` values correctly**
					if (old_index < new_index) {
						// Moving Down: Shift all regions in range UP
						for (var i = old_index + 1; i <= new_index; i++) {
							if (province_assignments[i]) {
								for (var j = 0; j < province_assignments[i].length; j++) {
									province_assignments[i][j].setGeoRegion(i - 1);
								}
							}
						}
					} else {
						// Moving Up: Shift all regions in range DOWN
						for (var i = old_index - 1; i >= new_index; i--) {
							if (province_assignments[i]) {
								for (var j = 0; j < province_assignments[i].length; j++) {
									province_assignments[i][j].setGeoRegion(i + 1);
								}
							}
						}
					}

					// **Step 3: Assign the moved region to its new index**
					if (province_assignments[old_index]) {
						for (var j = 0; j < province_assignments[old_index].length; j++) {
							province_assignments[old_index][j].setGeoRegion(new_index);
						}
					}
				} catch (e) {
					console.error(e.stack);
				}
			}
		} else {
			createRegion(options);
		}

		reloadRegions();
	}

	function reloadRegions () {
		var current_page = getCurrentPage();

		//Reload in-game menus
		saveRegions();
		Game.geographicalRegions.loadMapGeographicalRegions();

		if (current_page == "EDITOR_MAPS_EDIT_GEO_REGION") try {
			var geo_region_lists = Game.menuManager.menus.get(Game.menuManager.EDITOR_MAPS_EDIT_GEO_REGION);

			var current_scroll_y = geo_region_lists.get(1).getNewMenuPosY();

			geo_region_lists.get(0).setVisible(false);
			geo_region_lists.get(1).setVisible(false);
			geo_region_lists.set(0, new EditorMapGeoRegions());
			geo_region_lists.set(1, new EditorMapGeoRegionsList());

			geo_region_lists.get(1).updateMenuPosY(current_scroll_y);
		} catch (e) {
			console.error(e.stack);
		}
	}

	function shiftMapRegions (arg0_value, arg1_options) { //[WIP] - Finish function body
		//Convert from parameters
		var shift_value = arg0_value;
		var options = (arg1_options) ? arg1_options : {};

		//Initialise options
		options.excluded_ids = (options.excluded_ids != undefined) ? getList(options.excluded_ids) : [0];

		//Declare local instance variables
		var all_provinces = getAllProvinces();
		var all_regions = getAllRegions();

		//Iterate over all_provinces and invoke .setGeoRegion()
		for (var i = 0; i < all_provinces.length; i++) {

		}
	}
}