import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-icons/font/bootstrap-icons.css"

import { createApp } from 'vue';
import { createPinia } from 'pinia';

import App from './App.vue';
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import router from './router';
import 'bootstrap';

const app = createApp(App);
app.use(VueSweetalert2);
app.use(createPinia());
app.use(router);

app.mount('#app')
import "bootstrap/dist/js/bootstrap.min.js"
