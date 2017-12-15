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
   <div class="pleca_menu"></div>
	
	<div class="linea_supervisor margen margen_arriba">
   		<input class="campo_supervisor tam_2" placeholder="Fecha de Nacimiento"/> 
   		<div class="texto_supervisor5 gris tam_3 margen2">Sexo:
   			<div class="texto_supervisor5 gris tam_1 margen2">F
   				<input type="radio" name="sexo" /> 
   			</div>	
	    	<div class="texto_supervisor5 gris tam_1">M
	    		<input type="radio" name="sexo" />
	    	</div>	
   		</div>   		 
   </div>
	   
	<div class="linea_supervisor margen">
	 		<select class="selectt campo_supervisor tam_2" name="Temporal">
            	<option>Estado</option>
            	<option>Aguscalientes</option>
                <option>Baja California Sur</option>
            </select> 
	 		<select class="selectt campo_supervisor tam_2" name="Temporal">
            	<option>Delegación o Municipio</option>
            	<option>Álvaro Obregón</option>
                <option>Miguel Hidalgo</option>
            </select>
	 		<select class="selectt campo_supervisor tam_2" name="Temporal">
            	<option>Localidad/Colonia</option>
            	<option>Anáhuac</option>
                <option>Leyes de Reforma</option>
            </select>                            
	</div>  
	<div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_20" placeholder="Dirección"/> 
   		<input class="campo_supervisor tam_4" placeholder="Código Postal"/>  
    </div>
    
    <div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_2" placeholder="Teléfono"/> 
   		<input class="campo_supervisor tam_2" placeholder="Email"/>
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
    
    <div class="linea_supervisor margen margen_arriba2">
   		<input class="campo_supervisor tam_2" placeholder="Nombre Responsable"/> 
   		<input class="campo_supervisor tam_2" placeholder="Parentesco"/> 
   		<input class="campo_supervisor tam_3" placeholder="Edad Responsable"/>   
    </div>
    
    <div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_20" placeholder="Dirección Responsable"/> 
   		<input class="campo_supervisor tam_2" placeholder="Lugar Responsable"/>   
    </div>
    
     <div class="linea_supervisor margen">
   		<input class="campo_supervisor tam_2" placeholder="Teléfono Responsable"/>   
    </div>

     <div class="linea_supervisor margen margen_arriba2">
     		<div class="btn btn_guardar"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>

</form:form>


</body>

</html>