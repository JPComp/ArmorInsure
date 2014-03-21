package br.AtomGamers.AIE;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.PlayerInventory;

public class InsureEvent extends InsurePlayer implements Listener {

    private final HashMap<Player, PlayerInventory> items_old = new HashMap<Player, PlayerInventory>();

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onDeathInsure(PlayerDeathEvent e) {
        Player sender = (Player) e.getEntity();
        if (playerIsInsured(sender.getName())) {
            PlayerInventory i = sender.getInventory();
            items_old.put(sender, i);
            devolver.add(sender.getName());
            i.clear();
            e.getDrops().clear();
            sender.sendMessage("§b[ArmorInsure] §fVocê morreu, Mas sua armadura continua intacta!");
            sender.sendMessage("§b[ArmorInsure] §fPara recuperá-la, basta você dar Renascer!");
            sender.sendMessage("§b[ArmorInsure] §fSe você desconectar, sua armadura não voltará mais!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onRespawnInsure(PlayerRespawnEvent e) {
        Player sender = (Player) e.getPlayer();
        if (devolver.contains(sender.getName())) {
            sender.getInventory().setHelmet(items_old.get(sender).getHelmet());
            sender.getInventory().setChestplate(items_old.get(sender).getChestplate());
            sender.getInventory().setLeggings(items_old.get(sender).getLeggings());
            sender.getInventory().setBoots(items_old.get(sender).getBoots());
            devolver.remove(sender.getName());
            setInsure(sender, false);
            items_old.remove(sender);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onLogoutReset(PlayerQuitEvent e) {
        Player sender = (Player) e.getPlayer();
        if (playerIsInsured(sender.getName())) {
            if (devolver.contains(sender.getName())) {
                devolver.remove(sender.getName());
            }
            if (items_old.containsKey(sender)) {
                items_old.remove(sender);
            }
            setInsure(sender, false);
        }
    }
}
