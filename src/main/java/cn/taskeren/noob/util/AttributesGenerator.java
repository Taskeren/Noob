package cn.taskeren.noob.util;

import cn.taskeren.noob.NoobPlugin;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static cn.taskeren.noob.NoobConst.*;

public class AttributesGenerator {

	public static final List<Attribute> GENERAL_ATTRIBUTE = Lists.newArrayList(
			Attribute.GENERIC_KNOCKBACK_RESISTANCE, // 抗击退值 [0]-1
			Attribute.GENERIC_MOVEMENT_SPEED, // 移动速度 [0.1]
			Attribute.GENERIC_LUCK // 幸运 -1024-[0]-1024
	);

	public static final List<Attribute> TOOL_AND_SWORLD_ATTRIBUTES = Lists.newArrayList(
			Attribute.GENERIC_ATTACK_SPEED, // 攻击速度 0-[4]-1024
			Attribute.GENERIC_ATTACK_DAMAGE // 攻击伤害 0-[1]-2048

	);

	public static final List<Attribute> ARMOR_ATTRIBUTES = Lists.newArrayList(
			Attribute.GENERIC_ARMOR, // 护甲防御点数（图标） [0]-20
			Attribute.GENERIC_MAX_HEALTH // 最大生命值 2-[20]
	);

	private int[] attrs = new int[] {0, 0, 0, 0, 0};

	private boolean toolOrSword, armor;

	public void next(Material material) {
		this.toolOrSword = isTool(material) || isSword(material);
		this.armor = isArmor(material);
		final NoobRandom rand = NoobPlugin.random();

		// 随机增加 3-10 次属性
		for(int k=0; k<rand.nextInt(8)+3; k++) {
			int ind = rand.nextInt(attrs.length);
			attrs[ind] += rand.nextInt(4)+2; // 增加 2-5 点
		}

		System.out.println(Arrays.toString(attrs));
	}

	public AttributeModifier getKnockbackResistance(Material m) { // +1 击退抗性 = 0.1 Knockback Resistance
		final int point = Math.min(6, attrs[0]);
		final double amount = 0.05 * point;
		if(isHelmet(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
		else if(isChestplate(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
		else if(isLeggings(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
		else if(isBoots(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
		else
			return new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
	}

	public AttributeModifier getMovementSpeed(Material m) {
		final int point = attrs[1];
		final double amount = point * 0.05;
		if(isHelmet(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.movement_speed", amount, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.HEAD);
		else if(isChestplate(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.movement_speed", amount, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.CHEST);
		else if(isLeggings(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.movement_speed", amount, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.LEGS);
		else if(isBoots(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.movement_speed", amount, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.FEET);
		else
			return new AttributeModifier(UUID.randomUUID(), "generic.movement_speed", amount, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.HAND);
	}

	public AttributeModifier getLuck(Material m) {
		final double amount = attrs[2];
		if(isHelmet(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.luck", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
		else if(isChestplate(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.luck", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
		else if(isLeggings(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.luck", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
		else if(isBoots(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.luck", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
		else
			return new AttributeModifier(UUID.randomUUID(), "generic.luck", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
	}

	public AttributeModifier getAttackSpeed() {
		final double amount = attrs[3] * 0.2;
		if(toolOrSword)
			return new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
		else
			throw new IllegalArgumentException("Tool/Sword only");
	}

	public AttributeModifier getAttackDamage() {
		final double amount = attrs[4] * 2;
		if(toolOrSword)
			return new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
		else
			throw new IllegalArgumentException("Tools/Swords only");
	}

	public AttributeModifier getArmor(Material m) {
		final double amount = Math.min(10, attrs[3]);
		if(isHelmet(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.armor", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
		else if(isChestplate(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.armor", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
		else if(isLeggings(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.armor", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
		else if(isBoots(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.armor", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
		else
			throw new IllegalArgumentException("Armors only");
	}

	public AttributeModifier getMaxHealthModifier(Material m) {
		final int point = Math.min(10, attrs[4]);
		final double amount = 0.1 * point; // (0.2*point[10MAX])*base[20]=40
		if(isHelmet(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.max_health", amount, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.HEAD);
		else if(isChestplate(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.max_health", amount, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.CHEST);
		else if(isLeggings(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.max_health", amount, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.LEGS);
		else if(isBoots(m))
			return new AttributeModifier(UUID.randomUUID(), "generic.max_health", amount, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlot.FEET);
		else
			throw new IllegalArgumentException("Armors Only");
	}

}
