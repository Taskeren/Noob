package cn.taskeren.noob;

import cn.taskeren.noob.util.NoobRandom;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

@SuppressWarnings("ConstantConditions")
public final class NoobPlugin extends JavaPlugin implements Listener {

	private static NoobRandom rand;

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);

		this.saveDefaultConfig();
		FileConfiguration config = this.getConfig();
		// 配置随机器
		long seed = config.getLong("seed");
		if(seed == -1)
			rand = new NoobRandom();
		else
			rand = new NoobRandom(seed);

		getServer().getPluginCommand("noob").setExecutor(new NoobCommand());
	}

	@Override
	public void onDisable() {
		rand = null;
	}

	public static NoobRandom random() {
		return rand;
	}

	/**
	 * 改变凋零死亡时候的凋落物
	 * @param event 死亡事件
	 */
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		if(event.getEntity() instanceof Wither) {
			int r = rand.nextInt(100);

			int drop = 0;
			// 掉落 0-2 个
			if(r > 75)
				drop++;
			if(r > 95)
				drop++;
			if(drop != 0) {
				getServer().broadcastMessage(drop+" NoobStar(s) have just droped!");
				for(int k=0; k < drop; k++) {
					event.getDrops().add(NoobItems.WITHER_STAR);
				}
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getHand() != EquipmentSlot.HAND || !event.hasBlock())
			return;
		final Player player = event.getPlayer();
		final ItemStack held = player.getInventory().getItemInMainHand();
		// 判断是否是 NOOB 下界之星
		if(held.getType() == Material.NETHER_STAR && held.getItemMeta().hasLore()) {
			final Block block = event.getClickedBlock();
			// 判断是否是箱子
			if(block.getType() == Material.CHEST) {
				final Chest chest = (Chest) block.getState();
				// 检查 NOOB！
				final ItemMeta meta = held.getItemMeta();
				boolean hasLore = false;
				for (String lore : meta.getLore()) {
					if(lore.equalsIgnoreCase("noob!")) {
						hasLore = true;
						break;
					}
				}

				if(hasLore) { // 13
					Location loc = block.getState().getLocation();
					final Inventory chestInv = chest.getBlockInventory();
					if(chestInv.isEmpty()) { // 箱子不是空的
						// 东西放进去
						// TODO: Replace Item with Random Artifacts
						ItemStack arti = NoobItems.createArtifact();
						chestInv.setItem(13, arti);
						// chestInv.setItem(13, NoobItems.WITHER_STAR);
						// 炸一下
						player.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 3,false , false);
						// 阻止打开箱子
						event.setUseInteractedBlock(Event.Result.DENY);
						// 物品减一
						if(player.getGameMode() != GameMode.CREATIVE) {
							held.setAmount(held.getAmount()-1);
						}
					}
				}
			}
		}
	}
}
