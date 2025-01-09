//Notepad is reserved for internal note-taking within IDEs; typically used to cache deprecated/necessary code for reference.
//This file was created due to the impossibility to split screens on the same file.

//CONTEXT MENUS
/*
	function createLineGraph (arg0_options) {
		//Convert from parameters
		var options = (arg0_options) ? arg0_options : {};

		//Initialise options
		try {
			if (!options.data) options.data = [];
			if (options.display_float == undefined) options.display_float = false;
			options.height = returnSafeNumber(options.height, 3);
			options.width = returnSafeNumber(options.width, 3);
			options.x = returnSafeNumber(options.x);
			if (options.x_axis_name == undefined) options.x_axis_name = "";
			options.y = returnSafeNumber(options.y);
			if (options.y_axis_name == undefined)
				options.y_axis_name = (options.name) ? options.name : "";

			//Declare local instance variables
			var actual_height = parseInt((!options.raw_dimensions) ?
				CFG.BUTTON_WIDTH*options.height : options.height);
			var actual_width = parseInt((!options.raw_dimensions) ?
				CFG.BUTTON_WIDTH*options.width : options.width);

			var new_graph_obj = new Graph(
				options.x_axis_name, //(sTextX)
				options.y_axis_name, //(sTextY)
				options.x, //(iPosX)
				options.y, //(iPosY)
				actual_width, //(iWidth)
				actual_height, //(iHeight)
				true, //(visible)
				options.data.length, //(nLoadSize)
				Graph.GraphType.PLAYER_INCOME, //(graphType)
				options.display_float //(split100)
			);

			//Call new_graph_obj.setData() after standardising .civilisation for each entry point
			var graph_data_list = new ArrayList();

			for (var i = 0; i < options.data.length; i++) {
				var local_data = options.data[i];
				var local_graph_data_points = new ArrayList();

				//Set local_data.civilisation, .start
				if (!local_data.civilisation) local_data.civilisation = "neu";
				var local_civilisation_id = getCivilisationID(local_data.civilisation);

				local_data.civilisation = local_civilisation_id;
				local_data.start = returnSafeNumber(local_data.start);

				//Set local_data.values
				local_data.values = getList(local_data.values);
				for (var x = 0; x < local_data.values.length; x++)
					local_graph_data_points.add(new Integer(parseInt(local_data.values[x])));

				//Construct local_graph_data; add to graph_data_list
				var local_graph_data = new GraphData(
					new Integer(local_civilisation_id), //(iCivID)
					local_graph_data_points, //(nPointsY)
					new Integer(local_data.start) //(iBeginTurnID)
				);
				graph_data_list.add(local_graph_data);
				//new_graph_obj.lPointsPosX = local_graph_data_points;
			}
			new_graph_obj.setData(graph_data_list);
			new_graph_obj.buildGraph();

			//Return statement
			return new_graph_obj;
		} catch (e) {
			console.log(e.stack);
		}
	}
 */

//LOCALISATION
/*
Anarchy:
Anarchy ()
Anarcho-Communism (a)

Left-wing Authoritarianism:
Communism (b)
Partial Communism (c)
Titoism (d)
Real Socialism (e)
Marxism-Leninism (f)
Maoism (g)
Stalinism (h)
Dengism (i)
State Capitalism (j)

Democracy:
Eurocommunism (k)
Left-wing Populism (l)
Democratic Socialism (m)
Social Democrat (n)
Social Liberal (o)
Centrism (p)
Market Liberal (q)
Conservative (r)
Reactionary (s)
Right-wing Populism (t)
Illiberal Democracy (u)
Authoritarian Democracy (v)

Right-wing Authoritarianism:
Dictatorship (w)
Authoritarian Conservatism (x)
Authoritarian Dictatorship (y)
Military Junta (z)
Falangism (0)
Clerical Fascism (1)
Fascism (2)
National Socialism (3)

Arabism/Islamism:
Pan-Arabism (4)
Islamic Republic (5)
Islamism (6)
Islamic Theocracy (7)
Radical Islamism (8)

Monarchy:
Electoral Monarchy (9)
Semi-constitutional Monarchy ([)
Theocratic Monarchy (])
Absolute Monarchy ({)
Empire (})
Theocracy (()

Historical:
Federal Republic ())
Mercantile Republic (<)
Republic (>)
 */