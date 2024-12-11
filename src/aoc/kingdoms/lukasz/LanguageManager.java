//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski;

import AnalyticalEngine.Debugger.console;
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
    private I18NBundle bundle;
    private I18NBundle bundleCivs;
    private I18NBundle bundleLoading;
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

    public final void initCivsBundle(String nTag) {
        FileHandle fileHandleCivs = FileManager.loadFile("game/languages/civilizations/Bundle");
        if (nTag != null && nTag.length() > 0) {
            if (FileManager.loadFile("game/languages/civilizations/Bundle_" + nTag + ".properties").exists()) {
                Locale localeCivs = new Locale(nTag);
                this.bundleCivs = I18NBundle.createBundle(fileHandleCivs, localeCivs);
            } else {
                Locale localeCivs = new Locale("");
                this.bundleCivs = I18NBundle.createBundle(fileHandleCivs, localeCivs);
            }
        } else {
            Locale localeCivs = new Locale(nTag);
            this.bundleCivs = I18NBundle.createBundle(fileHandleCivs, localeCivs);
        }

    }

    public final void initLoadingBundle(String nTag) {
        FileHandle fileHandleLoading = FileManager.loadFile("game/languages/loading/Bundle");
        Locale localeLoading = new Locale(nTag);
        this.bundleLoading = I18NBundle.createBundle(fileHandleLoading, localeLoading);

        try {
            this.iLoading_NumOfTexts = Integer.parseInt(this.getLoading("NumOfTexts"));
        } catch (Exception var5) {
            this.iLoading_NumOfTexts = 0;
        }

    }

    public void loadModsLanguages(String nTag) {
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

            //ANALYTICALENGINE START
            //ANALYTICALENGINE END

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

    public String getCiv(String key) {
        try {
            return this.bundleCivs.get(key);
        } catch (Exception var8) {
            if (key != null && key.indexOf(95) > 0) {
                try {
                    for(int i = 0; i < this.modsBundlesSize; ++i) {
                        try {
                            return ((I18NBundle)this.modsBundles.get(i)).get(key.substring(0, key.indexOf(95)));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    return this.bundleCivs.get(key.substring(0, key.indexOf(95)));
                } catch (Exception var7) {
                }
            }

            try {
                Game.LoadCivilizationData tCiv = Game.loadCivilization(key);
                if (tCiv.Name != null && tCiv.Name.length() > 0) {
                    return tCiv.Name;
                }
            } catch (Exception var5) {
            }

            return key;
        }
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
