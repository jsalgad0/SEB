<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<script type="text/javascript">
		function recepcionPaciente(){
			var ctx = "${pageContext.request.contextPath}";
			window.location.href = ctx+"/recepcion/inicioRecepcion";		
		}
		
		function atencionesPendientes(){
			var ctx = "${pageContext.request.contextPath}";
			window.location.href = ctx+"/recepcion/inicioRecepcionAtencionesPendientes";		
		}
		
		function buscarAtencion(){
			var ctx = "${pageContext.request.contextPath}";
			window.location.href = ctx+"/recepcion/inicioRecepcionBuscarAtencion";		
		}
		
		function pacientesRecibidos(){
			var ctx = "${pageContext.request.contextPath}";
			window.location.href = ctx+"/recepcion/pacientesRecibidos";		
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
<div id="nav">
	<div id="reloj">
		<canvas id="clock" class="CoolClock:simple"></canvas>
	</div>
	<div class="menu_recepcion">
		<div class="menu_item item6"  onclick="recepcionPaciente()" >
			<div class="texto_recepcion"><p>RECEPCIÓN DE PACIENTE</p></div>
		</div>
              
		<div class="menu_item item7" onclick="pacientesRecibidos()">
			<div class="texto_recepcion"><p>CONSULTA PACIENTES RECIBIDOS</p></div>
		</div>
		<div class="menu_item item8" onclick="buscarAtencion()">
			<div class="texto_recepcion"><p>BUSCAR ATENCIÓN</p></div>
		</div>
		<div class="menu_item item9" onclick="atencionesPendientes()">
			<div class="texto_recepcion"><p>ATENCIONES PENDIENTES DE AUTORIZAR</p></div>
		</div>
		<div class="menu_item item0" onclick="regresar()">
			<div class="texto_recepcion"><p>REGRESAR</p></div>
		</div>
	</div>
	
	
</div>