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
              <path d="M9 4.804A7.968 7.968 0 005.5 4c-1.255 0-2.443.29-3.5.804v10A7.969 7.969 0 015.5 14c1.669 0 3.218.51 4.5 1.385A7.962 7.962 0 0114.5 14c1.255 0 2.443.29 3.5.804v-10A7.968 7.968 0 0014.5 4c-1.255 0-2.443.29-3.5.804V12a1 1 0 11-2 0V4.804z" />
            </svg>
            <span class="font-semibold text-sm">Blog TopZone</span>
          </div>

          <h1 class="text-3xl lg:text-5xl font-black mb-4 leading-tight">
            Tin tức & <span class="text-yellow-300">Bài viết</span>
          </h1>
          <div class="h-1 w-24 bg-gradient-to-r from-yellow-300 to-pink-400 rounded-full mx-auto mb-6"></div>

          <p class="text-base lg:text-lg text-blue-100">
            Cập nhật tin tức mới nhất về iPhone, Apple và công nghệ
          </p>
        </div>
      </div>
    </div>

    <!-- Search & Filter -->
    <div class="bg-white border-b border-gray-200 sticky top-0 z-40 shadow-sm">
      <div class="container mx-auto px-4 py-4">
        <div class="flex flex-col md:flex-row gap-3 items-center justify-between">
          <div class="relative flex-1 max-w-xl w-full">
            <input
              v-model="searchQuery"
              type="text"
              placeholder="Tìm kiếm bài viết..."
              class="w-full pl-11 pr-4 py-2.5 border-2 border-gray-200 rounded-lg focus:outline-none focus:border-blue-500 transition-all text-sm"
            />
            <svg class="w-5 h-5 absolute left-3 top-3 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
            </svg>
          </div>

          <div class="flex gap-2 flex-wrap justify-center">
            <button
              v-for="cat in categories"
              :key="cat.id"
              @click="selectedCategory = cat.id"
              :class="[
                'px-4 py-2 rounded-lg font-semibold transition-all text-sm',
                selectedCategory === cat.id
                  ? 'bg-blue-600 text-white shadow-md'
                  : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
              ]"
            >
              {{ cat.name }}
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Featured Post -->
    <div v-if="featuredPost" class="container mx-auto px-4 py-10">
      <div class="bg-white rounded-2xl overflow-hidden shadow-xl hover:shadow-2xl transition-all cursor-pointer group" @click="goToPost(featuredPost.id)">
        <div class="grid md:grid-cols-2 gap-0">
          <div class="relative h-80 md:h-auto overflow-hidden">
            <img :src="featuredPost.image" :alt="featuredPost.title" class="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500" />
            <div class="absolute top-4 left-4">
              <span class="bg-red-600 text-white px-4 py-2 rounded-lg text-sm font-bold shadow-lg">
                ⭐ Nổi bật
              </span>
            </div>
            <div class="absolute inset-0 bg-gradient-to-t from-black/50 to-transparent opacity-0 group-hover:opacity-100 transition-opacity"></div>
          </div>
          <div class="p-8 md:p-10 flex flex-col justify-center">
            <div class="flex items-center gap-3 mb-4">
              <span class="bg-blue-100 text-blue-600 px-3 py-1 rounded-lg text-sm font-semibold">
                {{ featuredPost.category }}
              </span>
              <span class="text-gray-500 text-sm flex items-center gap-1">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                </svg>
                {{ featuredPost.date }}
              </span>
            </div>
            <h2 class="text-2xl lg:text-3xl font-black text-gray-900 mb-4 group-hover:text-blue-600 transition-colors">
              {{ featuredPost.title }}
            </h2>
            <p class="text-gray-600 mb-6 leading-relaxed text-sm lg:text-base">
              {{ featuredPost.excerpt }}
            </p>
            <div class="flex items-center justify-between">
              <div class="flex items-center gap-3">
                <img :src="featuredPost.author.avatar" :alt="featuredPost.author.name" class="w-10 h-10 rounded-full border-2 border-blue-100" />
                <div>
                  <p class="font-semibold text-gray-900 text-sm">{{ featuredPost.author.name }}</p>
                  <p class="text-xs text-gray-500">{{ featuredPost.readTime }} phút đọc</p>
                </div>
              </div>
              <button class="bg-blue-600 text-white px-6 py-2.5 rounded-lg hover:bg-blue-700 transition-all font-semibold text-sm flex items-center gap-2 group-hover:gap-3">
                Đọc thêm
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
                </svg>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Posts Grid -->
    <div class="container mx-auto px-4 py-10">
      <div class="flex items-center justify-between mb-6">
        <h2 class="text-2xl font-black text-gray-900">
          {{ selectedCategory === 'all' ? 'Tất cả bài viết' : categories.find(c => c.id === selectedCategory)?.name }}
        </h2>
        <span class="text-sm text-gray-600">
          <strong class="text-blue-600">{{ filteredPosts.length }}</strong> bài viết
        </span>
      </div>

      <div class="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
        <article
          v-for="post in filteredPosts"
          :key="post.id"
          @click="goToPost(post.id)"
          class="bg-white rounded-xl overflow-hidden shadow-md hover:shadow-2xl transition-all cursor-pointer transform hover:-translate-y-1 group"
        >
          <div class="relative h-48 overflow-hidden">
            <img :src="post.image" :alt="post.title" class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-500" />
            <div class="absolute top-3 left-3">
              <span class="bg-white/95 backdrop-blur-sm text-gray-800 px-3 py-1 rounded-lg text-xs font-semibold shadow-md">
                {{ post.category }}
              </span>
            </div>
            <div class="absolute inset-0 bg-gradient-to-t from-black/30 to-transparent opacity-0 group-hover:opacity-100 transition-opacity"></div>
          </div>
          <div class="p-5">
            <div class="flex items-center gap-2 text-xs text-gray-500 mb-3">
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
              </svg>
              {{ post.date }}
            </div>
            <h3 class="text-lg font-bold text-gray-900 mb-3 line-clamp-2 group-hover:text-blue-600 transition-colors">
              {{ post.title }}
            </h3>
            <p class="text-gray-600 mb-4 line-clamp-3 text-sm leading-relaxed">
              {{ post.excerpt }}
            </p>
            <div class="flex items-center justify-between pt-4 border-t border-gray-100">
              <div class="flex items-center gap-2">
                <img :src="post.author.avatar" :alt="post.author.name" class="w-8 h-8 rounded-full border-2 border-gray-100" />
                <span class="text-sm font-semibold text-gray-700">{{ post.author.name }}</span>
              </div>
              <span class="text-xs text-gray-500 flex items-center gap-1">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                {{ post.readTime }} phút
              </span>
            </div>
          </div>
        </article>
      </div>

      <!-- No Results -->
      <div v-if="filteredPosts.length === 0" class="text-center py-20">
        <div class="bg-white rounded-2xl p-12 max-w-md mx-auto shadow-lg">
          <svg class="w-20 h-20 mx-auto text-gray-300 mb-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.172 16.172a4 4 0 015.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
          <h3 class="text-2xl font-bold text-gray-800 mb-3">Không tìm thấy bài viết</h3>
          <p class="text-gray-600 mb-6">Thử tìm kiếm với từ khóa khác hoặc chọn danh mục khác</p>
          <button
            @click="clearFilters"
            class="bg-blue-600 text-white px-6 py-2.5 rounded-lg hover:bg-blue-700 transition-colors font-semibold"
          >
            Xem tất cả bài viết
          </button>
        </div>
      </div>
    </div>

    <!-- Newsletter -->
    <div class="bg-gradient-to-r from-blue-600 to-indigo-700 py-12">
      <div class="container mx-auto px-4">
        <div class="max-w-2xl mx-auto text-center text-white">
          <div class="bg-white/10 w-16 h-16 rounded-2xl flex items-center justify-center mx-auto mb-6">
            <svg class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
            </svg>
          </div>
          <h2 class="text-2xl lg:text-3xl font-black mb-3">Đăng ký nhận tin</h2>
          <p class="text-blue-100 mb-8 text-sm lg:text-base">
            Nhận thông báo về những bài viết mới nhất, tin tức công nghệ và ưu đãi độc quyền
          </p>
          <form @submit.prevent="subscribeNewsletter" class="flex flex-col sm:flex-row gap-3 max-w-md mx-auto">
            <input
              v-model="email"
              type="email"
              required
              placeholder="Nhập email của bạn"
              class="flex-1 px-6 py-3 rounded-lg focus:outline-none focus:ring-2 focus:ring-white text-gray-900"
            />
            <button
              type="submit"
              class="bg-white text-blue-600 px-8 py-3 rounded-lg font-bold hover:bg-blue-50 transition-colors whitespace-nowrap"
            >
              Đăng ký
            </button>
          </form>
        </div>
      </div>
    </div>

    <!-- Related Topics -->
    <div class="bg-white py-12">
      <div class="container mx-auto px-4">
        <h2 class="text-2xl font-black text-gray-900 mb-6 text-center">Chủ đề phổ biến</h2>
        <div class="flex flex-wrap gap-3 justify-center max-w-4xl mx-auto">
          <a
            v-for="topic in topics"
            :key="topic"
            href="#"
            class="px-4 py-2 bg-gray-100 hover:bg-blue-600 hover:text-white rounded-lg text-sm font-medium text-gray-700 transition-all"
          >
            #{{ topic }}
          </a>
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
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import ClientHeader from './components/Header.vue';
import ClientFooter from './components/Footer.vue';

