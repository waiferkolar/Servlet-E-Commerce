let db = "products";
function addItemToDB(id) {
	var items = getAllFromDB();
	if (items.length < 1) {
		items.push({
			id : id,
			count : 1
		});
		saveToDB(items);
	} else {
		var indx = items.findIndex(x => x.id == id);
		if(indx == -1){
			items.push({
				id : id,
				count : 1
			});
		}else{
			items[indx].count =  items[indx].count +1 ;
		}
		saveToDB(items);
	}
}

function saveToDB(items) {
	clearDB();
	localStorage.setItem(db, JSON.stringify(items))
	updateItemCount();
}

function updateItemCount() {
	var items = getAllFromDB();
	document.getElementById("cartCount").innerHTML = items.length;
}

function clearDB() {
	localStorage.removeItem(db);
}

function getAllFromDB() {
	var items = localStorage.getItem(db);
	if (items == null) {
		return [];
	}
	return JSON.parse(items);
}

function toggleMenuIcon(){
	$(".sidebar").toggleClass("hidesidebar");
	$(".mymenu").toggleClass("fa-list fa-arrow-left");
}
