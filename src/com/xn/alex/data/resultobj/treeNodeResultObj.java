package com.xn.alex.data.resultobj;

import java.util.Vector;

public class treeNodeResultObj {
	
	public String nodeName = null;       //node name
	
	public String Prediction = null;     // Prediction field in txt
	
	public String Probability = null;    // Probability field in txt
	
    public conditionObject obj =null;    // store condition and it's value
	
	int objectLevel = -1;                // the level number of the tree, top node should be 0
	
	public String nodeInfo = null;       // output node Info
	
	public Vector<treeNodeResultObj> childNodeVec = null;  //store child node info

}
