<template>
    <div class="container mt-5">
        <h2 class="text-center mb-4"><svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="red" class="bi bi-flag-fill" viewBox="0 0 16 16">
  <path d="M14.778.085A.5.5 0 0 1 15 .5V8a.5.5 0 0 1-.314.464L14.5 8l.186.464-.003.001-.006.003-.023.009a12 12 0 0 1-.397.15c-.264.095-.631.223-1.047.35-.816.252-1.879.523-2.71.523-.847 0-1.548-.28-2.158-.525l-.028-.01C7.68 8.71 7.14 8.5 6.5 8.5c-.7 0-1.638.23-2.437.477A20 20 0 0 0 3 9.342V15.5a.5.5 0 0 1-1 0V.5a.5.5 0 0 1 1 0v.282c.226-.079.496-.17.79-.26C4.606.272 5.67 0 6.5 0c.84 0 1.524.277 2.121.519l.043.018C9.286.788 9.828 1 10.5 1c.7 0 1.638-.23 2.437-.477a20 20 0 0 0 1.349-.476l.019-.007.004-.002h.001"/>
</svg>  내가 정복한 산  <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="red" class="bi bi-flag-fill" viewBox="0 0 16 16">
  <path d="M14.778.085A.5.5 0 0 1 15 .5V8a.5.5 0 0 1-.314.464L14.5 8l.186.464-.003.001-.006.003-.023.009a12 12 0 0 1-.397.15c-.264.095-.631.223-1.047.35-.816.252-1.879.523-2.71.523-.847 0-1.548-.28-2.158-.525l-.028-.01C7.68 8.71 7.14 8.5 6.5 8.5c-.7 0-1.638.23-2.437.477A20 20 0 0 0 3 9.342V15.5a.5.5 0 0 1-1 0V.5a.5.5 0 0 1 1 0v.282c.226-.079.496-.17.79-.26C4.606.272 5.67 0 6.5 0c.84 0 1.524.277 2.121.519l.043.018C9.286.788 9.828 1 10.5 1c.7 0 1.638-.23 2.437-.477a20 20 0 0 0 1.349-.476l.019-.007.004-.002h.001"/>
</svg></h2>
        
        <div v-if="commentStore.MyCommentList.length === 0" class="text-center">
            <p>아직 등록한 코멘트가 없습니다.</p>
        </div>
        <div v-else class="row">
            <div v-for="mountain in commentStore.MyCommentList" :key="mountain.serial" class="col-md-4 mb-3">
                <div class="card mx-auto" style="width: 9rem;">
                    <div class="card-body">
                        <h5 class="card-title">{{ mountain.mountainName }}</h5>
                        <p class="card-text">등산 날 :<br> {{ new Date(mountain.regDate).toISOString().split('T')[0] }}</p>
                        <p class="card-text">걸린 시간: {{ mountain.turnaround }}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { useReplyStore } from "@/stores/replystore";
import axios from "axios";
const route = useRoute();
const router = useRouter();
const replyStore = useReplyStore();
import { useUserStore } from '@/stores/userstore';
import { ref, computed, onMounted } from 'vue';
import { useMountainStore } from '@/stores/mountainstore';
import { useCommentStore } from '@/stores/commentstore';

const mountainStore = useMountainStore()
const commentStore = useCommentStore()
const userStore = useUserStore();

const userName = ref(''); // 사용자 이름
const userSerial = ref(''); // 사용자 serial

const checkLoginStatus = () => {
    const token = sessionStorage.getItem('access-token');
    if (token) {
        try {
            const tokenPayload = JSON.parse(decodeURIComponent(escape(atob(token.split('.')[1]))));
            userName.value = tokenPayload['name'];
            userSerial.value = tokenPayload['serial'];
        } catch (error) {
            console.error('Failed to decode token:', error);
        }
    } else {
    }
};

onMounted(() => {
    checkLoginStatus();
    userStore.getUserByid(`${route.params.userId}`)
        .then(() => {
            userStore.getfollwerList(userSerial.value);
            userStore.getfollwingList(userSerial.value);
            mountainStore.getCompleteMountain(userSerial.value);
            commentStore.completeComment(userStore.User.serial);
        });
})
</script>

<style scoped>
.container {
    margin: 10px; 
    display: flex;
    flex-direction: column;
    background-color: rgb(248, 249, 253);
    border: 3px solid rgba(43, 97, 247, 0.68);
    padding: 30px;
    font-family: "Gowun Dodum", sans-serif;
    font-size: larger;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.card {
    border: none;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-title {
    font-size: 1.25rem;
    font-weight: bold;
}

.card-text {
    font-size: 1rem;
    color: #555;
}

.text-center {
    text-align: center;
}

.mt-5 {
    margin-top: 3rem;
}

.mb-3 {
    margin-bottom: 1rem;
}

.mb-4 {
    margin-bottom: 1.5rem;
}
</style>
