	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_medico.css">
	
	<div id="nav">
		<div id="reloj">
			<canvas id="clock" class="CoolClock:simple"></canvas>
		</div>
			<div class="menu_admin_atn">
            	<div class="menu_item item25"  onclick="location.href='#';" >
                	<div class="texto_admin"><p>NUEVO LUGAR DE ATENCIÓN</p></div>
                </div>
                
                <div class="menu_item item26" onclick="location.href='#';">
                	<div class="texto_admin"><p>MODIFICAR LUGAR DE ATENCIÓN</p></div>
                </div>
                <div class="menu_item item27" onclick="location.href='#';">
                	<div class="texto_admin"><p>ELIMINAR LUGAR DE ATENCIÓN</p></div>
                </div>
                                
                <div class="menu_item item0" onclick="regresar()">
                	<div class="texto_admin"><p>SALIR</p></div>
                </div>
            </div>
            
            
	</div>	
	
		<script type="text/javascript">

		function regresar(){
			window.location.href = '/SAB/menu';
		}
	</script>
	