function getEntities(callback){
	$.get("Enlistador", (responseJson) => {
		callback(responseJson);
	});
}

$(document).on("click", "#submit", ()=>{
	var form = $("form");
	var url = form.attr("action");
	var formData = form.serializeArray();
	$.post(url, formData).done((data)=>{
		alert(data);
	});
});








