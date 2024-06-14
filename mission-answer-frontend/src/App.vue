<template>
  <div id="app">
    <template v-if="route.path.startsWith('/user')">
      <router-view />
    </template>
    <template v-else>
      <BasicLayout />
    </template>
  </div>
</template>
<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout.vue";
import { onMounted } from "vue";
import { useRoute } from "vue-router";
import { getLoginUserUsingGet } from "@/api/userController";
import { useLoginUserStore } from "@/store/userStore";

const route = useRoute();
const loginUserStore = useLoginUserStore();
loginUserStore.fetchLoginUser();

/**
 * 全局初始化函数，有全局单次调用的代码，都可以写到这里
 */
const doInit = () => {
  console.log("hello 欢迎来到我的项目");
};

onMounted(() => {
  doInit();
});
</script>
<style>
#app {
  color: #2c3e50;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

nav {
  padding: 30px;
}

nav a {
  color: #2c3e50;
  font-weight: bold;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
