	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_medico.css">
	
	<div id="nav">
		<div id="reloj">
			<canvas id="clock" class="CoolClock:simple"></canvas>
		</div>
			<div class="menu_admin_atn">
				<c:choose>
					<c:when test="${menu == 1}">
		            	<div class="menu_item_off off16" >
		                	<div class="texto_admin"><p>NUEVO LECTOR</p></div>
		                </div>                							
					</c:when>
					<c:otherwise>
		            	<div class="menu_item item16"  onclick="nuevoRegistroLector()" >
		                	<div class="texto_admin"><p>NUEVO LECTOR</p></div>
		                </div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${menu == 2}">
		                <div class="menu_item_off off17">
		                	<div class="texto_admin"><p>MODIFICAR DATOS DE LECTOR</p></div>
		                </div>		                		                                							
					</c:when>
					<c:otherwise>
		                <div class="menu_item item17" onclick="modificarRegistroLector()">
		                	<div class="texto_admin"><p>MODIFICAR DATOS DE LECTOR</p></div>
		                </div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${menu == 3}">
		                <div class="menu_item_off off18">
		                	<div class="texto_admin"><p>DAR DE BAJA UN LECTOR</p></div>
		                </div>		                		                		                                							
					</c:when>
					<c:otherwise>
		                <div class="menu_item item18" onclick="eliminarRegistroLector()">
		                	<div class="texto_admin"><p>DAR DE BAJA UN LECTOR</p></div>
		                </div>
					</c:otherwise>
				</c:choose>				
 
                <div class="menu_item item0" onclick="regresar()">
                	<div class="texto_admin"><p>SALIR</p></div>
                </div>
            </div>
		
		
	</div>	
	
	<script type="text/javascript">

		function regresar(){
			window.location.href = '/SAB/menu';
		}
		
		function modificarRegistroLector(){
			window.location.href = '/SAB/administrador/modificarRegistroLector';
		}
		
		function nuevoRegistroLector(){
			window.location.href = '/SAB/administrador/nuevoRegistroLector';
		}
		
		function eliminarRegistroLector(){
			window.location.href = '/SAB/administrador/eliminarRegistroLector';
		}
		
	</script>	
	