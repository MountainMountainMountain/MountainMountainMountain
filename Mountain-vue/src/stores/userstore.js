import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_USER_API = `http://localhost:8080/api-user`

export const useUserStore = defineStore('user', () => {

  const User = ref({})
  const loginUserSerial = ref(null);
  const loginUserId = ref(null);
  const loginUserName = ref(null);
  const loginUserBirthDate = ref(null);
  const loginUserGender = ref(null);
  const loginUserEmail = ref(null);
  const loginUserRegDate = ref(null);
  const loginUserPoint = ref(null);
  const LoginUser = ref({})
  const UserList = ref([])
  const FollowingList = ref([])
  const FollowerList = ref([])
  const headers = ref([])

  const getUser = function () {
    axios.get(`${REST_USER_API}/user`)
      .then((response) => {
        UserList.value = response.data
      })
  }

  const getUserByName = function (name) {
    axios.get(`${REST_USER_API}/user/search/name/${name}`)
      .then((response) => {
        UserList.value = response.data
      })
  }

  const getUserByid = function (id) {
    axios.get(`${REST_USER_API}/user/search/id/${id}`)
      .then((response) => {
        User.value = response.data
      })
  }

  const checkId = function (id) {
    axios.get(`${REST_USER_API}/user/join/check/id/${id}`)
      .then((response) => {
        // HTTP 응답의 상태 코드 확인
        if (response.status === 200) {
          // 아이디 사용 가능
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
        // httpstatus를 확인해서 처리해야함
      })
  }

  const userLogin = function (id, password) {
    return new Promise((resolve, reject) => {
      axios.post(`${REST_USER_API}/user/login`, {
        id,
        password
      })
        .then((res) => {
          console.log(headers)
          sessionStorage.setItem('access-token', res.data["access-token"]);

          const token = res.data['access-token'].split('.');
          headers.value = res.headers;
          let id = JSON.parse(atob(token[1]))['id'];

          loginUserId.value = id;
          // loginUserName.value = JSON.parse(atob(token[1]))['name']; // 사용자 이름 설정

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

  // const userLogin = function (id, password) {
  //   axios.post(`${REST_USER_API}/user/login`, {
  //     id,
  //     password
  //   })
  //     .then((res) => {
  //       sessionStorage.setItem('access-token', res.data["access-token"]);

  //       const token = res.data['access-token'];
  //       const tokenPayload = token.split('.')[1];
  //       const decodedToken = JSON.parse(atob(tokenPayload));

  //       // 사용자 정보 추출
  //       const serial = decodedToken['serial'];
  //       const id = decodedToken['id'];
  //       const name = decodedToken['name'];
  //       const birthDate = decodedToken['birthDate'];
  //       const gender = decodedToken['gender'];
  //       const email = decodedToken['email'];
  //       const regDate = decodedToken['regDate'];
  //       const point = decodedToken['point'];

  //       // 각 정보를 Vue 리액티브 변수에 설정
  //       loginUserSerial.value = serial;
  //       loginUserId.value = id;
  //       loginUserName.value = name;
  //       loginUserBirthDate.value = birthDate;
  //       loginUserGender.value = gender;
  //       loginUserEmail.value = email;
  //       loginUserRegDate.value = regDate;
  //       loginUserPoint.value = point;
  //       // name, birthDate, gender, email, regDate, point 등 다른 정보도 필요한 경우 이와 같이 설정

  //       // 로그인 이후 작업 수행
  //       console.log(loginUserSerial.value);
  //       console.log(loginUserId.value);
  //       console.log(loginUserName.value);
  //       console.log(loginUserBirthDate.value);
  //       console.log(loginUserGender.value);
  //       console.log(loginUserEmail.value);
  //       console.log(loginUserRegDate.value);
  //       console.log("Point: ", loginUserPoint.value);
  //       // router.push({ name: 'MountainMainView' });
  //     })
  //     .catch((error) => {
  //       console.error("Login error: ", error);
  //     });
  // }


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



  // Follow
  const createFollow = function (fromFollow, toFollow) {
    axios.get(`${REST_USER_API}/follow/${fromFollow}/${toFollow}`)
      .then((response) => {
      })
      .catch((err) => {
        console.log(err)
      })
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

  return { User, LoginUser, UserList, FollowingList, FollowerList, getUser, getUserByName, getUserByid, checkId, checkEmail, userLogin, logoutUser, createUser, updateUser, createFollow, getfollwingList, getfollwerList, }
})
