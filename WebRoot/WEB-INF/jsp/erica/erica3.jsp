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
	          SALVADOR CONTRERAS HERN�NDEZ
	         </div>
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	          <div class="texto_supervisor verde margen tam_16">
	          Edad:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          20 a�os
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
	          �ltima Somatometr�a:
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
   				<li class="pactivaroja"><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/nota_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">NOTA M�DICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/receta.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">RECETA M�DICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/laboratorio.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE LABORATORIO</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/gabinete.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ESTUDIOS DE GABINETE</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/otros_estudios.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">OTROS ESTUDIOS O SERVICIOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/licencia_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">LICENCIA M�DICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/solicitud_referencia.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">SOLICITUD DE REFERENCIA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/certificados.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">CERTIFICADOS</a></li>
   					</ul>
   				</li>
   			</ul>
   		</div>
  
	<div class="linea_supervisor margen">
   		<textarea class="campo_supervisor tam_23" placeholder="Motivo de la Consulta"></textarea>
    </div>
    
    <div class="linea_supervisor margen">
   		<textarea class="campo_supervisor tam_24" placeholder="Exploraci�n F�sica"></textarea>
   		<div class="texto_supervisor gris tam_6">
				<div class="btn btn_tomar_sig_vitales"></div>
        </div>  
    </div>
    
    <div class="linea_supervisor margen">
   		<textarea class="campo_supervisor tam_24" placeholder="Diagn�stico Principal"></textarea>

   		<div class="texto_supervisor gris tam_6">
				<div class="btn btn_otrosdiag"></div>
        </div>  
    </div>
    
    <div class="linea_supervisor margen margen_arriba3">
    		<div class="texto_supervisor5 gris">Tipo de Diagn�stico:</div>
    		<input class="margen5" type="radio" name="definitivo" />
	    	<div class="texto_supervisor5 gris">Definitivo</div>
	    	<input class="margen5" type="radio" name="definitivo" />
	    	<div class="texto_supervisor5 gris">Presuntivo</div>
    </div>
    
	<div class="linea_supervisor margen">
   		<textarea class="campo_supervisor tam_23" placeholder="Pr�nostico - Tratamiento - Medidas Recomendadas"></textarea>
    </div>        

     <div class="linea_supervisor margen">
     		<div class="btn_grande btn_consultorio"></div>
     		<div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
</html>