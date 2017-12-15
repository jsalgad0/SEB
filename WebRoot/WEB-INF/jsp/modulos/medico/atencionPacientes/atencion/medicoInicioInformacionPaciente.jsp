<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tam_fijos.css">

<script type="text/javascript">
function enviar(){
	$("#estadoForm").val($("#idEstado").val());
	$("#municipioForm").val($("#idMunicipio").val());
	$("#coloniaForm").val($("#idColonia").val());
	$("#ocupacionForm").val($("#idOcupacion").val());
	$("#informacionPacienteForm").submit();
}
$(function() {
	$("#fechaNacimiento").datepicker({
		changeMonth: true,
		changeYear: true,
		maxDate: 0
	});
	$("#calle").alphanum({
        allowSpace: true,
        disallow: '¥Á«ø®°∑'
    });
	$("#codigoPostal").numeric({
        allowSpace: false,
        disallow: '¥Á«ø®°∑'
    });
	$("#telefono").numeric({
        allowSpace: false,
        disallow: '¥Á«ø®°∑'
    });
	$("#mail").alphanum({
        allowSpace: false,
        allow: '@.-_',
        disallow: '¥Á«ø®°∑'
    });
	$("#nacionalidad").alphanum({
        allowSpace: true,
        disallow: '¥Á«ø®°∑'
    });
	$("#religion").alphanum({
        allowSpace: true,
        disallow: '¥Á«ø®°∑'
    });
	$("#nivelSocioEconomico").alphanum({
        allowSpace: true,
        disallow: '¥Á«ø®°∑'
    });
	$("#responsableNombre").alphanum({
        allowSpace: true,
        disallow: '¥Á«ø®°∑'
    });
	$("#responsableParentezco").alphanum({
        allowSpace: true,
        disallow: '¥Á«ø®°∑'
    });
	$("#responsableEdad").numeric({
        allowSpace: false,
        disallow: '¥Á«ø®°∑'
    });
	$("#responsableDireccion").alphanum({
        allowSpace: true,
        disallow: '¥Á«ø®°∑'
    });
	$("#responsableLugar").alphanum({
        allowSpace: true,
        disallow: '¥Á«ø®°∑'
    });
	$("#responsableTelefono").numeric({
        allowSpace: false,
        disallow: '¥Á«ø®°∑'
    });
});
</script>
<html>
<body style="overflow:hidden;">
<form:form name='informacionPacienteForm' method='POST' commandName="informacionPacienteForm" id="informacionPacienteForm" action="actualizaInformacionPaciente">
<div id="admin_contenido2">              
    <div class="linea_supervisor back_paciente margen_arriba3" style="height:72px;">
    	<div class="linea_supervisor margen_arriba4 derecho">
    		<div class="texto_supervisor4 verde margen5">
	          PACIENTE
	         </div>
	         <div class="texto_supervisor4 gris margen5 pad_derecho">
	          ${atencion.afiliado.nombre } ${atencion.afiliado.apellidoPaterno } ${atencion.afiliado.apellidoMaterno }
	         </div>
    	</div> 
   		<div class="linea_supervisor margen_arriba4">     
	         <div class="texto_supervisor verde margen tam_16">
	          Edad: 
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${signos.edad } aÒos
	         </div>
	         
	         <div class="texto_supervisor verde tam_16">
	          Peso:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${signos.peso } kg
	         </div>
			
	        <div class="texto_supervisor verde tam_1">
	          Altura:
	         </div>
	         
	         <div class="texto_supervisor gris tam_6">
	          ${signos.altura } cm
	         </div>
	         
	         <div class="texto_supervisor verde margen5 tam_15">
	          T.A.:
	         </div>
	         
	         <div class="texto_supervisor gris margen5 tam_6">
	          ${signos.tensionArterial }
	         </div>
	         
	         <div class="texto_supervisor verde tam_3">
	          ⁄ltima SomatometrÌa:
	         </div>
	         
	         <div class="texto_supervisor gris tam_4">
	          ${signos.fechaUltimaSomatometria }
	         </div>
		</div>
    </div>  
 
  <div class="admin_contenidoFrame">   
  
  <div class="linea_corta2">
   <div class="pleca_menu"></div>
	
	<div class="linea_supervisor margen margen_arriba">
   		<form:input path="fechaNacimiento" class="campo_supervisor tam_2" placeholder="Fecha de Nacimiento" id="fechaNacimiento" readonly="true" value="${signos.fechaNacimiento }" />
   		<div class="texto_supervisor5 gris tam_3 margen2">Sexo:
   			<c:choose>
   				<c:when test="${atencion.afiliado.catsexos.sexoId eq '1' }">
   					<div class="texto_supervisor5 gris tam_1 margen2">F
   						<form:radiobutton name="sexo" path="idSexo" value="2" />
		   			</div>	
			    	<div class="texto_supervisor5 gris tam_1">M
				    	<form:radiobutton name="sexo" path="idSexo" value="1" checked="checked" />
			    	</div>	
   				</c:when>
   				<c:otherwise>
   					<div class="texto_supervisor5 gris tam_1 margen2">F
						<form:radiobutton name="sexo" path="idSexo" value="2" checked="checked" />
		   			</div>	
			    	<div class="texto_supervisor5 gris tam_1">M
			    		<form:radiobutton name="sexo" value="1" path="idSexo"/>
			    	</div>
   				</c:otherwise>
   			</c:choose>
   			
   		</div>   		 
   </div>
	   
	<div class="linea_supervisor margen">
        <select id="idEstado" onchange="municipios();" class="selectt campo_supervisor tam_2" name="Temporal">
			<option value="-1" label="-Selecciona un Estado-" />
			<c:forEach items="${catEstados}" var="estado">
				<c:choose>
					<c:when test="${estado.estadoId eq atencion.afiliado.catestadosByEstadoId.estadoId }">
						<option value="${estado.estadoId }" selected="selected" >${estado.estado } </option>
					</c:when>
					<c:otherwise>
						<option value="${estado.estadoId }" >${estado.estado } </option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
        <select class="selectt campo_supervisor tam_2" name="Temporal" id="idMunicipio" onchange="colonias();">
			<option value="-1" label="-Selecciona una Del/Mun-" />
			<c:forEach items="${catMunicipios}" var="municipio">
				<c:choose>
					<c:when test="${municipio.municipioId eq atencion.afiliado.catmunicipios.municipioId }">
						<option value="${municipio.municipioId }" selected="selected" >${municipio.municipio } </option>
					</c:when>
					<c:otherwise>
						<option value="${municipio.municipioId }" >${municipio.municipio } </option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
        <select id="idColonia" class="selectt campo_supervisor tam_2" name="Temporal">
			<option value="-1" label="-Selecciona una Colonia-" />
			<c:forEach items="${catColonias}" var="colonia">
				<c:choose>
					<c:when test="${colonia.coloniaId eq atencion.afiliado.catcolonias.coloniaId }">
						<option value="${colonia.coloniaId }" selected="selected" >${colonia.colonia } </option>
					</c:when>
					<c:otherwise>
						<option value="${colonia.coloniaId }" >${colonia.colonia } </option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>                 
	</div>  
	<div class="linea_supervisor margen">
   		<form:input path="calle" id="calle" class="campo_supervisor tam_20" maxlength="200" placeholder="DirecciÛn" value="${atencion.afiliado.calle }" /> 
   		<form:input path="codigoPostal" id="codigoPostal" class="campo_supervisor tam_4" maxlength="11" placeholder="CÛdigo Postal" value="${atencion.afiliado.cp }" />  
    </div>
    
    <div class="linea_supervisor margen">
   		<form:input path="telefono" id="telefono" class="campo_supervisor tam_2" placeholder="TelÈfono" maxlength="15" value="${atencion.afiliado.telefono1 }" /> 
   		<form:input path="mail" id="mail" class="campo_supervisor tam_2" placeholder="Email" maxlength="100" value="${atencion.afiliado.mail }" />
   		<select id="idOcupacion" class="selectt campo_supervisor tam_2" name="Ocupacion">
			<option value="-1" label="-Selecciona una Ocupacion-" />
			<c:forEach items="${catOcupacion}" var="ocupacion">
				<c:choose>
					<c:when test="${ocupacion.ocupacionId eq informacionPaciente.idOcupacion }">
						<option value="${ocupacion.ocupacionId }" selected="selected" >${ocupacion.descripcion } </option>
					</c:when>
					<c:otherwise>
						<option value="${ocupacion.ocupacionId }" >${ocupacion.descripcion } </option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
   		 
   </div>

	<div class="linea_supervisor margen">
   		<form:input path="nacionalidad" id="nacionalidad" class="campo_supervisor tam_2" maxlength="50" placeholder="Nacionalidad" value="${informacionPaciente.nacionalidad }" /> 
   		<form:input path="religion" id="religion" class="campo_supervisor tam_2" maxlength="50" placeholder="ReligiÛn" value="${informacionPaciente.religion }" /> 
   		<form:input path="nivelSocioEconomico" id="nivelSocioEconomico" class="campo_supervisor tam_2" maxlength="50" placeholder="Nivel Socio-EconÛmico" value="${informacionPaciente.nivelSocioeconomico }" />   
    </div>
    
    <div class="linea_supervisor margen margen_arriba2">
   		<form:input path="responsableNombre" id="responsableNombre" class="campo_supervisor tam_2" maxlength="100" placeholder="Nombre Responsable" value="${informacionPaciente.nombreResponsable }" /> 
   		<form:input path="responsableParentezco" id="responsableParentezco" class="campo_supervisor tam_2" maxlength="50" placeholder="Parentesco" value="${informacionPaciente.parentescoResponsable }" />
   		<c:choose>
   			<c:when test="${informacionPaciente.edadResponsable eq 0 }">
   				<form:input path="responsableEdad" id="responsableEdad" class="campo_supervisor tam_3" maxlength="3" placeholder="Edad Responsable" />		
   			</c:when>
   			<c:otherwise>
   				<form:input path="responsableEdad" id="responsableEdad" class="campo_supervisor tam_3" maxlength="3" placeholder="Edad Responsable" value="${informacionPaciente.edadResponsable }" />
   			</c:otherwise>
   		</c:choose>   		   
    </div>
    
    <div class="linea_supervisor margen">
   		<form:input path="responsableDireccion" id="responsableDireccion" class="campo_supervisor tam_20" maxlength="100" placeholder="DirecciÛn Responsable" value="${informacionPaciente.direccionResponsable }" /> 
   		<form:input path="responsableLugar" id="responsableLugar" class="campo_supervisor tam_2" maxlength="100" placeholder="Lugar Responsable" value="${informacionPaciente.lugarResponsable }" />   
    </div>
    
     <div class="linea_supervisor margen">
   		<form:input path="responsableTelefono" id="responsableTelefono" class="campo_supervisor tam_2" maxlength="20" placeholder="TelÈfono Responsable" value="${informacionPaciente.telefonoResponsable }" />   
    </div>

     <div class="linea_supervisor margen margen_arriba2">
     		<form:hidden path="idAtencionMedica" value="${atencion.atencionMedicaId }"/>
     		<form:hidden path="idEstado" id="estadoForm" value="-1"/>
     		<form:hidden path="idMunicipio" id="municipioForm" value="-1"/>
     		<form:hidden path="idColonia" id="coloniaForm" value="-1"/>
     		<form:hidden path="idOcupacion" id="ocupacionForm" value="-1"/>
     		<div class="btn btn_guardar" onclick="enviar();"></div>
            <div class="btn btn_cerrar"></div>
     </div>
  </div>  
 </div>    
</div>
</form:form>
</body>
</html>