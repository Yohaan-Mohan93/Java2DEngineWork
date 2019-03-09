package javagames.text;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javagames.util.*;

public class UtilityDrawStringExample extends SimpleFramework {
	
	public UtilityDrawStringExample() {
		appFont = new Font("Courier New", Font.BOLD, 48);
		appWidth = 640;
		appHeight = 640;
		appSleep = 10L;
		appTitle = "Utility Draw String Example";
		appBackground = Color.WHITE;
		appFPSColor = Color.BLACK;
	}
	
	@Override
	protected void processInput(float delta) {
		super.processInput(delta);
		
		if(keyboard.keyDownOnce(KeyEvent.VK_UP)) {
			int fontSize = appFont.getSize();
			appFont = new Font(appFont.getFamily(), appFont.getStyle(), fontSize + 2);
		}
		if(keyboard.keyDownOnce(KeyEvent.VK_DOWN)) {
			int fontSize = appFont.getSize();
			appFont = new Font(appFont.getFamily(), appFont.getStyle(), fontSize - 2);
		}
	}
	
}
