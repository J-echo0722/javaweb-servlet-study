package com.mj.servlet.response.demo07;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author : MaJ
 * @date : 2021/12/13
 * @description ：验证码
 */
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 每3秒刷新一次
        resp.setHeader("refresh", "3");
        // 在内存中创建一张图片
        BufferedImage image = new BufferedImage(105, 20, BufferedImage.TYPE_INT_RGB);
        // 得到图片，想成一个笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        // 设置颜色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 105, 20);
        // 给图片写数据
        g.setColor(Color.BLUE);
        g.setFont(new Font(null, Font.BOLD, 20));
        g.drawString(makeRandom(), 0, 20);
        // 告诉浏览器，这个请求用图片的方式打开
        resp.setContentType("image/jpeg");
        // 网站存在缓存，不让浏览器缓存
        resp.setDateHeader("expires", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");
        // 把图片写给浏览器
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    private String makeRandom() {
        Random random = new Random();
        String num = random.nextInt(99999999) + "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8 - num.length(); i++) {
            sb.append("0");
        }
        return sb.toString() + num;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
