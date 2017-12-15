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
<form:form action="guardarAntecedentesFamiliares" method='POST' commandName="exploracionFisicaForm">
<div id="admin_contenido2">
                    <form:hidden path="afiliadoId"/>
                     <form:hidden path="idHistoriaClinica"/>
                     <form:hidden path="idExploracionFisica"/>
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
   				
   				<li class="pactiva"><a href="#"><img src=""class="icono_pestania icono_exfisica"/></a>
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
   		
   		<div class="admin_contenidoFrame2">
			  <div class="linea_mini margen12 margen_arriba texto_arriba">
					<input class="campo_supervisor2 tam_12" placeholder="Peso*" value="${tomaSignosVo.peso}" readonly="readonly"/><!--
	    			--><input class="campo_1 tam_1" placeholder="Kg." disabled />
	    			 
	    			<input class="campo_supervisor2 tam_12" placeholder="Altura*"  value="${tomaSignosVo.altura}" readonly="readonly"/><!--
			    	--><input class="campo_1 tam_1" placeholder="cm." disabled />
			    	
			    	<input class="campo_supervisor2 tam_17" placeholder="T.A.*" value="${tomaSignosVo.tensionArterial}" readonly="readonly" /><!--
			    	--><input class="campo_1 tam_21" placeholder="mmHG" disabled />
			    	
			    	<input class="campo_supervisor2 tam_12" placeholder="Temperatura*" value=" ${tomaSignosVo.temperatura}" readonly="readonly" /><!--
			    	--><input class="campo_1 tam_1" placeholder="ºC" disabled />    
				</div>
		</div>
		
		<div class="linea_mini2 margen margen_arriba texto_arriba">
			<div class="titulo_HC fondo_v">
				<div class="texto_supervisor5 blanco fondo_v tam_12">Con Alteración</div>
			</div>
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado">Cabeza</div>
			   <form:checkbox path="cabeza" />
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado">Cuello</div>
			    <form:checkbox path="cuello" />
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado">Torax</div>
			    <form:checkbox path="torax" />
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado">Abdomen</div>
			    <form:checkbox path="abdomen" />
			<div class="texto_supervisor5 gris tam_12 margen2">Sistema Músculo Esquelético</div>
			    	<form:checkbox path="sistemaMusculo" />      
		</div>
		
		<div class="linea_mini2 margen margen_arriba texto_arriba">
			<div class="titulo_HC fondo_v">
				<div class="texto_supervisor5 blanco fondo_v tam_12">Con Alteración</div>
			</div>
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado">Extremidades</div>
			   <form:checkbox path="extremidades" />
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado">Sistema Nervioso</div>
			   <form:checkbox path="sistemaNervioso" />
			<div class="texto_supervisor5 gris tam_12 margen2 margen_arriba2">Sistema Cardiovascular</div>
			   <form:checkbox path="sistemaCardio" /> 
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado margen_arriba5">Aparato Digestivo</div>
			    <form:checkbox path="aparatoDigestivo" />     
		</div>		 

     <div class="linea_supervisor margen margen_arriba">
     		<div class="btn btn_guardar" onclick="guardarExploracionFisica()"></div>
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
	
	function guardarExploracionFisica(){
				var exploracionFisicaForm = $("#exploracionFisicaForm").serialize();
			    $.getJSON("guardarExploracionFisica", exploracionFisicaForm ,function(response){
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
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1> Exploración física guardada exitosamente</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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