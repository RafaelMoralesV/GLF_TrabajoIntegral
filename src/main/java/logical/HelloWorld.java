package logical;

import org.apache.logging.log4j.*;
public class HelloWorld {
 
    // Define a static logger variable so that it references the
    // Logger instance named "MyApp".
    private static final Logger LOGGER = LogManager.getLogger(HelloWorld.class);
 
    public static void main(final String... args) {
 
        // Set up a simple configuration that logs on the console.
    	LOGGER.trace("Trace");
    	LOGGER.debug("Debug");
    	LOGGER.info("Info");
    	LOGGER.warn("Warn");
    	LOGGER.error("Error");
    	LOGGER.fatal("Fatal");
    	ScannerTxt.main(args);
    }
}