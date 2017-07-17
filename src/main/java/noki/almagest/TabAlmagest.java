package noki.almagest;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import noki.almagest.ability.StarAbilityCreator;
import noki.almagest.ability.StarPropertyCreator;
import noki.almagest.ability.StarAbilityCreator.StarAbility;
import noki.almagest.ability.StarPropertyCreator.ItemStarLine;
import noki.almagest.registry.ModBlocks;

public class TabAlmagest extends CreativeTabs {
	
	//******************************//
	// define member variables.
	//******************************//
	public static String label = "Almagest";

	
	//******************************//
	// define member methods.
	//******************************//
	public TabAlmagest() {
		
		super(label);
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		
		return new ItemStack(Item.getItemFromBlock(ModBlocks.CONSTELLATION_BLOCK));

	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void displayAllRelevantItems(NonNullList<ItemStack> items) {
		
		super.displayAllRelevantItems(items);
		
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.TOP));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.BOTTOM));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.LEFT));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.RIGHT));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.TOP, ItemStarLine.BOTTOM));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.TOP, ItemStarLine.LEFT));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.TOP, ItemStarLine.RIGHT));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.BOTTOM, ItemStarLine.LEFT));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.BOTTOM, ItemStarLine.RIGHT));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.LEFT, ItemStarLine.RIGHT));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.TOP, ItemStarLine.BOTTOM, ItemStarLine.LEFT));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.TOP, ItemStarLine.BOTTOM, ItemStarLine.RIGHT));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.TOP, ItemStarLine.LEFT, ItemStarLine.RIGHT));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100, ItemStarLine.BOTTOM, ItemStarLine.LEFT, ItemStarLine.RIGHT));
		items.add(StarPropertyCreator.setProperty(new ItemStack(noki.almagest.registry.ModItems.STARDUST), 100,
				ItemStarLine.TOP, ItemStarLine.BOTTOM, ItemStarLine.LEFT, ItemStarLine.RIGHT));
		items.add(StarAbilityCreator.addAbility(new ItemStack(noki.almagest.registry.ModItems.STARDUST), StarAbility.SHINING));
		items.add(StarAbilityCreator.addAbility(new ItemStack(noki.almagest.registry.ModItems.STARDUST), StarAbility.FOOD_FULL_1));
		items.add(StarAbilityCreator.addAbility(new ItemStack(noki.almagest.registry.ModItems.STARDUST), StarAbility.AUTO_EXPLOSION));
		ItemStack addItem;
		addItem = new ItemStack(Items.DIAMOND_HELMET);
		addItem = StarAbilityCreator.addAbility2(addItem, 1, 4);
		addItem = StarAbilityCreator.addAbility2(addItem, 2, 4);
		addItem = StarAbilityCreator.addAbility2(addItem, 3, 4);
		items.add(addItem);
		addItem = new ItemStack(Items.DIAMOND_CHESTPLATE);
		addItem = StarAbilityCreator.addAbility2(addItem, 1, 4);
		addItem = StarAbilityCreator.addAbility2(addItem, 2, 4);
		addItem = StarAbilityCreator.addAbility2(addItem, 3, 4);
		items.add(addItem);
		addItem = new ItemStack(Items.DIAMOND_LEGGINGS);
		addItem = StarAbilityCreator.addAbility2(addItem, 1, 4);
		addItem = StarAbilityCreator.addAbility2(addItem, 2, 4);
		addItem = StarAbilityCreator.addAbility2(addItem, 3, 4);
		items.add(addItem);
		addItem = new ItemStack(Items.DIAMOND_BOOTS);
		addItem = StarAbilityCreator.addAbility2(addItem, 1, 4);
		addItem = StarAbilityCreator.addAbility2(addItem, 2, 4);
		addItem = StarAbilityCreator.addAbility2(addItem, 3, 4);
		items.add(addItem);
		
	}
	
}