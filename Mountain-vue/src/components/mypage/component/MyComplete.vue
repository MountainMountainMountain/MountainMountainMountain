<template>
    <div>
        <h1>complete</h1>
        <div v-for="mountain in mountainStore.completeMountain" :key="mountain.commentRegDate">
            <!-- {{ follower.serial }} -->
            산 이름 : {{ mountain.mountainName }}
            <br>
            간 날짜 : {{ new Date(mountain.commentRegDate).toISOString().split('T')[0] }}
            <br>
            난이도 : {{ mountain.difficulty }}
            <br>
            도 : {{ mountain.state }}
        </div>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { useCommentStore } from "@/stores/commentstore";
import { useReplyStore } from "@/stores/replystore";
import axios from "axios";
const route = useRoute();
const router = useRouter();
const commentStore = useCommentStore();
const replyStore = useReplyStore();
import { useUserStore } from '@/stores/userstore';
import { ref, computed, onMounted } from 'vue';
import { useMountainStore } from '@/stores/mountainstore';

const mountainStore = useMountainStore()
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
    mountainStore.getCompleteMountain(userSerial.value);
})
</script>

<style lang="scss" scoped></style>