package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
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
public class TinhTrangHoc {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tinhTrangHocID;

    @Column
    @Size(max = 40, message = "Tên tình trạng học không được vượt quá 40 ký tự")
    private String tenTinhTrang;

    @OneToMany(mappedBy = "tinhTrangHoc", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("tinhTrangHoc")
    private Set<DangKyHoc> dangKyHocs;
}
