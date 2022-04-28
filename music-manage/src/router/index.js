import { createRouter, createWebHashHistory } from "vue-router";
const routes = [
    {
        path: "/login",
        name: "login",
        component: () => import("../views/loginView.vue"),
    },
    {
        path: "/AdminIndex",
        name: "index",
        component: () => import("../views/adminIndexView.vue"),
        children: [
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
            {
                path: "/CollectManage",
                component: () => import("../views/contents/CollectManageView"),
            }
        ]
    },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});
router.beforeEach((name,from,next) =>{
    if(name.path === '/login'){
        next();
    }else {
        if(localStorage.getItem('token')){
            next();
        }else {
            next({name: 'login'});
        }
    }
});
const baseUrl = {baseUrl: 'http://127.0.0.2:8081'};
export default {router,baseUrl};
