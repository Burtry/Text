
import { createApp } from 'vue'
import { createPinia } from 'pinia';
import ElementPlus from 'element-plus'
import router from "@/router";
import 'element-plus/dist/index.css'
import App from './App.vue'

const app = createApp(App)
const pinia = createPinia()

app.use(ElementPlus)
app.use(router)
app.use(pinia)


app.mount('#app')
