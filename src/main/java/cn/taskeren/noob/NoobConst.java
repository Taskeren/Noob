package cn.taskeren.noob;

import org.bukkit.Material;

@SuppressWarnings("WeakerAccess")
public class NoobConst {

	public static boolean isAxe(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isAxe">
			case WOODEN_AXE:
			case STONE_AXE:
			case IRON_AXE:
			case GOLDEN_AXE:
			case DIAMOND_AXE:
			case NETHERITE_AXE:
				// </editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isHoe(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isHoe">
			case WOODEN_HOE:
			case STONE_HOE:
			case IRON_HOE:
			case GOLDEN_HOE:
			case DIAMOND_HOE:
			case NETHERITE_HOE:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isPickaxe(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isPickaxe">
			case WOODEN_PICKAXE:
			case STONE_PICKAXE:
			case IRON_PICKAXE:
			case GOLDEN_PICKAXE:
			case DIAMOND_PICKAXE:
			case NETHERITE_PICKAXE:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isShovel(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isShovel">
			case WOODEN_SHOVEL:
			case STONE_SHOVEL:
			case IRON_SHOVEL:
			case GOLDEN_SHOVEL:
			case DIAMOND_SHOVEL:
			case NETHERITE_SHOVEL:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isSword(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isSword">
			case WOODEN_SWORD:
			case STONE_SWORD:
			case IRON_SWORD:
			case GOLDEN_SWORD:
			case DIAMOND_SWORD:
			case NETHERITE_SWORD:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isHelmet(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isHelmet">
			case IRON_HELMET:
			case GOLDEN_HELMET:
			case DIAMOND_HELMET:
			case NETHERITE_HELMET:
			case LEATHER_HELMET:
			case CHAINMAIL_HELMET:
			case TURTLE_HELMET:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isChestplate(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isChestplate">
			case IRON_CHESTPLATE:
			case GOLDEN_CHESTPLATE:
			case DIAMOND_CHESTPLATE:
			case NETHERITE_CHESTPLATE:
			case LEATHER_CHESTPLATE:
			case CHAINMAIL_CHESTPLATE:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isLeggings(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isLeggings">
			case IRON_LEGGINGS:
			case GOLDEN_LEGGINGS:
			case DIAMOND_LEGGINGS:
			case NETHERITE_LEGGINGS:
			case LEATHER_LEGGINGS:
			case CHAINMAIL_LEGGINGS:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isBoots(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isBoots">
			case IRON_BOOTS:
			case GOLDEN_BOOTS:
			case DIAMOND_BOOTS:
			case NETHERITE_BOOTS:
			case LEATHER_BOOTS:
			case CHAINMAIL_BOOTS:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isTool(Material material) {
		return isAxe(material) || isHoe(material) || isPickaxe(material) || isShovel(material);
	}

	public static boolean isArmor(Material material) {
		return isHelmet(material) || isChestplate(material) || isLeggings(material) || isBoots(material);
	}

	public static boolean isToolOrSword(Material material) {
		return isTool(material) || isSword(material);
	}

	public static boolean isWooden(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isWooden">
			case WOODEN_AXE:
			case WOODEN_HOE:
			case WOODEN_SHOVEL:
			case WOODEN_SWORD:
			case WOODEN_PICKAXE:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isStone(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isStone">
			case STONE_SWORD:
			case STONE_SHOVEL:
			case STONE_PICKAXE:
			case STONE_HOE:
			case STONE_AXE:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isIron(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isIron">
			case IRON_BOOTS:
			case IRON_LEGGINGS:
			case IRON_CHESTPLATE:
			case IRON_HELMET:
			case IRON_SWORD:
			case IRON_SHOVEL:
			case IRON_PICKAXE:
			case IRON_HOE:
			case IRON_AXE:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isGolden(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isGolden">
			case GOLDEN_BOOTS:
			case GOLDEN_LEGGINGS:
			case GOLDEN_CHESTPLATE:
			case GOLDEN_HELMET:
			case GOLDEN_SWORD:
			case GOLDEN_SHOVEL:
			case GOLDEN_PICKAXE:
			case GOLDEN_HOE:
			case GOLDEN_AXE:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isDiamond(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isDiamond">
			case DIAMOND_BOOTS:
			case DIAMOND_LEGGINGS:
			case DIAMOND_CHESTPLATE:
			case DIAMOND_HELMET:
			case DIAMOND_SWORD:
			case DIAMOND_SHOVEL:
			case DIAMOND_PICKAXE:
			case DIAMOND_HOE:
			case DIAMOND_AXE:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isNetherite(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isNetherite">
			case NETHERITE_BOOTS:
			case NETHERITE_LEGGINGS:
			case NETHERITE_CHESTPLATE:
			case NETHERITE_HELMET:
			case NETHERITE_SWORD:
			case NETHERITE_SHOVEL:
			case NETHERITE_PICKAXE:
			case NETHERITE_HOE:
			case NETHERITE_AXE:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isLeather(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isLeather">
			case LEATHER_BOOTS:
			case LEATHER_LEGGINGS:
			case LEATHER_CHESTPLATE:
			case LEATHER_HELMET:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

	public static boolean isChainmail(Material material) {
		switch (material) {
			//<editor-fold defaultstate="collapsed" desc="isChainmail">
			case CHAINMAIL_BOOTS:
			case CHAINMAIL_LEGGINGS:
			case CHAINMAIL_CHESTPLATE:
			case CHAINMAIL_HELMET:
				//</editor-fold>
				return true;
			default:
				return false;
		}
	}

}
