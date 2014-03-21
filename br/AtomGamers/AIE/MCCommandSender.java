package br.AtomGamers.AIE;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MCCommandSender extends InsurePlayer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String str, String[] args) {

        if (!(cs instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("§b[ArmorInsure] §cComando desabilitado no Console!");
            return true;
        }

        Player sender = (Player) cs;

        if (cmd.getName().equalsIgnoreCase("insure")) {
            if (args.length >= 0) {
                if (sender.hasPermission("armorinsure.use")) {
                    if (EconomyCore.getBalance(sender.getName()) < getInsurePrice()) {
                        sender.sendMessage("§b[ArmorInsure] §cVocê não tem dinheiro suficiente.");
                    } else {
                        if (playerIsInsured(sender.getName())) {
                            sender.sendMessage("§b[ArmorInsure] §fVocê já usou o Insure!");
                        } else {
                            sender.sendMessage("§b[ArmorInsure] §fVocê utilizou o Insure, Agora quando você morrer na próxima vez, você não perderá sua Armadura.");
                            EconomyCore.withdrawPlayer(sender.getName(), getInsurePrice());
                            setInsure(sender, true);
                        }
                    }
                } else {
                    sender.sendMessage("§b[ArmorInsure] §cVocê não tem permissão.");
                }
            }
        }

        return false;
    }
}
