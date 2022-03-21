import { createRouter, createWebHashHistory } from "vue-router";
const routes = [
  {
    path: "/",
    name: "login",
    component: () => import("../views/loginView.vue"),
  },
  {
	  path: "/AdminIndex",
	  name: "index",
	  component: () => import("../views/adminIndexView.vue"),
  },
  {
	  path: "/SystemIndex",
	  component: () => import("../views/contents/SystemIndexView.vue"),
  },
  {
  	  path: "/UserManage",
  	  component: () => import("../views/contents/UserManageView.vue"),
  },
  {
  	  path: "/SongManage",
  	  component: () => import("../views/contents/SongManageView.vue"),
  },
  {
  	  path: "/SingerManage",
  	  component: () => import("../views/contents/SingerManageView.vue"),
  },
  {
  	  path: "/AlbumManage",
  	  component: () => import("../views/contents/AlbumManageView.vue"),
  },
  {
  	  path: "/CommentManage",
  	  component: () => import("../views/contents/CommentManageView.vue"),
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
