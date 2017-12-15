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
	          SALVADOR CONTRERAS HERNÁNDEZ
	         </div>
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	         <div class="texto_supervisor verde margen tam_16">
	          Edad:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          20 años
	         </div>
	         
	         <div class="texto_supervisor verde tam_16">
	          Peso:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          55 kg
	         </div>
			
	        <div class="texto_supervisor verde tam_1">
	          Altura:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          170.00 cm
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_15">
	          T.A.:
	         </div>
	         
	         <div class="texto_supervisor gris margen5 tam_6">
	          120/80
	         </div>
	         
	         <div class="texto_supervisor verde tam_3">
	          Última Somatometría:
	         </div>
	         
	         <div class="texto_supervisor gris tam_4">
	          12-noviembre-2015
	         </div>
		</div>
    </div>  
 
  <div class="admin_contenidoFrame">   
  
  <div class="linea_corta2">
   <div class="pleca_menu"></div><!--     
     --><div class="pestanias_contenedor">
   			<ul id="pmenu">
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/ante_familiares.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ANTECEDENTES FAMILIARES</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/personales_nopat.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">PERSONALES NO PATOLÓGICOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/ante_personales.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ANTECEDENTES PERSONALES</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/gineco.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">GINECO-OBSTÉTRICOS</a></li>
   					</ul>
   				</li>
   				
   				<li class="pactiva"><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/exploracion_fisica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">EXPLORACIÓN FÍSICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/tipo_familia.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text3 texto_pestania"><a href="#">FASE CICLO VITAL / TIPO DE FAMILIA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/familiograma.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">FAMILIOGRAMA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/historia_clinica/expediente_digital.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">EXPEDIENTE DIGITAL</a></li>
   					</ul>
   				</li>
   			</ul>
   		</div>
   		
   		<div class="admin_contenidoFrame2">
			  <div class="linea_mini margen12 margen_arriba texto_arriba">
					<input class="campo_supervisor2 tam_12" placeholder="Peso*" /><!--
	    			--><input class="campo_1 tam_1" placeholder="Kg." disabled />
	    			 
	    			<input class="campo_supervisor2 tam_12" placeholder="Altura*" /><!--
			    	--><input class="campo_1 tam_1" placeholder="cm." disabled />
			    	
			    	<input class="campo_supervisor2 tam_17" placeholder="T.A.*" /><!--
			    	--><input class="campo_1 tam_21" placeholder="mmHG" disabled />
			    	
			    	<input class="campo_supervisor2 tam_12" placeholder="Temperatura*" /><!--
			    	--><input class="campo_1 tam_1" placeholder="ºC" disabled />    
				</div>
		</div>
		
		<div class="linea_mini2 margen margen_arriba texto_arriba">
			<div class="titulo_HC fondo_v">
				<div class="texto_supervisor5 blanco fondo_v tam_12">Con Alteración</div>
			</div>
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado">Cabeza</div>
			   <input type="checkbox" name="alteraciones" />
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado">Cuello</div>
			    <input type="checkbox" name="alteraciones" />
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado">Torax</div>
			    <input type="checkbox" name="alteraciones" />
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado">Abdomen</div>
			    <input type="checkbox" name="alteraciones" />
			<div class="texto_supervisor5 gris tam_12 margen2">Sistema Músculo Esquelético</div>
			    	<input type="checkbox" name="alteraciones" />       
		</div>
		
		<div class="linea_mini2 margen margen_arriba texto_arriba">
			<div class="titulo_HC fondo_v">
				<div class="texto_supervisor5 blanco fondo_v tam_12">Con Alteración</div>
			</div>
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado">Extremidades</div>
			   <input type="checkbox" name="alteraciones" />
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado">Sistema Nervioso</div>
			    <input type="checkbox" name="alteraciones" />
			<div class="texto_supervisor5 gris tam_12 margen2 margen_arriba2">Sistema Cardiovascular</div>
			    <input type="checkbox" name="alteraciones" />   
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado margen_arriba5">Aparato Digestivo</div>
			    <input type="checkbox" name="alteraciones" />      
		</div>		 

     <div class="linea_supervisor margen margen_arriba">
     		<div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
</html>