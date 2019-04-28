<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="layout/header.jsp" />

<div class="container">
	<h1>Cart Out</h1>

	${user.name }

</div>

<c:import url="layout/jsgroup.jsp" />
<script src="${pageContext.request.contextPath }/assets/js/cart3.js"></script>
<c:import url="layout/footer.jsp" />