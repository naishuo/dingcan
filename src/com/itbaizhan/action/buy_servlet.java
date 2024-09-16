
package com.itbaizhan.action;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itbaizhan.orm.Tgoods;
import com.itbaizhan.orm.Torder;
import com.itbaizhan.orm.TorderItem;
import com.itbaizhan.orm.Tuser;
import com.itbaizhan.service.liuService;
import com.itbaizhan.util.Cart;


public class buy_servlet extends HttpServlet
{
	
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	//request����req��response����res
	{
        String type=req.getParameter("type");//�õ�type���Ը���type
		
		if(type.endsWith("addToCart"))//���URL����Ĳ�����addToCart
		{
			addToCart(req, res);//ִ����ӹ��ﳵ
		}
		if(type.endsWith("orderSubmit"))//���URL����Ĳ�����orderSubmit
		{
			orderSubmit(req, res);//ִ���ύ��������
		}
		if(type.endsWith("myorder"))
		{
			myorder(req, res);//ִ���ҵĶ�������
		}
		if(type.endsWith("orderDetail"))
		{
			orderDetail(req, res);//ִ�ж�����ϸ����
		}
		
		
		
	}
	
	
	public void addToCart(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//�����Ʒ�����ﳵ
	{
		String goods_id=req.getParameter("goods_id");//��ƷID
		int quantity=Integer.parseInt(req.getParameter("quantity"));//��Ʒ����
		Tgoods goods=liuService.getGoods(goods_id);//��ƷID������good
		
		TorderItem orderItem=new TorderItem();
		orderItem.setGoods(goods);//������ƷID
		orderItem.setGoods_quantity(quantity);//������Ʒ����
		
		HttpSession session=req.getSession();
		Cart cart =(Cart)session.getAttribute("cart");
		cart.addGoods(goods_id, orderItem);
		
		session.setAttribute("cart", cart);
		
		req.setAttribute("message", "�����ɹ�");
		req.setAttribute("path", "site/cart/mycart.jsp");//��ӹ��ﳵ�ɹ���ת����ҳ��
		
		String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);//��ת��targetURL
		
	}
	
	
	
	public void orderSubmit(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//�ύ����
	{
		String songhuodizhi=req.getParameter("songhuodizhi");//�õ��ͻ���ַ
		String fukuanfangshi=req.getParameter("fukuanfangshi");//�õ����ʽ
		
		HttpSession session=req.getSession();
		Cart cart =(Cart)session.getAttribute("cart");
		Tuser user=(Tuser)session.getAttribute("user");
		
		Torder order=new Torder();
		
		order.setId(String.valueOf(new Date().getTime()));//���ö���ID����ʱ��ĸ�ʽ
		order.setBianhao(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));//���ö�����ţ���ʽΪyyyyMMddhhmmss
		order.setShijian(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));//�����µ�ʱ�䣬��ʽΪyyyy-MM-dd hh:mm:ss
		order.setZhuangtai("no");
		
		order.setHuifu("");
		
		order.setSonghuodizhi(songhuodizhi);
		order.setFukuanfangshi(fukuanfangshi);
		order.setJine(cart.getTotalPrice());//�����ܽ��
		order.setUser_id(user.getId());
		
		liuService.saveOrder(order);
		
		for (Iterator it = cart.getItems().values().iterator(); it.hasNext();)
		//��������������it��ֵΪ��Ʒ������ֵ��������������滹�ж�����ִ��ѭ��
		{

			TorderItem orderItem = (TorderItem) it.next();//��ȡ��һ������ת����TorderItem���󣬸���TorderItem����orderItem
			
			String id=String.valueOf(new Date().getTime());//�õ����ID
			String order_id=order.getId();//�õ�����ID
			String goods_id=orderItem.getGoods().getId();
			int goods_quantity=orderItem.getGoods_quantity();
			liuService.saveOrderItem(id, order_id, goods_id, goods_quantity);//����id, order_id, goods_id, goods_quantity
			
		}
		
		cart.getItems().clear(); //��չ��ﳵ
		session.setAttribute("cart", cart);
		
		req.setAttribute("order", order);
		req.getRequestDispatcher("site/order/orderSubmit.jsp").forward(req, res);//��ת���������
	}
	
	
	public void myorder(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//�ҵĶ���
	{
		HttpSession session=req.getSession();
		Tuser user=(Tuser)session.getAttribute("user");
		
		req.setAttribute("orderList", liuService.orderList(user.getId()));//����orderList������
		req.getRequestDispatcher("site/order/myorder.jsp").forward(req, res);
	}
	
	public void orderDetail(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	//������ϸ
	{
		String order_id=req.getParameter("order_id");	
		System.out.println(order_id+"DD");//���order_idDD
		req.setAttribute("orderItemList", liuService.orderItemList(order_id));
		req.getRequestDispatcher("site/order/orderDetail.jsp").forward(req, res);
	}
	
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	//��ת����һ����
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
	//��ʼ��Servlet����
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
