<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>

	<script type="text/javascript">
        
		$( document ).ready(function() {
			window.location=document.getElementById("foo").href;
       	});
        
	</script>
	
	<a href="citasPresenciales/recepcion" id="foo"></a>
</body>
</html>