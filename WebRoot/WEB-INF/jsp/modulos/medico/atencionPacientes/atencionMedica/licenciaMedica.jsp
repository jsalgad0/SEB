<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.maskedinput.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_recepcion.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">


<html>
<body style="overflow:hidden;">
<form:form action="guardarLicenciaMedica" method='POST' commandName="medicoLicenciaMedicaForm">
<div id="admin_contenido2">
	<form:hidden path="atencionMedicaId"/>
	<form:hidden path="licenciaMedicaId"/>
                    
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
   				
   				<li class="pactiva"><a href="javascript:licenciaMedica();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/licencia_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">LICENCIA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:solicitudReferencia();"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/solicitud_referencia.png" class="icono_pestania"/></a>
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
   		<div class="texto_supervisor2 verde">
	          LICENCIA MÉDICA
	    </div>
   		<form:input class="campo_supervisor tam_3 margen2" path="folio" placeholder="Folio" />
   		<form:input class="campo_supervisor tam_3" path="fechaEmision" placeholder="Fecha de emisión" />
    </div>
  
	<div class="linea_supervisor margen">
   		<form:input class="campo_supervisor tam_26" path="cie10" placeholder="Diagnóstico CIE10" />
   		<form:input class="campo_supervisor tam_20" path="diagnostico" placeholder="Glosa Diagnóstico" />
    </div>
    
    <div class="linea_supervisor margen">
   		<form:input class="campo_supervisor tam_3" path="dias" placeholder="No. de días*" />
   		<form:input class="campo_supervisor tam_3" path="fechaInicio" style="margin-right:0px !important;" placeholder="Fecha Inicio"/><!--
          --><input class="btn_solo btn_calendario" id="datepicker" onclick="location.href='#';" />
        <form:input class="campo_supervisor tam_3 margen14" path="fechaFin"  style="margin-right:0px !important;" placeholder="Fecha Término" readonly="true"/><!--
          -->  
   		<form:input class="campo_supervisor tam_11 margen5" path="diasLetra" placeholder="Días otorgados (con letra)" readonly="true" />
    </div>
    
    <div class="linea_supervisor margen tam_27 margen_arriba2 texto_supervisor5 gris">
   		<div class="titulo_supervisor gris">Motivos de la Licencia:*</div>
   		<form:radiobuttons path="idLicMedicaMotivo" items="${motivos}" itemLabel="descripcion" itemValue="idLicMedicaMotivo" cssClass="margen2" />
    </div>
    
    <div class="linea_supervisor margen margen_arriba2 texto_supervisor5 gris">
    	<div class="titulo_supervisor gris">Carácter de la Licencia:*</div>
    	<form:radiobuttons path="idLicMedicaCaracter" items="${caracteres}" itemLabel="descripcion" itemValue="idLicMedicaCaracter" cssClass="margen2" />	
    </div>
    
	<div class="linea_supervisor margen margen_arriba2 texto_supervisor5 gris">
    	<div class="titulo_supervisor gris">Tipo de Servicio otorgado:*</div>
    	<form:radiobuttons path="idLicMedicaTipoServicio" items="${tiposServicio}" itemLabel="descripcion" itemValue="idLicMedicaTipoServicio" cssClass="margen2" />
    </div>       

     <div class="linea_supervisor margen margen_arriba">
     		<div class="btn_grande btn_licant"></div>
     		<div class="btn btn_guardar" onclick="guardarLicenciaMedica()"></div>
            <div class="btn btn_cerrar" onclick="cerrar()"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>
<script>
	$(document).ready(function() {
	
		$.ajaxSetup({ cache: false });
		
		$("#datepicker").click(function() {
			  $("#fechaInicio").datepicker("show");
		});
		
		$("#dias").numeric({
			allowLatin : false,
			disallow: '.´çÇ¿¨¡·'
		});
	});
	
	$(function() {
		$("#fechaInicio").datepicker({
			changeMonth: true,
			changeYear: true,
			minDate: 0,
			yearRange: "2016:2099",
			onSelect: function(date) {
				var medicoLicenciaMedicaForm = $("#medicoLicenciaMedicaForm").serialize();
			    $.getJSON("calcularFechaFinal", medicoLicenciaMedicaForm ,function(response){
			    	 $("#fechaFin").val(response.fechaFin);
			    	 $("#diasLetra").val(response.diasLetra);
			    });
	        },
		});
	});

	function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/menu";
	}
	
	function notaMedica(){
     if(parent.$("#actualizaDatos").val() == 0){
    	parent.$("#medicoAtencionPacientesForm").attr("action", "notamedica");
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
    
    function EstudiosOtros(){
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
	
	function guardarLicenciaMedica(){
				var medicoLicenciaMedicaForm = $("#medicoLicenciaMedicaForm").serialize();
			    $.getJSON("guardarLicenciaMedica", medicoLicenciaMedicaForm ,function(response){
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
		    			$.fancybox.open({
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1> Datos guardados exitosamente</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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
	
</script>

</body>
</html>