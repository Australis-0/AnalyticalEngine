//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map.army;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;

import javax.script.Invocable;
import javax.script.ScriptException;

public class ArmyRecruit {
    public int provinceID;
    public int unitID;
    public int armyID;
    public int timeLeft;
    public int cost;
    public String toArmyKey = null;
    public String armyKey = "";

    public ArmyRecruit() {
    }

    public ArmyRecruit(int iProvinceID, int iUnitID, int iArmyID, String assignToArmyKey) {
        this.provinceID = iProvinceID;
        this.unitID = iUnitID;
        this.armyID = iArmyID;
        this.toArmyKey = assignToArmyKey;
        this.armyKey = CFG.extraRandomTag();

        //ANALYTICALENGINE START
        parseOnArmyRecruitmentHandler();
        //ANALYTICALENGINE END
    }

    //ANALYTICALENGINE START
    public void parseOnArmyRecruitmentHandler () {
        try {
            Invocable invocable = (Invocable) AnalyticalEngine.AnalyticalEngine.nashorn;
            try {
                invocable.invokeFunction("parseOnArmyRecruitment", this);
            } catch (ScriptException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {}
    }
    //ANALYTICALENGINE END
}
