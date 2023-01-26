import { createApp } from 'vue';
import App from './App.vue';
import './css/common.css';
import router from './router.js';

import { library } from '@fortawesome/fontawesome-svg-core';

/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

/* import specific icons */
// import { faHouse } from '@fortawesome/free-solid-svg-icons';

import { fas } from '@fortawesome/free-solid-svg-icons';
library.add(fas);
import { fab } from '@fortawesome/free-brands-svg-icons';
library.add(fab);
import { far } from '@fortawesome/free-regular-svg-icons';
library.add(far);

/* add icons to the library */
// library.add(faHouse);
createApp(App).use(router).component('font-awesome-icon', FontAwesomeIcon).mount('#app');
