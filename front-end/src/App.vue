<template>
  <div class="app-container">
    <component :is="layout">
      <router-view />
      <footer-page />
    </component>
  </div>
</template>

<script lang="ts" setup>
import { computed, ref, provide, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import FooterPage from "@/components/FooterPage.vue";

// --- User State Management ---
const user = ref(null);

const login = (userData: any) => {
  user.value = userData;
  localStorage.setItem('user', JSON.stringify(userData));
};

const logout = () => {
  user.value = null;
  localStorage.removeItem('user');
  // No longer need to redirect, the UI will update automatically
};

// Provide user state and methods to all child components
provide('user', user);
provide('login', login);
provide('logout', logout);

// --- Layout Management ---
const defaultLayout = 'default'
const router = useRouter()
const { currentRoute } = router

const layout = computed(
  () => `${currentRoute.value.meta.layout || defaultLayout}-layout`
)

// --- Check for logged-in user on app startup ---
onMounted(() => {
  const storedUser = localStorage.getItem('user');
  if (storedUser) {
    try {
      user.value = JSON.parse(storedUser);
    } catch (e) {
      console.error('Failed to parse user from localStorage', e);
      localStorage.removeItem('user');
    }
  }
});

</script>

<style scoped>
.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

router-view {
  flex: 1 0 auto;
}

footer-page {
  flex-shrink: 0;
}
</style>