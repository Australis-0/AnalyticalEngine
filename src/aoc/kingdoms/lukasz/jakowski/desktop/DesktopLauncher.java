package aoc.kingdoms.lukasz.jakowski.desktop;

import aoc.kingdoms.lukasz.jakowski.AA_Game;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;

//Import AnalyticalEngine
import AnalyticalEngine.*;

//Define DesktopLauncher
public class DesktopLauncher {
    public DesktopLauncher() {
    }

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("PROJECT ORION - Age of Civilisations 3 - Dev Version");
        config.setWindowIcon(FileType.Internal, new String[]{"gfx/icon/icon_16x16.png"});
        config.setWindowIcon(FileType.Internal, new String[]{"gfx/icon/icon_32x32.png"});
        config.setWindowIcon(FileType.Internal, new String[]{"gfx/icon/icon_128x128.png"});
        config.setResizable(false);
        int tWidth = -1;
        int tHeight = -1;
        boolean fullScreenMode = false;
        int tSamples = 1;
        boolean tVSync = true;

        //INITIALISE ANALYTICAL ENGINE
        AnalyticalEngine analytical_engine = (new AnalyticalEngine()).initialise();

        try {
            FileReader fr = new FileReader("settings/Config.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String sLine = "";

            while((sLine = bfr.readLine()) != null) {
                String[] tempR = sLine.replace(";", "").split("=");

                try {
                    if (tempR[0].equals("FULLSCREEN")) {
                        fullScreenMode = Boolean.parseBoolean(tempR[1]);
                    } else if (tempR[0].equals("WIDTH")) {
                        tWidth = Integer.parseInt(tempR[1]);
                    } else if (tempR[0].equals("HEIGHT")) {
                        tHeight = Integer.parseInt(tempR[1]);
                    } else if (tempR[0].equals("ANTIALIASING")) {
                        tSamples = Integer.parseInt(tempR[1]);
                    } else if (tempR[0].equals("VSYNC")) {
                        tVSync = Boolean.parseBoolean(tempR[1]);
                    }
                } catch (Exception var12) {
                    tWidth = -1;
                    tHeight = -1;
                    fullScreenMode = true;
                    tSamples = 1;
                    tVSync = true;
                    break;
                }
            }

            fr.close();
            fr = null;
            bfr = null;
        } catch (IOException var13) {
        }

        if (tWidth < 0 && tHeight < 0) {
            config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        } else {
            config.setWindowedMode(tWidth, tHeight);
            if (fullScreenMode) {
                config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
            }
        }

        config.useVsync(tVSync);
        config.setAudioConfig(32, 512, 18);
        new Lwjgl3Application(new AA_Game(), config);
    }
}
