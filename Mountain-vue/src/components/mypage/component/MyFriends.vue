<template>
    <div>
        <h1>friends</h1>


        <div class="search">
            <div>
                <label><input type="text" class="form-control" v-model="name" placeholder="이름을 입력하세요" /></label>
            </div>
            <div>
                <button class="btn btn-primary" @click="searchFriendList"><i class="fas fa-search"
                        style="color: white"></i></button>
            </div>
        </div>
        
        <div v-for="friend in userStore.UserList" :key="friend.serial">
            {{ friend.name }}
        </div>
        <h1>following</h1>

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

const name = ref('')

const searchFriendList = () => {
    userStore.getUserByName(name.value, userSerial.value);
};

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
    userStore.getUserByid(`${route.params.userId}`);
    userStore.UserList = null;
})

</script>

<style scoped></style>