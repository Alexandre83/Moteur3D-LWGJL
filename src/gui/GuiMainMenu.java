package gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.Rectangle;
import org.lwjgl.util.vector.Vector2f;

import gameTester.MainGameLoop;
import renderEngine.Loader;

public class GuiMainMenu
{
	public boolean goInGame = false;
	public boolean isInGame = false;
	public boolean doCloseGame = false;
	
	private Rectangle rec = new Rectangle(1490, 270, 360, 60);
	private Rectangle rec2 = new Rectangle(1490, 375, 360, 60);
	private Rectangle rec3 = new Rectangle(1490, 60, 360, 60);
	
	public List<GuiTexture> initMainMenuGui(Loader loader)
	{
		List<GuiTexture> guiMain = new ArrayList<GuiTexture>();
		GuiTexture guiBG = new GuiTexture(loader.loadTexture("guis/background/MainMenuBG"), new Vector2f(0, -1), new Vector2f(1, 2));
		guiMain.add(guiBG);
		
		GuiTexture guiPlayBtn = new GuiTexture(loader.loadTexture("guis/buttons/PlayBTN_wh"), new Vector2f(0.85f, -0.25f), new Vector2f(0.35f, 0.075f));
		guiMain.add(guiPlayBtn);
		
		GuiTexture guiOptionsBTN = new GuiTexture(loader.loadTexture("guis/buttons/OptionsBTN_wh"), new Vector2f(0.85f, -0.45f), new Vector2f(0.35f, 0.075f));
		guiMain.add(guiOptionsBTN);
		
		GuiTexture guiQuitBTN = new GuiTexture(loader.loadTexture("guis/buttons/QuitBTN_wh"), new Vector2f(0.85f, -0.85f), new Vector2f(0.35f, 0.075f));
		guiMain.add(guiQuitBTN);
		
		return guiMain;
	}
	
	public void mousePosition(Loader loader)
	{
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
		if(Mouse.isInsideWindow() && rec.contains(mouseX, mouseY))
		{
			//System.out.println("options");
			GuiTexture guiOptionsBTN = new GuiTexture(loader.loadTexture("guis/buttons/OptionsBTN_b"), new Vector2f(0.85f, -0.45f), new Vector2f(0.35f, 0.075f));
			MainGameLoop.guiMain.remove(2);
			MainGameLoop.guiMain.add(2, guiOptionsBTN);
		}else if(rec.contains(mouseX, mouseY) == false)
		{
			GuiTexture guiOptionsBTN = new GuiTexture(loader.loadTexture("guis/buttons/OptionsBTN_wh"), new Vector2f(0.85f, -0.45f), new Vector2f(0.35f, 0.075f));
			MainGameLoop.guiMain.remove(2);
			MainGameLoop.guiMain.add(2, guiOptionsBTN);
		}
		if (Mouse.isInsideWindow() && rec2.contains(mouseX, mouseY))
		{
			//System.out.println("play");
			GuiTexture guiPlayBtn = new GuiTexture(loader.loadTexture("guis/buttons/PlayBTN_b"), new Vector2f(0.85f, -0.25f), new Vector2f(0.35f, 0.075f));
			MainGameLoop.guiMain.remove(1);
			MainGameLoop.guiMain.add(1, guiPlayBtn);
		} else if(rec2.contains(mouseX, mouseY) == false){
			GuiTexture guiPlayBtn = new GuiTexture(loader.loadTexture("guis/buttons/PlayBTN_wh"), new Vector2f(0.85f, -0.25f), new Vector2f(0.35f, 0.075f));
			MainGameLoop.guiMain.remove(1);
			MainGameLoop.guiMain.add(1, guiPlayBtn);
		}
		if (Mouse.isButtonDown(0) && rec2.contains(mouseX, mouseY))
		{
			MainGameLoop.guiRenderer.cleanUp();
			MainGameLoop.guiRenderer = new GuiRenderer(MainGameLoop.loader);
			goInGame = true;
			MainGameLoop.startMenu.goMainMenu = false;
		}
		if(Mouse.isInsideWindow() && rec3.contains(mouseX, mouseY))
		{
			//System.out.println("quit");
			GuiTexture guiQuitBTN = new GuiTexture(loader.loadTexture("guis/buttons/QuitBTN_b"), new Vector2f(0.85f, -0.85f), new Vector2f(0.35f, 0.075f));
			MainGameLoop.guiMain.remove(3);
			MainGameLoop.guiMain.add(3, guiQuitBTN);
		}
		else if(rec3.contains(mouseX, mouseY) == false)
		{
			GuiTexture guiQuitBTN = new GuiTexture(loader.loadTexture("guis/buttons/QuitBTN_wh"), new Vector2f(0.85f, -0.85f), new Vector2f(0.35f, 0.075f));
			MainGameLoop.guiMain.remove(3);
			MainGameLoop.guiMain.add(3, guiQuitBTN);
		}
		if(Mouse.isButtonDown(0) && rec3.contains(mouseX, mouseY))
		{
			doCloseGame = true;
		}
		
	}
}
