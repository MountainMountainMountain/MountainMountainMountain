<template>
    <div class="weather-app">
      <div class="weather-header">
        <h1>{{ mountainName }}의 날씨</h1>
      </div>
  
      <div class="weather-body">
        <div class="weather-info">
          <h4>날씨정보</h4>
          <p>산 이름: {{ mountainName }}</p>
          <p>기온 : {{ tmp }}℃</p>
          <p>하늘상태 : {{ sky }}</p>
          <p>강수형태 : {{ pty }}</p>
          <p>강수확률 : {{ pop }}%</p>
        </div>
        <div class="weather-icon">
          <img id="weather-icon" src="" alt="날씨아이콘">
          <i class="wu wu-black wu-32 wu-chanceflurries"></i>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { useRoute } from "vue-router";
  import { onMounted, ref } from "vue";
  import axios from "axios";
  const route = useRoute();
  const mountainName = ref('');
  const tmp = ref(null);
  const sky = ref(null);
  const pty = ref(null);
  const pop = ref(0);
  import { useMountainStore } from "@/stores/mountainstore";
  const mountainStore = useMountainStore();
  onMounted(() => {
    mountainStore.getMountainSerial(route.params.mountainSerial)
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
          base_date: todayStr, //20231105 형태
          base_time: "0200",   //이것은 총 8회 발표 
          numOfRows: 15,
          nx: nx,
          ny: ny,
        },
      })
      .then((response) => {
        return response.data.response.body.items.item;
      })
      .then((response) => {
        response.forEach((item) => {
          if (item.category === "TMP") {
            tmp.value = item.fcstValue;
          } else if (item.category === "SKY") {
            switch (item.fcstValue) {
              case "1":
                sky.value = "맑음";
                break;
              case "3":
                sky.value = "구름많음";
                break;
              case "4":
                sky.value = "흐림";
                break;
            }
          } else if (item.category === "PTY") {
            pty.value = item.fcstValue;
          } else if (item.category === "POP") {
            pop.value = item.fcstValue;
          }
        });
      });
  });
  </script>
  
  <style scoped>
  .weather-app {
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    padding: 20px;
    text-align: center;
    margin-top: 20px;
  }
  
  .weather-header h1 {
    font-size: 24px;
    margin: 0;
  }
  
  .weather-body {
    display: flex;
    align-items: center;
    justify-content: center;
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
  </style>
  