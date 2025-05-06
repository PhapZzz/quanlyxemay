package DTO;

public class danhsachxe {
        private String maChiTietXe;
        private String tenXe;
        private double donGia;
        private String soKhung;
        private String soMay;
        private String trangThai;
        private String color;
        public danhsachxe(String maChiTietXe, String tenXe, double donGia, String soKhung, String soMay,
                String trangThai, String color) {
            this.maChiTietXe = maChiTietXe;
            this.tenXe = tenXe;
            this.donGia = donGia;
            this.soKhung = soKhung;
            this.soMay = soMay;
            this.trangThai = trangThai;
            this.color = color;
           
        }
        public String getMaChiTietXe() {
            return maChiTietXe;
        }
        public void setMaChiTietXe(String maChiTietXe) {
            this.maChiTietXe = maChiTietXe;
        }
        public String getTenXe() {
            return tenXe;
        }
        public void setTenXe(String tenXe) {
            this.tenXe = tenXe;
        }
        public double getDonGia() {
            return donGia;
        }
        public void setDonGia(double donGia) {
            this.donGia = donGia;
        }
        public String getSoKhung() {
            return soKhung;
        }
        public void setSoKhung(String soKhung) {
            this.soKhung = soKhung;
        }
        public String getSoMay() {
            return soMay;
        }
        public void setSoMay(String soMay) {
            this.soMay = soMay;
        }
        public String getTrangThai() {
            return trangThai;
        }
        public void setTrangThai(String trangThai) {
            this.trangThai = trangThai;
        }
        public String getColor() {
            return color;
        }
        public void setColor(String color) {
            this.color = color;
        }
    
       
        
    }
    

