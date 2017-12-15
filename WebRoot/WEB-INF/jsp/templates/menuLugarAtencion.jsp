	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_medico.css">
	
	<div id="nav">
		<div id="reloj">
			<canvas id="clock" class="CoolClock:simple"></canvas>
		</div>
			<div class="menu_admin_atn">
				<c:choose>
					<c:when test="${menu == 1}">
						<div class="menu_item_off off25">
                			<div class="texto_admin"><p>NUEVO LUGAR DE ATENCIÓN</p></div>
                		</div>					
					</c:when>
					<c:otherwise>
						<div class="menu_item item25"  onclick="nuevoLugarAtencion()" >
                			<div class="texto_admin"><p>NUEVO LUGAR DE ATENCIÓN</p></div>
                		</div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${menu == 2}">
		                <div class="menu_item_off off26">
		                	<div class="texto_admin"><p>MODIFICAR LUGAR DE ATENCIÓN</p></div>
		                </div>					
					</c:when>
					<c:otherwise>
		                <div class="menu_item item26" onclick="modificarLugarAtencion()">
		                	<div class="texto_admin"><p>MODIFICAR LUGAR DE ATENCIÓN</p></div>
		                </div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${menu == 3}">
		                <div class="menu_item_off off27">
		                	<div class="texto_admin"><p>ELIMINAR LUGAR DE ATENCIÓN</p></div>
		                </div>					
					</c:when>
					<c:otherwise>
		                <div class="menu_item item27" onclick="eliminarLugarAtencion()">
		                	<div class="texto_admin"><p>ELIMINAR LUGAR DE ATENCIÓN</p></div>
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
		
		function modificarLugarAtencion(){
			window.location.href = '/SAB/administrador/modificarLugarAtencion';
		}
		
		function nuevoLugarAtencion(){
			window.location.href = '/SAB/administrador/nuevoLugarAtencion';
		}
		
		function eliminarLugarAtencion(){
			window.location.href = '/SAB/administrador/eliminarLugarAtencion';
		}
		
	</script>