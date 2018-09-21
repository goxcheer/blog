<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
  <meta charset="gbk">
  <title>goxcheer的博客</title>
  <meta name="keywords" content="个人博客,杨青个人博客,个人博客模板,杨青" />
  <meta name="description" content="杨青个人博客，是一个站在web前端设计之路的女程序员个人网站，提供个人博客模板免费资源下载的个人原创网站。" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="${pageContext.request.contextPath}/static/css/base.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/index.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/info.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/m.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/static/js/jquery.min.js" type="text/javascript"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/comm.js"></script>
  <!--[if lt IE 9]>
  <script src="${pageContext.request.contextPath}/static/js/modernizr.js"></script>
  <![endif]-->
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/third-party/codemirror/codemirror.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ueditor/third-party/codemirror/codemirror.js">
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
  <script type="text/javascript" >
          SyntaxHighlighter.all();
          var myCodeMirror = CodeMirror(document.body,{
              lineNumbers: false
          });
  </script>
  <script type="text/javascript" >
    function makeRequest(id) {
        $.post("${pageContext.request.contextPath}/blog/praise.do", {
            'id': id
        }, function (result) {
            if (result.success) {
                $("#diggnum").text(result.praiseCount);
                alert("谢谢亲的支持！");
            } else {
                alert("亲，点赞失败啦！");
            }
        }, "json");
    }
    function loadimage(){
        document.getElementById("randImage").src="${pageContext.request.contextPath}/image.jsp?"+Math.random();
    }
    function showOtherComment(){
       $(".otherCommentList").show();
    }
  </script>
  <style type="text/css" >
    .otherCommentList{
      display: none;
    }
  </style>
</head>
<body>
<jsp:include page="common/header.jsp" />
<article>
  <aside class="l_box">
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
  <main>
    <div class="infosbox">
      <div class="newsview">
        <h3 class="news_title">${blog.title}</h3>
        <div class="bloginfo">
          <ul>
            <li class="author">作者：<a href="/">goxcheer</a></li>
            <li class="lmname"><a href="/">学无止境</a></li>
            <li class="timer">时间：${blog.releaseDateStr}</li>
            <li class="view">${blog.clickCount}人已阅读</li>
          </ul>
        </div>
        <div class="tags">
          <c:forEach items="${keyWordsList}" var="keyWords" >
            <a href="/" target="_blank">${keyWords}</a> &nbsp;
          </c:forEach>
        </div>
        <div class="news_about"><strong>简介</strong>${blog.summary}</div>
        <div class="news_content">
          ${blog.content}
          &nbsp; </div>
      </div>
      <div class="share">
        <p class="diggit"><a href="JavaScript:makeRequest(${blog.id});"> 很赞哦！ </a>(<b id="diggnum">${blog.praiseCount}</b>)</p>
      </div>
      <div class="nextinfo">
        ${lastAndNextBlogCode}
      </div>
      <div class="news_pl">
        <h2>文章评论
          <c:if test="${commentList.size()>10 }">
            <a href="javascript:showOtherComment()" style="float: right;padding-right: 40px;">显示所有评论</a>
          </c:if>
        </h2>
        <div class="gbko">
          <c:forEach items="${commentList}" var="comment" varStatus="status">
            <c:choose>
                <c:when test="${status.index <10}" >
                  <div class="fb commentList">
                    <ul>
                      <p class="fbtime"><span>${comment.commentDate}</span>${comment.userName}</p>
                      <p class="fbinfo">${comment.content}</p>
                    </ul>
                  </div>
                </c:when>
              <c:otherwise>
                <div class="fb otherCommentList">
                  <ul>
                    <p class="fbtime"><span>${comment.commentDate}</span>${comment.userName} </p>
                    <p class="fbinfo">${comment.content}</p>
                  </ul>
                </div>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          <script>
              function sumbitComment(){
                    var userName = $("#username").val();
                    var checkCode = $("#checkCode").val();
                    var content = $("#saytext").val();
                    if (userName.trim() == ''){
                        alert("亲，告诉我您的名字呀！")
                    }
                    if (checkCode.trim() == '') {
                        alert("亲，请填写验证码哈！")
                    }
                    if (content.trim() == '') {
                        alert("您没什么话要说吗？");
                    } else {
                        $.post("${pageContext.request.contextPath}/comment/add.do", {
                            'blog.id': ${blog.id},
                            'userName':userName,
                            'content':content,
                            'checkCode':checkCode
                        }, function (result) {
                            if (result.success) {
                                alert("谢谢亲的评论，请等待博主审核！");
                            } else {
                                if (result.errorInfo != null) {
                                    alert(result.errorInfo);
                                }
                                 else {
                                     alert("亲，评论失败啦！");
                                }
                            }
                        }, "json");
                    }
              }
          </script>
            <div id="plpost">
              <p class="saying"><span style="color: red">共有${commentList.size()}条评论</span>来说两句吧...</p>
              <p class="yname"><span>用户名:</span>
                <input name="username" type="text" class="inputText" id="username" value="" size="16">
              </p>
              <p class="yzm"><span>验证码:</span>
                <input name="key" type="text" class="inputText" size="16" id="checkCode"><br>
                &nbsp;<img onclick="javascript:loadimage();"  title="换一张试试" name="randImage" id="randImage" src="${pageContext.request.contextPath}/image.jsp" style="width: 60px;height: 20px;border: 1px;;align-items: center;padding-left: 50px;" >
              </p>
              <input name="nomember" type="hidden" id="nomember" value="1" checked="checked">
              <textarea name="saytext" rows="6" id="saytext"></textarea>
              <input name="imageField" type="submit" value="提交" onclick="sumbitComment()">
              <input name="id" type="hidden" id="id" value="106">
              <input name="classid" type="hidden" id="classid" value="77">
              <input name="enews" type="hidden" id="enews" value="AddPl">
              <input name="repid" type="hidden" id="repid" value="0">
              <input type="hidden" name="ecmsfrom" value="/joke/talk/2018-07-23/106.html">
            </div>
        </div>
      </div>
    </div>
  </main>
</article>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
