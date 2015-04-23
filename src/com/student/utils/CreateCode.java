package com.student.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCode extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		//告诉浏览器不缓存,以便能及时更新验证码图片
		response.setHeader("Expires", "-1");	// 针对IE不缓存
		response.setHeader("Cache-Control", "no-cache");	// 针对火狐等不缓存	
		response.setHeader("Pragma", "no-cache");	// 针对其他浏览器不缓存
		
		//告诉浏览器以图片方式打开传过去的数据
		response.setHeader("Content-Type", "image/jpeg");
		
		//在内存中创建一个长60 宽20 具有8种像素数的空图片
		BufferedImage imager=new BufferedImage(60,20,BufferedImage.TYPE_INT_RGB);
		
		//得到画笔
		Graphics g=imager.getGraphics();
		
		//设置背景颜色
		g.setColor(Color.WHITE);
		//画个矩形长60宽20的
		g.fillRect(0, 0, 60,20);
		//设置字体色体
		g.setColor(Color.RED);
		//设置字体 加粗 大小为20
		g.setFont(new Font(null,Font.BOLD,20));
		
		// 把函数返回的num写入String里
		String num = makeNum();
		
		// 把随机生成的数值，保存到session，以便验证时用
		request.getSession().setAttribute("num", num);
		//把数字写入
		g.drawString(num, 0, 20);
		
		//写出去给浏览器
		ImageIO.write(imager, "jpg", response.getOutputStream());
	}
	
	// 随机生成4个数字的函数
	public String makeNum()
	{
		Random r=new Random();
		String num=r.nextInt(9999)+"";	// 9999代表4位数字的最大范围
		StringBuffer  sb=new StringBuffer ();
		if(num.length()<4)
		{
			for(int i=0;i<4-num.length();i++)
			{
				sb.append("0");	// 如果不够4位，前面补0
			}
			num=sb.toString()+num;
		}
		return num;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		this.doGet(request, response);
	}

}
