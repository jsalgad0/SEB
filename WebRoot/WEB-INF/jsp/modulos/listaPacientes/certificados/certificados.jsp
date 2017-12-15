<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
	<form:form name='certificadosForm' action="atencionMedica" method='POST' commandName="certificadosForm" id="certificadosForm">
		<form:hidden path="idAgenda"/>
		<input type="button" onclick="constanciaDeAsistencia();" value="contanciaDeAsistencia">
		<input type="button" onclick="constanciaDeSalud();" value="constanciaDeSalud">
		<input type="button" onclick="constanciaCuidadosMaternales();" value="constanciaCuidadosMaternales">
		<input type="button" onclick="recetaMedica();" value="recetaMedica">
		<input type="button" onclick="licenciaMedica();" value="licenciaMedica">
		<input type="button" onclick="estudiosLaboratorio();" value="estudiosLaboratorio">
		<input type="button" onclick="estudiosGabinete();" value="estudiosGabinete">
		<input type="button" onclick="estudiosOtros();" value="estudiosOtros">
		<input type="button" onclick="solicitudReferencia();" value="solicitudReferencia">
		<input type="button" onclick="solicitudContrareferencia();" value="solicitudContrareferencia">
		<a href="/SAB/menu">Regresar al Menu</a>
		
		<div id="dialog-constancia-asistencia" title="Constancia de asistencia"> </div>
		<div id="dialog-constancia-salud" title="Constancia de salud"> </div>
		<div id="dialog-constancia-maternales" title="Constancia de Cuidados Maternales"> </div>
	</form:form>

	<script type="text/javascript">
		$(function() {
			dialog = $("#dialog-constancia-asistencia").dialog({
				autoOpen : false,
				height : 600,
				width : 600,
				modal : true
			});

			dialogSalud = $("#dialog-constancia-salud").dialog({
				autoOpen : false,
				height : 600,
				width : 600,
				modal : true
			});	
			
			dialogMAternales = $("#dialog-constancia-maternales").dialog({
				autoOpen : false,
				height : 600,
				width : 600,
				modal : true
			});				
		});
		
		function constanciaDeAsistencia(){
			var idAgenda = $("#idAgenda").val();
			$("#dialog-constancia-asistencia").empty();
			$("#dialog-constancia-asistencia").append("<object type='text/html' data='constanciaDeAsistencia?idAgenda="+idAgenda+"' width='100%' height='100%'></object>");
			dialog.dialog( "open" );
		}
		
		function constanciaDeSalud(){
			var idAgenda = $("#idAgenda").val();
			$("#dialog-constancia-salud").empty();
			$("#dialog-constancia-salud").append("<object type='text/html' data='constanciaDeSalud?idAgenda="+idAgenda+"' width='100%' height='100%'></object>");
			dialogSalud.dialog( "open" );
		}	
		
		function constanciaCuidadosMaternales(){
			var idAgenda = $("#idAgenda").val();
			$("#dialog-constancia-salud").empty();
			$("#dialog-constancia-salud").append("<object type='text/html' data='constanciaCuidadosMaternales?idAgenda="+idAgenda+"' width='100%' height='100%'></object>");
			dialogSalud.dialog( "open" );
		}	
		
		function recetaMedica(){
			$("#certificadosForm").attr("action", "imprimirRecetaMedica");
			$("#certificadosForm").submit();
		}
		
		function licenciaMedica(){
			$("#certificadosForm").attr("action", "imprimirLicenciaMedica");
			$("#certificadosForm").submit();			
		}
		
		function estudiosLaboratorio(){
			$("#certificadosForm").attr("action", "imprimirEstudiosLaboratorio");
			$("#certificadosForm").submit();			
		}	
		
		function estudiosGabinete(){
			$("#certificadosForm").attr("action", "imprimirEstudiosGabinete");
			$("#certificadosForm").submit();			
		}
		
		function estudiosOtros(){
			$("#certificadosForm").attr("action", "imprimirEstudiosOtros");
			$("#certificadosForm").submit();			
		}
		
		function solicitudReferencia(){
			$("#certificadosForm").attr("action", "imprimirSolicitudReferencia");
			$("#certificadosForm").submit();			
		}
		
		function solicitudContrareferencia(){
			$("#certificadosForm").attr("action", "imprimirSolicitudContrareferencia");
			$("#certificadosForm").submit();			
		}		

	</script>
</body>
</html>