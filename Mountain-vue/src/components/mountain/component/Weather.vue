<template>
  <div class="weather-app">
    <div class="weather-header"></div>
    <div class="weather-body">
      <div class="weather-icon">
        <img :src="weatherIcon" alt="Weather Icon">
      </div>
      <div class="weather-info">
        <span>
          <h4>날씨정보</h4>
        </span>
        <br>
        <hr>
        <br>
        <p>{{ mountainStore.mountain.name }}</p>
        <p>{{ tmp }}℃</p>
        <p>{{ skyDescription }}</p>
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

const tmp = ref(null);
const weatherIcon = ref(null);
const skyDescription = ref('');

// 하늘 상태에 따라 아이콘과 설명을 설정하는 함수
import sunnyImage from '@/assets/weather/sunny.gif';
import rainyImage from '@/assets/weather/rain.gif';
import cloudyImage from '@/assets/weather/cloud.gif';

const getWeatherIcon = (sky) => {
  switch (sky) {
    case "1":
      weatherIcon.value = sunnyImage;
      skyDescription.value = '맑음';
      break;
    case "3":
      weatherIcon.value = cloudyImage;
      skyDescription.value = '구름 많음';
      break;
    case "4":
      weatherIcon.value = rainyImage;
      skyDescription.value = '비';
      break;
    default:
      weatherIcon.value = unknownImage;
      skyDescription.value = '알 수 없음';
  }
};

onMounted(async () => {
  try {
    await mountainStore.getMountainSerial(route.params.mountainSerial);

    const nx = mountainStore.mountain.weatherNX;
    const ny = mountainStore.mountain.weatherNY;
    const API_URL = `http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst`;
    const today = new Date();
    let year = today.getFullYear();
    let month = today.getMonth() + 1;
    let day = today.getDate();
    month = month < 10 ? "0" + month : month;
    day = day < 10 ? "0" + day : day;
    const todayStr = `${year}${month}${day}`;

    const currentHour = today.getHours();
    let baseTime;

    // 현재 시간을 기준으로 base_time 설정
    if (currentHour >= 2 && currentHour < 5) {
      baseTime = "0200";
    } else if (currentHour >= 5 && currentHour < 8) {
      baseTime = "0500";
    } else if (currentHour >= 8 && currentHour < 11) {
      baseTime = "0800";
    } else if (currentHour >= 11 && currentHour < 14) {
      baseTime = "1100";
    } else if (currentHour >= 14 && currentHour < 17) {
      baseTime = "1400";
    } else if (currentHour >= 17 && currentHour < 20) {
      baseTime = "1700";
    } else if (currentHour >= 20 && currentHour < 23) {
      baseTime = "2000";
    } else {
      baseTime = "2300";
    }

    const response = await axios.get(API_URL, {
      params: {
        serviceKey: import.meta.env.VITE_WEATHER_API_KEY,
        dataType: "JSON",
        base_date: todayStr,
        base_time: baseTime,
        numOfRows: 12,
        nx: nx,
        ny: ny,
      },
    });

    const data = response.data.response.body.items.item;
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
    getWeatherIcon(todayForecast.sky);

  } catch (error) {
    console.error("Error fetching weather data:", error);
  }
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
