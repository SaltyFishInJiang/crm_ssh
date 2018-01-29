package crm.action.verfiy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerfiyCode {
	private int w = 70;
	private int h = 35;
	private Random random = new Random();
	private String[] fontname = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
	private String code = "23456789qwertyuiopasdfghjklzxcvbnmQWERTYUIPASDFGHJKLZXCVBNM";
	private Color bgcolor = new Color(255, 255, 255);
	private String text;

	private BufferedImage createImage() {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setColor(bgcolor);
		g.fillRect(0, 0, w, h);
		return image;
	}

	private Font getFont() {
		int index = random.nextInt(fontname.length);
		String font = fontname[index];
		int style = random.nextInt(4);
		int size = random.nextInt(5) + 24;
		return new Font(font, style, size);
	}

	private Color getColor() {
		int red = random.nextInt(150);
		int green = random.nextInt(150);
		int blue = random.nextInt(150);
		return new Color(red, green, blue);
	}

	private char getCode() {
		int index = random.nextInt(code.length());
		return code.charAt(index);
	}

	private void drawLine(BufferedImage image) {
		Graphics2D g = image.createGraphics();
		for (int i = 0; i < 3; i++) {
			int x1 = random.nextInt(w);
			int y1 = random.nextInt(h);
			int x2 = random.nextInt(w);
			int y2 = random.nextInt(h);
			g.setStroke(new BasicStroke(1.5F));
			g.setColor(Color.blue);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	public BufferedImage getImage() {
		BufferedImage image = createImage();
		Graphics2D g = image.createGraphics();
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			String str = getCode() + "";
			s.append(str);
			float x = i * 1.0F * w / 4;
			g.setColor(getColor());
			g.setFont(getFont());
			g.drawString(str, x, h - 5);
		}
		this.text = s.toString();
		drawLine(image);
		return image;
	}

	public String getText() {
		return text;
	}

	public static void output(BufferedImage image, OutputStream out) throws IOException {
		ImageIO.write(image, "jpeg", out);
	}
	
}
