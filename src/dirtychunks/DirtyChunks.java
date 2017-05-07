package dirtychunks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import dirtychunks.utils.Utils;
import scala.reflect.runtime.ReflectionUtils;

@Mod(modid=CORE.MODID, name=CORE.NAME, version=CORE.VERSION, dependencies="required-after:Forge;")
public class DirtyChunks
implements ActionListener
{

	@Mod.Instance(CORE.MODID)
	public static DirtyChunks instance;

	//@SidedProxy(clientSide="gtPlusPlus.core.proxy.ClientProxy", serverSide="gtPlusPlus.core.proxy.ServerProxy")
	//public static CommonProxy proxy;

	//Pre-Init
	@Mod.EventHandler
	public void preInit(final FMLPreInitializationEvent event)
	{
		Utils.LOG_INFO("Loading "+CORE.NAME+" - V"+CORE.VERSION);

		//HANDLER_GT.mMaterialProperties = new GT_Config(new Configuration(new File(new File(event.getModConfigurationDirectory(), "GTplusplus"), "MaterialProperties.cfg")));
		proxy.preInit(event);
	}

	//Init
	@Mod.EventHandler
	public void init(final FMLInitializationEvent event)
	{
		proxy.init(event);
		
		Utils.LOG_INFO("[Proxy] Calling Entity registrator.");
		proxy.registerEntities();
		Utils.LOG_INFO("[Proxy] Calling Tile Entity registrator.");
		proxy.registerTileEntities();
		Utils.LOG_INFO("[Proxy] Calling Render registrator.");
		proxy.registerRenderThings();
		
		proxy.registerNetworkStuff();
		
		//Register Dimension A
		ModBiomes.registerWithBiomeDictionary();
		Dimension.registerWorldProvider();
		Dimension.registerDimensions();
		WorldTypesTutorial.addCustomWorldTypes();
		
		
	}

	//Post-Init
	@Mod.EventHandler
	public void postInit(final FMLPostInitializationEvent event) {
		proxy.postInit(event);

		if (DEBUG){
			this.dumpGtRecipeMap(Gregtech_Recipe_Map.sChemicalDehydratorRecipes);
			this.dumpGtRecipeMap(Gregtech_Recipe_Map.sCokeOvenRecipes);
			this.dumpGtRecipeMap(Gregtech_Recipe_Map.sMatterFab2Recipes);
			this.dumpGtRecipeMap(Gregtech_Recipe_Map.sAlloyBlastSmelterRecipes);
		}

		//~
		ReflectionUtils.becauseIWorkHard();

		//Utils.LOG_INFO("Activating GT OreDictionary Handler, this can take some time.");
		Utils.LOG_INFO("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Utils.LOG_INFO("| Recipes succesfully Loaded: "+RegistrationHandler.recipesSuccess+" | Failed: "+RegistrationHandler.recipesFailed + " |");
		Utils.LOG_INFO("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		//Meta_GT_Proxy.activateOreDictHandler();
		Utils.LOG_INFO("Finally, we are finished. Have some cripsy bacon as a reward.");
	}

	@EventHandler
	public void serverStarting(final FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandMath());
	}

	@Mod.EventHandler
	public void serverStopping(final FMLServerStoppingEvent event)
	{

	}

	@Override
	public void actionPerformed(final ActionEvent arg0) {

	}

	protected void dumpGtRecipeMap(final GT_Recipe_Map r){
		final Collection<GT_Recipe> x = r.mRecipeList;
		Utils.LOG_INFO("Dumping "+r.mUnlocalizedName+" Recipes for Debug.");
		for(final GT_Recipe newBo : x){
			Utils.LOG_INFO("========================");
			Utils.LOG_INFO("Dumping Input: "+ItemUtils.getArrayStackNames(newBo.mInputs));
			Utils.LOG_INFO("Dumping Inputs "+ItemUtils.getFluidArrayStackNames(newBo.mFluidInputs));
			Utils.LOG_INFO("Dumping Duration: "+newBo.mDuration);
			Utils.LOG_INFO("Dumping EU/t: "+newBo.mEUt);
			Utils.LOG_INFO("Dumping Output: "+ItemUtils.getArrayStackNames(newBo.mOutputs));
			Utils.LOG_INFO("Dumping Output: "+ItemUtils.getFluidArrayStackNames(newBo.mFluidOutputs));
			Utils.LOG_INFO("========================");
		}
	}

}
