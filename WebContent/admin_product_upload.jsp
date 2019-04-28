<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<c:import url="layout/header.jsp" />

<div class="container">
	<h1 class="text-center text-info">Product Upload</h1>
	<sql:setDataSource var="ds" dataSource="jdbc/TestDB"></sql:setDataSource>
	<sql:query dataSource="${ds }" var="results"
		sql="SELECT * FROM categories"></sql:query>
	<!-- Form Start -->
	<form action="${BASE_URL}AdminProductController?action=newproduct"
		method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="cat_id">Category</label> <select class="form-control"
				id="cat_id" name="cat_id">
				<c:forEach var="cat" items="${results.rows }">
					<option value=${cat.id }>${cat.name }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="name">Product Name</label> <input type="text"
				class="form-control" id="name" name="name">
		</div>
		<div class="form-group">
			<label for="price">Product Price</label> <input type="number"
				class="form-control" id="price" name="price">
		</div>

		<div class="form-group">
			<label for="image">Product Image</label> <input type="file"
				class="form-control-file" id="image" name="myFile[]" multiple>
		</div>
		<div class="form-group">
			<label for="description">Description</label>
			<textarea class="form-control" id="description" name="description"
				rows="3"></textarea>
		</div>
		<button type="submit" class="btn btn-primary">Upload</button>
	</form>
	<!-- Form End -->
</div>

<c:import url="layout/jsgroup.jsp" />

<c:import url="layout/footer.jsp" />
