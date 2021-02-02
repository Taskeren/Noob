package cn.taskeren.noob;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NoobCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
			if(args.length < 1) {
				sender.sendMessage("/noob <player>");
				return true;
			} else {
				final Player player = sender.getServer().getPlayer(args[0]);
				if(player == null) {
					sender.sendMessage("Unable to find "+args[0]+".");
					return true;
				}
				else {
					nooblize(player);
					return true;
				}
			}
		}
		else if(sender instanceof Player) {
			nooblize((Player) sender);
			return true;
		}
		return false;
	}

	private static void nooblize(Player player) {
		final ItemStack item = player.getInventory().getItemInMainHand();
		final ItemStack artifact = NoobItems.createArtifact(item);
		player.getInventory().setItemInMainHand(artifact);
	}

}
