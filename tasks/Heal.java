package org.unix.minigames.wintertodt.tasks;

import org.data.Database;
import org.scripter.Task;
import org.unix.minigames.wintertodt.uWT;
import simple.robot.api.ClientContext;

public class Heal extends Task {
    @Override
    public void run() {
        if (c.inventory.populate().filter(Database.food().TROUT).isEmpty()) {
            c.magic.castHomeTeleport();
            c.sleep(1000);
            System.out.println("STOPPING SCRIPT! WE ARE OUT OF FOOD AND GONNA DIE!!");
            ClientContext.instance().stopScript();
        }
    }

    @Override
    public String name() {
        return null;
    }
}
