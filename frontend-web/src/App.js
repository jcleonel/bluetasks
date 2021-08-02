import { Component } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import NavBar from "./components/NavBar";
import TaskForm from "./components/TaskForm";
import TaskListTable from "./components/TaskListTable";
import Login from "./components/Login";

class App extends Component {
  constructor(props) {
    super(props)

    this.onRefreshHandler = this.onRefreshHandler.bind(this);
  }

onRefreshHandler() {
  this.forceUpdate();
}

  render(){
    return (
      <BrowserRouter>
        <div>
          <NavBar onLinkClick={this.onRefreshHandler} />
          <div className="container" style={{ marginTop: 20}}>
          <Switch>
            <Route exact path="/login" render={() => <Login onLoginSuccess={this.onRefreshHandler} />} />
            <Route exact path="/" component={TaskListTable} /> 
            <Route exact path="/form" component={TaskForm} />
            <Route exact path="/form/:id" component={TaskForm} />
          </Switch>
          </div>          
        </div>
      </BrowserRouter>
    );
  }

}

export default App;
