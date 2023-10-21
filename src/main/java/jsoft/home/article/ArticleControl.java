package jsoft.home.article;

import java.util.ArrayList;
import java.util.HashMap;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.javatuples.Sextet;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.objects.ArticleObject;
import jsoft.objects.CategoryObject;

public class ArticleControl {

	private ArticleModel am;
	
	public ArticleControl(ConnectionPool cp) {
		this.am = new ArticleModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.am.getCP();
	}
	
	public void releaseConnection() {
		this.am.releaseConnection();
	}
	
	public ArticleObject getArticle(int id) {
		return this.am.getArticleObject(id);
	}
	
	public ArrayList<String> viewPostGrid(Quartet<ArticleObject, Short, Byte, Boolean> infors){
		Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> datas = this.am.getArticleObjects(infors);
		return ArticleLibrary.viewPostGrid(datas);
	}
	
	public ArrayList<String> viewNew(Quartet<ArticleObject, Short, Byte, Boolean> infors){
		Sextet<
			ArrayList<ArticleObject>, 
			ArrayList<ArticleObject>, 
			ArrayList<CategoryObject>, 
			HashMap<String, Integer>, 
			Integer, 
			ArrayList<ArticleObject>> datas = this.am.getNewArticleObjects(infors);
		return ArticleLibrary.viewNews(datas, infors);
	}
	
	public ArrayList<String> viewDetail(Quartet<ArticleObject, Short, Byte, Boolean> infors) {
		Sextet<
		ArrayList<ArticleObject>, 
		ArrayList<ArticleObject>, 
		ArrayList<CategoryObject>, 
		HashMap<String, Integer>, 
		Integer, 
		ArrayList<ArticleObject>> datas = this.am.getNewArticleObjects(infors);
		return ArticleLibrary.viewDetail(datas);
	}
	
}
