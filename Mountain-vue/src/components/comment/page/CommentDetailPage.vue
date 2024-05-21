<template>
    <div>
        <h1>commentdetail</h1>
        <RouterLink :to="{ name: 'CommentModifyPage', params: { commentSerial: '`${route.params.commentSerial}`' } }">
            modifypage</RouterLink>

        <ul>
            <span> 제목: {{ commentStore.Comment.title }}</span>
            <span> , 작성자: {{ commentStore.Comment.name }}</span>
            <span> , 조회 수: {{ commentStore.Comment.view_count }} </span>
            <span> , 내용: {{ commentStore.Comment.content }} </span>
            <!-- <RouterLink :to="{ name: 'MountainDetailPage', params: { mountainSerial: '1' } }">1</RouterLink> -->
            <button v-if="commentStore.Comment.user_serial === userSerial">
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

// let id = JSON.parse(atob(token[1]))['id'];


const checkUserSerial = () => {
    const payload = JSON.parse(atob(token.split('.')[1]));
    userName.value = payload.name;
    userSerial.value = payload.serial;
    userStore.getUserByid(userSerial.value);
}

onMounted(() => {
    commentStore.getComment(route.params.commentSerial);
    replyStore.getReplyList(route.params.commentSerial);
})

</script>

<style scoped></style>