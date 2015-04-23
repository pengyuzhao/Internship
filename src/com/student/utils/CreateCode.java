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
		
		//���������������,�Ա��ܼ�ʱ������֤��ͼƬ
		response.setHeader("Expires", "-1");	// ���IE������
		response.setHeader("Cache-Control", "no-cache");	// ��Ի���Ȳ�����	
		response.setHeader("Pragma", "no-cache");	// ������������������
		
		//�����������ͼƬ��ʽ�򿪴���ȥ������
		response.setHeader("Content-Type", "image/jpeg");
		
		//���ڴ��д���һ����60 ��20 ����8���������Ŀ�ͼƬ
		BufferedImage imager=new BufferedImage(60,20,BufferedImage.TYPE_INT_RGB);
		
		//�õ�����
		Graphics g=imager.getGraphics();
		
		//���ñ�����ɫ
		g.setColor(Color.WHITE);
		//�������γ�60��20��
		g.fillRect(0, 0, 60,20);
		//��������ɫ��
		g.setColor(Color.RED);
		//�������� �Ӵ� ��СΪ20
		g.setFont(new Font(null,Font.BOLD,20));
		
		// �Ѻ������ص�numд��String��
		String num = makeNum();
		
		// ��������ɵ���ֵ�����浽session���Ա���֤ʱ��
		request.getSession().setAttribute("num", num);
		//������д��
		g.drawString(num, 0, 20);
		
		//д��ȥ�������
		ImageIO.write(imager, "jpg", response.getOutputStream());
	}
	
	// �������4�����ֵĺ���
	public String makeNum()
	{
		Random r=new Random();
		String num=r.nextInt(9999)+"";	// 9999����4λ���ֵ����Χ
		StringBuffer  sb=new StringBuffer ();
		if(num.length()<4)
		{
			for(int i=0;i<4-num.length();i++)
			{
				sb.append("0");	// �������4λ��ǰ�油0
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
