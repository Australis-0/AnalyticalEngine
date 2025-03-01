//Declare global music variables since this is on-startup
var main_theme_id = 0;
var main_theme_positions = [];
var music_loaded = false;

//1. Play Main_Theme during loading
playMusic("Main_Theme.ogg");

//2. Keep track of current loading music timestamp so Main_Theme can be resumed upon reaching the Main Menu
var main_theme_loading_logic_loop = setInterval(function(){
	try {
		if (Game.soundsManager.lTitles.size() != 0 && !music_loaded) {
			for (var i = 0; i < Game.soundsManager.lTitles.size(); i++) {
				var local_title = Game.soundsManager.lTitles.get(i);

				console.log("[11:59] [Music] Music #" + i + ": " + local_title);
				if (local_title.toLowerCase().trim().includes("main_theme"))
					main_theme_id = i;
			}

			music_loaded = true;
		}

		if (music_loaded)
			setTimeout(function() {
				Game.soundsManager.iCurrentMusicID = main_theme_id;
			}, 50);
		if (Game.soundsManager.getCurrentMusicTittle().toLowerCase().trim().includes("main theme"))
			main_theme_positions.push(Game.soundsManager.currentMusic.getPosition());
	} catch (e) {}
}, 100);

//3. Post-load finish - Resume Main_Theme
onGameStartup(function (e) {
	//Clear main_theme_loading_logic_loop
	clearInterval(main_theme_loading_logic_loop);

	//Resume Main_Theme
	playMusic("Main_Theme.ogg");
	setTimeout(function(){
		var max_position = 0;
		for (var i = 0; i < main_theme_positions.length; i++)
			max_position = Math.max(main_theme_positions[i], max_position);

		Game.soundsManager.currentMusic.setPosition(max_position);
		main_theme_positions = [];
	}, 0);
});