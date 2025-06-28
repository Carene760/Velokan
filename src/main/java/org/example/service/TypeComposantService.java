package org.example.service;

import org.example.entity.TypeComposant;
import org.example.repository.TypeComposantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeComposantService {

    private final TypeComposantRepository typeComposantRepository;

    public TypeComposantService(TypeComposantRepository typeComposantRepository) {
        this.typeComposantRepository = typeComposantRepository;
    }

    public TypeComposant saveOrUpdate(TypeComposant typeComposant) {
        return typeComposantRepository.save(typeComposant);
    }

    public List<TypeComposant> findAll() {
        return typeComposantRepository.findAll();
    }

    public Optional<TypeComposant> findById(Integer id) {
        return typeComposantRepository.findById(id);
    }

    public void deleteById(Integer id) {
        typeComposantRepository.deleteById(id);
    }
}