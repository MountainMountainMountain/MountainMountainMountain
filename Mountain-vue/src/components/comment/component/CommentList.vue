<template>
    <div>
        <div class="table-responsive">
            <h3>{{ mountainStore.mountain.name }} Review</h3>
            <hr>
            <br>
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
                    <label>정렬 기준 :</label>
                    <select v-model="searchInfo.orderBy">
                        <option value=''>없음</option>
                        <option value="name">글쓰니</option>
                        <option value="title">제목</option>
                        <option value="viewCount">조회수</option>
                    </select>
                </div>
                <div>
                    <label>검색 내용 :</label>
                    <input type="text" v-model="searchInfo.word" />
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
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">제목</th>
                        <th scope="col">작성자</th>
                        <th scope="col">조회 수</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(comment, index) in commentStore.CommentList" :key="comment.serial">
                        <router-link
                            :to="{ name: 'CommentDetailPage', params: { commentSerial: `${comment.serial}` } }">
                            <th scope="row">{{ index + 1 }}</th>
                        </router-link>
                        <td>
                            <router-link
                                :to="{ name: 'CommentDetailPage', params: { commentSerial: `${comment.serial}` } }">
                                {{ comment.title }}
                            </router-link>
                        </td>
                        <td>{{ comment.name }}</td>
                        <td>{{ comment.viewCount }}</td>
                    </tr>
                </tbody>
            </table>

        </div>
    </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { onMounted, ref } from "vue";
import { useCommentStore } from "@/stores/commentstore";
import { useMountainStore } from "@/stores/mountainstore";

const route = useRoute();
const commentStore = useCommentStore();
const mountainStore = useMountainStore();
const mountainSerial = route.params.mountainSerial;

const searchInfo = ref({
    key: '',
    word: '',
    orderBy: '',
    orderByDir: 'asc'
});

const searchCommentList = () => {
    commentStore.searchCommentList(mountainSerial, searchInfo.value);
};

onMounted(() => {
    commentStore.getCommentList(mountainSerial);
    mountainStore.getMountainSerial(mountainSerial);
});
</script>

<style scoped>
.table-responsive {
    margin: 20px;
}

.table {
    width: 100%;
    max-width: 100%;
    margin-bottom: 1rem;
    background-color: transparent;
}

.table th,
.table td {
    padding: 0.75rem;
    vertical-align: top;
    border-top: 1px solid #dee2e6;
}

.table thead th {
    vertical-align: bottom;
    border-bottom: 2px solid #dee2e6;
}

.table tbody+tbody {
    border-top: 2px solid #dee2e6;
}

.table-striped tbody tr:nth-of-type(odd) {
    background-color: rgba(0, 0, 0, 0.05);
}

.search {
    display: flex;
    flex-wrap: wrap;
    gap: 1rem;
    margin-bottom: 1rem;
    align-content: center;
}

.search div {
    flex: 1;
    min-width: 150px;
}

.search label {
    display: block;
    margin-bottom: 0.5rem;
}

.search input,
.search select {
    width: 100%;
    padding: 0.5rem;
    border: 1px solid #dee2e6;
    border-radius: 4px;
}

.search button {
    padding: 0.5rem 1rem;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.search button:hover {
    background-color: #0056b3;
}
</style>
