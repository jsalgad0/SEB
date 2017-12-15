<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">


<html>
<body style="overflow:hidden;">
<form:form name='solicitudReferenciaForm' action="solicitudReferenciaForm" method='POST'
			commandName="solicitudReferenciaForm" id="solicitudReferenciaForm">
			<form:hidden path="idAtencion"/>
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
   <div class="pleca_menu"></div><!--     
     --><div class="pestanias_contenedor">
   			<ul id="pmenu">
   				<li><a href="javascript:atencionMedica();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/nota_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">NOTA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:receta();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/receta.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">RECETA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:estudiosLaboratorio();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/laboratorio.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE LABORATORIO</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:estudiosGabinete();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/gabinete.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE GABINETE</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:estudiosOtros();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/otros_estudios.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">OTROS ESTUDIOS O SERVICIOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:licenciaMedica();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/licencia_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">LICENCIA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li class="pactiva"><a href="javascript:solicitudReferencia();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/solicitud_referencia.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">SOLICITUD DE REFERENCIA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:certificados();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/certificados.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">CERTIFICADOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:terminar();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/terminar.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">TERMINAR ATENCIÓN</a></li>
   					</ul>
   				</li>     				
   			</ul>
   		</div>

	<div class="linea_supervisor margen">
   		<form:textarea cssClass="campo_supervisor tam_23" placeholder="Motivo de la Referencia*" path="motivo"/>
    </div>
    
    <div class="linea_supervisor margen">
   		<form:select cssClass="selectt campo_supervisor tam_18" path="idLugarAtencion">
            	<form:option label="Unidad Médica Receptora*" value="-1" />
            	<form:options items="${lugaresAtencion}" itemLabel="descripcion" itemValue="lugarDeAtencionId"/>
    	</form:select>
    	
    	<form:select cssClass="selectt campo_supervisor tam_10 margen2" path="idEspecialidad">
    			<form:option label="Especialidad*" value="-1" />
    			<form:options items="${especialidades}" itemLabel="especialidadMedica" itemValue="especialidadMedicaId"/>
    	</form:select>
    </div>
  
	<div class="linea_supervisor margen margen_arriba2">
		<c:forEach items="${catSoliReferenciaMotivos}" var="catSoliReferenciaMotivo">
			<div class="titulo_supervisor gris tam_5">
				<form:radiobutton path="idCatSoliReferenciaMotivo" label="${catSoliReferenciaMotivo.soliReferenciaMotivo}" value="${catSoliReferenciaMotivo.soliReferenciaMotivoId}"/>
			</div>
		</c:forEach>	
    </div>
    
	<div class="linea_supervisor margen">
   		<form:textarea cssClass="campo_supervisor tam_23" placeholder="Presentación del caso:Motivo del envío, valoración, diagnóstico y terapéutica" path="presentacionCaso" />
    </div>
    
    <div class="linea_supervisor margen">
   		<form:textarea cssClass="campo_supervisor tam_23" placeholder="Resultados de Laboratorio y Gabinete" path="resultadosLaboratorio" />
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
		var solicitudReferenciaForm = $("#solicitudReferenciaForm").serialize();
		$.getJSON("saveSolicitudReferencia", solicitudReferenciaForm ,function(response){
			$.fancybox.open({
				content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario1\"></div><h1>"+response.error+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
				openEffect : 'elastic',
				openSpeed  : 150,
				closeEffect : 'elastic',
				closeSpeed  : 150,								
				autoSize: false,
				width: 480,
				height: 200
			});	
		});					
	}

	function cerrar(){
		$("#medicoAtencionPacientesForm").attr("action", "notaMedica");
		$("#medicoAtencionPacientesForm").submit();	
	}	
</script>
</html>