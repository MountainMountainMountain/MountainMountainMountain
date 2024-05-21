import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_USER_API = `http://localhost:8080/api-user`

export const useUserStore = defineStore('user', () => {

  const User = ref({})
  const loginUserId = ref(null)
  const LoginUser = ref({})
  const UserList = ref([])
  const FollowingList = ref([])
  const FollowerList = ref([])

  const getUser = function () {
    axios.get(`${REST_USER_API}/user`)
      .then((response) => {
        UserList.value = response.data;
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
        UserList.value = response.data
      })
  }

  const checkId = function (id) {
    axios.get(`${REST_USER_API}/user/join/check/${id}`)
      .then((response) => {
        // httpstatus를 확인해서 처리해야함
      })
  }

  const checkEmail = function (email) {
    axios.get(`${REST_USER_API}/user/join/check/${email}`)
      .then((response) => {
        // httpstatus를 확인해서 처리해야함
      })
  }

  const userLogin = function (id, password) {
    axios.post(`${REST_USER_API}/user/login`, {
      id: id,
      password: password
    })
      .then((res) => {
        sessionStorage.setItem('access-token', res.data["access-token"])

        const token = res.data['access-token'].split('.')
        let id = JSON.parse(atob(token[1]))['id']

        loginUserId.value = id;

        router.push({ name: 'MountainMainView' })

      })
      .catch(() => {

      })
  }

  const createUser = function (user) {
    axios({
      url: `${REST_USER_API}/user/join`,
      method: 'POST',
      data: user
    })
      .then(() => {
        router.push({ name: 'Login' })
      })
      .catch((err) => {
        console.log(err)
      })
  }

  const updateUser = function (userSerial) {
    axios.put(`${REST_USER_API}/user/${LoginUser.value.userSerial}`, User.value)
      .then(() => {
        router.push({ name: 'MyInfo' })
      })
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
    axios.get(`${REST_USER_API}/follow/follwingList/${toFollow}`)
      .then((response) => {
        FollowingList.value = response.data
      })
      .catch((err) => {
        console.log(err)
      })
  }

  const getfollwerList = function (fromFollow) {
    axios.get(`${REST_USER_API}/follow/follwerList/${fromFollow}`)
      .then((response) => {
        FollowerList.value = response.data
      })
      .catch((err) => {
        console.log(err)
      })
  }

  return { User, LoginUser, UserList, FollowingList, FollowerList, getUser, getUserByName, getUserByid, checkId, checkEmail, userLogin, createUser, updateUser, createFollow, getfollwingList, getfollwerList, }
})
