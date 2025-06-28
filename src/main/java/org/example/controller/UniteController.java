package org.example.controller;

import org.example.entity.Unite;
import org.example.service.UniteService;
import org.example.service.ComposantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/unite")
public class UniteController {

    private final UniteService uniteService;
    private final ComposantService composantService;

    public UniteController(UniteService uniteService, ComposantService composantService) {
        this.uniteService = uniteService;
        this.composantService = composantService;
    }

    @GetMapping("/")
    public String showForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
        Unite unite = new Unite();
        if (id != null) {
            unite = uniteService.findById(id).orElse(new Unite());
        }
        model.addAttribute("unite", unite);
        model.addAttribute("unites", uniteService.findAll());
        return "unite";
    }

    @PostMapping("/save")
    public String saveUnite(
            @RequestParam("nom") String nom,
            @RequestParam("symbol") String symbol,
            @RequestParam(value = "id", required = false) Integer id,
            Model model) {

        Unite unite = (id != null) ? uniteService.findById(id).orElse(new Unite()) : new Unite();
        unite.setNom(nom);
        unite.setSymbol(symbol);

        uniteService.saveOrUpdate(unite);

        model.addAttribute("succes", "Unité enregistrée !");
        model.addAttribute("unites", uniteService.findAll());
        model.addAttribute("unite", new Unite());

        return "unite";
    }

    @GetMapping("/edit")
    public String editerUnite(@RequestParam("id") Integer id, Model model) {
        Unite unite = (id != null) ? uniteService.findById(id).orElse(new Unite()) : new Unite();

        model.addAttribute("unite", unite);
        model.addAttribute("unites", uniteService.findAll());
        return "unite";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id, Model model) {
        composantService.deleteByUniteId(id);
        uniteService.deleteById(id);
        model.addAttribute("succes", "Unité supprimée !");
        return "unite";
    }
}