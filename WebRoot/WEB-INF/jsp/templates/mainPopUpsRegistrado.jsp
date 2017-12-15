<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>

<html>
<head>
    <title><tiles:getAsString name="title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_regusuario.css" rel="stylesheet">
      

</head>
<body style="overflow-y:hidden">
	<tiles:insertAttribute name="body" />
</body>
</html>