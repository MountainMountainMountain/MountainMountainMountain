<template>
    <div>
        <h1>detail</h1>
        <Photo />
        <RouterLink :to="{ name: 'MountainInfo' }">정보</RouterLink> |
        <RouterLink :to="{ name: 'MountainLocation' }">위치</RouterLink> |
        이건 동적으로 바뀔 예정
        <RouterLink v-if="token !== null" @click="showReplyForm = true" :to="{ name: 'Book' }">예약</RouterLink>
        <RouterView />

    </div>
</template>

<script setup>
import Photo from '@/components/mountain/component/Photo.vue';

import { useReplyStore } from "@/stores/replystore";
import { ref, computed, onMounted } from 'vue';
import { useUserStore } from '@/stores/userstore';
const userStore = useUserStore();
const userName = ref(''); // 사용자 이름
const userSerial = ref(''); // 사용자 serial
const token = sessionStorage.getItem('access-token');
const checkUserSerial = () => {
    if (token !== null) {
        const payload = JSON.parse(atob(token.split('.')[1]));
        userName.value = payload.name;
        userSerial.value = payload.serial;
        userStore.getUserByid(userSerial.value);
    }
}

onMounted(() => {
    checkUserSerial();
})
</script>

<style scoped></style>