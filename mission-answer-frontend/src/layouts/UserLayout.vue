<template>
  <div id="userLayout">
    <a-layout style="height: 100vh">
      <a-layout-header class="header">
        <a-space>
          <img class="logo" src="../assets/logo.png" alt="logo" />
          <div class="title">使命必达-AI答题应用平台</div>
        </a-space>
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">
        <a
          href="http://github.com/ahuangyaw"
          target="_blank"
          style="text-decoration: none; color: black"
          >小黄
        </a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
#userLayout {
  text-align: center;
  background: url("@/assets/login-bg.jpg") no-repeat;
  background-size: 100% 130%;
}

#userLayout .logo {
  width: 48px;
  height: 48px;
}
#userLayout .title {
  color: white;
}

#userLayout .header {
  margin-top: 16px;
  font-size: 21px;
}

#userLayout .content {
}

.footer {
  margin-bottom: 16px;
  text-align: center;
}
</style>
