<template>
    <div class="search">
        <div>
            <label>검색 기준 :</label>
            <select v-model="searchInfo.key">
                <option value=''>없음</option>
                <option value="writer">작성자</option>
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
                <option value="writer">글쓰니</option>
                <option value="title">제목</option>
                <option value="view_cnt">조회수</option>
            </select>
        </div>
        <div>
            <label>정렬 방향 :</label>
            <select v-model="searchInfo.orderByDir">
                <option value="asc">오름차순</option>
                <option value="desc">내림차순</option>
            </select>
        </div>
        <!-- <div> -->
            <button @click="searchBoardList">검색</button>
        <!-- </div> -->
    </div>
</template>

<script setup>
import { useCommentStore } from '@/stores/commentstore';
import { ref } from 'vue';
import { useRouter, useRoute } from 'vue-router';
const route = useRoute();

const searchInfo = ref({
    key: '',
    word: '',
    orderBy: '',
    orderByDir: 'asc'
})

const store = useCommentStore()

const searchBoardList = function () {
    store.searchBoardList(route.params.mountainSerial, searchInfo.value)
}
</script>

<style scoped>
.search {
    display: flex;
}
</style>