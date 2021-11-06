package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.exceptions.NotFoundException;
import com.example.ncjavaproject.models.AttributeType;
import com.example.ncjavaproject.models.LinkValue;
import com.example.ncjavaproject.repositories.LinkValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("link_values")
public class LinkValueController {
    @Autowired
    LinkValueRepository linkValueRepository;

    @GetMapping
    public Iterable<LinkValue> getLinkValues() {
        return linkValueRepository.findAll();
    }

    @GetMapping("{id}")
    public LinkValue get(@PathVariable Long id) {
        return linkValueRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public LinkValue create(
            @RequestBody LinkValue linkValue) {
        return linkValueRepository.save(new LinkValue(
                linkValue.getObjectId(),
                linkValue.getAttributeId(),
                linkValue.getValueObjectId()
        ));
    }

    @PutMapping("{id}")
    public LinkValue update(@PathVariable Long id, @RequestBody LinkValue linkValue) {
        linkValue.setId(id); get(id);
        return linkValueRepository.save(linkValue);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        get(id); linkValueRepository.deleteById(id);
    }
}
