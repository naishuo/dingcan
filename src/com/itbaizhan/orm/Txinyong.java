package com.itbaizhan.orm;

public class Txinyong
{
	private String id;//����Ա���û�����ID
	private String shuxing;//����Ա���û���������
	private String neirong;//����Ա���û���������
	private String shijian;//����Ա���û�����ʱ��
	
	private String user_id;//�û�ID

	public String getId()//�õ�����ID
	{
		return id;
	}

	public void setId(String id)//��������ID
	{
		this.id = id;
	}

	public String getShuxing()//�õ���������
	{
		return shuxing;
	}

	public void setShuxing(String shuxing)//������������
	{
		this.shuxing = shuxing;
	}

	public String getNeirong()//�õ���������
	{
		return neirong;
	}

	public void setNeirong(String neirong)//������������
	{
		this.neirong = neirong;
	}

	public String getShijian()//�õ�����ʱ��
	{
		return shijian;
	}

	public void setShijian(String shijian)//��������ʱ��
	{
		this.shijian = shijian;
	}

	public String getUser_id()//�õ��û�ID
	{
		return user_id;
	}

	public void setUser_id(String user_id)//�����û�ID
	{
		this.user_id = user_id;
	}
	
}
