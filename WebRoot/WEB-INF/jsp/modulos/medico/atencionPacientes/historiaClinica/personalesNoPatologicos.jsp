<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">
<<style>
ul.verticalRadios {
  list-style-type: none;
  /* or whatever */
}
</style>

<html>
<body >

<div id="admin_contenido2">
                    
    <div class="linea_supervisor back_paciente margen_arriba3" style="height:72px;">
    	<div class="linea_supervisor margen_arriba4 derecho">
    		<div class="texto_supervisor4 verde margen5">
	          PACIENTE
	         </div>
	         <div class="texto_supervisor4 gris margen5 pad_derecho">
	          ${infoSignos.nombre}
	         </div>
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	         <div class="texto_supervisor verde margen2 tam_16">
	          Edad:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${infoSignos.edad} años
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_16">
	          Peso:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${infoSignos.peso} kg
	         </div>
			
	        <div class="texto_supervisor verde margen5 tam_1">
	          Altura:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${infoSignos.altura} cm
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_15">
	          T.A.:
	         </div>
	         
	         <div class="texto_supervisor gris margen5 tam_6">
	          ${infoSignos.tensionArterial}
	         </div>
	         
	         <div class="texto_supervisor verde tam_3">
	          Última Somatometría:
	         </div>
	         
	         <div class="texto_supervisor gris tam_4">
	          ${infoSignos.fechaUltimaSomatometria}
	         </div>
		</div>
    </div>  
 
  <div class="admin_contenidoFrame2">   
  
  <div class="linea_corta2">
   <div class="pleca_menu"></div><!--     
     --><div class="pestanias_contenedor">
   			<ul id="pmenu">
   				<li><a href="javascript:antecedentesFamiliares()"><img src=""class="icono_pestania icono_antefamiliares"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania">ANTECEDENTES FAMILIARES</li>
   					</ul>
   				</li>
   				
   				<li class="pactiva"><a href="#"><img src=""class="icono_pestania icono_nopat"/></a>
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
   				
   				<li><a href="javascript:cicloVital()"><img src=""class="icono_pestania icono_tipofam"/></a>
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
   
   		
   <form:form name='historiaClinicaPersonalesNoPatologicosForm' action="saveHistoriaClinicaPersonalesNoPatologicos" method='POST'
			commandName="historiaClinicaPersonalesNoPatologicosForm" id="historiaClinicaPersonalesNoPatologicosForm">
			<form:hidden path="idPersonalNoPatologicos"/>
			<form:hidden path="idDemografico"/>
			<form:hidden path="idAfiliado"/>
			<form:hidden path="error"/>
			
	<div class="admin_contenidoFrame2">	
		 <div class="linea_corta margen_arriba2 margen">	
			<form:input path="fechaPersonalesPatologicos" cssClass="campo_supervisor tam_4" cssStyle="margin-right:0px !important;" placeholder="Fecha" readonly="true"/><!--
	          --><input class="btn_solo btn_calendario" id="datepicker" />
		 </div>
		 
		 <div class="linea_corta margen_arriba2 margen">
	   		<div class="texto_supervisor5 gris">Tipo de sangre:</div>
	   		<div class="texto_supervisor5 gris tam_7">
	   			<form:radiobuttons path="idTipoSangre" items="${tiposSangre}" itemLabel="descripcion" itemValue="idSangre" cssClass="margen" />
	    	</div>
	    	
	    	<form:radiobutton path="idPositivoNegativo" cssClass="margen2" value="1"/><div class="texto_supervisor5 gris">Positivo</div>
	    	<form:radiobutton path="idPositivoNegativo" cssClass="margen5" value="0"/><div class="texto_supervisor5 gris">Negativo</div>
	    	
	    </div> 
	       
		<div class="linea_mini2 margen margen_arriba2 texto_arriba">
			<div class="titulo_HC fondo_v">
				<div class="texto_supervisor5 blanco fondo_v tam_4">Escolaridad</div>
			</div>
			<div class="texto_supervisor5 gris tam_4 margen2 interlineado2">
				<ul class="verticalRadios">
			   		<form:radiobuttons path="idEscolaridad" items="${escolaridades}" itemLabel="descripcion" itemValue="idEscolaridad" element="li" />
			   	</ul>
			</div>
		</div>
		
		<div class="linea_mini2 margen margen_arriba2 texto_arriba">
			<div class="titulo_HC fondo_v">
				<div class="texto_supervisor5 blanco fondo_v tam_12">Estado Civil</div>
			</div>
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado2">
				<ul class="verticalRadios">
			   		<form:radiobuttons path="idEstadoCivil" items="${estadosCiviles}" itemLabel="descripcion" itemValue="idEstadoCivil" element="li" />
			   	</ul>
			</div>    
		</div> 
		
		<div class="linea_mini2 margen margen_arriba2 texto_arriba">
			<div class="titulo_HC fondo_v">
				<div class="texto_supervisor5 blanco fondo_v tam_12">Alimentación</div>
			</div>
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">
				<ul class="verticalRadios">
			   		<form:radiobuttons path="idAlimentacion" items="${alimentaciones}" itemLabel="descripcion" itemValue="idAlimentacion" element="li" />
			   	</ul>
			</div>
		</div> 
		
		<div class="linea_mini2 margen margen_arriba2 texto_arriba">
			<div class="titulo_HC fondo_v">
				<div class="texto_supervisor5 blanco tam_12">Higiene Personal</div>
			</div>
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">
				<ul class="verticalRadios">
			   		<form:radiobuttons path="idHigienePersonal" items="${higienePersonales}" itemLabel="descripcion" itemValue="idHigiene" element="li" />
			   	</ul>			
			</div>   
		</div> 	       
	</div>
	
    <div class="linea_supervisor margen margen_arriba3">
     		<div class="btn btn_guardar" onclick="enviar();"></div>
            <div class="btn btn_cerrar" onclick="cerrar();"></div>
     </div>
     </form:form>
  </div>
    
 </div>
     
</div>


<script type="text/javascript">

	$(document).ready(function(){
	
		$.ajaxSetup({ cache: false });	
	});

	function enviar(){
		var historiaClinicaPersonalesNoPatologicosForm = $("#historiaClinicaPersonalesNoPatologicosForm").serialize();
	    $.getJSON("saveHistoriaClinicaPersonalesNoPatologicos", historiaClinicaPersonalesNoPatologicosForm ,function(response){
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
					content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1>Personales No Patologicos guardado exitosamente</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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
</script>

</body>
</html>