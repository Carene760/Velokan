package org.example.repository;

import org.example.entity.Composant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ComposantRepository extends JpaRepository<Composant, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Composant c WHERE c.typeComposant.id = :typeId")
    void deleteByTypeComposantId(@Param("typeId") Integer typeId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Composant c WHERE c.unite.id = :uniteId")
    void deleteByUniteId(@Param("uniteId") Integer uniteId);
}