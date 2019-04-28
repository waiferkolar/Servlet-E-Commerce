<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="layout/header.jsp" />

<div class="container my-5">
	<table class="table table-bordered">
		<thead>
			<tr class="bg-dark text-white">
				<th scope="col">No.</th>
				<th scope="col">name</th>
				<th scope="col">price</th>
				<th scope="col">image</th>
				<th scope="col">Add/Remove</th>
				<th scope="col">Total</th>
			</tr>
		</thead>
		<tbody id="tableBd">
		</tbody>
		<tbody>
			<tr>
				<td colspan="6" class="text-right"><c:if test="${empty user }">
						<a href="${BASE_URL}UserController?action=login"
							class="btn btn-info btn-sm">Login to Bill Out</a></td>
				</c:if>
				<c:if test="${!empty user }">
					<a href="${BASE_URL}UserController?action=billout"
						class="btn btn-info btn-sm">Bill Out</a>
				</c:if>

			</tr>
		</tbody>
	</table>
</div>

<c:import url="layout/jsgroup.jsp" />
<script src="${pageContext.request.contextPath }/assets/js/cart2.js"></script>
<c:import url="layout/footer.jsp" />
