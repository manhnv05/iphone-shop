import { createApp } from "vue";
import VueApexCharts from "vue3-apexcharts";
import DashboardLayout from "./components/DashboardLayout.vue";
import EmptyLayout from "./components/EmptyLayout.vue";
import "./assets/tailwind.css";
import '@fortawesome/fontawesome-free/css/all.min.css';

import App from "./App.vue";
import router from "./router";
import axios from "axios";

// Set default Axios configuration
axios.defaults.withCredentials = true;

// --- Axios Response Interceptor ---
// This interceptor will catch all API responses.
axios.interceptors.response.use(
  // If the response is successful (status 2xx), just pass it through.
  (response) => response,
  
  // If the response has an error...
  (error) => {
    // Check if the error is a 401 Unauthorized response.
    if (error.response && error.response.status === 401) {
      // This means the user's session has expired or they are not logged in.
      
      // 1. Clear user data from localStorage.
      // We do this directly here to ensure it's always cleared on a 401.
      localStorage.removeItem('user');

      // 2. Redirect to the login page.
      // We use router.push to navigate. The 'catch' is to prevent errors if the navigation is redundant.
      router.push('/login').catch(err => {
        if (err.name !== 'NavigationDuplicated') {
          console.error(err);
        }
      });
      
      // We can also show a toast message here if we have a global toast system.
      console.error("Session expired or unauthorized. Redirecting to login.");
    }
    
    // For all other errors (400, 403, 500, etc.), just pass the error along
    // so it can be handled by the specific component's catch block.
    return Promise.reject(error);
  }
);


const app = createApp(App);
app.component("default-layout", DashboardLayout);
app.component("empty-layout", EmptyLayout);

app.use(router).use(VueApexCharts).mount("#app");
