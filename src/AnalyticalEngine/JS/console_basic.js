var console = {
    /**
     * console.error() - Logs an error.
     * @param {String} arg0_string - The string to log.
     */
    error: function (arg0_string) {
        //Convert from parameters
        var string = arg0_string;

        print("[AnalyticalEngine] [ERROR] " + string);
    },

    /**
     * console.log() - Logs information.
     * @param {String} arg0_string - The string to log.
     */
    log: function (arg0_string) {
        //Convert from parameters
        var string = arg0_string;

        print("[AnalyticalEngine] " + string);
    },

    /**
     * console.warn() - Logs a warning.
     * @param {String} arg0_string - The string to log.
     */
    warn: function (arg0_string) {
        //Convert from parameters
        var string = arg0_string;
        
        print("[AnalyticalEngine] [WARN] " + string);
    }
};