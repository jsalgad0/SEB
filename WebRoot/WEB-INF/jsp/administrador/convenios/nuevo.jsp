<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_admin.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">


<html>
<body style="overflow:hidden;">
<form:form name='conveniosForm' action="convenioNuevo" method='POST' commandName="conveniosForm" id="conveniosForm">
	<form:hidden path="error" />
	<form:hidden path="exito" />
	<form:hidden path="busqueda" />
	<form:hidden path="idLugarDeAtencion"/>
<div id="admin_contenido2">

                    
    <div class="linea_supervisor margen_arriba3" style="height:72px;">
    	
   		<div class="linea_supervisor margen_arriba4">     
	
		</div>
    </div>  
 
  <div class="admin_contenidoFrame">   
  
  <div class="linea_corta2">
    
    <div class="linea_supervisor margen">
   		<form:input cssClass="campo_supervisor tam_2" placeholder="CÛdigo Lugar de atenciÛn *" path="codigoLugarAtencion" />
   		<form:input cssClass="campo_supervisor tam_10 mayusculas" placeholder="Lugar de atenciÛn *" path="lugarAtencion" />
    </div>
    
    	<div class="linea3 margen">
			<div class="error tam_2"><form:errors path="codigoLugarAtencion" /></div>
			<div class="error tam_10"><form:errors path="lugarAtencion" /></div>
		</div>
    
    <div class="linea_supervisor margen">
	    <form:select cssClass="selectt campo_supervisor tam_2" path="idAsegurador">
	    	<form:option value="-1" label="Nombre Asegurador *" />
	    	<form:options items="${aseguradores}" itemLabel="nombreRazonSocial" itemValue="aseguradorId"/>
	    </form:select>

	    <form:select cssClass="selectt campo_supervisor tam_7" path="idPrestador" onchange="llenarPrestador()">
	    	<form:option value="-1" label="RFC Prestador *" />
	    	<form:options items="${prestadores}" itemLabel="rfc" itemValue="prestadorId" />	    
	    </form:select>
	    
	    <form:input cssClass="campo_supervisor tam_10 mayusculas" placeholder="Nombre prestador *" path="prestador" />
	 </div>
	 
	 <div class="linea3 margen">
			<div class="error tam_2"><form:errors path="idAsegurador"/></div>
			<div class="error tam_7"><form:errors path="idPrestador" /></div>
			<div class="error tam_10"><form:errors path="prestador" /></div>
	 </div>
    
    <!--  
    <div class="linea_supervisor margen">
     		<div class="btn btn_registrar_convenio"></div>
     </div> 
	-->
  
	<div class="linea_supervisor margen margen_arriba2">
		
		<form:input cssClass="campo_supervisor tam_18 mayusculas" placeholder="Nombre del convenio *" path="convenio" maxlength="50" />
   		<form:input cssClass="campo_supervisor tam_19 mayusculas" placeholder="Identificador del convenio seg˙n el Asegurador *" path="identificadorConvenio" maxlength="50" />
   		
    </div>
    
    <div class="linea3 margen">
			<div class="error tam_18"><form:errors path="convenio" /></div>
			<div class="error tam_19"><form:errors path="identificadorConvenio" /></div>
	 </div>
    
   <div class="linea_supervisor margen">
		
		<form:input cssClass="campo_supervisor tam_4"  cssStyle="margin-right:0px !important;" placeholder="Vigencia inicial" path="vigenteDesde" readonly="true"/><!--
          --><input class="btn_solo btn_calendario" id="datepickerInicio"/> 
          
          <form:input cssClass="campo_supervisor tam_4"  cssStyle="margin-right:0px !important;" placeholder="Vigencia final" path="vigenteHasta" readonly="true"/><!--
          --><input class="btn_solo btn_calendario" id="datepickerFin"/>       
	</div>
	
	    <div class="linea3 margen">
			<div class="error tam_4"><form:errors path="vigenteDesde" /></div>
			<div class="error tam_4 margen4"><form:errors path="vigenteHasta" /></div>
	 </div>
	
     <div class="linea_supervisor margen margen_arriba">
     		<div class="btn btn_guardar" onclick="guardar()"></div>
            <div class="btn btn_cerrar" onclick="cerrar()"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>
