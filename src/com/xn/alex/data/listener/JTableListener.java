package com.xn.alex.data.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.xn.alex.data.action.LostValueAction;
import com.xn.alex.data.action.NormalizationAction;
import com.xn.alex.data.action.OddValueAction;
import com.xn.alex.data.common.ConfigElement;
import com.xn.alex.data.common.ConfigParser;
import com.xn.alex.data.common.CommonConfig.CURRENT_ACTION;
import com.xn.alex.data.window.MainWindow;
import com.xn.alex.data.window.UpdateObject;

public class JTableListener {
	
private static JTableListener jTableListenerHandler = null;

private JMenuItem mntmLostValue;

private JMenuItem mntmOddValue;

private JMenuItem mntmNormalization;
	
	private JTableListener(){
		
	}
	
	public static JTableListener Instance(){
		if(null == jTableListenerHandler){
			jTableListenerHandler = new JTableListener();
		}
		return jTableListenerHandler;
	}
	
	public void addListener(){
		
		if(null == MainWindow.Instance().getTable()){
			return;
		}
		
		try{	
			
		    addTabChangedListener();
		    
		    addJtableMouseListener();
		    
		    addColumnSelectListener();
		    
		}
		catch(Exception e){		    	
		    e.printStackTrace();			
		}
		
	}
	
	private void addTabChangedListener(){
		
        final DefaultTableModel dtm = (DefaultTableModel) MainWindow.Instance().getTable().getModel();
      	
    	dtm.addTableModelListener(new TableModelListener(){

			@Override
			public void tableChanged(TableModelEvent event) {
				
				if(event.getType() == TableModelEvent.UPDATE && MainWindow.getCurrentAct() == CURRENT_ACTION.NONE){
				// TODO Auto-generated method stub				
				
				int changedColumn = event.getColumn();
				
				int changedRow = event.getFirstRow();
				
				DefaultTableModel dtm = (DefaultTableModel) MainWindow.Instance().getTable().getModel();
				
				Object changedObj = dtm.getValueAt(changedRow, changedColumn);
				
				String columnChnName = MainWindow.getJtableColumnVec().get(changedColumn);
				
				String columnEnName = ConfigParser.chnToEnColumnName.get(columnChnName);
				
				ConfigElement element = ConfigParser.columnInfoMap.get(columnEnName);
				
				String fileName = MainWindow.treeNodeToFullPathMap.get(MainWindow.Instance().getCurrentNode().hashCode());
				
				String tableName = MainWindow.fileNameToTableMap.get(fileName);
				
				UpdateObject updateObj = new UpdateObject();
				
				updateObj.columIndex = changedColumn;
				
				updateObj.rowNumIndex = changedRow;
				
				updateObj.columnChnName = columnChnName;
				
				updateObj.columnEnName = ConfigParser.chnToEnColumnName.get(columnChnName);
				
				updateObj.columnValType = element.mValueType;
				
				updateObj.newValue = changedObj.toString();
				
				updateObj.tableName = tableName;
				
				MainWindow.Instance().getUpdateObjList().add(updateObj);
				}				
				
			}
    		
    	});
		
	}
	
	private void addJtableMouseListener(){
						
		MainWindow.Instance().getTable().addMouseListener(new MouseAdapter(){
    		
            public void mouseClicked(MouseEvent event){
    			
                if (! event.isShiftDown()){  
    				
                	MainWindow.Instance().getTable().clearSelection();  
                    
    	        }
                
                MainWindow.Instance().getTable().setColumnSelectionAllowed(false);
    			
                MainWindow.Instance().getTable().setRowSelectionAllowed(true);
    			
    	        //int rowNum = table.getSelectedRow();
    			
                //table.setRowSelectionInterval(rowNum, rowNum);
    			
    	        //table.getSelectedRow();
    			    			
    		}  		   		
    		
    	});
		
	}
	
	private void addColumnSelectListener(){
		
        final JTableHeader header = MainWindow.Instance().getTable().getTableHeader();
    	
    	header.addMouseListener(new MouseAdapter(){
    		
    		public void mouseClicked (MouseEvent event) {  
    			/*
    			if(event.getClickCount()!=1){
    				return;
    			}
    			*/
    			if (! event.isShiftDown()){  
    				
    				MainWindow.Instance().getTable().clearSelection();  
                    
    			}
    			
    			MainWindow.Instance().getTable().setColumnSelectionAllowed(true);
    			
    			MainWindow.Instance().getTable().setRowSelectionAllowed(false);
               
                int pick = header.columnAtPoint(event.getPoint());  
            
                MainWindow.Instance().getTable().addColumnSelectionInterval (pick, pick);                
                
                MainWindow.Instance().setSelectedColumnInds(MainWindow.Instance().getTable().getSelectedColumns());

    		}
    		
    		public void mousePressed(MouseEvent event) {
    			int mods = event.getModifiers();
    			
    			if((mods&InputEvent.BUTTON3_MASK)!=0){
    			
    			    JPopupMenu popMenu = new JPopupMenu();
    			    
    			    if(null == mntmLostValue){   			    	   			    	
    			        mntmLostValue = new JMenuItem("  缺失值处理    ");
    			        
    			        addLostValueMenuListener();
    			    }
    			    
    			    if(null == mntmOddValue){
    			        mntmOddValue = new JMenuItem(" 去除奇异值   ");
    			        
    			        addOddValueMenuListener();
    			    }
    			    
    			    if(null == mntmNormalization){
    			        mntmNormalization = new JMenuItem(" 数据归一化  ");
    			        
    			        addNormalizationMenuListener();
    			    }
    			    
    			    Vector<String> selecedColumnVec = MainWindow.Instance().getSelectedEnColumnVec();
    			    
    			    boolean isContainsNonNumeric = false;
    			    
    			    for(int i=0;i<selecedColumnVec.size();i++){
    			    	
    			    	String columnName = selecedColumnVec.get(i);
    			    	
    			    	if(ConfigParser.columnInfoMap.get(columnName).mValueType.contains("VARCHAR")){
    			    		
    			    		isContainsNonNumeric = true;
    			    		
    			    		break;
    			    	}
    			    	
    			    }
    			    
    			    popMenu.add(mntmLostValue);
    			    
    			    popMenu.add(mntmOddValue);
    			    
    			    if(false == isContainsNonNumeric){
    			        popMenu.add(mntmNormalization);
    			    }
    			
    			    popMenu.show(header, event.getX(), event.getY());
    			}
    		}
    		
    	});
    	   	    	   	
    } 
	
	private void addLostValueMenuListener(){
		
		mntmLostValue.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				LostValueAction.Instance().takeAction();
			}
			
		});
		
	}
	
	private void addOddValueMenuListener(){
		
		mntmOddValue.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				OddValueAction.Instance().takeAction();
			}
			
		});
		
	}
	
    private void addNormalizationMenuListener(){
		
    	mntmNormalization.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				NormalizationAction.Instance().takeAction();
			}
			
		});
		
	}

}
