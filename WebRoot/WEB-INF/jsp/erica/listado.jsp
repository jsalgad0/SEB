<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<script type="text/javascript">

$(document).ready(

);

</script>
<body>	
<form:form>
			
			
	<div id="admin_contenido">
	<div class="admin_titulo">LISTADO DE MENORES DE EDAD</div><!--
    --><div class="admin_pleca"></div>
                    
    <div class="linea_supervisor margen_arriba2">
    
    	<div class="titulo_supervisor verde margen9 tam_2">
          NOMBRE PACIENTE
         </div>

    </div>  
                 
    <div class="pleca_simple"></div> 
    
    <div class="contenedor_supervisor">
    <select><option value="0" selected>Filtrar</option><option value="1">Asegurador</option><option value="2">Lugar</option></select>	
    	<c:choose>
    		<c:when test="${ empty lista }">
    		<div>no hay menores</div>
    		</c:when>
    		<c:otherwise>
    			<c:forEach items="${lista}" var="renglon">
    				<div>${renglon.nombre} ${renglon.apellidoPaterno} ${renglon.apellidoMaterno}</div>
    				<div>${renglon.fechaDeNacimiento}</div>
    			</c:forEach>
    		</c:otherwise>
    	</c:choose> 
    </div>
    
                

</div>
	
	</form:form>
	
</body>

</html>