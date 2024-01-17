package org.unix.minigames.wintertodt.tasks;

import org.data.Database;
import org.data.Methods;
import org.data.database.minigames.Wintertodt;
import org.scripter.InterruptableTask;
import org.unix.minigames.wintertodt.uWT;
import simple.hooks.wrappers.SimpleObject;

public class Fix extends InterruptableTask {

    Wintertodt wt = Database.minigames.wintertodt();

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
        if (c.objects.populate().filter(wt.BRAZIER_BROKEN).filter(wt.PRODUCTION).isEmpty()) {
            System.out.println("The Brazier is fixed!!");
            uWT.scriptController.setTask("Burn");
            return;
        }

        Methods.intEntity(0, wt.BRAZIER_BROKEN, wt.PRODUCTION);
        c.sleepCondition(() -> c.objects.populate().filter(wt.BRAZIER_BROKEN).filter(wt.PRODUCTION).isEmpty(), 5000);
        System.out.println("Clicking the Brazier to fix it!");

    }

    @Override
    public String name() {
        return "<Fix> Fixing the Brazier!";
    }
}
