<template>
  <div class="bg-gray-50 min-h-screen">
    <ClientHeader />

    <!-- Hero Section - Compact -->
    <div class="relative bg-gradient-to-br from-blue-600 via-indigo-700 to-purple-800 text-white overflow-hidden">
      <div class="absolute inset-0 opacity-20">
        <div class="absolute top-0 left-0 w-96 h-96 bg-white rounded-full mix-blend-overlay filter blur-3xl animate-blob"></div>
        <div class="absolute top-0 right-0 w-96 h-96 bg-purple-300 rounded-full mix-blend-overlay filter blur-3xl animate-blob animation-delay-2000"></div>
      </div>

      <div class="container mx-auto px-4 py-12 relative z-10">
        <div class="text-center max-w-3xl mx-auto">
          <div class="inline-flex items-center gap-2 bg-white/20 backdrop-blur-sm px-4 py-2 rounded-full border border-white/30 mb-6">
            <svg class="w-4 h-4 text-yellow-300" fill="currentColor" viewBox="0 0 20 20">
              <path d="M2 3a1 1 0 011-1h2.153a1 1 0 01.986.836l.74 4.435a1 1 0 01-.54 1.06l-1.548.773a11.037 11.037 0 006.105 6.105l.774-1.548a1 1 0 011.059-.54l4.435.74a1 1 0 01.836.986V17a1 1 0 01-1 1h-2C7.82 18 2 12.18 2 5V3z" />
            </svg>
            <span class="font-semibold text-sm">Hỗ trợ 24/7</span>
          </div>

          <h1 class="text-3xl lg:text-5xl font-black mb-4 leading-tight">
            Liên hệ với <span class="text-yellow-300">TopZone</span>
          </h1>
          <div class="h-1 w-24 bg-gradient-to-r from-yellow-300 to-pink-400 rounded-full mx-auto mb-6"></div>

          <p class="text-base lg:text-lg text-blue-100">
            Chúng tôi luôn sẵn sàng lắng nghe và hỗ trợ bạn
          </p>
        </div>
      </div>
    </div>

    <div class="container mx-auto px-4 py-12">
      <!-- Contact Info Cards -->
      <div class="grid lg:grid-cols-3 gap-6 mb-12">
        <div class="bg-white rounded-xl p-6 shadow-md hover:shadow-xl transition-all group border-t-4 border-blue-500">
          <div class="bg-blue-100 w-14 h-14 rounded-xl flex items-center justify-center mb-4 group-hover:scale-110 transition-transform">
            <svg class="w-7 h-7 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
            </svg>
          </div>
          <h3 class="text-xl font-black text-gray-900 mb-3">Điện thoại</h3>
          <p class="text-gray-600 text-sm mb-2">Hotline: <a href="tel:1900xxxx" class="text-blue-600 font-bold hover:underline">1900 xxxx</a></p>
          <p class="text-gray-600 text-sm">Hỗ trợ: <a href="tel:0987654321" class="text-blue-600 font-bold hover:underline">098 765 4321</a></p>
        </div>

        <div class="bg-white rounded-xl p-6 shadow-md hover:shadow-xl transition-all group border-t-4 border-green-500">
          <div class="bg-green-100 w-14 h-14 rounded-xl flex items-center justify-center mb-4 group-hover:scale-110 transition-transform">
            <svg class="w-7 h-7 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
            </svg>
          </div>
          <h3 class="text-xl font-black text-gray-900 mb-3">Email</h3>
          <p class="text-gray-600 text-sm mb-2">
            <a href="mailto:support@topzone.vn" class="text-blue-600 font-bold hover:underline">support@topzone.vn</a>
          </p>
          <p class="text-gray-600 text-sm">
            <a href="mailto:sales@topzone.vn" class="text-blue-600 font-bold hover:underline">sales@topzone.vn</a>
          </p>
        </div>

        <div class="bg-white rounded-xl p-6 shadow-md hover:shadow-xl transition-all group border-t-4 border-purple-500">
          <div class="bg-purple-100 w-14 h-14 rounded-xl flex items-center justify-center mb-4 group-hover:scale-110 transition-transform">
            <svg class="w-7 h-7 text-purple-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
            </svg>
          </div>
          <h3 class="text-xl font-black text-gray-900 mb-3">Địa chỉ</h3>
          <p class="text-gray-600 text-sm">
            123 Nguyễn Huệ, Quận 1,<br />
            TP. Hồ Chí Minh, Việt Nam
          </p>
        </div>
      </div>

      <div class="grid lg:grid-cols-2 gap-8 mb-12">
        <!-- Contact Form -->
        <div class="bg-white rounded-2xl p-8 shadow-xl">
          <div class="flex items-center gap-3 mb-6">
            <div class="bg-blue-100 p-2 rounded-lg">
              <svg class="w-6 h-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 10h.01M12 10h.01M16 10h.01M9 16H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-5l-5 5v-5z" />
              </svg>
            </div>
            <div>
              <h2 class="text-2xl font-black text-gray-900">Gửi tin nhắn</h2>
              <p class="text-sm text-gray-600">Chúng tôi sẽ phản hồi trong 24h</p>
            </div>
          </div>

          <form @submit.prevent="submitForm" class="space-y-4">
            <div class="grid md:grid-cols-2 gap-4">
              <div>
                <label class="block text-sm font-bold text-gray-700 mb-2">Họ và tên *</label>
                <input
                  v-model="form.name"
                  type="text"
                  required
                  class="w-full px-4 py-2.5 border-2 border-gray-200 rounded-lg focus:outline-none focus:border-blue-500 transition-colors text-sm"
                  placeholder="Nguyễn Văn A"
                />
              </div>
              <div>
                <label class="block text-sm font-bold text-gray-700 mb-2">Số điện thoại *</label>
                <input
                  v-model="form.phone"
                  type="tel"
                  required
                  class="w-full px-4 py-2.5 border-2 border-gray-200 rounded-lg focus:outline-none focus:border-blue-500 transition-colors text-sm"
                  placeholder="0987654321"
                />
              </div>
            </div>

            <div>
              <label class="block text-sm font-bold text-gray-700 mb-2">Email *</label>
              <input
                v-model="form.email"
                type="email"
                required
                class="w-full px-4 py-2.5 border-2 border-gray-200 rounded-lg focus:outline-none focus:border-blue-500 transition-colors text-sm"
                placeholder="example@email.com"
              />
            </div>

            <div>
              <label class="block text-sm font-bold text-gray-700 mb-2">Chủ đề</label>
              <select
                v-model="form.subject"
                class="w-full px-4 py-2.5 border-2 border-gray-200 rounded-lg focus:outline-none focus:border-blue-500 transition-colors text-sm"
              >
                <option value="">Chọn chủ đề</option>
                <option value="product">Hỏi về sản phẩm</option>
                <option value="order">Đơn hàng</option>
                <option value="warranty">Bảo hành</option>
                <option value="complaint">Khiếu nại</option>
                <option value="other">Khác</option>
              </select>
            </div>

            <div>
              <label class="block text-sm font-bold text-gray-700 mb-2">Nội dung *</label>
              <textarea
                v-model="form.message"
                required
                rows="5"
                class="w-full px-4 py-2.5 border-2 border-gray-200 rounded-lg focus:outline-none focus:border-blue-500 transition-colors resize-none text-sm"
                placeholder="Nhập nội dung tin nhắn của bạn..."
              ></textarea>
            </div>

            <button
              type="submit"
              :disabled="loading"
              class="w-full bg-gradient-to-r from-blue-600 to-blue-700 text-white py-3 rounded-lg font-bold hover:shadow-xl transition-all disabled:opacity-50 disabled:cursor-not-allowed flex items-center justify-center gap-2"
            >
              <svg v-if="!loading" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8" />
              </svg>
              <span v-if="!loading">Gửi tin nhắn</span>
              <span v-else>Đang gửi...</span>
            </button>
          </form>
        </div>

        <!-- Map & Working Hours -->
        <div class="space-y-6">
          <div class="bg-white rounded-2xl p-6 shadow-xl">
            <div class="flex items-center gap-3 mb-4">
              <div class="bg-green-100 p-2 rounded-lg">
                <svg class="w-6 h-6 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
              </div>
              <h2 class="text-xl font-black text-gray-900">Giờ làm việc</h2>
            </div>
            <div class="space-y-3">
              <div class="flex justify-between items-center py-2 border-b border-gray-100">
                <span class="font-semibold text-gray-700 text-sm">Thứ 2 - Thứ 6:</span>
                <span class="text-blue-600 font-bold text-sm">8:00 - 22:00</span>
              </div>
              <div class="flex justify-between items-center py-2 border-b border-gray-100">
                <span class="font-semibold text-gray-700 text-sm">Thứ 7 - Chủ nhật:</span>
                <span class="text-blue-600 font-bold text-sm">8:00 - 23:00</span>
              </div>
              <div class="flex justify-between items-center py-2">
                <span class="font-semibold text-gray-700 text-sm">Ngày lễ:</span>
                <span class="text-blue-600 font-bold text-sm">9:00 - 21:00</span>
              </div>
            </div>
          </div>

          <div class="bg-white rounded-2xl overflow-hidden shadow-xl">
            <iframe
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.4964264154147!2d106.69661631533419!3d10.772587392322478!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752f4b5f8f2c39%3A0x92dcba2950537143!2zMTIzIE5ndXnhu4VuIEh14buHLCBCw6BuIE5naMOoLCBRdeG6rW4gMSwgVGjDoG5oIHBo4buRIEjhu5MgQ2jDrSBNaW5oLCBWaeG7h3QgTmFt!5e0!3m2!1svi!2s!4v1699999999999!5m2!1svi!2s"
              width="100%"
              height="300"
              style="border:0;"
              allowfullscreen=""
              loading="lazy"
              referrerpolicy="no-referrer-when-downgrade"
            ></iframe>
          </div>
        </div>
      </div>

      <!-- Store Locations -->
      <div class="mb-12">
        <div class="text-center mb-8">
          <span class="text-blue-600 font-bold text-xs uppercase tracking-wider">Địa chỉ</span>
          <h2 class="text-3xl font-black text-gray-900 mt-2 mb-2">Hệ thống cửa hàng</h2>
          <p class="text-gray-600">50+ cửa hàng trên toàn quốc</p>
        </div>
        <div class="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          <div
            v-for="store in stores"
            :key="store.id"
            class="bg-white rounded-xl p-5 shadow-md hover:shadow-xl transition-all group"
          >
            <div class="flex items-start gap-3">
              <div class="bg-blue-100 p-2 rounded-lg group-hover:bg-blue-600 transition-colors">
                <svg class="w-5 h-5 text-blue-600 group-hover:text-white transition-colors" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                </svg>
              </div>
              <div class="flex-1">
                <h3 class="text-lg font-black text-gray-900 mb-2">{{ store.name }}</h3>
                <p class="text-gray-600 text-sm mb-2">{{ store.address }}</p>
                <div class="flex items-center gap-2 text-xs text-gray-500 mb-3">
                  <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                  </svg>
                  {{ store.phone }}
                </div>
                <a
                  :href="store.mapLink"
                  target="_blank"
                  class="text-blue-600 hover:text-blue-700 font-bold text-sm inline-flex items-center gap-1 group"
                >
                  Chỉ đường
                  <svg class="w-4 h-4 group-hover:translate-x-1 transition-transform" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7l5 5m0 0l-5 5m5-5H6" />
                  </svg>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- FAQ Section -->
      <div class="bg-gradient-to-br from-gray-900 to-gray-800 rounded-2xl p-8 shadow-2xl text-white">
        <div class="text-center mb-8">
          <h2 class="text-2xl lg:text-3xl font-black mb-3">Câu hỏi thường gặp</h2>
          <p class="text-gray-300 text-sm">Những câu hỏi phổ biến từ khách hàng</p>
        </div>
        <div class="space-y-3 max-w-4xl mx-auto">
          <div
            v-for="(faq, index) in faqs"
            :key="index"
            class="bg-white/10 backdrop-blur-sm border border-white/20 rounded-xl overflow-hidden"
          >
            <button
              @click="toggleFaq(index)"
              class="w-full flex items-center justify-between p-5 text-left hover:bg-white/5 transition-colors"
            >
              <span class="font-bold text-white text-sm lg:text-base pr-4">{{ faq.question }}</span>
              <svg
                class="w-5 h-5 text-white flex-shrink-0 transition-transform"
                :class="{ 'rotate-180': faq.open }"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
              >
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
              </svg>
            </button>
            <div
              v-show="faq.open"
              class="px-5 pb-5 text-gray-300 leading-relaxed text-sm"
            >
              {{ faq.answer }}
            </div>
          </div>
        </div>
      </div>
    </div>

    <ClientFooter />

    <!-- Toast Notification -->
    <Transition name="toast">
      <div
        v-if="toast.show"
        :class="[
          'fixed bottom-4 right-4 px-6 py-4 rounded-lg shadow-2xl text-white z-50 flex items-center gap-3',
          toast.type === 'success' ? 'bg-green-500' : 'bg-red-500'
        ]"
      >
        <svg v-if="toast.type === 'success'" class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
        </svg>
        <svg v-else class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
        </svg>
        <span>{{ toast.message }}</span>
      </div>
    </Transition>
  </div>
