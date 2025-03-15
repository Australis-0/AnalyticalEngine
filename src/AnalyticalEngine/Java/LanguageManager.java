//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package aoc.kingdoms.lukasz.jakowski;

import AnalyticalEngine.Debugger.console;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;
import com.codedisaster.steamworks.SteamUGC;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageManager {
    public static boolean translationsKeysMode = false;
    public I18NBundle bundle;
    public I18NBundle bundleCivs;
    public I18NBundle bundleLoading;
    public int iLoading_NumOfTexts = 0;
    private KeyOutput keyOutput;
    public List<I18NBundle> modsBundles = new ArrayList();
    public int modsBundlesSize = 0;

    public LanguageManager(String nTag) {
        if (nTag == null) {
            nTag = "";
        }

        FileHandle fileHandle = FileManager.loadFile("game/languages/Bundle");
        Locale locale = new Locale(nTag);
        this.bundle = I18NBundle.createBundle(fileHandle, locale);
        this.initCivsBundle(nTag);
        this.initLoadingBundle(nTag);
        this.updateKeyOutput();
    }

    /**
     * initCivsBundle() - Initialises civilisation localisation for both mods and the base game. Overwrite method.
     * @param {String} arg0_language_id
     */
    public final void initCivsBundle (String arg0_language_id) {
        //Convert from parameters
        String language_id = arg0_language_id;

        //Declare local instance variables
        FileHandle file_handle_civs = FileManager.loadFile("game/languages/civilizations/Bundle");
        boolean language_file_exists = FileManager.loadFile("game/languages/civilizations/Bundle_" + language_id + ".properties").exists();

        //1. Load this.bundleCivs by default
        if (language_id != null && language_id.length() > 0 && !language_file_exists) {
            Locale locale_civs = new Locale(language_id);
            this.bundleCivs = I18NBundle.createBundle(file_handle_civs, locale_civs);
        } else {
            Locale locale_civs = new Locale(language_id);
            this.bundleCivs = I18NBundle.createBundle(file_handle_civs, locale_civs);
        }

        //2. Iterate over all mod folders and override this.bundleCivs if applicable
        for (int i = 0; i < SteamManager.modsFoldersSize; i++) {
            FileHandle local_file_handle_civs = FileManager.loadFile((String) SteamManager.modsFolders.get(i) + "languages/civilisations/Bundle.properties");
            FileHandle local_locale_file_handle_civs = FileManager.loadFile((String) SteamManager.modsFolders.get(i) + "languages/civilisations/Bundle_" + language_id + ".properties");

            if (local_file_handle_civs.exists()) {
                Locale local_locale_civs = new Locale(language_id);
                FileHandle local_stripped_file_handle_civs = local_file_handle_civs.sibling("Bundle");

                //Set bundleCivs; make sure to strip .properties
                this.bundleCivs = I18NBundle.createBundle(local_stripped_file_handle_civs, local_locale_civs);
            }
            if (local_locale_file_handle_civs.exists()) {
                FileHandle local_stripped_file_handle_civs = local_file_handle_civs.sibling("Bundle");
                Locale local_locale_civs = new Locale(language_id);

                //Set bundleCivs; make sure to strip .properties
                this.bundleCivs = I18NBundle.createBundle(local_stripped_file_handle_civs, local_locale_civs);
            }
        }
    }

    /**
     * initLoadingBundle() - Initialises loading tip localisation for both mods and the base game. Overwrite method.
     * @param {String} arg0_language_id
     */
    public final void initLoadingBundle (String arg0_language_id) {
        //Convert from parameters
        String language_id = arg0_language_id;

        //Declare local instance variables
        FileHandle file_handle_loading = FileManager.loadFile("game/languages/loading/Bundle");
        boolean language_file_exists = FileManager.loadFile("game/languages/loaing/Bundle_" + language_id + ".properties").exists();

        //1. Load this.bundleLoading by default
        if (language_id != null && language_id.length() > 0 && !language_file_exists) {
            Locale locale_loading = new Locale(language_id);
            this.bundleLoading = I18NBundle.createBundle(file_handle_loading, locale_loading);
            this.iLoading_NumOfTexts = Integer.parseInt(this.getLoading("NumOfTexts"));
        } else {
            Locale locale_loading = new Locale(language_id);
            this.bundleLoading = I18NBundle.createBundle(file_handle_loading, locale_loading);
            this.iLoading_NumOfTexts = Integer.parseInt(this.getLoading("NumOfTexts"));
        }

        //2. Iterate over all mod folders and override this.bundleLoading if applicable
        for (int i = 0; i < SteamManager.modsFoldersSize; i++) {
            FileHandle local_file_handle_loading = FileManager.loadFile((String) SteamManager.modsFolders.get(i) + "languages/loading/Bundle.properties");
            FileHandle local_locale_file_handle_loading = FileManager.loadFile((String) SteamManager.modsFolders.get(i) + "languages/loading/Bundle_" + language_id + ".properties");

            if (local_file_handle_loading.exists()) {
                Locale local_locale_loading = new Locale(language_id);
                FileHandle local_stripped_file_handle_loading = local_file_handle_loading.sibling("Bundle");

                //Set bundleLoading; make sure to strip .properties
                this.bundleLoading = I18NBundle.createBundle(local_stripped_file_handle_loading, local_locale_loading);
                this.iLoading_NumOfTexts = Integer.parseInt(this.getLoading("NumOfTexts"));
            }
            if (local_locale_file_handle_loading.exists()) {
                Locale local_locale_loading = new Locale(language_id);
                FileHandle local_stripped_file_handle_loading = local_file_handle_loading.sibling("Bundle");

                //Set bundleLoading; make sure to strip .properties
                this.bundleLoading = I18NBundle.createBundle(local_stripped_file_handle_loading, local_locale_loading);
                this.iLoading_NumOfTexts = Integer.parseInt(this.getLoading("NumOfTexts"));
            }
        }
    }

    /**
     * loadModsLanguages() - Loads mod localisation for both mods and the base game. Additive method.
     * @param {String} nTag
     */
    public void loadModsLanguages (String nTag) {
        try {
            this.modsBundles.clear();
            console.log("Called loadModsLanguages() with '" + nTag + "'.");

            for(int i = 0; i < SteamManager.modsFoldersSize; ++i) {
                if (Gdx.files.external((String)SteamManager.modsFolders.get(i) + "languages/Bundle" + "_" + nTag + ".properties").exists()) {
                    FileHandle fileHandleCivs = Gdx.files.external((String)SteamManager.modsFolders.get(i) + "languages/Bundle");
                    Locale localeCivs = new Locale(nTag);
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                } else if (Gdx.files.external((String)SteamManager.modsFolders.get(i) + "languages/Bundle" + ".properties").exists()) {
                    FileHandle fileHandleCivs = Gdx.files.external((String)SteamManager.modsFolders.get(i) + "languages/Bundle");
                    Locale localeCivs = new Locale("");
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                } else if (Gdx.files.internal((String)SteamManager.modsFolders.get(i) + "languages/Bundle" + "_" + nTag + ".properties").exists()) {
                    FileHandle fileHandleCivs = Gdx.files.internal((String)SteamManager.modsFolders.get(i) + "languages/Bundle");
                    Locale localeCivs = new Locale(nTag);
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                } else if (Gdx.files.internal((String)SteamManager.modsFolders.get(i) + "languages/Bundle" + ".properties").exists()) {
                    FileHandle fileHandleCivs = Gdx.files.internal((String)SteamManager.modsFolders.get(i) + "languages/Bundle");
                    Locale localeCivs = new Locale("");
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                }
            }

            for(int i = 0; i < SteamManager.itemsInstalledSize; ++i) {
                if (Gdx.files.absolute(((SteamUGC.ItemInstallInfo)SteamManager.itemsInstalled.get(i)).getFolder() + "/" + "languages/Bundle" + "_" + nTag + ".properties").exists()) {
                    FileHandle fileHandleCivs = Gdx.files.absolute(((SteamUGC.ItemInstallInfo)SteamManager.itemsInstalled.get(i)).getFolder() + "/" + "languages/Bundle");
                    Locale localeCivs = new Locale(nTag);
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                } else if (Gdx.files.absolute(((SteamUGC.ItemInstallInfo)SteamManager.itemsInstalled.get(i)).getFolder() + "/" + "languages/Bundle" + ".properties").exists()) {
                    FileHandle fileHandleCivs = Gdx.files.absolute(((SteamUGC.ItemInstallInfo)SteamManager.itemsInstalled.get(i)).getFolder() + "/" + "languages/Bundle");
                    Locale localeCivs = new Locale("");
                    this.modsBundles.add(I18NBundle.createBundle(fileHandleCivs, localeCivs));
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        this.modsBundlesSize = this.modsBundles.size();
    }

    public final void updateKeyOutput() {
        if (translationsKeysMode) {
            this.keyOutput = new KeyOutput() {
                public String get(String key) {
                    return "[" + key + "]";
                }

                public String get(String key, int iValue) {
                    return "[" + key + "]";
                }

                public String get(String key, String sValue) {
                    return "[" + key + "]";
                }

                public String get(String key, String sValue, String sValue2) {
                    return "[" + key + "]";
                }
            };
        } else {
            this.keyOutput = new KeyOutput() {
                public String get(String key) {
                    try {
                        for(int i = 0; i < LanguageManager.this.modsBundlesSize; ++i) {
                            try {
                                return ((I18NBundle)LanguageManager.this.modsBundles.get(i)).get(key);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                        return LanguageManager.this.bundle.get(key);
                    } catch (Exception var5) {
                        return key;
                    }
                }

                public String get(String key, int iValue) {
                    try {
                        for(int i = 0; i < LanguageManager.this.modsBundlesSize; ++i) {
                            try {
                                return ((I18NBundle)LanguageManager.this.modsBundles.get(i)).format(key, new Object[]{iValue});
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                        return LanguageManager.this.bundle.format(key, new Object[]{iValue});
                    } catch (Exception var6) {
                        return key;
                    }
                }

                public String get(String key, String sValue) {
                    try {
                        for(int i = 0; i < LanguageManager.this.modsBundlesSize; ++i) {
                            try {
                                return ((I18NBundle)LanguageManager.this.modsBundles.get(i)).format(key, new Object[]{sValue});
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                        return LanguageManager.this.bundle.format(key, new Object[]{sValue});
                    } catch (Exception var6) {
                        return key;
                    }
                }

                public String get(String key, String sValue, String sValue2) {
                    try {
                        for(int i = 0; i < LanguageManager.this.modsBundlesSize; ++i) {
                            try {
                                return ((I18NBundle)LanguageManager.this.modsBundles.get(i)).format(key, new Object[]{sValue, sValue2});
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                        return LanguageManager.this.bundle.format(key, new Object[]{sValue, sValue2});
                    } catch (Exception var7) {
                        return key;
                    }
                }
            };
        }

    }

    public String get(String key) {
        return this.keyOutput.get(key);
    }

    public String get(String key, int nValue) {
        return this.keyOutput.get(key, nValue);
    }

    public String get(String key, String nValue) {
        return this.keyOutput.get(key, nValue);
    }

    public String get(String key, String nValue, String nValue2) {
        return this.keyOutput.get(key, nValue, nValue2);
    }

    public String getCiv (String arg0_key) { //[WIP] - Refactor this so that it actually returns the correct key in line with base tags
        //Convert from parameters
        String key = arg0_key;

        //Guard clause if the current actual tag (e.g. 'eth_m') is already included.
        try { return this.bundleCivs.get(key); } catch (Exception e) {}

        //If key is defined and it contains an underscore, but the actual tag localisation couldn't be found, fetch the base tag localisation
        if (key != null && key.indexOf(95) > 0) {
            //Try to push the base tag
            try {
                String[] split_tag = key.split("_");

                //Return statement
                return this.bundleCivs.get(split_tag[0]);
            } catch (Exception e) {}
        }

        //Load default civilisation loc (i.e. in civilisation.json files) as fallback.
        try {
            Game.LoadCivilizationData civ_obj = Game.loadCivilization(key);

            if (civ_obj.Name != null && civ_obj.Name.length() > 0)
                //Return statement
                return civ_obj.Name;
        } catch (Exception e) {}

        //Return raw key if no localisation could be found
        return key;
    }

    public String getLoading(String key) {
        try {
            return this.bundleLoading.get(key);
        } catch (Exception var3) {
            return "";
        }
    }

    public final void dispose() {
        this.bundle = null;
        this.bundleCivs = null;
        this.bundleLoading = null;
        this.iLoading_NumOfTexts = 0;
    }

    interface KeyOutput {
        String get(String var1);

        String get(String var1, int var2);

        String get(String var1, String var2);

        String get(String var1, String var2, String var3);
    }
}
