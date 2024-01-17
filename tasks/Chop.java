package org.unix.minigames.wintertodt.tasks;

import org.data.Database;
import org.data.Methods;
import org.data.database.minigames.Wintertodt;
import org.scripter.InterruptableTask;
import org.unix.minigames.wintertodt.uWT;

public class Chop extends InterruptableTask {
    @Override
    public boolean interruptCondition() {
        //TODO - check for snowfall on my .loc() - Move out of the way
        return false;
    }

    @Override
    public void interruptAction() {
        uWT.scriptController.setTask("Dodge");
    }

    @Override
    public void run() {
        if (c.inventory.populate().size() == 28) {
            uWT.scriptController.setTask("Fletch");
            return;
        }

        if (!loc.equals(Database.minigames.wintertodt().SAFE_WC_SPOT)) {
            c.pathing.step(Database.minigames.wintertodt().SAFE_WC_SPOT);
            c.sleep(1000);
            return;
        }

        if (p.isAnimating()) {
            c.sleep(500);
            return;
        }

        Methods.intEntity(0, Database.minigames.wintertodt().BRUMA_TREE, "Chop");
    }

    @Override
    public String name() {
        return "Chop";
    }
}
