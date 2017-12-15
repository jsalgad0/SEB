<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<script type="text/javascript">
$(document).ready(function(){
	var ta 	= $("#tensionArterial").val();
	ta = ta.split("/");
	$("#ta1").val(ta[0]);
	$("#ta2").val(ta[1]);
	
	var form = document.getElementById('historiaClincaForm');
	form.addEventListener('submit', function(event) {
		$("#tensionArterial").val($("#ta1").val()+"/"+$("#ta2").val());
	});
});
</script>
<body>	
	<form:form method="post" action="registro"
				commandName="historiaClincaForm">
		<span>
			<form:errors path="error"></form:errors>
		</span>
		<table>
			<tr style="font-size: small;">
				<td>
					Diabete Mellitus <form:checkbox path="hcAntecedentesfamiliares.diabeteMellitus"/>
				</td>
				<td>
					Hipertension Arterial <form:checkbox path="hcAntecedentesfamiliares.hipertensionArterial"/>
				</td>
				<td>
					Neoplasias <form:checkbox path="hcAntecedentesfamiliares.neoplasias"/>
				</td>
				<td>
					Cardiopatias <form:checkbox path="hcAntecedentesfamiliares.cardiopatias"/>
				</td>
				<td>
					Alergias <form:checkbox path="hcAntecedentesfamiliares.alergias"/>
				</td>
				<td>
					Obesidad <form:checkbox path="hcAntecedentesfamiliares.obesidad"/>
				</td>
				<td>
					Tuberculosis <form:checkbox path="hcAntecedentesfamiliares.tuberculosis"/>
				</td>
				<td>
					Tabaquismo <form:checkbox path="hcAntecedentesfamiliares.tabaquismo"/>
				</td>
				<td>
					Alcoholismo <form:checkbox path="hcAntecedentesfamiliares.alcoholismo"/>
				</td>
				<td>
					Dependencia A Droga <form:checkbox path="hcAntecedentesfamiliares.dependenciaAdroga"/>
				</td>
				<td>
					Dependencia A Medicamentos <form:checkbox path="hcAntecedentesfamiliares.dependenciaAmedicamentos"/>
				</td>
				<td>
					Malformaciones <form:checkbox path="hcAntecedentesfamiliares.malformaciones"/>
				</td>
				<td>
					Disfunciones Familiares <form:checkbox path="hcAntecedentesfamiliares.disfuncionesFamiliares"/>
				</td>
				<td>
					Quirurgias <form:checkbox path="hcAntecedentesfamiliares.quirurgias"/>
				</td>
				<td>
					Otras <form:input path="hcAntecedentesfamiliares.otras"/>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr><td>Estado Civil</td></tr>
						<tr><td><form:radiobuttons path="idEstadoCivil" items="${estadoCivil}" itemLabel="descripcion" itemValue="estadoCivilId" /></td></tr>
					</table>					
				</td>
				<td>
					Escolaridad <form:radiobuttons path="idEscolaridad" items="${escolaridad}" itemLabel="descripcion" itemValue="escolaridadId" />
				</td>
				<td>
					Alimentacion <form:radiobuttons path="idAlimentacion" items="${alimentacion}" itemLabel="descripcion" itemValue="alimentacionId" />
				</td>
				<td>
					Higiene <form:radiobuttons path="idHigiene" items="${higiene}" itemLabel="descripcion" itemValue="higieneId" />
				</td>
			</tr>
			<tr style="font-size: small;">
				<td>
					Diabete Mellitus <form:checkbox path="hcAntecedentespersonal.diabeteMellitusPer"/>
				</td>
				<td>
					Hipertension Arterial <form:checkbox path="hcAntecedentespersonal.hipertensionArterialPer"/>
				</td>
				<td>
					Neoplasias <form:checkbox path="hcAntecedentespersonal.neoplasiasPer"/>
				</td>
				<td>
					Cardiopatias <form:checkbox path="hcAntecedentespersonal.cardiopatiasPer"/>
				</td>
				<td>
					Alergias <form:checkbox path="hcAntecedentespersonal.alergiasPer"/>
				</td>
				<td>
					Obesidad <form:checkbox path="hcAntecedentespersonal.obesidadPer"/>
				</td>
				<td>
					Tuberculosis <form:checkbox path="hcAntecedentespersonal.tuberculosisPer"/>
				</td>
				<td>
					Tabaquismo <form:checkbox path="hcAntecedentespersonal.tabaquismoPer"/>
				</td>
				<td>
					Alcoholismo <form:checkbox path="hcAntecedentespersonal.alcoholismoPer"/>
				</td>
				<td>
					Dependencia A Droga <form:checkbox path="hcAntecedentespersonal.dependencuAdrogaPer"/>
				</td>
				<td>
					Dependencia A Medicamentos <form:checkbox path="hcAntecedentespersonal.dependenciaAmedicamentosPer"/>
				</td>
				<td>
					Malformaciones <form:checkbox path="hcAntecedentespersonal.malformacionesPer"/>
				</td>
				<td>
					Disfunciones Familiares <form:checkbox path="hcAntecedentespersonal.disfuncionesFamiliaresPer"/>
				</td>
				<td>
					Quirurgias <form:checkbox path="hcAntecedentespersonal.quirurgiasPer"/>
				</td>
				<td>
					Otras <form:input path="hcAntecedentespersonal.otrasPer"/>
				</td>
			</tr>
			<tr>
				<td>
					MPF <form:radiobuttons path="idMpf" items="${mpf}" itemLabel="descripcion" itemValue="mpfId" />
				</td>
				<td>
					Menarca <form:input path="hcGineco.menarca"/>
				</td>
				<td>
					Ritmo <form:input path="hcGineco.ritmo"/>
				</td>
				<td>
					VSA <form:input path="hcGineco.vsa"/>
				</td>
				<td>
					FUR <form:input path="hcGineco.fur"/>
				</td>
				<td>
					G <form:input path="hcGineco.g"/>
				</td>
				<td>
					P <form:input path="hcGineco.p"/>
				</td>
				<td>
					A <form:input path="hcGineco.a"/>
				</td>
				<td>
					C <form:input path="hcGineco.c"/>
				</td>
			</tr>
			<tr>
				<td>
					Peso <form:input path="signosvitales.peso"/>
				</td>
				<td>
					Altura <form:input path="signosvitales.altura"/>
				</td>
				<td>
					TA
					<input type="text" id="ta1" style="width: 65px;"/>
					/
					<input type="text" id="ta2" style="width: 65px;"/> 					
				</td>
				<td>
					Temperatura <form:input path="signosvitales.temperatura"/>
				</td>
			</tr>
			<tr>
				<td>
					Cabeza <form:checkbox path="hcExploracionfisica.cabeza"/>
				</td>
				<td>
					Cuello <form:checkbox path="hcExploracionfisica.cuello"/>
				</td>
				<td>
					Torax <form:checkbox path="hcExploracionfisica.torax"/>
				</td>
				<td>
					Abdomen <form:checkbox path="hcExploracionfisica.abdomen"/>
				</td>
				<td>
					Extremidades <form:checkbox path="hcExploracionfisica.extremidades"/>
				</td>
				<td>
					Sistema Nervioso <form:checkbox path="hcExploracionfisica.sistemaNervioso"/>
				</td>
				<td>
					Sistema Cardio <form:checkbox path="hcExploracionfisica.sistemaCardio"/>
				</td>
				<td>
					Aparato Digestivo <form:checkbox path="hcExploracionfisica.aparatoDigestivo"/>
				</td>
				<td>
					Sistema Muscular <form:checkbox path="hcExploracionfisica.sistemaMusculo"/>
				</td>
				<td>
					Otras (Especifique) <form:input path="hcExploracionfisica.otrasEf"/>
				</td>
			</tr>
			<tr>
				<td>
					Matrimonio <form:checkbox path="hcFasedelciclo.matrimonio"/>
				</td>
				<td>
					Expansion <form:checkbox path="hcFasedelciclo.expansion"/>
				</td>
				<td>
					Dispersion <form:checkbox path="hcFasedelciclo.dispersion"/>
				</td>
				<td>
					Independencia <form:checkbox path="hcFasedelciclo.independencia"/>
				</td>
				<td>
					Retiro Muerte <form:checkbox path="hcFasedelciclo.retiroMuerte"/>
				</td>
				<td>
					Tipo de Failia <form:radiobuttons path="idTipoFamilia" items="${familia}" itemLabel="descripcion" itemValue="tipoFamiliaId" />
				</td>
			</tr>
		</table>
		<form:hidden path="signosvitales.tensionArterial" id="tensionArterial"/>
		<form:hidden path="idAtencion" id="idAtencion" value="${idAtencion }" />
		<form:hidden path="idAgenda" id="idAgenda" value="${historiaClincaForm.idAgenda }" />
		<input type="submit" value="guardar"/>
		<a href="/sab/listaPacientes/atencionMedica?idAgenda=${historiaClincaForm.idAgenda}">Regresar</a>
	</form:form>
	<a href="/SAB/menu" >Regresar al Menu</a>
</body>
</html>