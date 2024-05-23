<template>
  <div class="starrate container">
    <h3>평점</h3>
    <br>
    <div class="star-container container">
      <div class="number-container">
        <div class="align-middle">
          <h2>{{ averageStar }} 점</h2>
        </div>
      </div>
      <div>
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

const averageStar = computed(() => {
  const starList = commentStore.MountainStarList || {};
  const totalStars = Object.values(starList).reduce((sum, count) => sum + count, 0);
  const sumOfStars = Object.entries(starList).reduce((sum, [star, count]) => sum + star * count, 0);

  return totalStars === 0 ? 0 : (sumOfStars / totalStars).toFixed(1);
});

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

.star-container {
  display: flex;
  align-items: center;
}

.number-container {
  display: flex;
  align-items: center;
  height: 100%;
  text-align: center;
}

.align-middle {
  width: 100px;
  display: flex;
  align-items: center;
  height: 100%;
}

.star-bar {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  width: 100%;
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
  width: 400px;
  /* border-radius: 5%; */
}

.bar {
  background-color: #f3ef6c;
  height: 100%;
}
</style>
