package com.xn.alex.data.process;

import com.xn.alex.data.window.MainWindow;

public class MenuItemDisable {
	
    private static MenuItemDisable menuItemDisableHandler = null;
	
	private MenuItemDisable(){
		
	}
	
	public static MenuItemDisable Instance(){
		if(null == menuItemDisableHandler){
			menuItemDisableHandler = new MenuItemDisable();
		}		
		return menuItemDisableHandler;		
	}
	
	public boolean disableFirstColumnMenu(){
		
		try{

			
		}
		catch(Exception e){
			
			return false;
		}
		
		return true;
		
	}
	
	public boolean disableSecondColumnMenu(){
		
        try{
        	
        	MainWindow.Instance().getMntmLostVal().setEnabled(false);
        	
        	MainWindow.Instance().getMntmHistogram().setEnabled(false);
        	
        	MainWindow.Instance().getMntmNormalization().setEnabled(false);
        	
        	MainWindow.Instance().getMntmRemoveOddValue().setEnabled(false);
        	
        	MainWindow.Instance().getMntmSkip().setEnabled(false);
			
		}
		catch(Exception e){
			
			e.printStackTrace();
			
			return false;
		}
		
		return true;
		
	}
	
    public boolean disableThirdColumnMenu(){
		
        try{
        	
        	MainWindow.Instance().getMntmKaFang().setEnabled(false);
        	
        	MainWindow.Instance().getMntmC4_45().setEnabled(false);
        	
        	MainWindow.Instance().getMntmZAPTree().setEnabled(false);
        	
        	MainWindow.Instance().getMntmlogic().setEnabled(false);
        	
        	MainWindow.Instance().getMntmRoc().setEnabled(false);
        	
        	MainWindow.Instance().getMntmProfitModel().setEnabled(false);
        	
            MainWindow.Instance().getMntmKaFang_2().setEnabled(false);
        	
        	MainWindow.Instance().getMntmC4_45_2().setEnabled(false);
        	
        	MainWindow.Instance().getMntmZAPTree_2().setEnabled(false);
        	
        	MainWindow.Instance().getMntmlogic_2().setEnabled(false);
        	
        	MainWindow.Instance().getMntmRoc_2().setEnabled(false);
        	
        	MainWindow.Instance().getMntmProfitModel_2().setEnabled(false);
        	
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
