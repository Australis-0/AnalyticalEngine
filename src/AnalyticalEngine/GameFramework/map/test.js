/*
setInterval(function(){
    console.log("This is to confirm Nashorn operation.");
}, 1000);
setTimeout(function(){
    console.log("It's been 3 seconds!");
}, 3000);
setTimeout(function(){
    console.log("It's been 5 seconds!");
}, 5000);
*/
var test = eval("(function(){ return 5 + 3; })();");
console.log(test);