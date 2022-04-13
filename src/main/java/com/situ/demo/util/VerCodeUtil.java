package com.situ.demo.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * Verification Code
 * @author bobzyh
 *
 */
public class VerCodeUtil {
	/*
	 * 宽度
	 * 高度
	 * 长度
	 * 字符集
	 */
	private static final int WIDTH = 100;
	private static final int HEIGHT = 38;
	private static final int CODE_LENGTH = 4;
	private static final String CODES = "1234567890ABCDEFGHJKLMNPQRSTUVWXYZ";
	private static final int LINE_COUNT = 15;
	
	
	/**
	 * 
	 * @param response 
	 * @return
	 */
	public static String createVerCode(HttpServletResponse response) {
		return createVerCode(WIDTH, HEIGHT, CODE_LENGTH, response);
	}

	/**
	 * 
	 * @param width
	 * @param height
	 * @param codeLength
	 * @param response
	 * @return
	 */

	public static String createVerCode(int width, int height, int codeLength, HttpServletResponse response) {
		// 生成字符串
		String verCode = randomCode(codeLength);
		
		// 生成图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		// 获取画板
		Graphics graphics = image.getGraphics();
		
		// 绘制背景颜色
		graphics.setColor(randomBgColor());
		graphics.fillRect(0, 0, width, height);
		
		// 在 "画板"上生成干扰线条
		drawLine(width, height, graphics, LINE_COUNT);
		
		
		// 绘制字符串
		graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		for (int i = 0; i < verCode.length(); i++) {
			graphics.setColor(randomFontColor());
			graphics.drawString(verCode.substring(i, i+1), i*(width/4), (height)/2+30/6*2);
		}		
		
		// 释放资源
		graphics.dispose();
		
		// 写入Response
		try {
			ImageIO.write(image, "jpeg", response.getOutputStream());
			
			return verCode;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 绘制干扰线
	 * @param width
	 * @param height
	 * @param graphics
	 * @param count
	 */
	private static void drawLine(int width, int height, Graphics graphics, int count) {
		Random random = new Random();
		
		for (int i = 0; i < count; i++) {
		    graphics.setColor(randomFontColor());
		    final int x = random.nextInt(width);
		    final int y = random.nextInt(height);
		    final int w = random.nextInt(width);
		    final int h = random.nextInt(height);
		    graphics.drawLine(x, y, w, h);
		}
		
	}

	private static Color randomFontColor() {
		return randomColor(150, 0);
	}

	/**
	 * 随机生成背景
	 * @return
	 */
	private static Color randomBgColor() {
		return randomColor(256, 200);
	}
	
	
	/**
	 * 随机生成颜色
	 * @param maxValue
	 * @param minValue
	 * @return
	 */
	private static Color randomColor(int maxValue, int minValue) {
		Random random = new Random();
		
		int r = minValue+random.nextInt(maxValue-minValue);
		int g = minValue+random.nextInt(maxValue-minValue);
		int b = minValue+random.nextInt(maxValue-minValue);
		
		Color color = new Color(r, g, b);
		
		return color;
	}

	/**
	 * 生成字符串
	 * @param codeLength
	 * @return
	 */
	private static String randomCode(int codeLength) {
		
		StringBuilder verCode = new StringBuilder();
		Random random = new Random();
		
		for (int i = 0; i < codeLength; i++) {
			int index = random.nextInt(CODES.length());
			verCode.append(CODES.charAt(index));
		}
		
		return verCode.toString();
	}
		
}
