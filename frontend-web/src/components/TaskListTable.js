import React, { Component } from 'react';
import { toast, ToastContainer } from 'react-toastify';
import TaskService from '../api/TaskService';
import 'react-toastify/dist/ReactToastify.css';

class TaskListTable extends Component {
    constructor(props) {
        super(props);

        this.state = {
            tasks: []
        }

        this.onDeleteHandler = this.onDeleteHandler.bind(this);
    }
  
    componentDidMount() {
        this.listTasks();
    }

    listTasks() {
        const tasks = TaskService.list();
        this.setState({ tasks: tasks });
    }

    onDeleteHandler(id){
        if (window.confirm("Deseja mesmo excluir essa tarefa?")) {
            TaskService.delete(id);
            this.listTasks(); 
            toast.success("Tarefa excluída!");
        }
    }

    render() {
        return (
            <>
                <table className="table table-striped text-center">
                <TableHeader />
                
                {this.state.tasks.length > 0 ? 
                    <TableBody 
                        tasks={this.state.tasks} 
                        onDelete={this.onDeleteHandler}
                    />
                    :
                    <EmptyTableBody />
                }
                </table>
                <ToastContainer autoClose={3500} />
           </>
        );
    }
    
}

const TableHeader = () => {
    return(
        <thead className="table-dark">
            <tr>
                <th scope="col">Status</th>
                <th scope="col">Descrição</th>
                <th scope="col">Data</th>
                <th scope="col">Ações</th>
            </tr>
        </thead>
    )
}

const TableBody = (props) => {
    return(
        <tbody>
            {props.tasks.map(task =>
                <tr key={task.id}>
                    <td><input type="checkbox" checked={task.done} /></td>
                    <td>{task.description}</td>
                    <td>{task.whenToDo}</td>
                    <td>
                        <input type="button" className="btn btn-primary" value="Editar" />&nbsp;
                        <input 
                            type="button" 
                            className="btn btn-danger" 
                            value="Excluir" 
                            onClick={() => props.onDelete(task.id)}
                        />
                    </td>
                </tr>
            )} 
        </tbody>
 
    )
}

const EmptyTableBody = (props) => {
    return (
        <tbody>
            <tr>
                <td colSpan="4">Sem tarefas cadastradas no momento!</td>
            </tr>
        </tbody>
    );
}

export default TaskListTable;