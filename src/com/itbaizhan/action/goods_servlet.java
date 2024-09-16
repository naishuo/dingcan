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
import com.itbaizhan.orm.Tgoods;
import com.itbaizhan.service.liuService;


public class goods_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		if(type.endsWith("goodsAdd"))
		{
			goodsAdd(req, res);//��Ӳ�Ʒ
		}
		if(type.endsWith("goodsMana"))
		{
			goodsMana(req, res);//��Ʒ����
		}
		if(type.endsWith("goodsDel"))
		{
			goodsDel(req, res);//��Ʒɾ��
		}
		if(type.endsWith("goodsDetailHou"))
		{
			goodsDetailHou(req, res);//��Ʒ��̨��ϸ��Ϣ
		}
		if(type.endsWith("goodsPre"))
		{
			goodsPre(req, res);//��Ʒ��Ϣ���
		}
		if(type.endsWith("goodsEdit"))
		{
			goodsEdit(req, res);//��Ʒ��Ϣ�༭
		}
		
		
		
		if(type.endsWith("goodsNew"))
		{
			goodsNew(req, res);//��Ʒ������Ϣ
		}
		if(type.endsWith("goodsByCatelog"))
		{
			goodsByCatelog(req, res);//��Ʒ���
		}
		if(type.endsWith("goodsDetailQian"))
		{
			goodsDetailQian(req, res);//��Ʒǰ̨��ϸ��Ϣ
		}
		if(type.endsWith("goodsRes"))
		{
			goodsRes(req, res);//��Ʒ��Ϣ����ǰ
		}
	}
	
	public void goodsAdd(HttpServletRequest req,HttpServletResponse res)
	//�����Ʒ
	{
		String id=String.valueOf(new Date().getTime());
		String catelog_id=req.getParameter("catelog_id");
		String bianhao=req.getParameter("bianhao");
		String mingcheng=req.getParameter("mingcheng");
		
		String jieshao=req.getParameter("jieshao");
		String fujian=req.getParameter("fujian");
		int shichangjia=Integer.parseInt(req.getParameter("shichangjia"));
		int tejia=Integer.parseInt(req.getParameter("shichangjia"));


		String del="no";
		
		String sql="insert into t_goods(id,catelog_id,bianhao,mingcheng,jieshao,fujian,shichangjia,tejia,del) " +
				   "values(?,?,?,?,?,?,?,?,?)";
		Object[] params={id,catelog_id,bianhao,mingcheng,jieshao,fujian,shichangjia,tejia,del};//Object[]����һ��һά���󣬲���Ҫǿ������ת��
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "�����ɹ�");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void goodsMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//������Ʒ��������Ʒ��������
	{
		List goodsList=new ArrayList();
		String sql="select * from t_goods where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgoods goods=new Tgoods();
				
				goods.setId(rs.getString("id"));
				goods.setCatelog_id(rs.getString("catelog_id"));
				goods.setBianhao(rs.getString("bianhao"));
				
				goods.setMingcheng(rs.getString("mingcheng"));
				goods.setJieshao(rs.getString("jieshao"));
				goods.setFujian(rs.getString("fujian"));
				
				goods.setShichangjia(rs.getInt("shichangjia"));
				goods.setTejia(rs.getInt("tejia"));
				goods.setDel(rs.getString("del"));
				
				goodsList.add(goods);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("goodsList", goodsList);
		req.getRequestDispatcher("admin/goods/goodsMana.jsp").forward(req, res);
	}
	
	
	
	public void goodsDel(HttpServletRequest req,HttpServletResponse res)
	//ɾ����Ʒ
	{
		String id=req.getParameter("id");
		String sql="update t_goods set del='yes' where id="+id;
		Object[] params={};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "�����ɹ�");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void goodsDetailHou(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//��Ʒ�ľ���������Ϣ
	{
        String id=req.getParameter("id");
		
		req.setAttribute("goods", liuService.getGoods(id));
		req.getRequestDispatcher("admin/goods/goodsDetailHou.jsp").forward(req, res);
	}
	
	
	public void goodsPre(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//��Ʒ��Ϣ��Ӻ���
	{
		Tgoods goods=new Tgoods();
		
		String sql="select * from t_goods where id=?";
		Object[] params={req.getParameter("id")};
		
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				
				goods.setId(rs.getString("id"));
				goods.setCatelog_id(rs.getString("catelog_id"));
				goods.setBianhao(rs.getString("bianhao"));
				
				goods.setMingcheng(rs.getString("mingcheng"));
				goods.setJieshao(rs.getString("jieshao"));
				goods.setFujian(rs.getString("fujian"));
				
				goods.setShichangjia(rs.getInt("shichangjia"));
				goods.setTejia(rs.getInt("tejia"));
				goods.setDel(rs.getString("del"));
				
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("goods", goods);
		req.getRequestDispatcher("admin/goods/goodsPre.jsp").forward(req, res);
	}
	
	
	public void goodsEdit(HttpServletRequest req,HttpServletResponse res)
	//��Ʒ��Ϣ�༭
	{
		String id=req.getParameter("id");
		String catelog_id=req.getParameter("catelog_id");
		String bianhao=req.getParameter("bianhao");
		String mingcheng=req.getParameter("mingcheng");
		
		String jieshao=req.getParameter("jieshao");
		String fujian=req.getParameter("fujian");
		int shichangjia=Integer.parseInt(req.getParameter("shichangjia"));
		int tejia=Integer.parseInt(req.getParameter("shichangjia"));


		String sql="update t_goods set catelog_id=?,bianhao=?,mingcheng=?,jieshao=?,fujian=?,shichangjia=?,tejia=? where id=?";
		Object[] params={catelog_id,bianhao,mingcheng,jieshao,fujian,shichangjia,tejia,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "�����ɹ�");
		String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void goodsNew(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//�õ����µĲ�Ʒ��Ϣ
	{
		List goodsList=new ArrayList();
		String sql="select * from t_goods where del='no' order by id desc";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgoods goods=new Tgoods();
				
				goods.setId(rs.getString("id"));
				goods.setCatelog_id(rs.getString("catelog_id"));
				goods.setBianhao(rs.getString("bianhao"));
				
				goods.setMingcheng(rs.getString("mingcheng"));
				goods.setJieshao(rs.getString("jieshao"));
				goods.setFujian(rs.getString("fujian"));
				
				goods.setShichangjia(rs.getInt("shichangjia"));
				goods.setTejia(rs.getInt("tejia"));
				goods.setDel(rs.getString("del"));
				
				goodsList.add(goods);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		if(goodsList.size()>4)
		{
			goodsList=goodsList.subList(0, 4);
		}
		
		req.setAttribute("goodsList", goodsList);
		req.getRequestDispatcher("site/goods/goodsNew.jsp").forward(req, res);
	}
	
	
	
	
	public void goodsByCatelog(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//��Ʒ���
	{
        String catelog_id=req.getParameter("catelog_id");
		
		req.setAttribute("goodsList", liuService.goodsByCatelog(catelog_id));
		req.getRequestDispatcher("site/goods/goodsByCatelog.jsp").forward(req, res);
	}
	
	public void goodsDetailQian(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//��Ʒǰ̨��ϸ��Ϣ
	{
		String id=req.getParameter("id");
		
		req.setAttribute("goods", liuService.getGoods(id));
		req.getRequestDispatcher("site/goods/goodsDetailQian.jsp").forward(req, res);
	}
	
	
	public void goodsRes(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//��Ʒ֮ǰ����Ϣ
	{
		String mingcheng=req.getParameter("mingcheng");
		
		List goodsList=new ArrayList();
		String sql="select * from t_goods where del='no' and mingcheng like '%"+mingcheng.trim()+"%'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgoods goods=new Tgoods();
				
				goods.setId(rs.getString("id"));
				goods.setCatelog_id(rs.getString("catelog_id"));
				goods.setBianhao(rs.getString("bianhao"));
				
				goods.setMingcheng(rs.getString("mingcheng"));
				goods.setJieshao(rs.getString("jieshao"));
				goods.setFujian(rs.getString("fujian"));
				
				goods.setShichangjia(rs.getInt("shichangjia"));
				goods.setTejia(rs.getInt("tejia"));
				goods.setDel(rs.getString("del"));
				
				goodsList.add(goods);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("goodsList", goodsList);
		req.getRequestDispatcher("site/goods/goodsRes.jsp").forward(req, res);
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
		super.init(config);//���ø���init��������
	}
	
	public void destroy() 
	{
		
	}
}
