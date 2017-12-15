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
   				<li  class="pactiva"><a href="#"><img src=""class="icono_pestania icono_antefamiliares"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text2 texto_pestania"><a href="#">ANTECEDENTES FAMILIARES</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src=""class="icono_pestania icono_nopat"/></a>
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
	   
	 <div class="linea_corta margen margen_arriba2">
	   		<div class="texto_supervisor5 gris tam_3">Diabetes Mellitus</div>
	    	<input type="checkbox" name="sangre" />
	    	<div class="texto_supervisor5 gris tam_3 margen7">Hipertensión Arterial</div>
	    	<input type="checkbox" name="sangre" />
	    	<div class="texto_supervisor5 gris tam_3 margen7">Neoplasias</div>
	    	<input type="checkbox" name="sangre" />     
    </div> 

	 <div class="linea_corta margen margen_arriba2">
	   		<div class="texto_supervisor5 gris tam_3">Cardiopatías</div>
	    	<input type="checkbox" name="sangre" />
	    	<div class="texto_supervisor5 gris tam_3 margen7">Alergias</div>
	    	<input type="checkbox" name="sangre" />
	    	<div class="texto_supervisor5 gris tam_3 margen7">Obesidad</div>
	    	<input type="checkbox" name="sangre" />     
    </div> 
    
    <div class="linea_corta margen margen_arriba2">
	   		<div class="texto_supervisor5 gris tam_3">Tuberculosis</div>
	    	<input type="checkbox" name="sangre" />
	    	<div class="texto_supervisor5 gris tam_3 margen7">Tabaquismo</div>
	    	<input type="checkbox" name="sangre" />
	    	<div class="texto_supervisor5 gris tam_3 margen7">Alcoholismo</div>
	    	<input type="checkbox" name="sangre" />     
    </div>
    
    <div class="linea_corta margen margen_arriba2">
	   		<div class="texto_supervisor5 gris tam_3">Dependencia drogas</div>
	    	<input type="checkbox" name="sangre" />
	    	<div class="texto_supervisor5 gris tam_3 margen7">Farmacodependientel</div>
	    	<input type="checkbox" name="sangre" />
	    	<div class="texto_supervisor5 gris tam_3 margen7">Malformaciones</div>
	    	<input type="checkbox" name="sangre" />     
    </div> 
    <div class="linea_corta margen margen_arriba2">
	   		<div class="texto_supervisor5 gris tam_3">Disfunciones familiares</div>
	    	<input type="checkbox" name="sangre" />
	    	<div class="texto_supervisor5 gris tam_3 margen7">Quirúrgicas</div>
	    	<input type="checkbox" name="sangre" />     
    </div>
    
    <div class="linea_mini margen">

				<div class="texto_supervisor5 gris tam_3 margen_arriba5">
		          OTRAS (Especifíque)
		        </div>
		        <textarea class="campo_supervisor2 comment izquierdo" name="comentarios" rows="2">...Otras...</textarea>
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