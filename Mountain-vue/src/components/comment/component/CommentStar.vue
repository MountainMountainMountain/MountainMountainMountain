<template>
    <div class="starrate">
      <h3>평점</h3>
      <br>
      <div v-if="isDataLoaded">
        <div v-for="(count, star) in starListWithPercentage" :key="star" class="star-bar">
          <span>{{ star }}점 :</span>
          <div class="bar-container">
            <div class="bar" :style="{ width: `${count}%` }"></div>
          </div>
          <span>{{ count }}%</span>
        </div>
      </div>
      <div v-else>
        Loading...
      </div>
    </div>
  </template>
  
  <script setup>
  import { useCommentStore } from '@/stores/commentstore';
  import { onMounted, computed, ref } from 'vue';
  import { useRoute } from 'vue-router';
  
  const route = useRoute();
  const commentStore = useCommentStore();
  const isDataLoaded = ref(false);
  
  onMounted(async () => {
    await commentStore.getCommentStar(route.params.mountainSerial);
    isDataLoaded.value = true;
  });
  
  const starListWithPercentage = computed(() => {
    const starList = commentStore.MountainStarList || {};
    const totalStars = Object.values(starList).reduce((sum, count) => sum + count, 0);
  
    return totalStars === 0
      ? {}
      : Object.fromEntries(
          Object.entries(starList).map(([star, count]) => [star, (count / totalStars * 100).toFixed(2)])
        );
  });
  </script>
  
  <style scoped>
  .starrate {
    padding: 50px;
    width: 700px;
  }
  .star-bar {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
  }
  
  .star-bar span {
    width: 50px;
    text-align: right;
    margin-right: 5px;
  }
  
  .bar-container {
    flex: 1;
    background-color: #f0f0f0;
    height: 20px;
    margin-right: 20px;
    /* border-radius: 5%; */
  }
  
  .bar {
    background-color: #f3ef6c;
    height: 100%;
  }

  </style>
  