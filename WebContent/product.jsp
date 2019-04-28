<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="layout/header.jsp" />

<div class="container">
	<div class="row my-5">
		<c:forEach var="product" items="${products }">
			<div class="col-md-3 mb-3">
				<div class="card">
					<div class="card-header">${product.name }</div>
					<div class="card-body">
						<div class="row justify-content-center">
							<img src="${product.image }" class="productimage" alt="...">
						</div>
					</div>
					<div class="card-footer">
						<div class="row justify-content-between">
							<span>$ ${product.price }</span>
							<button class="btn btn-primary btn-sm"
								onclick="addItemToDB(${product.id})">
								<i class="fa fa-shopping-cart"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>


	</div>
</div>
<c:import url="layout/jsgroup.jsp" />
<c:import url="layout/footer.jsp" />