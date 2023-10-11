package jsoft.home.article;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.objects.ArticleObject;

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
	
	public ArrayList<String> viewPostGrid(Triplet<ArticleObject, Short, Byte> infors){
		Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> datas = this.am.getArticleObjects(infors);
		return ArticleLibrary.viewPostGrid(datas);
	}
	
	public ArrayList<String> viewNew(Triplet<ArticleObject, Short, Byte> infors){
		Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> datas = this.am.getArticleObjects(infors);
		return ArticleLibrary.viewNews(datas);
	}
	
}
