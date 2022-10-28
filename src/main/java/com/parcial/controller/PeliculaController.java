
package com.parcial.controller;

import com.parcial.entity.Pelicula;
import com.parcial.service.IPeliculaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PeliculaController {
    
    @Autowired
    private IPeliculaService peliculaService;
    
    @GetMapping("/pelicula")
     public String index(Model model) {
        List<Pelicula> listaPelicula = peliculaService.getAllPelicula();
        model.addAttribute("titulo", "Tabla Peliculas");
        model.addAttribute("Pelicula", listaPelicula);
        return "peliculas";
    } 
     
    @GetMapping("/peliculaN")
    public String crearPelicula(Model model) {
        List<Pelicula> listaPelicula = peliculaService.getAllPelicula();
        model.addAttribute("pelicula", new Pelicula());
        model.addAttribute("peliculas", listaPelicula);
        return "crear";
    }
    @PostMapping("/save")
    public String guardarPelicula(@ModelAttribute Pelicula pelicula) {
        peliculaService.savePelicula(pelicula);
        return "redirect:/pelicula";
    }
    @GetMapping("/editPelicula/{id}")
    public String editarPelicula(@PathVariable("id") Long idPelicula, Model model) {
        Pelicula pelicula = peliculaService.getPeliculaById(idPelicula);
        List<Pelicula> listaEstados= peliculaService.getAllPelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("peliculas", listaEstados);
        return "crear";
    }
    @GetMapping("/delete/{id}")
    public String eliminarEstado(@PathVariable("id") Long idPelicula) {
        peliculaService.delete(idPelicula);
        return "redirect:/pelicula";
    }
     

}
