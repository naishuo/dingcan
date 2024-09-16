package com.itbaizhan.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itbaizhan.dao.DB;
import com.itbaizhan.orm.Tuser;
import com.itbaizhan.service.liuService;

public class user_servlet extends HttpServlet
{
	
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		if(type.endsWith("userReg"))
		{
			userReg(req, res);//��Աע��
		}
		if(type.endsWith("userLogout"))
		{
			userLogout(req, res);//��Ա�˳�
		}
		if(type.endsWith("userMana"))
		{
			userMana(req, res);//��Ա����
		}
		if(type.endsWith("userDel"))
		{
			userDel(req, res);//��Աɾ��
		}
		
		if(type.endsWith("userDetail"))
		{
			userDetail(req, res);//��Ա��ϸ��Ϣ
		}
	}
	
	
	public void userReg(HttpServletRequest req,HttpServletResponse res)
	//��Աע��
	{
		String id=String.valueOf(new Date().getTime());
		String loginname=req.getParameter("loginname");
		String loginpw=req.getParameter("loginpw");
		String name=req.getParameter("name");
		String del="no";
		
		String s=liuService.panduan_zhanghao(loginname);
		if(s.equals("yizhan"))
		{
			req.setAttribute("message", "�˺��ѱ�ռ�ã������������˺�");
			req.setAttribute("path", "site/userreg/userreg.jsp");
			
	        String targetURL = "/common/success.jsp";
			dispatch(targetURL, req, res);
		}
		else
		{
			String sql="insert into t_user values(?,?,?,?,?)";
			Object[] params={id,loginname,loginpw,name,del};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			mydb.closed();
			
			req.setAttribute("message", "ע��ɹ������¼");
			req.setAttribute("path", "site/default.jsp");
			
	        String targetURL = "/common/success.jsp";
			dispatch(targetURL, req, res);
		}
		
		
	}
	
	
	
	public void userLogout(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session=req.getSession();
		session.setAttribute("userType", null);
		session.setAttribute("user", null);
		
			
		req.setAttribute("message", "�ɹ��˳�ϵͳ");
		req.setAttribute("path", "site/default.jsp");
		String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void userMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List userList=new ArrayList();
		String sql="select * from t_user where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tuser user=new Tuser();
				
				user.setId(rs.getString("id"));
				user.setLoginname(rs.getString("loginname"));
				user.setLoginpw(rs.getString("loginpw"));
				user.setLoginpw(rs.getString("loginpw"));
				user.setName(rs.getString("name"));
				
				userList.add(user);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("userList", userList);
		req.getRequestDispatcher("admin/user/userMana.jsp").forward(req, res);
	}
	
	
	
	public void userDel(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
        String id=req.getParameter("id");
		
		String sql="update t_user set del='yes' where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "�û���Ϣɾ�����");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void userDetail(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String id=req.getParameter("id");
		
		List userList=new ArrayList();
		String sql="select * from t_user where id=?";
		Object[] params={id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tuser user=new Tuser();
				
				user.setId(rs.getString("id"));
				user.setLoginname(rs.getString("loginname"));
				user.setLoginpw(rs.getString("loginpw"));
				user.setLoginpw(rs.getString("loginpw"));
				user.setName(rs.getString("name"));
				
				userList.add(user);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("userList", userList);
		req.setAttribute("xinyongList", liuService.getxinyongList(id));
		req.getRequestDispatcher("admin/user/userDetail.jsp").forward(req, res);
	}
	
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
