
import './assets/main.scss'
import { createApp } from 'vue'
import { createPinia } from 'pinia';
import { createPersistedState } from "pinia-persistedstate-plugin";
import ElementPlus from 'element-plus'
import router from "@/router";
import 'element-plus/dist/index.css'
import App from './App.vue'

import locale from 'element-plus/es/locale/lang/zh-cn'

const app = createApp(App)
const pinia = createPinia()
const persist = createPersistedState()

app.use(ElementPlus, { locale })
app.use(router)
app.use(pinia)
pinia.use(persist)
app.mount('#app')

