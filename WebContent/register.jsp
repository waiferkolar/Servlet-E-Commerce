<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="layout/header.jsp" />

<c:if test="${!empty user}">
	<jsp:forward page="/index.jsp"></jsp:forward>
</c:if>


<div class="container my-5">
	<div class="col-md-8 offset-md-2 table-bordered">
		<!-- Form Start -->
		<h1 class="text-info text-center my-3">Register To Buy</h1>
		<form method="post"
			action="${BASE_URL}UserController?action=registerFrom" autocomplete="off">
			<div class="form-group">
				<label for="name">Username</label> <input type="text"
					class="form-control rounded-0" id="name" name="name">
			</div>
			<div class="form-group">
				<label for="email">Email</label> <input type="email"
					class="form-control rounded-0" id="email" name="email">
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" id="password" name="password">
			</div>
			<div class="row justify-content-between no-gutters mt-3">
				<a
					href="http://localhost:8080/E-Commerce/UserController?action=login">
					Already member! Please login here</a>
				<button type="submit" class="btn btn-primary btn-sm mb-3">Submit</button>
			</div>
		</form>
		<!--  Form End -->
	</div>
</div>

<c:import url="layout/jsgroup.jsp" />
<c:import url="layout/footer.jsp" />
