package jsoft.home.article;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Unit;

import jsoft.objects.ArticleObject;

public class ArticleLibrary {
	
	public static ArrayList<String> viewNews(Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> datas){
		ArrayList<String> view = new ArrayList<>();
		
		//Danh sach doi tuong
		ArrayList<ArticleObject> items = datas.getValue0();
		ArrayList<ArticleObject> most_items = datas.getValue1();
		
		StringBuilder tmp = new StringBuilder();
		
		if(items.size() > 0) {
			//Lấy bài viết mới nhất
			ArticleObject item = items.get(0);
			tmp.append("<div class=\"post-entry-1 lg\">");
			tmp.append("<a href=\"#\"><img src=\""+item.getArticle_image()+"\" alt=\"\" class=\"img-fluid\"></a>");
			tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getCategory_created_date()+"</span></div>");
			tmp.append("<h2><a href=\"#\">"+item.getArticle_title()+"</a></h2>");
			tmp.append("<p class=\"mb-4 d-block\">"+item.getArticle_summary()+"</p>");
			tmp.append("<div class=\"d-flex align-items-center author\">");
			tmp.append("<div class=\"photo\"><img src=\"/home/img/person-1.jpg\" alt=\"\" class=\"img-fluid\"></div>");
			tmp.append("<div class=\"name\">");
			tmp.append("<h3 class=\"m-0 p-0\">"+item.getArticle_author_name()+"</h3>");
			tmp.append("</div>");
			tmp.append("</div>");
			tmp.append("</div>");
			//Phần trình bày bài viết đầu tiên
			view.add(tmp.toString());
			//2 cột bài viết mới nhất
			tmp.setLength(0);
			for(int i = 1; i < items.size(); i++) {
				if(i%3 == 1) {
					tmp.append("<div class=\"col-lg-4 border-start custom-border\">");	
				}
				
				item = items.get(i);
				
				tmp.append("<div class=\"post-entry-1\">");
				tmp.append("<a href=\"#\"><img src=\""+item.getArticle_image()+"\" alt=\"\" class=\"img-fluid\"></a>");
				tmp.append("<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getCategory_created_date()+"</span></div>");
				tmp.append("<h2><a href=\"single-post.html\">"+item.getArticle_title()+"</a></h2>");
				tmp.append("</div>");
				if((i%3 == 0) || (i == items.size() - 1)) {
					tmp.append("</div>");
				}
			}
			//Phần trình bài 2 cột giữa và trending
			//view.add(tmp.toString());
		}
		
		if(most_items.size() > 0) {
			tmp.append("<div class=\"col-lg-4\">");
			tmp.append("<div class=\"trending\">");
			tmp.append("<h3>Trending</h3>");
			tmp.append("<ul class=\"trending-post\">");
			most_items.forEach(mi -> {
				tmp.append("<li>");
				tmp.append("<a href=\"#\">");
				tmp.append("<span class=\"number\">"+(most_items.indexOf(mi) + 1)+"</span>");
				tmp.append("<h3>"+mi.getArticle_title()+"</h3>");
				tmp.append("<span class=\"author\">"+mi.getArticle_author_name()+"</span>");
				tmp.append("</a>");
				tmp.append("</li>");
			});
			tmp.append("</ul>");
			tmp.append("</div>");
			tmp.append("</div>");
		}
		
		return view;
	}
	
}
