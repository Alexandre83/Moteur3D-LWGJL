package textures;

public class ModelTexture 
{
	private int textureID;
	
	private float shineDamper = 1;
	private float reflectivity = 0;
	
	private boolean hasTransparancy = false;
	private boolean useFakeLighting = false;
	
	public boolean isUseFakeLighting() {
		return useFakeLighting;
	}

	public void setUseFakeLighting(boolean useFakeLighting) {
		this.useFakeLighting = useFakeLighting;
	}

	public float getShineDamper() {
		return shineDamper;
	}

	public boolean isHasTransparancy() {
		return hasTransparancy;
	}

	public void setHasTransparancy(boolean hasTransparancy) {
		this.hasTransparancy = hasTransparancy;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}
	
	public ModelTexture(int id)
	{
		this.textureID = id;
	}
	
	public int getID()
	{
		return this.textureID;
	}
}
