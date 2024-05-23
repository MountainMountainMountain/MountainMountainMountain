import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_COMMENT_API = `http://localhost:8080/api-comment`

export const useCommentStore = defineStore('comment', () => {

  const Comment = ref({})
  const CommentList = ref([])
  const MyCommentList = ref([])
  const MountainStarList = ref([])

  const getCommentList = function (mountainSerial) {
    axios.get(`${REST_COMMENT_API}/mountain/${mountainSerial}`)
      .then((response) => {
        CommentList.value = response.data
      })
  }

  const getComment = function (commentSerial) {
    axios.get(`${REST_COMMENT_API}/comment/${commentSerial}`)
      .then((response) => {
        Comment.value = response.data

        console.log(Comment.value)
      })
  }

  const getCommentStar = function (commentSerial) {
    axios.get(`${REST_COMMENT_API}/comment/star/${commentSerial}`)
      .then((response) => {
        MountainStarList.value = response.data
      })
  }

  const searchCommentList = function (mountainSerial, searchCondition) {
    axios.get(`${REST_COMMENT_API}/comment/search/${mountainSerial}`, { params: searchCondition })
      .then((response) => {
        CommentList.value = response.data
      })
  }

  const createComment = function (comment) {
    return new Promise((resolve, reject) => {
      axios({
        url: `${REST_COMMENT_API}/comment/create`,
        method: 'POST',
        data: comment
      })
        .then(() => {
          resolve(); // 성공 시 resolve 호출
        })
        .catch((error) => {
          reject(error); // 실패 시 reject 호출
        });
    });
  };


  const updateComment = function () {
    return new Promise((resolve, reject) => {
      axios.put(`${REST_COMMENT_API}/comment/update`, Comment.value)
        .then(() => {
          resolve();
        })
        .catch((err) => {
          reject(err);
        })
    });
  }

  const completeComment = function (userSerial) {
    console.log(userSerial)
    axios.get(`${REST_COMMENT_API}/comment/userSerial/${userSerial}`)
      .then((response) => {
        MyCommentList.value = response.data
        console.log(MyCommentList.value)
      }).catch((error) => {
        console.log(MyCommentList.value)
        console.log(error)
      })
  }

  return { Comment, CommentList, MyCommentList, MountainStarList, getCommentList, getComment, getCommentStar, searchCommentList, createComment, updateComment, completeComment }
})
