package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuyenHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quyenHanID;

    @Column
    @Size(max = 50, message = "Tên quyền hạn không được vượt quá 50 ký tự")
    private String tenQuyenHan;

    @OneToMany(mappedBy = "quyenHan", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("quyenHan")
    private Set<TaiKhoan> taiKhoans;

}
