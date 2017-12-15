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
<form:form action="guardarCicloVital" method='POST' commandName="cicloVitalForm">
<div id="admin_contenido2">

    <form:hidden path="idHistoriaClinica"/>
    <form:hidden path="idCicloVital"/>
        <form:hidden path="afiliadoId"/>
                    
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
   				<li><a href="javascript:antecedentesFamiliares()"><img src=""class="icono_pestania icono_antefamiliares"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania">ANTECEDENTES FAMILIARES</li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:personalesNoPatologicos()"><img src=""class="icono_pestania icono_nopat"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania">PERSONALES NO PATOLÓGICOS</li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:antecedentesPersonales()"><img src=""class="icono_pestania icono_antepersonales"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania" >ANTECEDENTES PERSONALES</li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:ginecoObstetricos()"><img src=""class="icono_pestania icono_gineco"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania">GINECO-OBSTÉTRICOS</li>
   					</ul>
   				</li>
   				
   				<li><a href="javascript:exploracionFisica()"><img src=""class="icono_pestania icono_exfisica"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania">EXPLORACIÓN FÍSICA</li>
   					</ul>
   				</li>
   				
   				<li class="pactiva"><a href="#"><img src=""class="icono_pestania icono_tipofam"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text3 texto_pestania">FASE CICLO VITAL / TIPO DE FAMILIA</li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src=""class="icono_pestania icono_familiograma"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania">FAMILIOGRAMA</li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src=""class="icono_pestania icono_expediente"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania">EXPEDIENTE DIGITAL</li>
   					</ul>
   				</li>
   			</ul>
   		</div>
   		
	<div class="admin_contenidoFrame2">
				 <div class="contenedor_medio margen margen_arriba texto_arriba">
						<div class="titulo_HC fondo_v">
								<div class="texto_supervisor5 blanco fondo_v tam_9">FASE DEL CICLO VITAL</div>
						</div>
							
					   <div class="texto_supervisor5 gris tam_4 margen8 interlineado2">Matrimonio</div>
					   <form:checkbox path="matrimonio"/>
					   
					   <div class="texto_supervisor5 gris tam_4 margen8 interlineado2">Expansión</div>
					   <form:checkbox path="expansion"/>
					   
					   <div class="texto_supervisor5 gris tam_4 margen8 interlineado2">Dispersión</div>
					   <form:checkbox path="dispersion"/>
					   
					   <div class="texto_supervisor5 gris tam_4 margen8 interlineado2">Independencia</div>
					   <form:checkbox path="independencia"/>
					   
					   <div class="texto_supervisor5 gris tam_4 margen8 interlineado2">Retiro y Muerte</div>
					   <form:checkbox path="retiroymuerte"/>
				   
				</div>
				
				<div class="contenedor_medio margen5 margen_arriba texto_arriba">
					<div class="titulo_HC fondo_v">
							<div class="texto_supervisor5 blanco fondo_v tam_7">TIPO DE FAMILIA</div>
					</div>
					
					<div class="contenedor_medio margen8 margen_arriba2 texto_arriba">
						<ul class="texto_supervisor5 gris margen7 interlineado3">
							<form:radiobuttons path="idTipoFamilia" items="${tipoDeFamilia}" itemLabel="descripcion" itemValue="idTipoFamilia" cssClass="margen_der2" element="li" />
						</ul>
			   		</div>
			   		
				</div>
		</div>

     <div class="linea_supervisor margen">
     		<div class="btn btn_guardar" onclick="guardarCicloVital()"></div>
            <div class="btn btn_cerrar" onclick="cerrar()"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>
<script>
	$(document).ready(function() {
	
	$.ajaxSetup({ cache: false });
	});
	
	function cerrar(){
		var ctx = "${pageContext.request.contextPath}";
		window.location.href = ctx+"/menu";
	}
	
	function antecedentesFamiliares(){
		parent.$("#medicoAtencionPacientesForm").attr("action", "antecedentesFamiliares");
		parent.$("#medicoAtencionPacientesForm").submit();		
	}	
	
	function antecedentesPersonales(){
		parent.$("#medicoAtencionPacientesForm").attr("action", "antecedentesPersonales");
		parent.$("#medicoAtencionPacientesForm").submit();		
	}
	
	function personalesNoPatologicos(){
		parent.$("#medicoAtencionPacientesForm").attr("action", "personalesNoPatologicos");
		parent.$("#medicoAtencionPacientesForm").submit();		
	}
	
	function ginecoObstetricos(){
		parent.$("#medicoAtencionPacientesForm").attr("action", "ginecoObstentricos");
		parent.$("#medicoAtencionPacientesForm").submit();		
	}
	
	function exploracionFisica(){
		parent.$("#medicoAtencionPacientesForm").attr("action", "exploracionFisica");
		parent.$("#medicoAtencionPacientesForm").submit();		
	}
	
	function cicloVital(){
		parent.$("#medicoAtencionPacientesForm").attr("action", "cicloVital");
		parent.$("#medicoAtencionPacientesForm").submit();		
	}
	
	function guardarCicloVital(){
				var cicloVitalForm = $("#cicloVitalForm").serialize();
			    $.getJSON("guardarCicloVital", cicloVitalForm ,function(response){
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