package com.purihuaman.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.purihuaman.dto.EditorialDTO;
import com.purihuaman.service.EditorialService;

@RestController
@RequestMapping(name = "/editorials", produces = MediaType.APPLICATION_JSON_VALUE)
public class EditorialController {
	private final EditorialService editorialService;

	public EditorialController(EditorialService service) {
		this.editorialService = service;
	}

	public ResponseEntity<List<EditorialDTO>> findAllEditorials(@RequestParam Map<String, String> keywords) {
		short offset = keywords.containsKey("offset") ? Short.parseShort(keywords.get("offset")) : 0;
		short limit = keywords.containsKey("offset") ? Short.parseShort(keywords.get("limit")) : 10;

		Pageable page = PageRequest.of(offset, limit);

		List<EditorialDTO> result = editorialService.findAllEditorials(page);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EditorialDTO> findEditorialById(@PathVariable("id") String id) {
		EditorialDTO result = editorialService.findEditorialById(id);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping
	public ResponseEntity<EditorialDTO> createEditorial(@RequestBody EditorialDTO editorial) {
		EditorialDTO result = editorialService.createEditorial(editorial);
		return ResponseEntity.ok().body(result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EditorialDTO> updateEditorial(@PathVariable("id") String id,
			@RequestBody EditorialDTO editorial) {
		EditorialDTO result = editorialService.updateEditorial(id, editorial);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEditorial(@PathVariable("id") String id) {
		editorialService.deleteEditorial(id);
		return ResponseEntity.noContent().build();
	}
}
