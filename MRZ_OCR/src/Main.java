
import java.io.*;
import net.sourceforge.tess4j.*;

public class Main {
	public static void main(String[] args) {
		File imageFile = new File("D:\\Users\\TEBLOM\\Downloads\\TEST.jpg");
		Tesseract instance = Tesseract.getInstance(); //

		try {
			
			instance.setDatapath("D:\\Users\\TEBLOM\\Documents\\GitHub\\ID\\Tess4J");
			String result = instance.doOCR(imageFile);
			System.out.println(result);

		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
	}
}