<template>
    <div>
        <h1>friends</h1>

        <h1>following</h1>

        <div class="search">
            <div>
                <label>검색 기준 :</label>
                <select v-model="searchInfo.key">
                    <option value=''>없음</option>
                    <option value="name">산 이름</option>
                    <option value="state">위치</option>
                </select>
            </div>
            <div>
                <label><input type="text" class="form-control" v-model="searchInfo.word"
                        placeholder="산을 입력하세요" /></label>
            </div>
            <div>
                <label>정렬 기준 :</label>
                <select v-model="searchInfo.orderBy">
                    <option value=''>없음</option>
                    <option value="name">산 이름</option>
                    <option value="state">위치</option>
                </select>
            </div>
            <div>
                <label>정렬 방향 :</label>
                <select v-model="searchInfo.orderByDir">
                    <option value="asc">오름차순</option>
                    <option value="desc">내림차순</option>
                </select>
            </div>
            <div>
                <button class="btn btn-primary" @click="searchMountainList"><i class="fas fa-search"
                        style="color: white"></i></button>
            </div>
        </div>

        <div v-for="following in userStore.FollowingList" :key="following.serial">
            <!-- {{ following.serial }} -->
            {{ following.name }}
        </div>
        <h1>follower</h1>
        <div v-for="follower in userStore.FollowerList" :key="follower.serial">
            <!-- {{ follower.serial }} -->
            {{ follower.name }}
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

const userStore = useUserStore();

const searchInfo = ref({
    key: '',
    word: '',
    orderBy: '',
    orderByDir: 'asc',
});

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
    userStore.getfollwerList(userSerial.value);
    userStore.getfollwingList(userSerial.value);
})

</script>

<style scoped></style>