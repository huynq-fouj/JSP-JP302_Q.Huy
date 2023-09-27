package jsoft.home.basic;

import java.sql.*;
import java.util.*;

import jsoft.*;

public interface Basic extends ShareControl {
	public ResultSet get(String sql, int id);
	public ResultSet get(ArrayList<String> sql, String name, String pass);
	public ResultSet gets(String sql);
	public ArrayList<ResultSet> getReList(String multiSelect);
	
}
