import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_REPLY_API = `http://localhost:8080/api-reply`

export const useReplyStore = defineStore('reply', () => {

  const Reply = ref({})
  const ReplyList = ref([])

  const getReplyList = function (commentSerial) {
    axios.get(`${REST_REPLY_API}/${commentSerial}`)
      .then((response) => {
        ReplyList.value = response.data
      })
  }

  const getReply = function (replySerail) {
    axios.get(`${REST_REPLY_API}/reply/${replySerail}`)
      .then((response) => {
        Reply.value = response.data
      })
  }

  const createReply = function (reply) {
    return new Promise((resolve, reject) => {
      axios({
        url: `${REST_REPLY_API}/reply`,
        method: 'POST',
        data: reply
      })
        .then(() => {
          router.push({ name: 'CommentDetailPage' })
          resolve();
        })
        .catch((err) => {
          console.log(err)
          reject(err);
        })
    })
  }

  const updateReply = function () {
    axios.put(REST_REPLY_API, Reply.value)
      .then(() => {
        router.push({ name: 'CommentDetailPage' })
      })
  }

  return { Reply, ReplyList, getReplyList, getReply, createReply, updateReply }
})
