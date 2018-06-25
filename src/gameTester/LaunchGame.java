package gameTester;

import java.util.List;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import gui.GuiInventory;
import gui.GuiRenderer;
import gui.GuiTexture;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import terrains.Terrain;

public class LaunchGame {
	
	public void StartGame(Loader loader, GuiInventory inventoryMenu, List<GuiTexture> guiIventory, Player player, Camera camera, MasterRenderer renderer,
			GuiRenderer guiRenderer, Light light, Terrain terrain, Terrain terrain2, Terrain terrain3, Terrain terrain4, Terrain[][] terrains)
	{
		inventoryMenu.checkInputs();
		
		if(inventoryMenu.isOpen == true)
		{
			guiRenderer.render(guiIventory);
			DisplayManager.updateDisplay();

		} else
		{
			int gridX = (int) (player.getPosition().x / Terrain.SIZE + 1);
			int gridZ = (int) (player.getPosition().z / Terrain.SIZE + 1);
			player.move(terrains[gridX][gridZ]);
			//System.out.println(gridX + " " + gridZ);
			
			camera.move();
			renderer.processEntity(player);
			//renderer.processEntity(lvl);
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			renderer.processTerrain(terrain3);
			renderer.processTerrain(terrain4);
			/*for(Entity entity:entities)
				renderer.processEntity(entity);*/
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
	}
}
