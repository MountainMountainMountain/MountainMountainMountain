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

    </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref, onMounted } from "vue";
import { useCommentStore } from "@/stores/commentstore";
import axios from "axios";
const route = useRoute();
const router = useRouter();
const commentStore = useCommentStore();

onMounted(() => {
    commentStore.getComment(route.params.commentSerial)
})

</script>

<style scoped></style>