<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/static/css/base.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/index.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/m.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/comm.js"></script>
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}static/js/modernizr.js"></script>
<![endif]-->
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
        <jsp:include page="${pageContext.request.contextPath}/common/header.jsp" />
        <article>
            <jsp:include page="${pageContext.request.contextPath}/common/aside.jsp" />
            <main class="r_box">
                <c:forEach items="${blogList}" var="blog">
                <li>
                        <c:forEach items="${blog.imgList}" var="img">
                            <i><a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${img}</a></i>
                        </c:forEach>
                    <h3><a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${blog.title}</a></h3>
                    <p>${blog.summary}</p>
                </li>
                </c:forEach>
                <div class="pagelist">
                    ${pageCode}
                </div>
            </main>
        </article>
        <jsp:include page="common/footer.jsp"/>
</body>
</html>
