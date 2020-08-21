/*
* @Author Kory Palmer
* @Version 0.1
*/
package asciiart;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import static java.lang.Math.ceil;
import javax.imageio.ImageIO;

public class AsciiArt {
	String asciiBrightToDark = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";
	
	public static void main(String[] args)throws IOException {
		BufferedImage image = null;	
		File input_file;
		
		
		try {
			input_file = new File(args[0]);
			image = ImageIO.read(input_file);
			AsciiArt aArt = new AsciiArt();
			String msg = aArt.processImage(image);
			System.out.println(msg);
		} catch(IOException e) {
			System.out.println("Error:" + e);
		}
	}
	
	public String processImage(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int[][] pixels = new int[width][height];
		double[][] averagePixels = new double[width][height];
		String pixelString = "";
		String[][] imgOut = new String[height][width];
		
		for(int i = width - 1; i >= 0; i--) {
			for (int j = 0; j < height; j++) {
				pixels[i][j] = img.getRGB(i,j);
				int alpha = (pixels[i][j]>>24) & 0xff;
				int red = (pixels[i][j]>>16) & 0xff;
				int green = (pixels[i][j]>>8) & 0xff;
				int blue = pixels[i][j] & 0xff;
				
				averagePixels[i][j] = (red + blue + green) / 3;
				
				//divide by 4 and ceiling the value to get corresponding ascii char for that value
				int temp = (int)(ceil(averagePixels[i][j]/4));
				//imgout[i][j] will then be the char corresponding to temp in the asciiBrightToDark String
				imgOut[j][i] = String.valueOf(asciiBrightToDark.charAt(temp));
				
				for (int k = 0; k < 3; k++) {
					System.out.print(imgOut[j][i]);
				}
//				debugging info
//				pixelString += "alpha: " + alpha + "\n" + "red: " + red 
//					+ "\n" + "green: " + green + "\n" + "blue: " + blue + "\n";
			}
			System.out.println("");	
		}
		
//		debugging info
		String info = "Successfully loaded image!\n Image size: " + img.getWidth() + " x " + img.getHeight();
		
		int alphaTest = (pixels[0][0]>>24) & 0xff;
		int redTest = (pixels[0][0]>>16) & 0xff;
		int greenTest = (pixels[0][0]>>8) & 0xff;
		int blueTest = pixels[0][0] & 0xff; 
		return info + "\n" + "alpha: " + alphaTest + "\n" + "red: " + redTest + "\n"
			+ "green: " + greenTest + "\n" + "blue: " + blueTest + "\n";
	}
}
