import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Kiss László
 *
 *         9-es csoport
 *
 */
public class CreateHTML {

	public static void processDirs() {
		for (Map.Entry<String, ArrayList<File>> entry : Main.dirsAndPhotos.entrySet()) {
//			System.out.println("Feldogozás: "+entry.getKey());
			index(entry.getKey());
		}

	}

	public static void processPhotos() {
		String photo = "";
		String name;
		for (Map.Entry<String, ArrayList<File>> entry : Main.dirsAndPhotos.entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i++) {
				photo = entry.getValue().get(i).toString();
				String parent = entry.getValue().get(i).getParent();
				int lastDot = photo.lastIndexOf('.');
				if (lastDot != -1) {
						name = photo.substring(0, lastDot + 1);
						createHTML(photo, name, parent);
				}
			}
		}
	}

	private static void createHTML(String realName, String name, String parent) {
		String htmlName = name + "html";
		File file = new File(htmlName);
		try {
			file.createNewFile();
			HTMLstring.photoHTML(realName, htmlName, parent);
		} catch (IOException e) {
			System.out.println("A html file-t nem sikerült legenerálni");
			e.printStackTrace();
		}
	}

	private static void index(String dir) {
		File file = new File(dir + "/index.html");
		File other = new File(file.getParent().toString()) ;
//		if(Main.BeginDir.equals(dir)) {
//			Main.Start = file.toString();
//		}
		try {
			file.createNewFile();
			HTMLstring.indexHTML(file.toString(),dir,other.getParent());
		} catch (IOException e) {
			System.out.println("Nem sikerült az index.html elkészítése");
			e.printStackTrace();
		}
	}

}
