package com.itbaizhan.orm;

public class TorderItem
{
	private String id;//������ϸID
	private String order_id;//����ID
	private String goods_id;//��ƷID
	private int goods_quantity;//��Ʒ����
	
	
	private Tgoods goods;//����û�С��Լ��ӵ�
	
	public int getGoods_quantity()//�õ���Ʒ����
	{
		return goods_quantity;
	}
	public void setGoods_quantity(int goods_quantity)//������Ʒ����
	{
		this.goods_quantity = goods_quantity;
	}
	
	public String getGoods_id()//�õ���ƷID
	{
		return goods_id;
	}
	public void setGoods_id(String goods_id)//������ƷID
	{
		this.goods_id = goods_id;
	}
	public String getId()//�õ�������ϸID
	{
		return id;
	}
	public void setId(String id)//���ö�����ϸID
	{
		this.id = id;
	}
	public String getOrder_id()//�õ�����ID
	{
		return order_id;
	}
	public void setOrder_id(String order_id)//���ö���ID
	{
		this.order_id = order_id;
	}
	public Tgoods getGoods()//�õ���Ʒ��Ϣ
	{
		return goods;
	}
	public void setGoods(Tgoods goods)//������Ʒ��Ϣ
	{
		this.goods = goods;
	}
	
	

}
