//Initialise functions
{
	function aStar (arg0_graph, arg1_start_key, arg2_end_key) {
		//Convert from parameters
		var graph = arg0_graph;
		var start = arg1_start_key;
		var goal = arg2_end_key;

		//Declare local instance variables
		var came_from = {};
		var f_score = {};
		var g_score = {};
		var open_set = [start];
		var path = [];

		//Initialise f_score, g_score
		g_score[start] = 0;
		f_score[start] = aStarHeuristicCost(start, goal);

		//Recursive traversal
		while (open_set.length > 0) {
			var current = getLowestFScoreNode(open_set, f_score);

			if (current == goal) {
				path = reconstructPath(came_from, current);
				break;
			}

			//Check sub-nodes
			open_set = open_set.filter((node) => node != current);

			var neighbours = graph[current];

			//Consider neighbours looking forwards
			for (var neighbour in neighbours) {
				var tentative_g_score = g_score[current] + neighbours[neighbour];

				if (!g_score.hasOwnProperty(neighbour) || tentative_g_score < g_score[neighbour]) {
					came_from[neighbour] = current;
					g_score[neighbour] = tentative_g_score;
					f_score[neighbour] = g_score[neighbour] + aStarHeuristicCost(neighbour, goal);

					if (!open_set.includes(neighbour))
						open_set.push(neighbour);
				}
			}

			//Consider neighbours in the reverse direction for bidirectional connections
			var reverse_neighbours = getReverseNeighbours(graph, current);

			for (var reverse_neighbour in reverse_neighbours) {
				var tentative_g_score_reverse = g_score[current] + reverse_neighbours[reverse_neighbour];

				if (!g_score.hasOwnProperty(reverse_neighbour) || tentative_g_score_reverse < g_score[reverse_neighbour]) {
					came_from[reverse_neighbour] = current;
					g_score[reverse_neighbour] = g_score[reverse_neighbour] + aStarHeuristicCost(reverse_neighbour, goal);

					if (!open_set.includes(reverse_neighbour))
						open_set.push(reverse_neighbour);
				}
			}
		}

		//Return statement
		return path;
	}

	function aStarHeuristicCost (arg0_start_key, arg1_end_key) {
		//Convert from parameters
		var start = arg0_start_key;
		var goal = arg1_end_key;

		//Return statement; placeholder
		return 0;
	}

	function getLowestFScoreNode (arg0_nodes, arg1_f_score) {
		//Convert from parameters
		var nodes = arg0_nodes;
		var f_score = arg1_f_score;

		//Declare local instance variables
		var lowest = nodes[0];

		//Iterate over all nodes
		for (var i = 1; i < nodes.length; i++)
			if (f_score[nodes[i]] < f_score[lowest])
				lowest = nodes[i];

		//Return statement
		return lowest;
	}

	function getReverseNeighbours (arg0_graph, arg1_node) {
		//Convert from parameters
		var graph = arg0_graph;
		var node = arg1_node;

		//Declare local instance variables
		var reverse_neighbours = {};

		//Iterate over graph
		for (var key in graph)
			if (graph.hasOwnProperty(key)) {
				var connections = graph[key];

				for (var neighbour in connections)
					if (neighbour == node)
						reverse_neighbours[key] = connections[neighbour];
			}

		//Return statement
		return reverse_neighbours;
	}

	function reconstructPath (arg0_came_from, arg1_current) {
		//Convert from parameters
		var came_from = arg0_came_from;
		var current = arg1_current;

		//Declare local instance variables
		var total_path = [current];

		//While loop to reconstruct path
		while (came_from.hasOwnProperty(current)) {
			current = came_from[current];
			total_path.unshift(current);
		}

		//Return statement
		return total_path;
	}
}