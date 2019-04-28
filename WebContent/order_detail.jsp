<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<c:import url="layout/header.jsp" />

<div class="container">
	<!--  Order Detail Table  -->
	<table class="table table-bordered">
		<thead>
			<tr class="bg-dark text-white">
				<th scope="col">No</th>
				<th scrop="col">Customer</th>
				<th scope="col">Name</th>
				<th scope="col">Price</th>
				<th scope="col">Image</th>
				<th scope="col">Count</th>
				<th scope="col">Total</th>
			</tr>
		</thead>
		<tbody>
			<%@ page import="java.util.*,coder.models.*"%>
			<%
				int i = 0;
				int total = 0;

				List<Product> products = (List<Product>) request.getAttribute("orders");
				for (Product product : products) {
					total += product.getPrice() * product.getCount();
				}
			%>
			<c:forEach var="order" items="${orders }">
				<tr class="text-muted">
					<td><%=i%></td>
					<td>${order.username }</td>
					<td>${order.name }</td>
					<td>${order.price }</td>
					<td><img src="${order.image }"
						style="width: 100px; height: 100px;" /></td>
					<td>${order.count }</td>
					<td>${order.price * order.count}</td>
				</tr>
			</c:forEach>
			<tr class="text-muted">
				<td colspan="6">Grand Total</td>
				<td><%=total %></td>
			</tr>
		</tbody>
	</table>
	<!--  Order Detail Table -->
</div>


<c:import url="layout/jsgroup.jsp" />

<c:import url="layout/footer.jsp" />