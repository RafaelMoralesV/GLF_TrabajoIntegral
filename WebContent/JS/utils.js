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
				if (obj.Tipo == "P") {
					var header = document.createElement("div");
					header.innerHTML = '<div class= "font-italic"> (' + obj.Tipo +') ID: ' + obj.ID + ' Pos: (' + obj.posX + ', ' + obj.posY + '): ' 
										+ '<input name="' + obj.ID + '" type="number" value="0"></div>' 
					mainContainer.appendChild(header);
				}
			}
		})
		.catch((error) => { console.log(error); });
});