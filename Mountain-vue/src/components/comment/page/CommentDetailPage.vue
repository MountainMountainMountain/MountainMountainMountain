<template>
    <div>
        <h1>commentdetail</h1>

        <ul>
            <span> 제목: {{ commentStore.Comment.title }}</span>
            <span> , 작성자: {{ commentStore.Comment.name }}</span>
            <span> , 조회 수: {{ commentStore.Comment.view_count }} </span>
            <span> , 내용: {{ commentStore.Comment.content }} </span>
            <!-- <RouterLink :to="{ name: 'MountainDetailPage', params: { mountainSerial: '1' } }">1</RouterLink> -->
            <button v-if="commentStore.Comment.userSerial == userSerial">
                <RouterLink
                    :to="{ name: 'CommentModifyPage', params: { commentSerial: '`${route.params.commentSerial}`' } }">
                    modifypage</RouterLink>
            </button>
        </ul>

        <li v-for="reply in replyStore.ReplyList" :key="reply.serial">
            <span> userserial: {{ reply.user_serial }}</span>
            <span> , replycontent: {{ reply.content }}</span>
            <!-- <RouterLink :to="{ name: 'MountainDetailPage', params: { mountainSerial: '1' } }">1</RouterLink> -->
        </li>
        <button v-if="token !== null" @click="showReplyForm = true">댓글 작성하기</button>

        <div v-if="showReplyForm">
            <textarea v-model="reply.content"></textarea>
            <button @click="postReply">댓글 작성</button>
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

const userName = ref(''); // 사용자 이름
const userSerial = ref(''); // 사용자 serial
const token = sessionStorage.getItem('access-token');
const showReplyForm = ref(false);
const reply = ref({
    userSerial: "",
    commentSerial: "",
    content: ""
})

// 사용자가 로그인했는지 확인하고 사용자 정보를 가져오는 함수
const checkUserSerial = () => {
    if (token !== null) {
        const payload = JSON.parse(atob(token.split('.')[1]));
        userName.value = payload.name;
        userSerial.value = payload.serial;
        userStore.getUserByid(userSerial.value);
        reply.value.userSerial = userSerial.value;
        reply.value.commentSerial = route.params.commentSerial;
    }
}

// 댓글과 답글을 불러오는 함수
onMounted(() => {
    commentStore.getComment(route.params.commentSerial);
    replyStore.getReplyList(route.params.commentSerial);
    checkUserSerial();
})

// 새로운 답글을 작성하는 함수
const postReply = function () {

    replyStore.createReply(reply.value)
        .then(() => {
            replyStore.getReplyList(route.params.commentSerial);
        });
    // 답글 작성 폼 숨기기
    showReplyForm.value = false;
}
</script>


<style scoped></style>