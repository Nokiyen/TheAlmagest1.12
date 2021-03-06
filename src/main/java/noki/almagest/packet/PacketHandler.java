package noki.almagest.packet;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import noki.almagest.ModInfo;

public class PacketHandler {
	
	public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.ID);
	
	public static void registerPre() {
		
		instance.registerMessage(PacketUpdateMessageHandler.class, PacketUpdateMessage.class, 1, Side.CLIENT);
		instance.registerMessage(PacketUpdateBookrestHandler.class, PacketUpdateBookrest.class, 2, Side.SERVER);
		instance.registerMessage(PacketUpdateMiraHandler.class, PacketUpdateMira.class, 3, Side.SERVER);
		instance.registerMessage(PacketSyncDataHandler.class, PacketSyncData.class, 4, Side.CLIENT);
		
	}

}
