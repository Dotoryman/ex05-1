<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>게시글 목록</title>
</head>

<body>
	<button type="button" onclick="location.href='boardInsert'">등록</button>
	<button type="button" id="delBtn">선택삭제</button>
	<table border="1">
		<thead>
			<tr>
				<th>선택</th>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일자</th>
				<th>댓글</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardList }">
				<tr onclick="location.href='boardInfo?bno=${board.bno }'">
					<td><input type="checkbox"></td>
					<td>${board.bno }</td>
					<td>${board.title }</td>
					<td>${board.writer }</td>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${board.regdate }" />
					</td>
					<td>
						<c:choose>
							<c:when test="${board.replycnt > 0}">
								여
							</c:when>
							<c:otherwise>
								부
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		let tagList = document.getElementsByTagName('input');
		for (tag of tagList) {
			tag.addEventListener('click', function (e) {
				e.stopPropagation();
			})
		}
		document.getElementById('delBtn')
						.addEventListener('click', function(e) {
							let bnoList = [];
							for(tag of tagList) {
								if(tag.checked){
									bnoList.push(tag.parentElement.nextElementSibling.textContent)
								}
							}
							console.log(bnoList);

							fetch('deleteBoardList',{
								method: 'post',
								headers: {
									'content-type' : 'application/json'
								},
								body : JSON.stringify(bnoList)
							})
							.then(response => response.json())
							.then(data => console.log(data))
							.catch(reject => console.log(reject))
						})
	</script>
</body>

</html>