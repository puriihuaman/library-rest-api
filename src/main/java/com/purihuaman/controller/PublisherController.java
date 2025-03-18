package com.purihuaman.controller;

import com.purihuaman.dto.PublisherDTO;
import com.purihuaman.service.PublisherService;
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

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/publishers", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublisherController {
	private final PublisherService publisherService;

	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<PublisherDTO>> findAllPublishers(
		@RequestParam Map<String, String> keywords
	)
	{
		short
			offset =
			keywords.containsKey("offset") ? Short.parseShort(keywords.get("offset")) : 0;
		short limit = keywords.containsKey("offset") ? Short.parseShort(keywords.get("limit")) : 10;

		Pageable page = PageRequest.of(offset, limit);

		List<PublisherDTO>
			result =
			keywords.isEmpty()
				? publisherService.findAllPublishers(page)
				: publisherService.filterPublishers(keywords, page);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<PublisherDTO> findPublisherById(@PathVariable("id") UUID id) {
		PublisherDTO result = publisherService.findPublisherById(id);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping("/create")
	public ResponseEntity<PublisherDTO> createPublisher(@RequestBody PublisherDTO publisherDTO) {
		PublisherDTO result = publisherService.createPublisher(publisherDTO);
		return ResponseEntity.ok().body(result);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<PublisherDTO> updatePublisher(
		@PathVariable("id") UUID id,
		@RequestBody PublisherDTO publisherDTO
	)
	{
		PublisherDTO result = publisherService.updatePublisher(id, publisherDTO);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deletePublisher(
		@PathVariable("id") UUID id
	)
	{
		publisherService.deletePublisher(id);
		return ResponseEntity.noContent().build();
	}
}
