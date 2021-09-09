<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Product page</title>
<style>
.paging {
	display: flex;
	justify-content: space-around;
}
</style>
</head>
<body>

	<h6>
		<span class="paging"> <span class="total-items">Total
				Products: <c:out value="${totalItems }" />
		</span> <c:choose>
				<c:when test="${currentPage > 1 }">
					<a href="${pageContext.request.contextPath}/${path }/1"> First
					</a>
					<a
						href="${pageContext.request.contextPath}/${path }/${currentPage - 1}">
						Previous </a>
				</c:when>
				<c:otherwise>
					<span>First</span>
					<span>Previous</span>
				</c:otherwise>
			</c:choose> <c:forEach begin="1" end="${totalPages }" varStatus="idx">
				<c:choose>
					<c:when test="${currentPage != idx.index }">
						<a
							href="${pageContext.request.contextPath}/${path }/${idx.index }">
							${idx.index } </a>
					</c:when>
					<c:otherwise>
						<span>${idx.index }</span>
					</c:otherwise>
				</c:choose>
			</c:forEach> <c:choose>
				<c:when test="${currentPage < totalPages }">
					<a
						href="${pageContext.request.contextPath}/${path }/${currentPage + 1}">
						Next </a>
					<a href="${pageContext.request.contextPath}/${path }/${totalPages}">
						Last </a>
				</c:when>
				<c:otherwise>
					<span>Next</span>
					<span>Last</span>
				</c:otherwise>
			</c:choose>
		</span>
	</h6>
</body>
</html>
