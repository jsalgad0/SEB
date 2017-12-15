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
	         <div class="texto_supervisor verde margen2 tam_16">
	          Edad:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          20 años
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_16">
	          Peso:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          55 kg
	         </div>
			
	        <div class="texto_supervisor verde margen5 tam_1">
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
   				<li><a href="#"><img src=""class="icono_pestania icono_domicilio"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">DOMICILIO</a></li>
   					</ul>
   				</li>
   				
   				<li class="pactiva"><a href="#"><img src=""class="icono_pestania icono_demog"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">DEMOGRÁFICOS</a></li>
   					</ul>
   				</li>
   				
   				<li><a href="#"><img src=""class="icono_pestania icono_otros"/></a>
   					<ul class="psubmenu">
   						<li class="caja_texto pad_text1 texto_pestania"><a href="#">OTROS</a></li>
   					</ul>
   				</li>
   			</ul>
   		</div>
	   
	<div class="linea_supervisor margen">
	 		<select class="selectt campo_supervisor tam_2" name="EdoCivil">
            	<option>Estado Civil</option>
            	<option>Casado</option>
                <option>Soltero</option>
            </select> 
	 		<select class="selectt campo_supervisor tam_2" name="Escolaridad">
            	<option>Escolaridad</option>
            	<option>Primaria</option>
                <option>Secundaria</option>
            </select>
	 		<select class="selectt campo_supervisor tam_2" name="Ocupacion">
            	<option>Ocupación</option>
            	<option>Carpintero</option>
                <option>Abogado</option>
            </select>                            
	</div>  
	<div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_2" placeholder="Nacionalidad"/> 
   		<input class="campo_supervisor tam_2" placeholder="Religión"/> 
   		<input class="campo_supervisor tam_2" placeholder="Nivel Socio-Económico"/>   
    </div>
    
     <div class="linea_corta margen_arriba2 margen">
	   		<div class="texto_supervisor5 gris">Grupo Sanguíneo:</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 gris">A</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 gris">B</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 gris">AB</div>
	    	<input class="margen2" type="radio" name="sangre" />
	    	<div class="texto_supervisor5 gris">O</div>
	    	<input class="margen3" type="radio" name="+-" />
	    	<div class="texto_supervisor5 gris">Positivo</div>
	    	<input class="margen2" type="radio" name="+-" />
	    	<div class="texto_supervisor5 gris">Negativo</div>
	    </div> 
	 <div class="linea_corta margen_arriba2 margen">
	   		<div class="texto_supervisor5 gris">Inmigrante:</div>
	    	<input type="checkbox" name="sangre" />
	    	<div class="texto_supervisor5 gris margen">Indígena:</div>
	    	<input type="checkbox" name="sangre" />     
    </div>

     <div class="linea_supervisor margen">
     		<div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>
</html>