package jsoft.home.article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.javatuples.Unit;

import jsoft.ConnectionPool;
import jsoft.objects.ArticleObject;

public class ArticleModel {
	
	private Article a;
	
	public ArticleModel(ConnectionPool cp) {
		this.a = new ArticleImpl(cp);
	}
	
	public ConnectionPool getCP() {
		return this.a.getCP();
	}
	
	public void releaseConnection() {
		this.a.releaseConnection();
	}
	
	public ArticleObject getArticleObject(int id) {
		ArticleObject item = null;
		ResultSet rs = this.a.getArticle(id);
		if(rs != null) {
			try {
				if(rs.next()) {
					item = new ArticleObject();
					item.setArticle_id(rs.getInt("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setArticle_summary(rs.getString("article_summary"));
					item.setArticle_content(rs.getString("article_content"));
					item.setArticle_image(rs.getString("article_image"));
					item.setArticle_created_date(rs.getString("article_created_date"));
					item.setArticle_last_modified(rs.getString("article_last_modified"));
					item.setArticle_author_name(rs.getString("article_author_name"));
					item.setArticle_tag(rs.getString("article_tag"));
					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return item;
	}
	
	public Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> getArticleObjects(Triplet<ArticleObject, Short, Byte> infors) {
		ArrayList<ResultSet> res = this.a.getArticles(infors);
		//Lấy danh sách bài viết mới nhất và bài viết xem nhiều nhất
		return new Pair<>(this.getAritcleObjects(res.get(0)), this.getAritcleObjects(res.get(1)));
	}
	
	private ArrayList<ArticleObject> getAritcleObjects(ResultSet rs){
		ArrayList<ArticleObject> items = new ArrayList<>();
		ArticleObject item = null;
		if(rs != null) {
			try {
				while(rs.next()) {
					item = new ArticleObject();
					item.setArticle_id(rs.getInt("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setArticle_summary(rs.getString("article_summary"));
					//item.setArticle_content(rs.getString("article_content"));
					item.setArticle_image(rs.getString("article_image"));
					item.setArticle_created_date(rs.getString("article_created_date"));
					item.setArticle_author_name(rs.getString("article_author_name"));
					item.setArticle_tag(rs.getString("article_tag"));
					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return items;
	}
	
}
