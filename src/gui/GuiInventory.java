package gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import renderEngine.Loader;

public class GuiInventory
{
	public boolean isOpen = false;
	
	public List<GuiTexture> initInventory(Loader loader)
	{
		List<GuiTexture> inventory = new ArrayList<GuiTexture>();
		GuiTexture guiInventory = new GuiTexture(loader.loadTexture("guis/inventory"), new Vector2f(0.75f, -0.25f), new Vector2f(0.40f, 0.8f));
		inventory.add(guiInventory);
		return inventory;
	}
	
	public void checkInputs()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_I))
		{
			isOpen = true;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && isOpen == true)
		{
			isOpen = false;
		}
	}
}
