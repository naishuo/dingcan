package com.itbaizhan.orm;

public class Tgoods //��Ʒ��Ϣ��
{
	private String id;//��Ʒ��ID
	private String catelog_id;//��Ʒ����ID
	private String bianhao;//��Ʒ�ı��
	private String mingcheng;//��Ʒ������
	
	private String jieshao;//��Ʒ�Ľ���
	private String fujian;//��Ʒ��ͼƬ
	private int shichangjia;//��Ʒ���г���
	private int tejia;//��Ʒ���ؼ�
	
	private String del;//��Ʒ�Ƿ�ɾ����־

	public String getBianhao()//�õ���Ʒ���
	{
		return bianhao;
	}

	public void setBianhao(String bianhao)//���ò�Ʒ���
	{
		this.bianhao = bianhao;
	}

	public String getCatelog_id()//�õ���Ʒ���ID
	{
		return catelog_id;
	}

	public void setCatelog_id(String catelog_id)//���ò�Ʒ���ID
	{
		this.catelog_id = catelog_id;
	}

	public String getDel()//�õ���Ʒ�Ƿ�ɾ����Ϣ
	{
		return del;
	}

	public void setDel(String del)//���ò�Ʒ�Ƿ�ɾ����Ϣ
	{
		this.del = del;
	}

	public String getFujian()//�õ���ƷͼƬ��Ϣ
	{
		return fujian;
	}

	public void setFujian(String fujian)//���ò�ƷͼƬ��Ϣ
	{
		this.fujian = fujian;
	}

	public String getId()//�õ���ƷID��Ϣ
	{
		return id;
	}

	public void setId(String id)//���ò�ƷID��Ϣ
	{
		this.id = id;
	}

	public String getJieshao()//�õ���Ʒ������Ϣ
	{
		return jieshao;
	}

	public void setJieshao(String jieshao)//���ò�Ʒ������Ϣ
	{
		this.jieshao = jieshao;
	}

	public String getMingcheng()//�õ���Ʒ������Ϣ
	{
		return mingcheng;
	}

	public void setMingcheng(String mingcheng)//���ò�Ʒ������Ϣ
	{
		this.mingcheng = mingcheng;
	}

	public int getShichangjia()//�õ���Ʒ�г�����Ϣ
	{
		return shichangjia;
	}

	public void setShichangjia(int shichangjia)//���ò�Ʒ�г�����Ϣ
	{
		this.shichangjia = shichangjia;
	}

	public int getTejia()//�õ���Ʒ�ؼ���Ϣ
	{
		return tejia;
	}

	public void setTejia(int tejia)//���ò�Ʒ���ؼ���Ϣ
	{
		this.tejia = tejia;
	}
		
}
