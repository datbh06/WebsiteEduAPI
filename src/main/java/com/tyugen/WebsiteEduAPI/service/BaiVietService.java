package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.tyugen.WebsiteEduAPI.model.BaiViet;
import com.tyugen.WebsiteEduAPI.repository.BaiVietRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * BaiVietService is a service class that contains business logic related to BaiVietController.
 */
@Service
public class BaiVietService {
    private final BaiVietRepository baiVietRepository;

    /**
     * Constructs a new BaiVietService object with the specified BaiVietRepository.
     *
     * @param baiVietRepository the BaiVietRepository to be used by this service
     */
    public BaiVietService(BaiVietRepository baiVietRepository) {
        this.baiVietRepository = baiVietRepository;
    }

    /**
     * Adds a new BaiViet object to the database.
     *
     * @param baiViet a JSON representation of the new BaiViet object
     * @return a ResponseEntity indicating the result of the add operation
     */
    public ResponseEntity<?> addBaiViet(String baiViet) {
        Gson gson = new Gson();
        BaiViet newBaiViet = gson.fromJson(baiViet, BaiViet.class);
        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<BaiViet>> violations = validator.validate(newBaiViet);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            baiVietRepository.save(newBaiViet);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Updates an existing BaiViet object in the database.
     *
     * @param id      the ID of the BaiViet object to be updated
     * @param baiViet a JSON representation of the updated BaiViet object
     * @return a ResponseEntity indicating the result of the update operation
     */
    public ResponseEntity<?> updateBaiViet(int id, String baiViet) {
        Gson gson = new Gson();
        BaiViet newBaiViet = gson.fromJson(baiViet, BaiViet.class);
        Optional<BaiViet> oldBaiViet = baiVietRepository.findById(id);
        if (oldBaiViet.isEmpty()) {
            return ResponseEntity.badRequest().body("BaiViet not found");
        } else {
            Validator validator;
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                validator = validatorFactory.getValidator();
            }
            Set<ConstraintViolation<BaiViet>> violations = validator.validate(newBaiViet);
            if (!violations.isEmpty()) {
                List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                newBaiViet.setBaiVietID(id);
                baiVietRepository.save(newBaiViet);
                return ResponseEntity.ok().build();
            }
        }
    }

    /**
     * Deletes an existing BaiViet object in the database.
     *
     * @param id the ID of the BaiViet object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    public ResponseEntity<?> deleteBaiViet(int id) {
        Optional<BaiViet> oldBaiViet = baiVietRepository.findById(id);
        if (oldBaiViet.isEmpty()) {
            return ResponseEntity.badRequest().body("BaiViet not found");
        } else {
            baiVietRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Gets a list of BaiViet objects from the database.
     *
     * @return a ResponseEntity containing a list of BaiViet objects
     */
    public ResponseEntity<?> getAllBaiViet() {
        Optional<List<BaiViet>> baiVietList = Optional.of(baiVietRepository.findAll());
        return baiVietList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}