<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>

<div id="nav">
	<div id="reloj">
		<canvas id="clock" class="CoolClock:simple"></canvas>
	</div>
	<form:form name='mantenimientoUsuariosForm' action="inicio" method='POST' commandName="mantenimientoUsuariosForm" id="mantenimientoUsuariosForm">
	<div class="menu_us">
		
		<div class="menu_item item1" id="clickeable" onclick="nuevoUsuario()">
        	<div class="texto_us"><p>NUEVO USUARIO</p></div>
		</div>

		<div class="menu_item_off off2" id="clickeable">
			<div class="texto_us">
				<p>MODIFICAR USUARIO</p>
			</div>
		</div>
		<div class="menu_item item3" id="clickeable"
			onclick="consultaUsuariosPendientesClave()">
			<div class="texto_us">
				<p>PENDIENTE DE CLAVE</p>
			</div>
		</div>
		<div class="menu_item item4" id="clickeable"
			onclick="usuariosConDeclaracionPendiente()">
			<div class="texto_us">
				<p>DECLARACIÓN NO ACEPTADA</p>
			</div>
		</div>
		<div class="menu_item item5" id="clickeable"
			onclick="bloqueados()">
			<div class="texto_us">
				<p>CLAVE BLOQUEADA</p>
			</div>
		</div>
		<div class="menu_item item6" id="clickeable"
		 	onclick="regresar()">
            <div class="texto_us">
            	<p>REGRESAR</p>
           	</div>
        </div>
	</div>
	</form:form>
	
	<script type="text/javascript">
		function nuevoUsuario(){
			$("#mantenimientoUsuariosForm").attr("action", "nuevoUsuario");
			$("#mantenimientoUsuariosForm").submit();			
		}
		
		function modificarUsuario(){
			$("#mantenimientoUsuariosForm").attr("action", "modificarUsuario");
			$("#mantenimientoUsuariosForm").submit();			
		}
		
		function consultaUsuariosPendientesClave(){
			$("#mantenimientoUsuariosForm").attr("action", "pendientesClave");
			$("#mantenimientoUsuariosForm").submit();			
		}
		
		function usuariosConDeclaracionPendiente(){
			$("#mantenimientoUsuariosForm").attr("action", "declaracionPendiente");
			$("#mantenimientoUsuariosForm").submit();			
		}
		
		function bloqueados(){
			$("#mantenimientoUsuariosForm").attr("action", "bloqueados");
			$("#mantenimientoUsuariosForm").submit();			
		}
		
		function regresar(){
			window.location.href = '/SAB/menu';
		}
	</script>	
</div>