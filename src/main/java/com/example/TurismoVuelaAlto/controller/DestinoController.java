package com.example.TurismoVuelaAlto.controller;

import com.example.TurismoVuelaAlto.dto.DestinoDTO;
import com.example.TurismoVuelaAlto.entity.DestinoEntity;
import com.example.TurismoVuelaAlto.exception.ResourceNotFoundException;
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

    // Listar destinos
    @GetMapping
    public String listar(Model model) {
        List<DestinoDTO> destinos = destinoService.listarTodos();
        model.addAttribute("destinos", destinos);
        return "destinos/lista";
    }

    // Mostrar formulario para registrar
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("destino", new DestinoDTO());
        return "destinos/form";
    }

    // Guardar destino
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("destino") DestinoDTO destino,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "destinos/form";
        }
        try {
            destinoService.guardar(destino);
            redirectAttributes.addFlashAttribute("mensaje", "Destino registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el destino: " + e.getMessage());
        }
        return "redirect:/destinos";
    }

    // Mostrar formulario para editar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<DestinoDTO> destino = destinoService.buscarPorId(id);
            if (destino.isPresent()) {
                model.addAttribute("destino", destino.get());
                return "destinos/form";
            }
            redirectAttributes.addFlashAttribute("error", "Destino no encontrado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al buscar el destino: " + e.getMessage());
        }
        return "redirect:/destinos";
    }

    // Actualizar destino
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("destino") DestinoDTO destino,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "destinos/form";
        }
        try {
            destinoService.actualizar(destino);
            redirectAttributes.addFlashAttribute("mensaje", "Destino actualizado exitosamente");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el destino: " + e.getMessage());
        }
        return "redirect:/destinos";
    }

    // Inactivar destino
    @GetMapping("/inactivar/{id}")
    public String inactivar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            destinoService.inactivar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Destino inactivado exitosamente");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al inactivar el destino: " + e.getMessage());
        }
        return "redirect:/destinos";
    }

    // Activar destino
    @GetMapping("/activar/{id}")
    public String activar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            destinoService.activar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Destino activado exitosamente");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al activar el destino: " + e.getMessage());
        }
        return "redirect:/destinos";
    }

    // Buscar destinos
    @GetMapping("/buscar")
    public String buscar(@RequestParam String nombre, Model model) {
        List<DestinoDTO> destinos = destinoService.buscarPorNombre(nombre);
        model.addAttribute("destinos", destinos);
        return "destinos/lista";
    }

    // Ver detalle del destino con actividades
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<DestinoEntity> destino = destinoService.obtenerConActividades(id);
            if (destino.isPresent()) {
                model.addAttribute("destino", destino.get());
                return "destinos/detalle";
            }
            redirectAttributes.addFlashAttribute("error", "Destino no encontrado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al obtener el detalle: " + e.getMessage());
        }
        return "redirect:/destinos";
    }

}
