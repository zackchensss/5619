
import { createRouter, createWebHistory } from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'
import ChatPage from "../components/ChatPage/ChatPage.vue"

const routes = [
    { path: '/', name: 'Home', component: HelloWorld },
    { path: '/ChatPage', name: 'ChatPage', component: ChatPage }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
