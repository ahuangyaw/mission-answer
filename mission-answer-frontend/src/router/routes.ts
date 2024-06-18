import { RouteRecordRaw } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import UserLoginView from "@/views/userViews/UserLoginView.vue";
import UserLayout from "@/layouts/UserLayout.vue";
import UserRegisterView from "@/views/userViews/UserRegisterView.vue";
import ACCESS_ENUM from "@/access/accessEnum";
import AdminUserView from "@/views/adminViews/adminUserView.vue";
import NoAuthView from "@/views/noAuthView.vue";
import AdminAppView from "@/views/adminViews/adminAppView.vue";
import AdminQuestionView from "@/views/adminViews/adminQuestionView.vue";
import AdminScoringResultView from "@/views/adminViews/adminScoringResultView.vue";
import AdminUserAnswerView from "@/views/adminViews/adminUserAnswerView.vue";
import TestView from "@/views/TestView.vue";
import AppDetailView from "@/views/app/AppDetailView.vue";
import AddAppView from "@/views/add/AddAppView.vue";
import AddQuestionView from "@/views/add/AddQuestionView.vue";
import AddScoringResultView from "@/views/add/AddScoringResultView.vue";
import DoAnswerView from "@/views/answer/doAnswerView.vue";
import AnswerResultView from "@/views/answer/answerResultView.vue";
import MyAnswerView from "@/views/answer/myAnswerView.vue";

export const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "主页",
    component: HomeView,
  },
  {
    path: "/admin/user",
    name: "用户管理",
    component: AdminUserView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/admin/app",
    name: "应用管理",
    component: AdminAppView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/admin/question",
    name: "题目管理",
    component: AdminQuestionView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/admin/scoring_result",
    name: "评分管理",
    component: AdminScoringResultView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/admin/user_answer",
    name: "回答管理",
    component: AdminUserAnswerView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/app/detail/:id",
    name: "应用详情",
    props: true,
    component: AppDetailView,
    meta: {
      hideInMenu: true,
    },
  },
  {
    path: "/add/app",
    name: "创建应用",
    component: AddAppView,
  },
  {
    path: "/add/app/:id",
    name: "修改应用",
    props: true,
    component: AddAppView,
    meta: {
      hideInMenu: true,
    },
  },
  {
    path: "/add/question/:appId",
    name: "创建题目",
    props: true,
    component: AddQuestionView,
    meta: {
      hideInMenu: true,
    },
  },
  {
    path: "/add/scoring_result/:appId",
    name: "创建评分",
    props: true,
    component: AddScoringResultView,
    meta: {
      hideInMenu: true,
    },
  },
  {
    path: "/answer/do/:appId",
    name: "答题",
    component: DoAnswerView,
    props: true,
    meta: {
      hideInMenu: true,
      access: ACCESS_ENUM.USER,
    },
  },
  {
    path: "/answer/result/:id",
    name: "答题结果",
    component: AnswerResultView,
    props: true,
    meta: {
      hideInMenu: true,
      access: ACCESS_ENUM.USER,
    },
  },
  {
    path: "/answer/my",
    name: "我的答题",
    component: MyAnswerView,
    meta: {
      access: ACCESS_ENUM.USER,
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
  {
    path: "/noAuth",
    name: "无权限",
    component: NoAuthView,
    meta: {
      hideInMenu: true,
    },
  },
  {
    path: "/test",
    name: "测试",
    component: TestView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
];
