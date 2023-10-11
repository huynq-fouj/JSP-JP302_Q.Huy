package jsoft.home.article;

import java.util.ArrayList;
import java.util.HashMap;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import org.javatuples.Unit;

import jsoft.objects.ArticleObject;
import jsoft.objects.CategoryObject;

public class ArticleLibrary {

	public static ArrayList<String> viewPostGrid(Pair<ArrayList<ArticleObject>, ArrayList<ArticleObject>> datas) {
		ArrayList<String> view = new ArrayList<>();

		// Danh sach doi tuong
		ArrayList<ArticleObject> items = datas.getValue0();
		ArrayList<ArticleObject> most_items = datas.getValue1();

		StringBuilder tmp = new StringBuilder();

		if (items.size() > 0) {
			// Lấy bài viết mới nhất
			ArticleObject item = items.get(0);
			tmp.append("<div class=\"post-entry-1 lg\">");
			tmp.append("<a href=\"#\"><img src=\"" + item.getArticle_image() + "\" alt=\"\" class=\"img-fluid\"></a>");
			tmp.append("<div class=\"post-meta\"><span class=\"date\">" + item.getCategory_name()
					+ "</span> <span class=\"mx-1\">&bullet;</span> <span>" + item.getCategory_created_date()
					+ "</span></div>");
			tmp.append("<h2><a href=\"#\">" + item.getArticle_title() + "</a></h2>");
			tmp.append("<p class=\"mb-4 d-block\">" + item.getArticle_summary() + "</p>");
			tmp.append("<div class=\"d-flex align-items-center author\">");
			tmp.append("<div class=\"photo\"><img src=\"/home/img/person-1.jpg\" alt=\"\" class=\"img-fluid\"></div>");
			tmp.append("<div class=\"name\">");
			tmp.append("<h3 class=\"m-0 p-0\">" + item.getArticle_author_name() + "</h3>");
			tmp.append("</div>");
			tmp.append("</div>");
			tmp.append("</div>");
			// Phần trình bày bài viết đầu tiên
			view.add(tmp.toString());
			// 2 cột bài viết mới nhất
			tmp.setLength(0);
			for (int i = 1; i < items.size(); i++) {
				if (i % 2 == 1) {
					tmp.append("<div class=\"col-lg-4 border-start custom-border\">");
				}

				item = items.get(i);

				tmp.append("<div class=\"post-entry-1\">");
				tmp.append("<a href=\"/home/tin-tuc/?id=" + item.getArticle_id() + "\"><img src=\""
						+ item.getArticle_image() + "\" alt=\"\" class=\"img-fluid\"></a>");
				tmp.append("<div class=\"post-meta\"><span class=\"date\">" + item.getCategory_name()
						+ "</span> <span class=\"mx-1\">&bullet;</span> <span>" + item.getCategory_created_date()
						+ "</span></div>");
				tmp.append("<h2><a href=\"/home/tin-tuc/?id=" + item.getArticle_id() + "\">" + item.getArticle_title()
						+ "</a></h2>");
				tmp.append("</div>");
				if ((i % 2 == 0) || (i == items.size() - 1)) {
					tmp.append("</div>");
				}
			}
			// Phần trình bài 2 cột giữa và trending
			// view.add(tmp.toString());
		}

		if (most_items.size() > 0) {
			tmp.append("<div class=\"col-lg-4\">");
			tmp.append("<div class=\"trending\">");
			tmp.append("<h3>Xu hướng</h3>");
			tmp.append("<ul class=\"trending-post\">");
			most_items.forEach(mi -> {
				tmp.append("<li>");
				tmp.append("<a href=\"/home/tin-tuc/?id=" + mi.getArticle_id() + "\">");
				tmp.append("<span class=\"number\">" + (most_items.indexOf(mi) + 1) + "</span>");
				tmp.append("<h3>" + mi.getArticle_title() + "</h3>");
				tmp.append("<span class=\"author\">" + mi.getArticle_author_name() + "</span>");
				tmp.append("</a>");
				tmp.append("</li>");
			});
			tmp.append("</ul>");
			tmp.append("</div>");
			tmp.append("</div>");
		}
		view.add(tmp.toString());

		return view;
	}

