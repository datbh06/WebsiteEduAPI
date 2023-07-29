package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tyugen.WebsiteEduAPI.Adapter.DateTypeAdapter;
import com.tyugen.WebsiteEduAPI.model.HocVien;
import com.tyugen.WebsiteEduAPI.repository.HocVienRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * HocVienService is a service class that contains business logic related to HocVienController.
 */
@Service
public class HocVienService {

    private final HocVienRepository hocVienRepository;

    /**
     * Constructs a new HocVienService object with the specified HocVienService.
     *
     * @param hocVienRepository the hocVienRepository to be used by this service
     */
    public HocVienService(HocVienRepository hocVienRepository) {
        this.hocVienRepository = hocVienRepository;
    }

    /**
     * Adds a new HocVien object to the database.
     *
     * @param hocVien a JSON representation of the new HocVien object
     * @return a ResponseEntity indicating the result of the add operation
     */
    public ResponseEntity<?> addHocVien(HocVien hocVien) {
        // Check if a HocVien object with the same email or soDienThoai value already exists
        Optional<HocVien> existingHocVienByEmail = hocVienRepository.findByEmail(hocVien.getEmail());
        Optional<HocVien> existingHocVienBySoDienThoai = hocVienRepository.findBySoDienThoai(hocVien.getSoDienThoai());
        if (existingHocVienByEmail.isPresent()) {
            // Return a badRequest response with an error message
            return ResponseEntity.badRequest().body("A HocVien object with the same email value already exists");
        } else if (existingHocVienBySoDienThoai.isPresent()) {
            // Return a badRequest response with an error message
            return ResponseEntity.badRequest().body("A HocVien object with the same soDienThoai value already exists");
        }
        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }

        Set<ConstraintViolation<HocVien>> violations = validator.validate(hocVien);

        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            hocVienRepository.save(hocVien);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Updates an existing HocVien object in the database.
     *
     * @param id      the ID of the HocVien object to update
     * @param hocVien a JSON representation of the updated HocVien object
     * @return a ResponseEntity indicating the result of the update operation
     */
    public ResponseEntity<?> updateHocVien(int id, String hocVien) {
        // Create a Gson object with a custom TypeAdapter for the java.sql.Date type
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
        HocVien hocVienNew = gson.fromJson(hocVien, HocVien.class);
        Optional<HocVien> oldHocVien = hocVienRepository.findById(id);

        if (oldHocVien.isPresent()) {
            Validator validator;
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                validator = validatorFactory.getValidator();
            }
            Set<ConstraintViolation<HocVien>> violations = validator.validate(hocVienNew);

            if (!violations.isEmpty()) {
                List<String> errorMessages = violations.stream().
                        map(ConstraintViolation::getMessage)
                        .collect(Collectors
                                .toList());
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                hocVienNew.setHocVienID(id);
                hocVienRepository.save(hocVienNew);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an existing HocVien object in the database.
     *
     * @param id the ID of the HocVien object to delete
     * @return a ResponseEntity indicating the result of the delete operation
     */
    public ResponseEntity<?> deleteHocVien(int id) {
        Optional<HocVien> hocVien = hocVienRepository.findById(id);

        if (hocVien.isPresent()) {
            hocVienRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Gets a list of HocVien objects from the database.
     *
     * @return a ResponseEntity containing a list of HocVien objects
     */
    public ResponseEntity<?> getAllHocVien() {
        Optional<List<HocVien>> optionalHocVienList = Optional.of(hocVienRepository.findAll());
        return optionalHocVienList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets a HocVien object from the database by hoTen
     *
     * @param name hoTen of HocVien
     * @return a ResponseEntity containing a HocVien object
     */
    public ResponseEntity<?> getHocVienByName(String name) {
        Optional<List<HocVien>> optionalHocVienList = Optional.ofNullable(hocVienRepository.findByHoTen(name));
        return optionalHocVienList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets a HocVien object from the database by email
     *
     * @param email email of HocVien
     * @return a ResponseEntity containing a HocVien object
     */
    public Optional<HocVien> getHocVienByEmail(String email) {
        return hocVienRepository.findByEmail(email);
    }

    /**
     * Retrieves a page of HocVien objects from the database (Pagination)
     *
     * @param pageable the paging information
     * @return a Page containing a list of HocVien objects
     */
    public Page<HocVien> getAllOnPage(Pageable pageable) {
        return hocVienRepository.findAll(pageable);
    }

}
