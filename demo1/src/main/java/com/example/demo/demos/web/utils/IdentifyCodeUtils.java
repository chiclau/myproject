package com.example.demo.demos.web.utils;

import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 随机验证码工具类
 */
public class IdentifyCodeUtils {
    //设置图片宽
    private int width = 95;
    //设置图片高度
    private int height = 25;
    //设置干扰线数量
    private int lineSize = 40;
    //随机产生数字和字母组合的字符串
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random random = new Random();

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 获取验证码
     *
     * @return
     */
    public String getIdentifyCode() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            char c = randString.charAt(random.nextInt(randString.length()));
            buffer.append(c);
        }
        return buffer.toString();
    }

    /**
     * 生成随机图片
     *
     * @param identifyCode
     * @return
     */
    public BufferedImage getIdentifyImage(String identifyCode) {
        //BufferedImage类是具有缓冲区的Image类，Image类是用来描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        //产生Image对象的Graphics对象，改对象可以在图像上进行各种绘制操作
        Graphics graphics = image.getGraphics();
        //图片大小
        graphics.fillRect(0, 0, width, height);
        //字体大小
        graphics.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        //字体颜色
        graphics.setColor(getRandColor(110, 133));
        //绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drawLine(graphics);
        }
        //绘制随机字符
        drawString(graphics, identifyCode);
        graphics.dispose();
        return image;

    }

    /**
     * 绘制字符串
     */
    private void drawString(Graphics g, String identifyCode) {
        for (int i = 0; i < identifyCode.length(); i++) {
            g.setFont(getFont());
            g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                    .nextInt(121)));
            g.translate(random.nextInt(3), random.nextInt(3));
            g.drawString(String.valueOf(identifyCode.charAt(i)), 13 * i + 20, 18);
        }


    }

    /**
     * 响应验证码图片
     *
     * @param identifyImg
     * @param response
     */
    public void responseIdentifyImg(BufferedImage identifyImg, HttpServletResponse response) {
        //设置响应类型，告诉浏览器输出的内容是图片
        response.setContentType("image/jpeg");
        //设置响应头信息，告诉浏览器不用缓冲此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try {
            //把内存中的图片通过流动形式输出到客户端
            ImageIO.write(identifyImg, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 绘制干扰线
     */
    private void drawLine(Graphics graphics) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        graphics.drawLine(x, y, x + xl, y + yl);
    }




}

