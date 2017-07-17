package noki.almagest.item;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import noki.almagest.AlmagestData;
import noki.almagest.ability.StarPropertyCreator;
import noki.almagest.attribute.EStarAttribute;
import noki.almagest.attribute.ItemWithAttribute;
import noki.almagest.helper.HelperNBTStack;
import noki.almagest.recipe.StarRecipe;
import noki.almagest.registry.IWithRecipe;
import noki.almagest.world.PlaniTeleporter;


/**********
 * @class ItemPlanisphere
 *
 * @description 右クリックで星座版世界に行ける重要なアイテムです。また、手に持っていると星座線が見えます。
 */
public class ItemPlanisphere extends ItemWithAttribute implements IWithRecipe {
	
	//******************************//
	// define member variables.
	//******************************//
	private static final String NBT_dimensionID = "dimensionID";
	private static final String NBT_posX = "posX";
	private static final String NBT_posY = "posY";
	private static final String NBT_posZ = "posZ";
	
	
	//******************************//
	// define member methods.
	//******************************//
	public ItemPlanisphere() {
		
		this.setAttributeLevel(EStarAttribute.STAR, 20);
		
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		
		if(world.isRemote) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
		}
		
		EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
		HelperNBTStack helper = new HelperNBTStack(player.getHeldItem(hand));
		// teleport from Atlas
		if(world.provider.getDimension() == AlmagestData.dimensionID) {
			// in case that the last position is memorized.
			if(helper.hasChild()) {
				int dimensionID = helper.getInteger(NBT_dimensionID);
				int posX = helper.getInteger(NBT_posX);
				int posY = helper.getInteger(NBT_posY);
				int posZ = helper.getInteger(NBT_posZ);
				teleportPlayer(dimensionID, (double)posX+0.5D, (double)posY, (double)posZ+0.5D, entityPlayerMP);
			}
			// otherwise.
			else {
				teleportPlayer(0, entityPlayerMP);
			}			
		}
		// teleport from others.
		else {
			helper.setInteger(NBT_dimensionID, world.provider.getDimension());
			helper.setInteger(NBT_posX, (int)entityPlayerMP.posX);
			helper.setInteger(NBT_posY, (int)entityPlayerMP.posY);
			helper.setInteger(NBT_posZ, (int)entityPlayerMP.posZ);
			
			teleportPlayer(AlmagestData.dimensionID, entityPlayerMP);
//			player.travelToDimension(AlmagestData.dimensionID);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		
	}
	
	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		
		if(StarPropertyCreator.getMemory(stack) > 999.0D/6.0D*3) {
			return true;
		}
		return false;
		
	}
	
	@Override
	@Nullable
	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		
		return new EntityItem(world, location.posX, location.posY, location.posZ, itemstack){
			@Override
			public boolean attackEntityFrom(DamageSource source, float amount) {
				if(source.isFireDamage() || source.isExplosion()) {
					return false;
				}
				return super.attackEntityFrom(source, amount);
			}
		};
		
	}
	
	@Override
	public List<IRecipe> getRecipe() {
		
		return this.makeRecipeList(
				new StarRecipe(new ItemStack(this))
				.setAttribute(EStarAttribute.STAR, 10).setStack(new ItemStack(Items.PAPER)));

	}
	
	
	//----------
	//Static Method.
	//----------
	public static void teleportPlayer(int dimensionID, EntityPlayerMP player) {
		
		BlockPos pos = player.mcServer.getWorld(dimensionID).getSpawnPoint();
		teleportPlayer(dimensionID, pos.getX()+0.5D, pos.getY(), pos.getZ()+0.5D, player);		
		
	}
	
	public static void teleportPlayer(int dimensionID, double posX, double posY, double posZ, EntityPlayerMP player) {
		
		PlayerList playerList = player.mcServer.getPlayerList();
		WorldServer worldServer = player.mcServer.getWorld(AlmagestData.dimensionID);
		
		// travel to dimension.
//		player.forceSpawn = true;
		playerList.transferPlayerToDimension(player, dimensionID, new PlaniTeleporter(worldServer));
		player.connection.setPlayerLocation(posX, posY, posZ, player.rotationYaw, player.rotationPitch);
		
	}
	
}
