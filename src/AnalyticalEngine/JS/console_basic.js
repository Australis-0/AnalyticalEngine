var console = {
    error: function (arg0_string) {
        //Convert from parameters
        var string = arg0_string;

        print("[AnalyticalEngine] [ERROR] " + string);
    },
    log: function (arg0_string) {
        //Convert from parameters
        var string = arg0_string;

        print("[AnalyticalEngine] " + string);
    },
    warn: function (arg0_string) {
        //Convert from parameters
        var string = arg0_string;
        
        print("[AnalyticalEngine] [WARN] " + string);
    }
};