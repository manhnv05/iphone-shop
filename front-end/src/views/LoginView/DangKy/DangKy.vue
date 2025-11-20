<template>
  <ToastNotification ref="toastRef" />

  <div class="min-h-screen bg-gradient-to-br from-orange-50 via-white to-red-50 flex items-center justify-center px-4 py-12">
    <div class="w-full max-w-md">
      <!-- Logo / Brand -->
      <div class="text-center mb-8">
<!--        <div class="flex justify-center mb-4">-->
<!--          <div class="bg-gradient-to-br from-orange-500 to-red-600 p-4 rounded-2xl shadow-lg">-->
<!--            <img class="w-12 h-12" src="../../../assets/Logo_Mobile_World_vector.png" alt="Logo">-->
<!--          </div>-->
<!--        </div>-->
        <h1 class="text-3xl font-black text-gray-900 mb-2">TopZone</h1>
        <p class="text-gray-600">Tạo tài khoản mới</p>
      </div>

      <!-- Register Card -->
      <div class="bg-white rounded-2xl shadow-2xl p-8 border-0">
        <div class="mb-6">
          <h2 class="text-2xl font-bold text-gray-900 mb-2">Đăng ký tài khoản</h2>
          <p class="text-gray-600 text-sm">Điền thông tin để tạo tài khoản mới</p>
        </div>
        
        
        <form @submit.prevent="AddTK" class="space-y-4">
          <!-- Tên đăng nhập -->
          <div class="space-y-2">
            <label class="block text-sm font-bold text-gray-700">
              Tên đăng nhập <span class="text-red-500">*</span>
            </label>
            <div class="relative">
              <svg class="absolute left-3 top-3 h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
              </svg>
              <input
                v-model="taikhoan.tenDangNhap"
                type="text"
                placeholder="Nhập tên đăng nhập"
                class="w-full pl-10 pr-4 py-2.5 border-2 border-gray-200 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition-all text-sm"
                :class="{ 'border-red-500 focus:ring-red-500': errors.tenDangNhap }"
              />
            </div>
            <transition name="fade">
              <span v-if="errors.tenDangNhap" class="text-xs text-red-500 flex items-center gap-1">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                {{ errors.tenDangNhap }}
              </span>
            </transition>
          </div>

          <!-- Email -->
          <div class="space-y-2">
            <label class="block text-sm font-bold text-gray-700">
              Email <span class="text-red-500">*</span>
            </label>
            <div class="relative">
              <svg class="absolute left-3 top-3 h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
              </svg>
              <input
                v-model="taikhoan.email"
                type="email"
                placeholder="Nhập email của bạn"
                class="w-full pl-10 pr-24 py-2.5 border-2 border-gray-200 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition-all text-sm"
                :class="{ 'border-red-500 focus:ring-red-500': errors.email }"
              />
              <button
                type="button"
                @click="sendOTP"
                :disabled="otpSending"
                class="absolute right-2 top-1/2 transform -translate-y-1/2 px-3 py-1.5 text-xs font-bold text-white bg-orange-600 rounded-lg hover:bg-orange-700 transition-all disabled:opacity-50 disabled:cursor-not-allowed"
              >
                {{ otpSending ? 'Đang gửi...' : otpSent ? 'Gửi lại' : 'Gửi mã' }}
              </button>
            </div>
            <transition name="fade">
              <span v-if="errors.email" class="text-xs text-red-500 flex items-center gap-1">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                {{ errors.email }}
              </span>
            </transition>
            <transition name="fade">
              <div v-if="otpSent && !errors.email" class="flex items-center gap-1 text-xs text-green-600">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
                Mã OTP đã được gửi đến email của bạn
              </div>
            </transition>
          </div>

          <!-- Mã xác nhận -->
          <div class="space-y-2">
            <label class="block text-sm font-bold text-gray-700">
              Mã xác nhận <span class="text-red-500">*</span>
            </label>
            <div class="relative">
              <svg class="absolute left-3 top-3 h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 7a2 2 0 012 2m4 0a6 6 0 01-7.743 5.743L11 17H9v2H7v2H4a1 1 0 01-1-1v-2.586a1 1 0 01.293-.707l5.964-5.964A6 6 0 1121 9z" />
              </svg>
              <input
                v-model="otp"
                type="text"
                placeholder="Nhập mã 6 số từ email"
                maxlength="6"
                class="w-full pl-10 pr-4 py-2.5 border-2 border-gray-200 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition-all text-sm tracking-widest font-semibold"
                :class="{ 'border-red-500 focus:ring-red-500': errors.otp }"
              />
            </div>
            <transition name="fade">
              <span v-if="errors.otp" class="text-xs text-red-500 flex items-center gap-1">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                {{ errors.otp }}
              </span>
            </transition>
          </div>

          <!-- Mật khẩu -->
          <div class="space-y-2">
            <label class="block text-sm font-bold text-gray-700">
              Mật khẩu <span class="text-red-500">*</span>
            </label>
            <div class="relative">
              <svg class="absolute left-3 top-3 h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z" />
              </svg>
              <input
                v-model="taikhoan.matKhau"
                :type="showPassword ? 'text' : 'password'"
                placeholder="Nhập mật khẩu"
                class="w-full pl-10 pr-12 py-2.5 border-2 border-gray-200 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition-all text-sm"
                :class="{ 'border-red-500 focus:ring-red-500': errors.matKhau }"
              />
              <button
                type="button"
                @click="togglePassword"
                class="absolute right-3 top-3 text-gray-400 hover:text-gray-600 transition-colors"
              >
                <svg v-if="!showPassword" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z" />
                </svg>
                <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.88 9.88l-3.29-3.29m7.532 7.532l3.29 3.29M3 3l3.59 3.59m0 0A9.953 9.953 0 0112 5c4.478 0 8.268 2.943 9.543 7a10.025 10.025 0 01-4.132 5.411m0 0L21 21" />
                </svg>
              </button>
            </div>
            <transition name="fade">
              <span v-if="errors.matKhau" class="text-xs text-red-500 flex items-center gap-1">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                {{ errors.matKhau }}
              </span>
            </transition>

            <!-- Password Strength Indicator -->
            <div v-if="taikhoan.matKhau" class="space-y-1">
              <div class="flex gap-1">
                <div class="h-1 flex-1 rounded-full transition-all" :class="passwordStrength >= 1 ? 'bg-red-500' : 'bg-gray-200'"></div>
                <div class="h-1 flex-1 rounded-full transition-all" :class="passwordStrength >= 2 ? 'bg-orange-500' : 'bg-gray-200'"></div>
                <div class="h-1 flex-1 rounded-full transition-all" :class="passwordStrength >= 3 ? 'bg-yellow-500' : 'bg-gray-200'"></div>
                <div class="h-1 flex-1 rounded-full transition-all" :class="passwordStrength >= 4 ? 'bg-green-500' : 'bg-gray-200'"></div>
              </div>
              <p class="text-xs" :class="{
                'text-red-500': passwordStrength === 1,
                'text-orange-500': passwordStrength === 2,
                'text-yellow-500': passwordStrength === 3,
                'text-green-500': passwordStrength === 4
              }">
                {{ passwordStrengthText }}
              </p>
            </div>
          </div>

          <!-- Xác nhận mật khẩu -->
          <div class="space-y-2">
            <label class="block text-sm font-bold text-gray-700">
              Xác nhận mật khẩu <span class="text-red-500">*</span>
            </label>
            <div class="relative">
              <svg class="absolute left-3 top-3 h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z" />
              </svg>
              <input
                v-model="confirmPassword"
                :type="showConfirmPassword ? 'text' : 'password'"
                placeholder="Nhập lại mật khẩu"
                class="w-full pl-10 pr-12 py-2.5 border-2 border-gray-200 rounded-lg focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition-all text-sm"
                :class="{ 'border-red-500 focus:ring-red-500': errors.confirmPassword }"
              />
              <button
                type="button"
                @click="toggleConfirmPassword"
                class="absolute right-3 top-3 text-gray-400 hover:text-gray-600 transition-colors"
              >
                <svg v-if="!showConfirmPassword" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z" />
                </svg>
                <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.88 9.88l-3.29-3.29m7.532 7.532l3.29 3.29M3 3l3.59 3.59m0 0A9.953 9.953 0 0112 5c4.478 0 8.268 2.943 9.543 7a10.025 10.025 0 01-4.132 5.411m0 0L21 21" />
                </svg>
              </button>
            </div>
            <transition name="fade">
              <span v-if="errors.confirmPassword" class="text-xs text-red-500 flex items-center gap-1">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                {{ errors.confirmPassword }}
              </span>
            </transition>
            <transition name="fade">
              <div v-if="confirmPassword && taikhoan.matKhau === confirmPassword && !errors.confirmPassword" class="flex items-center gap-1 text-xs text-green-600">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
                Mật khẩu khớp
              </div>
            </transition>
          </div>

          <!-- Terms & Conditions -->
          <div class="flex items-start gap-2 pt-2">
            <input
              v-model="acceptTerms"
              type="checkbox"
              id="terms"
              class="w-4 h-4 mt-0.5 rounded border-gray-300 text-orange-600 focus:ring-orange-500"
            />
            <label for="terms" class="text-xs text-gray-600">
              Tôi đồng ý với
              <a href="#" class="text-orange-600 hover:underline font-semibold">Điều khoản dịch vụ</a>
              và
              <a href="#" class="text-orange-600 hover:underline font-semibold">Chính sách bảo mật</a>
              của TopZone
            </label>
          </div>

          <!-- Submit Button -->
          <button
            type="submit"
            :disabled="loading || !acceptTerms"
            class="w-full py-3 px-4 bg-gradient-to-r from-orange-500 to-red-600 text-white rounded-lg hover:from-orange-600 hover:to-red-700 focus:ring-2 focus:ring-orange-500 focus:ring-offset-2 transition-all duration-200 font-bold shadow-lg hover:shadow-xl disabled:opacity-50 disabled:cursor-not-allowed flex items-center justify-center gap-2"
          >
            <span v-if="!loading">Đăng ký</span>
            <span v-else>Đang xử lý...</span>
            <svg v-if="!loading" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7l5 5m0 0l-5 5m5-5H6" />
            </svg>
          </button>

          <!-- Divider -->
          <div class="flex items-center gap-3 my-2">
            <div class="flex-1 h-px bg-gray-200"></div>
            <span class="text-gray-400 text-xs">hoặc đăng ký với</span>
            <div class="flex-1 h-px bg-gray-200"></div>
          </div>




          <div class="space-y-3 mb-6">
            <!-- Google Register Button -->
            <button
              type="button"
              @click="registerWithGoogle"
              class="w-full flex items-center justify-center gap-3 px-4 py-3 border-2 border-gray-200 rounded-lg hover:bg-gray-50 transition-all font-semibold text-gray-700 hover:border-gray-300"
            >
              <svg class="w-5 h-5" viewBox="0 0 24 24">
                <path
                  fill="#4285F4"
                  d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"
                />
                <path
                  fill="#34A853"
                  d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"
                />
                <path
                  fill="#FBBC05"
                  d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z"
                />
                <path
                  fill="#EA4335"
                  d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"
                />
              </svg>
              Đăng ký với Google
            </button>
          </div>
          
          
        </form>

        <!-- Login Link -->
        <div class="mt-6 text-center text-sm">
          <span class="text-gray-600">Đã có tài khoản? </span>
          <router-link to="/login" class="text-orange-600 hover:text-orange-700 font-bold transition-colors">
            Đăng nhập ngay
          </router-link>
        </div>
      </div>

      <!-- Help Text -->
      <p class="text-center text-xs text-gray-500 mt-6">
        Bạn cần hỗ trợ? Liên hệ
        <a href="#" class="text-orange-600 hover:underline font-semibold">1900 xxxx</a>
        hoặc
        <a href="#" class="text-orange-600 hover:underline font-semibold">support@topzone.vn</a>
      </p>
    </div>
  </div>

  <ConfirmModal
    :show="showConfirmModal"
    :message="'Bạn có muốn chuyển sang trang đăng nhập không?'"
    @confirm="chuyentrang"
    @cancel="showConfirmModal = false"
  />
