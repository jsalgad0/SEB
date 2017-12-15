<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='autorizacionEspecialForm' action="autorizarPermisoEspecialVigencia" method='POST' commandName="autorizacionEspecialForm" id="autorizacionEspecialForm">
		<form:hidden path="idPermisoEspecial" id="idPermisoEspecial"/>
		<table>
			<tr>
				<td colspan="1"><form:errors path="error" /><c:out value="${autorizacionEspecialForm.error}"></c:out> <span id="errores"></span> </td>
			</tr>
			<tr>
				<td>NÚM</td>
				<td>NOMBRE DEL PACIENTE</td>
				<td>TIPO DE AUTORIZACION</td>
				<td>AUTORIZACION</td>					
			</tr>			
			<c:forEach items="${permisosEspeciales}" var="permisoEspecial" >
				<tr>
					<td>${permisoEspecial.id.permisoEspecialAfiliadoId}</td>
					<td>${permisoEspecial.afiliado.nombre} ${permisoEspecial.afiliado.apellidoPaterno} ${permisoEspecial.afiliado.apellidoMaterno}</td>
					<c:if test="${permisoEspecial.tipoAutorizacion == 1}">
						<td>Si<input type="radio" name="tipoAutorizacion_${permisoEspecial.id.permisoEspecialAfiliadoId}" id="tipoAutorizacion_${permisoEspecial.id.permisoEspecialAfiliadoId}_si" onclick="autorizarVigencia(${permisoEspecial.id.permisoEspecialAfiliadoId})"/>/No<input type="radio" name="tipoAutorizacion_${permisoEspecial.id.permisoEspecialAfiliadoId}" id="tipoAutorizacion_${permisoEspecial.id.permisoEspecialAfiliadoId}_no" checked="checked"></td>
						<td>VIGENCIA</td>
					</c:if>
					<c:if test="${permisoEspecial.tipoAutorizacion == 2}">
						<td>Si<input type="radio" name="tipoAutorizacion_${permisoEspecial.id.permisoEspecialAfiliadoId}"  id="tipoAutorizacion_${permisoEspecial.id.permisoEspecialAfiliadoId}_si" onclick="autorizarHuella(${permisoEspecial.id.permisoEspecialAfiliadoId})"/>/No<input type="radio" name="tipoAutorizacion_${permisoEspecial.id.permisoEspecialAfiliadoId}"  id="tipoAutorizacion_${permisoEspecial.id.permisoEspecialAfiliadoId}_no" checked="checked"></td>
						<td>HUELLA</td>
					</c:if>					
				</tr>
			</c:forEach>			
		</table>
	</form:form>
	
	<script type="text/javascript">
		function autorizarVigencia(idPermiso){
			var respuesta = confirm("¿Desea dar autorizacion especial a este afiliado?");
			if(respuesta){
				$("#idPermisoEspecial").val(idPermiso);
				$("#autorizacionEspecialForm").submit();
			}else{
				$("#tipoAutorizacion_"+idPermiso+"_si").prop("checked",false);
				$("#tipoAutorizacion_"+idPermiso+"_no").prop("checked",true);
			}
		}
		
		function autorizarHuella(idPermiso){
			$("#idPermisoEspecial").val(idPermiso);
			$("#autorizacionEspecialForm").attr("action", "huella");
			$("#autorizacionEspecialForm").submit();
		}
	</script>
</body>
</html>