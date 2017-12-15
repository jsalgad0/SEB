
	function municipios(){
		var idEstado = $("#idEstado").val();
	    $.getJSON("/sab/generic/municipios", {id:idEstado} ,function(response){ 
	        $("#idMunicipio option").remove();
	        $("#idColonia option").remove();
	            var options = '<option value="-1">Delegacion</option>';
	            var optionsColonia = '<option value="-1">Colonia</option>';
	            $("#idColonia").html(optionsColonia);
	            $("#idMunicipio").html(options); 
	            $.each(response, function(index, item) {
	                options += '<option value="' + item.municipioId + '">' + item.municipio + '</option>';
	                $("#idMunicipio").html(options); 
	            });
	    });
	}

	function colonias(){
		var idMunicipio = $("#idMunicipio").val();
	    $.getJSON("/sab/generic/colonias", {id:idMunicipio} ,function(response){ 
	        $("#idColonia option").remove(); 
	            var options = '<option value="-1">Colonia</option>';
	            $("#idColonia").html(options);
	            $.each(response, function(index, item) {
	                options += '<option value="' + item.coloniaId + '">' + item.colonia + '</option>';
	                $("#idColonia").html(options); 
	            });
	    });
	}
	
	function validateEmail(email) {
	    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	    return re.test(email);
	}	
	