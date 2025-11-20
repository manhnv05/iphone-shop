<template>
  <div class="min-h-screen flex items-center justify-center bg-gray-50 p-6">
    <div class="bg-white p-8 rounded-xl shadow max-w-md w-full text-center">
      <div class="mb-4">
        <svg class="w-12 h-12 text-red-500 mx-auto" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
        </svg>
      </div>
      <h1 class="text-2xl font-bold mb-2">Đăng nhập thất bại</h1>
      <p class="text-gray-600 mb-6">Không thể xác thực với Google. Vui lòng thử lại.</p>
      <router-link to="/login" class="px-4 py-2 bg-orange-600 text-white rounded-lg hover:bg-orange-700">
        Quay lại đăng nhập
      </router-link>
    </div>
  </div>
</template>

<script setup lang="ts">
</script>
