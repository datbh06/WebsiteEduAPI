package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.tyugen.WebsiteEduAPI.model.TaiKhoan;
import com.tyugen.WebsiteEduAPI.repository.TaiKhoanRepository;
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
 * TaiKhoanService is a service class that contains business logic related to TaiKhoanController.
 */
@Service
public class TaiKhoanService {
    private final TaiKhoanRepository taiKhoanRepository;

    /**
     * Constructs a new TaiKhoanService object with the specified TaiKhoanRepository.
     *
     * @param taiKhoanRepository the TaiKhoanRepository to be used by this service
     */
    public TaiKhoanService(TaiKhoanRepository taiKhoanRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
    }

    /**
     * Adds a new TaiKhoan object to the database.
     *
     * @param taiKhoan a JSON representation of the new TaiKhoan object
     * @return a ResponseEntity indicating the result of the add operation
     */
    public ResponseEntity<?> addTaiKhoan(String taiKhoan) {
        Gson gson = new Gson();
        TaiKhoan newTaiKhoan = gson.fromJson(taiKhoan, TaiKhoan.class);

        if (taiKhoanRepository.existsByTaiKhoan(newTaiKhoan.getTaiKhoan())) {
            throw new RuntimeException("Account name already exists");
        }
        if (!newTaiKhoan.getMatKhau().matches("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]+$")) {
            throw new RuntimeException("Password must contain numbers and special characters");
        }

        Validator validator;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        Set<ConstraintViolation<TaiKhoan>> violations = validator.validate(newTaiKhoan);

        if (violations.isEmpty()) {
            taiKhoanRepository.save(newTaiKhoan);
            return ResponseEntity.ok().build();
        } else {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
    }

    /**
     * Updates an existing TaiKhoan object in the database.
     *
     * @param id       the ID of the TaiKhoan object to be updated
     * @param taiKhoan a JSON representation of the updated TaiKhoan object
     * @return a ResponseEntity indicating the result of the update operation
     */
    public ResponseEntity<?> updateTaiKhoan(int id, String taiKhoan) {
        Gson gson = new Gson();
        TaiKhoan newTaiKhoan = gson.fromJson(taiKhoan, TaiKhoan.class);
        Optional<TaiKhoan> oldTaiKhoan = taiKhoanRepository.findById(id);

        if (taiKhoanRepository.existsByTaiKhoan(newTaiKhoan.getTaiKhoan())) {
            TaiKhoan existingTaiKhoan = taiKhoanRepository.findByTaiKhoan(newTaiKhoan.getTaiKhoan());
            if (existingTaiKhoan.getTaiKhoanID() != id) {
                throw new RuntimeException("Account name already exists");
            }
        }
        if (!newTaiKhoan.getMatKhau().matches("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]+$")) {
            throw new RuntimeException("Password must contain numbers and special characters");
        }

        if (oldTaiKhoan.isPresent()) {

            Validator validator;
            try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                validator = factory.getValidator();
            }
            Set<ConstraintViolation<TaiKhoan>> violations = validator.validate(newTaiKhoan);

            if (violations.isEmpty()) {
                newTaiKhoan.setTaiKhoanID(id);
                taiKhoanRepository.save(newTaiKhoan);
                return ResponseEntity.ok().build();
            } else {
                List<String> errorMessages = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            }
        } else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }

    /**
     * Deletes an existing TaiKhoan object from the database.
     *
     * @param id the ID of the TaiKhoan object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    public ResponseEntity<?> deleteTaiKhoan(int id) {
        Optional<TaiKhoan> taiKhoan = taiKhoanRepository.findById(id);

        if (taiKhoan.isPresent()) {
            taiKhoanRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }

}