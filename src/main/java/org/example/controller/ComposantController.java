package org.example.controller;

import org.example.entity.Composant;
import org.example.entity.TypeComposant;
import org.example.entity.Unite;
import org.example.service.ComposantService;
import org.example.service.TypeComposantService;
import org.example.service.UniteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/composant")
public class ComposantController {

    private final ComposantService composantService;
    private final TypeComposantService typeComposantService;
    private final UniteService uniteService;

    public ComposantController(ComposantService composantService, 
                             TypeComposantService typeComposantService,
                             UniteService uniteService) {
        this.composantService = composantService;
        this.typeComposantService = typeComposantService;
        this.uniteService = uniteService;
    }

    @GetMapping("/")
    public String showForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
        Composant composant = new Composant();
        if (id != null) {
            composant = composantService.findById(id).orElse(new Composant());
        }
        model.addAttribute("composant", composant);
        model.addAttribute("composants", composantService.findAll());
        model.addAttribute("typesComposant", typeComposantService.findAll());
        model.addAttribute("unites", uniteService.findAll());
        return "composant";
    }

    @PostMapping("/save")
    public String saveComposant(
            @RequestParam("nom") String nom,
            @RequestParam("idType") Integer idType,
            @RequestParam("idUnite") Integer idUnite,
            @RequestParam(value = "id", required = false) Integer id,
            Model model) {

        Composant composant = (id != null) ? composantService.findById(id).orElse(new Composant()) : new Composant();
        composant.setNom(nom);
        composant.setTypeComposant(typeComposantService.findById(idType).orElse(null));
        composant.setUnite(uniteService.findById(idUnite).orElse(null));

        composantService.saveOrUpdate(composant);

        model.addAttribute("succes", "Composant enregistré !");
        model.addAttribute("composants", composantService.findAll());
        model.addAttribute("composant", new Composant());
        model.addAttribute("typesComposant", typeComposantService.findAll());
        model.addAttribute("unites", uniteService.findAll());

        return "composant";
    }

    @GetMapping("/edit")
    public String editerComposant(@RequestParam("id") Integer id, Model model) {
        Composant composant = (id != null) ? composantService.findById(id).orElse(new Composant()) : new Composant();

        model.addAttribute("composant", composant);
        model.addAttribute("composants", composantService.findAll());
        model.addAttribute("typesComposant", typeComposantService.findAll());
        model.addAttribute("unites", uniteService.findAll());
        return "composant";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id, Model model) {
        composantService.deleteById(id);
        model.addAttribute("succes", "Composant supprimé !");
        return "composant";
    }
}