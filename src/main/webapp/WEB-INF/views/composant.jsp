<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Gestion des Composants</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .success { color: green; }
    </style>
</head>
<body>
    <h1>Gestion des Composants</h1>

    <c:if test="${not empty succes}">
        <p class="success">${succes}</p>
    </c:if>

    <form action="save" method="post">
        <input type="hidden" name="id" value="${composant.id}">
        
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" value="${composant.nom}" required>
        
        <label for="idType">Type de Composant:</label>
        <select id="idType" name="idType" required>
            <option value="">-- Sélectionnez un type --</option>
            <c:forEach items="${typesComposant}" var="type">
                <option value="${type.id}" ${composant.typeComposant != null && composant.typeComposant.id == type.id ? 'selected' : ''}>
                    ${type.nom}
                </option>
            </c:forEach>
        </select>
        
        <label for="idUnite">Unité:</label>
        <select id="idUnite" name="idUnite" required>
            <option value="">-- Sélectionnez une unité --</option>
            <c:forEach items="${unites}" var="unite">
                <option value="${unite.id}" ${composant.unite != null && composant.unite.id == unite.id ? 'selected' : ''}>
                    ${unite.symbol} (${unite.nom})
                </option>
            </c:forEach>
        </select>
        
        <button type="submit">Enregistrer</button>
    </form>

    <h2>Liste des Composants</h2>
    <c:if test="${empty composants}">
        <p>Aucun composant disponible.</p>
    </c:if>
    <table>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Type</th>
            <th>Unité</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${composants}" var="comp">
            <tr>
                <td>${comp.id}</td>
                <td>${comp.nom}</td>
                <td>${comp.typeComposant.nom}</td>
                <td>${comp.unite.symbol} (${comp.unite.nom})</td>
                <td>
                    <a href="edit?id=${comp.id}">Modifier</a> |
                    <a href="delete?id=${comp.id}" onclick="return confirm('Êtes-vous sûr?')">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}"><< retour</a>
    <a href="${pageContext.request.contextPath}/type_composant/">creer un nouveau type de composant</a>
    <a href="${pageContext.request.contextPath}/unite/">ajouter une nouvelle unité</a>
</body>
</html>