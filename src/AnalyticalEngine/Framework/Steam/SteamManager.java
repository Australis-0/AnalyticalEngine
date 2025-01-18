//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski.Steam;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Steam.Multi.SteamMultiManager;
import aoc.kingdoms.lukasz.menus.LoadSave.Menu_Load_Workshop;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamAPICall;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamLeaderboardEntriesHandle;
import com.codedisaster.steamworks.SteamLeaderboardHandle;
import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamRemoteStorage;
import com.codedisaster.steamworks.SteamRemoteStorageCallback;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGC;
import com.codedisaster.steamworks.SteamUGCCallback;
import com.codedisaster.steamworks.SteamUGCDetails;
import com.codedisaster.steamworks.SteamUGCHandle;
import com.codedisaster.steamworks.SteamUGCQuery;
import com.codedisaster.steamworks.SteamUGCUpdateHandle;
import com.codedisaster.steamworks.SteamUserStats;
import com.codedisaster.steamworks.SteamUserStatsCallback;
import com.codedisaster.steamworks.SteamUtils;
import com.codedisaster.steamworks.SteamUtilsCallback;
import com.codedisaster.steamworks.SteamRemoteStorage.PublishedFileVisibility;
import com.codedisaster.steamworks.SteamRemoteStorage.WorkshopFileType;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SteamManager {
    public static final int APP_ID = 2772750;
    public static boolean initSteam = true;
    public static List<String> modsFolders = new ArrayList();
    public static int modsFoldersSize = 0;
    public static List<SteamUGC.ItemInstallInfo> itemsInstalled = new ArrayList();
    public static int itemsInstalledSize = 0;
    public static List<String> modsFoldersAll = new ArrayList();
    public static List<String> modsFoldersAll_ModName = new ArrayList();
    public static List<SteamUGC.ItemInstallInfo> itemsInstalledAll = new ArrayList();
    public static List<String> modsTurnedOff = new ArrayList();
    public static SteamPublishedFileID createItem_steamPublishedFileID = null;
    public static SteamRemoteStorage steamRemoteStorage = null;
    public static SteamUGC steamUGC = null;
    public static SteamUGCCallback steamUGCCallback = null;
    public static SteamUtils steamUtils = null;
    public static SteamUserStats userStats = null;
    public static boolean DONE = false;

    public SteamManager() {
    }

    public static void loadSubscribedItems() {
        try {
            if (initSteam && CFG.isDesktop()) {
                SteamPublishedFileID[] steamPublishedFileIDS = new SteamPublishedFileID[steamUGC.getNumSubscribedItems()];
                int numSubscribed = steamUGC.getSubscribedItems(steamPublishedFileIDS);
                if (steamPublishedFileIDS != null) {
                    for(int i = 0; i < steamPublishedFileIDS.length; ++i) {
                        SteamUGC.ItemInstallInfo itemInstallInfo = new SteamUGC.ItemInstallInfo();
                        steamUGC.getItemInstallInfo(steamPublishedFileIDS[i], itemInstallInfo);
                        itemsInstalled.add(itemInstallInfo);
                    }

                    itemsInstalledSize = itemsInstalled.size();
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        try {
            FileHandle[] files;
            if (FileManager.IS_MAC) {
                files = Gdx.files.external("mods/").list();
            } else {
                files = Gdx.files.local("mods/").list();
            }

            for(FileHandle file : files) {
                modsFolders.add("mods/" + file.name() + "/");
            }

            modsFoldersSize = modsFolders.size();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        readModsTurnedOff();
        try {
            SteamMultiManager.init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static final void updateSteam_runCallbacks() {
        if (SteamAPI.isSteamRunning()) {
            SteamAPI.runCallbacks();
        }

    }

    public static final void init() {
        if (initSteam && CFG.isDesktop()) {
            if (steamUGC == null) {
                try {
                    SteamAPI.loadLibraries();
                    if (!SteamAPI.init() && SteamAPI.restartAppIfNecessary(2772750)) {
                    }
                } catch (SteamException ex) {
                    ex.printStackTrace();
                }

                steamRemoteStorage = new SteamRemoteStorage(new SteamRemoteStorageCallback() {
                    public void onFileShareResult(SteamUGCHandle steamUGCHandle, String s, SteamResult steamResult) {
                        Gdx.app.log("SteamRemoteStorage", "onFileShareResult");
                    }

                    public void onDownloadUGCResult(SteamUGCHandle steamUGCHandle, SteamResult steamResult) {
                        Gdx.app.log("SteamRemoteStorage", "onDownloadUGCResult");
                    }

                    public void onPublishFileResult(SteamPublishedFileID steamPublishedFileID, boolean b, SteamResult steamResult) {
                        Gdx.app.log("SteamRemoteStorage", "onPublishFileResult");
                    }

                    public void onUpdatePublishedFileResult(SteamPublishedFileID steamPublishedFileID, boolean b, SteamResult steamResult) {
                        Gdx.app.log("SteamRemoteStorage", "onUpdatePublishedFileResult");
                    }

                    public void onPublishedFileSubscribed(SteamPublishedFileID steamPublishedFileID, int i) {
                        Gdx.app.log("SteamRemoteStorage", "onPublishedFileSubscribed");
                    }

                    public void onPublishedFileUnsubscribed(SteamPublishedFileID steamPublishedFileID, int i) {
                        Gdx.app.log("SteamRemoteStorage", "onPublishedFileUnsubscribed");
                    }

                    public void onPublishedFileDeleted(SteamPublishedFileID steamPublishedFileID, int i) {
                        Gdx.app.log("SteamRemoteStorage", "onPublishedFileDeleted");
                    }

                    public void onFileWriteAsyncComplete(SteamResult steamResult) {
                        Gdx.app.log("SteamRemoteStorage", "onFileWriteAsyncComplete");
                    }

                    public void onFileReadAsyncComplete(SteamAPICall steamAPICall, SteamResult steamResult, int i, int i1) {
                        Gdx.app.log("SteamRemoteStorage", "onFileReadAsyncComplete");
                    }
                });
                steamUGCCallback = new SteamUGCCallback() {
                    public void onUGCQueryCompleted(SteamUGCQuery steamUGCQuery, int i, int i1, boolean b, SteamResult steamResult) {
                        Gdx.app.log("onUGCQueryCompleted", "onUGCQueryCompleted");
                    }

                    public void onSubscribeItem(SteamPublishedFileID steamPublishedFileID, SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onSubscribeItem");
                    }

                    public void onUnsubscribeItem(SteamPublishedFileID steamPublishedFileID, SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onUnsubscribeItem");
                    }

                    public void onRequestUGCDetails(SteamUGCDetails steamUGCDetails, SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onRequestUGCDetails");
                    }

                    public void onCreateItem(SteamPublishedFileID steamPublishedFileID, boolean b, SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onCreateItem");
                        SteamManager.createItem_steamPublishedFileID = steamPublishedFileID;
                        if (steamResult == SteamResult.OK) {
                            Game.menuManager.addToastGold(Game.lang.get("UploadedSuccessfully"), Images.technology2, 60000);
                        } else {
                            Game.menuManager.addToastGold("Create: " + Game.lang.get("Error"), Images.technology2, 60000);
                        }

                        SteamManager.DONE = true;
                    }

                    public void onSubmitItemUpdate(SteamPublishedFileID steamPublishedFileID, boolean b, SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onSubmitItemUpdate");
                        if (steamResult == SteamResult.OK) {
                            Game.menuManager.addToastGold(Game.lang.get("UploadedSuccessfully"), Images.technology2, 60000);
                        } else {
                            Game.menuManager.addToastGold("Update" + Game.lang.get("Error") + ": " + steamResult.name(), Images.technology2, 60000);
                        }

                        SteamManager.DONE = true;
                        Menu_Load_Workshop.uploaded = true;
                    }

                    public void onDownloadItemResult(int i, SteamPublishedFileID steamPublishedFileID, SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onDownloadItemResult");
                    }

                    public void onUserFavoriteItemsListChanged(SteamPublishedFileID steamPublishedFileID, boolean b, SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onUserFavoriteItemsListChanged");
                    }

                    public void onSetUserItemVote(SteamPublishedFileID steamPublishedFileID, boolean b, SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onSetUserItemVote");
                    }

                    public void onGetUserItemVote(SteamPublishedFileID steamPublishedFileID, boolean b, boolean b1, boolean b2, SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onGetUserItemVote");
                    }

                    public void onStartPlaytimeTracking(SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onStartPlaytimeTracking");
                    }

                    public void onStopPlaytimeTracking(SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onStopPlaytimeTracking");
                    }

                    public void onStopPlaytimeTrackingForAllItems(SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onStopPlaytimeTrackingForAllItems");
                    }

                    public void onDeleteItem(SteamPublishedFileID steamPublishedFileID, SteamResult steamResult) {
                        Gdx.app.log("SteamUGC", "onDeleteItem");
                    }
                };
                steamUGC = new SteamUGC(steamUGCCallback);
                steamUtils = new SteamUtils(new SteamUtilsCallback() {
                    public void onSteamShutdown() {
                        Gdx.app.log("SteamUtils", "onSteamShutdown");
                        SteamAPI.shutdown();
                    }
                });
                userStats = new SteamUserStats(new SteamUserStatsCallback() {
                    public void onUserStatsReceived(long gameId, SteamID steamIDUser, SteamResult result) {
                    }

                    public void onUserStatsStored(long gameId, SteamResult result) {
                    }

                    public void onUserStatsUnloaded(SteamID steamIDUser) {
                    }

                    public void onUserAchievementStored(long gameId, boolean isGroupAchievement, String achievementName, int curProgress, int maxProgress) {
                    }

                    public void onLeaderboardFindResult(SteamLeaderboardHandle leaderboard, boolean found) {
                    }

                    public void onLeaderboardScoresDownloaded(SteamLeaderboardHandle leaderboard, SteamLeaderboardEntriesHandle entries, int numEntries) {
                    }

                    public void onLeaderboardScoreUploaded(boolean success, SteamLeaderboardHandle leaderboard, int score, boolean scoreChanged, int globalRankNew, int globalRankPrevious) {
                    }

                    public void onNumberOfCurrentPlayersReceived(boolean success, int players) {
                    }

                    public void onGlobalStatsReceived(long gameId, SteamResult result) {
                    }
                });
            }

        }
    }

    public static void createItem(String modFolder) {
        try {
            DONE = false;
            CFG.LOG("mods/" + modFolder + "/id.txt");
            if (FileManager.IS_MAC && Gdx.files.external("mods/" + modFolder + "/id.txt").exists()) {
                FileHandle fileList = Gdx.files.external("mods/" + modFolder + "/id.txt");
                String fileContent = fileList.readString();
                createItem_steamPublishedFileID = new SteamPublishedFileID(Long.parseLong(fileContent));
            } else if (Gdx.files.internal("mods/" + modFolder + "/id.txt").exists()) {
                FileHandle fileList = Gdx.files.internal("mods/" + modFolder + "/id.txt");
                String fileContent = fileList.readString();
                createItem_steamPublishedFileID = new SteamPublishedFileID(Long.parseLong(fileContent));
            } else {
                SteamAPICall steamAPICall = steamUGC.createItem(2772750, WorkshopFileType.Community);
                CFG.LOG("createItem", "steamAPICall.isValid: " + steamAPICall.isValid());

                while(!DONE) {
                    SteamAPI.runCallbacks();

                    try {
                        Thread.sleep(250L);
                    } catch (InterruptedException e) {
                        throw new SteamException(e);
                    }
                }

                try {
                    FileHandle fileSave = FileManager.getSaveType("mods/" + modFolder + "/id.txt");
                    fileSave.writeString("" + Long.parseLong(createItem_steamPublishedFileID.toString(), 16), false);
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }
            }

            Json json = new Json();
            FileHandle file;
            if (FileManager.IS_MAC) {
                file = Gdx.files.external("mods/" + modFolder + "/mod.txt");
            } else {
                file = Gdx.files.internal("mods/" + modFolder + "/mod.txt");
            }

            ModData modData = (ModData)json.fromJson(ModData.class, file);
            SteamUGCUpdateHandle updateHandle = steamUGC.startItemUpdate(2772750, createItem_steamPublishedFileID);
            String nPath = (new FileHandle("mods/" + modFolder + "/")).file().getAbsolutePath();
            Gdx.app.log("nPath", "nPath: " + nPath);
            steamUGC.setItemContent(updateHandle, nPath);
            CFG.LOG("modData.Name: " + modData.Name);
            steamUGC.setItemTitle(updateHandle, modData.Name);
            steamUGC.setItemTags(updateHandle, modData.Tags);
            steamUGC.setItemDescription(updateHandle, modData.Description);
            CFG.LOG((new FileHandle("mods/" + modFolder + "/logo.png")).file().getAbsolutePath());
            steamUGC.setItemPreview(updateHandle, (new FileHandle("mods/" + modFolder + "/logo.png")).file().getAbsolutePath());
            steamUGC.setItemVisibility(updateHandle, PublishedFileVisibility.Public);
            steamUGC.submitItemUpdate(updateHandle, modData.ChangeNote);
            SteamUGC.ItemUpdateInfo updateInfo = new SteamUGC.ItemUpdateInfo();
            steamUGC.getItemUpdateProgress(updateHandle, updateInfo);
            DONE = false;

            while(!DONE) {
                SteamAPI.runCallbacks();
                steamUGC.getItemUpdateProgress(updateHandle, updateInfo);
                Gdx.app.log("Progress", "Progress: " + updateInfo.getBytesProcessed() + "/" + updateInfo.getBytesTotal());

                try {
                    Thread.sleep(250L);
                } catch (InterruptedException e) {
                    throw new SteamException(e);
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static void addModsTurnedOff(String folder) {
        if (folder != null && folder.length() > 0) {
            if (!modsTurnedOff.contains(folder)) {
                modsTurnedOff.add(folder);
            } else {
                removeModsTurnedOff(folder);
            }

            saveModsTurnedOff();
        }

    }

    public static void removeModsTurnedOff(String folder) {
        if (folder.length() > 0) {
            for(int i = modsTurnedOff.size() - 1; i >= 0; --i) {
                if (((String)modsTurnedOff.get(i)).equals(folder)) {
                    modsTurnedOff.remove(i);
                    saveModsTurnedOff();
                    return;
                }
            }
        }

    }

    public static boolean isTurnedOn(String folder) {
        if (folder != null && folder.length() > 0) {
            for(int i = modsTurnedOff.size() - 1; i >= 0; --i) {
                if (((String)modsTurnedOff.get(i)).equals(folder)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void saveModsTurnedOff() {
        FileHandle fileSave = FileManager.getSaveType("settings/ModsOff.txt");
        if (modsTurnedOff.isEmpty()) {
            if (FileManager.getSaveType("settings/ModsOff.txt").exists()) {
                FileManager.getSaveType("settings/ModsOff.txt").delete();
            }
        } else {
            for(int i = 0; i < modsTurnedOff.size(); ++i) {
                fileSave.writeString((String)modsTurnedOff.get(i) + ";", i != 0);
            }
        }

    }

    public static void readModsTurnedOff() {
        for(int j = 0; j < modsFolders.size(); ++j) {
            modsFoldersAll.add((String)modsFolders.get(j));
        }

        for(int j = 0; j < itemsInstalled.size(); ++j) {
            itemsInstalledAll.add((SteamUGC.ItemInstallInfo)itemsInstalled.get(j));

            try {
                if (Gdx.files.absolute(((SteamUGC.ItemInstallInfo)itemsInstalled.get(j)).getFolder() + "/mod.txt").exists()) {
                    FileHandle file = Gdx.files.absolute(((SteamUGC.ItemInstallInfo)itemsInstalled.get(j)).getFolder() + "/mod.txt");
                    String tempTags = file.readString();
                    Pattern pattern = Pattern.compile("Name:\\s*\"(.*?)\"");
                    Matcher matcher = pattern.matcher(tempTags);
                    if (matcher.find()) {
                        String name = matcher.group(1);
                        modsFoldersAll_ModName.add(name);
                    } else {
                        modsFoldersAll_ModName.add(((SteamUGC.ItemInstallInfo)itemsInstalled.get(j)).getFolder());
                    }
                }
            } catch (Exception var6) {
                modsFoldersAll_ModName.add(((SteamUGC.ItemInstallInfo)itemsInstalled.get(j)).getFolder());
            }
        }

        try {
            if (Gdx.files.internal("settings/ModsOff.txt").exists() || FileManager.IS_MAC && Gdx.files.external("settings/ModsOff.txt").exists()) {
                FileHandle file;
                if (FileManager.IS_MAC) {
                    file = Gdx.files.external("settings/ModsOff.txt");
                } else {
                    file = Gdx.files.internal("settings/ModsOff.txt");
                }

                String tempTags = file.readString();
                modsTurnedOff.clear();
                String[] split = tempTags.split(";");

                for(int i = 0; i < split.length; ++i) {
                    modsTurnedOff.add(split[i]);
                }

                for(int i = 0; i < modsTurnedOff.size(); ++i) {
                    for(int j = modsFolders.size() - 1; j >= 0; --j) {
                        if (((String)modsTurnedOff.get(i)).equals(modsFolders.get(j))) {
                            modsFolders.remove(j);
                            break;
                        }
                    }

                    for(int j = itemsInstalled.size() - 1; j >= 0; --j) {
                        if (((String)modsTurnedOff.get(i)).equals(((SteamUGC.ItemInstallInfo)itemsInstalled.get(j)).getFolder())) {
                            itemsInstalled.remove(j);
                            break;
                        }
                    }
                }

                modsFoldersSize = modsFolders.size();
                itemsInstalledSize = itemsInstalled.size();
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static class ModData {
        public String Name;
        public String Description;
        public String[] Tags;
        public String ChangeNote;

        public ModData() {
        }
    }
}
