<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/13
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--导航栏-->
<div class="main-container">
    <div class="sidebar">
        <nav class="sidebar-nav">
            <ul class="nav">
                <c:forEach items="${map}" var="entry">
                    <c:if test="${entry.key=='网站管理'}">
                        <li class="nav-title">网站资源</li>
                        <c:forEach items="${entry.value}" var="resource">
                            <c:if test="${resource.resourceName=='资源管理'}">
                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/resourceList" class="nav-link">
                                        <i class="icon icon-speedometer"></i> 资源管理
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>

                        <c:forEach items="${entry.value}" var="resource">
                            <c:if test="${resource.resourceName=='用户管理'}">
                                <li class="nav-item ">
                                    <a href="${pageContext.request.contextPath}/userList" class="nav-link ">
                                        <i class="icon icon-target"></i> 用户管理
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>

                        <c:forEach items="${entry.value}" var="resource">
                            <c:if test="${resource.resourceName=='权限管理'}">
                                <li class="nav-item ">
                                    <a href="${pageContext.request.contextPath}/authority" class="nav-link ">
                                        <i class="icon icon-energy"></i> 权限管理
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:if>

                    <c:if test="${entry.key=='文章管理'}">
                        <li class="nav-title">文章管理</li>
                        <c:forEach items="${entry.value}" var="resource">
                            <c:if test="${resource.resourceName=='全部文章'}">
                                <li class="nav-item ">
                                    <a href="${pageContext.request.contextPath}/articleList/1" class="nav-link">
                                        <i class="icon icon-graph"></i> 全部文章
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${entry.value}" var="resource">
                            <c:if test="${resource.resourceName=='编写文章'}">
                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/editorArticle" class="nav-link">
                                        <i class="icon icon-puzzle"></i> 编写文章
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>

                        <c:forEach items="${entry.value}" var="resource">
                            <c:if test="${resource.resourceName=='全部分类'}">
                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/category" class="nav-link">
                                        <i class="icon icon-grid"></i> 全部分类
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>

                        <c:forEach items="${entry.value}" var="resource">
                            <c:if test="${resource.resourceName=='全部标签'}">
                                <li class="nav-item ">
                                    <a href="${pageContext.request.contextPath}/tagList" class="nav-link ">
                                        <i class="icon icon-umbrella"></i> 全部标签
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>

            </ul>
        </nav>
    </div>
</div>