package com.example.TurismoVuelaAlto.controller;

import com.example.TurismoVuelaAlto.dto.ActividadDTO;
import com.example.TurismoVuelaAlto.exception.ResourceNotFoundException;
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

    // Listar actividades
    @GetMapping
    public String listar(Model model) {
        List<ActividadDTO> actividades = actividadService.listarTodas();
        model.addAttribute("actividades", actividades);
        return "actividades/lista";
    }

    // Mostrar formulario para registrar
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("actividad", new ActividadDTO());
        model.addAttribute("destinos", destinoService.listarActivos());
        return "actividades/form";
    }

    // Guardar actividad
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("actividad") ActividadDTO actividad,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("destinos", destinoService.listarActivos());
            return "actividades/form";
        }
        try {
            actividadService.guardar(actividad);
            redirectAttributes.addFlashAttribute("mensaje", "Actividad registrada exitosamente");
        } catch (ResourceNotFoundException e) {
            model.addAttribute("destinos", destinoService.listarActivos());
            model.addAttribute("error", e.getMessage());
            return "actividades/form";
        } catch (Exception e) {
            model.addAttribute("destinos", destinoService.listarActivos());
            model.addAttribute("error", "Error al guardar la actividad: " + e.getMessage());
            return "actividades/form";
        }
        return "redirect:/actividades";
    }

    // Mostrar formulario para editar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<ActividadDTO> actividad = actividadService.buscarPorId(id);
            if (actividad.isPresent()) {
                model.addAttribute("actividad", actividad.get());
                model.addAttribute("destinos", destinoService.listarActivos());
                return "actividades/form";
            }
            redirectAttributes.addFlashAttribute("error", "Actividad no encontrada");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al buscar la actividad: " + e.getMessage());
        }
        return "redirect:/actividades";
    }

    // Actualizar actividad
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("actividad") ActividadDTO actividad,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("destinos", destinoService.listarActivos());
            return "actividades/form";
        }
        try {
            actividadService.actualizar(actividad);
            redirectAttributes.addFlashAttribute("mensaje", "Actividad actualizada exitosamente");
        } catch (ResourceNotFoundException e) {
            model.addAttribute("destinos", destinoService.listarActivos());
            model.addAttribute("error", e.getMessage());
            return "actividades/form";
        } catch (Exception e) {
            model.addAttribute("destinos", destinoService.listarActivos());
            model.addAttribute("error", "Error al actualizar la actividad: " + e.getMessage());
            return "actividades/form";
        }
        return "redirect:/actividades";
    }

    // Inactivar actividad
    @GetMapping("/inactivar/{id}")
    public String inactivar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            actividadService.inactivar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Actividad inactivada exitosamente");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al inactivar la actividad: " + e.getMessage());
        }
        return "redirect:/actividades";
    }

    // Activar actividad
    @GetMapping("/activar/{id}")
    public String activar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            actividadService.activar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Actividad activada exitosamente");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al activar la actividad: " + e.getMessage());
        }
        return "redirect:/actividades";
    }

    // Listar actividades por destino
    @GetMapping("/destino/{idDestino}")
    public String listarPorDestino(@PathVariable Long idDestino, Model model) {
        List<ActividadDTO> actividades = actividadService.listarActivasPorDestino(idDestino);
        model.addAttribute("actividades", actividades);
        return "actividades/lista";
    }

}
