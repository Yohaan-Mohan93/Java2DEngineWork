package javagames.completegame.admin;

import java.io.File;
import java.io.FileWriter;

public class ErrorLog {
	
	FileWriter fw;
	
	public boolean createErrorLog(Exception e) {
		
		try {
			fw = new FileWriter(new File("Error Log"));
			fw.write(e.getMessage());
		} catch(Exception ex) {
			try {
				fw.write(ex.getMessage());
			} catch(Exception ep) {}
			
		}
		
		
		return true;
	}
}
