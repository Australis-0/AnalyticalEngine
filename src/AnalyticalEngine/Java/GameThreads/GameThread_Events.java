//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski.GameThreads;

import aoc.kingdoms.lukasz.events.EventsManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class GameThread_Events extends Thread {
    public boolean running = true;
    public int iLastUpdateTurnID = 0;
    public long timeSleep;
    public static int THREAD_TURN_ID = 0;

    public GameThread_Events() {
    }

    public void run() {
        while(this.running) {
            try {
                try {
                    this.timeSleep = System.currentTimeMillis();
                    if (Game.menuManager.getInGame() && Game_Calendar.TURN_ID != this.iLastUpdateTurnID) {
                        ++THREAD_TURN_ID;
                        EventsManager.runEvents(THREAD_TURN_ID);
                        EventsManager.runEvents_Global(THREAD_TURN_ID);
                        Game.player.updateEvents();
                        this.iLastUpdateTurnID = Game_Calendar.TURN_ID;
                    }
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }

                try {
                    Thread.sleep(Math.max(1L, 10L - (System.currentTimeMillis() - this.timeSleep)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

    }
}
