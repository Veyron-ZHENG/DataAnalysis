package com.xn.alex.data.common;

public class CommonConfig {

    public enum FILE_TYPE{DBF_FILE, XLS_FILE, XLSX_FILE, INVALID_FILE};
	//please add path for it
    public final String dataFileFormatFile = "config.ini";
    
    public enum CURRENT_ACTION{NONE,DB_OPERATION,JTABLE_EDIT};
    
    public final static String REG_EKY_VALUE = "2592000000";  //24*3600*1000
    
    public final static String IF_OP = "IF";
    
    public final static String AND_OP = "AND";
    
    public final static String OR_OP = "OR";
    
    public final static String ISMISSING_OP = "IS MISSING";
    
    public final static String NOTMISSING_OP = "NOT MISSING";
    
    public final static String LEFT_PARENTHESE = "(";
    
    public final static String RIGHT_PARENTHESE = ")";
    
    public final static String SPACE = " ";
    
}
