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
<form:form action="guardarNotaMedica" method='POST' commandName="notaMedicaForm">
<div id="admin_contenido2">
	
	<form:hidden path="atencionMedicaId"/>
	<form:hidden path="notaMedicaId"/>
	<form:hidden path="diagnosticoPrincipalId" />
	<input type="hidden" id="idDiagnostico">
	<input type="hidden" id="codigo">
                    
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
   				<li class="pactiva">
   					<a href="javascript:atencionMedica();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/nota_medica.png" class="icono_pestania"/></a>	
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">NOTA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li>
					<a href="javascript:receta();" id="ligaReceta"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/receta.png" class="icono_pestania"/></a>	
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">RECETA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li>
					<a href="javascript:estudiosLaboratorio();" id="ligaEstudiosLaboratorio"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/laboratorio.png" class="icono_pestania"/></a>			
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE LABORATORIO</a></li>
   					</ul>
   				</li>
   				
   				<li>
					<a href="javascript:estudiosGabinete();" id="ligaEstudiosGabinete"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/gabinete.png" class="icono_pestania"/></a>	
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE GABINETE</a></li>
   					</ul>
   				</li>
   				
   				<li>
					<a href="javascript:estudiosOtros();" id="ligaEstudiosOtros"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/otros_estudios.png" class="icono_pestania"/></a>	
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">OTROS ESTUDIOS O SERVICIOS</a></li>
   					</ul>
   				</li>
   				
   				<li>
					<a href="javascript:licenciaMedica();" id="ligaLicenciaMedica"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/licencia_medica.png" class="icono_pestania"/></a>	
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">LICENCIA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li>
					<a href="javascript:solicitudReferencia();" id="ligaSolicitudReferencia"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/solicitud_referencia.png" class="icono_pestania"/></a>	
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">SOLICITUD DE REFERENCIA</a></li>
   					</ul>
   				</li>
   				
   				<li>
					<a href="javascript:certificados();" id="ligaCertificados"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/certificados.png" class="icono_pestania"/></a>	
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">CERTIFICADOS</a></li>
   					</ul>
   				</li>
   				
   				<li>
					<a href="javascript:terminar();" id="ligaTerminar"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/terminar.png" class="icono_pestania"/></a>	
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">TERMINAR ATENCIÓN</a></li>
   					</ul>
   				</li>
   			</ul>
   		</div>
  
	<div class="linea_supervisor margen">
   		<form:textarea path="motivoConsulta" cssClass="campo_supervisor tam_23" placeholder="Motivo de la Consulta" onchange="actualizoDatos()"></form:textarea>
    </div>
    
    <div class="linea_supervisor margen">
   		<form:textarea path="exploracionFisica" cssClass="campo_supervisor tam_24" onchange="actualizoDatos()" placeholder="Exploración Física"></form:textarea>
   		<div class="texto_supervisor gris tam_6">
				<div class="btn btn_tomar_sig_vitales" onclick="signosVitales()"></div>
        </div>  
    </div>
    
    <div class="linea_supervisor margen">
   		<form:textarea cssClass="campo_supervisor tam_24" placeholder="Diagnóstico Principal" path="diagnostico"></form:textarea>

   		<div class="texto_supervisor gris tam_6">
				<div class="btn btn_otrosdiag" onclick="otrosDiagnosticos()" style="display: none;" id="botonOtrosDiagnosticos"></div>
        </div>  
    </div>
    
    	
    
    <div class="linea_supervisor margen margen_arriba3 texto_supervisor5 gris" style="display:none">
    		<div class="texto_supervisor5 gris">Tipo de Diagnóstico:</div>
    		<form:radiobuttons items="${cattipodiagnosticos}" path="idTipoDiagnostico" onchange="actualizoDatos()" itemLabel="descripcion" itemValue="id"/>
    </div>
    
	<div class="linea_supervisor margen">
   		<form:textarea path="planASeguir" cssClass="campo_supervisor tam_23" onchange="actualizoDatos()" placeholder="Prónostico - Tratamiento - Medidas Recomendadas"></form:textarea>
    </div>        

     <div class="linea_supervisor margen">
     		<div class="btn_grande btn_consultorio" onclick="estudiosConsultorio()" style="display: none;" id="botonEstudiosConsultorio"></div>
     		<div class="btn btn_guardar" onclick="guardar()"></div>
            <div class="btn btn_cerrar" onclick="cerrar()"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>