export default {
  name: 'Blog',
  components: {
    ClientHeader,
    ClientFooter,
  },
  setup() {
    const router = useRouter();
    const searchQuery = ref('');
    const selectedCategory = ref('all');
    const email = ref('');

    const toast = ref({
      show: false,
      message: '',
    });

    const categories = ref([
      { id: 'all', name: 'Tất cả' },
      { id: 'news', name: 'Tin tức' },
      { id: 'review', name: 'Đánh giá' },
      { id: 'tutorial', name: 'Hướng dẫn' },
      { id: 'tips', name: 'Mẹo hay' },
    ]);

    const topics = ref([
      'iPhone 15',
      'iOS 17',
      'MacBook',
      'Apple Watch',
      'AirPods',
      'iPad',
      'Bảo hành',
      'Trả góp',
      'So sánh',
      'Mẹo vặt',
    ]);

    const featuredPost = ref({
      id: 1,
      title: 'iPhone 15 Pro Max: Đánh giá chi tiết sau 1 tháng sử dụng',
      excerpt: 'Sau một tháng trải nghiệm iPhone 15 Pro Max, chúng tôi xin chia sẻ những đánh giá chân thực nhất về chiếc smartphone cao cấp nhất của Apple với chip A17 Pro, camera 48MP và nhiều cải tiến đáng chú ý khác.',
      image: 'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=800&h=600&fit=crop',
      category: 'Đánh giá',
      date: '15/11/2025',
      readTime: 8,
      author: {
        name: 'Nguyễn Văn Mạnh',
        avatar: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=100&h=100&fit=crop',
      },
    });

    const posts = ref([
      {
        id: 2,
        title: 'MacBook Air M3: Lựa chọn hoàn hảo cho sinh viên và dân văn phòng',
        excerpt: 'MacBook Air M3 mang đến hiệu năng mạnh mẽ trong thiết kế siêu mỏng nhẹ, phù hợp cho mọi nhu cầu làm việc...',
        image: 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=600&h=400&fit=crop',
        category: 'Đánh giá',
        date: '14/11/2025',
        readTime: 6,
        author: {
          name: 'Trần Thị Lan',
          avatar: 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=100&h=100&fit=crop',
        },
      },
      {
        id: 3,
        title: '10 mẹo tăng thời lượng pin cho iPhone',
        excerpt: 'Khám phá những mẹo đơn giản nhưng hiệu quả giúp kéo dài thời lượng pin iPhone của bạn...',
        image: 'https://images.unsplash.com/photo-1592286927505-ffd7c31548d1?w=600&h=400&fit=crop',
        category: 'Mẹo hay',
        date: '13/11/2025',
        readTime: 5,
        author: {
          name: 'Lê Văn Hùng',
          avatar: 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=100&h=100&fit=crop',
        },
      },
      {
        id: 4,
        title: 'Hướng dẫn sử dụng tính năng mới trên iOS 17',
        excerpt: 'iOS 17 mang đến nhiều tính năng mới thú vị. Cùng tìm hiểu cách sử dụng chúng một cách hiệu quả...',
        image: 'https://images.unsplash.com/photo-1611472173362-3f53dbd65d80?w=600&h=400&fit=crop',
        category: 'Hướng dẫn',
        date: '12/11/2025',
        readTime: 7,
        author: {
          name: 'Phạm Thị Mai',
          avatar: 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=100&h=100&fit=crop',
        },
      },
      {
        id: 5,
        title: 'Apple Watch Series 9: Đồng hồ thông minh đáng mua nhất 2025',
        excerpt: 'Với nhiều cải tiến về sức khỏe và thể thao, Apple Watch Series 9 xứng đáng là lựa chọn hàng đầu...',
        image: 'https://images.unsplash.com/photo-1579586337278-3befd40fd17a?w=600&h=400&fit=crop',
        category: 'Đánh giá',
        date: '11/11/2025',
        readTime: 6,
        author: {
          name: 'Nguyễn Văn Mạnh',
          avatar: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=100&h=100&fit=crop',
        },
      },
      {
        id: 6,
        title: 'AirPods Pro 2: Tai nghe chống ồn tốt nhất hiện nay',
        excerpt: 'Đánh giá chi tiết về AirPods Pro 2 với khả năng chống ồn vượt trội và chất lượng âm thanh xuất sắc...',
        image: 'https://images.unsplash.com/photo-1606841837239-c5a1a4a07af7?w=600&h=400&fit=crop',
        category: 'Đánh giá',
        date: '10/11/2025',
        readTime: 5,
        author: {
          name: 'Trần Thị Lan',
          avatar: 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=100&h=100&fit=crop',
        },
      },
      {
        id: 7,
        title: 'So sánh iPad Air vs iPad Pro: Nên chọn loại nào?',
        excerpt: 'Phân tích chi tiết sự khác biệt giữa iPad Air và iPad Pro để giúp bạn đưa ra lựa chọn phù hợp...',
        image: 'https://images.unsplash.com/photo-1585790050230-5dd28404f8db?w=600&h=400&fit=crop',
        category: 'Tin tức',
        date: '09/11/2025',
        readTime: 8,
        author: {
          name: 'Lê Văn Hùng',
          avatar: 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=100&h=100&fit=crop',
        },
      },
    ]);

    const filteredPosts = computed(() => {
      let result = posts.value;

      if (selectedCategory.value !== 'all') {
        result = result.filter(post =>
          post.category.toLowerCase().includes(selectedCategory.value)
        );
      }

      if (searchQuery.value) {
        result = result.filter(post =>
          post.title.toLowerCase().includes(searchQuery.value.toLowerCase()) ||
          post.excerpt.toLowerCase().includes(searchQuery.value.toLowerCase())
        );
      }

      return result;
    });

    const goToPost = (id) => {
      router.push(`/blog/${id}`);
    };

    const clearFilters = () => {
      searchQuery.value = '';
      selectedCategory.value = 'all';
    };

    const subscribeNewsletter = () => {
      if (email.value) {
        toast.value = {
          show: true,
          message: 'Đăng ký thành công! Cảm ơn bạn đã đăng ký nhận tin.',
        };
        email.value = '';
        setTimeout(() => {
          toast.value.show = false;
        }, 3000);
      }
    };

    return {
      searchQuery,
      selectedCategory,
      email,
      categories,
      topics,
      featuredPost,
      posts,
      filteredPosts,
      toast,
      goToPost,
      clearFilters,
      subscribeNewsletter,
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

.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.line-clamp-3 {
  display: -webkit-box;
  -webkit-line-clamp: 3;
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