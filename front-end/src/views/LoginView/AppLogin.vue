<template>
  <div class="min-h-screen bg-gradient-to-br from-orange-50 via-white to-red-50 flex items-center justify-center px-4 py-12">
    <div class="w-full max-w-md">
      <!-- Logo / Brand -->
      <div class="text-center mb-8">
<!--        <div class="flex justify-center mb-4">-->
<!--          <div class="bg-gradient-to-br from-orange-500 to-red-600 p-4 rounded-2xl shadow-lg">-->
<!--            <svg class="w-12 h-12 text-white" fill="currentColor" viewBox="0 0 24 24">-->
<!--              <path d="M18.71 19.5c-.83 1.24-1.71 2.45-3.05 2.47-1.34.03-1.77-.79-3.29-.79-1.53 0-2 .77-3.27.82-1.31.05-2.3-1.32-3.14-2.53C4.25 17 2.94 12.45 4.7 9.39c.87-1.52 2.43-2.48 4.12-2.51 1.28-.02 2.5.87 3.29.87.78 0 2.26-1.07 3.81-.91.65.03 2.47.26 3.64 1.98-.09.06-2.17 1.28-2.15 3.81.03 3.02 2.65 4.03 2.68 4.04-.03.07-.42 1.44-1.38 2.83M13 3.5c.73-.83 1.94-1.46 2.94-1.5.13 1.17-.34 2.35-1.04 3.19-.69.85-1.83 1.51-2.95 1.42-.15-1.15.41-2.35 1.05-3.11z"/>-->
<!--            </svg>-->
<!--          </div>-->
<!--        </div>-->
        <h1 class="text-3xl font-black text-gray-900 mb-2">TopZone</h1>
        <p class="text-gray-600">Đăng nhập để tiếp tục</p>
      </div>

      <!-- Login Card -->
      <div class="bg-white rounded-2xl shadow-2xl p-8 border-0">
        <div class="mb-6">
          <h2 class="text-2xl font-bold text-gray-900 mb-2">Chào mừng trở lại!</h2>
          <p class="text-gray-600 text-sm">Nhập thông tin để đăng nhập</p>
        </div>

        <!-- Form -->
        <form @submit.prevent="login" class="space-y-5">
          <!-- Username Field -->
          <div class="space-y-2">
            <label class="block text-sm font-bold text-gray-700">
              Tên đăng nhập
            </label>
            <div class="relative">
              <svg class="absolute left-3 top-3 h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
              </svg>
              <input
                v-model="tenDangNhap"
                type="text"
                placeholder="Nhập tên đăng nhập"
                class="w-full pl-10 pr-4 py-2.5 border-2 border-gray-200 rounded-lg 
                      focus:ring-2 focus:ring-orange-500 focus:border-orange-500 
                      transition-all duration-200 text-sm"
                required
              />
            </div>
          </div>

          <!-- Password Field -->
          <div class="space-y-2">
            <label class="block text-sm font-bold text-gray-700">
              Mật khẩu
            </label>
            <div class="relative">
              <svg class="absolute left-3 top-3 h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z" />
              </svg>
              <input
                v-model="matKhau"
                :type="showPassword ? 'text' : 'password'"
                placeholder="Nhập mật khẩu"
                class="w-full pl-10 pr-12 py-2.5 border-2 border-gray-200 rounded-lg 
                      focus:ring-2 focus:ring-orange-500 focus:border-orange-500 
                      transition-all duration-200 text-sm"
                required
              />
              <button
                type="button"
                @click="showPassword = !showPassword"
                class="absolute right-3 top-3 text-gray-400 hover:text-gray-600"
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
          </div>

          <!-- Remember & Forgot -->
          <div class="flex items-center justify-between text-sm">
            <label class="flex items-center gap-2 cursor-pointer">
              <input
                v-model="rememberMe"
                type="checkbox"
                class="w-4 h-4 rounded border-gray-300 text-orange-600 focus:ring-orange-500"
              />
              <span class="text-gray-600">Ghi nhớ đăng nhập</span>
            </label>
            <router-link
              to="/quen-mk"
              class="text-orange-600 hover:text-orange-700 font-semibold transition-colors"
            >
              Quên mật khẩu?
            </router-link>
          </div>

          <!-- Submit Button -->
          <button
            type="submit"
            :disabled="loading"
            class="w-full py-3 px-4 bg-gradient-to-r from-orange-500 to-red-600 
                  text-white rounded-lg hover:from-orange-600 hover:to-red-700
                  focus:ring-2 focus:ring-orange-500 focus:ring-offset-2 
                  transition-all duration-200 font-bold shadow-lg hover:shadow-xl
                  disabled:opacity-50 disabled:cursor-not-allowed
                  flex items-center justify-center gap-2"
          >
            <span v-if="!loading">Đăng nhập</span>
            <span v-else>Đang xử lý...</span>
            <svg v-if="!loading" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7l5 5m0 0l-5 5m5-5H6" />
            </svg>
          </button>

          <!-- Error Message -->
          <transition name="fade">
            <div
              v-if="error"
              class="bg-red-50 border-2 border-red-200 text-red-700 px-4 py-3 rounded-lg flex items-center gap-2"
            >
              <svg class="w-5 h-5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
              <span class="text-sm font-medium">{{ error }}</span>
            </div>
          </transition>
        </form>

        <!-- Divider -->
        <div class="relative my-6">
          <div class="absolute inset-0 flex items-center">
            <span class="w-full border-t border-gray-200" />
          </div>
          <div class="relative flex justify-center text-xs uppercase">
            <span class="bg-white px-2 text-gray-500 font-semibold">
              Hoặc đăng nhập với
            </span>
          </div>
        </div>

        <!-- Social Login Buttons -->
        <div class="space-y-3">
          <!-- Google Login Button -->
          <button
            type="button"
            @click="loginWithGoogle"
            class="w-full flex items-center justify-center gap-3 px-4 py-3 
                  border-2 border-gray-200 rounded-lg hover:bg-gray-50 
                  transition-all font-semibold text-gray-700 hover:border-gray-300"
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
            Đăng nhập với Google
          </button>

          <!-- Facebook Login Button -->
