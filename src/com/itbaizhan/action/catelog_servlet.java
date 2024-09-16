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

import com.itbaizhan.dao.DB;
import com.itbaizhan.orm.Tcatelog;


public class catelog_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		if(type.endsWith("catelogAdd"))
		{
			catelogAdd(req, res);//��Ӳ�Ʒ���
		}
		if(type.endsWith("catelogMana"))
		{
			catelogMana(req, res);//��Ʒ������
		}
		if(type.endsWith("catelogDel"))
		{
			catelogDel(req, res);//��Ʒ���ɾ��
		}
	}
	
	public void catelogAdd(HttpServletRequest req,HttpServletResponse res)
	//��Ʒ�����Ӻ���
	{
		String id=String.valueOf(new Date().getTime());
		String name=req.getParameter("name").trim();//trim()ȥ��ǰ��Ŀհ�
		String del="no";
		
		String sql="insert into t_catelog(id,name,del) values(?,?,?)";
		Object[] params={id,name,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);//ִ��sql��䣬�����ݿ������ɾ�Ĳ飬�����ֵ�������params��
		mydb.closed();
		
		req.setAttribute("msg", "�����ɹ�");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void catelogMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//��Ʒ������
	{
		List catelogList=new ArrayList();
		String sql="select * from t_catelog where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())//���������һ����䣬ѭ��ִ��
			{
				Tcatelog catelog=new Tcatelog();
				catelog.setId(rs.getString("id"));
				catelog.setName(rs.getString("name"));
				catelogList.add(catelog);//�����𵽲�Ʒ���
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("catelogList", catelogList);
		req.getRequestDispatcher("admin/catelog/catelogMana.jsp").forward(req, res);
	}
	
	
	
	public void catelogDel(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//��Ʒ���ɾ���ɹ�
	{
		String sql="update t_catelog set del='yes' where id="+req.getParameter("id");
		Object[] params={};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "�����ɹ�");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	//��ת��һ����
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
	//��ʼ������
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
