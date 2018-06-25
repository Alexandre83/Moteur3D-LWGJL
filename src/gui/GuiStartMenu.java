package gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import gameTester.MainGameLoop;
import renderEngine.Loader;

public class GuiStartMenu
{
	public boolean goMainMenu = false;
	
	public void listenStartGuiSpaceKey()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && goMainMenu == false)
		{
			MainGameLoop.guiRenderer.cleanUp();
			MainGameLoop.guiRenderer = new GuiRenderer(MainGameLoop.loader);
			goMainMenu = true;
		}
	}
	
	public List<GuiTexture> initStartLaunchGui(Loader loader)
	{
		List<GuiTexture> guiStart = new ArrayList<GuiTexture>();
		GuiTexture guiBG = new GuiTexture(loader.loadTexture("guis/background/StartMenuBG"), new Vector2f(0, -1), new Vector2f(1, 2));
		guiStart.add(guiBG);
		
		GuiTexture guiStartPlay = new GuiTexture(loader.loadTexture("guis/buttons/startText"), new Vector2f(0.25f, -0.75f), new Vector2f(0.75f, 0.1f));
		guiStart.add(guiStartPlay);
		
		return guiStart;
	}
}
