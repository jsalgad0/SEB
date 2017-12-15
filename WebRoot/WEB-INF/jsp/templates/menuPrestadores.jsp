	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_medico.css">
	
	<div id="nav">
		<div id="reloj">
			<canvas id="clock" class="CoolClock:simple"></canvas>
		</div>
			<div class="menu_admin_atn">
				<c:choose>
					<c:when test="${menu == 1}">
		            	<div class="menu_item_off off22">
		                	<div class="texto_admin"><p>NUEVO PRESTADOR</p></div>
		                </div>					
					</c:when>
					<c:otherwise>
		            	<div class="menu_item item22"  onclick="nuevoPrestadores()" >
		                	<div class="texto_admin"><p>NUEVO PRESTADOR</p></div>
		                </div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${menu == 2}">
		                <div class="menu_item_off off23">
		                	<div class="texto_admin"><p>MODIFICAR PRESTADOR</p></div>
		                </div>			
					</c:when>
					<c:otherwise>
		                <div class="menu_item item23" onclick="modificarPrestadores()">
		                	<div class="texto_admin"><p>MODIFICAR PRESTADOR</p></div>
		                </div>
					</c:otherwise>
				</c:choose>				
				<c:choose>
					<c:when test="${menu == 3}">
		                <div class="menu_item_off off24">
		                	<div class="texto_admin"><p>ELIMINAR PRESTADOR</p></div>
		                </div>			
					</c:when>
					<c:otherwise>
		                <div class="menu_item item24" onclick="eliminarPrestadores()">
		                	<div class="texto_admin"><p>ELIMINAR PRESTADOR</p></div>
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
		
		function modificarPrestadores(){
			window.location.href = '/SAB/administrador/modificarPrestador';
		}
		
		function nuevoPrestadores(){
			window.location.href = '/SAB/administrador/nuevoPrestador';
		}
		
		function eliminarPrestadores(){
			window.location.href = '/SAB/administrador/eliminarPrestador';
		}
		
	</script>		
	