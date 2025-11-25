package com.example.TurismoVuelaAlto.controller;

import com.example.TurismoVuelaAlto.entity.ActividadEntity;
import com.example.TurismoVuelaAlto.service.ActividadService;
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
@RequestMapping("/actividades")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private DestinoService destinoService;

    // RF10.6 - Listar actividades
    @GetMapping
    public String listar(Model model) {
        List<ActividadEntity> actividades = actividadService.listarActivas();
        model.addAttribute("actividades", actividades);
        return "actividades/lista";
    }

    // RF10.5 - Mostrar formulario para registrar
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("actividad", new ActividadEntity());
        model.addAttribute("destinos", destinoService.listarActivos());
        return "actividades/form";
    }

    // RF10.5 - Guardar actividad
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("actividad") ActividadEntity actividad,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("destinos", destinoService.listarActivos());
            return "actividades/form";
        }
        actividadService.guardar(actividad);
        redirectAttributes.addFlashAttribute("mensaje", "Actividad registrada exitosamente");
        return "redirect:/actividades";
    }

    // RF10.7 - Mostrar formulario para editar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<ActividadEntity> actividad = actividadService.buscarPorId(id);
        if (actividad.isPresent()) {
            model.addAttribute("actividad", actividad.get());
            model.addAttribute("destinos", destinoService.listarActivos());
            return "actividades/form";
        }
        return "redirect:/actividades";
    }

    // RF10.7 - Actualizar actividad
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("actividad") ActividadEntity actividad,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("destinos", destinoService.listarActivos());
            return "actividades/form";
        }
        actividadService.actualizar(actividad);
        redirectAttributes.addFlashAttribute("mensaje", "Actividad actualizada exitosamente");
        return "redirect:/actividades";
    }

    // RF10.8 - Inactivar actividad
    @GetMapping("/inactivar/{id}")
    public String inactivar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        actividadService.inactivar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Actividad inactivada exitosamente");
        return "redirect:/actividades";
    }

    // RF10.6 - Listar actividades por destino
    @GetMapping("/destino/{idDestino}")
    public String listarPorDestino(@PathVariable Long idDestino, Model model) {
        List<ActividadEntity> actividades = actividadService.listarActivasPorDestino(idDestino);
        model.addAttribute("actividades", actividades);
        return "actividades/lista";
    }

}
