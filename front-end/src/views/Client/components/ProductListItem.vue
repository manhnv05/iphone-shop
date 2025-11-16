<template>
  <div class="bg-white rounded-lg shadow-sm overflow-hidden hover:shadow-md transition-shadow duration-300 p-4">
    <div class="flex gap-4">
      <!-- Product Image -->
      <div class="flex-shrink-0 cursor-pointer" @click="viewProduct">
        <div class="relative">
          <img
            :src="product.duongDanAnh || 'https://via.placeholder.com/200x200.png?text=iPhone'"
            :alt="product.tenSanPham"
            class="w-32 h-32 object-cover rounded-lg"
          />
          <!-- Discount Badge -->
          <div v-if="product.phanTramGiam > 0" class="absolute top-2 right-2 bg-red-500 text-white px-2 py-1 rounded text-xs font-bold">
            -{{ product.phanTramGiam }}%
          </div>
        </div>
      </div>

      <!-- Product Info -->
      <div class="flex-1 flex flex-col">
        <h3
          @click="viewProduct"
          class="text-lg font-semibold text-gray-800 mb-2 hover:text-blue-600 cursor-pointer line-clamp-2"
        >
          {{ product.tenSanPham }}
        </h3>

        <!-- Specs -->
        <div class="flex flex-wrap gap-2 mb-3">
          <span class="text-sm bg-gray-100 text-gray-600 px-3 py-1 rounded">{{ product.boNhoTrong }}</span>
          <span class="text-sm bg-gray-100 text-gray-600 px-3 py-1 rounded">{{ product.mauSac }}</span>
          <span class="text-sm bg-gray-100 text-gray-600 px-3 py-1 rounded">{{ product.ram }}</span>
        </div>

        <!-- Features -->
        <div class="mb-4 text-sm text-gray-600">
          <ul class="space-y-1">
            <li class="flex items-center">
              <svg class="w-4 h-4 mr-2 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
              </svg>
              Chính hãng Apple Việt Nam
            </li>
            <li class="flex items-center">
              <svg class="w-4 h-4 mr-2 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
              </svg>
              Bảo hành 12 tháng
            </li>
            <li class="flex items-center">
              <svg class="w-4 h-4 mr-2 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
              </svg>
              Miễn phí giao hàng toàn quốc
            </li>
          </ul>
        </div>

        <!-- Stock Status -->
        <div class="mb-3 text-sm">
          <span v-if="product.soLuongTonKho > 10" class="text-green-600 font-medium">✓ Còn hàng</span>
          <span v-else-if="product.soLuongTonKho > 0" class="text-orange-600 font-medium">⚠ Sắp hết hàng</span>
          <span v-else class="text-red-600 font-medium">✗ Hết hàng</span>
        </div>
      </div>

      <!-- Price and Actions -->
      <div class="flex flex-col justify-between items-end min-w-[200px]">
        <!-- Price -->
        <div class="text-right">
          <div v-if="product.phanTramGiam > 0">
            <div class="text-2xl font-bold text-red-600 mb-1">
              {{ formatPrice(product.giaSauKhiGiam) }}
            </div>
            <div class="text-sm text-gray-500 line-through">
              {{ formatPrice(product.giaBan) }}
            </div>
          </div>
          <div v-else>
            <div class="text-2xl font-bold text-gray-800">
              {{ formatPrice(product.giaBan) }}
            </div>
          </div>
        </div>

        <!-- Actions -->
        <div class="flex flex-col gap-2 w-full">
          <button
            @click="$emit('add-to-cart', product)"
            class="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 px-4 rounded-lg transition-colors flex items-center justify-center gap-2"
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
            </svg>
            Mua ngay
          </button>
          <button
            @click="$emit('toggle-wishlist', product)"
            class="w-full bg-white hover:bg-gray-50 text-gray-700 font-semibold py-2 px-4 rounded-lg border-2 border-gray-300 transition-colors flex items-center justify-center gap-2"
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
            </svg>
            Yêu thích
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
  name: 'ProductListItem',
  props: {
    product: {
      type: Object,
      required: true,
    },
  },
  emits: ['add-to-cart', 'toggle-wishlist'],
  setup(props) {
    const router = useRouter();

    const formatPrice = (price) => {
      return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
      }).format(price);
    };

    const viewProduct = () => {
      router.push(`/product/${props.product.idChiTietSanPham}`);
    };

    return {
      formatPrice,
      viewProduct,
    };
  },
};
</script>

<style scoped>
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>