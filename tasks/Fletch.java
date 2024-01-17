package org.unix.minigames.wintertodt.tasks;

import org.data.Database;
import org.data.database.minigames.Wintertodt;
import org.scripter.InterruptableTask;
import org.unix.minigames.wintertodt.uWT;
import simple.hooks.wrappers.SimpleItem;

import static org.data.Database.items;

public class Fletch extends InterruptableTask {
    @Override
    public boolean interruptCondition() {
        //TODO - check for snowfall on my .loc() - Move out of the way
        return false;
    }

    @Override
    public void interruptAction() {

    }

    @Override
    public void run() {
        if (c.inventory.populate().filter(Database.minigames.wintertodt().BRUMA_ROOT).isEmpty()) {
            uWT.scriptController.setTask("Burn");
            return;
        }

        if (p.isAnimating()) {
            c.sleep(500);
            return;
        }

        SimpleItem br = c.inventory.populate().filter(Database.minigames.wintertodt().BRUMA_ROOT).next();
        SimpleItem kn = c.inventory.populate().filter(items().KNIFE).next();

        kn.click(0);
        c.sleep(200);
        br.click(0);

        c.sleepCondition(() -> c.inventory.populate().filter(Database.minigames.wintertodt().BRUMA_ROOT).isEmpty(), 5000);

    }

    @Override
    public String name() {
        return "Fletch";
    }
}
