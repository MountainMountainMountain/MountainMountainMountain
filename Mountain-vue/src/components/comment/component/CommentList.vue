<template>
    <div>
        <div class="table-responsive">
            <div class="" style="display: flex; justify-content: space-between; height: 50px;">
                <div class="">
                    <h3 class="card-title">{{ mountainStore.mountain.name }} Review</h3>
                </div>
                <div class="">
                    <button class="button btnPush btnBlueGreen" v-if="token != null" style="width: 100px; ">
                        <RouterLink class="no-underline" :to="{ name: 'CommentCreatePage', params: { mountainSerial: route.params.mountainSerial } }" style="color: white;">글 작성
                        </RouterLink>
                    </button>
                </div>
            </div>
            <hr>
            <br>
            <div class="search">
                <div class="search-bar">
                    <select v-model="searchInfo.key" style="height: 30px;">
                        <option value=''>검색기준</option>
                        <option value="name">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </div>
                <input class="form-control" type="text" v-model="searchInfo.word" style="width: 250px;"
                    placeholder="검색 내용을 입력하세요" />
                <button class="btn btn-primary" @click="searchCommentList"><i class="fas fa-search"
                        style="color: white"></i></button>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">제목</th>
                        <th scope="col">별점</th>
                        <th scope="col">작성자</th>
                        <th scope="col">조회 수</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(comment, index) in commentStore.CommentList" :key="comment.serial">
                        <router-link class="no-underline"
                            :to="{ name: 'CommentDetailPage', params: { commentSerial: `${comment.serial}` } }">
                            <th scope="row">{{ index + 1 }}</th>
                        </router-link>
                        <td>
                            <router-link class="no-underline"
                                :to="{ name: 'CommentDetailPage', params: { commentSerial: `${comment.serial}` } }">
                                {{ comment.title }}
                            </router-link>
                        </td>
                        <td>{{ comment.star }}</td>
                        <td><router-link class="no-underline"
                                :to="{ name: 'CommentDetailPage', params: { commentSerial: `${comment.serial}` } }">{{
                                    comment.name }}</router-link></td>
                        <td><router-link class="no-underline"
                                :to="{ name: 'CommentDetailPage', params: { commentSerial: `${comment.serial}` } }">{{
                                    comment.viewCount }}</router-link></td>
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
const token = sessionStorage.getItem('access-token');

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
div.button {
    display: block;
    position: relative;
    float: left;
    width: 120px;
    padding: 0;
    margin: 10px 20px 10px 0;
    font-weight: 600;
    text-align: center;
    line-height: 50px;
    color: #FFF;
    border-radius: 5px;
    transition: all 0.2s;
}

.btnBlueGreen {
    background: #00AE68;
}

.btnBlueGreen.btnPush {
    box-shadow: 0px 5px 0px 0px #007144;
    border-radius: 4px;
}

.btnPush:hover {
    margin-top: 15px;
    margin-bottom: 5px;
}

.btnBlueGreen.btnPush:hover {
    box-shadow: 0px 0px 0px 0px #007144;
}

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
    justify-content: center;
    margin-bottom: 20px;
}

.search-bar {
    display: flex;
    gap: 10px;
    align-items: center;
    width: 100px;
}

.card-title {
    font-family: "Nanum Myeongjo", serif;
    font-weight: bold;
}

.no-underline {
    text-decoration: none;
    color: inherit;
}
</style>
