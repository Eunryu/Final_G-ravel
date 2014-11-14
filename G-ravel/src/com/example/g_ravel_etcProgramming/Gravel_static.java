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
	
	//아이디 맞는지 안맞는지 커맨드 저장.
	public String get_SEARCH_ID_COMMEND(){
		return SEARCH_ID_COMMEND;
	}
	public void set_SEARCH_ID_COMMEND(String SEARCH_ID_COMMEND){
		this.SEARCH_ID_COMMEND = SEARCH_ID_COMMEND;
	}
	
	//지역이름 받아저장.
	public String get_SELECT_AREA(){
		return SELECT_AREA;
	}
	public void set_SELECT_AREA(String SELECT_AREA){
		this.SELECT_AREA = SELECT_AREA;
	}
	
	//가게이름 저장
	public String get_SELECT_SHOP_NAME(){
		return SELECT_SHOP_NAME;
	}
	public void set_SELECT_SHOP_NAME(String SELECT_SHOP_NAME){
		this.SELECT_SHOP_NAME = SELECT_SHOP_NAME;
	}
	
	//베스트 로드 일때 패키지 이름 저장.
	public String get_SELECT_PACKAGE(){
		return SELECT_PACKAGE;
	}
	public void set_SELECT_PACKAGE(String SELECT_PACKAGE){
		this.SELECT_PACKAGE = SELECT_PACKAGE;
	}
	
	//멤버인지 아닌지 확인
	public String get_KIMI_MEMBER(){
		return KIMI_MEMBER;
	}
	public void set_KIMI_MEMBER(String KIMI_MEMBER){
		this.KIMI_MEMBER = KIMI_MEMBER;
	}
}
