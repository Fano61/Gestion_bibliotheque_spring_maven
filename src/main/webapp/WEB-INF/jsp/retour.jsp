<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Retourner un livre</title></head>
<body>
    <h2>Formulaire de retour</h2>

    <form method="post" action="${pageContext.request.contextPath}/retour">
        ID Adhérent : <input type="text" name="idAdherent" required />
        ID Exemplaire : <input type="text" name="idExemplaire" required />
        <input type="submit" value="Retourner" />
    </form>

    <p style="color:blue">
        ${message}
    </p>
</body>
</html>