</template>

<script setup lang="ts">
import axios from "axios";
import { ref, computed } from "vue";
import { useRouter } from "vue-router";
import ToastNotification from "@/components/ToastNotification.vue";
import ConfirmModal from "@/components/ConfirmModal.vue";

const showConfirmModal = ref(false);
const router = useRouter();
const toastRef = ref(null);

const showPassword = ref(false);
const showConfirmPassword = ref(false);
const confirmPassword = ref('');
const otp = ref('');
const loading = ref(false);
const otpSending = ref(false);
const otpSent = ref(false);
const acceptTerms = ref(false);

const errors = ref({
  tenDangNhap: '',
  email: '',
  otp: '',
  matKhau: '',
  confirmPassword: '',
});

const taikhoan = ref({
  tenDangNhap: '',
  email: '',
  soDienThoai: '',
  matKhau: '',
});

// Password strength calculator
const passwordStrength = computed(() => {
  const password = taikhoan.value.matKhau;
  if (!password) return 0;

  let strength = 0;
  if (password.length >= 8) strength++;
  if (/[a-z]/.test(password) && /[A-Z]/.test(password)) strength++;
  if (/\d/.test(password)) strength++;
  if (/[^a-zA-Z\d]/.test(password)) strength++;

  return strength;
});

const passwordStrengthText = computed(() => {
  const texts = ['', 'Yếu', 'Trung bình', 'Mạnh', 'Rất mạnh'];
  return texts[passwordStrength.value];
});

