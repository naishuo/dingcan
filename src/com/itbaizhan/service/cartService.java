package com.itbaizhan.service;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.itbaizhan.util.Cart;



public class cartService
{
	public String modiNum(String goodsId,int quantity)//���幫����ı乺�ﳵ����Ʒ������
	{
		String result="";		//��ʼֵΪ��
			 WebContext ctx = WebContextFactory.get();//��ȡWebContext������
			 HttpSession session=ctx.getSession();//���session����
			 Cart cart=(Cart)session.getAttribute("cart");//�õ�cart������
			 cart.updateCart(goodsId, quantity);//������Ʒid��������Ϣ
			 session.setAttribute("cart", cart);//����cart������Ϊcart	 
			 result="yes";//result��־��ʾ�ɹ�
		return result;
	}
	                                                                                                                                                                                                                                                                            
	public String delGoodsFromCart(String goodsId)//�ӹ��ﳵ��ɾ����Ʒ
	{
		 WebContext ctx = WebContextFactory.get(); //��ȡWebContext������
		 HttpSession session=ctx.getSession();//���session����
		 Cart cart=(Cart)session.getAttribute("cart");
		 cart.delGoods(goodsId);
		 session.setAttribute("cart", cart);
		 return "yes";
	}
	
	
	public String clearCart()//��չ��ﳵ
	{
		 WebContext ctx = WebContextFactory.get(); 
		 HttpSession session=ctx.getSession();
		 Cart cart=(Cart)session.getAttribute("cart");
		 cart.getItems().clear();
		 session.setAttribute("cart", cart);
		 return "yes";
	}
	
}
