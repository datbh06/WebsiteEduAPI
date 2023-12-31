package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.model.HocVien;
import com.tyugen.WebsiteEduAPI.service.HocVienService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * HocVienController is a RestController that handles HTTP requests for the HocVien API.
 */
@RestController
@RequestMapping("/api/v1/hocvien")
public class HocVienController {
    private final HocVienService hocVienService;

    /**
     * Constructs a new HocVienController object with the specified HocVienService.
     *
     * @param hocVienService the HocVienService to be used by this controller
     */
    public HocVienController(HocVienService hocVienService) {
        this.hocVienService = hocVienService;
    }

    /**
     * Adds a new HocVien object to the database.
     *
     * @param hocVien a JSON representation of the new HocVien object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addHocVien(@RequestBody HocVien hocVien) {
        return hocVienService.addHocVien(hocVien);
    }

    /**
     * Updates an existing HocVien object in the database.
     *
     * @param id      the ID of the HocVien object to be updated
     * @param hocVien a JSON representation of the updated HocVien object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHocVien(@PathVariable("id") int id, @RequestBody String hocVien) {
        return hocVienService.updateHocVien(id, hocVien);
    }

    /**
     * Deletes an existing HocVien object from the database.
     *
     * @param id the ID of the HocVien object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHocVien(@PathVariable("id") int id) {
        return hocVienService.deleteHocVien(id);
    }

    /**
     * Retrieves a list of HocVien objects from the database.
     *
     * @return a ResponseEntity containing a list of HocVien objects
     */
    @GetMapping("/list")
    public ResponseEntity<?> getHocViens() {
        return hocVienService.getAllHocVien();
    }

    /**
     * Retrieves a list of HocVien objects by hoTen from the database.
     *
     * @return a ResponseEntity containing a list of HocVien objects
     */
    @GetMapping("/list/{name}")
    public ResponseEntity<?> getHocVienByName(@PathVariable("name") String name) {
        return hocVienService.getHocVienByName(name);
    }

    /**
     * Retrieves a list of HocVien objects by email from the database.
     *
     * @return a ResponseEntity containing a list of HocVien objects
     */
    @GetMapping("/list/email")
    public ResponseEntity<?> findHocVienByEmail(@RequestParam String email) {
        Optional<HocVien> hocVien = hocVienService.getHocVienByEmail(email);
        if (hocVien.isPresent()) {
            return ResponseEntity.ok(hocVien.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a page of HocVien objects from the database (Pagination)
     *
     * @param page   the page number to be returned (default: 0)
     * @param size   the number of items to be returned per page (default: 5)
     * @param sortBy the field to sort the results by (default: hocVienID)
     * @return a Page containing a list of HocVien objects
     */
    @GetMapping("/page")
    public Page<HocVien> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size,
                                 @RequestParam(defaultValue = "hocVienID") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return hocVienService.getAllOnPage(pageable);
    }

    /**
     * Retrieves a page of HocVien objects from the database search by keyword of hoTen (Pagination)
     *
     * @param keyword the keyword to search for
     * @param page    the page number to be returned (default: 0)
     * @param size    the number of items to be returned per page (default: 5)
     * @param sortBy  the field to sort the results by (default: hocVienID)
     * @return a Page containing a list of HocVien objects
     */
    @GetMapping("/search")
    public Page<HocVien> search(@RequestParam String keyword,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "hocVienID") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return hocVienService.searchOnPage(keyword, pageable);
    }
}
