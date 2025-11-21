import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

// --- Import các component ---
// Client Views
import HomePage from "../views/Client/HomePage.vue";
import CartPage from "../views/Client/CartPage.vue";
import CheckoutPage from "../views/Client/CheckoutPage.vue";
import ShopPage from "../views/Client/Shop.vue";
import ProductDetail from "../views/Client/ProductDetail.vue";
import About from "../views/Client/About.vue";
import Blog from "../views/Client/Blog.vue";
import Contact from "../views/Client/Contact.vue";
import OutletSale from "../views/Client/OutletSale.vue";
// Login
import Login from "../views/LoginView/AppLogin.vue";
import DangKy from "@/views/LoginView/DangKy/DangKy.vue";
import QuenMk from "@/views/LoginView/QuenMk/QuenMk.vue";
import UpdateMk from "@/views/LoginView/QuenMk/UpdateMk.vue";
// Admin/Staff Views
import Dashboard from "../views/ThongKe/AppDashboard.vue";
import Voucher from "../views/Voucher/AppVoucher.vue";
import DotVoucher from "../views/Voucher/DotVoucher.vue";
import FormAddPgg from "../views/Voucher/components/FormAddPgg.vue";
import FormUpdatePgg from "@/views/Voucher/components/FormUpdatePgg.vue";
import LichSuHD from "../views/Bill/LichSuHoaDon.vue";
import HoaDon from "../views/Bill/HoaDon.vue";
import HoaDonChiTiet from "../views/Bill/HoaDonChiTiet.vue";
import ShowHoaDon from "../views/Bill/ShowHoaDon.vue";
import NhanVien from "../views/AccountType/NhanVien.vue";
import EmployeeForm from "@/views/AccountType/EmployeeForm.vue";
import KhachHang from "../views/AccountType/KhachHang.vue";
import CustmerForm from "@/views/AccountType/CustmerForm.vue";
import NotFound from "../views/NotFound.vue";
import UpdateNhanVien from "@/views/AccountType/UpdateNhanVien.vue";
import UpdateCustmer from "@/views/AccountType/UpdateCustmer.vue";
import SanPham from "@/views/Products/SanPham/SanPham.vue";
import NhaSanXuat from "@/views/Products/Manufacturer/Manufacturer.vue";
import CumCamera from "@/views/Products/CameraSanPham/CumCamera.vue";
import CongNgheManHinh from "@/views/Products/Screen/CongNgheMH.vue";
import HoTroCongNgheSac from "@/views/Products/Sac/HoTroCongNgheSac.vue";
import Ram from "@/views/Products/MemorySanPham/RamSanPham.vue";
import BoNhoTrong from "@/views/Products/MemorySanPham/BoNhoTrong.vue";
import BoNhoNgoai from "@/views/Products/MemorySanPham/BoNhoNgoai.vue";
import Cpu from "@/views/Products/Chip/CPU.vue";
import Gpu from "@/views/Products/Chip/GPU.vue";
import CongNgheMang from "@/views/Products/Other/CongNgheMang.vue";
import HeDieuHanh from "@/views/Products/Other/HeDieuHanh.vue";
import KhangNuocBui from "@/views/Products/Other/KhangBuiNuoc.vue";
import MauSac from "@/views/Products/Other/MauSac.vue";
import Pin from "@/views/Products/Other/PinSanPham.vue";
import Sim from "@/views/Products/Other/SimSanPham.vue";
import ThietKe from "@/views/Products/Other/ThietKe.vue";
import EditSanPham from "@/views/Products/SanPham/EditSanPham.vue";
import SanPhamChiTiet from "@/views/Products/SanPham/SanPhamChiTiet.vue";
import EditSanPhamChiTiet from "@/views/Products/SanPham/EditSanPhamChiTiet.vue";
import AddChiTietSanPham from "@/views/Products/SanPham/AddChiTietSanPham.vue";
import AddNhaSanXuat from "@/views/Products/Manufacturer/ManufacturerAdd.vue";
import EditNhaSanXuat from "@/views/Products/Manufacturer/ManufacturerEdit.vue";
import AddScreen from "@/views/Products/Screen/AddCongNgheManHinh.vue";
import EditScreen from "@/views/Products/Screen/EditCongNgheManHinh.vue";


