<template>
  <div class="mapbox">
    <div id="map"></div>
    <button @click="initMap" class="btn btn-primary btn-sm">산 위치 바로가기</button>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { onMounted, ref, toRaw } from 'vue';
import { useMountainStore } from "@/stores/mountainstore";

const route = useRoute();
const mountainStore = useMountainStore();

let map = null;

const initMap = function () {
  let myCenter = new kakao.maps.LatLng(`${mountainStore.mountain.latitude}`, `${mountainStore.mountain.longitude}`);
  
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      new kakao.maps.Marker({
        map,
        position: myCenter,
      });
      map.setCenter(myCenter);
    });
  }
  
  const container = document.getElementById('map');
  const options = {
    center: myCenter,
    level: 5,
  };
  
  map = new kakao.maps.Map(container, options);
  
  // 지도 확대/축소 컨트롤 추가
  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
  
  // 지도 유형 컨트롤 추가
  const mapTypeControl = new kakao.maps.MapTypeControl();
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
};

onMounted(() => {
  mountainStore.getMountainSerial(route.params.mountainSerial)
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement('script');
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_API_KEY}`;
    script.addEventListener('load', () => {
      kakao.maps.load(initMap);
    });
    document.head.appendChild(script);
  }
});

const myMarkerPosition = ref([[37.365146, 128.055632]]);
const markers = ref([]);

const displayMarker = function (markerPositions) {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
  
  const positions = markerPositions.map(
    (position) => new kakao.maps.LatLng(...position)
  );
  
  if (positions.length > 0) {
    markers.value = positions.map(
      (position) =>
        new kakao.maps.Marker({
          map: toRaw(map),
          position,
        })
    );
    
    const bounds = positions.reduce(
      (bounds, latlng) => bounds.extend(latlng),
      new kakao.maps.LatLngBounds()
    );
    
    toRaw(map).setBounds(bounds);
  }
};
</script>

<style scoped>
#map {
  width: 60%;
  height: calc(80vw * 0.6 / 10 * 7); 
  margin: 0 auto;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}
.mapbox {
  text-align: center;
  padding: 30px;
  background-color: rgb(246, 246, 246);
  border: none;
  width: 100%;
  height: auto
}
</style>
