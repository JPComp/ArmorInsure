package br.AtomGamers.AIE;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ArmorInsure extends JavaPlugin {

    @Override
    public void onEnable() {
        Plugin vault = Bukkit.getPluginManager().getPlugin("Vault");
        if (vault != null) {
            //TODO: Criar um Hook para o Vault.
        } else {
            Bukkit.getPluginManager().disablePlugin(this);
        }
        getServer().getPluginManager().registerEvents(new InsureEvent(), this);
        getCommand("insure").setExecutor(new MCCommandSender());
        File file = new File(getDataFolder(), "config_template.yml");
        if (!file.exists()) {
            try {
                saveResource("config_template.yml", false);
                new File(getDataFolder(), "config_template.yml").renameTo(new File(getDataFolder(), "config.yml"));
            } catch (Exception ex) {
                Bukkit.getConsoleSender().sendMessage("§4[ArmorInsure] §f" + ex.getStackTrace());
            }
        }
        reloadConfig();
        Bukkit.getConsoleSender().sendMessage("§b[ArmorInsure] §fPlugin inicializado. (Autor=AtomGamers)");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§b[ArmorInsure] §fPlugin desabilitado. (Autor=AtomGamers)");
    }

}
