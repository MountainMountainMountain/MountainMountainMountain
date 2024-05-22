<template>
    <div class="container">
        <Weather />
        <div class="wrapper">
            <header>
                <div class="nav">
                    <button @click="prevMonth" class="material-icons"> <i class="bi bi-caret-left-fill"></i> </button>
                    <p class="current-date">{{ currentDate }}</p>
                    <button @click="nextMonth" class="material-icons"> <i class="bi bi-caret-right-fill"></i> </button>
                </div>
            </header>
            <div class="calendar">
                <ul class="weeks">
                    <li>Sun</li>
                    <li>Mon</li>
                    <li>Tue</li>
                    <li>Wed</li>
                    <li>Thu</li>
                    <li>Fri</li>
                    <li>Sat</li>
                </ul>
                <ul class="days">
                    <li v-for="day in days" :key="day.date" :class="{ inactive: day.inactive, active: day.isToday }" @click="selectDate(day.date)">
                        {{ day.date }}
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script setup>
import Weather from '@/components/mountain/component/Weather.vue';
import { ref, computed } from 'vue';
import Swal from 'sweetalert2';

const today = new Date();
const currentMonth = ref(today.getMonth());
const currentYear = ref(today.getFullYear());

const daysInMonth = (month, year) => {
  return new Date(year, month + 1, 0).getDate();
};

const getStartDay = (month, year) => {
  return new Date(year, month, 1).getDay();
};

const isToday = (date, month, year) => {
  return date === today.getDate() && month === today.getMonth() && year === today.getFullYear();
};

const days = computed(() => {
  const daysArray = [];
  const startDay = getStartDay(currentMonth.value, currentYear.value);
  const numOfDays = daysInMonth(currentMonth.value, currentYear.value);

  for (let i = 0; i < startDay; i++) {
    daysArray.push({
      date: '',
      inactive: true,
    });
  }

  for (let i = 1; i <= numOfDays; i++) {
    daysArray.push({
      date: i,
      isToday: isToday(i, currentMonth.value, currentYear.value),
      inactive: false,
    });
  }

  return daysArray;
});

const currentDate = computed(() => {
  const monthNames = [
    'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'
  ];
  return `${monthNames[currentMonth.value]} ${currentYear.value}`;
});

const prevMonth = () => {
  if (currentMonth.value === 0) {
    currentMonth.value = 11;
    currentYear.value--;
  } else {
    currentMonth.value--;
  }
};

const nextMonth = () => {
  if (currentMonth.value === 11) {
    currentMonth.value = 0;
    currentYear.value++;
  } else {
    currentMonth.value++;
  }
};

const selectDate = (date) => {
  if (!date) return;

  const selectedDate = new Date(currentYear.value, currentMonth.value, date);
  const formattedDate = selectedDate.toDateString();

  Swal.fire({
    title: `${formattedDate}`,
    text: "예약하시겠습니까?",
    icon: 'question',
    showCancelButton: true,
    confirmButtonText: '예',
    cancelButtonText: '아니오'
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire('예약 완료!', '', 'success');
    }
  });
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}

body {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background: #008aff;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  width: 100%;
}

.wrapper {
  width: 500px;
  background: #fff;
  border-radius: 10px;
  padding: 30px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.wrapper .nav {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 30px;
}

.wrapper .nav .current-date {
  font-size: 24px;
  font-weight: 600;
}

.wrapper .nav button {
  width: 38px;
  height: 38px;
  font-size: 30px;
  color: #878787;
}

.calendar ul {
  display: flex;
  list-style: none;
  flex-wrap: wrap;
  text-align: center;
}

.calendar .weeks li {
  font-weight: 500;
}

.calendar .days {
  margin-bottom: 20px;
}

.calendar ul li {
  width: calc(100% / 7);
  position: relative;
}

.calendar .days li {
  z-index: 1;
  margin-top: 30px;
  cursor: pointer;
}

.days li.inactive {
  color: #aaa;
}

.days li.active {
  color: #fff;
}

.calendar .days li::before {
  position: absolute;
  content: '';
  height: 40px;
  width: 40px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 50%;
  z-index: -1;
}

.days li:hover::before {
  background: #f2f2f2;
}

.days li.active::before {
  background: #008aff;
}
</style>
