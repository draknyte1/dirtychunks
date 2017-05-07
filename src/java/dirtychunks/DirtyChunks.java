package java.dirtychunks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.dirtychunks.proxy.CommonProxy;
import java.dirtychunks.utils.Utils;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

@Mod(modid=CORE.MODID, name=CORE.NAME, version=CORE.VERSION, dependencies="required-after:Forge;")
public class DirtyChunks
implements ActionListener
{

	@Mod.Instance(CORE.MODID)
	public static DirtyChunks instance;

	@SidedProxy(clientSide="dirtychunks.proxy.CommonProxy", serverSide="dirtychunks.proxy.ServerProxy")
	public static CommonProxy proxy;

	//Pre-Init
	@Mod.EventHandler
	public void preInit(final FMLPreInitializationEvent event)
	{
		Utils.LOG_INFO("Loading "+CORE.NAME+" - V"+CORE.VERSION);
		proxy.preInit(event);
	}

	//Init
	@Mod.EventHandler
	public void init(final FMLInitializationEvent event)
	{
		proxy.init(event);		
		proxy.registerNetworkStuff();		
	}

	//Post-Init
	@Mod.EventHandler
	public void postInit(final FMLPostInitializationEvent event) {
		proxy.postInit(event);
		Utils.LOG_INFO("Finished loading "+CORE.NAME);
	}

	@EventHandler
	public void serverStarting(final FMLServerStartingEvent event)
	{

	}

	@Mod.EventHandler
	public void serverStopping(final FMLServerStoppingEvent event)
	{

	}

	@Override
	public void actionPerformed(final ActionEvent arg0) {

	}
}
