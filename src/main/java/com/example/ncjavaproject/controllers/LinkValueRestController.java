package com.example.ncjavaproject.controllers;

import com.example.ncjavaproject.entities.LinkValue;
import com.example.ncjavaproject.services.AttributeService;
import com.example.ncjavaproject.services.LinkValueService;
import com.example.ncjavaproject.services.ValueService;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("link_values")
public class LinkValueRestController {
    private final LinkValueService service;
    private final ValueService valueService;
    private final AttributeService attributeService;

    public LinkValueRestController(LinkValueService service, AttributeService attributeService, ValueService valueService) {
        this.valueService = valueService;
        this.service = service;
        this.attributeService = attributeService;
    }

    @GetMapping
    public Iterable<LinkValue> getLinkValues() {
        return service.getLinkValues();
    }

    @GetMapping("{id}")
    public LinkValue get(@PathVariable Long id) {
        return service.getLinkValue(id);
    }

    @PostMapping("add_new")
    @Transactional
    public void create(@RequestBody LinkValue linkValue) {
        LinkValue newValue = new LinkValue(
                linkValue.getObjectId(),
                linkValue.getAttributeId(),
                linkValue.getValueObjectId()
        );
        service.deleteByObjectIdAndAttributeId(newValue.getObjectId(), newValue.getAttributeId());
        valueService.deleteByObjectIdAndAttributeId(newValue.getObjectId(), newValue.getAttributeId());
        attributeService.setAttributeType(2L, newValue.getAttributeId());
        service.updateLinkValue(newValue);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody LinkValue linkValue) {
        linkValue.setId(id);
        service.updateLinkValue(linkValue);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.deleteLinkValue(id);
    }

    @DeleteMapping("{id}/delete_by_attribute_id")
    public void deleteByAttributeId(@PathVariable Long id) {
        service.deleteByAttributeId(id);
    }

    @GetMapping("get_by_object_id/{id}")
    public List<LinkValue> getLinkValuesByObjectId(@PathVariable Long id) {
        return service.getLinkValuesByObjectId(id);
    }
}