	public static ArrayList<String> viewNews(
			Quartet<ArrayList<ArticleObject>, ArrayList<ArticleObject>, ArrayList<CategoryObject>, HashMap<String, Integer>> datas,
			Triplet<ArticleObject, Short, Byte> infors
	) {
		ArrayList<String> view = new ArrayList<>();

		ArrayList<ArticleObject> items = datas.getValue0();
		ArrayList<ArticleObject> most_items = datas.getValue1();
		ArrayList<CategoryObject> cates = datas.getValue2();
		HashMap<String, Integer> tags = datas.getValue3();
		
		ArticleObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte totalperpage = infors.getValue2();
		
		StringBuilder tmp = new StringBuilder();

		tmp.append("<section>");
		tmp.append("<div class=\"container\">");
		tmp.append("<div class=\"row\">");

		tmp.append("<div class=\"col-md-9\" data-aos=\"fade-up\">");
		tmp.append(ArticleLibrary.viewCateOptions(cates, similar));
		//Moi nhat
		items.forEach(item -> {
			tmp.append("<div class=\"d-md-flex post-entry-2 half\">");
			tmp.append("<a href=\"/home/tin-tuc/?id="+item.getArticle_id()+"\" class=\"me-4 thumbnail\">");
			tmp.append("<img src=\""+item.getArticle_image()+"\" alt=\""+item.getArticle_title()+"\" class=\"img-fluid\">");
			tmp.append("</a>");
			tmp.append("<div>");
			tmp.append(
					"<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>Jul 5th '22</span></div>");
			tmp.append(
					"<h3><a href=\"/home/tin-tuc/?id="+item.getArticle_id()+"\">"+item.getArticle_title()+"</a></h3>");
			tmp.append("<p>"+item.getArticle_summary()+"</p>");
			tmp.append("<div class=\"d-flex align-items-center author\">");
			tmp.append("<div class=\"photo\"><img src=\"assets/img/person-2.jpg\" alt=\"\" class=\"img-fluid\"></div>");
			tmp.append("<div class=\"name\">");
			tmp.append("<h3 class=\"m-0 p-0\">"+item.getArticle_author_name()+"</h3>");
			tmp.append("</div>");
			tmp.append("</div>");
			tmp.append("</div>");
			tmp.append("</div>");
		});
		//
		tmp.append("<div class=\"text-start py-4\">");
		tmp.append("<div class=\"custom-pagination\">");
		tmp.append("<a href=\"#\" class=\"prev\">Prevous</a>");
		tmp.append("<a href=\"#\" class=\"active\">1</a>");
		tmp.append("<a href=\"#\">2</a>");
		tmp.append("<a href=\"#\">3</a>");
		tmp.append("<a href=\"#\">4</a>");
		tmp.append("<a href=\"#\">5</a>");
		tmp.append("<a href=\"#\" class=\"next\">Next</a>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");

		tmp.append("<div class=\"col-md-3\">");
		//SideBar
		tmp.append("<div class=\"aside-block\">");

		tmp.append("<ul class=\"nav nav-pills custom-tab-nav mb-4\" id=\"pills-tab\" role=\"tablist\">");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append(
				"<button class=\"nav-link active\" id=\"pills-trending-tab\" data-bs-toggle=\"pill\" data-bs-target=\"#pills-trending\" type=\"button\" role=\"tab\" aria-controls=\"pills-trending\" aria-selected=\"true\">Xu hướng</button>");
		tmp.append("</li>");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append(
				"<button class=\"nav-link\" id=\"pills-latest-tab\" data-bs-toggle=\"pill\" data-bs-target=\"#pills-latest\" type=\"button\" role=\"tab\" aria-controls=\"pills-latest\" aria-selected=\"false\">Mới nhất</button>");
		tmp.append("</li>");
		tmp.append("</ul>");

		tmp.append("<div class=\"tab-content\" id=\"pills-tabContent\">");
		//Trending
		tmp.append(
				"<div class=\"tab-pane fade show active\" id=\"pills-trending\" role=\"tabpanel\" aria-labelledby=\"pills-trending-tab\">");
		most_items.forEach(item -> {
			if(most_items.indexOf(item) < 5) {
				tmp.append("<div class=\"post-entry-1 border-bottom\">");
				tmp.append(
						"<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getArticle_created_date()+"</span></div>");
				tmp.append(
						"<h2 class=\"mb-2\"><a href=\"/home/tin-tuc/?id="+item.getArticle_id()+"\">"+item.getArticle_title()+"</a></h2>");
				tmp.append("<span class=\"author mb-3 d-block\">"+item.getArticle_author_name()+"</span>");
				tmp.append("</div>");
			}
		});

		tmp.append("</div>");
		//<!-- End Trending -->

		//<!-- Latest -->
		tmp.append("<div class=\"tab-pane fade\" id=\"pills-latest\" role=\"tabpanel\" aria-labelledby=\"pills-latest-tab\">");
		items.forEach(item -> {
			if(items.indexOf(item) < 5) {
				tmp.append("<div class=\"post-entry-1 border-bottom\">");
				tmp.append(
						"<div class=\"post-meta\"><span class=\"date\">"+item.getCategory_name()+"</span> <span class=\"mx-1\">&bullet;</span> <span>"+item.getArticle_created_date()+"</span></div>");
				tmp.append(
						"<h2 class=\"mb-2\"><a href=\"/home/tin-tuc/?id="+item.getArticle_id()+"\">"+item.getArticle_title()+"</a></h2>");
				tmp.append("<span class=\"author mb-3 d-block\">"+item.getArticle_author_name()+"</span>");
				tmp.append("</div>");
			}
		});
		tmp.append("</div>");
		// <!-- End Latest -->

		tmp.append("</div>");
		tmp.append("</div>");

		tmp.append("<div class=\"aside-block\">");
		tmp.append("<h3 class=\"aside-title\">Video</h3>");
		tmp.append("<div class=\"video-post\">");
		tmp.append("<a href=\"https://www.youtube.com/watch?v=AiFfDjmd0jU\" class=\"glightbox link-video\">");
		tmp.append("<span class=\"bi-play-fill\"></span>");
		tmp.append("<img src=\"assets/img/post-landscape-5.jpg\" alt=\"\" class=\"img-fluid\">");
		tmp.append("</a>");
		tmp.append("</div>");
		tmp.append("</div><!-- End Video -->");

		tmp.append("<div class=\"aside-block\">");
		tmp.append("<h3 class=\"aside-title\">Thể loại</h3>");
		tmp.append("<ul class=\"aside-links list-unstyled\">");
		cates.forEach(item -> {		
			tmp.append("<li><a href=\"/home/tin-tuc/?cid="+item.getCategory_id()+"\"><i class=\"bi bi-chevron-right\"></i>"+item.getCategory_name()+"</a></li>");
		});
		tmp.append("</ul>");
		tmp.append("</div><!-- End Categories -->");

		tmp.append("<div class=\"aside-block\">");
		tmp.append("<h3 class=\"aside-title\">Tags</h3>");
		tmp.append("<ul class=\"aside-tags list-unstyled\">");
		tags.forEach((tag, count) -> {
			tmp.append("<li><a href=\"/home/tin-tuc/?tag="+tag+"\">"+ tag +" ("+ count +")</a></li>");
		});

		tmp.append("</ul>");
		tmp.append("</div><!-- End Tags -->");

		tmp.append("</div>");

		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</section>");
		view.add(tmp.toString());
		return view;
	}
	
	private static StringBuilder viewCateOptions(ArrayList<CategoryObject> cates, ArticleObject similar) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("<div class=\"row align-items-center mb-3\">");
		tmp.append("<div class=\"col-sm-2\">");
		tmp.append("<h3 class=\"fs-5\">Thể loại: </h3>");
		tmp.append("</div>");
		tmp.append("<div class=\"col-sm-4\">");
		tmp.append("<form method=\"\" action=\"\">");
		tmp.append("<select class=\"form-select\" name=\"slcCateId\" onChange=\"refreshCate(this.form)\">");
		tmp.append("<option value=\"0\">Chọn</option>");
		cates.forEach(item -> {
			if(item.getCategory_id() == similar.getArticle_category_id()) {
				tmp.append("<option value=\""+item.getCategory_id()+"\" selected>");
			}else {
				tmp.append("<option value=\""+item.getCategory_id()+"\">");
			}
			tmp.append(item.getCategory_name());
			tmp.append("</option>");
		});
		tmp.append("</select>");
		tmp.append("</form>");
		tmp.append("</div>");
		tmp.append("</div>");
		//Script
		tmp.append("""
		<script language=\"javascript\">
			function refreshCate(fn) {
				let cate_id =  fn.slcCateId.value;
				fn.method = 'post';
				fn.action = `/home/tin-tuc/?cid=${cate_id}`;
				fn.submit();
			}
		</script>
		""");
		return tmp;
	}

}
