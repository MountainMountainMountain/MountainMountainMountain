<template>
  <div class="container">
    <div class="input-group mb-3">
      <span class="input-group-text"></span>
      <input type="text" class="form-control" placeholder="산을 입력하세요" />
      <button type="button" class="btn btn-primary"><i class="fas fa-search" style="color: blue"></i></button>
      <button class="btn btn-primary"><i class="fas fa-search" style="color: red"></i></button>
    </div>
    <div class="search">
      <div>
        <label>검색 기준 :</label>
        <select v-model="searchInfo.key">
          <option value=''>없음</option>
          <option value="name">산 이름</option>
          <option value="state">위치</option>
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
          <option value="name">산 이름</option>
          <option value="state">위치</option>
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
        <button @click="searchMountainList">검색</button>
      </div>
    </div>
  </div>

  <ul>
    <li v-for="ms in mountainstore.mountainList" :key="ms.serial">
      <router-link :to="`/mountain/${ms.serial}/MountainInfo`">
        <span> name: {{ ms.name }}</span>
        <span> , altitude: {{ ms.altitude }}</span>
        <span> , course: {{ ms.course }} </span>
        <span> , point: {{ ms.point }}</span>
        <!-- <RouterLink :to="{ name: 'MountainDetailPage', params: { mountainSerial: '1' } }">1</RouterLink> -->
      </router-link>
    </li>
  </ul>
</template>

<script setup>
import { useMountainStore } from '@/stores/mountainstore';
import { ref, onMounted, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
const route = useRoute();

const searchInfo = ref({
  key: '',
  word: '',
  orderBy: '',
  orderByDir: 'asc',
  state: route.params.state,
})

const mountainstore = useMountainStore()

const searchMountainList = function () {
  mountainstore.searchMountainList(searchInfo.value)
  // .then(() => {
  // })
  // .catch(() => {
  //   mountainstore.getMountainList()
  // });
}
const fetchMountains = () => {
  if (route.params.state == "전체") {
    mountainstore.getMountainList();
    console.log(route.params.state)
  }
  else {
    console.log(route.params.state)
    mountainstore.getMountainState(route.params.state);
  }
};

onMounted(() => {
  mountainstore.getMountainList()
})

watch(() => route.params.state, (newState) => {
  fetchMountains();
});
</script>

<style scoped>
.search {
  display: flex;
}
</style>