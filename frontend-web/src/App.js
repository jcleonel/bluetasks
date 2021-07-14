import { Component } from "react";
import { BrowserRouter } from "react-router-dom";
import NavBar from "./components/NavBar";
import TaskListTable from "./components/TaskListTable";

class App extends Component {
//  constructor(props) {
//    super(props)
//  }

  render(){
    return (
      <BrowserRouter>
        <div>
          <NavBar />
          <div className="container" style={{ marginTop: 20}}>
            <TaskListTable />
          </div>
          
        </div>
      </BrowserRouter>
    );
  }

}

export default App;
