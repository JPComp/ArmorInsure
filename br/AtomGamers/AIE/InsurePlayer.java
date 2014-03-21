package br.AtomGamers.AIE;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class InsurePlayer {
    
    protected static List<String> devolver = new ArrayList<String>();
    protected static List<Player> players = new ArrayList<Player>();
    protected static Economy EconomyCore = null;
    
    protected static FileConfiguration getConfig() {
        File folder = Bukkit.getPluginManager().getPlugin("ArmorInsure").getDataFolder();
        File file = new File(folder, "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config;
    }
    
    public static Player getInsurePlayer(String name) {
        Player pl = Bukkit.getPlayer(name);
        return pl;
    }
    
    public static boolean playerIsInsured(String name) {
        return players.contains(getInsurePlayer(name));
    }
    
    public static void setInsure(Player e, boolean result) {
        if ((result) == true) {
            players.add(e);
        } else {
            players.remove(e);
        }
    }
    
    public static int getInsurePrice() {
        return getConfig().getInt("Price");
    }
}
