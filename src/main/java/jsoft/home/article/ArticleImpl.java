package jsoft.home.article;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quartet;
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
	public synchronized ArrayList<ResultSet> getArticles(Quartet<ArticleObject, Short, Byte, Boolean> infors) {
		// TODO Auto-generated method stub
		ArticleObject similar = infors.getValue0();
		byte totalperpage = infors.getValue2();
		int at = (infors.getValue1() - 1) * totalperpage;
		boolean isDetail = infors.getValue3();
		StringBuilder sql = new StringBuilder();
		
		//New
		sql.append("SELECT * FROM tblarticle ");
		sql.append("LEFT JOIN tblcategory ON article_category_id=category_id ");
		sql.append("LEFT JOIN tblsection ON category_section_id=section_id ");
		sql.append("WHERE (article_delete=0) AND (article_enable=1) ");
		sql.append(this.createConditions(similar, true));
		sql.append("ORDER BY article_id DESC ");
		sql.append("");
		sql.append("LIMIT 5");
		sql.append("; ");
		
		//Trending
		sql.append("SELECT * FROM tblarticle ");
		sql.append("LEFT JOIN tblcategory ON article_category_id=category_id ");
		sql.append("LEFT JOIN tblsection ON category_section_id=section_id ");
		sql.append("WHERE (article_delete=0) AND (article_enable=1) ");
		sql.append(this.createConditions(similar, true));
		sql.append("ORDER BY article_visited DESC ");
		sql.append("");
		sql.append("LIMIT 5");
		sql.append("; ");
		
		//Danh sách thể loại
		sql.append("SELECT * FROM tblcategory ");
		sql.append("WHERE (category_section_id=").append(similar.getArticle_section_id() + ") ");
		sql.append("ORDER BY category_name ASC");
		sql.append("; ");
		
		//Danh sách tag bài viết 
		sql.append("SELECT article_tag FROM tblarticle ");
		sql.append("WHERE (article_section_id=").append(similar.getArticle_section_id()).append(") ");
		sql.append("ORDER BY article_visited DESC ");
		sql.append("; ");
		
		if(isDetail) {
			sql.append("SELECT COUNT(*) AS total FROM tblarticle ");
			sql.append("WHERE article_delete=0 AND article_enable=1 ");
			sql.append("AND article_id="+ similar.getArticle_id());
			sql.append(";");
		} else {
			//Bài viết mới có phân trang;
			sql.append("SELECT * FROM tblarticle ");
			sql.append("LEFT JOIN tblcategory ON article_category_id=category_id ");
			sql.append("LEFT JOIN tblsection ON category_section_id=section_id ");
			sql.append("WHERE (article_delete=0) AND (article_enable=1) ");
			sql.append(this.createConditions(similar, false));
			sql.append("ORDER BY article_id DESC ");
			sql.append("");
			sql.append("LIMIT ").append(at).append(", ").append(totalperpage);
			sql.append("; ");
			
			//Toong so bao viet
			sql.append("SELECT COUNT(*) AS total FROM tblarticle ");
			sql.append("WHERE article_delete=0 AND article_enable=1 ");
			sql.append(this.createConditions(similar, false));
			sql.append(";");
		}
		return this.getReList(sql.toString());
	}
	
	/**
	 * Khởi tại điều kiện cho mệnh đề WHERE
	 * @param similar - Đối tượng lưu trữ thông tin
	 * @param disable_cate - Loại bỏ thể loại trong truy phân nế tuyền vào là true
	 * @return
	 */
	private StringBuilder createConditions(ArticleObject similar, boolean disable_cate) {
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
				tmp.append("(article_section_id=").append(sid).append(") ");
			}
			if(!disable_cate) {	
				short cid = similar.getArticle_category_id();
				if(cid == 0) {
					cid = similar.getCategory_id();
				}
				if(cid > 0) {
					if(!tmp.toString().equalsIgnoreCase("")) {
						tmp.append(" AND ");
					}
					tmp.append("(article_category_id=").append(cid).append(") ");
				}
			}
		}
		
		if(!tmp.toString().contentEquals("")) {
			tmp.insert(0, " AND ");
		}
		
		return tmp;
	}

}
