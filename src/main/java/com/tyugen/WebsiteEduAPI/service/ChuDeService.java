package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.tyugen.WebsiteEduAPI.model.ChuDe;
import com.tyugen.WebsiteEduAPI.repository.ChuDeRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ChuDeService is a service class that contains business logic related to ChuDeController.
 */
@Service
public class ChuDeService {
    private final ChuDeRepository chuDeRepository;

    /**
     * Constructs a new ChuDeService object with the specified ChuDeRepository.
     *
     * @param chuDeRepository the ChuDeRepository to be used by this service
     */
    public ChuDeService(ChuDeRepository chuDeRepository) {
        this.chuDeRepository = chuDeRepository;
    }

    /**
     * Adds a new ChuDe object to the database.
     *
     * @param chuDe a JSON representation of the new ChuDe object
     * @return a ResponseEntity indicating the result of the add operation
     */
    public ResponseEntity<?> addChuDe(String chuDe) {
        Gson gson = new Gson();
        ChuDe newChuDe = gson.fromJson(chuDe, ChuDe.class);
        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<ChuDe>> violations = validator.validate(newChuDe);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            chuDeRepository.save(newChuDe);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Update an existing ChuDe object in the database.
     *
     * @param id    the id of the ChuDe object to be updated
     * @param chuDe a JSON representation of the updated ChuDe object
     * @return a ResponseEntity indicating the result of the update operation
     */
    public ResponseEntity<?> updateChuDe(int id, String chuDe) {
        Gson gson = new Gson();
        ChuDe newChuDe = gson.fromJson(chuDe, ChuDe.class);
        Optional<ChuDe> oldChuDe = chuDeRepository.findById(id);
        if (oldChuDe.isPresent()) {
            Validator validator;
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                validator = validatorFactory.getValidator();
            }
            Set<ConstraintViolation<ChuDe>> violations = validator.validate(newChuDe);
            if (!violations.isEmpty()) {
                List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                newChuDe.setChuDeID(id);
                chuDeRepository.save(newChuDe);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete an existing ChuDe object in the database.
     *
     * @param id the id of the ChuDe object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    public ResponseEntity<?> deleteChuDe(int id) {
        Optional<ChuDe> oldChuDe = chuDeRepository.findById(id);
        if (oldChuDe.isPresent()) {
            chuDeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get all ChuDe objects from the database.
     *
     * @return a ResponseEntity containing a List of ChuDe objects
     */
    public ResponseEntity<?> getAllChuDe() {
        Optional<List<ChuDe>> chuDes = Optional.of(chuDeRepository.findAll());
        return chuDes.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get a page of ChuDe object from the database.
     *
     * @param pageable the page information
     * @return a Page of ChuDe objects
     */
    public Page<ChuDe> getAllOnPage(Pageable pageable) {
        return chuDeRepository.findAll(pageable);
    }
}