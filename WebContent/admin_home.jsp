<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<c:import url="layout/header.jsp" />

<div class="container">
	<h1 class="text-info text-center">All Users</h1>

	<sql:setDataSource var="ds" dataSource="jdbc/TestDB"></sql:setDataSource>
	<sql:query dataSource="${ds }" var="users" sql="SELECT * FROM users"></sql:query>


	<!-- User Table Start -->
	<table class="table table-bordered">
		<thead>
			<tr class="bg-dark text-white">
				<th scope="col">No</th>
				<th scope="col">Name</th>
				<th scope="col">Email</th>
				<th scope="col">Orders</th>
				<th scope="col">Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users.rows }">
				<tr class="text-muted">
					<td>${user.id }</td>
					<td>${user.name }</td>
					<td>${user.email }</td>
					<td>
						<a href="${BASE_URL}AdminUserController?action=orderdetail&user_id=${user.id }" class="btn btn-sm btn-info">View Orders</a>
					</td>
					<td>
					<a href="" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i></a>
					<a href="" class="btn btn-sm btn-warning"><i class="fa fa-edit"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- User Table End -->

</div>

<c:import url="layout/jsgroup.jsp" />

<c:import url="layout/footer.jsp" />