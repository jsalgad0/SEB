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

	<form:form name='ordenServicioForm' action="ordenServicio" method='POST' commandName="ordenServicioForm" id="ordenServicioForm">
		<div id="contenedor4_popup">
		
			<div class="icon_popup reportem"></div>
			
			<div class="linea_popup"><h1>Orden de Servicios Médicos</h1>
		    	<div class="campo_datos_popup tam_8">${ordenServicioForm.ordenServicio}</div>
		    	<h1>de fecha</h1>
		    	<div class="campo_datos_popup tam_6">
					<script>
						var today = new Date();
					    var dd = today.getDate();
					    var mm = today.getMonth()+1; //January is 0!
					    var yyyy = today.getFullYear();
					    if(dd<10){
					        dd='0'+dd
					    } 
					    if(mm<10){
					        mm='0'+mm
					    } 
					    var today = dd+'/'+mm+'/'+yyyy;
						document.write(today);
					
				</script>
			
		    	</div>
		    </div>
		    
			<div  class="tabla_popup2 margen10">   
				<div class="tabla_popup2">   
					<div class="fila_popup">
						<div class="celda_popup fondo_v texto_popup1 blanco tam_fijo6">Prestaciones Solicitadas
			            </div>
			            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo7">Fecha de Atención
			            </div>
			            <div class="celda_popup fondo_v texto_popup1 blanco tam_fijo9">
			            </div>
			    	</div> 
				</div>
				
				<div class="tabla_contenedor_popup2 desplazar alto_tabla2">  	
					<div class="tabla_popup2">
						<c:forEach items="${prestacionesPorTomarVos}" var="prestacionesPorTomarVo" varStatus="index">
							<div class="fila_popup">
				        		<div class="celda_popup texto_popup1 blanco tam_fijo6">${prestacionesPorTomarVo.prestacion}</div>
				        		
				        		<c:choose>
				        			<c:when test="${prestacionesPorTomarVo.idAtencionMedicaPorTomar == 0}">
				        				<input type="hidden" id="prestacion${prestacionesPorTomarVo.idPrestacion}" value="${prestacionesPorTomarVo.prestacion}"/>
				        				<input type="hidden" id="cantidad${prestacionesPorTomarVo.idPrestacion}" value="${prestacionesPorTomarVo.cantidad}"/>
				        				<div class="celda_popup texto_popup1 blanco tam_fijo7"></div>
				        				<div class="celda_popup texto_popup1 blanco tam_fijo9"><form:checkbox path="idsPrestaciones" value="${prestacionesPorTomarVo.idPrestacion}" /></div>				        			
				        			</c:when>
				        			<c:otherwise>
				        				<div class="celda_popup texto_popup1 blanco tam_fijo7">${prestacionesPorTomarVo.fecha}</div>
				        				<div class="celda_popup texto_popup1 blanco tam_fijo9"></div>				        				
				        			</c:otherwise>
				        		</c:choose>
				    	 	</div> 				
						</c:forEach> 
					</div>
				</div> 
			</div>
			
	 		<div class="linea_supervisor centrado">
        		<div class="error_popup ancho2" id="error" style="display: none;">
            		Seleccione alguna prestacion
            	</div> 
       	 	</div>
			 
			<div class="btn_popup">
				<div class="btn_aceptar_popup" onclick="enviar();"> </div>
				<div class="btn_cerrar_popup" onclick="parent.$.fancybox.close();"></div>
			</div>
		    
		   
		</div>

	</form:form>
	
	<script type="text/javascript">
		function enviar(){
			var selecciono = false;
			var prestacionesPorOrdenServicio = "";
			parent.document.getElementById("prestacionesPorOrdenServicio");
			$("input[name='idsPrestaciones']:checked").each(function (){
				selecciono = true;
				parent.document.getElementById("seleccionPorOrdenServicio").value = true;
				var idPrestacion = $(this).val();
			    var prestacion = $("#prestacion"+idPrestacion).val();
			    var cantidad = $("#cantidad"+idPrestacion).val();				
				prestacionesPorOrdenServicio = prestacionesPorOrdenServicio + idPrestacion +"|" + prestacion + "|" + cantidad + ",";
			});
			if (prestacionesPorOrdenServicio.length!=0) {
				prestacionesPorOrdenServicio = prestacionesPorOrdenServicio.substring(0, prestacionesPorOrdenServicio.length-1);	
			}
			
			
			parent.document.getElementById("prestacionesPorOrdenServicio").value = prestacionesPorOrdenServicio;
			if (selecciono == false) {
				$("#error").show();
			}else{
				parent.$.fancybox.close();
			}
		}
	</script>
</body>
</html>
