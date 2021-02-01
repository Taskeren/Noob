package cn.taskeren.noob;

import cn.taskeren.noob.util.AttributesGenerator;
import cn.taskeren.noob.util.WeightedRandom;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

import static cn.taskeren.noob.NoobConst.*;

@SuppressWarnings({"ConstantConditions", "WeakerAccess"})
public class NoobItems {

	private static WeightedRandom<Material> wr;

	public static final ItemStack WITHER_STAR = new ItemStack(Material.NETHER_STAR);

	public static final List<Material> REWARD_POOL = Lists.newArrayList(
			Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD,
			Material.DIAMOND_SWORD, Material.NETHERITE_SWORD,
			Material.WOODEN_AXE, Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL, Material.WOODEN_HOE,
			Material.STONE_AXE, Material.STONE_PICKAXE, Material.STONE_SHOVEL, Material.STONE_HOE,
			Material.IRON_AXE, Material.IRON_PICKAXE, Material.IRON_SHOVEL, Material.IRON_HOE,
			Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL, Material.DIAMOND_HOE,
			Material.NETHERITE_AXE, Material.NETHERITE_PICKAXE, Material.NETHERITE_SHOVEL, Material.NETHERITE_HOE,
			Material.FLINT_AND_STEEL, Material.SHEARS,
			Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
			Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS,
			Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
			Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
			Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS,
			Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS,
			Material.TURTLE_HELMET,Material.ELYTRA, Material.SHIELD, Material.TRIDENT, Material.CROSSBOW, Material.FISHING_ROD
	);

	static {
		final ItemMeta meta = WITHER_STAR.getItemMeta();
		meta.setLore(Lists.newArrayList("NOOB!"));
		WITHER_STAR.setItemMeta(meta);
	}

	/**
	 * @return 随机产生一个支持的物品
	 */
	static Material getRandomMaterial() {
		if(wr == null) {
			wr = new WeightedRandom<>();
			REWARD_POOL.forEach(m->{

				// 只有工具和武器
				if(isWooden(m)) {
					wr.add(1, m);
				}
				else if(isStone(m)) {
					wr.add(1, m);
				}

				// 工具武器护甲
				else if(isIron(m)) {
					if (isArmor(m)) {
						wr.add(5, m);
					}
					else if (isSword(m)) {
						wr.add(4, m);
					}
					else {
						wr.add(6, m);
					}
				}
				else if(isGolden(m)) {
					if (isArmor(m)) {
						wr.add(6, m);
					}
					else if(isSword(m)) {
						wr.add(5, m);
					}
					else {
						wr.add(7, m);
					}
				}
				else if(isDiamond(m)) {
					if (isArmor(m)) {
						wr.add(4, m);
					}
					else if(isSword(m)) {
						wr.add(3, m);
					}
					else {
						wr.add(5, m);
					}
				}
				else if(isNetherite(m)) {
					if (isArmor(m)) {
						wr.add(1, m);
					}
					else {
						wr.add(2, m);
					}
				}

				// 只有护甲
				else if(isLeather(m)) {
					wr.add(4, m);
				}
				else if(isChainmail(m)) {
					wr.add(3, m);
				}
			});
		}

		return wr.next();
	}

	private static void addLore(ItemMeta meta, String...lores) {
		List<String> loreList;
		if(meta.hasLore()) {
			loreList = meta.getLore();
		} else {
			loreList = Lists.newArrayList();
		}
		loreList.addAll(Arrays.asList(lores));
		meta.setLore(loreList);
	}

	public static ItemStack createArtifact() {
		return createArtifact(new ItemStack(getRandomMaterial()));
	}

	public static ItemStack createArtifact(ItemStack item) {
		final ItemMeta meta = item.getItemMeta();

		// 随机附魔
		// TODO: Add Enchants
		//meta.addEnchant(Enchantment.DURABILITY, 10, true);

		// 随机属性
		final Material material = item.getType();
		final AttributesGenerator ag = new AttributesGenerator();
		ag.next(material);

		meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, ag.getKnockbackResistance(material));
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, ag.getMovementSpeed(material));
		meta.addAttributeModifier(Attribute.GENERIC_LUCK, ag.getLuck(material));
		if(isToolOrSword(material)) {
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, ag.getAttackSpeed());
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, ag.getAttackDamage());
		}
		else if(isArmor(material)) {
			meta.addAttributeModifier(Attribute.GENERIC_ARMOR, ag.getArmor(material));
			meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, ag.getMaxHealthModifier(material));
		}

		// 结尾
		addLore(meta, "Noob Artifacts™");
		item.setItemMeta(meta);
		return item;
	}

}
