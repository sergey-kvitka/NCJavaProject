package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.LinkValue;
import com.example.ncjavaproject.repositories.LinkValueRepository;
import org.springframework.stereotype.Service;

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
}
