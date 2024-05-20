import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_USER_API = `http://localhost:8080/api-user`

export const useUserStore = defineStore('user', () => {

  const User = ref({})
  const LoginUser = ref({})
  const UserList = ref([])
  const FollowingList = ref([])
  const FollowerList = ref([])

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


  
  
  
  
  
  
  
  
  // Follow
  const follow = function (email) {
    axios.get(`${REST_USER_API}/user/join/check/${email}`)
      .then((response) => {
        // httpstatus를 확인해서 처리해야함
      })
  }



  return { User, LoginUser, UserList, FollowingList, FollowerList, getUser, getUserByName, getUserByid, checkId, checkEmail }
})
