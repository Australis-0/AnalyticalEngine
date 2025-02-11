//Initialise functions
{
	//Meta.
	{
		function provinceControllerIs (arg0_provinces, arg1_civ_tags) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var civ_tags = getList(arg1_civ_tags);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_controller = getProvinceController(provinces[i]);

				if (!civ_tags.includes(local_controller))
					return false;
			}

			//Return statement
			return true;
		}

		function provinceControllerIsNot (arg0_provinces, arg1_civ_tags) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var civ_tags = getList(arg1_civ_tags);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_controller = getProvinceController(provinces[i]);

				if (civ_tags.includes(local_controller))
					return false;
			}

			//Return statement
			return true;
		}

		function provinceIsCapital (arg0_provinces) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				if (!local_province.isCapital)
					return false;
			}

			//Return statement
			return true;
		}

		function provinceIsCapitalOf (arg0_provinces, arg1_civ_tags) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var civ_tags = getList(arg1_civ_tags);

			//Declare local instance variables
			var capital_province_ids = [];

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				capital_province_ids.push(getCivilisationCapital(civ_tags[i]).getProvinceID());

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				if (!capital_province_ids.includes(local_province.getProvinceID()))
					return false;
			}

			//Return statement
			return true;
		}

		function provinceIsCore (arg0_provinces, arg1_civ_tags) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);
				var local_province_cores = getProvinceCores(provinces[i]);

				for (var x = 0; x < civ_tags.length; x++)
					if (!local_province_cores.includes(civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function provinceIsOccupied (arg0_provinces) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				if (!isOccupied(local_province))
					return false;
			}

			//Return statement
			return true;
		}

		function provinceIsOccupiedBy (arg0_provinces, arg1_civ_tags) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				if (isOccupied(local_province))
					if (!civ_tags.includes(getProvinceController(provinces[i])))
						return false;
			}

			//Return statement
			return true;
		}

		function provinceIsNotCapital (arg0_provinces) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				if (local_province.isCapital)
					return false;
			}

			//Return statement
			return true;
		}

		function provinceIsNotCapitalOf (arg0_provinces, arg1_civ_tags) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var civ_tags = getList(arg1_civ_tags);

			//Declare local instance variables
			var capital_province_ids = [];

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				capital_province_ids.push(getCivilisationCapital(civ_tags[i]).getProvinceID());

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);

				if (capital_province_ids.includes(local_province.getProvinceID()))
					return false;
			}

			//Return statement
			return true;
		}

		function provinceIsNotCore (arg0_provinces, arg1_civ_tags) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province = getProvince(provinces[i]);
				var local_province_cores = getProvinceCores(provinces[i]);

				for (var x = 0; x < civ_tags.length; x++)
					if (local_province_cores.includes(civ_tags[x]))
						return false;
			}

			//Return statement
			return true;
		}

		function provinceIsNotUnderSiege (arg0_provinces) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++)
				if (isUnderSiege(provinces[i]))
					//Return statement
					return false;
			return true;
		}

		function provinceIsNotUnderSiegeBy (arg0_provinces, arg1_civ_tags) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province =  getProvince(provinces[i]);

				if (civ_tags.includes(getProvinceSiegeOccupier(local_province)))
					return false;
			}

			//Return statement
			return true;
		}

		function provinceIsUnderSiege (arg0_provinces) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++)
				if (!isUnderSiege(provinces[i]))
					//Return statement
					return false;
			return true;
		}

		function provinceIsUnderSiegeBy (arg0_provinces, arg1_civ_tags) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var civ_tags = getList(arg1_civ_tags);

			//Iterate over civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_province =  getProvince(provinces[i]);

				if (!civ_tags.includes(getProvinceSiegeOccupier(local_province)))
					return false;
			}

			//Return statement
			return true;
		}

		function provinceOwnerIs (arg0_provinces, arg1_civ_tags) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var civ_tags = getList(arg1_civ_tags);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_owner = getProvinceOwner(provinces[i]);

				if (!civ_tags.includes(local_owner))
					return false;
			}

			//Return statement
			return true;
		}

		function provinceOwnerIsNot (arg0_provinces, arg1_civ_tags) {
			//Convert from parameters
			var provinces = getList(arg0_provinces);
			var civ_tags = getList(arg1_civ_tags);

			//Iterate over all civ_tags
			for (var i = 0; i < civ_tags.length; i++)
				civ_tags[i] = getCurrentTag(civ_tags[i]);

			//Iterate over all provinces
			for (var i = 0; i < provinces.length; i++) {
				var local_owner = getProvinceOwner(provinces[i]);

				if (civ_tags.includes(local_owner))
					return false;
			}

			//Return statement
			return true;
		}
	}
}