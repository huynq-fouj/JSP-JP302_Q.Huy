<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
//Lấy cấu trúc hiển thị trong phiên
@SuppressWarnings("unchecked")
ArrayList<String> postGrid = (ArrayList<String>)session.getAttribute("postGrid");
%>
    <!-- ======= Post Grid Section ======= -->
    <section id="posts" class="posts">
      <div class="container" data-aos="fade-up">
        <div class="row g-5">
        
          <div class="col-lg-4">
            <%
            	//Bài viết đầu tiên
            	if(postGrid != null && postGrid.size() != 0){
            		out.append(postGrid.get(0));
            	}
            %>
          </div><!-- col-4 -->

          <div class="col-lg-8">
            <div class="row g-5">
              	<%
              		//2 cột
              		if(postGrid != null && postGrid.size() != 0){
	            		out.append(postGrid.get(1));
	            	}
              	%>
              <!-- Trending Section -->
              
              <!-- End Trending Section -->
            </div>
          </div>

        </div> <!-- End .row -->
      </div>
    </section> <!-- End Post Grid Section -->