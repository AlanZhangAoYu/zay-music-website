import { createRouter, createWebHashHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'index',
    component: () => import('../views/index.vue'),
    children: [
      {
        path: '/AlbumView',
        component: () => import('../views/contents/album')
      },
      {
        path: '/ListView',
        component: () => import('../views/contents/list')
      },
      {
        path: '/MainView',
        component: () => import('../views/contents/main')
      },
      {
        path: '/SingerView',
        component: () => import('../views/contents/singer')
      },
    ]
  }
];
const router = createRouter({
  history: createWebHashHistory(),
  routes
});
/*router.beforeEach((name,from,next) =>{
  if(name.path === '/'){
    next();
  }else {
    if(localStorage.getItem('token')){
      next();
    }else {
      next({name: 'index'});
    }
  }
});*/
const baseUrl = {baseUrl: 'http://127.0.0.2:8081'};
export default {router,baseUrl};
