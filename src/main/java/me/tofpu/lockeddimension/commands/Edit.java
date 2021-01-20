package me.tofpu.lockeddimension.commands;

import me.tofpu.lockeddimension.commands.handler.CommandHandler;
import me.tofpu.lockeddimension.gui.MainGui;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Edit extends CommandHandler {
    public Edit(){
        super.setName("edit");
        super.setDescription("edit the config");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(sender instanceof Player))
            return;

        ((Player) sender).openInventory(MainGui.getGui().getInventory());
//        sender.sendMessage(String.format(UtilsHelper.color("&8[&5Locked&dDimension&8] &dYou have successfully reloaded the &5config.yml&d! (%d&5ms&d)"), time));
    }
}
