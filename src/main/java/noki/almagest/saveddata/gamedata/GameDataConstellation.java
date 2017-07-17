package noki.almagest.saveddata.gamedata;

import net.minecraft.util.ResourceLocation;
import noki.almagest.helper.HelperConstellation.Constellation;


public class GameDataConstellation extends GameData {
	
	private Constellation constellation;
	
	public GameDataConstellation(Constellation constellation) {
		
		this.constellation = constellation;
		this.name = new ResourceLocation("almagest", "constellation."+this.constellation.getId());
		
	}
	
	public Constellation getConstellation() {
		
		return this.constellation;
		
	}
	
	@Override
	public String getDisplay() {
		
		return this.constellation.getDisplay();
		
	}

}