</body>
<script type="text/javascript">
	function guardar() {
		$("#conveniosForm").submit();
	}

	function cerrar() {
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx + "/administrador/convenio";
	}

	$(document)
			.ready(
					function() {
						$.ajaxSetup({
							cache : false
						});
						inicializaAutocomplete();
						
						$("#datepickerInicio").click(function() {
							  $("#vigenteDesde").datepicker("show");
						});
						
						$("#datepickerFin").click(function() {
							  $("#vigenteHasta").datepicker("show");
						});						

						$("#lugarAtencion").alphanum({
							allowSpace : true,
							disallow : '¥Á«ø®°∑'
						});
						$("#prestador").alphanum({
							allowSpace : true,
							disallow : '¥Á«ø®°∑'
						});
						$("#convenio").alphanum({
							allowSpace : true,
							disallow : '¥Á«ø®°∑'
						});	
						$("#identificadorConvenio").alphanum({
							allowSpace : true,
							disallow : '¥Á«ø®°∑'
						});								
						$("#codigoLugarAtencion").numeric({
							allowSpace : false,
							disallow : '¥Á«ø®°∑'
						});

						var error = $("#error").val();
						var exito = $("#exito").val();
						if (error != "") {
							$.fancybox
									.open({
										content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"
												+ error
												+ "</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
										openEffect : 'elastic',
										openSpeed : 150,
										closeEffect : 'elastic',
										closeSpeed : 150,
										autoSize : false,
										width : 470,
										height : 185
									});
						}
						if (exito != "") {
							$.fancybox
									.open({
										content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario1\"></div><h1>"
												+ exito
												+ "</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
										openEffect : 'elastic',
										openSpeed : 150,
										closeEffect : 'elastic',
										closeSpeed : 150,
										autoSize : false,
										width : 470,
										height : 185
									});
						}

					});

	function inicializaAutocomplete(){
		$("#lugarAtencion").autocomplete({
		source: "buscarLugarAtencionDescripcion",
 	   	minLength: 3,
        select: function( event, ui ){
        	if (ui.item.lugarDeAtencionId!=-1) {
       	    	$("#lugarAtencion").val(ui.item.descripcion);
       	    	$("#idLugarDeAtencion").val(ui.item.lugarDeAtencionId);
       	    	$("#codigoLugarAtencion").val(ui.item.codigoLugarAtencion);
			}else{
       	    	$("#lugarAtencion").val("");
       	    	$("#idLugarDeAtencion").val(0);
			}
            return false;
		},
		focus: function (event, ui) {
			if (ui.item.lugarDeAtencionId!=-1) {
				$("#lugarAtencion").val(ui.item.descripcion);
			}else{
       	    	$("#lugarAtencion").val("");
       	    	$("#idLugarDeAtencion").val(0);
			}
			return false;
        }

		}).autocomplete("instance")._renderItem = function( ul, item ) {
		      return $("<li>")
		        .append(item.descripcion)
		        .appendTo(ul);
		}; 
		
		$("#prestador").autocomplete({
			source: "buscarPrestadorDescripcion",
	 	   	minLength: 3,
	        select: function( event, ui ){
	        	if (ui.item.prestadorId!=-1) {
	       	    	$("#prestador").val(ui.item.nombreRazonSocial);
	       	    	$("#idPrestador").val(ui.item.prestadorId);
	       	    	$("#busqueda").val(1);
				}else{
	       	    	$("#prestador").val("");
	       	    	$("#idPrestador").val(-1);
				}
	            return false;
			},
			focus: function (event, ui) {
				if (ui.item.prestadorId!=-1) {
					$("#prestador").val(ui.item.nombreRazonSocial);
				}else{
	       	    	$("#prestador").val("");
	       	    	$("#idPrestador").val(-1);
				}
				return false;
	        }

		}).autocomplete("instance")._renderItem = function( ul, item ) {
		      return $("<li>")
		        .append(item.nombreRazonSocial)
		        .appendTo(ul);
		};  		
		
		$("#codigoLugarAtencion").autocomplete({
			source: "buscarLugarAtencionCodigo",
	 	   	minLength: 3,
	        select: function( event, ui ){
	        	if (ui.item.lugarDeAtencionId!=-1) {
	       	    	$("#lugarAtencion").val(ui.item.descripcion);
	       	    	$("#idLugarDeAtencion").val(ui.item.lugarDeAtencionId);
	       	    	$("#codigoLugarAtencion").val(ui.item.codigoLugarAtencion);
				}else{
	       	    	$("#lugarAtencion").val("");
	       	    	$("#codigoLugarAtencion").val("");
	       	    	$("#idLugarDeAtencion").val(0);
				}
	            return false;
			},
			focus: function (event, ui) {
				if (ui.item.lugarDeAtencionId!=-1) {
					$("#codigoLugarAtencion").val(ui.item.codigoLugarAtencion);
				}else{
	       	    	$("#lugarAtencion").val("");
	       	    	$("#codigoLugarAtencion").val("");
	       	    	$("#idLugarDeAtencion").val(0);
				}
				return false;
	        }

			}).autocomplete("instance")._renderItem = function( ul, item ) {
				if (item.lugarDeAtencionId!=-1) {
					return $("<li>")
			        .append(item.codigoLugarAtencion)
			        .appendTo(ul);
				}else{
					return $("<li>")
			        .append(item.descripcion)
			        .appendTo(ul);
				}
			      
			};
	}	
	
	$(function() {
		$("#vigenteDesde").datepicker({
			changeMonth: true,
			changeYear: true,
			minDate: 0,
			yearRange: "2016:2099"
		});
		
		$("#vigenteHasta").datepicker({
			changeMonth: true,
			changeYear: true,
			minDate: 0,
			yearRange: "2016:2099"
		});		
	});
	
	function llenarPrestador(){
		var idPrestador = $("#idPrestador").val();
		if (idPrestador!=-1) {
		    $.getJSON("buscarPrestadorById", {id:idPrestador} ,function(response){
		    	$("#prestador").val(response.nombreRazonSocial);
		    });	
		}
	}
</script>

</html>