<!--          <button-->
<!--            type="button"-->
<!--            @click="loginWithFacebook"-->
<!--            class="w-full flex items-center justify-center gap-3 px-4 py-3 -->
<!--                  border-2 border-gray-200 rounded-lg hover:bg-gray-50 -->
<!--                  transition-all font-semibold text-gray-700 hover:border-gray-300"-->
<!--          >-->
<!--            <svg class="w-5 h-5" fill="#1877F2" viewBox="0 0 24 24">-->
<!--              <path d="M24 12.073c0-6.627-5.373-12-12-12s-12 5.373-12 12c0 5.99 4.388 10.954 10.125 11.854v-8.385H7.078v-3.47h3.047V9.43c0-3.007 1.792-4.669 4.533-4.669 1.312 0 2.686.235 2.686.235v2.953H15.83c-1.491 0-1.956.925-1.956 1.874v2.25h3.328l-.532 3.47h-2.796v8.385C19.612 23.027 24 18.062 24 12.073z" />-->
<!--            </svg>-->
<!--            Đăng nhập với Facebook-->
<!--          </button>-->
        </div>

        <!-- Register Link -->
        <div class="mt-6 text-center text-sm">
          <span class="text-gray-600">Chưa có tài khoản? </span>
          <router-link
            to="/dang-ky"
            class="text-orange-600 hover:text-orange-700 font-bold transition-colors"
          >
            Đăng ký ngay
          </router-link>
        </div>
      </div>

      <!-- Terms & Privacy -->
      <p class="text-center text-xs text-gray-500 mt-6">
        Bằng việc đăng nhập, bạn đồng ý với
        <a href="#" class="text-orange-600 hover:underline">Điều khoản dịch vụ</a>
        và
        <a href="#" class="text-orange-600 hover:underline">Chính sách bảo mật</a>
        của chúng tôi
      </p>
    </div>

    <!-- Toast Notification -->
    <Transition name="toast">
      <div
        v-if="toast.show"
        :class="[
          'fixed bottom-4 right-4 px-6 py-4 rounded-lg shadow-2xl text-white z-50 flex items-center gap-3',
          toast.type === 'success' ? 'bg-green-500' : toast.type === 'error' ? 'bg-red-500' : 'bg-blue-500'
        ]"
      >
        <svg v-if="toast.type === 'success'" class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
        </svg>
        <svg v-else-if="toast.type === 'error'" class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
        </svg>
        <svg v-else class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
        <div>
          <p class="font-semibold">{{ toast.message }}</p>
          <p v-if="toast.description" class="text-sm opacity-90">{{ toast.description }}</p>
        </div>
      </div>
    </Transition>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import useLogin from "@/views/LoginView/Login.js";

const { tenDangNhap, matKhau, error, login } = useLogin();

const showPassword = ref(false);
const rememberMe = ref(false);
const loading = ref(false);

const toast = ref({
  show: false,
  message: '',
  description: '',
  type: 'success',
});

const showToast = (message, description = '', type = 'success') => {
  toast.value = { show: true, message, description, type };
  setTimeout(() => {
    toast.value.show = false;
  }, 3000);
};

const loginWithGoogle = () => {
  showToast(
    'Chức năng đang phát triển',
    'Đăng nhập Google sẽ sớm có mặt',
    'info'
  );

  // TODO: Implement Google OAuth
  // window.location.href = 'YOUR_BACKEND_URL/auth/google';
};

const loginWithFacebook = () => {
  showToast(
    'Chức năng đang phát triển',
    'Đăng nhập Facebook sẽ sớm có mặt',
    'info'
  );

  // TODO: Implement Facebook OAuth
  // window.location.href = 'YOUR_BACKEND_URL/auth/facebook';
};
</script>

<style scoped>
input:focus {
  outline: none;
}

/* Animation cho error message */
.fade-enter-active,
.fade-leave-active {
  transition: all 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}

/* Toast animation */
.toast-enter-active,
.toast-leave-active {
  transition: all 0.3s ease;
}

.toast-enter-from,
.toast-leave-to {
  opacity: 0;
  transform: translateX(100px);
}
</style>