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
<body style="overflow: hidden;">
	<form:form name='lugarAtencionForm' action="nuevoLugaratencion" method='POST' commandName="lugarAtencionForm" id="lugarAtencionForm">
		<form:hidden path="error"/>
		<form:hidden path="exito"/>
		<form:hidden path="editar"/>
		<form:hidden path="busqueda"/>
		<form:hidden path="idLugarAtencion"/>
		<form:hidden path="rolesSeleccionadosPrimero"/>
		<form:hidden path="rolesSeleccionadosSegundo"/>
		<div id="admin_contenido2">


			<div class="linea_supervisor margen_arriba3" style="height: 72px;">

				<div class="linea_supervisor margen_arriba4"></div>
			</div>

			<div class="admin_contenidoFrame">

				<div class="linea_corta2">

					<div class="linea_supervisor margen">
					   		<form:input path="codigo" cssClass="campo_supervisor tam_7" cssStyle="margin-right:0px !important;" placeholder="CÛdigo*"/><!--
        					  --><input class="btn_solo buscar_icono" onclick="buscar()" />
   							<form:input path="lugarAtencion" cssClass="campo_supervisor tam_18 mayusculas" placeholder="Nombre Û DescripciÛn *" maxlength="100" />
					</div>

					<div class="linea_supervisor margen">
						<div class="error tam_7"><form:errors path="codigo"/></div>
						<div class="error tam_18"><form:errors path="lugarAtencion"/></div>
					</div>

					<div class="linea_supervisor margen">
						<div class="texto_supervisor5 verde tam_6 interlineado2">DIRECCI”N</div>
						<form:input path="calle" cssClass="campo_supervisor tam_20" placeholder="Calle *" maxlength="100" /> 
						<form:input path="numeroExterno" cssClass="campo_supervisor tam_6" placeholder="# Externo*" maxlength="10" /> 
						<form:input path="numeroInterno" cssClass="campo_supervisor tam_6" placeholder="# Interno*" maxlength="10" />
					</div>

					<div class="linea_supervisor margen10">
						<div class="error tam_20"><form:errors path="calle"/></div>
						<div class="error tam_6 margen14"><form:errors path="numeroExterno"/></div>
						<div class="error tam_6 margen14"><form:errors path="numeroInterno"/></div>
					</div>

					<div class="linea_supervisor margen">
						<form:select path="idEstado" id="idEstado" onchange="municipios();" cssClass="selectt campo_supervisor tam_2">
							<form:option value="-1" label="Estado" />
							<form:options items="${catEstados}" itemLabel="estado" itemValue="estadoId" ></form:options>
						</form:select>
						
						<form:select path="idMunicipio" id="idMunicipio" onchange="colonias();" cssClass="selectt campo_supervisor tam_2">
							<form:option value="-1" label="DelegaciÛn" />
							<form:options items="${catMunicipios}" itemLabel="municipio" itemValue="municipioId"></form:options>
						</form:select>

						<form:select path="idColonia" id="idColonia" cssClass="selectt campo_supervisor tam_9">
							<form:option value="-1" label="Colonia" />
							<form:options items="${catColonias}" itemLabel="colonia" itemValue="ColoniaId"></form:options>
						</form:select>
					 						
						<form:input path="cp" cssClass="campo_supervisor tam_6" placeholder="C.P. *" maxlength="5" />

					</div>

					<div class="linea_supervisor margen">
						<div class="error tam_2"><form:errors path="idEstado"/></div>
						<div class="error tam_2"><form:errors path="idMunicipio"/></div>
						<div class="error tam_9"><form:errors path="idColonia"/></div>
						<div class="error tam_6"><form:errors path="cp"/></div>
					</div>

					<div class="linea_supervisor margen">
						<div class="texto_supervisor5 verde tam_8 interlineado2">CONTACTO</div>
						<form:input path="nombre" cssClass="campo_supervisor tam_10 margen_izq1 mayusculas" placeholder="Nombre *" maxlength="100" />
						<form:input path="telefono1" cssClass="campo_supervisor tam_7" placeholder="TelÈfono 1 *" maxlength="10" /> 
						<form:input path="telefono2" cssClass="campo_supervisor tam_7" placeholder="TelÈfono 2" maxlength="10" />
					</div>

					<div class="linea3 margen10">
						<div class="error tam_10"><form:errors path="nombre"/></div>
						<div class="error tam_7"><form:errors path="telefono1"/></div>
						<div class="error tam_7"><form:errors path="telefono2"/></div>
					</div>

					<div class="linea_supervisor margen">
						<form:input path="correoLugarAtencion" cssClass="campo_supervisor tam_11 margen12" placeholder="Correo electrÛnico *" maxlength="50" />
					</div>

					<div class="linea3 margen10">
						<div class="error tam_7"><form:errors path="correoLugarAtencion"/></div>
					</div>

					<div class="linea3 margen margen">
					
					<div class="texto_supervisor4 verde">
					         AsignaciÛn de Roles <br /> al  Lugar de AtenciÛn
					</div>         
				 
				     <div class="archivos_caja">Cat·logo de Roles
				     
					<select class="campo_cajax" id="primero" style="width:20vw; height: 8vw;" name="primero" multiple="multiple">
						<c:forEach items="${lugarAtencionForm.rolesLugarAtencionPrimero}" var="roleLugarAtencion">
							<option value="${roleLugarAtencion.rolId}">${roleLugarAtencion.rol}</option>
						</c:forEach>
					</select>
				  
				     </div>
				     
					 <!-- Fijaros que a la funcion le paso el ID del select que envia y el segundo parametro es el ID que recibe -->
					<input style="margin-top:5vw;" type="button" value="&rarr;" onclick="volcarSelects('primero', 'segundo');" />
					
					<!-- En este caso quiero poder "quitar" opciones, por lo que invierto el orden de los parametros pasados a la funciÛn -->
					<input style="margin-top:5vw;" type="button" value="&larr;" onclick="volcarSelects('segundo', 'primero');" />
					     
				
				     <div class="archivos_caja">Roles del Lugar de AtenciÛn
				     <select class="campo_cajax"  style="width:20vw; height: 8vw;" id="segundo" name="segundo" multiple="multiple">
						<c:forEach items="${lugarAtencionForm.rolesLugarAtencionSegundo}" var="roleLugarAtencion">
							<option value="${roleLugarAtencion.rolId}">${roleLugarAtencion.rol}</option>
						</c:forEach>				     
					</select>
				     </div>
						
					</div>

					<div class="linea_supervisor margen margen_arriba">
						<div class="btn btn_eliminar" onclick="eliminar()"></div>
						<div class="btn btn_cerrar" onclick="cerrar()"></div>
					</div>
				</div>
			</div>
		</div>

	</form:form>


