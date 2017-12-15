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
<form:form>
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
   				<li><a href="javascript:notaMedica();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/nota_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">NOTA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:recetaMedica();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/receta.png" class="icono_pestania"/></a>
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
   				
   				<li><a href="javascript:solicitudReferencia();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/solicitud_referencia.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">SOLICITUD DE REFERENCIA</a></li>
   					</ul>
   				</li>
   				
   				<li class="pactiva"><a href="javascript:certificados();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/certificados.png" class="icono_pestania"/></a>
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
   		<div class="texto_supervisor2 verde">
	         CERTIFICADOS
	    </div>
    </div>
    
    <div class="linea_supervisor margen margen_arriba2">
           <div class="pestania_certificado btn_certificado1_clic" onclick="javascript:constanciaAsistencia();"></div>
           <div class="pestania_certificado btn_certificado2" onclick="javascript:cuidadosMaternales();"></div>
           <div class="pestania_certificado btn_certificado3" onclick="javascript:constanciaSalud();"></div>
    </div>    	
	
	<div class="linea_90 margen borde1 alto_tabla4">
		<div class="linea_supervisor margen_arriba3">
			<input class="campo_supervisor tam_18 centrado margen5" placeholder="Servicio al que asistió" />
		    <div class="texto_supervisor5 gris">Entre las</div>
	    		<input class="campo_supervisor tam_8 centrado" placeholder="Horas" />
	    	<div class="texto_supervisor5 gris">y las</div>	
	    		<input class="campo_supervisor tam_8 centrado" placeholder="Horas" />
    	</div>			
    </div>
	
     <div class="linea_supervisor margen margen_arriba3">
            <div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>
<script>

	function notaMedica(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.$("#medicoAtencionPacientesForm").attr("action", "notaMedica");
		parent.$("#medicoAtencionPacientesForm").submit();
     }else{
     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					parent.$("#medicoAtencionPacientesForm").attr("action", "notaMedica");
					parent.$("#medicoAtencionPacientesForm").submit();				
				}
     	}
    }

	function recetaMedica(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.$("#medicoAtencionPacientesForm").attr("action", "recetaMedica");
		parent.$("#medicoAtencionPacientesForm").submit();
     }else{
     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					parent.$("#medicoAtencionPacientesForm").attr("action", "recetaMedica");
					parent.$("#medicoAtencionPacientesForm").submit();				
				}
     	}
    }
    
    function estudiosLaboratorio(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosLaboratorio");
		parent.$("#medicoAtencionPacientesForm").submit();
     }else{
     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosLaboratorio");
					parent.$("#medicoAtencionPacientesForm").submit();				
				}
     	}
    }
    
    function estudiosGabinete(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosGabinete");
		parent.$("#medicoAtencionPacientesForm").submit();
     }else{
     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosGabinete");
					parent.$("#medicoAtencionPacientesForm").submit();				
				}
     	}
    }
    
    function estudiosOtros(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosOtros");
		parent.$("#medicoAtencionPacientesForm").submit();
     }else{
     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosOtros");
					parent.$("#medicoAtencionPacientesForm").submit();				
				}
     	}
    }
    
    function licenciaMedica(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.$("#medicoAtencionPacientesForm").attr("action", "licenciaMedica");
		parent.$("#medicoAtencionPacientesForm").submit();
     }else{
     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					parent.$("#medicoAtencionPacientesForm").attr("action", "licenciaMedica");
					parent.$("#medicoAtencionPacientesForm").submit();				
				}
     	}
    }

	function constanciaAsistencia(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.$("#medicoAtencionPacientesForm").attr("action", "constanciaAsistencia");
		parent.$("#medicoAtencionPacientesForm").submit();
     }else{
     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					parent.$("#medicoAtencionPacientesForm").attr("action", "constanciaAsistencia");
					parent.$("#medicoAtencionPacientesForm").submit();				
				}
     	}
    }
    
    function cuidadosMaternales(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.$("#medicoAtencionPacientesForm").attr("action", "cuidadosMaternales");
		parent.$("#medicoAtencionPacientesForm").submit();
     }else{
     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					parent.$("#medicoAtencionPacientesForm").attr("action", "cuidadosMaternales");
					parent.$("#medicoAtencionPacientesForm").submit();				
				}
     	}
    }
    
    function constanciaSalud(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.$("#medicoAtencionPacientesForm").attr("action", "constanciaSalud");
		parent.$("#medicoAtencionPacientesForm").submit();
     }else{
     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					parent.$("#medicoAtencionPacientesForm").attr("action", "constanciaSalud");
					parent.$("#medicoAtencionPacientesForm").submit();				
				}
     	}
    }
</script>

</body>
</html>