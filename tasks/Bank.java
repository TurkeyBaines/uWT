package org.unix.minigames.wintertodt.tasks;

import org.data.Database;
import org.data.Methods;
import org.data.database.minigames.Wintertodt;
import org.scripter.Task;
import org.unix.minigames.wintertodt.uWT;
import simple.hooks.wrappers.SimpleWidget;

public class Bank extends Task {

    Wintertodt wt = Database.minigames.wintertodt();

    public void run() {
        SimpleWidget w = c.widgets.getWidget(wt.HEALTH_WIDGET[0], wt.HEALTH_WIDGET[1]);
        if (w != null) {
            if (!w.getText().toLowerCase().contains("wintertodt's energy: 0%")) {
                uWT.scriptController.setTask("Cut");
                return;
            }
        }

        if (!c.inventory.populate().filter(wt.REWARDS_CRATE).isEmpty()) {
            if (wt.PRODUCTION.containsPoint(loc)) {
                if (!wt.SAFE_WC_SPOT.equals(loc)) {
                    c.pathing.step(wt.SAFE_WC_SPOT);
                    c.sleep(500);
                    return;
                }

                c.pathing.step(wt.WALK_TO_LEAVE);
                c.sleep(1000);
            } else if (wt.LOBBY.containsPoint(loc)) {
                if (c.widgets.getWidget(219, 1) != null) {
                    c.widgets.getWidget(219, 1).getChild(1).click(0);
                    c.sleep(1000);
                    return;
                }

                if (wt.WALK_TO_LEAVE.equals(loc)) {
                    Methods.intEntity(0, wt.DOOR, 2000);
                } else {
                    c.pathing.step(wt.WALK_TO_LEAVE);
                    c.sleep(1000);
                }
            } else if (wt.OUTSIDE_AREA.containsPoint(loc)) {
                c.pathing.step(wt.BANK_AREA.getCenterPoint());
                c.sleep(1000);
            } else if (wt.BANK_AREA.containsPoint(loc)) {
                if (!c.bank.bankOpen()) {
                    Methods.intEntity(0, wt.BANK_CHEST, 1000);
                } else {
                    c.inventory.populate().filter(wt.REWARDS_CRATE).next().click(0);
                    c.sleep(1000);
                    return;
                }
            }
        } else {
            if (wt.BANK_AREA.containsPoint(loc)) {
                c.pathing.step(wt.WALK_TO_ENTER);
                c.sleep(1000);
            } else if (wt.OUTSIDE_AREA.containsPoint(loc)) {
                if (wt.WALK_TO_ENTER.equals(loc)) {
                    Methods.intEntity(0, wt.DOOR, 2000);
                } else {
                    c.pathing.step(wt.WALK_TO_ENTER);
                    c.sleep(2000);
                }
            } else if (wt.LOBBY.containsPoint(loc)) {
                c.pathing.step(wt.SAFE_WC_SPOT);
                c.sleep(1000);
            } else if (wt.PRODUCTION.containsPoint(loc)) {
                c.sleep(1000);
            }
        }
    }

    @Override
    public String name() {
        return "Bank";
    }
}
