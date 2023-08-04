package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.tyugen.WebsiteEduAPI.exceptions.ValidationUtils;
import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import com.tyugen.WebsiteEduAPI.model.LoaiBaiViet;
import com.tyugen.WebsiteEduAPI.repository.LoaiBaiVietRepository;
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
 * LoaiBaiVietService is a service class that contains business logic related to LoaiBaiVietController.
 */
@Service
public class LoaiBaiVietService {
    private final LoaiBaiVietRepository loaiBaiVietRepository;

    /**
     * Constructs a new LoaiBaiVietService object with the specified LoaiBaiVietRepository.
     *
     * @param loaiBaiVietRepository the LoaiBaiVietRepository to be used by this service
     */
    public LoaiBaiVietService(LoaiBaiVietRepository loaiBaiVietRepository) {
        this.loaiBaiVietRepository = loaiBaiVietRepository;
    }

    /**
     * Adds a new LoaiBaiViet object to the database.
     *
     * @param loaiBaiViet a JSON representation of the new LoaiBaiViet object
     * @return a ResponseEntity indicating the result of the add operation
     */
    public ResponseEntity<?> addLoaiBaiViet(String loaiBaiViet) {
        Gson gson = new Gson();
        LoaiBaiViet loaiBaiVietNew = gson.fromJson(loaiBaiViet, LoaiBaiViet.class);

        List<String> errorMessages = ValidationUtils.validate(loaiBaiVietNew, LoaiBaiViet.class);
        if (!errorMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            loaiBaiVietRepository.save(loaiBaiVietNew);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Updates a LoaiBaiViet object with the specified ID.
     *
     * @param id          the ID of the LoaiBaiViet object to be updated
     * @param loaiBaiViet a JSON representation of the LoaiBaiViet object with updated fields
     * @return a ResponseEntity indicating the result of the update operation
     */
    public ResponseEntity<?> updateLoaiBaiViet(int id, String loaiBaiViet) {
        Gson gson = new Gson();
        LoaiBaiViet loaiBaiVietNew = gson.fromJson(loaiBaiViet, LoaiBaiViet.class);
        Optional<LoaiBaiViet> loaiBaiVietOld = loaiBaiVietRepository.findById(id);
        if (loaiBaiVietOld.isPresent()) {
            Validator validator;
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                validator = validatorFactory.getValidator();
            }
            Set<ConstraintViolation<LoaiBaiViet>> violations = validator.validate(loaiBaiVietNew);
            if (!violations.isEmpty()) {
                List<String> errorMessages = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            } else {

                loaiBaiVietNew.setLoaiBaiVietID(id);
                loaiBaiVietRepository.save(loaiBaiVietNew);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a LoaiBaiViet object with the specified ID.
     *
     * @param id the ID of the LoaiBaiViet object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    public ResponseEntity<?> deleteLoaiBaiViet(int id) {
        Optional<LoaiBaiViet> loaiBaiViet = loaiBaiVietRepository.findById(id);
        if (loaiBaiViet.isPresent()) {
            loaiBaiVietRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves list of all LoaiBaiViet objects.
     *
     * @return a ResponseEntity containing a list of all LoaiBaiViet objects
     */
    public ResponseEntity<?> getAllLoaiBaiViet() {
        Optional<List<LoaiBaiViet>> loaiBaiViet = Optional.of(loaiBaiVietRepository.findAll());
        return loaiBaiViet.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a page of LoaiBaiViet objects.
     *
     * @param pageable the pagination information including page and size
     * @return a Page containing a list of LoaiBaiViet objects
     */
    public Page<LoaiBaiViet> getAllLoaiBaiVietPage(Pageable pageable) {
        return loaiBaiVietRepository.findAll(pageable);
    }
}

