package com.nbfc.utility;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
 
public class CaptchaServlet extends HttpServlet {

	private int height=0;
	private int width=0;		
	public static final String CAPTCHA_KEY = "captcha_key_name";

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 Color backgroundColor = Color.white;
         Color borderColor = Color.black;
         Color textColor = Color.black;
         Color circleColor = new Color(190, 160, 150);
         Font textFont = new Font("Verdana", Font.BOLD, 20);
         int charsToPrint = 6;
         int width = 160;
         int height = 50;
         int circlesToDraw = 25;
         float horizMargin = 10.0f;
         double rotationRange = 0.7; 
         BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
         g.setColor(backgroundColor);
         g.fillRect(0, 0, width, height);

         // lets make some noisey circles
         g.setColor(circleColor);
         for (int i = 0; i < circlesToDraw; i++) {
             int L = (int) (Math.random() * height / 2.0);
             int X = (int) (Math.random() * width - L);
             int Y = (int) (Math.random() * height - L);
             g.draw3DRect(X, Y, L * 2, L * 2, true);
         }
         g.setColor(textColor);
         g.setFont(textFont);
         FontMetrics fontMetrics = g.getFontMetrics();
         int maxAdvance = fontMetrics.getMaxAdvance();
         int fontHeight = fontMetrics.getHeight();

         String elegibleChars = "ABCDEFGHJKLMNPQRSTUVWXYabcdefghjkmnpqrstuvwxy";
         char[] chars = elegibleChars.toCharArray();
         String elegibleNum = "23456789";
         char[] numChars = elegibleNum.toCharArray();
         float spaceForLetters = -horizMargin * 2 + width;
         float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);
         StringBuffer finalString = new StringBuffer();
         char characterToShow;
         for (int i = 0; i < charsToPrint; i++) {
             if(i>=4) {
            	 double randomValue2 = Math.random();
                 int randomIndex2 = (int) Math.round(randomValue2 * (numChars.length - 1));
                 characterToShow = numChars[randomIndex2];
             }else {
            	 double randomValue = Math.random();
                 int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
                 characterToShow = chars[randomIndex];
             }
        	 finalString.append(characterToShow);
             // this is a separate canvas used for the character so that
             // we can rotate it independently
             int charWidth = fontMetrics.charWidth(characterToShow);
             int charDim = Math.max(maxAdvance, fontHeight);
             int halfCharDim = (int) (charDim / 2);
             BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
             Graphics2D charGraphics = charImage.createGraphics();
             charGraphics.translate(halfCharDim, halfCharDim);
             double angle = (Math.random() - 0.5) * rotationRange;
             charGraphics.transform(AffineTransform.getRotateInstance(angle));
             charGraphics.translate(-halfCharDim, -halfCharDim);
             charGraphics.setColor(textColor);
             charGraphics.setFont(textFont);
             int charX = (int) (0.5 * charDim - 0.5 * charWidth);
             charGraphics.drawString("" + characterToShow, charX, (int) ((charDim - fontMetrics.getAscent()) / 2 + fontMetrics.getAscent()));
             float x = horizMargin + spacePerChar * (i) - charDim / 2.0f;
             int y = (int) ((height - charDim) / 2);
             g.drawImage(charImage, (int) x, y, charDim, charDim, null, null);
             charGraphics.dispose();
         }
         g.setColor(borderColor);
         g.drawRect(0, 0, width - 1, height - 1);
         g.dispose();
         String captcha = finalString.toString();
         System.out.println("Captcha Key:"+captcha);
		 request.getSession(true).setAttribute(CAPTCHA_KEY, captcha );
		 response.setContentType("image/jpeg");
	     OutputStream os = response.getOutputStream();
		 ImageIO.write(bufferedImage, "jpg", os);
		 os.close();
		} 
		
		
	protected void doGet(HttpServletRequest request, 
	   HttpServletResponse response)
	       throws ServletException, IOException {
	processRequest(request, response);
	} 
	
	
	protected void doPost(HttpServletRequest request, 
	    HttpServletResponse response)
	        throws ServletException, IOException {
	processRequest(request, response);
	}

}