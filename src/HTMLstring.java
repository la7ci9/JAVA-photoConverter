import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kiss László
 *
 *         9-es csoport
 *
 */
public class HTMLstring {
	static FileWriter myWriter;

	public static void photoHTML(String name, String htmlName, String parent) {
		try {
			myWriter = new FileWriter(htmlName);
			///// Start page/////
			myWriter.write("<h1>\n<a href=" + Main.Start + "> Start page </a> </h1>\n");

			///// BackToDir/////
			myWriter.write("<h4>\n<a href=" + parent + "/index.html" + "> ^^ </a> </h4>\n");

			///// NextOrPrev/////
			List<File> leftAndRightPhoto = new ArrayList<File>();
			for (Map.Entry<String, ArrayList<File>> entry : Main.dirsAndPhotos.entrySet()) {
				if (entry.getKey().equals(parent)) {
					leftAndRightPhoto = entry.getValue();
				}
			}
			for(int i = 0;i<leftAndRightPhoto.size();i++) {
				if(leftAndRightPhoto.get(i).isDirectory()) {
					leftAndRightPhoto.remove(i);
				}
			}
			int spotPhoto = -1;

			for (int i = 0; i < leftAndRightPhoto.size(); i++) {
				if (leftAndRightPhoto.get(i).toString().equals(name)) {
					spotPhoto = i;
				}
			}
			int	befSpotPhoto = spotPhoto + 1;
			int	prevSpotPhoto = spotPhoto - 1;
			

			if (spotPhoto == 0) {
				myWriter.write("<h4>\n<a href="
						+ leftAndRightPhoto.get(spotPhoto).toString().substring(0,leftAndRightPhoto.get(spotPhoto).toString().lastIndexOf("."))
						+ ".html" + "> << </a>" + leftAndRightPhoto.get(spotPhoto).getName() + "<a href="
						+ leftAndRightPhoto.get(befSpotPhoto).toString().substring(0,leftAndRightPhoto.get(befSpotPhoto).toString().lastIndexOf("."))+ ".html" + "> >> </a> </h4>\n");
			} else if (spotPhoto>0&&befSpotPhoto<leftAndRightPhoto.size()) {
				myWriter.write("<h4>\n<a href="
						+ leftAndRightPhoto.get(prevSpotPhoto).toString().substring(0,leftAndRightPhoto.get(prevSpotPhoto).toString().lastIndexOf("."))
						+ ".html" + "> << </a>" + leftAndRightPhoto.get(spotPhoto).getName() + "<a href="
						+ leftAndRightPhoto.get(befSpotPhoto).toString().substring(0,leftAndRightPhoto.get(befSpotPhoto).toString().lastIndexOf("."))+ ".html" + "> >> </a> </h4>\n");	
			}
			else {
				myWriter.write("<h4>\n<a href="
						+ leftAndRightPhoto.get(prevSpotPhoto).toString().substring(0,leftAndRightPhoto.get(prevSpotPhoto).toString().lastIndexOf("."))
						+ ".html" + "> << </a>" + leftAndRightPhoto.get(spotPhoto).getName() + "<a href="
						+ leftAndRightPhoto.get(spotPhoto).toString().substring(0,leftAndRightPhoto.get(spotPhoto).toString().lastIndexOf("."))+ ".html" + "> >> </a> </h4>\n");	
			
			}

			///// Photo/////
			myWriter.write("<a>\n<img src=\"" + name + "\" width=\"25%\" height=\"25%\">");
			myWriter.close();

		} catch (

		IOException e) {
			System.out.println("Nem sikerült a html-be írni.");
			e.printStackTrace();
		}
	}

	public static void indexHTML(String string, String dir, String string2) {

		try {
			myWriter = new FileWriter(string);
			///// Start page/////
			myWriter.write("<h1>\n<a href=" + Main.Start + "> Start page </a> </h1>\n");
			///// Directorys/////
			myWriter.write("<hr>\n<b>Directory:</b>");
			if (!Main.Start.equals(string)) {
				myWriter.write("<h4>\n<a href=" + string2 + "\\index.html" + "> << </a> </h4>\n");
			}
			for (Map.Entry<String, ArrayList<File>> entry : Main.dirsAndPhotos.entrySet()) {
				if (entry.getKey().toString().equals(dir)) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (entry.getValue().get(i).isDirectory()) {
							myWriter.write("<h4>\n<a href=" + entry.getValue().get(i) + "/index.html" + ">"
									+ entry.getValue().get(i).getName() + "</a></h4>");
						}

					}
				}
			}
			///// Images/////
			myWriter.write("<hr>\n<b>Images:</b>");
			for (Map.Entry<String, ArrayList<File>> entry : Main.dirsAndPhotos.entrySet()) {
				if (entry.getKey().toString().equals(dir)) {
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (!entry.getValue().get(i).isDirectory()) {
							String refTitle = reference(entry.getValue().get(i).getName());
							myWriter.write("<h4>\n<li><a href=" + refTitle + ">" + entry.getValue().get(i).getName()
									+ "</a></li>\n</h4>");
						}
					}
				}
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("A index.html fileba írás nem sikerült");
			e.printStackTrace();
		}
	}

	private static String reference(String file) {
		int lastDot = file.lastIndexOf('.');
		String result = file.substring(0, lastDot + 1) + "html";
		return result;
	}

}
