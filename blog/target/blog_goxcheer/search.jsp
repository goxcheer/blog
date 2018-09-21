<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/static/css/base.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/index.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/m.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js" type="text/javascript"></script>
<script type="${pageContext.request.contextPath}/text/javascript" src="static/js/comm.js"></script>
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
                <div class="result" style="vertical-align: sub;font-size: 20px;font-weight: bolder;padding-bottom: 10px;">
                    <img src="/static/images/search_icon.png" style="position:absolute;float: bottom"/>
                    <span style="padding-left: 30px;">搜索&nbsp;<font color="red">${keyboard }</font>&nbsp;的结果 &nbsp;(总共搜索到&nbsp;${resultTotal }&nbsp;条记录)</span>
                </div>
                <c:choose>
                <c:when test="${resultTotal==0 }">
                    <div align="center" style="padding-top: 20px">未查询到结果，请换个关键字试试看！</div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${blogList}" var="blog">
                        <li>
                            <h3><a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html" target="_blank">${blog.title}</a></h3>
                            <p>摘要：${blog.content}...</p>
                            <p>
                                <a href="${pageContext.request.contextPath}/blog/articles/${blog.id }.html" style="color: #008400">http://localhost:8080/blog/articles/${blog.id }.html</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;发布日期：${blog.releaseDateStr}
                            </p>
                        </li>
                    </c:forEach>
                </c:otherwise>
                </c:choose>
                <div class="pagelist">
                    ${pageCode}
                </div>
            </main>
        </article>
        <jsp:include page="common/footer.jsp"/>
</body>
</html>
