<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Connexion Bibliothécaire</title></head>
<body>
<h2>Connexion</h2>
<form action="login" method="post">
    <label>Utilisateur :</label>
    <input type="text" name="username" required><br><br>
    <label>Mot de passe :</label>
    <input type="password" name="password" required><br><br>
    <input type="submit" value="Se connecter">
</form>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
</body>
</html>
