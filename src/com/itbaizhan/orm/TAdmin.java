package com.itbaizhan.orm;

public class TAdmin //����Ա��
{
	private int userId;//����Ա�û�ID
	private String userName;//����Ա�û���
	private String userPw;//����Ա����
	
	
	public String getUserName()//�õ�����Ա�û���
	{
		return userName;
	}

	public void setUserName(String userName)//���ù���Ա�û���
	{
		this.userName = userName;
	}

	public String getUserPw()//�õ�����Ա����
	{
		return userPw;
	}

	public void setUserPw(String userPw)//���ù���Ա����
	{
		this.userPw = userPw;
	}

	public int getUserId()//�õ�����Ա�û�ID
	{
		return userId;
	}

	public void setUserId(int userId)//���ù���Ա�û�ID
	{
		this.userId = userId;
	}

}
