function getEntities(callback){
	$.get("Enlistador", (responseJson) => {
		callback(responseJson);
	});
}

$(document).on("click", "#submit", ()=>{
	var form = $("form");
	var url = form.attr("action");
	var formData = form.serializeArray();
	$.post(url, formData)
	.done((data)=>{
		alert(data);
	})
	.fail((error) => {
		const sc = error.status;
		if(sc == 500){
			alert("Ha ocurrido un error interno.");
		}
		if(sc == 409){
			alert("Ha ocurrido un error al vincular los puntos de venta." +
			" Es posible que no existan caminos disponibles, revise el formulario.");
		}
		if(sc == 400){
			alert("Uno o mas puntos de venta tiene valores malformados. Por favor revise el formulario.");
		}
	});
});








