package noki.almagest.attribute;


/**********
 * @class EStarProperty
 *
 * @description アイテムの属性のenumです。
 */
public enum EStarAttribute {
	
	FOOD("almagest.property.food.name"),
	LIQUID("almagest.property.liqiud.name"),
	PLANT("almagest.property.plant.name"),
	ANIMAL("almagest.property.animal.name"),
	STAR("almagest.property.star.name"),
	MINERAL("almagest.property.mineral.name"),
	ARTIFACT("almagest.property.artifact.name");
	
	
	//******************************//
	// define member variables.
	//******************************//
	private String name;
	
	
	//******************************//
	// define member methods.
	//******************************//
	private EStarAttribute(String name) {
		
		this.name = name;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}

}
