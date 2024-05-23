<template>
    <div class="container mt-5">
        <!-- <h1 class="text-center mb-4">내 코멘트</h1> -->
        <div v-if="commentStore.MyCommentList.length === 0" class="text-center">
            <p>아직 등록한 코멘트가 없습니다.</p>
        </div>
        <div v-else class="row">
            <!-- 내 코멘트 표시 -->
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

<style lang="scss" scoped>
.container {
    max-width: 600px;
    margin: auto;
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
