import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const REST_MOUNTAIN_API = 'http://localhost:8080/api-mountain/';

export const useMountainStore = defineStore('mountain', () => {

  const mountainList = ref([]);
  const mountain = ref({});
  const completeMountain = ref([]);

  const createMountain = function (mountain) {
    axios({
      url: REST_MOUNTAIN_API,
      method: 'POST',
      data: mountain
    })
      .then(() => {
        router.push({ name: 'mountainList' });
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const getMountainList = function () {
    axios.get(REST_MOUNTAIN_API)
      .then((response) => {
        mountainList.value = response.data;
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const searchMountainList = function (searchCondition) {
    axios.get(`${REST_MOUNTAIN_API}mountain`, { params: searchCondition })
      .then((res) => {
        mountainList.value = res.data;
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const getMountainSerial = function (mountainSerial) {
    axios.get(`${REST_MOUNTAIN_API}${mountainSerial}`)
      .then((response) => {
        mountain.value = response.data;
      })
      .catch((err) => {
        console.error('Error fetching mountain data', err);
      });
  };

  const getMountainState = function (mountainState) {
      axios.get(`${REST_MOUNTAIN_API}state/${mountainState}`)
        .then((response) => {
          mountainList.value = response.data;
        })
        .catch((err) => {
          console.error(err);
        });
    // return new Promise((resolve, reject) => {
    //   axios.get(`${REST_MOUNTAIN_API}state/${mountainState}`)
    //     .then((response) => {
    //       mountainList.value = response.data;
    //       resolve();
    //     })
    //     .catch((err) => {
    //       console.error(err);
    //       reject();
    //     });
    // });
  };

  const updateMountain = function () {
    axios.put(REST_MOUNTAIN_API, mountain.value)
      .then(() => {
        router.push({ name: "mountainList" });
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const deleteMountain = function (mountainSerial) {
    axios
      .delete(`${REST_MOUNTAIN_API}/${mountainSerial}`)
      .then(() => {
        router.push({ name: "mountainList" });
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const getCompleteMountain = function (userSerial) {
    axios.get(`${REST_MOUNTAIN_API}user/${userSerial}`)
      .then((response) => {
        completeMountain.value = response.data
      })
      .catch((err) => {
        console.error(err);
      });
  }

  return { createMountain, mountainList, mountain, getMountainList, searchMountainList, getMountainSerial, getMountainState, updateMountain, deleteMountain, completeMountain, getCompleteMountain };
});