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
}
