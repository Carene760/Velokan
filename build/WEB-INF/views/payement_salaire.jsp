<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Payement salaire</title>
</head>
<body>
    <h2>Payement Salaire</h2>

    <!-- Affichage du message de retour -->
    <c:if test="${not empty retour}">
        <div style="color: green;">
            <strong>${retour}</strong>
        </div>
    </c:if>

    <form action="employe/t_payement_salaire" method="post">
        <div>
            <label for="id_employe">Employé</label>
            <select name="id_employe" id="id_employe" required>
                <option value="">-- Choisir un employé --</option>
                <c:forEach var="e" items="${employes}">
                    <option value="${e.id}">${e.nom} ${e.prenom} ($e.getNumIdentification())</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label for="montant">Montant à payer</label>
            <input type="number" name="montant" id="montant" placeholder="Saisir le montant à payer" required>
        </div>
        <div>
            <input type="submit" value="OK">
        </div>
    </form>
</body>
</html>