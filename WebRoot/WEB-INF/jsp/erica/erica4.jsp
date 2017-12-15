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
 
  <div class="admin_contenidoFrame2">   
  
  <div class="linea_corta2">
   <div class="pleca_menu"></div><!--     
     --><div class="pestanias_contenedor">
   			<ul id="pmenu">
   				<li><a href="#"><img src=""class="icono_pestania icono_antefamiliares"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ANTECEDENTES FAMILIARES</a></li>
   					</ul>
   				</li>
   				
   				<li class="pactiva"><a href="#"><img src=""class="icono_pestania icono_nopat"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">PERSONALES NO PATOLÓGICOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src=""class="icono_pestania icono_antepersonales"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ANTECEDENTES PERSONALES</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src=""class="icono_pestania icono_gineco"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">GINECO-OBSTÉTRICOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src=""class="icono_pestania icono_exfisica"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">EXPLORACIÓN FÍSICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src=""class="icono_pestania icono_tipofam"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text3 texto_pestania"><a href="#">FASE CICLO VITAL / TIPO DE FAMILIA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src=""class="icono_pestania icono_familiograma"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">FAMILIOGRAMA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src=""class="icono_pestania icono_expediente"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">EXPEDIENTE DIGITAL</a></li>
   					</ul>
   				</li>
   			</ul>
   		</div>
	<div class="admin_contenidoFrame2">	
		 <div class="linea_corta margen_arriba2 margen">	
			<input class="campo_supervisor tam_4"  style="margin-right:0px !important;" placeholder="Fecha"/><!--
	          --><input class="btn_solo btn_calendario" id="datepicker" onclick="location.href='#';" />
		 </div>
		 
		 <div class="linea_corta margen_arriba2 margen">
	   		<div class="texto_supervisor5 gris">Tipo de sangre:</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 gris">A</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 gris">B</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 gris">AB</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 gris">O</div>
	    	<input class="margen" type="radio" name="+-" />
	    	<div class="texto_supervisor5 gris">Positivo</div>
	    	<input class="margen2" type="radio" name="+-" />
	    	<div class="texto_supervisor5 gris">Negativo</div>
	    </div> 
	       
		<div class="linea_mini2 margen margen_arriba texto_arriba">
			<div class="titulo_HC fondo_v">
				<div class="texto_supervisor5 blanco fondo_v tam_12">Escolaridad</div>
			</div>
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">PRIMARIA</div>
			   <input type="radio" name="Escolaridad" />
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">SECUNDARIA</div>
			    <input type="radio" name="Escolaridad" />
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">PREPARATORIA</div>
			    <input type="radio" name="Escolaridad" />
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">LICENCIATURA</div>
			    <input type="radio" name="Escolaridad" />
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">SIN ESTUDIOS</div>
			    	<input type="radio" name="Escolaridad" />       
		</div>
		
		<div class="linea_mini2 margen margen_arriba texto_arriba">
			<div class="titulo_HC fondo_v">
				<div class="texto_supervisor5 blanco fondo_v tam_12">Estado Civil</div>
			</div>
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado2">SOLTERO(A)</div>
			   <input type="radio" name="estadocivil" />
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado2">CASADO(A)</div>
			    <input type="radio" name="estadocivil" />
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado2">DIVORCIADO(A)</div>
			    <input type="radio" name="estadocivil" />
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado2">UNIÓN LIBRE</div>
			    <input type="radio" name="estadocivil" />
			<div class="texto_supervisor5 gris tam_12 margen2 interlineado2">VIUDO(A)</div>
			    	<input type="radio" name="estadocivil" />       
		</div> 
		
		<div class="linea_mini2 margen margen_arriba texto_arriba">
			<div class="titulo_HC fondo_v">
				<div class="texto_supervisor5 blanco fondo_v tam_12">Alimentación</div>
			</div>
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">BUENA</div>
			   <input type="radio" name="alimentacion" />
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">REGULAR</div>
			    <input type="radio" name="alimentacion" />
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">MALA</div>
			    <input type="radio" name="alimentacion" />     
		</div> 
		
		<div class="linea_mini2 margen margen_arriba texto_arriba">
			<div class="titulo_HC fondo_v">
				<div class="texto_supervisor5 blanco tam_12">Higiene Personal</div>
			</div>
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">BUENA</div>
			   <input type="radio" name="higiene" />
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">REGULAR</div>
			    <input type="radio" name="higiene" />
			<div class="texto_supervisor5 gris tam_17 margen2 interlineado2">MALA</div>
			    <input type="radio" name="higiene" />   
		</div> 	       
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