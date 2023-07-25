package doanhockysummer;

import java.util.Scanner;

public class  BaiTapDoAn{

    static Scanner sc = new Scanner(System.in);
    static int n = nhapSoLuongSanPham();
		
    static String[] tenSanPham = new String[n];
    static String[] donViTinh = new String[n];
    static int[] soLuong = new int[n];
    static double[] giaBan = new double[n];
    static double[] thanhTien = new double[n];
    static int soSanPham = 0;
    // menu
    public static void main(String[] args) {
    	System.out.println("Quản Lý Sản Phẩm Mua Bán");
        while (true) {
            System.out.println("-------- MENU --------");
            System.out.println("1. Nhập thông tin sản phẩm");
            System.out.println("2. Tính giá trị thành tiền cho các sản phẩm đã bán");
            System.out.println("3. Sắp xếp danh sách sản phẩm theo chiều giảm dần của thành tiền");
            System.out.println("4. Sắp xếp danh sách sản phẩm theo chiều tăng dần của giá bán");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("Q. Thoát chương trình");
            System.out.print("Chọn chức năng: ");
            String luaChon = sc.nextLine();
            if (luaChon.equalsIgnoreCase("Q")) {
                break;
            }
            switch (luaChon) {
                case "1":
                    nhapThongTinSanPham();
                    break;
                case "2":
                    tinhGiaTriThanhTien();
                    break;
                case "3":
                    sapXepSanPhamTheoThanhTienGiamDan();
                    break;
                case "4":
                    sapXepSanPhamTheoGiaBanTangDan();
                    break;
                case "5":
                    timKiemSanPhamTheoTen();
                    break;
                case "q":
                case "Q":
                	System.out.println("Ket thuc chuong trinh");
                	break;
                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
        System.out.println("Kết Thúc Chương Trình");
    }
    // Số lượng sản phẩm cần nhập thông tin
    public static int nhapSoLuongSanPham() {
    	 
		try {
			int n;
			Scanner sc = new Scanner(System.in);
			do {
				System.out.print("Nhap so luong San Pham n = ");
				n = sc.nextInt();
			} while (n <= 0);
			return n;
		} catch (Exception e) {
			System.out.println("DA XAY RA LOI TRONG QUA TRINH NHAP DU LIEU");
			return -1;
		}
	}
    //Nhập thông tin sản phẩm
    static void nhapThongTinSanPham() {
    	
    	 try {
        if (soSanPham == n) {
            System.out.println("Danh sách sản phẩm đã đầy. Không thể thêm sản phẩm mới.");
            return;
        }
        
       for(int i =0; i< tenSanPham.length;i++){
    	 System.out.println("Nhap du lieu cho San Pham thu " + (i + 1));
        System.out.print("Nhập tên sản phẩm: ");
        tenSanPham[soSanPham] = sc.nextLine();
        System.out.print("Nhập đơn vị tính: ");
        donViTinh[soSanPham] = sc.nextLine();
        System.out.print("Nhập số lượng: ");
        soLuong[soSanPham] = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập giá bán: ");
        giaBan[soSanPham] = Double.parseDouble(sc.nextLine());
        thanhTien[soSanPham] = soLuong[soSanPham] * giaBan[soSanPham];
        soSanPham++;
        System.out.println("Đã thêm sản phẩm vào danh sách.");
       }
        }catch(Exception e) {
        	System.out.println("Lỗi Nhập Dữ Liệu");
        }
    }
    //tính giá trị thành tiền
    static void tinhGiaTriThanhTien() {
        double tongGiaTriThanhTien = 0;
        for (int i = 0; i < soSanPham; i++) {
            tongGiaTriThanhTien += thanhTien[i];
        }
        System.out.printf("Tổng giá trị thành tiền của các sản phẩm đã bán là: %.2f\n", tongGiaTriThanhTien);
    }
    //sắp xếp thành tiền giảm dần
    static void sapXepSanPhamTheoThanhTienGiamDan() {
        for (int i = 0; i < soSanPham - 1; i++) {
            for (int j = i + 1; j < soSanPham; j++) {
                if (thanhTien[i] < thanhTien[j]) {
                    hoanDoiSanPham(i, j);
                }
            }
        }
        System.out.println("Danh sách sản phẩm đã được sắp xếp theo chiều giảm dần của thành tiền:");
        inDanhSachSanPham();
    }
    //sắp xếp sản phẩm theo giá bán tăng dần
    static void sapXepSanPhamTheoGiaBanTangDan() {
        for (int i = 0; i < soSanPham - 1; i++) {
            for (int j = i + 1; j < soSanPham; j++) {
                if (giaBan[i] > giaBan[j]) {
                    hoanDoiSanPham(i, j);
                }
            }
        }
        System.out.println("Danh sách sản phẩm đã được sắp xếp theo chiều tăng dần của giá bán:");
        inDanhSachSanPham();
    }
    //tìm kiếm sản phẩm theo tên
    static void timKiemSanPhamTheoTen() {
        System.out.print("Nhập tên sản phẩm cần tìm kiếm: ");
        String tuKhoa = sc.nextLine();
        boolean timThay = false;
        for (int i = 0; i < soSanPham; i++) {
            if (tenSanPham[i].toLowerCase().contains(tuKhoa.toLowerCase())) {
                inSanPham(i);
                timThay = true;
            }
        }
        if (!timThay) {
            System.out.println("Không tìm thấy sản phẩm nào có tên chứa từ khóa \"" + tuKhoa + "\".");
        }
    }

    static void inSanPham(int i) {
        System.out.printf("%s (%s): %d %s, giá bán: %.2f, thành tiền: %.2f\n",
                tenSanPham[i], donViTinh[i], soLuong[i], donViTinh[i], giaBan[i], thanhTien[i]);
    }

    static void inDanhSachSanPham() {
        for (int i = 0; i < soSanPham; i++) {
            inSanPham(i);
        }
    }
    //Hoán đổi sản phẩm
    static void hoanDoiSanPham(int i, int j) {
        String tempTen = tenSanPham[i];
        tenSanPham[i] = tenSanPham[j];
        tenSanPham[j] = tempTen;

        String tempDonViTinh = donViTinh[i];
        donViTinh[i] = donViTinh[j];
        donViTinh[j] = tempDonViTinh;

        int tempSoLuong = soLuong[i];
        soLuong[i] = soLuong[j];
        soLuong[j] = tempSoLuong;

        double tempGiaBan = giaBan[i];
        giaBan[i] = giaBan[j];
        giaBan[j] = tempGiaBan;

        double tempThanhTien = thanhTien[i];
        thanhTien[i] = thanhTien[j];
        thanhTien[j] = tempThanhTien;
    }
}