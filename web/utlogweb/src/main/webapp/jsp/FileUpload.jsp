<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Urban Terror file upload</title>
</head>
<body>
	Provide a single log file, or a zip archive containing several clog
	files
	<form action="<%=request.getContextPath()%>
		/UtLogUploadServlet"
		method="post" enctype="multipart/form-data">
		File (or zip archive) to analyze
		<input type="file" name="logfile" size="20" /> <br /> 
		(optional) alias file (cf sample here (TODO))
		<input type="file" name="alias.file" size="20" /> <br /> 
		(optional) or url to an alias file
		<input type="text" name="alias.url" size="20" /> <br /> 
		<input type="submit" value="Upload File" />
	</form>
</body>
</html>