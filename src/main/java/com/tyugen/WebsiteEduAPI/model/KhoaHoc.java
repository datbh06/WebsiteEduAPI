package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhoaHoc {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer khoaHocID;

    @Column
    @Size(max = 50, message = "Tên khóa học không được quá 50 ký tự")
    private String tenKhoaHoc;
    @Column
    private Integer thoiGianHoc;
    @Column
    private String gioiThieu;
    @Column
    private String noiDung;
    @Column
    private Float hocPhi;
    @Column
    private Integer soHocVien;
    @Column
    private Integer soLuongMon;
    @Column
    private String hinhAnh;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loaiKhoaHocID", foreignKey = @ForeignKey(name = "fk_khoahoc_loaikhoahoc"))
    private LoaiKhoaHoc loaiKhoaHoc;

    @OneToMany(mappedBy = "khoaHoc", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("khoaHoc")
    private Set<DangKyHoc> dangKyHocs;

}
