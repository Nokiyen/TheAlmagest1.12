package noki.almagest.proxy;

import noki.almagest.entity.EntityTsuchinoko;
import noki.almagest.packet.PacketHandler;
import noki.almagest.packet.PacketSyncData;
import noki.almagest.tile.TileConstellation;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;


/**********
 * @class ProxyServer
 *
 * @description サーバ用proxyクラス。
 * @description_en Proxy class for Server.
 */
public class ProxyServer implements ProxyCommon {
	
	//******************************//
	// define member variables.
	//******************************//

	
	//******************************//
	// define member methods.
	//******************************//
	@Override
	public void registerTileEntities() {
		
		GameRegistry.registerTileEntity(TileConstellation.class, "TileConstellation");
				
	}
	
	@Override
	public void registerEntities() {
		
	}
	
	@Override
	public void registerRenderers() {
		
	}
	
	@Override
	public void registerPre() {
		
	}
	
	@Override
	public boolean hasPlanisphere() {
		
		return false;
		
	}
	
	@Override
	public void log(String message, Object... data) {
		
	}
	
	@Override
	public EntityPlayer getPlayer() {
		
		return null;
		
	}
	
	@Override
	public void syncData(NBTTagCompound nbt) {
		
//		PacketHandler.instance.sendToAll(new PacketSyncData(nbt));
		
	}
	
	@Override
	public void registerFixers() {
		
		EntityLiving.registerFixesMob(FMLCommonHandler.instance().getMinecraftServerInstance().getDataFixer(), EntityTsuchinoko.class);
		
	}

}
