package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class DangKyHoc {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dangKyHocID;
    @Column
    private Date ngayDangKy;
    @Column
    private Date ngayBatDau;
    @Column
    private Date ngayKetThuc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "khoaHocID", foreignKey = @ForeignKey(name = "fk_dangkyhoc_khoahoc"))
    private KhoaHoc khoaHoc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hocVienID", foreignKey = @ForeignKey(name = "fk_dangkyhoc_hocvien"))
    private HocVien hocVien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tinhTrangHocID", foreignKey = @ForeignKey(name = "fk_dangkyhoc_tinhtranghoc"))
    private TinhTrangHoc tinhTrangHoc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taiKhoanID", foreignKey = @ForeignKey(name = "fk_dangkyhoc_taikhoan"))
    private TaiKhoan taiKhoan;


}
