<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="jsoft.*,jsoft.objects.*"%>
<%@ page import="java.util.*,org.javatuples.*"%>
<%@ page import="jsoft.home.*, jsoft.home.article.*"%>
<%@ page import="jsoft.library.*"%>
<%
//xác địn tập ký tự cần lấy
request.setCharacterEncoding("utf-8");
//Lấy đường đẫn xác định vị trí
String uri = request.getRequestURI().substring(6);
int at = uri.indexOf("/");
//Tìm bộ quản lý kết nối
ConnectionPool cp = (ConnectionPool)application.getAttribute("CPool");
ArticleControl ac = new ArticleControl(cp);
if(cp == null){
	application.setAttribute("CPool", ac.getCP());
}
ArticleObject similar = new ArticleObject();
similar.setArticle_section_id((short)2);
if(at != -1) {
	
	short cid = Utilities.getShortParam(request, "cid");
	short p = Utilities.getShortPage(request, "page");
	short id = Utilities.getShortParam(request, "id");
	
	similar.setArticle_category_id(cid);
	similar.setArticle_id(id);
	if(id > 0) {
		ArrayList<String> detail = ac.viewDetail(new Quartet<>(similar, p, (byte) 5, true));
		if(detail.size() > 0) {
			//Gửi cấu trúc hiển thị vào phiên
			session.setAttribute("news", detail.get(0));
		}
	} else {
		ArrayList<String> news = ac.viewNew(new Quartet<>(similar, p, (byte) 5, false));
		if(news.size() > 0) {
			//Gửi cấu trúc hiển thị vào phiên
			session.setAttribute("news", news.get(0));
		}
	}
} else {
	ArrayList<String> postGrid = ac.viewPostGrid(new Quartet<>(similar, (short) 1, (byte) 5, false));
	
	//Gửi cấu trúc hiển thị vào phiên
	session.setAttribute("postGrid", postGrid);
}

ac.releaseConnection();
%>