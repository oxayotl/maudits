package com.maudits.website.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maudits.website.domain.Display;
import com.maudits.website.domain.form.FilmForm;
import com.maudits.website.service.BoFilmService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/{edition}/film")
public class BoFilmController {
	private final BoFilmService boFilmService;

	@ModelAttribute
	public void setAction(@PathVariable Display edition, Model model) {
		model.addAttribute("actionBaseUrl", "/admin/" + edition.name().toLowerCase() + "/film");
		model.addAttribute("back", "/admin/" + edition.name().toLowerCase() + "/dashboard");
	}

	@GetMapping("create/new")
	public String showFilmCreateForm(@PathVariable Display edition, Model model) {
		model.addAttribute("form", boFilmService.createFilmForm());
		return "admin/film-create-or-edit";
	}

	@GetMapping("edit/{id}")
	public String showFilmEditForm(@PathVariable Long id, Model model) {
		model.addAttribute("form", boFilmService.findFilmFormFromId(id));
		return "admin/film-create-or-edit";
	}

	@PostMapping("save-edit")
	public String saveFilmByForm(@PathVariable Display edition, @Validated @ModelAttribute("form") FilmForm form,
			BindingResult bindingResult) throws IOException {
		if (bindingResult.hasErrors()) {
			return "admin/film-create-or-edit";
		}
		boFilmService.saveWithoutPublishingFilm(edition, form);
		return "redirect:/admin/" + edition.name().toLowerCase() + "/dashboard";
	}

	@PostMapping("save-edit-publish")
	public String saveAndPublishFilmByForm(@PathVariable Display edition,
			@Validated @ModelAttribute("form") FilmForm form, BindingResult bindingResult) throws IOException {
		if (bindingResult.hasErrors()) {
			return "admin/film-create-or-edit";
		}
		boFilmService.saveAndPublishFilm(edition, form);
		return "redirect:/admin/" + edition.name().toLowerCase() + "/dashboard";
	}

	@PostMapping("delete")
	public String deleteFilm(@PathVariable Display edition, Long id) throws IOException {
		boFilmService.deleteFilm(id);
		return "redirect:/admin/" + edition.name().toLowerCase() + "/dashboard";
	}
}
