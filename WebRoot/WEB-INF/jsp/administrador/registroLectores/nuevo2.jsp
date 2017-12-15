<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D'
	name="Autentia" id='Autentia'>
	<embed></embed>
</OBJECT>
<script type="text/javascript">
	function editar(){
		$("#registroLectoresForm").attr("action", "editar");
		$("#registroLectoresForm").submit();
	}
	
    function TParams(){
        this.CodExt = '';
        this.CSensor  = '';
        this.Ins  = '';
        this.CSite = '';
    }
	
	function consulta(){
		var Params   = new TParams; 
		erc				= 200;
		Params.CodExt   = $("#noDeSerie").val();
		Params.CSensor	= "";
		Params.Ins		= "";
		Params.CSite	= "";

		if ($("#noDeSerie").val() == "" || $("#noDeSerie").val() == "Numero de Serie Externo"){
			alert("Debe Ingresar Código Externo!!");
			return;
		}
		
		erc = Autentia.Transaccion ("$SYS/_conslector",Params);  //enrola_10dedos_C
		if (Params.CSensor != ""){
			$("#codigoLugarAtencion").val(Params.Ins);
			$("#propietarioLector").val(Params.CSite);
			$("#noDeSerieInterno").val(Params.CSensor);
			
			$.getJSON("propietarioLectorByPropietario", {busqueda:Params.CSite} ,function(response){ 
	            $.each(response, function(index, item) {
	            	$("#rfcPropietarioLector").val(item.rfc);
	            	$("#idPropietarioLector").val(item.idPropietarioLector);
	            });
			});
			
			$.getJSON("lugarAtencionByCodigo", {busqueda:Params.Ins} ,function(response){ 
	            $.each(response, function(index, item) {
	            	$("#lugarAtencion").val(item.descripcion);
	            	$("#idLugarAtencion").val(item.lugarDeAtencionId);
	            });
			});			
		}
		else{
            var Params2 = new TParams;
            Params2.CSensor = "";
            Params2.Ins     = "";
            Params2.CSite   = "";         
            res = Autentia.Transaccion("$SYS/_CodSensorMX",Params2);
            alert(Params2.CSensor);
            $("#noDeSerieInterno").val(Params2.CSensor);
			/* alert("El código " +Params2.CodExt+ " no existe en la base, debe ser registrado."); */
		}
	}
	
	function registra(){
		var Params = new TParams;
			Params.CodInternoBD	= $("#noDeSerieInterno").val();
			Params.CodExterno	= $("#noDeSerie").val();
			Params.Institucion	= "SAB";
			Params.Ubicacion	= $("#codigoLugarAtencion").val();
			Params.Descripcion	= $("#propietarioLector").val();
		
		if (Params.CodInternoBD==""){
			alert("Debe Ingresar Codigo Interno!!");
		   	return false;
	   	}
	   	if (Params.Institucion==""){
			alert("Debe Ingresar Institucion!!");
			return false;
	   	}
	    if (Params.CodExterno==""){
		   	alert("Debe Ingresar Codigo Externo!!");
		   	return false;
	   	}
	    if (Params.Ubicacion==""){
		   	alert("Debe Ingresar Ubicacion!!");
		   	return false;
	   	}
	    if (Params.Descripcion==""){
		   	alert("Debe Ingresar Descripcion!!");
		   	return false;
	   	}

	   	res = parent.Autentia.Transaccion("$SYS/_RegSensor64",Params);
		if (Params.resul == "S" || res==0){
			alert("Operacion Realizada Correctamente !!");
			$("#registroLectoresForm").submit();
		}else{
			test.Resp.value ="";
			alert("Operacion No se Realizo !!");
			return false;
		}
	}
	
    $(document).ready(function(){
    	$("#lugarAtencion").autocomplete({
    	    serviceUrl: "lugarAtencion",
     	    paramName: "busqueda",
		    transformResult: function(response) {
		        return {
		            suggestions: $.map(JSON.parse(response), function(dataItem) {
		                return { value: dataItem.value, data: dataItem.data };
		            })
		        };
		    },
    	    onSelect: function (suggestion) {
    	    	$("#idLugarAtencion").val(suggestion.data);
    	    	
    			$.getJSON("lugarAtencionById", {busqueda:suggestion.data} ,function(response){ 
    	            $.each(response, function(index, item) {
    	            	$("#codigoLugarAtencion").val(item.codigoLugarAtencion);
    	            });
    			});		    	    	
    	    },
    	    onInvalidateSelection:function() {
    	    	$("#idLugarAtencion").val("");
            }
    	});
    	
    	$("#rfcPropietarioLector").autocomplete({
    	    serviceUrl: "propietarioLector",
     	    paramName: "busqueda",
		    transformResult: function(response) {
		        return {
		            suggestions: $.map(JSON.parse(response), function(dataItem) {
		                return { value: dataItem.value, data: dataItem.data };
		            })
		        };
		    },
		    onSelect: function (suggestion) {
    	    	$("#idPropietarioLector").val(suggestion.data);
    	    	
    			$.getJSON("propietarioLectorById", {busqueda:suggestion.data} ,function(response){ 
    	            $.each(response, function(index, item) {
    	            	$("#propietarioLector").val(item.propietarioLector);
    	            });
    			});		
    	    },
    	    onInvalidateSelection:function() {
    	    	$("#idPropietarioLector").val("");
            }
    	}); 
    	
    	$( "#lugarAtencion" ).keypress(function() {
    		$("#idLugarAtencion").val("");
    		$("#codigoLugarAtencion").val("");
   		});
    	
    	$( "#rfcPropietarioLector" ).keypress(function() {
    		$("#idPropietarioLector").val("");
    		$("#propietarioLector").val("");
   		});    	
    });
    

