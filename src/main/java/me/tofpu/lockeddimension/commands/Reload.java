package me.tofpu.lockeddimension.commands;

import me.tofpu.lockeddimension.LockedDimension;
import me.tofpu.lockeddimension.commands.handler.CommandHandler;
import me.tofpu.lockeddimension.utils.UtilsHelper;
import org.bukkit.command.CommandSender;

public class Reload extends CommandHandler {
    private final LockedDimension lockedDimension;
    public Reload(LockedDimension lockedDimension){
        this.lockedDimension = lockedDimension;
        
        super.setName("reload");
        super.setDescription("Reloads the config");
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        long start = System.currentTimeMillis();
        lockedDimension.reload();
        long end = System.currentTimeMillis();
        int time = (int) (end - start);
        sender.sendMessage(String.format(UtilsHelper.color("&8[&5Locked&dDimension&8] &dYou have successfully reloaded the &7config.yml &8(%dms)"), time));
    }
}