<script>

    function actualizoDatos(){
    parent.$("#actualizaDatos").val(1);
    }
    
    function cerrar(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.$("#medicoAtencionPacientesForm").attr("action", "menuMedico");
		parent.$("#medicoAtencionPacientesForm").submit();
     }else{
     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					parent.$("#medicoAtencionPacientesForm").attr("action", "menuMedico");
					parent.$("#medicoAtencionPacientesForm").submit();				
				}
     }
    }
    
    function otrosDiag(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.otrosDiagnostico();
     }else{
     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					parent.otrosDiagnostico();				
				}
     	}
    }
    
    function signosVitales(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.$("#medicoAtencionPacientesForm").attr("action", "signosVitales");
		parent.$("#medicoAtencionPacientesForm").submit();
     }else{
     	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
				if(respuesta){
					parent.$("#medicoAtencionPacientesForm").attr("action", "signosVitales");
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
    
    function estudiosConsultorio(){
        if(parent.$("#actualizaDatos").val() == 0){
       	parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosConsultorio");
   		parent.$("#medicoAtencionPacientesForm").submit();
        }else{
        	var respuesta = confirm("Ha realizado cambios sin guardar y estos se perderán, ¿Desea continuar?");
   				if(respuesta){
   					parent.$("#medicoAtencionPacientesForm").attr("action", "estudiosConsultorio");
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

	function certificados(){
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
    
	$(document).ready(function(){
		$.ajaxSetup({ cache: false });
		inicializaAutocomplete();
		$("#agregar").prop( "disabled", true );
		if($("#notaMedicaId").val() == 0){
			$("#botonOtrosDiagnosticos").hide();
			$("#botonEstudiosConsultorio").hide();
			$("#ligaReceta").hide();
			$("#ligaEstudiosLaboratorio").hide();
			$("#ligaEstudiosGabinete").hide();
			$("#ligaEstudiosOtros").hide();
			$("#ligaLicenciaMedica").hide();
			$("#ligaSolicitudReferencia").hide();
			$("#ligaCertificados").hide();
			$("#ligaTerminar").hide();
		}else{
			$("#botonOtrosDiagnosticos").show();
			$("#botonEstudiosConsultorio").show();
			$("#ligaReceta").show();
			$("#ligaEstudiosLaboratorio").show();
			$("#ligaEstudiosGabinete").show();
			$("#ligaEstudiosOtros").show();
			$("#ligaLicenciaMedica").show();
			$("#ligaSolicitudReferencia").show();
			$("#ligaCertificados").show();
			$("#ligaTerminar").show();
		}
		
	});
	
	function validaCampos(){
	var flag = true;
	var llenadoSignosVitales = '${tomaSignosVo.llenado}';
	if($("#motivoConsulta").val() == ""){
		flag = false;
		$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1> Favor de capturar el motivo de la consulta</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,								
			 				autoSize: false,
			 				width: 480,
			 				height: 200
						});
		}else if($("#exploracionFisica").val() == ""){
			flag = false;
			$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1> Favor de capturar exploración física</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,								
			 				autoSize: false,
			 				width: 480,
			 				height: 200
						});
		}else if($("#diagnosticoPrincipalId").val() == ""){
		flag = false;
		$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1> Favor de capturar el diagnóstico</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,								
			 				autoSize: false,
			 				width: 480,
			 				height: 200
						});
		}else if($("#planASeguir").val() == ""){
		flag = false;
		$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1> Favor de capturar el plan a seguir</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,								
			 				autoSize: false,
			 				width: 480,
			 				height: 200
						});
		}else if (llenadoSignosVitales==0) {
			flag = false;
			$.fancybox.open({
								content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1> Favor de llenar Signos Vitales</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
								openEffect : 'elastic',
								openSpeed  : 150,
								closeEffect : 'elastic',
								closeSpeed  : 150,								
				 				autoSize: false,
				 				width: 480,
				 				height: 200
							});			
		}
	
	return flag;
	}
	
	function guardar(){
	var flag = validaCampos();
 	if(flag == true){
				var notaMedicaForm = $("#notaMedicaForm").serialize();
			    $.getJSON("guardarNotaMedica", notaMedicaForm ,function(response){
			    	if(response.error != undefined && response.error != ""){
			    	
						$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>"+response.error+"</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,								
			 				autoSize: false,
			 				width: 480,
			 				height: 200
						});				    		
			    	}else{
			    		parent.$("#actualizaDatos").val(0);
						$("#botonOtrosDiagnosticos").show();
						$("#botonEstudiosConsultorio").show();
						$("#ligaReceta").show();
						$("#ligaEstudiosLaboratorio").show();
						$("#ligaEstudiosGabinete").show();
						$("#ligaEstudiosOtros").show();
						$("#ligaLicenciaMedica").show();
						$("#ligaSolicitudReferencia").show();
						$("#ligaCertificados").show();
						$("#ligaTerminar").show();			    		
		    			$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1> Nota médica guardada exitosamente</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
							openEffect : 'elastic',
							openSpeed  : 150,
							closeEffect : 'elastic',
							closeSpeed  : 150,								
			 				autoSize: false,
			 				width: 480,
			 				height: 200
						});	
			    	
			    	}
			    });	
		
	
		}
	}
	
	function inicializaAutocomplete(){
			$("#diagnostico").autocomplete({
			source: "diagnosticoNotaMedica",
	 	   	minLength: 3,
	        select: function( event, ui ){
	        	if (ui.item.idDiagnostico!=-1) {
	       	    	$("#diagnostico").val(ui.item.diagnostico);
	       	    	$("#diagnosticoPrincipalId").val(ui.item.idDiagnostico);
	       	    	$("#codigo").val(ui.item.codigo);
	       	    	$("#idTipoDiagnostico").val(ui.item.idTipoDiagnostico);
	       	    	$("#agregar").prop( "disabled", false );
	       	    	actualizoDatos();
				}else{
	       	    	$("#diagnostico").val("");
	       	    	$("#idDiagnostico").val("");
	       	    	$("#codigo").val("");
				}
	            return false;
			},
			focus: function (event, ui) {
				if (ui.item.idDiagnostico!=-1) {
					$("#diagnostico").val(ui.item.diagnostico);
				}else{
	       	    	$("#diagnostico").val("");
	       	    	$("#diagnosticoPrincipalId").val("");
	       	    	$("#codigo").val("");
				}
				return false;
	        }
	
		}).autocomplete("instance")._renderItem = function( ul, item ) {
		      return $("<li>")
		        .append(item.diagnostico)
		        .appendTo(ul);
		};  		
	}
</script>
</body>
</html>