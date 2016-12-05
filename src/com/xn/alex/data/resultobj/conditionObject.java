package com.xn.alex.data.resultobj;

import java.util.Vector;

public class conditionObject {
	
	public String fieldName = null;     // store field name such as ÷’∂À÷∆ Ω . Filed name should 
                                        //comply with config file	
	public String filedOperator = null; // the operator between fieldName and value such as 
                                        //	AND,OR,IS MISSING, NOT MISSING	
	public String fieldValue = null;
	
	public Vector<conditionObject> childCondObject = null;

}
