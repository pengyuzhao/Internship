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
 * 这是一个操作数据库的工具类
 * 
 */
public class SqlHelper
{
    //定义变量
    private static Connection ct = null;
    //大多数情况下用preparedstatement替代statement
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
   
    //连接数据库的参数
    private static String url = "";
    private static String username = "";
    private static String driver = "";
    private static String passwd = "";
   
    private static CallableStatement cs = null;
    
    // 读取配置文件的
    private static Properties  pp = null;
    private static InputStream fis = null;
    
    //加载驱动，用静态代码块,只需要一次，
    static
    {
        try
        {
        	
            //从dbinfo.properties文件中读取配置信息
			pp = new Properties();
			// tomcat默认主目录是bin目录下
//            fis=new FileInputStream("dbinfo.properties");
			// 当我们使用java web的时候，读取文件要使用类加载器
			// 因为类加载器去读取资源的时候，默认的主目录是src目录
            fis = SqlHelper.class.getClassLoader().getResourceAsStream("dbinfo.properties");
            pp.load(fis);
            url = pp.getProperty("url");
            username = pp.getProperty("username");
            driver = pp.getProperty("driver");
            passwd = pp.getProperty("passwd");
            Class.forName(driver);
            System.out.println("SQL加载驱动成功！");
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
            fis = null;//垃圾回收站上收拾
        }
       
    }
    //得到连接
    public static Connection getConnection()
        {
            try
            {
//            	ct = DriverManager.getConnection(url,username,passwd);
            	
            	ct = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Internship","sa","123");
            	System.out.println("SQL连接！");
            }
            	catch(Exception e) {e.printStackTrace();}
            return ct;
        }
    
    // 分页问题?
    public static ResultSet executeQuery2(){
    	return null;
    }
   
   
//*************callPro1存储过程函数1*************   
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
   
//*******************callpro2存储过程2************************
    // 调用存储过程，有返回Result，sql call过程 (?,?)
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
	// 统一的select
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
            System.out.println("SQL出错：查询");
        }
        return rs;
    }
    
   //-----------------------------------
    // 对查询语句升级
    // 这样我们可以满足，哪里用资源，哪里关闭资源，直接返回ArrayList
    public static ArrayList executeQuery3(String sql,String[] parameters)
    {
    	// 定义局部变量
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
   
    	try
    	{	
    		conn=getConnection();
    		pstmt=conn.prepareStatement(sql);
    		
    		// 对？赋值
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
				// 这里可以得到，查询结果有多少行
				int column=rsmd.getColumnCount();
				
				while(rs.next())
				{
					// 对象数组，表示一行数据
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
    		// 关闭资源
    		close(rs,pstmt,conn);
    	}

    }
    
   
   // 如果有多个 update/delete/insert [需要考虑事物]
    public static void executeUpdate2(String[] sql,String[][] parameters)
    {
        try
        {
        	//核心，1.获得连接
            ct = getConnection();
            
            // 以为这时，用户传入的可能是多个sql语句
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
   
    //先写一个update、delete、insert
    //sql格式：update 表名 set 字段名 =？where 字段=？
    //parameters 应该是（”abc“,23）
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
            e.printStackTrace();//开发阶段
            System.out.println("SQL修改错误");
            //抛出异常,可以给调用该函数的函数具体处理 
            //可以处理，也可以不处理
            throw new RuntimeException(e.getMessage());
        }
        finally
        {
//            close(rs,ps,ct);
        }
    }
   
    // 关闭资源的函数
    public static void close(ResultSet rs,Statement ps,Connection ct)
    {
    	System.out.println("执行关闭SQL！");
        //关闭资源(先开后关)
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
        //关闭资源[先开后闭]
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