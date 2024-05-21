<template>
    <div>
        <h1>commentlist</h1>


        <div class="search">
            <div>
                <label>검색 기준 :</label>
                <select v-model="searchInfo.key">
                    <option value=''>없음</option>
                    <option value="name">작성자</option>
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                </select>
            </div>
            <div>
                <label>검색 내용 :</label>
                <input type="text" v-model="searchInfo.word" />
            </div>
            <div>
                <label>정렬 기준 :</label>
                <select v-model="searchInfo.orderBy">
                    <option value=''>없음</option>
                    <option value="name">글쓰니</option>
                    <option value="title">제목</option>
                    <option value="viewCount">조회수</option>
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
                <button @click="searchCommentList">검색</button>
            </div>
        </div>


        <ul>
            <li v-for="comment in commentStore.CommentList" :key="comment.serial">
                <router-link :to="{ name: 'CommentDetailPage', params: { commentSerial: `${comment.serial}` } }">
                    <span> 제목: {{ comment.title }}</span>
                    <span> , 작성자: {{ comment.name }}</span>
                    <span> , 조회 수: {{ comment.viewCount }} </span>
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

const searchInfo = ref({
    key: '',
    word: '',
    orderBy: '',
    orderByDir: 'asc'
})

const searchCommentList = function () {
    commentStore.searchCommentList(route.params.mountainSerial, searchInfo.value)
}

onMounted(() => {
    commentStore.getCommentList(route.params.mountainSerial);
})
</script>

<style scoped>
.search {
    display: flex;
}
</style>