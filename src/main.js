
import { createApp } from 'vue'
import { createPinia } from 'pinia';
import { createPersistedState } from "pinia-persistedstate-plugin";
import ElementPlus from 'element-plus'
import router from "@/router";
import 'element-plus/dist/index.css'
import App from './App.vue'


const app = createApp(App)
const pinia = createPinia()
const persist = createPersistedState()

app.use(ElementPlus)
app.use(router)
app.use(pinia)
pinia.use(persist)

app.mount('#app')
