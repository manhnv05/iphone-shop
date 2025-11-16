<template>
  <div
    class="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-xl transition-shadow duration-300 group cursor-pointer"
    :class="compact ? 'h-auto' : ''"
    @click="viewProduct"
  >
    <!-- Product Image -->
    <div class="relative overflow-hidden">
      <img
        :src="product.duongDanAnh || 'https://via.placeholder.com/300x300.png?text=iPhone'"
        :alt="product.tenSanPham"
        :class="compact ? 'h-40' : 'h-64'"
        class="w-full object-cover group-hover:scale-110 transition-transform duration-300"
      />

      <!-- Discount Badge -->
      <div v-if="product.phanTramGiam > 0" class="absolute top-3 right-3 bg-red-500 text-white px-2 py-1 rounded-full text-xs font-bold">
        -{{ product.phanTramGiam }}%
      </div>

      <!-- New Badge -->
      <div v-if="product.isNew" class="absolute top-3 left-3 bg-green-500 text-white px-2 py-1 rounded-full text-xs font-bold">
        MỚI
      </div>
    </div>

    <!-- Product Info -->
    <div :class="compact ? 'p-3' : 'p-4'">
      <h3 :class="compact ? 'text-sm' : 'text-base'" class="font-semibold text-gray-800 mb-2 line-clamp-2 group-hover:text-blue-600 transition-colors">
        {{ product.tenSanPham }}
      </h3>

      <!-- Specs -->
      <div v-if="!compact" class="flex flex-wrap gap-1 mb-3">
        <span class="text-xs bg-gray-100 text-gray-600 px-2 py-1 rounded">{{ product.boNhoTrong }}</span>
        <span class="text-xs bg-gray-100 text-gray-600 px-2 py-1 rounded">{{ product.mauSac }}</span>
      </div>

      <!-- Price -->
      <div class="mb-3">
        <div v-if="product.phanTramGiam > 0" class="flex items-center gap-2">
          <span :class="compact ? 'text-base' : 'text-lg'" class="font-bold text-red-600">
            {{ formatPrice(product.giaSauKhiGiam) }}
          </span>
          <span class="text-xs text-gray-500 line-through">
            {{ formatPrice(product.giaBan) }}
          </span>
        </div>
        <div v-else>
          <span :class="compact ? 'text-base' : 'text-lg'" class="font-bold text-gray-800">
            {{ formatPrice(product.giaBan) }}
          </span>
        </div>
      </div>

      <!-- Actions -->
      <div v-if="!compact" class="flex gap-2">
        <button
          @click.stop="$emit('add-to-cart', product)"
          class="flex-1 bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-3 rounded-lg transition-colors text-sm"
        >
          Mua ngay
        </button>
        <button
          @click.stop="$emit('toggle-wishlist', product)"
          class="p-2 rounded-lg border-2 border-gray-300 hover:border-red-500 hover:text-red-500 transition-colors"
        >
          <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
          </svg>
        </button>
      </div>

      <button
        v-else
        @click.stop="$emit('add-to-cart', product)"
        class="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 rounded-lg transition-colors text-sm"
      >
        Mua ngay
      </button>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
  name: 'ProductCard',
  props: {
    product: {
      type: Object,
      required: true,
    },
    compact: {
      type: Boolean,
      default: false,
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