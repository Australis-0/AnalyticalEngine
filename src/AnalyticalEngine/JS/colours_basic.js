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
	 * convertIntToRGB() - Converts int back to [r, g, b].
	 * @param {number} arg0_colour - The int to convert back to RGB/A.
	 *
	 * @returns {Array<number, number, number, number>}
	 */
	function convertIntToRGB (arg0_colour) {
		//Convert from parameters
		var colour = arg0_colour;

		//Return statement
		return convertIntToRGBA(colour);
	}

	/**
	 * convertIntToRGBA() - Converts int back to [r, g, b, a].
	 * @param {number} arg0_colour - The int to convert back to RGBA.
	 *
	 * @returns {Array<number, number, number, number>}
	 */
	function convertIntToRGBA (arg0_colour) {
		//Convert from parameters
		var colour = arg0_colour;

		//Return statement
		return [
			(colour >> 16) & 0xFF, //red
			(colour >> 8) & 0xFF,  //green
			colour & 0xFF,         //blue
			(colour >> 24) & 0xFF  //alpha
		];
	}

	/**
	 * convertRGB() - Converts [r, g, b] to float fraction format.
	 * @param {Array<number, number, number>} arg0_rgb - The RGB array to convert.
	 *
	 * @returns {Array<number, number, number, 1.0>}
	 */
	function convertRGB (arg0_rgb) {
		//Convert from parameters
		var rgb = getList(arg0_rgb);

		//Return statement
		return [
			new Float(rgb[0]/255),
			new Float(rgb[1]/255),
			new Float(rgb[2]/255),
			new Float(1.0)
		];
	}

	/**
	 * convertRGBA() - Converts [r, g, b, a] to float fraction format.
	 * @param {Array<number, number, number, number>} arg0_rgba - The RGBA array to convert.
	 *
	 * @returns {Array<number, number, number, number>}
	 */
	function convertRGBA (arg0_rgba) {
		//Convert from parameters
		var rgba = getList(arg0_rgba);

		//Return statement
		return [
			new Float(rgba[0]/255),
			new Float(rgba[1]/255),
			new Float(rgba[2]/255),
			new Float(rgba[3])
		];
	}

	/**
	 * convertRGBToInt() - Converts [r, g, b] to int fraction format.
	 * @param {Array<number, number, number>} arg0_rgb - The RGB array to convert.
	 *
	 * @returns {number}
	 */
	function convertRGBToInt (arg0_rgb) {
		//Convert from parameters
		var rgb = arg0_rgb;

		//Return statement
		return convertRGBAToInt([rgb[0], rgb[1], rgb[2], 255]);
	}

	/**
	 * convertRGBAToInt() - Converts [r, g, b, a] to int fraction format.
	 * @param {Array<number, number, number, number>} arg0_rgba - The RGBA array to convert.
	 *
	 * @returns {number}
	 */
	function convertRGBAToInt (arg0_rgba) {
		//Convert from parameters
		var rgba = arg0_rgba;

		//Return statement
		return ((rgba[3] & 0xFF) << 24) | //alpha
			((rgba[0] & 0xFF) << 16) | //red
			((rgba[1] & 0xFF) << 8)  | //green
			(rgba[2] & 0xFF); //blue
	}

	function decodeRGBAsNumber (arg0_rgb) {
		//Convert from parameters
		var rgb = arg0_rgb;

		//Declare local instance variables
		var r = rgb[0];
		var g = rgb[1];
		var b = rgb[2];

		//Return statement
		return (r << 16) | (g << 8) | b;
	}

	function decodeRGBAAsNumber (arg0_rgba) {
		//Convert from parameters
		var rgba = arg0_rgba;

		//Declare local instance variables
		var r = rgba[0];
		var g = rgba[1];
		var b = rgba[2];
		var a = rgba[3];

		//Return statement (rebuild 32-bit integer)
		return ((r << 24) | (g << 16) | (b << 8) | a) >>> 0;
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

	function encodeNumberAsRGB (arg0_number) {
		//Convert from parameters
		var number = returnSafeNumber(arg0_number);

		//Declare local instance variables
		var r = (number >> 16) & 0xFF;
		var g = (number >> 8) & 0xFF;
		var b = number & 0xFF;
		var a = 255;

		//Return statement
		return [r, g, b, a];
	}

	function encodeNumberAsRGBA (arg0_number) {
		//Convert from parameters
		var number = returnSafeNumber(Math.round(arg0_number));

		//Declare local instance variables
		var r = (number >> 24) & 0xFF; //Extract highest 8 bits
		var g = (number >> 16) & 0xFF; //Extract next 8 bits
		var b = (number >> 8) & 0xFF;  //Extract next 8 bits
		var a = number & 0xFF;         //Extract lowest 8 bits

		//Return statement
		return [r, g, b, a];
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