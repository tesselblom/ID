import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.apache.xmlgraphics.ps.ImageFormGenerator;

import com.lowagie.text.Image;

public class Grayscale {
	
	BufferedImage grayImage;
	BufferedImage testImage;
	
	public Grayscale(){
		File imageFile = new File("D:\\Users\\RPESCH\\Downloads\\TESTHenk.jpg");
		try{
		BufferedImage image = ImageIO.read(imageFile);
		BufferedImage bwImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D graphics = bwImage.createGraphics();
	    graphics.drawImage(image, 0, 0, null);
		
		grayImage = getScaledImage(bwImage, bwImage.getWidth()+400, bwImage.getHeight()+200);
	    //testImage = getScaledImage(image, image.getWidth()+200, image.getHeight()+200);
		//grayImage = toGray(testImage);
		}catch(IOException e){
			System.out.println(e);
		}
	}
	public BufferedImage toGray(BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		for(int i = 0; i<height; i++){
			for(int j = 0; j<width; j++){
				Color c = new Color(image.getRGB(j, i));
				int red = (int)(c.getRed()*0.11);
				int green = (int)(c.getGreen()*0.12);
				int blue = (int)(c.getBlue()*0.47);
				int sum = red+green+blue;
				Color newColor = new Color(sum,sum,sum);
				image.setRGB(j, i, newColor.getRGB());
			}
		}
		return image;
	}
	
	public BufferedImage getScaledImage(BufferedImage image, int width, int height){
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();
		
		double scaleX = (double)width/imageWidth;
		double scaleY = (double)height/imageHeight;
		AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
		AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
		
		return bilinearScaleOp.filter(image, new BufferedImage(width, height, image.getType()));
	}
}
