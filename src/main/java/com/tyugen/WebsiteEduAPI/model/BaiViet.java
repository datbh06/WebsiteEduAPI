package com.tyugen.WebsiteEduAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer baiVietID;

    @Column
    @Size(max = 50, message = "Tên bài viết không được vượt quá 50 ký tự")
    private String tenBaiViet;

    @Column
    @Size(max = 50, message = "Tên tác giả không được vượt quá 50 ký tự")
    private String tenTacGia;

    @Column
    private String noiDung;

    @Column
    @Size(max = 1000, message = "Nội dung ngắn không được vượt quá 1000 ký tự")
    private String noiDungNgan;

    @Column
    private Date thoiGianTao;

    @Column
    private String hinhAnh;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chuDeID", foreignKey = @ForeignKey(name = "fk_baiviet_chude"))
    private ChuDe chuDes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taiKhoanID", foreignKey = @ForeignKey(name = "fk_baiviet_taikhoan"))
    private TaiKhoan taiKhoan;

    /**
     * Auto update thoiGianTao before inserting or updating
     */
    @PrePersist
    @PreUpdate
    public void prePersist() {
        thoiGianTao = new Date(System.currentTimeMillis());
    }

}
