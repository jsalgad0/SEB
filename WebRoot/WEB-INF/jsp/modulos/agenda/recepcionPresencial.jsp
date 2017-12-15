<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<OBJECT classid='CLSID:592B9D7E-51C9-401F-A03C-4DE61FF7008D'
	name="Autentia" id='Autentia'>
	<embed></embed>
</OBJECT>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>

<body>

	<form:form name='atencionMedicaForm' action="nuevoAtencionMedica" method='POST' commandName="atencionMedicaForm" id="atencionMedicaForm">
		<form:hidden path="idAgenda"/>
		<form:hidden path="idPersona"/>
		<form:hidden path="afiliadotipoidentificador.cattipoidentificador.tipoIdentificadorId"/>
		<form:hidden path="agenda.afiliado.nombre" id="nombreUsuario"/>
		<form:hidden path="agenda.afiliado.apellidoPaterno" id="apellidoPaterno"/>
		<form:hidden path="agenda.afiliado.apellidoMaterno" id="apellidoMaterno"/>
		<form:hidden path="agenda.afiliado.catestadosByEstadoId.estadoId" id="estado"/>
		<form:hidden path="agenda.afiliado.catsexos.sexoId" id="sexoId"/>
		<form:hidden path="tipoDato" id="tipoDato"/>
		<form:hidden path="dato" id="dato"/>
		<form:hidden path="menorDeEdad" id="menorDeEdad"/>
		
		
		<table>
			<tr>
				<td colspan="1"><form:errors path="error" /><c:out value="${atencionMedicaForm.error}"></c:out> </td>
			</tr>		
			<tr>
				<td>Asegurador:</td>
				<td>
					<form:input path="agenda.aseguradores.nombreRazonSocial" readonly="true"/>
				</td>
			</tr>	
			<tr>
				<td>Prestador:</td>
				<td><form:input path="agenda.prestadores.nombreRazonSocial" readonly="true"/></td>
			</tr>			
			<tr>
				<td>Nombre:</td>
				<td>
					<form:select path="idConvenio" id="idConvenio" >
						<form:options items="${convenios}" itemLabel="convenio" itemValue="convenioId" ></form:options>
					</form:select>
				</td>
			</tr>	
			<tr>
				<td>Identificador:</td>
				<td><form:input path="afiliadotipoidentificador.cattipoidentificador.tipoIdentificador" readonly="true" /></td>
			</tr>	
			<tr>
				<td>Valor Identificador:</td>
				<td><form:input path="afiliadotipoidentificador.tipoIdValor" readonly="true" /></td>
			</tr>
			<tr>
				<td>Tipo:</td>
				<td>Derechohabiente - <form:radiobutton path="tipoAfiliado" value="D" /> Benefeciario - <form:radiobutton path="tipoAfiliado" value="B" /></td>
			</tr>
			<tr>
				<td>Fecha:</td>
				<td><form:input path="fechaDeNacimiento" readonly="true" /></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><form:input path="agenda.afiliado.nombre" readonly="true" /></td>
			</tr>														
			<tr>
				<td>Apellido Paterno:</td>
				<td><form:input path="agenda.afiliado.apellidoPaterno" readonly="true" /></td>
			</tr>														
			<tr>
				<td>Apellido Materno:</td>
				<td><form:input path="agenda.afiliado.apellidoMaterno" readonly="true" /></td>
			</tr>
			<tr>
				<td>Telefono:</td>
				<td><form:input path="agenda.afiliado.telefono1" readonly="true" /></td>
			</tr>	
			<tr>
				<td>Correo:</td>
				<td><form:input path="agenda.afiliado.mail" readonly="true" /></td>
			</tr>
			<tr>
				<td colspan='2'>
					<input type="button" value="Certificar" id="certificar" onclick="certificarAfiliado();"/>
				</td>
			</tr>	
		</table>			
		<div id="divPrestacion" style="display: none;">
			<c:if test="${not empty catPrestacion}">
				Prestacion:<form:hidden path="catPrestacion.prestacionId"/> ${catPrestacion.codigo} - ${catPrestacion.descripcion}
			</c:if>
			<c:if test="${not empty prestacionPrestador}">
				Prestacion:<form:hidden path="prestacionprestador.prestacionPrestadorId"/> ${prestacionPrestador.codigo} - ${prestacionPrestador.descripcion}
			</c:if>
			Medico:${agenda.usuarios.nombre}
			Consultorio:${agenda.consultorio}
			Hora:${agenda.horaCita}
			<input type="submit" value="Gaurdar"/>
			
		</div>	
			
	<div id="dialog-form" title="Acompañantes  o Personas de Confianza Registradas"> 
	</div>
	
	</form:form>
	<script type="text/javascript">
	
	function certificarAfiliado(){		
		var menorDeEdad = $("#menorDeEdad").val();
		var idAgenda = $("#idAgenda").val();
		if(menorDeEdad == "false"){
			if (IniciarUsuario(0, 0, idAgenda, $("#rfc").val(), $("#nombreUsuario").val(), $("#apellidoPaterno").val(), $("#apellidoMaterno").val(), $("#sexoId").val(), $("#fechaDeNacimiento").val(), $("#estado").val())) {
				$( "#divPrestacion" ).show();
				$( "#certificar" ).hide();
			}else{
				alert("no paso la autenticacion de huella");	
			}
		}else{
			var idAgenda = $("#idAgenda").val();
			$("#dialog-form").empty();
			$("#dialog-form").append("<object type='text/html' data='inicioPersonaConfianza?idAgenda="+idAgenda+"' width='100%' height='100%'></object>");
			dialog.dialog( "open" );
		}
		
	}
	
	$(function() {
		dialog = $("#dialog-form").dialog({
			autoOpen : false,
			height : 600,
			width : 600,
			modal : true
		});
	});
		
	</script>

</body>
</html>