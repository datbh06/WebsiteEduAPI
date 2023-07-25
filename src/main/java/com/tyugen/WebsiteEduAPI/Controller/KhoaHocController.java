package com.tyugen.WebsiteEduAPI.Controller;

import com.google.gson.Gson;
import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import com.tyugen.WebsiteEduAPI.repository.HocVienRepository;
import com.tyugen.WebsiteEduAPI.repository.KhoaHocRepository;
import com.tyugen.WebsiteEduAPI.service.KhoaHocService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/khoahoc")
public class KhoaHocController {

    private final KhoaHocService khoaHocService;

    private final KhoaHocRepository khoaHocRepository;

    private final HocVienRepository hocVienRepository;

    /**
     * Constructs a new KhoaHocController object with the specified KhoaHocRepository.
     *
     * @param khoaHocService    the KhoaHocService to be used by this controller
     * @param khoaHocRepository the khoaHocRepository to be used by this controller
     * @param hocVienRepository the hocVienRepository to be used by this controller
     */
    public KhoaHocController(KhoaHocService khoaHocService, KhoaHocRepository khoaHocRepository, HocVienRepository hocVienRepository) {
        this.khoaHocService = khoaHocService;
        this.khoaHocRepository = khoaHocRepository;
        this.hocVienRepository = hocVienRepository;
    }

    /**
     * Adds a new KhoaHoc object to the database.
     *
     * @param khoaHoc a JSON representation of the new KhoaHoc object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addKhoaHoc(@RequestBody String khoaHoc) {
        Gson gson = new Gson();
        KhoaHoc khoaHocNew = gson.fromJson(khoaHoc, KhoaHoc.class);

        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<KhoaHoc>> violations = validator.validate(khoaHocNew);

        if (violations.size() == 0) {
            khoaHocRepository.save(khoaHocNew);
            return ResponseEntity.ok().build();
        } else {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
    }

    /**
     * Updates an existing KhoaHoc object in the database.
     *
     * @param id      the ID of the KhoaHoc object to be updated
     * @param khoaHoc a JSON representation of the updated KhoaHoc object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateKhoaHoc(@PathVariable("id") int id, @RequestBody String khoaHoc) {
        Gson gson = new Gson();
        KhoaHoc khoaHocNew = gson.fromJson(khoaHoc, KhoaHoc.class);
        Optional<KhoaHoc> khoaHocOld = khoaHocRepository.findById(id);
        if (khoaHocOld.isPresent()) {
            Validator validator;
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                validator = validatorFactory.getValidator();
            }
            Set<ConstraintViolation<KhoaHoc>> violations = validator.validate(khoaHocNew);
            if (violations.size() > 0) {
                List<String> errorMessages = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                khoaHocNew.setKhoaHocID(id);
                khoaHocRepository.save(khoaHocNew);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an existing KhoaHoc object from the database.
     *
     * @param id the ID of the KhoaHoc object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteKhoaHoc(@PathVariable("id") int id) {
        Optional<KhoaHoc> khoaHoc = khoaHocRepository.findById(id);
        if (khoaHoc.isPresent()) {
            khoaHocRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Gets all KhoaHoc objects from the database.
     *
     * @return a ResponseEntity containing the retrieved KhoaHoc object
     */
    @GetMapping
    public ResponseEntity<?> getAllKhoaHoc() {
        Optional<List<KhoaHoc>> optionalKhoaHocList = Optional.of(khoaHocRepository.findAll());
        return ResponseEntity.ok(optionalKhoaHocList.get());
    }

    /**
     * Gets a KhoaHoc object from the database by its name
     *
     * @param tenKhoaHoc the name of the KhoaHoc object to be retrieved
     * @return a ResponseEntity containing the retrieved KhoaHoc object
     */
    @GetMapping("/find/{tenKhoaHoc}")
    public ResponseEntity<?> findKhoaHocByName(@PathVariable("tenKhoaHoc") String tenKhoaHoc) {
        Optional<KhoaHoc> khoaHoc = Optional.ofNullable((KhoaHoc) khoaHocRepository.findByTenKhoaHoc(tenKhoaHoc));
        if (khoaHoc.isPresent()) {
            return ResponseEntity.ok(khoaHoc.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Gets all KhoaHoc objects from the database displaying the specified number of objects per page.
     *
     * @param page the page number
     * @param size the number of objects per page
     * @return a Page containing the KhoaHoc objects
     */
    @GetMapping("/page")
    public Page<KhoaHoc> getKhoaHocByPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return khoaHocService.getKhoaHocByPage(page, size);
    }


}