</template>

<script>
import { ref } from 'vue';
import ClientHeader from './components/Header.vue';
import ClientFooter from './components/Footer.vue';

export default {
  name: 'Contact',
  components: {
    ClientHeader,
    ClientFooter,
  },
  setup() {
    const loading = ref(false);

    const form = ref({
      name: '',
      phone: '',
      email: '',
      subject: '',
      message: '',
    });

    const toast = ref({
      show: false,
      message: '',
      type: 'success',
    });

    const stores = ref([
      {
        id: 1,
        name: 'TopZone Quận 1',
        address: '123 Nguyễn Huệ, Quận 1, TP.HCM',
        phone: '028 1234 5678',
        mapLink: 'https://maps.google.com',
      },
      {
        id: 2,
        name: 'TopZone Quận 3',
        address: '456 Võ Văn Tần, Quận 3, TP.HCM',
        phone: '028 9876 5432',
        mapLink: 'https://maps.google.com',
      },
      {
        id: 3,
        name: 'TopZone Thủ Đức',
        address: '789 Võ Văn Ngân, TP Thủ Đức, TP.HCM',
        phone: '028 5555 6666',
        mapLink: 'https://maps.google.com',
      },
      {
        id: 4,
        name: 'TopZone Hà Nội',
        address: '321 Trần Duy Hưng, Cầu Giấy, Hà Nội',
        phone: '024 3333 4444',
        mapLink: 'https://maps.google.com',
      },
      {
        id: 5,
        name: 'TopZone Đà Nẵng',
        address: '147 Lê Duẩn, Hải Châu, Đà Nẵng',
        phone: '0236 7777 8888',
        mapLink: 'https://maps.google.com',
      },
      {
        id: 6,
        name: 'TopZone Cần Thơ',
        address: '963 Trần Hưng Đạo, Ninh Kiều, Cần Thơ',
        phone: '0292 9999 0000',
        mapLink: 'https://maps.google.com',
      },
    ]);

    const faqs = ref([
      {
        question: 'Chính sách bảo hành của TopZone như thế nào?',
        answer: 'Tất cả sản phẩm tại TopZone đều được bảo hành chính hãng từ Apple. Thời gian bảo hành 12 tháng kể từ ngày mua hàng. Khách hàng có thể mang sản phẩm đến bất kỳ cửa hàng TopZone nào để được hỗ trợ bảo hành.',
        open: false,
      },
      {
        question: 'TopZone có chính sách đổi trả hàng không?',
        answer: 'Có. Khách hàng được đổi trả sản phẩm trong vòng 15 ngày kể từ ngày mua hàng nếu phát hiện lỗi do nhà sản xuất. Sản phẩm đổi trả phải còn nguyên vẹn, đầy đủ phụ kiện và hóa đơn mua hàng.',
        open: false,
      },
      {
        question: 'Có hỗ trợ trả góp không?',
        answer: 'TopZone hỗ trợ trả góp 0% qua thẻ tín dụng và công ty tài chính. Thời gian trả góp từ 6-12 tháng tùy theo giá trị sản phẩm. Khách hàng chỉ cần CMND và giấy tờ cần thiết để được duyệt nhanh chóng.',
        open: false,
      },
      {
        question: 'Làm thế nào để kiểm tra bảo hành sản phẩm?',
        answer: 'Bạn có thể kiểm tra bảo hành bằng cách truy cập website chính thức của Apple và nhập số Serial Number của sản phẩm. Hoặc liên hệ hotline 1900 xxxx để được hỗ trợ.',
        open: false,
      },
      {
        question: 'TopZone có giao hàng toàn quốc không?',
        answer: 'Có. TopZone giao hàng toàn quốc miễn phí cho đơn hàng trên 5 triệu đồng. Đơn hàng dưới 5 triệu sẽ có phí ship tùy theo khu vực. Thời gian giao hàng từ 1-3 ngày tùy địa điểm.',
        open: false,
      },
    ]);

    const toggleFaq = (index) => {
      faqs.value[index].open = !faqs.value[index].open;
    };

    const submitForm = async () => {
      loading.value = true;

      try {
        await new Promise(resolve => setTimeout(resolve, 2000));

        showToast('Gửi tin nhắn thành công! Chúng tôi sẽ liên hệ với bạn sớm nhất.', 'success');

        form.value = {
          name: '',
          phone: '',
          email: '',
          subject: '',
          message: '',
        };
      } catch (error) {
        showToast('Có lỗi xảy ra. Vui lòng thử lại!', 'error');
      } finally {
        loading.value = false;
      }
    };

    const showToast = (message, type = 'success') => {
      toast.value = { show: true, message, type };
      setTimeout(() => {
        toast.value.show = false;
      }, 3000);
    };

    return {
      form,
      loading,
      stores,
      faqs,
      toast,
      toggleFaq,
      submitForm,
    };
  },
};
</script>

<style scoped>
@keyframes blob {
  0%, 100% { transform: translate(0, 0) scale(1); }
  33% { transform: translate(30px, -50px) scale(1.1); }
  66% { transform: translate(-20px, 20px) scale(0.9); }
}

.animate-blob {
  animation: blob 7s infinite;
}

.animation-delay-2000 {
  animation-delay: 2s;
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