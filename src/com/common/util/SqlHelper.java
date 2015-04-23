package com.common.util;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.*;

/*
 * ����һ���������ݿ�Ĺ�����
 * 
 */
public class SqlHelper
{
    //�������
    private static Connection ct = null;
    //������������preparedstatement���statement
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
   
    //�������ݿ�Ĳ���
    private static String url = "";
    private static String username = "";
    private static String driver = "";
    private static String passwd = "";
   
    private static CallableStatement cs = null;
    
    // ��ȡ�����ļ���
    private static Properties  pp = null;
    private static InputStream fis = null;
    
    //�����������þ�̬�����,ֻ��Ҫһ�Σ�
    static
    {
        try
        {
        	
            //��dbinfo.properties�ļ��ж�ȡ������Ϣ
			pp = new Properties();
			// tomcatĬ����Ŀ¼��binĿ¼��
//            fis=new FileInputStream("dbinfo.properties");
			// ������ʹ��java web��ʱ�򣬶�ȡ�ļ�Ҫʹ���������
			// ��Ϊ�������ȥ��ȡ��Դ��ʱ��Ĭ�ϵ���Ŀ¼��srcĿ¼
            fis = SqlHelper.class.getClassLoader().getResourceAsStream("dbinfo.properties");
            pp.load(fis);
            url = pp.getProperty("url");
            username = pp.getProperty("username");
            driver = pp.getProperty("driver");
            passwd = pp.getProperty("passwd");
            Class.forName(driver);
            System.out.println("SQL���������ɹ���");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            { 
            	fis.close();
            }
            catch(Exception e) {e.printStackTrace();}
            fis = null;//��������վ����ʰ
        }
       
    }
    //�õ�����
    public static Connection getConnection()
        {
            try
            {
//            	ct = DriverManager.getConnection(url,username,passwd);
            	
            	ct = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Internship","sa","123");
            	System.out.println("SQL���ӣ�");
            }
            	catch(Exception e) {e.printStackTrace();}
            return ct;
        }
    
    // ��ҳ����?
    public static ResultSet executeQuery2(){
    	return null;
    }
   
   
//*************callPro1�洢���̺���1*************   
    public static CallableStatement callPro1(String sql,String[] parameters)
    {
        try{
            ct = getConnection();
            cs = ct.prepareCall(sql);
            if(parameters!=null){
                for(int i=0;i<parameters.length;i++){
                 cs.setObject(i+1,parameters[i]);
                }
            }   
            cs.execute();
        }
        catch(Exception e) { e.printStackTrace(); throw new RuntimeException(e.getMessage());}
        finally
        { close(rs,cs,ct);}
        return cs;
    }
   
//*******************callpro2�洢����2************************
    // ���ô洢���̣��з���Result��sql call���� (?,?)
public static CallableStatement callPro2(String sql,String[] inparameters,
Integer[] outparameters)
{
    try
    {
        ct = getConnection();
        cs = ct.prepareCall(sql);
        if(inparameters!=null)
        {
            for(int i=0;i<inparameters.length;i++)
            {
                cs.setObject(i+1,inparameters[i]);
            }
        }
    //cs.registerOutparameter(2,oracle.jdbc.OracleTypes.CURSOR);
        if(outparameters!=null)
        {
            for(int i=0;i<outparameters.length;i++)
            {
                cs.registerOutParameter(inparameters.length+1+i,outparameters[i]);
            }
        }
        cs.execute();
    }
    catch(Exception e) {
        e.printStackTrace(); throw new RuntimeException(e.getMessage());
    }
    finally
    {
       close(rs,cs,ct);
    }
    return cs;
}

//---------------------------------------------------------------------------------------------------
	// ͳһ��select
	// ResultSet->ArrayList
    public static ResultSet executeQuery(String sql,String[] parameters)
    {
        try
        {
            ct=getConnection();
            ps=ct.prepareStatement(sql);
            if(parameters!=null)
            {
                for(int i=0;i<parameters.length;i++)
                {
                    ps.setString(i+1,parameters[i]);
                }
            }
            rs = ps.executeQuery();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("SQL������ѯ");
        }
        return rs;
    }
    
