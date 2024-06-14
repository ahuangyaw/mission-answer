import { RouteRecordRaw } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import UserLoginView from "@/views/userViews/UserLoginView.vue";
import UserLayout from "@/layouts/UserLayout.vue";
import UserRegisterView from "@/views/userViews/UserRegisterView.vue";
import ACCESS_ENUM from "@/access/accessEnum";
import AdminUserView from "@/views/adminViews/adminUserView.vue";
import NoAuthView from "@/views/noAuthView.vue";

export const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "首页",
    component: HomeView,
  },
  {
    path: "/admin/user",
    name: "管理用户",
    component: AdminUserView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/noAuth",
    name: "无权限",
    component: NoAuthView,
    meta: {
      hideInMenu: true,
    },
  },
  {
    path: "/user",
    name: "用户",
    component: UserLayout,
    children: [
      {
        path: "/user/login",
        name: "用户登录",
        component: UserLoginView,
      },
      {
        path: "/user/register",
        name: "用户注册",
        component: UserRegisterView,
      },
    ],
    meta: {
      hideInMenu: true,
    },
  },
];
