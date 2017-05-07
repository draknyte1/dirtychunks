package java.dirtychunks.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

	//Create Logger
	private static final Logger modLogger = makeLogger();

	//Generate Logger
	public static Logger makeLogger(){
		final Logger chunksLogger = LogManager.getLogger("Dirty_Chunks");
		return chunksLogger;
	}

	//Comment Logging
	public static void LOG_INFO(final String s){
		modLogger.info(s);
	}

}
