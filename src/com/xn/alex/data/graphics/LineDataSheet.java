package com.xn.alex.data.graphics;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.xn.alex.data.resultobj.algorithmResultObj2;

public class LineDataSheet extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	
	private JFreeChart chart = null;
    
	private static LineDataSheet lineDataSheetHandler = null;
	
	private LineDataSheet(String title){
		
        super(title);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public static LineDataSheet Instance(){
		
		if(null == lineDataSheetHandler){
			
			lineDataSheetHandler = new LineDataSheet("ROC曲线图");
			
		}
		
		return lineDataSheetHandler;
	}
	
	
	public void show(Vector<algorithmResultObj2> resultObjVec){
		
        this.setContentPane(createPanel(resultObjVec));
		
		Instance().pack();
		
		Instance().setVisible(true);
		
		Instance().setLocationRelativeTo(null);
		
	}
	
	private JPanel createPanel(Vector<algorithmResultObj2> resultObjVec){
		
		chart =createChart(createLineDataset(resultObjVec));
		
        return new ChartPanel(chart); //将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
		
	}
	
	private JFreeChart createChart(CategoryDataset dataset){
		
		JFreeChart chart = ChartFactory.createLineChart(
				"ROC曲线图",
				"底框文字", 
				"左边框文字", 
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false);
		
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD+Font.PLAIN, 12));
		
		chart.setTitle(new TextTitle("ROC曲线图",new Font("宋体",Font.BOLD+Font.ITALIC,20)));
		
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		
		plot.setRangeGridlinePaint(Color.white);
		
		CategoryAxis categoryAxis=plot.getDomainAxis();//获得横坐标
		   
        categoryAxis.setTickLabelFont(new Font("sans-serif", Font.BOLD+Font.PLAIN, 11));
        
        categoryAxis.setLabelFont(new Font("宋体",Font.BOLD,12));//设置横坐标字体       
		
		NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
		
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		rangeAxis.setAutoRangeIncludesZero(true);
		
		rangeAxis.setTickLabelFont(new Font("sans-serif", Font.BOLD+Font.PLAIN, 12)); 
		
		rangeAxis.setLabelFont(new Font("宋体", Font.BOLD+Font.PLAIN, 12));
		
		LineAndShapeRenderer render = (LineAndShapeRenderer)plot.getRenderer();
		
		//render.setShapesVisible(true);
		
		render.setDrawOutlines(true);
		
		render.setUseFillPaint(true);
		
		//render.setItemLabelsVisible(true);
		
		//render.setFillPaint(Color.white);
		
		return chart;
		
	}
	
	private CategoryDataset createLineDataset(Vector<algorithmResultObj2> resultObjVec){
		
     DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		
		try{
		
		for(int i=0;i<resultObjVec.size();i++){
			
			algorithmResultObj2 obj = resultObjVec.get(i);
			
			if(null == obj){
				continue;
			}
			
			dataset.addValue(obj.y, obj.type,  obj.x.toString());			
		  }
		}
		catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		return dataset;
		
	}
	
     public void testCategoryLineDataSheet(){
    	
    	algorithmResultObj2 obj = new algorithmResultObj2();
    	
    	obj.x = 1;
    	
    	obj.y = 20;
    	
    	obj.type = "a";
    	
    	Vector<algorithmResultObj2> resultObjVec = new Vector<algorithmResultObj2>();
    	
    	resultObjVec.add(obj);
    	
    	algorithmResultObj2 obj2 = new algorithmResultObj2();
    	
    	obj2.x = 2;
    	
    	obj2.y = 35;
    	
    	obj2.type = "a";
    	
    	resultObjVec.add(obj2);
    	
    	show(resultObjVec);
    	
    }
}
