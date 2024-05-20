<template>
    <div>
        <h1>commentlist</h1>
        <RouterLink :to="{ name: 'CommentDetailPage', params: { commentSerial: '1' } }">1</RouterLink>
        <ul>
            <li v-for="comment in commentStore.CommentList" :key="comment.serial">
                <router-link :to="{ name: 'CommentDetailPage', params: { commentSerial: `${comment.serial}` } }">
                    <span> 제목: {{ comment.title }}</span>
                    <span> , 작성자: {{ comment.name }}</span>
                    <span> , 조회 수: {{ comment.view_count }} </span>
                    <!-- <RouterLink :to="{ name: 'MountainDetailPage', params: { mountainSerial: '1' } }">1</RouterLink> -->
                </router-link>
            </li>
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
    commentStore.getCommentList(route.params.mountainSerial);
})


</script>

<style scoped></style>