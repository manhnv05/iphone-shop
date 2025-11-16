<template>
  <div class="bg-gray-50 min-h-screen">
    <ClientHeader />

    <!-- Compact Banner -->
    <div class="relative bg-gradient-to-r from-red-600 to-red-700 overflow-hidden">
      <div class="absolute inset-0 opacity-10">
        <div class="absolute top-0 left-0 w-full h-full" style="background-image: url('data:image/svg+xml,%3Csvg width=\'60\' height=\'60\' viewBox=\'0 0 60 60\' xmlns=\'http://www.w3.org/2000/svg\'%3E%3Cg fill=\'none\' fill-rule=\'evenodd\'%3E%3Cg fill=\'%23ffffff\' fill-opacity=\'0.1\'%3E%3Cpath d=\'M36 34v-4h-2v4h-4v2h4v4h2v-4h4v-2h-4zm0-30V0h-2v4h-4v2h4v4h2V6h4V4h-4zM6 34v-4H4v4H0v2h4v4h2v-4h4v-2H6zM6 4V0H4v4H0v2h4v4h2V6h4V4H6z\'/%3E%3C/g%3E%3C/g%3E%3C/svg%3E')"></div>
      </div>

      <div class="container mx-auto px-4 py-8 relative z-10">
        <div class="flex items-center justify-between gap-8">
          <!-- Left Content -->
          <div class="text-white flex-1">
            <div class="inline-flex items-center gap-2 bg-yellow-400 text-red-600 px-4 py-1.5 rounded-lg font-bold text-sm mb-3">
              <svg class="w-4 h-4" fill="currentColor" viewBox="0 0 20 20">
                <path d="M11.3 1.046A1 1 0 0112 2v5h4a1 1 0 01.82 1.573l-7 10A1 1 0 018 18v-5H4a1 1 0 01-.82-1.573l7-10a1 1 0 011.12-.38z" />
              </svg>
              FLASH SALE
            </div>

            <h1 class="text-3xl lg:text-4xl font-black mb-2 leading-tight">
              OUTLET SALE - IPHONE
            </h1>

            <p class="text-lg font-bold mb-3 text-yellow-300">
              Giảm giá lên đến 50%
            </p>

            <div class="flex flex-wrap gap-4 text-sm mb-4">
              <div class="flex items-center gap-2">
                <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                </svg>
                Chính hãng VN/A
              </div>
              <div class="flex items-center gap-2">
                <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-12a1 1 0 10-2 0v4a1 1 0 00.293.707l2.828 2.829a1 1 0 101.415-1.415L11 9.586V6z" clip-rule="evenodd" />
                </svg>
                Bảo hành 12 tháng
              </div>
              <div class="flex items-center gap-2">
                <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M8 16.5a1.5 1.5 0 11-3 0 1.5 1.5 0 013 0zM15 16.5a1.5 1.5 0 11-3 0 1.5 1.5 0 013 0z" />
                  <path d="M3 4a1 1 0 00-1 1v10a1 1 0 001 1h1.05a2.5 2.5 0 014.9 0H10a1 1 0 001-1V5a1 1 0 00-1-1H3zM14 7a1 1 0 00-1 1v6.05A2.5 2.5 0 0115.95 16H17a1 1 0 001-1v-5a1 1 0 00-.293-.707l-2-2A1 1 0 0015 7h-1z" />
                </svg>
                Miễn phí ship
              </div>
            </div>

            <button
              @click="scrollToProducts"
              class="bg-yellow-400 text-red-600 px-6 py-2.5 rounded-lg text-base font-black hover:bg-yellow-300 transition-all shadow-lg inline-flex items-center gap-2"
            >
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
              </svg>
              XEM DEAL HOT
            </button>
          </div>

          <!-- Countdown Timer - Compact -->
          <div class="hidden md:block">
            <div class="bg-white/10 backdrop-blur-sm rounded-xl p-4 border-2 border-white/20">
              <p class="text-white text-xs mb-2 font-semibold text-center">⏰ Kết thúc sau:</p>
              <div class="flex gap-2">
                <div class="text-center">
                  <div class="bg-white text-red-600 rounded-lg px-3 py-2 font-black text-xl min-w-[50px]">
                    {{ countdown.days }}
                  </div>
                  <div class="text-white text-xs mt-1">Ngày</div>
                </div>
                <div class="text-center">
                  <div class="bg-white text-red-600 rounded-lg px-3 py-2 font-black text-xl min-w-[50px]">
                    {{ countdown.hours }}
                  </div>
                  <div class="text-white text-xs mt-1">Giờ</div>
                </div>
                <div class="text-center">
                  <div class="bg-white text-red-600 rounded-lg px-3 py-2 font-black text-xl min-w-[50px]">
                    {{ countdown.minutes }}
                  </div>
                  <div class="text-white text-xs mt-1">Phút</div>
                </div>
                <div class="text-center">
                  <div class="bg-white text-red-600 rounded-lg px-3 py-2 font-black text-xl min-w-[50px]">
                    {{ countdown.seconds }}
                  </div>
                  <div class="text-white text-xs mt-1">Giây</div>
                </div>
              </div>
            </div>
          </div>

          <!-- Right Image - Smaller -->
          <div class="hidden lg:block">
            <img
              src="https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=400&h=400&fit=crop"
              alt="iPhone Outlet Sale"
              class="w-48 h-48 object-contain rounded-xl shadow-2xl transform hover:scale-105 transition-transform"
            />
          </div>
        </div>
      </div>
    </div>

    <!-- Hot Deals Bar -->
    <div class="bg-yellow-400 py-2 border-t-2 border-red-600">
      <div class="container mx-auto px-4">
        <div class="flex items-center justify-center gap-8 text-red-600 font-bold text-xs md:text-sm">
          <div class="flex items-center gap-2">
            <svg class="w-4 h-4" fill="currentColor" viewBox="0 0 20 20">
              <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
            </svg>
            100% CHÍNH HÃNG
          </div>
          <div class="hidden md:flex items-center gap-2">
            <svg class="w-4 h-4" fill="currentColor" viewBox="0 0 20 20">
              <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-12a1 1 0 10-2 0v4a1 1 0 00.293.707l2.828 2.829a1 1 0 101.415-1.415L11 9.586V6z" clip-rule="evenodd" />
            </svg>
            BẢO HÀNH 12 THÁNG
          </div>
          <div class="hidden md:flex items-center gap-2">
            <svg class="w-4 h-4" fill="currentColor" viewBox="0 0 20 20">
              <path d="M8 16.5a1.5 1.5 0 11-3 0 1.5 1.5 0 013 0zM15 16.5a1.5 1.5 0 11-3 0 1.5 1.5 0 013 0z" />
              <path d="M3 4a1 1 0 00-1 1v10a1 1 0 001 1h1.05a2.5 2.5 0 014.9 0H10a1 1 0 001-1V5a1 1 0 00-1-1H3zM14 7a1 1 0 00-1 1v6.05A2.5 2.5 0 0115.95 16H17a1 1 0 001-1v-5a1 1 0 00-.293-.707l-2-2A1 1 0 0015 7h-1z" />
            </svg>
            GIAO HÀNG NHANH
          </div>
        </div>
      </div>
    </div>

    <!-- Category Tabs -->
    <div class="container mx-auto px-4 py-6" ref="productsSection">
      <div class="bg-white rounded-xl shadow-sm border border-gray-200 p-3 mb-6">
        <div class="flex flex-wrap items-center gap-2">
          <span class="text-gray-700 font-semibold text-sm hidden md:inline">Lọc theo:</span>
          <button
            v-for="cat in productCategories"
            :key="cat.id"
            @click="selectedCategory = cat.id"
            :class="[
              'px-4 py-2 rounded-lg font-semibold transition-all text-sm',
              selectedCategory === cat.id
                ? 'bg-red-600 text-white shadow-md'
                : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
            ]"
          >
            {{ cat.name }}
          </button>
        </div>
      </div>

      <!-- Toolbar -->
      <div class="flex items-center justify-between mb-6">
        <div>
          <h2 class="text-xl font-bold text-gray-800">
            {{ selectedCategory === 'all' ? 'Tất cả iPhone Outlet' : productCategories.find(c => c.id === selectedCategory)?.name }}
          </h2>
          <p class="text-sm text-gray-600 mt-1">
            <strong class="text-red-600">{{ filteredProducts.length }}</strong> sản phẩm
          </p>
        </div>

        <select
          v-model="sortBy"
          class="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-red-500 bg-white text-sm font-medium"
        >
          <option value="default">Sắp xếp</option>
          <option value="discount">Giảm giá nhiều</option>
          <option value="price-asc">Giá tăng dần</option>
          <option value="price-desc">Giá giảm dần</option>
          <option value="newest">Mới nhất</option>
        </select>
      </div>

      <!-- Products Grid -->
      <div class="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-4">
        <div
          v-for="product in filteredProducts"
          :key="product.id"
          class="bg-white rounded-xl overflow-hidden shadow-sm hover:shadow-xl transition-all cursor-pointer border border-gray-100 hover:border-red-500 group"
          @click="viewProduct(product.id)"
        >
          <!-- Product Image -->
          <div class="relative aspect-square p-4 bg-gray-50">
            <img
              :src="product.image"
              :alt="product.name"
              class="w-full h-full object-contain group-hover:scale-105 transition-transform"
            />

            <!-- Badges -->
            <div class="absolute top-2 left-2 flex flex-col gap-1">
              <span class="bg-red-600 text-white px-2 py-1 rounded text-xs font-bold">
                -{{ product.discount }}%
              </span>
              <span class="bg-blue-600 text-white px-2 py-1 rounded text-xs font-semibold">
                {{ product.condition }}
              </span>
            </div>

            <div v-if="product.stock < 5" class="absolute top-2 right-2">
              <span class="bg-yellow-400 text-red-600 px-2 py-1 rounded text-xs font-bold">
                Sắp hết
              </span>
            </div>

            <!-- Wishlist -->
            <button
              @click.stop="toggleWishlist(product)"
              class="absolute bottom-2 right-2 bg-white rounded-full p-2 shadow-md hover:bg-red-50 transition-colors"
            >
              <svg class="w-5 h-5 text-gray-400 hover:text-red-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
              </svg>
            </button>
          </div>

          <!-- Product Info -->
          <div class="p-4">
            <h3 class="text-sm font-semibold text-gray-800 mb-2 line-clamp-2 min-h-[40px]">
              {{ product.name }}
            </h3>

            <!-- Price -->
            <div class="mb-3">
              <div class="flex items-baseline gap-2">
                <span class="text-xl font-black text-red-600">
                  {{ formatPrice(product.salePrice) }}
                </span>
              </div>
              <div class="flex items-center gap-2">
                <span class="text-sm text-gray-400 line-through">
                  {{ formatPrice(product.originalPrice) }}
                </span>
              </div>
            </div>

            <!-- Progress Bar -->
            <div class="mb-3">
              <div class="flex justify-between text-xs mb-1">
                <span class="text-gray-500">Đã bán {{ product.sold }}</span>
                <span class="font-semibold text-red-600">Còn {{ product.stock }}</span>
              </div>
              <div class="w-full bg-gray-200 rounded-full h-1.5">
                <div
                  class="bg-red-600 h-1.5 rounded-full transition-all"
                  :style="{ width: `${(product.sold / (product.sold + product.stock)) * 100}%` }"
                ></div>
              </div>
            </div>

            <!-- Buy Button -->
            <button
              @click.stop="addToCart(product)"
              class="w-full bg-red-600 hover:bg-red-700 text-white py-2.5 rounded-lg font-semibold transition-all text-sm flex items-center justify-center gap-2"
            >
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
              </svg>
              Mua ngay
            </button>
          </div>
        </div>
      </div>

      <!-- No Results -->
      <div v-if="filteredProducts.length === 0" class="text-center py-16">
        <div class="bg-white rounded-xl p-12 max-w-md mx-auto shadow-sm">
          <svg class="w-20 h-20 mx-auto text-gray-300 mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.172 16.172a4 4 0 015.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
          <h3 class="text-xl font-bold text-gray-800 mb-2">Hết hàng</h3>
          <p class="text-gray-600 mb-4">Sản phẩm trong danh mục này đã hết. Vui lòng chọn danh mục khác.</p>
          <button
            @click="selectedCategory = 'all'"
            class="bg-red-600 text-white px-6 py-2 rounded-lg hover:bg-red-700 transition-colors"
          >
            Xem tất cả sản phẩm
          </button>
        </div>
      </div>
    </div>

    <!-- Why Buy Section -->
    <div class="bg-white border-t border-gray-200 py-12">
      <div class="container mx-auto px-4">
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-8">
          Tại sao nên mua iPhone Outlet tại TopZone?
        </h2>
        <div class="grid md:grid-cols-4 gap-6">
          <div class="text-center">
            <div class="bg-red-50 w-14 h-14 rounded-full flex items-center justify-center mx-auto mb-3">
              <svg class="w-7 h-7 text-red-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
            <h3 class="font-bold text-gray-800 mb-2 text-sm">Giá cực sốc</h3>
            <p class="text-xs text-gray-600">Giảm đến 50%</p>
          </div>

          <div class="text-center">
            <div class="bg-green-50 w-14 h-14 rounded-full flex items-center justify-center mx-auto mb-3">
              <svg class="w-7 h-7 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
            <h3 class="font-bold text-gray-800 mb-2 text-sm">Chính hãng 100%</h3>
            <p class="text-xs text-gray-600">iPhone VN/A</p>
          </div>

          <div class="text-center">
            <div class="bg-blue-50 w-14 h-14 rounded-full flex items-center justify-center mx-auto mb-3">
              <svg class="w-7 h-7 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z" />
              </svg>
            </div>
            <h3 class="font-bold text-gray-800 mb-2 text-sm">Bảo hành 12 tháng</h3>
            <p class="text-xs text-gray-600">Chính hãng Apple</p>
          </div>

          <div class="text-center">
            <div class="bg-purple-50 w-14 h-14 rounded-full flex items-center justify-center mx-auto mb-3">
              <svg class="w-7 h-7 text-purple-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 8h14M5 8a2 2 0 110-4h14a2 2 0 110 4M5 8v10a2 2 0 002 2h10a2 2 0 002-2V8m-9 4h4" />
              </svg>
            </div>
            <h3 class="font-bold text-gray-800 mb-2 text-sm">Đổi trả 15 ngày</h3>
            <p class="text-xs text-gray-600">Miễn phí đổi trả</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Product Conditions Info -->
    <div class="bg-gray-50 py-12">
      <div class="container mx-auto px-4">
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-8">
          Phân loại iPhone Outlet
        </h2>
        <div class="grid md:grid-cols-3 gap-6 max-w-5xl mx-auto">
          <div class="bg-white rounded-xl p-6 shadow-sm border border-gray-200">
            <div class="bg-blue-100 text-blue-600 px-3 py-1 rounded-full text-xs font-bold inline-block mb-3">
              Hộp mở
            </div>
            <h3 class="font-bold text-gray-800 mb-3 text-sm">iPhone hộp mở</h3>
            <ul class="text-xs text-gray-600 space-y-2">
              <li class="flex items-start gap-2">
                <svg class="w-4 h-4 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                </svg>
                <span>Hộp đã mở nhưng chưa kích hoạt</span>
              </li>
              <li class="flex items-start gap-2">
                <svg class="w-4 h-4 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                </svg>
                <span>Máy 100% nguyên zin</span>
              </li>
              <li class="flex items-start gap-2">
                <svg class="w-4 h-4 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                </svg>
                <span>Đầy đủ phụ kiện gốc</span>
              </li>
            </ul>
          </div>

          <div class="bg-white rounded-xl p-6 shadow-sm border border-gray-200">
            <div class="bg-blue-100 text-blue-600 px-3 py-1 rounded-full text-xs font-bold inline-block mb-3">
              Trưng bày
            </div>
            <h3 class="font-bold text-gray-800 mb-3 text-sm">iPhone trưng bày</h3>
            <ul class="text-xs text-gray-600 space-y-2">
              <li class="flex items-start gap-2">
                <svg class="w-4 h-4 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                </svg>
                <span>Máy trưng bày tại cửa hàng</span>
              </li>
              <li class="flex items-start gap-2">
                <svg class="w-4 h-4 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                </svg>
                <span>Hoạt động bình thường 100%</span>
              </li>
              <li class="flex items-start gap-2">
                <svg class="w-4 h-4 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                </svg>
                <span>Có thể có vết xước nhẹ</span>
              </li>
            </ul>
          </div>

          <div class="bg-white rounded-xl p-6 shadow-sm border border-gray-200">
            <div class="bg-blue-100 text-blue-600 px-3 py-1 rounded-full text-xs font-bold inline-block mb-3">
              Like New
            </div>
            <h3 class="font-bold text-gray-800 mb-3 text-sm">iPhone Like New</h3>
            <ul class="text-xs text-gray-600 space-y-2">
              <li class="flex items-start gap-2">
                <svg class="w-4 h-4 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                </svg>
                <span>Đã qua sử dụng rất ít</span>
              </li>
              <li class="flex items-start gap-2">
                <svg class="w-4 h-4 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                </svg>
                <span>Trạng thái 98-99% như mới</span>
              </li>
              <li class="flex items-start gap-2">
                <svg class="w-4 h-4 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                </svg>
                <span>Pin, màn hình còn tốt</span>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <ClientFooter />

    <!-- Toast -->
    <Transition name="toast">
      <div
        v-if="toast.show"
        class="fixed bottom-4 right-4 px-6 py-4 rounded-lg shadow-2xl text-white z-50 flex items-center gap-3 bg-green-500"
      >
        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
        </svg>
        <span>{{ toast.message }}</span>
      </div>
    </Transition>
  </div>
