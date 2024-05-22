import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'
import { useLink } from 'vue-router'

const REST_CHAT_API = `http://localhost:8080/api-chat`

export const useChatStore = defineStore('chat', () => {

    const Chat = ref({})
    const ChatList = ref([])
    const ChatLog = ref({})
    const ChatLogList = ref([])

    const ChatInfo = ref({})
    const ChatInfoList = ref([])
    const ChatUserManager = ref({})
    const ChatUserManagerList = ref([])

    const getChat = function (chatSerial) {
        axios.get(`${REST_CHAT_API}/chat/${chatSerial}`)
            .then((response) => {
                Chat.value = response.data
            })
            .catch((error) => {
                console.log(error)
            })
    }

    const getChatInfo = function (chatInfoSerial) {
        axios.get(`${REST_CHAT_API}/chatInfo/${chatInfoSerial}`)
            .then((response) => {
                ChatInfo.value = response.data
            })
            .catch((error) => {
                console.log(error)
            })
    }

    const getChatUserManager = function (chatUserManagerSerial) {
        axios.get(`${REST_CHAT_API}/chatManager/${chatUserManagerSerial}`)
            .then((response) => {
                ChatUserManager.value = response.data
            })
            .catch((error) => {
                console.log(error)
            })
    }

    const getChatListByChatInfoSerial = function (chatInfoSerial) {
        axios.get(`${REST_CHAT_API}/chat/bychatinfo/${chatInfoSerial}`)
            .then((response) => {
                ChatLogList.value = response.data
            })
            .catch((error) => {
                console.log(error)
            })
    }


    const createChat = function (chat) {
        return new Promise((resolve, reject) => {
            axios.post(`${REST_CHAT_API}/chat`, chat)
                .then(() => {
                    resolve();
                })
                .catch((error) => {
                    reject(error);
                })
        });
    }

    const createChatInfo = function (chatInfo) {
        return new Promise((resolve, reject) => {
            axios.post(`${REST_CHAT_API}/chatInfo`, chatInfo)
                .then(() => {
                    resolve();
                })
                .catch((error) => {
                    reject(error);
                })
        });
    }

    const createChatUserManager = function (chatUserManager) {
        return new Promise((resolve, reject) => {
            axios.post(`${REST_CHAT_API}/chatManager`, chatUserManager)
                .then(() => {
                    resolve();
                })
                .catch((error) => {
                    reject(error);
                })
        });
    }

    const updateChat = function (chatSerial) {
        return new Promise((resolve, reject) => {
            axios.post(`${REST_CHAT_API}/chat/${chatSerial}`, Chat.value)
                .then(() => {
                    resolve();
                })
                .catch((error) => {
                    reject(error);
                })
        });
    }

    const updateChatInfo = function (chatInfoSerial) {
        return new Promise((resolve, reject) => {
            axios.post(`${REST_CHAT_API}/chatInfo/${chatInfoSerial}`, ChatInfo.value)
                .then(() => {
                    resolve();
                })
                .catch((error) => {
                    reject(error);
                })
        });
    }

    const updateChatUserManager = function (chatUserManagerSerial) {
        return new Promise((resolve, reject) => {
            axios.post(`${REST_CHAT_API}/chatManager/${chatUserManagerSerial}`, ChatUserManager.value)
                .then(() => {
                    resolve();
                })
                .catch((error) => {
                    reject(error);
                })
        });
    }





    return { Chat, ChatList, ChatLog, ChatLogList, ChatInfo, ChatInfoList, ChatUserManager, ChatUserManagerList, getChat, getChatInfo, getChatUserManager, getChatListByChatInfoSerial, createChat, createChatInfo, createChatUserManager, updateChat, updateChatInfo, updateChatUserManager }
})
