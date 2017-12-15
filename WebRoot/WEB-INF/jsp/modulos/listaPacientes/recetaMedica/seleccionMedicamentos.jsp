<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>

	<form:form name='recetaMedicaForm' action="nuevaRecetaMedica" method='POST' commandName="recetaMedicaForm" id="recetaMedicaForm">
		<form:hidden path="idAgenda" id="idAgenda"/>
		<form:hidden path="idMedicamento" id="idMedicamento"/>
		<table>		
			<tr>
				<td>
					${recetaMedicaForm.medicamentoWs.clave}
				</td>
				<td>
					<a href="#" onclick="cerrar(${recetaMedicaForm.medicamentoWs.cantidadDisponible}); false;">${recetaMedicaForm.medicamentoWs.cantidadDisponible}</a>	
				</td>				
			</tr>																						
		</table>
	</form:form>
	
	<script type="text/javascript">
		function cerrar(stock){
			window.top.$("#stock").val(stock);
			window.top.closeIframe();	
		}
	</script>
</body>
</html>