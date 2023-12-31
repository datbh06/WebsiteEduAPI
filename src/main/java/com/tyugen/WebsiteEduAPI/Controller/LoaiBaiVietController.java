package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.model.LoaiBaiViet;
import com.tyugen.WebsiteEduAPI.service.LoaiBaiVietService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * LoaiBaiVietController is a controller class that handles requests related to LoaiBaiViet objects.
 */
@RestController
@RequestMapping("api/v1/loaibaiviet")
public class LoaiBaiVietController {
    private final LoaiBaiVietService loaiBaiVietService;

    /**
     * Constructs a new LoaiBaiVietController object with the specified LoaiBaiVietService.
     *
     * @param loaiBaiVietService the LoaiBaiVietService to be used by this controller
     */
    public LoaiBaiVietController(LoaiBaiVietService loaiBaiVietService) {
        this.loaiBaiVietService = loaiBaiVietService;
    }

    /**
     * Adds a new LoaiBaiViet object to the database.
     *
     * @param loaiBaiViet a JSON representation of the new LoaiBaiViet object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addLoaiBaiViet(@RequestBody String loaiBaiViet) {
        return loaiBaiVietService.addLoaiBaiViet(loaiBaiViet);
    }

    /**
     * Updates a LoaiBaiViet object with the specified ID.
     *
     * @param id          the ID of the LoaiBaiViet object to be updated
     * @param loaiBaiViet a JSON representation of the updated LoaiBaiViet object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLoaiBaiViet(@PathVariable("id") int id, @RequestBody String loaiBaiViet) {
        return loaiBaiVietService.updateLoaiBaiViet(id, loaiBaiViet);
    }

    /**
     * Deletes a LoaiBaiViet object with the specified ID.
     *
     * @param id the ID of the LoaiBaiViet object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLoaiBaiViet(@PathVariable("id") int id) {
        return loaiBaiVietService.deleteLoaiBaiViet(id);
    }

    /**
     * Retrieve all LoaiBaiViet objects from the database.
     *
     * @return a ResponseEntity containing a List of all LoaiBaiViet objects
     */
    @GetMapping("/list")
    public ResponseEntity<?> getAllLoaiBaiViet() {
        return loaiBaiVietService.getAllLoaiBaiViet();
    }

    /**
     * Retrieve a page of LoaiBaiViet object
     *
     * @param page the page number (default: 0)
     * @param size the number of LoaiBaiViet objects per page (default: 5)
     * @return a Page of LoaiBaiViet objects
     */
    @GetMapping("/list/page")
    public Page<LoaiBaiViet> getAllLoaiBaiVietPage(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return loaiBaiVietService.getAllLoaiBaiVietPage(pageable);
    }
}
