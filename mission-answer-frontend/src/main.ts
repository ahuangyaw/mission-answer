import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import ArcoVue from "@arco-design/web-vue";
import "@arco-design/web-vue/dist/arco.css";
import { createPinia } from "pinia";
import "@/access";
// 额外引入图标库
import ArcoVueIcon from "@arco-design/web-vue/es/icon";

const pinia = createPinia();

createApp(App)
  .use(ArcoVue)
  .use(ArcoVueIcon)
  .use(pinia)
  .use(router)
  .mount("#app");
