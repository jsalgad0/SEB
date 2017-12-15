	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu_medico.css">
	
	<div id="nav">
		<div id="reloj">
			<canvas id="clock" class="CoolClock:simple"></canvas>
		</div>
			<div class="menu_admin_atn2">
            	<div class="menu_item item30"  onclick="cargarCatalogo()" >
                	<div class="texto_admin"><p>CARGAR CATÁLOGO SAB</p></div>
                </div>
                
                <div class="menu_item item31" onclick="location.href='#';">
                	<div class="texto_admin"><p>BUSCAR PRESTACIÓN</p></div>
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
		
		function buscarPrestacion(){
			window.location.href = '/sab/administrador/buscarPrestacion';
		}
		
		function cargarCatalogo(){
			window.location.href = '/sab/administrador/inicioCargaCatalogoSab';
		}
		
		
	</script>			