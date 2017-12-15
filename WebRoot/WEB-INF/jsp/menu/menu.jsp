<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	
	<div id="menu_interno">
		<div class="titulo_menu">MENÚ</div>
		<div class="pleca"></div>	
		<c:forEach items="${userInfo.menus}" var="menu">
			<div>
           		<a href="${pageContext.request.contextPath}${menu.url}" class="btn_flotante">		
					<img src="${pageContext.request.contextPath}/resources/img/menu/${menu.imagen}.png" alt="Cita" border="0" onMouseOver="this.src='${pageContext.request.contextPath}/resources/img/menu/${menu.imagen}_over.png';" onMouseOut="this.src='${pageContext.request.contextPath}/resources/img/menu/${menu.imagen}.png';" onclick="this.src='${pageContext.request.contextPath}/resources/img/menu/${menu.imagen}_clic.png';" /><h2>${menu.menu}</h2>
				</a>
    	    </div>		
		</c:forEach>
	</div>		

</body>
</html>