package me.fps.simplejoin;

import me.fps.simplejoin.commands.PrincipalCommands;
import me.fps.simplejoin.events.EntryEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SimpleJoin extends JavaPlugin {
    public String ConfigRute;
    PluginDescriptionFile pdffile = getDescription();
    public String version = ChatColor.BLUE+"["+pdffile.getVersion()+ChatColor.BLUE+"]";
    public String name = ChatColor.RED+"["+pdffile.getName()+ChatColor.RED+"]";

    @Override
    public void onEnable (){

        Bukkit.getConsoleSender().sendMessage(name+" has been enabled (version: "+version+")");
        registerCommands();
        registerConfig();
        registerEvents();
    }
    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(name+" has been disabled (version: "+version+")");

    }
    public void registerCommands(){
        this.getCommand("simplejoin").setExecutor(new PrincipalCommands(this));
    }

    public void registerConfig(){
        File config = new File(this.getDataFolder(),"config.yml");
        ConfigRute = config.getPath();
        if(!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }

    }
    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new EntryEvent(this), this);
    }
}
