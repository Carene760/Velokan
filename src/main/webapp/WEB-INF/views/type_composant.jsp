<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Gestion des Types de Composant</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .success { color: green; }
    </style>
</head>
<body>
    <h1>Gestion des Types de Composant</h1>

    <c:if test="${not empty succes}">
        <p class="success">${succes}</p>
    </c:if>

    <form action="save" method="post">
        <input type="hidden" name="id" value="${typeComposant.id}">
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" value="${typeComposant.nom}" required>
        <button type="submit">Enregistrer</button>
    </form>

    <h2>Liste des Types de Composant</h2>
    <c:if test="${empty typesComposant}">
        <p>Aucun type de composant disponible.</p>
    </c:if>
    <table>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${typesComposant}" var="type">
            <tr>
                <td>${type.id}</td>
                <td>${type.nom}</td>
                <td>
                    <a href="edit?id=${type.id}">Modifier</a> |
                    <a href="delete?id=${type.id}" onclick="return confirm('Êtes-vous sûr?')">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}"><< retour</a>
</body>
</html>