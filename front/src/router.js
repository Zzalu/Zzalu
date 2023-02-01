import { createWebHistory, createRouter } from "vue-router";
// import App from "./App"


const routes = [
  {
    path: "/",
    redirect: "/main"
    // component: App,
  },
  // --------------------------------------------------------
  // ACCOUNTS

  // 로그인
  {
    path: "/login",
    name: "login",
    component: () => import ('@/views/Accounts/LoginView'),
  },
  // 회원가입
  {
    path: "/signup",
    name: "signup",
    component: () => import ('@/views/Accounts/SignUpView'),
  },
  // 아이디찾기
  {
    path: "/find-id",
    name: "find-id",
    component: () => import ('@/views/Accounts/FindIdView'),
  },
  // 비번찾기(재설정)
  {
    path: "/reset-password",
    name: "reset-password",
    component: () => import ('@/views/Accounts/ResetPasswordView'),
  },
  // 탈퇴
  {
    path: "/account-delete",
    name: "account-delete",
    component: () => import ('@/views/Accounts/AccountDeleteView'),
  },
  // 설정에서 비번변경
  {
    path: "/change-password",
    name: "change-password",
    component: () => import ('@/views/Accounts/ChangePasswordView'),
  },
  // ----------------------------------------------------------------
  // QUIET CHAT
  // 고독방 안
  {
    path: "/chat/:chat_id",
    name: "chat",
    component: () => import ('@/views/QuietChat/ChatInsideView'),
  },
  // 고독방 만들기
  {
    path: "/make-chat",
    name: "make-chat",
    component: () => import ('@/views/QuietChat/MakeChatView'),
  },
  // 채팅 리스트
  {
    path: "/chat-list",
    name: "chat-list",
    component: () => import ('@/views/QuietChat/ChatListView'),
  },
  
  // ERROR
  // 에러404 페이지X
  {
    path: '/:pathMatch(.*)*',
    redirect: '/error-404',
  },
  {
    path:'/error-404',
    name: "error-404",
    component: () => import ('@/views/Error/Error404View'),
  },
  // 에러 500 internal server error
  {
    path: "/error-500",
    name: "error-500",
    component: () => import ('@/views/Error/Error500View'),
  },
  // 로그인 필요
  {
    path: "/login-required",
    name: "login-required",
    component: () => import ('@/views/Error/LoginRequiredView'),
  },
  // ------------------------------------------------------------
  // MAIN
  {
    path: "/main",
    name: "main",
    component: () => import ('@/views/MainView'),
  },
  // ------------------------------------------------------------------
  // PROFILE
  // 프로필 메인
  {
    path: "/profile/:user_id",
    name: "profile",
    component: () => import ('@/views/Profile/ProfileView'),
  },  
  // 팔로우 팔로잉
  {
    path: "/profile/:user_id/follow",
    name: "follow",
    component: () => import ('@/views/Profile/FollowView'),
  },  
  // 설정 가는 페이지
  {
    path: "/:user_id/settings",
    name: "settings",
    component: () => import ('@/views/Profile/UserSettingsView'),
  },
  // 프로필 정보 수정
  {
    path: "/:user_id/edit-profile",
    name: "edit-profile",
    component: () => import ('@/views/Profile/EditProfileView'),
  },
  // -----------------------------------------------------------------
  // 제목학원
  {
    path: "/title-competition/:id",
    name: "title-competition",
    component: () => import ('@/views/TitleCompetitionView'),
  },
  // --------------------------------------------------------------------
  // 짤 디테일
  {
  path: "/zzal/:zzal_id",
  name: "zzal",
  component: () => import ('@/views/ZzalDetailView'),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;