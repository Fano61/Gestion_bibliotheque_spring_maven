<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Emprunter un livre</title></head>
<body>
    <h2>Formulaire d’emprunt</h2>

    <form method="post" action="${pageContext.request.contextPath}/emprunt">
  ID Adhérent : <input type="text" name="idAdherent" />
  ID Exemplaire : <input type="text" name="idExemplaire" />
  <input type="submit" value="Emprunter" />
</form>

    <p style="color:blue">
        ${message}
    </p>

    <p><a href="${pageContext.request.contextPath}/retour">Retourner un livre</a></p>
    <p><a href="${pageContext.request.contextPath}/dashboard">Voir le dashboard</a></p>
</body>
</html>