</script>

<body>

	<form:form name='registroLectoresForm' action="nuevo" method='POST'
		commandName="registroLectoresForm" id="registroLectoresForm">
		<form:hidden path="lectores.lectorId" />
		<form:hidden path="idLugarAtencion" id="idLugarAtencion" />
		<form:hidden path="idPropietarioLector" id="idPropietarioLector" />

		<table>
			<tr>
				<td colspan="1"><form:errors path="error" /> <c:out
						value="${registroLectoresForm.error}"></c:out></td>
			</tr>
			<tr>
				<td>No. de Serie Externo:</td>
				<td><c:choose>
						<c:when test="${registroLectoresForm.editar}">
							<form:input path="lectores.noDeSerie" id="noDeSerie"
								readonly="true" />
						</c:when>
						<c:otherwise>
							<form:input path="lectores.noDeSerie" id="noDeSerie" />
						</c:otherwise>
					</c:choose></td>
			</tr>
			<c:choose>
				<c:when test="${registroLectoresForm.editar}">

				</c:when>
				<c:otherwise>
					<tr>
						<td>No. de Serie Interno:</td>
						<td><form:input path="noDeSerieInterno" id="noDeSerieInterno"
								readonly="true" /></td>
					</tr>
				</c:otherwise>
			</c:choose>

			<tr>
				<td>Lugar de atencion:</td>
				<td><form:input path="lugarAtencion" autocomplete="off"
						id="lugarAtencion" /></td>
			</tr>
			<tr>
				<td>Codigo del Lugar de atencion:</td>
				<td><form:input path="codigoLugarAtencion" readonly="true" /></td>
			</tr>
			<tr>
				<td>RFC Propietario del lector:</td>
				<td><form:input path="rfcPropietarioLector" autocomplete="off"
						id="rfcPropietarioLector" /></td>
			</tr>
			<tr>
				<td>Propietario del lector:</td>
				<td><form:input path="propietarioLector" /></td>
			</tr>
			<tr>
				<td colspan='2'><c:choose>
						<c:when test="${registroLectoresForm.editar}">
							<input type="button" value="Editar"
								onclick="javascript:editar();" />
						</c:when>
						<c:otherwise>
							<input type="button" value="Gaurdar" onclick="registra()" />
						</c:otherwise>
					</c:choose> <c:if test="${!registroLectoresForm.editar}">
						<input type="button" value="Consultar"
							onclick="javascript:consulta();" />
					</c:if></td>
			</tr>
		</table>
	</form:form>

</body>
</html>