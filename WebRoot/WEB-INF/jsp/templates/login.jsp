<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
    <title><tiles:getAsString name="title" /></title>
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="0">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.blockUI.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugin.autentia.js"></script>
	
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

</head>
<body>
    <div id="content">
       <tiles:insertAttribute name="body" />
    </div>
</body>
</html>