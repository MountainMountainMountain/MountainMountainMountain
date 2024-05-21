import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_COMMENT_API = `http://localhost:8080/api-comment`

export const useCommentStore = defineStore('comment', () => {

  const Comment = ref({})
  const CommentList = ref([])
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
    axios({
      url: REST_COMMENT_API,
      method: 'POST',
      data: comment
    })
      .then(() => {
        router.push({ name: 'MountainListPage' })
      })
      .catch((err) => {
        console.log(err)
      })
  }

  const updateComment = function () {
    console.log(Comment.value)
    axios.put(REST_COMMENT_API, Comment.value)
      .then(() => {
        router.push({ name: 'MountainListPage' })
      })
  }

  return { Comment, CommentList, MountainStarList, getCommentList, getComment, getCommentStar, searchCommentList, createComment, updateComment }
})
