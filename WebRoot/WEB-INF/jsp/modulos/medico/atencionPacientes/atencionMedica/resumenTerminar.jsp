<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_recepcion.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">


<html>
<body style="overflow:hidden;">
	<form:form name='atencionTerminarForm' action="finTerminar" method='POST' commandName="atencionTerminarForm" id="atencionTerminarForm">
		<form:hidden path="idAtencion" id="idAtencion"/>
		<form:hidden path="idAfiliadoTerminar" id="idAfiliadoTerminar"/>
		<form:hidden path="nombreAfiliado" id="nombreAfiliado"/>
		<form:hidden path="apellidoPaternoAfiliado" id="apellidoPaternoAfiliado"/>
		<form:hidden path="apellidoMaternoAfiliado" id="apellidoMaternoAfiliado"/>
		<form:hidden path="sexoAfiliado" id="sexoAfiliado"/>
		<form:hidden path="fechaNacimientoAfiliado" id="fechaNacimientoAfiliado"/>
		<form:hidden path="estadoAfiliado" id="estadoAfiliado"/>
		<form:hidden path="idUsuario" id="idUsuario"/>
		<form:hidden path="rfcUsuario" id="rfcUsuario"/>
		<form:hidden path="nombreUsuario" id="nombreUsuario"/>
		<form:hidden path="apellidoPaternoUsuario" id="apellidoPaternoUsuario"/>
		<form:hidden path="apellidoMaternoUsuario" id="apellidoMaternoUsuario"/>
		<form:hidden path="sexoUsuario" id="sexoUsuario"/>
		<form:hidden path="fechaNacimientoUsuario" id="fechaNacimientoUsuario"/>
		<form:hidden path="estadoUsuario" id="estadoUsuario"/>
		<form:hidden path="codigoAutentia" id="codigoAutentia"/>
		<form:hidden path="mensajeAutentia" id="mensajeAutentia"/>
		<form:hidden path="huellaAfiliado" id="huellaAfiliado"/>
		<form:hidden path="huellaMedico" id="huellaMedico"/>
