<template>
    <div>
        <div class="mountainbox">
            <div v-if="mountainStore.mountain && Object.keys(mountainStore.mountain).length > 0"
                class="d-flex justify-content-center">
                <div class="card" style="width: 80%; border-color: white;">
                    <div class="mountainbody">
                        <h4 class="card-title"><span>{{ mountainStore.mountain.name }}</span></h4>
                        <div class="mountaintable">
                            <table class="table text-center">
                                <tbody>
                                    <tr>
                                        <th scope="row" class="text-right">위치</th>
                                        <td class="text-left">{{ mountainStore.mountain.state }} {{ mountainStore.mountain.town }}</td>
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
            <RouterLink :to="{ name: 'CommentCreatePage' }">CommentCreatePage</RouterLink>
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
</script>

<style scoped>
.mountainbox {
    display: flex;
    flex-direction: column;
    background-color: rgb(246, 246, 246);
    border: none;
    text-align: center;
    padding: 30px;
}
.card-title{
    font-family: "Nanum Myeongjo", serif;
    font-weight: bold;
}

.mountainbody {
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Add shadow to mountainbody */
    padding: 20px;
    background-color: white;
    border-radius: 10px;
}

.mountaintable {
    margin-top: 30px;
    border: 1px solid white;
}

.mountaintable table {
    margin: 0 auto;
}

.mountaintable th,
.mountaintable td {
    position: relative;
    padding: 10px 20px;
}

.mountaintable th::after,
.mountaintable td::after {
    content: "";
    position: absolute;
    height: 1px;
    background: black;
    bottom: 0;
}

.mountaintable th::after {
    left: 0;
}

.mountaintable td::after {
    right: 0;
}
span{
    box-shadow: inset 0 -13px 0 #bfffa1;
}
</style>
