package com.example.ncjavaproject.services;

import com.example.ncjavaproject.models.LinkValue;
import com.example.ncjavaproject.repositories.LinkValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkValueService {
    @Autowired
    LinkValueRepository repository;

    public Iterable<LinkValue> getLinkValues() {
        return repository.findAll();
    }

    public LinkValue getLinkValue(Long id) {
        return (repository
                .findById(id)
                .orElse(null)
        );
    }

    public void updateLinkValue(LinkValue linkValue, Long id) {
        linkValue.setId(id);
        repository.save(linkValue);
    }

    public void updateLinkValue(LinkValue linkValue) {
        repository.save(linkValue);
    }

    public void deleteLinkValue(Long id) {
        repository.findById(id);
    }
}