const togglePassword = () => {
  showPassword.value = !showPassword.value;
};

const toggleConfirmPassword = () => {
  showConfirmPassword.value = !showConfirmPassword.value;
};

const chuyentrang = () => {
  router.push({ path: '/' });
};

const registerWithGoogle = () => {
  // TODO: Implement Google OAuth Registration
  // Example:
  window.location.href = 'http://localhost:8080/oauth2/authorization/google';
};


function checktk() {
  let isValid = true;
  errors.value = { tenDangNhap: '', email: '', otp: '', matKhau: '', confirmPassword: '' };

  if (!taikhoan.value.tenDangNhap.trim()) {
    errors.value.tenDangNhap = 'Tên đăng nhập không được để trống!';
    isValid = false;
  }
  if (!taikhoan.value.email.trim()) {
    errors.value.email = 'Email không được để trống!';
    isValid = false;
  } else if (!/\S+@\S+\.\S+/.test(taikhoan.value.email)) {
    errors.value.email = 'Email không hợp lệ!';
    isValid = false;
  }
  if (!taikhoan.value.matKhau.trim()) {
    errors.value.matKhau = 'Mật khẩu không được để trống!';
    isValid = false;
  } else if (taikhoan.value.matKhau.length < 6) {
    errors.value.matKhau = 'Mật khẩu phải có ít nhất 6 ký tự!';
    isValid = false;
  }
  if (!confirmPassword.value.trim()) {
    errors.value.confirmPassword = 'Xác nhận mật khẩu không được để trống!';
    isValid = false;
  }
  if (taikhoan.value.matKhau && confirmPassword.value && taikhoan.value.matKhau !== confirmPassword.value) {
    errors.value.confirmPassword = 'Mật khẩu và xác nhận mật khẩu không khớp!';
    isValid = false;
  }

  return isValid;
}

