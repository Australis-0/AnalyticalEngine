//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski;

import aoc.kingdoms.lukasz.map.map.MapScenarios;
import aoc.kingdoms.lukasz.menu_element.button.ButtonTopDate;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.textures.Images;

import javax.script.Invocable;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class Game_Calendar {
    public static int TURN_ID = 1;
    public static int TURN_ID_HOST = 1;
    public static int HOUR = 0;
    public static int IMG_MANPOWER = 0;
    public static int IMG_MANPOWER_SPLIT = 0;
    public static int IMG_MANPOWER_DISBAND = 0;
    public static int IMG_MANPOWER_REORGANIZE = 0;
    public static int IMG_MANPOWER_MERGE = 0;
    public static int IMG_MANPOWER_UP = 0;
    public static int IMG_MANPOWER_TIME = 0;
    public static int IMG_TECHNOLOGY = 0;
    public static int IMG_ECONOMY = 0;
    public static int IMG_ECONOMY_UP = 0;
    public static int IMG_ECONOMY_DOWN = 0;
    public static int IMG_FORT_DEFENSE = 0;
    public static int CURRENT_AGEID = 0;
    public static int currentDay = 1;
    public static int currentMonth = 1;
    public static int currentYear = 2014;
    public static float GAME_SPEED = 1.0F;
    public static float GAME_SPEED_MIN = 0.5F;
    public static float GAME_SPEED_MAX = 2.0F;
    public static float UPDATE_NUM_OF_DAYS = 30.0F;
    public static int UPDATE_NUM_OF_DAYS_INT = 30;
    public static boolean ENABLE_COLONIZATION_OF_WASTELAND = true;
    private static final int[] NUM_OF_DAYS_IN_MONTH = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final int NUM_OF_DAYS = 365;
    public static final int NUM_OF_MONTHS = 12;

    public Game_Calendar() {
    }

    public static final int getNumOfDaysInMonth(int nMonth) {
        try {
            return NUM_OF_DAYS_IN_MONTH[nMonth - 1];
        } catch (Exception var2) {
            return 28;
        }
    }

    public static final String getCurrentDate() {
        return (Game.HOURS_PER_TURN < 24 ? (HOUR < 10 ? "0" : "") + HOUR + ":00, " : "") + "" + currentDay + " " + getMonthName(currentMonth) + " " + Game.gameAges.getYear(currentYear);
    }

    public static final String getCurrentDate_Simple() {
        return (Game.HOURS_PER_TURN < 24 ? (HOUR < 10 ? "0" : "") + HOUR + ":00, " : "") + "" + currentDay + " " + currentMonth + " " + Game.gameAges.getYear(currentYear);
    }

    public static final String getMonthName(int nMonth) {
        switch (nMonth) {
            case 1:
                return Game.lang.get("January");
            case 2:
                return Game.lang.get("February");
            case 3:
                return Game.lang.get("March");
            case 4:
                return Game.lang.get("April");
            case 5:
                return Game.lang.get("May");
            case 6:
                return Game.lang.get("June");
            case 7:
                return Game.lang.get("July");
            case 8:
                return Game.lang.get("August");
            case 9:
                return Game.lang.get("September");
            case 10:
                return Game.lang.get("October");
            case 11:
                return Game.lang.get("November");
            case 12:
                return Game.lang.get("December");
            case 13:
                return Game.lang.get("January");
            default:
                return Game.lang.get("December");
        }
    }

    public static void updateDateNextTurn() {
        nextDays(Game.gameAges.getAge_TurnDays(CURRENT_AGEID));
    }

    public static final String getNumOfDates_ByTurnID(int nTurnID) {
        if (nTurnID == TURN_ID) {
            return Game.lang.get("DaysX", 0);
        } else if (nTurnID > TURN_ID) {
            List<Integer> tempDate = new ArrayList();
            tempDate.add(currentDay);
            tempDate.add(currentMonth);
            tempDate.add(currentYear);
            tempDate.add(CURRENT_AGEID);
            tempDate = forwardDays(tempDate, nTurnID - TURN_ID);
            return "" + tempDate.get(0) + " " + getMonthName((Integer)tempDate.get(1)) + " " + Game.gameAges.getYear((Integer)tempDate.get(2));
        } else {
            List<Integer> tempDate = new ArrayList();
            tempDate.add(currentDay);
            tempDate.add(currentMonth);
            tempDate.add(currentYear);
            tempDate.add(CURRENT_AGEID);
            tempDate = backwardsDays(tempDate, TURN_ID - nTurnID);
            List<Integer> tempDateOut = getNumOfDates_ByTurnID(tempDate);
            return "" + ((Integer)tempDateOut.get(2) > 0 ? Game.lang.get("YearsX", (Integer)tempDateOut.get(2)) + ((Integer)tempDateOut.get(1) <= 0 && (Integer)tempDateOut.get(0) <= 0 ? "" : " ") : "") + ((Integer)tempDateOut.get(1) > 0 ? Game.lang.get("MonthsX", (Integer)tempDateOut.get(1)) + ((Integer)tempDateOut.get(0) > 0 ? " " : "") : "") + ((Integer)tempDateOut.get(0) > 0 ? Game.lang.get("DaysX", (Integer)tempDateOut.get(0)) : "");
        }
    }

    public static final String getNumOfDays_ByTurnsPlayed(int nTurnID) {
        List<Integer> tempDate = new ArrayList();
        tempDate.add(currentDay);
        tempDate.add(currentMonth);
        tempDate.add(currentYear);
        tempDate.add(CURRENT_AGEID);
        tempDate = backwardsDays(tempDate, nTurnID);
        List<Integer> tempDateOut = getNumOfDates_ByTurnID(tempDate);
        return "" + ((Integer)tempDateOut.get(2) > 0 ? Game.lang.get("YearsX", (Integer)tempDateOut.get(2)) + ((Integer)tempDateOut.get(1) <= 0 && (Integer)tempDateOut.get(0) <= 0 ? "" : " ") : "") + ((Integer)tempDateOut.get(1) > 0 ? Game.lang.get("MonthsX", (Integer)tempDateOut.get(1)) + ((Integer)tempDateOut.get(0) > 0 ? " " : "") : "") + ((Integer)tempDateOut.get(0) > 0 ? Game.lang.get("DaysX", (Integer)tempDateOut.get(0)) : "");
    }

    public static final String getNumOfDays_ByTurnsPlayed_WithoutDays(int nTurnID) {
        List<Integer> tempDate = new ArrayList();
        tempDate.add(currentDay);
        tempDate.add(currentMonth);
        tempDate.add(currentYear);
        tempDate.add(CURRENT_AGEID);
        tempDate = backwardsDays(tempDate, nTurnID);
        List<Integer> tempDateOut = getNumOfDates_ByTurnID(tempDate);
        return "" + ((Integer)tempDateOut.get(2) > 0 ? Game.lang.get("YearsX", (Integer)tempDateOut.get(2)) + ((Integer)tempDateOut.get(1) <= 0 && (Integer)tempDateOut.get(0) <= 0 ? "" : " ") : "") + ((Integer)tempDateOut.get(1) > 0 ? Game.lang.get("MonthsX", (Integer)tempDateOut.get(1)) + ((Integer)tempDateOut.get(0) > 0 ? " " : "") : "");
    }

    private static List<Integer> getNumOfDates_ByTurnID(List<Integer> tempDate) {
        List<Integer> out = new ArrayList();
        out.add(0);
        out.add(0);
        out.add(0);
        out.set(2, Math.abs(currentYear - (Integer)tempDate.get(2)));
        tempDate.set(2, currentYear);
        if ((Integer)tempDate.get(1) == currentMonth) {
            if ((Integer)tempDate.get(0) > currentDay) {
                out.set(1, (Integer)out.get(1) - 1);
                out.set(0, currentDay + (NUM_OF_DAYS_IN_MONTH[(Integer)tempDate.get(1) - 1] - (Integer)tempDate.get(0)));
                if ((Integer)out.get(1) < 0) {
                    out.set(1, 11);
                    out.set(2, (Integer)out.get(2) - 1);
                    if ((Integer)out.get(2) < 0) {
                        out.set(2, 0);
                    }
                }
            } else {
                out.set(0, currentDay - (Integer)tempDate.get(0));
            }
        } else if ((Integer)tempDate.get(1) < currentMonth) {
            out.set(1, currentMonth - (Integer)tempDate.get(1));
            if ((Integer)tempDate.get(0) > currentDay) {
                out.set(1, (Integer)out.get(1) - 1);
                out.set(0, currentDay + (NUM_OF_DAYS_IN_MONTH[(Integer)tempDate.get(1) - 1] - (Integer)tempDate.get(0)));
            } else {
                out.set(0, currentDay - (Integer)tempDate.get(0));
            }
        } else {
            if ((Integer)out.get(2) > 0) {
                out.set(2, (Integer)out.get(2) - 1);
            }

            out.set(1, currentMonth + (12 - (Integer)tempDate.get(1)));
            if ((Integer)tempDate.get(0) > currentDay) {
                out.set(1, (Integer)out.get(1) - 1);
                out.set(0, currentDay + (NUM_OF_DAYS_IN_MONTH[(Integer)tempDate.get(1) - 1] - (Integer)tempDate.get(0)));
            } else {
                out.set(0, currentDay - (Integer)tempDate.get(0));
            }
        }

        return out;
    }

    public static final String getDate_ByTurnID(int nTurnID) {
        if (nTurnID == TURN_ID) {
            return getCurrentDate();
        } else if (nTurnID > TURN_ID) {
            List<Integer> tempDate = new ArrayList();
            tempDate.add(currentDay);
            tempDate.add(currentMonth);
            tempDate.add(currentYear);
            tempDate.add(CURRENT_AGEID);
            tempDate = forwardDays(tempDate, nTurnID - TURN_ID);
            return "" + tempDate.get(0) + " " + getMonthName((Integer)tempDate.get(1)) + " " + Game.gameAges.getYear((Integer)tempDate.get(2));
        } else {
            List<Integer> tempDate = new ArrayList();
            tempDate.add(currentDay);
            tempDate.add(currentMonth);
            tempDate.add(currentYear);
            tempDate.add(CURRENT_AGEID);
            tempDate = backwardsDays(tempDate, TURN_ID - nTurnID);
            return "" + tempDate.get(0) + " " + getMonthName((Integer)tempDate.get(1)) + " " + Game.gameAges.getYear((Integer)tempDate.get(2));
        }
    }

    public static final String getDate_ByTurnID_MessageShort(int nTurnID) {
        if (nTurnID == TURN_ID) {
            return "" + currentDay + " " + getMonthName(currentMonth).substring(0, Math.min(getMonthName(currentMonth).length(), 3));
        } else if (nTurnID > TURN_ID) {
            List<Integer> tempDate = new ArrayList();
            tempDate.add(currentDay);
            tempDate.add(currentMonth);
            tempDate.add(currentYear);
            tempDate.add(CURRENT_AGEID);
            tempDate = forwardDays(tempDate, nTurnID - TURN_ID);
            return "" + tempDate.get(0) + " " + getMonthName((Integer)tempDate.get(1)).substring(0, Math.min(getMonthName((Integer)tempDate.get(1)).length(), 3));
        } else {
            List<Integer> tempDate = new ArrayList();
            tempDate.add(currentDay);
            tempDate.add(currentMonth);
            tempDate.add(currentYear);
            tempDate.add(CURRENT_AGEID);
            tempDate = backwardsDays(tempDate, TURN_ID - nTurnID);
            return "" + tempDate.get(0) + " " + getMonthName((Integer)tempDate.get(1)).substring(0, Math.min(getMonthName((Integer)tempDate.get(1)).length(), 3));
        }
    }

    private static List<Integer> forwardDays(List<Integer> tempDate, int nTurns) {
        try {
            for(int i = 0; i < nTurns; ++i) {
                tempDate.set(0, (Integer)tempDate.get(0) + Game.gameAges.getAge_TurnDays((Integer)tempDate.get(3)));

                while((Integer)tempDate.get(0) > NUM_OF_DAYS_IN_MONTH[(Integer)tempDate.get(1) - 1]) {
                    tempDate.set(0, (Integer)tempDate.get(0) - NUM_OF_DAYS_IN_MONTH[(Integer)tempDate.get(1) - 1]);
                    tempDate.set(1, (Integer)tempDate.get(1) + 1);
                    if ((Integer)tempDate.get(1) > 12) {
                        tempDate.set(1, 1);
                        tempDate.set(2, (Integer)tempDate.get(2) + 1);
                        tempDate.set(3, Game.gameAges.getAgeOfYear((Integer)tempDate.get(2)));
                    }
                }
            }
        } catch (Exception var3) {
            tempDate.set(1, 1);
        }

        return tempDate;
    }

    private static List<Integer> backwardsDays(List<Integer> tempDate, int nTurns) {
        try {
            for(int i = 0; i < nTurns; ++i) {
                int nMinDays = Game.gameAges.getAge_TurnDays((Integer)tempDate.get(3));

                while(nMinDays > 0) {
                    if (nMinDays < (Integer)tempDate.get(0)) {
                        tempDate.set(0, (Integer)tempDate.get(0) - nMinDays);
                        break;
                    }

                    tempDate.set(1, (Integer)tempDate.get(1) - 1);
                    if ((Integer)tempDate.get(1) < 1) {
                        tempDate.set(1, 12);
                        tempDate.set(2, (Integer)tempDate.get(2) - 1);
                        tempDate.set(3, Game.gameAges.getAgeOfYear((Integer)tempDate.get(2)));
                    }

                    nMinDays -= (Integer)tempDate.get(0);
                    tempDate.set(0, NUM_OF_DAYS_IN_MONTH[(Integer)tempDate.get(1) - 1]);
                }
            }
        } catch (Exception var4) {
            tempDate.set(1, 1);
        }

        return tempDate;
    }

    public static void nextDays(int numOfDays) {
        try {
            currentDay += numOfDays;

            while(currentDay > NUM_OF_DAYS_IN_MONTH[currentMonth - 1]) {
                currentDay -= NUM_OF_DAYS_IN_MONTH[currentMonth - 1];
                if (++currentMonth > 12) {
                    currentMonth = 1;
                    ++currentYear;
                    updateAge();
                }
            }

            //ANALYTICALENGINE START
            Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
            try {
                invocable.invokeFunction("onDateChangeLogicHandler");
            } catch (ScriptException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            //ANALYTICALENGINE END
        } catch (Exception var2) {
            currentMonth = 1;
        }

    }

    public static void minusMonth() {
        try {
            --currentMonth;
            if (currentMonth < 1) {
                currentMonth = 12;
            }

            if (currentDay > NUM_OF_DAYS_IN_MONTH[currentMonth - 1]) {
                currentDay = NUM_OF_DAYS_IN_MONTH[currentMonth - 1];
            }
        } catch (Exception var1) {
            currentMonth = 1;
        }

    }

    public static void plusMonth() {
        try {
            ++currentMonth;
            if (currentMonth > 12) {
                currentMonth = 1;
            }

            if (currentDay > NUM_OF_DAYS_IN_MONTH[currentMonth - 1]) {
                currentDay = NUM_OF_DAYS_IN_MONTH[currentMonth - 1];
            }
        } catch (Exception var1) {
            currentMonth = 1;
        }

    }

    public static void addYears(int numOfYears) {
        currentYear += numOfYears;
        updateAge();
    }

    public static void updateAge() {
        updateAge(true);
    }

    public static void updateAge(boolean sendMessages) {
        int nAgeID = Game.gameAges.getAgeOfYear(currentYear);
        boolean updateAge = CURRENT_AGEID != nAgeID;
        CURRENT_AGEID = nAgeID;
        if (updateAge && Game.menuManager.getInGame()) {
            HOUR = 0;
            ButtonTopDate.updateMaxWidth();
            MapScenarios.updateUQ_UI();
            if (sendMessages) {
                InGame_Info.iCivID = Game.player.iCivID;
                InGame_Info.iCivID2 = Game.player.iCivID;
                Game.menuManager.rebuildInGame_Info(Game.lang.get("WelcomeInTheNewAge"), ((Game_Ages.Data_Ages)Game.gameAges.lAges.get(CURRENT_AGEID)).Name);
                InGame_Info.imgID = Images.infoCrown;
            }

            Game.menuManager.updateInGameFlag();
        }

        updateManpowerImg();
    }

    public static final void updateManpowerImg() {
        if (((Game_Ages.Data_Ages)Game.gameAges.lAges.get(CURRENT_AGEID)).OVER_IMAGE_ID == 0) {
            IMG_MANPOWER = Images.manpower;
            IMG_MANPOWER_SPLIT = Images.splitArmy;
            IMG_MANPOWER_MERGE = Images.mergeArmy;
            IMG_MANPOWER_REORGANIZE = Images.reorganizeArmy;
            IMG_MANPOWER_DISBAND = Images.disbandArmy;
            IMG_MANPOWER_UP = Images.manpowerUp;
            IMG_MANPOWER_TIME = Images.manpowerTime;
            IMG_TECHNOLOGY = Images.technology;
            IMG_ECONOMY = Images.economy;
            IMG_ECONOMY_UP = Images.economyUp;
            IMG_ECONOMY_DOWN = Images.economyDown;
            IMG_FORT_DEFENSE = Images.fortDefense;
        } else if (((Game_Ages.Data_Ages)Game.gameAges.lAges.get(CURRENT_AGEID)).OVER_IMAGE_ID == 1) {
            IMG_MANPOWER = Images.manpower;
            IMG_MANPOWER_SPLIT = Images.splitArmy;
            IMG_MANPOWER_MERGE = Images.mergeArmy;
            IMG_MANPOWER_REORGANIZE = Images.reorganizeArmy;
            IMG_MANPOWER_DISBAND = Images.disbandArmy;
            IMG_MANPOWER_UP = Images.manpowerUp;
            IMG_MANPOWER_TIME = Images.manpowerTime;
            IMG_TECHNOLOGY = Images.technology;
            IMG_ECONOMY = Images.economy;
            IMG_ECONOMY_UP = Images.economyUp;
            IMG_ECONOMY_DOWN = Images.economyDown;
            IMG_FORT_DEFENSE = Images.fortDefense;
        } else if (((Game_Ages.Data_Ages)Game.gameAges.lAges.get(CURRENT_AGEID)).OVER_IMAGE_ID == 2) {
            IMG_MANPOWER = Images.manpower2;
            IMG_MANPOWER_SPLIT = Images.splitArmy2;
            IMG_MANPOWER_MERGE = Images.mergeArmy2;
            IMG_MANPOWER_REORGANIZE = Images.reorganizeArmy2;
            IMG_MANPOWER_DISBAND = Images.disbandArmy2;
            IMG_MANPOWER_UP = Images.manpower2Up;
            IMG_MANPOWER_TIME = Images.manpower2Time;
            IMG_TECHNOLOGY = Images.technology;
            IMG_ECONOMY = Images.economy;
            IMG_ECONOMY_UP = Images.economyUp;
            IMG_ECONOMY_DOWN = Images.economyDown;
            IMG_FORT_DEFENSE = Images.fortDefense_2;
        } else {
            IMG_MANPOWER = Images.manpower3;
            IMG_MANPOWER_SPLIT = Images.splitArmy3;
            IMG_MANPOWER_MERGE = Images.mergeArmy3;
            IMG_MANPOWER_REORGANIZE = Images.reorganizeArmy3;
            IMG_MANPOWER_DISBAND = Images.disbandArmy3;
            IMG_MANPOWER_UP = Images.manpower3Up;
            IMG_MANPOWER_TIME = Images.manpower3Time;
            IMG_TECHNOLOGY = Images.technology2;
            IMG_ECONOMY = Images.economy2;
            IMG_ECONOMY_UP = Images.economyUp2;
            IMG_ECONOMY_DOWN = Images.economyDown2;
            IMG_FORT_DEFENSE = Images.fortDefense_2;
        }

    }
}
