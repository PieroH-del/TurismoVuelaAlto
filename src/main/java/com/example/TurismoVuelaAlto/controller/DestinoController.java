package com.example.TurismoVuelaAlto.controller;

import com.example.TurismoVuelaAlto.entity.DestinoEntity;
import com.example.TurismoVuelaAlto.service.DestinoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    // RF10.2 - Listar destinos
    @GetMapping
    public String listar(Model model) {
        List<DestinoEntity> destinos = destinoService.listarActivos();
        model.addAttribute("destinos", destinos);
        return "destinos/lista";
    }

    // RF10.1 - Mostrar formulario para registrar
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("destino", new DestinoEntity());
        return "destinos/form";
    }

    // RF10.1 - Guardar destino
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("destino") DestinoEntity destino,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "destinos/form";
        }
        destinoService.guardar(destino);
        redirectAttributes.addFlashAttribute("mensaje", "Destino registrado exitosamente");
        return "redirect:/destinos";
    }

    // RF10.3 - Mostrar formulario para editar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<DestinoEntity> destino = destinoService.buscarPorId(id);
        if (destino.isPresent()) {
            model.addAttribute("destino", destino.get());
            return "destinos/form";
        }
        return "redirect:/destinos";
    }

    // RF10.3 - Actualizar destino
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("destino") DestinoEntity destino,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "destinos/form";
        }
        destinoService.actualizar(destino);
        redirectAttributes.addFlashAttribute("mensaje", "Destino actualizado exitosamente");
        return "redirect:/destinos";
    }

    // RF10.4 - Inactivar destino
    @GetMapping("/inactivar/{id}")
    public String inactivar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        destinoService.inactivar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Destino inactivado exitosamente");
        return "redirect:/destinos";
    }

    // RF10.9 - Buscar destinos
    @GetMapping("/buscar")
    public String buscar(@RequestParam String nombre, Model model) {
        List<DestinoEntity> destinos = destinoService.buscarPorNombre(nombre);
        model.addAttribute("destinos", destinos);
        return "destinos/lista";
    }

    // RF10.10 - Ver detalle del destino con actividades
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        Optional<DestinoEntity> destino = destinoService.obtenerConActividades(id);
        if (destino.isPresent()) {
            model.addAttribute("destino", destino.get());
            return "destinos/detalle";
        }
        return "redirect:/destinos";
    }

}