   //-----------------------------------
    // �Բ�ѯ�������
    // �������ǿ������㣬��������Դ������ر���Դ��ֱ�ӷ���ArrayList
    public static ArrayList executeQuery3(String sql,String[] parameters)
    {
    	// ����ֲ�����
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
   
    	try
    	{	
    		conn=getConnection();
    		pstmt=conn.prepareStatement(sql);
    		
    		// �ԣ���ֵ
			if(parameters!=null && !parameters.equals(""))
			{
					for(int i=0;i<parameters.length;i++)
					{
						pstmt.setString(i+1, parameters[i]);
						
					}
			}
				rs=pstmt.executeQuery();
				
				ArrayList al = new ArrayList();
				
				ResultSetMetaData rsmd = rs.getMetaData();
				// ������Եõ�����ѯ����ж�����
				int column=rsmd.getColumnCount();
				
				while(rs.next())
				{
					// �������飬��ʾһ������
					Object[] ob=new Object[column];
					for(int i=1;i<=column;i++)
					{
							ob[i-1]=rs.getObject(i);
					}
					al.add(ob);			
				}
				return al;
					
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
    	finally
    	{
    		// �ر���Դ
    		close(rs,pstmt,conn);
    	}

    }
    
   
   // ����ж�� update/delete/insert [��Ҫ��������]
    public static void executeUpdate2(String[] sql,String[][] parameters)
    {
        try
        {
        	//���ģ�1.�������
            ct = getConnection();
            
            // ��Ϊ��ʱ���û�����Ŀ����Ƕ��sql���
            ct.setAutoCommit(false);
           
            for(int i=0;i<sql.length;i++)
            {
               
                if(null!=parameters[i])
                {
                    ps = ct.prepareStatement(sql[i]);
                    for(int j=0;j<parameters[i].length;j++)
                    {
                        ps.setString(j+1,parameters[i][j]);
                    }
                    ps.executeUpdate();
                }
            }
           
            ct.commit();
           
           
        }catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                ct.rollback();
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            throw  new RuntimeException(e.getMessage());
        }finally
        {
            close(rs,ps,ct);
        }
       
    }
   
    //��дһ��update��delete��insert
    //sql��ʽ��update ���� set �ֶ��� =��where �ֶ�=��
    //parameters Ӧ���ǣ���abc��,23��
    public static void executeUpdate(String sql,String[] parameters)
    {
    	
        try
        {
            ct=getConnection();
            ps = ct.prepareStatement(sql);
            
            if(parameters!=null)
            {
                for(int i=0;i<parameters.length;i++)
                {
                    ps.setString(i+1,parameters[i]); 
                }
            }
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();//�����׶�
            System.out.println("SQL�޸Ĵ���");
            //�׳��쳣,���Ը����øú����ĺ������崦�� 
            //���Դ���Ҳ���Բ�����
            throw new RuntimeException(e.getMessage());
        }
        finally
        {
//            close(rs,ps,ct);
        }
    }
   
    // �ر���Դ�ĺ���
    public static void close(ResultSet rs,Statement ps,Connection ct)
    {
    	System.out.println("ִ�йر�SQL��");
        //�ر���Դ(�ȿ����)
        if(rs!=null)
        {
            try
            {
                rs.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            rs=null;
        }
        //�ر���Դ[�ȿ����]
        if(ps!=null)
        {
            try
            {
                ps.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            ps=null;
        }
        if(null!=ct)
        {
            try
            {
                ct.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            ct=null;
        }
    }
    
    public static Connection getCt()
    {
        return ct;
    }
    public static PreparedStatement getPs()
    {
        return ps;
    }
    public static ResultSet getRs()
    {
        return rs;
    }
    public static CallableStatement getCs()
    {
        return cs;
    }
}