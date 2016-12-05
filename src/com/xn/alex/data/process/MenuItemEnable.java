package com.xn.alex.data.process;

import com.xn.alex.data.window.MainWindow;

public class MenuItemEnable {
	
	private static MenuItemEnable menuItemEnableHandler = null;
	
	private MenuItemEnable(){
		
	}
	
	public static MenuItemEnable Instance(){
		if(null == menuItemEnableHandler){
			menuItemEnableHandler = new MenuItemEnable();
		}		
		return menuItemEnableHandler;		
	}
	
public boolean enableFirstColumnMenu(){
		
		try{

			
		}
		catch(Exception e){
			
			return false;
		}
		
		return true;
		
	}
	
	public boolean enableSecondColumnMenu(){
		
        try{
        	
        	MainWindow.Instance().getMntmLostVal().setEnabled(true);
        	
        	MainWindow.Instance().getMntmHistogram().setEnabled(true);
        	
        	MainWindow.Instance().getMntmNormalization().setEnabled(true);
        	
        	MainWindow.Instance().getMntmRemoveOddValue().setEnabled(true);
        	
        	MainWindow.Instance().getMntmSkip().setEnabled(true);
			
		}
		catch(Exception e){
			
			e.printStackTrace();
			
			return false;
		}
		
		return true;
		
	}
	
    public boolean enableThirdColumnMenu(){
		
        try{
        	
        	MainWindow.Instance().getMntmKaFang().setEnabled(false);
        	
        	MainWindow.Instance().getMntmC4_45().setEnabled(true);
        	
        	MainWindow.Instance().getMntmZAPTree().setEnabled(false);
        	
        	MainWindow.Instance().getMntmlogic().setEnabled(false);
        	
        	MainWindow.Instance().getMntmRoc().setEnabled(true);
        	
        	MainWindow.Instance().getMntmProfitModel().setEnabled(true);
        	
            MainWindow.Instance().getMntmKaFang_2().setEnabled(false);
        	
        	MainWindow.Instance().getMntmC4_45_2().setEnabled(true);
        	
        	MainWindow.Instance().getMntmZAPTree_2().setEnabled(false);
        	
        	MainWindow.Instance().getMntmlogic_2().setEnabled(false);
        	
        	MainWindow.Instance().getMntmRoc_2().setEnabled(true);
        	
        	MainWindow.Instance().getMntmProfitModel_2().setEnabled(true);
        	
        	MainWindow.Instance().getMntmTariffpSale().setEnabled(false);
        	
        	MainWindow.Instance().getMntmTransfer().setEnabled(false);
        	
        	MainWindow.Instance().getMntmCuKeep().setEnabled(false);
			
		}
		catch(Exception e){
			
			return false;
		}
		
		return true;
		
	}

}
