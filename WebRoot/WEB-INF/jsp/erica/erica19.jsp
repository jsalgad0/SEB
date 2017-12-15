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
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/nota_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">NOTA MÉDICA</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/receta.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">RECETA MÉDICA</a></li>
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
   				
   				<li class="pactiva"><a href="#"><img src="${pageContext.request.contextPath}/resources/img/atn_medica/licencia_medica.png" class="icono_pestania"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">LICENCIA MÉDICA</a></li>
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
   		<div class="texto_supervisor2 verde">
	          LICENCIA MÉDICA
	    </div>
   		<input class="campo_supervisor tam_3 margen2" placeholder="Folio" />
   		<input class="campo_supervisor tam_3" placeholder="Fecha de emisión" />
    </div>
  
	<div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_26" placeholder="Diagnóstico CIE10" />
   		<input class="campo_supervisor tam_20" placeholder="Glosa Diagnóstico" />
    </div>
    
    <div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_3" placeholder="No. de días*" />
   		<input class="campo_supervisor tam_3"  style="margin-right:0px !important;" placeholder="Fecha Inicio"/><!--
          --><input class="btn_solo btn_calendario" id="datepicker" onclick="location.href='#';" />
        <input class="campo_supervisor tam_3 margen14"  style="margin-right:0px !important;" placeholder="Fecha Término"/><!--
          --><input class="btn_solo btn_calendario" id="datepicker" onclick="location.href='#';" />  
   		<input class="campo_supervisor tam_11 margen5" placeholder="Días otorgados (con letra)" />
    </div>
    
    <div class="linea_supervisor margen tam_27 margen_arriba2">
   		<div class="titulo_supervisor gris">Motivos de la Licencia:*</div>
	    	<input class="margen2" type="radio" name="motivo" />
	    	<div class="texto_supervisor5 gris">Enfermedad General</div>
	    	<input class="margen2" type="radio" name="motivo" />
	    	<div class="texto_supervisor5 gris">Maternidad (Pre)</div>
	    	<input class="margen2" type="radio" name="motivo" />
	    	<div class="texto_supervisor5 gris">Maternidad (Post)</div>
    </div>
    
    <div class="linea_supervisor margen margen_arriba2">
    		<div class="titulo_supervisor gris">Carácter de la Licencia:*</div>
    		<input class="margen5" type="radio" name="licencia" />
	    	<div class="texto_supervisor5 gris">Inicial</div>
	    	<input class="margen5" type="radio" name="licencia" />
	    	<div class="texto_supervisor5 gris">Subsecuente</div>
	    	<input class="margen5" type="radio" name="licencia" />
	    	<div class="texto_supervisor5 gris">Retroactiva</div>
	    	<input class="margen5" type="radio" name="licencia" />
	    	<div class="texto_supervisor5 gris">Excepcional</div>
    </div>
    
	<div class="linea_supervisor margen margen_arriba2">
    		<div class="titulo_supervisor gris">Tipo de Servicio otorgado:*</div>
    		<input class="margen5" type="radio" name="servicio" />
	    	<div class="texto_supervisor5 gris">Consulta externa</div>
	    	<input class="margen5" type="radio" name="servicio" />
	    	<div class="texto_supervisor5 gris">Hospitalización</div>
	    	<input class="margen5" type="radio" name="servicio" />
	    	<div class="texto_supervisor5 gris">Urgencias</div>
    </div>       

     <div class="linea_supervisor margen margen_arriba">
     		<div class="btn_grande btn_licant"></div>
     		<div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
</html>