	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_medico.css">
	
	<div id="nav">
		<div id="reloj">
			<canvas id="clock" class="CoolClock:simple"></canvas>
		</div>
			<div class="menu_admin_atn">
				<c:choose>
					<c:when test="${menu == 1}">
		            	<div class="menu_item_off off19">
		                	<div class="texto_admin"><p>NUEVO ASEGURADOR</p></div>
		                </div>					
					</c:when>
					<c:otherwise>
		            	<div class="menu_item item19"  onclick="nuevoAseguradores()" >
		                	<div class="texto_admin"><p>NUEVO ASEGURADOR</p></div>
		                </div>
					</c:otherwise>
				</c:choose>			
				<c:choose>
					<c:when test="${menu == 2}">
		                <div class="menu_item_off off20">
		                	<div class="texto_admin"><p>MODIFICAR ASEGURADOR</p></div>
		                </div>					
					</c:when>
					<c:otherwise>
		                <div class="menu_item item20" onclick="modificarAseguradores()">
		                	<div class="texto_admin"><p>MODIFICAR ASEGURADOR</p></div>
		                </div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${menu == 3}">
		                <div class="menu_item_off off21">
		                	<div class="texto_admin"><p>ELIMINAR ASEGURADOR</p></div>
		                </div>					
					</c:when>
					<c:otherwise>
		                <div class="menu_item item21" onclick="eliminarAseguradores()">
		                	<div class="texto_admin"><p>ELIMINAR ASEGURADOR</p></div>
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
		
		function modificarAseguradores(){
			window.location.href = '/SAB/administrador/modificarAsegurador';
		}
		
		function nuevoAseguradores(){
			window.location.href = '/SAB/administrador/nuevoAsegurador';
		}
		
		function eliminarAseguradores(){
			window.location.href = '/SAB/administrador/eliminarAsegurador';
		}
		
	</script>			