import { createWebHistory, createRouter } from "vue-router";
import App from "./App"
// ACCOUNTS
import SignUpView from "./views/Accounts/SignUpView";
import LoginView from "./views/Accounts/LoginView";
import FindIdView from "./views/Accounts/FindIdView";
import ChangePasswordView from "./views/Accounts/ChangePasswordView";
// QUIET CHAT
import ChatListView from "./views/QuietChat/ChatListView";
import MakeChatView from "./views/QuietChat/MakeChatView";
import ChatInsideView from "./views/QuietChat/ChatInsideView";
// Error
import Error404View from "./views/Error/Error404View";
import Error500View from "./views/Error/Error500View";
import LoginRequiredView from "./views/Error/LoginRequiredView";
// MAIN
import MainView from "./views/MainView";
// PROFILE
import ProfileView from "./views/ProfileView";
// TITLE COMPETITION
import TitleCompetitionView from "./views/TitleCompetitionView";
// ZZAL DETAIL
import ZzalDetailView from "./views/ZzalDetailView";
// temp
import HelloWorldView from "./views/HelloWorldView"



const routes = [
  {
    path: "/",
    component: App,
  },
  // ACCOUNTS
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/signup",
    name: "signup",
    component: SignUpView,
  },
  {
    path: "/find-id",
    name: "find-id",
    component: FindIdView,
  },
  {
    path: "/change-password",
    name: "change-password",
    component: ChangePasswordView,
  },
  // QUIET CHAT
  {
    path: "/chat/:chat_id",
    name: "chat",
    component: ChatInsideView,
  },
  {
    path: "/make-chat",
    name: "make-chat",
    component: MakeChatView,
  },
  {
    path: "/chat-list",
    name: "chat-list",
    component: ChatListView,
  },
  // ERROR
  {
    path: "/error-404",
    name: "error-404",
    component: Error404View,
  },
  {
    path: "/error-500",
    name: "error-500",
    component: Error500View,
  },
  {
    path: "/login-required",
    name: "login-required",
    component: LoginRequiredView,
  },
  // MAIN
  {
    path: "/main",
    name: "main",
    component: MainView,
  },
  {
    path: "/profile/:user_id",
    name: "profile",
    component: ProfileView,
  },
  {
    path: "/title-competition/:id",
    name: "title-competition",
    component: TitleCompetitionView,
  },
  {
  path: "/zzal/:zzal_id",
  name: "zzal",
  component: ZzalDetailView,
  },
  // temp
  {
    path: "/HelloWorld",
    name: "HelloWorldView",
    component: HelloWorldView,
  },

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;