import axios from 'axios';
import AuthService from '../api/AuthService';
import { API_ENDPOINT } from '../constants';

class TaskService {
    constructor() {
        this.tasks = [
            { id: 1, description: "Tarefa 1", whenToDo: "2030-01-01", done: false },
            { id: 2, description: "Tarefa 2", whenToDo: "2030-01-02", done: true },
            { id: 3, description: "Tarefa 3", whenToDo: "2030-01-03", done: false },
            { id: 4, description: "Tarefa 4", whenToDo: "2030-01-04", done: true },
            { id: 5, description: "Tarefa 5", whenToDo: "2030-01-05", done: false }
        ]
    }

    list(onFetch, onError) {
        axios.get(`${API_ENDPOINT}/tasks?sort=whenToDo,asc`, this.buildAuthHeader())
            .then(response => onFetch(response.data.content))
            .catch(e => onError(e));
    }

    load(id, onLoad, onError) {
        axios.get(`${API_ENDPOINT}/tasks/${id}`, this.buildAuthHeader())
            .then(response => onLoad(response.data))
            .catch(e => onError(e));
    }

    delete(id, onDelete, onError) {
        axios.delete(`${API_ENDPOINT}/tasks/${id}`, this.buildAuthHeader())
            .then(() => onDelete())
            .catch(e => onError(e));
    }

    save(task, onSave, onError) {
        if (task.id === 0) {
            axios.post(`${API_ENDPOINT}/tasks`, task, this.buildAuthHeader())
                .then(() => onSave())
                .catch(e => onError(e));
        
        } else {
            axios.put(`${API_ENDPOINT}/tasks/${task.id}`, task, this.buildAuthHeader())
                .then(() => onSave())
                .catch(e => onError(e));
        }
    }

    buildAuthHeader() {
        return {
            headers: {
                'Authorization': `Bearer ${AuthService.getJWTToken()}`
            }
        }
    }

}

export default new TaskService();