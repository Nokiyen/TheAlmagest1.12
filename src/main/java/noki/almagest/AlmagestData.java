package noki.almagest;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome.BiomeProperties;
import noki.almagest.entity.EntityMira;
import noki.almagest.entity.EntityTsuchinoko;
import noki.almagest.event.EventConstellation;
import noki.almagest.event.EventFishing;
import noki.almagest.event.EventProperty;
import noki.almagest.event.EventToolTip;
import noki.almagest.event.EventUseItem;
import noki.almagest.gui.GuiHandler;
import noki.almagest.registry.AlmagestRegistry;
import noki.almagest.registry.JsonRegistry;
import noki.almagest.registry.ModBlocks;
import noki.almagest.registry.ModItems;
import noki.almagest.registry.RecipeRegistry;
import noki.almagest.world.PlaniBiome;
import noki.almagest.world.PlaniWorldProvider;
import scala.reflect.internal.Trees.New;


/**********
 * @class AlmagestData
 *
 * @description このModの各種データの格納、登録をするクラスです。
 */
public class AlmagestData {
	
	
	//******************************//
	// define member variables.
	//******************************//
	//--------------------
	// Creative Tab.
	//--------------------
	public static CreativeTabs tab;
	
	//--------------------
	// Tags of NBT.
	//--------------------
	public static final String NBT_prefix = "8fcd6c65_Almagest";
	
	//--------------------
	// Dimension.
	//--------------------
	public static int dimensionID;
	public static DimensionType dimensionType;
	
	//--------------------
	// GUI.
	//--------------------
	public static int guiID_almagest = 0;
	public static int guiID_bookrest = 1;
	public static int guiID_mira = 2;
	
	
	//******************************//
	// define member methods.
	//******************************//
	public static void registerPre() {
		
		// creative tab.
		tab = new TabAlmagest();
		
		// register.
		ModBlocks.register();
		ModItems.register();
		JsonRegistry.register();
		
		// world.
		dimensionID = 88;
		dimensionType = DimensionType.register("Planisphere", "_planisphere", dimensionID, PlaniWorldProvider.class, false);
		ForgeRegistries.BIOMES.register(
				new PlaniBiome(new BiomeProperties("Planisphere").setRainDisabled()).setRegistryName(ModInfo.ID.toLowerCase(), "planisphere"));
		DimensionManager.registerDimension(dimensionID, dimensionType);

	}
	
	public static void register() {
		
		//recipe.
		RecipeRegistry.register();
		AlmagestRegistry.register();
		
		//json.
//		JsonRegistry.register();
		
		//gui.
		NetworkRegistry.INSTANCE.registerGuiHandler(AlmagestCore.instance, new GuiHandler());
		
		//event.
		MinecraftForge.EVENT_BUS.register(new EventConstellation() );
		MinecraftForge.EVENT_BUS.register(new EventToolTip());
		MinecraftForge.EVENT_BUS.register(new EventUseItem());
		MinecraftForge.EVENT_BUS.register(new EventProperty());
		MinecraftForge.EVENT_BUS.register(new EventFishing());
		
		//entity.
		EntityRegistry.registerModEntity(new ResourceLocation("almagest.entity.mira"),
				EntityMira.class, "Mira", 0, AlmagestCore.instance, 250, 1, false, 0xAAAAAA, 0xCCCCCC);
		EntityRegistry.registerModEntity(new ResourceLocation("almagest.entity.tsuchinoko"),
				EntityTsuchinoko.class, "Tsuchinoko", 1, AlmagestCore.instance, 250, 1, false, 0xDDDDDD, 0xEEEEEE);
//		EntityRegistry.addSpawn(EntityMira.class, 20, 1, 4, EnumCreatureType.CREATURE, Biomes.PLAINS);
		
	}
	
}
