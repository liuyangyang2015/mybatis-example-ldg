<%@page import="java.io.*,java.util.*,javax.servlet.*,ldg.mybatis.service.*,ldg.mybatis.model.*" contentType="text/html; charset=utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
</head>
<body>

<%
Long commentNo = Long.parseLong(request.getParameter("commentNo"));
CommentResultMapService commentService = new CommentResultMapService();
Comment comment = commentService.selectCommentByPrimaryKeyCollection(commentNo);
%>

<%= comment %>
</body>
</html>