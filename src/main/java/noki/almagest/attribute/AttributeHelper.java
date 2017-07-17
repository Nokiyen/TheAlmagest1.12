package noki.almagest.attribute;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;


/**********
 * @class AttributeHelper
 *
 * @description star attributeを取り出すためのクラスです。
 * バニラ等のブロック・アイテムのattributeも管理します。
 */
public class AttributeHelper {
	
	
	//******************************//
	// define member variables.
	//******************************//
	private static Map<Item, AttributeSet> vanillaAttributes = new HashMap<Item, AttributeSet>();
	
	
	//******************************//
	// define member methods.
	//******************************//
	public static void register() {
		
	}
	
	public static int getAttrributeLevel(ItemStack stack, EStarAttribute attribute) {
		
		Item item = stack.getItem();
		if(item == null) {
			return 0;
		}
		
		if(item instanceof IWithAttribute) {
			return ((IWithAttribute)item).getAttributeLevel(attribute, stack);
		}
		
		if(item instanceof ItemBlock) {
			Block block = Block.getBlockFromItem(item);
			if(block instanceof IWithAttribute) {
				return ((IWithAttribute)block).getAttributeLevel(attribute, stack);
			}
		}
		
		AttributeSet set = vanillaAttributes.get(stack.getItem());
		if(set == null) {
			return 0;
		}
		
		return set.getAttribute(attribute);
		
	}
	
	private class AttributeSet {
		
		private Map<EStarAttribute, Integer> attributes = new HashMap<>();
		
		public AttributeSet SetAttributes(EStarAttribute attribute, Integer level) {
			
			this.attributes.put(attribute, level);
			return this;
			
		}
		
		public int getAttribute(EStarAttribute attribute) {
			
			Integer level = this.attributes.get(attribute);
			if(level == null) {
				return 0;
			}
			return level;
			
		}
		
	}

}
