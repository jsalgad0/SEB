	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_medico.css">
	
	<div id="nav">
		<div id="reloj">
			<canvas id="clock" class="CoolClock:simple"></canvas>
		</div>
			<div class="menu_admin_atn2">
				<c:choose>
					<c:when test="${menu == 1}">
		            	<div class="menu_item_off off28">
		                	<div class="texto_admin"><p>NUEVO CONVENIO</p></div>
		                </div>		                					
					</c:when>
					<c:otherwise>
		            	<div class="menu_item item28"  onclick="nuevoConvenios()" >
		                	<div class="texto_admin"><p>NUEVO CONVENIO</p></div>
		                </div>
					</c:otherwise>
				</c:choose>				
				<c:choose>
					<c:when test="${menu == 2}">
		                <div class="menu_item_off off29">
		                	<div class="texto_admin"><p>MANTENCIÓN DE CONVENIO</p></div>
		                </div>		                		                					
					</c:when>
					<c:otherwise>
		                <div class="menu_item item29" onclick="modificarConvenios()">
		                	<div class="texto_admin"><p>MANTENCIÓN DE CONVENIO</p></div>
		                </div>
					</c:otherwise>
				</c:choose>				

                <div class="menu_item item0" onclick="regresar()">
                	<div class="texto_admin"><p>SALIR CONVENIOS</p></div>
                </div>
            </div>
		
		
	</div>	
	
	<script type="text/javascript">

		function regresar(){
			window.location.href = '/SAB/menu';
		}
		
		function modificarConvenios(){
			window.location.href = '/SAB/administrador/modificarConvenio';
		}
		
		function nuevoConvenios(){
			window.location.href = '/SAB/administrador/nuevoConvenio';
		}
		
	</script>		
	