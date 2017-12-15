<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
	<title>Autorización/Rechazo</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="0">	
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.alphanum.js"></script>
</head>
<body style="overflow-y:hidden">

<div id="contenedor2_popup">
	<form:form name='supervisorNotasNoFirmadasForm' action="actualizarNotaNoFirmada" method='POST'
		commandName="supervisorNotasNoFirmadasForm" id="supervisorNotasNoFirmadasForm">
		<form:hidden path="autorizarRechazar"/>
		<form:hidden path="idAfiliado"/>
		<form:hidden path="idAtencionMedica"/>
		<form:hidden path="tipoMotivo"/>
		<form:hidden path="finalizo"/>
		<form:hidden path="medicoAfiliado"/>
		
		<div class="icon_popup ask"></div>
	   	
	   	<form:input path="motivo" id="motivo" cssClass="campo_popup campo_size1_popup" placeholder="Motivo de Autorización/Rechazo"/>
	   	<div class="error_popup">${supervisorNotasNoFirmadasForm.error}</div>
		<div class="btn_popup">
			<div class="linea_botones_popup">
				<div class="btn_aceptar_popup" onclick="enviar();"></div>
				<div class="margen2_popup"></div> 
	     		<div class="btn_cerrar_popup" onclick="parent.$.fancybox.close();"></div>
			</div>    
		</div>
   	</form:form>
</div>
<script type="text/javascript">
	$( document ).ready(function() {
		
		$("#motivo").alphanum({
	        allowSpace: false,
	        disallow: '´çÇ¿¨¡·'
	    });
		
		var finalizo = $("#finalizo").val();
		if (finalizo == "true") {
			parent.$.fancybox.close();
		}
	});
	
	function enviar(){
		$("#supervisorNotasNoFirmadasForm").submit();
	}

</script>
</body>
</html>