<template>
  <div class="container">
    <div class="withimg">
      <img src="@/assets/images/redmountain.jpg" alt="" />
    </div>
    <div class="search">
      <div class="search-bar">
        <!-- <label>검색 기준 :</label> -->
        <select v-model="searchInfo.key" style="height: 30px;">
          <option value=''>검색기준</option>
          <option value="name">산 이름</option>
          <option value="state">위치</option>
        </select>
        <input type="text" class="form-control" v-model="searchInfo.word" placeholder="산을 입력하세요" />
        <button class="btn btn-primary" @click="searchMountainList">
          <i class="fas fa-search" style="color: white"></i>
        </button>
      </div>
    </div>

    <table>
      <thead>
        <tr>
          <th>이름</th>
          <th>고도</th>
          <th>코스</th>
          <th>포인트</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="ms in mountainstore.mountainList" :key="ms.serial">
          <td>
            <router-link :to="`/mountain/${ms.serial}/MountainInfo`">
              {{ ms.name }}
            </router-link>
          </td>
          <td>{{ ms.altitude }}</td>
          <td>{{ ms.course }}</td>
          <td>{{ ms.point }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { useMountainStore } from '@/stores/mountainstore';
import { ref, onMounted, watch } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const searchInfo = ref({
  key: '',
  word: '',
  orderBy: '',
  orderByDir: 'asc',
  state: route.params.state,
});

const mountainstore = useMountainStore();

const searchMountainList = function () {
  searchInfo.value.state = `${route.params.state}`;
  mountainstore.searchMountainList(searchInfo.value);
};

const fetchMountains = () => {
  console.log(route.params.state);
  mountainstore.getMountainState(route.params.state);
};

onMounted(() => {
  mountainstore.getMountainList();
});

watch(() => route.params.state, (newState) => {
  fetchMountains();
});
</script>

<style scoped>
.container {
  width: 100%;
  padding: 20px;
}

.withimg {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.withimg img {
  width: 80%;
  max-width: 800px;
  margin-top: 10px;
  margin-bottom: 10px;
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
  width: 700px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

thead {
  background-color: #f4f4f4;
}

th, td {
  padding: 10px;
  border: 1px solid #ddd;
  text-align: left;
}

th {
  background-color: #f8f9fa;
}

tr:nth-child(even) {
  background-color: #f9f9f9;
}

tr:hover {
  background-color: #f1f1f1;
}

a {
  color: #007bff;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}
</style>