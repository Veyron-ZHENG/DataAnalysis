package com.xn.alex.data.database;

public class DatabaseConstant {
		
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String DB_NAME = "test";
	
	public static final String USERNAME = "root";
	
	public static final String PASSWORD = "xiangwenni0";
	//public static final String PASSWORD = "Root1234";
	
    public static final String IP = "localhost";
    
	public static final String PORT = "3306";
	
	public static final String URL = "jdbc:mysql://" + IP + ":" + PORT + "/" + DB_NAME;
	
	public static final String AVAILABLE_ID = "availableId";
	
	public static final String MAX_ID = "maxId";
	
	public static final String FILE_TO_TABLE = "fileToTable";
	
	public static final String TABLE_NAME = "tableName";
	
	public static final String FILE_FULL_PATH = "fileFullPth";
	
	public static final String TREE_NODE_NUM= "nodeNum";
	
    public final static String DATABASE_TABLE_PREFIX = "table_";
    
    public final static String AVAILABLE_TABLEID_TABLE = "available_id_table";

}
