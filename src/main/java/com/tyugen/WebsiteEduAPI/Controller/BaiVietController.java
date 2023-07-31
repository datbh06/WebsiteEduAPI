package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.model.BaiViet;
import com.tyugen.WebsiteEduAPI.service.BaiVietService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * BaiVietController is a controller class that handles requests related to BaiViet objects.
 */
@RestController
@RequestMapping("api/v1/baiviet")
public class BaiVietController {
    private final BaiVietService baiVietService;

    /**
     * Constructs a new BaiVietController object with the specified BaiVietService.
     *
     * @param baiVietService the BaiVietService to be used by this controller
     */
    public BaiVietController(BaiVietService baiVietService) {
        this.baiVietService = baiVietService;
    }

    /**
     * Adds a new BaiViet object to the database.
     *
     * @param baiViet a JSON representation of the new BaiViet object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addBaiViet(@RequestBody String baiViet) {
        return baiVietService.addBaiViet(baiViet);
    }

    /**
     * Updates an existing BaiViet object in the database.
     *
     * @param id      the ID of the BaiViet object to be updated
     * @param baiViet a JSON representation of the updated BaiViet object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBaiViet(@PathVariable("id") int id, @RequestBody String baiViet) {
        return baiVietService.updateBaiViet(id, baiViet);
    }

    /**
     * Deletes an existing BaiViet object from the database.
     *
     * @param id the ID of the BaiViet object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBaiViet(@PathVariable("id") int id) {
        return baiVietService.deleteBaiViet(id);
    }

    /**
     * Retrieves a list of BaiViet objects from the database.
     *
     * @return a ResponseEntity containing a list of BaiViet objects
     */
    @GetMapping("/list")
    public ResponseEntity<?> getBaiVietList() {
        return baiVietService.getAllBaiViet();
    }

    /**
     * Retrieves a BaiViet object from the database by its tenBaiViet
     *
     * @return a ResponseEntity containing a BaiViet object
     */
    @GetMapping("/find/tenbaiviet")
    public ResponseEntity<?> getBaiVietByTenBaiViet(@RequestParam String tenBaiViet) {
        return baiVietService.getBaiVietByTenBaiViet(tenBaiViet);
    }

    /**
     * Retrieves a page of BaiViet object from the database
     *
     * @param page the page number
     * @param size the number of BaiViet objects per page
     * @return a ResponseEntity containing a BaiViet object
     */
    @GetMapping("list/page")
    public Page<BaiViet> getBaiVietListPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return baiVietService.getBaiVietByPage(pageable);
    }

    /**
     * Retrieves a page of BaiViet object from the database by its keyword of tenBaiViet
     *
     * @param page    the page number
     * @param size    the number of BaiViet objects per page
     * @param keyword the keyword of tenBaiViet
     * @return a ResponseEntity containing a BaiViet object
     */
    @GetMapping("/page/find")
    public Page<BaiViet> getBaiVietListPageByKeyword(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size,
                                                     @RequestParam String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        return baiVietService.getBaiVietByPageAndTenBaiViet(pageable, keyword);
    }
}
