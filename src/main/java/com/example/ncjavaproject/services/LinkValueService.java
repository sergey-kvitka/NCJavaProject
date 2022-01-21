package com.example.ncjavaproject.services;

import com.example.ncjavaproject.entities.LinkValue;
import com.example.ncjavaproject.repositories.LinkValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkValueService {

    private final LinkValueRepository linkValueRepository;

    public LinkValueService(LinkValueRepository linkValueRepository) {
        this.linkValueRepository = linkValueRepository;
    }

    public Iterable<LinkValue> getLinkValues() {
        return linkValueRepository.findAll();
    }

    public LinkValue getLinkValue(Long id) {
        return (linkValueRepository.findById(id).orElse(null));
    }

    public void updateLinkValue(LinkValue linkValue) {
        linkValueRepository.save(linkValue);
    }

    public void deleteLinkValue(Long id) {
        linkValueRepository.deleteById(id);
    }

    public List<LinkValue> getLinkValuesByObjectId(Long id) {
        return linkValueRepository.findAllByObjectId(id);
    }

    public void deleteByAttributeId(Long attributeId) {
        linkValueRepository.deleteByAttributeId(attributeId);
    }

    public void deleteByObjectIdAndAttributeId(Long objectId, Long attributeId) {
        linkValueRepository.deleteByObjectIdAndAttributeId(objectId, attributeId);
    }

    public LinkValue getLinkValueByObjectIdAndAttributeId(Long objectId, Long attributeId) {
        return linkValueRepository.findByObjectIdAndAttributeId(objectId, attributeId);
    }
}
