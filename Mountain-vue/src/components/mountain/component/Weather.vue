<template>
  <div class="weather-app">
    <div class="weather-header"></div>
    <div class="weather-body">
      <div class="weather-icon">
        <img src="@/assets/weather/rain.gif" alt="">
      </div>
      <div class="weather-info">
        <span><h4>날씨정보</h4></span>
        <br>
        <hr>
        <br>
        <p>산 이름: {{ mountainStore.mountain.name }}</p>
        <p>기온 : {{ tmp }}℃</p>
        <p>최고 기온 : {{ tmax }}℃</p>
        <p>최저 기온 : {{ tmin }}℃</p>
        <!-- <p>하늘상태 : {{ sky }}</p> -->
        <p>하늘상태 : 비 </p>
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
const weatherIcon = ref(null);

const getWeatherIcon = (sky) => {
  console.log(sky);
  switch (sky) {
    case "1":
      weatherIcon.value = '@/assets/images/daram.jpg';
      break;
    case "3":
      weatherIcon.value = "@/assets/images/daram.jpg";
      break;
    case "4":
      weatherIcon.value = '/assets/weather/rain.gif';
      break;
    default:
      weatherIcon.value = '/assets/weather/rain.gif';
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
      console.log(data); // 응답 데이터를 콘솔에 출력하여 확인
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
      console.log(todayForecast); // 오늘의 예보 데이터를 콘솔에 출력하여 확인
      tmp.value = todayForecast.tmp;
      tmax.value = todayForecast.tmax;
      tmin.value = todayForecast.tmin;
      sky.value = todayForecast.sky;

      getWeatherIcon(sky.value);

      const forecastArray = [];
      for (const date in dailyForecast) {
        forecastArray.push({
          date,
          tmp: dailyForecast[date].tmp,
          sky: dailyForecast[date].sky,
        });
      }
      forecast.value = forecastArray.slice(0, 5); // 5일 예보
    })
    .catch((error) => {
      console.error("Error fetching weather data:", error);
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

.weather-body {
  display: flex;
  align-items: center;
  justify-content: center;
}

.weather-info {
  margin-left: 120px;
  font-size: larger;
  text-align: left;
  padding: 10px;
}

.weather-icon img {
  width: 100px;
  height: 100px;
  margin-bottom: 70px;
  margin-top: 80px;
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

span {
  display: inline-block;
  box-shadow: inset 0 -13px 0 #bfffa1;
}
</style>
