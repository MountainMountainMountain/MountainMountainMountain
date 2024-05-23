<template>
  <div class="bookbox">
    <div class="weather-app">
      <div class="weather-header">
      </div>
      
      <div class="weather-body">
        <div class="weather-info">
          <h1 style="text-align: center;">날씨 앱</h1>
          <h4>날씨정보</h4>
          <p>산 이름: {{ mountainStore.mountain.name }}</p>
          <p>기온 : {{ tmp }}℃</p>
          <p>최고 기온 : {{ tmax }}℃</p>
          <p>최저 기온 : {{ tmin }}℃</p>
          <p>하늘상태 : {{ sky }}</p>
        </div>
        <div class="weather-icon">
          <img :src="getWeatherIcon(sky)" alt="날씨아이콘">
        </div>
      </div>

      <div class="forecast">
        <h4>5일 예보</h4>
        <div class="forecast-day" v-for="(day, index) in forecast" :key="index">
          <p>{{ day.date }}</p>
          <img :src="getWeatherIcon(day.sky)" alt="날씨아이콘">
          <p>{{ day.tmp }}℃</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { onMounted, ref } from "vue";
import axios from "axios";
import { useMountainStore } from "@/stores/mountainstore";

const route = useRoute();
const mountainStore = useMountainStore();

const mountainName = ref('');
const tmp = ref(null);
const tmax = ref(null);
const tmin = ref(null);
const sky = ref(null);
const forecast = ref([]);

const getWeatherIcon = (sky) => {
  switch (sky) {
    case "1":
      return '/assets/images/daram.jpg';
    case "3":
      return "/assets/images/daram.jpg" ;
    case "4":
      return '/assets/weather/rain.gif';
    default:
      return '/assets/weather/rain.gif';
  }
};

onMounted(() => {
  mountainStore.getMountainSerial(route.params.mountainSerial);
  
  const API_URL = `http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst`;
  const today = new Date();
  let year = today.getFullYear();
  let month = today.getMonth() + 1;
  let day = today.getDate();
  month = month < 10 ? "0" + month : month;
  day = day < 10 ? "0" + day : day;
  const todayStr = `${year}${month}${day}`;
  const nx = 78; // 예시로 설정된 값
  const ny = 124; // 예시로 설정된 값

  axios
    .get(API_URL, {
      params: {
        ServiceKey: import.meta.env.VITE_WEATHER_API_KEY,
        dataType: "JSON",
        base_date: todayStr,
        base_time: "0500",
        numOfRows: 50,
        nx: nx,
        ny: ny,
      },
    })
    .then((response) => {
      return response.data.response.body.items.item;
    })
    .then((data) => {
      const dailyForecast = {};
      data.forEach((item) => {
        const date = item.fcstDate;
        if (!dailyForecast[date]) {
          dailyForecast[date] = {};
        }
        if (item.category === "TMP") {
          dailyForecast[date].tmp = item.fcstValue;
        } else if (item.category === "TMX") {
          dailyForecast[date].tmax = item.fcstValue;
        } else if (item.category === "TMN") {
          dailyForecast[date].tmin = item.fcstValue;
        } else if (item.category === "SKY") {
          dailyForecast[date].sky = item.fcstValue;
        }
      });

      const todayForecast = dailyForecast[todayStr];
      tmp.value = todayForecast.tmp;
      tmax.value = todayForecast.tmax;
      tmin.value = todayForecast.tmin;
      sky.value = todayForecast.sky;

      const forecastArray = [];
      for (const date in dailyForecast) {
        forecastArray.push({
          date,
          tmp: dailyForecast[date].tmp,
          sky: dailyForecast[date].sky,
        });
      }
      forecast.value = forecastArray.slice(0, 5); // 5일 예보
    });
});
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Gowun Dodum", sans-serif;
}

body {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background: rgb(246, 246, 246);
}

.bookbox {
  display: flex;
  align-items: flex-start;
  justify-content: center;
  background: rgb(246, 246, 246);
  height: auto;
  padding: 20px;
}

.weather-app {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  padding: 20px;
  text-align: center;
  width: 500px;
}

.weather-header h1 {
  font-size: 24px;
  margin: 0;
}

.weather-body {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 20px;
}

.weather-info {
  text-align: left;
  padding: 10px;
}

.weather-icon img {
  width: 100px;
  height: 100px;
}

.forecast {
  margin-top: 20px;
}

.forecast-day {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
}

.forecast-day p {
  margin: 0 10px;
}
</style>
