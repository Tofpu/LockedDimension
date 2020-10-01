package me.tofpu.lockeddimension.commands.module;

import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandHandler {
    private String name = null;
    private String permission = "";
    private String description = "";
    private int tabArgs = 1;
    
    public void onCommand(CommandSender sender, String[] args){
    
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    public String getPermission() {
        return permission;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void register(){
        CommandManager.commands.add(this);
    }
    
    public void setTabArgs(int tabArgs) {
        this.tabArgs = tabArgs;
    }
    
    public int getTabArgs() {
        return tabArgs;
    }
    
    public List<String> onTabComplete() {
        return null;
    }
}
