<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<body>
	<table>
		<c:forEach items="${licenciaMedicaForm.licenciamedicas}" var="licenciaMedica">
			<tr>
				<td>
					${licenciaMedica.folio}	
				</td>
				<td>
					${licenciaMedica.fechaInicio}
				</td>				
				<td>
					${licenciaMedica.fechaTermino}	
				</td>
				<td>
					${licenciaMedica.numDias}		
				</td>				
			</tr>
		</c:forEach>							
	</table>
</body>
</html>