<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Gestion des Unités</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .success { color: green; }
    </style>
</head>
<body>
    <h1>Gestion des Unités</h1>

    <c:if test="${not empty succes}">
        <p class="success">${succes}</p>
    </c:if>

    <form action="save" method="post">
        <input type="hidden" name="id" value="${unite.id}">
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" value="${unite.nom}" required>
        
        <label for="symbol">Symbole:</label>
        <input type="text" id="symbol" name="symbol" value="${unite.symbol}" required maxlength="5">
        
        <button type="submit">Enregistrer</button>
    </form>

    <h2>Liste des Unités</h2>
    <c:if test="${empty unites}">
        <p>Aucun unité disponible.</p>
    </c:if>
    <table>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Symbole</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${unites}" var="unite">
            <tr>
                <td>${unite.id}</td>
                <td>${unite.nom}</td>
                <td>${unite.symbol}</td>
                <td>
                    <a href="edit?id=${unite.id}">Modifier</a> |
                    <a href="delete?id=${unite.id}" onclick="return confirm('Êtes-vous sûr?')">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}"><< retour</a>
</body>
</html>