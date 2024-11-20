//Initialise functions
{
	/**
	 * componentToHex() - Fetches the hex of a single component.
	 * @param {number} [arg0_c] - The individual r/g/b component to pass to the function.
	 *
	 * @returns {String}
	 */
	function componentToHex (arg0_c) {
		//Convert from parameters
		var hex = arg0_c.toString(16);

		//Return statement
		return (hex.length == 1) ? "0" + hex : hex;
	}

	/**
	 * deltaE() - Calculates the deltaE between two RGB values.
	 * @param {Array<number, number, number>} arg0_rgb_a - The 1st RGB code to pass.
	 * @param {Array<number, number, number>} arg1_rgb_b - The 2nd RGB code to pass.
	 *
	 * @returns {number}
	 */
	function deltaE (arg0_rgb_a, arg1_rgb_b) {
		//Convert from parameters
		var lab_a = RGB2Lab(arg0_rgb_a);
		var lab_b = RGB2Lab(arg0_rgb_b);

		//Declare local instance variables
		var delta_l = lab_a[0] - lab_b[0];
		var delta_a = lab_a[1] - lab_b[1];
		var delta_b = lab_a[2] - lab_b[2];

		var c1 = Math.sqrt(lab_a[1]*lab_a[1] + lab_a[2]*lab_a[2]);
		var c2 = Math.sqrt(lab_b[1]*lab_b[1] + lab_b[2]*lab_b[2]);
		var delta_c = c1 - c2;
		var delta_h = delta_a*delta_a + delta_b*delta_b - delta_c*delta_c;
		var sc = 1.0 + 0.045*c1;
		var sh = 1.0 + 0.015*c1;

		var delta_lklsl = delta_l/1.0;
		var delta_ckcsc = delta_c/sc;
		var delta_hkhsh = delta_h/sh;
		var i = delta_lklsl*delta_lklsl + delta_ckcsc*delta_ckcsc + delta_hkhsh*delta_hkhsh;

		//Return statement
		return (i < 0) ? 0 : Math.sqrt(i);
	}

	/**
	 * generateRandomColour() - Generates a random RGB colour.
	 *
	 * @returns {Array<number, number, number>}
	 */
	function generateRandomColour (arg0_rgba) {
		//Convert from parameters
		var rgba = arg0_rgba;

		//Return statement
		return (arg0_rgba) ?
			[randomNumber(0, 255), randomNumber(0, 255), randomNumber(0, 255)] :
			[randomNumber(0, 255), randomNumber(0, 255), randomNumber(0, 255), randomNumber(0, 255)];
	}

	/**
	 * getColourDistance() - Fetches the absolute colour distance between two colours.
	 * @param {Array<number, number, number>} arg0_rgb - The 1st RGB code to pass.
	 * @param {Array<number, number, number>} arg1_rgb - The 2nd RGB code to pass.
	 *
	 * @returns {number}
	 */
	function getColourDistance (arg0_rgb, arg1_rgb) {
		//Convert from parameters
		var colour_one = arg0_rgb;
		var colour_two = arg1_rgb;

		//Declare local instance variables
		var distance = Math.sqrt(
			Math.pow((colour_one[0] - colour_two[0]), 2) +
			Math.pow((colour_one[1] - colour_two[1]), 2) +
			Math.pow((colour_one[2] - colour_two[2]), 2)
		);

		//Return statement
		return distance;
	}

	/**
	 * hexToRGB() - Converts a hex to RGB.
	 * @param {String} arg0_hex - The hex code to pass to the function.
	 *
	 * @returns {Array<number, number, number>}
	 */
	function hexToRGB (arg0_hex) {
		//Convert from parameters
		var hex = arg0_hex;

		//Declare local instance variables
		return result ? [
			parseInt(result[1], 16),
			parseInt(result[2], 16),
			parseInt(result[3], 16)
		] : undefined;
	}

	/**
	 * RGBToHex() - Converts an RGB value to hex.
	 * @param {number} arg0_r - The r value.
	 * @param {number} arg0_g - The g value.
	 * @param {number} arg0_b - The b value.
	 *
	 * @returns {String}
	 */
	function RGBToHex (arg0_r, arg1_g, arg2_b) {
		//Convert from parameters
		var r = arg0_r;
		var g = arg1_g;
		var b = arg2_b;

		if (Array.isArray(r)) { //This is an RGB array instead
			b = r[2];
			g = r[1];
			r = r[0];
		}

		//Return statement
		return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
	}

	/**
	 * RGB2Lab() - Converts an RGB value to lab distance.
	 * @param {Array<number, number, number>} - The RGB value to pass.
	 *
	 * @returns {Array<number, number, number>}
	 */
	function RGB2Lab (arg0_rgb) {
		//Convert from parameters
		var rgb = arg0_rgb;

		//Declare local instance variables
		var r = rgb[0] / 255, g = rgb[1] / 255, b = rgb[2] / 255, x, y, z;
		var r = (r > 0.04045) ? Math.pow((r + 0.055) / 1.055, 2.4) : r / 12.92;
		var g = (g > 0.04045) ? Math.pow((g + 0.055) / 1.055, 2.4) : g / 12.92;
		var b = (b > 0.04045) ? Math.pow((b + 0.055) / 1.055, 2.4) : b / 12.92;
		var x = (r * 0.4124 + g * 0.3576 + b * 0.1805) / 0.95047;
		var y = (r * 0.2126 + g * 0.7152 + b * 0.0722) / 1.00000;
		var z = (r * 0.0193 + g * 0.1192 + b * 0.9505) / 1.08883;

		x = (x > 0.008856) ? Math.pow(x, 1/3) : (7.787 * x) + 16/116;
		y = (y > 0.008856) ? Math.pow(y, 1/3) : (7.787 * y) + 16/116;
		z = (z > 0.008856) ? Math.pow(z, 1/3) : (7.787 * z) + 16/116;

		//Return statement
		return [(116 * y) - 16, 500 * (x - y), 200 * (y - z)]
	}
}