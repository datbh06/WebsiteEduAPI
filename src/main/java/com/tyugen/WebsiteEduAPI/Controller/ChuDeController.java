package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.ChuDeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ChuDeController is a controller class that handles requests related to ChuDe objects.
 */
@RestController
@RequestMapping("/api/v1/chude")
public class ChuDeController {
    private final ChuDeService chuDeService;

    /**
     * Constructs a new ChuDeController object with the specified ChuDeService.
     *
     * @param chuDeService the ChuDeService to be used by this controller
     */
    public ChuDeController(ChuDeService chuDeService) {
        this.chuDeService = chuDeService;
    }

    /**
     * Adds a new ChuDe object to the database.
     *
     * @param chuDe a JSON representation of the new ChuDe object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addChuDe(@RequestBody String chuDe) {
        return chuDeService.addChuDe(chuDe);
    }

    /**
     * Update an existing ChuDe object in the database.
     *
     * @param id    the id of the ChuDe object to be updated
     * @param chuDe a JSON representation of the updated ChuDe object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateChuDe(@PathVariable int id, @RequestBody String chuDe) {
        return chuDeService.updateChuDe(id, chuDe);
    }
}
