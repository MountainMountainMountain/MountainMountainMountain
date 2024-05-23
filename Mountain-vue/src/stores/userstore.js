import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_USER_API = `http://localhost:8080/api-user`

export const useUserStore = defineStore('user', () => {

  const User = ref({})
  const loginUserId = ref(null);
  const loginUserName = ref(null);
  const loginPassword = ref(null);
  const UserList = ref([])
  const FollowingList = ref([])
  const FollowerList = ref([])

  const getUser = function () {
    axios.get(`${REST_USER_API}/user`)
      .then((response) => {
        UserList.value = response.data
      })
  }

  const getUserByName = function (name, serial) {
    axios.get(`${REST_USER_API}/user/search/name/${name}/${serial}`)
      .then((response) => {
        UserList.value = response.data
      })
      .catch((error) => {
        console.log(name)
        console.log(serial)
        console.log(error)
      })
  }

  const getUserByid = function (id) {
    console.log(id)
    return new Promise((resolve, reject) => {
      axios.get(`${REST_USER_API}/user/search/id/${id}`)
        .then((response) => {
          User.value = response.data
          console.log(User.value)
          resolve();
        })
        .catch(() => {
          reject();
        })
    });
  }

  const checkId = function (id) {
    axios.get(`${REST_USER_API}/user/join/check/id/${id}`)
      .then((response) => {
        if (response.status === 200) {
          alert("사용 가능한 아이디입니다.");
        }
      })
      .catch((error) => {
        if (error.response.status === 409)
          alert("이미 사용 중인 아이디입니다.");
      });
  }

  const checkEmail = function (email) {
    axios.get(`${REST_USER_API}/user/join/check/email/${email}`)
      .then((response) => {
      })
  }

  const userLogin = function (id, password) {
    return new Promise((resolve, reject) => {
      axios.post(`${REST_USER_API}/user/login`, {
        id,
        password
      })
        .then((res) => {
          sessionStorage.setItem('access-token', res.data["access-token"]);

          const token = res.data['access-token'].split('.');


          const tokenPayload = JSON.parse(decodeURIComponent(escape(atob(token[1]))));
          let id = tokenPayload['id'];
          let name = tokenPayload['name'];
          let password = tokenPayload['id'];

          loginUserName.value = name;
          loginUserId.value = id;
          loginPassword.value = password;
          console.log(loginUserName.value)

          resolve(); // 성공 시 resolve 호출
        })
        .catch((error) => {
          console.error(error);
          reject(error); // 실패 시 reject 호출
        });
    });
  }


  const logoutUser = function () {
    sessionStorage.removeItem('access-token');
    router.push({ name: 'MountainMainView' });
  }



  const createUser = function (user) {
    return new Promise((resolve, reject) => {
      axios({
        url: `${REST_USER_API}/user/join`,
        method: 'POST',
        data: user
      })
        .then(() => {
          resolve();
        })
        .catch((err) => {
          reject();
        })
    });
  }

  const updateUser = function (user) {
    return new Promise((resolve, reject) => {
      axios.put(`${REST_USER_API}/user/${user.serial}`, user)
        .then(() => {
          User.value = user
          resolve();
        })
        .catch((error) => {
          reject(error);
        })
    });
  }

  const updateUserPoint = function (userSerial, point) {
    return new Promise((resolve, reject) => {
      axios.get(`${REST_USER_API}/user/point/${userSerial}/${point}`)
        .then(() => {
          resolve();
        })
        .catch((error) => {
          reject(error);
        })
    });
  }



  // Follow
  const createFollow = function (fromFollow, toFollow) {
    return new Promise((resolve, reject) => {
      axios.get(`${REST_USER_API}/follow/${fromFollow}/${toFollow}`)
        .then((response) => {
          resolve()
        })
        .catch((err) => {
          console.log(err)
          reject()
        })
    });
  }

  const getfollwingList = function (toFollow) {
    axios.get(`${REST_USER_API}/follow/followingList/${toFollow}`)
      .then((response) => {
        FollowingList.value = response.data

      })
      .catch((err) => {
        console.log(err)
      })
  }

  const getfollwerList = function (fromFollow) {
    axios.get(`${REST_USER_API}/follow/followerList/${fromFollow}`)
      .then((response) => {
        FollowerList.value = response.data
      })
      .catch((err) => {
        console.log(err)
      })
  }

  return { User, UserList, FollowingList, FollowerList, getUser, getUserByName, getUserByid, checkId, checkEmail, userLogin, logoutUser, createUser, updateUser, createFollow, getfollwingList, getfollwerList, updateUserPoint, loginUserId, loginUserName, loginPassword }
})
