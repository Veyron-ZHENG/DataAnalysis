package com.xn.alex.data.action;

import com.xn.alex.data.graphics.LineDataSheet;

public class RocAction extends WindowAction {
	
	private static RocAction rocActionHandler = null;
	
	 private RocAction(){
		 
	 }
	 
	 public static RocAction Instance(){
		 
		 if(null == rocActionHandler){
			 rocActionHandler = new RocAction();
		 }
		 
		 return rocActionHandler;		 
	 }
	 
	 public void takeAction(){
		 LineDataSheet.Instance().testCategoryLineDataSheet();
	 }

}
