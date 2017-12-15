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
<form:form action="guardarAntecedentesPersonales" method='POST' commandName="antecedentesPersonalesForm">
<div id="admin_contenido2">

    <form:hidden path="idHistoriaClinica"/>
    <form:hidden path="idAntecedentesPersonales"/>
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
   				
   				<li class="pactiva"><a href="#"><img src=""class="icono_pestania icono_antepersonales"/></a>
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
	   
	 <div class="linea_corta margen margen_arriba2">
	   		<div class="texto_supervisor5 gris tam_3">Diabetes Mellitus</div>
	    	 <form:checkbox path="diabeteMellitus"/>
	    	<div class="texto_supervisor5 gris tam_3 margen7">Hipertensión Arterial</div>
	    	 <form:checkbox path="hipertensionArterial"/>
	    	<div class="texto_supervisor5 gris tam_3 margen7">Neoplasias</div>
	    	 <form:checkbox path="neoplasias"/>
    </div> 

	 <div class="linea_corta margen margen_arriba2">
	   		<div class="texto_supervisor5 gris tam_3">Cardiopatías</div>
	    	 <form:checkbox path="cardiopatias"/>
	    	<div class="texto_supervisor5 gris tam_3 margen7">Alergias</div>
	    	 <form:checkbox path="alergias"/>
	    	<div class="texto_supervisor5 gris tam_3 margen7">Obesidad</div>
	    	 <form:checkbox path="obesidad"/>     
    </div> 
    
    <div class="linea_corta margen margen_arriba2">
	   		<div class="texto_supervisor5 gris tam_3">Tuberculosis</div>
	    	 <form:checkbox path="tuberculosis"/>
	    	<div class="texto_supervisor5 gris tam_3 margen7">Tabaquismo</div>
	    	 <form:checkbox path="tabaquismo"/>
	    	<div class="texto_supervisor5 gris tam_3 margen7">Alcoholismo</div>
	    	 <form:checkbox path="alcoholismo"/>    
    </div>
    
    <div class="linea_corta margen margen_arriba2">
	   		<div class="texto_supervisor5 gris tam_3">Dependencia drogas</div>
	    	 <form:checkbox path="dependenciaADroga"/>
	    	<div class="texto_supervisor5 gris tam_3 margen7">Farmacodependiente</div>
	    	 <form:checkbox path="dependenciaAMedicamentos"/>
	    	<div class="texto_supervisor5 gris tam_3 margen7">Malformaciones</div>
	    	<form:checkbox path="malformaciones"/>   
    </div> 
    <div class="linea_corta margen margen_arriba2">
	   		<div class="texto_supervisor5 gris tam_3">Disfunciones familiares</div>
	   		 <form:checkbox path="disfuncionesFamiliares"/>  
	    	<div class="texto_supervisor5 gris tam_3 margen7">Quirúrgicas</div>
	    	 <form:checkbox path="quirurgias"/>      
    </div>
    
    <div class="linea_mini margen">

				<div class="texto_supervisor5 gris tam_3 margen_arriba5">
		          OTRAS (Especifíque)
		        </div>
		        <form:textarea path="otras" class="campo_supervisor2 comment izquierdo" name="comentarios" placeholder="...Otras..." rows="2"></form:textarea>
     	</div>       

     <div class="linea_supervisor margen margen_arriba">
     		<div class="btn btn_guardar" onclick="guardarAntecedentes()"></div>
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
	
	function guardarAntecedentes(){
				var antecedentesPersonalesForm = $("#antecedentesPersonalesForm").serialize();
			    $.getJSON("guardarAntecedentesPersonales", antecedentesPersonalesForm ,function(response){
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
							content : "<div id=\"contenedor3_popup\"><div class=\"icon_popup usuario2\"></div><h1> Antecedentes Personales Guardados exitosamente</h1><div class=\"btn_popup\"><div class=\"btn_cerrar_popup\" onclick=\"$.fancybox.close();\"></div></div></div>",
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