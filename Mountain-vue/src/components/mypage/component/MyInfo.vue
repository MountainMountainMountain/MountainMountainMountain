<template>
    <div>
        <router-view />
    </div>



    
</template>

<script setup>
// import MyState from './MyState.vue';
import { useUserStore } from '@/stores/userstore';
import { ref, computed, onMounted } from 'vue';

const userStore = useUserStore();

const userName = ref(''); // 사용자 이름
const userSerial = ref(''); // 사용자 serial
const userId = ref(''); // 사용자 id
const userEmail = ref(''); // 사용자 email
const token = sessionStorage.getItem('access-token');

// let id = JSON.parse(atob(token[1]))['id'];


const checkUserSerial = () => {
    const payload = JSON.parse(atob(token.split('.')[1]));
    userName.value = payload.name;
    userSerial.value = payload.serial;
    userId.value = payload.id;
    userEmail.value = payload.email;
}

onMounted(() => {
    checkUserSerial();
    // userStore.getUserByid();
    // userStore.getfollwingList(userStore.User);
    // userStore.getfollwerList(userStore.User);
})

</script>

<style scoped>

</style>
