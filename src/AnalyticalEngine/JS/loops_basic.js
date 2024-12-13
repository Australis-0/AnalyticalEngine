//Imports
var Executors = java.util.concurrent.Executors;
var Thread = java.lang.Thread;
var TimeUnit = java.util.concurrent.TimeUnit;

//Interval functions
{
    /**
     * clearInterval() - Clears a currently set interval.
     * @param {thread} arg0_thread - The thread for which to clear the given interval.
     */
    function clearInterval (arg0_thread) {
        //Convert from parameters
        var thread = arg0_thread;

        //Cancel thread
        if (thread && thread.isAlive()) thread.interrupt();
    }

    /**
     * setInterval() - Sets an interval.
     * @param {Function} arg0_function - The function which to call per ms interval.
     * @param {number} arg1_delay - The delay in milliseconds. Every n_ms, this function is called.
     *
     * @returns {thread}
     */
    function setInterval (arg0_function, arg1_delay) {
        //Convert from parameters
        var local_function = arg0_function;
        var delay = arg1_delay;

        //Declare local instance variables
        var interval_thread = new Thread(function(){
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    local_function();
                    Thread.sleep(delay);
                }
            } catch (e) {
                console.error(e);
            }
        });
        interval_thread.start();

        //Return statement
        return interval_thread;
    }
}

//Timeout functions
{
    /**
     * clearTimeout() - Clears a currently set timeout.
     * @param {thread} arg0_thread - The thread for which to clear the given timeout.
     */
    function clearTimeout (arg0_thread) {
        //Convert from parameters
        var thread = arg0_thread;

        //Cancel thread
        if (thread && thread.isAlive()) thread.interrupt();
    }

    /**
     * setTimeout() - Sets a timeout/wait command.
     * @param {Function} arg0_function - The function which to call per ms interval.
     * @param {number} arg1_delay - The delay in milliseconds. After n_ms, this function is called.
     *
     * @returns {thread}
     */
    function setTimeout (arg0_function, arg1_delay) {
        //Convert from parameters
        var local_function = arg0_function;
        var delay = arg1_delay;

        //Declare local instance variables
        var timeout_thread = new Thread(function(){
            try {
                Thread.sleep(delay);
                local_function();
            } catch (e) {
                try {
                    console.error(e);
                } catch (e) {
                    console.error("An error could not be logged to console due to ArrayIndexOutOfBoundsException!");
                }
            }
        });
        timeout_thread.start();

        //Return statement
        return timeout_thread;
    }
}