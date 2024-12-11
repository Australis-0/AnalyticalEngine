var console = {
    convertArrayToString: function (arg0_array) {
        //Convert from parameters
        var array = getList(arg0_array);

        //Declare local instance variables
        var processed_string = "";

        //Iterate over array; handle sub-arrays and objects
        for (var i = 0; i < array.length; i++) {
            if (Array.isArray(array[i])) {
                processed_string += console.convertArrayToString(array[i]);
            } else if (typeof array[i] == "object") {
                processed_string += console.convertObjectToString(array[i], { inline: true });
            } else if (typeof array[i] == "string") {
                processed_string += '"' + array[i].toString() + '"';
            } else if (array[i] == undefined) {
                processed_string += "undefined";
            } else {
                processed_string += array[i].toString();
            }

            //Add comma separator
            if (i != array.length - 1)
                processed_string += ", ";
        }

        //Return statement
        return "[" + processed_string + "]";
    },

    convertObjectToString: function (arg0_object, arg1_options) {
        //Convert from parameters
        var object = arg0_object;
        var options = (arg1_options) ? arg1_options : {};

        //Initialise options
        if (!options.nesting) options.nesting = 1;

        //Declare local instance variables
        var all_object_keys = Object.keys(object);
        var processed_string = [];

        processed_string.push("{"); //Does not require nesting handling since this is handled inline
        //Iterate over all_object_keys and log each one according to type.
        for (var i = 0; i < all_object_keys.length; i++) {
            var local_padding = parseNonMarkdownNesting(options.nesting);
            var local_prefix = ((!options.inline) ? local_padding : "") + all_object_keys[i] + ": ";
            var local_suffix = (i != all_object_keys.length - 1) ? "," : "";
            var local_value = object[all_object_keys[i]];

            if (Array.isArray(local_value)) {
                processed_string.push(local_prefix + console.convertArrayToString(local_value) + local_suffix);
            } else if (typeof local_value == "object") {
                var new_options = JSON.parse(JSON.stringify(options));
                new_options.nesting++;

                processed_string.push(local_prefix + console.convertObjectToString(local_value, new_options) + local_suffix);
            } else if (typeof local_value == "string") {
                processed_string.push(local_prefix + '"' + local_value.toString() + '"' + local_suffix);
            } else {
                processed_string.push(local_prefix + local_value.toString() + local_suffix);
            }
        }
        processed_string.push(parseNonMarkdownNesting(options.nesting - 1) + "}");

        //Return statement
        return (!options.inline) ? processed_string.join("\n") : processed_string.join(" ");
    },

    /**
     * console.error() - Logs an error.
     * @param {String} arg0_string - The string to log.
     */
    error: function () {
        //Declare local instance variables
        var arguments = Array.prototype.slice.call(arguments);
        var e = new Error();
        var stack = e.stack;

        print("[AnalyticalEngine] [ERROR] " + console.print.apply(null, arguments));
        print("[AnalyticalEngine] Stack trace: " + stack);

        /*var caller = stack.split("\n")[2];  // Assuming the caller is the third line
        print("Called from: " + caller);*/
    },

    /**
     * console.log() - Logs information.
     * @param {String} arg0_string - The string to log.
     */
    log: function () {
        //Declare local instance variables
        var arguments = Array.prototype.slice.call(arguments);

        print("[AnalyticalEngine] " + console.print.apply(null, arguments));
    },

    /**
     * console.print() - Internal helper function for printing any number of arguments.
     * @param {...*} arguments - The dynamic list of arguments to print.
     */
    print: function () {
        //Declare local instance variable
        var arguments = Array.prototype.slice.call(arguments);
        var processed_string = "";

        //Has any number of arguments
        for (var i = 0; i < arguments.length; i++)
            if (Array.isArray(arguments[i])) {
                processed_string += console.convertArrayToString(arguments[i]);
            } else if (typeof arguments[i] == "object") {
                processed_string += console.convertObjectToString(arguments[i]);
            } else {
                processed_string += arguments[i].toString();
            }

        //Return string
        return processed_string;
    },

    /**
     * console.warn() - Logs a warning.
     * @param {String} arg0_string - The string to log.
     */
    warn: function () {
        //Declare local instance variables
        var arguments = Array.prototype.slice.call(arguments);
        
        print("[AnalyticalEngine] [WARN] " + console.print.apply(null, arguments));
    }
};