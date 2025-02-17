<img src = "https://i.postimg.cc/Xvh25St6/analytical-engine-icon-monochrome.png" width = 128 height = 128 align = "left">
<div id = "toc">
  <ul>
    <summary>
      <h1>AnalyticalEngine.</h1><br>AOC3, Open Sourced.
    </summary>
  </ul>
</div>

## Abstract.
[![Join our community!](https://img.shields.io/discord/548994743925997570?label=Discord&style=for-the-badge)](https://discord.gg/89kQY2KFQz) ![](https://img.shields.io/github/languages/code-size/Australis-0/AnalyticalEngine?style=for-the-badge) ![](https://img.shields.io/github/downloads/Australis-0/AnalyticalEngine/total?style=for-the-badge)

> [!WARNING]
> AnalyticalEngine is currently in **beta.**
> This means that certain features such as custom mapmodes and Event Effects are supported, but only partially implemented - with MP support still in testing.

AnalyticalEngine (Project Orion) is a development effort to open-source the core binary code of AOC3 and improve moddability by allowing for custom <a href = "https://github.com/openjdk/nashorn">NashornJS</a> scripts in mods, providing an API for Event Conditions, Effects, and Game Scopes, modded Multiplayer (MP) support, as well as custom mapmodes and custom UIs. 

Completed builds are available as the current `src/AOC3-Source.jar`. This may be downloaded dropped into any basegame AOC3 directory to gain access to the current stable version of AnalyticalEngine. You may either run it normally as a Java binary or via the command line in the extracted folder if you wish to access AnalyticalEngine's console: `java -jar src/AOC3-Source.jar`.

**Developers.**
- Aust Kätzchen (Confoederatio)
- Vis Tacitus (Confoederatio)

## Contacts.
## Documentation.
## Installing a Custom Build.

**AnalyticalEngine, Dev Build.**
> [!NOTE]
> These instructions are only for users that want to contribute to the project by decompiling and recompiling AnalyticalEngine themselves to work on hot code. 

1. Download and install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=windows).
2. Launch **IntelliJ IDEA.**
3. Click `Open` > Navigate to the folder containing `AOC3-Source.iml`.
4. Select `OK` or `Open`.
5. IntelliJ should open the project structure automatically.
6. Within `Project Structure` > `Modules`, make sure the AOC3 Source is set up such that the `src/` folder has the scope 'compile' and Export is checked. The order should be as follows:
    - 23 (Oracle OpenJDK 23)
    - \<Module source\>
    - src/ scope
8. If no build chain is present, open `File` > `Project Structure` > `Artifacts` > `+` > `JAR` > `From Modules With Dependencies` > `Main Class` > DesktopLauncher > `OK`.
9. To build the .JAR file, go to `Build` > `Build Artifacts` > `AOC3-Source.jar` > `Build`. The .JAR should now be in `out/artifacts/AOC3_Source_jar`. Cut and paste this into the `src/` folder. Remember to delete `src/AOC3-Source.jar` each time you build.

**Vanilla AOC3, Dev Build.**

> [!CAUTION]
> These instructions apply to direct decompilation/recompilation of the base game only, and not to AnalyticalEngine itself. [Similar instructions](https://vk.com/@bloodyeurope2-gpk-1-zapusk-ishodnogo-koda) are available for AOC2.

<details>
  <summary><b>Click to expand.</b></summary>

1. Launch **Steam** > Age of History 3 > Properties > Installed Data > Browse ...
2. Rename either `game.jar` (if running the MP version) to `game.zip` or `aoh3.exe` to `aoh.zip` (if running the SP version).
3. Select the file > `Extract All`.
4. Download and install [Oracle OpenJDK 23](https://www.oracle.com/uk/java/technologies/downloads/).
5. Download and install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=windows).
6. Launch **IntelliJ IDEA.**
7. Create a new Maven project in IntelliJ with SDK 23.
8. Create a new folder within the base directory named `src/`, and `Mark Directory as Generated Sources`.
9. Move all game folders and code directly into `src/`. This means that `aoc.kingdoms.lukasz` and game config files such as `map/` and `game/` should be within the base `src/` folder.
10. Create a new folder within the base directory named `target/` and `Mark Directory as Excluded.` This is where exported classes will be cached for overwriting within the mod.
11. Within `Project Structure` > `Modules`, add AOC3 source files such that the `src/` folder has the Scope 'compile' and Export is checked. The order should be as follows:
    - 23 (Oracle OpenJDK 23)
    - \<Module source\>
    - src/ scope
12. Navigate to `src/aoc.kingdoms.lukasz/jakowski/desktop/DesktopLauncher`. This is where the `DesktopLauncher.class` is located, and where you should run the application from. Setup `Run Configuration` as specified:

> **Run Configuration (`Run` > `Edit Configurations` > 'DesktopLauncher').**<br>
> Name: DesktopLauncher<br>
> Run on: Local machine<br>
> Build and run: java 8 for AOC3 module. (`Select alternative JRE` > `src/jre`)<br>
> aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher<br>
> Working directory: %base_folder%\src

13. Create a new `autorun.bat` file in the base directory. The script to compile and run the executable should be:
    
    ```bat
    @echo off
    cd src
    set "base_directory=%~dp0"
    java "-Djava.library.path=%base_directory%\src" -classpath "%base_directory%\target\classes;%base_directory%\src" aoc.kingdoms.lukasz.jakowski.desktop.DesktopLauncher
    ```
15. By default, AOC3 utilises Maven. In `pom.xml` in the base folder (not `src/`), copy/paste the following XML:
    
    ```xml
    <project xmlns="http://maven.apache.org/POM/4.0.0"
     	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.aoc3</groupId>
	<artifactId>AOC3-Source</artifactId>
	<version>1.0-SNAPSHOT</version>

	<properties>
    	<maven.compiler.source>1.8</maven.compiler.source>
    	<maven.compiler.target>1.8</maven.compiler.target>
	</properties>

	<dependencies>
    	<dependency>
        	<groupId>com.badlogicgames.gdx</groupId>
        	<artifactId>gdx</artifactId>
        	<version>1.11.0</version> <!-- Use the latest version available -->
    	</dependency>
    	<dependency>
        	<groupId>com.badlogicgames.gdx</groupId>
        	<artifactId>gdx-backend-lwjgl3</artifactId>
        	<version>1.11.0</version> <!-- Use the latest version available -->
    	</dependency>
	</dependencies>

	<build>
    	<sourceDirectory>src</sourceDirectory>
    	<plugins>
        	<plugin>
            	<groupId>org.apache.maven.plugins</groupId>
            	<artifactId>maven-compiler-plugin</artifactId>
            	<version>3.8.1</version>
            	<configuration>
                	<source>1.8</source>
                	<target>1.8</target>
            	</configuration>
        	</plugin>
    	</plugins>
	</build>
    </project>
    ```
16. **Finalising DesktopLauncher.**

    The initial `DesktopLauncher.class` file comes with significant errors accordding to FernFlower, namely to do with the variable `tSamples`. `tSamples` is of type int, but is assigned to boolean values by default. Create a new file named `DesktopLauncher.java`, and copy/paste the entirety of `DesktopLauncher.class` inside. Change boolean true values for tSamples to 1, and boolean false values to 0.

    Changing the `config.setTitle` string within `DesktopLauncher.java` can then be used to verify that your decompilation process is correct. After editing, your next launch should have an executable window that displays the changed executable name.
    
17. **Editing Files.**

    To edit the base game's code, you'll have to copy/paste the `<file_name>.class` to `<file_name>.java` and fix any outstanding syntax errors before running `DesktopLauncher` as previously discussed.
  
</details>

## Roadmap.
