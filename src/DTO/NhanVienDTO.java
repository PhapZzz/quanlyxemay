package DTO;



public class NhanVienDTO {
    private int maNV;
    private String tenNV;
    private String chucVu;

    public NhanVienDTO(int maNV, String tenNV, String chucVu) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.chucVu = chucVu;
    }

    public NhanVienDTO(String tenNV, String chucVu) {
        this.tenNV = tenNV;
        this.chucVu = chucVu;
    }

    public int getMaNV() { return maNV; }
    public String getTenNV() { return tenNV; }
    public String getChucVu() { return chucVu; }

    public void setMaNV(int maNV) { this.maNV = maNV; }
    public void setTenNV(String tenNV) { this.tenNV = tenNV; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }
}
