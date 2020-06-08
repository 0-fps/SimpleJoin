package me.fps.simplejoin.events;

import com.connorlinfoot.titleapi.TitleAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import me.fps.simplejoin.SimpleJoin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;



public class EntryEvent implements Listener {
    private SimpleJoin plugin;

    public  EntryEvent (SimpleJoin plugin){
        this.plugin = plugin;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayersJoins (PlayerJoinEvent event){
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("Config.JoinMessages");
        if(section != null) {
            for (String messages : config.getConfigurationSection("Config.JoinMessages").getKeys(false)){
                if(messages != null) {
                String message  = config.getString("Config.JoinMessages."+ messages +".Join-Message");
                String permission  = config.getString("Config.JoinMessages."+ messages +".Permission");
                String title = config.getString("Config.JoinMessages."+messages + ".Title");
                String subtitle = config.getString("Config.JoinMessages."+messages + ".SubTitle");
                int time = config.getInt("Config.JoinMessages."+messages + ".Time");
                    if (player.hasPermission(permission)) {
                        String vaultprefix = "%vault_prefix%";
                        vaultprefix = PlaceholderAPI.setPlaceholders(player.getPlayer(), vaultprefix);
                        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message).replace("%player%", player.getDisplayName() + "").replace("%vault_prefix%", vaultprefix + ""));
                        TitleAPI.sendTitle(player,5 , time , 5, ChatColor.translateAlternateColorCodes('&', title).replace("%player%", player.getDisplayName() + "").replace("%vault_prefix%", vaultprefix + ""), (ChatColor.translateAlternateColorCodes('&', subtitle).replace("%player%", player.getDisplayName() + "").replace("%vault_prefix%", vaultprefix + "")));
                    }
                }
            }
            }

        }
}




