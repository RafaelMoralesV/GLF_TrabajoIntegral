$(document).one("click", "#somebutton", function() {  // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
	$.get("Enlistador", function(responseJson) {    // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
		var $ul = $("<ul>").appendTo($("#somediv")); // Create HTML <ul> element and append it to HTML DOM element with ID "somediv".
		$.each(responseJson, function(index, item) { // Iterate over the JSON array.
			var string = "(" + item.Tipo + ") ID: " + item.ID +
				" en (" + item.posX + ", " + item.posY + ")";
			$("<li>").text(string).appendTo($ul);      // Create HTML <li> element, set its text content with currently iterated item and append it to the <ul>.
		});
	});
});

$(document).one("click", "#somebutton1", () => {
	fetch('Enlistador')
		.then((response) => { return response.json(); })
		.then((data) => {
			for (var i = 0; i < data.length; i++) {
				const obj = data[i];
				var mainContainer = document.getElementById("detail");
				if (obj.Tipo == "C") {
					var header = document.createElement("div");
					header.innerHTML = '<div class= "font-italic"> (' + obj.Tipo + ') ID: ' + obj.ID + ' Pos: (' + obj.posX + ', ' + obj.posY + '): '
						+ '<input class="text-center d-flex justify-content-between mt-2 mb-4" name="' + obj.ID + '" type="number" min="0" value="0"></div>'
					mainContainer.appendChild(header);
				}
			}
		})
		.catch((error) => { console.log(error); });
});

$(document).one("click", "#somebutton2", () => {
	fetch('Enlistador')
		.then((response) => { return response.json(); })
		.then((data) => {
			for (var j = 0; j < data.length; j++) {
				const obj = data[j];
				var mainContainer = document.getElementById("detail");
				var sel = document.createElement("select");
				if (obj.Tipo == "P") {
					for (var i = 0; i < data.length; i++) {
						const obj1 = data[i];
						var mainContainer = document.getElementById("detail");
						if (obj1.Tipo == "C") {
							var opt = document.createElement('OPTION');
							opt.setAttribute('name', 'selector' + obj.ID);
							opt.innerHTML = '(' + obj1.Tipo + ') ID: ' + obj1.ID + ' Pos: (' + obj1.posX + ', ' + obj1.posY + ')';
							sel.appendChild(opt);
						}
					}
					var header = document.createElement("div");
					var cantidad = document.createElement("div");
					header.innerHTML = '<div class= "h5 mb-3 mt-5 resultado__output"><u> (' + obj.Tipo + ') ID: ' + obj.ID + ' Pos: (' + obj.posX + ', ' + obj.posY + ')</u></div><div class="mb-3 d-flex align-items-end justify-content-center">Punto de distribucion asociado</div>'
					cantidad.innerHTML = '<div class= "text-center my-2">Cantidad de productos para este Punto de Venta <br><input class="my-3 text-center" name="' + obj.ID + '" type="number" min="0" value="0"></div>'
					mainContainer.appendChild(header);
					mainContainer.appendChild(sel);
					mainContainer.appendChild(cantidad);


				}
			}
		})
		.catch((error) => { console.log(error); });
});








