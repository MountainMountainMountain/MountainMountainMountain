<template>
    <div>
        <h1>chatlist</h1>

        <div>로그인 유저의 시리얼 : {{ userSerial }}</div>

        

        <!-- <div>
            <div v-for="chatlog in chatStore.ChatLogList" :key="chatlog.serial">
                <div v-if="userSerial == chatlog.userSerial" style="text-align: right;">
                    <span> 가는 산: {{ chatlog.mountainName }}</span>
                    <span> 가는 날짜: {{ chatlog.hikingDate }}</span>
                    <span> name: {{ chatlog.userName }}</span>
                    <span> , content: {{ chatlog.content }}</span>
                    <span> , chatCreateDate: {{ chatlog.chatCreateDate }} </span>
                    <span> , point: {{ chatlog.userPoint }}</span>
                    <span> , gender: {{ chatlog.userGender }}</span>
                    <span> , userserial: {{ chatlog.userSerial }}</span>
                </div>
                <div v-else>
                    <span> 가는 산: {{ chatlog.mountainName }}</span>
                    <span> 가는 날짜: {{ chatlog.hikingDate }}</span>
                    <span> name: {{ chatlog.userName }}</span>
                    <span> , content: {{ chatlog.content }}</span>
                    <span> , chatCreateDate: {{ chatlog.chatCreateDate }} </span>
                    <span> , point: {{ chatlog.userPoint }}</span>
                    <span> , gender: {{ chatlog.userGender }}</span>
                    <span> , userserial: {{ chatlog.userSerial }}</span>
                </div>
            </div>
        </div> -->
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useChatStore } from '@/stores/chatstore';
import { useUserStore } from '@/stores/userstore';

const chatStore = useChatStore()


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
    chatStore.getChatListByChatInfoSerial(1);
})
</script>

<style scoped></style>