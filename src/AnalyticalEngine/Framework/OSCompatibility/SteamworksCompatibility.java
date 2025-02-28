package AnalyticalEngine.Framework.OSCompatibility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import AnalyticalEngine.Debugger.console;
import com.codedisaster.steamworks.SteamAPI;

public class SteamworksCompatibility {
    public static String getWindowsSteamPath () {
        try {
            ProcessBuilder process_builder = new ProcessBuilder("powershell", "-command",
                "(Get-ItemProperty 'HKCU:\\Software\\Valve\\Steam').SteamPath");
            Process process = process_builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String steam_path = reader.readLine();
            process.waitFor();

            if (steam_path != null && !steam_path.trim().isEmpty())
                //Return statement
                return steam_path.trim() + "\\Steam.exe";
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        //Return statement
        return null;
    }

    public static boolean isFlatpakSteamInstalled () {
        try {
            //Declare local instance variables
            Process process = new ProcessBuilder("flatpak", "info", "com.valvesoftware.Steam").start();

            //Return statement
            return (process.waitFor() == 0);
        } catch (Exception e) {
            //Return statement
            return false;
        }
    }

    public static boolean isSteamReady () {
        //Return statement
        try {
            return SteamAPI.isSteamRunning();
        } catch (UnsatisfiedLinkError | NoClassDefFoundError e) {
            return false;
        }
    }

    public static boolean isSteamRunning () { //[WIP] - Rework Java function
        //Declare local instance variables
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        ProcessBuilder process_builder = new ProcessBuilder();

        //Guard clause - Check Steamworks API first
        try {
            if (SteamAPI.isSteamRunning())
                return true;
        } catch (UnsatisfiedLinkError | NoClassDefFoundError e) {}

        //Check if Steam is running in process check
        if (os.contains("win")) {
            process_builder = new ProcessBuilder("cmd.exe", "/c", "tasklist");
        } else if (os.contains("nix") || os.contains("nux") || os.contains("bsd")) {
            process_builder = new ProcessBuilder("pgrep", "steam");
        } else if (os.contains("mac")) {
            process_builder = new ProcessBuilder("pgrep", "steam_osx");
        } else {
            console.log("[ERROR] isSteamRunning() - Unsupported OS: " + os);
        }

        try {
            Process process = process_builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            //Iterate over the current tasklist
            while ((line = reader.readLine()) != null)
                if (line.toLowerCase().contains("steam") && !line.toLowerCase().contains("errorreporter"))
                    return true; //Steam process found
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        //Return statement
        return false;
    }

    public static void startSteam () {
        //Declare local instance variables
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        ProcessBuilder process_builder = new ProcessBuilder();

        //Set OS-specific builder
        if (os.contains("win")) {
            String steam_path = getWindowsSteamPath();

            if (steam_path == null)
                steam_path = "C:\\Program Files (x86)\\Steam\\Steam.exe"; //Fallback Windows steam path
            process_builder.command("cmd", "/c", "\"" + steam_path + "\"");
        } else if (os.contains("nix") || os.contains("nux") || os.contains("bsd")) {
            if (isFlatpakSteamInstalled()) {
                process_builder.command("flatpak", "run", "com.valvesoftware.Steam");
            } else {
                process_builder.command("sh", "-c", "steam");
            }
        } else if (os.contains("mac")) {
            process_builder.command("open", "-a", "Steam");
        } else {
            console.log("[ERROR] startSteam() - Unsupported OS: " + os);
        }

        try {
            process_builder.start();
            console.log("Steam process started automatically.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void waitForSteam() {
        console.log("[JAVA] [AnalyticalEngine] Waiting for Steam to start...");

        //1. Wait for Steam process to appear
        while (!isSteamRunning()) {
            console.log("[DEBUG] Steam not detected. Retrying.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        console.log("Steam process detected! Waiting 20 seconds to allow full initialization...");

        //2. Fixed wait time (20 seconds) to ensure Steam initialises fully
        try {
            Thread.sleep(20000); // 20 seconds fixed wait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
