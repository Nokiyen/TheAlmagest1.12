package noki.almagest.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import noki.almagest.ability.StarAbilityCreator;
import noki.almagest.ability.StarPropertyCreator;
import noki.almagest.ability.StarPropertyCreator.ItemStarLine;
import noki.almagest.attribute.AttributeHelper;
import noki.almagest.attribute.EStarAttribute;


/**********
 * @class EventToolTip
 *
 * @description item stackのpropertyを表示するためのイベントです。
 * @description_en
 */
public class EventToolTip {
	
	//******************************//
	// define member variables.
	//******************************//
	
	
	//******************************//
	// define member methods.
	//******************************//
	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		
		if(event.getItemStack() == null) {
			return;
		}
		
		if(event.getToolTip().size() != 0) {
			event.getToolTip().add("");
		}
		
		boolean hasAttribute = false;
		for(EStarAttribute attribute: EStarAttribute.values()) {
			int level = AttributeHelper.getAttrributeLevel(event.getItemStack(), attribute);
			if(level != 0) {
				event.getToolTip().add(
						new TextComponentTranslation(attribute.getName()).getFormattedText()+":"+Integer.toString(level));
				hasAttribute = true;
			}
		}
		
		if(event.getToolTip().size() != 0 && hasAttribute == true) {
			event.getToolTip().add("");
		}
		
		event.getToolTip().add(new TextComponentTranslation("almagest.property.memory.name").getFormattedText()
				+":"+Integer.toString(StarPropertyCreator.getMemory(event.getItemStack())));
		
		ArrayList<ItemStarLine> list = StarPropertyCreator.getLines(event.getItemStack());
//		if(list.isEmpty() == false) {
		if(true) {
			if(list.contains(ItemStarLine.TOP)) {
				event.getToolTip().add(" □■□ ");
			}
			else {
				event.getToolTip().add(" □ □ □ ");
			}
			
			if(list.contains(ItemStarLine.LEFT) && list.contains(ItemStarLine.RIGHT)) {
				event.getToolTip().add("■□■");
			}
			else if(list.contains(ItemStarLine.LEFT)) {
				event.getToolTip().add("■□ □ ");
			}
			else if(list.contains(ItemStarLine.RIGHT)) {
				event.getToolTip().add(" □ □■");
			}
			else {
				event.getToolTip().add(" □ □ □ ");
			}
			
			if(list.contains(ItemStarLine.BOTTOM)) {
				event.getToolTip().add(" □■□ ");
			}
			else {
				event.getToolTip().add(" □ □ □ ");
			}
		}
		
/*		ArrayList<StarAbility> abilities = StarAbilityCreator.getAbilities(event.getItemStack());
		for(StarAbility each: abilities) {
			event.getToolTip().add(each.getLocalizedName());
		}*/
		HashMap<Integer, ArrayList<Integer>> abilities = StarAbilityCreator.getAbility2(event.getItemStack());
		if(event.getToolTip().size() != 0 && abilities.size() != 0) {
			event.getToolTip().add("");
		}
		for(Map.Entry<Integer, ArrayList<Integer>> eachAbilitiy: abilities.entrySet()) {
			for(Integer level: eachAbilitiy.getValue()) {
				event.getToolTip().add(new TextComponentTranslation("almagest.ability."+eachAbilitiy.getKey()+"."+level+".name").getFormattedText());
			}
		}
/*		NBTTagCompound abilityTag = new HelperNBTStack(event.getItemStack()).getTag("abilities");
		for(String eachId: abilityTag.getKeySet()) {
			for(int eachLevel: abilityTag.getIntArray(eachId)) {
				event.getToolTip().add(new TextComponentTranslation("almagest.ability."+eachId+"."+eachLevel+".name").getFormattedText());
			}
		}*/
		
	}

}
