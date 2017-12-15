<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>
</head>
<body>

	<script type="text/javascript">
        
		$( document ).ready(function() {
			$( "#loginForm" ).submit();
       	});
        
	</script>
	<div id="login-box">
		
		<form name='loginForm' id="loginForm" action="<c:url value='j_spring_security_check' />" method='POST'>
			<input type="hidden" id="autenticacionHuella" name="autenticacionHuella" value="${loginForm.autenticacionHuella}">
			<input type="hidden" id="muestraRoles" name="muestraRoles" value="${loginForm.muestraRoles}">
			<input type="hidden" id="nroAudit" name="nroAudit" value="${loginForm.nroAudit}">
			<input type="hidden" id="ercDesc" name="ercDesc" value="${loginForm.ercDesc}">
			<input type="hidden" id="j_username" name="j_username" value="${loginForm.rfc}">
			<input type="hidden" id="rol" name="rol" value="${loginForm.rol}">
			<input type="hidden" id="tx_Marca" name="tx_Marca" value="${loginForm.tx_Marca}">
			<input type="hidden" id="tx_Serie" name="tx_Serie" value="${loginForm.tx_Serie}">
			<input type="hidden" id="tx_Modelo" name="tx_Modelo" value="${loginForm.tx_Modelo}">
			<input type="hidden" id="tx_Fabric" name="tx_Fabric" value="${loginForm.tx_Fabric}">
			<input type="hidden" id="idUsuario" name="idUsuario" value="${loginForm.usuarios.usuarioId}">
		</form>		
	</div>

</body>
</html>