package dirtychunks.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

	public CommonProxy(){
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}

	public void preInit(final FMLPreInitializationEvent e) {

	}

	public void init(final FMLInitializationEvent e) {

	}

	public void postInit(final FMLPostInitializationEvent e) {

	}


	public void serverStarting(final FMLServerStartingEvent e)
	{

	}

	public void registerNetworkStuff(){

	}

}
