<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_recepcion.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">


<html>
<body style="overflow:hidden;" class="fondo_claro_popup">
<form:form name='atencionTerminarForm' action="verificarClave" method='POST' commandName="atencionTerminarForm" id="atencionTerminarForm">
	<form:hidden path="error" id="error"/>
	<form:hidden path="exito" id="exito"/>
	<form:hidden path="intentos" id="intentos"/>

	<div id="contenedor_popup" class="centrar">
<!-- para el width:520px; height:380px; !-->
	<div class="icon_popup usuario1 pad_arriba_popup"></div>
   	<input class="campo_popup_verde campo_size1_popup margen_arriba_popup" placeholder="RFC" value="${userInfo.usuarios.rfc}">
<!--    	<div class="error_popup campo_size1_popup">RFC inexistente</div> -->
   	
   	<form:password cssClass="campo_popup_verde campo_size1_popup" placeholder="Clave" path="clave" />
   	<div class="error_popup campo_size1_popup" id="mensajeErrorClave"></div>
   
   <div class="btn_popup margen_arriba_popup">
   		<div class="linea_botones_popup">
   			<div class="btn_aceptar_popup" onclick="guardar();"></div>
            <div class="margen2_popup"></div> 
        </div>    
   </div>
    
   
</div>
                    

</form:form>

<script type="text/javascript">

	function guardar() {
		$("#atencionTerminarForm").submit();
	}

	$(document).ready(function(){
		$.ajaxSetup({ cache: false });
		var error = $("#error").val();
		var exito = $("#exito").val();
		var intentos = $("#intentos").val();
		
		if (intentos == 3) {
			parent.$.fancybox.close();
		}else{
			if (error=="") {
				if (exito!="") {
					parent.document.getElementById("claveCorrecta").value = 1;
					parent.$.fancybox.close();
				}				
			}else{
				$("#mensajeErrorClave").html(error);
			}	
		}
	});
</script>
</body>
</html>