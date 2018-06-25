package gameTester;

import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Light;
import entities.Player;
import gui.GuiInventory;
import gui.GuiMainMenu;
import gui.GuiRenderer;
import gui.GuiStartMenu;
import gui.GuiTexture;
import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop
{
	public static boolean first = true;
	public static List<GuiTexture> guiMain;
	public static List<GuiTexture> guiInventory;
	public static Loader loader;
	public static GuiStartMenu startMenu;
	public static GuiInventory inventoryMenu;
	public static GuiRenderer guiRenderer;
	public static LaunchGame launch = new LaunchGame();
	public static Player player;
	
	public static void main(String[] args)
	{		
		DisplayManager.createDisplay();
		loader = new Loader();
		
		/*************************** CREATION DES MODELS TEXTURÉ ***************************/
		
		//RawModel model = OBJLoader.loadObjModel("tree", loader);
		
		//TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("tree")));
		
		ModelData bunnyData = OBJFileLoader.loadOBJ("Character Running");
		RawModel bunnyModel = loader.loadToVAO(bunnyData.getVertices(), bunnyData.getTextureCoords(), bunnyData.getNormals(), bunnyData.getIndices());
		TexturedModel stanfordBunny = new TexturedModel(bunnyModel, new ModelTexture(loader.loadTexture("white")));
		
		/*ModelData lvl1Data = OBJFileLoader.loadOBJ("lowPolyTree");
		RawModel lvl1Model = loader.loadToVAO(lvl1Data.getVertices(), bunnyData.getTextureCoords(), bunnyData.getNormals(), bunnyData.getIndices());
		TexturedModel lvl1 = new TexturedModel(lvl1Model, new ModelTexture(loader.loadTexture("lowPolyTree")));*/
		
		/*************************** CREATION DES GUI's ***************************/

		GuiMainMenu mainMenu = new GuiMainMenu();
		inventoryMenu = new GuiInventory();
		startMenu = new GuiStartMenu();
		
		List<GuiTexture> guiStart = startMenu.initStartLaunchGui(loader);
		guiMain = mainMenu.initMainMenuGui(loader);
		guiInventory = inventoryMenu.initInventory(loader);
		
		
		guiRenderer = new GuiRenderer(loader);
		
		/*************************** GÉNÉRATION DU TERRAIN ***************************/
		
		Terrain terrain = new Terrain(-1,-1,loader, new ModelTexture(loader.loadTexture("grass")), "heightMap");
		Terrain terrain2 = new Terrain(0,-1,loader, new ModelTexture(loader.loadTexture("grass")), "heightMap");
		Terrain terrain3 = new Terrain(-1,0,loader, new ModelTexture(loader.loadTexture("grass")), "heightMap");
		Terrain terrain4 = new Terrain(0,0,loader, new ModelTexture(loader.loadTexture("grass")), "heightMap");
		
		Terrain[][] terrains = new Terrain[2][2];
		terrains[0][0] = terrain;
		terrains[1][0] = terrain2;
		terrains[0][1] = terrain3;
		terrains[1][1] = terrain4;
		
		/*************************** GÉNÉRATION DU CONTENU DE LA MAP ***************************/
		
		//Entity level_1 = new Entity(lvl1, new Vector3f(0,0,-50), 0, 0, 0, 1);
		
		Light light = new Light(new Vector3f(0,100,200), new Vector3f(1,1,1)); //CREATION DE LA LUMIERE
		
		MasterRenderer renderer = new MasterRenderer(loader);
		
		/*************************** CRÉATION DU PERSONNAGE JOUEUR ***************************/
		
		player = new Player(stanfordBunny, new Vector3f(100, 0, -50), 0, 0, 0, 1);
		Camera camera = new Camera(player);
		
		/*************************** ***************************** ***************************/

		while(!Display.isCloseRequested())
		{
			if(startMenu.goMainMenu == false && first == true && mainMenu.goInGame == false)
			{
				guiRenderer.render(guiStart);
				startMenu.listenStartGuiSpaceKey();
				DisplayManager.updateDisplay();
			} else if(startMenu.goMainMenu == true && mainMenu.goInGame == false && mainMenu.doCloseGame == false)
			{
				if(mainMenu.isInGame == false)
				{
					guiRenderer.render(guiMain);
					mainMenu.mousePosition(loader);
					DisplayManager.updateDisplay();
				}
			} else if(mainMenu.goInGame == true)
			{
				launch.StartGame(loader, inventoryMenu, guiInventory, player, camera, renderer, guiRenderer, light,
				terrain, terrain2, terrain3, terrain4, terrains);
			} else if(mainMenu.doCloseGame == true)
			{
				quitGame(guiRenderer, renderer, loader);
				System.exit(0);
			}

		}
	}
	
	public static void quitGame(GuiRenderer guiRenderer, MasterRenderer renderer, Loader loader)
	{
		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
