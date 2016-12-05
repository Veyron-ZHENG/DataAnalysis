package com.xn.alex.data.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JMenuItem;

import com.xn.alex.data.action.AboutAction;
import com.xn.alex.data.action.C45Action;
import com.xn.alex.data.action.CloseAction;
import com.xn.alex.data.action.LostValueAction;
import com.xn.alex.data.action.NormalizationAction;
import com.xn.alex.data.action.OddValueAction;
import com.xn.alex.data.action.OpenAction;
import com.xn.alex.data.action.RocAction;
import com.xn.alex.data.action.SaveAction;
import com.xn.alex.data.action.SkipAction;
import com.xn.alex.data.action.WindowAction;
import com.xn.alex.data.common.CommonConfig.CURRENT_ACTION;
import com.xn.alex.data.common.ConfigParser;
import com.xn.alex.data.common.SoftWareLicence;
import com.xn.alex.data.database.DatabaseAction;
import com.xn.alex.data.database.DatabaseConstant;
import com.xn.alex.data.graphics.CategoryDataSheet;
import com.xn.alex.data.listener.JButtonListener;
import com.xn.alex.data.listener.JTableListener;
import com.xn.alex.data.listener.JTreeListener;

public class MainWindow extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpenFile;	
	private JMenuItem mntmSaveFile;
	private JMenuItem mntmImportRule;
	private JMenuItem mntmSaveRule;
	private JMenuItem mntmCloseFile;
	private JMenuItem mntmExit;
    private JMenu mnFilter;
    private JMenuItem mntmLostVal;
    private JMenuItem mntmHistogram;
    private JMenuItem mntmNormalization;
    private JMenuItem mntmRemoveOddValue;
    private JMenuItem mntmSkip;
    private JMenu mnSmart;
    private JMenu mnInCallDist;
    private JMenuItem mntmKaFang;
    private JMenuItem mntmC4_45;
    private JMenuItem mntmZAPTree;
    private JMenuItem mntmlogic;
    private JMenuItem mntmRoc;
    private JMenuItem mntmProfitModel;
    private JMenu mnUeSale;
    private JMenuItem mntmKaFang_2;
    private JMenuItem mntmC4_45_2;
    private JMenuItem mntmZAPTree_2;
    private JMenuItem mntmlogic_2;
    private JMenuItem mntmRoc_2;
    private JMenuItem mntmProfitModel_2;
    private JMenuItem mntmTariffpSale;
    private JMenuItem mntmTransfer;
    private JMenuItem mntmCuKeep;
    
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	
	
	private JSplitPane splitPane;
	private JSplitPane splitPane_1;
	
	private JScrollPane scrollPane;
	private DefaultMutableTreeNode rootNode;

	private JTree tree;

	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JTextArea textArea;
	private JToolBar toolBar;
	
	private JButton openButton;
	private JButton saveButton;
	private JButton closeButton;
	
	public static Map<String, String> fileNameToTableMap = new ConcurrentHashMap<String, String>();
	
	public static Map<Integer, String> treeNodeToFullPathMap = new ConcurrentHashMap<Integer, String>();
	
	private static Vector<String> columnVec = new Vector<String>();
	
	private static Vector<Vector<String>> valueVec = new Vector<Vector<String>>();
	
	private List<UpdateObject> updateObjList = new ArrayList<UpdateObject>();
	
	private static MainWindow mainWindowHandler = null;
	
	private WindowAction action;
	
	private int[] selectedColumnInds;	

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public JTree getTree() {
		return tree;
	}

	public void setTree(JTree tree) {
		this.tree = tree;
	}
	
	public List<UpdateObject> getUpdateObjList() {
		return updateObjList;
	}

	public void setUpdateObjList(List<UpdateObject> updateObjList) {
		this.updateObjList = updateObjList;
	}

	private static CURRENT_ACTION currentAct = CURRENT_ACTION.NONE;

	public static CURRENT_ACTION getCurrentAct() {
		return currentAct;
	}

	public static void setCurrentAct(CURRENT_ACTION currentAct) {
		MainWindow.currentAct = currentAct;
	}
	
	public DefaultMutableTreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(DefaultMutableTreeNode rootNode) {
		this.rootNode = rootNode;
	}

	private DefaultMutableTreeNode currentNode;
	public DefaultMutableTreeNode getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(DefaultMutableTreeNode currentNode) {
		this.currentNode = currentNode;
	}
	
	public JMenuItem getMntmOpenFile() {
		return mntmOpenFile;
	}

	public JMenuItem getMntmImportRule() {
		return mntmImportRule;
	}

	public JMenuItem getMntmSaveRule() {
		return mntmSaveRule;
	}

	public JMenuItem getMntmLostVal() {
		return mntmLostVal;
	}

	public JMenuItem getMntmHistogram() {
		return mntmHistogram;
	}

	public JMenuItem getMntmNormalization() {
		return mntmNormalization;
	}

	public JMenuItem getMntmRemoveOddValue() {
		return mntmRemoveOddValue;
	}

	public JMenuItem getMntmSkip() {
		return mntmSkip;
	}

	public JMenu getMnSmart() {
		return mnSmart;
	}

	public JMenu getMnInCallDist() {
		return mnInCallDist;
	}

	public JMenuItem getMntmKaFang() {
		return mntmKaFang;
	}

	public JMenuItem getMntmC4_45() {
		return mntmC4_45;
	}

	public JMenuItem getMntmZAPTree() {
		return mntmZAPTree;
	}

	public JMenuItem getMntmlogic() {
		return mntmlogic;
	}

	public JMenuItem getMntmRoc() {
		return mntmRoc;
	}

	public JMenuItem getMntmProfitModel() {
		return mntmProfitModel;
	}

	public JMenu getMnUeSale() {
		return mnUeSale;
	}

	public JMenuItem getMntmTariffpSale() {
		return mntmTariffpSale;
	}

	public JMenuItem getMntmTransfer() {
		return mntmTransfer;
	}

	public JMenuItem getMntmCuKeep() {
		return mntmCuKeep;
	}
	
	public JMenuItem getMntmKaFang_2() {
		return mntmKaFang_2;
	}

	public JMenuItem getMntmC4_45_2() {
		return mntmC4_45_2;
	}

	public JMenuItem getMntmZAPTree_2() {
		return mntmZAPTree_2;
	}

	public JMenuItem getMntmlogic_2() {
		return mntmlogic_2;
	}

	public JMenuItem getMntmRoc_2() {
		return mntmRoc_2;
	}

	public JMenuItem getMntmProfitModel_2() {
		return mntmProfitModel_2;
	}
	
	public int[] getSelectedColumnInds() {
		return selectedColumnInds;
	}

	public void setSelectedColumnInds(int[] selectedColumnInds) {
		this.selectedColumnInds = selectedColumnInds;
	}
	
	public JButton getOpenButton() {
		return openButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public JButton getCloseButton() {
		return closeButton;
	}

	/**
	 * Create the frame.
	 */
	private MainWindow() {
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent e){				
				if(JOptionPane.showConfirmDialog(null,"是否退出")==JOptionPane.OK_OPTION){
					SoftWareLicence.Instance().setLeftTime();
				    System.exit(0);
				}
				
			}						
		});
				
	}
	
	public static MainWindow Instance(){
		
		if(null == mainWindowHandler){
			mainWindowHandler = new MainWindow();
		}
		return mainWindowHandler;
	}
	
	public void createWindow(){		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		
		createMeanu();
		
		createPanel();
		
		addListenerForTree();
		
		addListenserForJtable();
		
		addListenserForJButton();
				
		setOutPutToTextArea();
		
		loadUnDeleteFileLastTime();
		
	}
	
    private void loadUnDeleteFileLastTime(){
		
		try{
		
		    ResultSet rs = DatabaseAction.Instance().getAllResult(DatabaseConstant.FILE_TO_TABLE);
		
		    while(rs.next()){
		    	
		    	String fileName = rs.getString(DatabaseConstant.FILE_FULL_PATH);
		    	
		    	String tableName = rs.getString(DatabaseConstant.TABLE_NAME);
		    	
                fileNameToTableMap.put(fileName, tableName);
                
                action = OpenAction.Instance();
                ((OpenAction) action).addToTreeNode(fileName , rootNode);
                
                treeNodeToFullPathMap.put(currentNode.hashCode(), fileName);
						
		    }
		
		
		    DatabaseAction.Instance().closeCurrentConnection();
		}
		catch(Exception e){
			
			DatabaseAction.Instance().closeCurrentConnection();
			
		}
	}
	
	
	private void createMeanu(){
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
	    mnFile = new JMenu("文件");
		menuBar.add(mnFile);
		
		mntmOpenFile = new JMenuItem("  导入数据");
		mnFile.add(mntmOpenFile);
		mntmOpenFile.addActionListener(this);
		
		mntmSaveFile = new JMenuItem("  保存");
		mnFile.add(mntmSaveFile);
		mntmSaveFile.addActionListener(this);
		
		mnFile.addSeparator();
		
		mntmImportRule = new JMenuItem("  导入规则");
		mnFile.add(mntmImportRule);
		mntmImportRule.addActionListener(this);
		
		mntmSaveRule = new JMenuItem("  保存规则");
		mnFile.add(mntmSaveRule);
		mntmSaveRule.addActionListener(this);
		
		mnFile.addSeparator();
		
		mntmCloseFile = new JMenuItem("  关闭");
		mnFile.add(mntmCloseFile);
		mntmCloseFile.addActionListener(this);
		
		mnFile.addSeparator();
		mntmExit = new JMenuItem("  退出");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(this);
		
		mnFilter = new JMenu(" 数据处理");
		menuBar.add(mnFilter);
		
		mntmLostVal = new JMenuItem("  缺失值处理");
		mnFilter.add(mntmLostVal);
		mntmLostVal.addActionListener(this);
		
		mntmHistogram = new JMenuItem("  直方图");
		mnFilter.add(mntmHistogram);
		mntmHistogram.addActionListener(this);
		
		mntmRemoveOddValue = new JMenuItem("  去除奇异值");
		mnFilter.add(mntmRemoveOddValue);
		mntmRemoveOddValue.addActionListener(this);
				
		mntmNormalization = new JMenuItem("  数据归一化");
		mnFilter.add(mntmNormalization);
		mntmNormalization.addActionListener(this);
		
		mntmSkip = new JMenuItem("  跳过");
		mnFilter.add(mntmSkip);
		mntmSkip.addActionListener(this);
		
		mnSmart = new JMenu(" 智慧运营");
		menuBar.add(mnSmart);
		
	    mnInCallDist = new JMenu("  呼入分流");
	    mnSmart.add(mnInCallDist);
	    
	    mntmKaFang = new JMenuItem("  卡方树");
	    mnInCallDist.add(mntmKaFang);
	    mntmKaFang.addActionListener(this);
	    
	    mntmC4_45 = new JMenuItem("  C4.5");
	    mnInCallDist.add(mntmC4_45);
	    mntmC4_45.addActionListener(this);
	    
	    mntmZAPTree = new JMenuItem("  ZAP树");
	    mnInCallDist.add(mntmZAPTree);
	    mntmZAPTree.addActionListener(this);

	    mntmlogic = new JMenuItem("  逻辑回归");
	    mnInCallDist.add(mntmlogic);
	    mntmlogic.addActionListener(this);
	    
	    mntmRoc = new JMenuItem("  ROC");
	    mnInCallDist.add(mntmRoc);
	    mntmRoc.addActionListener(this);
	    
	    mntmProfitModel = new JMenuItem("  利润模型");
	    mnInCallDist.add(mntmProfitModel);
	    mntmProfitModel.addActionListener(this);
	    
	    mnUeSale = new JMenu("  终端营销");
        mnSmart.add(mnUeSale);
                   
        mntmKaFang_2 = new JMenuItem("  卡方树");
        mnUeSale.add(mntmKaFang_2);
	    mntmKaFang_2.addActionListener(this);
	    
	    mntmC4_45_2 = new JMenuItem("  C4.5");
	    mnUeSale.add(mntmC4_45_2);
	    mntmC4_45_2.addActionListener(this);
	    
	    mntmZAPTree_2 = new JMenuItem("  ZAP树");
	    mnUeSale.add(mntmZAPTree_2);
	    mntmZAPTree_2.addActionListener(this);

	    mntmlogic_2 = new JMenuItem("  逻辑回归");
	    mnUeSale.add(mntmlogic_2);
	    mntmlogic_2.addActionListener(this);
	    
	    mntmRoc_2 = new JMenuItem("  ROC");
	    mnUeSale.add(mntmRoc_2);
	    mntmRoc_2.addActionListener(this);
	    
	    mntmProfitModel_2 = new JMenuItem("  利润模型");
	    mnUeSale.add(mntmProfitModel_2);
	    mntmProfitModel_2.addActionListener(this);
        
	    mntmTariffpSale = new JMenuItem("  套餐营销");
	    mnSmart.add(mntmTariffpSale);
	    mntmTariffpSale.addActionListener(this);

	    mntmTransfer = new JMenuItem("  掌厅迁移");
	    mnSmart.add(mntmTransfer);
	    mntmTransfer.addActionListener(this);

	    mntmCuKeep = new JMenuItem("  客户保有");
	    mnSmart.add(mntmCuKeep);
	    mntmCuKeep.addActionListener(this);
						
		mnHelp = new JMenu(" 帮助");
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem(" 关于");
		mnHelp.add(mntmAbout);
		mntmAbout.addActionListener(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	private void createPanel(){
		
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.8);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.2);
		splitPane.setLeftComponent(splitPane_1);
		
		scrollPane = new JScrollPane();
		splitPane_1.setLeftComponent(scrollPane);
		
		rootNode = new DefaultMutableTreeNode("项目文件");
		tree = new JTree(rootNode);
		tree.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tree.setPreferredSize(new Dimension(200, HEIGHT));
		scrollPane.setViewportView(tree);
		
		scrollPane_2 = new JScrollPane();
		splitPane_1.setRightComponent(scrollPane_2);
		
		
		table = new JTable();
		DefaultTableModel dtm = new DefaultTableModel();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(dtm);
		getColumnNames(columnVec);
		dtm.setDataVector(valueVec, columnVec);
		dtm.fireTableStructureChanged();
		dtm.fireTableDataChanged();
		scrollPane_2.setViewportView(table);
		
		scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1.setViewportView(textArea);
		
		toolBar = new JToolBar();
		openButton = generateToolBarButton("resource/open.png");
		openButton.addActionListener(this);
		saveButton = generateToolBarButton("resource/save.png");
		saveButton.addActionListener(this);
		closeButton = generateToolBarButton("resource/delete.png");
		closeButton.addActionListener(this);
		toolBar.add(openButton);
		toolBar.add(saveButton);
		toolBar.add(closeButton);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
	}
	
	public static Vector<String> getJtableColumnVec(){
		
		return columnVec;
		
	}
	
	public static Vector<Vector<String>> getJtableValueVec(){
		
		return valueVec;
		
	}
	
	private JButton generateToolBarButton(String fileName){
		ImageIcon imageicon = null;
		
		if(null != fileName){		
			
		    imageicon = new ImageIcon(fileName);
		
		    Image image = imageicon.getImage();
		
		    image = image.getScaledInstance(22, 22, Image.SCALE_DEFAULT);
		
		    imageicon.setImage(image);		
		}
		
		JButton button = new JButton(imageicon);
		
		button.setMargin(new Insets(0,0,0,0));
		
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		
		return button;
	}
	
	public void getColumnNames(Vector<String> columnVec){
			
		//only show 20 columns in startup when too many columns in config.xml
		for(int i=0; i<20; i++){
			
			String columnName = ConfigParser.columnVecInConfigOrder.get(i);
			
			columnVec.add(columnName);		
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		action = null;
        if(event.getSource() == mntmOpenFile || event.getSource() == openButton){       
        	action = OpenAction.Instance();        	
        }
        else if(event.getSource() == mntmCloseFile || event.getSource() == closeButton){
        	
        	if(currentNode == null){
        		return;
        	}
        	
        	action = CloseAction.Instance();        	       	
        }
        else if(event.getSource() == mntmSaveFile || event.getSource() == saveButton){
        	action = SaveAction.Instance();
        }
        else if(event.getSource() == mntmExit){
        	System.exit(0);
        }
        else if(event.getSource() == mntmNormalization){      	
        	action = NormalizationAction.Instance();
        }
        else if(event.getSource() == mntmRemoveOddValue){
        	action = OddValueAction.Instance();       	
        }
        else if(event.getSource() == mntmAbout){
            action = AboutAction.Instance();
        }
        else if(event.getSource() == mntmLostVal){
        	LostValueAction.Instance().takeAction();
        }
        else if(event.getSource() == mntmC4_45_2 || event.getSource() == mntmC4_45){
        	action = C45Action.Instance();
        }
        else if(event.getSource() == mntmRoc_2 || event.getSource() == mntmRoc){
        	action = RocAction.Instance();
        }
        else if(event.getSource() == mntmSkip){
        	action = SkipAction.Instance();
        }
        else if(event.getSource() == mntmProfitModel_2 || event.getSource() == mntmProfitModel){
        	CategoryDataSheet.Instance().testCategoryDataSheet();
        }
        
        
        if(null!=action){
        	action.takeAction();
        }

		
	}
			    
    private void addListenerForTree(){
    	
    	JTreeListener.Instance().addListener();
    	
    }
    
    private void setOutPutToTextArea(){
    	
    	TextAreaOutPutControl.Instance().setTextArea(textArea);
    	
    	TextAreaOutPutControl.Instance().Initialize();
    	
    }
    
    private void addListenserForJtable(){ 
    	
    	JTableListener.Instance().addListener();   	
    	   	
    } 
    
    private void addListenserForJButton(){
    	
    	JButtonListener.Instance().addListener();
    	
    }
    
    public Vector<String> getSelectedChnColumnVec(){
    	
    	if(null == selectedColumnInds){
    		return null;
    	}
    	
    	Vector<String> selectedChnColumnVec = new Vector<String>();
    	
    	for(int i=0;i<selectedColumnInds.length;i++){
    		
    		String ChnColumnName = columnVec.get(selectedColumnInds[i]);
    		
    		if(null == ChnColumnName){
    			return null;
    		}
    		
    		selectedChnColumnVec.add(ChnColumnName);
    		
    	}
    	
    	return selectedChnColumnVec;
    	
    }
    
    public Vector<String> getSelectedEnColumnVec(){
    	
    	if(null == selectedColumnInds){
    		return null;
    	}
    	
    	Vector<String> selectedEnColumnVec = new Vector<String>();
    	     	
        for(int i=0;i<selectedColumnInds.length;i++){
    		
    		String ChnColumnName = columnVec.get(selectedColumnInds[i]);
    		
    		String EnColumnName = ConfigParser.chnToEnColumnName.get(ChnColumnName);
    		
    		if(null == EnColumnName){
    			return null;
    		}
    		
    		selectedEnColumnVec.add(EnColumnName);
    		
    	}
    	
    	return selectedEnColumnVec;
    	
    }
	
}
	
