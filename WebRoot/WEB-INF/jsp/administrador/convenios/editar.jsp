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
<form:form name='conveniosForm' action="convenioEditar" method='POST' commandName="conveniosForm" id="conveniosForm">
	<form:hidden path="error" />
	<form:hidden path="exito" />
	<form:hidden path="busqueda" />
	<form:hidden path="idLugarDeAtencion"/>
	<form:hidden path="idConvenio"/>
	
<div id="admin_contenido2">

                    
    <div class="linea_supervisor margen_arriba3" style="height:72px;">
    	
   		<div class="linea_supervisor margen_arriba4">     
	
		</div>
    </div>  
 
  <div class="admin_contenidoFrame">   
  
  <div class="linea_corta2">
    
    <div class="linea_supervisor margen">
   		<form:input cssClass="campo_supervisor tam_2 mayusculas" placeholder="CÛdigo Lugar de atenciÛn *" path="codigoLugarAtencion"/>
   		<form:input cssClass="campo_supervisor tam_10 mayusculas" placeholder="Lugar de atenciÛn *" path="lugarAtencion" />
    </div>
    
    	<div class="linea3 margen">
			<div class="error tam_2"><form:errors path="codigoLugarAtencion" /></div>
			<div class="error tam_10"><form:errors path="lugarAtencion" /></div>
		</div>
    
    <div class="linea_supervisor margen">
	    <form:select cssClass="selectt campo_supervisor tam_2" path="idAsegurador" onchange="verificarAsegurador()">
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
	 
	 <div class="linea_corta margen_arriba2 margen">
		 <div class="tabla"> 
	     
	    <div class="tabla_contenedor desplazar alto_tabla5 centrado">

	        <div class="linea_tabla alto">
	        	<div class="celda3 fondo_v texto_supervisor blanco tam_17 centrado">Correl.
	            </div>
	            <div class="celda3 fondo_v texto_supervisor blanco tam_18 centrado">Nombre
	            </div>
	            <div class="celda3 fondo_v texto_supervisor blanco tam_4 centrado">Id Asegurador
	            </div>
	            <div class="celda3 fondo_v texto_supervisor blanco tam_2 centrado">Vigencia
	            </div>
	    	</div>
	    		    
	    	<div id="contenidoTabla">
	 		</div> 	    		 
	     </div> 

	    </div>

	    </div>	 
    
    <!--  
    <div class="linea_supervisor margen">
     		<div class="btn btn_registrar_convenio"></div>
     </div> 
	-->
  
	<div class="linea_supervisor margen margen_arriba2">
		
		<form:input cssClass="campo_supervisor tam_18 mayusculas" placeholder="Nombre del convenio *" path="convenio" maxlength="50" />
   		<form:input cssClass="campo_supervisor tam_19 mayusculas" placeholder="Identificador del convenio seg˙n el Asegurador *" path="identificadorConvenio" maxlength="50"/>
   		
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
     		<div class="btn btn_eliminar" onclick="eliminar()"></div>
     		<div class="btn_grande btn_cuadro_prest"></div>
     		<div class="btn_grande btn_cuadro_med"></div>
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
	
	function eliminar() {
		$("#conveniosForm").attr("action", "convenioEliminar");
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
						
						var idAsegurador = $("#idAsegurador").val();
						var idPrestador = $("#idPrestador").val();
						var idLugarDeAtencion = $("#idLugarDeAtencion").val();
						
						if (idAsegurador!=-1 || idPrestador!=-1 || idLugarDeAtencion !=0) {
							buscarConvenios();
						}
						
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
       	    	buscarConvenios();
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
	       	    	buscarConvenios();
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
	       	    	buscarConvenios();
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
			buscarConvenios();
		    $.getJSON("buscarPrestadorById", {id:idPrestador} ,function(response){
		    	$("#prestador").val(response.nombreRazonSocial);
		    });	
		}
	}
	
	function verificarAsegurador(){
		var idAsegurador = $("#idAsegurador").val();
		if (idAsegurador!=-1) {
			buscarConvenios();
		}
	}
	
	function buscarConvenios(){
		var conveniosForm = $("#conveniosForm").serialize();
	    $.getJSON("buscarConvenios", conveniosForm ,function(response){
	    	var html = "";
	    	if (response.length!=0) {
				for (var i = 0; i < response.length; i++) {
					html = html + "<div class='linea_tabla alto'><div class='celda2 texto_supervisor blanco tam_17 centrado'>"+response[i].convenioId+"</div>"+
		            			  "<div class='celda2 texto_supervisor blanco tam_18 centrado link' onclick='precargar("+response[i].idLugarAtencion+","+response[i].codigoLugarAtencion+",\""+response[i].lugarAtencion+"\","+response[i].idAsegurador+","+response[i].idPrestador+",\""+response[i].prestador+"\",\""+response[i].identificador+"\",\""+response[i].convenio+"\","+response[i].convenioId+",\""+response[i].vigenciaDesde+"\",\""+response[i].vigenciaHasta+"\");'>"+response[i].convenio+"</div>"+
		            			  "<div class='celda2 texto_supervisor blanco tam_4 centrado'>"+response[i].idAsegurador+"</div>"+
		                          "<div class='celda2 texto_supervisor blanco tam_2 centrado'>"+response[i].vigencia+"</div></div>";
				}	
			}else{
				$("#idConvenio").val(0);
				html = html + "";
				$.fancybox.open({
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"
							+ "No hay convenios registrados para los datos seleccionados"
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
			$("#contenidoTabla").html(html);
	    });	
	}
	
	function precargar(idLugarAtencion,codigoLugarAtencion,lugarAtencion,idAsegurador,idPrestador,prestador,identificador,convenio,convenioId,vigenciaDesde,vigenciaHasta){
		$("#idLugarDeAtencion").val(idLugarAtencion);
 		$("#codigoLugarAtencion").val(codigoLugarAtencion);
		$("#lugarAtencion").val(lugarAtencion);
		$("#idAsegurador").val(idAsegurador);
		$("#idPrestador").val(idPrestador);
		$("#prestador").val(prestador);
		$("#identificadorConvenio").val(identificador);
		$("#convenio").val(convenio);
		$("#idConvenio").val(convenioId);
		$("#vigenteDesde").val(vigenciaDesde);
		$("#vigenteHasta").val(vigenciaHasta);
	}
</script>

</html>