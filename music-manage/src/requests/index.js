import axios from 'axios';
import {
	useRouter
} from 'vue-router';

axios.defaults.timeout = 50000;

axios.interceptors.request.use(config => {
	// ...
	return config
}, error => {
	return Promise.error(error)
});

export {
}
