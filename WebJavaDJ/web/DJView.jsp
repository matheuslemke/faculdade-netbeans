<%-- 
    Document   : DJView
    Created on : 13/08/2015, 08:08:58
    Author     : matheus
--%>

<jsp:useBean id="beatModel" scope="application" class="model.BeatModel" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DJ View</title>
    </head>
    <body>
        <h1>DJ View</h1>
        Beats per minutes = <jsp:getProperty name="beatModel" property="BPM" />
        <br/>
        <hr/>
        <br/>
        <form method="post" action="DJController">
            BPM: <input type="text" name="bpm"
                        value="<jsp:getProperty name="beatModel" property="BPM" />" />
            &nbsp;
            <input type="submit" name="set" value="set"/> <br/>
            <input type="submit" name="decrease" value="<<"/>
            <input type="submit" name="increase" value=">>"/> <br/>
            <input type="submit" name="on" value="on"/>
            <input type="submit" name="off" value="off"/> <br/>
        </form>
    </body>
</html>
