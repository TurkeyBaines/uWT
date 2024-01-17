package org.unix.minigames.wintertodt;

import org.data.tracker.XPTracker;
import org.scripter.ScriptController;
import org.unix.minigames.wintertodt.tasks.*;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.robot.script.Script;

import java.awt.*;

@ScriptManifest(servers = {"OSRSPS"}, description = "a", version = "1", author = "unix", discord = "", category = Category.FIREMAKING, name = "uWT")
public class uWT extends Script {

    public static ScriptController scriptController;
    public XPTracker xpTracker;
    public int gamesCompleted;

    @Override
    public void onExecute() {
        scriptController = new ScriptController();
        scriptController.addTask("Chop", new Chop());
        scriptController.addTask("Fletch", new Fletch());
        scriptController.addTask("Burn", new Burn());
        scriptController.addTask("Fix", new Fix());
        scriptController.addTask("Bank", new Bank());
        scriptController.setTask("Chop");

        xpTracker = new XPTracker();
        xpTracker.init();
    }

    @Override
    public void onProcess() {
        scriptController.runTask();
    }

    @Override
    public void onTerminate() {
        xpTracker.end();
    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {
        if (chatMessage.getMessage().contains("Your subdued Wintertodt count is:")) {
            scriptController.setTask("Bank");
        }
    }

    @Override
    public void paint(Graphics graphics) {

    }
}
