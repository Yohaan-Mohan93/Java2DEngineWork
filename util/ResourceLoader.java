package javagames.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceLoader {
	
	public static InputStream load(Class<?> clazz, String filePath, String resPath) {
		InputStream in = null;
		if(!(resPath == null || resPath.isEmpty())) {
			in = clazz.getResourceAsStream(resPath);
		}
		if(in == null) {
			try {
				in = new FileInputStream(filePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return in;
	}

}