<div id="admin_contenido2">

                    
    <div class="linea_supervisor back_paciente margen_arriba3" style="height:72px;">
    	<div class="linea_supervisor margen_arriba4 derecho">
    		<div class="texto_supervisor4 verde margen5">
	          PACIENTE
	         </div>
	         <div class="texto_supervisor4 gris margen5 pad_derecho">
	          ${tomaSignosVo.nombre}
	         </div>
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	          <div class="texto_supervisor verde margen tam_16">
	          Edad:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${tomaSignosVo.edad} años
	         </div>
	         
	         <div class="texto_supervisor verde tam_16">
	          Peso:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${tomaSignosVo.peso} kg
	         </div>
			
	        <div class="texto_supervisor verde tam_1">
	          Altura:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${tomaSignosVo.altura} cm
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_15">
	          T.A.:
	         </div>
	         
	         <div class="texto_supervisor gris margen5 tam_6">
	          ${tomaSignosVo.tensionArterial}
	         </div>
	         
	         <div class="texto_supervisor verde tam_3">
	          Última Somatometría:
	         </div>
	         
	         <div class="texto_supervisor gris tam_4">
	          ${tomaSignosVo.fechaUltimaSomatometria}
	         </div>
		</div>
    </div>  
 
  <div class="admin_contenidoFrame">   
  
  <div class="linea_corta2">
   <div class="pleca_menu"></div>
   		
	<div class="linea_supervisor margen">
   		<div class="texto_supervisor2 verde">
	         RESUMEN DE LA ATENCIÓN
	    </div>
    </div>
    
			<c:if test="${not empty atencionTerminarForm.diagnosticoVos || not empty atencionTerminarForm.consultorioVos}">    
	    		<div class="linea_supervisor margen">
						<div class="linea_90 margen5 margen_arriba4 texto_arriba borde2 desplazar tam_14e">
		    					<c:forEach items="${atencionTerminarForm.diagnosticoVos}" var="diagnosticoVo">
									<div class="titulo_supervisor gris tam_12 margen2">Diagnóstico
			    					</div>		    					
			    					<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" value="${diagnosticoVo.codigo}" />
									<input class="campo_supervisor3 tam_20" placeholder="Observaciones" value="${diagnosticoVo.diagnostico}" />
		    					</c:forEach>
		    					
		    					<c:forEach items="${atencionTerminarForm.consultorioVos}" var="consultorioVos">
									<div class="titulo_supervisor gris tam_12 margen2">Diagnóstico
			    					</div>		    							    					
			    					<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" value="${consultorioVos.codigo}" />
									<input class="campo_supervisor3 tam_20" placeholder="Observaciones" value="${consultorioVos.label}" />
		    					</c:forEach>		    						    						
				   		 </div> 
				   		 <div class="linea_6 margen5 margen_arriba4 texto_arriba tam_14e pad_arriba">
				   		 	<input class="btn_solo btn_imprimir" onclick="location.href='${pageContext.request.contextPath}/certificados/imprimirMediaccessAtencionConsultorio?idAtencion=${atencionTerminarForm.idAtencion}'" /> 
				   		</div> 	  
	    		</div> 
    		</c:if>
    		
    		<c:if test="${not empty atencionTerminarForm.recetasVos}">
	    		<div class="linea_supervisor margen">
						<div class="linea_90 margen5 margen_arriba4 texto_arriba borde2 desplazar tam_14e">
								<c:forEach items="${atencionTerminarForm.recetasVos}" var="recetasVo">
									<div class="titulo_supervisor gris tam_12 margen2">Medicamentos
			    					</div>	
									<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" value="${recetasVo.idMedicamento}" />
									<input class="campo_supervisor3 tam_2" placeholder="Descripción" value="${recetasVo.medicamento}" />
									<input class="campo_supervisor3 tam_2" placeholder="Indicaciones" value="${recetasVo.unidad} ${recetasVo.unidadDescripcion} cada ${recetasVo.cada} ${recetasVo.cadaDescripcion} durante ${recetasVo.durante} ${recetasVo.duranteDescripcion}"/>
								</c:forEach>							
				   		 </div>
				   		 <div class="linea_6 margen5 margen_arriba4 texto_arriba tam_14e pad_arriba">
				   		 	<input class="btn_solo btn_imprimir" onclick="location.href='${pageContext.request.contextPath}/certificados/imprimirMediaccessRecetaMedica?idAtencion=${atencionTerminarForm.idAtencion}'" /> 
				   		</div> 	   		     
	    		</div>
    		</c:if>
    		
    		<c:if test="${not empty atencionTerminarForm.laboratorioVos}">
	    		<div class="linea_supervisor margen">
	    				<div class="linea_90 margen5 margen_arriba4 borde2 desplazar tam_14e">
		    					<c:forEach items="${atencionTerminarForm.laboratorioVos}" var="laboratorioVo">
									<div class="titulo_supervisor gris tam_12 margen2">Exám. Clínicos
			    					</div>		    					
			    					<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" value="${laboratorioVo.codigo}" />
									<input class="campo_supervisor3 tam_20" placeholder="Observaciones" value="${laboratorioVo.label}" />
		    					</c:forEach>
				   		 </div>
				   		 <div class="linea_6 margen5 margen_arriba4 texto_arriba tam_14e pad_arriba">
				   		 	<input class="btn_solo btn_imprimir" onclick="location.href='${pageContext.request.contextPath}/certificados/imprimirMediaccessOrdenLaboratorio?idAtencion=${atencionTerminarForm.idAtencion}'" /> 
				   		</div> 	    	
				</div>
			</c:if>
			
			<c:if test="${not empty atencionTerminarForm.gabineteVos}">
	    		<div class="linea_supervisor margen">
	    				<div class="linea_90 margen5 margen_arriba4 borde2 desplazar tam_14e">
		    					<c:forEach items="${atencionTerminarForm.gabineteVos}" var="gabineteVo">
									<div class="titulo_supervisor gris tam_12 margen2">Est. de gabinete
			    					</div>			    					
			    					<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" value="${gabineteVo.codigo}" />
									<input class="campo_supervisor3 tam_20" placeholder="Observaciones" value="${gabineteVo.label}" />									
		    					</c:forEach>
				   		 </div>
				   		 <div class="linea_6 margen5 margen_arriba4 texto_arriba tam_14e pad_arriba">
				   		 	<input class="btn_solo btn_imprimir" onclick="location.href='${pageContext.request.contextPath}/certificados/imprimirMediaccessOrdenGabinete?idAtencion=${atencionTerminarForm.idAtencion}'" /> 
				   		</div> 	    	
				</div>
			</c:if>	
			
			<c:if test="${not empty atencionTerminarForm.otrosVos}">
	    		<div class="linea_supervisor margen">
	    				<div class="linea_90 margen5 margen_arriba4 borde2 desplazar tam_14e">
		    					<c:forEach items="${atencionTerminarForm.otrosVos}" var="otrosVo">
									<div class="titulo_supervisor gris tam_12 margen2">Otros estudios
			    					</div>			    					
			    					<input class="campo_supervisor3 tam_6 margen2" placeholder="Código" value="${otrosVo.codigo}" />
									<input class="campo_supervisor3 tam_20" placeholder="Observaciones" value="${otrosVo.label}" />									
		    					</c:forEach>
				   		 </div>
				   		 <div class="linea_6 margen5 margen_arriba4 texto_arriba tam_14e pad_arriba">
				   		 	<input class="btn_solo btn_imprimir" onclick="location.href='${pageContext.request.contextPath}/certificados/imprimirMediaccessOtrosApoyos?idAtencion=${atencionTerminarForm.idAtencion}'" /> 
				   		</div> 	    	
				</div>
			</c:if>
			
			<c:if test="${atencionTerminarForm.llenadoLicenciaMedica == true}">
				<div class="linea_supervisor margen">
	    				<div class="linea_90 margen5 margen_arriba4 borde2 tam_14f">
								<div class="titulo_supervisor gris tam_12 margen2">Licencia médica
		    					</div>	
								<div class="texto_chico3 verde margen5">Motivo
									<input class="campo_supervisor3 tam_4" placeholder="Motivo" value="${atencionTerminarForm.licenciaMedicaVo.motivo}" />
								</div>
								<div class="texto_chico3 verde">Carácter
									<input class="campo_supervisor3 tam_4" placeholder="Carácter" value="${atencionTerminarForm.licenciaMedicaVo.caracter}" />
								</div>
								
								<div class="texto_chico3 verde">Desde
									<input class="campo_supervisor3 tam_8" placeholder="fecha" value="${atencionTerminarForm.licenciaMedicaVo.fechaDesde}" />
								</div>
								
								<div class="texto_chico3 verde">Hasta
									<input class="campo_supervisor3 tam_8" placeholder="fecha" value="${atencionTerminarForm.licenciaMedicaVo.fechaHasta}" />
								</div>
								
								<c:if test="${atencionTerminarForm.autorizacionAfiliado != 2}">
									<input class="btn_solo btn_imprimir" onclick="location.href='#';" />
								</c:if>
				   		 </div>	    	
				</div>			
			</c:if>

			
    		<div class="linea_supervisor margen">
    				<c:if test="${atencionTerminarForm.llenadoReferencia == true}">	 
	    				<div class="contenedor_medio margen5 margen_arriba4 borde2 tam_14f">
								<div class="titulo_supervisor gris tam_17 margen2">Referencia a:
		    					</div>	
								<input class="campo_supervisor3 tam_5" placeholder="Especialidad" value="${atencionTerminarForm.especialidad}" />			
								<input class="btn_solo btn_imprimir margen5" onclick="location.href='${pageContext.request.contextPath}/certificados/imprimirMediaccessSolicitudReferencia?idAtencion=${atencionTerminarForm.idAtencion}'" />
						</div>
					</c:if>
					<c:if test="${atencionTerminarForm.llenadoCuidadosMaternales == true}">
						<div class="linea_mini3 margen5 borde2 tam_14f">		
								<div class="texto_chico3 verde margen14 link2">Cuidados maternales
								</div>
								<input class="campo_transparente tam_0" />
								<input class="btn_solo btn_imprimir" onclick="location.href='#';" />
						</div>
					</c:if>
					<c:if test="${atencionTerminarForm.llenadoConstanciaSalud == true}">		
						<div class="linea_mini3 margen5 borde2 tam_14f">		
								<div class="texto_chico3 verde margen14 link2">Constancia de salud
								</div>
								<input class="campo_transparente tam_0" />
								<input class="btn_solo btn_imprimir" onclick="location.href='#';" />
				   		 </div>	
			   		 </c:if>			   		    	
			</div>								
	
     <div class="linea_supervisor margen">
            <div class="btn btn_imprimir_todo" onclick="location.href='${pageContext.request.contextPath}/certificados/imprimirMediaccessTodo?idAtencion=${atencionTerminarForm.idAtencion}'"></div>
            <div class="btn btn_cerrar_atn" onclick="cerrar()"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>
<script type="text/javascript">
	function cerrar(){
		window.location.href = '/sab/medico/atencionPacientes';	
	}
	
	
</script>

</body>
</html>