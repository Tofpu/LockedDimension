package me.tofpu.lockeddimension.commands.manager;

import me.tofpu.lockeddimension.LockedDimension;
import me.tofpu.lockeddimension.commands.handler.CommandHandler;
import me.tofpu.lockeddimension.utils.UtilsHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {
    private final LockedDimension lockedDimension;
    public final static List<CommandHandler> commands = new ArrayList<>();
    
    public CommandManager(LockedDimension lockedDimension) {
        this.lockedDimension = lockedDimension;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("lockeddimension.help")){
            sender.sendMessage(UtilsHelper.color("&cYou do not have permission to execute this command."));
            return true;
        }
        for(CommandHandler handler : commands){
            if (args.length != 0 && handler.getName().equals(args[0])){
                if (sender.hasPermission(handler.getPermission())){
                    handler.onCommand(sender, args);
                } else {
                    sender.sendMessage(UtilsHelper.color("&cYou do not have permission to execute this command."));
                }
                return true;
            }
        }
        String header = UtilsHelper.color(String.format("&8&m-&d&m-&8&m--|&r &5Locked&dDimension&8 &5&lV&d%s\n&8&m--|&r &dCommands:\n&8&m----|\n", lockedDimension.getDescription().getVersion()));
        sender.sendMessage(header);
        commands.forEach(commandHandler -> sender.sendMessage(UtilsHelper.color("&8&m-|&r &8/&5lockeddimension &d" + commandHandler.getName() + " &8- &d" + commandHandler.getDescription())));
        String footer = UtilsHelper.color("&8&m----|&r\n&8&m--|&r &5&lMade &dby Tofpu \n&8&m-&d&m-&8&m--|&r &dStay safe and be careful out there!");
        sender.sendMessage(footer);
        
        return false;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // credits to nicuch for this, much love <3
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for(CommandHandler handler : CommandManager.commands){
                if (sender.hasPermission(handler.getPermission())){
                    commands.add(handler.getName());
                }
            }
            StringUtil.copyPartialMatches(args[0], commands, completions);
        } else if (args.length == 2){
            for(CommandHandler handler : CommandManager.commands){
                if (sender.hasPermission(handler.getPermission()) && args[0].equalsIgnoreCase(handler.getName())){
                    List<String> list = handler.onTabComplete();
                    if (list != null){
                        commands = list;
                    }
                }
            }
            StringUtil.copyPartialMatches(args[1], commands, completions);
        }
        
        Collections.sort(completions);
        return completions;
    }
}
