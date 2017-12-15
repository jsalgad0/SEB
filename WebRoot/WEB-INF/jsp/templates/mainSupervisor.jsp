<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>

<html>
<head>
    <title><tiles:getAsString name="title" /></title>
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="0">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.alphanum.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.paginate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/genericScript.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/getinfo_autentia.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" media="screen"/>
    
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/supervisor.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/botones.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estilos_1.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/popup_generales.css">

	<script src="${pageContext.request.contextPath}/resources/reloj_archivos/coolclock.js" type="text/javascript"></script><!-- reloj-->
	<script src="${pageContext.request.contextPath}/resources/reloj_archivos/moreskins.js" type="text/javascript"></script><!-- reloj-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/reloj_archivos/excanvas.js"></script> <!--IE reloj-->
	
  	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script><!--calendario datepicker -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tamanio_datepicker.css">	
	
	<!-- Add mousewheel plugin (this is optional) -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/lib/jquery.mousewheel-3.0.6.pack.js"></script>
	
	<!-- Add fancyBox -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/jquery.fancybox.css" type="text/css" media="screen" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/jquery.fancybox.pack.js"></script>
	
	<!-- Optionally add helpers - button, thumbnail and/or media -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-buttons.css" type="text/css" media="screen" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-buttons.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-media.js"></script>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-thumbs.css" type="text/css" media="screen" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancyBox/fancyapps-fancyBox/source/helpers/jquery.fancybox-thumbs.js"></script>	

	<script type="text/javascript">
		function deshabilitarBack(){
			history.pushState(null, null, location.href);
			window.onpopstate = function(event) {
			    history.go(1);
			};
		}
	</script>
</head>
<body style="overflow:hidden;" onload="deshabilitarBack()">
	<div id="main_wrapper">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />           
 		<tiles:insertAttribute name="body" />
	</div>
</body>
</html>