</template>

<script>
import { ref, computed, onMounted, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import ClientHeader from './components/Header.vue';
import ClientFooter from './components/Footer.vue';

export default {
  name: 'OutletSale',
  components: {
    ClientHeader,
    ClientFooter,
  },
  setup() {
    const router = useRouter();
    const productsSection = ref(null);
    const selectedCategory = ref('all');
    const sortBy = ref('default');

    const countdown = ref({
      days: 0,
      hours: 0,
      minutes: 0,
      seconds: 0,
    });

    const toast = ref({
      show: false,
      message: '',
    });

    const productCategories = ref([
      { id: 'all', name: 'Tất cả iPhone' },
      { id: 'iphone-15', name: 'iPhone 15 Series' },
      { id: 'iphone-14', name: 'iPhone 14 Series' },
      { id: 'iphone-13', name: 'iPhone 13 Series' },
      { id: 'iphone-12', name: 'iPhone 12 Series' },
    ]);

    const products = ref([
      {
        id: 1,
        name: 'iPhone 14 Pro Max 256GB - Hộp mở',
        image: 'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=600&h=600&fit=crop',
        originalPrice: 32990000,
        salePrice: 24990000,
        discount: 24,
        condition: 'Hộp mở',
        category: 'iphone-14',
        stock: 3,
        sold: 12,
      },
      {
        id: 2,
        name: 'iPhone 14 Pro 128GB - Trưng bày',
        image: 'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=600&h=600&fit=crop',
        originalPrice: 29990000,
        salePrice: 22990000,
        discount: 23,
        condition: 'Trưng bày',
        category: 'iphone-14',
        stock: 5,
        sold: 8,
      },
      {
        id: 3,
        name: 'iPhone 13 Pro Max 256GB - Like New',
        image: 'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=600&h=600&fit=crop',
        originalPrice: 26990000,
        salePrice: 18990000,
        discount: 30,
        condition: 'Like New',
        category: 'iphone-13',
        stock: 4,
        sold: 15,
      },
      {
        id: 4,
        name: 'iPhone 15 128GB - Hộp mở',
        image: 'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=600&h=600&fit=crop',
        originalPrice: 24990000,
        salePrice: 21990000,
        discount: 12,
        condition: 'Hộp mở',
        category: 'iphone-15',
        stock: 7,
        sold: 20,
      },
      {
        id: 5,
        name: 'iPhone 14 256GB - Trưng bày',
        image: 'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=600&h=600&fit=crop',
        originalPrice: 23990000,
        salePrice: 18990000,
        discount: 21,
        condition: 'Trưng bày',
        category: 'iphone-14',
        stock: 10,
        sold: 25,
      },
      {
        id: 6,
        name: 'iPhone 13 128GB - Hộp mở',
        image: 'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=600&h=600&fit=crop',
        originalPrice: 18990000,
        salePrice: 13990000,
        discount: 26,
        condition: 'Hộp mở',
        category: 'iphone-13',
        stock: 2,
        sold: 30,
      },
      {
        id: 7,
        name: 'iPhone 12 Pro 256GB - Like New',
        image: 'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=600&h=600&fit=crop',
        originalPrice: 19990000,
        salePrice: 12990000,
        discount: 35,
        condition: 'Like New',
        category: 'iphone-12',
        stock: 6,
        sold: 18,
      },
      {
        id: 8,
        name: 'iPhone 15 Pro 256GB - Trưng bày',
        image: 'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=600&h=600&fit=crop',
        originalPrice: 31990000,
        salePrice: 28990000,
        discount: 9,
        condition: 'Trưng bày',
        category: 'iphone-15',
        stock: 3,
        sold: 5,
      },
      {
        id: 9,
        name: 'iPhone 14 Plus 128GB - Like New',
        image: 'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=600&h=600&fit=crop',
        originalPrice: 24990000,
        salePrice: 18990000,
        discount: 24,
        condition: 'Like New',
        category: 'iphone-14',
        stock: 4,
        sold: 16,
      },
      {
        id: 10,
        name: 'iPhone 13 Pro 512GB - Hộp mở',
        image: 'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=600&h=600&fit=crop',
        originalPrice: 28990000,
        salePrice: 19990000,
        discount: 31,
        condition: 'Hộp mở',
        category: 'iphone-13',
        stock: 2,
        sold: 8,
      },
    ]);

    const filteredProducts = computed(() => {
      let result = products.value;

      if (selectedCategory.value !== 'all') {
        result = result.filter(p => p.category === selectedCategory.value);
      }

      if (sortBy.value === 'discount') {
        result.sort((a, b) => b.discount - a.discount);
      } else if (sortBy.value === 'price-asc') {
        result.sort((a, b) => a.salePrice - b.salePrice);
      } else if (sortBy.value === 'price-desc') {
        result.sort((a, b) => b.salePrice - a.salePrice);
      } else if (sortBy.value === 'newest') {
        result.sort((a, b) => b.id - a.id);
      }

      return result;
    });

    let countdownInterval;

    const updateCountdown = () => {
      const targetDate = new Date();
      targetDate.setDate(targetDate.getDate() + 7);

      const now = new Date();
      const difference = targetDate - now;

      if (difference > 0) {
        countdown.value = {
          days: Math.floor(difference / (1000 * 60 * 60 * 24)),
          hours: Math.floor((difference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)),
          minutes: Math.floor((difference % (1000 * 60 * 60)) / (1000 * 60)),
          seconds: Math.floor((difference % (1000 * 60)) / 1000),
        };
      }
    };

    const scrollToProducts = () => {
      if (productsSection.value) {
        productsSection.value.scrollIntoView({ behavior: 'smooth', block: 'start' });
      }
    };

    const formatPrice = (price) => {
      return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
      }).format(price);
    };

    const viewProduct = (id) => {
      router.push(`/product/${id}`);
    };

    const addToCart = (product) => {
      toast.value = {
        show: true,
        message: `Đã thêm ${product.name} vào giỏ hàng!`,
      };
      setTimeout(() => {
        toast.value.show = false;
      }, 3000);
    };

    const toggleWishlist = (product) => {
      toast.value = {
        show: true,
        message: 'Đã thêm vào yêu thích!',
      };
      setTimeout(() => {
        toast.value.show = false;
      }, 3000);
    };

    onMounted(() => {
      updateCountdown();
      countdownInterval = setInterval(updateCountdown, 1000);
    });

    onUnmounted(() => {
      if (countdownInterval) {
        clearInterval(countdownInterval);
      }
    });

    return {
      productsSection,
      selectedCategory,
      sortBy,
      countdown,
      toast,
      productCategories,
      products,
      filteredProducts,
      scrollToProducts,
      formatPrice,
      viewProduct,
      addToCart,
      toggleWishlist,
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