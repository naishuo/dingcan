package com.itbaizhan.orm;

public class Tcatelog //��Ʒ�����
{
	private String id;//��Ʒ���ID
	private String name;//��Ʒ�������
	private String del;//��Ʒ����Ƿ�ɾ����־
	
	public String getDel()//�õ��Ƿ�ɾ����Ϣ
	{
		return del;
	}
	public void setDel(String del)//�����Ƿ�ɾ����Ϣ
	{
		this.del = del;
	}
	public String getName()//�õ���Ʒ�������Ϣ
	{
		return name;
	}
	public void setName(String name)//���ò�Ʒ�������Ϣ
	{
		this.name = name;
	}
	public String getId()//�õ���Ʒ���ID��Ϣ
	{
		return id;
	}
	public void setId(String id)//���ò�Ʒ���ID��Ϣ
	{
		this.id = id;
	}

}
