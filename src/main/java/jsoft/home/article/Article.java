package jsoft.home.article;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quartet;
import org.javatuples.Triplet;

import jsoft.ShareControl;
import jsoft.objects.*;

public interface Article extends ShareControl{	
	public ResultSet getArticle(int id);
	public ArrayList<ResultSet> getArticles(Quartet<ArticleObject, Short, Byte, Boolean> infors);
}
