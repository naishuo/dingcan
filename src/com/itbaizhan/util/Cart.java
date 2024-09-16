package com.itbaizhan.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.itbaizhan.orm.Tgoods;
import com.itbaizhan.orm.TorderItem;




public class Cart
{
	// ���ﳵ����
	// �������:Map������������Ʒ��id,value:������
	protected Map<String, TorderItem> items;

	public Cart()
	{

		if (items == null)
		{
			items = new HashMap<String, TorderItem>();
		}
	}

public void addGoods(String goodsId, TorderItem orderItem)//�����Ʒ�����ﳵ��
	{
	//�жϹ��ﳵ���Ƿ��Ѿ����ڸù�����,�������,��������;���������,��map����ӹ�����
		if (items.containsKey(goodsId)) //����
		{

			TorderItem _orderitem = items.get(goodsId); //��ù��ﳵ��ԭ���Ĺ�����
			_orderitem.setGoods_quantity(_orderitem.getGoods_quantity()+ orderItem.getGoods_quantity());
			items.put(goodsId, _orderitem);//��������
		} 
		else//������
		{

			items.put(goodsId, orderItem);//��������
		}
	}
	
	public void delGoods(String goodsId)//ɾ�����ﳵ�е���Ʒ��ɾ������id
	{
		items.remove(goodsId);
	}
	

	public void updateCart(String goodsId, int quantity)//���¹��ﳵ
	{

		TorderItem orderItem = items.get(goodsId);
		orderItem.setGoods_quantity(quantity);
		items.put(goodsId, orderItem);
	}

	public int getTotalPrice()//��ù��ﳵ����Ʒ���ܼ�
	{

		int totalPrice = 0;//�ܼ۳�ʼֵΪ0
		for (Iterator it = items.values().iterator(); it.hasNext();)
		{

			TorderItem orderItem = (TorderItem) it.next();
			Tgoods goods = orderItem.getGoods();//
			int quantity = orderItem.getGoods_quantity();//����
			totalPrice += goods.getTejia()* quantity;//�ܼ�=�ؼ�*����
		}
		return totalPrice;
	}

	public Map<String, TorderItem> getItems()//�����Ʒ
	{
		return items;
	}

}
