package jsoft.home.article;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.home.basic.BasicImpl;
import jsoft.objects.ArticleObject;

public class ArticleImpl extends BasicImpl implements Article {

	public ArticleImpl(ConnectionPool cp) {
		super(cp, "Article-Home");
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized ResultSet getArticle(int id) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FORM tblarticle ");
		sql.append("LEFT JOIN tblcategory ON article_category_id=category_id ");
		sql.append("LEFT JOIN tblsection ON category_section_id=section_id ");
		sql.append("WHERE (article_id=?) AND (article_delete=0) AND (article_enable=1);");
		return this.get(sql.toString(), id);
	}

	@Override
	public synchronized ArrayList<ResultSet> getArticles(Triplet<ArticleObject, Short, Byte> infors) {
		// TODO Auto-generated method stub
		ArticleObject similar = infors.getValue0();
		byte totalperpage = infors.getValue2();
		int at = (infors.getValue1() - 1) * totalperpage;
		StringBuilder sql = new StringBuilder();
		
		//New
		sql.append("SELECT * FORM tblarticle ");
		sql.append("LEFT JOIN tblcategory ON article_category_id=category_id ");
		sql.append("LEFT JOIN tblsection ON category_section_id=section_id ");
		sql.append("WHERE (article_delete=0) AND (article_enable=1) ");
		sql.append(this.createConditions(similar));
		sql.append("ORDER BY article_id DESC ");
		sql.append("");
		sql.append("LIMIT ").append(at).append(", ").append(totalperpage);
		sql.append("; ");
		
		//Trending
		sql.append("SELECT * FORM tblarticle ");
		sql.append("LEFT JOIN tblcategory ON article_category_id=category_id ");
		sql.append("LEFT JOIN tblsection ON category_section_id=section_id ");
		sql.append("WHERE (article_delete=0) AND (article_enable=1) ");
		sql.append(this.createConditions(similar));
		sql.append("ORDER BY article_visited DESC ");
		sql.append("");
		sql.append("LIMIT ").append(at).append(", ").append(totalperpage);
		sql.append("; ");
		
		return this.getReList(sql.toString());
	}
	
	private StringBuilder createConditions(ArticleObject similar) {
		StringBuilder tmp = new StringBuilder();
		if(similar != null) {
			short sid = similar.getArticle_section_id();
			if(sid == 0) {
				sid = similar.getCategory_section_id();
			}
			if(sid == 0) {
				sid = (short) similar.getSection_id();
			}
			if(sid > 0) {
				tmp.append("(article_section_id=").append(sid).append(")");
			}
			short cid = similar.getArticle_category_id();
			if(cid == 0) {
				cid = similar.getCategory_id();
			}
			if(cid > 0) {
				if(!tmp.toString().equalsIgnoreCase("")) {
					tmp.append(" AND ");
				}
				tmp.append("article_category_id=").append(cid).append(")");
			}
		}
		
		if(!tmp.toString().contentEquals("")) {
			tmp.insert(0, " AND ");
		}
		
		return tmp;
	}

}
