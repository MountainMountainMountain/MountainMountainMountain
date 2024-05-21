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
import { ref, onMounted } from "vue";
import { useCommentStore } from "@/stores/commentstore";
import { useReplyStore } from "@/stores/replystore";
import axios from "axios";
const route = useRoute();
const router = useRouter();
const commentStore = useCommentStore();
const replyStore = useReplyStore();



onMounted(() => {
    commentStore.getComment(route.params.commentSerial);
    replyStore.getReplyList(route.params.commentSerial);
})

</script>

<style scoped></style>