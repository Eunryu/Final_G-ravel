package com.example.g_ravel_etcProgramming;

import android.app.Application;

public class Gravel_static extends Application{
	public String randomNum;
	public String SEARCH_ID_COMMEND;
	public String SELECT_PACKAGE;
	public String SELECT_AREA;
	public String SELECT_SHOP_NAME;
	public String KIMI_MEMBER;
	
	public void OnCreate(){
		randomNum = null;
		SEARCH_ID_COMMEND = null;
		SELECT_PACKAGE = null;
		SELECT_AREA = null;
		SELECT_SHOP_NAME = null;
		KIMI_MEMBER = null;
		super.onCreate();
	}
	public void OnTerminate(){
		super.onTerminate();
	}
	
	public String getrandomNum(){
		return randomNum;
	}
	public void setrandomNum(String randomNum){
		this.randomNum = randomNum;
	}
	
	//���̵� �´��� �ȸ´��� Ŀ�ǵ� ����.
	public String get_SEARCH_ID_COMMEND(){
		return SEARCH_ID_COMMEND;
	}
	public void set_SEARCH_ID_COMMEND(String SEARCH_ID_COMMEND){
		this.SEARCH_ID_COMMEND = SEARCH_ID_COMMEND;
	}
	
	//�����̸� �޾�����.
	public String get_SELECT_AREA(){
		return SELECT_AREA;
	}
	public void set_SELECT_AREA(String SELECT_AREA){
		this.SELECT_AREA = SELECT_AREA;
	}
	
	//�����̸� ����
	public String get_SELECT_SHOP_NAME(){
		return SELECT_SHOP_NAME;
	}
	public void set_SELECT_SHOP_NAME(String SELECT_SHOP_NAME){
		this.SELECT_SHOP_NAME = SELECT_SHOP_NAME;
	}
	
	//����Ʈ �ε� �϶� ��Ű�� �̸� ����.
	public String get_SELECT_PACKAGE(){
		return SELECT_PACKAGE;
	}
	public void set_SELECT_PACKAGE(String SELECT_PACKAGE){
		this.SELECT_PACKAGE = SELECT_PACKAGE;
	}
	
	//������� �ƴ��� Ȯ��
	public String get_KIMI_MEMBER(){
		return KIMI_MEMBER;
	}
	public void set_KIMI_MEMBER(String KIMI_MEMBER){
		this.KIMI_MEMBER = KIMI_MEMBER;
	}
}