const routes: Array<RouteRecordRaw> = [
  // Base redirect to avoid NotFound when using '/'
  { path: '/', redirect: '/home' },
  // --- Public Client Routes (Guest, Customer, Staff, Admin) ---
  { path: "/home", name: "HomePage", component: HomePage, meta: { layout: "empty" } },
  { path: "/cart", name: "CartPage", component: CartPage, meta: { layout: "empty" } },
  { path: "/checkout", name: "CheckoutPage", component: CheckoutPage, meta: { layout: "empty" } },
  { path: "/shop", name: "ShopPage", component: ShopPage, meta: { layout: "empty" } },
  { path: "/product/:id", name: "ProductDetail", component: ProductDetail, meta: { layout: "empty" } },
  { path: "/about", name: "AboutPage", component: About, meta: { layout: "empty" } },
  { path: "/blog", name: "BlogPage", component: Blog, meta: { layout: "empty" } },
  { path: "/contact", name: "ContactPage", component: Contact, meta: { layout: "empty" } },
  { path: "/outlet-sale", name: "OutletSalePage", component: OutletSale, meta: { layout: "empty" } },

  // --- Auth Routes ---
  { path: "/login", name: "Login", component: Login, meta: { layout: "empty" } },
  { path: "/dang-ky", name: "DangKy", component: DangKy, meta: { layout: "empty" } },
  { path: "/quen-mk", name: "Quenmk", component: QuenMk, meta: { layout: "empty" } },
  { path: "/update-mk", name: "updatemk", component: UpdateMk, meta: { layout: "empty" } },
  // OAuth2 callbacks (Google)
  { path: "/oauth2/success", name: "OAuth2Success", component: () => import("@/views/LoginView/OAuth2Success.vue"), meta: { layout: "empty" } },
  { path: "/oauth2/failure", name: "OAuth2Failure", component: () => import("@/views/LoginView/OAuth2Failure.vue"), meta: { layout: "empty" } },

  // --- Protected Routes (Admin & Staff) ---
  // STAFF & ADMIN
  { path: "/dashboard", name: "Dashboard", component: Dashboard, meta: { breadcrumb: ["Thống kê"], roles: ['ADMIN', 'STAFF'] } },
  { path: "/ban-hang", name: "BanHangTaiQuay", component: () => import("@/views/BanHang/BanHang.vue"), meta: { breadcrumb: ["Bán Hàng Tại Quầy"], roles: ['ADMIN', 'STAFF'] } },
  { path: "/hoa-don", name: "HoaDon", component: HoaDon, meta: { breadcrumb: ["Hóa đơn"], roles: ['ADMIN', 'STAFF'] } },
  { path: "/show-hoa-don/:id", name: "ShowHoaDon", component: ShowHoaDon, props: true, meta: { breadcrumb: (route: any) => ["Hóa đơn", `Chi tiết hóa đơn #${route.params.id}`], roles: ['ADMIN', 'STAFF'] } },
  { path: "/lich-su-hoa-don", name: "LichSuHD", component: LichSuHD, meta: { breadcrumb: ["Lịch sử hóa đơn"], roles: ['ADMIN', 'STAFF'] } },
  { path: "/hoa-don-chi-tiet", name: "HDCT", component: HoaDonChiTiet, meta: { breadcrumb: ["Hóa đơn", "Hóa đơn chi tiết"], roles: ['ADMIN', 'STAFF'] } },

  // ADMIN ONLY
  { path: "/phieu-giam-gia", name: "Voucher", component: Voucher, meta: { breadcrumb: ["Phiếu Giảm Giá", "Phiếu giảm giá"], roles: ['ADMIN'] } },
  { path: "/dot-giam-gia", name: "DotVoucher", component: DotVoucher, meta: { breadcrumb: ["Phiếu Giảm Giá", "Đợt giảm giá"], roles: ['ADMIN'] } },
  { path: "/add-phieu-giam-gia", name: "FormAddPgg", component: FormAddPgg, meta: { breadcrumb: ["Phiếu Giảm Giá", "Phiếu giảm giá", "Thêm phiếu giảm giá"], roles: ['ADMIN'] } },
  { path: "/update-phieu-giam-gia/:id", name: "FormUpdatePgg", component: FormUpdatePgg, props: true, meta: { breadcrumb: (route: any) => ["Phiếu Giảm Giá", "Phiếu giảm giá", `Cập nhật phiếu giảm giá #${route.params.id}`], roles: ['ADMIN'] } },
  { path: "/ViewAddDotGiamGia", name: "ViewAddDotGiamGia", component: () => import("@/views/Voucher/ViewAddDotGiamGia.vue"), meta: { breadcrumb: ["Phiếu Giảm Giá", "Đợt giảm giá", "Thêm đợt giảm giá"], roles: ['ADMIN'] } },
  { path: "/nhan-vien", name: "NhanVien", component: NhanVien, meta: { breadcrumb: ["Tài khoản", "Nhân viên"], roles: ['ADMIN'] } },
  { path: "/them-nhan-vien", name: "ThemNhanVien", component: EmployeeForm, meta: { breadcrumb: ["Tài khoản", "Nhân viên", "Thêm nhân viên"], roles: ['ADMIN'] } },
  { path: "/update-nhan-vien", name: "UpdateNhanVien", component: UpdateNhanVien, meta: { breadcrumb: ["Tài khoản", "Nhân viên", "Cập nhật nhân viên"], roles: ['ADMIN'] } },
  { path: "/khach-hang", name: "KhachHang", component: KhachHang, meta: { breadcrumb: ["Tài khoản", "Khách hàng"], roles: ['ADMIN'] } },
  { path: "/them-khach-hang", name: "ThemKhachHang", component: CustmerForm, meta: { breadcrumb: ["Tài khoản", "Khách hàng", "Thêm khách hàng"], roles: ['ADMIN'] } },
  { path: "/update-khach-hang", name: "UpdateKhachHang", component: UpdateCustmer, meta: { breadcrumb: ["Tài khoản", "Khách hàng", "Cập nhật khách hàng"], roles: ['ADMIN'] } },
  // Product Routes (ADMIN ONLY)
  { path: "/products", name: "products", component: SanPham, meta: { breadcrumb: ["Sản phẩm", "Danh sách sản phẩm"], roles: ['ADMIN'] } },
  { path: "/manufacturer", name: "manufacturer", component: NhaSanXuat, meta: { breadcrumb: ["Sản phẩm", "Nhà sản xuất"], roles: ['ADMIN'] } },
  { path: "/manufacturer/add", name: "addmanufacturer", component: AddNhaSanXuat, meta: { breadcrumb: ["Sản phẩm", "Nhà sản xuất", "Thêm Nhà Sản Xuất"], roles: ['ADMIN'] } },
  { path: "/manufacturer/edit/:id", name: "editmanufacturer", component: EditNhaSanXuat, meta: { breadcrumb: ["Sản phẩm", "Nhà sản xuất", "Sửa Nhà Sản Xuất"], roles: ['ADMIN'] } },
  { path: "/camera-module", name: "cameraModule", component: CumCamera, meta: { breadcrumb: ["Sản phẩm", "Cụm Camera"], roles: ['ADMIN'] } },
  { path: "/screens/technology", name: "screenTechnology", component: CongNgheManHinh, meta: { breadcrumb: ["Sản phẩm", "Công nghệ màn hình"], roles: ['ADMIN'] } },
  { path: "/screens/technology/add", name: "addscreenTechnology", component: AddScreen, meta: { breadcrumb: ["Sản phẩm", "Công nghệ màn hình", "Thêm Công nghệ màn hình"], roles: ['ADMIN'] } },
  { path: "/screens/technology/edit/:id", name: "editscreenTechnology", component: EditScreen, meta: { breadcrumb: ["Sản phẩm", "Công nghệ màn hình", "Sửa Công nghệ màn hình"], roles: ['ADMIN'] } },
  { path: "/charging/tech-support", name: "chargingTechSupport", component: HoTroCongNgheSac, meta: { breadcrumb: ["Sản phẩm", "Hỗ trợ sạc"], roles: ['ADMIN'] } },
  { path: "/ram", name: "ram", component: Ram, meta: { breadcrumb: ["Sản phẩm", "RAM"], roles: ['ADMIN'] } },
  { path: "/internal-storage", name: "internalStorage", component: BoNhoTrong, meta: { breadcrumb: ["Sản phẩm", "Bộ nhớ trong"], roles: ['ADMIN'] } },
  { path: "/external-storage", name: "externalStorage", component: BoNhoNgoai, meta: { breadcrumb: ["Sản phẩm", "Bộ nhớ ngoài"], roles: ['ADMIN'] } },
  { path: "/cpu", name: "cpu", component: Cpu, meta: { breadcrumb: ["Sản phẩm", "CPU"], roles: ['ADMIN'] } },
  { path: "/gpu", name: "gpu", component: Gpu, meta: { breadcrumb: ["Sản phẩm", "GPU"], roles: ['ADMIN'] } },
  { path: "/network-technology", name: "networkTechnology", component: CongNgheMang, meta: { breadcrumb: ["Sản phẩm", "Công nghệ mạng"], roles: ['ADMIN'] } },
  { path: "/operating-system", name: "operatingSystem", component: HeDieuHanh, meta: { breadcrumb: ["Sản phẩm", "Hệ điều hành"], roles: ['ADMIN'] } },
  { path: "/dust-water-resistance", name: "dustWaterResistance", component: KhangNuocBui, meta: { breadcrumb: ["Sản phẩm", "Kháng bụi/nước"], roles: ['ADMIN'] } },
  { path: "/color", name: "color", component: MauSac, meta: { breadcrumb: ["Sản phẩm", "Màu sắc"], roles: ['ADMIN'] } },
  { path: "/battery", name: "battery", component: Pin, meta: { breadcrumb: ["Sản phẩm", "Pin"], roles: ['ADMIN'] } },
  { path: "/sim", name: "sim", component: Sim, meta: { breadcrumb: ["Sản phẩm", "SIM"], roles: ['ADMIN'] } },
  { path: "/design", name: "design", component: ThietKe, meta: { breadcrumb: ["Sản phẩm", "Thiết kế"], roles: ['ADMIN'] } },

  // --- Product CRUD + Details Routes (ADMIN ONLY) ---
  { path: "/products/add", name: "AddSanPham", component: AddChiTietSanPham, meta: { breadcrumb: ["Sản phẩm", "Thêm sản phẩm"], roles: ['ADMIN'] } },
  { path: "/products/edit/:id", name: "EditSanPham", component: EditSanPham, props: true, meta: { breadcrumb: (route: any) => ["Sản phẩm", `Sửa sản phẩm #${route.params.id}`], roles: ['ADMIN'] } },
  { path: "/products/:id", name: "SanPhamChiTiet", component: SanPhamChiTiet, props: true, meta: { breadcrumb: (route: any) => ["Sản phẩm", `Chi tiết sản phẩm #${route.params.id}`], roles: ['ADMIN'] } },
  { path: "/products/details/:productId/edit/:detailId", name: "EditSanPhamChiTiet", component: EditSanPhamChiTiet, props: true, meta: { breadcrumb: (route: any) => ["Sản phẩm", `Sản phẩm #${route.params.productId}`, `Sửa chi tiết #${route.params.detailId}`], roles: ['ADMIN'] } },

  // --- 404 Not Found ---
  { path: "/:pathMatch(.*)*", component: NotFound },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

// --- Global Navigation Guard ---
router.beforeEach((to, from, next) => {
  const requiredRoles = to.meta.roles as string[];

  // 1. If the route doesn't require any roles, let everyone pass.
  if (!requiredRoles || requiredRoles.length === 0) {
    return next();
  }

  // 2. Get user from localStorage.
  const userString = localStorage.getItem('user');
  
  // 3. If route requires roles but user is not logged in, redirect to login.
  if (!userString) {
    return next('/login');
  }

  // 4. Parse user data and get their role.
  try {
    const user = JSON.parse(userString);
    // The role is nested in idQuyenHan.ma based on your backend structure
    const userRole = user?.idQuyenHan?.ma;

    // 5. If user is logged in but has no role, something is wrong. Redirect to login.
    if (!userRole) {
      localStorage.removeItem('user'); // Clean up bad data
      return next('/login');
    }

    // 6. Check if the user's role is in the list of required roles.
    if (requiredRoles.includes(userRole)) {
      // Authorized: let them pass.
      return next();
    } else {
      // Not authorized: redirect to home page.
      return next('/');
    }
  } catch (e) {
    // If parsing fails, clean up and redirect to login.
    localStorage.removeItem('user');
    return next('/login');
  }
});

export default router;