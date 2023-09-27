package jsoft.home.article;

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
	
}
