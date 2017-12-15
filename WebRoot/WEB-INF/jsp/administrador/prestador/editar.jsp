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
	<form:form name='prestadorForm' action="editarPrestador" method='POST' commandName="prestadorForm" id="prestadorForm">
		<form:hidden path="error"/>
		<form:hidden path="exito"/>
		<form:hidden path="editar"/>
		<form:hidden path="busqueda"/>
		<form:hidden path="idPrestador"/>
		<div id="admin_contenido2">


			<div class="linea_supervisor margen_arriba3" style="height: 72px;">

				<div class="linea_supervisor margen_arriba4"></div>
			</div>

			<div class="admin_contenidoFrame">

				<div class="linea_corta2">

					<div class="linea_supervisor margen">
					   		<form:input path="rfc" cssClass="campo_supervisor tam_7 mayusculas" cssStyle="margin-right:0px !important;" placeholder="RFC *"/><!--
        					  --><input class="btn_solo buscar_icono" onclick="buscar()" />
   							<form:input path="prestador" cssClass="campo_supervisor tam_18 mayusculas" placeholder="Nombre Û RazÛn Social *" maxlength="100" />
					</div>

					<div class="linea_supervisor margen">
						<div class="error tam_7"><form:errors path="rfc"/></div>
						<div class="error tam_18"><form:errors path="prestador"/></div>
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
						<form:input path="nombre" cssClass="campo_supervisor tam_20 margen_izq1 mayusculas" placeholder="Nombre *" maxlength="100" />
					</div>

					<div class="linea3 margen10">
						<div class="error tam_20"><form:errors path="nombre"/></div>
					</div>

					<div class="linea_supervisor margen">
						<form:input path="telefono1" cssClass="campo_supervisor tam_7 margen12" placeholder="TelÈfono 1 *" maxlength="10" /> 
						<form:input path="telefono2" cssClass="campo_supervisor tam_7 margen5" placeholder="TelÈfono 2" maxlength="10" />
					</div>

					<div class="linea3 margen10">
						<div class="error tam_7"><form:errors path="telefono1"/></div>
						<div class="error tam_7 margen5"><form:errors path="telefono2"/></div>
					</div>

					<div class="linea_supervisor margen">
						<form:input path="correo" cssClass="campo_supervisor tam_11 margen12" placeholder="Correo electrÛnico *" maxlength="50" />
					</div>

					<div class="linea3 margen10">
						<div class="error tam_7"><form:errors path="correo"/></div>
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
	function guardar(){
		$("#prestadorForm").attr("action", "editarPrestador");
		$("#prestadorForm").submit();
	}
	
	function buscar(){
		$("#prestadorForm").attr("action", "buscarPrestador");
		$("#prestadorForm").submit();
	}
	
	function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/administrador/prestador";
	}
	
	$( document ).ready(function() {
		$.ajaxSetup({ cache: false });
		inicializaAutocomplete();
		
		$("#rfc").alphanum({
	        allowSpace: false,
	        disallow: '¥Á«ø®°∑'
	    });			
		$("#prestador").alphanum({
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
		
		$('#rfc').prop('readonly', false);
		$('#prestador').prop('readonly', false);
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
		$('#correo').prop('readonly', true);
		
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
			$('#rfc').prop('readonly', true);
			$('#prestador').prop('readonly', true);
			$('#idEstado').prop('readonly', false);
			$('#idMunicipio').prop('readonly', false);
			$('#idColonia').prop('readonly', false);
			$('#calle').prop('readonly', false);
			$('#numeroInterno').prop('readonly', false);
			$('#numeroExterno').prop('readonly', false);
			$('#cp').prop('readonly', false);
			$('#nombre').prop('readonly', false);
			$('#telefono1').prop('readonly', false);
			$('#telefono2').prop('readonly', false);
			$('#correo').prop('readonly', false);
		}
		
	});	
	
	function inicializaAutocomplete(){
		$("#prestador").autocomplete({
		source: "buscarPrestadorDescripcion",
 	   	minLength: 3,
        select: function( event, ui ){
        	if (ui.item.prestadorId!=-1) {
       	    	$("#prestador").val(ui.item.nombreRazonSocial);
       	    	$("#idPrestador").val(ui.item.prestadorId);
       	    	$("#busqueda").val(1);
       	    	buscar();
			}else{
       	    	$("#prestador").val("");
       	    	$("#idPrestador").val(0);
			}
            return false;
		},
		focus: function (event, ui) {
			if (ui.item.prestadorId!=-1) {
				$("#prestador").val(ui.item.nombreRazonSocial);
			}else{
       	    	$("#prestador").val("");
       	    	$("#idPrestador").val(0);
			}
			return false;
        }

	}).autocomplete("instance")._renderItem = function( ul, item ) {
	      return $("<li>")
	        .append(item.nombreRazonSocial)
	        .appendTo(ul);
	};  		
}
	
	
</script>
</html>