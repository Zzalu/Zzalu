import { createWebHistory, createRouter } from "vue-router";
import QuietChatListView from "./views/QuietChat/QuietChatListView"
import App from "./App"

const routes = [
  {
    path: "/",
    component: App,
  },
  {
    path: "/QuietChatListView",
    component: QuietChatListView,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;