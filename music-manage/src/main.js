import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import axios from 'axios';

const app = createApp(App);
app.use(ElementPlus);
app.use(store);
app.use(router);
app.mount("#app");
axios.interceptors.request.use(
    config => {
        if(localStorage.getItem('token')){
            config.headers.token = `${localStorage.getItem('token')}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);
