import { ref, inject } from "vue"; // Import inject
import { useRouter } from "vue-router";
import axios from "axios";

export default function useLogin() {
  const router = useRouter();
  const tenDangNhap = ref("");
  const matKhau = ref("");
  const error = ref("");

  // Inject the global login function provided in App.vue
  const updateUserState = inject('login');

  async function login() {
    error.value = ""; // Reset error before new attempt
    try {
      const response = await axios.post(
        "http://localhost:8080/tai-khoan/login",
        {
          tenDangNhap: tenDangNhap.value,
          matKhau: matKhau.value,
        },
        { withCredentials: true }
      );

      if (response.status === 200 && response.data) {
        // --- THIS IS THE FIX ---
        // 1. Update the global user state
        if (updateUserState) {
          updateUserState(response.data);
        } else {
          console.error("Login function from App.vue was not injected correctly.");
          error.value = "Lỗi hệ thống: Không thể cập nhật trạng thái người dùng.";
          return;
        }

        // 2. Redirect the user
        // Redirect based on role
        const userRole = response.data?.idQuyenHan?.ma;
        if (userRole === 'ADMIN' || userRole === 'STAFF') {
          router.push("/dashboard");
        } else {
          router.push("/"); // Redirect CUSTOMER and others to home page
        }

      } else {
        // This case might not be reached if server returns 401, but it's good for safety
        error.value = "Tên đăng nhập hoặc mật khẩu không đúng!";
      }
    } catch (err) {
      if (err.response && err.response.status === 401) {
        error.value = "Tên đăng nhập hoặc mật khẩu không chính xác.";
      } else {
        error.value = "Đăng nhập thất bại. Vui lòng kiểm tra lại kết nối.";
      }
      console.error("Login error:", err);
    }
  }

  return {
    tenDangNhap,
    matKhau,
    error,
    login,
  };
}