<template>
    <div>
        <!-- <h1>mountaininfo</h1> -->
        <div class="mountainbox">
            <div v-if="mountainStore.mountain && Object.keys(mountainStore.mountain).length > 0"
                class="d-flex justify-content-center">
                <div class="card" style="width: 80%; border-color: white;">
                    <div class="mountainbody">
                        <h4 class="card-title">{{ mountainStore.mountain.name }}</h4>
                        <div class="mountaintable">
                            <table class="table text-center">
                                <tbody>
                                    <tr>
                                        <th scope="row" class="text-right">위치</th>
                                        <td class="text-left">{{ mountainStore.mountain.state }} {{
                                            mountainStore.mountain.town }}</td>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="text-right">고도</th>
                                        <td class="text-left">{{ mountainStore.mountain.altitude }}m</td>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="text-right">코스</th>
                                        <td class="text-left">{{ mountainStore.mountain.course }}</td>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="text-right">난이도</th>
                                        <td class="text-left">{{ mountainStore.mountain.difficulty }}</td>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="text-right">입장료</th>
                                        <td class="text-left">{{ mountainStore.mountain.fee }}</td>
                                    </tr>
                                    <tr>
                                        <th scope="row" class="text-right">점수</th>
                                        <td class="text-left">{{ mountainStore.mountain.point }}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div v-else>
                Loading...
            </div>
        </div>
        <CommentStar />
        <CommentList />


        <button v-if="token != null">
            <RouterLink :to="{ name: 'CommentCreatePage', params: { mountainSerial: route.params.mountainSerial } }">
                CommentCreatePage</RouterLink>
        </button>

    </div>
</template>





<script setup>

import { onMounted } from 'vue';
import { useMountainStore } from '@/stores/mountainstore';
import { useRoute } from "vue-router";
import CommentStar from '@/components/comment/component/CommentStar.vue';
import CommentList from '@/components/comment/component/CommentList.vue';

const route = useRoute();
const mountainStore = useMountainStore();
const mountainSerial = route.params.mountainSerial;
const token = sessionStorage.getItem('access-token');

onMounted(() => {
    mountainStore.getMountainSerial(mountainSerial);
});

function moveUpdate() {
    // Implement the logic for updating the mountain
}

function deleteMountain() {
    mountainStore.deleteMountain(mountainSerial).then(() => {
        // Navigate to the list page or show a confirmation message
    });
}
</script>

<style scoped>
.mountainbox {
    text-align: center;
    padding: 30px;
    background-color: rgb(230, 229, 229);
    border: 1px solid black;
    width: 100%;
    height: auto;
}

.mountaintable {
    margin-top: 30px;
    border: 1px solid white;
}

.mountaintable table {
    margin: 0 auto;
    /* 테이블을 가운데 정렬 */
}

.mountaintable th,
.card-table td {
    padding: 10px 20px;
    /* 각 셀에 적당한 패딩 적용 */
}
</style>