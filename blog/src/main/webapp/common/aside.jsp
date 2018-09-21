<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="l_box">
    <div class="about_me">
        <h2>关于我</h2>
        <ul>
            <i><img src="${pageContext.request.contextPath}/static/blogger/${blogger.image}"></i>
            <p><b>${blogger.nickname}</b>，${blogger.profile}</p>
            <h4>（${blogger.signature}）</h4>
        </ul>
    </div>
    <div class="wdxc">
        <h2>我的相册</h2>
        <ul>
            <li><a href="/"><img src="${pageContext.request.contextPath}/static/myphotos/7.jpg"></a></li>
            <li><a href="/"><img src="${pageContext.request.contextPath}/static/myphotos/7.jpg"></a></li>
            <li><a href="/"><img src="${pageContext.request.contextPath}/static/myphotos/7.jpg"></a></li>
            <li><a href="/"><img src="${pageContext.request.contextPath}/static/myphotos/7.jpg"></a></li>
            <li><a href="/"><img src="${pageContext.request.contextPath}/static/myphotos/7.jpg"></a></li>
            <li><a href="/"><img src="${pageContext.request.contextPath}/static/myphotos/7.jpg"></a></li>
        </ul>
    </div>
    <div class="search">
        <form action="${pageContext.request.contextPath}/blog/search.html" method="post" name="searchform" id="searchform">
            <input name="keyboard" id="keyboard" class="input_text" placeholder="请输入关键字词" value="${keyboard}" type="text">
            <input name="show" value="title" type="hidden">
            <input name="tempid" value="1" type="hidden">
            <input name="tbname" value="news" type="hidden">
            <input name="Submit" class="input_submit" value="搜索" type="submit">
        </form>
    </div>
    <div class="fenlei">
        <h2>文章分类</h2>
        <ul>
            <c:forEach items="${blogCategoryList}" var="blogCategory">
                <li><a href="${pageContext.request.contextPath}/index.html?categoryId=${blogCategory.id}">${blogCategory.categoryName}（${blogCategory.blogCount}）</a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="fenlei">
    <h2>时间分类</h2>
    <ul>
        <c:forEach items="${dateCategoryList}" var="dateCategory">
            <li><a href="${pageContext.request.contextPath}/index.html?releaseDate=${dateCategory.releaseDateStr}">${dateCategory.releaseDateStr}（${dateCategory.blogCount}）</a></li>
        </c:forEach>
    </ul>
    </div>
    <div class="tuijian">
        <h2>点击排行</h2>
        <ul>
            <c:forEach items="${blogListDesc}" var="hotBlog" >
                <li><a href="${pageContext.request.contextPath}/blog/articles/${hotBlog.id}.html">${hotBlog.title}(${hotBlog.clickCount})</a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="links cloud">
        <h2>友情链接</h2>
        <ul>
            <a href="https://github.com/goxcheer">github</a> <a href="https://www.huya.com">虎牙直播</a>
            <a href="http://www.iconfont.cn/">icon图标库</a> <a href="https://music.163.com/">网易云音乐</a>
        </ul>
    </div>
    <div class="guanzhu">
        <h2>关注我 么么哒</h2>
        <ul>
            <img src="${pageContext.request.contextPath}/static/images/wx.png">
        </ul>
    </div>
</aside>
