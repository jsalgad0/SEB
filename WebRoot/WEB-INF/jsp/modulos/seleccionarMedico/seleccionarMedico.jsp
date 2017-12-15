<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_recepcion.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">

<script type="text/javascript">

</script>
<body style="overflow: hidden;">

	<form:form name='seleccionarMedicoForm' action="medico" method='POST' commandName="seleccionarMedicoForm" id="seleccionarMedicoForm">
		<input type="hidden" value="false" id="seleccionarMedico">
		<input type="hidden" id="idMedico">
		<input type="hidden" id="idTiempo">
		<input type="hidden" id="medico">
		<div id="contenedor4_popup">
		
			<div class="icon_popup b_medico"></div>
			<div class="linea_popup"><h1>BÚSQUEDA DE MÉDICOS</h1></div>
		
			<div class="tabla_popup3 margen10">
				<div class="tabla_popup3">   
					<div class="fila_popup">
		        		<div class="celda_popup fondo_v texto_popup2 blanco tam_13">Médico y Horarios disponibles
		            	</div>
		    		</div> 
				</div>
					
				<div class="tabla_contenedor_popup3 desplazar_2 alto_1">  		
					<div class="tabla_popup3 alto_1">
						<c:forEach items="${seleccionarMedicoForm.medicosHorariosVos}" var="medicosHorariosVo">
							<div class="fila_popup">
								<div class="celda_popup fondo_g texto_popup1 blanco tam_fijo1">${medicosHorariosVo.nombre}</div>
								<c:forEach items="${medicosHorariosVo.horariosVos}" var="horarioVo">
						            <div id="celda_${horarioVo.id}" onclick="cambiarBgCol(${horarioVo.id}); elegirMedico('${medicosHorariosVo.nombre}',${medicosHorariosVo.idUsuario},'${horarioVo.horario}')" class="celda_popup fondo_v2 texto_popup1 blanco tam_fijo8">${horarioVo.horario}
						            </div>								
								</c:forEach>
							</div> 						
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="error_popup ancho3 margen11" style="display: none;" id="error">
			     Es necesario que seleccione un horario
			</div>
			<div class="btn_popup">
				<div class="btn_guardar_popup" onclick="guardar();"></div>
				<div class="btn_cerrar_popup" onclick="parent.$.fancybox.close();"></div>
			</div>
		   
		</div>	

	</form:form>
	
	<script type="text/javascript">
	function elegirMedico(medico, idMedico, horario){
 		$("#idMedico").val(idMedico);
		$("#idTiempo").val(horario);
		$("#medico").val(medico);
		$("#seleccionarMedico").val("true");
	}	
	
	function cambiarBgCol(num){
		$(".fondo_v2").removeClass("fondo_g2");
		$("#celda_"+num).addClass("fondo_g2");
	} 	
	
	function guardar(){
		var seleccionarMedico = $("#seleccionarMedico").val();
		if (seleccionarMedico=="true") {
	 		window.top.$("#idMedico").val($("#idMedico").val());
			window.top.$("#idTiempo").val($("#idTiempo").val());
			window.top.$("#medico").val($("#medico").val());			
			parent.$.fancybox.close();
		}else{
			$("#error").show();
		}
	}
	</script>
</body>
</html>