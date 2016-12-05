package com.xn.alex.data.action;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.xn.alex.data.graphics.TreeDataSheet;

public class C45Action extends WindowAction{
	
	private static C45Action c45ActionHandler = null;
	
	private JFrame frame;
	
	private JPanel contentPane;
	
	private JTextField treewidthTextField;
	
	private JTextField treeDeepthTextField;
	
	private JButton okBt;
	
	private JButton cancelBt;
	
	private int treeWidth = -1;
	
	private int treeDeepth = -1;
	
	private C45Action(){
		 
	 }
	 
	 public static C45Action Instance(){
		 
		 if(null == c45ActionHandler){
			 c45ActionHandler = new C45Action();
		 }
		 
		 return c45ActionHandler;		 
	 }
	 
	 public void takeAction(){
		 try{
			 
		     createWindow();
		     
		     addListener();
		     
		     
		 }
		 catch(Exception e){
			 
			 System.out.print("����C45��ʧ��");
		 }
		 
	 }
	 
	 private void createWindow(){
		 
		 frame = new JFrame("C45��");
		 
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	     
	     contentPane = new JPanel();
	     
	     contentPane.setLayout(new BorderLayout());
	     
	     JPanel topPanel = new JPanel();
	     
	     GridLayout layout = new GridLayout(0,2);
	     
	     topPanel.setLayout(layout);
	     
	     layout.setVgap(15);
	     
	     layout.setHgap(5);
	     
	     topPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 30));
	     
	     JLabel treewidthLabel = new JLabel("���������Ŀ��");
	     
	     topPanel.add(treewidthLabel);
	     
	     treewidthTextField = new JTextField(1);
	     
	     topPanel.add(treewidthTextField);
	     
	     JLabel treeDeepthLabel = new JLabel("�������������");
	     
	     topPanel.add(treeDeepthLabel);
	     
	     treeDeepthTextField = new JTextField(1);
	     
	     topPanel.add(treeDeepthTextField);
	     
	     contentPane.add(topPanel, BorderLayout.NORTH);
	     
	     JPanel bottomPanel = new JPanel();
	     
	     okBt = new JButton("ȷ��");
	     
	     bottomPanel.add(okBt);
	     
	     cancelBt = new JButton("ȡ��");
	     
	     bottomPanel.add(cancelBt);
	     
	     contentPane.add(bottomPanel, BorderLayout.SOUTH);
	     
	     frame.setContentPane(contentPane);

	     frame.pack();

	     frame.setLocationRelativeTo(null);
	     
	     frame.setSize(270,160);
	     
	     frame.setResizable(false);

	     frame.setVisible(true);
		 
	 }
	 
	 private void addListener(){
		 
		 cancelBt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
			 
		 });
		 
		 
		 okBt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if(false == isValueValid()){
			    	 return;
			     }
			     
			    //do something, call tree generate algorithm
			    executeAlogrithm(treeWidth,treeDeepth);
			    
			    TreeDataSheet.Instance().test();
			    
			    frame.dispose();				
			}
			 
		 });
		 
	 }
	 
	 private boolean isValueValid(){
		 
		 String treeWidthText = treewidthTextField.getText();
		 if("".equals(treeWidthText) || "-1".equals(treeWidthText)){
			 
			 JOptionPane.showMessageDialog(null,"�����������","������Ϣ",JOptionPane.ERROR_MESSAGE);
			 
			 return false;
		 }
		 
		 treeWidth = Integer.parseInt(treeWidthText);
		 
		 String treeDeepthText = treeDeepthTextField.getText();
		 
		 if("".equals(treeDeepthText) || "-1".equals(treeDeepthText)){
			 
			 JOptionPane.showMessageDialog(null,"�����������","������Ϣ",JOptionPane.ERROR_MESSAGE);
			 
			 return false;
			 
		 }
		 
		 treeDeepth = Integer.parseInt(treeDeepthText);
		 
		 return true;
	 }
	 
	 private void executeAlogrithm(int treeWidth, int treeHeight){
		 
	 }

}
