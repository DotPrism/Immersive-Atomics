package com.magneticprism.immersiveatomics.common.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

/**
 * @author DotPrism
 * @since 06.06.2024
 */
public class IALogger
{
	public static boolean debug = false;
	public static Logger logger;

	public static void log(Level logLevel, Object object)
	{
		logger.log(logLevel, String.valueOf(object));
	}

	public static void error(Object object)
	{
		log(Level.ERROR, object);
	}

	public static void info(Object object)
	{
		log(Level.INFO, object);
	}

	public static void warn(Object object)
	{
		log(Level.WARN, object);
	}

	public static void error(String message, Object... params)
	{
		logger.log(Level.ERROR, message, params);
	}

	public static void info(String message, Object... params)
	{
		logger.log(Level.INFO, message, params);
	}

	public static void warn(String message, Object... params)
	{
		logger.log(Level.WARN, message, params);
	}
}
