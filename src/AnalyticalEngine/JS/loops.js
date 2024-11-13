//Imports
var Executors = java.util.concurrent.Executors;
var TimeUnit = java.util.concurrent.TimeUnit;

//Interval functions
{
    function clearInterval (arg0_scheduler) {
        //Convert from parameters
        var scheduler = arg0_scheduler;

        //Shutdown scheduler
        scheduler.shutdown();
    }

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
    function clearTimeout (arg0_future) {
        //Convert from parameters
        var future = arg0_future;

        //Cancel future
        future.cancel(false);
    }

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