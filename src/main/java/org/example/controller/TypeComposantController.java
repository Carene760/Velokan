package org.example.controller;

import org.example.entity.TypeComposant;
import org.example.service.TypeComposantService;
import org.example.service.ComposantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/type_composant")
public class TypeComposantController {

    private final TypeComposantService typeComposantService;
    private final ComposantService composantService;

    public TypeComposantController(TypeComposantService typeComposantService, ComposantService composantService) {
        this.typeComposantService = typeComposantService;
        this.composantService = composantService;
    }

    @GetMapping("/")
    public String showForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
        TypeComposant typeComposant = new TypeComposant();
        if (id != null) {
            typeComposant = typeComposantService.findById(id).orElse(new TypeComposant());
        }
        model.addAttribute("typeComposant", typeComposant);
        model.addAttribute("typesComposant", typeComposantService.findAll());
        return "type_composant";
    }

    @PostMapping("/save")
    public String saveTypeComposant(
            @RequestParam("nom") String nom,
            @RequestParam(value = "id", required = false) Integer id,
            Model model) {

        TypeComposant typeComposant = (id != null) ? typeComposantService.findById(id).orElse(new TypeComposant()) : new TypeComposant();
        typeComposant.setNom(nom);

        typeComposantService.saveOrUpdate(typeComposant);

        model.addAttribute("succes", "Type de composant enregistré !");
        model.addAttribute("typesComposant", typeComposantService.findAll());
        model.addAttribute("typeComposant", new TypeComposant());

        return "type_composant";
    }

    @GetMapping("/edit")
    public String editerTypeComposant(@RequestParam("id") Integer id, Model model) {
        TypeComposant typeComposant = (id != null) ? typeComposantService.findById(id).orElse(new TypeComposant()) : new TypeComposant();

        model.addAttribute("typeComposant", typeComposant);
        model.addAttribute("typesComposant", typeComposantService.findAll());
        return "type_composant";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id, Model model) {
        // 1. Supprimer les composants liés
        composantService.deleteByTypeComposantId(id); // À implémenter dans ComposantService
        
        // 2. Supprimer le type_composant
        typeComposantService.deleteById(id);
        
        model.addAttribute("succes", "Type de composant supprimé !");
        return "type_composant";
    }
}