package org.unix.minigames.wintertodt.tasks;

import org.data.Database;
import org.data.Methods;
import org.data.database.minigames.Wintertodt;
import org.scripter.InterruptableTask;
import org.scripter.Task;
import org.unix.minigames.wintertodt.uWT;

public class Burn extends Task {
    Wintertodt wt = Database.minigames.wintertodt();

    long lastAnimation = 0;

    @Override
    public void run() {
        if (c.inventory.populate().filter(wt.BRUMA_KINDLING).isEmpty()) {
            uWT.scriptController.setTask("Chop");
            return;
        }


        if (!c.objects.populate().filter(wt.BRAZIER_BROKEN).filter(wt.PRODUCTION).isEmpty()) {
            System.out.println("The Brazier is broken... We need to fix it!");
            uWT.scriptController.setTask("Fix");
            return;
        }

        if (!c.objects.populate().filter(wt.BRAZIER_UNLIT).filter(wt.PRODUCTION).isEmpty()) {
            Methods.intEntity(0, wt.BRAZIER_UNLIT, wt.PRODUCTION);
            c.sleepCondition(() -> c.objects.populate().filter(wt.BRAZIER_UNLIT).filter(wt.SNOWFALL_BRAZIER_CHECK).isEmpty(), 5000);
            return;
        }

        if (anim == wt.FM_ANIMATION) {
            lastAnimation = System.currentTimeMillis();
        }

        if ((System.currentTimeMillis() - lastAnimation) > 3000) {
            Methods.intEntity(0, wt.BRAZIER_LIT, wt.PRODUCTION);
        }

    }

    @Override
    public String name() {
        return "Burn";
    }
}
