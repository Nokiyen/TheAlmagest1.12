package noki.almagest.saveddata.gamedata;

import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public enum ERandomTalkMira {
	
	TALK001(3),
	TALK002(3),
	TALK003(3),
	TALK004(3),
	TALK005(3),
	TALK006(3),
	TALK007(3),
	TALK008(3),
	TALK009(3),
	TALK010(3);
	
	
	private int talkEnd;
	private ResourceLocation resource;
	private ERandomTalkMira(int talkEnd) {
		this.talkEnd = talkEnd;
		this.resource = new ResourceLocation("almagest.talk.mira.random."+this.toString().toLowerCase());
	}
	public ResourceLocation getResource() {
		return this.resource;
	}
	public String getLocalTalk(int talkId) {
		return new TextComponentTranslation(this.resource.getResourcePath()+"."+talkId).getFormattedText();
	}
	public int getTalkEnd(){
		return this.talkEnd;
	}
	
	public static ERandomTalkMira getRandomTalk() {
		int target = new Random().nextInt(ERandomTalkMira.values().length);
		return ERandomTalkMira.values()[target];
	}
	
}
