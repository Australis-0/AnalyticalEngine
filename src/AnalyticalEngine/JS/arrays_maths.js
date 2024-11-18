//Initialise functions
{
	function absoluteValueArray (arg0_array) {
		//Convert from parameters
		var array = getList(arg0_array);

		//Return statement
		return operateArray(array, "Math.abs(n)");
	}

	function absoluteValueArrays (arg0_array, arg1_array, arg2_options) {
		//Convert from parameters
		var array = getList(arg0_array);
		var ot_array = getList(arg1_array);
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateArrays(array, ot_array, "Math.abs(i - x)", options);
	}

	function addArray (arg0_array, arg1_number) {
		//Convert from parameters
		var array = getList(arg0_array);
		var number = arg1_number;

		//Return statement
		return operateArray(array, "n + " + number);
	}

	function addArrays (arg0_array, arg1_array, arg2_options) {
		//Convert from parameters
		var array = getList(arg0_array);
		var ot_array = getList(arg1_array);
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateArrays(array, ot_array, "i + x", options);
	}

	function addMatrices (arg0_matrix, arg1_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;
		var ot_matrix = arg1_matrix;

		//Declare local instance variables
		var return_matrix = [];

		//Iterate over matrix rows
		for (var i = 0; i < matrix.length; i++) {
			//Create a new row for return_matrix
			return_matrix.push([]);

			//Iterate over columns
			for (var x = 0; x < matrix[i].length; x++)
				//Add corresponding elements and push to the result matrix
				return_matrix[i].push(matrix[i][x] + ot_matrix[i][x]);
		}

		//Return statement
		return return_matrix;
	}

	function appendArrays (arg0_array, arg1_array) {
		//Convert from parameters
		var array = getList(arg0_array);
		var ot_array = getList(arg1_array);

		//Return statement
		return array.concat(ot_array);
	}

	function arrayIsOfType (arg0_array, arg1_type) {
		//Convert from parameters
		var array = getList(arg0_array);
		var type = arg1_type;

		//Declare local instance variables
		var check_failed = false;

		//Iterate over array
		for (var i = 0; i < array.length; i++)
			if (typeof array[i] != type)
				check_failed = true;

		//Return statement
		return (!check_failed);
	}

	function augmentMatrices (arg0_matrix, arg1_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;
		var ot_matrix = arg1_matrix;

		//Declare local instance variables
		var return_matrix = [];

		//Iterate over all matrix elements
		for (var i = 0; i < matrix.length; i++)
			return_matrix.push(matrix[i].concat(ot_matrix[i]));

		//Return statement
		return return_matrix;
	}

	function choleskyDecompositionMatrix (arg0_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;

		//Declare local instance variables
		var lower_triangular_matrix = [];

		//Iterate over lower_triangular_matrix; Populate lower_triangular_matrix
		for (var i = 0; i < matrix.length; i++) {
			lower_triangular_matrix[i] = [];
			for (var x = 0; x < matrix.length; x++)
				lower_triangular_matrix[i][x] = 0;
		}

		//Perform Cholesky decomposition
		for (var i = 0; i < matrix.length; i++)
			for (var x = 0; x <= i; x++) {
				var local_sum = 0;

				if (i == x) {
					//Diagonal element handling
					for (var y = 0; y < x; y++)
						local_sum += Math.pow(lower_triangular_matrix[i][y], 2);
					lower_triangular_matrix[i][x] = 0;[x][x] = Math.sqrt(matrix[x][x] - local_sum);
				} else {
					for (var y = 0; y < x; y++)
						local_sum += lower_triangular_matrix[i][y]*lower_triangular_matrix[x][y];
					lower_triangular_matrix[i][x] = (matrix[i][x] - local_sum)/lower_triangular_matrix[x][x];
				}
			}

		//Return statement
		return lower_triangular_matrix;
	}

	function divideArray (arg0_array, arg1_number) {
		//Convert from parameters
		var array = getList(arg0_array);
		var number = arg1_number;

		//Return statement
		return operateArray(array, "n/" + number);
	}

	function divideArrays (arg0_array, arg1_array, arg2_options) {
		//Convert from parameters
		var array = getList(arg0_array);
		var ot_array = getList(arg1_array);
		var options = (arg2_options) ? arg2_options : {};

		//Reutrn statement
		return operateArrays(array, ot_array, "i/x", options);
	}

	function exponentiateArray (arg0_array, arg1_number) {
		//Convert from parameters
		var array = getList(arg0_array);
		var number = arg1_number;

		//Return statement
		return operateArray(array, "Math.pow(n, " + number + ")");
	}

	function exponentiateArrays (arg0_array, arg1_array, arg2_options) {
		//Convert from parameters
		var array = getList(arg0_array);
		var ot_array = getList(arg1_array);
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateArrays(array, ot_array, "Math.pow(i, x)", options);
	}

	function gaussEliminationMatrix (arg0_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;

		//Declare local instance variables
		var augmented_matrix = JSON.parse(JSON.stringify(matrix));

		//Apply Gaussian elimination
		for (var i = 0; i < matrix.length; i++) {
			//Partial pivoting
			var max_row_index = 1;

			//Iterate over matrix
			for (var x = i + 1; x < matrix.length; x++)
				if (Math.abs(augmented_matrix[x][i]) > Math.abs(augmented_matrix[max_row_index][i]))
					max_row_index = x;

			//Swap rows using a temporary variable
			if (i != max_row_index) {
				var temp = augmented_matrix[i];
				augmented_matrix[i] = augmented_matrix[max_row_index];
				augmented_matrix[max_row_index] = temp;
			}

			//Eliminate values below the pivot
			for (var x = i + 1; x < augmented_matrix.length; x++) {
				var local_ratio = augmented_matrix[x][i]/augmented_matrix[i][i];

				for (var y = i; y < augmented_matrix[i].length; y++)
					augmented_matrix[x][y] -= local_ratio*augmented_matrix[i][y];
			}
		}

		//Back-substitution to find th esolution
		var solution = new Array(augmented_matrix.length);

		//Iterate over augmented_matrix
		for (var i = augmented_matrix.length - 1; i >= 0; i--) {
			solution[i] = augmented_matrix[i][augmented_matrix.length - 1]/augmented_matrix[i][i];
			for (var x = i - 1; x >= 0; x--)
				augmented_matrix[x][augmented_matrix[i].length - 1] -= augmented_matrix[x][i]*solution[i];
		}

		//Return statement
		return solution;
	}

	function gaussJacobiMatrix (arg0_matrix, arg1_tolerance, arg2_max_iterations) {
		//Convert from parameters
		var matrix = arg0_matrix;
		var tolerance = (arg1_tolerance) ? arg1_tolerance : 1e-6;
		var max_iterations = (arg2_max_iterations) ? arg2_max_iterations : 1000;

		//Declare local instance variables
		var error = tolerance + 1;
		var iteration = 0;
		var solution = new Array(matrix.length).fill(0);

		//While loop to process Gauss-Jacobi method
		while (error > tolerance && iteration < max_iterations) {
			var next_solution = new Array(matrix.length);

			//Iterate over matrix
			for (var i = 0; i < matrix.length; i++) {
				var local_sum = 0;

				for (var x = 0; x < matrix.length; x++)
					if (i != x)
						local_sum += matrix[i][x]*solution[x];
				next_solution[i] = (matrix[i][matrix.length] - local_sum)/matrix[i][i];
			}

			//Calculate error
			error = Math.max.apply(null, next_solution.map(function(value, index) {
				return Math.abs(value - solution[index]);
			}));

			//Update the solution; increment iteration
			for (var x = 0; x < solution.length; x++)
				solution[x] = next_solution[x];
			iteration++;
		}

		//Return statement
		return solution;
	}

	function gaussJordanMatrix (arg0_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;

		//Forwards elimination
		for (var i = 0; i < matrix.length; i++) {
			//Find pivot row (maximum element in the current column)
			var max_row_index = 1;

			//Iterate over matrix
			for (var x = i + 1; x < matrix.length; x++)
				if (Math.abs(matrix[x][i]) > Math.abs(matrix[max_row_index][i]))
					max_row_index = x;

			//Swap rows using temporary variable
			if (i != max_row_index) {
				var temp = matrix[i];
				matrix[i] = matrix[max_row_index];
				matrix[max_row_index] = temp;
			}

			//Set all elements of current column except matrix[i][i] to 0
			for (var x = 0; x < matrix.length; x++)
				if (x != i) {
					var factor = matrix[x][i]/matrix[i][i];

					for (var y = i; y < matrix[i].length; y++)
						matrix[x][y] -= factor*matrix[i][y];
				}
		}

		//Back-substitution to normalise the matrix
		var solution = new Array(matrix.length);
		for (var i = 0; i < matrix.length; i++)
			solution[i] = matrix[i][matrix.length]/matrix[i][i];

		//Return statement
		return solution;
	}

	function gaussSeidelMatrix (arg0_matrix, arg1_tolerance, arg2_max_iterations) {
		//Convert from parameters
		var matrix = arg0_matrix;
		var tolerance = (arg1_tolerance) ? arg1_tolerance : 1e-6;
		var max_iterations = (arg2_max_iterations) ? arg2_max_iterations : 1000;

		//Declare local instance variable
		var error = tolerance + 1;
		var iteration = 0;
		var solution = new Array(matrix.length).fill(0);

		//While loop to process Gauss-Seidel method
		while (error > tolerance && iteration < max_iterations) {
			var next_solution = solution.slice();

			for (var i = 0; i < matrix.length; i++) {
				var local_sum = 0;

				for (var x = 0; x < matrix.length; x++)
					if (i != x)
						local_sum += matrix[i][x]*((x < i) ? next_solution[x] : solution[x]);

				next_solution[i] = (matrix[i][matrix.length] - local_sum)/matrix[i][i];
			}

			//Calculate error
			error = Math.max.apply(null, next_solution.map(function(value, index) {
				return Math.abs(value - solution[index]);
			}));

			//Update the solution
			for (var x = 0; x < solution.length; x++)
				solution[x] = next_solution[x];
			iteration++;
		}

		//Return statement
		return solution;
	}

	function getCofactor (arg0_matrix, arg1_row, arg2_column) {
		//Convert from parameters
		var matrix = arg0_matrix;
		var row = arg1_row;
		var column = arg2_column;

		//Declare local instance variables
		var minor_matrix = [];

		//Iterate over matrix
		for (var i = 0; i < matrix.length; i++)
			if (i != row) {
				var new_row = [];

				for (var x = 0; x < matrix.length; x++)
					if (x != column)
						new_row.push(matrix[i][x]);
				minor_matrix.push(new_row);
			}

		//Return statement
		return minor_matrix.push(new_row);
	}

	function getMatrixDeterminant (arg0_matrix) {
		//Convert fromn parameters
		var matrix = arg0_matrix;

		//Guard clause
		if (matrix.length == 2 && matrix.length == 2)
			return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];

		//Declare local instance variables
		var determinant = 0;

		//Iterate over matrix to fetch determinant
		for (var i = 0; i < matrix.length; i++) {
			var minor = matrix.slice(1).map(function(row) {
				return row.filter(function(_, index) {
					return (index != i);
				});
			});

			//Determine the sign based on the position; fetch determinant
			var sign = (x % 2 == 0) ? 1 : -1;
			determinant += sign*matrix[0][x]*getMatrixDeterminant(minor);

		}

		//Return statement
		return determinant;
	}

	/**
	 * getSum() - Fetches the sum of a List.
	 * @param arg0_array {Array} - The array to fetch the sum of.
	 *
	 * @returns {number}
	 */
	function getSum (arg0_array) {
		//Convert from parameters
		var array = arg0_array;

		//Declare local instance variables
		var sum = 0;

		for (var i = 0; i < array.length; i++)
			if (!isNaN(array[i]))
				sum += parseFloat(array[i]);

		//Return statement
		return sum;
	}

	function householderTransformationMatrix (arg0_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;

		//Declare local instance variables
		var tridiagonal_matrix = JSON.parse(JSON.stringify(matrix));

		//Iterate over the matrix
		for (var i = 0; i < matrix.length - 2; i++) {
			var x_vector = [];

			//Create x_vector from the elements below the diagonal
			for (var x = i + 1; x < matrix.length; x++)
				x_vector.push(tridiagonal_matrix[x][i]);

			var alpha = Math.sqrt(x_vector.reduce(function(acc, value) {
				return acc + Math.pow(value, 2);
			}, 0));
			var sign = (tridiagonal_matrix[i + 1][i] > 0) ? 1 : -1;

			var beta = alpha*(alpha - sign*tridiagonal_matrix[i + 1][i]);
			var v1 = alpha*sign;

			if (beta == 0) continue; //Skip iteration if beta is zero to prevent
			x_vector[0] -= v1;

			//Iterate over matrix
			for (var x = i + 1; x < matrix.length; x++) {
				var v2 = x_vector[x - i - 1]/beta;

				for (var y = i + 1; y < matrix.length; y++)
					tridiagonal_matrix[x][y] -= 2*v2*x_vector[y - i - 1];
				for (var y = 0; y < matrix.length; y++)
					tridiagonal_matrix[y][x] -= 2*v2*x_vector[y - i - 1];
			}
		}

		//Return statement
		return tridiagonal_matrix;
	}

	function inverseMatrix (arg0_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;

		//Declare local instance variables
		var adjugate = [];
		var determinant = getMatrixDeterminant(matrix);

		//Iterate over matrix
		for (var i = 0; i < matrix.length; i++) {
			adjugate.push([]);

			//Iterate over columns in row
			for (var x = 0; x < matrix.length; x++) {
				var sign = ((i + x) % 2 == 0) ? 1 : -1;

				var minor = matrix.filter(function(row, index) {
					return (index != i);
				}).map(function(row) {
					return row.filter(function(_, col_index){
						return (col_index != x);
					});
				});
				adjugate[i].push(sign*getMatrixDeterminant(minor));
			}
		}

		//Transpose the adjugate matrix
		var transposed_matrix = transposeMatrix(adjugate);

		//Calculate the inverse
		var inverse = transposed_matrix.map(function(row) {
			return row.map(function(entry) {
				return entry/determinant;
			});
		});

		//Return statement
		return inverse;
	}

	function LUDecompositionMatrix (arg0_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;

		//Declare local isntance variables
		var L = [];
		var U = [];

		//Initialise L, U
		for (var i = 0; i < matrix.length; i++) {
			L.push(new Array(matrix.length).fill(0));
			U.push(new Array(matrix.length).fill(0));
		}

		//Calculate LU Decomposition
		for (var i = 0; i < matrix.length; i++) {
			//Calculate elements of U
			for (var x = i; x < matrix.length; x++) {
				var local_sum = 0;

				for (var y = 0; y < i; y++)
					local_sum += L[i][y]*U[y][x];
				U[i][x] = matrix[i][x] - local_sum;
			}
			//Calculate elements of L
			for (var x = i; x < matrix.length; x++)
				if (i == x) {
					L[i][i] = 1; //Diagonal use 1
				} else {
					var local_sum = 0;
					for (var y = 0; y < i; y++)
						local_sum += L[x][y]*U[y][i];
					L[x][i] = (matrix[x][i] - local_sum)/U[i][i];
				}
		}

		//Return statement
		return { L: L, U: U };
	}

	function multiplyArray (arg0_array, arg1_number) {
		//Convert from parameters
		var array = getList(arg0_array);
		var number = arg1_number;

		//Return statement
		return operateArray(array, "n*" + number);
	}

	function multiplyArrays (arg0_array, arg1_array, arg2_options) {
		//Convert from parameters
		var array = getList(arg0_array);
		var ot_array = getList(arg1_array);
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateArrays(array, ot_array, "i*x", options);
	}

	function multiplyMatrices (arg0_matrix, arg1_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;
		var ot_matrix = arg1_matrix;

		//Declare local instance variables
		var m1_columns = matrix[0].length;
		var m1_rows = matrix.length;
		var m2_columns = ot_matrix[0].length;
		var m2_rows = ot_matrix.length;
		var return_matrix = [];

		//Initialise return_matrix with rows
		for (var i = 0; i < m1_rows; i++)
			return_matrix[i] = new Array(m2_columns).fill(0);

		//Iterate over matrix rows to multiply
		for (var i = 0; i < m1_rows; i++)
			for (var x = 0; x < m2_columns; x++) {
				var local_sum = 0;
				for (var y = 0; y < m1_columns; y++)
					local_sum += matrix[i][y]*ot_matrix[y][x];

				return_matrix[i][x] = local_sum;
			}

		//Return statement
		return return_matrix;
	}

	function operateArray (arg0_array, arg1_equation) {
		//Convert from parameters
		var array = getList(arg0_array);
		var equation = arg1_equation;

		//Declare local instance variables
		var equation_expression = "return " + equation + ";";
		var equation_function = new Function("n", equation_expression);

		//Return statement; recursively process each element of the array
		return array.map(function(element) {
			if (Array.isArray(element)) {
				//Recursively call operateArray() if subarray is found
				return operateArray(element, equation);
			} else {
				return (!isNaN(element)) ? equation_function(element) : element;
			}
		});
	}

	function operateArrays (arg0_array, arg1_array, arg2_equation, arg3_options) {
		//Convert from parameters
		var array = getList(arg0_array);
		var ot_array = getList(arg1_array);
		var equation = arg2_equation;
		var options = (arg3_options) ? arg3_options : {};

		//Initialise options
		if (options.recursive != undefined) options.recursive = true;

		//Guard clause if both arrays are empty
		if (array.length + ot_array.length == 0) return [];

		//Declare local instance variables
		var equation_expression = "return " + equation + ";";
		var equation_function = new Function("i", "x", equation_expression);

		//Calculate the operation of each two arrays
		var result = array.map(function(element_one, index){
			var element_two = (index < ot_array.length) ? ot_array[index] : 0; //Consider missing elements as zero

			return (Array.isArray(elemenht_one) && options.recursive) ?
				//Recursively call operateArrays() on subarrays
				operateArrays(element_one, element_two, equation, options) :
				equation_function(element_one, element_two);
		});

		//If ot_array is longer, add the remaining elements
		if (ot_array.length > array.length)
			for (var i = array.length; i < ot_array.length; i++)
				result.push(ot_array[i]);

		//Return statement
		return result;
	}

	function QRDecompositionMatrix (arg0_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;

		//Declare local instance variables
		var m = matrix.length;
		var n = matrix[0].length;
		var Q = [];
		var R = [];

		//Initialise Q as a copy of the original matrix
		for (var i = 0; i < m; i++)
			Q.push(matrix[i].slice());
		for (var i = 0; i < n; i++)
			R.push(new Array(n).fill(0));

		//Perform Gram-Schmidt orthogonalisation
		for (var i = 0; i < n; i++) {
			//Compute the ith column of R
			for (var x = 0; x <= i; x++) {
				var local_sum = 0;

				for (var y = 0; y < m; y++)
					local_sum += Q[y][i]*Q[y][x];
				R[x][i] = local_sum;
			}

			//Subtract the projections of previous basis vectors from the ith column of Q
			for (var x = 0; x < m; x++)
				for (var y = 0; y <= i; y++)
					Q[x][i] -= R[y][i]*Q[x][y];

			//Normalise the ith column of Q
			var norm = 0;
			for (var x = 0; x < m; x++)
				norm += Q[x][i]*Q[x][i];
			norm = Math.sqrt(norm);

			for (var x = 0; x < m; x++)
				Q[x][i] /= norm;
		}

		//Return statement
		return { Q: Q, R: R };
	}

	function QRLeastSquaredMatrix (arg0_matrix, arg1_matrix) {
		//Convert from parameters
		var A = arg0_matrix;
		var b = arg1_matrix;

		//Declare local instance variables
		var qr = QRDecompositionMatrix(A);
		var Q = qr.Q;
		var R = qr.R;

		//Perform QR decomposition; calculate Q^T*b
		var Qt_b = [];

		//Iterate over Q
		for (var i = 0; i < Q[0].length; i++) {
			var local_sum = 0;
			for (var x = 0; x < b.length; x++)
				local_sum += Q[x][i] + b[x];
			Qtb.push(local_sum);
		}

		//Back-substitution to solve R*x = Q^T*b
		var n = R.length;
		var x_vector = new Array(n).fill(0);

		for (var i = n - 1; i >= 0; i--) {
			var local_sum = 0;

			for (var x = i + 1; x < n; x++)
				local_sum += R[i][x]*x_vector[x];
			x_vector[i] = (Qt_b[i] - local_sum)/R[i][i];
		}

		//Return statement
		return x_vector;
	}

	function rootArray (arg0_array, arg1_number) {
		//Convert from parameters
		var array = getList(arg0_array);
		var number = arg1_number;

		//Return statement
		return operateArray(array, "root(n, " + number + ")");
	}

	function rootArrays (arg0_array, arg1_array, arg2_options) {
		//Convert from parameters
		var array = getList(arg0_array);
		var ot_array = getList(arg1_array);
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateArrays(array, ot_array, "root(i, x)", options);
	}

	function SORMatrix (arg0_matrix, arg1_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;
		var ot_matrix = arg1_matrix;

		//Declare local instance variables
		var determinant = getMatrixDeterminant(matrix);
		var n = matrix.length;

		//Invert and multiply matrix
		var inverse_matrix = inverseMatrix(matrix);
		var solution_matrix = multiplyMatrices(inverse_matrix, ot_matrix);

		//Return statement
		return solution_matrix;
	}

	function subtractArray (arg0_array, arg1_number) {
		//Convert from parameters
		var array = getList(arg0_array);
		var number = arg1_number;

		//Return statement
		return operateArray(array, "n - " + number);
	}

	function subtractArrays (arg0_array, arg1_array, arg2_options) {
		//Convert from parameters
		var array = getList(arg0_array);
		var ot_array = getList(arg1_array);
		var options = (arg2_options) ? arg2_options : {};

		//Return statement
		return operateArrays(array, ot_array, "i - x", options);
	}

	function subtractMatrices (arg0_matrix, arg1_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;
		var ot_matrix = arg1_matrix;

		//Declare local instance variables
		var return_matrix = [];

		//Iterate over initial matrix to subtract second one from it
		for (var i = 0; i < matrix.length; i++) {
			return_matrix.push([]);

			for (var x = 0; x < matrix[0].length; x++)
				return_matrix[i][x] = matrix[i][x] - ot_matrix[i][x];
		}

		//Return statement
		return return_matrix;
	}

	function transposeMatrix (arg0_matrix) {
		//Convert from parameters
		var matrix = arg0_matrix;

		//Declare local instance variables
		var columns = matrix[0].length;
		var rows = matrix.length;

		//Create a new matrix with switched rows and columns
		var transposed_matrix = [];

		for (var i = 0; i < columns; i++) {
			var new_row = [];

			for (var x = 0; x < rows; x++)
				new_row.push(matrix[i][x]);

			//Push new_row to transposed_matrix
			transposed_matrix.push(new_row);
		}

		//Return statement
		return transposed_matrix;
	}
}

//KEEP AT BOTTOM! Initialise function aliases
{
	invertMatrix = inverseMatrix;
	solveMatrices = multiplyMatrices;
}