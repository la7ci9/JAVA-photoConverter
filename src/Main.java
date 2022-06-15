import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kiss László
 *
 *         9-es csoport
 *
 */
public class Main {
	public static String Start;
	public static String BeginDir;

	public static Map<String, ArrayList<File>> dirsAndPhotos = new HashMap<String, ArrayList<File>>();
	private static ArrayList<File> directorys = new ArrayList<File>();

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Adjon meg egy mappát elérési útként.");

			System.exit(0);
		}
		BeginDir = args[0];
		File file = new File(BeginDir);
		if (!file.isDirectory()) {
			System.out.println("Nem mappát adott meg.");
			System.exit(0);
		}
		Start = BeginDir+"\\index.html";
		File[] listOfFiles = file.listFiles();
		folderProcessing(listOfFiles);
		CreateHTML.processDirs();
		CreateHTML.processPhotos();
		System.out.println("A program lefutott.");
	}

	private static void folderProcessing(File[] listOfFiles) {
		dirsAndPhotos.put(listOfFiles[0].getParent(), new ArrayList<File>());
		if (directorys.size() != 0) {
			directorys.remove(0);
		}
		for (File fl : listOfFiles) {
			if (fl.isDirectory()) {
				directorys.add(fl);
			}
			if (dirsAndPhotos.containsKey(fl.getParent())) {
				String extension = ".jpg.jpeg.png.gif";
				String name = fl.toString();
				int lastDot = name.lastIndexOf('.');
				if (lastDot != -1) {
					String photoExtension = name.substring(lastDot, name.length()).toLowerCase();
					if (extension.contains(photoExtension)) {
						dirsAndPhotos.get(fl.getParent()).add(fl);
					}
				} else {
					dirsAndPhotos.get(fl.getParent()).add(fl);
				}
			}

		}
		nextFolder();

	}

	private static void nextFolder() {
		if (directorys.size() != 0) {
			File nextDir = new File(directorys.get(0).toString());
			folderProcessing(nextDir.listFiles());
		}
	}

}