function loginWithGoogle() {
  window.location.href = 'http://localhost:8080/oauth2/authorization/google';
}

async function sendOTP() {
  errors.value.tenDangNhap = '';
  errors.value.email = '';

  if (!taikhoan.value.tenDangNhap.trim()) {
    errors.value.tenDangNhap = 'Tên đăng nhập không được để trống!';
    return;
  }
  if (!taikhoan.value.email.trim()) {
    errors.value.email = 'Email không được để trống!';
    return;
  }
  if (!/\S+@\S+\.\S+/.test(taikhoan.value.email)) {
    errors.value.email = 'Email không hợp lệ!';
    return;
  }

  otpSending.value = true;

  try {
    const response = await axios.post('http://localhost:8080/tai-khoan/requestOtp', {
      email: taikhoan.value.email,
      tenDangNhap: taikhoan.value.tenDangNhap,
      matKhau: taikhoan.value.matKhau
    }, {
      headers: { 'Content-Type': 'application/json' },
    });

    otpSent.value = true;
    toastRef.value.kshowToast('success', response.data);
  } catch (error) {
    const errorMsg = error.response?.data || 'Lỗi khi gửi OTP!';
    if (errorMsg.includes("Tên đăng nhập")) {
      errors.value.tenDangNhap = errorMsg;
    } else if (errorMsg.includes("Email")) {
      errors.value.email = errorMsg;
    } else {
      toastRef.value.kshowToast('error', errorMsg);
    }
  } finally {
    otpSending.value = false;
  }
}

async function AddTK() {
  if (!checktk()) {
    return;
  }
  if (!otp.value.trim()) {
    errors.value.otp = 'Vui lòng nhập mã OTP!';
    return;
  }

  loading.value = true;

  const requestData = {
    tenDangNhap: taikhoan.value.tenDangNhap,
    email: taikhoan.value.email,
    matKhau: taikhoan.value.matKhau,
    otp: otp.value,
  };

  try {
    const response = await axios.post('http://localhost:8080/tai-khoan/addTk', requestData, {
      headers: { 'Content-Type': 'application/json' },
    });
    toastRef.value.kshowToast('success', 'Chúc mừng bạn đã đăng ký tài khoản thành công!');
    setTimeout(() => {
      showConfirmModal.value = true;
    }, 1000);
  } catch (error) {
    const errorMsg = error.response?.data.split(": ")[1] || 'Mã OTP không hợp lệ!';
    if (errorMsg.includes("Tên đăng nhập")) {
      errors.value.tenDangNhap = errorMsg;
    } else if (errorMsg.includes("Email")) {
      errors.value.email = errorMsg;
    } else {
      errors.value.otp = errorMsg;
    }
    toastRef.value.kshowToast('error', errorMsg);
  } finally {
    loading.value = false;
  }
}
</script>

<style scoped>
input:focus {
  outline: none;
}

.fade-enter-active,
.fade-leave-active {
  transition: all 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}
</style>