<template>
  <div class="min-h-screen flex items-center justify-center bg-gray-50 p-6">
    <div class="bg-white p-8 rounded-xl shadow max-w-md w-full text-center">
      <div class="mb-4">
        <svg class="w-12 h-12 text-green-500 mx-auto" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
        </svg>
      </div>
      <h1 class="text-2xl font-bold mb-2">Đăng nhập thành công</h1>
      <p class="text-gray-600 mb-6">Đang lấy thông tin tài khoản, vui lòng đợi...</p>
      <div class="flex items-center justify-center gap-2 text-sm text-gray-500">
        <span class="animate-pulse">●</span>
        <span class="animate-pulse" style="animation-delay: 0.2s">●</span>
        <span class="animate-pulse" style="animation-delay: 0.4s">●</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, inject } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const router = useRouter();
const updateUserState: any = inject('login');

onMounted(async () => {
  try {
    const resp = await axios.get('http://localhost:8080/tai-khoan/me', { withCredentials: true });
    const user = resp.data;
    if (updateUserState) {
      updateUserState(user);
    }
    const role = user?.idQuyenHan?.ma;
    if (role === 'ADMIN' || role === 'STAFF') {
      router.replace('/dashboard');
    } else {
      router.replace('/');
    }
  } catch (e) {
    // If cannot fetch user, go to login
    router.replace('/login');
  }
});
</script>
