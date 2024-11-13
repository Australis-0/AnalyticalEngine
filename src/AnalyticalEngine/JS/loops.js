//Imports
var Executors = java.util.concurrent.Executors;
var TimeUnit = java.util.concurrent.TimeUnit;

//Interval functions
{
    /**
     * clearInterval() - Clears a currently set interval.
     * @param {scheduler} arg0_scheduler - The scheduler for which to clear the given interval.
     */
    function clearInterval (arg0_scheduler) {
        //Convert from parameters
        var scheduler = arg0_scheduler;

        //Shutdown scheduler
        scheduler.shutdown();
    }

    /**
     * setInterval() - Sets an interval.
     * @param {Function} arg0_function - The function which to call per ms interval.
     * @param {number} arg1_delay - The delay in milliseconds. Every n_ms, this function is called.
     *
     * @returns {scheduler}
     */
    function setInterval (arg0_function, arg1_delay) {
        //Convert from parameters
        var local_function = arg0_function;
        var delay = arg1_delay;

        //Declare local instance variables
        var scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(local_function, 0, delay, TimeUnit.MILLISECONDS);

        //Return statement
        return scheduler;
    }
}

//Timeout functions
{
    /**
     * clearTimeout() - Clears a currently set timeout.
     * @param {scheduler} arg0_scheduler - The scheduler for which to clear the given timeout.
     */
    function clearTimeout (arg0_future) {
        //Convert from parameters
        var future = arg0_future;

        //Cancel future
        future.cancel(false);
    }

    /**
     * setTimeout() - Sets a timeout/wait command.
     * @param {Function} arg0_function - The function which to call per ms interval.
     * @param {number} arg1_delay - The delay in milliseconds. After n_ms, this function is called.
     *
     * @returns {scheduler}
     */
    function setTimeout (arg0_function, arg1_delay) {
        //Convert from parameters
        var local_function = arg0_function;
        var delay = arg1_delay;

        //Declare local instance variables
        var scheduler = Executors.newScheduledThreadPool(1);

        var future = scheduler.schedule(function(){
            local_function();
            scheduler.shutdown(); //Automatically shut scheduler post-execution
        });

        //Return statement
        return future;
    }
}