</body>

<script type="text/javascript">
	function eliminar(){
		$("#lugarAtencionForm").attr("action", "eliminarLugarAtencion");		
		$("#lugarAtencionForm").submit();
	}
	
	function buscar(){
		$("#lugarAtencionForm").attr("action", "buscarLugarAtencionEliminar");		
		$("#lugarAtencionForm").submit();
	}
	
	function volcarSelects(emisorId, receptorId){
		
		// Accedemos a los 2 selects
		var emisor = document.getElementById(emisorId);
		var receptor = document.getElementById(receptorId);
		
		// Obtenemos algunos datos necesarios
		var posicion = receptor.options.length;
		var selecionado = emisor.selectedIndex;
		
		if(selecionado != -1) {
		
			var volcado = emisor.options[selecionado];
			
			// Volcamos la opcion al select receptor y lo eliminamos del emisor
			receptor.options[posicion] = new Option(volcado.text, volcado.value);
			emisor.options[selecionado] = null;
			emisor.selectedIndex=selecionado;
			if(selecionado>emisor.length-1){
				emisor.selectedIndex=emisor.length-1;
			}

		}
	}		
	
	function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/administrador/lugarAtencion";
	}
	
	$( document ).ready(function() {
		$.ajaxSetup({ cache: false });
		inicializaAutocomplete();
		
		$("#lugarAtencion").alphanum({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });		
		$("#calle").alphanum({
	        allowSpace: true,
	        disallow: '¥Á«ø®°∑'
	    });		
		$("#numeroExterno").alphanum({
	        allowSpace: true,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#numeroInterno").alphanum({
	        allowSpace: true,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#cp").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#nombre").alpha({
	        allowSpace: true,
	        disallow: '¥Á«ø®°∑'
	    });	
		$("#telefono1").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });
		$("#telefono2").numeric({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });		
		$("#correo").alphanum({
	        allowSpace: false,
	        allow: '@.-_',
	        disallow: '¥Á«ø®°∑'
	    });
		
		$('#codigo').prop('readonly', false);
		$('#lugarAtencion').prop('readonly', false);
		$('#idEstado').prop('readonly', true);
		$('#idMunicipio').prop('readonly', true);
		$('#idColonia').prop('readonly', true);
		$('#calle').prop('readonly', true);
		$('#numeroInterno').prop('readonly', true);
		$('#numeroExterno').prop('readonly', true);
		$('#cp').prop('readonly', true);
		$('#nombre').prop('readonly', true);
		$('#telefono1').prop('readonly', true);
		$('#telefono2').prop('readonly', true);
		$('#correoLugarAtencion').prop('readonly', true);
		
		var error = $("#error").val();
		var exito = $("#exito").val();
		var editar = $("#editar").val();
		if (error!="") {
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"+error+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
				autoSize: false,
				width: 470,
				height: 185
			});	
		}
		if (exito!="") {
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario1\"></div><h1>"+exito+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
				autoSize: false,
				width: 470,
				height: 185
			});	
		}
		if (editar == "true") {
			$('#codigo').prop('readonly', true);
			$('#lugarAtencion').prop('readonly', true);
			$('#idEstado').prop('readonly', true);
			$('#idMunicipio').prop('readonly', true);
			$('#idColonia').prop('readonly', true);
			$('#calle').prop('readonly', true);
			$('#numeroInterno').prop('readonly', true);
			$('#numeroExterno').prop('readonly', true);
			$('#cp').prop('readonly', true);
			$('#nombre').prop('readonly', true);
			$('#telefono1').prop('readonly', true);
			$('#telefono2').prop('readonly', true);
			$('#correoLugarAtencion').prop('readonly', true);
		}
		
	});	
	
	function inicializaAutocomplete(){
		$("#lugarAtencion").autocomplete({
		source: "buscarLugarAtencionDescripcion",
 	   	minLength: 3,
        select: function( event, ui ){
        	if (ui.item.lugarDeAtencionId!=-1) {
       	    	$("#lugarAtencion").val(ui.item.descripcion);
       	    	$("#idLugarAtencion").val(ui.item.lugarDeAtencionId);
       	    	$("#busqueda").val(1);
       	    	buscar();
			}else{
       	    	$("#lugarAtencion").val("");
       	    	$("#idLugarAtencion").val(0);
			}
            return false;
		},
		focus: function (event, ui) {
			if (ui.item.lugarDeAtencionId!=-1) {
				$("#lugarAtencion").val(ui.item.descripcion);
			}else{
       	    	$("#lugarAtencion").val("");
       	    	$("#idLugarAtencion").val(0);
			}
			return false;
        }

	}).autocomplete("instance")._renderItem = function( ul, item ) {
	      return $("<li>")
	        .append(item.descripcion)
	        .appendTo(ul);
	};  		
}
	
	
</script>
</html>