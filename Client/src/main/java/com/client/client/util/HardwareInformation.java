package com.client.client.util;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

/**
 * 
 * @author Nick Hartskeerl <apachenick@hotmail.com>
 *
 */
@SuppressWarnings("deprecation")
public class HardwareInformation {
	
	private static SystemInfo systemInfo = new SystemInfo();
	
	private static HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
	
	private static CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
	
	public static String serial = "undefined";
	
	public static String getSerial() {
		return serial;
	}
	
	static {
		try {
			serial = centralProcessor.getSystemSerialNumber();
		} catch(Throwable t) {
			
		}
	}
	
}
