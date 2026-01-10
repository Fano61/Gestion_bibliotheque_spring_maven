<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Dashboard</title></head>
<body>
<h2>Statistiques</h2>

<h2>📚 Livres les plus empruntés</h2>
<c:choose>
    <c:when test="${not empty topLivres}">
        <ul>
            <c:forEach var="livre" items="${topLivres}">
                <li>${livre}</li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <p>Aucune donnée à afficher.</p>
    </c:otherwise>
</c:choose>

<h2>👤 Adhérents les plus actifs</h2>
<c:choose>
    <c:when test="${not empty topAdherents}">
        <ul>
            <c:forEach var="adh" items="${topAdherents}">
                <li>${adh}</li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <p>Aucune donnée à afficher.</p>
    </c:otherwise>
</c:choose>


<h3>📊 Emprunts par type d’adhérent</h3>
<ul>
  <c:forEach var="profil" items="${profils}">
    <li>${profil.type_adherent} : ${profil.total} emprunts</li>
  </c:forEach>
</ul>

<h3>⏰ Taux de retard</h3>
<p>${tauxRetard}% des emprunts sont en retard.</p>

</body>
</html>
